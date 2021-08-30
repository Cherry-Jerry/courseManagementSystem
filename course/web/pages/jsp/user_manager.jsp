<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chenyujie
  Date: 2021/08/16
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/pages/user/jsp/adminPage.jsp"%>
<script type="text/javascript">
    $(function () {
        $("a.deleteClass").click(function () {
            return confirm("你确定要删除账号"+$(this).parent().parent().find("td:first").text()+"吗？");
            return false;
        })
    })
</script>
<body>
    <div id="main">
        <hr>
        <span class="wel_word">这里是用户管理界面</span>
        <hr>
        <table>
            <tr>
                <td>id</td>
                <td>用户名</td>
                <td>密码</td>
                <td>用户类型</td>
                <td>学院</td>
                <td>年级</td>
                <td>班级</td>
                <td colspan="2" style="text-align: center" >操作</td>
            </tr>
            <c:forEach items="${requestScope.users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                    <td>${user.identity}</td>
                    <td>${user.institute}</td>
                    <td>${user.grade}</td>
                    <td>${user.cls}</td>
                    <td><a href="/course/userServlet?action=getUser&method=updateUser&id=${user.id}">修改</a> </td>
                    <td><a class="deleteClass" href="/course/userServlet?action=deleteUser&id=${user.id}"r>删除</a> </td>
                </tr>
            </c:forEach>

            <tr>
                <td colspan="7"></td>
                <td colspan="2" style="text-align: center">
                    <a href="/course/userServlet?action=getUser&method=addUser&id=${user.id}">添加</a>
                </td>
            </tr>
        </table>

    </div>
</body>


