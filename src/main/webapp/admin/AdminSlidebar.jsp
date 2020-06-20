<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>上海电力大学研究生导师双选系统</title>
</head>
<body>
<c:set var="pageTitle" value="${param.pageTitle}" scope="page"/>
<c:set var="adminMajor" value="${sessionScope.userMajor}" scope="page"/>
<div class="container-fluid">
    <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
            <div class="sidebar-sticky">
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <c:if test="${'管理学生'.equals(pageTitle)}">
                            <a class="nav-link active" href="AdminStudentList.jsp">
                                <span data-feather="file"></span>
                                管理学生
                            </a>
                        </c:if>
                        <c:if test="${!'管理学生'.equals(pageTitle)}">
                            <a class="nav-link" href="AdminStudentList.jsp">
                                <span data-feather="file"></span>
                                管理学生
                            </a>
                        </c:if>
                    </li>
                    <li class="nav-item">
                        <c:if test="${'管理老师'.equals(pageTitle)}">
                            <a class="nav-link active" href="AdminTeacherList.jsp">
                                <span data-feather="file"></span>
                                管理老师
                            </a>
                        </c:if>
                        <c:if test="${!'管理老师'.equals(pageTitle)}">
                            <a class="nav-link" href="AdminTeacherList.jsp">
                                <span data-feather="file"></span>
                                管理老师
                            </a>
                        </c:if>
                    </li>
                    <c:if test="${'ALL'.equals(adminMajor)}">
                        <li class="nav-item">
                            <c:if test="${'管理管理员'.equals(pageTitle)}">
                                <a class="nav-link active" href="AdminAdminList.jsp">
                                    <span data-feather="file"></span>
                                    管理管理员
                                </a>
                            </c:if>
                            <c:if test="${!'管理管理员'.equals(pageTitle)}">
                                <a class="nav-link" href="AdminAdminList.jsp">
                                    <span data-feather="file"></span>
                                    管理管理员
                                </a>
                            </c:if>
                        </li>
                    </c:if>
                    <c:if test="${'ALL'.equals(adminMajor)}">
                        <li class="nav-item">
                            <c:if test="${'管理专业'.equals(pageTitle)}">
                                <a class="nav-link active" href="AdminMajorList.jsp">
                                    <span data-feather="file"></span>
                                    管理专业
                                </a>
                            </c:if>
                            <c:if test="${!'管理专业'.equals(pageTitle)}">
                                <a class="nav-link" href="AdminMajorList.jsp">
                                    <span data-feather="file"></span>
                                    管理专业
                                </a>
                            </c:if>
                        </li>
                    </c:if>
                    <li class="nav-item">
                        <c:if test="${'管理志愿'.equals(pageTitle)}">
                            <a class="nav-link active" href="AdminIdeaList.jsp">
                                <span data-feather="file"></span>
                                管理志愿
                            </a>
                        </c:if>
                        <c:if test="${!'管理志愿'.equals(pageTitle)}">
                            <a class="nav-link" href="AdminIdeaList.jsp">
                                <span data-feather="file"></span>
                                管理志愿
                            </a>
                        </c:if>
                    </li>
                    <%--<c:if test="${!'ALL'.equals(adminMajor)}">--%>
                    <li class="nav-item">
                        <c:if test="${'审核结果'.equals(pageTitle)}">
                            <a class="nav-link active" href="AdminAuditTeacherShow.jsp">
                                <span data-feather="file"></span>
                                审核结果
                            </a>
                        </c:if>
                        <c:if test="${!'审核结果'.equals(pageTitle)}">
                            <a class="nav-link" href="AdminAuditTeacherShow.jsp">
                                <span data-feather="file"></span>
                                审核结果
                            </a>
                        </c:if>
                    </li>
                    <%--</c:if>--%>
                    <li class="nav-item">
                        <c:if test="${'管理结果'.equals(pageTitle)}">
                            <a class="nav-link active" href="AdminStudentTeacherList.jsp">
                                <span data-feather="file"></span>
                                管理结果
                            </a>
                        </c:if>
                        <c:if test="${!'管理结果'.equals(pageTitle)}">
                            <a class="nav-link" href="AdminStudentTeacherList.jsp">
                                <span data-feather="file"></span>
                                管理结果
                            </a>
                        </c:if>
                    </li>
                    <c:if test="${'ALL'.equals(adminMajor)}">
                        <li class="nav-item">
                            <c:if test="${'自主选择时间设定'.equals(pageTitle)}">
                                <a class="nav-link active" href="AdminOpenSystem.jsp">
                                    <span data-feather="file"></span>
                                    自主选择时间设定
                                </a>
                            </c:if>
                            <c:if test="${!'自主选择时间设定'.equals(pageTitle)}">
                                <a class="nav-link" href="AdminOpenSystem.jsp">
                                    <span data-feather="file"></span>
                                    自主选择时间设定
                                </a>
                            </c:if>
                        </li>
                    </c:if>
                    <c:if test="${'ALL'.equals(adminMajor)}">
                        <li class="nav-item">
                            <c:if test="${'查看系统日志'.equals(pageTitle)}">
                                <a class="nav-link active" href="AdminSeeSystemLogs.jsp">
                                    <span data-feather="file"></span>
                                    查看系统日志
                                </a>
                            </c:if>
                            <c:if test="${!'查看系统日志'.equals(pageTitle)}">
                                <a class="nav-link" href="AdminSeeSystemLogs.jsp">
                                    <span data-feather="file"></span>
                                    查看系统日志
                                </a>
                            </c:if>
                        </li>
                    </c:if>
                </ul>
            </div>
        </nav>
    </div>
</div>
</body>
</html>
