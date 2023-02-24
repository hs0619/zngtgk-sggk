package com.baosight.sggk.du.hf.service;

import com.baosight.sggk.du.hf.domain.DUHF7102;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.UUID;

public class ServiceDUHF7103 extends ServiceBase {
	
	private static final Logger logger = Logger.getLogger(ServiceDUHF7103.class);
	
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return query(inInfo);
	}

	/**
	 * 查询功能
	 *
	 * @param inInfo
	 * @return
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {
		EiInfo outInfo = super.query(inInfo, "DUHF7102.query", new DUHF7102());
		return outInfo;
	}

//	//初始化下拉框
//	EiCommunicator.send("DUHF6201","initSelectData",eiInfo,{
//		onSuccess: function(ei) {
//		   if (ei["blocks"]["qstatus"] != null) {
//			    var status = [];
//			    $.each(ei["blocks"]["qstatus"]["rows"], function(i, obj) {
//			    	status.push({ "valueField": obj[0], "textField": obj[1] });
//			    });
//			    var dataSource = new kendo.data.DataSource({
//			    	data: status
//			    });
//			    IPLAT.EFSelect.setDataSource($("#inqu_status-0-status"), dataSource);
//			    IPLAT.EFSelect.value($("#inqu_status-0-status"), ei.get("inqu_status-0-status"));
//		   };
//		   if (ei["blocks"]["qdoctype"] != null) {
//			    var doctypes = [];
//			    $.each(ei["blocks"]["qdoctype"]["rows"], function(i, obj) {
//			    	doctypes.push({ "valueField": obj[0], "textField": obj[1] });
//			    });
//			    var dataSource = new kendo.data.DataSource({
//			    	data: doctypes
//			    });
//			    IPLAT.EFSelect.setDataSource($("#tab1_status-0-filetype"), dataSource);
//			    IPLAT.EFSelect.setDataSource($("#tab2_status-0-filetype"), dataSource);
//			    IPLAT.EFSelect.setDataSource($("#tab3_status-0-filetype"), dataSource);
//			    IPLAT.EFSelect.setDataSource($("#tab4_status-0-filetype"), dataSource);
//			    IPLAT.EFSelect.setDataSource($("#tab5_status-0-filetype"), dataSource);
//			    IPLAT.EFSelect.setDataSource($("#tab6_status-0-filetype"), dataSource);
//			    IPLAT.EFSelect.setDataSource($("#tab7_status-0-filetype"), dataSource);
//			    //IPLAT.EFSelect.value($("#tab1_status-0-filetype"), ei.get("tab1_status-0-filetype"));
//		   };
//
//	  	}, onFail: function(ei) {
//	   // 发生异常
//	   NotificationUtil("加载参数出错" + ei.getMsg(), "error");
//	   console.log(ei);
//	  	}
//	 });
	
	/**
	 * 新增功能
	 *
	 * @param inInfo
	 * @return
	 */
	@Override
	public EiInfo insert(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		String currentUser = String.valueOf(UserSession.getLoginName());
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		for (int i = 0; i < inInfo.getBlock("result").getRowCount(); i++) {
			
			inInfo.getBlock("result").setCell(i, "typeid", UUID.randomUUID().toString());
//			inInfo.getBlock("result").setCell(i, "createman",currentUser);
//			inInfo.getBlock("result").setCell(i, "createtime", dateTimeFormat.format(new Date()));
			outInfo = super.insert(inInfo, "DUHF7102.insert");
		}
		
		return outInfo;
	}

	/**
	 * 修改功能
	 *
	 * @param inInfo
	 * @return
	 */
	@Override
	public EiInfo update(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		String currentUser = String.valueOf(UserSession.getLoginName());
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		for (int i = 0; i < inInfo.getBlock("result").getRowCount(); i++) {
//			inInfo.getBlock("result").setCell(i, "updateman",currentUser);
//			inInfo.getBlock("result").setCell(i, "updatetime", dateTimeFormat.format(new Date()));
			outInfo = super.insert(inInfo, "DUHF7102.update");
		}
		
		return outInfo;
	}

	/**
	 * 删除功能
	 *
	 * @param inInfo
	 * @return
	 */
	@Override
	public EiInfo delete(EiInfo inInfo) {
		EiInfo outInfo = super.delete(inInfo, "DUHF7102.delete");
		return outInfo;
	}

}
