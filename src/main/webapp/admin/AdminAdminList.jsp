<%@ page import="com.naclo.pojo.Major" %>
<%@ page import="com.naclo.service.MajorService" %>
<%@ page import="com.naclo.service.impl.MajorServiceImpl" %>
<%@ page import="com.naclo.utils.Constants" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link href="../css/dashboard.css" rel="stylesheet">
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
</script>
<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/bootstrap-table/1.16.0/bootstrap-table.min.js"></script>
<script src="../js/common.js"></script>
<script src="../js/admin/AdminAdminList.js"></script>
</html>