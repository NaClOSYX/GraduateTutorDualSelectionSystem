$('#loginLogsTable').bootstrapTable({
    url: '/admin/admin.do',
    method: 'post',
    contentType: 'application/x-www-form-urlencoded',
    toolbar: '#toolbar',             //工具按钮用哪个容器
    striped: true,                   //是否显示行间隔色
    cache: false,                    //是否使用缓存
    pageNumber: 1,                   //初始化加载第一页
    pagination: true,                //是否分页
    sidePagination: 'server',        //server:服务器端分页|client：前端分页
    search: true,                    //是否显示表格搜索
    sortName: "logId",           //定义要排序的列
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
    uniqueId: "logId",           //每一行的唯一标识，一般为主键列
    queryParams: function (params) {//上传服务器的参数
        var temp = {//如果是在服务器端实现分页，limit、offset这两个参数是必须的
            method: "getAllLogsLimit",
            limit: params.limit, // 每页显示数量
            offset: params.offset, // SQL语句起始索引
            search: params.search,
            //page: (params.offset / params.limit) + 1,   //当前页码
        };
        console.log(temp);
        return temp;
    },    //传递参数
    columns: [{
        title: '日志编号',
        field: 'logId',
        sortable: true,
        width: 150
    }, {
        title: '用户名',
        field: 'userId',
        sortable: true,
        width: 150
    }, {
        title: '用户角色',
        field: 'userRole',
        sortable: true,
        width: 150
    }, {
        title: '操作',
        field: 'userOp',
        width: 150
    }, {
        title: '时间',
        field: 'opTime',
        sortable: true,
        width: 150
    }, {
        title: 'ip地址',
        field: 'opIp',
        width: 150
    }]
});

function getParams(params) {//上传服务器的参数
    var temp = {//如果是在服务器端实现分页，limit、offset这两个参数是必须的
        limit: params.limit, // 每页显示数量
        offset: params.offset, // SQL语句起始索引
        //page: (params.offset / params.limit) + 1,   //当前页码
    };
    console.log(temp);
    return temp;
}