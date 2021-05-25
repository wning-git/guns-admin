package com.qyhl.guns.config.uniquery;

import java.io.Serializable;

/**
 * 排序，暂时不适用，直接在parentTable配置的orderby属性上配置
 * 
 * @author ningsw
 *
 */
@Deprecated
public class OrderBy implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer index;
	private String orderBy;
	public OrderBy() {
		super();
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
}
