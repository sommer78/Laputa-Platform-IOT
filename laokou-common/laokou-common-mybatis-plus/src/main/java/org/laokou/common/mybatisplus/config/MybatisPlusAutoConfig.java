/*
 * Copyright (c) 2022-2024 KCloud-Platform-IoT Author or Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.laokou.common.mybatisplus.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.parser.JsqlParserGlobal;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.SneakyThrows;
import org.laokou.common.core.utils.ThreadUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionOperations;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * mybatis-plus配置.
 *
 * @author laokou
 */
@AutoConfiguration
@ConditionalOnClass({ DataSource.class })
@MapperScan("org.laokou.common.mybatisplus.mapper")
public class MybatisPlusAutoConfig {

	static {
		JsqlParserGlobal.setJsqlParseCache(new FurySerialCaffeineJsqlParseCache(
				Caffeine.newBuilder().maximumSize(4096).expireAfterWrite(10, TimeUnit.MINUTES).build(),
				ThreadUtil.newVirtualTaskExecutor(), true));
	}

	@Bean
	public ConfigurationCustomizer slowSqlConfigurationCustomizer(MybatisPlusExtProperties mybatisPlusExtProperties) {
		return configuration -> {
			// 异步查询count
			configuration.addInterceptor(new AsyncCountInterceptor());
			// 慢SQL
			SqlMonitorInterceptor sqlMonitorInterceptor = new SqlMonitorInterceptor(mybatisPlusExtProperties);
			configuration.addInterceptor(sqlMonitorInterceptor);
		};
	}

	// @formatter:off
	/**
	 * 注意: 使用多个功能需要注意顺序关系,建议使用如下顺序
	 * 											- 多租户，动态表名
	 * 											- 分页，乐观锁
	 * 											- sql性能规范，防止全表更新与删除
	 * 总结：对 sql进行单次改造的优先放入,不对 sql 进行改造的最后放入.
	 * @param mybatisPlusExtProperties mybatis配置
	 */
	// @formatter:on
	@Bean
	@ConditionalOnMissingBean(MybatisPlusInterceptor.class)
	public MybatisPlusInterceptor mybatisPlusInterceptor(MybatisPlusExtProperties mybatisPlusExtProperties,
			DataSource dataSource) {
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		// 数据权限插件
		interceptor.addInnerInterceptor(new DataFilterInterceptor());
		// 多租户插件
		if (mybatisPlusExtProperties.getTenant().isEnabled()) {
			interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(
					new GlobalTenantLineHandler(mybatisPlusExtProperties.getTenant().getIgnoreTables())));
		}
		// 动态表名插件
		DynamicTableNameInnerInterceptor dynamicTableNameInnerInterceptor = new DynamicTableNameInnerInterceptor();
		dynamicTableNameInnerInterceptor.setTableNameHandler(new DynamicTableNameHandler());
		interceptor.addInnerInterceptor(dynamicTableNameInnerInterceptor);
		// 分页插件
		interceptor.addInnerInterceptor(asyncPaginationInnerInterceptor(dataSource));
		// 乐观锁插件
		interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
		// 防止全表更新与删除插件
		interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
		return interceptor;
	}

	/**
	 * <a href="https://baomidou.com/pages/568eb2/#spring-boot">...</a>
	 * 生成雪花算法就是使用的这个，请查看{@link IdWorker}
	 * 为什么要修改这个配置，集群环境可能会重复，网上的解决方案是加网卡信息（用zookeeper生成分布式ID也是可以哦，重写生成雪花算法的逻辑，mp官网支持自定义雪花算法生成）
	 */
	@Bean
	@SneakyThrows
	public IdentifierGenerator identifierGenerator() {
		// 查看 DefaultIdentifierGenerator 可以自定义网卡信息哦，话不多说，就是怼源码
		return new DefaultIdentifierGenerator(InetAddress.getLocalHost());
	}

	@Bean(name = "transactionTemplate")
	@ConditionalOnMissingBean(TransactionOperations.class)
	public TransactionTemplate transactionTemplate(PlatformTransactionManager transactionManager) {
		TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
		// 只读事务
		transactionTemplate.setReadOnly(false);
		// 新建事务
		transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		// 数据库隔离级别 => 读已提交
		transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
		// 事务超时时间，单位s
		transactionTemplate.setTimeout(180);
		// 事务名称
		transactionTemplate.setName("laokou-transaction-template");
		return transactionTemplate;
	}

	/**
	 * 异步分页. 解除每页500条限制.
	 */
	private AsyncPaginationInnerInterceptor asyncPaginationInnerInterceptor(DataSource dataSource) {
		// 使用postgresql，如果使用其他数据库，需要修改DbType
		// 使用postgresql，如果使用其他数据库，需要修改DbType
		// 使用postgresql，如果使用其他数据库，需要修改DbType
		ExecutorService executor = ThreadUtil.newVirtualTaskExecutor();
		AsyncPaginationInnerInterceptor asyncPaginationInnerInterceptor = new AsyncPaginationInnerInterceptor(
				DbType.POSTGRE_SQL, dataSource, executor);
		// -1表示不受限制
		asyncPaginationInnerInterceptor.setMaxLimit(-1L);
		// 溢出总页数后是进行处理，查看源码就知道是干啥的
		asyncPaginationInnerInterceptor.setOverflow(true);
		return asyncPaginationInnerInterceptor;
	}

}
