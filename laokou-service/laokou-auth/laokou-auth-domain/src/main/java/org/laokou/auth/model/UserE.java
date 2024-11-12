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

package org.laokou.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.laokou.common.crypto.utils.AESUtil;
import org.laokou.common.i18n.utils.ObjectUtil;

import static org.laokou.common.i18n.common.constant.SuperAdmin.YES;

/**
 * 用户实体.
 *
 * @author laokou
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserE {

	/**
	 * ID.
	 */
	private Long id;

	/**
	 * 用户名.
	 */
	private String username;

	/**
	 * 密码.
	 */
	private String password;

	/**
	 * 超级管理员标识 0否 1是.
	 */
	private Integer superAdmin;

	/**
	 * 头像.
	 */
	private String avatar;

	/**
	 * 邮箱.
	 */
	private String mail;

	/**
	 * 用户状态 0启用 1禁用.
	 */
	private Integer status;

	/**
	 * 手机号.
	 */
	private String mobile;

	/**
	 * 租户ID.
	 */
	private Long tenantId;

	public UserE(String username, String mail, String mobile, Long tenantId) {
		this.username = AESUtil.encrypt(username);
		this.mail = AESUtil.encrypt(mail);
		this.mobile = AESUtil.encrypt(mobile);
		this.tenantId = tenantId;
	}

	public UserE(Long tenantId) {
		this.tenantId = tenantId;
	}

	public boolean isSuperAdministrator() {
		return ObjectUtil.equals(YES.ordinal(), this.superAdmin);
	}

	public boolean isDefaultTenant() {
		return ObjectUtil.equals(0L, this.tenantId);
	}

}
