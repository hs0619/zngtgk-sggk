$(function () {
	
	$(document).ready(function(){ 
		// 设置选中第一行
	    envdevicGrid.setCheckedRows(0);
	})

		IPLATUI.EFGrid = {
		    "envdevic": {
		    	pageable: false,
		    	exportGrid: false,
		    	showCount: false,
		    	toolbarConfig: {
		    	buttons:[
	    			{
	    				name: "addInfo",
	    				text: "添加设施关联",
	    				click: function () {
	    					addInfo();
	    				}
	    				
	    			},
	    			{
	    				name: "remove",
	    				text: "取消设施关联",
	    				click: function () {
	    					removeEnvdevic();
	    				}
	    				
	    			}
		    	]	
	    	},
	    	onCheckRow: function (e) {
	        	loadMsg(e.model.envdeviceid);

	        }
		}
	};
	
	
	IPLATUI.EFWindow = {
        "showEnvdevic": {
            close: function (e) {
            	window.location.reload();
            }
        }
    };


    $("#submitButton").on("click", function (e) {
    	var oprationType = $('#inqu_status-0-oprationType').val();
		if(oprationType == null || oprationType == "" || oprationType =="undefined"){
			NotificationUtil("参数出错，禁止保存", "error");  
			return;
		}
		IPLAT.confirm({
            message: '<b>确定保存废气产排污节点信息吗？</b>',
            okFn: function (e) { 
            	var eiInfo = new EiInfo();
            	eiInfo.setByNode("inqu");
                EiCommunicator.send("DUHB1801", "save", eiInfo, {
                    onSuccess: function (ei) {
                    	if(ei.getStatus() == -1){
                    		NotificationUtil(ei.getMsg(), "error");  
                    	}else
                    	{
                        	NotificationUtil(ei.getMsg()); 
                        	window.parent['showEDWindow'].close();
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
function loadMsg(envdeviceid){
	var ei =new EiInfo();
	ei.set("envdeviceid",envdeviceid);
	EiCommunicator.send("DUHB2102", "loadMsg", ei, {
		onSuccess: function(ei) {
			var status = ei.getStatus();
			if (status == 1) {
				$("#inqu_status-0-deviceid").val(ei.get("deviceid"));
				$("#inqu_status-0-devicename").val(ei.get("devicename"));
				$("#inqu_status-0-sourcename").val(ei.get("sourcename"));
				$("#inqu_status-0-outtype").val(ei.get("outtype"));
				$("#inqu_status-0-factorname").val(ei.get("factorname"));
				$("#inqu_status-0-portid").val(ei.get("portid"));
				$("#inqu_status-0-portname").val(ei.get("portname"));
				$("#inqu_status-0-other").val(ei.get("other"));
				
				$("#inqu_status-0-outto").val(ei.get("outto"));
				$("#inqu_status-0-portouttype").val(ei.get("portouttype"));
				$("#inqu_status-0-outrule").val(ei.get("outrule"));
				
				NotificationUtil("设备信息加载成功","success");
			}else if(status == 2){
				NotificationUtil("未查询到相关内容！", "info");
			}else{
				NotificationUtil("信息加载失败", "error");
			}
			
		}, onFail: function(ei) {
			NotificationUtil(ei.getMsg(), "error");
		}
	});
}

//移除关联设备
function removeEnvdevic(){
	//获取选中的数据列
	var checkedArr = envdevicGrid.getCheckedRows();
	var checkedLength = checkedArr.length;
	if(checkedLength == 0){
		NotificationUtil("请选择记录！", "info");
		return false;
	}else{
		var envdeviceid = checkedArr[0].envdeviceid;
		var facilityid = __eiInfo.get("inqu_status-0-facilityid"); 
    	var eiInfo = new EiInfo();
		eiInfo.set("envdeviceid",envdeviceid);		            	
		eiInfo.set("facilityid",facilityid);	
		
		IPLAT.confirm({
            message: '<b>确定要取消设备关联吗？</b>',
            okFn: function (e) { 
                EiCommunicator.send("DUHB2102", "removeConection", eiInfo, {
                    onSuccess: function (ei) {
                    	if(ei.getStatus() == -1){
                    		NotificationUtil("操作失败！", "error");  
                    	}else if(ei.getStatus() == 1)
                    	{
                        	NotificationUtil("取消成功！","success"); 
                        	window.location.reload();
                    	}else if(ei.getStatus() == -2){
                    		NotificationUtil("系统参数错误！","success"); 
                    	}
                    	
                    }, onFail: function () {
                    	// 发生异常
                    	NotificationUtil("操作失败" , "error");  
                    }
                });
            },
            cancelFn: function (e) { IPLAT.NotificationUtil('取消该操作!'); },
            title: '操作提示',
            minWidth: 200
        });
		
	}
	
	

}

	
	function addInfo(){
	    var facilityid = __eiInfo.get("inqu_status-0-facilityid");
		var popWindow = $("#showEnvdevic");
	  	var url = IPLATUI.CONTEXT_PATH + "/web/DUHB2103?facilityid=" + facilityid;
	  	popWindow.data("kendoWindow").setOptions({
	  	       open: function () {
		  	       popWindow.data("kendoWindow").refresh({
		  	       	  url: url
		  	       });
	  	       },
	  	       lazyload: true,
	  	       minWidth: 1000,
	  	       minHeight: 500,
	  	       iframe: true
	  	   });
	  	popWindow.data("kendoWindow").open().center();
	}


