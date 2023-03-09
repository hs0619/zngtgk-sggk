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
		resultGrid.dataSource.page(1);
	});

	//导出
	$("#EXPORT").on("click", function() {
		var monitorid=$("#inqu_status-0-monitorid").val();
		var plantype=$("#inqu_status-0-plantype").val();
		if(monitorid=="%"||plantype=="%"){
			NotificationUtil("导出前请选择监测类型和计划类型！", "warning");
			return;
		}
		var eiInfo = new EiInfo();
		eiInfo.setByNode("inqu");
		IPLAT.confirm({
			message: '<b>确定根据查询条件报表吗？</b>',
			okFn: function(e) {
				IPLAT.progress($("body"), true);
				EiCommunicator.send("DUHC23", "exportExcel", eiInfo, {
					onSuccess: function(ei) {
						if (ei.getStatus() == "1") {
							var reportPath = ei.get("reportPath");
							var excelName = ei.get("excelName");
							var url = IPLATUI.CONTEXT_PATH + "/upload/download1.jsp?reportPath=" + reportPath + "&excelName=" + excelName;
							window.open(url);
							NotificationUtil(ei.get("message"));
							IPLAT.progress($("body"), false);
						} else {
							NotificationUtil(ei.get("message"), "error");
							IPLAT.progress($("body"), false);
						}
					}, onFail: function(ei) {
						alert("报表生成失败");
						IPLAT.progress($("body"), false);
					}
				})

			},
			cancelFn: function(e) { IPLAT.NotificationUtil('取消该操作!'); },
			title: '操作提示',
			minWidth: 200
		});
	});
	//导入
	$("#IMPORT").on("click", function() {
		var popWindow = $("#showHM");
		var url = IPLATUI.CONTEXT_PATH + "/web/DUHC230101";
		popWindow.data("kendoWindow").setOptions({
			open: function() {
				popWindow.data("kendoWindow").refresh({
					url: url
				});
			},
			lazyload: true,
			minWidth: 800,
			minHeight: 300,
			iframe: true
		});
		popWindow.data("kendoWindow").open().center();
	});


	IPLATUI.EFGrid = {
		"result": {
			 pageable:{
			 	pageSize:200,
			 	pageSizes:[100,200,500,1000]
			 },
			height: function(){
				return ($("#main-container").height() - $("#inqu").height() - 115);
			},
			columns: [
				{
					field: "operationedit",
					title: "编辑",
					encoded: false,
					template: function(dateItem) {
						return '<div style="text-align: center"><input value="编辑" class="k-button k-button-details" type="button" align="center" onclick="edit(\'' + dateItem.siteid + '\',\'' + dateItem.factorid + '\',\'' + dateItem.itemtime + '\',\'' + dateItem.planid + '\')"/></div>';
					}
				},

			],
			beforeAdd: function(e) {
				e.preventDefault();
				var oprationType = "insert";
				var popWindow = $("#showHM");
				var url = IPLATUI.CONTEXT_PATH + "/web/DUHC2301?oprationType=" + oprationType;
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
			dataBound: function(e) {
				var grid = e.sender;
				$.each(grid.getDataItems(), function(index, item) {
					var tr = grid.element.find("tr[data-uid=" + item.uid + "]");
					var itemvalue = item["itemvalue"].trim();
					var itemvaluezs = item["itemvaluezs"].trim();
					var itemlimit = item["itemlimit"].trim();
					var issc=true;
					var iszs=true;
					if("6~9"==itemlimit){//pH
						//实测值
						if(parseFloat(itemvalue)>6&& parseFloat(itemvalue)<9){
							issc=false;
						}else{
							issc=true;
						}
						//折算值
						if(parseFloat(itemvaluezs)>6&& parseFloat(itemvaluezs)<9){
							iszs=false;
						}else{
							iszs=true;
						}
					}else{
						//实测值
						if(parseFloat(itemvalue) >= parseFloat(itemlimit)){
							issc=true;
						}else{
							issc=false;
						}
						//折算值
						if(parseFloat(itemvaluezs) >= parseFloat(itemlimit)){
							iszs=true;
						}else{
							iszs=false;
						}
					}
					//实测值
					if (itemvalue!=""&&itemlimit!=""&&itemlimit!=null&&issc) {
						var td = tr.children("td:eq(12)");
						td.css({
							background: "yellow",
							color: "#F00"
						});
					}
					//折算值
					if (itemvaluezs!=""&&itemlimit!=""&&itemlimit!=null&&iszs) {
						var td = tr.children("td:eq(13)");
						td.css({
							background: "yellow",
							color: "#F00"
						});
					}
				})
			}
		}
	};

	IPLATUI.EFWindow = {
		"showHM": {
			close: function(e) {
				resultGrid.dataSource.page(1);
			}
		},
	};

	edit = function(siteid, factorid, itemtime, planid) {
		var popWindow = $("#showHM");
		var oprationType = "update";
		var url = IPLATUI.CONTEXT_PATH + "/web/DUHC2301?oprationType=" + oprationType + "&siteid=" + siteid + "&factorid=" + factorid + "&itemtime=" + itemtime + "&planid=" + planid;
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
});

function isEmpty(obj) {
	if (obj == null || obj == "undefined" || obj.trim() == "") {
		return true;
	} else {
		return false;
	}
}