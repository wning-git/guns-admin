package com.qyhl.guns.config.uniquery;

import java.io.Serializable;
import java.util.List;

/**
 * 结果列表链接
 *
 * @author ningsw
 */
public class Link implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String href;// 链接地址
    private String location;// 链接生成位置(列表中某列)
    private String target;//页面上的弹出方式
    private List<Param> paramList; // 新增功能:链接参数名字 及 链接参数

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Param> getParamList() {
        return paramList;
    }

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

}
