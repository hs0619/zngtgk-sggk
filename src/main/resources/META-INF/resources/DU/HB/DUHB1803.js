$(function() {
			
	$(document).ready(function(){ 
		// 设置选中第一行
	    resultGrid.setCheckedRows(0); 
	    var checkRow = resultGrid.getCheckedRows();
	    if(checkRow.length > 0){
	    	loadMsg(checkRow[0].dischargeportid);
	    }
	})
		
	IPLATUI.EFSelect = {
        "inqu_status-0-departid": {
        	// 下拉选项改变之后触发
            change: function (e) { //获取改变值
            	var eiInfo = new EiInfo();
            	eiInfo.set("departid",this.value());
            	EiCommunicator.send("DUHB1803","queryProcedureByDepart",eiInfo, {
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
        }
    }
	
	IPLATUI.EFGrid = {
		    "result": {
		    	pageable: false,
		    	exportGrid: false,
		    	showCount: false,
		        onRowClick: function (e) {
		        	loadMsg(e.model.dischargeportid);
		        }
			}
	};

	
	var validator = IPLAT.Validator({
		id : "inqu"
	});
	var eiInfo = new EiInfo();
	var oprationType = __eiInfo.get("inqu_status-0-oprationType");
	var initdischargeportid = __eiInfo.get("inqu_status-0-dischargeportid");
	eiInfo.set("oprationType",oprationType);
	eiInfo.set("dischargeportid",initdischargeportid);
	
    EiCommunicator.send("DUHB0501", "initSelectData", eiInfo, {
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
        	if(ei["blocks"]["monitorList"] != null){
        		var monitorList = [];
        		$.each(ei["blocks"]["monitorList"]["rows"], function (i, obj) {
        			monitorList.push({"valueField": obj[0], "textField": obj[1]});
                });
                var dataSource = new kendo.data.DataSource({
                    data: monitorList
                });
                IPLAT.EFSelect.setDataSource($("#inqu_status-0-monitorid"), dataSource);
                IPLAT.EFSelect.value($("#inqu_status-0-monitorid"),ei.get("inqu_status-0-monitorid"));
        	};
        	if(ei["blocks"]["formalList"] != null){
        		var formalList = [];
        		$.each(ei["blocks"]["formalList"]["rows"], function (i, obj) {
        			formalList.push({"valueField": obj[0], "textField": obj[1]});
                });
                var dataSource = new kendo.data.DataSource({
                    data: formalList
                });
                IPLAT.EFSelect.setDataSource($("#inqu_status-0-isformal"), dataSource);
                IPLAT.EFSelect.value($("#inqu_status-0-isformal"),ei.get("inqu_status-0-isformal"));
        	};
        	if(ei["blocks"]["flagList"] != null){
        		var flagList = [];
        		$.each(ei["blocks"]["flagList"]["rows"], function (i, obj) {
        			flagList.push({"valueField": obj[0], "textField": obj[1]});
                });
                var dataSource = new kendo.data.DataSource({
                    data: flagList
                });
                IPLAT.EFSelect.setDataSource($("#inqu_status-0-setright"), dataSource);
                IPLAT.EFSelect.value($("#inqu_status-0-setright"),ei.get("inqu_status-0-setright"));
                IPLAT.EFSelect.setDataSource($("#inqu_status-0-ismap"), dataSource);
                IPLAT.EFSelect.value($("#inqu_status-0-ismap"),ei.get("inqu_status-0-ismap"));
                
                IPLAT.EFSelect.setDataSource($("#inqu_status-0-countrypoint"), dataSource);
                IPLAT.EFSelect.value($("#inqu_status-0-countrypoint"),ei.get("inqu_status-0-countrypoint"));
                //IPLAT.EFSelect.setDataSource($("#inqu_status-0-citypoint"), dataSource);
                //IPLAT.EFSelect.value($("#inqu_status-0-citypoint"),ei.get("inqu_status-0-citypoint"));
                IPLAT.EFSelect.setDataSource($("#inqu_status-0-companypoint"), dataSource);
                IPLAT.EFSelect.value($("#inqu_status-0-companypoint"),ei.get("inqu_status-0-companypoint"));
                //IPLAT.EFSelect.setDataSource($("#inqu_status-0-cleanpoint"), dataSource);
                //IPLAT.EFSelect.value($("#inqu_status-0-cleanpoint"),ei.get("inqu_status-0-cleanpoint"));
                
                IPLAT.EFSelect.setDataSource($("#inqu_status-0-status"), dataSource);
                IPLAT.EFSelect.value($("#inqu_status-0-status"),ei.get("inqu_status-0-status"));
				IPLAT.EFSelect.setDataSource($("#inqu_status-0-signform"), dataSource);
                IPLAT.EFSelect.value($("#inqu_status-0-signform"),ei.get("inqu_status-0-signform"));
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
		if (!validator.validate()){
			NotificationUtil("信息校验不通过，禁止保存","error");
			return;
		}
		IPLAT.confirm({
            message: '<b>确定保存排口信息吗？</b>',
            okFn: function (e) { 
            	var eiInfo = new EiInfo();
            	eiInfo.setByNode("inqu");
                EiCommunicator.send("DUHB0501", "save", eiInfo, {
                    onSuccess: function (ei) {
                    	if(ei.getStatus() == -1){
                    		NotificationUtil(ei.getMsg(), "error");  
                    	}else
                    	{
                        	window.parent['showDCPWindow'].close();
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
//加载详细信息
function loadMsg(dischargeportid){
	var ei =new EiInfo();
	ei.set("dischargeportid",dischargeportid);
	EiCommunicator.send("DUHB1803", "loadMsg", ei, {
		onSuccess: function(ei) {
			var status = ei.get("status");
			if (status == 1) {
				
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
				
				$("#inqu_status-0-dischargeportcode").val(ei.get("dischargeportcode"));
				$("#inqu_status-0-dischargeportname").val(ei.get("dischargeportname"));
				IPLAT.EFSelect.value($("#inqu_status-0-dischargeClassify"),ei.get("dischargeClassify"));
				IPLAT.EFSelect.value($("#inqu_status-0-isformal"),ei.get("isformal"));
				IPLAT.EFSelect.value($("#inqu_status-0-status"),ei.get("statusDataBase"));
				IPLAT.EFSelect.value($("#inqu_status-0-monitorid"),ei.get("monitorid"));
				IPLAT.EFSelect.value($("#inqu_status-0-departid"),ei.get("departid"));
				IPLAT.EFSelect.value($("#inqu_status-0-procid"),ei.get("procid"));
				IPLAT.EFSelect.value($("#inqu_status-0-countrypoint"),ei.get("countrypoint"));
				IPLAT.EFSelect.value($("#inqu_status-0-companypoint"),ei.get("companypoint"));
				IPLAT.EFSelect.value($("#inqu_status-0-signform"),ei.get("signform"));
				
				$("#inqu_status-0-position").val(ei.get("position"));
				$("#inqu_status-0-longitude").val(ei.get("longitude"));
				$("#inqu_status-0-latitude").val(ei.get("latitude"));
				$("#inqu_status-0-dischargemode").val(ei.get("dischargemode"));
				$("#inqu_status-0-outto").val(ei.get("outto"));
				$("#inqu_status-0-outtype").val(ei.get("outtype"));
				$("#inqu_status-0-outrule").val(ei.get("outrule"));
				$("#inqu_status-0-exhaustheight").val(ei.get("exhaustheight"));
				$("#inqu_status-0-exhaustinside").val(ei.get("exhaustinside"));
				$("#inqu_status-0-portTemperature").val(ei.get("portTemperature"));
				$("#inqu_status-0-importantport").val(ei.get("importantport"));
				IPLAT.EFSelect.value($("#inqu_status-0-setright"),ei.get("setright"));
				IPLAT.EFSelect.value($("#inqu_status-0-ismap"),ei.get("ismap"));
				$("#inqu_status-0-executionstandard").val(ei.get("executionstandard"));
				$("#inqu_status-0-description").val(ei.get("description"));
				
				$("#inqu_status-0-dischargeportid").val(ei.get("dischargeportid"));
				$("#inqu_status-0-oprationType").val(ei.get("oprationType"));
				
				NotificationUtil(ei.get("message"));
			}else if(status == 2){
				NotificationUtil(ei.get("message"), "warning");
			}else{
				NotificationUtil(ei.get("message"), "error");
			}
		}, onFail: function(ei) {
			NotificationUtil(ei.getMsg(), "error");
		}
	});
}