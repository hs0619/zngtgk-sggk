$(function () {
		$("#QUERY").on("click", function(e) {
		    var eiInfo = new EiInfo();
            eiInfo.setByNode("inqu");	            	
            EiCommunicator.send("DUHB2003", "query", eiInfo, {
                onSuccess: function (ei) {
                	if(ei.getStatus() == -1){
                		NotificationUtil(ei.getMsg(), "error");  
                	}else{
                    	NotificationUtil("查询成功！" , "success"); 
                    	envdevicGrid.setEiInfo(ei);
                	}
                	
                }, onFail: function () {
                	// 发生异常
                	NotificationUtil("保存数据出错" + ei.getMsg(), "error");  
                }
            });
		});

		IPLATUI.EFGrid = {
		    "envdevic": {
		    	exportGrid: false,
		    	pageable: false,
		    	beforeAdd: function (e) {
		    		e.preventDefault();
		    		var facilityid = __eiInfo.get("inqu_status-0-facilityid");
		    		var checkData = envdevicGrid.getCheckedRows();
		    		var checkLength = checkData.length;
		    		var envdeviceids = "";
		    		if(checkLength == 0){
		    			NotificationUtil("请先勾选设备", "warning");  
						return;
		    		}else{
		    			for(var i = 0; i < checkLength; i++){
		    				var envdeviceid = checkData[i].envdeviceid;
		    				if(i == checkLength - 1){
		    					envdeviceids += envdeviceid;
		    				}else{
		    					envdeviceids += envdeviceid + ",";
		    				}
		    			}
		    			
		    			var eiInfo = new EiInfo();
		    			eiInfo.set("envdeviceid",envdeviceids);		            	
		    			eiInfo.set("facilityid",facilityid);		            	
		                EiCommunicator.send("DUHB2003", "saveEnvdeviceInfo", eiInfo, {
		                    onSuccess: function (ei) {
		                    	if(ei.getStatus() == -1){
		                    		NotificationUtil(ei.getMsg(), "error");  
		                    	}else{
		                        	NotificationUtil(ei.getMsg()); 
		                        	window.parent['showEnvdevicWindow'].close();
		                    	}
		                    	
		                    }, onFail: function () {
		                    	// 发生异常
		                    	NotificationUtil("保存数据出错" + ei.getMsg(), "error");  
		                    }
		                });
		    		
		    		}

		    		
		    	}
			}
	};
	



});


