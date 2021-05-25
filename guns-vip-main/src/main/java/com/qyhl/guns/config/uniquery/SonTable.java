package com.qyhl.guns.config.uniquery;

import java.io.Serializable;
import java.util.List;

/**
 * 父子表查询中子表对应结构实体
 *
 * @author ningsw
 */
public class SonTable implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int index;    //from语句中，出现的位置
    private String table;    //表名
    private String aliasName;// 表别名
    private String joinType;    // 表连接方式 inner join/left join/right join
    private String joinCondition; // 表连接条件，如 a.pk = b.fk
    private List<Query> queryList;//查询条件

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getJoinType() {
        return joinType;
    }

    public void setJoinType(String joinType) {
        this.joinType = joinType;
    }

    public String getJoinCondition() {
        return joinCondition;
    }

    public void setJoinCondition(String joinCondition) {
        this.joinCondition = joinCondition;
    }

    public List<Query> getQueryList() {
        return queryList;
    }

    public void setQueryList(List<Query> queryList) {
        this.queryList = queryList;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "SonTable{" +
                "index=" + index +
                ", table表名='" + table + '\'' +
                ", aliasName表别名='" + aliasName + '\'' +
                ", joinType关联类型='" + joinType + '\'' +
                ", joinCondition关联条件='" + joinCondition + '\'' +
                '}';
    }

}
