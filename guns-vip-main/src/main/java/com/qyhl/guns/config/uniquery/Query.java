package com.qyhl.guns.config.uniquery;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * 统一查询 条件列实体类
 *
 * @author ningsw
 */
public class Query implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;//生成页面时元素的 id、name,即传到后台的变量名
    private String column;// 数据库字段名。如果为空，说明跟变量名一致，即取id的值。为了解决多表关联时，字段名重复的问题
    private String label;// 生成页面时控件标签
    private String labelEn;// 引文标签
    private String tableName;// 表名，数据库表起的别名
    private int type;// 页面元素类型：输入、选择、日期等
    private String match;// 过滤匹配方式：equal/like/between等
    private int order;// 页面元素排列次序
    private String cascadetype;// 生成页面时控件联动类型
    private String selectMapKey;// 对应下拉框的数据map
    private String security;// 显示权限。多端登录时使用，相同查询不同端可能查询条件略微不同。避免重复配置
    private String defualtValue;// 默认值

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getCascadetype() {
        return cascadetype;
    }

    public void setCascadetype(String cascadetype) {
        this.cascadetype = cascadetype;
    }

    public String getSelectMapKey() {
        return selectMapKey;
    }

    public void setSelectMapKey(String selectMapKey) {
        this.selectMapKey = selectMapKey;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDefualtValue() {
        return defualtValue;
    }

    public void setDefualtValue(String defualtValue) {
        this.defualtValue = defualtValue;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
        /*return "{" +
                "id='" + id + '\'' +
                ", column='" + column + '\'' +
                ", label='" + label + '\'' +
                ", labelEn='" + labelEn + '\'' +
                ", tableName='" + tableName + '\'' +
                ", type=" + type +
                ", match='" + match + '\'' +
                ", order=" + order +
                ", cascadetype='" + cascadetype + '\'' +
                ", selectMapKey='" + selectMapKey + '\'' +
                ", security='" + security + '\'' +
                ", defualtValue='" + defualtValue + '\'' +
                '}';*/
    }

    public String getLabelEn() {
        return labelEn;
    }

    public void setLabelEn(String labelEn) {
        this.labelEn = labelEn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
