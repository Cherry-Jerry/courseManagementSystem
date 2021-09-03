package com.atcyj.dao;

import com.atcyj.pojo.Course;
import java.util.List;
/**
 * @author chenyujie
 */
public interface CourseDao {

     /**
      * 将新课程对象信息持久化到数据库，courseId自增
      * @param course 新课程对象
      * @return 返回处理的语句数
      */
     int addCourse(Course course);

     /**
      * 通过courseId删除 课程信息
      * @param id 课程id
      * @return 返回处理的语句数
      */
     int deleteUserById(Integer id);

     /**
      * 更新课程信息
      * @param course 修改信息后的课程对象
      * @return 返回处理的语句数
      */
     int updateCourse(Course course);

     /**
      * 用过课程id获取相应课程对象
      * @param id 课程id
      * @return 返回与id相符的课程对象
      */
     Course queryCourseById(Integer id);

     /**
      * 获取所有课程对象
      * @return 包含所有课程对象的列表
      */
     List<Course> queryAllCourse();

}
