<%@ page import="com.naclo.utils.Constants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>上海电力大学研究生导师双选系统</title>
    <!-- Bootstrap -->
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.bootcdn.net/ajax/libs/font-awesome/5.13.0/css/all.css" rel="stylesheet">
    <link href="https://cdn.bootcdn.net/ajax/libs/bootstrap-daterangepicker/3.0.5/daterangepicker.css" rel="stylesheet">
    <link href="../css/dashboard.css" rel="stylesheet">
</head>

<body>
<!--topbar-->
<jsp:include page="AdminTopbar.jsp"></jsp:include>
<!--slidebar-->
<jsp:include page="AdminSlidebar.jsp">
    <jsp:param name="pageTitle" value="自主选择时间设定"/>
</jsp:include>
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
    <!-- 日期时间范围选择代码 -->
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <h3><p>请设定自选时间段</p></h3>
                <hr>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-6">
                <div class="box">
                    <input type="text" id="datePicker" name="datePicker" class="form-control" id="datePicker"
                           value="${applicationScope.startTime} - ${applicationScope.endTime}">
                </div>
            </div>
        </div>
        <br/>
        <div class="row">
            <div class="col-lg-6">
                <div class="box">
                    <button id="submit" value="提交" class="btn btn-primary">提交</button>
                    <button id="closeSystem" value="关闭系统" class="btn btn-primary">关闭系统</button>
                </div>
            </div>
        </div>
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
<script src="https://cdn.bootcdn.net/ajax/libs/moment.js/2.26.0/moment.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/bootstrap-daterangepicker/3.0.5/daterangepicker.js"></script>
<script src="../js/common.js"></script>
<script src="../js/DateFormat.js"></script>
<script src="../js/admin/AdminOpenSystem.js"></script>
</html>