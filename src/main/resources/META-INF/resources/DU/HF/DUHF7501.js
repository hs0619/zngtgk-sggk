$(function() {
	var operationStr = __eiInfo.get("inqu_status-0-operation");
	var loginName = __eiInfo.get("inqu_status-0-loginName");
	var createdid = __eiInfo.get("inqu_status-0-createdid");
	var status = __eiInfo.get("inqu_status-0-status");
	if ("update" == operationStr) {
		if (status == "0" && loginName != createdid) {//暂存and登录人不是登记人
			$("#TEMSAVE").hide();
			$("#SAVE").hide();
		} else if (status == "1") { //提交
			$("#inqu_status-0-departmentid").attr("disabled", true);
			$("#inqu_status-0-workerid").attr("disabled", true);
			$("#inqu_status-0-workername").attr("disabled", true);
			$("#inqu_status-0-gender").attr("disabled", true);
			$("#inqu_status-0-birthdate").attr("disabled", true);
			$("#inqu_status-0-graschool").attr("disabled", true);
			$("#inqu_status-0-education").attr("disabled", true);
			$("#inqu_status-0-eventtype").attr("disabled", true);
			$("#inqu_status-0-certificatetype").attr("disabled", true);
			$("#inqu_status-0-certificatenumber").attr("disabled", true);
			$("#inqu_status-0-operatingpost").attr("disabled", true);
			$("#inqu_status-0-deadlinedate").attr("disabled",  true);
			$("#inqu_status-0-certificateno").attr("disabled",  true);
			$("#inqu_status-0-certificatename").attr("disabled",  true);
			$("#inqu_status-0-remark").attr("disabled",  true);
			$("#inqu_status-0-iswork").attr("disabled",  true);
			$("#TEMSAVE").hide();
			$("#SAVE").hide();
		}
	}
	//暂存可以修改
	$("#TEMSAVE").on("click", function() {
		var istrue = checkParam() //参数检索
		if (!istrue) {
			return;
		}
		var flag = "暂存";
		var status = "0";
		operation(flag, status)
	})

	//提交后不能修改
	$("#SAVE").on("click", function() {
		var istrue = checkParam() //参数检索
		if (!istrue) {
			return;
		}
		var flag = "提交";
		var status = "1";
		operation(flag, status)
	})

	function operation(flag, status) {
		IPLAT.confirm({
			message: '<b>确认要' + flag + '辐射工作人员信息吗？</b>',
			okFn: function() {
				var eiInfo = new EiInfo();
				$("#inqu_status-0-status").val(status);
				eiInfo.setByNode("inqu");
				EiCommunicator.send("DUHF7501", "save", eiInfo, {
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

})

function checkParam() {
	var departmentid = $("#inqu_status-0-departmentid").val();
	if (departmentid == "%") {
		NotificationUtil("请选择单位", "warning");
		return false;
	}
	var name = $("#inqu_status-0-workerid").val();
	if (name == "" || name == null) {
		NotificationUtil("请填写工号", "warning");
		return false;
	}
	var ename = $("#inqu_status-0-workername").val();
	if (ename == "" || ename == null) {
		NotificationUtil("请填写姓名", "warning");
		return false;
	}
	var gender = $("#inqu_status-0-gender").val();
	if (gender == "%") {
		NotificationUtil("请选择性别", "warning");
		return false;
	}
	var birthdate = $("#inqu_status-0-birthdate").val();
	if (birthdate == "" || birthdate == null) {
		NotificationUtil("请选择出生日期", "warning");
		return false;
	}
	var graschool = $("#inqu_status-0-graschool").val();
	if (graschool == "" || graschool == null) {
		NotificationUtil("请填写毕业学校", "warning");
		return false;
	}
	var education = $("#inqu_status-0-education").val();
	if (education == "%") {
		NotificationUtil("请选择学历", "warning");
		return false;
	}
	var eventtype = $("#inqu_status-0-eventtype").val();
	if (eventtype == "" || eventtype == null) {
		NotificationUtil("请填写专业", "warning");
		return false;
	}
	var certificatetype = $("#inqu_status-0-certificatetype").val();
	if (certificatetype == "%") {
		NotificationUtil("请选择证件类型", "warning");
		return false;
	}
	var certificatenumber = $("#inqu_status-0-certificatenumber").val();
	if (certificatenumber == "" || certificatenumber == null) {
		NotificationUtil("请填写证件号码", "warning");
		return false;
	}
	var operatingpost = $("#inqu_status-0-operatingpost").val();
	if (operatingpost == "" || operatingpost == null) {
		NotificationUtil("请填写工作岗位", "warning");
		return false;
	}
	var deadlinedate = $("#inqu_status-0-deadlinedate").val();
	if (deadlinedate == "" || deadlinedate == null) {
		NotificationUtil("请选择有效期", "warning");
		return false;
	}
	var certificateno = $("#inqu_status-0-certificateno").val();
	if (certificateno == "" || certificateno == null) {
		NotificationUtil("请填写培训/考试编号", "warning");
		return false;
	}

	var remark = $("#inqu_status-0-remark").val();
	if (/^\s+$/gi.test(document.getElementById('inqu_status-0-remark').value)) {
		NotificationUtil("备注不能全为空格！", "warning");
		return false;
	}
	return true;
}