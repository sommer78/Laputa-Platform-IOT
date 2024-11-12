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

package org.laokou.iot.productModel.service;

import lombok.RequiredArgsConstructor;
import org.laokou.iot.productModel.api.ProductModelsServiceI;
import org.laokou.iot.productModel.command.*;
import org.laokou.iot.productModel.command.query.ProductModelGetQryExe;
import org.laokou.iot.productModel.command.query.ProductModelPageQryExe;
import org.laokou.iot.productModel.dto.*;
import org.laokou.iot.productModel.dto.clientobject.ProductModelCO;
import org.laokou.common.i18n.dto.Page;
import org.laokou.common.i18n.dto.Result;
import org.springframework.stereotype.Service;

/**
 *
 * 产品模型接口实现类.
 *
 * @author laokou
 */
@Service
@RequiredArgsConstructor
public class ProductModelsServiceImpl implements ProductModelsServiceI {

	private final ProductModelSaveCmdExe productModelSaveCmdExe;

	private final ProductModelModifyCmdExe productModelModifyCmdExe;

	private final ProductModelRemoveCmdExe productModelRemoveCmdExe;

	private final ProductModelImportCmdExe productModelImportCmdExe;

	private final ProductModelExportCmdExe productModelExportCmdExe;

	private final ProductModelPageQryExe productModelPageQryExe;

	private final ProductModelGetQryExe productModelGetQryExe;

	@Override
	public void save(ProductModelSaveCmd cmd) {
		productModelSaveCmdExe.executeVoid(cmd);
	}

	@Override
	public void modify(ProductModelModifyCmd cmd) {
		productModelModifyCmdExe.executeVoid(cmd);
	}

	@Override
	public void remove(ProductModelRemoveCmd cmd) {
		productModelRemoveCmdExe.executeVoid(cmd);
	}

	@Override
	public void importI(ProductModelImportCmd cmd) {
		productModelImportCmdExe.executeVoid(cmd);
	}

	@Override
	public void export(ProductModelExportCmd cmd) {
		productModelExportCmdExe.executeVoid(cmd);
	}

	@Override
	public Result<Page<ProductModelCO>> page(ProductModelPageQry qry) {
		return productModelPageQryExe.execute(qry);
	}

	@Override
	public Result<ProductModelCO> getById(ProductModelGetQry qry) {
		return productModelGetQryExe.execute(qry);
	}

}
