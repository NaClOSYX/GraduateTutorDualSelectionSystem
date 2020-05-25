<%@ page import="com.naclo.utils.Constants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>上海电力大学研究生导师双选系统</title>
</head>
<body>
<%
    String pageTitle = request.getParameter("pageTitle");
    String adminMajor = (String) (session.getAttribute(Constants.USER_MAJOR));
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
                        <span data-feather="file"></span>
                        管理老师
                        </a>
                    </li>
                    <%
                        if ("ALL".equals(adminMajor)) {
                            out.print("<li class=\"nav-item\">");
                            if (pageTitle.equals("管理管理员")) {
                                out.print("<a class=\"nav-link active\" href=\"AdminAdminList.jsp\">");
                            } else {
                                out.print("<a class=\"nav-link\" href=\"AdminAdminList.jsp\">");
                            }
                            out.print("<span data-feather=\"file\"></span>管理管理员</a></li>");
                        }
                    %>
                    <%
                        if ("ALL".equals(adminMajor)) {
                            out.print("<li class=\"nav-item\">");
                            if (pageTitle.equals("管理专业")) {
                                out.print("<a class=\"nav-link active\" href=\"AdminMajorList.jsp\">");
                            } else {
                                out.print("<a class=\"nav-link\" href=\"AdminMajorList.jsp\">");
                            }
                            out.print("<span data-feather=\"file\"></span>管理专业</a></li>");
                        }
                    %>
                    <li class="nav-item">
                        <%
                            if (pageTitle.equals("管理志愿")) {
                                out.print("<a class=\"nav-link active\" href=\"AdminIdeaList.jsp\">");
                            } else
                                out.print("<a class=\"nav-link\" href=\"AdminIdeaList.jsp\">");
                        %>
                        <span data-feather="file"></span>
                        管理志愿
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</div>
</body>
</html>
