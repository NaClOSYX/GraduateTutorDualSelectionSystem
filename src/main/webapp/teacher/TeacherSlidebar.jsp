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
                                out.print("<a class=\"nav-link active\" href=\"TeacherUpdatePassword.jsp\">");
                            } else
                                out.print("<a class=\"nav-link\" href=\"TeacherUpdatePassword.jsp\">");
                        %>
                        <span data-feather="file"></span>
                        修改密码
                        </a>
                    </li>
                    <li class="nav-item">
                        <%
                            if (pageTitle.equals("选择学生")) {
                                out.print("<a class=\"nav-link active\" href=\"TeacherChooseStudent.jsp\">");
                            } else
                                out.print("<a class=\"nav-link\" href=\"TeacherChooseStudent.jsp\">");
                        %>
                        <span data-feather="file"></span>
                        选择学生
                        </a>
                    </li>
                    <li class="nav-item">
                        <%
                            if (pageTitle.equals("查看选择的学生")) {
                                out.print("<a class=\"nav-link active\" href=\"TeacherSeeStudents.jsp\">");
                            } else
                                out.print("<a class=\"nav-link\" href=\"TeacherSeeStudents.jsp\">");
                        %>
                        <span data-feather="file"></span>
                        查看选择的学生
                        </a>
                    </li>
                    <li class="nav-item">
                        <%
                            if (pageTitle.equals("查看最终的学生")) {
                                out.print("<a class=\"nav-link active\" href=\"TeacherSeeFinalStudents.jsp\">");
                            } else
                                out.print("<a class=\"nav-link\" href=\"TeacherSeeFinalStudents.jsp\">");
                        %>
                        <span data-feather="file"></span>
                        查看最终的学生
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</div>
</body>
</html>
