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
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.naclo.pojo.Teacher" %>
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
    System.out.println("ideaTable = " + ideaTable);
    IdeaService ideaService = new IdeaServiceImpl();
    List<Idea> ideaList = new ArrayList<>();
    ideaList.addAll(ideaService.queryIdeasByStudentIdAndState(studentId, 5));
    ideaList.addAll(ideaService.queryIdeasByStudentIdAndState(studentId, 7));
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
                        <h1 style="text-align: center" class="idea">
                            <%=ideaTable.getTeacherName1()%>
                        </h1>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card mb-4 shadow-sm">
                        <h1 style="text-align: center" class="idea">
                            <%=ideaTable.getTeacherName2()%>
                        </h1>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card mb-4 shadow-sm">
                        <h1 style="text-align: center" id="idea3" class="idea">
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


    <%--模态框 新增管理员--%>
    <div class="modal fade" id="changeIdea" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addStudentModelTitle">修改志愿</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="/student/student.do" method="get" id="changeIdeaForm" name="changeIdeaForm">
                        <input name="method" type="hidden" value="changeIdea"><br>
                        <div>
                            <label for="oldTeacher" style="float: left;font-size: large">旧志愿&nbsp;&nbsp;</label>
                            <input type="text" class="form-control" name="oldTeacher" readonly
                                   id="oldTeacher"
                                   required="" style="width: 50%;float: left">
                        </div>
                        <br/><br/>
                        <div>
                            <label for="newTeacher" style="float: left;font-size: large">新志愿&nbsp;&nbsp;</label>
                            <select id="newTeacher" class="form-control" name="newTeacher" style="width: 50%">
                                <%
                                    List<Teacher> teacherList = teacherService.queryTeacherByMajor(major);
                                    for (Teacher teacher : teacherList) {
                                        String teacherName1 = teacher.getTeacherName();
                                        if (!teacherName1.equals(ideaTable.getTeacherName1()) && !teacherName1.equals(ideaTable.getTeacherName2()) && !teacherName1.equals(ideaTable.getTeacherName3())) {
                                            out.print("<option value='" + teacherName1 + "' >");
                                            out.print(teacherName1);
                                            out.print("</option>");
                                        }
                                    }
                                %>
                            </select>
                        </div>
                        <br/><br/>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                            <button type="submit" class="btn btn-primary" id="confirmChangeIdea">修改</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</main>

<!-- Copyright -->
<jsp:include page="../commons/copyright.jsp"></jsp:include>

</body>
<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/bootstrap-table/1.16.0/bootstrap-table.min.js"></script>
<script src="../js/student/StudentManageIdeas.js"></script>
<script>
    <% //操作成功弹窗
    if(session.getAttribute(Constants.STATE_MESSAGE)==null||"".equals(session.getAttribute(Constants.STATE_MESSAGE))){
    }else{
        String stateMessage = session.getAttribute(Constants.STATE_MESSAGE).toString();
        out.print("alert('"+stateMessage+"');");
    }
    session.setAttribute(Constants.STATE_MESSAGE, "");
%>

</script>
</html>