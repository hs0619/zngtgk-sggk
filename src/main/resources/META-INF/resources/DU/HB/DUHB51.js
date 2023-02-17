$(function() {

    $("#QUERY").on("click", function(e) {
        var eiInfo = new EiInfo();
        eiInfo.setByNode("inqu");
        EiCommunicator.send("DUHB51", "query", eiInfo, {
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


    IPLATUI.EFGrid = {
        "result" : {
            pageable: {
                pageSize: 200,
                pageSizes: [ 200, 500, 1000]
            },
            height: function(){
                return ($("#main-container").height() - $("#inqu").height() - 115);
            },
            columns: [
                {
                    field: "operation",
                    title: "编辑",
                    encoded:false,
                    template: function(dateItem) {
                        return '<div style="text-align: center"><input value="编辑" class="k-button k-button-details" type="button" align="center" onclick="showDetail(\''+dateItem.rfId+'\')"/></div>';
                    }
                }
            ],
            beforeAdd: function (e) {
                e.preventDefault();
                var rfId = $("#rfId").val();
                var oprationType = "insert";
                var popWindow = $("#showDP");
                var url = IPLATUI.CONTEXT_PATH + "/web/DUHB5101?oprationType=" + oprationType + "&rfId=" + rfId;
                popWindow.data("kendoWindow").setOptions({
                    open: function () {
                        popWindow.data("kendoWindow").refresh({
                            url: url
                        });
                    },
                    lazyload: true,
                    minWidth: 1000,
                    minHeight: 500,
                    iframe: true
                });
                popWindow.data("kendoWindow").open().center();
            }
        }
    };

    //关闭弹窗的时候重新刷新一下页面的最新数据
    IPLATUI.EFWindow = {
        "showDP" : {
            close : function(e) {
                resultGrid.dataSource.page(1);
            }
        }
    };

    showDetail = function(rfId){

        var popWindow = $("#showDP");
        var oprationType = "update";
        var url = IPLATUI.CONTEXT_PATH + "/web/DUHB5101?oprationType=" + oprationType + "&rfId=" + rfId;
        popWindow.data("kendoWindow").setOptions({
            open: function () {
                popWindow.data("kendoWindow").refresh({
                    url: url
                });
            },
            lazyload: true,
            minWidth: 1000,
            minHeight: 500,
            iframe: true
        });
        popWindow.data("kendoWindow").open().center();

    };

});

