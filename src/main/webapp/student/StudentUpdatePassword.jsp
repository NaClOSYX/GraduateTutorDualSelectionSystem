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

    <script src="../js/common.js" charset="UTF-8"></script>
    <script src="../js/StudentUpdatePassword.js"></script>
</head>

<body>
<!--topbar-->
<jsp:include page="StudentTopbar.jsp"></jsp:include>
<!--slidebar-->
<jsp:include page="StudentSlidebar.jsp">
    <jsp:param name="pageTitle" value="修改密码"/>
</jsp:include>

<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
    <div class="panel-body" style="padding-bottom:0px;">
        <fieldset>
            <div class="container">
                <div class="row">
                    <div class="col-lg-9">
                        <form id="studentForm" name="studentForm" method="post" action="/student/student.do">
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
    var oldPassword = null;
    var newPassword = null;
    var repeatNewPassword = null;
    var saveBtn = null;

    $(function () {
        oldPassword = $("#oldPassword");
        newPassword = $("#newPassword");
        repeatNewPassword = $("#repeatNewPassword");
        saveBtn = $("#saveBtn");

        oldPassword.next().html("*");
        newPassword.next().html("*");
        repeatNewPassword.next().html("*");
        //验证旧密码是否正确
        oldPassword.on("focus", function () {
            validateTip(oldPassword.next(), {"color": "#666666"}, "* 请输入原密码", false);
        }).on("blur", function () {
            $.ajax({
                type: "POST",
                url: "/student/student.do",
                data: {method: "validateOldPassword", oldPassword: oldPassword.val()},
                dataType: "json",
                success: function (data) {
                    if (data.result == "true") {//旧密码正确
                        validateTip(oldPassword.next(), {"color": "green"}, imgYes, true);
                    } else if (data.result == "false") {//旧密码输入不正确
                        validateTip(oldPassword.next(), {"color": "red"}, imgNo + " 原密码输入不正确", false);
                    } else if (data.result == "error") {//旧密码输入为空
                        validateTip(oldPassword.next(), {"color": "red"}, imgNo + " 请输入旧密码", false);
                    }
                },
                error: function (data) {
                    //请求出错
                    validateTip(oldPassword.next(), {"color": "red"}, imgNo + " 请求错误", false);
                }
            })
        });
        //验证新密码是否符合格式
        newPassword.on("focus", function () {
            validateTip(newPassword.next(), {"color": "#666666"}, "* 密码长度必须是大于6小于20", false);
        }).on("blur", function () {
            if (newPassword.val() != null && newPassword.val().length > 6 && newPassword.val().length < 20) {
                validateTip(newPassword.next(), {"color": "green"}, imgYes, true);
            } else {
                validateTip(newPassword.next(), {"color": "red"}, imgNo + " 密码输入不符合规范，请重新输入", false);
            }
        });
        //验证新密码是否符合格式
        repeatNewPassword.on("focus", function () {
            validateTip(repeatNewPassword.next(), {"color": "#666666"}, "* 请输入与上面一致的密码", false);
        }).on("blur", function () {
            if (repeatNewPassword.val() != null && repeatNewPassword.val().length > 6
                && repeatNewPassword.val().length < 20 && repeatNewPassword.val() == newPassword.val()) {
                validateTip(repeatNewPassword.next(), {"color": "green"}, imgYes, true);
            } else {
                validateTip(repeatNewPassword.next(), {"color": "red"}, imgNo + " 两次密码输入不一致，请重新输入", false);
            }
        });
        saveBtn.on("click", function () {
            oldPassword.blur();
            newPassword.blur();
            repeatNewPassword.blur();
            if (oldPassword.attr("validateStatus") == "true" &&
                newPassword.attr("validateStatus") == "true" &&
                repeatNewPassword.attr("validateStatus") == "true") {
                if (confirm("确定要修改密码？")) {
                    $("#studentForm").submit();
                }
            } else {
                return false;
            }
        });
    });
</script>

</html>