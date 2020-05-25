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

    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/bootstrap-table/1.16.0/bootstrap-table.min.js"></script>

    <link href="../css/dashboard.css" rel="stylesheet">

    <script src="../js/common.js"></script>
</head>

<body>
<!--topbar-->
<jsp:include page="AdminTopbar.jsp"></jsp:include>
<!--slidebar-->
<jsp:include page="AdminSlidebar.jsp">
    <jsp:param name="pageTitle" value="管理管理员"/>
</jsp:include>
<%
    MajorService majorService = new MajorServiceImpl();
    List<Major> majorList = majorService.queryAllMajors();
    String adminMajor = (String) (session.getAttribute(Constants.USER_MAJOR));
    if (!"ALL".equals(adminMajor)) {
        majorList = majorService.queryMajorByName(adminMajor);
    }
%>
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
    <div class="panel-body" style="padding-bottom:0px;">
        <div id="toolbar" class="btn-group">
            <button id="btn_add" type="button" class="btn btn-primary" data-toggle="modal"
                    data-target="#addAdminModel">新增
            </button>
            <button id="btn_import" type="button" onclick="openUploadDialog()" class="btn btn-primary">导入
            </button>
            <button id="btn_export" type="button" class="btn btn-primary">
                <a href="/admin/admin.do?method=exportAdminList" style="color: white">导出
                </a>
            </button>
            <form name="uploadFileForm" id="uploadFileForm" method="post" enctype="multipart/form-data"
                  action="/admin/admin.do?method=importAdminList">
                <input type="file" name="uploadFile" id="btnFileUpload" hidden onchange="submitForm()">
            </form>
        </div>

        <%--table--%>
        <table id="adminTable"></table>
    </div>

    <%--模态框 新增管理员--%>
    <div class="modal fade" id="addAdminModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addStudentModelTitle">新增管理员</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="/admin/admin.do" method="get" id="addAdminModelForm" name="addAdminModelForm">
                        <input name="method" type="hidden" value="addAdminData"><br>
                        <div class="info">${message}</div>
                        <div>
                            <input type="text" class="form-control" placeholder="请输入管理员名" name="adminId"
                                   id="addAdminModelAdminId"
                                   required="" style="width: 50%;float: left">
                            <font color="red" style="float:none;"></font>
                        </div>
                        <br/><br/>
                        <div>
                            <select class="custom-select form-control" style="width: 50%;float: left"
                                    id="addAdminModelAdminMajor" name="adminMajor">
                                <%
                                    for (Major major : majorList) {
                                        String majorName = major.getMajorName();
                                        if ("ALL".equals(majorName)) {
                                            out.print("<option value=\"请选择专业\">请选择专业</option>");
                                        } else {
                                            out.println("<option value='" + majorName + "'" + ">" + majorName + "</option>");
                                        }
                                    }
                                %>
                            </select>
                            <font color="red" style="float:none;"></font>
                        </div>
                        <br/><br/>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                            <button type="submit" class="btn btn-primary" id="confirmAddAdmin">新增</button>
                        </div>
                    </form>
                </div>
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
    $('#adminTable').bootstrapTable({
        ajax: function (request) {//使用ajax请求
            $.ajax({
                type: "GET",
                url: '/admin/admin.do',
                contentType: 'application/json;charset=utf-8',
                dataType: 'json',
                data: {method: "getAllAdmins"},
                success: function (res) {
                    request.success({
                        row: res,
                    });
                    $('#adminTable').bootstrapTable('load', res);
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
        sortName: "adminId",           //定义要排序的列
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
        uniqueId: "adminId",           //每一行的唯一标识，一般为主键列
        columns: [{
            checkbox: true
        }, {
            title: '用户名',
            field: 'adminId',
            sortable: true,
            width: 300
        }, {
            title: '专业',
            field: 'adminMajor',
            sortable: true,
            width: 300
        }, {
            title: '操作',
            field: 'publicationTime',
            formatter: operation,//对资源进行操作
        }]
    });

    function operation(value, row, index) {
        var htm = "<a onClick='return confirm(\"确定删除?\");' href='/admin/admin.do?method=deleteAdminData&adminId=" + row["adminId"] + "'>删除</a> " +
            "<a onClick='return confirm(\"确定重置?\");' href='/admin/admin.do?method=resetAdminPassword&adminId=" + row["adminId"] + "'>密码重置</a>";
        return htm;
    }

    //打开上传文件窗口
    function openUploadDialog() {
        $('#btnFileUpload').click();
    };

    //选中文件自动上传
    function submitForm() {
        $("#uploadFileForm").submit();
    };
    //验证输入的名字是否符合格式
    $(function () {
        var newAdminId = $('#addAdminModelAdminId');
        var newAdminMajor = $('#addAdminModelAdminMajor');
        var confirmAddAdmin = $('#confirmAddAdmin');

        newAdminId.next().html("*");
        newAdminMajor.next().html("*");


        newAdminMajor.on("focus", function () {
            validateTip(newAdminMajor.next(), {"color": "#666666"}, "* 请选择专业", false);
        }).on("blur", function () {
            if (newAdminMajor.val() == "请选择专业") {
                validateTip(newAdminMajor.next(), {"color": "red"}, imgNo + " 请选择专业", false);
            } else {
                validateTip(newAdminMajor.next(), {"color": "green"}, imgYes, true);
            }
        });
        newAdminId.on("focus", function () {
            validateTip(newAdminId.next(), {"color": "#666666"}, "* 请输入正确格式的学号", false);
        }).on("blur", function () {
            $.ajax({
                type: "get",
                url: "/admin/admin.do",
                data: {method: "validateAdminId", adminId: newAdminId.val()},
                dataType: "json",
                success: function (data) {
                    if (data.result == "true") {//学号不存在
                        validateTip(newAdminId.next(), {"color": "green"}, imgYes, true);
                    } else if (data.result == "false") {//学号已存在
                        validateTip(newAdminId.next(), {"color": "red"}, imgNo + " 用户名已存在", false);
                    } else if (data.result == "error") {//学号输入为空
                        validateTip(newAdminId.next(), {"color": "red"}, imgNo + " 请输入用户名", false);
                    } else if (data.result == "length") {//学号格式不对
                        validateTip(newAdminId.next(), {"color": "red"}, imgNo + " 请输入正确的格式", false);
                    }
                },
                error: function (data) {
                    //请求出错
                    validateTip(newAdminId.next(), {"color": "red"}, imgNo + " 请求错误", false);
                }
            })
        })
        confirmAddAdmin.on("click", function () {
            newAdminId.blur();
            newAdminMajor.blur();
            if (newAdminId.attr("validateStatus") == "true" &&
                newAdminMajor.attr("validateStatus") == "true") {
                if (confirm("确定要增加管理员吗？")) {
                    $("#addAdminModelForm").submit();
                }
            } else {
                return false;
            }
        })
    })
</script>

</html>