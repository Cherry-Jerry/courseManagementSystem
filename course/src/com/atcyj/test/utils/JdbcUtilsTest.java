package com.atcyj.test.utils;

import com.atcyj.utils.my_jdbc_pool.JdbcUtils;
import com.atcyj.utils.my_jdbc_pool.MyPoolImpl;
import org.junit.Test;

import java.sql.*;

public class JdbcUtilsTest {
    public static void main(String[] args) {

        //向连接池pool请求连接
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = JdbcUtils.getConnection();
            //获取数据库处理对象
            String sql = "select * from t_user where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1,"3");
            //执行sql
            rs = ps.executeQuery();
            //处理结果

            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
                String columnName = rsmd.getColumnName(i).toLowerCase();
                System.out.println(columnName);
            }

            while(rs.next()){
                System.out.println(rs.getString("id")+"|"+rs.getString("username")+"|"+rs.getString("password"));
            }
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            JdbcUtils.close(connection,ps,rs);
        }


    }
}
