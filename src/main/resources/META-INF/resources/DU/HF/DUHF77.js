$(function() {
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
	});

	//文件上传
	$("#fileUpload").on("click", function() {
		var excelTemplatePath=$("#inqu_status-0-excelTemplatePath").val();
		var filePath = excelTemplatePath+"辐射报告管理/uploadExcel";
        var filetype=$("#result_status-0-filetype").val();
        if(filetype==""||filetype==null){
            IPLAT.alert("请先选择文件！");
            return;
        }
		var file=$("#result_status-0-file").val();
        if (file == null || file == "") {
            IPLAT.alert("请先选择文件！");
            return;
        }
        var eiInfo = new EiInfo();
        var filename = file.substr(file.lastIndexOf("\\") + 1);
        eiInfo.set("filename",filename);
        EiCommunicator.send("DUHF77", "checkFileByName", eiInfo, {
            onSuccess: function(ei) {
                var status = ei.get("status");
                if (status == 1) {
                    var maxSize = "4194304";//最大附件大小4M
                    upload("result_status-0-file",filePath,maxSize)
                } else {
                    NotificationUtil(ei.getMsg(), "error");
                    console.log(ei);
                }
            }, onFail: function() {
                NotificationUtil("错误!", "error");
            }
        });
	});
	IPLATUI.EFGrid = {
	    "result": {
			pageable: {
				pageSize: 200,
				pageSizes: [ 200, 500, 1000]
			},
			height: function(){
				return ($("#main-container").height() - $("#inqu").height() - 115);
			},

	        columns: [
				{
					field: "operation",
					title: "下载",
					encoded: false,
					width: 120,
					template: function(dateItem) {
						return '<div style="text-align: center"><input value="下载" class="k-button k-button-details" type="button" align="center" onclick="download(\''+dateItem.filepath+'\')"/></div>';
					}
				}
			]
		}
	};
	download = function(filepath){
    	var url = IPLATUI.CONTEXT_PATH + "/upload/download.jsp?path=" + filepath ;
		window.open(url);
    }
});
//文件上传
function upload(fileElementId, filePath,maxSize) {
	$.ajaxFileUpload({
		url: IPLATUI.CONTEXT_PATH + "/upload/upload2.jsp?filePath=" + filePath+"&maxSize="+maxSize,
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
				ei.setByNode("result");
				
				//ei.setByNode("register");
				EiCommunicator.send("DUHF77", "importExcel", ei, {
					onSuccess: function(ei) {
						var fileid = ei.get("fileid");
						if (ei.getStatus() == 1) {
							NotificationUtil("上传成功," + ei.getMsg());
							$("#result_status-0-file").val();
							resultGrid.dataSource.page(1);
							
						}else if(ei.getStatus() == 2){
							NotificationUtil(ei.getMsg(), "error");
						}else if(ei.getStatus() == 0){
							NotificationUtil(ei.getMsg(), "error");
						}else {
							NotificationUtil("上传失败," + ei.getMsg(), "error");
						}

					}, onFail: function(ei) {
						// 发生异常
						NotificationUtil("上传数据出错!" + ei.getMsg(), "error");
					}
				});
			} else {
				NotificationUtil("上传出错!" + ei.getMsg(), "error");
			}
		},
		error: function(data) {
			// 发生异常responseText
			//var  obj = JSON.parse(data);
			var obj = JSON.parse(data.responseText);
			NotificationUtil("上传出错!" + obj.msg, "error");
		}
	});
}