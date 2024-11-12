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

package org.laokou.iot.model.command.query;

import lombok.RequiredArgsConstructor;
import org.laokou.iot.model.dto.ModelGetQry;
import org.laokou.iot.model.dto.clientobject.ModelCO;
import org.laokou.iot.model.gatewayimpl.database.ModelMapper;
import org.laokou.common.i18n.dto.Result;
import org.springframework.stereotype.Component;
import org.laokou.iot.model.convertor.ModelConvertor;

/**
 * 查看模型请求执行器.
 *
 * @author laokou
 */
@Component
@RequiredArgsConstructor
public class ModelGetQryExe {

	private final ModelMapper modelMapper;

	public Result<ModelCO> execute(ModelGetQry qry) {
		return Result.ok(ModelConvertor.toClientObject(modelMapper.selectById(qry.getId())));
	}

}
