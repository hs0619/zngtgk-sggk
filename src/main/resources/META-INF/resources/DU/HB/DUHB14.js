$(function () {
	$("#QUERY").on("click", function (e) {
	    resultGrid.dataSource.page(1);
	});
	
	IPLATUI.EFGrid = {
	        "result" : {
	        	         onRowDblClick: function (e) {
	                    	 var temdischargeportid = e.model.dischargeportid;
	                    	 var popWindow = $("#showSF");
	                    	 var url = IPLATUI.CONTEXT_PATH + "/web/DUHB15?dischargeportid=" + temdischargeportid;
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
	            "showSF": {
	                close: function (e) {
	                	resultGrid.dataSource.page(1);
	                }
	            }
	        };
	    
});