// @ts-ignore
/* eslint-disable */
import {request} from '@umijs/max';

/** 修改领域事件 修改领域事件 PUT /v3/domain-events */
export async function modifyV3(body: API.DomainEventModifyCmd, options?: { [key: string]: any }) {
	return request<any>('/v3/domain-events', {
		method: 'PUT',
		headers: {
			'Content-Type': 'application/json',
		},
		data: body,
		...(options || {}),
	});
}

/** 保存领域事件 保存领域事件 POST /v3/domain-events */
export async function saveV3(body: API.DomainEventSaveCmd, options?: { [key: string]: any }) {
	return request<any>('/v3/domain-events', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		data: body,
		...(options || {}),
	});
}

/** 删除领域事件 删除领域事件 DELETE /v3/domain-events */
export async function removeV3(body: number[], options?: { [key: string]: any }) {
	return request<any>('/v3/domain-events', {
		method: 'DELETE',
		headers: {
			'Content-Type': 'application/json',
		},
		data: body,
		...(options || {}),
	});
}

/** 查看领域事件详情 查看领域事件详情 GET /v3/domain-events/${param0} */
export async function getByIdV3(
	// 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
	params: API.getByIdV3Params,
	options?: { [key: string]: any },
) {
	const {id: param0, ...queryParams} = params;
	return request<API.Result>(`/v3/domain-events/${param0}`, {
		method: 'GET',
		params: {...queryParams},
		...(options || {}),
	});
}

/** 导出领域事件 导出领域事件 POST /v3/domain-events/export */
export async function exportV3(body: API.DomainEventExportCmd, options?: { [key: string]: any }) {
	return request<any>('/v3/domain-events/export', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		data: body,
		...(options || {}),
	});
}

/** 导入领域事件 导入领域事件 POST /v3/domain-events/import */
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

	return request<any>('/v3/domain-events/import', {
		method: 'POST',
		data: formData,
		requestType: 'form',
		...(options || {}),
	});
}

/** 分页查询领域事件列表 分页查询领域事件列表 POST /v3/domain-events/page */
export async function pageV3(body: API.DomainEventPageQry, options?: { [key: string]: any }) {
	return request<API.Result>('/v3/domain-events/page', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		data: body,
		...(options || {}),
	});
}
