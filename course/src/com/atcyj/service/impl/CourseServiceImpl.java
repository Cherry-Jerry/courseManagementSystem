package com.atcyj.service.impl;

import com.atcyj.dao.CourseDao;
import com.atcyj.dao.impl.CourseDaoImpl;
import com.atcyj.pojo.Course;
import com.atcyj.pojo.User;
import com.atcyj.service.CourseService;

import java.util.List;

public class CourseServiceImpl implements CourseService {

    CourseDao courseDao = new CourseDaoImpl();

    @Override
    public int addCourse(Course course) {
        return courseDao.addCourse(course);
    }

    @Override
    public int deleteCourseById(String id) {
        return courseDao.deleteUserById(id);
    }

    @Override
    public int updateCourse(Course course) {
        return courseDao.updateCourse(course);
    }

    @Override
    public Course getCourseById(String id) {
        return courseDao.queryCourseById(id);
    }

    @Override
    public List<Course> getAllCourse() {
        return courseDao.queryAllCourse();
    }

    @Override
    public Boolean isAllSelected(Course course) {
        course = courseDao.queryCourseById(course.getId().toString());
        return course.getSelected() >= course.getPlaces();
    }

    @Override
    public void learn(User user, Course course) {

    }

    @Override
    public int selectedPlusOne(Course course) {
        Integer selected = course.getSelected();
        selected++;
        course.setSelected(selected);
        return courseDao.updateCourse(course);
    }

    @Override
    public int selectedSubtractOne(Course course) {
        Integer selected = course.getSelected();
        if (selected <= 0) {
            selected =0;
        } else {
            selected--;
        }
        course.setSelected(selected);
        return courseDao.updateCourse(course);
    }
}
