package com.baosight.sggk.du.hf.service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.baosight.sggk.common.du.domain.Tduhb01;
import com.baosight.sggk.du.hf.domain.DUHF75;
import com.baosight.sggk.util.PermissionUtil;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.sggk.util.SelectUtil;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.*;

public class ServiceDUHF7501 extends ServiceBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHF7501.class);

	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		// 登录人
		String loginName = String.valueOf(UserSession.getLoginName());
		String loginCName = String.valueOf(UserSession.getLoginCName());
		// 单位
		EiBlock qdepartBlock = outInfo.addBlock("qdepart");
		qdepartBlock.setBlockMeta((new Tduhb01()).eiMetadata);
		// 根据权限拉厂部
		String status = PermissionUtil.getUserDepart(this.dao, UserSession.getLoginName());
		if (!"".equals(status)) {
			if ("%".equals(status)) {
				List<Map> dlist1 = new ArrayList<>();
				Map<String, String> dmap1 = new HashMap<String, String>();
				dmap1.put("departmentName", "--请选择--");
				dmap1.put("departmentId", "%");
				dlist1.add(dmap1);
				qdepartBlock.addRows(dlist1);
				Map dquerymap = new HashMap();
				dquerymap.put("type", "D1");
				dquerymap.put("parentid", "root");
				dquerymap.put("status", "1");
				List dlist2 = this.dao.query("tduhb01.query", dquerymap);
				qdepartBlock.addRows(dlist2);
			} else {
				Map dquerymap = new HashMap();
				dquerymap.put("type", "D1");
				dquerymap.put("departmentId", status);
				dquerymap.put("status", "1");
				List dlist2 = this.dao.query("tduhb01.query", dquerymap);
				qdepartBlock.addRows(dlist2);
			}
		}
		if (qdepartBlock.getRowCount() > 0) {
			outInfo.set("inqu_status-0-departmentid", qdepartBlock.getRow(0).get("departmentId"));
		}
        // 性别
        outInfo.addBlock(SelectUtil.getTextAndValue(dao,"gender","1"));

        // 学历
        outInfo.addBlock(SelectUtil.getTextAndValue(dao,"education","1"));

        // 证件类型
        outInfo.addBlock(SelectUtil.getTextAndValue(dao,"certificate","1"));

		String logicid = inInfo.getString("logicid");
		if (!StringUtils.isBlank(logicid)) {
			DUHF75 duhf75 = queryBylogicid(logicid);
			if (duhf75 != null) {
				outInfo.set("inqu_status-0-logicid", duhf75.getLogicid());
				outInfo.set("inqu_status-0-status", duhf75.getStatus());
				outInfo.set("inqu_status-0-departmentid", duhf75.getDepartmentid());
				outInfo.set("inqu_status-0-workerid", duhf75.getWorkerid());
				outInfo.set("inqu_status-0-workername", duhf75.getWorkername());
				outInfo.set("inqu_status-0-gender", duhf75.getGender());
				outInfo.set("inqu_status-0-birthdate", duhf75.getBirthdate());
				outInfo.set("inqu_status-0-graschool", duhf75.getGraschool());
				outInfo.set("inqu_status-0-education", duhf75.getEducation());
				outInfo.set("inqu_status-0-eventtype", duhf75.getEventtype());
				outInfo.set("inqu_status-0-certificatetype", duhf75.getCertificatetype());
				outInfo.set("inqu_status-0-certificatenumber", duhf75.getCertificatenumber());
				outInfo.set("inqu_status-0-operatingpost", duhf75.getOperatingpost());
				outInfo.set("inqu_status-0-deadlinedate", duhf75.getDeadlinedate());
				outInfo.set("inqu_status-0-certificateno", duhf75.getCertificateno());
				outInfo.set("inqu_status-0-certificatename", duhf75.getCertificatename());
				outInfo.set("inqu_status-0-remark", duhf75.getRemark());
				outInfo.set("inqu_status-0-iswork", duhf75.getIswork());
				outInfo.set("inqu_status-0-createdid", duhf75.getCreatedid());
				outInfo.set("inqu_status-0-createdman", duhf75.getCreatedman());
				outInfo.set("inqu_status-0-createdtime", duhf75.getCreatedtime());
				outInfo.set("inqu_status-0-operation", "update");

			}
		} else {
//			outInfo.set("inqu_status-0-ename", loginName);
//			outInfo.set("inqu_status-0-name", loginCName);
			outInfo.set("inqu_status-0-createdman", loginCName);
			outInfo.set("inqu_status-0-operation", "insert");
		}
		outInfo.set("inqu_status-0-loginName", loginName);
		outInfo.set("inqu_status-0-loginCName", loginCName);
		return outInfo;
	}

	// 根据logicid查询投诉信息
	public DUHF75 queryBylogicid(String logicid) {
		DUHF75 duhf75 = null;
		Map<String, String> map = new HashMap<String, String>();
		if (!StringUtils.isBlank(logicid)) {
			map.put("logicid", logicid);
			List<DUHF75> list = this.dao.query("DUHF75.query", map);
			if (list.size() > 0 && list != null) {
				duhf75 = list.get(0);
			}
		}
		return duhf75;
	}


	// 暂存或提交
	public EiInfo save(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		String loginname = String.valueOf(UserSession.getLoginName());
		String userName = String.valueOf(UserSession.getLoginCName());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String operation = inInfo.getString("inqu_status-0-operation");
			inInfo.set("inqu_status-0-createdtime", format.format(new Date()));
			if ("update".equals(operation)) {
				inInfo.set("inqu_status-0-updatedman", userName);
				inInfo.set("inqu_status-0-updatedtime", format.format(new Date()));
				dao.update("DUHF75.update", inInfo.getBlock("inqu_status").getRow(0));
				outInfo.set("message", "修改一条辐射工作人员记录");
			} else {
				inInfo.set("inqu_status-0-logicid", UUID.randomUUID().toString());
				inInfo.set("inqu_status-0-createdid", loginname);
				inInfo.set("inqu_status-0-createdman", userName);
				dao.insert("DUHF75.insert", inInfo.getBlock("inqu_status").getRow(0));
				outInfo.set("message", "新增一条辐射工作人员记录");
			}
			outInfo.set("status", 1);
		} catch (Exception e) {
			inInfo.set("status", -1);
		}
		return outInfo;
	}

}
