<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta charset="UTF-8">
    <title>cyj选课管理系统-用户注册界面</title>
    <%@include file="/pages/common/head.jsp"%>
    <script type="text/javascript">
        //页面加载后
        $(function () {
            //登录绑定单击事件
            $("#sub_btn").click(function () {
                //验证用户名是否符合规范
                //1、获取输入框内容
                var usernameText = $("#username").val();
                //2、创建正则表达式对象
                var usernamePatt = /^\w{4,12}$/;
                //3、使用test方法
                if (!usernamePatt.test(usernameText)) {
                    //4、提示用户结果
                    $("span.errorMsg").text("用户名不合法");
                    return false;
                }
                //验证用户名和密码是合法
                //1、获取输入框内容
                var passwordText = $("#password").val();
                //2、创建正则表达式对象
                var passwordPatt = /^\w{4,12}$/;
                //3、使用test方法
                if (!passwordPatt.test(passwordText)) {
                    //4、提示用户结果
                    $("span.errorMsg").text("密码不合法");
                    return false;
                }

                var passwordForCheckText = $("#passwordForCheck").val();
                if(passwordText != passwordForCheckText){
                    $("span.errorMsg").text("密码不相同");
                    return false;
                }

            })

        })
    </script>
</head>
<body>
<div class="text" style=" text-align:center;">
    <div class="msg_cont">
        <b><span class="errorMsg" style="color: darkred">${empty requestScope.msg?"欢迎来到注册页面":requestScope.msg}</span></b>
    </div>
    <div class="form">
        <form action="/course/userServlet" method="post">
            <input type="hidden" name="action" value="register">
            <label>用户名称：</label>
            <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username"
                   id="username"/>
            <br/>
            <br/>
            <label>用户密码：</label>
            <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password"
                   id="password"/>
            <br/>
            <label>确认密码：</label>
            <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password"
                   id="passwordForCheck"/>
            <br/>
            <br/>
            <input type="submit" value="确认" id="sub_btn"/>
        </form>
        <form action="pages/user/login.jsp">
            <input type="submit" value="返回" id="sub_btn2"/>
        </form>
    </div>
</div>

</body>
</html>