$(function() {
	var portid=__eiInfo.get("portid");
	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.page(1);
	});
	$("#ADD_SITE").on("click", function(e) {
		var info = new EiInfo();
		info.set("portid",portid);
		if (resultGrid.getCheckedRows().length < 1){
			NotificationUtil("无选中数据");
			return;
		}
		var block = resultGrid.getInitBlock();
		block.setRows(resultGrid.getCheckedBlockData().rows);
		info.addBlock(block);
		IPLAT.confirm({
			message : '<b>确定关联选中监测点到到该排口？</b>',
			okFn : function(e) {
				EiCommunicator.send("DUHB0602", "updatePortId", info, {
					onSuccess : function(ei) {
						NotificationUtil(ei.getMsg());
						window.parent['showSIWindow'].close();
					},
					onFail : function(ei) {
						NotificationUtil("请求错误：" + ei.getMsg(), "error");
						console.log(ei);
					}
				});
			},
			cancelFn : function(e) {
			}
		});
	});
	
});