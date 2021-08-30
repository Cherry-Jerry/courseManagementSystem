package com.atcyj.web;

import com.atcyj.pojo.Course;
import com.atcyj.pojo.Learn;
import com.atcyj.pojo.User;
import com.atcyj.service.CourseService;
import com.atcyj.service.LearnService;
import com.atcyj.service.impl.CourseServiceImpl;
import com.atcyj.service.impl.LearnServiceImpl;
import com.atcyj.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseServlet extends BaseServlet{

    private CourseService courseService = new CourseServiceImpl();
    private LearnService learnService = new LearnServiceImpl();

    public void courseList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Course> courses = courseService.getAllCourse();
        Map<String,Object> resultMap = new HashMap<>(16);
        resultMap.put("courseList",courses);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);
    }

    public void chooseList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Course> courses = courseService.getAllCourse();
        request.setAttribute("courses",courses);
        request.getRequestDispatcher("/pages/student/course_choose.jsp").forward(request,response);
    }

    public void getCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Course course = courseService.getCourseById(id);
        Map<String,Object> resultMap = new HashMap<>(16);
        resultMap.put("course",course);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);
    }

    public void showSelected(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User student = (User)request.getSession().getAttribute("loginUser");
        Map<String,Object> resultMap = new HashMap<>(16);
        List<Course> selectedCourses = learnService.getCourseListOf(student);
        resultMap.put("selectedCourses",selectedCourses);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);
    }

    public void addCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Course course = WebUtils.copyParamToBean(request.getParameterMap(),new Course());
        courseService.addCourse(course);
        Map<String,Object> resultMap = new HashMap<>(16);
        resultMap.put("addCourseSuccess",true);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);
    }

    public void deleteCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        courseService.deleteCourseById(request.getParameter("id"));
        Map<String,Object> resultMap = new HashMap<>(16);
        resultMap.put("deleteCourseSuccess",true);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);
    }

    public void updateCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Course course = WebUtils.copyParamToBean(request.getParameterMap(), new Course());
        System.out.println(course);
        courseService.updateCourse(course);
        Map<String,Object> resultMap = new HashMap<>(16);
        resultMap.put("updateCourseSuccess",true);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);
    }

    public void learnBy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取选课信息
        String courseId = request.getParameter("courseId");
        User student = (User)request.getSession().getAttribute("loginUser");
        Course course = courseService.getCourseById(courseId);
        // 判断选课申请是否符合要求
        Map<String,Object> resultMap = tryToLearn(student,course,new HashMap<>(16));
        if (resultMap.containsValue(true)) {
            //添加选课记录
            learnService.addRecord(student,course);
            //已选人数加[1]
            courseService.selectedPlusOne(course);
        }

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);
    }
    private Map<String, Object> tryToLearn(User student, Course course, Map<String, Object> resultMap) {
        resultMap.put("chooseCourseSuccess",false);
        // 课程和学生是否存在
        if (student == null || course == null) {
            resultMap.put("errorMsg","学生或课程不存在！");
            return resultMap;
        }
        //课程是否已选择
        if (learnService.existRecord(student,course)) {
            resultMap.put("errorMsg","课程["+course.getCourseName()+"]已选择！");
            return resultMap;
        }
        //学生课程数已满
        if (learnService.isUptoMaxSelectNumber(student)){
            resultMap.put("errorMsg","最多只能选择[?]门课程！");
            return resultMap;
        }
        // 是否时间冲突
        if (learnService.isTimeConflict(student,course)) {
            resultMap.put("errorMsg","课程["+course.getCourseName()+"]与已选课程有时间冲突！");
            return resultMap;
        }
        //课程人数是否已满
        //@Todo 加锁,更新检查，改位置
        if (courseService.isAllSelected(course)){
            resultMap.put("errorMsg","课程["+course.getCourseName()+"]已选满！");
            return resultMap;
        }
        //其它情况
        resultMap.put("chooseCourseSuccess",true);
        return resultMap;
    }

    public void cancelSelected(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseId = request.getParameter("courseId");
        Course course = courseService.getCourseById(courseId);
        User student = (User)request.getSession().getAttribute("loginUser");
        Map<String,Object> resultMap = new HashMap<>(16);
        learnService.cancelLearn(student,course);
        courseService.selectedSubtractOne(course);
        resultMap.put("cancelSelectedSuccess",true);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);
    }

}
