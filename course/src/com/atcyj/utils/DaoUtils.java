package com.atcyj.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chenyujie
 */
public class DaoUtils {

    private DaoUtils() {}

    /**
     * 给数据库操作对象占位符赋值
     * @param ps 数据库操作对象
     * @param args 占位符赋值
     * @throws SQLException 传参异常
     */
    public static void setParam(PreparedStatement ps, Object... args) throws SQLException {
        if(args != null) {
            for (int i = 1; i <= args.length; i++) {
                ps.setObject(i,args[i-1]);
            }
        }
    }

    /**
     * 将一行查询结果封装成java对象
     * @param type 封装对象的字节码
     * @param rs 结果集
     * @param <T> 类
     * @return 注入信息后的一个对象
     * @throws SQLException ~
     */
    public static <T> T queryResultToObject(Class<T> type, ResultSet rs) throws SQLException {
        try {
            // 反射构造对象t
            Constructor<T> constructor = type.getDeclaredConstructor();
            constructor.setAccessible(true);
            T t = constructor.newInstance();
            // 获取所有成员变量的字段
            Field[] declaredFields = type.getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                // 获取字段名
                String fieldName = humpToUnderline(field.getName());
                // 根据字段名获取值
                Object fieldValue = rs.getObject(fieldName);
                field.set(t,fieldValue);
            }
            return t;
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 驼峰转下划线
     * @param str   目标字符串
     * @return: java.lang.String
     */
    public static String humpToUnderline(String str) {
        String regex = "([A-Z])";
        Matcher matcher = Pattern.compile(regex).matcher(str);
        while (matcher.find()) {
            String target = matcher.group();
            str = str.replaceAll(target, "_"+target.toLowerCase());
        }
        return str;
    }

    /**
     * 下划线转驼峰
     * @param str   目标字符串
     * @return: java.lang.String
     */
    public static String underlineToHump(String str) {
        String regex = "_(.)";
        Matcher matcher = Pattern.compile(regex).matcher(str);
        while (matcher.find()) {
            String target = matcher.group(1);
            str = str.replaceAll("_"+target, target.toUpperCase());
        }
        return str;
    }



}
