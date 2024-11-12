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

package org.laokou.gateway.filter.ip;

import lombok.Data;
import org.laokou.common.i18n.common.exception.SystemException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * IP配置.
 *
 * @author laokou
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.cloud.gateway.ip")
public class IpProperties implements InitializingBean {

	private White white = new White();

	private Black black = new Black();

	@Override
	public void afterPropertiesSet() {
		if (white.enabled && black.enabled) {
			throw new SystemException("S_Gateway_IpConfigError", "IP配置错误");
		}
	}

	@Data
	public static class White {

		/**
		 * 白名单开关，默认不开启.
		 */
		private boolean enabled = false;

	}

	@Data
	public static class Black {

		/**
		 * 黑名单开关，默认不开启.
		 */
		private boolean enabled = false;

	}

}
