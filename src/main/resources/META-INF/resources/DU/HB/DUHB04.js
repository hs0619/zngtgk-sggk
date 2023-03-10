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
        	// ????????????
        	NotificationUtil("??????????????????" + ei.getMsg(), "error");  
        }
    });

    $("#submitButton").on("click", function (e) {
    	var oprationType = $('#inqu_status-0-oprationType').val();
		if(oprationType == null || oprationType == "" || oprationType =="undefined"){
			NotificationUtil("???????????????????????????", "error");  
			return;
		}
		if (!validator.validate()){
			NotificationUtil("????????????????????????????????????","error");
			return;
		}
		IPLAT.confirm({
            message: '<b>??????????????????????????????</b>',
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
                    	// ????????????
                    	NotificationUtil("??????????????????" + ei.getMsg(), "error");  
                    }
                });
            },
            cancelFn: function (e) { IPLAT.NotificationUtil('???????????????!'); },
            title: '????????????',
            minWidth: 200
        });
	});
	 

});


