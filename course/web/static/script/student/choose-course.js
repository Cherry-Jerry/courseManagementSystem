
var showCourses = function () {
    $.getJSON("courseServlet","action=courseList",function (data) {
        let $chooseTable = $("#chooseTable");
        $chooseTable.empty();
        let $field = $(
            "<tr>\n" +
            "   <td colSpan=\"7\" style=\"text-align: center\">可选课程</td>\n" +
            "</tr>"+
            "<tr>\n" +
            "    <td>id</td>\n" +
            "    <td>课程名</td>\n" +
            "    <td>课程详情</td>\n" +
            "    <td>开课人数</td>\n" +
            "    <td>已选人数</td>\n" +
            "    <td colspan=\"2\" style=\"text-align: center\" >操作</td>\n" +
            "</tr>");
        let $line;

        $field.appendTo($chooseTable);
        for (let i=0; i<data.courseList.length; i++) {
            $line =$(
                "<tr>\n" +
                "<td>"+data.courseList[i].id+"</td>\n" +
                "<td>"+data.courseList[i].courseName+"</td>\n" +
                "<td>"+data.courseList[i].information+"</td>\n" +
                "<td>"+data.courseList[i].places+"</td>\n" +
                "<td>"+data.courseList[i].selected+"</td>\n" +
                "<td><button class='select'>选择</button></td>\n" +
                "<td><button class='showInfo'>详情</button></td>\n" +
                "</tr>");
            $line.appendTo($chooseTable);
        }
        $(".select").click(chooseCourse);
        $(".showInfo").click(showInfo);
    })
    return false;
}

var chooseCourse = function () {
    let trObj =$(this).parent().parent();
    let courseId = trObj.find("td:first").text();
    $.getJSON("courseServlet","action=learnBy&courseId="+courseId,function (data) {
        if (data.chooseCourseSuccess) {
            alert("选课成功！");
            showSelected();
        } else {
            alert(data.errorMsg);
        }
        showCourses();
    })
    return false;
}
var showInfo =function () {
    alert("展示信息2");
    return false;
}

var showSelected = function () {
    let $selectedTable = $("#selectedTable");
    let $headline = $(
        "<tr>\n" +
        "    <td colspan=\"7\" style=\"text-align: center\">已选课程</td>\n" +
        "</tr>\n" +
        "<tr>\n" +
        "    <td>id</td>\n" +
        "    <td>课程名</td>\n" +
        "    <td>课程详情</td>\n" +
        "    <td>开课人数</td>\n" +
        "    <td>已选人数</td>\n" +
        "    <td colspan=\"2\" style=\"text-align: center\" >操作</td>\n" +
        "</tr>");
    $selectedTable.empty();
    $headline.appendTo($selectedTable);
    $.getJSON("courseServlet","action=showSelected",function (data) {
        let $selectLine;
        for (let i=0; i<data.selectedCourses.length; i++) {
            $selectLine= $(
                "<tr>\n" +
                "<td>"+data.selectedCourses[i].id+"</td>\n" +
                "<td>"+data.selectedCourses[i].courseName+"</td>\n" +
                "<td>"+data.selectedCourses[i].information+"</td>\n" +
                "<td>"+data.selectedCourses[i].places+"</td>\n" +
                "<td>"+data.selectedCourses[i].selected+"</td>\n" +
                "<td colspan=\"2\" style=\"text-align: center\" >" +
                "<button class='cancelSelect'>取消选择</button></td>\n" +
                "</tr>");
            $selectLine.appendTo($selectedTable);
        }
        $(".cancelSelect").click(cancelSelect);
    })
    return false;
}

var cancelSelect = function () {
    let trObj =$(this).parent().parent();
    let courseId = trObj.find("td:first").text();
    $.getJSON("courseServlet","action=cancelSelected&courseId="+courseId,function (data) {
        if (data.cancelSelectedSuccess) {
            alert("已取消选择！");
            showSelected();
        } else {
            alert("取消失败！");
        }
        showCourses();
    })
    return false;
}



