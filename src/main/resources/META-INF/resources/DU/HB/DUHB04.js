$(function () {
	
	var validator = IPLAT.Validator({
		id : "inqu"
	});
	var eiInfo = new EiInfo();
	var oprationType = __eiInfo.get("inqu_status-0-oprationType");
	var initdepartmentId = __eiInfo.get("inqu_status-0-departmentId"); 
	var parentid=__eiInfo.get("inqu_status-0-parentid"); 
	eiInfo.set("oprationType",oprationType);
	eiInfo.set("departmentId",initdepartmentId);
	eiInfo.set("parentid",parentid);
	
    EiCommunicator.send("DUHB04", "initSelectData", eiInfo, {
        onSuccess: function (ei) {  
        	if(ei["blocks"]["typeList"] != null){
        		var typeList = [];
        		$.each(ei["blocks"]["typeList"]["rows"], function (i, obj) {
        			typeList.push({"valueField": obj[0], "textField": obj[1]});
                });
                var dataSource = new kendo.data.DataSource({
                    data: typeList
                });
                IPLAT.EFSelect.setDataSource($("#inqu_status-0-type"), dataSource);
                IPLAT.EFSelect.value($("#inqu_status-0-type"),ei.get("inqu_status-0-type"));
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
        	if(ei["blocks"]["departList"] != null){
        		var departList = [];
        		$.each(ei["blocks"]["departList"]["rows"], function (i, obj) {
        			departList.push({"valueField": obj[0], "textField": obj[1]});
                });
                var dataSource = new kendo.data.DataSource({
                    data: departList
                });
                IPLAT.EFSelect.setDataSource($("#inqu_status-0-parentid"), dataSource);
                IPLAT.EFSelect.value($("#inqu_status-0-parentid"),ei.get("inqu_status-0-parentid"));
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
            message: '<b>确定保存工序信息吗？</b>',
            okFn: function (e) { 
            	var eiInfo = new EiInfo();
            	eiInfo.setByNode("inqu");
                EiCommunicator.send("DUHB04", "save", eiInfo, {
                    onSuccess: function (ei) {
                    	if(ei.getStatus() == -1){
                    		NotificationUtil(ei.getMsg(), "error");  
                    	}else
                    	{
                        	NotificationUtil(ei.getMsg()); 
                        	window.parent['showDPWindow'].close();
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


