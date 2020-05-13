<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>上海电力大学研究生导师双选系统</title>
    <!-- Bootstrap -->
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">

    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<div style="margin: 0 auto">
    <h1><a href="login.jsp">登陆</a></h1>
    <h1>
        <%
            if (session.getAttribute("username") != null) {
                String username = session.getAttribute("username").toString();
                out.print(username);
                out.print("已登陆");
            } else {
                out.print("无用户登陆");
            }
        %>
    </h1>
    <h1><a href="admin/AdminIndex.jsp">AdminIndex.jsp</a></h1>
    <h1><a href="teacher/TeacherIndex.jsp">TeacherIndex.jsp</a></h1>
    <h1><a href="student/StudentIndex.jsp">StudentIndex.jsp</a></h1>
    <h1><a href="/logout.do">注销</a></h1>
</div>

<!-- Copyright -->
<%@include file="commons/copyright.jsp" %>
</body>
</html>
