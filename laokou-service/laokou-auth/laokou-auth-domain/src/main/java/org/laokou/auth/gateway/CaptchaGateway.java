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

package org.laokou.auth.gateway;

/**
 * 验证码.
 *
 * @author laokou
 */
public interface CaptchaGateway {

	/**
	 * 写入Redis.
	 * @param uuid UUID
	 * @param captcha 验证码
	 */
	void setValue(String uuid, String captcha);

	/**
	 * 检查验证码.
	 * @param uuid UUID
	 * @param code 验证码
	 * @return 校验结果
	 */
	Boolean checkValue(String uuid, String code);

	/**
	 * 获取key.
	 * @param uuid UUID
	 * @return key
	 */
	String getKey(String uuid);

}
