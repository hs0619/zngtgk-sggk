$(function () {
	IPLATUI.EFSelect = {
	        "inqu_status-0-pfmonitorid": {
	        	// 下拉选项改变之后触发
	            change: function (e) { //获取改变值
	            	var eiInfo = new EiInfo();
	            	eiInfo.set("monitorid",this.value());
	            	EiCommunicator.send("DUHB0601","queryFactor",eiInfo, {
	                    onSuccess: function (ei) {
	                    	if(ei["blocks"]["pffactor"] != null){
	                    		var pffactor = [];
	                    		$.each(ei["blocks"]["pffactor"]["rows"], function (i, obj) {
	                    			pffactor.push({"valueField": obj[0], "textField": obj[1]});
	                            });
	                            var dataSource = new kendo.data.DataSource({
	                                data: pffactor
	                            });
	                            $("#inqu_status-0-pffactorid").data("kendoMultiSelect").setDataSource(dataSource);
	                            //IPLAT.EFSelect.setDataSource($("#inqu_status-0-pffactorid"), dataSource);
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
	var initpfportid = __eiInfo.get("inqu_status-0-pfportid");
	var initfactorid = __eiInfo.get("inqu_status-0-pffactorid");
	eiInfo.set("oprationType",oprationType);
    eiInfo.set("pfportid",initpfportid);
    eiInfo.set("factorid",initfactorid);
	
    EiCommunicator.send("DUHB0601", "initSelectData", eiInfo, {
        onSuccess: function (ei) {        	
        	if(ei["blocks"]["pfmonitor"] != null){
        		var pfmonitor = [];
        		$.each(ei["blocks"]["pfmonitor"]["rows"], function (i, obj) {
        			pfmonitor.push({"valueField": obj[0], "textField": obj[1]});
                });
                var dataSource = new kendo.data.DataSource({
                    data: pfmonitor
                });
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
                $("#inqu_status-0-pffactorid").data("kendoMultiSelect").setDataSource(dataSource);
                //IPLAT.EFSelect.value($("#inqu_status-0-pffactorid"),"");
        	};
        	if(ei["blocks"]["flagList"] != null){
        		var flagList = [];
        		$.each(ei["blocks"]["flagList"]["rows"], function (i, obj) {
        			flagList.push({"valueField": obj[0], "textField": obj[1]});
                });
                var dataSource = new kendo.data.DataSource({
                    data: flagList
                });
                IPLAT.EFSelect.setDataSource($("#inqu_status-0-status"), dataSource);
                IPLAT.EFSelect.value($("#inqu_status-0-status"),ei.get("inqu_status-0-status"));
        	};
        }, onFail: function (ei) {
        	// 发生异常
        	NotificationUtil("加载参数出错" + ei.getMsg(), "error");  
        }
    });
    
    $("#submitButton").on("click", function (e) {
    	var oprationType = $('#inqu_status-0-oprationType').val();
		if(oprationType == null || oprationType == "" || oprationType =="undefined"){
			NotificationUtil("参数出错，禁止保存", "error");  
			return;
		}
		var monitorid = IPLAT.EFSelect.value($("#inqu_status-0-pfmonitorid"));
		if(monitorid == null || monitorid.length == 0){
			NotificationUtil("监测类型不能为空!", "warning");
			return false;
		}
		var factorids = $("#inqu_status-0-pffactorid").data("kendoMultiSelect").value();
		if(factorids == null || factorids.length == 0){
			NotificationUtil("因子不能为空!", "warning");
			return false;
		}
		IPLAT.confirm({
            message: '<b>确定保存排口监测因子数据吗？</b>',
            okFn: function (e) { 
            	var eiInfo = new EiInfo();
            	eiInfo.setByNode("inqu");
                EiCommunicator.send("DUHB0601", "save", eiInfo, {
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


