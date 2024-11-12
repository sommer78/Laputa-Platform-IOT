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

package org.laokou.admin.role.convertor;

import org.laokou.admin.role.gatewayimpl.database.dataobject.RoleDO;
import org.laokou.common.core.utils.ConvertUtil;
import org.laokou.common.i18n.utils.ObjectUtil;
import org.laokou.admin.role.dto.clientobject.RoleCO;
import org.laokou.admin.role.model.RoleE;

/**
 * 角色转换器.
 *
 * @author laokou
 */
public class RoleConvertor {

	public static RoleDO toDataObject(RoleE roleE) {
		RoleDO roleDO = ConvertUtil.sourceToTarget(roleE, RoleDO.class);
		if (ObjectUtil.isNull(roleDO.getId())) {
			roleDO.generatorId();
		}
		return roleDO;
	}

	public static RoleCO toClientObject(RoleDO roleDO) {
		return ConvertUtil.sourceToTarget(roleDO, RoleCO.class);
	}

	public static RoleE toEntity(RoleCO roleCO) {
		return ConvertUtil.sourceToTarget(roleCO, RoleE.class);
	}

}
