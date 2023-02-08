$(function() {
	
	$(document).ready(function(){ 
		// 设置选中第一行
	    resultGrid.setCheckedRows(0);
	})
	
	IPLATUI.EFGrid = {
		    "result": {
		    	pageable: false,
		    	exportGrid: false,
		    	showCount: false,
		        onCheckRow: function (e) {
		        	loadMsg(e.model.facilityid);
		           /*WindowUtil({
		                "title": "点选数据行信息",
		                "content": "<div class='kendo-del-message'>勾选了第" + e.model.processid + "行数据</div>"
		            })*/
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
            message: '<b>确定保存废水处理设施信息吗？</b>',
            okFn: function (e) { 
            	var eiInfo = new EiInfo();
            	eiInfo.setByNode("inqu");
                EiCommunicator.send("DUHB1902", "save", eiInfo, {
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
//加载详细信息
function loadMsg(facilityid){
	var ei =new EiInfo();
	ei.set("facilityid",facilityid);
	EiCommunicator.send("DUHB1902", "loadMsg", ei, {
		onSuccess: function(ei) {
			var status = ei.get("status");
			if (status == 1) {
				$("#inqu_status-0-facilityid").val(ei.get("facilityid"));
				$("#inqu_status-0-facilitycode").val(ei.get("facilitycode"));
				$("#inqu_status-0-facilityname").val(ei.get("facilityname"));
				$("#inqu_status-0-origin").val(ei.get("origin"));
				$("#inqu_status-0-handmethod").val(ei.get("handmethod"));
				$("#inqu_status-0-facilitydepart").val(ei.get("facilitydepart"));
				$("#inqu_status-0-numbers").val(ei.get("numbers"));
				$("#inqu_status-0-handability").val(ei.get("handability"));
				$("#inqu_status-0-shandnum").val(ei.get("shandnum"));
				$("#inqu_status-0-isexecute").val(ei.get("isexecute"));
				$("#inqu_status-0-remark").val(ei.get("remark"));
				$("#inqu_status-0-handfactors").val(ei.get("handfactors"));
				$("#inqu_status-0-outstandard").val(ei.get("outstandard"));
				$("#inqu_status-0-runtime").val(ei.get("runtime"));
				$("#inqu_status-0-devicecode").val(ei.get("devicecode"));
				$("#inqu_status-0-devicenames").val(ei.get("devicenames"));
				$("#inqu_status-0-situation").val(ei.get("situation"));
				$("#inqu_status-0-devicemodel").val(ei.get("devicemodel"));
				$("#inqu_status-0-motormodel").val(ei.get("motormodel"));
				$("#inqu_status-0-controllermodel").val(ei.get("controllermodel"));
				$("#inqu_status-0-portid").val(ei.get("portid"));
				$("#inqu_status-0-portname").val(ei.get("portname"));
				$("#inqu_status-0-oprationType").val(ei.get("oprationType"));
				$("#inqu_status-0-departmentname").val(ei.get("departmentname"));
				$("#inqu_status-0-procedurename").val(ei.get("procedurename"));
				$("#inqu_status-0-mark").val(ei.get("mark"));
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