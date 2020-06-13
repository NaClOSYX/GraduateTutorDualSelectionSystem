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
            url: "/teacher/teacher.do",
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