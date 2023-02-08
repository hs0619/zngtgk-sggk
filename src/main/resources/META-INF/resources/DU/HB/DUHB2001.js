$(function () {
    IPLATUI.EFSelect = {
        "inqu_status-0-departmentid": {
            // 下拉选项改变之后触发
            change: function(e) { //获取改变值
                var eiInfo = new EiInfo();
                eiInfo.set("departmentid", this.value());
                EiCommunicator.send("DUHB2001", "getProcedureByDepartid", eiInfo, {
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
                            IPLAT.EFSelect.value($("#inqu_status-0-procedureid"), ei.get("inqu_status-0-departmentid"));
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
    $("#submitButton").on("click", function (e) {
    	var oprationType = $('#inqu_status-0-oprationType').val();
		if(oprationType == null || oprationType == "" || oprationType =="undefined"){
			NotificationUtil("参数出错，禁止保存", "error");  
			return;
		}
		IPLAT.confirm({
            message: '<b>确定保存废气处理设施信息吗？</b>',
            okFn: function (e) { 
            	var eiInfo = new EiInfo();
            	eiInfo.setByNode("inqu");
                EiCommunicator.send("DUHB2001", "save", eiInfo, {
                    onSuccess: function (ei) {
                    	if(ei.getStatus() == -1){
                    		NotificationUtil(ei.getMsg(), "error");  
                    	}else
                    	{
                        	NotificationUtil(ei.getMsg()); 
                        	window.parent['showFIWindow'].close();
                    	}
                    	
                    }, onFail: function () {
                    	// 发生异常
                    	NotificationUtil("保存数据出错" + ei.getMsg(), "error");  
                    }
                });
            },
            cancelFn: function (e) { IPLAT.NotificationUtil('取消该操作!'); },
            title: '操作提示',
            minWidth: 200
        });
	});
	 

});


