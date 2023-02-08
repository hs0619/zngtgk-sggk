$(function () {

	var startHourTemp = $("#inqu_status-0-startHourTemp").val();
	var startMinTemp = $("#inqu_status-0-startMinTemp").val();
	var endHourTemp = $("#inqu_status-0-endHourTemp").val();
	var endMinTemp = $("#inqu_status-0-endMinTemp").val();
	$("#inqu_status-0-startHour").val(startHourTemp);
	$("#inqu_status-0-startMin").val(startMinTemp);
	$("#inqu_status-0-endHour").val(endHourTemp);
	$("#inqu_status-0-endMin").val(endMinTemp);
	
	//获取操作类型 新增 "insert" 还是 修改 "update"
	var operate = $("#operate").val();
	//获取返回的eiInfo
	var returnEiInfo = __eiInfo;
	if("update" == operate){
		var monitorType = returnEiInfo.get("inqu_status-0-monitorType");
		IPLAT.EFInput.value($("input[name='inqu_status-0-monitorType']"), monitorType);	
		
		$("#inqu_status-0-alarmPersonIdentity").attr("readonly", true);
		$("#inqu_status-0-alarmPersonName").attr("readonly", true);
		$("#inqu_status-0-departmentName").attr("readonly", true);
		$("#inqu_status-0-phone").attr("readonly", true);
	}
	
	
	//校验输入的开始和结束时间是否合法
	$("#inqu_status-0-startHour").on("blur",function(){
		if($(this).val() != "" && $(this).val() != null){
			var sHourVal = Number($(this).val());
			if(sHourVal > 23){
				IPLAT.alert({
					title: '系统提示',
					message: '小时值不能大于23',
					okFn: function (e) {
						$("#inqu_status-0-startHour").val("");
					},
				});
			}
		}
	})
	
	$("#inqu_status-0-startMin").on("blur",function(){
		if($(this).val() != "" && $(this).val() != null){
			var sHourVal = Number($(this).val());
			if(sHourVal > 59){
				IPLAT.alert({
					title: '系统提示',
					message: '分钟值不能大于59',
					okFn: function (e) {
						$("#inqu_status-0-startMin").val("");
					},
				});
			}
		}
	})
	
	$("#inqu_status-0-endHour").on("blur",function(){
		if($(this).val() != "" && $(this).val() != null){
			var sHourVal = Number($(this).val());
			if(sHourVal > 23){
				IPLAT.alert({
					title: '系统提示',
					message: '小时值不能大于23',
					okFn: function (e) {
						$("#inqu_status-0-endHour").val("");
					},
				});
			}
		}
	})
	
	$("#inqu_status-0-endMin").on("blur",function(){
		if($(this).val() != "" && $(this).val() != null){
			var sHourVal = Number($(this).val());
			if(sHourVal > 59){
				IPLAT.alert({
					title: '系统提示',
					message: '小时值不能大于59',
					okFn: function (e) {
						$("#inqu_status-0-endMin").val("");
					},
				});
			}
		}
	})
	
	
	
	IPLATUI.EFSelect = {
		"inqu_status-0-alarmPersonIdentity": {
			change: function (e) {    //改变下拉选择框时触发的事件
				var selectValue = this.value();//当前选择项
				if(selectValue != "" && selectValue != null){
					var ei = new EiInfo();
		        	ei.set("userId", selectValue);
		        	
		        	EiCommunicator.send("DUHB2501", "queryAccountInfoById", ei, {
				        onSuccess: function (ei) {
				        	if(ei.getStatus() == 1){
				        		 $("#inqu_status-0-alarmPersonName").val(ei.get("userName"));
				        		 $("#inqu_status-0-departmentid").val(ei.get("departmentId"));
				        		 $("#inqu_status-0-departmentName").val(ei.get("departmentName"));
				        		 $("#inqu_status-0-phone").val(ei.get("mobile"));
				        		 
				        		 $("#inqu_status-0-alarmPersonName").attr("readonly", true);
				        		 $("#inqu_status-0-departmentName").attr("readonly", true);
				        		 $("#inqu_status-0-phone").attr("readonly", true);
				        	
				        	}else if(ei.getStatus() == -1){
				        		NotificationUtil("数据出错" + ei.getMsg(), "error");  
				        	}

				        }, onFail: function () {
				        	// 发生异常
				        	NotificationUtil("系统错误" + ei.getMsg(), "error");  
				        }
				    });
				
				}else{//选择为空时，设置可输入
					 $("#inqu_status-0-alarmPersonName").val("");
		    		 $("#inqu_status-0-departmentid").val("");
		    		 $("#inqu_status-0-departmentName").val("");
		    		 $("#inqu_status-0-phone").val("");
		    		 
		 			 $("#inqu_status-0-alarmPersonName").attr("readonly", false);
	        		 $("#inqu_status-0-departmentName").attr("readonly", false);
	        		 $("#inqu_status-0-phone").attr("readonly", false);
				
				}
				
			}
		
		
		}
	
	
	}
	
	
	//保存异常申报信息
	$("#SAVE").on("click", function (e) {
		
		var alarmPersonIdentity = $("#inqu_status-0-alarmPersonIdentity").val();
		var tipStr = "";
		if(alarmPersonIdentity == ""||alarmPersonIdentity == null){
			tipStr += "人员ID不能为空！\n";
		}
		
		var alarmPersonName = $("#inqu_status-0-alarmPersonName").val();  
		if(alarmPersonName == ""||alarmPersonName == null){
			tipStr += "人员姓名不能为空！\n";
		}
		
		var departmentName = $("#inqu_status-0-departmentName").val();  
		if(departmentName == ""||departmentName == null){
			tipStr += "所属部门不能为空！\n";
		}
		
		var phone = $("#inqu_status-0-phone").val(); 
		if(phone == ""||phone == null){
			tipStr += "电话号码不能为空！\n";
		}
		
		
		if(tipStr != ""){
			IPLAT.alert({
				title: '警告信息',
				message: tipStr
			});
			return;
		}
		
		//获取选中的监控类型的值
		var monitorVal = "";
		var monitorType = document.getElementsByName("inqu_status-0-monitorType");
		for(var i = 0; i < monitorType.length; i++){
			if(monitorType[i].checked){
				monitorVal = monitorType[i].value;
			}
		}	
		
		//处理发送报警的开始和结束时间
		var startHour = $("#inqu_status-0-startHour").val();  
		var startMin = $("#inqu_status-0-startMin").val();  
		var endHour = $("#inqu_status-0-endHour").val();  
		var endMin = $("#inqu_status-0-endMin").val();  
		if(startHour == "" || startHour == null ){
			startHour = "00";
		}else if(startHour.length == 1){
			startHour = "0"+ startHour;
		}
		
		if(startMin == "" || startMin == null ){
			startMin = "00";
		}else if(startMin.length == 1){
			startMin = "0"+ startMin;
		}
		
		
		if(endHour == "" || endHour == null ){
			endHour = "00";
		}else if(endHour.length == 1){
			endHour = "0"+ endHour;
		}
		
		if(endMin == "" || endMin == null ){
			endMin = "00";
		}else if(endMin.length == 1){
			endMin = "0"+ endMin;
		}
		var alarmStartTime = startHour + ":" + startMin ;
		var alarmEndTime = endHour + ":" + endMin ;
		
		//获取选中的 使用状态 的值
		var status = "";
		var statusArr = document.getElementsByName("inqu_status-0-status");
		for(var i = 0; i < statusArr.length; i++){
			if(statusArr[i].checked){
				status = statusArr[i].value;
			}
		}
		
		
		IPLAT.confirm({
            message: '<b>确定要保存数据吗？</b>',
            okFn: function (e) { 
            	var departmentid = $("#inqu_status-0-departmentid").val();
            	var eiInfo = new EiInfo();
            	eiInfo.set("alarmPersonIdentity", alarmPersonIdentity);
            	eiInfo.set("alarmPersonName", alarmPersonName);
            	eiInfo.set("departmentid", departmentid);
            	eiInfo.set("departmentName", departmentName);
            	eiInfo.set("phone", phone);
            	eiInfo.set("monitorType", monitorVal);
            	eiInfo.set("alarmStartTime", alarmStartTime);
            	eiInfo.set("alarmEndTime", alarmEndTime);
            	eiInfo.set("status", status);
            	
            	if(operate == "insert"){
	            	EiCommunicator.send("DUHB2501", "saveAlarmPersonInfo", eiInfo, {
	                    onSuccess: function (ei) {
	                    	if(ei.getStatus() == -1){
	                    		NotificationUtil(ei.getMsg(), "error");  
	                    	}else if(ei.getStatus() == 1) {
				            	IPLAT.alert({
									title: '系统提示',
									message: '保存成功',
									okFn: function (e) {
										window.parent['insertOrUpdateWindow'].close();
									},
								});
				            	
	                    	}else if(ei.getStatus() == 2){
	                    		IPLAT.alert({
									title: '系统提示',
									message: ei.getMsg()
								});
	                    	}
	                    	
	                    }, onFail: function () {
	                    	// 发生异常
	                    	NotificationUtil("保存数据出错" + ei.getMsg(), "error");  
	                    }
	                });
            	}else{
            		var creator = $("#inqu_status-0-creator").val();
            		var createdtime = $("#inqu_status-0-createdtime").val();
            		eiInfo.set("creator", creator);
            		eiInfo.set("createdtime", createdtime);
            		
	            	EiCommunicator.send("DUHB2501", "updateAlarmPersonInfo", eiInfo, {
	                    onSuccess: function (ei) {
	                    	if(ei.getStatus() == -1){
	                    		NotificationUtil(ei.getMsg(), "error");  
	                    	}else{
				            	IPLAT.alert({
									title: '系统提示',
									message: '修改成功',
									okFn: function (e) {
										window.parent['insertOrUpdateWindow'].close();
									},
								});
				            	
	                    	}
	                    	
	                    }, onFail: function () {
	                    	// 发生异常
	                    	NotificationUtil("保存数据出错" + ei.getMsg(), "error");  
	                    }
	                });
            	}

            },
            cancelFn: function (e) { IPLAT.NotificationUtil('取消该操作!'); },
            title: '操作提示',
            minWidth: 200
        });
		
	});
	 	
});




