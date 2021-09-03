package com.atcyj.service.impl;

import com.atcyj.dao.CourseDao;
import com.atcyj.dao.impl.CourseDaoImpl;
import com.atcyj.pojo.Course;
import com.atcyj.service.CourseService;

import java.util.List;

/**
 * @author chenyujie
 */
public class CourseServiceImpl implements CourseService {

    CourseDao courseDao = new CourseDaoImpl();

    @Override
    public void addCourse(Course course) {
        courseDao.addCourse(course);
    }

    @Override
    public void deleteCourseById(Integer id) {
        courseDao.deleteUserById(id);
    }

    @Override
    public void updateCourse(Course course) {
        courseDao.updateCourse(course);
    }

    @Override
    public Course getCourseById(Integer id) {
        return courseDao.queryCourseById(id);
    }

    @Override
    public List<Course> getAllCourse() {
        return courseDao.queryAllCourse();
    }

    @Override
    public Boolean isAllSelected(Course course) {
        course = courseDao.queryCourseById(course.getId());
        return course.getSelected() >= course.getPlaces();
    }

    @Override
    public void selectedPlusOne(Course course) {
        Integer selected = course.getSelected();
        selected++;
        course.setSelected(selected);
        courseDao.updateCourse(course);
    }

    @Override
    public void selectedSubtractOne(Course course) {
        Integer selected = course.getSelected();
        if (selected <= 0) {
            selected =0;
        } else {
            selected--;
        }
        course.setSelected(selected);
        courseDao.updateCourse(course);
    }
}
