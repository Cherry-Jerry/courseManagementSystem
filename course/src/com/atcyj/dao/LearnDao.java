package com.atcyj.dao;

import com.atcyj.pojo.Learn;
import java.util.List;

/**
 * @author chenyujie
 */
public interface LearnDao {

     /**
      * 添加一条选课记录
      * @param courseId 课程id
      * @param studentId 学生id
      * @return 处理的语句数
      */
     int add(Integer courseId, Integer studentId);

     /**
      * 删除一条选课记录
      * @param courseId 课程id
      * @param studentId 学生id
      * @return 处理的语句数
      */
     int delete(Integer courseId, Integer studentId);

     /**
      * 用过课程id和学生id查询选课记录
      * @param courseId 课程id
      * @param studentId 学生id
      * @return 选课记录
      */
     Learn queryRecord(Integer courseId, Integer studentId);

     /**
      * 根据学生id查询该学生已选课程数量
      * @param studentId 学生id
      * @return 已选课程数量
      */
     int selectedCourseNumber(Integer studentId);

     /**
      * 通过学生id查询该学生的所有选课记录
      * @param studentId 学生id
      * @return 所有选课记录对象的列表
      */
     List<Learn> queryLearnByStudentId(Integer studentId);
}
