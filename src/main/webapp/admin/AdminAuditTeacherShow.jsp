<%@ page import="com.naclo.pojo.Major" %>
<%@ page import="com.naclo.service.MajorService" %>
<%@ page import="com.naclo.service.TeacherService" %>
<%@ page import="com.naclo.service.impl.MajorServiceImpl" %>
<%@ page import="com.naclo.service.impl.TeacherServiceImpl" %>
<%@ page import="com.naclo.utils.Constants" %>
<%@ page import="java.util.List" %>
<%@ page import="com.naclo.pojo.Teacher" %>
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
    <link href="../css/dashboard.css" rel="stylesheet">
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

    function operation(value, row, index) {
        var htm = "<form action='admin.do'>" +
            "<input type='hidden' name='method' value='adminSetTeacher'>" +
            "<input type='hidden' name='studentId' value=" + row["studentId"] + ">" +
            "<input type='hidden' name='ideaId' value=" + row["ideaId"] + ">" +
            "    <div class=\"container\">\n" +
            "        <div class=\"row\">\n" +
            "            <div class=\"col-6\">" +
            "<select name=teacherId class=\"form-control\">" +
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
            "            </div>\n" +
            "            <div class=\"col-6\">" +
            "<input type='submit' class=\"form-control\">" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>" +
            "</form>"
        return htm;
    }

</script>
<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/bootstrap-table/1.16.0/bootstrap-table.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/bootstrap-table/1.16.0/locale/bootstrap-table-zh-CN.js"></script>
<script src="../js/common.js"></script>
<script src="../js/admin/AdminAuditTeacherShow.js"></script>
</html>