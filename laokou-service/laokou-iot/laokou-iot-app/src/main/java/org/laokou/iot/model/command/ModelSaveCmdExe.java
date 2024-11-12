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

package org.laokou.iot.model.command;

import lombok.RequiredArgsConstructor;
import org.laokou.common.mybatisplus.utils.TransactionalUtil;
import org.laokou.iot.model.dto.ModelSaveCmd;
import org.springframework.stereotype.Component;
import org.laokou.iot.model.convertor.ModelConvertor;
import org.laokou.iot.model.ability.ModelDomainService;

/**
 *
 * 保存模型命令执行器.
 *
 * @author laokou
 */
@Component
@RequiredArgsConstructor
public class ModelSaveCmdExe {

	private final ModelDomainService modelDomainService;

	private final TransactionalUtil transactionalUtil;

	public void executeVoid(ModelSaveCmd cmd) {
		// 校验参数
		transactionalUtil.executeInTransaction(() -> modelDomainService.create(ModelConvertor.toEntity(cmd.getCo())));
	}

}
