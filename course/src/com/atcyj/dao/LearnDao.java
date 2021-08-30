package com.atcyj.dao;

import com.atcyj.pojo.Course;
import com.atcyj.pojo.Learn;

import java.util.List;

public interface LearnDao {

     int add(Integer courseId, Integer studentId);

     int delete(Integer courseId, Integer studentId);

     Learn queryRecord(Integer courseId, Integer studentId);

     int selectedCourseNumber(Integer studentId);

     List<Learn> queryLearnByStudentId(Integer studentId);
}
