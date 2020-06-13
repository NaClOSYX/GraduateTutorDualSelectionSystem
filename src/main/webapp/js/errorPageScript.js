var timer;

//启动跳转的定时器
function startTimes() {
    timer = window.setInterval(showSeconds, 1000);
}

var i = 5;

function showSeconds() {
    if (i > 0) {
        i--;
        document.getElementById("seconds").innerHTML = i;
    } else {
        window.clearInterval(timer);
        /*要跳转的请求*/
        location.href = "javascript:history.go(-1)";
    }
}

//取消跳转
function resetTimer() {
    if (timer != null && timer != undefined) {
        window.clearInterval(timer);
        /*取消跳转的请求*/
        location.href = "javascript:history.go(-1)";
    }
}