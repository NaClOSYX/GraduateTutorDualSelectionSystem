<%@ page import="com.naclo.pojo.Major" %>
<%@ page import="com.naclo.service.MajorService" %>
<%@ page import="com.naclo.service.impl.MajorServiceImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="com.naclo.utils.Constants" %>
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
    <script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/bootstrap-table/1.16.0/bootstrap-table.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/bootstrap-table/1.16.0/locale/bootstrap-table-zh-CN.js"></script>

    <link href="../css/dashboard.css" rel="stylesheet">

    <script src="../js/common.js"></script>
    <script src="../js/AdminTeacherList.js"></script>
</head>

<body>
<!--topbar-->
<jsp:include page="AdminTopbar.jsp"></jsp:include>
<!--slidebar-->
<jsp:include page="AdminSlidebar.jsp">
    <jsp:param name="pageTitle" value="管理老师"/>
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
        <%--toolbar--%>
        <div id="toolbar" class="btn-group">
            <button id="btn_add" type="button" class="btn btn-primary" data-toggle="modal"
                    data-target="#addTeacherModel">新增
            </button>
            <button id="btn_import" type="button" onclick="openUploadDialog()" class="btn btn-primary">导入
            </button>
            <button id="btn_export" type="button" class="btn btn-primary">
                <a href="/admin/admin.do?method=exportTeacherList" style="color: white">导出
                </a>
            </button>
            <form name="uploadFileForm" id="uploadFileForm" method="post" enctype="multipart/form-data"
                  action="/admin/admin.do?method=importTeacherList">
                <input type="file" name="uploadFile" id="btnFileUpload" hidden onchange="submitForm()">
            </form>
        </div>

        <%--table--%>
        <table id="teacherTable"></table>

        <%--模态框 新增导师--%>
        <div class="modal fade" id="addTeacherModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addTeacherModelTitle">新增导师</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="/admin/admin.do" method="get" id="addTeacherModelForm" name="addTeacherModelForm">
                            <input name="method" type="hidden" value="addTeacherData"><br>
                            <div class="info">${message}</div>
                            <div>
                                <input type="text" class="form-control" placeholder="请输入工号" name="teacherId"
                                       id="addTeacherModelTeacherId"
                                       required="" style="width: 50%;float: left">
                                <font color="red" style="float:none;"></font>
                            </div>
                            <br/><br/>
                            <div>
                                <input type="text" class="form-control" placeholder="请输入姓名" name="teacherName"
                                       id="addTeacherModelTeacherName"
                                       required="" style="width: 50%;float: left">
                                <font color="red" style="float:none;"></font>
                            </div>
                            <br/><br/>
                            <div>
                                <select class="custom-select form-control" style="width: 50%;float: left"
                                        id="addTeacherModelTeacherMajor" name="teacherMajor">
                                    <%
                                        for (Major major : majorList) {
                                            String majorName = major.getMajorName();
                                            if (!"ALL".equals(majorName))
                                                out.print("<option value='" + majorName + "'" + ">" + majorName + "</option>");
                                        }
                                    %>
                                </select>
                                <font color="red" style="float:none;"></font>
                            </div>
                            <br/><br/>
                            <div>
                                <input type="text" class="form-control" placeholder="请输入简介" name="teacherIntroduce"
                                       id="addTeacherModelTeacherIntroduce"
                                       required="" style="width: 50%;float: left">

                                <font color="red" style="float:none;"></font>
                            </div>

                            <br/><br/><br/>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                                <button type="submit" class="btn btn-primary" id="confirmAddTeacher">新增</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <%--模态框 修改导师--%>
        <div class="modal fade" id="updateTeacherModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="updateTeacherModelTitle">修改导师</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="/admin/admin.do" method="get" id="updateTeacherModelForm"
                              name="updateTeacherModelForm">
                            <input name="method" type="hidden" value="updateTeacherData"><br>
                            <div class="info">${message}</div>
                            <div>
                                <input type="text" class="form-control" placeholder="请输入工号" name="teacherId"
                                       id="updateTeacherModelTeacherId"
                                       required="" style="width: 50%;float: left">
                                <font color="red" style="float:none;"></font>
                            </div>
                            <br/><br/>
                            <div>
                                <input type="text" class="form-control" placeholder="请输入姓名" name="teacherName"
                                       id="updateTeacherModelTeacherName"
                                       required="" style="width: 50%;float: left">
                                <font color="red" style="float:none;"></font>
                            </div>
                            <br/><br/>
                            <div>
                                <select class="custom-select form-control" style="width: 50%;float: left"
                                        id="updateTeacherModelTeacherMajor" name="teacherMajor">
                                    <%
                                        for (Major major : majorList) {
                                            String majorName = major.getMajorName();
                                            if (!"ALL".equals(majorName))
                                                out.print("<option value='" + majorName + "'" + ">" + majorName + "</option>");
                                        }
                                    %>
                                </select>
                                <font color="red" style="float:none;"></font>
                            </div>
                            <br/><br/>
                            <div>
                                <input type="text" class="form-control" placeholder="请输入简介" name="teacherIntroduce"
                                       id="updateTeacherModelTeacherIntroduce"
                                       required="" style="width: 50%;float: left">
                                <font color="red" style="float:none;"></font>
                            </div>
                            <br/><br/><br/>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                                <button type="submit" class="btn btn-primary" id="confirmUpdateTeacher">修改</button>
                            </div>
                        </form>
                    </div>
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

    $('#teacherTable').bootstrapTable({
        ajax: function (request) {//使用ajax请求
            $.ajax({
                type: "GET",
                url: '/admin/admin.do',
                contentType: 'application/json;charset=utf-8',
                dataType: 'json',
                data: {method: "getAllTeachers"},
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
        pagination: true,                //是否分页
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
        height: $(window).height() - 70,   //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
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
        }, {
            title: '操作',
            field: 'publicationTime',
            formatter: operation,//对资源进行操作
        }]
    });

    function operation(value, row, index) {
        var htm = "<a onClick='return confirm(\"确定删除?\");' href='/admin/admin.do?method=deleteTeacherData&teacherId=" + row["teacherId"] + "'>删除</a> " +
            "<a style='color: dodgerblue' data-toggle='modal' data-target='#updateTeacherModel' onclick='values(" + row["teacherId"] + ")'>修改</a> " +
            "<a onClick='return confirm(\"确定重置?\");' href='/admin/admin.do?method=resetTeacherPassword&teacherId=" + row["teacherId"] + "'>密码重置</a>";
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
        console.log(id);
        //document.getElementById('updateStudentModelStudentId').value = id;
        $.ajax({
            type: "POST",
            url: "/admin/admin.do",
            data: {method: "getTeacherById", teacherId: id},
            success: function (data) {
                document.getElementById('updateTeacherModelTeacherId').value = data.teacherId;
                document.getElementById('updateTeacherModelTeacherName').value = data.teacherName
                document.getElementById('updateTeacherModelTeacherMajor').value = data.teacherMajor;
                document.getElementById('updateTeacherModelTeacherIntroduce').value = data.teacherIntroduce;
            },
            error: function (data) {
                //请求出错
            }
        })
    };
    //验证输入的新导师是否符合格式
    $(function () {
        var newTeacherId = $('#addTeacherModelTeacherId');
        var newTeacherName = $('#addTeacherModelTeacherName');
        var newTeacherMajor = $('#addTeacherModelTeacherMajor');
        var newTeacherIntroduce = $('#addTeacherModelTeacherIntroduce');
        var confirmAddTeacher = $('#confirmAddTeacher');

        newTeacherId.next().html("*");
        newTeacherName.next().html("*");
        newTeacherMajor.next().html("*");
        newTeacherIntroduce.next().html("*");

        newTeacherId.on("focus", function () {
            validateTip(newTeacherId.next(), {"color": "#666666"}, "* 请输入正确格式的工号", false);
        }).on("blur", function () {
            $.ajax({
                type: "get",
                url: "/admin/admin.do",
                data: {method: "validateTeacherId", teacherId: newTeacherId.val()},
                dataType: "json",
                success: function (data) {
                    if (data.result == "true") {//工号不存在
                        validateTip(newTeacherId.next(), {"color": "green"}, imgYes, true);
                    } else if (data.result == "false") {//工号已存在
                        validateTip(newTeacherId.next(), {"color": "red"}, imgNo + " 工号已存在", false);
                    } else if (data.result == "error") {//工号输入为空
                        validateTip(newTeacherId.next(), {"color": "red"}, imgNo + " 请输入工号", false);
                    } else if (data.result == "length") {//工号格式不对
                        validateTip(newTeacherId.next(), {"color": "red"}, imgNo + " 请输入正确格式的工号", false);
                    }
                },
                error: function (data) {
                    //请求出错
                    validateTip(newTeacherId.next(), {"color": "red"}, imgNo + " 请求错误", false);
                }
            })
        });
        newTeacherName.on("focus", function () {
            validateTip(newTeacherName.next(), {"color": "#666666"}, "* 请输入姓名", false);
        }).on("blur", function () {
            console.log("log" + newTeacherName.val() + "log");
            if (newTeacherName.val() == "") {
                validateTip(newTeacherName.next(), {"color": "red"}, imgNo + " 姓名不能为空", false);
            } else {
                validateTip(newTeacherName.next(), {"color": "green"}, imgYes, true);
            }
        });

        newTeacherMajor.on("focus", function () {
            validateTip(newTeacherMajor.next(), {"color": "#666666"}, "* 请输入专业", false);
        }).on("blur", function () {
            if (newTeacherMajor.val() == "请选择专业") {

                validateTip(newTeacherMajor.next(), {"color": "red"}, imgNo + " 请选择专业", false);
            } else {
                validateTip(newTeacherMajor.next(), {"color": "green"}, imgYes, true);
            }
        });
        newTeacherIntroduce.on("focus", function () {
            validateTip(newTeacherIntroduce.next(), {"color": "#666666"}, "* 请输入简介", false);
        }).on("blur", function () {
            if (newTeacherIntroduce.val() == "") {

                validateTip(newTeacherIntroduce.next(), {"color": "red"}, imgNo + " 简介不能为空", false);
            } else {
                validateTip(newTeacherIntroduce.next(), {"color": "green"}, imgYes, true);
            }
        });

        confirmAddTeacher.on("click", function () {
            newTeacherId.blur();
            newTeacherName.blur();
            newTeacherMajor.blur();
            if (newTeacherId.attr("validateStatus") == "true" &&
                newTeacherName.attr("validateStatus") == "true" &&
                newTeacherMajor.attr("validateStatus") == "true" &&
                newTeacherIntroduce.attr("validateStatus") == "true") {
                if (confirm("确定要增加导师吗？")) {
                    $("#addTeacherModelForm").submit();
                }
            } else {
                return false;
            }
        })
    });
</script>

</html>