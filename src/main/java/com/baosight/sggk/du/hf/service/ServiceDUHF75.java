package com.baosight.sggk.du.hf.service;

import com.baosight.sggk.common.du.domain.Tduhb01;
import com.baosight.sggk.du.hf.domain.DUHF75;
import com.baosight.sggk.util.PermissionUtil;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.sggk.util.SelectUtil;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceDUHF75 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHF75.class);

	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		// 单位
		EiBlock qdepartBlock = outInfo.addBlock("qdepart");
		qdepartBlock.setBlockMeta((new Tduhb01()).eiMetadata);
		// 根据权限拉厂部
		String status = PermissionUtil.getUserDepart(this.dao, UserSession.getLoginName());
		if (!"".equals(status)) {
			if ("%".equals(status)) {
				List<Map> dlist1 = new ArrayList<>();
				Map<String, String> dmap1 = new HashMap<String, String>();
				dmap1.put("departmentName", "全部");
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

		// 状态
		EiBlock statusBlock = outInfo.addBlock("status");
		Map<String, Object> statusMap = new HashMap<>();
		statusMap.put("statusid", "0");
		statusMap.put("statusname", "暂存");
		statusBlock.addRow(statusMap);

		statusMap = new HashMap<>();
		statusMap.put("statusid", "1");
		statusMap.put("statusname", "提交");
		statusBlock.addRow(statusMap);

		// 是否在岗
		EiBlock isworkBlock = outInfo.addBlock("iswork");
		Map<String, Object> isworkMap = new HashMap<>();
		isworkMap.put("isworkid", "0");
		isworkMap.put("isworkname", "在岗");
		isworkBlock.addRow(isworkMap);

		isworkMap = new HashMap<>();
		isworkMap.put("isworkid", "1");
		isworkMap.put("isworkname", "离岗");
		isworkBlock.addRow(isworkMap);
		return outInfo;
	}

	/**
	 * 查询功能
	 *
	 * @param inInfo
	 * @return
	 */
	public EiInfo query(EiInfo inInfo) {
		String departmentid = inInfo.getString("inqu_status-0-departmentid");
		if ("%".equals(departmentid)) {
			inInfo.set("inqu_status-0-departmentid", "");
		}
		inInfo.set("inqu_status-0-createdid", UserSession.getLoginName());
		inInfo = super.query(inInfo, "DUHF75.queryUnit", new DUHF75());
		return inInfo;
	}

	public EiInfo delete(EiInfo inInfo) {
		StringBuffer buffer = new StringBuffer();
		int count = 0;
		String loginName = String.valueOf(UserSession.getLoginName());
		try {
			for (int j = 0; j < inInfo.getBlock("result").getRowCount(); j++) {
				String createdid = inInfo.getCellStr("result", j, "createdid");
				if (loginName.equals(createdid)) {
					String status = inInfo.getCellStr("result", j, "status");
					if ("0".equals(status)) {
						dao.delete("DUHF75.delete", inInfo.getBlock("result").getRow(j));
						count++;
						inInfo.setStatus(1);
					} else {
						buffer.insert(0, "删除失败，“提交状态”的记录不可以删除！\n");
						inInfo.setStatus(-1);
						break;
					}
				} else {
					buffer.insert(0, "删除失败，删除记录中有不是您本人登记的记录！\n");
					inInfo.setStatus(-1);
					break;
				}
			}
		} catch (Exception e) {
			throw e;
		}
		if (inInfo.getStatus() == 1) {
			buffer.insert(0, "删除成功" + count + "条记录");
		}
		inInfo.setMsg(buffer.toString());
		return inInfo;
	}
}
