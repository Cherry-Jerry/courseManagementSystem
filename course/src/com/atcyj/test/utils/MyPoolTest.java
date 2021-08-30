package com.atcyj.test.utils;

import com.atcyj.utils.my_jdbc_pool.MyPoolImpl;

import java.sql.*;

/**
 * @author chenyujie
 * 连接池测试
 *    //https://blog.csdn.net/yhflyl/article/details/
 *     100144564?utm_medium=distribute.pc_relevant.none-task-blog-2
 *     ~default~baidujs_baidulandingword~default-1.control&spm=1001.2101.3001.4242
 */

public class MyPoolTest {
    public static void main(String[] args) {
        //创建数据库连接池
        MyPoolImpl pool = new MyPoolImpl();
        for(int i = 0; i < 100; i++) {
            new Thread(()-> {
                //@Override public void run()
                 {
                    for (int j = 0; j < 10; j++) {
                        Connection connection = null;
                        try {
                            //获取连接
                            connection = pool.getPoolConnection();
                            //获取数据库操作对象
                            String sql = "select * from t_user";
                            PreparedStatement ps = connection.prepareStatement(sql);
                            //执行sql
                            ResultSet rs = ps.executeQuery();
                            //处理结果
                            synchronized (rs) {
                                System.out.println("线程 : [" + Thread.currentThread().getName() + "] 执行SQL, 开始读取返回值");
                                while(rs.next()){
                                    rs.getString(1);
                                }
                            }
                        } catch (SQLException | InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            pool.releaseConnection(connection);
                        }
                    }
                }
            }, "Pool-" + i).start();
        }
    }


}

