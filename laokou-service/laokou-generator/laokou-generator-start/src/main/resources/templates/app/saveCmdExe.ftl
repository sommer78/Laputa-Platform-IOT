// @formatter:off
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

package ${packageName}.${instanceName}.command;

import lombok.RequiredArgsConstructor;
import ${packageName}.${instanceName}.dto.${className}SaveCmd;
import org.springframework.stereotype.Component;
import ${packageName}.${instanceName}.convertor.${className}Convertor;
import ${packageName}.${instanceName}.ability.${className}DomainService;
import org.laokou.common.mybatisplus.utils.TransactionalUtil;

/**
 *
 * 保存${comment}命令执行器.
 *
 * @author ${author}
 */
@Component
@RequiredArgsConstructor
public class ${className}SaveCmdExe {

	private final ${className}DomainService ${instanceName}DomainService;

    private final TransactionalUtil transactionalUtil;

	public void executeVoid(${className}SaveCmd cmd) {
		// 校验参数
        transactionalUtil.executeInTransaction(() -> ${instanceName}DomainService.create(${className}Convertor.toEntity(cmd.getCo())));
	}

}
// @formatter:on
