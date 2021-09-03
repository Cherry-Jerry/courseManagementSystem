package com.atcyj.dao.impl;

import com.atcyj.dao.BaseDao;
import com.atcyj.utils.DaoUtils;
import com.atcyj.utils.my_jdbc_pool.JdbcUtils;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author chenyujie
 * @version 1.0.0
 */

public class BaseDaoImpl implements BaseDao {

    @Override
    public int manipulate(String sql, Object... args) {
        Connection connection = null;
        PreparedStatement ps =null;
        try {
            // 连接数据库
            connection = JdbcUtils.getConnection();
            // 获取数据库操作对象
            ps = connection.prepareStatement(sql);
            // 给占位符传参
            DaoUtils.setParam(ps,args);
            // 执行sql语句,返回执行结果
            return ps.executeUpdate();
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            JdbcUtils.close(connection, ps, null);
        }
        return -1;
    }

    @Override
    public <T> ArrayList<T> queryForList(Class<T> type, String sql, Object... args) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 连接数据库
            connection = JdbcUtils.getConnection();
            // 获取数据库操作对象
            ps = connection.prepareStatement(sql);
            // 给占位符传参
            DaoUtils.setParam(ps,args);
            // 执行sql语句
            rs = ps.executeQuery();
            // 遍历resultSet，把结果逐个添加到list
            ArrayList<T> list = new ArrayList<>();
            while (rs.next()) {
                list.add(DaoUtils.queryResultToObject(type, rs));
            }
            return list;
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection, ps, rs);
        }
        return null;
    }

    @Override
    public <T> T queryForOne(Class<T> type, String sql, Object... args) {
        ArrayList<T> objectList = queryForList(type,sql,args);
        if (objectList.size() == 1){
            return objectList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Object queryForSingleValue(String sql, Object... args) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            DaoUtils.setParam(ps,args);
            rs = ps.executeQuery();
            rs.next();
            return rs.getObject(1);
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection, ps, rs);
        }
        return null;
    }

}
