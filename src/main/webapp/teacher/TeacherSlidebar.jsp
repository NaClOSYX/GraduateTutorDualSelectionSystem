<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>上海电力大学研究生导师双选系统</title>
</head>
<body>
<c:set var="pageTitle" value="${param.pageTitle}" scope="page"/>
<div class="container-fluid">
    <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
            <div class="sidebar-sticky">
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <c:if test="${'修改密码'.equals(pageTitle)}">
                            <a class="nav-link active" href="TeacherUpdatePassword.jsp">
                                <span data-feather="file"></span>
                                修改密码
                            </a>
                        </c:if>
                        <c:if test="${!'修改密码'.equals(pageTitle)}">
                            <a class="nav-link" href="TeacherUpdatePassword.jsp">
                                <span data-feather="file"></span>
                                修改密码
                            </a>
                        </c:if>
                    </li>
                    <li class="nav-item">
                        <c:if test="${'选择学生'.equals(pageTitle)}">
                            <a class="nav-link active" href="TeacherChooseStudent.jsp">
                                <span data-feather="file"></span>
                                选择学生
                            </a>
                        </c:if>
                        <c:if test="${!'选择学生'.equals(pageTitle)}">
                            <a class="nav-link" href="TeacherChooseStudent.jsp">
                                <span data-feather="file"></span>
                                选择学生
                            </a>
                        </c:if>
                    </li>
                    <li class="nav-item">
                        <c:if test="${'查看选择的学生'.equals(pageTitle)}">
                            <a class="nav-link active" href="TeacherSeeStudents.jsp">
                                <span data-feather="file"></span>
                                查看选择的学生
                            </a>
                        </c:if>
                        <c:if test="${!'查看选择的学生'.equals(pageTitle)}">
                            <a class="nav-link" href="TeacherSeeStudents.jsp">
                                <span data-feather="file"></span>
                                查看选择的学生
                            </a>
                        </c:if>
                    </li>
                    <li class="nav-item">
                        <c:if test="${'查看最终的学生'.equals(pageTitle)}">
                            <a class="nav-link active" href="TeacherSeeFinalStudents.jsp">
                                <span data-feather="file"></span>
                                查看最终的学生
                            </a>
                        </c:if>
                        <c:if test="${!'查看最终的学生'.equals(pageTitle)}">
                            <a class="nav-link" href="TeacherSeeFinalStudents.jsp">
                                <span data-feather="file"></span>
                                查看最终的学生
                            </a>
                        </c:if>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</div>
</body>
</html>
