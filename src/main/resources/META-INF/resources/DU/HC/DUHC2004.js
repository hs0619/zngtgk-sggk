$(function() {
	var eiInfo = new EiInfo();
	var departmentid = __eiInfo.get("inqu_status-0-departmentid");
	if (departmentid != "%") {
		$("#inqu_status-0-departmentid").attr("disabled", true);
	}

	//需要的初始化下拉框（1、厂部 2、厂部+监测类型 3、厂部+监测因子类型+站点 4、厂部+监测因子类型+监测点类型
	eiInfo.set("haveType", "4");

	//监测类型条件
	//eiInfo.set("monitorid","'01'");//监测类型
	eiInfo.set("mon_isonline", "'1'");//是否在线 1是0否
	//eiInfo.set("istotal","'1'");//是否统计

	//厂部条件
	//eiInfo.set("departmentid","'1'");//厂部id
	//eiInfo.set("type","P1");//单位类型 D1:厂部，P1:工序
	//eiInfo.set("level","2");//等级
	eiInfo.set("totalplan", "'1'");//标志位

	//站点条件
	//eiInfo.set("siteid","'SI00000001'");//站点id
	//eiInfo.set("isartificial","1");//是否人工 1是0否
	eiInfo.set("site_isonline", "1");//是否在线	1是0否
	//eiInfo.set("classifyid","'01','02'");//人工监测类型
	//eiInfo.set("monitorid","01");//在线监测类型
	eiInfo.set("departmentid_site", departmentid);//厂部
	//eiInfo.set("procid","2");//工序

	EiCommunicator.send("DUHA01", "initSelectData", eiInfo, {
		onSuccess: function(ei) {
			if (ei["blocks"]["porttype"] != null) {
				var porttypes = [];
				//porttypes.push({ "valueField": "%", "textField": "全部" });
				$.each(ei["blocks"]["porttype"]["rows"], function(i, obj) {
					porttypes.push({ "valueField": obj[0], "textField": obj[1] });
				});
				var dataSource = new kendo.data.DataSource({
					data: porttypes
				});
				IPLAT.EFSelect.setDataSource($("#inqu_status-0-porttypeid"), dataSource);
				IPLAT.EFSelect.value($("#inqu_status-0-porttypeid"), "%");
			};
			
			/*if (ei["blocks"]["monitor"] != null) {
				var monitors = [];
				//monitors.push({ "valueField": "%", "textField": "全部" });

				$.each(ei["blocks"]["monitor"]["rows"], function(i, obj) {
					monitors.push({ "valueField": obj[1], "textField": obj[0] });
				});
				var dataSource = new kendo.data.DataSource({
					data: monitors
				});
				IPLAT.EFSelect.setDataSource($("#inqu_status-0-monitorid"), dataSource);
				IPLAT.EFSelect.value($("#inqu_status-0-monitorid"), "01");
			};*/

			if (ei["blocks"]["depart"] != null) {
				var departs = [];
				if (departmentid == "%") {
					departs.push({ "valueField": "%", "textField": "全部" });
				}
				$.each(ei["blocks"]["depart"]["rows"], function(i, obj) {
					departs.push({ "valueField": obj[1], "textField": obj[0] });
				});
				var dataSource = new kendo.data.DataSource({
					data: departs
				});
				IPLAT.EFSelect.setDataSource($("#inqu_status-0-departmentid"), dataSource);
				IPLAT.EFSelect.value($("#inqu_status-0-departmentid"), departmentid);
			};

			if (ei["blocks"]["site"] != null) {
				var sites = [];
				//sites.push({ "valueField": "%", "textField": "全部" });

				$.each(ei["blocks"]["site"]["rows"], function(i, obj) {
					sites.push({ "valueField": obj[1], "textField": obj[0] });
				});
				var dataSource = new kendo.data.DataSource({
					data: sites
				});
				IPLAT.EFSelect.setDataSource($("#inqu_status-0-siteid"), dataSource);
				var siteid_one=ei["blocks"]["site"]["rows"]["0"]["1"];
				IPLAT.EFSelect.value($("#inqu_status-0-siteid"), siteid_one);
			};
		}, onFail: function(ei) {
			// 发生异常
			NotificationUtil("加载数据出错" + ei.getMsg(), "error");
		}
	});


	//获取站点
	function getSite(eiInfo) {
		eiInfo.set("inqu_status-0-site_isonline", "1");//是否在线	1是0否
		eiInfo.set("inqu_status-0-siteid", "");
		eiInfo.set("inqu_status-0-departmentid_site", $("#inqu_status-0-departmentid").val())
		EiCommunicator.send("DUHA01", "getSite", eiInfo, {
			onSuccess: function(ei) {
				if (ei["blocks"]["site"] != null) {
					var sites = [];
					//sites.push({ "valueField": "%", "textField": "全部" });
	
					$.each(ei["blocks"]["site"]["rows"], function(i, obj) {
						sites.push({ "valueField": obj[1], "textField": obj[0] });
					});
					var dataSource = new kendo.data.DataSource({
						data: sites
					});
					IPLAT.EFSelect.setDataSource($("#inqu_status-0-siteid"), dataSource);
					var siteid_one=ei["blocks"]["site"]["rows"]["0"]["1"];
					IPLAT.EFSelect.value($("#inqu_status-0-siteid"), siteid_one);
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
		"inqu_status-0-porttypeid": {
			// 下拉选项改变之后触发
			change: function(e) { // 获取改变值
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
		var siteid = $("#inqu_status-0-siteid").val();
		if (isEmpty(siteid)) {
			NotificationUtil("请选择一个监测点!", "warning");
			return false;
		}
		var typeid = $("#inqu_status-0-typeid").val();
		if (isEmpty(typeid)) {
			NotificationUtil("请选择一个数据类型!", "warning");
			return false;
		}
		var startdate = $("#inqu_status-0-startdate").val();
		var enddate = $("#inqu_status-0-enddate").val();
		if (isEmpty(startdate)) {
			NotificationUtil("开始日期不能为空!", "warning");
			return false;
		}
		if (isEmpty(enddate)) {
			NotificationUtil("结束日期不能为空!", "warning");
			return false;
		}
		if (startdate > enddate) {
			NotificationUtil("开始日期必须早于结束日期!", "warning");
			return false;
		}
		if (typeid == "2011") {
			if (startdate.substring(0, 7) != enddate.substring(0, 7)) {
				NotificationUtil("数据类型为实时值时，请选择同一月份!", "warning");
				return false;
			}
		} else if (typeid == "2051") {
			if (startdate.substring(0, 7) != enddate.substring(0, 7)) {
				NotificationUtil("数据类型为分钟值时，请选择同一月份!", "warning");
				return false;
			}
		} else if (typeid == "2061") {
			if (startdate.substring(0, 4) != enddate.substring(0, 4)) {
				NotificationUtil("数据类型为小时值时，请选择同一年份!", "warning");
				return false;
			}
		} else if (typeid == "2031") {
			if (startdate.substring(0, 4) != enddate.substring(0, 4)) {
				NotificationUtil("数据类型为日均时，请选择同一年份!", "warning");
				return false;
			}
		}

		IPLAT.progress($("body"), true);
		var ei = new EiInfo();
		ei.setByNode("inqu");
		EiCommunicator.send("DUHC2004", "query", ei, {
			onSuccess: function(ei) {
				initolDBGrid(ei);
				IPLAT.progress($("body"), false);
			}, onFail: function(ei) {
				// 发生异常
				NotificationUtil("加载数据出错" + ei.getMsg(), "error");
				IPLAT.progress($("body"), false);
			}
		});

	});

	IPLATUI.EFGrid = {
		"oldbdata": {
			"exportGrid": {
				// exportFileName: "result2", // 默认值和blockId相同
				exportFileName: function(gridInstance) {
					var exportFileNamedesc = IPLAT.EFSelect.text($("#inqu_status-0-siteid")) + "(" + IPLAT.EFSelect.text($("#inqu_status-0-typeid")) + ")(" + $("#inqu_status-0-startdate").val() + "到" + $("#inqu_status-0-enddate").val() + ")";
					// 导出的文件名包含时间戳 yyyyMMddHHmmss
					return exportFileNamedesc;
				}
			},
			columns: [
				{
					field: "datatime",
					title: "日期",
					locked: true,
				},

			],
		},

	}

	//导出月报
	$("#EXPORTMR").on("click", function() {

		var siteid = $("#inqu_status-0-siteid").val();
		if (isEmpty(siteid)) {
			NotificationUtil("请选择一个监测点!", "warning");
			return false;
		}
		var typeid = $("#inqu_status-0-typeid").val();
		if (isEmpty(typeid)) {
			NotificationUtil("请选择一个数据类型!", "warning");
			return false;
		}
		var startdate = $("#inqu_status-0-startdate").val();
		var enddate = $("#inqu_status-0-enddate").val();
		if (typeid == "2011") {
			if (startdate.substring(0, 7) != enddate.substring(0, 7)) {
				NotificationUtil("数据类型为实时值时，请选择同一月份!", "warning");
				return false;
			}
		} else if (typeid == "2051") {
			if (startdate.substring(0, 7) != enddate.substring(0, 7)) {
				NotificationUtil("数据类型为分钟值时，请选择同一月份!", "warning");
				return false;
			}
		} else if (typeid == "2061") {
			if (startdate.substring(0, 4) != enddate.substring(0, 4)) {
				NotificationUtil("数据类型为小时值时，请选择同一年份!", "warning");
				return false;
			}
		} else if (typeid == "2031") {
			if (startdate.substring(0, 4) != enddate.substring(0, 4)) {
				NotificationUtil("数据类型为日均时，请选择同一年份!", "warning");
				return false;
			}
		}

		IPLAT.confirm({
			message: '<b>确定导出数据吗？</b>',
			okFn: function(e) {
				IPLAT.progress($("body"), true);
				var ei = new EiInfo();
				ei.setByNode("inqu");
				EiCommunicator.send("DUHC2004", "exportExcel", ei, {
					onSuccess: function(ei) {
						var filepath = ei.get("filepath");
						if (filepath != "undefined" && filepath != null && filepath != "") {
							var url = IPLATUI.CONTEXT_PATH + "/upload/download.jsp?path=" + encodeURI(filepath);
							window.open(url);
						}
						NotificationUtil(ei.getMsg());
						IPLAT.progress($("body"), false);
					}, onFail: function(ei) {
						// 发生异常
						NotificationUtil("加载数据出错" + ei.getMsg(), "error");
						IPLAT.progress($("body"), false);
					}
				});

			},
			cancelFn: function(e) { IPLAT.NotificationUtil('取消该操作!'); },
			title: '操作提示',
			minWidth: 200
		});
	});
});



function isEmpty(obj) {
	if (obj == null || obj == "undefined" || obj.trim() == "") {
		return true;
	} else {
		return false;
	}
}


function initolDBGrid(ei) {
	var gridHeight = $("#main-container").height() - $("#inqu").height() - 115;
	var tagId = "oldbdata-" + kendo.guid();

	$("#ef_grid_oldbdata").empty();

	$("<div id='" + tagId + "'></div>").appendTo($("#ef_grid_oldbdata"));
	var extend = $.extend,
		__ctx__ = IPLATUI.CONTEXT_PATH;

	var defaultOption = {
		serviceName: "DUHC2004",
		autoBind: false,
		autoDraw: "yes",
		autoFit: false,
		pageable: false,
		needAuth: false,
		copyToAdd: false,
		readonly: false,
		enable: false,
		rowNo: true,
		showCount: false,
		checkMode: "hidden,row",
		url: __ctx__ + "/service",
		eiInfo: ei,
		toolbarConfig: { add: false, save: false, cancel: false, delete: false },
		height: gridHeight
	};

	IPLAT.EFGrid[tagId] = {
		columns: [],
		readonlyColumns: []
	};

	var masterOptions = extend({}, defaultOption, {
		tagId: tagId,
		blockId: "oldbdata",
		gridId: tagId
	});
	window['oldbdataGrid'] = IPLAT.Grid(masterOptions);

}

function isEmpty(obj) {
	if (obj == null || obj == "undefined" || obj.trim() == "") {
		return true;
	} else {
		return false;
	}
}
