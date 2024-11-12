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

package org.laokou.admin.user.api;

import org.laokou.admin.user.dto.*;
import org.laokou.admin.user.dto.clientobject.UserCO;
import org.laokou.admin.user.dto.clientobject.UserProfileCO;
import org.laokou.common.i18n.dto.Page;
import org.laokou.common.i18n.dto.Result;

/**
 * 用户接口.
 *
 * @author laokou
 */
public interface UsersServiceI {

	/**
	 * 保存用户.
	 * @param cmd 保存命令
	 */
	void save(UserSaveCmd cmd);

	/**
	 * 修改用户.
	 * @param cmd 修改命令
	 */
	void modify(UserModifyCmd cmd);

	/**
	 * 删除用户.
	 * @param cmd 删除命令
	 */
	void remove(UserRemoveCmd cmd);

	/**
	 * 导入用户.
	 * @param cmd 导入命令
	 */
	void importI(UserImportCmd cmd);

	/**
	 * 导出用户.
	 * @param cmd 导出命令
	 */
	void export(UserExportCmd cmd);

	/**
	 * 分页查询用户.
	 * @param qry 分页查询请求
	 */
	Result<Page<UserCO>> page(UserPageQry qry);

	/**
	 * 查看用户.
	 * @param qry 查看请求
	 */
	Result<UserCO> getById(UserGetQry qry);

	/**
	 * 查看个人信息.
	 */
	Result<UserProfileCO> getProfile();

}
