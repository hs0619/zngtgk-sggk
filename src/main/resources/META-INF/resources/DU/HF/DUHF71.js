$(function() {
	
	$("#QUERY").on("click", function (e) {
		/*var beginDate=$("#inqu_status-0-beginDate").val();
		if(beginDate==null||beginDate==""){
			NotificationUtil("登记起始日期不能为空！", "warning");  
			return;
		}
		var endDate=$("#inqu_status-0-endDate").val();
		if(endDate==null||endDate==""){
			NotificationUtil("登记截至日期不能为空！", "warning");  
			return;
		}
		if(beginDate>endDate){
			NotificationUtil("登记截至日期不能早于登记起始日期！", "warning");  
			return;
		}*/
		resultGrid.dataSource.page(1);
		
	});
	
	//类型配置
	$("#TYPEDEPLOY").on("click", function(e) {
		var popWindow = $("#showHM");
		var url = IPLATUI.CONTEXT_PATH + "/web/DUHF7103";
		popWindow.data("kendoWindow").setOptions({
			open: function() {
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
	});
	
	IPLATUI.EFGrid = {
		"result": {
			pageable: {
				pageSize: 200,
				pageSizes: [ 200, 500, 1000]
			},
			height: function(){
				return ($("#main-container").height() - $("#inqu").height() - 115);
			},
			beforeAdd: function (e) {
				e.preventDefault();
				showDetail();
				
		    },
			columns: [
				{
					field: "operation",
					title: "编辑",
					encoded: false,
					width: 120,
					template: function(dateItem) {
						return '<div style="text-align: center"><input value="编辑" class="k-button k-button-details" type="button" align="center" onclick="edit(\''+dateItem.radiateid+'\')"/></div>';
					}
				},
			],
		}
	}
	showDetail = function() {
		//IPLAT.openForm("DUHF7401");
		var popWindow = $("#showHM");
		var url = IPLATUI.CONTEXT_PATH + "/web/DUHF7101";
		popWindow.data("kendoWindow").setOptions({
			open: function() {
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
	
	//编辑
	edit = function(radiateid) {
		//debugger;
		var ei = new EiInfo();
		ei.set("radiateid",radiateid);
		var url = "radiateid=" + radiateid;
		
		//IPLAT.openForm("DUHF6101",url);
		var popWindow = $("#showHM");
		var url = IPLATUI.CONTEXT_PATH + "/web/DUHF7101?radiateid="+radiateid;
		popWindow.data("kendoWindow").setOptions({
			open: function() {
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
    
    IPLATUI.EFWindow = {
        "showHM": {
            close: function (e) {
            	resultGrid.dataSource.page(1);
            	
            }
        },
        "showCheck": {
            close: function (e) {
            	resultGrid.dataSource.page(1);
            }
        }
    };
	
});
