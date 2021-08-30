package com.atcyj.service.impl;

import com.atcyj.dao.UserDao;
import com.atcyj.dao.impl.UserDaoImpl;
import com.atcyj.pojo.User;
import com.atcyj.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }
    /**
     *
     * @param user user
     * @return 登录成功返user，登陆失败返回null
     */
    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public Boolean existsUsername(String username) {
        return !(userDao.queryUserByUsername(username) == null);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.queryAllUser();
    }

    @Override
    public User getUserById(String id) {
        return userDao.queryUserById(id);
    }

    @Override
    public String getUsernameById(Integer id) {
        User user = userDao.queryUserById(id.toString());
        return user.getUsername();
    }

    @Override
    public int deleteUserById(String id) {
        return userDao.deleteUserById(id);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }
}
