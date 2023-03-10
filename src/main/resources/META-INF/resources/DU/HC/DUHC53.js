$(function () {
	
	function query() {
		IPLAT.progress($("body"), true);
		var ei = new EiInfo();
        ei.setByNode("inqu");
        EiCommunicator.send("DUHC53", "query", ei, {
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
            				if(oldataBlock.getCell(i, "overline") == "1"){
            					siteflag = "&nbsp;&nbsp;&nbsp;&nbsp;<span class='badge bg-danger'>超标</span>";
            				}
            			}
            			onerow+= "<div class='col-md-2'><div class='info-box' id='"+oldataBlock.getCell(i, "siteid")+"' onclick='showDetail(&apos;"+oldataBlock.getCell(i, "siteid")+"&apos;)'><div class='info-box-content'><img class='info-box-icon' src='" + IPLATUI.CONTEXT_PATH + "/img/"+oldataBlock.getCell(i, "imgname")+"'><span class='info-box-text'>"+oldataBlock.getCell(i, "monitorname")+siteflag+"</span><span class='info-box-sitename'>"+oldataBlock.getCell(i, "sitename")+"</span></div></div> </div>";
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
            	IPLAT.progress($("body"), false);
            }, onFail: function (ei) {
            	// 发生异常
            	NotificationUtil("加载数据出错" + ei.getMsg(), "error");  
                IPLAT.progress($("body"), false);
            }
        });
	};
	
	IPLATUI.EFSelect = {
	        "inqu_status-0-monitorid": {
	        	// 下拉选项改变之后触发
	            change: function (e) { //获取改变值
	            	query();
	            }
	        },
	        "inqu_status-0-porttypeid": {
	        	// 下拉选项改变之后触发
	            change: function (e) { //获取改变值
	            	query();
	            }
	        }
	
	    }
	
	window.onload = function(){
		query();
    	/*setInterval(function () {
    		query();
        }, 180000);//3分钟*/
    };
});


showDetail = function(siteid){
	IPLAT.openForm("DUHC2002", "siteid=" + siteid);
};
