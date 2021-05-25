package com.qyhl.guns.config.uniquery;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 通用查询配置对应实体
 * 
 * @author ningsw
 * 
 */
public class ParentTable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;// xml配置中查询的唯一标识，配置超链接时:超链接写成/common/toCasUniQuery/ILoveU
	private String name;// 页面的title
	private String nameEn;//英文名称
	private String table;// 表名，同时Sql语句也把table作为别名，例如：table="tbl_example", SQL:from tbl_example as tbl_example
	private String tableAliasName;// 表别名
	private String clazz;// 与表对应的实体类对象名，包含包名，例如：com.order.bean.NewTicketOrder
	private String pk;// 主键, 由于需要分页，所以select count(pk) from table
	private String where;// 默认where条件
	private String isCrossGroup;// 是否跨组，0为不跨组，1为跨组
	private String crossGroupColumn;// 跨组字段
	private String defaultResult;
	private String showType;// 显示类型，0：无查询条件显示；1：查询条件和查询结果分开显示；2：查询条件和查询结果同页显示
	private String orderby;// 默认排序条件
	private String joinDepaOn;// 哪个字段与 历史表 的 处理机构ID 关联
	private String joinChannelOn;// 哪个字段与 渠道表 的ID关联
	private String historyTable;// 历史表
	private String joinHistoryOn;// 历史表关联条件

	private String toolbarWidth;// 操作列列宽
	private String toolbarMinWidth;// 操作列最小宽度

	private List<SonTable> sonTableList;// 要关联的子表
	private List<Query> queryList;// 查询条件列
	private List<Result> resultList;// 查询结果列

//	private RadioCas radioCas;// 单选框
	private List<Operation> operationList;// 操作按钮列表
	private List<Operation> uniOperationList;// 操作按钮列表
	private List<Operation> detailOperationList;// 操作按钮列表

	private List<Link> linkList;// 结果列表链接
	private Map<String, Link> linkMap;// 链接map，由list转换得到，方便页面添加链接时获取

	public ParentTable() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public String getJoinDepaOn() {
		return joinDepaOn;
	}

	public void setJoinDepaOn(String joinDepaOn) {
		this.joinDepaOn = joinDepaOn;
	}

	public String getJoinChannelOn() {
		return joinChannelOn;
	}

	public void setJoinChannelOn(String joinChannelOn) {
		this.joinChannelOn = joinChannelOn;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public String getIsCrossGroup() {
		return isCrossGroup;
	}

	public void setIsCrossGroup(String isCrossGroup) {
		this.isCrossGroup = isCrossGroup;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public List<Query> getQueryList() {
		return queryList;
	}

	public void setQueryList(List<Query> queryList) {
		this.queryList = queryList;
	}

	public List<SonTable> getSonTableList() {
		return sonTableList;
	}

	public void setSonTableList(List<SonTable> sonTableList) {
		this.sonTableList = sonTableList;
	}

	public List<Result> getResultList() {
		return resultList;
	}

	public void setResultList(List<Result> resultList) {
		this.resultList = resultList;
	}

	public String getTableAliasName() {
		return tableAliasName;
	}

	public void setTableAliasName(String tableAliasName) {
		this.tableAliasName = tableAliasName;
	}
//	public RadioCas getRadioCas() {
//		return radioCas;
//	}
//
//	public void setRadioCas(RadioCas radioCas) {
//		this.radioCas = radioCas;
//	}

	public List<Operation> getOperationList() {
		return operationList;
	}

	public void setOperationList(List<Operation> operationList) {
		this.operationList = operationList;
	}

	public List<Link> getLinkList() {
		return linkList;
	}

	public void setLinkList(List<Link> linkList) {
		this.linkList = linkList;
	}

	public Map<String, Link> getLinkMap() {
		return linkMap;
	}

	public void setLinkMap(Map<String, Link> linkMap) {
		this.linkMap = linkMap;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getHistoryTable() {
		return historyTable;
	}

	public void setHistoryTable(String historyTable) {
		this.historyTable = historyTable;
	}

	public String getJoinHistoryOn() {
		return joinHistoryOn;
	}

	public void setJoinHistoryOn(String joinHistoryOn) {
		this.joinHistoryOn = joinHistoryOn;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	/**
	 * 获得defaultResult
	 */
	public String getDefaultResult() {
		return defaultResult;
	}

	/**
	 * 设置defaultResult
	 */
	public void setDefaultResult(String defaultResult) {
		this.defaultResult = defaultResult;
	}

	public String getCrossGroupColumn() {
		return crossGroupColumn;
	}

	public void setCrossGroupColumn(String crossGroupColumn) {
		this.crossGroupColumn = crossGroupColumn;
	}

	public List<Operation> getUniOperationList() {
		return this.operationList.stream().filter(operation -> "0".equals(operation.getDisplayPosition())).collect(Collectors.toList());
	}

	public List<Operation> getDetailOperationList() {
		return this.operationList.stream().filter(operation -> !"0".equals(operation.getDisplayPosition())).collect(Collectors.toList());
	}

	public String getToolbarWidth() {
		return toolbarWidth;
	}

	public void setToolbarWidth(String toolbarWidth) {
		this.toolbarWidth = toolbarWidth;
	}

	public String getToolbarMinWidth() {
		return toolbarMinWidth;
	}

	public void setToolbarMinWidth(String toolbarMinWidth) {
		this.toolbarMinWidth = toolbarMinWidth;
	}
}
