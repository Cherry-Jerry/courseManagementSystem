package com.atcyj.utils.my_jdbc_pool;

import java.sql.Connection;

/**
 * @author chenyujie
 * 定义连接池接口
 */
public interface MyPool {
    /**
     * 获取连接池中的数据库连接
     * @return Connection
     * @throws InterruptedException
     */
    Connection getPoolConnection() throws InterruptedException;
}
