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

package org.laokou.gateway.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.laokou.gateway.filter.ip.Ip;
import org.laokou.gateway.utils.ReactiveI18nUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.handler.predicate.RemoteAddrRoutePredicateFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * IP过滤器. see {@link RemoteAddrRoutePredicateFactory}.
 *
 * @author laokou
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class IpFilter implements GlobalFilter, Ordered {

	private final Ip ip;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		try {
			// 国际化
			ReactiveI18nUtil.set(exchange);
			return validate(exchange, chain);
		}
		finally {
			ReactiveI18nUtil.reset();
		}
	}

	@Override
	public int getOrder() {
		return HIGHEST_PRECEDENCE + 1000;
	}

	/**
	 * 校验IP并响应.
	 * @param exchange 服务网络交换机
	 * @param chain 链式过滤器
	 * @return 响应结果
	 */
	private Mono<Void> validate(ServerWebExchange exchange, GatewayFilterChain chain) {
		return ip.validate(exchange, chain);
	}

}
