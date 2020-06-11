<%@ page import="com.naclo.utils.Constants" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.ParseException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
%>
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

    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/bootstrap-table/1.16.0/bootstrap-table.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/bootstrap-table/1.16.0/locale/bootstrap-table-zh-CN.js"></script>

    <link href="../css/dashboard.css" rel="stylesheet">


    <script src="../js/common.js" charset="UTF-8"></script>

</head>
<%
    Object startTime = application.getAttribute("startTime");
    Object endTime = application.getAttribute("endTime");
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    Date startDate = new Date();
    Date endDate = new Date();
    System.out.println("startTime = " + startTime);
    try {
        if (null != startTime && !"".equals(startTime)) {
            startDate = format.parse(startTime.toString());
        }
        if (null != endTime && !"".equals(endTime)) {
            endDate = format.parse(endTime.toString());
        }
    } catch (ParseException e) {
        e.printStackTrace();
    }

%>
<body>
<!--topbar-->
<jsp:include page="StudentTopbar.jsp"></jsp:include>
<!--slidebar-->
<jsp:include page="StudentSlidebar.jsp">
    <jsp:param name="pageTitle" value="选择导师"/>
</jsp:include>

<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
    <div class="panel-body" style="padding-bottom:0px;">
        <%--table--%>

        <%
            Date today = new Date();
            if (!(today.after(startDate) && today.before(endDate))) {
                out.print("        <div id=\"toolbar\" class=\"btn-group\">\n" +
                        "            <button id=\"btn_choose\" type=\"button\" onclick=\"chooseTeacher()\" class=\"btn btn-primary\">选择\n" +
                        "            </button>\n" +
                        "        </div>");
                out.print("<table id=\"teacherTable\"></table>");
            } else {
                out.print("<h1 style=\"color: red\">系统未开放</h1>");
                if (null != startTime && null != endTime) {
                    out.print("<h1><p>系统开放时间</p>");
                    out.print(startTime);
                    out.print("~");
                    out.print(endTime);
                    out.print("</h1>");
                }
            }
        %>
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
    $('#teacherTable').bootstrapTable({
        ajax: function (request) {//使用ajax请求
            $.ajax({
                type: "GET",
                url: '/student/student.do',
                contentType: 'application/json;charset=utf-8',
                dataType: 'json',
                data: {method: "getTeacherList"},
                success: function (res) {
                    request.success({
                        row: res,
                    });
                    $('#teacherTable').bootstrapTable('load', res);
                },
                error: function (error) {
                    console.log(error);
                }
            })
        },
        toolbar: '#toolbar',             //工具按钮用哪个容器
        striped: true,                   //是否显示行间隔色
        cache: false,                    //是否使用缓存
        pageNumber: 1,                   //初始化加载第一页
        pagination: false,                //是否分页
        sidePagination: 'client',        //server:服务器端分页|client：前端分页
        //queryParams: 'queryParams',    //传递参数
        search: true,                    //是否显示表格搜索
        sortName: "teacherId",           //定义要排序的列
        sortOrder: "asc",                //排序方式
        pageSize: 10,                    //单页记录数
        clickToSelect: true,             //是否启用点击选中行
        pageList: [10],                  //可选择单页记录数
        showRefresh: true,               //刷新按钮
        showToggle: false,               //是否显示详细视图和列表视图的切换按钮
        cardView: false,                 //是否显示详细视图
        showColumns: true,               //是否显示所有的列
        switchable: false,               //禁用可切换的列项
        height: $(window).height() - 80,   //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "teacherId",           //每一行的唯一标识，一般为主键列
        columns: [{
            checkbox: true
        }, {
            title: '工号',
            field: 'teacherId',
            sortable: true,
            width: 200
        }, {
            title: '姓名',
            field: 'teacherName',
            sortable: true,
            width: 200
        }, {
            title: '专业',
            field: 'teacherMajor',
            width: 200
        }, {
            title: '简介',
            field: 'teacherIntroduce',
            width: 300
        }]
    });

    //确定选择导师操作
    function chooseTeacher() {
        var row = $.map($('#teacherTable').bootstrapTable('getSelections'), function (row) {
            return row;
        });
        if (row.length > 3) {
            alert("人数大于三个")
            return false;
        } else if (row.length < 3) {
            alert("人数不足三个")
            return false;
        } else {
            var result = confirm("是否确定选择导师？");
            if (result == true) {
                var teacherId1 = row[0].teacherId;
                var teacherId2 = row[1].teacherId;
                var teacherId3 = row[2].teacherId;
                window.location.href = "student.do?method=studentChooseTeacher" +
                    "&teacherId1=" + teacherId1 +
                    "&teacherId2=" + teacherId2 +
                    "&teacherId3=" + teacherId3;
            }
        }
    }
</script>

</html>