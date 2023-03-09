$(function() {
	var eiInfo = new EiInfo();
	var oprationType = __eiInfo.get("inqu_status-0-oprationType");
	var departmentid = __eiInfo.get("inqu_status-0-departmentid");
	var monitorid = __eiInfo.get("inqu_status-0-monitorid");
	var siteid = __eiInfo.get("inqu_status-0-siteid");
	var planid = __eiInfo.get("inqu_status-0-planid");
	if ("update" == oprationType) {
		$("#inqu_status-0-departmentid").attr("disabled", true);
		$("#inqu_status-0-siteid").attr("disabled", true);
		$("#inqu_status-0-monitorid").attr("disabled", true);
		$("#inqu_status-0-planid").attr("disabled", true);
		$("#inqu_status-0-itemtime").attr("disabled", true);
		eiInfo.set("classifyid", monitorid);
	} else {
		$("#inqu_status-0-oprationType").val("insert");
		eiInfo.set("classifyid", "01");//废气
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
	eiInfo.set("classifyid",monitorid);//人工监测类型
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
				sites.push({ "valueField": "", "textField": "--前选择--" });

				$.each(ei["blocks"]["site"]["rows"], function(i, obj) {
					sites.push({ "valueField": obj[1], "textField": obj[0] });
				});
				var dataSource = new kendo.data.DataSource({
					data: sites
				});
				IPLAT.EFSelect.setDataSource($("#inqu_status-0-siteid"), dataSource);
				if (siteid != "" && siteid != null) {
					IPLAT.EFSelect.value($("#inqu_status-0-siteid"), siteid);
					IPLAT.EFSelect.value($("#inqu_status-0-planid"), planid);
					eiInfo.set("siteid", siteid);
					eiInfo.set("planid", planid);
					eiInfo.set("monitorid", monitorid);
					eiInfo.setByNode("inqu");
					EiCommunicator.send("DUHC2301", "queryPlan", eiInfo, {
						onSuccess: function(ei) {
							if (ei["blocks"]["plan"] != null) {
								var plans = [];
								$.each(ei["blocks"]["plan"]["rows"], function(i, obj) {
									plans.push({ "valueField": obj[0], "textField": obj[1] });
								});
								var dataSource = new kendo.data.DataSource({
									data: plans
								});
								IPLAT.EFSelect.setDataSource($("#inqu_status-0-planid"), dataSource);
								if (planid != null && planid != "") {
									IPLAT.EFSelect.value($("#inqu_status-0-planid"), planid);
									//因子
									resultGrid.setEiInfo(ei);
								}
							};
						}, onFail: function(ei) {
							// 发生异常
							NotificationUtil("加载参数出错" + ei.getMsg(), "error");
							console.log(ei);
						}
					});
				} else {
					IPLAT.EFSelect.value($("#inqu_status-0-siteid"), "");
				}
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
					sites.push({ "valueField": "", "textField": "--请选择--" });
	
					$.each(ei["blocks"]["site"]["rows"], function(i, obj) {
						sites.push({ "valueField": obj[1], "textField": obj[0] });
					});
					var dataSource = new kendo.data.DataSource({
						data: sites
					});
					IPLAT.EFSelect.setDataSource($("#inqu_status-0-siteid"), dataSource);
					IPLAT.EFSelect.value($("#inqu_status-0-siteid"), "");
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
				eiInfo.set("siteid", this.value());
				var monitorid = $("#inqu_status-0-monitorid").val();
				eiInfo.set("monitorid", monitorid);
				eiInfo.setByNode("inqu");
				EiCommunicator.send("DUHC2301", "queryPlan", eiInfo, {
					onSuccess: function(ei) {
						//计划
						if (ei["blocks"]["plan"] != null) {
							var plans = [];
							$.each(ei["blocks"]["plan"]["rows"], function(i, obj) {
								plans.push({ "valueField": obj[0], "textField": obj[1] });
							});
							var dataSource = new kendo.data.DataSource({
								data: plans
							});
							IPLAT.EFSelect.setDataSource($("#inqu_status-0-planid"), dataSource);
							IPLAT.EFSelect.value($("#inqu_status-0-planid"), ei.get("inqu_status-0-planid"));
							//因子
							resultGrid.setEiInfo(ei);
						};
						//上一次采样时间
						var lastitemtime = ei.get("inqu_status-0-lastitemtime");
						$("#lastitemtime").text(lastitemtime);
					}, onFail: function(ei) {
						// 发生异常
						NotificationUtil("加载数据出错" + ei.getMsg(), "error");
						console.log(ei);
					}
				});
			}
		},
		"inqu_status-0-planid": {
			// 下拉选项改变之后触发 
			change: function(e) { //获取改变值
				eiInfo.set("planid", this.value());
				eiInfo.setByNode("inqu");
				EiCommunicator.send("DUHC2301", "queryFactor", eiInfo, {
					onSuccess: function(ei) {
						//因子
						resultGrid.setEiInfo(ei);
						//上一次采样时间
						var lastitemtime = ei.get("inqu_status-0-lastitemtime");
						$("#lastitemtime").text(lastitemtime);
					}, onFail: function(ei) {
						// 发生异常
						NotificationUtil("加载数据出错" + ei.getMsg(), "error");
						console.log(ei);
					}
				});
			}
		},
	}

	IPLATUI.EFGrid = {
		"result": {
			pageable: false,
			dataBound: function(e) {
				var grid = e.sender;
				$.each(grid.getDataItems(), function(index, item) {
					var tr = grid.element.find("tr[data-uid=" + item.uid + "]");
					var itemvalue = item["itemvalue"];
					if (itemvalue != "" && itemvalue != "undefined" && itemvalue != null) {
						itemvalue = item["itemvalue"].trim();
					}
					var itemvaluezs = item["itemvaluezs"];
					if (itemvaluezs != "" && itemvaluezs != "undefined" && itemvaluezs != null) {
						itemvaluezs = item["itemvaluezs"].trim();
					}
					var itemlimit = item["itemlimit"];
					if (itemlimit != "" && itemlimit != "undefined" && itemlimit != null) {
						itemlimit = item["itemlimit"].trim();
					}
					var issc = true;
					var iszs = true;
					if ("6~9" == itemlimit) {//pH
						//实测值
						if (itemvalue != "" && itemvalue != undefined && itemvalue != null) {
							if (parseFloat(itemvalue) >= 6 && parseFloat(itemvalue) <= 9) {
								issc = false;
							} else {
								issc = true;
							}
						}
						if (itemvaluezs != "" && itemvaluezs != undefined && itemvaluezs != null) {
							//折算值
							if (parseFloat(itemvaluezs) >= 6 && parseFloat(itemvaluezs) <= 9) {
								iszs = false;
							} else {
								iszs = true;
							}
						}

					} else {
						if (itemvalue != "" && itemvalue != undefined && itemvalue != null) {
							//实测值
							if (parseFloat(itemvalue) >= parseFloat(itemlimit)) {
								issc = true;
							} else {
								issc = false;
							}
						}
						if (itemvaluezs != "" && itemvaluezs != undefined && itemvaluezs != null) {
							//折算值
							if (parseFloat(itemvaluezs) >= parseFloat(itemlimit)) {
								iszs = true;
							} else {
								iszs = false;
							}
						}
					}
					//实测值
					if (itemvalue != "" && itemlimit != "" && itemlimit != null && itemvalue != undefined && issc) {
						var td = tr.children("td:eq(6)");
						td.css({
							background: "yellow",
							color: "#F00"
						});
					}
					//折算值
					if (itemvaluezs != "" && itemlimit != "" && itemlimit != null && itemvaluezs != undefined && iszs) {
						var td = tr.children("td:eq(7)");
						td.css({
							background: "yellow",
							color: "#F00"
						});
					}
				})
			}
		}
	}

	//保存
	$(document.body).on("click", "#SAVEINFO", function(e) {
		var istrue = checkParam() //参数检索
		if (!istrue) {
			return;
		}
		var results = resultGrid.getCheckedRows();
		if (results.length > 0) {
			var block = resultGrid.model2EiBlock(results);
			eiInfo.setByNode("inqu");
			eiInfo.addBlock(block);
			EiCommunicator.send("DUHC2301", "save", eiInfo, {
				onSuccess: function(ei) {
					if (ei.getStatus() == 1) {
						NotificationUtil("保存成功，" + ei.getMsg());
					} else if (ei.getStatus() == 2) {
						NotificationUtil("保存失败，" + ei.getMsg(), "error");
					}
				}, onFail: function(ei) {
					// 发生异常
					NotificationUtil("保存失败", "error");
				}
			});
		} else {
			NotificationUtil("请选择需要保存的数据！", "warning");
		}
	})
	//参数检索
	function checkParam() {
		var siteid = $("#inqu_status-0-siteid").val();
		var siteid = $("#inqu_status-0-siteid").val();
		var planid = $("#inqu_status-0-planid").val();
		var itemtime = $("#inqu_status-0-itemtime").val();
		if (siteid == null || siteid == "" || siteid == "%") {
			NotificationUtil("请选择监测点！", "warning");
			return false;
		}
		if (planid == null || planid == "") {
			NotificationUtil("请选择监测计划！", "warning");
			return false;
		}
		if (itemtime == null || itemtime == "") {
			NotificationUtil("请填写采样时间！", "warning");
			return false;
		}
		return true;
	}
});


