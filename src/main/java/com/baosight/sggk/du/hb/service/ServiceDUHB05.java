/**
 *
 */
package com.baosight.sggk.du.hb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.sggk.common.du.domain.Tduhb01;
import com.baosight.sggk.common.du.domain.Tduhb06;
import com.baosight.sggk.common.du.domain.Tduhb07;
import com.baosight.sggk.common.du.domain.Tduhb08;
import com.baosight.sggk.common.du.domain.Tduhb21;
import com.baosight.sggk.util.PermissionUtil;
import com.baosight.sggk.util.StrUtil;

public class ServiceDUHB05 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHB05.class);
	// 获取配置文件里的参数
	ResourceBundle dbPro = ResourceBundle.getBundle("application");
	String DbSchema = dbPro.getString("hbSchema");

	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		outInfo.addBlock(EiConstant.queryBlock);
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

		// 添加类别
		EiBlock monitorblock = new EiBlock("monitorList");
		monitorblock.setBlockMeta((new Tduhb08()).eiMetadata);
		List monitorlist = this.dao.query("tduhb08.query", new HashMap());
		monitorblock.setRows(monitorlist);
		outInfo.setBlock(monitorblock);

		// 添加类别
		EiBlockMeta eiMetadata = new EiBlockMeta();
		EiColumn eiColumn = new EiColumn("formalid");
		eiMetadata.addMeta(eiColumn);
		eiColumn = new EiColumn("formalname");
		eiMetadata.addMeta(eiColumn);
		EiBlock formalblock = new EiBlock("formalList");
		formalblock.setBlockMeta(eiMetadata);
		List<Map> list0 = new ArrayList<>();
		Map<String, String> map0 = new HashMap<String, String>();
		map0.put("formalid", "1");
		map0.put("formalname", "正式");
		list0.add(map0);
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("formalid", "0");
		map1.put("formalname", "虚拟");
		list0.add(map1);
		formalblock.setRows(list0);
		outInfo.setBlock(formalblock);

		// 添加标志
		eiMetadata = new EiBlockMeta();
		eiColumn = new EiColumn("flagid");
		eiMetadata.addMeta(eiColumn);
		eiColumn = new EiColumn("flagname");
		eiMetadata.addMeta(eiColumn);
		EiBlock flagblock = new EiBlock("flagList");
		flagblock.setBlockMeta(eiMetadata);
		List<Map> list1 = new ArrayList<>();
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("flagid", "1");
		map2.put("flagname", "是");
		list1.add(map2);
		Map<String, String> map3 = new HashMap<String, String>();
		map3.put("flagid", "0");
		map3.put("flagname", "否");
		list1.add(map3);
		flagblock.setRows(list1);
		outInfo.setBlock(flagblock);

		//添加点位
		eiMetadata = new EiBlockMeta();
		eiColumn = new EiColumn("pointname");
		eiMetadata.addMeta(eiColumn);
		eiColumn = new EiColumn("pointid");
		eiMetadata.addMeta(eiColumn);
		EiBlock pointblock = new EiBlock("blockPoint");
		pointblock.setBlockMeta(eiMetadata);
		List<Map> list2 = new ArrayList<>();
		Map<String,String> map4 = new HashMap<>();
		map4.put("pointid","1");
		map4.put("pointname","国控点");
		list2.add(map4);
		Map<String,String> map5 = new HashMap<>();
		map5.put("pointid","2");
		map5.put("pointname","内控点");
		list2.add(map5);
		Map<String,String> map6 = new HashMap<>();
		map6.put("pointid","3");
		map6.put("pointname","市控点");
		list2.add(map6);
		pointblock.setRows(list2);
		outInfo.setBlock(pointblock);

		//添加排放口状态
		eiMetadata = new EiBlockMeta();
		eiColumn = new EiColumn("statusname");
		eiMetadata.addMeta(eiColumn);
		eiColumn = new EiColumn("statusid");
		eiMetadata.addMeta(eiColumn);
		EiBlock blockStatus = new EiBlock("blockStatus");
		pointblock.setBlockMeta(eiMetadata);
		List<Map> list3 = new ArrayList<>();
		Map<String,String> map7 = new HashMap<>();
		map7.put("statusid","1");
		map7.put("statusname","正常排放");
		list3.add(map7);
		Map<String,String> map8 = new HashMap<>();
		map8.put("statusid","2");
		map8.put("statusname","临时排放");
		list3.add(map8);
		Map<String,String> map9 = new HashMap<>();
		map9.put("statusid","3");
		map9.put("statusname","停用");
		list3.add(map9);
		blockStatus.setRows(list3);
		outInfo.setBlock(blockStatus);


		Map params1 = new HashMap();
		String monitorid = (String)inInfo.get("monitorid");
		if("01".equals(monitorid)){
			outInfo.set("inqu_status-0-monitorid","01");
			outInfo.set("inqu_status-0-status","1");
			outInfo.set("inqu_status-0-isformal","1");
			outInfo.set("inqu_status-0-dischargeClassify","一般排放口,主要排放口");
			outInfo.addBlock(EiConstant.resultBlock);
			params1.put("monitorid",monitorid);
			params1.put("isformal","1");
			params1.put("dischargeClassify","其它");
			List dischargeportQas = this.dao.query("tduhb05.queryDischargeport", params1);
			outInfo.setStatus(1);
			outInfo.setMsg("页面加载"+dischargeportQas.size()+"条");
			outInfo.getBlock("result").setRows(dischargeportQas);
			return outInfo;
		}
		if("02".equals(monitorid)){
			outInfo.set("inqu_status-0-monitorid","02");
			outInfo.set("inqu_status-0-status","1");
			outInfo.set("inqu_status-0-isformal","1");
			outInfo.set("inqu_status-0-dischargeClassify","一般排放口,主要排放口");
			outInfo.addBlock(EiConstant.resultBlock);
			params1.put("monitorid",monitorid);
			params1.put("dischargeClassify","其它");
			params1.put("monitorid","02");
			params1.put("isformal",null);
			List dischargeportShui = this.dao.query("tduhb05.queryDischargeport", params1);
			outInfo.setStatus(1);
			outInfo.setMsg("页面加载"+dischargeportShui.size()+"条");
			outInfo.getBlock("result").setRows(dischargeportShui);
			return outInfo;
		}

        outInfo.set("inqu_status-0-dischargeClassify","一般排放口,主要排放口,其它");
		outInfo.setMsg("页面加载完成");
		return outInfo;
	}

	public EiInfo query(EiInfo inInfo) {
		String  monitorid = 	inInfo.getCellStr("inqu_status",0,"monitorid");
		String  departid = inInfo.getCellStr("inqu_status",0,"departid");
		String  procid = 	inInfo.getCellStr("inqu_status",0,"procid");
		String  dischargeportcode = 	inInfo.getCellStr("inqu_status",0,"dischargeportcode");
		String  dischargeportname = 	inInfo.getCellStr("inqu_status",0,"dischargeportname");
		String[]  dischargeClassify = 	inInfo.getCellStr("inqu_status",0,"dischargeClassify").split(",");
		String  status = inInfo.getCellStr("inqu_status",0,"status");
		String  isformal = inInfo.getCellStr("inqu_status",0,"isformal");
		String controlPoint = inInfo.getCellStr("inqu_status",0,"controlPoint");
		String dischargeStatus = inInfo.getCellStr("inqu_status",0,"dischargeStatus");
		Map<String,Object> map = new HashMap<>();
		map.put("monitorid",monitorid);
		map.put("departid",departid);
		map.put("procid",procid);
		map.put("dischargeportcode",dischargeportcode);
		map.put("dischargeportname",dischargeportname);
		map.put("controlPoint",controlPoint);
		map.put("dischargeStatus",dischargeStatus);
		if(!dischargeClassify[0].equals("")  ){
            map.put("dischargeClassify",dischargeClassify);
        }
		map.put("status",status);
		map.put("isformal",isformal);
		//inInfo.setCell("inqu_status",0,"dischargeClassify","1,2");
		List list = dao.query("tduhb05.query",map, 0, -999999);
		inInfo.getBlock("result").setRows(list);
		//	EiInfo outInfo = super.query(inInfo, "tduhb05.query", new Tduhb05());
			return inInfo;

	}

	/**
	 * 删除流程 1.判断该排口下是否存在对应的监测因子，若存在，则需先删除对应的监测因子
	 * 3.若排口监测因子为空，则可以删除，删除的同时，需去除对应的监测站点关联、环保设施关联、主体设施关联
	 */
	public EiInfo delete(EiInfo inInfo) {
		// 获取排口id集合
		EiInfo outInfo = new EiInfo();
		for (int i = 0; i < inInfo.getBlock("result").getRowCount(); i++) {
			String dischargeportid = inInfo.getCellStr("result", i, "dischargeportid");
			// 首先判断该排口下对应的监测因子，是否还存在，如果存在，则需先删除对应的监测因子
			Map<String, String> map = new HashMap<String, String>();
			map.put("portid", dischargeportid);
			List<Tduhb06> list = this.dao.query("tduhb06.queryById", map);

			if (StrUtil.listIsNotNullOrEmpty(list)) {
				outInfo.setStatus(-1);
				outInfo.setMsg("排口下存在监测因子，请先删除对应的监测因子！");
				return outInfo;
			} else {// 当未查询到排口下的因子时，可进行删除操作
					// 1.去除排口下，对应的监测站点关联
				List<Tduhb07> siteList = this.dao.query("tduhb07.query", map);
				if (StrUtil.listIsNotNullOrEmpty(siteList)) {
					for (int j = 0; j < siteList.size(); j++) {
						String siteid = siteList.get(i).getSiteid();
						Map<String, String> pMap = new HashMap<>();
						pMap.put("siteid", siteid);
						pMap.put("portid", "");
						this.dao.update("tduhb07.updateByPortDelete", pMap);
					}
				}

				// 去除排口对应治理设施关联
				Map<String, String> pMap = new HashMap<String, String>();
				pMap.put("dischargeportid", dischargeportid);
				List<Tduhb21> list21 = this.dao.query("tduhb21.query", pMap);
				if (StrUtil.listIsNotNullOrEmpty(list21)) {
					this.dao.delete("tduhb21.deleteByPortid", pMap);
				}

			}
		}

		outInfo = super.delete(inInfo, "tduhb05.delete");// 删除排口
		EiInfo out = query(inInfo);
		out.setMsg(outInfo.getMsg());
		out.setDetailMsg(outInfo.getDetailMsg());
		return out;
	}

	/**
	 * 根据厂部id，查询对应的工序列表
	 * 
	 * @param inInfo
	 * @return
	 */
	public EiInfo queryProcByDepid(EiInfo inInfo) {
		EiBlock procedureblock = inInfo.getBlock("procedureList");
		if (procedureblock == null) {
			procedureblock = new EiBlock("procedureList");
			procedureblock.setBlockMeta(new Tduhb01().eiMetadata);
		}

		String departmentId = inInfo.getString("departmentId");
		Map querymap = new HashMap();
		querymap.put("type", "P1");
		querymap.put("parentid", departmentId);
		List procedureblocklist = this.dao.query("tduhb01.query", querymap);
		procedureblock.addRows(procedureblocklist);
		inInfo.setBlock(procedureblock);
		return inInfo;
	}

}
