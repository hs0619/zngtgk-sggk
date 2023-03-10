$(function() {
	IPLATUI.EFTab = {
		"info": {
			show: function(e) {
				$(e.contentElement).find("div[data-role='grid']").data("kendoGrid").refresh();
			}
		}
	};
	
	
	var eiInfo = new EiInfo();
	var departmentid = __eiInfo.get("inqu_status-0-departmentid");
	var siteid = __eiInfo.get("inqu_status-0-siteid");
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
				if(siteid!=null && siteid!=""&&siteid!="undefined"){
					IPLAT.EFSelect.value($("#inqu_status-0-siteid"), siteid);
				}else{
					var siteid_one=ei["blocks"]["site"]["rows"]["0"]["1"];
					IPLAT.EFSelect.value($("#inqu_status-0-siteid"), siteid_one);
				}
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
		},
		
		"inqu_status-0-factortype": {
			// 下拉选项改变之后触发
			change: function(e) { // 获取改变值
				var zstype = $("#inqu_status-0-zstype").val();
				initnewolChart(oldataGrid.eiInfo, this.value(), zstype)
			}
		},
		"inqu_status-0-zstype": {
			// 下拉选项改变之后触发
			change: function(e) { // 获取改变值
				var factortype = $("#inqu_status-0-factortype").val();
				initnewolChart(oldataGrid.eiInfo, factortype, this.value())
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
		var typeid = $("#inqu_status-0-typeid").val();
		if (Date.parse(enddate) != Date.parse(startdate)) {
			var days = (Date.parse(enddate) - Date.parse(startdate)) / (1 * 24 * 60 * 60 * 1000);
			if ("2011" == typeid) {//实时值
				if(parseFloat(days)>2){
					NotificationUtil("数据类型为实时值，时间间隔不能超过3天!", "warning");
					return false;
				}
			} else if ("2051" == typeid) {//分钟值
				if(parseFloat(days)>5){
					NotificationUtil("数据类型为分钟值，时间间隔不能超过6天!", "warning");
					return false;
				}
			} else if ("2061" == typeid) {//小时值
				if(parseFloat(days)>30){
					NotificationUtil("数据类型为小时值，时间间隔不能超过31天!", "warning");
					return false;
				}
			} else if ("2031" == typeid) {//日均值
				if(parseFloat(days)>730){
					NotificationUtil("数据类型为日均值，时间间隔不能超过2年!", "warning");
					return false;
				}
			}
		}

		IPLAT.progress($("body"), true);
		var ei = new EiInfo();
		ei.setByNode("inqu");
		ei.set("isquery", "true");
        ei.set("iscdp", "false");
		EiCommunicator.send("DUHC2002", "query", ei, {
			onSuccess: function(ei) {
				initolGrid(ei);
				initolChart(ei);
				IPLAT.progress($("body"), false);
			}, onFail: function(ei) {
				// 发生异常
				NotificationUtil("加载数据出错" + ei.getMsg(), "error");
				IPLAT.progress($("body"), false);
			}
		});
	});

	IPLATUI.EFGrid = {
		"oldata": {
			dataBound: function(e) {
				var titles = [], factors = [];
				var grid = e.sender;
				$.each(grid.columns, function(index, temcolumn) {
					var colname = temcolumn['name'];
					titles.push(colname);
					if (colname != "undefined" && colname != null && colname != "" && colname.indexOf("limit") == -1 && colname.indexOf("datatime") == -1) {
						factors.push(colname);
					}
				});
				$.each(grid.getDataItems(), function(index, item) {
					var tr = grid.element.find("tr[data-uid=" + item.uid + "]");
					//如果是停运期间，日期显示蓝色
                    var flag = item["flag"];
                    if("DY"==flag){
                        var td = tr.children("td:eq(1)");
                        td.css({
                            background: "#009cff"
                        });
                    }
					if (factors.length > 0) {
						for (var j = 0; j < factors.length; j++) {
							var temfactorid = factors[j];
							if (temfactorid != "undefined" && temfactorid != null && temfactorid != "") {

								if ((temfactorid.indexOf("zs") == -1)) {
									var temfactordatalimit = item[temfactorid + "limit"];
									if (temfactordatalimit != "undefined" && temfactordatalimit != null && temfactordatalimit != "") {
										var temfactordata = item[temfactorid];
										if (temfactordatalimit.indexOf("-") == -1) {
											if (parseFloat(temfactordata) >= parseFloat(temfactordatalimit)) {
												var td = tr.children("td:eq(" + titles.indexOf(temfactorid) + ")");
												td.css({
													color: "#F00"
												});
											}
										} else {
											var limitarr = temfactordatalimit.split("-");
											if ((parseFloat(temfactordata) <= parseFloat(limitarr[0])) || (parseFloat(temfactordata) >= parseFloat(limitarr[1]))) {
												var td = tr.children("td:eq(" + titles.indexOf(temfactorid) + ")");
												td.css({
													color: "#F00"
												});
											}
										}
									}
								} else {
									var temfactordatalimit = item[temfactorid.replace("zs", "") + "limit"];
									if (temfactordatalimit != "undefined" && temfactordatalimit != null && temfactordatalimit != "") {
										var temfactordata = item[temfactorid];
										if (temfactordatalimit.indexOf("-") == -1) {
											if (parseFloat(temfactordata) >= parseFloat(temfactordatalimit)) {
												var td = tr.children("td:eq(" + titles.indexOf(temfactorid) + ")");
												td.css({
													color: "#F00"
												});
											}
										} else {
											var limitarr = temfactordatalimit.split("-");
											if ((parseFloat(temfactordata) <= parseFloat(limitarr[0])) || (parseFloat(temfactordata) >= parseFloat(limitarr[1]))) {
												var td = tr.children("td:eq(" + titles.indexOf(temfactorid) + ")");
												td.css({
													color: "#F00"
												});
											}
										}
									}
								}
							}
						}
					}
				});
			},
			onSuccess: function(e) {
				if (e.type == "read") {
					initolChart(e.eiInfo);
				}
			},
			pageable:false,
			"exportGrid": {
				// exportFileName: "result2", // 默认值和blockId相同
				exportFileName: function(gridInstance) {
					var exportFileNamedesc = IPLAT.EFSelect.text($("#inqu_status-0-siteid")) + "(" + IPLAT.EFSelect.text($("#inqu_status-0-typeid")) + ")(" + $("#inqu_status-0-startdate").val() + "到" + $("#inqu_status-0-enddate").val() + ")";
					// 导出的文件名包含时间戳 yyyyMMddHHmmss
					return exportFileNamedesc;
				}
			}
		},
	}

	window.onload = function() {
		var presiteid = __eiInfo.get("siteid");
		if (presiteid != null && presiteid != "" && presiteid != "undefined") {
			var ei = new EiInfo();
			ei.set("inqu_status-0-siteid", presiteid);
			ei.set("inqu_status-0-typeid", "2011");
			ei.set("inqu_status-0-startdate", $("#inqu_status-0-startdate").val());
			ei.set("inqu_status-0-enddate", $("#inqu_status-0-enddate").val());
			ei.set("isquery", "true");
			EiCommunicator.send("DUHC2002", "query", ei, {
				onSuccess: function(ei) {
					initolGrid(ei);
					initolChart(ei);
					IPLAT.progress($("body"), false);
				}, onFail: function(ei) {
					// 发生异常
					NotificationUtil("加载数据出错" + ei.getMsg(), "error");
					IPLAT.progress($("body"), false);
				}
			});
		}

	};
});

function isEmpty(obj) {
	if (obj == null || obj == "undefined" || obj.trim() == "") {
		return true;
	} else {
		return false;
	}
}

function initolGrid(ei) {

	var gridHeight = $("#DUHC2002").height() - $("#inqu").height() - 115;
	var tagId = "oldata-" + kendo.guid();

	$("#ef_grid_oldata").empty();

	$("<div id='" + tagId + "'></div>").appendTo($("#ef_grid_oldata"));
	var extend = $.extend,
		__ctx__ = IPLATUI.CONTEXT_PATH;

	var defaultOption = {
		serviceName: "DUHC2002",
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
		blockId: "oldata",
		gridId: tagId
	});
	window['oldataGrid'] = IPLAT.Grid(masterOptions);

}

function initolChart(ei) {
	var colorlines = ["#50e3c2", "#4a90e2", "#137c7e", "#233449", "#417505", "#f5a623", "#8b572a", "#540697", "#7e137a", "#7e5813", "#deab8a", "#426ab3", "#fab27b", "#78a355", "#6950a1", "#f3715c", "#ffd400", "#1b315e"];
	//var colorlines = ["#2ec7c9", "#b6a2de", "#5ab1ef", "#ffb980", "#d87a80", "#8d98b3", "#e5cf0d", "#97b552", "#95706d", "#dc69aa"];
	if (ei["blocks"]["olinfo"] != null) {
		var factortype = [];
		factortype.push({ "valueField": "%", "textField": "全部" });
		$.each(ei["blocks"]["olinfo"]["rows"], function(i, obj) {
			factortype.push({ "valueField": obj[0], "textField": obj[5] });
		});
		var dataSource = new kendo.data.DataSource({
			data: factortype
		});
		IPLAT.EFSelect.setDataSource($("#inqu_status-0-factortype"), dataSource);
		IPLAT.EFSelect.value($("#inqu_status-0-factortype"), "%");
		IPLAT.EFSelect.value($("#inqu_status-0-zstype"), "sc");
	};
	var chart = document.getElementById('olchart');
	chart.style.width = window.innerWidth * 0.8 - 50 + 'px';
	var legendArray = new Array();// 监控数据的chart的legend数组
	var seriesArray = new Array();// 监控数据的chart的series数组
	var xcategory = new Array();// 监控数据的chart的xcategory数组
	var olinfoBlock = ei.getBlock("olinfo");
	var oldataBlock = ei.getBlock("oldata");
	if (olinfoBlock != null && olinfoBlock.getRows().length > 0) {
		for (var i = 0; i < olinfoBlock.getRows().length; i++) {
			var factoridchart = "ol" + olinfoBlock.getCell(i, "factorid");
			var factornamechart = olinfoBlock.getCell(i, "factorname");
			legendArray.push(factornamechart);
			var ycategory = new Array();
			if (oldataBlock != null && oldataBlock.getRows().length > 0) {
				for (var j = oldataBlock.getRows().length; j > 0; j--) {
					if (i == 0) {
						xcategory.push(oldataBlock.getCell(j - 1, "datatime"));
					}
					ycategory.push(oldataBlock.getCell(j - 1, factoridchart));
				}
			}
			seriesArray.push({
				name: factornamechart,
				type: 'line',
				itemStyle: {
					color: colorlines[i]
				},
				data: ycategory,
				markPoint: {
					data: [
						{ type: 'max', name: '最大值' },
						{ type: 'min', name: '最小值' }
					]
				}
			});
		}
	}

	// 基于准备好的dom，初始化echarts实例
	var jsChart = echarts.init(chart);
	var chartoption = {
		legend: [{
			left: "left",
			data: legendArray
		}],
		grid: {
			width: "85%",
			containLabel: true
		},
		toolbox: {
			show: true,
			feature: {
				magicType: { type: ['line', 'bar'] },
				saveAsImage: {}
			}
		},
		tooltip: {
			trigger: 'axis',
			axisPointer: {
				type: 'cross',
				animation: false,
				label: {
					backgroundColor: '#505765'
				}
			}
		},
		calculable: true,
		xAxis: [{
			type: 'category',
			boundaryGap: false,
			data: xcategory
		}],
		yAxis: [{
			type: 'value'
		}],
		dataZoom: [{
			show: true
		}, {
			type: 'inside',
			start: 0,
			end: 10
		}],
		series: seriesArray
	};

	jsChart.setOption(chartoption, true);
}
//根据因子和折算类型切换图表
function initnewolChart(ei, type, zstype) {
	var colorlines = ["#50e3c2", "#4a90e2", "#137c7e", "#233449", "#417505", "#f5a623", "#8b572a", "#540697", "#7e137a", "#7e5813"];
	//var colorlines = ["#2ec7c9", "#b6a2de", "#5ab1ef", "#ffb980", "#d87a80", "#8d98b3", "#e5cf0d", "#97b552", "#95706d", "#dc69aa"];
	if (ei != null && type != "" && type != "undefined") {
		var chart = document.getElementById('olchart');
		chart.style.width = window.innerWidth * 0.8 - 50 + 'px';
		var legendArray = new Array();// 监控数据的chart的legend数组
		var seriesArray = new Array();// 监控数据的chart的series数组
		var xcategory = new Array();// 监控数据的chart的xcategory数组
		var olinfoBlock = ei.getBlock("olinfo");
		var oldataBlock = ei.getBlock("oldata");
		if (type == "%") {
			if (olinfoBlock != null && olinfoBlock.getRows().length > 0) {
				for (var i = 0; i < olinfoBlock.getRows().length; i++) {
					var factoridchart = "ol" + olinfoBlock.getCell(i, "factorid");
					var factornamechart = olinfoBlock.getCell(i, "factorname");
					legendArray.push(factornamechart);
					var ycategory = new Array();
					if (oldataBlock != null && oldataBlock.getRows().length > 0) {
						/*for (var j = oldataBlock.getRows().length; j > 0; j--) {
							if(i == 0){
								xcategory.push(oldataBlock.getCell(j - 1, "datatime"));
							}
							ycategory.push(oldataBlock.getCell(j - 1, factoridchart));
						}*/
						for (var j = oldataBlock.getRows().length; j > 0; j--) {
							//xcategory.push(oldataBlock.getCell(j - 1, "datatime"));
							if (i == 0) {
								xcategory.push(oldataBlock.getCell(j - 1, "datatime"));
							}
							var datavalue = "";
							if ("zs" == zstype) {
								datavalue = oldataBlock.getCell(j - 1, "olzs" + olinfoBlock.getCell(i, "factorid"));
							} else {
								datavalue = oldataBlock.getCell(j - 1, factoridchart);
							}
							ycategory.push(datavalue);
						}
					}
					seriesArray.push({
						name: factornamechart,
						type: 'line',
						itemStyle: {
							color: colorlines[i]
						},
						data: ycategory,
						markPoint: {
							data: [
								{ type: 'max', name: '最大值' },
								{ type: 'min', name: '最小值' }
							]
						}
					});
				}
			}
		} else {
			if (olinfoBlock != null && olinfoBlock.getRows().length > 0) {
				for (var i = 0; i < olinfoBlock.getRows().length; i++) {
					var factoridchart = "ol" + olinfoBlock.getCell(i, "factorid");
					if (factoridchart == ("ol" + type)) {
						//var zstype = $("#inqu_status-0-zstype").val();
						if (factoridchart == "ol001") {//pH值
							var factornamechart = olinfoBlock.getCell(i, "factorname");
							legendArray.push(factornamechart);

							var ycategory = new Array();
							var hlimitycategory = new Array();
							var llimitycategory = new Array();
							if (oldataBlock != null && oldataBlock.getRows().length > 0) {
								for (var j = oldataBlock.getRows().length; j > 0; j--) {
									xcategory.push(oldataBlock.getCell(j - 1, "datatime"));
									ycategory.push(oldataBlock.getCell(j - 1, factoridchart));
									var limitdata = oldataBlock.getCell(j - 1, ("ol" + type + "limit"));
									if (limitdata.indexOf("-") > 0) {
										hlimitycategory.push(limitdata.substring(2, 3));
										llimitycategory.push(limitdata.substring(0, 1));
									}

								}
							}
							seriesArray.push({
								name: factornamechart,
								type: 'line',
								itemStyle: {
									color: colorlines[i]
								},
								data: ycategory,
								markPoint: {
									data: [
										{ type: 'max', name: '最大值' },
										{ type: 'min', name: '最小值' }
									]
								}
							});
							if (hlimitycategory.length > 0 && hlimitycategory[0] != "") {
								legendArray.push("高限值");
								seriesArray.push({
									name: "高限值",
									type: 'line',
									itemStyle: {
										color: "#FF0000"
									},
									data: hlimitycategory,
									markPoint: {
										data: [
											{ type: 'max', name: '最大值' },
											{ type: 'min', name: '最小值' }
										]
									}
								});
							}
							if (llimitycategory.length > 0 && llimitycategory[0] != "") {
								legendArray.push("低限值");
								seriesArray.push({
									name: "低限值",
									type: 'line',
									itemStyle: {
										color: "#FF0000"
									},
									data: llimitycategory,
									markPoint: {
										data: [
											{ type: 'max', name: '最大值' },
											{ type: 'min', name: '最小值' }
										]
									}
								});
							}
						} else {
							var factornamechart = olinfoBlock.getCell(i, "factorname");
							legendArray.push(factornamechart);

							var ycategory = new Array();
							var hlimitycategory = new Array();
							if (oldataBlock != null && oldataBlock.getRows().length > 0) {
								for (var j = oldataBlock.getRows().length; j > 0; j--) {
									xcategory.push(oldataBlock.getCell(j - 1, "datatime"));
									var datavalue = "";
									if ("zs" == zstype) {
										datavalue = oldataBlock.getCell(j - 1, "olzs" + olinfoBlock.getCell(i, "factorid"));
									} else {
										datavalue = oldataBlock.getCell(j - 1, factoridchart);
									}
									ycategory.push(datavalue);
									hlimitycategory.push(oldataBlock.getCell(j - 1, ("ol" + type + "limit")));
								}
							}
							seriesArray.push({
								name: factornamechart,
								type: 'line',
								itemStyle: {
									color: colorlines[i]
								},
								data: ycategory,
								markPoint: {
									data: [
										{ type: 'max', name: '最大值' },
										{ type: 'min', name: '最小值' }
									]
								}
							});
							if (hlimitycategory.length > 0 && hlimitycategory[0] != "") {
								legendArray.push("高限值");
								seriesArray.push({
									name: "高限值",
									type: 'line',
									itemStyle: {
										color: "#FF0000"
									},
									data: hlimitycategory,
									markPoint: {
										data: [
											{ type: 'max', name: '最大值' },
											{ type: 'min', name: '最小值' }
										]
									}
								});
							}

						}
					}
				}
			}
		}

		// 基于准备好的dom，初始化echarts实例
		var jsChart = echarts.init(chart);

		var chartoption = {
			legend: [{
				left: "left",
				data: legendArray
			}],
			grid: {
				width: "85%",
				containLabel: true
			},
			toolbox: {
				show: true,
				feature: {
					magicType: { type: ['line', 'bar'] },
					saveAsImage: {}
				}
			},
			tooltip: {
				trigger: 'axis',
				axisPointer: {
					type: 'cross',
					animation: false,
					label: {
						backgroundColor: '#505765'
					}
				}
			},
			calculable: true,
			xAxis: [{
				type: 'category',
				boundaryGap: false,
				data: xcategory,

			}],
			yAxis: [{
				type: 'value'
			}],
			dataZoom: [{
				show: true
			}, {
				type: 'inside',
				start: 0,
				end: 10
			}],
			series: seriesArray
		};

		jsChart.setOption(chartoption, true);
	};
}

