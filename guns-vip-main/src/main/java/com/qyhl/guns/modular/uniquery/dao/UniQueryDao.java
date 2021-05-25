package com.qyhl.guns.modular.uniquery.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UniQueryDao<T> {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 根据记录id获得一个对象,采用statement方式进行不需要创建临时空间
     *
     * @param sql
     *            要执行的sql语句
     * @param rowMapper
     *            数据库返回值集合
     * @return 返回根据条件查询出的对象(单个对象),如果不是单个对象,会抛出异常
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public T getObject(String sql, RowMapper rowMapper) throws Exception {
        long begintime = System.currentTimeMillis();
        try {
            List results = jdbcTemplate.query(sql, rowMapper);
            if (results != null && results.size() > 0) {
                return (T) DataAccessUtils.requiredSingleResult(results);
            }
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            this.logInfo(sql, begintime);
        }
    }

    /**
     * 根据记录id获得一个对象
     *
     * @param sql
     * @param arg
     * @param rowMapper
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public T getObject(String sql, Object[] arg, RowMapper rowMapper) throws Exception {
        long begintime = System.currentTimeMillis();
        try {
            List results = (List) jdbcTemplate.query(sql, arg, new RowMapperResultSetExtractor(rowMapper, 1));
            if (results != null && results.size() > 0) {
                return (T) DataAccessUtils.requiredSingleResult(results);
            }
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            this.logInfo(sql, begintime);
        }
    }

    /**
     * @param sql
     *            要执行的sql语句
     * @param args
     *            查询参数数组
     * @param rowMapper
     *            数据库返回值集合
     * @return 对象集合列表
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List getList(String sql, Object[] args, RowMapper rowMapper) throws Exception {
        long begintime = System.currentTimeMillis();
        try {
            return jdbcTemplate.query(sql, args, rowMapper);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            this.logInfo(sql, begintime);
        }
    }

    /**
     * @param sql
     *            要执行的sql语句
     * @param begin
     *            记录开始号 1开始计算
     * @param size
     *            获取记录数量
     * @param args
     *            查询参数数组
     * @param rowMapper
     *            数据库返回值集合
     * @return 对象集合列表
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List getList(String sql, int begin, int size, Object[] args, RowMapper rowMapper) throws Exception {
        long begintime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append("select * from (select a.*,rownum rn from (");
        sb.append(sql);
        sb.append(") a where rownum <=" + (begin + size) + " ) where rn > " + begin);
        try {
            if (args == null)
                return jdbcTemplate.query(sb.toString(), rowMapper);
            else
                return jdbcTemplate.query(sb.toString(), args, rowMapper);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            this.logInfo(sql, begintime);
        }
    }

    /**
     * @param sql
     *            要执行的sql语句
     * @param rowMapper
     *            数据库返回值集合
     * @return 对象集合列表
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<T> getList(String sql, RowMapper rowMapper) throws Exception {
        long begintime = System.currentTimeMillis();
        try {
            return jdbcTemplate.query(sql, rowMapper);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            this.logInfo(sql, begintime);
        }
    }

    /**
     * @param sql
     *            要执行的sql语句
     * @param begin
     *            记录开始号
     * @param size
     *            获取记录数量
     * @param rowMapper
     *            数据库返回值集合
     * @return 对象集合列表
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List getList(String sql, int begin, int size, RowMapper rowMapper) throws Exception {
        long begintime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append("select * from ");
        sb.append("(select a.*,rownum rn from (");
        sb.append(sql);
        sb.append(") a where rownum <=");
        sb.append((begin + size));
        sb.append(")where rn > ");
        sb.append(begin);
        try {
            return jdbcTemplate.query(sb.toString(), rowMapper);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            this.logInfo(sql, begintime);
        }
    }

    /**
     * @param sql
     *            要执行的sql语句
     * @return 返回数值类型
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public int queryForInt(String sql) throws Exception {
        long begintime = System.currentTimeMillis();
        try {
            List result = jdbcTemplate.query(sql, new RowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

                    return new Integer(rs.getInt(1));
                }
            });
            if (result.size() == 0) {
                return 0;
            }
            Integer tmp = (Integer) (result.iterator().next());
            return tmp.intValue();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            this.logInfo(sql, begintime);
        }

    }

    /**
     * @param sql
     *            要执行的sql语句
     * @return 返回数值类型
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public BigDecimal queryForBigDecimal(String sql) throws Exception {
        long begintime = System.currentTimeMillis();
        try {
            List<BigDecimal> results = jdbcTemplate.query(sql, new RowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

                    return rs.getBigDecimal(1, 2);
                }
            });
            if (results.size() == 0) {
                return BigDecimal.ZERO;
            }
            BigDecimal result = (BigDecimal) results.iterator().next();
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            this.logInfo(sql, begintime);
        }

    }

    /**
     * 重写queryForLong
     *
     * @param sql
     * @param args
     * @return
     * @throws Exception
     */
    public long queryForLong(String sql, Object[] args) throws Exception {
        return jdbcTemplate.queryForObject(sql, args, Long.class);
    }

    /**
     * @param sql
     *            要执行的sql语句
     * @param args
     *            查询参数数组
     * @return 返回数值类型
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public int queryForInt(String sql, Object[] args) throws Exception {
        long begintime = System.currentTimeMillis();
        try {
            List result = jdbcTemplate.query(sql, args, new RowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new Integer(rs.getInt(1));
                }
            });
            if (result.size() == 0) {
                return 0;
            }
            Integer tmp = (Integer) (result.iterator().next());
            return tmp.intValue();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            this.logInfo(sql, begintime);
        }
    }
    public List<Map<String, Object>> queryForList(String sql){

        return jdbcTemplate.queryForList(sql);
    }

    public void logInfo(String sql, long begintime) {
        if (logger.isInfoEnabled()) {
            logger.info("result :: [ " + (System.currentTimeMillis() - begintime) + " ] | execute sql :: << [ " + sql + " ] >>");
        }
    }

    /**
     * @param SquencesName Squence表名
     * @return 返回Sqence值
     * @throws Exception
     */
    public long loadSqencesValue(String SquencesName) throws Exception {
        long squencesValues = 0;
        long begintime = System.currentTimeMillis();
        String sql = "select " + SquencesName + ".NEXTVAL from dual";
        try {
            squencesValues = jdbcTemplate.queryForObject(sql,Long.class);
            return squencesValues;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            this.logInfo(sql, begintime);
        }
    }

    /**
     * 更新数据库记录,并返回更新数量
     *
     * @param sql
     *            要执行的sql语句
     * @param pss
     *            参数值集合
     * @return 更新数量
     */
    public int update(String sql, PreparedStatementSetter pss) throws Exception {
        long begintime = System.currentTimeMillis();
        try {
            return jdbcTemplate.update(sql, pss);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new Exception(e);
        } finally {
            this.logInfo(sql, begintime);
        }
    }

    /**
     * 修改操作执行SQL
     *
     * @param sql
     * @return
     * @throws Exception
     */
    public int update(String sql) throws Exception {
        long begintime = System.currentTimeMillis();
        try {
            return jdbcTemplate.update(sql);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new Exception(e);
        } finally {
            this.logInfo(sql, begintime);
        }

    }

    /**
     * 批量操作
     *
     * @param setter
     * @param sql
     * @param setter
     * @throws Exception
     */
    public void bathUpdate(final String sql, BatchPreparedStatementSetter setter) throws Exception {
        long begintime = System.currentTimeMillis();
        try {
            jdbcTemplate.batchUpdate(sql, setter);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new Exception(e);
        } finally {
            this.logInfo(sql, begintime);
        }
    }


    /**
     * 批量操作
     *
     * @param setter
     * @param sql
     * @param setter
     * @return 数组
     * @throws Exception
     */
    public int[] bathInsert(final String sql, BatchPreparedStatementSetter setter) throws Exception {
        long begintime = System.currentTimeMillis();
        try {
            return jdbcTemplate.batchUpdate(sql, setter);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new Exception(e);
        } finally {
            this.logInfo(sql, begintime);
        }
    }

    /**
     * 批量执行SQL
     *
     * @param sqls
     *            SQL集合
     * @return 返回int数组
     * @exception Exception
     */
    public int[] bathUpdate(final String[] sqls) throws Exception {
        long begintime = System.currentTimeMillis();
        try {
            return jdbcTemplate.batchUpdate(sqls);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 事务回滚
            throw new Exception(e);
        } finally {
            this.logInfo("bath execute SQL exception:", begintime);
        }
    }

    /***
     * 执行SQL语句,将结果集的每一行自动封装成Class的对象
     *
     * @param sql
     * @param c
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List queryList(String sql, Class c) throws Exception {
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(c));
    }

    /***
     * 李振坤 分页查询 执行SQL语句,将结果集的每一行自动封装成Class的对象 being从0开始，rn从1开始，所以是左开右闭
     *
     * @param sql
     * @param c
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List queryList(String sql, int begin, int size, Class c) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(sql);
        buffer.append(" limit ").append(begin).append(",").append(size);
        //buffer.append(") a where rownum <=" + (begin + size) + " ) where rn > " + begin);
        // System.out.println("Query : \n" + buffer.toString());
        return jdbcTemplate.query(buffer.toString(), new BeanPropertyRowMapper(c));
    }

    /**
     * queryForInt
     * 认为输入的sql就是:select count(*) from XXX的形式
     *
     * @param sql
     * @return
     * @throws Exception
     */
    public int queryForIntOrginal(String sql) throws Exception {
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    /**
     * 构建order by 条件
     *
     * @param orderBy
     * @return
     */
    public static String orderByCondition(List<String> orderBy) {
        if (orderBy != null && orderBy.size() > 0) {
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < orderBy.size(); i++) {
                if (i == 0) {
                    buffer.append(" order by ");
                } else {
                    buffer.append(", ");
                }
                buffer.append(orderBy.get(i));
            }
            return buffer.toString();
        } else {
            return "";
        }
    }

}
