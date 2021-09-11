package com.atcyj.utils.pool.jdbc;

import java.sql.Connection;

/**
 * @author chenyujie
 */
public interface MyDataSource {

    /**
     * 从数据库连接池中获取一个连接
     * @return 连接
     */
    Connection getConnection();

    /**
     * 将连接归还给连接池
     * @param connection 要归还的连接
     */
    void freeConnection(Connection connection);

    /**
     * 获取真实的数据库连接
     * @return 连接对象
     */
    Connection activeConnection();
}
