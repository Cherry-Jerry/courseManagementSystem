package com.atcyj.test.utils;

import com.atcyj.utils.pool.jdbc.JdbcUtils;

import java.sql.Connection;

/**
 * @author chenyujie
 */
public class QueryThread extends Thread{
    @Override
    public void run() {
         //Connection connection = JdbcUtils.getConnection();
        System.out.println(this.getName()+"获取了连接");
         //JdbcUtils.freeConnection(connection);
        for(int i = 0; i<100; i++){
            i++;
            i--;
        }
        System.out.println(this.getName()+"关闭了连接");
    }
}
