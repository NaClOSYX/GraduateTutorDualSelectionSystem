$('#teacherTable').bootstrapTable({
    ajax: function (request) {//使用ajax请求
        $.ajax({
            type: "GET",
            url: '/student/student.do',
            contentType: 'application/json;charset=utf-8',
            dataType: 'json',
            data: {method: "getTeacherList"},
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
    pagination: false,                //是否分页
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
    height: $(window).height() - 80,   //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
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
    }]
});

//确定选择导师操作
function chooseTeacher() {
    var row = $.map($('#teacherTable').bootstrapTable('getSelections'), function (row) {
        return row;
    });
    if (row.length > 3) {
        alert("人数大于三个")
        return false;
    } else if (row.length < 3) {
        alert("人数不足三个")
        return false;
    } else {
        var result = confirm("是否确定选择导师？");
        if (result == true) {
            var teacherId1 = row[0].teacherId;
            var teacherId2 = row[1].teacherId;
            var teacherId3 = row[2].teacherId;
            window.location.href = "student.do?method=studentChooseTeacher" +
                "&teacherId1=" + teacherId1 +
                "&teacherId2=" + teacherId2 +
                "&teacherId3=" + teacherId3;
        }
    }
}