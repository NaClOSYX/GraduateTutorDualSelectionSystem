<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>上海电力大学研究生导师双选系统</title>
    <!-- Bootstrap -->
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">

    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.4.1/js/bootstrap.min.js"></script>

    <link href="css/LoginStyle.css" rel="stylesheet">
    <link href="css/dashboard.css" rel="stylesheet">
    <script src="js/gVerify.js"></script>
</head>

<body>
<!--Login  -->
<div class="text-center">
    <form class="form-signin" method="post" action="login.do">
        <br/> <br/>
        <a href="${pageContext.request.contextPath}/index.jsp">
            <img class="mb-4" src="img/logo.jpg" alt="" width="100" height="100">
        </a>
        <h1 class="h3 mb-3 font-weight-normal">请登陆</h1>
        <input type="text" class="form-control" placeholder="请输入学号/工号" name="userId" required="" autofocus="">
        <br/>
        <input type="password" class="form-control" placeholder="请输入密码" name="password" required="">
        <div style="color: red">${error}</div>
        <br/>
        <input type="text" class="form-control" placeholder="请输入验证码" name="code_input" autocomplete="off"
               id="code_input">
        <br/>
        <p style="color: red;display: none" id="verifyCodeWrong">验证码错误</p>
        <div id="v_container" style="width:300px;height: 50px;" title="刷新验证码"></div>
        <br/>
        <!--<input type="checkbox" name="remember-me" value="remember-me"> 记住我<br/>-->
        <button class="btn btn-lg btn-primary btn-block" id="login" type="submit">登陆</button>
    </form>
</div>
<!-- Copyright -->
<%@include file="commons/copyright.jsp" %>
</body>
<script>
    var verifyCodeWrong = document.getElementById("verifyCodeWrong");
    var verifyCode = new GVerify("v_container");
    document.getElementById("login").onclick = function () {
        var res = verifyCode.validate(document.getElementById("code_input").value);
        if (!res) {
            //alert("验证码错误");
            //verifyCodeWrong.style.display = "block";
            //return false;
            return true;
        }
    }

</script>

