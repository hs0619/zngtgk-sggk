$(function() {
	$("#QUERY").on("click", function(e) {
		//resultGrid.dataSource.page(1);
	});
	
	
	//实时刷新系统待办消息
	function refreshMessage(){
		var eiInfo = new EiInfo();
		IPLAT.progress($("body"), true);
		EiCommunicator.send("DUHB99", "query", eiInfo, {
	        onSuccess: function (ei) {
	        	resultGrid.setEiInfo(ei);
        		IPLAT.progress($("body"), false);
	        }, onFail: function () {
	        	// 发生异常
	        	IPLAT.progress($("body"), false);
	        }
	    });
		
	}
	

	IPLATUI.EFGrid = {
			"result" : {
				pageable : false,
				exportGrid: false,
				columns: [
	            	{
	                    field: "operation",
	                    title: "处理",
	                    encoded:false,
	                    template: function(dateItem) {
	                        return '<div style="text-align: center"><input value="查看"' +
                                ' class="k-button k-button-details" type="button" align="center" ' +
                                'onclick="showDetail(\'' + dateItem.messageId  +  '\' ,\'' +  dateItem.jumpPage +  '\',\''+dateItem.remarks+'\')"/></div>';
	                    }
	                }
	            ]
	        }
	    };
	
	window.onload = function(){
		refreshMessage();
    	setInterval(function () {
    		refreshMessage();
        }, 180000);//3分钟
    };


});

//查看待处理消息
/*function showDetail(messageId , jumpPage){
	var url = IPLATUI.CONTEXT_PATH + "/web/" + jumpPage;	
	window.open(url);
}*/



//查看待处理消息
function showDetail(messageId , jumpPage,remarks){
	var eiInfo = new EiInfo();
	eiInfo.set("messageId",messageId);
	IPLAT.progress($("body"), true);
	EiCommunicator.send("DUHB99", "checkMessages", eiInfo, {
        onSuccess: function (ei) {
        	if(ei.getStatus() == -1){
        		NotificationUtil("系统参数错误，查看失败", "error");
        		IPLAT.progress($("body"), false);
        	}else{
        		IPLAT.progress($("body"), false);
        		remarks=remarks.substring(0,remarks.indexOf("-"));
        		var starttime="2022-01-01";
                var endtime="2099-01-01";
        		var url = IPLATUI.CONTEXT_PATH + "/web/" + jumpPage+"?remarks="+remarks+
                    "&starttime="+starttime+"&endtime="+endtime;
        		window.open(url);
        	}
        	
        }, onFail: function () {
        	// 发生异常
        	NotificationUtil("查看失败" + ei.getMsg(), "error");  
        	IPLAT.progress($("body"), false);
        }
    });	
}



