$(function() {
	$("#uploadButton").on("click", function(e) {
		var excelTemplatePath = $("#inqu_status-0-excelTemplatePath").val();
		var filePath = excelTemplatePath + "hjjc/rgjc/uploadExcel";
		startUpload("fileImport", filePath)
	});
});

function startUpload(fileElementId, filePath) {
	$.ajaxFileUpload({
		url: IPLATUI.CONTEXT_PATH + "/upload/upload.jsp?filePath=" + filePath,
		type: 'POST',
		secureuri: false,                           //是否启用安全提交,默认为false
		fileElementId: fileElementId,               //文件选择框的id属性
		dataType: 'json',                           //服务器返回的格式,选择json或者xml貌似有问题
		success: function(data) {
			var obj = JSON.parse(data);
			if ("9" == obj.status) {
				var ei = new EiInfo();
				ei.set("filename", obj.name);
				ei.set("filepath", obj.path);
				ei.setByNode("inqu");
				EiCommunicator.send("DUHC230101", "importExcel", ei, {
					onSuccess: function(ei) {
						NotificationUtil("导入成功！"+ ei.getMsg());
                        setTimeout(function() {
                            window.parent["showHMWindow"].close();
                        }, 1000)
					}, onFail: function(ei) {
						// 发生异常
						NotificationUtil("导入数据出错!" + ei.getMsg(), "error");
					}
				});
			} else {
				NotificationUtil("报表导入出错!" + ei.getMsg(), "error");
			}
		},
		error: function(data) {
			// 发生异常responseText
			//var  obj = JSON.parse(data);
			var obj = JSON.parse(data.responseText);
			NotificationUtil("报表导入出错!" + obj.msg, "error");
		}
	});
}