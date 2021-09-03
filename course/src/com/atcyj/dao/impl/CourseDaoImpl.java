package com.atcyj.dao.impl;

import com.atcyj.dao.CourseDao;
import com.atcyj.pojo.Course;
import java.util.List;

/**
 * @author chenyujie
 */
public class CourseDaoImpl extends BaseDaoImpl implements CourseDao {

    @Override
    public int addCourse(Course course) {
        String sql = "insert into t_course (course_name,information,places,selected) value(?,?,?,?)";
        return manipulate(sql,course.getCourseName(),course.getInformation(),course.getPlaces(),course.getSelected());
    }

    @Override
    public int deleteUserById(Integer id) {
        String sql = "delete from t_course where id=?";
        return manipulate(sql,id);
    }

    @Override
    public int updateCourse(Course course) {
        String sql = "update t_course set course_name =?,information=?,places=?,selected=? where id=?";
        return manipulate(sql,course.getCourseName(),course.getInformation(),
                course.getPlaces(),course.getSelected(),course.getId());
    }

    @Override
    public Course queryCourseById(Integer id) {
        String sql = "select * from t_course where id = ?";
        return queryForOne(Course.class,sql,id);
    }

    @Override
    public List<Course> queryAllCourse() {
        String sql = "select * from t_course";
        return queryForList(Course.class,sql);
    }

}
