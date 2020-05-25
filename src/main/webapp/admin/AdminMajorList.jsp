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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/bootstrap-table/1.16.0/bootstrap-table.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/bootstrap-table/1.16.0/locale/bootstrap-table-zh-CN.js"></script>

    <link href="../css/dashboard.css" rel="stylesheet">

    <script src="../js/common.js"></script>
</head>

<body>
<!--topbar-->
<jsp:include page="AdminTopbar.jsp"></jsp:include>
<!--slidebar-->
<jsp:include page="AdminSlidebar.jsp">
    <jsp:param name="pageTitle" value="管理专业"/>
</jsp:include>

<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
    <div class="panel-body" style="padding-bottom:0px;">
        <%--toolbar--%>
        <div id="toolbar" class="btn-group">
            <button id="btn_add" type="button" class="btn btn-primary" data-toggle="modal"
                    data-target="#addMajorModel">新增
            </button>
        </div>

        <%--table--%>
        <table id="majorTable"></table>

        <%--模态框 新增专业--%>
        <div class="modal fade" id="addMajorModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addMajorModelTitle">新增专业</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="/admin/admin.do" method="get" id="addMajorModelForm" name="addMajorModelForm">
                            <input name="method" type="hidden" value="addMajorData"><br>
                            <div class="info">${message}</div>
                            <div>
                                <input type="text" class="form-control" placeholder="请输入专业名字" name="majorName"
                                       id="addMajorModelMajorName"
                                       required="" style="width: 50%;float: left">
                                <font color="red" style="float:none;"></font>
                            </div>
                            <br/><br/>
                            <div>
                                <input type="text" class="form-control" placeholder="请输入专业最大招生人数" name="studentMax"
                                       id="addMajorStudentMax"
                                       required="" style="width: 50%;float: left">
                                <font color="red" style="float:none;"></font>
                            </div>
                            <br/><br/>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                                <button type="submit" class="btn btn-primary" id="confirmAddMajor">新增</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <%--模态框 修改专业--%>
        <div class="modal fade" id="updateMajorModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="updateMajorModelTitle">修改专业</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="/admin/admin.do" method="get" id="updateMajorModelForm"
                              name="uodateMajorModelForm">
                            <input name="method" type="hidden" value="updateMajorData"><br>
                            <input name="majorId" type="hidden" id="updateMajorModelMajorId"><br>
                            <div class="info">${message}</div>
                            <div>
                                <input type="text" class="form-control" placeholder="请输入专业名字" name="majorName"
                                       id="updateMajorModelMajorName"
                                       required="" style="width: 50%;float: left">
                                <font color="red" style="float:none;"></font>
                            </div>
                            <br/><br/>
                            <div>
                                <input type="text" class="form-control" placeholder="请输入专业最大招生人数" name="studentMax"
                                       id="updateMajorStudentMax"
                                       required="" style="width: 50%;float: left">
                                <font color="red" style="float:none;"></font>
                            </div>
                            <br/><br/>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                                <button type="submit" class="btn btn-primary" id="confirmUpdateMajor">修改</button>
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
    $('#majorTable').bootstrapTable({
        ajax: function (request) {//使用ajax请求
            $.ajax({
                type: "GET",
                url: '/admin/admin.do',
                contentType: 'application/json;charset=utf-8',
                dataType: 'json',
                data: {method: "getAllMajorsExceptALL"},
                success: function (res) {
                    request.success({
                        row: res,
                    });
                    $('#majorTable').bootstrapTable('load', res);
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
        sortName: "majorId",           //定义要排序的列
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
        uniqueId: "majorId",           //每一行的唯一标识，一般为主键列
        columns: [{
            title: '专业编号',
            field: 'majorId',
            sortable: true,
            width: 150
        }, {
            title: '专业名',
            field: 'majorName',
            sortable: true,
            width: 150
        }, {
            title: '最大人数',
            field: 'studentMax',
            sortable: true,
            width: 150
        }, {
            title: '操作',
            field: 'publicationTime',
            formatter: operation,//对资源进行操作
        }]
    });

    function operation(value, row, index) {
        var htm = "<a onClick='return confirm(\"确定删除?\");' href='/admin/admin.do?method=deleteMajorData&majorId=" + row["majorId"] + "'>删除</a> " +
            "<a style='color: dodgerblue' data-toggle='modal' data-target='#updateMajorModel' onclick='values(" + row["majorId"] + ")'>修改</a> ";
        return htm;
    }

    //验证输入的专业是否符合格式
    $(function () {
        var newMajorName = $('#addMajorModelMajorName');
        var newStudentMax = $('#addMajorStudentMax');
        var confirmAddMajor = $('#confirmAddMajor');
        newMajorName.next().html("*");
        newStudentMax.next().html("*");

        newStudentMax.on("focus", function () {
            validateTip(newStudentMax.next(), {"color": "#666666"}, "* 请输入专业最大招生人数", false);
        }).on("blur", function () {
            if (newStudentMax.val() == "") {
                validateTip(newStudentMax.next(), {"color": "red"}, imgNo + " 专业最大招生人数不能为空", false);
            } else {
                validateTip(newStudentMax.next(), {"color": "green"}, imgYes, true);
            }
        });
        newMajorName.on("focus", function () {
            validateTip(newMajorName.next(), {"color": "#666666"}, "* 请输入专业名", false);
        }).on("blur", function () {
            $.ajax({
                type: "get",
                url: "/admin/admin.do",
                data: {method: "validateMajorName", majorName: newMajorName.val()},
                dataType: "json",
                success: function (data) {
                    if (data.result == "true") {//专业不存在
                        validateTip(newMajorName.next(), {"color": "green"}, imgYes, true);
                    } else if (data.result == "false") {//专业已存在
                        validateTip(newMajorName.next(), {"color": "red"}, imgNo + " 专业已存在", false);
                    } else if (data.result == "error") {//专业输入为空
                        validateTip(newMajorName.next(), {"color": "red"}, imgNo + " 请输入专业", false);
                    }
                },
                error: function (data) {
                    //请求出错
                    validateTip(newMajorName.next(), {"color": "red"}, imgNo + " 请求错误", false);
                }
            })
        })

        confirmAddMajor.on("click", function () {
            newMajorName.blur();
            newStudentMax.blur();
            if (newStudentId.attr("validateStatus") == "true" &&
                newStudentMax.attr("validateStatus") == "true") {
                if (confirm("确定要增加专业吗？")) {
                    $("#addMajorModel").submit();
                }
            } else {
                return false;
            }
        })
    });

    function values(id) {
        //document.getElementById('updateStudentModelStudentId').value = id;
        $.ajax({
            type: "POST",
            url: "/admin/admin.do",
            data: {method: "getMajorById", majorId: id},
            success: function (data) {
                document.getElementById('updateMajorModelMajorId').value = data.majorId;
                document.getElementById('updateMajorModelMajorName').value = data.majorName;
                document.getElementById('updateMajorStudentMax').value = data.studentMax;
            },
            error: function (data) {
                //请求出错
            }
        })
    };

</script>

</html>