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

package org.laokou.common.i18n.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.laokou.common.i18n.common.constant.EventType;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

/**
 * 领域事件.
 *
 * @author laokou
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class DomainEvent<ID> implements Serializable {

	@Serial
	private static final long serialVersionUID = 1532877866226749304L;

	/**
	 * ID.
	 */
	protected ID id;

	/**
	 * 聚合根ID.
	 */
	protected ID aggregateId;

	/**
	 * 事件类型.
	 */
	protected EventType eventType;

	/**
	 * MQ主题.
	 */
	protected String topic;

	/**
	 * 标签.
	 */
	protected String tag;

	/**
	 * 数据源名称.
	 */
	protected String sourceName;

	/**
	 * 应用服务ID.
	 */
	protected String serviceId;

	/**
	 * 创建人.
	 */
	protected ID creator;

	/**
	 * 编辑人.
	 */
	protected ID editor;

	/**
	 * 租户ID.
	 */
	protected ID tenantId;

	/**
	 * 创建时间.
	 */
	protected Instant createTime;

	/**
	 * 修改时间.
	 */
	protected Instant updateTime;

	protected abstract void generatorId();

}
