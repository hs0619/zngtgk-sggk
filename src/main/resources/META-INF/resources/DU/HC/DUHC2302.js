$(function() {
	var eiInfo = new EiInfo();
	var departmentid = __eiInfo.get("inqu_status-0-departmentid");
	if (departmentid != "%") {
		$("#inqu_status-0-departmentid").attr("disabled", true);
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
	//eiInfo.set("classifyid","'01','02'");//人工监测类型
	//eiInfo.set("monitorid","01");//在线监测类型
	eiInfo.set("departmentid_site", departmentid);//厂部
	//eiInfo.set("procid","2");//工序

	EiCommunicator.send("DUHA01", "initSelectData", eiInfo, {
		onSuccess: function(ei) {
			if (ei["blocks"]["monitor"] != null) {
				var monitors = [];
				monitors.push({ "valueField": "%", "textField": "全部" });

				$.each(ei["blocks"]["monitor"]["rows"], function(i, obj) {
					monitors.push({ "valueField": obj[0], "textField": obj[1] });
				});
				var dataSource = new kendo.data.DataSource({
					data: monitors
				});
				IPLAT.EFSelect.setDataSource($("#inqu_status-0-monitorid"), dataSource);
				IPLAT.EFSelect.value($("#inqu_status-0-monitorid"), "%");
			};

			if (ei["blocks"]["depart"] != null) {
				var departs = [];
				if (departmentid == "%") {
					departs.push({ "valueField": "%", "textField": "全部" });
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
				sites.push({ "valueField": "%", "textField": "全部" });

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
					sites.push({ "valueField": "%", "textField": "全部" });
	
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
		}
	}

	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.page(1);
	});
	
	//复制计划
	$(document.body).on("click", "#COPYPLAN", function(e) {
		var copyyear=$("#inqu_status-0-clockyear").val();
		if(copyyear==""||copyyear==null){
			NotificationUtil("请填写复制年份！", "warning");
			return ;
		}
		IPLAT.progress($("body"), true);
		var lastyear=copyyear-1;
		var msg="确定将"+lastyear+"年份的监测计划复制成"+copyyear+"年的吗？";
		IPLAT.confirm({
			message: msg,
			okFn: function(e) {
				var eiInfo = new EiInfo();
				eiInfo.set("copyyear",copyyear);
				eiInfo.set("lastyear",lastyear);
				eiInfo.setByNode("inqu");
				EiCommunicator.send("DUHC230201", "copyplan", eiInfo, {
					onSuccess: function(ei) {
						if (ei.getStatus() == "1") {
							NotificationUtil(ei.getMsg());
						} else {
							NotificationUtil(ei.getMsg(), "error");
						}
						IPLAT.progress($("body"), false);
					}, onFail: function(ei) {
						alert("计划复制失败");
						IPLAT.progress($("body"), false);
					}
					
				})

			},
			cancelFn: function(e) { 
				IPLAT.NotificationUtil('取消该操作!'); 
				IPLAT.progress($("body"), false);
			},
			title: '操作提示',
			minWidth: 200
		});
	});
	//限值配置
	/*$("#LIMIT").on("click", function(e) {
		var popWindow = $("#showHM");
		var url = IPLATUI.CONTEXT_PATH + "/web/DUHC230203";
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
	});*/


	IPLATUI.EFGrid = {
		"result": {
			pageable: {
				pageSize: 500,
				pageSizes:[100,200,500,1000]
			},
			height: function(){
				return ($("#main-container").height() - $("#inqu").height() - 115);
			},
			//因子选择窗口
			columns: [
				{
					field: "operationedit",
					title: "计划编辑",
					encoded:  false,
					template: function(dateItem) {
						return '<div style="text-align: center"><input value="计划编辑" class="k-button k-button-details" type="button" align="center" onclick="showPlanDetail(\'' + dateItem.planid+ '\')"/></div>';
					}
				},
				{
					field: "operation",
					title: "因子配置",
					encoded: false,
					template: function(dateItem) {
						return '<div style="text-align: center"><input value="因子配置" class="k-button k-button-details" type="button" align="center" onclick="showFactorDetail(\''  + dateItem.siteid + '\',\''  + dateItem.planid + '\',\''  + dateItem.monitorid + '\')"/></div>';
					}
				},
			],
			beforeAdd: function(e) {
				e.preventDefault();
				var oprationType = "insert";
				var popWindow = $("#showHM");
				var planid =  "";//新增没有planid
				var url = IPLATUI.CONTEXT_PATH + "/web/DUHC230201?oprationType=" + oprationType + "&planid=" + planid;
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
			},
		}
	};

	IPLATUI.EFDatePicker = {
		"inqu_status-0-clockyear": {
			start: "decade",
			depth: "decade"
		},
		"inqu_status-0-copyyear": {
			start: "decade",
			depth: "decade"
		}
	};

	//弹出计划编辑框
	showPlanDetail = function(planid) {
		var popWindow = $("#showHM");
		var url = IPLATUI.CONTEXT_PATH + "/web/DUHC230201?planid="+ planid;
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

	};

	//因子配置
	showFactorDetail = function(siteid,planid,monitorid) {
		var popWindow = $("#showHM");
		var url = IPLATUI.CONTEXT_PATH + "/web/DUHC230202?siteid=" + siteid+"&planid="+planid+"&monitorid="+monitorid;
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


	};
	
	

	IPLATUI.EFWindow = {
		"showHM": {
			close: function(e) {
				resultGrid.dataSource.page(1);
			}
		}
	};
});