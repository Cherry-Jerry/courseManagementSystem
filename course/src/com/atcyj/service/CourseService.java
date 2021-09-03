package com.atcyj.service;

import com.atcyj.pojo.Course;
import java.util.List;

/**
 * @author chenyujie
 */
public interface CourseService {

    /**
     * 增加课程
     * @param course 课程对象
     */
    void addCourse(Course course);

    /**
     * 通过id删除课程
     * @param id 课程id
     */
    void deleteCourseById(Integer id);

    /**
     *  更新课程
     * @param course 课程对象
     */
    void updateCourse(Course course);

    /**
     * 通过id获取课程对象
     * @param id 课程id
     * @return 课程对象
     */
    Course getCourseById(Integer id);

    /**
     * 获取所有课程对象
     * @return 包含所有课程对象的列表
     */
    List<Course> getAllCourse();

    /**
     * 判断该课程是否完全被选满
     * @param course 课程对象
     * @return 是true 不是false
     */
    Boolean isAllSelected(Course course);

    /**
     * 使已选人数加一
     * @param course 课程对象
     * @return 更新条数
     */
    void selectedPlusOne(Course course);

    /**
     * 使已选人数减一
     * @param course 课程对象
     */
    void selectedSubtractOne(Course course);

}
