package com.qyhl.guns.config.uniquery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniQueryConstant {

	/**
     * 查询条件下拉数据来源（枚举、字典表）MAP
     */
    private static Map<String, List<Option>> selectMap = new HashMap<String, List<Option>>();

	/**
	 * 查询结果数值转义（枚举、字典）map
	 */
	private static Map<String, Map<String, String>> resultMap = new HashMap<String, Map<String, String>>();

	/**
	 * 所有通用查询的配置map
	 */
	private static Map<String, ParentTable> parentTableMap = new HashMap<String, ParentTable>();

	/** get/set方法 **/
	public static Map<String, List<Option>> getSelectMap() {
		return selectMap;
	}

	public static void setSelectMap(Map<String, List<Option>> selectMap) {
		UniQueryConstant.selectMap = selectMap;
	}

	public static Map<String, Map<String, String>> getResultMap() {
		return resultMap;
	}

	public static void setResultMap(Map<String, Map<String, String>> resultMap) {
		UniQueryConstant.resultMap = resultMap;
	}

	public static Map<String, ParentTable> getParentTableMap() {
		return parentTableMap;
	}

	public static void setParentTableMap(Map<String, ParentTable> parentTableMap) {
		UniQueryConstant.parentTableMap = parentTableMap;
	}
	
}
