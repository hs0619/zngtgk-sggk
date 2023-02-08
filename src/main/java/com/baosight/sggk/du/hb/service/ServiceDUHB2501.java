package com.baosight.sggk.du.hb.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.sggk.common.du.domain.Tduhb25;
import com.baosight.sggk.common.du.domain.Tduhb2501;
import com.baosight.sggk.util.StrUtil;

public class ServiceDUHB2501 extends ServiceEPBase {
	private static final Logger logger = Logger.getLogger(ServiceDUHB2501.class);
	
	public EiInfo initLoad(EiInfo inInfo) {
		String operate = StrUtil.paramIsNotNullOrEmpty(inInfo.getString("operate"))?inInfo.getString("operate") : "";
		inInfo.set("operate", operate);
		if ("".equals(operate)) {
			inInfo.setStatus(-1);
			inInfo.setMsg("系统参数错误！");
			return inInfo;
		}else if ("insert".equals(operate)) {//新增操作
			//获取所有登录账号的信息，在新增时供用户选择账号进行新增操作
			this.initAccountBlock(inInfo.addBlock("userBlock"));
		}else if("update".equals(operate)) {//修改操作
			//根据报警人记录id，查询对应的记录，回显页面供用户参考修改
			String alarmPersonIdentity = StrUtil.paramIsNotNullOrEmpty(inInfo.getString("alarmPersonIdentity"))?inInfo.getString("alarmPersonIdentity") : ""; 
			if ("".equals(alarmPersonIdentity)) {
				inInfo.setStatus(-1);
				inInfo.setMsg("系统参数丢失！");
				return inInfo;
			}
			this.initAccountBlock(inInfo.addBlock("userBlock"));
			
			Map<String, String> pMap = new HashMap<>();
			pMap.put("alarmPersonIdentity", alarmPersonIdentity);
			List<Tduhb25> alarmPersonList = this.dao.query("tduhb25.queryByUserId",pMap);
			if (StrUtil.listIsNotNullOrEmpty(alarmPersonList)) {
				Tduhb25 duhb25 = alarmPersonList.get(0);
				String alarmPersonName = duhb25.getAlarmPersonName();
				String departmentid = duhb25.getDepartmentid();
				String departmentName = duhb25.getDepartmentName();
				String phone = duhb25.getPhone();
				String monitorType = duhb25.getMonitorType();
				String alarmStartTime = duhb25.getAlarmStartTime();
				String startHour = "";
				String startMin = "";
				if (StrUtil.paramIsNotNullOrEmpty(alarmStartTime)) {
					startHour = alarmStartTime.substring(0,2);
					startMin = alarmStartTime.substring(3);
				}
				String alarmEndTime = duhb25.getAlarmEndTime();
				String endHour = "";
				String endMin = "";
				if (StrUtil.paramIsNotNullOrEmpty(alarmEndTime)) {
					endHour = alarmEndTime.substring(0,2);
					endMin = alarmEndTime.substring(3);
				}
				String status = duhb25.getStatus();
				String creator = duhb25.getCreator();
				String createdtime = duhb25.getCreatedtime();
				inInfo.set("inqu_status-0-alarmPersonIdentity", alarmPersonIdentity);
				inInfo.set("inqu_status-0-alarmPersonName", alarmPersonName);
				inInfo.set("inqu_status-0-departmentid", departmentid);
				inInfo.set("inqu_status-0-departmentName", departmentName);
				inInfo.set("inqu_status-0-phone", phone);
				inInfo.set("inqu_status-0-monitorType", monitorType);
				inInfo.set("inqu_status-0-startHourTemp", startHour);
				inInfo.set("inqu_status-0-startMinTemp", startMin);
				inInfo.set("inqu_status-0-endHourTemp", endHour);
				inInfo.set("inqu_status-0-endMinTemp", endMin);
				inInfo.set("inqu_status-0-status", status);
				inInfo.set("inqu_status-0-creator", creator);
				inInfo.set("inqu_status-0-createdtime", createdtime);
				
			}
			
		}
		
		return inInfo;	
	}
	
	/**
	 * 根据用户选择的登录人id，查询当前登录人的账户信息
	 * @param inInfo
	 * @return
	 */
	public EiInfo queryAccountInfoById(EiInfo inInfo) {
		String userId = inInfo.getString("userId");
		if (!StrUtil.paramIsNotNullOrEmpty(userId)) {
			inInfo.setStatus(-1);
			inInfo.setMsg("系统参数错误");
			return inInfo;
		}
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		List<Tduhb2501> list2501 = this.dao.query("tduhb2501.query",map);
		if (StrUtil.listIsNotNullOrEmpty(list2501)) {
			inInfo.setStatus(1);
			Tduhb2501 tduhb2501 = list2501.get(0);
			inInfo.set("departmentId", tduhb2501.getDepartmentId());
			inInfo.set("userName", tduhb2501.getUserName());
			inInfo.set("mobile", tduhb2501.getMobile());
			inInfo.set("departmentName", tduhb2501.getDepartmentName());
		}
		
		return inInfo;
	}
	

	/**
	 * 新增报警人信息
	 * @param inInfo
	 * @return
	 */
	public EiInfo saveAlarmPersonInfo(EiInfo inInfo) {
		StringBuffer msgBuffer = new StringBuffer();
		String user = String.valueOf(UserSession.getLoginCName());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String alarmPersonIdentity = StrUtil.paramIsNotNullOrEmpty(inInfo.getString("alarmPersonIdentity"))?inInfo.getString("alarmPersonIdentity") : ""; 
		if ("".equals(alarmPersonIdentity)) {
			inInfo.setStatus(-1);
			inInfo.setMsg("系统参数丢失！");
			return inInfo;
		}
		try {
			String alarmPersonName =  inInfo.getString("alarmPersonName");
			String departmentid =  inInfo.getString("departmentid");
			String departmentName =  inInfo.getString("departmentName");
			String phone =  inInfo.getString("phone");
			String monitorType =  inInfo.getString("monitorType");
			String alarmStartTime =  inInfo.getString("alarmStartTime");
			String alarmEndTime =  inInfo.getString("alarmEndTime");
			String status =  inInfo.getString("status");
			
			//根据当前填报的userId,获取对应的登录名称
			Map<String,String> idMap = new HashMap<>();
			idMap.put("userId", alarmPersonIdentity);
			String alarmPersonLoginname = "";
			List<Tduhb2501> list2501 = this.dao.query("tduhb2501.query" , idMap);
			if (StrUtil.listIsNotNullOrEmpty(list2501)) {
				Tduhb2501 duhb2501 = list2501.get(0);
				alarmPersonLoginname = duhb2501.getLoginName();
			}			
			
			//新增报警人信息时，为保持报警人的唯一性，需要判断当前添加的报警人时候已经存在
			Map<String,String> userIdMap = new HashMap<>();
			userIdMap.put("alarmPersonIdentity", alarmPersonIdentity);
			List<Tduhb25> list25 = this.dao.query("tduhb25.queryByUserId",userIdMap);
			if (StrUtil.listIsNotNullOrEmpty(list25)) {
				inInfo.setStatus(2);
				inInfo.setMsg("该报警人信息已存在！");
				return inInfo;
			}
			
			
			Map<String, String> map = new HashMap<>();
			map.put("alarmPersonIdentity", alarmPersonIdentity);
			map.put("alarmPersonLoginname", alarmPersonLoginname);
			map.put("alarmPersonName", alarmPersonName);
			map.put("departmentid", departmentid);
			map.put("departmentName", departmentName);
			map.put("phone", phone);
			map.put("monitorType", monitorType);
			map.put("alarmStartTime", alarmStartTime);
			map.put("alarmEndTime", alarmEndTime);
			map.put("status", status);
			map.put("creator", user);
			map.put("createdtime", sdf.format(new Date()));

			
			dao.insert("tduhb25.insert",map);
			inInfo.setStatus(1);
			msgBuffer.insert(0, "保存成功\n");
			
		} catch (Exception e) {
           msgBuffer.insert(0, "记录保存失败\n" + e.toString() );
           inInfo.setStatus( -1 );
		}
		
		inInfo.setMsg(msgBuffer.toString());
		return inInfo;
	}
	
	
	/**
	 * 修改报警人信息
	 * @param inInfo
	 * @return
	 */
	public EiInfo updateAlarmPersonInfo(EiInfo inInfo) {
		StringBuffer msgBuffer = new StringBuffer();
		String user = String.valueOf(UserSession.getLoginCName());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String alarmPersonIdentity = StrUtil.paramIsNotNullOrEmpty(inInfo.getString("alarmPersonIdentity"))?inInfo.getString("alarmPersonIdentity") : ""; 
		if ("".equals(alarmPersonIdentity)) {
			inInfo.setStatus(-1);
			inInfo.setMsg("系统参数丢失！");
			return inInfo;
		}
		
		try {
			String alarmPersonName =  inInfo.getString("alarmPersonName");
			String departmentid =  inInfo.getString("departmentid");
			String departmentName =  inInfo.getString("departmentName");
			String phone =  inInfo.getString("phone");
			String monitorType =  inInfo.getString("monitorType");
			String alarmStartTime =  inInfo.getString("alarmStartTime");
			String alarmEndTime =  inInfo.getString("alarmEndTime");
			String status =  inInfo.getString("status");
			String creator =  inInfo.getString("creator");
			String createdtime =  inInfo.getString("createdtime");
			
			//根据当前填报的userId,获取对应的登录名称
			Map<String,String> idMap = new HashMap<>();
			idMap.put("userId", alarmPersonIdentity);
			String alarmPersonLoginname = "";
			List<Tduhb2501> list2501 = this.dao.query("tduhb2501.query" , idMap);
			if (StrUtil.listIsNotNullOrEmpty(list2501)) {
				Tduhb2501 duhb2501 = list2501.get(0);
				alarmPersonLoginname = duhb2501.getLoginName();
			}	
			
			
			Map<String, String> map = new HashMap<>();
			map.put("alarmPersonIdentity", alarmPersonIdentity);
			map.put("alarmPersonLoginname", alarmPersonLoginname);
			map.put("alarmPersonName", alarmPersonName);
			map.put("departmentid", departmentid);
			map.put("departmentName", departmentName);
			map.put("phone", phone);
			map.put("monitorType", monitorType);
			map.put("alarmStartTime", alarmStartTime);
			map.put("alarmEndTime", alarmEndTime);
			map.put("status", status);
			map.put("creator", creator);
			map.put("createdtime", createdtime);
			map.put("modifier", user);
			map.put("updatedtime", sdf.format(new Date()));
			
			dao.update("tduhb25.update",map);
			msgBuffer.insert(0, "修改成功\n");
			
		} catch (Exception e) {
           msgBuffer.insert(0, "修改记录失败\n" + e.toString() );
           inInfo.setStatus( -1 );
		}
		
		inInfo.setMsg(msgBuffer.toString());
		
		return inInfo;
	}	
	
	

	
	/**
	 * 初始化所有的登录账号信息
	 */
	private void  initAccountBlock(EiBlock userBlock) {
		EiBlockMeta meta = new Tduhb2501().eiMetadata;
		userBlock.addBlockMeta(meta);
	
		List<Tduhb2501> list = this.dao.query("tduhb2501.query",new HashMap<>());
		if (StrUtil.listIsNotNullOrEmpty(list)) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> row = new HashMap<>();
				row.put("userId", list.get(i).getUserId());
				row.put("loginName", list.get(i).getLoginName());
				userBlock.addRow(row);
			}
		}
	}
	

	
	/**
	 * 获取一个uuid
	 * @return
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		//去掉"-"符号
		return uuid.replaceAll("-", "");
	}


}
