package com.atcyj.utils.pool.jdbc;

import java.sql.Connection;

/**
 * @author chenyujie
 */
public class JdbcUtils {

    private static MyDataSourceImpl dataSource;

    private JdbcUtils() {}

    static {
        dataSource = new MyDataSourceImpl();
    }

    public static MyDataSourceImpl getDataSource()  {
        return dataSource;
    }
    /**
     * 从连接池pool中获取连接对象并返回
     * @return 返回一个数据库连接或null
     */
    public static Connection getConnection()  {
        return dataSource.getConnection();
    }

    /**
     * 把连接放回连接池
     */
    public static void freeConnection(Connection connection)  {
        dataSource.freeConnection(connection);
    }
}
