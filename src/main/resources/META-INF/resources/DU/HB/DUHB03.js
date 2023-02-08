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
	
	$("#CLEAR").on("click", function(e) {
		$("#inqu_status-0-parentid").val("");
		$("#inqu_status-0-departmentName").val("");

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
	                        return '<div style="text-align: center"><input value="编辑" class="k-button k-button-details" type="button" align="center" onclick="showDetail(\''+dateItem.departmentId+'\')"/></div>';
	                    }
	                }
	            ],
				beforeAdd: function (e) {
					e.preventDefault();
					var oprationType = "insert";
					var popWindow = $("#showDP");
					var parentid=IPLAT.EFInput.value("#inqu_status-0-parentid");
					var url = IPLATUI.CONTEXT_PATH + "/web/DUHB04?oprationType=" + oprationType+ "&parentid=" + parentid;;
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
					text : "长江钢铁",
					leaf : true,
					parent : "root"
				},
				select : function(e) {
					var _data = this.dataItem(e.node);
					if("root" == _data.parentid){
						$("#inqu_status-0-parentid").val(_data.id);
						resultGrid.dataSource.page(1);
					}
					
				}
			}
		};

});

showDetail = function(departmentId){
	
  	 var popWindow = $("#showDP");
  	 var oprationType = "update";
  	 var url = IPLATUI.CONTEXT_PATH + "/web/DUHB04?oprationType=" + oprationType + "&departmentId=" + departmentId;
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