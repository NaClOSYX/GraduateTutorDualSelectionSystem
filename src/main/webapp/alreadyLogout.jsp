<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上海电力大学研究生导师双选系统</title>
</head>
<body>
<script language="javascript" type="text/javascript">
    var timer;

    //启动跳转的定时器
    function startTimes() {
        timer = window.setInterval(showSecondes, 1000);
    }

    var i = 5;

    function showSecondes() {
        if (i > 0) {
            i--;
            document.getElementById("secondes").innerHTML = i;
        } else {
            window.clearInterval(timer);
            /*要跳转的请求*/
            location.href = "/index.jsp";
        }
    }

    //取消跳转
    function resetTimer() {
        if (timer != null && timer != undefined) {
            window.clearInterval(timer);
            /*取消跳转的请求*/
            location.href = "/index.jsp";
        }
    }
</script>


<body class="error_page" onload="startTimes();">
<h1 id="error">
    已注销，&nbsp;<span id="secondes">5</span>&nbsp;秒后将自动跳转到首页，立即跳转请点击&nbsp;
    <a href="javascript:resetTimer();">首页</a>
</h1>
</body>
</body>
</html>
