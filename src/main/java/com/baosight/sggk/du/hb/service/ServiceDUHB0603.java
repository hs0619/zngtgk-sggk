/**
 *
 */
package com.baosight.sggk.du.hb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.sggk.common.du.domain.Tduhb01;
import com.baosight.sggk.common.du.domain.Tduhb18;
import com.baosight.sggk.common.du.domain.Tduhb20;
import com.baosight.sggk.util.PermissionUtil;
import com.baosight.sggk.util.StrUtil;

public class ServiceDUHB0603 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHB0603.class);
	// 获取配置文件里的参数
	ResourceBundle dbPro = ResourceBundle.getBundle("application");
	String DbSchema = dbPro.getString("hbSchema");

	public EiInfo initLoad(EiInfo inInfo) {
		String portid = inInfo.getString("portid");
		EiInfo outInfo = new EiInfo();
		// 登录人
		String loginName = String.valueOf(UserSession.getLoginName());
		String status = PermissionUtil.GetIsUserInGroup(loginName, "tqglz");
		Map departmap = new HashMap();
		Map proceduremap = new HashMap();
		EiBlock departblock = outInfo.addBlock("departList");
		if ("1".equals(status)||"admin".equals(loginName)) {
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
				String departmentId=StrUtil.trimToString(mapdata.get("DEPARTMENT_ID"));
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

		outInfo.set("portid", portid);
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
		inInfo.set("inqu_status-0-type", "01");
		EiInfo outInfo = super.query(inInfo, "tduhb20.query", new Tduhb20());
		for (int i = 0; i < outInfo.getBlock("result").getRowCount(); i++) {
			String devicecode = outInfo.getCellStr("result", i, "devicecode");
			outInfo.setCell("result", i, "paramDevicecode", devicecode);
			outInfo.setCell("result", i, "devicecode", getEnvdevicCodeById(devicecode));
			outInfo.setCell("result", i, "devicenames", getEnvdevicNameById(devicecode));
		}
		return outInfo;
	}

	/**
	 * 将产排污设施id,转换为编码
	 * 
	 * @param devicecode，多个id在一起的字符串
	 * @return
	 */
	public String getEnvdevicCodeById(String devicecode) {
		List<String> codeList = new ArrayList<>();
		if (StrUtil.paramIsNotNullOrEmpty(devicecode)) {
			String[] envdeviceidArr = devicecode.split(",");
			for (int j = 0; j < envdeviceidArr.length; j++) {
				// 根据产排污设备id，查询对应的设备编码
				Map<String, String> pMap = new HashMap<>();
				pMap.put("envdeviceid", envdeviceidArr[j]);
				List<Tduhb18> list = this.dao.query("tduhb18.query", pMap);
				if (StrUtil.listIsNotNullOrEmpty(list)) {
					String code = list.get(0).getDeviceid();
					codeList.add(code);
				}

			}
		}

		if (StrUtil.listIsNotNullOrEmpty(codeList)) {
			return StringUtils.join(codeList, ",");
		} else {
			return "";
		}
	}

	/**
	 * 将产排污设施id,转换为名称
	 * 
	 * @param devicecode，多个产污设施名称，拼在一起的字符串
	 * @return
	 */
	public String getEnvdevicNameById(String devicecode) {
		List<String> nameList = new ArrayList<>();
		if (StrUtil.paramIsNotNullOrEmpty(devicecode)) {
			String[] envdeviceidArr = devicecode.split(",");
			for (int j = 0; j < envdeviceidArr.length; j++) {
				// 根据产排污设备id，查询对应的设备编码
				Map<String, String> pMap = new HashMap<>();
				pMap.put("envdeviceid", envdeviceidArr[j]);
				List<Tduhb18> list = this.dao.query("tduhb18.query", pMap);
				if (StrUtil.listIsNotNullOrEmpty(list)) {
					String name = list.get(0).getDevicename();
					nameList.add(name);
				}

			}
		}

		if (StrUtil.listIsNotNullOrEmpty(nameList)) {
			return StringUtils.join(nameList, ",");
		} else {
			return "";
		}
	}

	// 添加排口-设施关联
	public EiInfo addEquipment(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		String portid = StrUtil.paramIsNotNullOrEmpty(inInfo.getString("portid")) ? inInfo.getString("portid") : "";
		String facilityids = StrUtil.paramIsNotNullOrEmpty(inInfo.getString("facilityids"))
				? inInfo.getString("facilityids")
				: "";
		if ("".equals(portid) || "".equals(facilityids)) {
			outInfo.setStatus(-1);
			outInfo.setMsg("添加失败，系统参数错误");
			return inInfo;
		}

		String[] facilityidArr = facilityids.split(";");
		if (facilityidArr.length > 0) {
			for (int i = 0; i < facilityidArr.length; i++) {
				String facilityid = facilityidArr[i];
				Map<String, String> pMap = new HashMap<>();
				pMap.put("facilityid", facilityid);
				pMap.put("dischargeportid", portid);
				try {
					this.dao.insert("tduhb21.insert", pMap);
					outInfo.setStatus(1);
				} catch (Exception e) {
					outInfo.setStatus(-2);
				}

			}
		}
		return outInfo;
	}

}
