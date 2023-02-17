$(function() {
    var type = __eiInfo.get("inqu_status-0-type");
    var rfId = __eiInfo.get("inqu_status-0-rfId");

    var validator1 = IPLAT.Validator({
        id : "inqu"
    });


    $("#SAVE").on("click", function() {
        if (!validator1.validate()) {
            NotificationUtil("请填写完整信息！", "error");
            return;
        }
        var info = new EiInfo();
        info.setByNode("inqu");
        if (type == "insert") {
            EiCommunicator.send("DUHB5101", "insert", info, {
                onSuccess : function(ei) {
                    NotificationUtil(ei.getMsg(), "success");
                    window.parent['showDPWindow'].close();
                },
                onFail : function(ei) {
                    NotificationUtil("保存信息出错" + ei.getMsg(), "error");
                    console.log(ei);
                }
            });
        } else if (type == "update") {

            EiCommunicator.send("DUHB5101", "update", info, {
                onSuccess : function(ei) {
                    NotificationUtil(ei.getMsg(), "success");
                    window.parent['showDPWindow'].close();

                },
                onFail : function(ei) {
                    NotificationUtil("修改信息出错" + ei.getMsg(), "error");
                    console.log(ei);
                }
            });
        }

    })
});
