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

package org.laokou.auth.dto.domainevent;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.laokou.common.core.utils.IdGenerator;
import org.laokou.common.i18n.common.constant.EventType;
import org.laokou.common.i18n.dto.DefaultDomainEvent;
import org.laokou.common.i18n.utils.DateUtil;

/**
 * @author laokou
 */
@Data
@NoArgsConstructor
public class SendCaptchaEvent extends DefaultDomainEvent {

	private String uuid;

	public SendCaptchaEvent(String uuid, String topic, String tag, EventType eventType, String serviceId,
			String sourceName, Long tenantId) {
		super(topic, tag, eventType, serviceId, sourceName, DateUtil.nowInstant(), IdGenerator.defaultSnowflakeId(),
				tenantId);
		this.uuid = uuid;
	}

	@Override
	protected void generatorId() {
		super.id = IdGenerator.defaultSnowflakeId();
	}

}
