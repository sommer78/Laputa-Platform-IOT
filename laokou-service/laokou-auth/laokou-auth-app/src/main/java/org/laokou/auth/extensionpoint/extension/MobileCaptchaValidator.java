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

package org.laokou.auth.extensionpoint.extension;

import org.laokou.auth.extensionpoint.CaptchaValidatorExtPt;
import org.laokou.common.core.utils.RegexUtil;
import org.laokou.common.extension.Extension;
import org.laokou.common.i18n.common.exception.AuthException;
import org.laokou.common.i18n.utils.ValidatorUtil;

import static org.laokou.auth.common.constant.MqConstant.MOBILE_TAG;
import static org.laokou.auth.dto.CaptchaSendCmd.USE_CASE_CAPTCHA;
import static org.laokou.common.i18n.common.constant.Constant.SCENARIO;
import static org.laokou.common.i18n.common.exception.ParamException.OAUTH2_MOBILE_ERROR;

/**
 * @author laokou
 */
@Extension(bizId = MOBILE_TAG, useCase = USE_CASE_CAPTCHA, scenario = SCENARIO)
public class MobileCaptchaValidator implements CaptchaValidatorExtPt {

	@Override
	public void validate(String uuid) {
		// 手机号格式判断
		if (!RegexUtil.mobileRegex(uuid)) {
			throw new AuthException(OAUTH2_MOBILE_ERROR, ValidatorUtil.getMessage(OAUTH2_MOBILE_ERROR));
		}
	}

}
