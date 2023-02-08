$(function() {
	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.page(1);
	});
	
	IPLATUI.EFSelect = {
        "inqu_status-0-departid": {
        	// 下拉选项改变之后触发
            change: function (e) { //获取改变值
            	var departmentId = this.value();
            	//根据选中的厂部id,去查询对应的工序列表列表
            	var eiInfo = new EiInfo();
            	eiInfo.set("departmentId",departmentId);
            	EiCommunicator.send("DUHB05","queryProcByDepid",eiInfo, {
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
                            IPLAT.EFSelect.setDataSource($("#inqu_status-0-procid"), dataSource);
                            
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
	                    title: "操作",
	                    encoded:false,
	                    template: function(dateItem) {
	                        return '<div style="text-align: center">'+
	                        	'<input value="编辑" class="k-button k-button-details" type="button" align="center" onclick="showDetail(\''+dateItem.dischargeportid+'\')"/>'+
	                        	'<input value="配置信息" class="k-button k-button-configs" type="button" align="center" onclick="showConfig(\''+dateItem.dischargeportid+'\')"/>'+
	                        	'</div>';
	                        
	                    }
	                }
	            ],
				beforeAdd: function (e) {
					e.preventDefault();
					var oprationType = "insert";
					var popWindow = $("#showDCP");
					var url = IPLATUI.CONTEXT_PATH + "/web/DUHB0501?oprationType=" + oprationType;
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
            "showDCP": {
                close: function (e) {
                	resultGrid.dataSource.page(1);
                }
            },
            "showSF": {
                close: function (e) {
                	resultGrid.dataSource.page(1);
                }
            }
        };

});

showDetail = function(dischargeportid) {

	var popWindow = $("#showDCP");
	var oprationType = "update";
	var url = IPLATUI.CONTEXT_PATH + "/web/DUHB0501?oprationType="
			+ oprationType + "&dischargeportid=" + dischargeportid;
	popWindow.data("kendoWindow").setOptions({
		open : function() {
			popWindow.data("kendoWindow").refresh({
				url : url
			});
		},
		lazyload : true,
		minWidth : 1000,
		minHeight : 500,
		iframe : true
	});
	popWindow.data("kendoWindow").open().center();

};
showConfig = function(dischargeportid) {
	var popWindow = $("#showSF");
	var url = IPLATUI.CONTEXT_PATH + "/web/DUHB06?dischargeportid="
			+ dischargeportid;
	popWindow.data("kendoWindow").setOptions({
		open : function() {
			popWindow.data("kendoWindow").refresh({
				url : url
			});
		},
		lazyload : true,
		minWidth : 1000,
		minHeight : 500,
		iframe : true
	});
	popWindow.data("kendoWindow").open().center();
};