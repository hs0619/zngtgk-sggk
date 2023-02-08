$(function () {
	//保存
	$("#SAVE").on("click",function(){
		var departmentId = $("#inqu_status-0-departmentId").val();
		var user = $("#inqu_status-0-user").val();
		var operation = $("#inqu_status-0-operation").val();
		var status = $("#inqu_status-0-status").val();
		if(departmentId != "root" && user != "%"){
			if(operation == 1){
				IPLAT.confirm({
					message: '<b>确认修改信息吗？</b>',
					okFn: function() {
						var eiInfo = new EiInfo();
						eiInfo.setByNode("inqu");
						EiCommunicator.send("DUHB2802", "update", eiInfo, {
							onSuccess: function(ei) {
								var status = ei.get("status");
								if (status == 1) {
									NotificationUtil(ei.get("message"));
									
								} else {
									NotificationUtil(ei.get("message"), "error");
									console.log(ei);
								}
							}, onFail: function() {
								NotificationUtil("修改错误!", "error");
							}
						});
					},
					cancelFn: function() { IPLAT.NotificationUtil('取消该操作!'); },
					title: '操作提示',
					minWidth: 200
				});
			} else {
				IPLAT.confirm({
					message: '<b>确认保存信息吗？</b>',
					okFn: function() {
						var eiInfo = new EiInfo();
						eiInfo.setByNode("inqu");
						EiCommunicator.send("DUHB2802", "insert", eiInfo, {
							onSuccess: function(ei) {
								var id = ei.get("id");
								var status = ei.get("status");
								if (status == 1) {
									NotificationUtil(ei.get("message"));
									window.parent["showDPWindow"].close();
								} else if (status == 2){
									NotificationUtil(ei.get("message"), "warning");
								}else{
									NotificationUtil(ei.get("message"), "error");
								}
							}, onFail: function() {
								NotificationUtil("新增错误!", "error");
							}
						});
	
					},
					cancelFn: function() { IPLAT.NotificationUtil('取消该操作!'); },
					title: '操作提示',
					minWidth: 200
				});
			}
		}else if(departmentId == "root"){
			NotificationUtil("请关闭当前页面，选择厂部后再点击新增!","warning");
		}else if(user == "%"){
			NotificationUtil("请选择用户！","warning");
		}
		
		
		
	})
});