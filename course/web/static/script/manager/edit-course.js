
var getCourses = function () {
    $.getJSON("courseServlet","action=courseList",function (data) {
        let $table = $("#table");
        $table.empty();
        $("#form").empty();
        let $field = $(
            "<tr>\n" +
            "    <td>id</td>\n" +
            "    <td>课程名</td>\n" +
            "    <td>课程详情</td>\n" +
            "    <td>开课人数</td>\n" +
            "    <td>已选人数</td>\n" +
            "    <td colspan=\"2\" style=\"text-align: center\" >操作</td>\n" +
            "</tr>");
        let $addSelect = $(
            "<tr>\n" +
            "<td colspan=\"5\" style=\"text-align: center\" ></td>\n" +
            "<td colspan=\"2\" style=\"text-align: center\" >" +
            "<button class='add'>添加</button></td>\n" +
            "</tr>"
        );
        let $line;

        $field.appendTo($table);
        $addSelect.appendTo($table);
        for (let i=0; i<data.courseList.length; i++) {
            $line =$(
                "<tr>\n" +
                "<td>"+data.courseList[i].id+"</td>\n" +
                "<td>"+data.courseList[i].courseName+"</td>\n" +
                "<td>"+data.courseList[i].information+"</td>\n" +
                "<td>"+data.courseList[i].places+"</td>\n" +
                "<td>"+data.courseList[i].selected+"</td>\n" +
                "<td><button class='update'>修改</button></td>\n" +
                "<td><button class='delete'>删除</button></td>\n" +
                "</tr>");
            $line.appendTo($table);
        }
        $(".add").click(addCourse);
        $(".delete").click(deleteCourse);
        $(".update").click(updateCourse);
    })
}

var addCourse =function () {
    let $form = $("#form");
    let $updateTable = $(
        "<table>\n" +
        "   <tr>\n" +
        "    <td><input readonly=\"readonly\" name=\"id\" type=\"text\" value=\"id\"/></td>\n" +
        "    <td><input name=\"courseName\" type=\"text\" value=\"课程名\"/></td>\n" +
        "    <td><input name=\"information\" type=\"text\" value=\"课程信息\"/></td>\n" +
        "    <td><input name=\"places\" type=\"text\" value=\"开课人数\"/></td>\n" +
        "    <td><input name=\"selected\" type=\"text\" value=\"已选人数\"/></td>\n" +
        "    <td><input id=\"create\" type=\"submit\" value=\"提交\"/></td>\n" +
        "   </tr>\n"+
        "</table>");
    $updateTable.appendTo($form);

    $("#create").click(function () {
        $.getJSON("courseServlet","action=addCourse&"+$form.serialize(),function (data) {
            $($form).empty();
            if (data.addCourseSuccess) {
                alert("更新成功!");
                getCourses();
            } else {
                alert("更新失败!");
            }
        })
        // ？防止事件冒泡，表单提交
        return false;
    })
}

var deleteCourse = function () {
    let trObj =$(this).parent().parent();
    let id = trObj.find("td:first").text();
    let courseName = trObj.find("td").eq(1).text();
    if (confirm("你确定要删除要删除课程["+id+":"+courseName+"]吗？")){
        $.getJSON("courseServlet","action=deleteCourse&id="+id,function () {
            getCourses();
        })
    }
    return false;
}

var updateCourse = function () {
    let $form = $("#form");
    let trObj =$(this).parent().parent();
    let id = trObj.find("td:first").text();
    $.getJSON("courseServlet","action=getCourse&id="+id,function (data) {
        $form.empty();
        let $updateTable = $(
            "<table>\n" +
            "    <tr>\n" +
            "        <td colspan=\"6\" style=\"text-align: center\" >修改课程["+data.course.courseName+"]的信息</td>\n"+
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td><input readonly=\"readonly\" name=\"id\" type=\"text\" value=\""+data.course.id+"\"/></td>\n" +
            "        <td><input name=\"courseName\" type=\"text\" value=\""+data.course.courseName+"\"/></td>\n" +
            "        <td><input name=\"information\" type=\"text\" value=\""+data.course.information+"\"/></td>\n" +
            "        <td><input name=\"places\" type=\"text\" value=\""+data.course.places+"\"/></td>\n" +
            "        <td><input name=\"selected\" type=\"text\" value=\""+data.course.selected+"\"/></td>\n" +
            "        <td><input id=\"submit\" type=\"submit\" value=\"提交\"/></td>\n" +
            "   </tr>\n"+
            "</table>");
        $updateTable.appendTo($form);

        $("#submit").click(function () {
            $.getJSON("courseServlet","action=updateCourse&"+$form.serialize(),function (data) {
                $($form).empty();
                if (data.updateCourseSuccess) {
                    alert("更新成功!");
                    getCourses();
                } else {
                    alert("更新失败!");
                }
            })
            // ？防止事件冒泡，表单提交
            return false;
        })
    })
}