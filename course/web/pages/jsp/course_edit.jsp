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
    <form action="/course/courseServlet" method="post">
        <input type="hidden" name="action" value="${param.method}">
        <table>
            <tr>
                <td>id</td>
                <td>课程名</td>
                <td>课程详情</td>
                <td>开课人数</td>
                <td>已选人数</td>
                <td colspan="1" style="text-align: center" >操作</td>
            </tr>
            <tr>
                <td><input name="id" type="text" value="${course.id}"/></td>
                <td><input name="courseName" type="text" value="${course.courseName}"/></td>
                <td><input name="information" type="text" value="${course.information}"/></td>
                <td><input name="places" type="text" value="${course.places}"/></td>
                <td><input name="selected" type="text" value="${course.selected}"/></td>
                <td><input type="submit" value="提交"/></td>
            </tr>
        </table>
    </form>
</div>
</body>