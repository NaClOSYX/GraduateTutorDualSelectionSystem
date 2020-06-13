$('#adminTable').bootstrapTable({
    ajax: function (request) {//使用ajax请求
        $.ajax({
            type: "GET",
            url: '/admin/admin.do',
            contentType: 'application/json;charset=utf-8',
            dataType: 'json',
            data: {method: "getAllAdmins"},
            success: function (res) {
                request.success({
                    row: res,
                });
                $('#adminTable').bootstrapTable('load', res);
            },
            error: function (error) {
                console.log(error);
            }
        })
    },
    toolbar: '#toolbar',             //工具按钮用哪个容器
    striped: true,                   //是否显示行间隔色
    cache: false,                    //是否使用缓存
    pageNumber: 1,                   //初始化加载第一页
    pagination: true,                //是否分页
    sidePagination: 'client',        //server:服务器端分页|client：前端分页
    //queryParams: 'queryParams',    //传递参数
    search: true,                    //是否显示表格搜索
    sortName: "adminId",           //定义要排序的列
    sortOrder: "asc",                //排序方式
    pageSize: 10,                    //单页记录数
    clickToSelect: true,             //是否启用点击选中行
    pageList: [10],                  //可选择单页记录数
    showRefresh: true,               //刷新按钮
    showToggle: false,               //是否显示详细视图和列表视图的切换按钮
    cardView: false,                 //是否显示详细视图
    showColumns: true,               //是否显示所有的列
    switchable: false,               //禁用可切换的列项
    height: $(window).height() - 70,   //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
    uniqueId: "adminId",           //每一行的唯一标识，一般为主键列
    columns: [{
        checkbox: true
    }, {
        title: '用户名',
        field: 'adminId',
        sortable: true,
        width: 300
    }, {
        title: '专业',
        field: 'adminMajor',
        sortable: true,
        width: 300
    }, {
        title: '操作',
        field: 'publicationTime',
        formatter: operation,//对资源进行操作
    }]
});

function operation(value, row, index) {
    var htm = "<a onClick='return confirm(\"确定删除?\");' href='/admin/admin.do?method=deleteAdminData&adminId=" + row["adminId"] + "'>删除</a> " +
        "<a onClick='return confirm(\"确定重置?\");' href='/admin/admin.do?method=resetAdminPassword&adminId=" + row["adminId"] + "'>密码重置</a>";
    return htm;
}

//打开上传文件窗口
function openUploadDialog() {
    $('#btnFileUpload').click();
};

//选中文件自动上传
function submitForm() {
    $("#uploadFileForm").submit();
};
//验证输入的名字是否符合格式
$(function () {
    var newAdminId = $('#addAdminModelAdminId');
    var newAdminMajor = $('#addAdminModelAdminMajor');
    var confirmAddAdmin = $('#confirmAddAdmin');

    newAdminId.next().html("*");
    newAdminMajor.next().html("*");


    newAdminMajor.on("focus", function () {
        validateTip(newAdminMajor.next(), {"color": "#666666"}, "* 请选择专业", false);
    }).on("blur", function () {
        if (newAdminMajor.val() == "请选择专业") {
            validateTip(newAdminMajor.next(), {"color": "red"}, imgNo + " 请选择专业", false);
        } else {
            validateTip(newAdminMajor.next(), {"color": "green"}, imgYes, true);
        }
    });
    newAdminId.on("focus", function () {
        validateTip(newAdminId.next(), {"color": "#666666"}, "* 请输入正确格式的学号", false);
    }).on("blur", function () {
        $.ajax({
            type: "get",
            url: "/admin/admin.do",
            data: {method: "validateAdminId", adminId: newAdminId.val()},
            dataType: "json",
            success: function (data) {
                if (data.result == "true") {//学号不存在
                    validateTip(newAdminId.next(), {"color": "green"}, imgYes, true);
                } else if (data.result == "false") {//学号已存在
                    validateTip(newAdminId.next(), {"color": "red"}, imgNo + " 用户名已存在", false);
                } else if (data.result == "error") {//学号输入为空
                    validateTip(newAdminId.next(), {"color": "red"}, imgNo + " 请输入用户名", false);
                } else if (data.result == "length") {//学号格式不对
                    validateTip(newAdminId.next(), {"color": "red"}, imgNo + " 请输入正确的格式", false);
                }
            },
            error: function (data) {
                //请求出错
                validateTip(newAdminId.next(), {"color": "red"}, imgNo + " 请求错误", false);
            }
        })
    })
    confirmAddAdmin.on("click", function () {
        newAdminId.blur();
        newAdminMajor.blur();
        if (newAdminId.attr("validateStatus") == "true" &&
            newAdminMajor.attr("validateStatus") == "true") {
            if (confirm("确定要增加管理员吗？")) {
                $("#addAdminModelForm").submit();
            }
        } else {
            return false;
        }
    })
})