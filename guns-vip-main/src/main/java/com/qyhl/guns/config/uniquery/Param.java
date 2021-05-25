package com.qyhl.guns.config.uniquery;

import java.io.Serializable;

/**
 * 链接参数
 * @author ningsw
 *
 */
public class Param implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;//参数名称
	private String resultRef;// 参数对应的查询结果字段

	@Override
	public String toString() {
		return "LinkParam{" +
				"name='" + name + '\'' +
				", resultRef='" + resultRef + '\'' +
				'}';
	}

	public Param() {
	}
	public Param(String name, String resultRef) {
		super();
		this.name = name;
		this.resultRef = resultRef;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getResultRef() {
		return resultRef;
	}
	public void setResultRef(String resultRef) {
		this.resultRef = resultRef;
	}
	
}
