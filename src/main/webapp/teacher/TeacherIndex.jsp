<%@ page import="com.naclo.pojo.Teacher" %>
<%@ page import="com.naclo.service.impl.TeacherServiceImpl" %>
<%@ page import="com.naclo.utils.Constants" %>
<%@ page import="com.naclo.utils.MD5Utils" %>
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
</head>

<body>
<!--topbar-->
<jsp:include page="TeacherTopbar.jsp"></jsp:include>
<!--slidebar-->
<jsp:include page="TeacherSlidebar.jsp">
    <jsp:param name="pageTitle" value="主页"/>
</jsp:include>
<%
    String teacherId = session.getAttribute(Constants.USER_SESSION).toString();
    TeacherServiceImpl teacherService = new TeacherServiceImpl();
    Teacher teacher = teacherService.queryTeacherById(teacherId);
%>
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
    <div class="panel-body" style="padding-bottom:0px;">
        <h1>欢迎导师
            <b>
                <%=teacher.getTeacherName()%>
            </b>
            登陆
            <%
                if (teacher.getTeacherPassword().equals(MD5Utils.stringToMD5(teacher.getTeacherId()))) {
                    out.print(",请<a href=\"TeacherUpdatePassword.jsp\">修改密码</a>。");
                } else {
                    out.print("。");
                }
            %>
        </h1>
    </div>
</main>

<!-- Copyright -->
<jsp:include page="../commons/copyright.jsp"></jsp:include>

</body>
<script>

</script>

</html>