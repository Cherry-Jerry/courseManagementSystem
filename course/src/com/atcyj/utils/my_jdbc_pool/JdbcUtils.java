package com.atcyj.utils.my_jdbc_pool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {

    private static MyPoolImpl pool ;

    private JdbcUtils() {}

    static {
        pool = new MyPoolImpl();
        //System.out.println("Utils静态方法被执行了");
    }

    /**
     * 从连接池pool中获取连接对象并返回
     * @return
     * @throws InterruptedException
     */
    public static Connection getConnection() throws InterruptedException {
            return pool.getPoolConnection();
    }

    /**
     * 关闭结果对象和数据库操作对象
     * @param conn
     * @param ps
     * @param rs
     */
    public static void close (Connection conn , Statement ps, ResultSet rs ){

        if(rs != null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if(ps != null){
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if(conn != null){
            try {
                if(pool != null){
                    pool.releaseConnection(conn);
                }else{
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }


}
