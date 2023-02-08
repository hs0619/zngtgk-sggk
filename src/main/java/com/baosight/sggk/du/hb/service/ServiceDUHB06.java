package com.baosight.sggk.du.hb.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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
import com.baosight.sggk.common.du.domain.Tduhb01;
import com.baosight.sggk.common.du.domain.Tduhb05;
import com.baosight.sggk.common.du.domain.Tduhb06;
import com.baosight.sggk.common.du.domain.Tduhb07;
import com.baosight.sggk.common.du.domain.Tduhb08;
import com.baosight.sggk.common.du.domain.Tduhb18;
import com.baosight.sggk.common.du.domain.Tduhb20;
import com.baosight.sggk.util.PermissionUtil;
import com.baosight.sggk.util.StrUtil;

public class ServiceDUHB06 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHB06.class);
	// 获取配置文件里的参数
	ResourceBundle dbPro = ResourceBundle.getBundle("application");
	String DbSchema = dbPro.getString("hbSchema");

	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		// 登录人
		String loginName = String.valueOf(UserSession.getLoginName());
		String status = PermissionUtil.GetIsUserInGroup(loginName, "tqglz");
		String dischargeportid = StringUtils.isBlank((String) inInfo.get("dischargeportid")) ? ""
				: (String) inInfo.get("dischargeportid");
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
		Tduhb05 tduhb05 = getDischargeportById(dischargeportid);
		if (StringUtils.isNotBlank(dischargeportid) && tduhb05 != null) {
			outInfo.set("inqu_status-0-dischargeportid", tduhb05.getDischargeportid());
			outInfo.set("inqu_status-0-dischargeportcode", tduhb05.getDischargeportcode());
			outInfo.set("inqu_status-0-dischargeportname", tduhb05.getDischargeportname());
			outInfo.set("monitorid", tduhb05.getMonitorid());

		} else {
			outInfo.setMsg("排口参数错误");
			return outInfo;
		}

		EiBlockMeta eiMetadata;
		EiColumn eiColumn;

		// 加载因子表
//		EiBlock portfsblock = new EiBlock("portfs");
//		portfsblock.addBlockMeta(new Tduhb06().eiMetadata);
//		outInfo.setBlock(portfsblock);

		// 添加标志
		eiMetadata = new EiBlockMeta();
		eiColumn = new EiColumn("flagid");
		eiMetadata.addMeta(eiColumn);
		eiColumn = new EiColumn("flagname");
		eiMetadata.addMeta(eiColumn);
		EiBlock flagblock = new EiBlock("flagList");
		flagblock.setBlockMeta(eiMetadata);
		List<Map> list1 = new ArrayList<>();
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("flagid", "1");
		map1.put("flagname", "是");
		list1.add(map1);
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("flagid", "0");
		map2.put("flagname", "否");
		list1.add(map2);
		flagblock.setRows(list1);
		outInfo.setBlock(flagblock);

		EiBlock block = new EiBlock(EiConstant.resultBlock);
		block.addBlockMeta(new Tduhb07().eiMetadata);
		outInfo.setBlock(block);

		// 添加类别
		EiBlock monitorblock = new EiBlock("monitorList");
		monitorblock.setBlockMeta((new Tduhb08()).eiMetadata);
		List monitorlist = this.dao.query("tduhb08.query", new HashMap());
		monitorblock.setRows(monitorlist);
		outInfo.setBlock(monitorblock);

		outInfo.setMsg("页面加载完成");
		return outInfo;
	}

	// 查询排口因子
	public EiInfo queryPORTFS(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		EiBlock portfsBlock = new EiBlock("portfs");
		portfsBlock.setBlockMeta(new Tduhb06().eiMetadata);
		String dischargeportid = inInfo.getString("inqu_status-0-dischargeportid");
		if (StringUtils.isBlank(dischargeportid)) {
			inInfo.setMsg("排口ID为空");
			return inInfo;
		}
		inInfo.set("inqu_status-0-portid", dischargeportid);
		List<Tduhb06> list = this.dao.query("tduhb06.queryDetail", inInfo.getBlock("inqu_status").getRow(0), 0,
				-999999);
		portfsBlock.setRows(list);
		outInfo.setBlock(portfsBlock);
//		EiInfo outInfo = super.query(inInfo, "tduhb06.queryDetail", new Tduhb06(), false, null, EiConstant.queryBlock,
//				"portfs", "portfs");
//		outInfo.getBlock("portfs").set("limit", 100);
		outInfo.set("portfs-offset", 0);
		outInfo.set("portfs-limit", 1000);
		return outInfo;
	}

	public EiInfo deletePORTFS(EiInfo inInfo) {

		StringBuffer buffer = new StringBuffer();
		Dao dao = this.getDao();
		int deleteSuccessCount = 0;
		int deleteFailCount = 0;
		for (int i = 0; i < inInfo.getBlock("portfs").getRowCount(); i++) {
			try {
				String portid = StringUtils.isBlank((String) inInfo.getCell("portfs", i, "portid")) ? "空"
						: (String) inInfo.getCell("portfs", i, "portid");
				String factorid = StringUtils.isBlank((String) inInfo.getCell("portfs", i, "factorid")) ? "空"
						: (String) inInfo.getCell("portfs", i, "factorid");
				inInfo.setCell("portfs", i, "portid", portid);
				inInfo.setCell("portfs", i, "factorid", factorid);

				dao.delete("tduhb06.delete", inInfo.getBlock("portfs").getRow(i));
				deleteSuccessCount++;
			} catch (Exception ex) {
				buffer.append("删除第" + i + "条记录失败\n");
				inInfo.setStatus(-1);
				deleteFailCount++;
			}
		}
		if (deleteSuccessCount > 0)
			buffer.insert(0, "删除成功" + deleteSuccessCount + "条记录\n");
		if (deleteFailCount > 0)
			buffer.insert(0, "删除失败" + deleteFailCount + "条记录\n");

		EiInfo outInfo = queryPORTFS(inInfo);
		outInfo.setMsg(buffer.toString());
		return outInfo;
	}

	public EiInfo updatePORTFS(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		String currentUser = String.valueOf(UserSession.getLoginName());
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < inInfo.getBlock("portfs").getRowCount(); i++) {
			inInfo.setCell("portfs", i, "modifier", currentUser);
			inInfo.setCell("portfs", i, "updatedTime", dateTimeFormat.format(new Date()));
		}
		outInfo = super.update(inInfo, "tduhb06.update", "portfs");
		return outInfo;
	}

	// 查询监测点
	public EiInfo querySITEFS(EiInfo inInfo) {
		String dischargeportid = inInfo.getString("inqu_status-0-dischargeportid");
		if (StringUtils.isBlank(dischargeportid)) {
			inInfo.setMsg("排口ID为空");
			return inInfo;
		}
		inInfo.set("inqu_status-0-portid", dischargeportid);
		EiInfo outInfo = super.query(inInfo, "tduhb07.queryInfo", new Tduhb07(), false, null, EiConstant.queryBlock,
				"sitefs", "sitefs");
		return outInfo;
	}

	public EiInfo deleteSITEFS(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		StringBuffer buffer = new StringBuffer();
		Dao dao = this.getDao();
		int deleteSuccessCount = 0;
		int deleteFailCount = 0;
		for (int i = 0; i < inInfo.getBlock("sitefs").getRowCount(); i++) {
			try {
				String siteid = StringUtils.isBlank((String) inInfo.getCell("sitefs", i, "siteid")) ? ""
						: (String) inInfo.getCell("sitefs", i, "siteid");
				inInfo.setCell("sitefs", i, "siteid", siteid);
				if ("".equals(siteid)) {
					outInfo.setStatus(-1);
					outInfo.setMsg("系统参数错误！");
					return outInfo;
				}

				Map<String, String> pMap = new HashMap<>();
				pMap.put("siteid", siteid);
				pMap.put("portid", "");
				// this.dao.delete("tduhb07.delete", pMap);
				this.dao.update("tduhb07.updateByPortDelete", pMap);
				deleteSuccessCount++;
			} catch (Exception ex) {
				buffer.append("删除第" + i + "条记录失败\n");
				inInfo.setStatus(-1);
				deleteFailCount++;
			}
		}
		if (deleteSuccessCount > 0)
			buffer.insert(0, "删除成功" + deleteSuccessCount + "条记录\n");
		if (deleteFailCount > 0)
			buffer.insert(0, "删除失败" + deleteFailCount + "条记录\n");

		outInfo = querySITEFS(inInfo);
		outInfo.setMsg(buffer.toString());
		return outInfo;
	}

	// 污染治理设施
	public EiInfo querySSFS(EiInfo inInfo) {
		String dischargeportid = inInfo.getString("inqu_status-0-dischargeportid");

		inInfo.set("inqu_status-0-dischargeportid", dischargeportid);
		EiInfo outInfo = super.query(inInfo, "tduhb20.queryBYPortId", new Tduhb20(), false, null, EiConstant.queryBlock,
				"ssfs", "ssfs");
		for (int i = 0; i < outInfo.getBlock("ssfs").getRowCount(); i++) {
			String devicecode = outInfo.getCellStr("ssfs", i, "devicecode");
			devicecode = getEnvdevicCodeById(devicecode);
			outInfo.setCell("ssfs", i, "devicecode", devicecode);
		}

		return outInfo;
	}
	
	//取消污染防治设施关联
	public EiInfo deleteSSFS(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		StringBuffer buffer = new StringBuffer();
		int deleteSuccessCount = 0;
		int deleteFailCount = 0;
		for (int i = 0; i < inInfo.getBlock("ssfs").getRowCount(); i++) {
			try {
				this.dao.delete("tduhb20.deletessfs", inInfo.getBlock("ssfs").getRow(i));
				deleteSuccessCount++;
				inInfo.setStatus(1);
			} catch (Exception ex) {
				buffer.append("取消关联第" + i + "条记录失败\n");
				inInfo.setStatus(-1);
				deleteFailCount++;
			}
		}
		if (deleteSuccessCount > 0)
			buffer.insert(0, "取消关联成功" + deleteSuccessCount + "条记录\n");
		if (deleteFailCount > 0)
			buffer.insert(0, "取消关联失败" + deleteFailCount + "条记录\n");

		outInfo = querySSFS(inInfo);
		outInfo.setMsg(buffer.toString());
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

	private Tduhb05 getDischargeportById(String dischargeportid) {
		try {
			if (StringUtils.isBlank(dischargeportid)) {
				dischargeportid = "空";
			}
			Tduhb05 tduhb05 = null;
			Map<String, String> map = new HashMap<String, String>();
			map.put("dischargeportid", dischargeportid);
			List list = this.dao.query("tduhb05.query", map);
			if (list.size() > 0) {
				tduhb05 = (Tduhb05) list.get(0);
			}
			return tduhb05;
		} catch (Exception ex) {
			throw ex;
		}
	}
}
