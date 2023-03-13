$(function() {

    var filePath = __eiInfo.get("inqu_status-0-file");
    //上传附件
    $("#upload").on("click", function() {
        var imgVal = $("#inqu_status-0-file").val();
        if(imgVal == null || imgVal == ""){
            NotificationUtil("请先选择文件!", "info");
            return;
        }
        // 检查每个上传的文件的后缀，如果不是.xls .xlsx格式的话弹出信息提示框并且阻止上传
        var ret=false;
        $.each($("#inqu_status-0-file")[0].files, function () {
            if (!/\.(?:xls|xlsx)$/.test(this.name)) {
                ret=true;
            }
        });
        if(ret){
            alert("只能上传.xls .xlsx类型模板文件");
            return;
        }
        IPLAT.confirm({
            message: '<b>确定上传此文件做为报表模板？</b>',
            okFn: function (e) {
                $.ajaxFileUpload({
                    url: IPLATUI.CONTEXT_PATH + "/upload/upload.jsp?filePath=" + filePath,
                    type: 'POST',
                    secureuri: false,                           //是否启用安全提交,默认为false
                    fileElementId: "inqu_status-0-file",               //文件选择框的id属性
                    dataType: 'json',                           //服务器返回的格式,选择json或者xml貌似有问题
                    success: function(data) {
                        var obj = JSON.parse(data);
                        if ("9" == obj.status) {
                            var reporttype = $("#inqu_status-0-reporttype").val();
                            var ei = new EiInfo();
                            ei.set("reportname", obj.name);
                            ei.set("reporttype", reporttype);
                            ei.set("filepath", filePath);
                            EiCommunicator.send("DUHD20", "importExcel", ei, {
                                onSuccess: function(ei) {
                                    if(ei.getStatus() == 1){
                                        NotificationUtil("上传成功！", "success");
                                        $("#inqu_status-0-file").val("");
                                        resultGrid.dataSource.page(1);
                                    }else if(ei.getStatus() == -1){
                                        NotificationUtil("上传失败！", "error");
                                    }else if(ei.getStatus() == -2){
                                        NotificationUtil("上传失败，系统参数错误！", "error");
                                    }else if(ei.getStatus() == 2){
                                        NotificationUtil("上传失败，报表文件已存在！", "error");
                                    }else{
                                        NotificationUtil("上传失败！", "error");
                                    }

                                }, onFail: function(ei) {
                                    // 发生异常
                                    NotificationUtil("上传出错!", "error");
                                }
                            });

                        }

                    },
                    error: function(data) {
                        // 发生异常responseText
                        //var  obj = JSON.parse(data);
                        var obj = JSON.parse(data.responseText);
                        NotificationUtil("上传出错!" + obj.msg, "error");
                    }
                });

            },
            cancelFn: function (e) {
                return;
            }
        });

    });

    IPLATUI.EFGrid = {
        "result" : {
            columns : [ {
                field : "download",
                title : "下载",
                enable : false,
                width : 40,
                template : "<span><button class='i-btn-sm compute'>下载</button></span>"
            } ],
            onCellClick : function(e) {
                if (e.field == 'download') {
                    var model = e.model;
                    if (!model) return;
                    //下载路劲为浏览器的默认配置，可以去浏览器设置下载地址
                    var url = IPLATUI.CONTEXT_PATH + "/upload/download.jsp?path=" + encodeURI(filePath + model.reportname);
                    window.open(url);
                }
            },
            pageable : {
                pageSize : 20,
                pageSizes : [ 10, 20, 50, 100 ]
            }

        }
    }

    IPLATUI.EFSelect = {
        "inqu_status-0-reporttype" : {
            // 下拉选项改变之后触发，选中时直接重新刷新数据
            change : function(e) {
                resultGrid.dataSource.page(1);
            }
        }
    }
});