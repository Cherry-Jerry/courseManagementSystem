<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta charset="UTF-8">
    <title>cyj选课管理系统-用户登录界面</title>
<%@include file="/pages/common/head.jsp"%>
    <script type="text/javascript" src="static/script/limitUsernameAndPassword.js"></script>
</head>
<body>
<div class="text" style=" text-align:center;">

    <div class="msg_cont">
        <b><span class="errorMsg" style="color: darkred">${empty requestScope.msg ? "请输入用户名和密码":requestScope.msg}</span></b>
    </div>
    <div class="form">
        <form action="/course/userServlet" method="post">
            <input type="hidden" name="action" value="login">
            <label>用户名称：</label>
            <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username"
                   id="username" value="${requestScope.username}"/>
            <br/>
            <br/>
            <label>用户密码：</label>
            <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password"
                   id="password"/>
            <br/>
            <br/>
            <input type="submit" value="登录" id="sub_btn1"/>
        </form>
            <form action="pages/user/register.jsp">
                <input type="submit" value="注册" id="sub_btn2"/>
            </form>
    </div>
</div>
</body>
</html>