$(function () {
	
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
	});
	
	
	
		
	IPLATUI.EFGrid = {
			"result" : {
				exportGrid: false,//设置分页右侧按钮是否可见false为不可见
				columns: [
					{
						field: "issend",
						title: "发送短信",
						encoded: false,
						width: 50,
						template: function(dateItem) {
							var flag = dateItem.ISSEND;
							var siteid = dateItem.siteid;
							var str = "";
							if(flag == "1"){
								str = '<div style="text-align: center">'
									  +'<span style="padding-right:5px" >关</span>'
									  +'<input type="checkbox" checked="checked"  onclick="changeSendStatus(this,\'' + siteid + '\')"  class="switch switch-info" />'
									  +'<span  style="padding-left:5px">开</span>'
									  +'</div>';
							}else{
								str = '<div style="text-align: center">'
									  +'<span style="padding-right:5px" >关</span>'
									  +'<input type="checkbox" onclick="changeSendStatus(this, \'' + siteid + '\')" class="switch switch-info" />'
									  +'<span  style="padding-left:5px">开</span>'
									  +'</div>';
							
							}
							
							return str;
						}
					}
				]
	        	

            }//result内容结束
        	
        };
	
	
	
		IPLATUI.EFWindow = {
            "declareWin": {
                close: function (e) {
                	resultGrid.dataSource.page(1);
                }
            }
        };
        
        
        // 排口编号 查询框的keyup事件，用户输入一个字符后，立即进行查询
		$("#inqu_status-0-mnid").keyup(function(){
			//获取查询区域两个输入框的值
			var mnid = $("#inqu_status-0-mnid").val();
			var departmentid = $("#inqu_status-0-departmentid").val();
			
			var ei =new EiInfo();
			ei.set("mnid", mnid);
			ei.set("departmentid", departmentid);
			EiCommunicator.send("DUHB27", "queryInfoByCondition	", ei, {
				onSuccess: function(ei) {
				 	resultGrid.dataSource.page(1);
				}, onFail: function(ei) {
					NotificationUtil(ei.getMsg(), "error");
				}
			});
        
        
		})
		
		
		// 排口编号 查询框的keyup事件，用户输入一个字符后，立即进行查询
		$("#inqu_status-0-departmentid").change(function(){
			//获取查询区域两个输入框的值
			var mnid = $("#inqu_status-0-mnid").val();
			var departmentid = $("#inqu_status-0-departmentid").val();
			
			var ei =new EiInfo();
			ei.set("mnid", mnid);
			ei.set("departmentid", departmentid);
			EiCommunicator.send("DUHB27", "queryInfoByCondition	", ei, {
				onSuccess: function(ei) {
				 	resultGrid.dataSource.page(1);
				}, onFail: function(ei) {
					NotificationUtil(ei.getMsg(), "error");
				}
			});
        
        
		})
        
	    	
});


//点击改变发送短信得状态
function changeSendStatus(obj, siteid){
	//true为打开状态，false为关闭状态
	var status = obj.checked;
	var issend = "";
	if(status){
		issend = "1";
	}else{
		issend = "0";
	}
	
	var ei =new EiInfo();
	ei.set("issend", issend);
	ei.set("siteid", siteid);
	EiCommunicator.send("DUHB27", "changeSendStatus", ei, {
		onSuccess: function(ei) {
		 	
		}, onFail: function(ei) {
			NotificationUtil(ei.getMsg(), "error");
		}
	});

}







