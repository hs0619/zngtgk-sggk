// $(function (){
//     $("#QUERY").on("click",function(e){
//         resultGrid.dataSource.page(1);
//     });
// })


$(function () {
    IPLATUI.EFGrid = {
        "result": { // 根据ID重写
            loadComplete: function () {
                initEvent();
            },
            onFail: function (_out) {
                let err = _out.errorMsg;
            }
        }
    };
    IPLATUI.EFGrid = {
        "result2": { // 根据ID重写
            loadComplete: function () {
                initEvent();
            },
            onFail: function (_out) {
                let err = _out.errorMsg;
            }
        }
    };

    var validator = IPLAT.Validator({
        id: "result"
    });

    //保存
    $(document.body).on("click", "#SAVEINFO", function(e) {
        //获取选中的数据列
        var checkedArr = resultGrid.getCheckedRows();
        var selectCount =checkedArr.length;
        if(selectCount>0){
            //
            if (!validator.validate()) {
                NotificationUtil("数据填写不正确！", "error");
                return;
            }
            var block = resultGrid.model2EiBlock(checkedArr);
            var eiInfo = new EiInfo();
            eiInfo.setByNode("inqu");
            eiInfo.addBlock(block);
            IPLAT.confirm({
                message: '<b>确定保存数据吗？</b>',
                okFn: function(e) {
                    EiCommunicator.send("DUHD10", "update", eiInfo, {
                        onSuccess: function(ei) {
                            if (ei.get("status") == -1) {
                                NotificationUtil(ei.getMsg(), "error");
                            } else if (ei.get("status") == 1) {
                                NotificationUtil("保存成功，"+ei.getMsg(), "success");
                                //resultGrid.setEiInfo(ei);
                            }

                            setTimeout(function() {
                                on_query_click();
                            }, 800)
                        }, onFail: function() {
                            // 发生异常
                            NotificationUtil("比对失败", "error");
                        }
                    });
                },
                cancelFn: function(e) { IPLAT.NotificationUtil('取消该操作!'); },
                title: '操作提示',
                minWidth: 200
            });
        }else{
            NotificationUtil("没有选中的数据！", "info");
            return false;
        }
    });

    //保存
    $(document.body).on("click", "#SAVEINFO2", function(e) {
        //获取选中的数据列
        var checkedArr = result2Grid.getCheckedRows();
        var selectCount =checkedArr.length;
        if(selectCount>0){
            //
            if (!validator.validate()) {
                NotificationUtil("数据填写不正确！", "error");
                return;
            }
            var block = result2Grid.model2EiBlock(checkedArr);
            var eiInfo = new EiInfo();
            eiInfo.setByNode("inqu");
            eiInfo.addBlock(block);
            IPLAT.confirm({
                message: '<b>确定保存数据吗？</b>',
                okFn: function(e) {
                    EiCommunicator.send("DUHD10", "update2", eiInfo, {
                        onSuccess: function(ei) {
                            if (ei.get("status") == -1) {
                                NotificationUtil(ei.getMsg(), "error");
                            } else if (ei.get("status") == 1) {
                                NotificationUtil("保存成功，"+ei.getMsg(), "success");
                                //resultGrid.setEiInfo(ei);
                            }

                            setTimeout(function() {
                                on_query_click();
                            }, 800)
                        }, onFail: function() {
                            // 发生异常
                            NotificationUtil("比对失败", "error");
                        }
                    });
                },
                cancelFn: function(e) { IPLAT.NotificationUtil('取消该操作!'); },
                title: '操作提示',
                minWidth: 200
            });
        }else{
            NotificationUtil("没有选中的数据！", "info");
            return false;
        }
    });


    IPLATUI.EFSelect = {
        "inqu_status-0-departmentid": {
            // 下拉选项改变之后触发
            change: function (e) { //获取改变值
                var departmentId = this.value();
                //根据选中的厂部id,去查询对应的工序列表列表
                var eiInfo = new EiInfo();
                eiInfo.set("departmentid",departmentId);
                EiCommunicator.send("DUHD10","queryProcByDepid",eiInfo, {
                    onSuccess: function (ei) {
                        if(ei["blocks"]["procedureList"] != null){
                            var proce = [];
                            $.each(ei["blocks"]["procedureList"]["rows"], function (i, obj) {
                                if(i == 0){
                                    proce.push({"valueField": "", "textField": "全部"});
                                }
                                proce.push({"valueField": obj[0], "textField": obj[1]});
                            });
                            var dataSource = new kendo.data.DataSource({
                                data: proce
                            });
                            IPLAT.EFSelect.setDataSource($("#inqu_status-0-procedureid"), dataSource);

                        };

                    }, onFail: function (ei) {
                        // 发生异常
                        NotificationUtil("加载数据出错" + ei.getMsg(), "error");
                        console.log(ei);
                    }
                });
            }

        }


    }


    // IPLATUI.EFGrid = {
    //     "result": {
    //         pageable: {
    //             pageSize: 200,
    //             pageSizes: [ 100,200, 500, 1000]
    //         },
    //         height: function(){
    //             return ($("#main-container").height() - $("#inqu").height() - 115);
    //         },
    //
    //         onSuccess: function (e) {
    //             if(e.type == "create" || e.type == "update"){
    //                 var resultblock = e.eiInfo.blocks["result"];
    //                 if (resultblock != null) {
    //                     resultGrid.setEiBlock(resultblock);
    //                 }
    //             }
    //         }
    //     },
    //     "result2": {
    //         pageable: {
    //             pageSize: 200,
    //             pageSizes: [ 100,200, 500, 1000]
    //         },
    //         height: function(){
    //             return ($("#main-container").height() - $("#inqu").height() - 115);
    //         },
    //
    //         onSuccess: function (e) {
    //             if(e.type == "create" || e.type == "update"){
    //                 var resultblock = e.eiInfo.blocks["result2"];
    //                 if (resultblock != null) {
    //                     resultGrid.setEiBlock(resultblock);
    //                 }
    //             }
    //         }
    //     }
    // }



});

function on_query_click() {
    resultGrid.dataSource.page(1);
    result2Grid.dataSource.page(1);
}

function initEvent() {
    $("#QUERY").on("click", on_query_click);
}


