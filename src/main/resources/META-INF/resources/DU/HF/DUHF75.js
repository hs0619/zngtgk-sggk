$(function() {
    $("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
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
						return '<div style="text-align: center"><input value="编辑" class="k-button k-button-details" type="button" align="center" onclick="edit(\''+dateItem.logicid+'\')"/></div>';
					}
				},
				
				/*{
					field: "jiliangedit",
					title: "剂量管理",
					encoded: false,
					width: 120,
					template: function(dateItem) {
						return '<div style="text-align: center"><input value="剂量管理" class="k-button k-button-details" type="button" align="center" onclick="jiliang(\''+dateItem.ename+'\',\''+dateItem.name+'\',\''+dateItem.departmentid+'\')"/></div>';
					}
				},*/
			],
		}
	}
	showDetail = function() {
		var popWindow = $("#showHM");
		var url = IPLATUI.CONTEXT_PATH + "/web/DUHF7501";
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
	edit = function(logicid) {
		var popWindow = $("#showHM");
		var url = IPLATUI.CONTEXT_PATH + "/web/DUHF7501?logicid="+logicid;
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
	
	//剂量管理
	jiliang = function(ename,name,departmentid) {
		var popWindow = $("#showHM");
		var url = IPLATUI.CONTEXT_PATH + "/web/DUHF90?ename="+ename+"&name="+name+"&departmentid="+departmentid;
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
    };
});
