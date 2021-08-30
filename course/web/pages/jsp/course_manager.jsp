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
            return confirm("你确定要删除课程"+$(this).parent().parent().find("td:first").text()+"吗？");
            return false;
        })
    })
</script>
<body>
<div id="main">
    <hr>
    <span class="wel_word">这里是课程管理界面</span>
    <hr>
    <table>
        <tr>
            <td>id</td>
            <td>课程名</td>
            <td>课程详情</td>
            <td>开课人数</td>
            <td>已选人数</td>
            <td colspan="2" style="text-align: center" >操作</td>
        </tr>
        <c:forEach items="${requestScope.courses}" var="course">
            <tr>
                <td>${course.id}</td>
                <td>${course.courseName}</td>
                <td>${course.information}</td>
                <td>${course.places}</td>
                <td>${course.selected}</td>
                <td><a href="/course/courseServlet?action=getCourse&method=updateCourse&id=${course.id}">修改</a> </td>
                <td><a class="deleteClass" href="/course/courseServlet?action=deleteCourse&id=${course.id}"r>删除</a> </td>
            </tr>
        </c:forEach>

        <tr>
            <td colspan="5"></td>
            <td colspan="2" style="text-align: center">
                <a href="/course/courseServlet?action=getCourse&method=addCourse&id=${user.id}">添加</a>
            </td>
        </tr>
    </table>

</div>
</body>