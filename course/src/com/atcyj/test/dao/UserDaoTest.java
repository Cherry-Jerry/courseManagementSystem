package com.atcyj.test.dao;

import com.atcyj.dao.UserDao;
import com.atcyj.dao.impl.UserDaoImpl;
import com.atcyj.pojo.User;
import org.junit.Test;


public class UserDaoTest {
    UserDao userDao = new UserDaoImpl();
    User user = new User(4,"wangwu","654321");
    @Test
    public void queryUserByUsername() {
        System.out.println(userDao.queryUserByUsername("zhangsan"));
        if( userDao.queryUserByUsername("nobody") == null){
            System.out.println("用户名可用");
        } else {
            System.out.println("用户已存在");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if (userDao.queryUserByUsernameAndPassword("zhangsan","654321")== null){
            System.out.println("账号密码不匹配");
        } else {
            System.out.println("登录成功");
        }
    }

    @Test
    public void saveUserByUsernameAndPassword() {
        System.out.println(userDao.saveUser(user));
    }


    @Test
    public void queryAllUser() {
        System.out.println(userDao.queryAllUser());
    }

    @Test
    public void queryUserById() {
        System.out.println(userDao.queryUserById("3"));
    }

    @Test
    public void updateUser() {
        System.out.println(userDao.updateUser(new User(4,"wangwu","123456")));
    }

    @Test
    public void deleteUserById() {
        System.out.println(userDao.deleteUserById("4"));
    }
}