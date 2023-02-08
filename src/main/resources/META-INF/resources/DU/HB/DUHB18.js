$(function() {
	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.page(1);
	});
	
	
	IPLATUI.EFGrid = {
			"result" : {
				pageable: false,
				/*pageable: {
					pageSize: 20,
					pageSizes: [10, 20, 50, 100]
	            },*/
				height: function(){
					return ($("#main-container").height() - $("#inqu").height() - 115);
				},
				columns: [
	            	{
	                    field: "operation",
	                    title: "编辑",
	                    encoded:false,
	                    template: function(dateItem) {
	                        return '<div style="text-align: center"><input value="编辑" class="k-button k-button-details" type="button" align="center" onclick="showDetailED(\''+dateItem.envdeviceid+'\')"/></div>';
	                    }
	                },
	                {
	                    field: "facilityinfo",
	                    title: "污染防治设施",
	                    encoded:false,
	                    template: function(dateItem) {
	                        return '<div style="text-align: center"><input value="污染防治设施" class="k-button k-button-details" type="button" align="center" onclick="showDetailFI(\''+dateItem.envdeviceid+'\')"/></div>';
	                    }
	                },
	                {
	                    field: "portinfo",
	                    title: "排放口信息",
	                    encoded:false,
	                    template: function(dateItem) {
	                        return '<div style="text-align: center"><input value="排放口信息" class="k-button k-button-details" type="button" align="center" onclick="showDetailDPI(\''+dateItem.envdeviceid+'\')"/></div>';
	                    }
	                }
	            ],
				beforeAdd: function (e) {
					e.preventDefault();
					var oprationType = "insert";
					var popWindow = $("#showED");
					var url = IPLATUI.CONTEXT_PATH + "/web/DUHB1801?oprationType=" + oprationType;
					popWindow.data("kendoWindow").setOptions({
					        open: function () {
					        	popWindow.data("kendoWindow").refresh({
					                url: url
					            });
					        },
					        lazyload: true,
					        minWidth: 1100,
					        minHeight: 700,
					        iframe: true
					    });
					popWindow.data("kendoWindow").open().center();
			    }
	        }
	    };
	
	IPLATUI.EFWindow = {
            "showED": {
                close: function (e) {
                	resultGrid.dataSource.page(1);
                	
                }
            }
        };

});

showDetailED = function(envdeviceid){
	
  	 var popWindow = $("#showED");
  	 var oprationType = "update";
  	 var url = IPLATUI.CONTEXT_PATH + "/web/DUHB1801?oprationType=" + oprationType + "&envdeviceid=" + envdeviceid;
  	 popWindow.data("kendoWindow").setOptions({
  	        open: function () {
  	        	popWindow.data("kendoWindow").refresh({
  	                url: url
  	            });
  	        },
  	        lazyload: true,
  	        minWidth: 1100,
  	        minHeight: 700,
  	        iframe: true
  	    });
  	 popWindow.data("kendoWindow").open().center();
	
};

//查看治理设施信息
showDetailFI = function(envdeviceid){
		var popWindow = $("#showFI");
		var url = IPLATUI.CONTEXT_PATH + "/web/DUHB1802?envdeviceid="+envdeviceid;
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
	
	
	
//查看排口信息	
showDetailDPI = function(envdeviceid){
		var popWindow = $("#showDCP");
		var url = IPLATUI.CONTEXT_PATH + "/web/DUHB1803?envdeviceid="+envdeviceid;
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



