package com.atcyj.utils.my_jdbc_pool;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * @author chenyujie
 * 实现连接池功能
 */
public class MyPoolImpl implements MyPool {

    // 定义一个链表存放连接池的连接
    private final LinkedList<Connection> pools = new LinkedList<>();

    public MyPoolImpl() {
        init();
        System.out.println("连接池初始化成功");
    }

    private void init() {
        try {
            Driver driver = (Driver)Class.forName(MyPoolConfig.driver).newInstance();
            DriverManager.registerDriver(driver);
            initConnectionPool();
        } catch (InstantiationException | IllegalAccessException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    private void initConnectionPool() throws SQLException {
        //创建MyPoolConfig.size个连接，并添加到链表pools
        for (int i = 0; i < MyPoolConfig.size; i++) {
            Connection connection = DriverManager.getConnection(MyPoolConfig.url, MyPoolConfig.username, MyPoolConfig.password);
            pools.add(connection);
        }
    }

    @Override
    public Connection getPoolConnection() throws InterruptedException {
        // 同步pools
        synchronized (pools) {
            //非超时等待模式
            if (MyPoolConfig.mills <= 0) {
                while (pools.isEmpty()) {
                    //释放锁，让出cpu，进入等待状态
                    pools.wait();
                }
                //等待结束后，返回链表第一个连接通道
                return  pools.removeFirst();
            } else {
                long future = System.currentTimeMillis() + MyPoolConfig.mills;
                long remaining = MyPoolConfig.mills;
                while (pools.isEmpty() && remaining>0) {
                    //释放锁，让出cpu，等待连接空闲连接，最多等待预设时间
                    pools.wait(remaining);
                    remaining = future - System.currentTimeMillis();
                }
                return  pools.isEmpty() ?null: pools.removeFirst();
            }

        }
    }

    //释放连接，归还连接
    public void releaseConnection(Connection connection) {
        if(connection != null){
            synchronized (pools) {
                //将连接添加到链表末尾
                pools.addLast(connection);
                //唤醒所有线程
                pools.notifyAll();
            }
        }
    }

    //关闭所有连接

}
