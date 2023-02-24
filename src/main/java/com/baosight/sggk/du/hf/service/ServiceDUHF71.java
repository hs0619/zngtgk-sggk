package com.baosight.sggk.du.hf.service;

import com.baosight.sggk.common.du.domain.Tduhb01;
import com.baosight.sggk.du.hf.domain.DUHF71;
import com.baosight.sggk.du.hf.domain.DUHF7102;
import com.baosight.sggk.util.PermissionUtil;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceDUHF71 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHF71.class);

	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();

		// 类型
		EiBlock qtypeBlock = outInfo.addBlock("qtype");
		qtypeBlock.setBlockMeta((new DUHF7102()).eiMetadata);
		List list1 = this.dao.query("DUHF7102.query", new HashMap());
		Map<String, Object> typeMap = new HashMap<>();
		typeMap.put("typeid", "%");
		typeMap.put("typename", "全部");
		list1.add(0, typeMap);
		qtypeBlock.addRows(list1);

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
		

		// 日期
		/*SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cale = null;
		cale = Calendar.getInstance();
		cale.add(Calendar.MONTH, -1);
		// cale.set(Calendar.DAY_OF_MONTH, 1);
		String firstday = sdf1.format(cale.getTime());
		outInfo.set("inqu_status-0-beginDate", firstday);
		outInfo.set("inqu_status-0-endDate", sdf1.format(new Date()));*/
		//状态
		EiBlock statusBlock=outInfo.addBlock("status");
		Map<String, Object> statusMap=new HashMap<>();
		statusMap.put("statusid", "0");
		statusMap.put("statusname", "暂存");
		statusBlock.addRow(statusMap);
		
		statusMap=new HashMap<>();
		statusMap.put("statusid", "1");
		statusMap.put("statusname", "提交");
		statusBlock.addRow(statusMap);
		//
		EiBlock useBlock=outInfo.addBlock("use");
		Map<String, Object> useMap=new HashMap<>();
		useMap.put("useid", "0");
		useMap.put("usename", "否");
		useBlock.addRow(useMap);
		
		useMap=new HashMap<>();
		useMap.put("useid", "1");
		useMap.put("usename", "是");
		useBlock.addRow(useMap);
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
		String radiatetype = inInfo.getString("inqu_status-0-radiatetype");
		/*String beginDate = inInfo.getString("inqu_status-0-beginDate");
		String endDate = inInfo.getString("inqu_status-0-endDate");*/
		if ("%".equals(departmentid)) {
			inInfo.set("inqu_status-0-departmentid", "");
		}
		if ("%".equals(radiatetype)) {
			inInfo.set("inqu_status-0-radiatetype", "");
		}
		/*inInfo.set("inqu_status-0-beginDate", beginDate+" 00:00:00");
		inInfo.set("inqu_status-0-endDate", endDate+" 23:59:59");*/
		
		inInfo.set("inqu_status-0-recordid", UserSession.getLoginName());
		inInfo = super.query(inInfo, "DUHF71.queryInqu", new DUHF71());	

		return inInfo;
	}


	public EiInfo delete(EiInfo inInfo) {
		StringBuffer buffer = new StringBuffer();
		int count = 0;
		String loginName = String.valueOf(UserSession.getLoginName());
		try {
			for (int j = 0; j < inInfo.getBlock("result").getRowCount(); j++) {
				String recordid = inInfo.getCellStr("result", j, "recordid");
				if (loginName.equals(recordid)) {
					String status = inInfo.getCellStr("result", j, "status");
					if("0".equals(status)) {
						dao.delete("DUHF71.delete", inInfo.getBlock("result").getRow(j));
						count++;
						inInfo.setStatus(1);
					}else {
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
			buffer.insert(0, "删除成功" + count + "条放射源记录");
		}
		inInfo.setMsg(buffer.toString());
		return inInfo;

	}
}
