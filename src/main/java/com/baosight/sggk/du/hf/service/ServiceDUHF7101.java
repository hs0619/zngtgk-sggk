package com.baosight.sggk.du.hf.service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.baosight.sggk.common.du.domain.Tduhb01;
import com.baosight.sggk.du.hf.domain.DUHF71;
import com.baosight.sggk.du.hf.domain.DUHF7102;
import com.baosight.sggk.util.PermissionUtil;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.*;

public class ServiceDUHF7101 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHF7101.class);

	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		// 登录人
		String loginName = String.valueOf(UserSession.getLoginName());
		String loginCName = String.valueOf(UserSession.getLoginCName());
		// 类型
		EiBlock typeBlock = outInfo.addBlock("type");
		typeBlock.setBlockMeta((new DUHF7102()).eiMetadata);
		List list1 = this.dao.query("DUHF7102.query", new HashMap());
		Map<String, Object> typeMap = new HashMap<>();
		typeMap.put("typeid", "%");
		typeMap.put("typename", "请选择");
		list1.add(0, typeMap);
		typeBlock.addRows(list1);
		// 单位
		EiBlock qdepartBlock = outInfo.addBlock("qdepart");
		qdepartBlock.setBlockMeta((new Tduhb01()).eiMetadata);
		// 根据权限拉厂部
		String status = PermissionUtil.getUserDepart(this.dao, UserSession.getLoginName());
		if (!"".equals(status)) {
			if ("%".equals(status)) {
				List<Map> dlist1 = new ArrayList<>();
				Map<String, String> dmap1 = new HashMap<String, String>();
				dmap1.put("departmentName", "请选择");
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
			inInfo.set("inqu_status-0-departmentid", qdepartBlock.getRow(0).get("departmentId"));
		}

		String radiateid = inInfo.getString("radiateid");
		if (!StringUtils.isBlank(radiateid)) {
			DUHF71 duhf71 = queryByRadiateid(radiateid);
			if (duhf71 != null) {
				outInfo.set("inqu_status-0-radiateid", duhf71.getRadiateid());
				outInfo.set("inqu_status-0-departmentid", duhf71.getDepartmentid());
				outInfo.set("inqu_status-0-nuclide", duhf71.getNuclide());
				outInfo.set("inqu_status-0-comedate", duhf71.getComedate());
				outInfo.set("inqu_status-0-comeactivity", duhf71.getComeactivity());
				outInfo.set("inqu_status-0-radiatecode", duhf71.getRadiatecode());
				outInfo.set("inqu_status-0-radiatetype", duhf71.getRadiatetype());
				outInfo.set("inqu_status-0-purpose", duhf71.getPurpose());
				outInfo.set("inqu_status-0-address", duhf71.getAddress());
				outInfo.set("inqu_status-0-supplier", duhf71.getSupplier());
				outInfo.set("inqu_status-0-prounit", duhf71.getProunit());
				outInfo.set("inqu_status-0-remark", duhf71.getRemark());
				outInfo.set("inqu_status-0-recorddate", duhf71.getRecorddate());
				outInfo.set("inqu_status-0-recordid", duhf71.getRecordid());
				outInfo.set("inqu_status-0-recordname", duhf71.getRecordname());
				outInfo.set("inqu_status-0-status", duhf71.getStatus());
				outInfo.set("inqu_status-0-iswork", duhf71.getIswork());
                outInfo.set("inqu_status-0-number", duhf71.getNumber());
                outInfo.set("inqu_status-0-radiatename", duhf71.getRadiatename());
				outInfo.set("inqu_status-0-operation", "update");
			}
		} else {
			outInfo.set("inqu_status-0-recordname", loginCName);
			outInfo.set("inqu_status-0-operation", "insert");
		}
		outInfo.set("inqu_status-0-loginName", loginName);
		outInfo.set("inqu_status-0-loginCName", loginCName);
		return outInfo;
	}

	// 配置
	public EiInfo loadConfig(EiInfo inInfo) {
		// 类型
		EiBlock typeBlock = inInfo.addBlock("type");
		typeBlock.setBlockMeta((new DUHF7102()).eiMetadata);
		List list1 = this.dao.query("DUHF7102.query", new HashMap());
		Map<String, Object> siteMap = new HashMap<>();
		siteMap.put("typeid", "%");
		siteMap.put("typename", "请选择");
		list1.add(0, siteMap);
		typeBlock.setRows(list1);
		inInfo.setBlock(typeBlock);

		return inInfo;
	}

	// 根据radiateid查询投诉信息
	public DUHF71 queryByRadiateid(String radiateid) {
		DUHF71 duhf71 = null;
		Map<String, String> map = new HashMap<String, String>();
		if (!StringUtils.isBlank(radiateid)) {
			map.put("radiateid", radiateid);
			List<DUHF71> list = this.dao.query("DUHF71.query", map);
			if (list.size() > 0 && list != null) {
				duhf71 = list.get(0);
			}
		}
		return duhf71;
	}

	// 根据装置id查询回复信息
	/*public DUHF7103 getByRadiateid(String replyid) {
		DUHF7103 duhf7103 = null;
		Map<String, String> map = new HashMap<String, String>();
		if (!StringUtils.isBlank(replyid)) {
			map.put("replyid", replyid);
			List<DUHF7103> list = this.dao.query("DUHF7103.query", map);
			if (list.size() > 0 && list != null) {
				duhf7103 = list.get(0);
			}
		}
		return duhf7103;
	}*/

	// 暂存或提交
	public EiInfo save(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		String loginname = String.valueOf(UserSession.getLoginName());
		String userName = String.valueOf(UserSession.getLoginCName());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String operation = inInfo.getString("inqu_status-0-operation");
			inInfo.set("inqu_status-0-recorddate", format.format(new Date()));
			if ("update".equals(operation)) {
				inInfo.set("inqu_status-0-updatedman", userName);
				inInfo.set("inqu_status-0-updatedtime", format.format(new Date()));
				dao.update("DUHF71.update", inInfo.getBlock("inqu_status").getRow(0));
				outInfo.set("message", "修改一条放射源记录");
			} else {
				inInfo.set("inqu_status-0-radiateid", UUID.randomUUID().toString());
				inInfo.set("inqu_status-0-recordid", loginname);
				inInfo.set("inqu_status-0-recordname", userName);
				dao.insert("DUHF71.insert", inInfo.getBlock("inqu_status").getRow(0));
				outInfo.set("message", "新增一条放射源记录");
			}
			outInfo.set("status", 1);
		} catch (Exception e) {
			inInfo.set("status", -1);
		}
		return outInfo;
	}

}
