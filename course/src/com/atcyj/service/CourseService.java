package com.atcyj.service;

import com.atcyj.pojo.Course;
import com.atcyj.pojo.User;

import java.util.List;

public interface CourseService {

    int addCourse(Course course);

    int deleteCourseById(String id);

    int updateCourse(Course course);

    Course getCourseById(String id);

    List<Course> getAllCourse();

    Boolean isAllSelected(Course course);

    void learn(User user,Course course);

    int selectedPlusOne(Course course);

    int selectedSubtractOne(Course course);

}
