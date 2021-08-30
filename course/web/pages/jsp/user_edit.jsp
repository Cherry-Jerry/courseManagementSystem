<%--
  Created by IntelliJ IDEA.
  User: chenyujie
  Date: 2021/08/16
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/pages/user/jsp/adminPage.jsp"%>
<body>
    <div id="main">
        <form action="/course/userServlet" method="post">
            <input type="hidden" name="action" value="${param.method}">
            <table>
                <tr>
                    <td>id</td>
                    <td>用户名</td>
                    <td>密码</td>
                    <td>用户类型</td>
                    <td>学院</td>
                    <td>年级</td>
                    <td>班级</td>
                    <td>操作</td>
                </tr>
                <tr>
                    <td><input name="id" type="text" value="${user.id}"/></td>
                    <td><input name="username" type="text" value="${user.username}"/></td>
                    <td><input name="password" type="text" value="${user.password}"/></td>
                    <td><input name="identity" type="text" value="${user.identity}"/></td>
                    <td><input name="institute" type="text" value="${user.institute}"/></td>
                    <td><input name="grade" type="text" value="${user.grade}"/></td>
                    <td><input name="cls" type="text" value="${user.cls}"/></td>
                    <td><input type="submit" value="提交"/></td>
                </tr>
            </table>
        </form>
    </div>
</body>