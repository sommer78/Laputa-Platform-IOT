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

package org.laokou.auth.config;

import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.laokou.common.core.context.ShutdownHolder;
import org.laokou.common.core.utils.ResponseUtil;
import org.laokou.common.i18n.dto.Result;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.laokou.common.i18n.common.exception.StatusCode.SERVICE_UNAVAILABLE;

/**
 * @author laokou
 */
@NonNullApi
public class UsernamePasswordFilter extends OncePerRequestFilter {

	public static final UsernamePasswordFilter INSTANCE = new UsernamePasswordFilter();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		if (ShutdownHolder.status()) {
			ResponseUtil.responseOk(response, Result.fail(SERVICE_UNAVAILABLE));
			return;
		}
		chain.doFilter(request, response);
	}

}
