$('#studentTable').bootstrapTable({
    ajax: function (request) {//使用ajax请求
        $.ajax({
            type: "GET",
            url: '/teacher/teacher.do',
            contentType: 'application/json;charset=utf-8',
            dataType: 'json',
            data: {method: "getStudentListDecided"},
            success: function (res) {
                request.success({
                    row: res,
                });
                $('#studentTable').bootstrapTable('load', res);
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
    sortName: "studentId",           //定义要排序的列
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
    uniqueId: "studentId",           //每一行的唯一标识，一般为主键列
    columns: [{
        title: '学号',
        field: 'studentId',
        sortable: true,
        width: 200
    }, {
        title: '姓名',
        field: 'studentName',
        sortable: true,
        width: 200
    }, {
        title: '专业',
        field: 'majorName',
        sortable: true,
        width: 300
    }]
});