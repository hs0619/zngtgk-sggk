$(function() {

	$("#QUERY").on("click", function(e) {
		queryPortfs();
		sitefsGrid.dataSource.page(1);
		ssfsGrid.dataSource.page(1);
	});
	$(document.body).on("click", "#ADD_SITE", function(e) {
		var popWindow = $("#showSI");
		var portid = IPLAT.EFInput.value("#inqu_status-0-dischargeportid");
		var url = IPLATUI.CONTEXT_PATH + "/web/DUHB0602?portid=" + portid;
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

	//关联已有环保设施
	$(document.body).on("click", "#ADD_CONNECT", function(e) {
		var monitorid = __eiInfo.get("monitorid");

		if (monitorid == "01") {//废气
			var popWindow = $("#showFQ");
			var portid = IPLAT.EFInput.value("#inqu_status-0-dischargeportid");
			var url = IPLATUI.CONTEXT_PATH + "/web/DUHB0603?portid=" + portid;
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


		} else if (monitorid == "02") {
			var popWindow = $("#showFS");
			var portid = IPLAT.EFInput.value("#inqu_status-0-dischargeportid");
			var url = IPLATUI.CONTEXT_PATH + "/web/DUHB0604?portid=" + portid;
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

		} else {
			IPLAT.alert({
				title: '系统提示',
				message: '请选择废气或废水排口进行关联！',
			});
			return;
		}
	});

	//取消关联污染防治设施
	$(document.body).on("click", "#DEL_CONNECT", function(e) {
		//获取选中的数据列
		var checkedArr = ssfsGrid.getCheckedRows();
		var checkedLength = checkedArr.length;
		if (checkedLength == 0) {
			NotificationUtil("请选择记录！", "info");
			return false;
		} else {
			var block = ssfsGrid.model2EiBlock(checkedArr);
			var eiInfo = new EiInfo();
			eiInfo.addBlock(block);
			IPLAT.confirm({
				message: '<b>确定要取消关联污染防治设施吗？</b>',
				okFn: function(e) {
					EiCommunicator.send("DUHB06", "deleteSSFS", eiInfo, {
						onSuccess: function(ei) {
							if (ei.getStatus() == -1) {
								NotificationUtil("操作失败！", "error");
							} else if (ei.getStatus() == 1) {
								NotificationUtil("取消成功！", "success");
								window.location.reload();
							} else if (ei.getStatus() == -2) {
								NotificationUtil("系统参数错误！", "success");
							}

						}, onFail: function() {
							// 发生异常
							NotificationUtil("操作失败", "error");
						}
					});
				},
				cancelFn: function(e) { IPLAT.NotificationUtil('取消该操作!'); },
				title: '操作提示',
				minWidth: 200
			});

		}
	});



	IPLATUI.EFTab = {
		"portfs": {
			show: function(e) {
				$(e.contentElement).find("div[data-role='grid']").data("kendoGrid").refresh();
			}
		}
	};


	IPLATUI.EFGrid = {
		"portfs": {
			pageable: false,
			exportGrid: false,
			beforeAdd: function(e) {
				e.preventDefault();
				var portid = $("#inqu_status-0-dischargeportid").val();
				if (portid == null || portid == "undefined" || portid == "") {
					NotificationUtil("排口信息出错", "error");
					return;
				}
				var oprationType = "insert";
				var popWindow = $("#showPF");
				var url = IPLATUI.CONTEXT_PATH + "/web/DUHB0601?oprationType="
					+ oprationType + "&portid=" + portid;
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
			}
		},
		"sitefs": {
			columns: [{
				field: "operation",
				title: "编辑",
				encoded: false,
				template: function(dateItem) {
					return '<div style="text-align: center"><input value="编辑" class="k-button k-button-details" type="button" align="center" onclick="showDetailSI(\''
						+ dateItem.siteid + '\')"/></div>';
				}
			}],
			beforeAdd: function(e) {
				e.preventDefault();
				var oprationType = "insert";
				var popWindow = $("#showSI");
				var url = IPLATUI.CONTEXT_PATH + "/web/DUHB0701?oprationType="
					+ oprationType;
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
			onRowDblClick: function(e) {
				var temsiteid = e.model.siteid;
				var popWindow = $("#showSF");
				var url = IPLATUI.CONTEXT_PATH + "/web/DUHB0702?siteid="
					+ temsiteid;
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
			}
		},
		"ssfs": {
			columns: [
				{
					field: "operation",
					title: "操作",
					encoded: false,
					template: function(dateItem) {
						var monitorid = __eiInfo.get("monitorid");
						return '<div style="text-align: center"><input value="详情" class="k-button k-button-details" type="button" align="center" onclick="showDetailSS(\''
							+ dateItem.facilityid + '\',\'' + monitorid + '\',\'' + dateItem.departmentid + '\')"/></div>';
					}
				}
			],

			/*beforeAdd : function(e) {
			e.preventDefault();
			var oprationType = "insert";
			var popWindow = $("#showSS");
			var portid = IPLAT.EFInput.value("#inqu_status-0-dischargeportcode");
			var url = IPLATUI.CONTEXT_PATH + "/web/DUHB2001?oprationType="
					+ oprationType + "&dischargeportcode=" + dischargeportcode;
			popWindow.data("kendoWindow").setOptions({
				open : function() {
					popWindow.data("kendoWindow").refresh({
						url : url
					});
				},
				lazyload : true,
				minWidth : 1000,
				minHeight : 500,
				iframe : true
			});
			popWindow.data("kendoWindow").open().center();
		}*/
		}
	};

	IPLATUI.EFWindow = {
		"showPF": {
			close: function(e) {
				queryPortfs();
				sitefsGrid.dataSource.page(1);
				ssfsGrid.dataSource.page(1);
			}
		},
		"showSI": {
			close: function(e) {
				queryPortfs();
				sitefsGrid.dataSource.page(1);
				ssfsGrid.dataSource.page(1);
			}
		},
		"showSF": {
			close: function(e) {
				queryPortfs();
				sitefsGrid.dataSource.page(1);
				ssfsGrid.dataSource.page(1);
			}
		},
		"showSS": {
			close: function(e) {
				queryPortfs();
				sitefsGrid.dataSource.page(1);
				ssfsGrid.dataSource.page(1);
			}
		},
		"showFQ": {
			close: function(e) {
				queryPortfs();
				sitefsGrid.dataSource.page(1);
				ssfsGrid.dataSource.page(1);
			}
		},
		"showFS": {
			close: function(e) {
				queryPortfs();
				sitefsGrid.dataSource.page(1);
				ssfsGrid.dataSource.page(1);
			}
		}
	};

	window.onload = function() {
		//		portfsGrid.dataSource.page(1);
		//		sitefsGrid.dataSource.page(1);
		//		ssfsGrid.dataSource.page(1);
	};
});

showDetail = function(portid, factorid) {

	var popWindow = $("#showPF");
	var oprationType = "update";
	var url = IPLATUI.CONTEXT_PATH + "/web/DUHB0601?oprationType="
		+ oprationType + "&portid=" + portid + "&factorid=" + factorid;
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

showDetailSI = function(siteid) {

	var popWindow = $("#showSI");
	var oprationType = "update";
	var url = IPLATUI.CONTEXT_PATH + "/web/DUHB0701?oprationType="
		+ oprationType + "&siteid=" + siteid;
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
//
function showDetailSS(facilityid, type, departmentid) {

	var popWindow = $("#showFI");
	var oprationType = "update";
	var url = IPLATUI.CONTEXT_PATH + "/web/DUHB2001?oprationType="
		+ oprationType + "&facilityid=" + facilityid + "&departmentid=" + departmentid;
	if (type =="02") {
		url = IPLATUI.CONTEXT_PATH + "/web/DUHB2101?oprationType="
			+ oprationType + "&facilityid=" + facilityid + "&departmentid=" + departmentid;
	}
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


function queryPortfs() {
	var ei = new EiInfo();
	ei.setByNode("inqu");
	EiCommunicator.send("DUHB06", "queryPORTFS", ei, {
		onSuccess: function(ei) {
			portfsGrid.setEiInfo(ei);
		}, onFail: function() {

		}
	});

}

