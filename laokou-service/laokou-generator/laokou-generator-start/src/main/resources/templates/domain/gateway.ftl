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

package ${packageName}.${instanceName}.gateway;

import ${packageName}.${instanceName}.model.${className}E;

/**
 *
 * ${comment}网关【防腐】.
 *
 * @author ${author}
 */
public interface ${className}Gateway {

    /**
     * 新增${comment}.
     */
	void create(${className}E ${instanceName}E);

    /**
	 * 修改${comment}.
     */
	void update(${className}E ${instanceName}E);

	/**
     * 删除${comment}.
	 */
	void delete(Long[] ids);

}
// @formatter:on
