package com.atcyj.dao.impl;

import com.atcyj.dao.CourseDao;
import com.atcyj.pojo.Course;
import com.atcyj.pojo.User;

import java.util.List;


public class CourseDaoImpl extends BaseDao implements CourseDao {

    @Override
    public int addCourse(Course course) {
        String sql = "insert into t_course (courseName,information,places,selected) value(?,?,?,?)";
        return update(sql,course.getCourseName(),course.getInformation(),course.getPlaces().toString(),course.getSelected().toString());
    }

    @Override
    public int deleteUserById(String id) {
        String sql = "delete from t_course where id=?";
        return update(sql,id);
    }

    @Override
    public int updateCourse(Course course) {
        String sql = "update t_course set courseName =?,information=?,places=?,selected=? where id=?";
        return update(sql,course.getCourseName(),course.getInformation(),
                course.getPlaces().toString(),course.getSelected().toString(),course.getId().toString());
    }

    @Override
    public Course queryCourseById(String id) {
        String sql = "select * from t_course where id = ?";
        return queryForOne(Course.class,sql,id);
    }

    @Override
    public List<Course> queryAllCourse() {
        String sql = "select * from t_course";
        return queryForList(Course.class,sql);
    }

}
