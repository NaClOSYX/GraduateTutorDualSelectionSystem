<%@ page import="com.naclo.pojo.Major" %>
<%@ page import="com.naclo.service.MajorService" %>
<%@ page import="com.naclo.service.impl.MajorServiceImpl" %>
<%@ page import="com.naclo.utils.Constants" %>
<%@ page import="java.util.List" %>
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/bootstrap-table/1.16.0/bootstrap-table.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/bootstrap-table/1.16.0/locale/bootstrap-table-zh-CN.js"></script>

    <link href="../css/dashboard.css" rel="stylesheet">

    <script src="../js/common.js"></script>
    <script src="../js/AdminStudentList.js"></script>
</head>

<body>
<!--topbar-->
<jsp:include page="AdminTopbar.jsp"></jsp:include>
<!--slidebar-->
<jsp:include page="AdminSlidebar.jsp">
    <jsp:param name="pageTitle" value="管理学生"/>
</jsp:include>
<%
    MajorService majorService = new MajorServiceImpl();
    List<Major> majorList = majorService.queryAllMajors();
    String adminMajor = (String) (session.getAttribute(Constants.USER_MAJOR));
    if (!"ALL".equals(adminMajor)) {
        majorList = majorService.queryMajorByName(adminMajor);
    }
%>
<%--main--%>
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
    <div class="panel-body" style="padding-bottom:0px;">

        <%--toolbar--%>
        <div id="toolbar" class="btn-group">
            <button id="btn_add" type="button" class="btn btn-primary" data-toggle="modal"
                    data-target="#addStudentModel">新增
            </button>
            <button id="btn_import" type="button" onclick="F_Open_dialog()" class="btn btn-primary">导入
            </button>
            <button id="btn_export" type="button" class="btn btn-primary">
                <a href="/admin/admin.do?method=exportStudentList" style="color: white">导出
                </a>
            </button>
            <form name="uploadFileForm" id="uploadFileForm" method="post" enctype="multipart/form-data"
                  action="/admin/admin.do?method=importStudentList">
                <input type="file" name="uploadFile" id="btnFileUpload" hidden onchange="submitForm()">
            </form>
        </div>

        <%--table--%>
        <table id="studentTable"></table>

        <%--模态框 新增学生--%>
        <div class="modal fade" id="addStudentModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addStudentModelTitle">新增学生</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="/admin/admin.do" method="get" id="addStudentModelForm" name="addStudentModelForm">
                            <input name="method" type="hidden" value="addStudentData"><br>
                            <div class="info">${message}</div>
                            <div>
                                <input type="text" class="form-control" placeholder="请输入学号" name="studentId"
                                       id="addStudentModelStudentId"
                                       required="" style="width: 50%;float: left">
                                <font color="red" style="float:none;"></font>
                            </div>
                            <br/><br/>
                            <div>
                                <input type="text" class="form-control" placeholder="请输入姓名" name="studentName"
                                       id="addStudentModelStudentName"
                                       required="" style="width: 50%;float: left">
                                <font color="red" style="float:none;"></font>
                            </div>
                            <br/><br/>
                            <div>
                                <select class="custom-select form-control" style="width: 50%;float: left"
                                        id="addStudentModelStudentMajor" name="studentMajor">
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
                                <button type="submit" class="btn btn-primary" id="confirmAddStudent">新增</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <%--模态框 修改学生--%>
        <div class="modal fade" id="updateStudentModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="updateStudentModelTitle">修改学生</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="/admin/admin.do" method="get">
                            <input name="method" type="hidden" value="updateStudentData"><br>
                            <div>
                                <label for="updateStudentModelStudentId" style="float: left;"><h4>学号：</h4></label>
                                <input type="text" class="form-control" placeholder="请输入学号" name="studentId"
                                       id="updateStudentModelStudentId"
                                       required="" style="width: 50%">
                                <font color="red" style="float:none;"></font>
                            </div>
                            <br/>
                            <div>
                                <label for="updateStudentModelStudentName" style="float: left"><h4>姓名：</h4></label>
                                <input type="text" class="form-control" placeholder="请输入姓名" name="studentName"
                                       id="updateStudentModelStudentName"
                                       required="" style="width: 50%">
                                <font color="red" style="float:none;"></font>
                            </div>
                            <br/>
                            <div>
                                <label for="updateStudentModelStudentMajor" style="float: left"><h4>专业：</h4></label>
                                <select class="custom-select form-control" style="width: 50%;float: left"
                                        id="updateStudentModelStudentMajor" name="studentMajor">
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
                            <br/>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                                <button type="submit" class="btn btn-primary" id="confirmUpdateStudent">修改</button>
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

    $('#studentTable').bootstrapTable({
        ajax: function (request) {//使用ajax请求
            $.ajax({
                type: "GET",
                url: '/admin/admin.do',
                contentType: 'application/json;charset=utf-8',
                dataType: 'json',
                data: {method: "getAllStudents"},
                success: function (res) {
                    request.success({
                        row: res,
                    });
                    $('#studentTable').bootstrapTable('load', res);
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
            width: 200
        }, {
            title: '姓名',
            field: 'studentName',
            sortable: true,
            width: 200
        }, {
            title: '专业',
            field: 'studentMajor',
            sortable: true,
            width: 300
        }, {
            title: '操作',
            field: 'publicationTime',
            formatter: operation,//对资源进行操作
        }]
    });

    function operation(value, row, index) {
        var htm = "<a onClick='return confirm(\"确定删除?\");' href='/admin/admin.do?method=deleteStudentData&studentId=" + row["studentId"] + "'>删除</a> " +
            "<a style='color: dodgerblue' data-toggle='modal' data-target='#updateStudentModel' onclick='values(" + row["studentId"] + ")'>修改</a> " +
            "<a onClick='return confirm(\"确定重置?\");' href='/admin/admin.do?method=resetStudentPassword&studentId=" + row["studentId"] + "'>密码重置</a>";
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

    function values(id) {
        //document.getElementById('updateStudentModelStudentId').value = id;
        $.ajax({
            type: "POST",
            url: "/admin/admin.do",
            data: {method: "getStudentById", studentId: id},
            success: function (data) {
                document.getElementById('updateStudentModelStudentId').value = data.studentId;
                document.getElementById('updateStudentModelStudentName').value = data.studentName;
                document.getElementById('updateStudentModelStudentMajor').value = data.studentMajor;
            },
            error: function (data) {
                //请求出错
            }
        })
    };

    //验证输入的新学生是否符合格式
    $(function () {
        var newStudentId = $('#addStudentModelStudentId');
        var newStudentName = $('#addStudentModelStudentName');
        var newStudentMajor = $('#addStudentModelStudentMajor');
        var confirmAddStudent = $('#confirmAddStudent');

        newStudentId.next().html("*");
        newStudentName.next().html("*");
        newStudentMajor.next().html("*");

        newStudentName.on("focus", function () {
            validateTip(newStudentName.next(), {"color": "#666666"}, "* 请输入姓名", false);
        }).on("blur", function () {
            if (newStudentName.val() == "") {
                validateTip(newStudentName.next(), {"color": "red"}, imgNo + " 姓名不能为空", false);
            } else {
                validateTip(newStudentName.next(), {"color": "green"}, imgYes, true);
            }
        });
        newStudentMajor.on("focus", function () {
            validateTip(newStudentMajor.next(), {"color": "#666666"}, "* 请选择专业", false);
        }).on("blur", function () {
            if (newStudentMajor.val() == "请选择专业") {
                validateTip(newStudentMajor.next(), {"color": "red"}, imgNo + " 请选择专业", false);
            } else {
                validateTip(newStudentMajor.next(), {"color": "green"}, imgYes, true);
            }
        });
        //ajax验证输入的学号是否存在
        newStudentId.on("focus", function () {
            validateTip(newStudentId.next(), {"color": "#666666"}, "* 请输入正确格式的学号", false);
        }).on("blur", function () {
            $.ajax({
                type: "get",
                url: "/admin/admin.do",
                data: {method: "validateStudentId", studentId: newStudentId.val()},
                dataType: "json",
                success: function (data) {
                    if (data.result == "true") {//学号不存在
                        validateTip(newStudentId.next(), {"color": "green"}, imgYes, true);
                    } else if (data.result == "false") {//学号已存在
                        validateTip(newStudentId.next(), {"color": "red"}, imgNo + " 学号已存在", false);
                    } else if (data.result == "error") {//学号输入为空
                        validateTip(newStudentId.next(), {"color": "red"}, imgNo + " 请输入学号", false);
                    } else if (data.result == "length") {//学号格式不对
                        validateTip(newStudentId.next(), {"color": "red"}, imgNo + " 请输入正确的格式", false);
                    }
                },
                error: function (data) {
                    //请求出错
                    validateTip(newStudentId.next(), {"color": "red"}, imgNo + " 请求错误", false);
                }
            })
        })
        confirmAddStudent.on("click", function () {
            newStudentId.blur();
            newStudentName.blur();
            newStudentMajor.blur();
            if (newStudentId.attr("validateStatus") == "true" &&
                newStudentName.attr("validateStatus") == "true" &&
                newStudentMajor.attr("validateStatus") == "true") {
                if (confirm("确定要增加学生吗？")) {
                    $("#addStudentModel").submit();
                }
            } else {
                return false;
            }
        })
    })

</script>

</html>