package com.atcyj.dao;

import com.atcyj.pojo.Course;
import com.atcyj.pojo.User;

import java.util.List;

public interface CourseDao {

     int addCourse(Course course);

     int deleteUserById(String id);

     int updateCourse(Course course);

     Course queryCourseById(String id);

     List<Course> queryAllCourse();

}
