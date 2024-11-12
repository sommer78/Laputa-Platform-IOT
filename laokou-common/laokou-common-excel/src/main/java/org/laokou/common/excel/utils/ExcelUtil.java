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

package org.laokou.common.excel.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.enums.BooleanEnum;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import com.alibaba.excel.enums.poi.VerticalAlignmentEnum;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.google.common.collect.Lists;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.laokou.common.core.utils.CollectionUtil;
import org.laokou.common.i18n.common.exception.SystemException;
import org.laokou.common.i18n.dto.PageQuery;
import org.laokou.common.i18n.utils.DateUtil;
import org.laokou.common.i18n.utils.LogUtil;
import org.laokou.common.i18n.utils.StringUtil;
import org.laokou.common.i18n.utils.ValidatorUtil;
import org.laokou.common.mybatisplus.mapper.BaseDO;
import org.laokou.common.mybatisplus.mapper.CrudMapper;
import org.laokou.common.mybatisplus.utils.MybatisUtil;

import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;

import static org.laokou.common.i18n.common.constant.StringConstant.DROP;
import static org.laokou.common.i18n.common.constant.StringConstant.EMPTY;

/**
 * Excel工具类.
 *
 * @author laokou
 */
@Slf4j
public class ExcelUtil {

	private static final int DEFAULT_SIZE = 1000;

	public static <M, T> void doImport(String fileName, InputStream inputStream, HttpServletResponse response,
			Class<M> clazz, BiConsumer<M, T> consumer, MybatisUtil mybatisUtil) {
		EasyExcel.read(inputStream, new DataListener<>(clazz, consumer, response, mybatisUtil, fileName))
			.sheet()
			.doRead();
	}

	public static <EXCEL, DO extends BaseDO> void doExport(String fileName, HttpServletResponse response,
			PageQuery pageQuery, CrudMapper<Long, Integer, DO> crudMapper, Class<EXCEL> clazz,
			ExcelConvert<DO, EXCEL> convertor) {
		doExport(fileName, DEFAULT_SIZE, response, pageQuery, crudMapper, clazz, convertor);
	}

	@SneakyThrows
	public static <EXCEL, DO extends BaseDO> void doExport(String fileName, int size, HttpServletResponse response,
			PageQuery pageQuery, CrudMapper<Long, Integer, DO> crudMapper, Class<EXCEL> clazz,
			ExcelConvert<DO, EXCEL> convertor) {
		if (crudMapper.selectObjectCount(pageQuery) > 0) {
			try (ServletOutputStream out = response.getOutputStream();
					ExcelWriter excelWriter = EasyExcel.write(out, clazz).build()) {
				// 设置请求头
				header(fileName, response);
				// https://easyexcel.opensource.alibaba.com/docs/current/quickstart/write#%E4%BB%A3%E7%A0%81
				List<DO> list = Collections.synchronizedList(new ArrayList<>(size));
				crudMapper.selectObjectList(pageQuery, resultContext -> {
					list.add(resultContext.getResultObject());
					if (list.size() % size == 0) {
						writeSheet(list, clazz, convertor, excelWriter);
					}
				});
				if (list.size() % size != 0) {
					writeSheet(list, clazz, convertor, excelWriter);
				}
				// 刷新数据
				excelWriter.finish();
			}
			catch (Exception e) {
				log.error("Excel导出失败，错误信息：{}，详情见日志", LogUtil.record(e.getMessage()), e);
			}
		}
		else {
			throw new SystemException("S_Excel_DataIsEmpty", "Excel导出失败，数据不能为空");
		}
	}

	private static void header(String fileName, HttpServletResponse response) {
		fileName = fileName + "_" + DateUtil.format(DateUtil.now(), DateUtil.YYYYMMDDHHMMSS) + ".xlsx";
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setHeader("Content-disposition",
				"attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
		response.addHeader("Access-Control-Expose-Headers", "Content-disposition");
	}

	private static <EXCEL, DO> void writeSheet(List<DO> list, Class<EXCEL> clazz, ExcelConvert<DO, EXCEL> convertor,
			ExcelWriter excelWriter) {
		WriteSheet writeSheet = EasyExcel.writerSheet().head(clazz).build();
		// 写数据
		excelWriter.write(convertor.toExcelList(list), writeSheet);
		list.clear();
	}

	public interface ExcelConvert<DO, EXCEL> {

		List<EXCEL> toExcelList(List<DO> list);

	}

	private static class DataListener<M, T> implements ReadListener<T> {

		public static final int BATCH_COUNT = 1000;

		/**
		 * Temporary storage of data.
		 */
		private final List<T> CACHED_DATA_LIST;

		/**
		 * 错误信息.
		 */
		private final List<String> ERRORS;

		/**
		 * Single handle the amount of data.
		 */
		private final int batchCount;

		private final String fileName;

		private final HttpServletResponse response;

		private final MybatisUtil mybatisUtil;

		private final Class<M> clazz;

		private final BiConsumer<M, T> consumer;

		DataListener(Class<M> clazz, BiConsumer<M, T> consumer, HttpServletResponse response, MybatisUtil mybatisUtil,
				String fileName) {
			this(clazz, consumer, BATCH_COUNT, fileName, response, mybatisUtil);
		}

		DataListener(Class<M> clazz, BiConsumer<M, T> consumer, int batchCount, String fileName,
				HttpServletResponse response, MybatisUtil mybatisUtil) {
			this.batchCount = batchCount;
			this.clazz = clazz;
			this.fileName = fileName;
			this.response = response;
			this.ERRORS = new ArrayList<>();
			this.CACHED_DATA_LIST = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
			this.mybatisUtil = mybatisUtil;
			this.consumer = consumer;
		}

		@Override
		public void invoke(T data, AnalysisContext context) {
			int currentRowNum = context.readRowHolder().getRowIndex() + 1;
			// 校验数据
			Set<String> validates = ValidatorUtil.validateEntity(data);
			if (CollectionUtil.isNotEmpty(validates)) {
				ERRORS.add(template(currentRowNum, StringUtil.collectionToDelimitedString(validates, DROP)));
			}
			else {
				CACHED_DATA_LIST.add(data);
				if (CACHED_DATA_LIST.size() % batchCount == 0) {
					mybatisUtil.batch(CACHED_DATA_LIST, clazz, consumer);
					CACHED_DATA_LIST.clear();
				}
			}
		}

		@Override
		public void onException(Exception exception, AnalysisContext context) {
			log.error("Excel导入异常，错误信息：{}，详情见日志", LogUtil.record(exception.getMessage()));
		}

		@SneakyThrows
		@Override
		public void doAfterAllAnalysed(AnalysisContext context) {
			log.info("完成数据解析");
			if (CollectionUtil.isNotEmpty(CACHED_DATA_LIST)) {
				mybatisUtil.batch(CACHED_DATA_LIST, clazz, consumer);
			}
			// 写入excel
			try (ServletOutputStream out = response.getOutputStream();
					ExcelWriter excelWriter = EasyExcel.write(out, Error.class).build()) {
				// 设置请求头
				header(fileName, response);
				if (CollectionUtil.isEmpty(ERRORS)) {
					excelWriter.write(Collections.singletonList(new Error(EMPTY)),
							EasyExcel.writerSheet().head(Error.class).build());
				}
				else {
					List<List<String>> partition = Lists.partition(ERRORS, BATCH_COUNT);
					partition
						.forEach(item -> writeSheet(item, Error.class, ImportExcelConvertor.INSTANCE, excelWriter));
				}
				// 刷新数据
				excelWriter.finish();
			}
		}

		private String template(int num, String msg) {
			return String.format("第%s行，%s", num, msg);
		}

	}

	private static class ImportExcelConvertor implements ExcelConvert<String, Error> {

		public static final ImportExcelConvertor INSTANCE = new ImportExcelConvertor();

		@Override
		public List<Error> toExcelList(List<String> list) {
			return list.stream().map(Error::new).toList();
		}

	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	private static class Error {

		@ColumnWidth(30)
		@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER,
				verticalAlignment = VerticalAlignmentEnum.CENTER, wrapped = BooleanEnum.TRUE)
		@ExcelProperty(value = "错误信息", index = 0)
		private String msg;

	}

}
