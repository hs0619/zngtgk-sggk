$(function() {

    $("#QUERY").on("click", function(e) {
        var eiInfo = new EiInfo();
        eiInfo.setByNode("inqu");
        EiCommunicator.send("DUHB50", "query", eiInfo, {
            onSuccess : function(ei) {
                resultGrid.setEiInfo(ei);
                NotificationUtil(ei.getMsg());
            },
            onFail : function(ei) {
                // 发生异常
                NotificationUtil("加载数据出错" + ei.getMsg(), "error");
                console.log(ei);
            }
        });

    });

    // $("#INSERT").on("click", function(e) {
    //     var url = IPLATUI.CONTEXT_PATH + "/web/DMCM2101?type=insert";
    //     $("#itemSF").data("kendoWindow").setOptions({
    //         open : function() {
    //             $("#itemSF").data("kendoWindow").refresh({
    //                 url : url
    //             });
    //         },
    //         lazyload : true,
    //         minWidth : 1200,
    //         minHeight : 160,
    //         iframe : true
    //     });
    //     $("#itemSF").data("kendoWindow").open().center();
    // });
    //
    //
    // $("#DELETE").on("click", function(e) {
    //     var info = new EiInfo();
    //     info.setByNode("inqu");
    //     if (resultGrid.getCheckedRows().length < 1)
    //         return;
    //     var block = resultGrid.getInitBlock();
    //     block.setRows(resultGrid.getCheckedBlockData().rows);
    //     info.addBlock(block);
    //     IPLAT.confirm({
    //         message : '<b>确定删除选中数据吗？</b>',
    //         okFn : function(e) {
    //             EiCommunicator.send("DMCM21", "delete", info, {
    //                 onSuccess : function(ei) {
    //                     resultGrid.setEiInfo(ei);
    //                     NotificationUtil(ei.getMsg());
    //                 },
    //                 onFail : function(ei) {
    //                     NotificationUtil("信息加载出错" + ei.getMsg(), "error");
    //                     console.log(ei);
    //                 }
    //             });
    //         },
    //         cancelFn : function(e) {
    //         }
    //     });
    //
    // });
    //
    // IPLATUI.EFGrid = {
    //     "result" : {
    //         columns : [ {
    //             field : "edit",
    //             title : "编辑",
    //             enable : false,
    //             width : 40,
    //             template : "<span><button class='i-btn-sm compute'>编辑</button></span>"
    //         } ],
    //         onCellClick : function(e) {
    //             if (e.field === 'edit') {
    //                 var model = e.model;
    //                 if (!model)
    //                     return;
    //                 var url = IPLATUI.CONTEXT_PATH
    //                     + "/web/DMCM2101?type=update&itemcode="
    //                     + model.itemcode;
    //                 $("#itemSF").data("kendoWindow").setOptions({
    //                     open : function() {
    //                         $("#itemSF").data("kendoWindow").refresh({
    //                             url : url
    //                         });
    //                     },
    //                     lazyload : true,
    //                     minWidth : 1200,
    //                     minHeight : 160,
    //                     iframe : true
    //                 });
    //                 $("#itemSF").data("kendoWindow").open().center();
    //             }
    //         },
    //
    //     }
    // }
    //
    //
    // IPLATUI.EFSelect = {
    //     "inqu_status-0-departmentid" : {
    //         // 下拉选项改变之后触发
    //         change : function(e) { // 获取改变值
    //             var eiInfo = new EiInfo();
    //             eiInfo.set("inqu_status-0-departmentid", this.value());
    //             EiCommunicator.send("DMCM21", "getProcedureByDept", eiInfo, {
    //                 onSuccess : function(ei) {
    //                     var list = [ {
    //                         "valueField" : "",
    //                         "textField" : "全部"
    //                     } ];
    //                     if (ei["blocks"]["procedureblock"] != null) {
    //                         $.each(ei["blocks"]["procedureblock"]["rows"],
    //                             function(i, obj) {
    //                                 list.push({
    //                                     "valueField" : obj[0],
    //                                     "textField" : obj[1]
    //                                 });
    //                             });
    //                     }
    //                     var dataSource = new kendo.data.DataSource({
    //                         data : list
    //                     });
    //                     IPLAT.EFSelect.setDataSource(
    //                         $("#inqu_status-0-procedureid"), dataSource);
    //                     ;
    //                 },
    //                 onFail : function(ei) {
    //                     // 发生异常
    //                     NotificationUtil("加载数据出错" + ei.getMsg(), "error");
    //                     console.log(ei);
    //                 }
    //             });
    //         }
    //     }
    // }
    //
    //
    // IPLATUI.EFWindow = {
    //     "itemSF" : {
    //         close : function(e) {
    //             var eiInfo = new EiInfo();
    //             eiInfo.setByNode("inqu");
    //             EiCommunicator.send("DMCM21", "query", eiInfo, {
    //                 onSuccess : function(ei) {
    //                     resultGrid.setEiInfo(ei);
    //                 },
    //                 onFail : function(ei) {
    //                     // 发生异常
    //                     NotificationUtil("加载数据出错" + ei.getMsg(), "error");
    //                     console.log(ei.getMsg());
    //                 }
    //             });
    //         }
    //     }
    // };

});