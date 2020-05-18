<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>上海电力大学研究生导师双选系统</title>
</head>
<body>
<%
    String pageTitle = request.getParameter("pageTitle");
%>
<div class="container-fluid">
    <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
            <div class="sidebar-sticky">
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <%
                            if (pageTitle.equals("修改密码")) {
                                out.print("<a class=\"nav-link active\" href=\"StudentUpdatePassword.jsp\">");
                            } else
                                out.print("<a class=\"nav-link\" href=\"StudentUpdatePassword.jsp\">");
                        %>
                        <span data-feather="file"></span>
                        修改密码
                        </a>
                    </li>
                    <li class="nav-item">

                        <%
                            if (pageTitle.equals("选择导师")) {
                                out.print("<a class=\"nav-link active\" href=\"StudentChooseTeacher.jsp\">");
                            } else
                                out.print("<a class=\"nav-link\" href=\"StudentChooseTeacher.jsp\">");
                        %>
                        <%-- <a class="nav-link" href="AdminStudentList.jsp">--%>
                        <span data-feather="file"></span>
                        选择导师
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</div>
</body>
</html>
