package com.atcyj.dao.impl;

import com.atcyj.dao.UserDao;
import com.atcyj.pojo.User;

import java.util.List;
/**
 * @author chenyujie
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    @Override
    public User queryUserByUsername(String username) {
        String sql = "select * from t_user where username = ?";
        return queryForOne(User.class,sql,username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select * from t_user where username = ? and password = ?";
        return queryForOne(User.class,sql,username,password);
    }

    @Override
    public int addUser(User user) {
        String sql = "insert into t_user (username,password,identity,institute,grade,cls) value(?,?,?,?,?,?)";
        return manipulate(sql,user.getUsername(),user.getPassword(),user.getIdentity(),user.getInstitute(),user.getGrade(),user.getCls());
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user (username,password) value(?,?)";
        return manipulate(sql,user.getUsername(),user.getPassword());
    }

    @Override
    public List<User> queryAllUser() {
        String sql = "select * from t_user";
        return queryForList(User.class,sql);
    }

    @Override
    public int updateUser(User user) {
        String sql = "update t_user set username =?,password=?,identity=?,institute=?,grade=?,cls=?  where id=?";
        return manipulate(sql,user.getUsername(),user.getPassword(),user.getIdentity(),user.getInstitute(),user.getGrade(),user.getCls(),user.getId());
    }

    @Override
    public User queryUserById(Integer id) {
        String sql ="select * from t_user where id=?";
        return queryForOne(User.class,sql,id);
    }

    @Override
    public int deleteUserById(Integer id) {
        String sql = "delete from t_user where id=?";
        return manipulate(sql,id);
    }
}
