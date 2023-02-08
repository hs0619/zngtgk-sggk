package com.baosight.sggk.du.hb.service;

import com.baosight.sggk.common.du.domain.Tduhb99;
import com.baosight.sggk.util.EnumUtil;
import com.baosight.sggk.util.StrUtil;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;

import java.text.SimpleDateFormat;
import java.util.*;
//import com.baosight.eplat.shareservice.service.EServiceManager;


public class ServiceDUHB99 extends ServiceEPBase {

	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
			outInfo = query(inInfo);
		return outInfo;
	}

	public EiInfo query(EiInfo inInfo) {
		String loginName = String.valueOf(UserSession.getLoginName());
		String loginCName = String.valueOf(UserSession.getLoginCName());
		EiBlock resBlock = new EiBlock("result");
		resBlock.addBlockMeta(new Tduhb99().eiMetadata);
		Map<String, Object> pMap = new HashMap<>();
//		List<String> statusList = new ArrayList<>();
//		statusList.add(EnumUtil.ONE_OFF_STREAM.getKey());
		//厂部ID
		//String departmentId = PermissionUtil.getDepartmentIdByLoginName(loginName,dao);
		pMap.put("status", EnumUtil.ONE_OFF_STREAM.getKey());
		pMap.put("userId", loginName);
        pMap.put("isonline", "1");
		List<Tduhb99> list = this.dao.query("tduhb99.query", pMap,0,1000);
	//	List<Tduhb99> listPaiWu = this.dao.query("tduhb99.query2", pMap,0,1000);
//		pMap.put("status", "1");
//		List<Tduhb99> list1 = this.dao.query("tduhb99.query", pMap,0,1000);
//		pMap.put("status", "2");
//		List<Tduhb99> list2 = this.dao.query("tduhb99.query", pMap,0,1000);
		resBlock.setRows(list);
//		resBlock.addRows(list1);
//		resBlock.addRows(list2);
		inInfo.addBlock(resBlock);
		inInfo.getBlock("result").set("offset", 0);
		inInfo.getBlock("result").set("limit", 9999);
		inInfo.getBlock("result").set("count", list.size());
		return inInfo;
	}

	/**
	 * 调用sdk服务处理消息，并跳转待办消息处理页面
	 * @param inInfo
	 * @return
	 */
	public EiInfo checkMessages(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		String messageId = StrUtil.isNullToStr(inInfo.getString("messageId"));
		if ("".equals(messageId)) {
			outInfo.setStatus(-1);
			outInfo.setMsg("系统参数丢失，请联系管理员！");
			return outInfo;
		}
		String messageTitle = getMessageTitleById(messageId);
		String messageType = getMessageTypeById(messageId);
		//没有数字平台以下可以不需要
		if(messageTitle.indexOf("超标通知单处理完毕")!=-1||messageTitle.indexOf("临时计划已下发执行")!=-1||messageTitle.indexOf("临时计划数据审核完毕")!=-1||messageTitle.indexOf("临时计划退回")!=-1||messageTitle.indexOf("停机申报被退回")!=-1||messageTitle.indexOf("停机申报审核通过")!=-1||messageTitle.indexOf("视频分析已处置")!=-1) {
			//1.需要先调用数字平台的sdk服务，将对应的用户的待办消息数量，进行增加或减少处理
			String loginName = String.valueOf(UserSession.getLoginName());
			//获取待办信息表中，当前登录人需要待办的信息条数，先默认进行减一操作，
			Map<String, String> wclMap = new HashMap<>();
			wclMap.put("status", "0");
			wclMap.put("userId", loginName);
			List<Tduhb99> list = this.dao.query("tduhb99.query", wclMap , 0 ,1000);
			int sendNum = 0;
			if (list != null) {
				if (list.size() > 0) {
					sendNum = list.size() - 1;
				}
				int reStatus = getSolveStatus(loginName,sendNum);
				//2.当调用sdk的服务状态值为 ‘ 1 ’时，说明调用成功，将系统数据表中对应的待办消息，状态置为 “ 1 ”，表示已经查看过了
				//3.返回成功的执行成功的状态值1
				if (reStatus == 1) {
					Map<String, String> idMap = new HashMap<>();
					idMap.put("messageId", messageId);
					idMap.put("status", "1");
					idMap.put("solvePerson", loginName);
					idMap.put("solvedTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					try {
						this.dao.update("tduhb99.updateMessage",idMap);
						outInfo.setStatus(1);
					} catch (Exception e) {
						outInfo.setStatus(-1);
						outInfo.setMsg("系统消息状态修改失败。");
					}
				}
			}
		}
		return outInfo;

	}


	public int getSolveStatus(String handler,int sendNum) {
		EiInfo eiInfo = new EiInfo();
		List msgNumList= new ArrayList();//查看待办消息人员的待办消息的数量
		msgNumList.add(String.valueOf(sendNum));

		List userIdList = new ArrayList();
		userIdList.add(handler);
		eiInfo.set("message","环保待办消息-查看");
		eiInfo.set("appEname","cqgthb-sggk");
		eiInfo.set("userIdList",userIdList);
		eiInfo.set("msgNumList",msgNumList);
		eiInfo.set("sendUserId",handler);
		eiInfo.set(EiConstant.serviceId,"S_BI_MS_15");
//		EiInfo outInfo = EServiceManager.call(eiInfo);
//		int status = outInfo.getStatus();
		//c
		int status = eiInfo.getStatus();
		//
		return status;
	}


	public EiInfo getSolveOutInfo(String handler,int sendNum) {
		EiInfo eiInfo = new EiInfo();
		List msgNumList= new ArrayList();//查看待办消息人员的待办消息的数量
		msgNumList.add(String.valueOf(sendNum));

		List userIdList = new ArrayList();
		userIdList.add(handler);
		eiInfo.set("message","环保待办消息-查看");
		eiInfo.set("appEname","cqgthb-sggk");
		eiInfo.set("userIdList",userIdList);
		eiInfo.set("msgNumList",msgNumList);
		eiInfo.set("sendUserId",handler);
		eiInfo.set(EiConstant.serviceId,"S_BI_MS_15");
	//	EiInfo outInfo = EServiceManager.call(eiInfo);
	//	return outInfo;

		return eiInfo;
	}





	//根据当前的待办消息id,去获取点击的待办记录的消息类型
	public String getMessageTypeById(String messageId) {
		String messageType = "";
		Map<String, String> pMap = new HashMap<>();
		pMap.put("messageId", messageId);
		List<Tduhb99> list = this.dao.query("tduhb99.query",pMap , 0 ,1000);
		if (StrUtil.listIsNotNullOrEmpty(list)) {
			messageType = list.get(0).getMessageType();
		}
		return messageType;
	}
	    //根据当前的待办消息id,去获取点击的待办记录的消息内容
		public String getMessageTitleById(String messageId) {
			String messageTitle = "";
			Map<String, String> pMap = new HashMap<>();
			pMap.put("messageId", messageId);
			List<Tduhb99> list = this.dao.query("tduhb99.query",pMap , 0 ,1000);
			if (StrUtil.listIsNotNullOrEmpty(list)) {
				messageTitle = list.get(0).getTitle();
			}
			return messageTitle;
		}

}
