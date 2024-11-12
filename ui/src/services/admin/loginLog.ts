// @ts-ignore
/* eslint-disable */
import {request} from '@umijs/max';
import {ExportAllToExcel} from "@/utils/export";
import moment from "moment/moment";

/** 修改登录日志 修改登录日志 PUT /v3/login-logs */
export async function modifyV3(body: API.LoginLogModifyCmd, options?: { [key: string]: any }) {
	return request<any>('/api/admin/v3/login-logs', {
		method: 'PUT',
		headers: {
			'Content-Type': 'application/json',
		},
		data: body,
		...(options || {}),
	});
}

/** 保存登录日志 保存登录日志 POST /v3/login-logs */
export async function saveV3(body: API.LoginLogSaveCmd, options?: { [key: string]: any }) {
	return request<any>('/api/admin/v3/login-logs', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		data: body,
		...(options || {}),
	});
}

/** 删除登录日志 删除登录日志 DELETE /v3/login-logs */
export async function removeV3(body: number[], options?: { [key: string]: any }) {
	return request<any>('/api/admin/v3/login-logs', {
		method: 'DELETE',
		headers: {
			'Content-Type': 'application/json',
		},
		data: body,
		...(options || {}),
	});
}

/** 查看登录日志详情 查看登录日志详情 GET /v3/login-logs/${param0} */
export async function getByIdV3(
	// 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
	params: API.getByIdV3Params,
	options?: { [key: string]: any },
) {
	const {id: param0, ...queryParams} = params;
	return request<API.Result>(`/api/admin/v3/login-logs/${param0}`, {
		method: 'GET',
		params: {...queryParams},
		...(options || {}),
	});
}

/** 导出登录日志 导出登录日志 POST /v3/login-logs/export */
export function exportV3(body: API.LoginLogExportCmd, options?: { [key: string]: any }) {
	ExportAllToExcel("登录日志" + "_" + moment(new Date()).format('YYYYMMDDHHmmss') + ".xlsx", '/api/admin/v3/login-logs/export', 'POST', body, options)
}

/** 导入登录日志 导入登录日志 POST /v3/login-logs/import */
export async function importV3(body: {}, file?: File[], options?: { [key: string]: any }) {
	const formData = new FormData();

	if (file) {
		file.forEach((f) => formData.append('file', f || ''));
	}

	Object.keys(body).forEach((ele) => {
		const item = (body as any)[ele];

		if (item !== undefined && item !== null) {
			if (typeof item === 'object' && !(item instanceof File)) {
				if (item instanceof Array) {
					item.forEach((f) => formData.append(ele, f || ''));
				} else {
					formData.append(ele, JSON.stringify(item));
				}
			} else {
				formData.append(ele, item);
			}
		}
	});

	return request<any>('/api/admin/v3/login-logs/import', {
		method: 'POST',
		data: formData,
		requestType: 'form',
		...(options || {}),
	});
}

/** 分页查询登录日志列表 分页查询登录日志列表 POST /v3/login-logs/page */
export async function pageV3(body: API.LoginLogPageQry, options?: { [key: string]: any }) {
	return request<API.Result>('/api/admin/v3/login-logs/page', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		data: body,
		...(options || {}),
	});
}

export async function clearV3(options?: { [key: string]: any }) {
	return request<API.Result>('/api/admin/v3/login-logs/all', {
		method: 'DELETE',
		headers: {
			'Content-Type': 'application/json',
		},
		...(options || {}),
	});
}
