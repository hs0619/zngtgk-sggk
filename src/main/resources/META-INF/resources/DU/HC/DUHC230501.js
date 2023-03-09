$(function() {
	IPLATUI.EFSelect = {
		"inqu_status-0-monitorid": {
			// 下拉选项改变之后触发
			change: function(e) { //获取改变值
				var eiInfo = new EiInfo();
				eiInfo.setByNode("inqu");
				EiCommunicator.send("DUHC230501", "getFactor", eiInfo, {
					onSuccess: function(ei) {
						if (ei["blocks"]["factor"] != null) {
							var pffactor = [];
							$.each(ei["blocks"]["factor"]["rows"], function(i, obj) {
								pffactor.push({ "valueField": obj[0], "textField": obj[1] });
							});
							var dataSource = new kendo.data.DataSource({
								data: pffactor
							});
							//$("#inqu_status-0-factorid").data("kendoMultiSelect").setDataSource(dataSource);
							IPLAT.EFSelect.setDataSource($("#inqu_status-0-factorid"), dataSource);
						};
					}, onFail: function(ei) {
						// 发生异常
						NotificationUtil("加载数据出错" + ei.getMsg(), "error");
						console.log(ei);
					}
				});
			}
		}
	}
	
	var monitorid = __eiInfo.get("inqu_status-0-monitorid");
	var factorid = __eiInfo.get("inqu_status-0-factorid");
	if(monitorid!=null && monitorid!="" && factorid!=null && factorid!=""){
		$("#inqu_status-0-monitorid").attr("disabled", true);
		$("#inqu_status-0-factorid").attr("disabled", true);
	}
	
	//提交信息
	$("#SAVE").on("click", function(e) {
		var istrue = checkParam() //参数检索
		if (!istrue) {
			return;
		}
		IPLAT.confirm({
			message: '<b>确定保存人工检测因子信息吗？</b>',
			okFn: function(e) {
				var eiInfo = new EiInfo();
				var monitorname=IPLAT.EFSelect.text($("#inqu_status-0-monitorid"));
				var factorname=IPLAT.EFSelect.text($("#inqu_status-0-factorid"));
				$("#inqu_status-0-monitorname").val(monitorname);
				$("#inqu_status-0-factorname").val(factorname);
				eiInfo.setByNode("inqu");
				EiCommunicator.send("DUHC230501", "save", eiInfo, {
					onSuccess: function(ei) {
						if (ei.getStatus() == -1) {
							NotificationUtil(ei.getMsg(), "error");
						} else if(ei.getStatus() == 2) {
							NotificationUtil(ei.getMsg(), "warning");
						}else{
							NotificationUtil(ei.getMsg());
							window.parent['showHMWindow'].close(); //關閉窗口的方法，參數：窗口id+Window           	
						}
					}, onFail: function() {
						// 发生异常
						NotificationUtil("保存出错" + ei.getMsg(), "error");
					}
				});
			},
			cancelFn: function(e) { IPLAT.NotificationUtil('取消该操作!'); },
			title: '操作提示',
			minWidth: 200
		});
	});
});

function checkParam() {
	var monitorid = $("#inqu_status-0-monitorid").val();
	if (monitorid == null || monitorid == "") {
		NotificationUtil("监测类型不能为空！", "warning");
		return false;
	}
	/*var factorid = $("#inqu_status-0-factorid").val();
	if (factorid == null || factorid == "") {
		NotificationUtil("监测因子不能为空！", "warning");
		return false;
	}*/
	var unit = $("#inqu_status-0-unit").val();
	if (unit == null || unit == "") {
		NotificationUtil("因子单位不能为空！", "warning");
		return false;
	}
	return true;
}