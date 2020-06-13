$('#majorTable').bootstrapTable({
    ajax: function (request) {//使用ajax请求
        $.ajax({
            type: "GET",
            url: '/admin/admin.do',
            contentType: 'application/json;charset=utf-8',
            dataType: 'json',
            data: {method: "getAllMajorsExceptALL"},
            success: function (res) {
                request.success({
                    row: res,
                });
                $('#majorTable').bootstrapTable('load', res);
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
    sortName: "majorId",           //定义要排序的列
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
    uniqueId: "majorId",           //每一行的唯一标识，一般为主键列
    columns: [{
        title: '专业编号',
        field: 'majorId',
        sortable: true,
        width: 150
    }, {
        title: '专业名',
        field: 'majorName',
        sortable: true,
        width: 150
    }, {
        title: '最大人数',
        field: 'studentMax',
        sortable: true,
        width: 150
    }, {
        title: '操作',
        field: 'publicationTime',
        formatter: operation,//对资源进行操作
    }]
});

function operation(value, row, index) {
    var htm = "<a onClick='return confirm(\"确定删除?\");' href='/admin/admin.do?method=deleteMajorData&majorId=" + row["majorId"] + "'>删除</a> " +
        "<a style='color: dodgerblue' data-toggle='modal' data-target='#updateMajorModel' onclick='values(" + row["majorId"] + ")'>修改</a> ";
    return htm;
}

//验证输入的专业是否符合格式
$(function () {
    var newMajorName = $('#addMajorModelMajorName');
    var newStudentMax = $('#addMajorStudentMax');
    var confirmAddMajor = $('#confirmAddMajor');
    newMajorName.next().html("*");
    newStudentMax.next().html("*");

    newStudentMax.on("focus", function () {
        validateTip(newStudentMax.next(), {"color": "#666666"}, "* 请输入专业最大招生人数", false);
    }).on("blur", function () {
        if (newStudentMax.val() == "") {
            validateTip(newStudentMax.next(), {"color": "red"}, imgNo + " 专业最大招生人数不能为空", false);
        } else {
            validateTip(newStudentMax.next(), {"color": "green"}, imgYes, true);
        }
    });
    newMajorName.on("focus", function () {
        validateTip(newMajorName.next(), {"color": "#666666"}, "* 请输入专业名", false);
    }).on("blur", function () {
        $.ajax({
            type: "get",
            url: "/admin/admin.do",
            data: {method: "validateMajorName", majorName: newMajorName.val()},
            dataType: "json",
            success: function (data) {
                if (data.result == "true") {//专业不存在
                    validateTip(newMajorName.next(), {"color": "green"}, imgYes, true);
                } else if (data.result == "false") {//专业已存在
                    validateTip(newMajorName.next(), {"color": "red"}, imgNo + " 专业已存在", false);
                } else if (data.result == "error") {//专业输入为空
                    validateTip(newMajorName.next(), {"color": "red"}, imgNo + " 请输入专业", false);
                }
            },
            error: function (data) {
                //请求出错
                validateTip(newMajorName.next(), {"color": "red"}, imgNo + " 请求错误", false);
            }
        })
    })

    confirmAddMajor.on("click", function () {
        newMajorName.blur();
        newStudentMax.blur();
        if (newStudentId.attr("validateStatus") == "true" &&
            newStudentMax.attr("validateStatus") == "true") {
            if (confirm("确定要增加专业吗？")) {
                $("#addMajorModel").submit();
            }
        } else {
            return false;
        }
    })
});

function values(id) {
    //document.getElementById('updateStudentModelStudentId').value = id;
    $.ajax({
        type: "POST",
        url: "/admin/admin.do",
        data: {method: "getMajorById", majorId: id},
        success: function (data) {
            document.getElementById('updateMajorModelMajorId').value = data.majorId;
            document.getElementById('updateMajorModelMajorName').value = data.majorName;
            document.getElementById('updateMajorStudentMax').value = data.studentMax;
        },
        error: function (data) {
            //请求出错
        }
    })
};

