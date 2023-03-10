$(function() {
	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.page(1);
		var departmentTree = $("#departmentTree").data("kendoTreeView");
        var node = departmentTree.dataSource.get("root");
        // 重新设置flag , 这样才能重新使其到服务器获取数据
        if (node) {
            node.loaded(false);
            // 重新加载
            node.load();
        }

	});
	IPLATUI.EFWindow = {
            "showDP": {
                close: function (e) {
                	resultGrid.dataSource.page(1);
                	var departmentTree = $("#departmentTree").data("kendoTreeView");
                    var node = departmentTree.dataSource.get("root");
                    // 重新设置flag , 这样才能重新使其到服务器获取数据
                    if (node) {
                        node.loaded(false);
                        // 重新加载
                        node.load();
                    }
                }
            }
        };
	IPLATUI.EFTree = {
			"departmentTree" : {
				ROOT : {
					label : "root",
					text : "马钢(合肥)板材公司",
					leaf : true,
					parent : "root"
				},
				select : function(e) {
					var _data = this.dataItem(e.node);
					$("#inqu_status-0-departmentId").val(_data.id);
					resultGrid.dataSource.page(1);
				}
			}
		};
	IPLATUI.EFGrid = {
			"result" : {
				beforeAdd: function (e) {
					e.preventDefault();
					var oprationType = "insert";
					var departmentId = $("#inqu_status-0-departmentId").val();
					showDetail(departmentId);
			    }
			    /*,
				columns: [
	            	{
	                    field: "operation",
	                    title: "编辑",
	                    encoded:false,
	                    template: function(dateItem) {
	                        return '<div style="text-align: center"><input value="编辑" class="k-button k-button-details" type="button" align="center" onclick="showDetail(\''+dateItem.departmentId+'\')"/></div>';
	                    }
	                }
	            ]*/
	        }
	    };
	    showDetail = function(departmentId) {
		//IPLAT.openForm("DUHF7301");
		var popWindow = $("#showDP");
		var url = IPLATUI.CONTEXT_PATH + "/web/DUHB2802?departmentId="+departmentId;
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
	$("#CLEAR").on("click", function(e) {
		$("#inqu_status-0-userId").val("");
		

	});
});