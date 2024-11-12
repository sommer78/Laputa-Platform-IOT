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

package org.laokou.common.security.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import org.laokou.common.core.utils.IdGenerator;
import org.laokou.common.crypto.utils.AESUtil;
import org.laokou.common.i18n.common.exception.SystemException;
import org.laokou.common.i18n.dto.Identifier;
import org.laokou.common.i18n.utils.ObjectUtil;
import org.laokou.common.i18n.utils.StringUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

import java.io.Serial;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.laokou.common.i18n.common.constant.SuperAdmin.YES;
import static org.laokou.common.i18n.common.exception.SystemException.*;

/**
 * 用户详细信息. JsonTypeInfo.Id.NAME => 多态子类与抽象类绑定.
 *
 * @author laokou
 */
@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class UserDetail extends Identifier<Long> implements UserDetails, OAuth2AuthenticatedPrincipal {

	@Serial
	private static final long serialVersionUID = 3319752558160144611L;

	/**
	 * 部门PATHS.
	 */
	private final Set<String> deptPaths;

	/**
	 * 菜单权限标识集合.
	 */
	private final Set<String> permissions;

	/**
	 * 用户名.
	 */
	private String username;

	/**
	 * 头像.
	 */
	private String avatar;

	/**
	 * 超级管理员标识 0否 1是.
	 */
	private Integer superAdmin;

	/**
	 * 用户状态 0启用 1禁用.
	 */
	private Integer status;

	/**
	 * 邮箱.
	 */
	private String mail;

	/**
	 * 手机号.
	 */
	private String mobile;

	/**
	 * 密码.
	 */
	@JsonIgnore
	private transient String password;

	/**
	 * 租户ID.
	 */
	private Long tenantId;

	/**
	 * 数据源名称.
	 */
	private String sourceName;

	public UserDetail() {
		super(IdGenerator.defaultSnowflakeId());
		this.deptPaths = Collections.emptySet();
		this.permissions = Collections.emptySet();
	}

	public UserDetail(Long id, String username, String password, String avatar, Integer superAdmin, Integer status,
			String mail, String mobile, Set<String> deptPaths, Set<String> permissions, Long tenantId,
			String sourceName) {
		super(id);
		this.username = username;
		this.password = password;
		this.avatar = avatar;
		this.superAdmin = superAdmin;
		this.status = status;
		this.mail = mail;
		this.mobile = mobile;
		this.deptPaths = deptPaths;
		this.permissions = permissions;
		this.tenantId = tenantId;
		this.sourceName = sourceName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (ObjectUtil.isNull(o) || getClass() != o.getClass()) {
			return false;
		}
		UserDetail that = (UserDetail) o;
		if (!id.equals(that.id)) {
			return false;
		}
		if (!username.equals(that.username)) {
			return false;
		}
		if (!avatar.equals(that.avatar)) {
			return false;
		}
		if (!superAdmin.equals(that.superAdmin)) {
			return false;
		}
		if (!status.equals(that.status)) {
			return false;
		}
		if (!deptPaths.equals(that.deptPaths)) {
			return false;
		}
		if (!permissions.equals(that.permissions)) {
			return false;
		}
		if (!tenantId.equals(that.tenantId)) {
			return false;
		}
		if (!sourceName.equals(that.sourceName)) {
			return false;
		}
		if (!mobile.equals(that.mobile)) {
			return false;
		}
		return mail.equals(that.mail);
	}

	@Override
	public int hashCode() {
		int result = id.hashCode();
		result = 31 * result + username.hashCode();
		result = 31 * result + avatar.hashCode();
		result = 31 * result + superAdmin.hashCode();
		result = 31 * result + status.hashCode();
		result = 31 * result + deptPaths.hashCode();
		result = 31 * result + permissions.hashCode();
		result = 31 * result + tenantId.hashCode();
		result = 31 * result + sourceName.hashCode();
		result = 31 * result + mail.hashCode();
		result = 31 * result + mobile.hashCode();
		return result;
	}

	@JsonIgnore
	public boolean isSuperAdministrator() {
		return ObjectUtil.equals(YES.ordinal(), this.superAdmin);
	}

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return true;
	}

	/**
	 * Get the OAuth 2.0 token attributes.
	 * @return the OAuth 2.0 token attributes
	 */
	@Override
	@JsonIgnore
	public Map<String, Object> getAttributes() {
		return Collections.emptyMap();
	}

	@Override
	@JsonIgnore
	public String getName() {
		return this.username;
	}

	public void decrypt() {
		decryptMail();
		decryptMobile();
		decryptUsername();
	}

	private void decryptUsername() {
		if (StringUtil.isNotEmpty(this.username)) {
			try {
				this.username = AESUtil.decrypt(this.username);
			}
			catch (Exception e) {
				throw new SystemException(USERNAME_AES_DECRYPT_FAIL);
			}
		}
	}

	private void decryptMail() {
		if (StringUtil.isNotEmpty(this.mail)) {
			try {
				this.mail = AESUtil.decrypt(this.mail);
			}
			catch (Exception e) {
				throw new SystemException(MAIL_AES_DECRYPT_FAIL);
			}
		}
	}

	private void decryptMobile() {
		if (StringUtil.isNotEmpty(this.mobile)) {
			try {
				this.mobile = AESUtil.decrypt(this.mobile);
			}
			catch (Exception e) {
				throw new SystemException(MOBILE_AES_DECRYPT_FAIL);
			}
		}
	}

}
