$(function () {
	
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
	});
	
	//加载左侧电话栏数据信息
	loadPhoneInfo();
	
	$("#treeview").kendoTreeView({
	  checkboxes: {
         checkChildren: true
      },
	  dataSource: []

	});
	
	//点击保存菜单信息
	$("#SAVE").on("click", function (e) {
		var personId = $("#inqu_status-0-personId").val();	
		if(personId == ""|| personId == null ){
			return;
		}
        var checkedNodes = [];
        var treeView = $("#treeview").data("kendoTreeView");
        var dcpIds = "";
        checkedNodeIds(treeView.dataSource.view(), checkedNodes);
        

        if (checkedNodes.length > 0) {
        	for(var i = 0; i < checkedNodes.length; i++){
        		var regStr = checkedNodes[i].substring(0,3);
        		if( "DEP" != regStr ){
        			if( i == checkedNodes.length-1){
        				dcpIds += checkedNodes[i];
        			}else{
        				dcpIds += checkedNodes[i] + ";";
        			}
        		}
        	}
        } 
        
        if( dcpIds != null){
        	var ei = new EiInfo();
        	ei.set("dcpIds", dcpIds);
        	ei.set("personId", personId);
        	
        	EiCommunicator.send("DUHB26", "saveCheckMenu", ei, {
		        onSuccess: function (ei) {
		        	if(ei.getStatus() == 1){
			        	IPLAT.alert({
				    		title: '系统提示',
				    		message: '保存成功！',
				    		okFn: reloadTreeById()
						});
		        	}else if(ei.getStatus() == -1){
		        		IPLAT.alert({
				    		title: '错误提示',
				    		message: '错误，数据保存失败！'
						});
		        	}
		        }, onFail: function () {
		        	// 发生异常
		        	NotificationUtil("数据出错" + ei.getMsg(), "error");  
		        }
		    });
        	
        	
        }
        
        
		
	});

	
	    		
});

// 获得被勾选节点的所有id
function checkedNodeIds(nodes, checkedNodes) {
    for (var i = 0; i < nodes.length; i++) {
        if (nodes[i].checked) {
            checkedNodes.push(nodes[i].id);
        }

        if (nodes[i].hasChildren) {
            checkedNodeIds(nodes[i].children.view(), checkedNodes);
        }
    }
}






//初始化加载左侧电话栏部分信息
function loadPhoneInfo(){
	var inInfo = __ei
	var phoneList = inInfo.phoneList;
	var phoneInfo  = $("#phoneInfo");
	if(phoneList != null || phoneList != ""){
		var liStr = "";
		for(var i = 0;i < phoneList.length; i++){
			var alarmPersonName = phoneList[i].alarmPersonName;	
			var phone = phoneList[i].phone;	
			var phoneStr = alarmPersonName + "（" + phone + "）" ;
			liStr += "<li onclick='checkPhone(this)' >" 
				   + "<input value='" + phoneList[i].alarmPersonIdentity + "' type='hidden' /> "
				   + "<div class= 'layui-menu-body-title'>"
				   + phoneStr 
				   + "</div>"
				   + "</li>";
		}
		
		phoneInfo.html(liStr);
	}
}





//点击查看对应的树结构
function checkPhone(obj){
	var personId = obj.firstElementChild.value;
	$("#inqu_status-0-personId").val(personId);
	
	//获取联系人信息所有的li
	var liArr  = $("#phoneInfo li");
	if(liArr.length > 0){
		for(var i = 0; i < liArr.length; i++){
			liArr[i].classList.remove("mg-hb26-li");
		}
	}
	$(obj).addClass("mg-hb26-li");
	
	var eiInfo = new EiInfo();
	eiInfo.set("personId",personId);

    EiCommunicator.send("DUHB26", "changeMenuById", eiInfo, {
        onSuccess: function (ei) {
        	//console.log(ei.get("menuData"));
        	var treeview = $("#treeview").data("kendoTreeView");
			treeview.setDataSource(new kendo.data.HierarchicalDataSource({
			  data: ei.get("menuData")
			}));
        	
        }, onFail: function () {
        	// 发生异常
        	NotificationUtil("数据出错" + ei.getMsg(), "error");  
        }
    });
	

}

//重新加载树结构
function reloadTreeById(){
	var personId = $("#inqu_status-0-personId").val();
	var eiInfo = new EiInfo();
	eiInfo.set("personId",personId);
	
	EiCommunicator.send("DUHB26", "changeMenuById", eiInfo, {
        onSuccess: function (ei) {
        	var treeview = $("#treeview").data("kendoTreeView");
			treeview.setDataSource(new kendo.data.HierarchicalDataSource({
			  data: ei.get("menuData")
			}));
        	
        }
    });



}




