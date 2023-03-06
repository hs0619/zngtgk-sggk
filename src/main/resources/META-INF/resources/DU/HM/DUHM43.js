$(function () {
    /* 时间控件知只到月*/
    IPLATUI.EFDatePicker = {
        "inqu_status-0-datatime": {
            start: "year",
            depth: "year",
        },
    }

    $("#QUERY").on("click",function(e){
        qeruy();
    });



    qeruy=function () {
        resultGrid.dataSource.page(1);
    }


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
                    EiCommunicator.send("DUHM43", "saveInfo", eiInfo, {
                        onSuccess: function(ei) {
                            if (ei.get("status") == -1) {
                                NotificationUtil(ei.getMsg(), "error");
                            } else if (ei.get("status") == 1) {
                                NotificationUtil("保存成功，"+ei.getMsg(), "success");
                                //resultGrid.setEiInfo(ei);
                            }

                            setTimeout(function() {
                                qeruy();
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

    IPLATUI.EFGrid = {
        "result": {
            pageable: {
                pageSize: 200,
                pageSizes: [ 100,200, 500, 1000]
            },
            height: function(){
                return ($("#main-container").height() - $("#inqu").height() - 115);
            },

            onSuccess: function (e) {
                if(e.type == "create" || e.type == "update"|| e.type == "destroy"){
                    var resultblock = e.eiInfo.blocks["result"];
                    if (resultblock != null) {
                        resultGrid.setEiBlock(resultblock);
                    }
                }
            }
        }
    }
});

