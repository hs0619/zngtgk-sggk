$(function () {

    $("#QUERY").on("click", function (e) {
        queryinfo()
        // var eiInfo = new EiInfo();
        // eiInfo.setByNode("inqu");
        // var reportType = $("#inqu_status-0-reporttype").val();
        // var reportName = $("#inqu_status-0-reportname").val();
        // if (reportType == "" || reportName == "") {
        //     NotificationUtil("查询前请选择报表", "warning");
        //     return;
        // }
        // IPLAT.progress($("body"), true);
        // EiCommunicator.send("DUHD21", "query", eiInfo, {
        //     onSuccess : function(ei) {
        //         resultGrid.setEiInfo(ei);
        //         NotificationUtil(ei.getMsg());
        //         IPLAT.progress($("body"), false);
        //     },
        //     onFail : function(ei) {
        //         // 发生异常
        //         NotificationUtil("加载数据出错" + ei.getMsg(), "error");
        //         console.log(ei);
        //         IPLAT.progress($("body"), false);
        //     }
        // });
    });

    queryinfo = function () {
        var eiInfo = new EiInfo();
        eiInfo.setByNode("inqu");
        var reportType = $("#inqu_status-0-reporttype").val();
        var reportName = $("#inqu_status-0-reportname").val();
        if (reportType == "" || reportName == "") {
            NotificationUtil("查询前请选择报表", "warning");
            return;
        }
        IPLAT.progress($("body"), true);
        EiCommunicator.send("DUHD21", "query", eiInfo, {
            onSuccess: function (ei) {
                resultGrid.setEiInfo(ei);
                NotificationUtil(ei.getMsg());
                IPLAT.progress($("body"), false);
            },
            onFail: function (ei) {
                // 发生异常
                NotificationUtil("加载数据出错" + ei.getMsg(), "error");
                console.log(ei);
                IPLAT.progress($("body"), false);
            }
        });
    }

    IPLATUI.EFGrid = {
        "result": {
            columns: [{
                field: "operation",
                title: "操作",
                enable: false,
                width: 50,
                template: function (data) {
                    return "<span><button class='i-btn-sm create'>生成</button><button class='i-btn-sm download'>下载</button></span>"
                }
            }],
            loadComplete: function (grid) {
                grid.element.on("click", ".download", function (e) {
                    e.preventDefault(); // 防止button的form submit
                    var model = grid.dataItem($(this).closest("tr"));
                    if (model.exist != "已生成") {
                        NotificationUtil("该报表暂未生成,不可下载");
                        return;
                    }
                    var reportPath = model.filepath;
                    var excelName = model.filename;
                    var url = IPLATUI.CONTEXT_PATH + "/web/DUHD2909?reportPath=" + reportPath + "&excelName=" + excelName;
                    window.open(url);
                });
                grid.element.on("click", ".create", function (e) {
                    e.preventDefault(); // 防止button的form submit
                    var model = grid.dataItem($(this).closest("tr"));

                    var eiInfo = new EiInfo();
                    eiInfo.setAttr(model);
                    IPLAT.progress($("body"), true);
                    EiCommunicator.send("DUHD21", "createReport", eiInfo, {
                        onSuccess: function (ei) {
                            IPLAT.progress($("body"), false);
                            if (ei.getStatus() == "1") {
                                resultGrid.dataSource.page(1);
                                // queryinfo();
                                NotificationUtil(eiInfo.getMsg());
                            } else {
                                NotificationUtil(eiInfo.getMsg(), "error");
                            }
                        },
                        onFail: function (ei) {
                            // 发生异常
                            NotificationUtil("请求出错" + ei.getMsg(), "error");
                            console.log(ei);
                            IPLAT.progress($("body"), false);
                        }
                    });
                });
            },
        }
    }

    IPLATUI.EFSelect = {
        "inqu_status-0-reporttype": {
            // 下拉选项改变之后触发
            change: function (e) { // 获取改变值
                var eiInfo = new EiInfo();
                eiInfo.set("inqu_status-0-reporttype", this.value());
                IPLAT.progress($("body"), true);
                EiCommunicator.send("DUHD21", "getReportInfo", eiInfo, {
                    onSuccess: function (ei) {
                        var list = [];
                        if (ei["blocks"]["reportInfoBlock"] != null) {
                            $.each(ei["blocks"]["reportInfoBlock"]["rows"],
                                function (i, obj) {
                                    list.push({
                                        "valueField": obj[0],
                                        "textField": obj[0]
                                    });
                                });
                        }
                        var dataSource = new kendo.data.DataSource({
                            data: list
                        });
                        IPLAT.EFSelect.setDataSource(
                            $("#inqu_status-0-reportname"), dataSource);
                        if (list.length > 0) {
                            IPLAT.EFSelect.value(
                                $("#inqu_status-0-reportname"),
                                list[0]["valueField"]);
                        }
                        IPLAT.progress($("body"), false);
                    },
                    onFail: function (ei) {
                        // 发生异常
                        NotificationUtil("加载数据出错" + ei.getMsg(), "error");
                        console.log(ei);
                        IPLAT.progress($("body"), false);
                    }
                });
            }
        }
    }
});
