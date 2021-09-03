package com.atcyj.service.impl;

import com.atcyj.dao.CourseDao;
import com.atcyj.dao.LearnDao;
import com.atcyj.dao.impl.CourseDaoImpl;
import com.atcyj.dao.impl.LearnDaoImpl;
import com.atcyj.pojo.Course;
import com.atcyj.pojo.Learn;
import com.atcyj.pojo.User;
import com.atcyj.service.LearnService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenyujie
 */
public class LearnServiceImpl implements LearnService {
    LearnDao learnDao = new LearnDaoImpl();
    CourseDao courseDao =new CourseDaoImpl();
    Integer maxCourseNumber = 3;
    @Override
    public void addRecord(User student, Course course) {
        learnDao.add(course.getId(),student.getId());
    }

    @Override
    public void cancelLearn(User student, Course course) {
        learnDao.delete(course.getId(),student.getId());
    }

    @Override
    public Boolean existRecord(User student, Course course) {
        return learnDao.queryRecord(course.getId(),student.getId()) != null;
    }

    @Override
    public Boolean isUptoMaxSelectNumber(User student) {
        return learnDao.selectedCourseNumber(student.getId()) >= maxCourseNumber;
    }

    @Override
    public Boolean isTimeConflict(User student, Course course) {
        return false;
    }

    @Override
    public List<Course> getCourseListOf(User student) {
        List<Learn> learns = learnDao.queryLearnByStudentId(student.getId());
        List<Course> courses = new ArrayList<>();
        for (Learn learn :learns) {
            Course course = courseDao.queryCourseById(learn.getCourseId());
            courses.add(course);
        }
        return courses;
    }
}
