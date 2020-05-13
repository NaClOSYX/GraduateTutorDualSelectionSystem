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
                            if (pageTitle.equals("管理学生")) {
                                out.print("<a class=\"nav-link active\" href=\"AdminStudentList.jsp\">");
                            } else
                                out.print("<a class=\"nav-link\" href=\"AdminStudentList.jsp\">");
                        %>
                        <%-- <a class="nav-link" href="AdminStudentList.jsp">--%>
                        <span data-feather="file"></span>
                        管理学生
                        </a>
                    </li>
                    <li class="nav-item">
                        <%
                            if (pageTitle.equals("管理老师")) {
                                out.print("<a class=\"nav-link active\" href=\"AdminTeacherList.jsp\">");
                            } else
                                out.print("<a class=\"nav-link\" href=\"AdminTeacherList.jsp\">");
                        %>
                        <%--<a class="nav-link" href="AdminTeacherList.jsp">--%>
                        <span data-feather="file"></span>
                        管理老师
                        </a>
                    </li>
                    <li class="nav-item">
                        <%
                            if (pageTitle.equals("管理管理员")) {
                                out.print("<a class=\"nav-link active\" href=\"AdminAdminList.jsp\">");
                            } else
                                out.print("<a class=\"nav-link\" href=\"AdminAdminList.jsp\">");
                        %>
                        <%--<a class="nav-link" href="AdminAdminList.jsp">--%>
                        <span data-feather="file"></span>
                        管理管理员
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="AdminAdminList.jsp">
                            <span data-feather="file"></span>
                            <%
                                if (session.getAttribute("username") != null) {
                                    String username = session.getAttribute("username").toString();
                                    out.print(username);
                                }
                            %>
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</div>
</body>
</html>
