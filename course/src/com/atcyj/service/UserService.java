package com.atcyj.service;

import com.atcyj.pojo.User;

import java.util.List;


public interface UserService {

    void  registerUser(User user);

    User login(User user);

    Boolean existsUsername(String usrname);

    List<User> getAllUser();

    User getUserById(String id);

    String getUsernameById(Integer id);

    int deleteUserById(String id);

    int updateUser(User user);

    void addUser(User user);

}
