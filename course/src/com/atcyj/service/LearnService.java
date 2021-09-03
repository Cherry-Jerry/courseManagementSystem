package com.atcyj.service;

import com.atcyj.pojo.Course;
import com.atcyj.pojo.User;

import java.util.List;

/**
 * @author chenyujie
 */
public interface LearnService {
    /**
     * 添加一条学习记录
     * @param student 学生
     * @param course 课程
     */
    void addRecord(User student, Course course);

    /**
     * 取消选择课程，删除一条学习记录
     * @param student 学生
     * @param course 课程
     */
    void cancelLearn(User student, Course course);

    /**
     * 判断某条学习记录是否存在
     * @param student 学生
     * @param course 课程
     * @return 是true 否false
     */
    Boolean existRecord(User student, Course course);

    /**
     * 判断学习已选课程数是否达到上限
     * @param student 学生
     * @return 是true 否false
     */
    Boolean isUptoMaxSelectNumber(User student);

    /**
     * 判断学生已选课程和将选课程在时间上是否冲突（时间段重叠）
     * @param student 学生
     * @param course 将选课程
     * @return 是true 否false
     */
    Boolean isTimeConflict(User student, Course course);

    /**
     *获取学生所有已选的课程
     * @param student 学生
     * @return  包含学生所有已选的课程的列表
     */
    List<Course> getCourseListOf(User student);

}
