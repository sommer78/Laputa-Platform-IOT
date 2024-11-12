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

package org.laokou.auth.api;

import org.laokou.auth.dto.TenantIDGetQry;
import org.laokou.common.i18n.dto.Option;
import org.laokou.common.i18n.dto.Result;

import java.util.List;

/**
 * 租户管理.
 *
 * @author laokou
 */
public interface TenantsServiceI {

	/**
	 * 查询租户下拉框选择项列表.
	 * @return 租户下拉框选择项列表
	 */
	Result<List<Option>> listOption();

	/**
	 * 根据域名查看租户ID.
	 * @param qry 根据域名查看租户ID
	 * @return 租户ID
	 */
	Result<Long> getIdByDomainName(TenantIDGetQry qry);

}
