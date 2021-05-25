package com.qyhl.guns.config.uniquery;

import java.io.Serializable;
import java.util.List;

/**
 * 查询结果操作按钮
 * 
 * @author ningsw
 * 
 */
public class Operation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String label;// 按钮显示名称
	private String id;// 按钮的ID
	private String name;// 按钮的name
	private String resultRef;// 判断按钮是否需要显示的字段
	private String resultValue;// 判断按钮需要显示的字段对应的值
	private String target;//页面上的弹出方式
	private String href;// 按钮的连接地址
	private String security;// 按钮的权限，哪一端客户可以卡按到按钮
	private String displayPosition;// 显示位置，查询按钮后面；每行查询列表最后面一列
	private String style;// 按钮样式,对应按钮样式的class,多个以“ ”空格隔开
	private String icon;// 按钮图标，对应图标的class
	private List<Param> paramList;// 按钮所传参数
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 获得paramList
	 */
	public List<Param> getParamList() {
		return paramList;
	}

	/**
	 * 设置paramList
	 */
	public void setParamList(List<Param> paramList) {
		this.paramList = paramList;
	}

	/**
	 * 获得target
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * 设置target
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	/**
	 * 获得ref
	 */
	public String getResultRef() {
		return resultRef;
	}

	/**
	 * 设置ref
	 */
	public void setResultRef(String resultRef) {
		this.resultRef = resultRef;
	}

	/**
	 * 获得resultValue
	 */
	public String getResultValue() {
		return resultValue;
	}

	/**
	 * 设置resultValue
	 */
	public void setResultValue(String resultValue) {
		this.resultValue = resultValue;
	}

	public String getDisplayPosition() {
		return displayPosition;
	}

	public void setDisplayPosition(String displayPosition) {
		this.displayPosition = displayPosition;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
