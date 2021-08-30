package com.atcyj.service;

import com.atcyj.pojo.Course;
import com.atcyj.pojo.User;

import java.util.List;


public interface LearnService {
    void addRecord(User student, Course course);

    void cancelLearn(User student, Course course);

    Boolean existRecord(User student, Course course);

    Boolean isUptoMaxSelectNumber(User student);

    Boolean isTimeConflict(User student, Course course);

    List<Course> getCourseListOf(User student);

}
