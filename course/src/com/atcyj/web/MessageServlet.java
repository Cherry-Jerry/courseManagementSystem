package com.atcyj.web;

import com.atcyj.pojo.Message;
import com.atcyj.pojo.User;
import com.atcyj.service.MessageService;
import com.atcyj.service.UserService;
import com.atcyj.service.impl.MessageServiceImpl;
import com.atcyj.service.impl.UserServiceImpl;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenyujie
 */
public class MessageServlet extends BaseServlet {
    MessageService messageService = new MessageServiceImpl();
    UserService userService = new UserServiceImpl();
    public void sendMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User sender = (User)request.getSession().getAttribute("loginUser");
        String msg = request.getParameter("msg");
        String receiver = request.getParameter("receiver");

        List<User> users = userService.getAllUser();
        int count = 0;
        for (User user :users) {
            if (receiver.equals(user.getIdentity())) {
                messageService.sendMessage(sender,user,msg);
                count++;
            }
        }

        Map<String,Object> resultMap = new HashMap<>(16);
        resultMap.put("msg","已将信息[ "+msg+" ]发送给["+count+"]个用户");
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);
    }

    public void showMyMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User receiver = (User)request.getSession().getAttribute("loginUser");
        Map<String,Object> resultMap = new HashMap<>(16);
        List<Message> messages = messageService.showMessageTo(receiver);

        List<String> messageLines = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        for (Message message : messages){
            sb.delete(0, sb.length());
            String senderName = userService.getUsernameById(message.getSenderId());
            String msg = message.getMessage();
            sb.append(senderName).append(" : ").append(msg);
            messageLines.add(sb.toString());
        }

        resultMap.put("messageLines",messageLines);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);
    }


}
