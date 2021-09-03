package com.atcyj.test.dao;

import com.atcyj.dao.CourseDao;
import com.atcyj.dao.impl.CourseDaoImpl;
import com.atcyj.pojo.Course;
import org.junit.Test;

import static org.junit.Assert.*;

public class CourseDaoImplTest {
    CourseDao courseDao = new CourseDaoImpl();
    Course course = new Course(7,"testCourse","this course is used to test",80,0);
    @Test
    public void addCourse() {
        courseDao.addCourse(course);
    }

    @Test
    public void deleteUserById() {
        courseDao.deleteUserById(7);
    }

    @Test
    public void updateCourse() {
        courseDao.updateCourse(course);
    }

    @Test
    public void queryCourseById() {
        System.out.println(courseDao.queryCourseById(7));
    }

    @Test
    public void queryAllCourse() {
        System.out.println(courseDao.queryAllCourse());
    }
}