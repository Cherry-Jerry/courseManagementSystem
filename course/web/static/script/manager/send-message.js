
var notice = function () {
    let $form = $("#form");
    $form.empty();
    $("#table").empty();
    let $sendingForm = $(
        "<table>\n" +
        "   <tr><td colspan='3'>信息发送栏</td></tr>\n" +
        "   <tr>\n" +
        "       <td>发送到：</td>\n" +
        "       <td>信息</td>\n" +
        "       <td>操作</td>\n" +
        "   </tr>\n" +
        "   <tr>\n" +
        "       <td>\n" +
        "           <select name='receiver'>\n" +
        "               <option value='student'>student</option>\n" +
        "           </select>\n" +
        "       </td>\n" +
        "       <td><input type=\"text\" id=\"msg\" name='msg'></td>\n" +
        "       <td><input type=\"button\" id=\"send\" value=\"发布\"></td>\n" +
        "   </tr>\n" +
        "</table>"
    )
    $sendingForm.appendTo($form);

    $("#send").click(function () {
        $.getJSON("messageServlet","action=sendMessage&"+$form.serialize(),function (data) {
            alert(data.msg);
        })
        // ？防止事件冒泡，表单提交
        return false;
    })
}