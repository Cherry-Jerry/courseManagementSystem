package com.atcyj.dao.impl;

import com.atcyj.dao.LearnDao;
import com.atcyj.pojo.Learn;
import com.atcyj.pojo.User;

import java.util.List;

/**
 * @author chenyujie
 */
public class LearnDaoImpl extends BaseDaoImpl implements LearnDao {
    @Override
    public int add(Integer courseId, Integer studentId) {
        String sql = "insert into t_learn (course_id,student_id) value(?,?)";
        return manipulate(sql,courseId,studentId);
    }

    @Override
    public int delete(Integer courseId, Integer studentId) {
        String sql = "delete from t_learn where course_id=? and student_id=?";
        return manipulate(sql,courseId,studentId);
    }

    @Override
    public Learn queryRecord(Integer courseId, Integer studentId) {
        String sql = "select * from t_learn where course_id=? and student_id=?";
        return queryForOne(Learn.class,sql, courseId, studentId);
    }

    @Override
    public int selectedCourseNumber(Integer studentId) {
        String sql = "select count(*) from t_learn where student_id=?";
        return Integer.parseInt(String.valueOf(queryForSingleValue(sql,studentId)));
    }

    @Override
    public List<Learn> queryLearnByStudentId(Integer studentId) {
        String sql = "select * from t_learn where student_id=?";
        return queryForList(Learn.class,sql,studentId);
    }
}
