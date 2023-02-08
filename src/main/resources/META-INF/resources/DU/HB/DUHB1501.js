$(function () {
	IPLATUI.EFSelect = {
	        "inqu_status-0-pfmonitorid": {
	        	// 下拉选项改变之后触发
	            change: function (e) { //获取改变值
	            	var eiInfo = new EiInfo();
	            	eiInfo.set("monitorid",this.value());
	            	EiCommunicator.send("DUHB1501","queryFactor",eiInfo, {
	                    onSuccess: function (ei) {
	                    	if(ei["blocks"]["pffactor"] != null){
	                    		var pffactor = [];
	                    		$.each(ei["blocks"]["pffactor"]["rows"], function (i, obj) {
	                    			pffactor.push({"valueField": obj[0], "textField": obj[1]});
	                            });
	                            var dataSource = new kendo.data.DataSource({
	                                data: pffactor
	                            });
	                            IPLAT.EFSelect.setDataSource($("#inqu_status-0-pffactorid"), dataSource);
	                    	};
	                    }, onFail: function (ei) {
	                    	// 发生异常
	                    	NotificationUtil("加载数据出错" + ei.getMsg(), "error");  
	                        console.log(ei);
	                    }
	                });
	            }
	        }
	    }
	
	var eiInfo = new EiInfo();
	var oprationType = __eiInfo.get("inqu_status-0-oprationType");
	if("update" == oprationType){
		$("#inqu_status-0-pfmonitorid").attr("disabled",true);
		$("#inqu_status-0-pffactorid").attr("disabled",true);
	}
	var initmonitorid = __eiInfo.get("inqu_status-0-pfmonitorid");
	var initfactorid = __eiInfo.get("inqu_status-0-pffactorid");
	eiInfo.set("oprationType",oprationType);
    eiInfo.set("monitorid",initmonitorid);
    eiInfo.set("factorid",initfactorid);
	
    EiCommunicator.send("DUHB1501", "initSelectData", eiInfo, {
        onSuccess: function (ei) {        	
        	if(ei["blocks"]["pfmonitor"] != null){
        		var pfmonitor = [];
        		$.each(ei["blocks"]["pfmonitor"]["rows"], function (i, obj) {
        			pfmonitor.push({"valueField": obj[0], "textField": obj[1]});
                });
                var dataSource = new kendo.data.DataSource({
                    data: pfmonitor
                });
                //$("#inqu_status-0-pfmonitor").data("kendoDropDownList").setDataSource(dataSource);
                IPLAT.EFSelect.setDataSource($("#inqu_status-0-pfmonitorid"), dataSource);
                IPLAT.EFSelect.value($("#inqu_status-0-pfmonitorid"),ei.get("inqu_status-0-pfmonitorid"));
        	};
        	if(ei["blocks"]["pffactor"] != null){
        		var pffactor = [];
        		$.each(ei["blocks"]["pffactor"]["rows"], function (i, obj) {
        			pffactor.push({"valueField": obj[0], "textField": obj[1]});
                });
                var dataSource = new kendo.data.DataSource({
                    data: pffactor
                });
                IPLAT.EFSelect.setDataSource($("#inqu_status-0-pffactorid"), dataSource);
                IPLAT.EFSelect.value($("#inqu_status-0-pffactorid"),ei.get("inqu_status-0-pffactorid"));
        	};
        }, onFail: function (ei) {
        	// 发生异常
        	NotificationUtil("加载参数出错" + ei.getMsg(), "error");  
            console.log(ei);
        }
    });
    
    $("#submitButton").on("click", function (e) {
    	var oprationType = $('#inqu_status-0-oprationType').val();
		if(oprationType == null || oprationType == "" || oprationType =="undefined"){
			NotificationUtil("参数出错，禁止保存", "error");  
			return;
		}
		IPLAT.confirm({
            message: '<b>确定保存排口监测因子数据吗？</b>',
            okFn: function (e) { 
            	var eiInfo = new EiInfo();
            	eiInfo.setByNode("inqu");
                EiCommunicator.send("DUHB1501", "save", eiInfo, {
                    onSuccess: function (ei) {
                    	if(ei.getStatus() == -1){
                    		NotificationUtil(ei.getMsg(), "error");  
                    	}else
                    	{
                        	NotificationUtil(ei.getMsg()); 
                        	window.parent['showPFWindow'].close();
                    	}
                    	
                    }, onFail: function () {
                    	// 发生异常
                    	NotificationUtil("保存数据出错" + ei.getMsg(), "error");  
                        console.log(ei);
                    }
                });
            },
            cancelFn: function (e) { IPLAT.NotificationUtil('取消该操作!'); },
            title: '操作提示',
            minWidth: 200
        });
	});
});


