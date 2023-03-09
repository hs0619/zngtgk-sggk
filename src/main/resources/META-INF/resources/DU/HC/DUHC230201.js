$(function() {
	var eiInfo = new EiInfo();
	var departmentid = __eiInfo.get("inqu_status-0-departmentid");
	var monitorid = __eiInfo.get("inqu_status-0-monitorid");
	var siteid = __eiInfo.get("inqu_status-0-siteid");
	var oprationType = __eiInfo.get("inqu_status-0-oprationType");
	if (oprationType != null && oprationType != "") {
		$("#inqu_status-0-clockyear").attr("disabled", true);
		$("#inqu_status-0-departmentid").attr("disabled", true);
		$("#inqu_status-0-monitorid").attr("disabled", true);
		$("#inqu_status-0-siteid").attr("disabled", true);
		$("#inqu_status-0-plantype").attr("disabled", true);
		$("#inqu_status-0-licenserate").attr("disabled", true);
		$("#copy").hide();
	} else {
		$("#inqu_status-0-oprationType").val("insert");
		$("#COPY").hide();
		//$("#COPY").css("display","none");
		if (departmentid != "%") {
			$("#inqu_status-0-departmentid").attr("disabled", true);
		}
	}
	
	//需要的初始化下拉框（1、厂部 2、厂部+监测类型 3、厂部+监测因子类型+站点 4、厂部+监测因子类型+监测点类型
	eiInfo.set("haveType", "3");

	//监测类型条件
	//eiInfo.set("monitorid","'01'");//监测类型
	//eiInfo.set("mon_isonline", "'1'");//是否在线 1是0否
	//eiInfo.set("istotal","'1'");//是否统计

	//厂部条件
	//eiInfo.set("departmentid","'1'");//厂部id
	//eiInfo.set("type","P1");//单位类型 D1:厂部，P1:工序
	//eiInfo.set("level","2");//等级
	eiInfo.set("totalplan", "'1'");//标志位

	//站点条件
	//eiInfo.set("siteid","'SI00000001'");//站点id
	eiInfo.set("isartificial","1");//是否人工 1是0否
	//eiInfo.set("site_isonline", "1");//是否在线	1是0否
	eiInfo.set("classifyid","'01'");//人工监测类型
	//eiInfo.set("monitorid","01");//在线监测类型
	eiInfo.set("departmentid_site", departmentid);//厂部
	//eiInfo.set("procid","2");//工序

	EiCommunicator.send("DUHA01", "initSelectData", eiInfo, {
		onSuccess: function(ei) {
			if (ei["blocks"]["monitor"] != null) {
				var monitors = [];
				//monitors.push({ "valueField": "%", "textField": "全部" });

				$.each(ei["blocks"]["monitor"]["rows"], function(i, obj) {
					monitors.push({ "valueField": obj[0], "textField": obj[1] });
				});
				var dataSource = new kendo.data.DataSource({
					data: monitors
				});
				IPLAT.EFSelect.setDataSource($("#inqu_status-0-monitorid"), dataSource);
				IPLAT.EFSelect.value($("#inqu_status-0-monitorid"), monitorid);
			};

			if (ei["blocks"]["depart"] != null) {
				var departs = [];
				if (departmentid == "%") {
					departs.push({ "valueField": "%", "textField": "--请选择--" });
				}
				$.each(ei["blocks"]["depart"]["rows"], function(i, obj) {
					departs.push({ "valueField": obj[0], "textField": obj[1] });
				});
				var dataSource = new kendo.data.DataSource({
					data: departs
				});
				IPLAT.EFSelect.setDataSource($("#inqu_status-0-departmentid"), dataSource);
				IPLAT.EFSelect.value($("#inqu_status-0-departmentid"), departmentid);
			};

			if (ei["blocks"]["site"] != null) {
				var sites = [];
				sites.push({ "valueField": "", "textField": "" });

				$.each(ei["blocks"]["site"]["rows"], function(i, obj) {
					sites.push({ "valueField": obj[1], "textField": obj[0] });
				});
				var dataSource = new kendo.data.DataSource({
					data: sites
				});
				IPLAT.EFSelect.setDataSource($("#inqu_status-0-siteid"), dataSource);
				IPLAT.EFSelect.value($("#inqu_status-0-siteid"), siteid);
			};
		}, onFail: function(ei) {
			// 发生异常
			NotificationUtil("加载数据出错" + ei.getMsg(), "error");
		}
	});


	//获取站点
	function getSite(eiInfo) {
		eiInfo.set("inqu_status-0-isartificial", "1");//是否在线	1是0否
		eiInfo.set("inqu_status-0-siteid", "");
		eiInfo.set("inqu_status-0-departmentid_site", $("#inqu_status-0-departmentid").val())
		EiCommunicator.send("DUHA01", "getSite", eiInfo, {
			onSuccess: function(ei) {
				if (ei["blocks"]["site"] != null) {
					var sites = [];
					sites.push({ "valueField": "", "textField": "" });
	
					$.each(ei["blocks"]["site"]["rows"], function(i, obj) {
						sites.push({ "valueField": obj[1], "textField": obj[0] });
					});
					var dataSource = new kendo.data.DataSource({
						data: sites
					});
					IPLAT.EFSelect.setDataSource($("#inqu_status-0-siteid"), dataSource);
					IPLAT.EFSelect.value($("#inqu_status-0-siteid"), "%");
				};
			}, onFail: function(ei) {
				// 发生异常
				NotificationUtil("加载数据出错" + ei.getMsg(), "error");
				console.log(ei);
			}
		});

	}

	IPLATUI.EFSelect = {
		"inqu_status-0-monitorid": {
			// 下拉选项改变之后触发
			change: function(e) { //获取改变值
				var eiInfo = new EiInfo();
				eiInfo.setByNode("inqu");
				getSite(eiInfo);
			}
		},
		
		"inqu_status-0-departmentid": {
			// 下拉选项改变之后触发
			change: function(e) { // 获取改变值
				var eiInfo = new EiInfo();
				eiInfo.setByNode("inqu");
				getSite(eiInfo);
			}
		},
		"inqu_status-0-siteid": {
			// 下拉选项改变之后触发
			change: function(e) { //获取改变值
				createPlanName(e);
			}
		},
		"inqu_status-0-plantype": {
			// 下拉选项改变之后触发
			change: function(e) { //获取改变值
				createPlanName(e);
			}
		},
		"inqu_status-0-licenserate": {
			// 下拉选项改变之后触发
			change: function(e) { //获取改变值
				createPlanName(e);
			}
		},
	}
	
	
	//根据配置改变计划名称
	createPlanName = function(e) {
		var sitename = IPLAT.EFSelect.text($("#inqu_status-0-siteid"));
		if (sitename == "请选择监测点" || sitename == "") return;
		//value的括号里面一定要有空格
		var Date = $("#inqu_status-0-clockyear").data("kendoDatePicker").value()
		//var yearclock=kendo.toString(Date, "yyyy");
		var yearclock = $("#inqu_status-0-clockyear").val();
		//
		var departname = IPLAT.EFSelect.text($("#inqu_status-0-departmentid"));
		if (departname == "") return;
		//计划内外
		var plantype = $("#inqu_status-0-plantype").val();
		var plantypename = "计划内";
		if (plantype != "1") plantypename = "计划外";
		//监测类型
		var monitorname = IPLAT.EFSelect.text($("#inqu_status-0-monitorid"));
		//监测频率
		var licenseratename = IPLAT.EFSelect.text($("#inqu_status-0-licenserate"));
		//
		IPLAT.EFInput.value($("#inqu_status-0-planname"), yearclock + "年:"
			+ departname + "-" + sitename + "(" + monitorname + plantypename + ")"+"-"+licenseratename);
	};
	
	//根据配置改变计划名称
	createcopyName = function(e) {
		var sitename = IPLAT.EFSelect.text($("#inqu_status-0-siteid"));
		if (sitename == "请选择监测点" || sitename == "") return;
		//value的括号里面一定要有空格
		var Date = $("#inqu_status-0-clockyear").data("kendoDatePicker").value()
		//var yearclock=kendo.toString(Date, "yyyy");
		var copyyear = $("#inqu_status-0-copyyear").val();
		//
		var departname = IPLAT.EFSelect.text($("#inqu_status-0-departmentid"));
		if (departname == "") return;
		//计划内外
		var plantype = $("#inqu_status-0-plantype").val();
		var plantypename = "计划内";
		if (plantype != "1") plantypename = "计划外";
		//监测类型
		var monitorname = IPLAT.EFSelect.text($("#inqu_status-0-monitorid"));
		//监测频率
		var licenseratename = IPLAT.EFSelect.text($("#inqu_status-0-licenserate"));
		//
		IPLAT.EFInput.value($("#inqu_status-0-copyname"), copyyear + "年:"
			+ departname + "-" + sitename + "(" + monitorname + plantypename + ")"+"-"+licenseratename);
	};

	

	//提交信息
	$("#SAVE").on("click", function(e) {
		var oprationType = $('#inqu_status-0-oprationType').val();
		if (oprationType == null || oprationType == "" || oprationType == "undefined") {
			NotificationUtil("参数出错，禁止保存", "error");
			return;
		}
		var istrue = checkParam() //参数检索
		if (!istrue) {
			return;
		}
		IPLAT.confirm({
			message: '<b>确定保存计划信息吗？</b>',
			okFn: function(e) {
				var eiInfo = new EiInfo();
				//保存inqu区域数据，包括操作标志
				eiInfo.setByNode("inqu");
				EiCommunicator.send("DUHC230201", "savePlanInof", eiInfo, {
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
	
	//复制信息
	$("#COPY").on("click", function(e) {
		var oprationType = $('#inqu_status-0-oprationType').val();
		if (oprationType == null || oprationType == "" || oprationType == "undefined") {
			NotificationUtil("参数出错，禁止保存", "error");
			return;
		}
		var licensecount = $("#inqu_status-0-licensecount").val();
		if (licensecount == null || licensecount == "") {
			NotificationUtil("监测次数不能为空！", "warning");
			return ;
		}
		var copyyear = $("#inqu_status-0-copyyear").val();
		if (copyyear == null || copyyear == "") {
			NotificationUtil("复制年份不能为空！", "warning");
			return ;
		}
		IPLAT.confirm({
			message: '<b>确定复制计划信息吗？</b>',
			okFn: function(e) {
				var eiInfo = new EiInfo();
				$("#inqu_status-0-oprationType").val("copy");
				//保存inqu区域数据，包括操作标志
				eiInfo.setByNode("inqu");
				EiCommunicator.send("DUHC230201", "savePlanInof", eiInfo, {
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

	//年份时间控件初始化
	IPLATUI.EFDatePicker = {
		"inqu_status-0-clockyear": {
			start: "decade",
			depth: "decade",
			change: function(e) { //获取改变值
				createPlanName(e);
			}
		},
		"inqu_status-0-copyyear": {
			start: "decade",
			depth: "decade",
			change: function(e) { //获取改变值
				createcopyName(e);
			}
		}
	};
});

function checkParam() {
	var departmentid = $("#inqu_status-0-departmentid").val();
	if (departmentid == null || departmentid == ""|| departmentid == "%") {
		NotificationUtil("厂部不能为空！", "warning");
		return false;
	}
	var monitorid = $("#inqu_status-0-monitorid").val();
	if (monitorid == null || monitorid == ""|| monitorid == "%") {
		NotificationUtil("监测类别不能为空！", "warning");
		return false;
	}
	var siteid = $("#inqu_status-0-siteid").val();
	if (siteid == null || siteid == ""|| siteid == "%") {
		NotificationUtil("站点名称不能为空！", "warning");
		return false;
	}
	var licensecount = $("#inqu_status-0-licensecount").val();
	if (licensecount == null || licensecount == "") {
		NotificationUtil("监测次数不能为空！", "warning");
		return false;
	}
	return true;
}
