$(function () {
	
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
	});
	
	
	
		
	IPLATUI.EFGrid = {
			"result" : {
				beforeAdd:function (e) {
					e.preventDefault();
					var popWindow = $("#insertOrUpdate");
					var operate = "insert";
					var url = IPLATUI.CONTEXT_PATH + "/web/DUHB2501?operate=" + operate;
		           	popWindow.data("kendoWindow").setOptions({
						open: function () {
		        			popWindow.data("kendoWindow").refresh({
				                url: url
				        	});
				        },
				        lazyload: true,
				        minWidth: 500,
				        minHeight: 300,
				        iframe: true
				    });
					popWindow.data("kendoWindow").open().center();
	        	},
	        	toolbarConfig: {
				    buttons: [ // 不允许覆盖默认的4个按钮，可以设置自定义的按钮
					    {
					        name: "modify",
					        text: "修改",
					        icon: "fa-edit",
					        layout: "3",
					        click: function() {
								updateInfoById();
					      	}
					    } 
				    ]
				}
	        	

            }//result内容结束
        	
    };
	
	
	
	
		IPLATUI.EFWindow = {
            "insertOrUpdate": {
                close: function (e) {
                	resultGrid.dataSource.page(1);
                }
            }
        };

	    	
});

//根据选中数据的报警人ID，修改信息
function updateInfoById(){
	var selectRows = resultGrid.getCheckedRows();
	var selectLength = selectRows.length;
	var popWindow = $("#insertOrUpdate");
	if(selectLength == 0){
		IPLAT.alert({
    		title: '系统提示',
    		message: '您还未选择要修改的记录！'
		});
		return;
	}else if(selectLength > 1){
		IPLAT.alert({
    		title: '系统提示',
    		message: '不能同时选择修改多条记录！'
		});
		return;
	}else{
		var inInfo = new EiInfo();
		var alarmPersonIdentity = selectRows[0].alarmPersonIdentity;
		var operate = "update";
		var url = IPLATUI.CONTEXT_PATH + "/web/DUHB2501?operate=" + operate + "&alarmPersonIdentity=" + alarmPersonIdentity;
       	popWindow.data("kendoWindow").setOptions({
			open: function () {
    			popWindow.data("kendoWindow").refresh({
	                url: url
	        	});
	        },
	        lazyload: true,
	        minWidth: 500,
	        minHeight: 300,
	        iframe: true
	    });
		popWindow.data("kendoWindow").open().center();
	
	}
	
	
		
}











