<%@ page import="com.naclo.utils.Constants" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.ParseException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<%
    Object startTime = application.getAttribute("startTime");
    Object endTime = application.getAttribute("endTime");
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    Date startDate = new Date();
    Date endDate = new Date();
    System.out.println("startTime = " + startTime);
    try {
        if (null != startTime && !"".equals(startTime)) {
            startDate = format.parse(startTime.toString());
        }
        if (null != endTime && !"".equals(endTime)) {
            endDate = format.parse(endTime.toString());
        }
    } catch (ParseException e) {
        e.printStackTrace();
    }

%>
<body>
<!--topbar-->
<jsp:include page="StudentTopbar.jsp"></jsp:include>
<!--slidebar-->
<jsp:include page="StudentSlidebar.jsp">
    <jsp:param name="pageTitle" value="选择导师"/>
</jsp:include>

<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
    <div class="panel-body" style="padding-bottom:0px;">
        <%--table--%>

        <%
            Date today = new Date();
            if (!(today.after(startDate) && today.before(endDate))) {
                out.print("        <div id=\"toolbar\" class=\"btn-group\">\n" +
                        "            <button id=\"btn_choose\" type=\"button\" onclick=\"chooseTeacher()\" class=\"btn btn-primary\">选择\n" +
                        "            </button>\n" +
                        "        </div>");
                out.print("<table id=\"teacherTable\"></table>");
            } else {
                out.print("<h1 style=\"color: red\">系统未开放</h1>");
                if (null != startTime && null != endTime) {
                    out.print("<h1><p>系统开放时间</p>");
                    out.print(startTime);
                    out.print("~");
                    out.print(endTime);
                    out.print("</h1>");
                }
            }
        %>
    </div>
</main>

<!-- Copyright -->
<jsp:include page="../commons/copyright.jsp"></jsp:include>

</body>

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
<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/bootstrap-table/1.16.0/bootstrap-table.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/bootstrap-table/1.16.0/locale/bootstrap-table-zh-CN.js"></script>
<script src="../js/common.js" charset="UTF-8"></script>
<script src="../js/student/StudentChooseTeacher.js" charset="UTF-8"></script>

</html>