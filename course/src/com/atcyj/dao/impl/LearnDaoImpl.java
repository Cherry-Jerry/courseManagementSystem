package com.atcyj.dao.impl;

import com.atcyj.dao.LearnDao;
import com.atcyj.pojo.Learn;
import com.atcyj.pojo.User;

import java.util.List;

public class LearnDaoImpl extends BaseDao implements LearnDao {
    @Override
    public int add(Integer courseId, Integer studentId) {
        String sql = "insert into t_learn (courseId,studentId) value(?,?)";
        return update(sql,courseId.toString(),studentId.toString());
    }

    @Override
    public int delete(Integer courseId, Integer studentId) {
        String sql = "delete from t_learn where courseId=? and studentId=?";
        return update(sql,courseId.toString(),studentId.toString());
    }

    @Override
    public Learn queryRecord(Integer courseId, Integer studentId) {
        String sql = "select * from t_learn where courseId=? and studentId=?";
        return queryForOne(Learn.class,sql, courseId.toString(), studentId.toString());
    }

    @Override
    public int selectedCourseNumber(Integer studentId) {
        String sql = "select count(*) from t_learn where studentId=?";
        return Integer.parseInt(queryForSingleValue(sql,studentId.toString()));
    }

    @Override
    public List<Learn> queryLearnByStudentId(Integer studentId) {
        String sql = "select * from t_learn where studentId=?";
        return queryForList(Learn.class,sql,studentId.toString());
    }
}
