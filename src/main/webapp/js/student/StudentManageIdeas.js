$(".idea").click(function () {
    $("#changeIdea").modal("show")
    $("#oldTeacher").val($.trim($(this).text()))
})