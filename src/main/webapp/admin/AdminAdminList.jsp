<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/bootstrap-table/1.16.0/bootstrap-table.min.js"></script>

    <link href="../css/dashboard.css" rel="stylesheet">
</head>

<body>
<!--topbar-->
<jsp:include page="AdminTopbar.jsp"></jsp:include>
<!--slidebar-->
<jsp:include page="AdminSlidebar.jsp">
    <jsp:param name="pageTitle" value="管理管理员"/>
</jsp:include>

<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
    <div class="panel-body" style="padding-bottom:0px;">
        <div id="toolbar" class="btn-group">
            <button id="btn_add" type="button" class="btn btn-default">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
            </button>
            <button id="btn_import" type="button" class="btn btn-default">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>导入
            </button>
            <button id="btn_export" type="button" class="btn btn-default">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>导出
            </button>
        </div>

        <table id="mytab"></table>
    </div>

</main>

<!-- Copyright -->
<jsp:include page="../commons/copyright.jsp"></jsp:include>

</body>
<script>

    $('#mytab').bootstrapTable({
        method: 'POST',
        //url: "/queryAll",//请求路径
        //method: 'post',//请求方式（*）
        url: "../data/StudentData.json",//请求路径
        toolbar: '#toolbar',                //工具按钮用哪个容器
        striped: true, //是否显示行间隔色
        cache: false,//是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pageNumber: 1, //初始化加载第一页
        pagination: true,//是否分页
        sidePagination: 'client',//server:服务器端分页|client：前端分页
        sortOrder: "asc",                   //排序方式
        pageSize: 10,//单页记录数
        clickToSelect: true,                //是否启用点击选中行
        pageList: [10],//可选择单页记录数
        showRefresh: true,//刷新按钮
        columns: [{
            checkbox: true
        }, {
            title: '学号',
            field: 'studentId',
            sortable: true
        }, {
            title: '姓名',
            field: 'name',
            sortable: true
        }, {
            title: '专业',
            field: 'dept',
        }, {
            title: '操作',
            field: 'publicationTime',
            formatter: operation,//对资源进行操作
        }]
    });

    function operation(value, row, index) {
        var htm = "<a href='/deleteUserData?studentId=" + row["studentId"] + "'>删除</a> " +
            "<a href='/modifyUserData?studentId=" + row["studentId"] + "'>修改</a> " +
            "<a href='/resetPassword?studentId=" + row["studentId"] + "'>密码重置</a>";
        return htm;
    }
</script>

</html>