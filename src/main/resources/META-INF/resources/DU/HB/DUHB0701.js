$(function () {
	
	IPLATUI.EFSelect = {
	        "inqu_status-0-departid": {
	        	// 下拉选项改变之后触发
	            change: function (e) { //获取改变值
	            	IPLAT.EFSelect.value($("#inqu_status-0-procid"),"");
	            	var eiInfo = new EiInfo();
	            	eiInfo.set("departid",this.value());
	            	EiCommunicator.send("DUHB0701","queryProcedureByDepart",eiInfo, {
	                    onSuccess: function (ei) {
	                    	if(ei["blocks"]["procedureList"] != null){
	                    		var procedureList = [];
	                    		$.each(ei["blocks"]["procedureList"]["rows"], function (i, obj) {
	                    			procedureList.push({"valueField": obj[0], "textField": obj[1]});
	                            });
	                            var dataSource = new kendo.data.DataSource({
	                                data: procedureList
	                            });
	                            IPLAT.EFSelect.setDataSource($("#inqu_status-0-procid"), dataSource);
	                    	};
	                    }, onFail: function (ei) {
	                    	// 发生异常
	                    	NotificationUtil("加载数据出错" + ei.getMsg(), "error");  
	                    }
	                });
	            }
	        },
	        "inqu_status-0-procid": {
	        	// 下拉选项改变之后触发
	            change: function (e) { //获取改变值
	            	IPLAT.EFSelect.value($("#inqu_status-0-portid"),"");
	            	var eiInfo = new EiInfo();
	            	eiInfo.set("procid",this.value());
	            	EiCommunicator.send("DUHB0701","queryPortByProcedure",eiInfo, {
	                    onSuccess: function (ei) {
	                    	if(ei["blocks"]["portList"] != null){
	                    		var portList = [];
	                    		$.each(ei["blocks"]["portList"]["rows"], function (i, obj) {
	                    			portList.push({"valueField": obj[0], "textField": obj[1]});
	                            });
	                            var dataSource = new kendo.data.DataSource({
	                                data: portList
	                            });
	                            IPLAT.EFSelect.setDataSource($("#inqu_status-0-portid"), dataSource);
	                    	};
	                    }, onFail: function (ei) {
	                    	// 发生异常
	                    	NotificationUtil("加载数据出错" + ei.getMsg(), "error");  
	                    }
	                });
	            }
	        },
	        "inqu_status-0-portid": {
	        	// 下拉选项改变之后触发
	            change: function (e) { //获取改变值
	            	var eiInfo = new EiInfo();
	            	eiInfo.set("portid",this.value());
	            	EiCommunicator.send("DUHB0701","queryPortTypeById",eiInfo, {
	                    onSuccess: function (ei) {
	                    	var portType = ei.get("portType");
	                    	IPLAT.EFSelect.value($("#inqu_status-0-classifyid"),portType);
	                    	IPLAT.EFSelect.value($("#inqu_status-0-monitorid"),portType);
	                    }, onFail: function (ei) {
	                    	// 发生异常
	                    	NotificationUtil("加载数据出错" + ei.getMsg(), "error");  
	                    }
	                });
	            }
	        }
	        
	        
	    }

	var validator = IPLAT.Validator({
		id : "inqu"
	});
	var eiInfo = new EiInfo();
	var oprationType = __eiInfo.get("inqu_status-0-oprationType");
	var initsiteid = __eiInfo.get("inqu_status-0-siteid");
	eiInfo.set("oprationType",oprationType);
	eiInfo.set("siteid",initsiteid);
	
    EiCommunicator.send("DUHB0701", "initSelectData", eiInfo, {
        onSuccess: function (ei) {  
        	if(ei["blocks"]["departList"] != null){
        		var departList = [];
        		$.each(ei["blocks"]["departList"]["rows"], function (i, obj) {
        			departList.push({"valueField": obj[0], "textField": obj[1]});
                });
                var dataSource = new kendo.data.DataSource({
                    data: departList
                });
                IPLAT.EFSelect.setDataSource($("#inqu_status-0-departid"), dataSource);
                IPLAT.EFSelect.value($("#inqu_status-0-departid"),ei.get("inqu_status-0-departid"));
        	};
        	if(ei["blocks"]["procedureList"] != null){
        		var procedureList = [];
        		$.each(ei["blocks"]["procedureList"]["rows"], function (i, obj) {
        			procedureList.push({"valueField": obj[0], "textField": obj[1]});
                });
                var dataSource = new kendo.data.DataSource({
                    data: procedureList
                });
                IPLAT.EFSelect.setDataSource($("#inqu_status-0-procid"), dataSource);
                IPLAT.EFSelect.value($("#inqu_status-0-procid"),ei.get("inqu_status-0-procid"));
        	};
        	if(ei["blocks"]["portList"] != null){
        		var portList = [];
        		$.each(ei["blocks"]["portList"]["rows"], function (i, obj) {
        			portList.push({"valueField": obj[0], "textField": obj[1]});
                });
                var dataSource = new kendo.data.DataSource({
                    data: portList
                });
                IPLAT.EFSelect.setDataSource($("#inqu_status-0-portid"), dataSource);
                IPLAT.EFSelect.value($("#inqu_status-0-portid"),ei.get("inqu_status-0-portid"));
        	};
        	if(ei["blocks"]["monitorList"] != null){
        		var monitorList = [];
        		$.each(ei["blocks"]["monitorList"]["rows"], function (i, obj) {
        			monitorList.push({"valueField": obj[0], "textField": obj[1]});
                });
                var dataSource = new kendo.data.DataSource({
                    data: monitorList
                });
                IPLAT.EFSelect.setDataSource($("#inqu_status-0-classifyid"), dataSource);
                IPLAT.EFSelect.value($("#inqu_status-0-classifyid"),ei.get("inqu_status-0-classifyid"));
                IPLAT.EFSelect.setDataSource($("#inqu_status-0-monitorid"), dataSource);
                IPLAT.EFSelect.value($("#inqu_status-0-monitorid"),ei.get("inqu_status-0-monitorid"));
        	};
        	if(ei["blocks"]["flagList"] != null){
        		var flagList = [];
        		$.each(ei["blocks"]["flagList"]["rows"], function (i, obj) {
        			flagList.push({"valueField": obj[0], "textField": obj[1]});
                });
                var dataSource = new kendo.data.DataSource({
                    data: flagList
                });
                IPLAT.EFSelect.setDataSource($("#inqu_status-0-isartificial"), dataSource);
                IPLAT.EFSelect.value($("#inqu_status-0-isartificial"),ei.get("inqu_status-0-isartificial"));
                IPLAT.EFSelect.setDataSource($("#inqu_status-0-isonline"), dataSource);
                IPLAT.EFSelect.value($("#inqu_status-0-isonline"),ei.get("inqu_status-0-isonline"));
                IPLAT.EFSelect.setDataSource($("#inqu_status-0-status"), dataSource);
                IPLAT.EFSelect.value($("#inqu_status-0-status"),ei.get("inqu_status-0-status"));
        	};
        }, onFail: function (ei) {
        	// 发生异常
        	NotificationUtil("加载参数出错" + ei.getMsg(), "error");  
        }
    });
    
    if(oprationType == "update"){
    	$('#inqu_status-0-departid').attr("disabled",true);
    	$('#inqu_status-0-procid').attr("disabled",true);
    	$('#inqu_status-0-portid').attr("disabled",true);
    	$('#inqu_status-0-classifyid').attr("disabled",true);
    	$('#inqu_status-0-monitorid').attr("disabled",true);
    }else if(oprationType == "insert"){
    	$('#inqu_status-0-classifyid').attr("disabled",true);
    	$('#inqu_status-0-monitorid').attr("disabled",true);
    }
    
    	
    
    $("#submitButton").on("click", function (e) {
    	var oprationType = $('#inqu_status-0-oprationType').val();
		if(oprationType == null || oprationType == "" || oprationType =="undefined"){
			NotificationUtil("参数出错，禁止保存", "error");  
			return;
		}
		if (!validator.validate()){
			NotificationUtil("请将带*标识的必填选项填写完整","error");
			return;
		}
		IPLAT.confirm({
            message: '<b>确定保存监测点信息吗？</b>',
            okFn: function (e) { 
            	var eiInfo = new EiInfo();
            	eiInfo.setByNode("inqu");
                EiCommunicator.send("DUHB0701", "save", eiInfo, {
                    onSuccess: function (ei) {
                    	if(ei.getStatus() == -1){
                    		NotificationUtil(ei.getMsg(), "error");  
                    	}else
                    	{
                        	NotificationUtil(ei.getMsg()); 
                        	window.parent['showSIWindow'].close();
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


