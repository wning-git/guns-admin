package com.qyhl.guns.config.uniquery;

import java.io.Serializable;

/**
 * 下拉列表内容映射: <select>里面的option内容
 * 
 * @author ningsw
 * 
 */
public class Option implements Serializable {

	private static final long serialVersionUID = 1L;

	private String show;// 显示内容
	private String value;// option的value值
	private boolean select;// 是否给选中
	private String showEn;

	public Option() {
	}

	public Option(String value, String show) {
		this.show = show;
		this.value = value;
		this.select = false;
	}
	public Option(String value, String show, String showEn) {
		this.show = show;
		this.value = value;
		this.select = false;
		this.showEn = showEn;
	}

	public Option(String value, String show, boolean select) {
		this.show = show;
		this.value = value;
		this.select = select;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}

	public String getShowEn() {
		return showEn;
	}

	public void setShowEn(String showEn) {
		this.showEn = showEn;
	}
	

}
