package com.atcyj.web;

import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TimeServlet extends BaseServlet {
    public void setOpenTime(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        // 把时间封装成Date openTime
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String date = request.getParameter("date");
        String hour = request.getParameter("hour");
        String minute = "00";
        String second = "00";
        StringBuffer sb = new StringBuffer();
        sb.append(year).append(".").append(month).append(".").append(date).append(" ").append(hour).append(":").append(minute).append(":").append(second);
        // properties 保存 openTime
        Properties properties = new Properties();
        System.out.println(sb);
        properties.setProperty("openTime",sb.toString());
        FileWriter fw = new FileWriter("D:\\IdeaProjects\\StudentManagementSystem\\course\\src\\com\\atcyj\\web\\timeConfig.txt");
        properties.store(fw,null);
        fw.close();
        // 返回提示
        Map<String,Object> resultMap = new HashMap<>(16);
        resultMap.put("setOpenTimeSuccess",true);
        resultMap.put("openTime",sb.toString());
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);
    }
    public void showOpenTime(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        Properties properties = new Properties();
        FileReader fr = new FileReader("D:\\IdeaProjects\\StudentManagementSystem\\course\\src\\com\\atcyj\\web\\timeConfig.txt");
        properties.load(fr);
        fr.close();
        String openTime = properties.getProperty("openTime");

        Map<String,Object> resultMap = new HashMap<>(16);
        resultMap.put("openTime",openTime);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);
    }
}
