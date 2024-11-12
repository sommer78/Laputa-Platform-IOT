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

package org.laokou.auth.service.authentication;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.laokou.auth.factory.AuthFactory;
import org.laokou.auth.model.AuthA;
import org.laokou.common.core.utils.RequestUtil;
import org.laokou.common.i18n.common.exception.AuthException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 用户认证.
 *
 * @author laokou
 */
@Slf4j
@RequiredArgsConstructor
@Component("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

	private final OAuth2AuthenticationProvider authProvider;

	/**
	 * 获取用户信息.
	 * @param username 用户名
	 * @return 用户信息
	 * @throws UsernameNotFoundException 异常
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			AuthA auth = AuthFactory.authorizationCode(RequestUtil.getHttpServletRequest());
			auth.createUserByAuthorizationCode();
			return (UserDetails) authProvider.authentication(auth).getPrincipal();
		}
		catch (AuthException e) {
			throw new UsernameNotFoundException(e.getMsg(), e);
		}
	}

}
