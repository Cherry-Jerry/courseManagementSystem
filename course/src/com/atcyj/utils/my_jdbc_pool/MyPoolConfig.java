package com.atcyj.utils.my_jdbc_pool;

/**
 * @author chenyujie
 * 配置连接池的参数
 */
public class MyPoolConfig {


    public static String url = "jdbc:mysql://localhost:3306/course_project";
    public static String driver = "com.mysql.cj.jdbc.Driver";
    public static String username = "root";
    public static String password = "0000";
    public static int size = 10;
    public static long mills = 2000;


}
