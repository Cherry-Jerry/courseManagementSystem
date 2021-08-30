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

public class EditUserServlet extends BaseServlet {
    private final UserService userService = new UserServiceImpl();

    public void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = WebUtils.copyParamToBean(request.getParameterMap(),new User());
        Map<String,Object> resultMap = new HashMap<>();
        if (userService.existsUsername(user.getUsername())) {
            resultMap.put("addUserSuccess",false);
        } else {
            userService.addUser(user);
            resultMap.put("addUserSuccess",true);
        }

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);
    }

    public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userService.deleteUserById(request.getParameter("id"));
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("deleteUserSuccess",true);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);

    }

    public void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = WebUtils.copyParamToBean(request.getParameterMap(),new User());
        userService.updateUser(user);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("updateUserSuccess",true);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);
    }

    public void userList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userService.getAllUser();
        Map<String,Object> resultMap = new HashMap<>(16);
        resultMap.put("userList",users);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);
    }
}
