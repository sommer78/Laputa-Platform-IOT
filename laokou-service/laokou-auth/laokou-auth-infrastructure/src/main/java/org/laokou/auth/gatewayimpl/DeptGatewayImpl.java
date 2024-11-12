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

package org.laokou.auth.gatewayimpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.laokou.auth.gateway.DeptGateway;
import org.laokou.auth.gatewayimpl.database.DeptMapper;
import org.laokou.auth.model.DeptV;
import org.laokou.auth.model.UserE;
import org.laokou.common.i18n.common.exception.SystemException;
import org.laokou.common.i18n.utils.LogUtil;
import org.laokou.common.i18n.utils.MessageUtil;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.laokou.auth.common.constant.Constant.TABLE_DEPT;
import static org.laokou.common.i18n.common.exception.SystemException.TABLE_NOT_EXIST;

/**
 * 部门.
 *
 * @author laokou
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DeptGatewayImpl implements DeptGateway {

	private final DeptMapper deptMapper;

	/**
	 * 查看部门PATHS.
	 * @param user 用户对象
	 * @return 部门PATHS
	 */
	@Override
	public DeptV getPaths(UserE user) {
		try {
			if (user.isSuperAdministrator()) {
				return new DeptV(new HashSet<>(getPaths(deptMapper.selectDeptPaths())));
			}
			return new DeptV(new HashSet<>(getPaths(deptMapper.selectDeptPathsByUserId(user.getId()))));
		}
		catch (BadSqlGrammarException e) {
			log.error("表 {} 不存在，错误信息：{}，详情见日志", TABLE_DEPT, LogUtil.record(e.getMessage()), e);
			throw new SystemException(TABLE_NOT_EXIST,
					MessageUtil.getMessage(TABLE_NOT_EXIST, new String[] { TABLE_DEPT }));
		}
	}

	private Set<String> getPaths(List<String> list) {
		// 字符串长度排序
		list.sort(Comparator.comparingInt(String::length));
		Set<String> paths = new HashSet<>(list.size());
		paths.add(list.getFirst());
		for (String path : list.subList(1, list.size())) {
			int find = paths.size();
			for (String p : paths) {
				if (path.contains(p)) {
					break;
				}
				find--;
			}
			if (find == 0) {
				paths.add(path);
			}
		}
		return paths;
	}

}
