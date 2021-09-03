package com.atcyj.dao;

import com.atcyj.pojo.User;

import java.util.List;

/**
 * @author chenyujie
 */
public interface UserDao {

    /**
     * 根据用户名查询用户对象
     * @param username 用户名
     * @return User对象
     */
     User queryUserByUsername(String username);

    /**
     * 根据用户名和密码查询用户信息
     * @param username 用户名
     * @param password 密码
     * @return User对象
     */
     User queryUserByUsernameAndPassword(String username,String password);

    /**
     * 根据用户名和密码保存账号，userId自增
     * @param user 用户对象
     * @return 返回处理的语句数
     */
     int saveUser(User user);

    /**
     * 将新用户对象信息持久化到数据库，userId自增
     * @param user 用户对象
     * @return 返回处理的语句数
     */
     int addUser(User user);

    /**
     * 获取所有用户对象
     * @return 包含所有用户对象的列表
     */
     List<User> queryAllUser();

    /**
     * 通过用户id查询用户
     * @param id 用户id
     * @return 与id对应的用户对象
     */
     User queryUserById(Integer id);

    /**
     * 更新用户信息
     * @param user 修改信息后的用户对象
     * @return 返回处理的语句数
     */
     int updateUser(User user);

    /**
     * 通过studentId删除用户信息
     * @param id 用户id
     * @return 返回处理的语句数
     */
     int deleteUserById(Integer id);

}
