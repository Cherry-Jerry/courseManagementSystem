package com.atcyj.test.dao;

import com.atcyj.dao.BaseDao;
import com.atcyj.dao.impl.BaseDaoImpl;
import com.atcyj.pojo.Course;
import com.atcyj.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class BaseDaoImplTest {
    BaseDao baseDao = new BaseDaoImpl();
    Course course = new Course(0,"testCourse","this course is used to test",50,0);
    @Test
    public void manipulate() {
        // 插入
        baseDao.manipulate("insert into t_course (id,course_name,information,places,selected) value(?,?,?,?,?)",
                course.getId(),course.getCourseName(),course.getInformation(),course.getPlaces(),course.getSelected());
        // 修改
        baseDao.manipulate("update t_course set places=? where course_name=?",80,course.getCourseName());
        // 删除
        baseDao.manipulate("delete from t_course where course_name=?",course.getCourseName());
    }

    @Test
    public void queryForList() {
        System.out.println(baseDao.queryForList(User.class,"select * from t_user",null));
    }

    @Test
    public void queryForOne() {
        System.out.println(baseDao.queryForOne(User.class,"select * from t_user where id=?",0));
    }

    @Test
    public void queryForSingleValue() {
        System.out.println(baseDao.queryForSingleValue("select count(*) from t_user",null));
        System.out.println(baseDao.queryForSingleValue("select username from t_user where id=0",null));
    }
}