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
		eiInfo.set("inqu_status-0-isartificial", "1");//是否人工	1是0否
		eiInfo.set("inqu_status-0-classifyid", $("#inqu_status-0-plantype").val().substring(0, 2));
		eiInfo.set("inqu_status-0-siteid", "");
		eiInfo.set("inqu_status-0-departmentid_site", $("#inqu_status-0-departmentid").val())
		eiInfo.setByNode("inqu");
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
		"inqu_status-0-plantype": {
			// 下拉选项改变之后触发
			change: function(e) { //获取改变值
				var eiInfo = new EiInfo();
				getSite(eiInfo);
			}
		},
		
		"inqu_status-0-departmentid": {
			// 下拉选项改变之后触发
			change: function(e) { // 获取改变值
				var eiInfo = new EiInfo();
				getSite(eiInfo);
			}
		}
	}
	
	$("#QUERY").on("click", function(e) {
		var starttime = $("#inqu_status-0-starttime").val();
		var endtime = $("#inqu_status-0-endtime").val();
		if (isEmpty(starttime)) {
			NotificationUtil("开始时间不能为空!", "warning");
			return false;
		}
		if (isEmpty(endtime)) {
			NotificationUtil("结束时间不能为空!", "warning");
			return false;
		}
		if (starttime > endtime) {
			NotificationUtil("开始时间必须早于结束时间!", "warning");
			return false;
		}
		IPLAT.progress($("body"), true);
		var ei = new EiInfo();
		ei.setByNode("inqu");
		ei.set("isquery", "true");
		EiCommunicator.send("DUHC2303", "query", ei, {
			onSuccess: function(ei) {
			    if(ei.getStatus()==1){
                    initolGrid(ei);
                }else{
                    $("#ef_grid_ardata").empty();
                    NotificationUtil(ei.getMsg(), "warning");
                }
				IPLAT.progress($("body"), false);
			}, onFail: function(ei) {
				// 发生异常
				NotificationUtil("加载数据出错" + ei.getMsg(), "error");
				IPLAT.progress($("body"), false);
			}
		});
	});

	IPLATUI.EFGrid = {
		"ardata": {
			columns: [
				{
					field: "itemtime",
					title: "日期",
					locked: true,
				},
				{
					field: "sitename",
					title: "监测点名称",
					locked: true,
				},

			],
			dataBound: function(e) {
				var titles = [], factors = [];
				var grid = e.sender;
				$.each(grid.columns, function(index, temcolumn) {
					var colname = temcolumn['name'];
					titles.push(colname);
					if (colname != "undefined" && colname != null && colname != "") {
						factors.push(colname);
					}
				});
				$.each(grid.getDataItems(), function(index, item) {
					var tr = grid.element.find("tr[data-uid=" + item.uid + "]");
					var offline = item["offline"];
					/*if (offline == "1") {
						var td = tr.children("td:eq(2)");
						td.css({
							background: "yellow"
						});
					}*/
					if (factors.length > 0) {
						for (var j = 0; j < factors.length; j++) {
							var temfactorid = factors[j];
							if (temfactorid != "undefined" && temfactorid != null && temfactorid != "") {
								var temfactordatalimit = item[temfactorid + "limit"];//限值
								if (temfactordatalimit != "undefined" && temfactordatalimit != null && temfactordatalimit != "") {
									var temfactordata = item[temfactorid];
									if (temfactordatalimit.indexOf("~") == -1) {//废气
										if (parseFloat(temfactordata) >= parseFloat(temfactordatalimit)) {
											var td = tr.children("td:eq(" + titles.indexOf(temfactorid) + ")");
											td.css({
												background: "yellow",
												color: "#F00"
											});
										}
									} else {//废水
										var limitarr = temfactordatalimit.split("~");
										if ((parseFloat(temfactordata) <= parseFloat(limitarr[0])) || (parseFloat(temfactordata) >= parseFloat(limitarr[1]))) {
											var td = tr.children("td:eq(" + titles.indexOf(temfactorid) + ")");
											td.css({
												background: "yellow",
												color: "#F00"
											});
										}
									}

								}

							}
						}
					}
				});
			},
		}
	}

	window.onload = function() {
		$("#QUERY").click();
		setInterval(function() {
			//$("#QUERY").click();
		}, 180000);
	};


	//导出
	$("#EXPORT").on("click", function(e) {
		var popWindow = $("#export");
		var url = IPLATUI.CONTEXT_PATH + "/web/DUHC230302";
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

	//因子单价配置
	$("#PRICE").on("click", function(e) {
		var popWindow = $("#config");
		var url = IPLATUI.CONTEXT_PATH + "/web/DUHC230301";
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
});

function initolGrid(ei) {
	var gridHeight = $("#main-container").height() - $("#inqu").height() - 115;
	var tagId = "artificial-" + kendo.guid();

	$("#ef_grid_ardata").empty();

	$("<div id='" + tagId + "'></div>").appendTo($("#ef_grid_ardata"));
	var extend = $.extend,
		__ctx__ = IPLATUI.CONTEXT_PATH;

	var defaultOption = {
		serviceName: "DUHC2303",
		autoBind: false,
		autoDraw: "yes",
		autoFit: true,
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
		blockId: "ardata",
		gridId: tagId
	});
	window['ardataGrid'] = IPLAT.Grid(masterOptions);

}

function isEmpty(obj) {
	if (obj == null || obj == "undefined" || obj.trim() == "") {
		return true;
	} else {
		return false;
	}
}