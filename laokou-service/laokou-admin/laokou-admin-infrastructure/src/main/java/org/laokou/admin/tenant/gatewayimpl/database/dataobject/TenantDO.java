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

package org.laokou.admin.tenant.gatewayimpl.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.laokou.common.mybatisplus.mapper.BaseDO;

/**
 * 租户数据对象.
 *
 * @author laokou
 */
@Data
@TableName("boot_sys_tenant")
public class TenantDO extends BaseDO {

	/**
	 * 租户名称.
	 */
	private String name;

	/**
	 * 租户标签.
	 */
	private String label;

	/**
	 * 数据源ID.
	 */
	private Long sourceId;

	/**
	 * 套餐ID.
	 */
	private Long packageId;

}
