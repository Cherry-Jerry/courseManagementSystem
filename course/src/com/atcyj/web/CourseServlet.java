package com.atcyj.web;

import com.atcyj.pojo.Course;
import com.atcyj.pojo.Learn;
import com.atcyj.pojo.User;
import com.atcyj.service.CourseService;
import com.atcyj.service.LearnService;
import com.atcyj.service.TimeService;
import com.atcyj.service.impl.CourseServiceImpl;
import com.atcyj.service.impl.LearnServiceImpl;
import com.atcyj.service.impl.TimeServiceImpl;
import com.atcyj.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenyujie
 */
public class CourseServlet extends BaseServlet{

    private CourseService courseService = new CourseServiceImpl();
    private LearnService learnService = new LearnServiceImpl();
    private TimeService timeService = new TimeServiceImpl();

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
        Integer id = Integer.parseInt(request.getParameter("id"));
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
        courseService.deleteCourseById(Integer.parseInt(request.getParameter("id")));
        Map<String,Object> resultMap = new HashMap<>(16);
        resultMap.put("deleteCourseSuccess",true);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);
    }

    public void updateCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Course course = WebUtils.copyParamToBean(request.getParameterMap(), new Course());
        courseService.updateCourse(course);
        Map<String,Object> resultMap = new HashMap<>(16);
        resultMap.put("updateCourseSuccess",true);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);
    }

    public void learnBy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ??????????????????
        Integer courseId = Integer.parseInt(request.getParameter("courseId"));
        User student = (User)request.getSession().getAttribute("loginUser");
        Course course = courseService.getCourseById(courseId);
        // ????????????????????????????????????
        Map<String,Object> resultMap = tryToLearn(student,course,new HashMap<>(16));
        if (resultMap.containsValue(true)) {
            //??????????????????
            learnService.addRecord(student,course);
            //???????????????[1]
            courseService.selectedPlusOne(course);
        }

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);
    }
    private Map<String, Object> tryToLearn(User student, Course course, Map<String, Object> resultMap) {
        resultMap.put("chooseCourseSuccess",false);
        // ???????????????????????????
        if (student == null || course == null) {
            resultMap.put("errorMsg","???????????????????????????");
            return resultMap;
        }
        // ????????????????????????
        if (timeService.isBeforeOpenTime()) {
            resultMap.put("errorMsg","???????????????????????????");
            return resultMap;
        }
        // ?????????????????????
        if (learnService.existRecord(student,course)) {
            resultMap.put("errorMsg","??????["+course.getCourseName()+"]????????????");
            return resultMap;
        }
        //?????????????????????
        if (learnService.isUptoMaxSelectNumber(student)){
            resultMap.put("errorMsg","??????????????????[?]????????????");
            return resultMap;
        }
        // ??????????????????
        if (learnService.isTimeConflict(student,course)) {
            resultMap.put("errorMsg","??????["+course.getCourseName()+"]?????????????????????????????????");
            return resultMap;
        }
        //????????????????????????
        //@Todo ??????,????????????????????????
        if (courseService.isAllSelected(course)){
            resultMap.put("errorMsg","??????["+course.getCourseName()+"]????????????");
            return resultMap;
        }
        //????????????
        resultMap.put("chooseCourseSuccess",true);
        return resultMap;
    }

    public void cancelSelected(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer courseId = Integer.parseInt(request.getParameter("courseId"));
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
