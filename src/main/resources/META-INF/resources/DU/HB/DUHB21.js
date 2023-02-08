$(function() {
	IPLATUI.EFSelect = {
        "inqu_status-0-departmentid": {
            // 下拉选项改变之后触发
            change: function(e) { //获取改变值
                var eiInfo = new EiInfo();
                eiInfo.set("departmentid", this.value());
                EiCommunicator.send("DUHB21", "getProcedureByDepartid", eiInfo, {
                    onSuccess: function(ei) {
                        if (ei["blocks"]["procedureList"] != null) {
                            var procedure = [];
                            $.each(ei["blocks"]["procedureList"]["rows"], function(i, obj) {
                                procedure.push({ "valueField": obj[1], "textField": obj[0] });
                            });
                            var dataSource = new kendo.data.DataSource({
                                data: procedure
                            });
                            IPLAT.EFSelect.setDataSource($("#inqu_status-0-procedureid"), dataSource);
                        };
                    }, onFail: function(ei) {
                        // 发生异常
                        NotificationUtil("加载数据出错" + ei.getMsg(), "error");
                        console.log(ei);
                    }
                });
            }
        }
    }

	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.page(1);
	});
	
	
	IPLATUI.EFGrid = {
			"result" : {
				pageable: {
					pageSize: 500,
					pageSizes: [100, 200, 500, 1000]
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
	                        return '<div style="text-align: center"><input value="编辑" class="k-button k-button-details" type="button" align="center" onclick="showDetail(\''+dateItem.facilityid+'\',\''+dateItem.departmentid+'\')"/></div>';
	                    }
	                },
	                {	
	                    field: "deviceInfo",
	                    title: "产污设施",
	                    encoded:false,
	                    template: function(dateItem) {
	                        return '<div style="text-align: center"><input value="产污设施" class="k-button k-button-details" type="button" align="center" onclick="showSheBeiInfo(\''+dateItem.facilityid+'\')"/></div>';
	                    }
	                },
	            ],
				beforeAdd: function (e) {
					e.preventDefault();
					var oprationType = "insert";
					var popWindow = $("#showFI");
					var url = IPLATUI.CONTEXT_PATH + "/web/DUHB2101?oprationType=" + oprationType;
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
	
	IPLATUI.EFWindow = {
            "showFI": {
                close: function (e) {
                	resultGrid.dataSource.page(1);
                }
            }
        };

});

showDetail = function(facilityid,departmentid){
	
  	 var popWindow = $("#showFI");
  	 var oprationType = "update";
  	 var url = IPLATUI.CONTEXT_PATH + "/web/DUHB2101?oprationType=" + oprationType + "&facilityid=" + facilityid+ "&departmentid=" + departmentid;
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


//展示产排污设备信息，
function showSheBeiInfo(facilityid){
	 var popWindow = $("#showSBI");
  	 var url = IPLATUI.CONTEXT_PATH + "/web/DUHB2102?facilityid=" + facilityid;
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