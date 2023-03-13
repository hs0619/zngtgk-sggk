$(function() {
    $("#QUERY").on("click", function (e) {
        resultGrid.dataSource.page(1);
    });
    //设置日期控件显示的层级（年）
    IPLATUI.EFDatePicker = {
        "inqu_status-0-year": {
            start: "decade",
            depth: "decade",
        },
    }

});
