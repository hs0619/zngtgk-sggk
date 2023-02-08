$(function () {
	
	$("#QUERY").on("click", function (e) {
		portfsGrid.dataSource.page(1);
		sitefsGrid.dataSource.page(1);
	});
   
	IPLATUI.EFTab = {
	        "info": {
	            show: function (e) {
	                $(e.contentElement).find("div[data-role='grid']").data("kendoGrid").refresh();
	            }
	        }
	    };
	
	IPLATUI.EFGrid = {
			"portfs" : {
				beforeAdd: function (e) {
					e.preventDefault();
					var portid = $("#inqu_status-0-dischargeportid").val();
					if(portid == null || portid == "undefined" || portid == ""){
						NotificationUtil("排口信息出错", "error");  
						return;
					}
					var oprationType = "insert";
					var popWindow = $("#showPF");
					var url = IPLATUI.CONTEXT_PATH + "/web/DUHB1501?oprationType=" + oprationType + "&portid=" + portid;
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
			    },
			    onRowDblClick: function (e) {
			    	var portid = $("#inqu_status-0-dischargeportid").val();
					if(portid == null || portid == "undefined" || portid == ""){
						NotificationUtil("排口信息出错", "error");  
						return;
					}
                   	 var factorid = e.model.factorid;
                   	 var popWindow = $("#showPF");
                   	 var oprationType = "update";
                   	 var url = IPLATUI.CONTEXT_PATH + "/web/DUHB1501?oprationType=" + oprationType + "&portid=" + portid + "&factorid=" + factorid;
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
            "showPF": {
                close: function (e) {
                	portfsGrid.dataSource.page(1);
                }
            }
        };
});