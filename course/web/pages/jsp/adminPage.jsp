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
				<span class="wel_word">后台管理系统</span>
			</h1>

			<div>
				<a href="/course/userServlet?action=list">用户管理</a>
				<a href="/course/courseServlet?action=coursesList">课程管理</a>
				<a href="/course/userServlet?action=logout">退出登录</a>
			</div>
		</div>
</body>
</html>
