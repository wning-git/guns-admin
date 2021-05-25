package com.qyhl.guns.modular.uniquery.util;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import com.qyhl.guns.config.uniquery.ParentTable;
import com.qyhl.guns.config.uniquery.Query;
import com.qyhl.guns.config.uniquery.Result;
import com.qyhl.guns.config.uniquery.SonTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.List;

/**
 * 
 * 生成通用查询sql工具类
 * 
 */
public class SqlBuilder {
	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(SqlBuilder.class);

	public static final String MATCH_EQUAL = "equal";// 查询条件匹配类型：等于
	public static final String MATCH_LIKE = "like";// 查询条件匹配类型：模糊查询
	public static final String MATCH_DATE = "date";// 查询条件匹配类型：日期范围
	public static final String MATCH_BETWEEN = "between";// 查询条件匹配类型：数值范围

	public static final String PARAM_SUFFIX_START = "Start";//变量后缀：开始；用于开始时间等
	public static final String PARAM_SUFFIX_END = "End";//变量后缀：结束；用于结束时间等

	public static final String TIME_SUFFIX_START = " 00:00:00";// 时间后缀：0点0分0秒
	public static final String TIME_SUFFIX_END = " 23:59:59";// 时间后缀：0点0分0秒



	/**
	 * 拼接通用查询sql
	 * @param req HttpServletRequest请求
	 * @param parentTable 通用查询配置类
	 * @return
	 */
	@SuppressWarnings("unused")
	public static String getSqlFromReq(HttpServletRequest req, ParentTable parentTable) {
		// 获取查询的parentID
//		String parentTid = req.getParameter("parent");



		StringBuffer sql = new StringBuffer("select distinct ");
		List<Result> resultList = parentTable.getResultList();
		// select 字段1, 字段2, ... , 字段n
		for (int i = 0; i < resultList.size(); i++) {
			Result result = resultList.get(i);
			String fromTable = StrUtil.isNotEmpty(result.getTableName())?result.getTableName():parentTable.getTableAliasName();
			if (i != 0) {
				sql.append(",");
			}
			if (StrUtil.isNotEmpty(result.getCustomizeColumn())) {
				sql.append("(").append(result.getCustomizeColumn()).append(") as ").append(StrUtil.blankToDefault(result.getAliasName(), result.getColumn()));
			} else {
				sql.append(fromTable).append(".").append(result.getColumn()).append(" ").append(StrUtil.blankToDefault(result.getAliasName(), result.getColumn()));
			}
		}
		// from
		sql.append("\n from ").append(parentTable.getTable()).append(" ").append(parentTable.getTableAliasName()); // 给主表起别名，别名仍为主表名
		List<SonTable> sonTableList = parentTable.getSonTableList();

		// 关联子表查询
		if (sonTableList != null) {
			for (int i = 0; i < sonTableList.size(); i++) {
				SonTable sonTable = sonTableList.get(i);
				sql.append("\n ").append(sonTable.getJoinType()).append(" join ").append(sonTable.getTable()).append(" ").append(sonTable.getAliasName()).append(" on ").append(sonTable.getJoinCondition());
			}
		}
		// 自定义where条件
		sql.append("\n where 1=1 ");
		sql.append(getQueryString(parentTable.getQueryList(), parentTable.getTableAliasName(), req));

		// 跨组 开始 TODO 需要获取管理的机构
		// 是否跨组
		String isCrossGroup = parentTable.getIsCrossGroup();
		boolean isCross = (StrUtil.isNotEmpty(isCrossGroup) && "1".equals(isCrossGroup));
		if (isCross) {
			getDepaRangeSQL(sql, parentTable);
		}
		// 跨组 结束

		// 固定where条件
		if (parentTable.getWhere() != null && !"".equals(parentTable.getWhere().trim())) {
			sql.append("\n and (").append(parentTable.getWhere()).append(")");
		}
		// order by
		if (StrUtil.isNotBlank(parentTable.getOrderby())) {
			sql.append("\n ").append(parentTable.getOrderby());
		}
		return sql.toString();
	}

	/**
	 * 拼接where条件
	 * @param querylist 查询条件列表
	 * @param tableAliasName 主表表别名
	 * @param req http请求对象
	 * @return
	 */
	private static String getQueryString(List<Query> querylist, String tableAliasName, HttpServletRequest req) {
		StringBuffer queryStr = new StringBuffer();
		for (int j = 0; j < querylist.size(); j++) {
			Query query = querylist.get(j);
			// 查询条件匹配类型
			String matchType = query.getMatch();
			// 1.获取查询条件变量的值
			String paramValue  = null;
			String paramStartValue = null;
			String paramEndValue = null;
			if (MATCH_DATE.equals(matchType) || MATCH_BETWEEN.equals(matchType)) {
				paramStartValue = req.getParameter(query.getId() + PARAM_SUFFIX_START);
				paramEndValue = req.getParameter(query.getId() + PARAM_SUFFIX_END);
			} else {
				paramValue = req.getParameter(query.getId());
			}

			if (MATCH_EQUAL.equals(matchType)) {// equal 匹配类型
				if (StrUtil.isNotEmpty(paramValue)) {
					queryStr.append("\n and ").append(StrUtil.blankToDefault(query.getTableName(),tableAliasName)).append(".").append(StrUtil.blankToDefault(query.getColumn(),query.getId())).append(" = '").append(paramValue).append("' ");
				}
			} else if (MATCH_LIKE.equals(matchType)) {// like模糊查询 匹配类型
				if (StrUtil.isNotEmpty(paramValue)) {
					queryStr.append("\n and upper(").append(StrUtil.blankToDefault(query.getTableName(),tableAliasName)).append(".").append(StrUtil.blankToDefault(query.getColumn(),query.getId())).append(") like upper('%").append(paramValue).append("%') ");
				}
			} else if (MATCH_DATE.equals(matchType)) {// 日期范围查询
				if (StrUtil.isNotEmpty(paramStartValue)) {
					queryStr.append("\n and ").append(StrUtil.blankToDefault(query.getTableName(),tableAliasName)).append(".").append(StrUtil.blankToDefault(query.getColumn(),query.getId())).append(" >= '").append(paramStartValue + TIME_SUFFIX_START).append("' ");
				}
				if (StrUtil.isNotEmpty(paramEndValue)) {
					queryStr.append("\n and ").append(StrUtil.blankToDefault(query.getTableName(),tableAliasName)).append(".").append(StrUtil.blankToDefault(query.getColumn(),query.getId())).append(" <= '").append(paramEndValue + TIME_SUFFIX_END).append("' ");
				}
			} else if (MATCH_BETWEEN.equals(matchType)) {// 数值范围查询
				if (StrUtil.isNotEmpty(paramStartValue)) {
					queryStr.append("\n and ").append(StrUtil.blankToDefault(query.getTableName(),tableAliasName)).append(".").append(StrUtil.blankToDefault(query.getColumn(),query.getId())).append(" >= '").append(paramStartValue).append("' ");
				}
				if (StrUtil.isNotEmpty(paramEndValue)) {
					queryStr.append("\n and ").append(StrUtil.blankToDefault(query.getTableName(),tableAliasName)).append(".").append(StrUtil.blankToDefault(query.getColumn(),query.getId())).append(" <= '").append(paramEndValue).append("' ");
				}
			}
		}
		return queryStr.toString();
	}


	/**
	 * 获取记录条数
	 * @param querySql 明细查询sql
	 * @param parentTable 通用查询配置类
	 * @return
	 */
	public static String getCountSqlFromReq(String querySql, ParentTable parentTable) {
		StringBuffer countSql = new StringBuffer();
		countSql.append("select count(1)").append(" from \n(").append(querySql).append("\n) tmp ");
		return countSql.toString();
	}


	/**
	 * 拼接跨组sql
	 */
	public static void getDepaRangeSQL(StringBuffer sql, ParentTable parentTable) {
		// 获取登录人机构范围
		List<Long> deptDataScope = LoginContextHolder.getContext().getDeptDataScope();
		// 跨组列
		String crossGroupColumn = parentTable.getCrossGroupColumn();
		if (StrUtil.isNotEmpty(crossGroupColumn)) {
			sql.append(" AND (1 = 0 ");
			if (CollUtil.isNotEmpty(deptDataScope)) {
				for (Long deptId : deptDataScope) {
					sql.append(" OR ").append(crossGroupColumn).append(" = '").append(deptId).append("'");
				}
			}
			sql.append(") ");
		} else {
			logger.error(parentTable.getId() + "通用查询 配置 跨祖列crossGroupColumn出错!joinDepaOn为空!");
		}
	}

	public static void main(String[] args) {
		// String s1 = "方式的方式";
		// String s2 = "asdf";
		// String s3 = "2010-01-01";
		// try {
		// String ss1=URLEncoder.encode(s1+s2, "utf-8");
		// String ss2=URLEncoder.encode(s2+s3, "utf-8");
		// String ss3=URLEncoder.encode(s3+s1, "utf-8");
		// System.out.println(s1+"     "+ss1);
		// System.out.println(s2+"     "+ss2);
		// System.out.println(s3+"     "+ss3);
		// } catch (UnsupportedEncodingException e) {
		// e.printStackTrace();
		// }
		Integer $10 = new Integer(10);
		Integer $20 = new Integer(20);
		int $3 = -3;
		System.out.println($10 < $20);
		System.out.println($10 > $20);
		System.out.println($3 < $20);
	}
}
