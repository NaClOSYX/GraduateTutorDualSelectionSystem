<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
%>
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
    <script src="https://cdn.bootcdn.net/ajax/libs/bootstrap-table/1.16.0/locale/bootstrap-table-zh-CN.js"></script>

    <link href="../css/dashboard.css" rel="stylesheet">

    <script src="../js/common.js" charset="UTF-8"></script>
    <script src="../js/TeacherUpdatePassword.js" charset="UTF-8"></script>


</head>

<body>
<!--topbar-->
<jsp:include page="TeacherTopbar.jsp"></jsp:include>
<!--slidebar-->
<jsp:include page="TeacherSlidebar.jsp">
    <jsp:param name="pageTitle" value="修改密码"/>
</jsp:include>

<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
    <div class="panel-body" style="padding-bottom:0px;">
        <fieldset>
            <div class="container">
                <div class="row">
                    <div class="col-lg-9">
                        <form id="studentForm" name="studentForm" method="post" action="/teacher/teacher.do">
                            <legend>修改密码</legend>
                            <input type="hidden" name="method" value="updatePassword">
                            <div class="info">${message}</div>
                            <div>
                                <input type="password" class="form-control" placeholder="请输入旧密码" name="oldPassword"
                                       id="oldPassword"
                                       required="" style="width: 50%;float: left">
                                <font color="red" style="float:none;"></font>
                            </div>
                            <br/><br/>
                            <div>
                                <input type="password" class="form-control" placeholder="请输入新密码" name="newPassword"
                                       id="newPassword"
                                       required="" style="width: 50%;float: left">
                                <font color="red" style="float:none;"></font>
                            </div>
                            <br/><br/>
                            <div>
                                <input type="password" class="form-control" placeholder="请重新输入新密码"
                                       name="repeatNewPassword"
                                       id="repeatNewPassword"
                                       required="" style="width: 50%;float: left">
                                <font color="red" style="float:none;"></font>
                            </div>
                            <br/><br/>
                            <button class="btn btn-lg btn-primary btn-block" id="saveBtn" type="submit"
                                    style="width: 50%">保存
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </fieldset>
    </div>
</main>

<!-- Copyright -->
<jsp:include page="../commons/copyright.jsp"></jsp:include>

</body>

<script>

</script>

</html>