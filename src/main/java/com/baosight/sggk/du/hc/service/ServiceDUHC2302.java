/**
 *
 */
package com.baosight.sggk.du.hc.service;

import com.baosight.sggk.du.hc.domain.DUHC2302;
import com.baosight.sggk.util.PermissionUtil;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.sggk.util.SelectUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.*;

//

public class ServiceDUHC2302 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHC2302.class);

	// 获取配置文件里的参数
	public ResourceBundle dbPro = ResourceBundle.getBundle("application");
	public String DbSchema = dbPro.getString("hbSchema");

	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy");
		outInfo.set("inqu_status-0-clockyear", dateTimeFormat.format(new Date()));



		// 计划类型
		EiBlock plantypeBlock = outInfo.addBlock("plantype");
		List<Map> plantypelist1 = new ArrayList<>();
		Map<String, String> plantypemap1 = new HashMap<String, String>();
		plantypemap1.put("plantypename", "计划内");
		plantypemap1.put("plantypeid", "1");
		plantypelist1.add(plantypemap1);
		Map<String, String> plantypemap2 = new HashMap<String, String>();
		plantypemap2.put("plantypename", "计划外");
		plantypemap2.put("plantypeid", "0");
		plantypelist1.add(plantypemap2);
		plantypeBlock.addRows(plantypelist1);
		// 是否比对
		EiBlock iscompareBlock = outInfo.addBlock("iscompare");
		List<Map> iscomparelist1 = new ArrayList<>();
		Map<String, String> iscomparemap1 = new HashMap<String, String>();
		iscomparemap1.put("iscomparename", "有比对");
		iscomparemap1.put("iscompareid", "1");
		iscomparelist1.add(iscomparemap1);
		Map<String, String> iscomparemap2 = new HashMap<String, String>();
		iscomparemap2.put("iscomparename", "没有比对");
		iscomparemap2.put("iscompareid", "0");
		iscomparelist1.add(iscomparemap2);
		iscompareBlock.addRows(iscomparelist1);
		// 是否在用
		EiBlock statusBlock = outInfo.addBlock("status");
		List<Map> statuslist1 = new ArrayList<>();
		Map<String, String> statusmap1 = new HashMap<String, String>();
		statusmap1.put("statusname", "在用");
		statusmap1.put("statusid", "1");
		statuslist1.add(statusmap1);
		Map<String, String> statusmap2 = new HashMap<String, String>();
		statusmap2.put("statusname", "停用");
		statusmap2.put("statusid", "0");
		statuslist1.add(statusmap2);
		statusBlock.addRows(statuslist1);

		// 登录人
		String loginName = String.valueOf(UserSession.getLoginName());
		outInfo.set("inqu_status-0-departmentid", PermissionUtil.getUserDepart(dao, loginName));
		return outInfo;
	}

	public EiInfo query(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		String clockyear = inInfo.getString("inqu_status-0-clockyear");
		String monitorid = inInfo.getString("inqu_status-0-monitorid");
		String plantype = inInfo.getString("inqu_status-0-plantype");
		String status = inInfo.getString("inqu_status-0-status");
		String departmentid = inInfo.getString("inqu_status-0-departmentid");
		String siteid = inInfo.getString("inqu_status-0-siteid");
		if (StringUtils.isBlank(clockyear)) {
			inInfo.set("inqu_status-0-clockyear", "空");
		}
		if ("%".equals(monitorid)) {
			inInfo.set("inqu_status-0-monitorid", "");
		}
		if ("%".equals(plantype)) {
			inInfo.set("inqu_status-0-plantype", "");
		}
		if ("%".equals(status)) {
			inInfo.set("inqu_status-0-status", "");
		}
		if ("%".equals(departmentid)) {
			inInfo.set("inqu_status-0-departmentid", "");
		}
		if ("%".equals(siteid)) {
			inInfo.set("inqu_status-0-siteid", "");
		}
		outInfo = super.query(inInfo, "DUHC2302.query1", new DUHC2302());
		return outInfo;

	}

	public EiInfo delete(EiInfo inInfo) {
		EiInfo outInfo = super.delete(inInfo, "DUHC2302.delete1");
		if (outInfo.getBlock("result").getRowCount() > 0) {
			for (int i = 0; i < outInfo.getBlock("result").getRowCount(); i++) {
				String planid = inInfo.getBlock("result").getCellStr(i, "planid");
				Map<String, String> map = new HashMap<>();
				map.put("planid", planid);
				dao.delete("DUHC230202.deleteByPlanid", map);
			}
		}
		return outInfo;
	}

}
