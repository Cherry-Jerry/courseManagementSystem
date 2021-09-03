package com.atcyj.dao;

import java.util.ArrayList;

/**
 * @author chenyujie
 */
public interface BaseDao {
    /**
     * 用于执行DML：add，delete，update
     * @param sql 需要执行sql语句
     * @param args 给sql语句占位符传的参数，没有则传null
     * @return 返回处理的语句数
     */
    int manipulate(String sql, Object... args);

    /**
     * 用于执行DQL，且查询结果有多行
     * @param type pojo类的字节码
     * @param sql 需要执行sql语句
     * @param args 给sql语句占位符传的参数，没有则传null
     * @param <T> pojo类
     * @return 返回列表，每个成员对应一条查询结果
     */
    <T> ArrayList<T> queryForList(Class<T> type, String sql, Object... args);

    /**
     * 用于执行DQL，且查询结果仅一行
     * @param type pojo类的字节码
     * @param sql 需要执行sql语句
     * @param args 给sql语句占位符传的参数，没有则传null
     * @param <T> pojo类
     * @return 返回查询结果对应的一个pojo
     */
    <T> T queryForOne(Class<T> type, String sql, Object... args);

    /**
     * 返回查询的单值 ，如平均值，最大值
     * @param sql 需要执行sql语句
     * @param args 需要执行sql语句
     * @return 返回查询值
     */
    Object queryForSingleValue(String sql, Object... args);

}
