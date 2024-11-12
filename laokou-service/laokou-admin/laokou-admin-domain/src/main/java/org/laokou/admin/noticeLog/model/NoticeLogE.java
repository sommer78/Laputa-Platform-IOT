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

package org.laokou.admin.noticeLog.model;

import lombok.Data;

/**
 * 通知日志领域对象【实体】.
 *
 * @author laokou
 */
@Data
public class NoticeLogE {

	/**
	 * ID.
	 */
	private Long id;

	/**
	 * Api编码.
	 */
	private String code;

	/**
	 * Api名称.
	 */
	private String name;

	/**
	 * Api状态 0成功 1失败.
	 */
	private Integer status;

	/**
	 * 错误信息.
	 */
	private String errorMessage;

	/**
	 * Api参数.
	 */
	private String param;

}
