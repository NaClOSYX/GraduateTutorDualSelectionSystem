$('#teacherTable').bootstrapTable({
    ajax: function (request) {//使用ajax请求
        $.ajax({
            type: "GET",
            url: '/admin/admin.do',
            contentType: 'application/json;charset=utf-8',
            dataType: 'json',
            data: {method: "getAllTeachers"},
            success: function (res) {
                request.success({
                    row: res,
                });
                $('#teacherTable').bootstrapTable('load', res);
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
    sortName: "teacherId",           //定义要排序的列
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
    uniqueId: "teacherId",           //每一行的唯一标识，一般为主键列
    columns: [{
        checkbox: true
    }, {
        title: '工号',
        field: 'teacherId',
        sortable: true,
        width: 200
    }, {
        title: '姓名',
        field: 'teacherName',
        sortable: true,
        width: 200
    }, {
        title: '专业',
        field: 'teacherMajor',
        width: 200
    }, {
        title: '简介',
        field: 'teacherIntroduce',
        width: 300
    }, {
        title: '操作',
        field: 'publicationTime',
        formatter: operation,//对资源进行操作
    }]
});

function operation(value, row, index) {
    var htm = "<a onClick='return confirm(\"确定删除?\");' href='/admin/admin.do?method=deleteTeacherData&teacherId=" + row["teacherId"] + "'>删除</a> " +
        "<a style='color: dodgerblue' data-toggle='modal' data-target='#updateTeacherModel' onclick='values(" + row["teacherId"] + ")'>修改</a> " +
        "<a onClick='return confirm(\"确定重置?\");' href='/admin/admin.do?method=resetTeacherPassword&teacherId=" + row["teacherId"] + "'>密码重置</a>";
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

function values(id) {
    $.ajax({
        type: "POST",
        url: "/admin/admin.do",
        data: {method: "getTeacherById", teacherId: id},
        success: function (data) {
            document.getElementById('updateTeacherModelTeacherId').value = data.teacherId;
            document.getElementById('updateTeacherModelTeacherName').value = data.teacherName
            document.getElementById('updateTeacherModelTeacherMajor').value = data.teacherMajor;
            document.getElementById('updateTeacherModelTeacherIntroduce').value = data.teacherIntroduce;
        },
        error: function (data) {
            //请求出错
        }
    })
};
//验证输入的新导师是否符合格式
$(function () {
    var newTeacherId = $('#addTeacherModelTeacherId');
    var newTeacherName = $('#addTeacherModelTeacherName');
    var newTeacherMajor = $('#addTeacherModelTeacherMajor');
    var newTeacherIntroduce = $('#addTeacherModelTeacherIntroduce');
    var confirmAddTeacher = $('#confirmAddTeacher');

    newTeacherId.next().html("*");
    newTeacherName.next().html("*");
    newTeacherMajor.next().html("*");
    newTeacherIntroduce.next().html("*");

    newTeacherId.on("focus", function () {
        validateTip(newTeacherId.next(), {"color": "#666666"}, "* 请输入正确格式的工号", false);
    }).on("blur", function () {
        $.ajax({
            type: "get",
            url: "/admin/admin.do",
            data: {method: "validateTeacherId", teacherId: newTeacherId.val()},
            dataType: "json",
            success: function (data) {
                if (data.result == "true") {//工号不存在
                    validateTip(newTeacherId.next(), {"color": "green"}, imgYes, true);
                } else if (data.result == "false") {//工号已存在
                    validateTip(newTeacherId.next(), {"color": "red"}, imgNo + " 工号已存在", false);
                } else if (data.result == "error") {//工号输入为空
                    validateTip(newTeacherId.next(), {"color": "red"}, imgNo + " 请输入工号", false);
                } else if (data.result == "length") {//工号格式不对
                    validateTip(newTeacherId.next(), {"color": "red"}, imgNo + " 请输入正确格式的工号", false);
                }
            },
            error: function (data) {
                //请求出错
                validateTip(newTeacherId.next(), {"color": "red"}, imgNo + " 请求错误", false);
            }
        })
    });
    newTeacherName.on("focus", function () {
        validateTip(newTeacherName.next(), {"color": "#666666"}, "* 请输入姓名", false);
    }).on("blur", function () {
        console.log("log" + newTeacherName.val() + "log");
        if (newTeacherName.val() == "") {
            validateTip(newTeacherName.next(), {"color": "red"}, imgNo + " 姓名不能为空", false);
        } else {
            validateTip(newTeacherName.next(), {"color": "green"}, imgYes, true);
        }
    });

    newTeacherMajor.on("focus", function () {
        validateTip(newTeacherMajor.next(), {"color": "#666666"}, "* 请输入专业", false);
    }).on("blur", function () {
        if (newTeacherMajor.val() == "请选择专业") {
            validateTip(newTeacherMajor.next(), {"color": "red"}, imgNo + " 请选择专业", false);
        } else {
            validateTip(newTeacherMajor.next(), {"color": "green"}, imgYes, true);
        }
    });
    newTeacherIntroduce.on("focus", function () {
        validateTip(newTeacherIntroduce.next(), {"color": "#666666"}, "* 请输入简介", false);
    }).on("blur", function () {
        if (newTeacherIntroduce.val() == "") {
            validateTip(newTeacherIntroduce.next(), {"color": "red"}, imgNo + " 简介不能为空", false);
        } else {
            validateTip(newTeacherIntroduce.next(), {"color": "green"}, imgYes, true);
        }
    });

    confirmAddTeacher.on("click", function () {
        newTeacherId.blur();
        newTeacherName.blur();
        newTeacherMajor.blur();
        if (newTeacherId.attr("validateStatus") == "true" &&
            newTeacherName.attr("validateStatus") == "true" &&
            newTeacherMajor.attr("validateStatus") == "true" &&
            newTeacherIntroduce.attr("validateStatus") == "true") {
            if (confirm("确定要增加导师吗？")) {
                $("#addTeacherModelForm").submit();
            }
        } else {
            return false;
        }
    })
});