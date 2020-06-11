<%@ page import="com.naclo.pojo.Major" %>
<%@ page import="com.naclo.service.MajorService" %>
<%@ page import="com.naclo.service.TeacherService" %>
<%@ page import="com.naclo.service.impl.MajorServiceImpl" %>
<%@ page import="com.naclo.service.impl.TeacherServiceImpl" %>
<%@ page import="com.naclo.utils.Constants" %>
<%@ page import="java.util.List" %>
<%@ page import="com.naclo.pojo.Teacher" %>
<%@ page import="java.util.ArrayList" %>
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

    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/bootstrap-table/1.16.0/bootstrap-table.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/bootstrap-table/1.16.0/locale/bootstrap-table-zh-CN.js"></script>

    <link href="../css/dashboard.css" rel="stylesheet">

    <script src="../js/common.js"></script>
</head>
<%
    MajorService majorService = new MajorServiceImpl();
    TeacherService teacherService = new TeacherServiceImpl();
    List<Major> majorList = majorService.queryAllMajors();
    String adminMajor = (String) (session.getAttribute(Constants.USER_MAJOR));
    List<Teacher> teacherList = teacherService.queryAllTeachers();
    if (!"ALL".equals(adminMajor)) {
        teacherList = teacherService.queryTeacherByMajor(adminMajor);
    }
%>
<body>
<!--topbar-->
<jsp:include page="AdminTopbar.jsp"></jsp:include>
<!--slidebar-->
<jsp:include page="AdminSlidebar.jsp">
    <jsp:param name="pageTitle" value="审核结果"/>
</jsp:include>

<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
    <div class="panel-body" style="padding-bottom:0px;">
        <%--toolbar--%>
        <div id="toolbar" class="btn-group">
            <button id="btn_choose" type="button" onclick="chooseIdeas()" class="btn btn-primary">同意
            </button>
        </div>

        <%--table--%>
        <table id="ideaTable"></table>
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
    $('#ideaTable').bootstrapTable({
        ajax: function (request) {//使用ajax请求
            $.ajax({
                type: "GET",
                url: '/admin/admin.do',
                contentType: 'application/json;charset=utf-8',
                dataType: 'json',
                data: {method: "getTeacherSelectStudentList"},
                success: function (res) {
                    request.success({
                        row: res,
                    });
                    $('#ideaTable').bootstrapTable('load', res);
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
        pagination: true,                //是否分页
        sidePagination: 'client',        //server:服务器端分页|client：前端分页
        //queryParams: 'queryParams',    //传递参数
        search: true,                    //是否显示表格搜索
        sortName: "studentId",           //定义要排序的列
        sortOrder: "asc",                //排序方式
        pageSize: 10,                    //单页记录数
        clickToSelect: true,             //是否启用点击选中行
        pageList: [10],                  //可选择单页记录数
        showRefresh: true,               //刷新按钮
        showToggle: false,               //是否显示详细视图和列表视图的切换按钮
        cardView: false,                 //是否显示详细视图
        showColumns: true,               //是否显示所有的列
        switchable: false,               //禁用可切换的列项
        height: $(window).height() - 70,   //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "studentId",           //每一行的唯一标识，一般为主键列
        columns: [{
            checkbox: true
        }, {
            title: '学号',
            field: 'studentId',
            sortable: true,
            width: 150
        }, {
            title: '学生姓名',
            field: 'studentName',
            sortable: true,
            width: 150
        }, {
            title: '工号',
            field: 'teacherId',
            sortable: true,
            width: 150
        }, {
            title: '导师姓名',
            field: 'teacherName',
            sortable: true,
            width: 150
        }, {
            title: '指定导师',
            field: 'setTeacher',
            sortable: true,
            width: 150,
            formatter: operation,//对资源进行操作
        }]
    });


    function operation(value, row, index) {
        var htm = "<form action='admin.do'>" +
            "<input type='hidden' name='method' value='adminSetTeacher'>" +
            "<input type='hidden' name='studentId' value=" + row["studentId"] + ">" +
            "<select name=teacherId>" +
            <%
            out.print("'");
            for (Teacher teacher : teacherList) {
                String teacherName = teacher.getTeacherName();
                String teacherId = teacher.getTeacherId();
                out.print("<option value="+teacherId+">"+teacherName+"</option>");
            }
                out.print("'");
            %>
            +"</select>" +
            "<input type='submit'>" +
            "</form>"
        return htm;
    }

    //确定选择学生操作
    function chooseIdeas() {
        var row = $.map($('#ideaTable').bootstrapTable('getSelections'), function (row) {
            return row;
        });

        var ideaLength = row.length;
        if (ideaLength > 10) {
            alert("人数大于十个")
            return false;
        } else if (ideaLength == 0) {
            alert("请选择志愿")
            return false;
        } else {
            var result = confirm("您选择了" + ideaLength + "个人志愿，是否确定选择？");
            if (result == true) {
                var href = "admin.do?method=adminAuditTeacherDecide";
                for (let i = 1; i <= ideaLength; i++) {
                    href += "&ideaId" + "=" + row[i - 1].ideaId;
                }
                window.location.href = href;
            }
        }
    }
</script>

</html>