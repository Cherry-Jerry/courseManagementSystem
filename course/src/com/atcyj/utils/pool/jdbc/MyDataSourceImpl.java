package com.atcyj.utils.pool.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author chenyujie
 */
public class MyDataSourceImpl implements MyDataSource{

    private final List<Connection> freeConnections = new ArrayList<>();
    private final List<Connection> busyConnections = new ArrayList<>();
    private final Object monitor = new Object();

    @Override
    public Connection getConnection() {
        int maxPoolConnectionsNumber = 10;
        int poolTimeToWait = 30000;

        Connection proxyConnection = null;
        boolean waitOutTime = false;

        synchronized (monitor){
            if (freeConnections.isEmpty()) {
                if (busyConnections.size() < maxPoolConnectionsNumber) {
                    // 没有空闲连接，且连接数没达到连接池的最大连接数量上限，则新建一个连接放在空闲集
                    freeConnections.add(activeConnection());
                } else {
                    // 等待空闲连接的出现，等待超时则不再尝试连接
                    try {
                        monitor.wait(poolTimeToWait);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 等待结束后还是没有空闲连接，则判定为等待超时
                    waitOutTime = freeConnections.isEmpty();
                }
            }
            if (!waitOutTime) {
                // 从空闲连接集中获取第一个连接
                proxyConnection = freeConnections.remove(0);
            }

            if (proxyConnection != null) {
                // 把该链接放到占线连接集
                busyConnections.add(proxyConnection);
            }
        }
        System.out.println("从MyDataSourceImpl中获取连接 freeConnections.size()="+freeConnections.size());
        return proxyConnection;
    }

    @Override
    public void freeConnection(Connection connection) {
        int maxFreeConnectionsNumber = 5;
        if (freeConnections.size() < maxFreeConnectionsNumber) {
            busyConnections.remove(connection);
            freeConnections.add(connection);
        } else {
            try {
                connection.close();
                while (freeConnections.size() > maxFreeConnectionsNumber) {
                    freeConnections.get(0).close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        synchronized (monitor){
             monitor.notifyAll();
            System.out.println("其它线程可以再次尝试连接了");
        }
        System.out.println("把连接还给了MyDataSourceImpl freeConnections.size()="+freeConnections.size());
    }

    @Override
    public Connection activeConnection() {
        String url = "jdbc:mysql://localhost:3306/course_project";
        String driverUrl = "com.mysql.cj.jdbc.Driver";
        String username = "root";
        String password = "0000";

        Connection connection =null;
        try {
            Driver driver = (Driver)Class.forName(driverUrl).newInstance();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(url, username,password);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
