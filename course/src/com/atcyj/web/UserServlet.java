package com.atcyj.web;

import com.atcyj.pojo.User;
import com.atcyj.service.UserService;
import com.atcyj.service.impl.UserServiceImpl;
import com.atcyj.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServlet extends BaseServlet {
    private final UserService userService = new UserServiceImpl();

    public void existsUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        Boolean existsUsername = userService.existsUsername(username);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername",existsUsername);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loginUser = WebUtils.copyParamToBean(request.getParameterMap(),new User());
        loginUser = userService.login(loginUser);
        Map<String,Object> resultMap = new HashMap<>();
        if (loginUser == null){
            //找不到账户
            resultMap.put("loginSuccess",false);
            resultMap.put("username",request.getParameter("username"));
        } else {
            resultMap.put("loginSuccess",true);
            resultMap.put("loginUser",loginUser);
            request.getSession().setAttribute("loginUser",loginUser);
        }
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        Map<String,Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);
    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User registerUser = WebUtils.copyParamToBean(request.getParameterMap(),new User());
        String username = registerUser.getUsername();
        userService.registerUser(registerUser);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("registerSuccess",true);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);

    }

    public void getUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        User user = userService.getUserById(id);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("user",user);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);
    }

}


