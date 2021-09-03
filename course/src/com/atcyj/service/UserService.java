package com.atcyj.service;

import com.atcyj.pojo.User;
import java.util.List;

/**
 * @author chenyujie
 */
public interface UserService {

    /**
     * 注册一个新用户，仅需要用户名和密码
     * @param user 用户
     */
    void  registerUser(User user);

    /**
     * 登录，通过用户名和密码返回完整的用户对象
     * @param user 用户
     * @return 完整的用户对象
     */
    User login(User user);

    /**
     * 是否存在该用户名
     * @param username 用户名
     * @return 是true 否false
     */
    Boolean existsUsername(String username);

    /**
     * 获取所有用户对象
     * @return 包含所有用户对象的一个列表
     */
    List<User> getAllUser();

    /**
     * 通过用户id获取完整的用户对象
     * @param id 用户id
     * @return 完整的用户对象
     */
    User getUserById(Integer id);

    /**
     * 通过用户id获取完整的用户名
     * @param id 用户id
     * @return 用户名
     */
    String getUsernameById(Integer id);

    /**
     * 通过用户id删除用户
     * @param id 用户id
     */
    void deleteUserById(Integer id);

    /**
     * 更新用户用户信息
     * @param user 用户对象
     */
    void updateUser(User user);

    /**
     * 添加新用户
     * @param user 用户对象
     */
    void addUser(User user);

}
