<%@ page import="com.naclo.service.StudentService" %>
<%@ page import="com.naclo.service.impl.StudentServiceImpl" %>
<%@ page import="com.naclo.utils.Constants" %>
<%@ page import="com.naclo.pojo.Student" %>
<%@ page import="com.naclo.service.IdeaService" %>
<%@ page import="com.naclo.service.impl.IdeaServiceImpl" %>
<%@ page import="com.naclo.pojo.Idea" %>
<%@ page import="java.util.List" %>
<%@ page import="com.naclo.service.IdeaTableService" %>
<%@ page import="com.naclo.service.impl.IdeaTableServiceImpl" %>
<%@ page import="com.naclo.pojo.IdeaTable" %>
<%@ page import="com.naclo.service.TeacherService" %>
<%@ page import="com.naclo.service.impl.TeacherServiceImpl" %>
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

    <link href="../css/dashboard.css" rel="stylesheet">
</head>

<body>
<!--topbar-->
<jsp:include page="StudentTopbar.jsp"></jsp:include>
<!--slidebar-->
<jsp:include page="StudentSlidebar.jsp">
    <jsp:param name="pageTitle" value="我的志愿"/>
</jsp:include>
<%
    StudentService studentService = new StudentServiceImpl();
    IdeaTableService ideaTableService = new IdeaTableServiceImpl();
    TeacherService teacherService = new TeacherServiceImpl();
    String studentId = (String) (session.getAttribute(Constants.USER_SESSION));
    String major = (String) (session.getAttribute(Constants.USER_MAJOR));
    Student student = studentService.queryStudentById(studentId);
    List<IdeaTable> ideaTableList = ideaTableService.queryStudentIdeasByMajor(major);
    IdeaTable ideaTable = new IdeaTable();
    for (IdeaTable ideaTable1 : ideaTableList) {
        if (ideaTable1.getStudentId().equals(studentId)) {
            ideaTable = ideaTable1;
            break;
        }
    }
    IdeaService ideaService = new IdeaServiceImpl();
    List<Idea> ideaList = ideaService.queryIdeasByStudentIdAndState(studentId, 5);
    ideaList.forEach(System.out::println);
    String teacherName = null;
    if (ideaList.size() > 0) {
        String teacherId = ideaList.get(0).getTeacherId();
        teacherName = teacherService.queryTeacherById(teacherId).getTeacherName();
    }


%>
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
    <div class="panel-body" style="padding-bottom:0px;">
        <br/><br/>
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <div class="card mb-4 shadow-sm">
                        <h1 style="text-align: center">
                            <%=ideaTable.getTeacherName1()%>
                        </h1>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card mb-4 shadow-sm">
                        <h1 style="text-align: center">
                            <%=ideaTable.getTeacherName2()%>
                        </h1>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card mb-4 shadow-sm">
                        <h1 style="text-align: center">
                            <%=ideaTable.getTeacherName3()%>
                        </h1>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <%
                if (teacherName == null) {
                } else {
                    out.print("<h1> 您的导师：");
                    out.print("<b>" + teacherName + "</b>");
                    out.print("</h1>");
                }
            %>
        </div>
    </div>
</main>

<!-- Copyright -->
<jsp:include page="../commons/copyright.jsp"></jsp:include>

</body>
<script>

</script>

</html>