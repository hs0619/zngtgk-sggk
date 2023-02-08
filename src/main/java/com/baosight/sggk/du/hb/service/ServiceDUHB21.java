/**
 *
 */
package com.baosight.sggk.du.hb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.sggk.common.du.domain.Tduhb01;
import com.baosight.sggk.common.du.domain.Tduhb20;
import com.baosight.sggk.util.PermissionUtil;
import com.baosight.sggk.util.StrUtil;

public class ServiceDUHB21 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHB21.class);
	// 获取配置文件里的参数
	ResourceBundle dbPro = ResourceBundle.getBundle("application");
	String DbSchema = dbPro.getString("hbSchema");

	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		// 登录人
		String loginName = String.valueOf(UserSession.getLoginName());
		String status = PermissionUtil.GetIsUserInGroup(loginName, "tqglz");

		Map departmap = new HashMap();
		Map proceduremap = new HashMap();
		EiBlock departblock = outInfo.addBlock("departList");
		if ("1".equals(status) || "admin".equals(loginName)) {
			Map<String, Object> map = new HashMap<>();
			map.put("departmentName", "全部");
			map.put("departmentId", "");
			departblock.addRow(map);
			departmap.put("parentid", "root");
		} else {
			outInfo.set("inqu_status-0-loginName", loginName);
			String departSql = "select t1.department_id from " + DbSchema + ".T_HA_XS_USER_EX t1 "
					+ " where t1.USER_ID = '" + loginName + "'";
			Map sqlmap = new HashMap();
			sqlmap.put("sqlMap", departSql);
			List list = this.dao.query("tduhb20.executesql", sqlmap);
			if (list != null && list.size() > 0) {
				Map mapdata = (HashMap) list.get(0);
				String departmentId = StrUtil.trimToString(mapdata.get("DEPARTMENT_ID"));
				departmap.put("departmentId", departmentId);
				proceduremap.put("parentid", departmentId);
				outInfo.set("inqu_status-0-departmentid", departmentId);
			}
		}
		// 添加厂部
		List<Tduhb01> departlist = this.dao.query("tduhb01.query", departmap);

		departblock.addRows(departlist);

		// 添加工序
		EiBlock procedureblock = outInfo.addBlock("procedureList");
		proceduremap.put("type", "P1");
		List procedureblocklist = this.dao.query("tduhb01.query", proceduremap);
		procedureblock.addRows(procedureblocklist);

		outInfo.setMsg("页面加载完成");
		return outInfo;
	}

	// 根据厂部id查询工序
	public EiInfo getProcedureByDepartid(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		String departmentid = inInfo.getString("departmentid");
		// 添加工序
		EiBlock procedureblock = outInfo.addBlock("procedureList");
		Map<String, Object> map = new HashMap<>();
		map.put("departmentName", "全部");
		map.put("departmentId", "");
		procedureblock.addRow(map);
		Map<String, Object> proceduremap = new HashMap();
		proceduremap.put("parentid", departmentid);
		proceduremap.put("type", "P1");
		List procedurelist = this.dao.query("tduhb01.query", proceduremap);
		procedureblock.addRows(procedurelist);
		return outInfo;
	}

	public EiInfo query(EiInfo inInfo) {
		inInfo.set("inqu_status-0-type", "02");
		EiInfo outInfo = super.query(inInfo, "tduhb20.query", new Tduhb20());
		return outInfo;
	}

	public EiInfo delete(EiInfo inInfo) {
		EiInfo outInfo = super.delete(inInfo, "tduhb20.delete");
		EiInfo out = query(inInfo);
		out.setMsg(outInfo.getMsg());
		out.setDetailMsg(outInfo.getDetailMsg());
		return out;
	}

}
