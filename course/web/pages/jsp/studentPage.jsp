<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理员界面</title>
    <%@include file="/pages/common/head.jsp"%>
</head>
<body>
<div id="header">
    <h1>
        <span class="wel_word">学生选课系统</span>
    </h1>
    <span>欢迎${sessionScope.loginUser.username}使用选课系统</span>
    <div>
        <a href="/course/courseServlet?action=chooseList">选择课程</a>
        <a href="/course/userServlet?action=logout">退出登录</a>
    </div>
</div>
</body>
</html>
