$(function () {
	$("#QUERY").on("click", function (e) {
	    resultGrid.dataSource.page(1);
	});
	
	IPLATUI.EFGrid = {
		"result" : {
			pageable: {
				pageSize: 200,
				pageSizes: [200, 500, 1000]
			},	height: function () {
				return ($("#main-container").height() - $("#inqu").height() - 115);
			},
			 onSave: function (e) {
				 e.preventDefault();
				 var checkRows = resultGrid.getCheckedRows();
				 if(checkRows.length == 0){
				 	NotificationUtil("请选择要保存的数据！" , "info");  
					return false;
				}else{
					var obj =  JSON.stringify(checkRows);
					var eiInfo = new EiInfo();
					eiInfo.set("obj" , obj);
					EiCommunicator.send("DUHB09", "saveInsert", eiInfo, {
			            onSuccess: function (ei) {
			            	if(ei.getStatus() == -1){
			            		NotificationUtil("保存失败" + ei.getMsg(), "error");  
			            	}else{
				            	NotificationUtil("保存成功！","success");
				        		resultGrid.dataSource.query();
			            	}
			            	
			            }, onFail: function () {
			            	// 发生异常
			            	NotificationUtil("保存失败" + ei.getMsg(), "error");  
			            }
			        });
				 
				}
				 
				 
			 }
						
        }
    };
	
	
	IPLATUI.EFSelect = {
        "inqu_status-0-monitorid": {
        	// 下拉选项改变之后触发
            change: function (e) { //获取改变值
            	var eiInfo = new EiInfo();
            	eiInfo.set("monitorid",this.value());
            	EiCommunicator.send("DUHB09","getFactor",eiInfo, {
                    onSuccess: function (ei) {
                    	if(ei["blocks"]["factor"] != null){
                    		var List = [{"valueField": "", "textField": "全部"}];
                    		$.each(ei["blocks"]["factor"]["rows"], function (i, obj) {
                    			List.push({"valueField": obj[0], "textField": obj[1]});
                            });
                            var dataSource = new kendo.data.DataSource({
                                data: List
                            });
                            IPLAT.EFSelect.setDataSource($("#inqu_status-0-factorid"), dataSource);
                    	};
                    }, onFail: function (ei) {
                    	// 发生异常
                    	NotificationUtil("加载数据出错" + ei.getMsg(), "error");  
                    }
                });
            }
        }
	}

});