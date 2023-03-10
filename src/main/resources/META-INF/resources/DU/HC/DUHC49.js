$(function () {
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
					monitors.push({ "valueField": obj[0], "textField": obj[1] });
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
	
	var ap = new APlayer({
        container: document.getElementById('aplayer'),
        audio: [{
            name: 'name',
            artist: 'artist',
            url: IPLATUI.CONTEXT_PATH + '/js/alarm/warn.mp3',
            loop: 'one'
        }]
    });

	$("#MUTE").on("click", function (e) {
		Lobibox.notify.closeAll();
		ap.pause()
	})
	
	$("#QUERY").on("click", function (e) {
		Lobibox.notify.closeAll();
		var warnlist =  new Array();
		ap.pause()
		IPLAT.progress($("body"), true);
		var ei = new EiInfo();
        ei.setByNode("inqu");
        EiCommunicator.send("DUHC49", "query", ei, {
            onSuccess: function (ei) {
            	$("#siteinfo").html("");
            	var oldataBlock = ei.getBlock("oldata");
            	var onerow = "";
            	var depart_now="";
            	var monitor_now="";
            	var colindex=1;
            	if (oldataBlock != null && oldataBlock.getRows().length > 0) {
            		var allcount = ei.getBlock("oldata").getRows().length;
            		onerow += "<div class='row'>";
            		for (var i = 0; i < allcount; i++) {
            			var departname=oldataBlock.getCell(i, "departmentname");
            			var monitorname=oldataBlock.getCell(i, "monitorname");
            			if(depart_now!=departname){
            				onerow+= "</div><div class='row'><div class='col-md-12'><div class='title-box'>"+departname+"</div></div>";
            				onerow+= "</div><div class='row'>";
							depart_now = departname;
							colindex=1;
            			}
            			if(monitor_now!=monitorname){
            				onerow+= "</div><hr class='line' /><div class='row'>";
            				monitor_now = monitorname;
							colindex=1;
            			}
            			var siteflag = "&nbsp;&nbsp;&nbsp;&nbsp;<span class='badge bg-success'>正常</span>";
            			if(oldataBlock.getCell(i, "offline") == "1"){
            				siteflag = "&nbsp;&nbsp;&nbsp;&nbsp;<span class='badge bg-warning'>离线</span>";
            			}else{
            				if(oldataBlock.getCell(i, "overline") == "1"||oldataBlock.getCell(i, "overline") == "2"){
            				    if(oldataBlock.getCell(i, "overline") == "2"){
                                    siteflag = "&nbsp;&nbsp;&nbsp;&nbsp;<span style='background-color: #007bff;' class='badge'>小于等于0</span>";
                                }else{
                                    siteflag = "&nbsp;&nbsp;&nbsp;&nbsp;<span class='badge bg-danger'>超标</span>";
                                }
            					var temsiteid = oldataBlock.getCell(i, "siteid");
            					var temsitename = oldataBlock.getCell(i, "sitename");
            					warnlist.push(temsiteid);
            					Lobibox.notify('error', {
            	                    title: '报警',
            	                    delay: false,
            	                    sound: false,
            	                    img: IPLATUI.CONTEXT_PATH + '/js/alarm/baojing.gif',
            	                    msg: temsitename + '</br>该监测点有报警数据，请及时处理！',
            	                    onClick: function () {
            	                    	IPLAT.openForm("DUHC2003", "siteid=" + temsiteid);
            	                    }
            	                });
            				}
            			}
            			onerow+= "<div class='col-md-2'><div class='info-box' id='"+oldataBlock.getCell(i, "siteid")+"' onclick='showDetail(&apos;"+oldataBlock.getCell(i, "siteid")+"&apos;)'><img class='info-box-icon' src='" + IPLATUI.CONTEXT_PATH + "/img/"+oldataBlock.getCell(i, "imgname")+"'><div class='info-box-content'><span class='info-box-text' style='font-size:20px;'>"+oldataBlock.getCell(i, "monitorname")+siteflag+"</span><span>"+oldataBlock.getCell(i, "sitename")+"</span></div></div> </div>";
            			if(colindex==6){
            				onerow += "</div><div class='row'>";
            				colindex=0;
            			}
            			if((i+1)==allcount){
            				onerow += "</div>";
            			}
            			
            			colindex++;
            		}
            	}
            	$("#siteinfo").html(onerow);
            	if (warnlist.length > 0) {
        	        ap.play();
        	    }
            	IPLAT.progress($("body"), false);
            }, onFail: function (ei) {
            	// 发生异常
            	NotificationUtil("加载数据出错" + ei.getMsg(), "error");  
                IPLAT.progress($("body"), false);
            }
        });
	});
	
	
	
	window.onload = function(){
		setTimeout(function () {
			$("#QUERY").click();
		}, 1000);
    	setInterval(function () {
    		$("#QUERY").click();
        }, 180000);//三分钟
    };
});


showDetail = function(siteid){
	IPLAT.openForm("DUHC2003", "siteid=" + siteid);
};
