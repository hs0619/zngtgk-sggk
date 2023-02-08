$(function() {
	IPLATUI.EFSelect = {
        "inqu_status-0-departmentid": {
            // 下拉选项改变之后触发
            change: function(e) { //获取改变值
                var eiInfo = new EiInfo();
                eiInfo.set("departmentid", this.value());
                EiCommunicator.send("DUHB0604", "getProcedureByDepartid", eiInfo, {
                    onSuccess: function(ei) {
                        if (ei["blocks"]["procedureList"] != null) {
                            var procedure = [];
                            $.each(ei["blocks"]["procedureList"]["rows"], function(i, obj) {
                                procedure.push({ "valueField": obj[1], "textField": obj[0] });
                            });
                            var dataSource = new kendo.data.DataSource({
                                data: procedure
                            });
                            IPLAT.EFSelect.setDataSource($("#inqu_status-0-procedureid"), dataSource);
                        };
                    }, onFail: function(ei) {
                        // 发生异常
                        NotificationUtil("加载数据出错" + ei.getMsg(), "error");
                        console.log(ei);
                    }
                });
            }
        }
    }
	
	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.page(1);
	});
	
	
	IPLATUI.EFGrid = {
			"result" : {
				beforeAdd: function (e) {
					e.preventDefault();
					var selectRows = resultGrid.getCheckedRows();
					var selectLength = selectRows.length;
					var facilityids = "";
					var portid = __eiInfo.get("portid");
					if(selectLength == 0){
						IPLAT.alert({
				    		title: '系统提示',
				    		message: '请选择要关联的设施！'
						});
						return ;
					}else{
						for(var i = 0; i <selectLength; i++){
							if(i == selectLength - 1){
								facilityids += selectRows[i].facilityid;
							}else{
								facilityids += selectRows[i].facilityid + ";";
							}
							
						}
						
						var ei = new EiInfo();
			        	ei.set("portid", portid);
			        	ei.set("facilityids", facilityids);
						
						EiCommunicator.send("DUHB0603", "addEquipment", ei, {
					        onSuccess: function (ei) {
					        	if(ei.getStatus() == 1){
						        	IPLAT.alert({
							    		title: '系统提示',
							    		message: '添加成功！',
							    		okFn: function(e){
											window.parent['showFQWindow'].close();
										}
							    		
									});
					        	}else if(ei.getStatus() == -2){
					        		IPLAT.alert({
							    		title: '错误提示',
							    		message: '添加失败，该设施已存在！'
									});
					        	}
					        }, onFail: function () {
					        	// 发生异常
					        	NotificationUtil("数据出错" + ei.getMsg(), "error");  
					        }
					    });
						
						
					
					}
					
					
					
					
			    }
	        }
	    };
	

});
