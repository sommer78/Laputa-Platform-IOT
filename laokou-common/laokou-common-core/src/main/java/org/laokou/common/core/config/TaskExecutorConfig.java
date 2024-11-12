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

package org.laokou.common.core.config;

import com.alibaba.ttl.threadpool.TtlExecutors;
import org.laokou.common.core.utils.SpringUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 异步配置.
 *
 * @author laokou
 */
public class TaskExecutorConfig {

	/**
	 * 线程池名称.
	 */
	public static final String THREAD_POOL_TASK_EXECUTOR_NAME = "executor";

	@Bean(value = THREAD_POOL_TASK_EXECUTOR_NAME)
	public Executor executor(SpringTaskExecutionProperties springTaskExecutionProperties, SpringUtil springUtil) {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		// 核心池大小
		executor.setCorePoolSize(springTaskExecutionProperties.getPool().getCoreSize());
		// 最大线程数
		executor.setMaxPoolSize(springTaskExecutionProperties.getPool().getMaxSize());
		// 队列容量
		executor.setQueueCapacity(springTaskExecutionProperties.getPool().getQueueCapacity());
		executor.setThreadPriority(Thread.MAX_PRIORITY);
		executor.setDaemon(false);
		executor.setAllowCoreThreadTimeOut(springTaskExecutionProperties.getPool().isAllowCoreThreadTimeout());
		// 线程空闲时间
		executor.setKeepAliveSeconds((int) springTaskExecutionProperties.getPool().getKeepAlive().toSeconds());
		// 线程名称
		executor.setThreadNamePrefix("ttl-task-");
		// 初始化
		executor.initialize();
		return TtlExecutors.getTtlExecutorService(executor.getThreadPoolExecutor());
	}

}
