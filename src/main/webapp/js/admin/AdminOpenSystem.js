var startTime = new Date().format("yyyy-MM-dd hh:mm");
var endTime = new Date().format("yyyy-MM-dd hh:mm");

$('#datePicker').daterangepicker({
    timePicker: true, //显示时间
    timePicker24Hour: true, //时间制
    showWeekNumbers: true,//显示周数
    minDate: new Date(),//过期时间不能选择
    timePickerIncrement: 30,// 分钟选择列表的增量
    linkedCalendars: false,//显示的两个日历将始终为两个连续月份
    locale: {
        format: "YYYY-MM-DD HH:mm", //设置显示格式
        applyLabel: '确定', //确定按钮文本
        cancelLabel: '取消', //取消按钮文本
        customRangeLabel: '自定义',
        daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
        monthNames: ['一月', '二月', '三月', '四月', '五月', '六月',
            '七月', '八月', '九月', '十月', '十一月', '十二月'
        ],
        firstDay: 1
    },
}, function (start, end) {
    startTime = start.format('YYYY-MM-DD HH:mm')
    endTime = end.format('YYYY-MM-DD HH:mm')
});

function updateDate() {
    //var val = $('#datetimes').val();
    console.log(startTime)
    console.log(endTime)
    $.post({})
}

$("#submit").click(function () {
    //发起异步请求//参数一：请求的地址；参数二：传递的参数；参数三：回调函数，接收服务器回传的数据
    $.ajax({
        type: "POST",
        url: "admin.do",
        async: false,
        data: {
            method: 'setChooseTime',
            startTime: startTime,
            endTime: endTime
        },
        dataType: "json",
        success: function (data) {
            alert(data.message)
        }
    });
});
$("#closeSystem").click(function () {
    $.ajax({
        type: "POST",
        url: "admin.do",
        async: false,
        data: {
            method: 'closeSystem',
        },
        dataType: "json",
        success: function (data) {
            alert(data.message)
            window.location.reload()
        }
    });
});
