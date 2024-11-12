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

package org.laokou.common.redis.utils;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author laokou
 */
public final class RedisKeyUtil {

	/**
	 * 验证码Key.
	 * @param uuid UUID
	 */
	public static String getUserCaptchaKey(String uuid) {
		String key = "user:captcha:" + uuid;
		return DigestUtils.md5DigestAsHex(key.getBytes(StandardCharsets.UTF_8));
	}

	/**
	 * 布隆过滤器Key.
	 */
	public static String getBloomFilterKey() {
		return "bloom:filter";
	}

	/**
	 * OSS配置Key.
	 * @param tenantId 租户ID
	 */
	public static String getOssConfigKey(Long tenantId) {
		return "oss:config:" + tenantId;
	}

	/**
	 * 手机验证码Key.
	 * @param mobile 手机号
	 */
	public static String getMobileCaptchaKey(String mobile) {
		return getUserCaptchaKey(mobile);
	}

	/**
	 * 邮箱验证码Key.
	 * @param mail 邮箱
	 */
	public static String getMailCaptchaKey(String mail) {
		return getUserCaptchaKey(mail);
	}

	/**
	 * 接口幂等性Key.
	 * @param token 令牌
	 */
	public static String getApiIdempotentKey(String token) {
		return "api:idempotent:" + token;
	}

	/**
	 * 动态路由Key.
	 */
	public static String getRouteDefinitionHashKey() {
		return "route:definition";
	}

	/**
	 * IP缓存Key.
	 * @param type 类型
	 */
	public static String getIpCacheHashKey(String type) {
		return "ip:cache:" + type;
	}

}
