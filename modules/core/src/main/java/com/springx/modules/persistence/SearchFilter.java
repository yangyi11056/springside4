package com.springx.modules.persistence;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

public class SearchFilter {

	public enum Operator {
		/**
		 * 等于
		 */
		EQ,

		/**
		 * 不等于
		 */
		NE,

		/**
		 * 大于
		 */
		GT,

		/**
		 * 小于
		 */
		LT,

		/**
		 * 大于等于
		 */
		GTE,

		/**
		 * 小于等于
		 */
		LTE,

		/**
		 * 相似
		 */
		LIKE,

		/**
		 * 包含
		 */
		IN,

		/**
		 * 为NULL
		 */
		NULL,

		/**
		 * 不为NULL
		 */
		NOTNULL
	}

	public String fieldName;
	public Object value;
	public Operator operator;

	public SearchFilter(String fieldName, Operator operator, Object value) {
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
	}

	/**
	 * searchParams中key的格式为OPERATOR_FIELDNAME
	 */
	public static Map<String, SearchFilter> parse(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = Maps.newHashMap();

		for (Entry<String, Object> entry : searchParams.entrySet()) {
			// 过滤掉空值
			String key = entry.getKey();
			Object value = entry.getValue();

			if (StringUtils.isBlank(ConvertUtils.convert(value))) {
				continue;
			}

			// 拆分operator与filedAttribute
			String[] names = StringUtils.split(key, "_");
			if (names.length != 2) {
				throw new IllegalArgumentException(key + " is not a valid search filter name");
			}
			String filedName = names[1];
			Operator operator = Operator.valueOf(names[0]);

			// 创建searchFilter
			SearchFilter filter = new SearchFilter(filedName, operator, value);
			filters.put(key, filter);
		}

		return filters;
	}
}