package com.baosight.sggk.util;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.soa.XServiceManager;

import java.util.List;
import java.util.Map;

public class FlowUtil {
	/**
	 * 启动实例 获取processid
	 * @param inInfo
	 * @param processDefinitionKey 流程编码
	 * @param starterId 用户ID
	 * @return
	 */
	public static String initFlow(String processDefinitionKey,String starterId) {
		EiInfo inInfo=new EiInfo();
		EiInfo outInfo = new EiInfo();
		inInfo.set("processDefinitionKey", processDefinitionKey);
		inInfo.set("starterId", starterId);
		inInfo.set(EiConstant.serviceId, "S_EW_00");
		outInfo = XServiceManager.call(inInfo);
		return outInfo.getString("processInstanceId");
	}
	/**
	 * 获取当前taskid
	 * @param inInfo
	 * @param processid 流程实例ID
	 * @param userId 用户ID
	 * @return
	 */
	public static String getTaskId(String processid,String userId) {
		EiInfo inInfo=new EiInfo();
		EiInfo outInfo = new EiInfo();
		inInfo.set("processInstanceId", processid);
		inInfo.set("userId", userId);
		inInfo.set(EiConstant.serviceId, "S_EW_04");
		outInfo = XServiceManager.call(inInfo);
		 if (outInfo.getStatus() < EiConstant.STATUS_DEFAULT) {
            	outInfo.setMsg(outInfo.getMsg());
                //状态返回必须小于0
            	outInfo.setStatus(EiConstant.STATUS_FAILURE);
            }
        Map task = ((List<Map>) outInfo.get("tasks")).get(0);
		return (String) task.get("id");
	}
	/**
	 * 提交任务
	 * @param inInfo
	 * @param taskId 任务id
	 * @param userId 任务提交人ID
	 * @param comment 提交内容
	 * @param transition 转移路径编码
	 */
	public static void submitFlow(String taskId,String userId,String comment,String transition) {
		EiInfo inInfo=new EiInfo();
		inInfo.set("taskId", taskId);
		inInfo.set("userId", userId);
		inInfo.set("comment",comment);
		inInfo.set("transitionKey", transition);
		inInfo.set(EiConstant.serviceId, "S_EW_02");
		EiInfo outInfo = XServiceManager.call(inInfo);
		if (outInfo.getStatus() < EiConstant.STATUS_DEFAULT) {
			inInfo.setMsg(outInfo.getMsg());
			// 状态返回必须小于0
			inInfo.setStatus(EiConstant.STATUS_FAILURE);
		}
	}
	
	/**
	 * 提交任务
	 * @param inInfo
	 * @param taskId 任务id
	 * @param userId 任务提交人ID
	 * @param transition 转移路径编码
	 */
	public static void submitFlow(String taskId,String userId,String transition) {
		EiInfo inInfo=new EiInfo();
		inInfo.set("taskId", taskId);
		inInfo.set("userId", userId);
		inInfo.set("transitionKey", transition);
		inInfo.set(EiConstant.serviceId, "S_EW_02");
		EiInfo outInfo = XServiceManager.call(inInfo);
		if (outInfo.getStatus() < EiConstant.STATUS_DEFAULT) {
			inInfo.setMsg(outInfo.getMsg());
			// 状态返回必须小于0
			inInfo.setStatus(EiConstant.STATUS_FAILURE);
		}
	}
	
	/**
	 * 退回任务
	 * @param inInfo
	 * @param taskId 任务id
	 * @param userId 任务提交人ID
	 * @param transition 转移路径编码
	 */
	public static void backFlow(String taskId,String userId,String comment,String transition) {
		EiInfo inInfo=new EiInfo();
		inInfo.set("taskId", taskId);
		inInfo.set("userId", userId);
		inInfo.set("comment",comment);
		inInfo.set("transitionKey", transition);
		inInfo.set(EiConstant.serviceId, "S_EW_03");
		EiInfo outInfo = XServiceManager.call(inInfo);
		if (outInfo.getStatus() < EiConstant.STATUS_DEFAULT) {
			inInfo.setMsg(outInfo.getMsg());
			// 状态返回必须小于0
			inInfo.setStatus(EiConstant.STATUS_FAILURE);
		}
	}
}
