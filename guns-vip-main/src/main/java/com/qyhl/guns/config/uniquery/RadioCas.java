package com.qyhl.guns.config.uniquery;

import java.io.Serializable;

/**
 * 统一查询结果 单选框实体类
 * 暂时不适用了，操作按钮放在了每行数据的后面
 * @author ningsw
 * 
 */
@Deprecated
public class RadioCas implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;// radio的ID
	private String name;// radio的名字
	private String ref;// 对应哪一个结果列的ID

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

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

}
