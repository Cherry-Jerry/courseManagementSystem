package com.atcyj.test.utils;
import com.atcyj.utils.pool.jdbc.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author chenyujie
 */
public class JdbcUtilsTest {

    Connection connection = null;
    PreparedStatement ps =null;
    ResultSet rs = null;
    @Test
    public void getConnection() {
        connection = JdbcUtils.getConnection();
        try {
            ps = connection.prepareStatement("select * from t_user");
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("username:"+rs.getString("username")+
                        "password:"+rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.freeConnection(connection);
        }

    }

    @Test
    public void freeConnection() {
        for (int i = 0; i<100; i++) {
            QueryThread queryThread = new QueryThread();
            queryThread.start();
        }
    }
}
