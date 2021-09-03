package com.atcyj.test.service;

import com.atcyj.pojo.User;
import com.atcyj.service.UserService;
import com.atcyj.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceImplTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void registerUser() {
        userService.registerUser(new User(null,"tom","123321"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null,"zhangsan","123456")));
        System.out.println(userService.login(new User(null,"zhangsan","1324242")));

    }

    @Test
    public void existsUsername() {
        if (userService.existsUsername("zhangsan")){
            System.out.println("用户已存在");
        }else{
            System.out.println("用户可用");
        }
    }

    @Test
    public void getAllUser() {
        System.out.println(userService.getAllUser());
    }

    @Test
    public void getUserById() {
        System.out.println(userService.getUserById(5));
    }
}