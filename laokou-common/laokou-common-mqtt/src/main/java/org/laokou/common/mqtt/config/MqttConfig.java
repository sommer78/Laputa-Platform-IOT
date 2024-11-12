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

package org.laokou.common.mqtt.config;

import org.laokou.common.mqtt.template.MqttTemplate;
import org.springframework.context.annotation.Bean;

/**
 * @author laokou
 */
public class MqttConfig {

	@Bean(name = "mqttManager", initMethod = "open", destroyMethod = "close")
	public MqttManager mqttManager(SpringMqttBrokerProperties springMqttBrokerProperties,
			MqttLoadBalancer mqttLoadBalancer) {
		return new MqttManager(springMqttBrokerProperties, mqttLoadBalancer);
	}

	@Bean
	public MqttTemplate mqttTemplate(MqttManager mqttManager) {
		return new MqttTemplate(mqttManager);
	}

}
