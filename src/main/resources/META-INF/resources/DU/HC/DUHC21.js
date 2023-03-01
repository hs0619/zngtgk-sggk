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
			
			if (ei["blocks"]["monitor"] != null) {
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
			};

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
		eiInfo.set("inqu_status-0-site_isonline", "1");//是否在线	1是0否
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
		IPLAT.progress($("body"), true);
		var ei = new EiInfo();
		ei.setByNode("inqu");
		ei.set("isquery", "true");
		EiCommunicator.send("DUHC21", "query", ei, {
			onSuccess: function(ei) {
				initolGrid(ei);
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
                    if (colname != "undefined" && colname != null && colname != "" && colname.indexOf("limit") == -1) {
                        factors.push(colname);
                    }
                });
				$.each(grid.getDataItems(), function(index, item) {
					var tr = grid.element.find("tr[data-uid=" + item.uid + "]");
					if(factors.length > 0){
                         for (var j = 0; j < factors.length; j++) {
                             var temfactorid = factors[j];
                             if(temfactorid != "undefined" && temfactorid != null && temfactorid != ""){
                                 var temfactordatalimit = item[temfactorid+ "limit"];
                                 if(temfactordatalimit != "undefined" && temfactordatalimit != null && temfactordatalimit != ""){
                                     var temfactordata = item[temfactorid];
                                     if(temfactordatalimit.indexOf("-") == -1){
                                         if(parseFloat(temfactordata) >= parseFloat(temfactordatalimit)){
                                             var td = tr.children("td:eq("+titles.indexOf(temfactorid)+")");
                                             td.css({
                                                 color: "#F00"
                                             });
                                         }
                                     }else{
                                         var limitarr = temfactordatalimit.split("-");
                                         if((parseFloat(temfactordata) <= parseFloat(limitarr[0])) || (parseFloat(temfactordata) >= parseFloat(limitarr[1]))){
                                             var td = tr.children("td:eq("+titles.indexOf(temfactorid)+")");
                                             td.css({
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
			onRowDblClick: function(e) {
				var siteid = e.model.siteid;
				IPLAT.openForm("DUHC2002", "siteid=" + siteid);

			},
			pageable: {
				pageSize: 50,
				pageSizes: [50, 100, 200, 500]
			},
			loadComplete:  function(grid) {
				grid.dataSource.read();
				grid.refresh();
			}
		}
	}

	window.onload = function() {
		setTimeout(function() {
			$("#QUERY").click();
		}, 1000);
		setInterval(function()  {
			$("#QUERY").click();
		}, 180000);
	};
});

function initolGrid(ei) {
	var gridHeight = $("#main-container").height() - $("#inqu").height() - 115;
	var tagId = "oldata-" + kendo.guid();

	$("#ef_grid_oldata").empty();

	$("<div id='" + tagId + "'></div>").appendTo($("#ef_grid_oldata"));
	var extend = $.extend,
		__ctx__ = IPLATUI.CONTEXT_PATH;

	var defaultOption = {
		serviceName: "DUHC21",
		autoBind: false,
		autoDraw: "yes",
		needAuth: false,
		copyToAdd: false,
		readonly: false,
		enable: false,
		rowNo: true,
		showCount: false,
		checkMode: "hidden,row",
		url: __ctx__ + "/service",
		eiInfo: ei,
		toolbarConfig: { add:  false, save: false, cancel: false, delete: false },
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