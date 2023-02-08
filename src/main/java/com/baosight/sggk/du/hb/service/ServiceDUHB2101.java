/**
 *
 */
package com.baosight.sggk.du.hb.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.baosight.sggk.common.du.domain.Tduhb01;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.sggk.common.du.domain.Tduhb18;
import com.baosight.sggk.common.du.domain.Tduhb20;
import com.baosight.sggk.util.PermissionUtil;
import com.baosight.sggk.util.StrUtil;

public class ServiceDUHB2101 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHB2101.class);
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
//					Map<String,Object> map=new HashMap<>();
//					map.put("departmentName","全部");
//					map.put("departmentId","");
//					departblock.addRow(map);
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
				departmap.put("departmentId", StrUtil.trimToString(mapdata.get("DEPARTMENT_ID")));

			}
		}
		// 添加厂部
		List<Tduhb01> departlist = this.dao.query("tduhb01.query", departmap);
		departblock.addRows(departlist);

		// 是否编辑
		String departmentid = inInfo.getString("departmentid");
		// 添加工序
		EiBlock procedureblock = outInfo.addBlock("procedureList");
		proceduremap.put("parentid",
				StringUtils.isBlank(departmentid) ? departlist.get(0).getDepartmentId() : departmentid);
		proceduremap.put("type", "P1");
		List procedureblocklist = this.dao.query("tduhb01.query", proceduremap);
		procedureblock.addRows(procedureblocklist);

		String oprationType = (String) inInfo.get("oprationType");
		if (StringUtils.isBlank(oprationType)) {
			outInfo.setMsg("废水处理设施参数错误");
			return outInfo;
		}

		EiBlock queryblock = new EiBlock(EiConstant.queryBlock);
		EiBlockMeta metadata = new EiBlockMeta();
		EiColumn eiColumn = new EiColumn("facilityid");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("facilitycode");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("facilityname");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("origin");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("handmethod");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("numbers");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("portid");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("portname");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("handability");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("shandnum");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("runtime");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("handfactors");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("devicecode");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("devicenames");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("devicemodel");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("motormodel");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("controllermodel");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("situation");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("outstandard");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("isexecute");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("remark");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("facilitydepart");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("oprationType");
		metadata.addMeta(eiColumn);
		queryblock.setBlockMeta(metadata);

		Map<String, String> row = new HashMap<String, String>();

		if ("insert".equals(oprationType)) {
			row.put("oprationType", "insert");
			row.put("facilityid", "");
			row.put("facilitycode", "");
			row.put("facilityname", "");
			row.put("origin", "");
			row.put("handmethod", "");
			row.put("numbers", "");
			row.put("portid", "");
			row.put("portname", "");
			row.put("handability", "");
			row.put("shandnum", "");
			row.put("runtime", "");
			row.put("handfactors", "");
			row.put("devicecode", "");
			row.put("devicenames", "");
			row.put("devicemodel", "");
			row.put("motormodel", "");
			row.put("controllermodel", "");
			row.put("situation", "");
			row.put("outstandard", "");
			row.put("isexecute", "");
			row.put("remark", "");
			row.put("departmentid", "");
			row.put("procedureid", "");
			row.put("handabilityAct", "");
			row.put("devicearea", "");
			row.put("deviceflow", "");
			row.put("devicenames", "");
			row.put("facilitydepart", "");
		} else if ("update".equals(oprationType)) {
			row.put("oprationType", "update");
			String facilityid = StringUtils.isBlank((String) inInfo.get("facilityid")) ? "空"
					: (String) inInfo.get("facilityid");
			Map querymap = new HashMap();
			querymap.put("facilityid", facilityid);
			List list = this.dao.query("tduhb20.query", querymap);
			if (list.size() > 0) {
				Tduhb20 tduhb20 = (Tduhb20) list.get(0);
				row.put("facilityid", tduhb20.getFacilityid());
				row.put("facilitycode", tduhb20.getFacilitycode());
				row.put("facilityname", tduhb20.getFacilityname());
				row.put("origin", tduhb20.getOrigin());
				row.put("handmethod", tduhb20.getHandmethod());
				row.put("numbers", tduhb20.getNumbers());
				row.put("portid", tduhb20.getPortid());
				row.put("portname", tduhb20.getPortname());
				row.put("handability", tduhb20.getHandability());
				row.put("shandnum", tduhb20.getShandnum());
				row.put("runtime", tduhb20.getRuntime());
				row.put("handfactors", tduhb20.getHandfactors());
				row.put("devicecode", getEnvdevicCodeById(tduhb20.getDevicecode()));
				row.put("devicenames", getEnvdevicNameById(tduhb20.getDevicecode()));
				row.put("devicemodel", tduhb20.getDevicemodel());
				row.put("motormodel", tduhb20.getMotormodel());
				row.put("controllermodel", tduhb20.getControllermodel());
				row.put("situation", tduhb20.getSituation());
				row.put("outstandard", tduhb20.getOutstandard());
				row.put("isexecute", tduhb20.getIsexecute());
				row.put("remark", tduhb20.getRemark());
				row.put("facilitydepart", tduhb20.getFacilitydepart());
				row.put("departmentid", tduhb20.getDepartmentid());
				row.put("procedureid", tduhb20.getProcedureid());
				row.put("mark", tduhb20.getMark());
			}
		}
		queryblock.addRow(row);
		outInfo.setBlock(queryblock);

		outInfo.setMsg("页面加载完成");
		return outInfo;
	}

	// 根据厂部id查询工序
	public EiInfo getProcedureByDepartid(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		String departmentid = inInfo.getString("departmentid");
		// 添加工序
		EiBlock procedureblock = outInfo.addBlock("procedureList");
		List<Map> procedureList = new ArrayList<>();
		Map<String, String> procedureMap = new HashMap<String, String>();
		procedureMap.put("departmentName", "");
		procedureMap.put("departmentId", "");
		procedureList.add(procedureMap);
		procedureblock.addRows(procedureList);
		Map<String, Object> selectmap = new HashMap();
		selectmap.put("parentid", departmentid);
		selectmap.put("type", "P1");
		List procedureblocklist = this.dao.query("tduhb01.query", selectmap);
		procedureblock.addRows(procedureblocklist);
		return outInfo;
	}

	//保存废水防治设施
	public EiInfo save(EiInfo inInfo) {
		StringBuffer buffer = new StringBuffer();
		String currentUser = String.valueOf(UserSession.getLoginName());
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Dao dao = this.getDao();
		try {
			String oprationType = (String) inInfo.get("inqu_status-0-oprationType");
			String facilityid = (String) inInfo.get("inqu_status-0-facilityid");
			String facilitycode = (String) inInfo.get("inqu_status-0-facilitycode");
			String facilityname = (String) inInfo.get("inqu_status-0-facilityname");
			String origin = (String) inInfo.get("inqu_status-0-origin");
			String handmethod = (String) inInfo.get("inqu_status-0-handmethod");
			String numbers = (String) inInfo.get("inqu_status-0-numbers");
			String portid = (String) inInfo.get("inqu_status-0-portid");
			String portname = (String) inInfo.get("inqu_status-0-portname");
			String handability = (String) inInfo.get("inqu_status-0-handability");
			String shandnum = (String) inInfo.get("inqu_status-0-shandnum");
			String runtime = (String) inInfo.get("inqu_status-0-runtime");
			String handfactors = (String) inInfo.get("inqu_status-0-handfactors");
			String devicecode = (String) inInfo.get("inqu_status-0-devicecode");
			String devicemodel = (String) inInfo.get("inqu_status-0-devicemodel");
			String motormodel = (String) inInfo.get("inqu_status-0-motormodel");
			String controllermodel = (String) inInfo.get("inqu_status-0-controllermodel");
			String situation = (String) inInfo.get("inqu_status-0-situation");
			String outstandard = (String) inInfo.get("inqu_status-0-outstandard");
			String isexecute = (String) inInfo.get("inqu_status-0-isexecute");
			String remark = (String) inInfo.get("inqu_status-0-remark");
			String facilitydepart = (String) inInfo.get("inqu_status-0-facilitydepart");
			
			String mark = (String) inInfo.get("inqu_status-0-mark");
			String departmentid = (String) inInfo.get("inqu_status-0-departmentid");
			String procedureid = (String) inInfo.get("inqu_status-0-procedureid");
			Map<String, String> map = new HashMap<String, String>();

			map.put("facilitycode", facilitycode);
			map.put("facilityname", facilityname);
			map.put("pollutantname", "");
			map.put("facilitytype", "");
			map.put("origin", "");
			map.put("handmethod", handmethod);
			map.put("numbers", numbers);
			map.put("handfactors", "");
			map.put("portid", portid);
			map.put("portname", portname);
			map.put("handability", handability);
			map.put("shandnum", shandnum);
			map.put("runtime", runtime);
			map.put("handfactors", handfactors);
			map.put("devicemodel", devicemodel);
			map.put("motormodel", motormodel);
			map.put("controllermodel", controllermodel);
			map.put("situation", situation);
			map.put("outstandard", "");
			map.put("isexecute", isexecute);
			map.put("remark", remark);
			map.put("facilitydepart", "");
			map.put("departmentid", "");
			map.put("procedureid", "");
			map.put("devicenames", "");
			map.put("type", "02");
			map.put("status", "1");
			
			map.put("mark", mark);
			map.put("departmentid", departmentid);
			map.put("procedureid", procedureid);
			if ("insert".equals(oprationType)) {
				map.put("devicecode", devicecode);
				map.put("facilityid", getFacilityId());
				map.put("creator", currentUser);
				map.put("createdTime", dateTimeFormat.format(new Date()));
				dao.insert("tduhb20.insert", map);
				buffer.insert(0, "记录保存成功\n");
			} else if ("update".equals(oprationType)) {
				map.put("facilityid", facilityid);
				map.put("modifier", currentUser);
				map.put("updatedTime", dateTimeFormat.format(new Date()));
				dao.update("tduhb20.updateWasteWater", map);
				buffer.insert(0, "记录保存成功\n");
			} else {
				buffer.insert(0, "记录保存失败\n");
			}
		} catch (Exception ex) {
			buffer.insert(0, "记录保存失败\n" + ex.toString());
			inInfo.setStatus(-1);
		}
		inInfo.setMsg(buffer.toString());

		return inInfo;
	}

	// 保存因子
//	public EiInfo save(EiInfo inInfo) {
//		StringBuffer buffer = new StringBuffer();
//		String currentUser = String.valueOf(UserSession.getLoginName());
//		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Dao dao = this.getDao();
//		try {
//			String oprationType = (String) inInfo.get("inqu_status-0-oprationType");
//			String facilityid = (String) inInfo.get("inqu_status-0-facilityid");
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("type", "02");
//			map.put("status", "1");
//			inInfo.getBlock("inqu_status").setCell(0, "type", "02");
//			inInfo.getBlock("inqu_status").setCell(0, "status", "1");
//			if ("insert".equals(oprationType)) {
//				inInfo.getBlock("inqu_status").setCell(0, "facilityid", getFacilityId());
//				inInfo.getBlock("inqu_status").setCell(0, "creator", currentUser);
//				inInfo.getBlock("inqu_status").setCell(0, "createdTime", dateTimeFormat.format(new Date()));
//				dao.insert("tduhb20.insert", inInfo.getBlock("inqu_status").getRow(0));
//			} else if ("update".equals(oprationType)) {
//				inInfo.getBlock("inqu_status").setCell(0, "facilityid", facilityid);
//				inInfo.getBlock("inqu_status").setCell(0, "modifier", currentUser);
//				inInfo.getBlock("inqu_status").setCell(0, "updatedTime", dateTimeFormat.format(new Date()));
//				dao.update("tduhb20.updateWasteWater", inInfo.getBlock("inqu_status").getRow(0));
//			} else {
//				buffer.insert(0, "记录保存失败\n");
//			}
//			buffer.insert(0, "记录保存成功\n");
//		} catch (Exception ex) {
//			buffer.insert(0, "记录保存失败\n" + ex.toString());
//			inInfo.setStatus(-1);
//		}
//		inInfo.setMsg(buffer.toString());
//
//		return inInfo;
//	}

	private String getFacilityId() throws Exception {
		String facilityid = "FI";
		List list = this.dao.query("tduhb20.queryId", new HashMap<String, String>());
		int count = 1;
		if (list != null && list.size() > 0) {
			count = Integer.parseInt(((Tduhb20) list.get(0)).getFacilityid().substring(2)) + 1;
		}
		return facilityid + String.format("%06d", count);
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
}
