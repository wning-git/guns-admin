package com.qyhl.guns.config.uniquery;

import java.io.Serializable;

/**
 * 查询结果结构实体
 *
 * @author ningsw
 */
public class Result implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String label;// 显示的标签文字
    private String labelEn;// 英文标签名
    private String column; // 数据库的columnName
    private String aliasName; // POJO对应数据库字段的属性名，配置文件可以不配置，这个时候，将pojoField的值设为：id
    private String tableName;// 表名。字段来自哪张表，如果为空，默认为主表的字段
    private int isshow;// 列表是否显示
    private int order;// 数据列排列次序
    private String security;// 权限：哪一端可以看到，多端登录时使用
    private String resultMapKey;// 结果列对应的转义数据map的key
    private String align;  // 文本对齐方式
    private String columntype;  // 导出excl显示列的数据类型
    private String width;// 设定列宽，若不填写，则自动分配；若填写，则支持值为：数字、百分比
    private String minWidth;// 局部定义当前常规单元格的最小宽度（默认：60），一般用于列宽自动分配的情况。
    private String fixed;// 固定列。可选值有：left（固定在左）、right（固定在右）
    private String sort;// 是否允许排序（默认：false）。如果设置 true，则在对应的表头显示排序icon，从而对列开启排序功能。
    private String customizeColumn;// 自定义列内容

    public Result() {
        super();
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public int getIsshow() {
        return isshow;
    }

    public void setIsshow(int isshow) {
        this.isshow = isshow;
    }

    public String getResultMapKey() {
        return resultMapKey;
    }

    public void setResultMapKey(String resultMapKey) {
        this.resultMapKey = resultMapKey;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getLabelEn() {
        return labelEn;
    }

    public void setLabelEn(String labelEn) {
        this.labelEn = labelEn;
    }


    /**
     * 获得columntype
     */
    public String getColumntype() {
        return columntype;
    }

    /**
     * 设置columntype
     */
    public void setColumntype(String columntype) {
        this.columntype = columntype;
    }

    /**
     * 获得textAlign
     */
    public String getAlign() {
        return align;
    }

    /**
     * 设置textAlign
     */
    public void setAlign(String align) {
        this.align = align;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getMinWidth() {
        return minWidth;
    }

    public void setMinWidth(String minWidth) {
        this.minWidth = minWidth;
    }

    public String getFixed() {
        return fixed;
    }

    public void setFixed(String fixed) {
        this.fixed = fixed;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getCustomizeColumn() {
        return customizeColumn;
    }

    public void setCustomizeColumn(String customizeColumn) {
        this.customizeColumn = customizeColumn;
    }

    @Override
    public String toString() {
        return "Result{" +
                "label='" + label + '\'' +
                ", labelEn='" + labelEn + '\'' +
                ", column='" + column + '\'' +
                ", aliasName='" + aliasName + '\'' +
                ", tableName='" + tableName + '\'' +
                ", isshow=" + isshow +
                ", order=" + order +
                ", security='" + security + '\'' +
                ", resultMapKey='" + resultMapKey + '\'' +
                ", align='" + align + '\'' +
                ", columntype='" + columntype + '\'' +
                ", width='" + width + '\'' +
                ", minWidth='" + minWidth + '\'' +
                ", fixed='" + fixed + '\'' +
                ", sort='" + sort + '\'' +
                ", customizeColumn='" + customizeColumn + '\'' +
                '}';
    }
}
