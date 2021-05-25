package com.qyhl.guns.config.uniquery;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 通用查询中，用于输入下拉框和查询结果中数值转义的配置类
 */
@Component
@ConfigurationProperties(prefix = "uniquery.select-list")
public class Select {

    /**
     * selectName:下拉框名字
     */
    private String selectName;
    /**
     * selectMapKey:查询条件中下拉列表Map的Key
     */
    private String selectMapKey;
    /**
     * resultMapKey:查询结果列表中结果Map的Key，当该值为空时，默认何selectMapKey一样
     */
    private String resultMapKey;
    /**
     * selectSourceType:下拉数据来源类型：0：枚举；1：字典表（暂时未实现）
     */
    private String selectSourceType;
    /**
     * selectSource:枚举的类名，或者字典表id
     */
    private String selectSource;
    /**
     * defaultValue:默认选中的值(功能未实现)
     */
    private String defaultValue;

    public String getSelectName() {
        return selectName;
    }

    public void setSelectName(String selectName) {
        this.selectName = selectName;
    }

    public String getSelectMapKey() {
        return selectMapKey;
    }

    public void setSelectMapKey(String selectMapKey) {
        this.selectMapKey = selectMapKey;
    }

    public String getResultMapKey() {
        return resultMapKey;
    }

    public void setResultMapKey(String resultMapKey) {
        this.resultMapKey = resultMapKey;
    }

    public String getSelectSourceType() {
        return selectSourceType;
    }

    public void setSelectSourceType(String selectSourceType) {
        this.selectSourceType = selectSourceType;
    }

    public String getSelectSource() {
        return selectSource;
    }

    public void setSelectSource(String selectSource) {
        this.selectSource = selectSource;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
