<%@ page import="com.naclo.pojo.Major" %>
<%@ page import="com.naclo.service.MajorService" %>
<%@ page import="com.naclo.service.impl.MajorServiceImpl" %>
<%@ page import="com.naclo.utils.Constants" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>上海电力大学研究生导师双选系统</title>
    <!-- Bootstrap -->
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.bootcdn.net/ajax/libs/bootstrap-table/1.16.0/bootstrap-table.min.css" rel="stylesheet">
    <link href="https://cdn.bootcdn.net/ajax/libs/font-awesome/5.13.0/css/all.css" rel="stylesheet">
    <link href="https://cdn.bootcdn.net/ajax/libs/bootstrap-daterangepicker/3.0.5/daterangepicker.css" rel="stylesheet">

    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/bootstrap-table/1.16.0/bootstrap-table.min.js"></script>
    <%--    <script src="https://cdn.bootcdn.net/ajax/libs/moment.js/2.26.0/moment-with-locales.min.js"></script>--%>
    <script src="https://cdn.bootcdn.net/ajax/libs/moment.js/2.26.0/moment.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/bootstrap-daterangepicker/3.0.5/daterangepicker.js"></script>
    <link href="../css/dashboard.css" rel="stylesheet">

    <script src="../js/common.js"></script>
    <script src="../js/DateFormat.js"></script>
</head>

<body>
<!--topbar-->
<jsp:include page="AdminTopbar.jsp"></jsp:include>
<!--slidebar-->
<jsp:include page="AdminSlidebar.jsp">
    <jsp:param name="pageTitle" value="自主选择时间设定"/>
</jsp:include>
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">

    <!-- 日期时间范围选择代码 -->
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <h3><p>请设定自选时间段</p></h3>
                <hr>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-6">
                <div class="box">
                    <input type="text" id="datePicker" name="datePicker" class="form-control" id="datePicker"
                           value="${applicationScope.startTime} - ${applicationScope.endTime}">
                </div>
            </div>
        </div>
        <br/>
        <div class="row">
            <div class="col-lg-6">
                <div class="box">
                    <button id="submit" value="提交" class="btn btn-primary">提交</button>
                    <button id="closeSystem" value="关闭系统" class="btn btn-primary">关闭系统</button>
                </div>
            </div>
        </div>
</main>

<!-- Copyright -->
<jsp:include page="../commons/copyright.jsp"></jsp:include>

</body>
<script>
    <% //操作成功弹窗
    if(session.getAttribute(Constants.STATE_MESSAGE)==null||"".equals(session.getAttribute(Constants.STATE_MESSAGE))){
    }else{
        String stateMessage = session.getAttribute(Constants.STATE_MESSAGE).toString();
        out.print("alert('"+stateMessage+"');");
    }
    session.setAttribute(Constants.STATE_MESSAGE, "");
%>

    var startTime = new Date().format("yyyy-MM-dd hh:mm");
    var endTime = new Date().format("yyyy-MM-dd hh:mm");

    $('#datePicker').daterangepicker({
        timePicker: true, //显示时间
        timePicker24Hour: true, //时间制
        showWeekNumbers: true,//显示周数
        minDate: new Date(),//过期时间不能选择
        timePickerIncrement: 30,// 分钟选择列表的增量
        linkedCalendars: false,//显示的两个日历将始终为两个连续月份
        locale: {
            format: "YYYY-MM-DD HH:mm", //设置显示格式
            applyLabel: '确定', //确定按钮文本
            cancelLabel: '取消', //取消按钮文本
            customRangeLabel: '自定义',
            daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
            monthNames: ['一月', '二月', '三月', '四月', '五月', '六月',
                '七月', '八月', '九月', '十月', '十一月', '十二月'
            ],
            firstDay: 1
        },
    }, function (start, end) {
        startTime = start.format('YYYY-MM-DD HH:mm')
        endTime = end.format('YYYY-MM-DD HH:mm')
    });

    function updateDate() {
        //var val = $('#datetimes').val();
        console.log(startTime)
        console.log(endTime)
        $.post({})
    }

    $("#submit").click(function () {
        //发起异步请求//参数一：请求的地址；参数二：传递的参数；参数三：回调函数，接收服务器回传的数据
        $.ajax({
            type: "POST",
            url: "admin.do",
            async: false,
            data: {
                method: 'setChooseTime',
                startTime: startTime,
                endTime: endTime
            },
            dataType: "json",
            success: function (data) {
                alert(data.message)
            }
        });
    });
    $("#closeSystem").click(function () {
        $.ajax({
            type: "POST",
            url: "admin.do",
            async: false,
            data: {
                method: 'closeSystem',
            },
            dataType: "json",
            success: function (data) {
                alert(data.message)
                window.location.reload()
            }
        });
    });

</script>

</html>