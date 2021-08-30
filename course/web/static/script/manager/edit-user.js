
var showUsers = function () {
    $.getJSON("editUserServlet","action=userList",function (data) {
        let $table = $("#table");
        $("#form").empty();
        $table.empty();
        let $field = $(
            "<tr>\n" +
            "<td>id</td>\n" +
            "<td>用户名</td>\n" +
            "<td>密码</td>\n" +
            "<td>用户类型</td>\n" +
            "<td>学院</td>\n" +
            "<td>年级</td>\n" +
            "<td>班级</td>\n" +
            "<td colspan=\"2\" style=\"text-align: center\" >操作</td>\n" +
            "</tr>");
        let $addSelect = $(
            "<tr>\n" +
            "<td colspan=\"7\" style=\"text-align: center\" ></td>\n" +
            "<td colspan=\"2\" style=\"text-align: center\" >" +
            "<button class='add'>添加</button></td>\n" +
            "</tr>"
        );
        let $line;

        $field.appendTo($table);
        $addSelect.appendTo($table);
        for (let i=0; i<data.userList.length; i++) {
            $line =$(
                "<tr>\n" +
                "<td>"+data.userList[i].id+"</td>\n" +
                "<td>"+data.userList[i].username+"</td>\n" +
                "<td>"+data.userList[i].password+"</td>\n" +
                "<td>"+data.userList[i].identity+"</td>\n" +
                "<td>"+data.userList[i].institute+"</td>\n" +
                "<td>"+data.userList[i].grade+"</td>\n" +
                "<td>"+data.userList[i].cls+"</td>\n" +
                "<td><button class='update'>修改</button></td>\n" +
                "<td><button class='delete'>删除</button></td>\n" +
                "</tr>");
            $line.appendTo($table);
        }
        $(".add").click(addUser);
        $(".delete").click(deleteUser);
        $(".update").click(updateUser);
    })
}

var addUser =function () {
    let $form = $("#form");
    let $updateTable = $(
        "<table>\n" +
        "    <tr>\n" +
        "        <td><input readonly=\"readonly\" name=\"id\" type=\"text\" value=\"id\"/></td>\n" +
        "        <td><input name=\"username\" type=\"text\" value=\"用户名\" /></td>\n" +
        "        <td><input name=\"password\" type=\"text\" value=\"密码\" /></td>\n" +
        "        <td><input name=\"identity\" type=\"text\" value=\"身份\" /></td>\n" +
        "        <td><input name=\"institute\" type=\"text\" value=\"学院\" /></td>\n" +
        "        <td><input name=\"grade\" type=\"text\" value=\"年级\" /></td>\n" +
        "        <td><input name=\"cls\" type=\"text\" value=\"班级\" /></td>\n" +
        "        <td><input id=\"create\" type=\"submit\" value=\"创建\"/></td>\n" +
        "    </tr>\n" +
        "</table>");

    $form.empty();
    $updateTable.appendTo($form);

    $("#create").click(function () {
        $.getJSON("editUserServlet","action=addUser&"+$form.serialize(),function (data) {
            $("#form").empty();
            if (data.addUserSuccess) {
                alert("添加成功!");
                showUsers();
            } else {
                alert("添加失败！");
            }
        })
        // ？防止事件冒泡，表单提交
        return false;
    })

}

var deleteUser = function () {
    let trObj =$(this).parent().parent();
    let id = trObj.find("td:first").text();
    let username = trObj.find("td").eq(1).text();
    if (confirm("你确定要删除要删除用户["+id+":"+username+"]的账号吗？")){
        $.getJSON("editUserServlet","action=deleteUser&id="+id,function () {
            showUsers();
        })
    }
    return false;
}

var updateUser = function () {
    let $form = $("#form");
    let trObj =$(this).parent().parent();
    let id = trObj.find("td:first").text();
    $.getJSON("userServlet","action=getUser&id="+id,function (data) {
        $form.empty();
        let $updateTable = $(
            "<table>\n" +
            "    <tr>\n" +
            "        <td colspan=\"8\" style=\"text-align: center\" >修改用户["+data.user.username+"]的信息</td>\n"+
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td><input readonly=\"readonly\" name=\"id\" type=\"text\" value=\""+data.user.id+"\"/></td>\n" +
            "        <td><input name=\"username\" type=\"text\" value=\""+data.user.username+"\"/></td>\n" +
            "        <td><input name=\"password\" type=\"text\" value=\""+data.user.password+"\"/></td>\n" +
            "        <td><input name=\"identity\" type=\"text\" value=\""+data.user.identity+"\"/></td>\n" +
            "        <td><input name=\"institute\" type=\"text\" value=\""+data.user.institute+"\"/></td>\n" +
            "        <td><input name=\"grade\" type=\"text\" value=\""+data.user.grade+"\"/></td>\n" +
            "        <td><input name=\"cls\" type=\"text\" value=\""+data.user.cls+"\"/></td>\n" +
            "        <td><input id=\"submit\" type=\"submit\" value=\"提交\"/></td>\n" +
            "    </tr>\n" +
            "</table>");
        $updateTable.appendTo($form);

        $("#submit").click(function () {
            $.getJSON("editUserServlet","action=updateUser&"+$form.serialize(),function (data) {
                $($form).empty();
                if (data.updateUserSuccess) {
                    alert("更新成功!");
                    showUsers();
                } else {
                    alert("更新失败!");
                }
            })
            // ？防止事件冒泡，表单提交
            return false;
        })
    })
}