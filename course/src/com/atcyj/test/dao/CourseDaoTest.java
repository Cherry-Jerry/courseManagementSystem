package com.atcyj.test.dao;

import com.atcyj.dao.CourseDao;
import com.atcyj.dao.impl.CourseDaoImpl;
import com.atcyj.pojo.Course;
import org.junit.Test;

import static org.junit.Assert.*;

public class CourseDaoTest {
    CourseDao courseDao = new CourseDaoImpl();

    @Test
    public void addCourse() {
        courseDao.addCourse(new Course(null,"计算机","学计算机",50,0));
    }

    @Test
    public void deleteUserById() {
        courseDao.deleteUserById("34");
    }

    @Test
    public void updateCourse() {
        courseDao.updateCourse(new Course(4,"计算机","computer",75,0));
    }

    @Test
    public void queryCourseById() {
        String id = "2";
        System.out.println(courseDao.queryCourseById(id));
    }

    @Test
    public void queryAllCourse() {
        System.out.println(courseDao.queryAllCourse());
    }

}