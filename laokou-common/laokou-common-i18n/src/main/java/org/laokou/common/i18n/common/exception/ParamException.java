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

package org.laokou.common.i18n.common.exception;

/**
 * 参数异常.
 *
 * @author laokou
 */
public final class ParamException extends GlobalException {

	/**
	 * UUID不能为空.
	 */
	public static final String OAUTH2_UUID_REQUIRE = "P_OAuth2_UuidIsNull";

	/**
	 * 验证码不能为空.
	 */
	public static final String OAUTH2_CAPTCHA_REQUIRE = "P_OAuth2_CaptchaIsNull";

	/**
	 * 用户名不能为空.
	 */
	public static final String OAUTH2_USERNAME_REQUIRE = "P_OAuth2_UsernameIsNull";

	/**
	 * 密码不能为空.
	 */
	public static final String OAUTH2_PASSWORD_REQUIRE = "P_OAuth2_PasswordIsNull";

	/**
	 * 手机号不能为空.
	 */
	public static final String OAUTH2_MOBILE_REQUIRE = "P_OAuth2_MobileIsNUll";

	/**
	 * 邮箱不能为空.
	 */
	public static final String OAUTH2_MAIL_REQUIRE = "P_OAuth2_MailIsNull";

	/**
	 * 租户ID不能为空.
	 */
	public static final String OAUTH2_TENANT_ID_REQUIRE = "P_OAuth2_TenantIdIsNull";

	/**
	 * ID不能为空.
	 */
	public static final String SYSTEM_ID_REQUIRE = "P_System_IdIsNull";

	/**
	 * 邮箱错误.
	 */
	public static final String OAUTH2_MAIL_ERROR = "A_OAuth2_MailError";

	/**
	 * 手机号错误.
	 */
	public static final String OAUTH2_MOBILE_ERROR = "A_OAuth2_MobileError";

	public ParamException(String code) {
		super(code);
	}

	public ParamException(String code, String msg) {
		super(code, msg);
	}

}
