$(function() {
	var operationStr = __eiInfo.get("inqu_status-0-operation");
	var loginName = __eiInfo.get("inqu_status-0-loginName");
	var recordid = __eiInfo.get("inqu_status-0-recordid");
	var status = __eiInfo.get("inqu_status-0-status");
	if ("update" == operationStr) {
		if (status == "0" && loginName != recordid) {//暂存and登录人不是登记人
			$("#TEMSAVE").hide();
			$("#SAVE").hide();
		} else if (status == "1") { //提交
			$("#inqu_status-0-departmentid").attr("disabled", true);
			$("#inqu_status-0-nuclide").attr("disabled", true);
			$("#inqu_status-0-comedate").attr("disabled", true);
			$("#inqu_status-0-comeactivity").attr("disabled", true);
			$("#inqu_status-0-radiatecode").attr("disabled", true);
			$("#inqu_status-0-radiatetype").attr("disabled", true);
            $("#inqu_status-0-radiatename").attr("disabled", true);
			$("#inqu_status-0-purpose").attr("disabled", true);
			$("#inqu_status-0-address").attr("disabled", true);
			$("#inqu_status-0-supplier").attr("disabled", true);
			$("#inqu_status-0-prounit").attr("disabled", true);
			$("#inqu_status-0-recorddate").attr("disabled", true);
			$("#inqu_status-0-recordname").attr("disabled", true);
			$("#inqu_status-0-remark").attr("disabled", true);
			$("#inqu_status-0-iswork").attr("disabled", true);
            $("#inqu_status-0-number").attr("disabled", true);
			$("#TEMSAVE").hide();
			$("#SAVE").hide();
		}
	}

	//类型配置
	$("#TYPEDEPLOY").on("click", function(e) {
		var popWindow = $("#showHM");
		var url = IPLATUI.CONTEXT_PATH + "/web/DUHF7103";
		popWindow.data("kendoWindow").setOptions({
			open: function() {
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
	});

	//暂存可以修改
	$("#TEMSAVE").on("click", function() {
		var istrue = checkParam() //参数检索
		if (!istrue) {
			return;
		}
		var flag = "暂存";
		var status = "0";
		operation(flag,status)
	})

	//提交后不能修改
	$("#SAVE").on("click", function() {
		var istrue = checkParam() //参数检索
		if (!istrue) {
			return;
		}
		var flag = "提交";
		var status = "1";
		operation(flag,status)
	})

	function operation(flag,status) {
		IPLAT.confirm({
			message: '<b>确认要' + flag + '放射源信息吗？</b>',
			okFn: function() {
				var eiInfo = new EiInfo();
				$("#inqu_status-0-status").val(status);
				eiInfo.setByNode("inqu");
				EiCommunicator.send("DUHF7101", "save", eiInfo, {
					onSuccess: function(ei) {
						var status = ei.get("status");
						if (status == 1) {
							NotificationUtil(flag + "成功！" + ei.get("message"));
							setTimeout(function() {
								window.parent["showHMWindow"].close();
							}, 1000)

						} else {
							NotificationUtil(flag + "失败！", "error");
							console.log(ei);
						}
					}, onFail: function() {
						NotificationUtil("操作失败!", "error");
					}
				});
			},
			cancelFn: function() { IPLAT.NotificationUtil('取消该操作!'); },
			title: '操作提示',
			minWidth: 200
		});
	}

});


function checkParam() {
	var departmentid = $("#inqu_status-0-departmentid").val();
	if (departmentid == "%") {
		NotificationUtil("请选择单位", "warning");
		return false;
	}
	var nuclide = $("#inqu_status-0-nuclide").val();
	if (nuclide == "" || nuclide == null) {
		NotificationUtil("请填写核素", "warning");
		return false;
	}
	var comedate = $("#inqu_status-0-comedate").val();
	if (comedate == "" || comedate == null) {
		NotificationUtil("请选择出厂日期", "warning");
		return false;
	}
	var comeactivity = $("#inqu_status-0-comeactivity").val();
	if (comeactivity == "" || comeactivity == null) {
		NotificationUtil("请填写出厂活度（Bq）", "warning");
		return false;
	}
	var radiatecode = $("#inqu_status-0-radiatecode").val();
	if (radiatecode == "" || radiatecode == null) {
		NotificationUtil("请填写编码", "warning");
		return false;
	}
	var radiatetype = $("#inqu_status-0-radiatetype").val();
	if (radiatetype == "%") {
		NotificationUtil("请选择类别", "warning");
		return false;
	}
	var purpose = $("#inqu_status-0-purpose").val();
	if (purpose == "" || purpose == null) {
		NotificationUtil("请填写用途", "warning");
		return false;
	}
	var address = $("#inqu_status-0-address").val();
	if (address == "" || address == null) {
		NotificationUtil("请填写场所", "warning");
		return false;
	}

	var remark = $("#inqu_status-0-remark").val();
	if (/^\s+$/gi.test(document.getElementById('inqu_status-0-remark').value)) {
		NotificationUtil("备注不能全为空格！", "warning");
		return false;
	}
	return true;
}