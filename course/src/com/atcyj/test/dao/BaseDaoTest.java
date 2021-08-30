package com.atcyj.test.dao;

//将BaseDao abstract 去掉再使用

public class BaseDaoTest {
    public static void main(String[] args) {

//        BaseDao bd = new BaseDao();
//
//        int count = bd.update("insert into t_user (username,password) values(?,?)","jack","654321");
//        if(count == -1){
//            System.out.println("未执行sql语句");
//        }else if (count == 0) {
//            System.out.println("数据库跟新失败");
//        }else{
//            System.out.println(count+"条语句被跟新");
//        }
//        结果User{id=1, username='zhangsan', password='123456'}
//        String sql1 = "select id,username,password from t_user where username = ?";
//        User user = bd.queryForOne(User.class, sql1, "zhangsan");
//        System.out.println(user);
//
//
//        //结果[User{id=4, username='jack', password='null'},
//        // User{id=2, username='lisi', password='null'},
//        // User{id=3, username='wangwu', password='null'},
//        // User{id=1, username='zhangsan', password='null'}]
//        String sql2 = "select id,username from t_user where id >= 1";
//        ArrayList<User> userList = bd.queryForList(User.class,sql2,null);
//        System.out.println(userList);
//
//
//        String sql3 = "select max(id) from t_user";
//        Object obj = bd.queryForSingleValue(sql3,null);
//        System.out.println();

    }
}



