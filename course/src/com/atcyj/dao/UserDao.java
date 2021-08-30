package com.atcyj.dao;

import com.atcyj.pojo.User;

import java.util.List;

public interface UserDao {

    /**
     * 查询用户名是否存在
     * @param username `
     * @return `
     */
     User queryUserByUsername(String username);

    /**
     * 根据用户名和密码查询用户信息
     * @param username 用户名
     * @param password 密码
     * @return 返回User对象 ，null说明用户名和密码不匹配
     */
     User queryUserByUsernameAndPassword(String username,String password);

    /**
     * 保存信息
     * @param user 用户
     * @return `
     */
     int saveUser(User user);

     int addUser(User user);

     List<User> queryAllUser();

     User queryUserById(String id);

     int updateUser(User user);

     int deleteUserById(String id);

}
