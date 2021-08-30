package com.atcyj.dao.impl;

import com.atcyj.utils.my_jdbc_pool.JdbcUtils;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;


/**
 * @author chenyujie
 */
public abstract class BaseDao {


    /**
     * update() 方法用来执行Insert、Update、Delete语句
     *
     * @param sql  sql语句
     * @param args ?
     * @return -1；sql未执行，0：数据库未更改 其它：n条数据被更改
     */
    public int update(String sql, Object... args) {

        int count = -1;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = JdbcUtils.getConnection();

            ps = connection.prepareStatement(sql);

            for (int i = 1; i <= args.length; i++) {
                ps.setString(i, (String) args[i - 1]);
            }

            count = ps.executeUpdate();

        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            JdbcUtils.close(connection, ps, null);
        }
        return count;

    }


    /**
     *
     * @param type ·
     * @param sql  ·
     * @param args ·
     * @param <T> ·
     * @return ·
     */
    public <T> ArrayList<T> queryForList(Class<T> type, String sql, Object... args) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //jdbc
            connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            if (args != null) {
                for (int i = 1; i <= args.length; i++) {
                    ps.setString(i, (String) args[i - 1]);
                }
            }
            rs = ps.executeQuery();
            ArrayList<T> list = new ArrayList<>();
            while (rs.next()) {
                list.add(queryResultToObject(type, rs));
            }
            if(list.size() != 0){
                return list;
            } else {
                return null;
            }

        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection, ps, rs);
        }
        return null;
    }

    /**
     *
     * @param type `
     * @param sql `
     * @param args `
     * @param <T> `
     * @return `
     */
    public <T> T queryForOne(Class<T> type, String sql, Object... args){
        ArrayList<T> objectList = queryForList(type,sql,args);
        if (objectList != null){
            return objectList.get(0);
        } else {
            return null;
        }

    }


    public String queryForSingleValue(String sql, Object... args) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //jdbc
            connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            if (args != null) {
                for (int i = 1; i <= args.length; i++) {
                    ps.setString(i, (String) args[i - 1]);
                }
            }
            rs = ps.executeQuery();
            rs.next();
            return rs.getString(1);
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection, ps, rs);
        }
        return null;
    }

    /**
     *
     * @param type ·
     * @param rs ·
     * @param <T> ·
     * @return
     * @throws SQLException
     */
    private <T> T queryResultToObject(Class<T> type, ResultSet rs) throws SQLException {
        ResultSetMetaData rsMeta=rs.getMetaData();
        int columnCount=rsMeta.getColumnCount();
        try {
            //反射获取无参构造器
            Constructor<T> constructor = type.getDeclaredConstructor();
            constructor.setAccessible(true);
            //反射方法构造对象t
            T t = constructor.newInstance();
            //反射获取所有字段
            Field[] declaredFields = type.getDeclaredFields();
            //向每个字段赋值
            for (Field field : declaredFields) {
                field.setAccessible(true);
                //判断对象的字段名是否在查询结果中
                String stringValue = null;
                //遍历查询所得字段
                for (int i=1; i<=columnCount; i++) {
                    String columnName = rsMeta.getColumnName(i).toLowerCase();
                    if (field.getName().equalsIgnoreCase(columnName)){
                        stringValue = rs.getString(field.getName());
                        break;
                    }
                }
                if (field.getType() == String.class) {
                    //String类型，直接赋值
                    field.set(t, stringValue);
                } else {
                    //反射构造字段的数据类型的对象
                    Constructor<?> fieldTypeConstructor = field.getType().getDeclaredConstructor(String.class);
                    fieldTypeConstructor.setAccessible(true);
                    Object obj = fieldTypeConstructor.newInstance("0");

                    //反射获取valueOf方法
                    Method valueOfMethod = field.getType().getDeclaredMethod("valueOf", String.class);
                    valueOfMethod.setAccessible(true);

                    //向字段赋值
                    if (stringValue == null){
                        stringValue = "0";
                    }
                    field.set(t, valueOfMethod.invoke(obj, stringValue));
                }

            }
            return t;
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
