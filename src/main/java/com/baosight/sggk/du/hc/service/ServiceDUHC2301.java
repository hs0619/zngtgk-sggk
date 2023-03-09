package com.baosight.sggk.du.hc.service;

import com.baosight.sggk.du.hc.domain.DUHC23;
import com.baosight.sggk.du.hc.domain.DUHC2302;
import com.baosight.sggk.du.hc.domain.DUHC230202;
import com.baosight.sggk.util.PermissionUtil;
import com.baosight.sggk.util.StrUtil;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceDUHC2301 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHC2301.class);
	// 登录人工号
	String loginName = UserSession.getLoginName();
	// 登录人名称
	String loginCName = UserSession.getLoginCName();

	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		String siteid = StringUtils.isBlank((String) inInfo.get("siteid")) ? "" : (String) inInfo.get("siteid");
		String factorid = StringUtils.isBlank((String) inInfo.get("factorid")) ? "" : (String) inInfo.get("factorid");
		String itemtime = StringUtils.isBlank((String) inInfo.get("itemtime")) ? "" : (String) inInfo.get("itemtime");
		String planid = StringUtils.isBlank((String) inInfo.get("planid")) ? "" : (String) inInfo.get("planid");
		// 编辑状态处理
		List<DUHC23> list = getManualById(siteid, factorid, itemtime, planid);
		if (list != null && list.size() > 0) {
			DUHC23 duhc23 = list.get(0);
			outInfo.set("inqu_status-0-departmentid", duhc23.getDepartmentid());
			outInfo.set("inqu_status-0-siteid", siteid);
			outInfo.set("inqu_status-0-monitorid", duhc23.getMonitorid());
			outInfo.set("inqu_status-0-planid", planid);
			outInfo.set("inqu_status-0-itemtime", itemtime);
			outInfo.set("inqu_status-0-oprationType", "update");
			EiBlock resultBlock = outInfo.addBlock("result");
			resultBlock.addRows(list);
		} else {
			outInfo.set("inqu_status-0-itemtime", sdf.format(new Date()));
			outInfo.set("inqu_status-0-monitorid", "01");
			// 登录人
			String loginName = String.valueOf(UserSession.getLoginName());
			outInfo.set("inqu_status-0-departmentid", PermissionUtil.getUserDepart(dao, loginName));
		}
		outInfo.setMsg("页面加载完成");
		return outInfo;
	}

	/**
	 * 查询监测计划
	 * 
	 * @param inInfo
	 * @return
	 */
	public EiInfo queryPlan(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		String departmentid = StringUtils.isBlank(inInfo.getString("inqu_status-0-departmentid")) ? ""
				: inInfo.getString("inqu_status-0-departmentid");
		String siteid = StringUtils.isBlank(inInfo.getString("inqu_status-0-siteid")) ? ""
				: inInfo.getString("inqu_status-0-siteid");
		String monitorid = StringUtils.isBlank(inInfo.getString("inqu_status-0-monitorid")) ? ""
				: inInfo.getString("inqu_status-0-monitorid");
		String planid = StringUtils.isBlank(inInfo.getString("planid")) ? "" : inInfo.getString("planid");
		String oprationType = inInfo.getString("inqu_status-0-oprationType");
		EiBlock planBlock = outInfo.addBlock("plan");
		planBlock.addBlockMeta(new DUHC2302().eiMetadata);
		Map<String, Object> planMap = new HashMap<>();
		planMap.put("departmentid", "%".equals(departmentid) ? "" : departmentid);
		planMap.put("monitorid", "%".equals(monitorid) ? "" : monitorid);
		planMap.put("siteid", siteid);
		planMap.put("status", "1");
		List<DUHC2302> planList = dao.query("DUHC2302.query1", planMap);
		if ("insert".equals(oprationType)) {
			if (planList != null && planList.size() > 0) {
				Map<String, Object> map=new HashMap<>();
				map.put("planid", planList.get(0).getPlanid());
				List<DUHC23> timeList=dao.query("DUHC23.querytime",map);
				if (timeList!=null&& timeList.size()>0) {
					outInfo.set("inqu_status-0-lastitemtime", "上一次采样时间："+timeList.get(0).getItemtime());
				}else {
					outInfo.set("inqu_status-0-lastitemtime", "上一次采样时间：无");
				}
				// 查询因子
				inInfo.set("inqu_status-0-planid", planList.get(0).getPlanid());
				// 前台回显
				outInfo.set("inqu_status-0-planid", planList.get(0).getPlanid());
			}
		} else {
			inInfo.set("inqu_status-0-planid", planid);
		}
		planBlock.addRows(planList);
		outInfo.addBlock(queryFactor(inInfo).getBlock("result"));
		return outInfo;
	}

	/**
	 * 根据计划查询因子
	 * 
	 * @param inInfo
	 * @return
	 */
	public EiInfo queryFactor(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		String oprationType = inInfo.getString("inqu_status-0-oprationType");
		EiBlock result=new EiBlock("result");
		result.setBlockMeta(new DUHC230202().eiMetadata);
		
		List<DUHC230202> list=null;
		if ("insert".equals(oprationType)) {
			String planid = StringUtils.isBlank(inInfo.getString("inqu_status-0-planid")) ? "空"
					: inInfo.getString("inqu_status-0-planid");
			inInfo.set("inqu_status-0-planid", planid);
			//outInfo = super.query(inInfo, "DUHC230202.queryByPlanid", new DUHC230202());
//			if (outInfo.getBlock("result").getRowCount() > 0) {
//				for (int i = 0; i < outInfo.getBlock("result").getRowCount(); i++) {
//					String lowlimit=(String) outInfo.getBlock("result").getCell(i, "lowlimit");
//					outInfo.getBlock("result").setCell(i, "itemunit", outInfo.getBlock("result").getCell(i, "unit"));
//					if (StringUtils.isNotBlank(lowlimit)) {
//						outInfo.getBlock("result").setCell(i, "itemlimit",lowlimit+"~"+outInfo.getBlock("result").getCell(i, "highlimit"));
//					}else {
//						outInfo.getBlock("result").setCell(i, "itemlimit",outInfo.getBlock("result").getCell(i, "highlimit"));
//					}
//				}
//			}
			list=dao.query("DUHC230202.queryByPlanid",inInfo.getBlock("inqu_status").getRow(0), 0, -999999);
			if (StrUtil.listIsNotNullOrEmpty(list)) {
				for (int i = 0; i < list.size(); i++) {
					DUHC230202 duhc230202=(DUHC230202) list.get(i);
					String lowlimit=duhc230202.getLowlimit();
					if (StringUtils.isNotBlank(lowlimit)) {
						list.get(i).setItemlimit(lowlimit+"~"+duhc230202.getHighlimit());
					}else {
						list.get(i).setItemlimit(duhc230202.getHighlimit());
					}
				}
			}
			Map<String, Object> map=new HashMap<>();
			map.put("planid", planid);
			List<DUHC23> timeList=dao.query("DUHC23.querytime",map);
			if (timeList!=null&& timeList.size()>0) {
				outInfo.set("inqu_status-0-lastitemtime", "上一次采样时间："+timeList.get(0).getItemtime());
			}else {
				outInfo.set("inqu_status-0-lastitemtime", "上一次采样时间：无");
			}
		} else {
			inInfo.set("inqu_status-0-factorid", "");
			//outInfo = super.query(inInfo, "DUHC23.queryDetail", new DUHC23());
			list=dao.query("DUHC23.queryDetail",inInfo.getBlock("inqu_status").getRow(0), 0, -999999);
		}
		result.setRows(list);
		result.set("limit",1000000);
		outInfo.addBlock(result);
		return outInfo;
	}

	// 保存手工监测数据
	public EiInfo save(EiInfo inInfo) {
		StringBuffer buffer = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int successInsertCount = 0;
		int failInsertCount = 0;
		int successUpdateCount = 0;
		int failUpdateCount = 0;
		String departmentid = StringUtils.isBlank(inInfo.getString("inqu_status-0-departmentid")) ? ""
				: inInfo.getString("inqu_status-0-departmentid");
		String siteid = StringUtils.isBlank(inInfo.getString("inqu_status-0-siteid")) ? ""
				: inInfo.getString("inqu_status-0-siteid");
		String monitorid = StringUtils.isBlank(inInfo.getString("inqu_status-0-monitorid")) ? ""
				: inInfo.getString("inqu_status-0-monitorid");
		String planid = StringUtils.isBlank(inInfo.getString("inqu_status-0-planid")) ? ""
				: inInfo.getString("inqu_status-0-planid");
		String itemtime = StringUtils.isBlank(inInfo.getString("inqu_status-0-itemtime")) ? ""
				: inInfo.getString("inqu_status-0-itemtime");
		String oprationType = inInfo.getString("inqu_status-0-oprationType");
		try {
			List<DUHC23> list = null;
			for (int i = 0; i < inInfo.getBlock("result").getRows().size(); i++) {
				if ("insert".equals(oprationType)) {
					inInfo.getBlock("result").setCell(i, "createid", loginName);
					inInfo.getBlock("result").setCell(i, "createname", loginCName);
					inInfo.getBlock("result").setCell(i, "createtime", sdf.format(new Date()));
					inInfo.getBlock("result").setCell(i, "departmentid", departmentid);
					inInfo.getBlock("result").setCell(i, "siteid", siteid);
					inInfo.getBlock("result").setCell(i, "monitorid", monitorid);
					inInfo.getBlock("result").setCell(i, "planid", planid);
					inInfo.getBlock("result").setCell(i, "itemtime", itemtime);
					list = getManualById(siteid, inInfo.getBlock("result").getCellStr(i, "factorid"), itemtime, planid);
					if (list != null && list.size() > 0) {
						String factorname = inInfo.getBlock("result").getCellStr(i, "factorname");
						inInfo.setMsg("同一时间同一计划" + factorname + "记录已存在！");
						inInfo.setStatus(2);
						return inInfo;
					} else {
						dao.insert("DUHC23.insert", inInfo.getBlock("result").getRow(i));
						successInsertCount++;
					}
				} else {
					inInfo.getBlock("result").setCell(i, "planid", planid);
					inInfo.getBlock("result").setCell(i, "updateid", loginName);
					inInfo.getBlock("result").setCell(i, "updatename", loginCName);
					inInfo.getBlock("result").setCell(i, "updatetime", sdf.format(new Date()));
					inInfo.getBlock("result").setCell(i, "siteid", siteid);
					inInfo.getBlock("result").setCell(i, "itemtime", itemtime);
					dao.update("DUHC23.update", inInfo.getBlock("result").getRow(i));
					successUpdateCount++;
				}
			}
			inInfo.setStatus(1);
		} catch (Exception e) {
			if ("insert".equals(oprationType)) {
				failInsertCount++;
			} else {
				failUpdateCount++;
			}
			inInfo.setStatus(2);
		}
		if (successInsertCount > 0) {
			buffer.insert(0, "新增成功" + successInsertCount + "条记录！\n");
		} else if (successUpdateCount > 0) {
			buffer.insert(0, "修改成功" + successUpdateCount + "条记录！\n");
		} else if (failInsertCount > 0) {
			buffer.insert(0, "新增失败" + failInsertCount + "条记录！\n");
		} else if (failUpdateCount > 0) {
			buffer.insert(0, "修改失败" + failUpdateCount + "条记录！\n");
		}
		inInfo.setMsg(buffer.toString());
		return inInfo;
	}

	//
	private List<DUHC23> getManualById(String siteid, String factorid, String itemtime, String planid) {
		try {
			if (StringUtils.isBlank(siteid)) {
				siteid = "空";
			}
			if (StringUtils.isBlank(factorid)) {
				factorid = "空";
			}
			if (StringUtils.isBlank(itemtime)) {
				itemtime = "空";
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("factorid", factorid);
			map.put("itemtime", itemtime);
			map.put("planid", planid);
			List list = this.dao.query("DUHC23.query", map);
			if (list.size() > 0) {
				return list;
			}
			return null;
		} catch (Exception ex) {
			throw ex;
		}
	}
}
