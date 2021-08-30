
var showNotice = function () {
    let $noticeTable = $("#noticeTable");
    let $headLine = $(
        "   <tr><td style='width: 200pc;text-align: center' >信息栏</td></tr>\n");

    $("#selectedTable").empty();
    $("#chooseTable").empty();
    $noticeTable.empty();
    $headLine.appendTo($noticeTable);

    $.getJSON("messageServlet","action=showMyMessage",function (data) {
        let $messageLine;
        for (let i=0; i<data.messageLines.length; i++) {
            $messageLine = $(
                "   <tr>\n" +
                "       <td>"+data.messageLines[i]+"</td>\n" +
                "   </tr>\n");
            $messageLine.appendTo($noticeTable);
        }
    })

}