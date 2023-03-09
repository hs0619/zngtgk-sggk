/**
 *
 */
package com.baosight.sggk.du.hc.service;

import com.baosight.sggk.common.du.domain.Tduhb10;
import com.baosight.sggk.du.hc.domain.DUHC2302;
import com.baosight.sggk.du.hc.domain.DUHC230202;
import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.sggk.util.SelectUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.*;

//

public class ServiceDUHC230202 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHC230202.class);

	// 获取配置文件里的参数
	public ResourceBundle dbPro = ResourceBundle.getBundle("application");
	public String DbSchema = dbPro.getString("hbSchema");

	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		String siteid = StringUtils.isBlank((String) inInfo.get("siteid")) ? "" : (String) inInfo.get("siteid");
		String planid = StringUtils.isBlank((String) inInfo.get("planid")) ? "" : (String) inInfo.get("planid");
		String monitorid = StringUtils.isBlank((String) inInfo.get("monitorid")) ? ""
				: (String) inInfo.get("monitorid");
		List<DUHC2302> list = getPlanById(siteid, planid);
		if (list != null && list.size() > 0) {
			outInfo.set("inqu_status-0-planid", planid);
			outInfo.set("inqu_status-0-monitorid", monitorid);
			outInfo.set("inqu_status-0-siteid", siteid);
			outInfo.set("inqu_status-0-planname", list.get(0).getPlanname());
		} else {
			outInfo.setMsg("参数错误");
			return outInfo;
		}
		// 加载手工子表
		EiBlock humanblock = new EiBlock("human");
		humanblock.addBlockMeta(new DUHC230202().eiMetadata);
		outInfo.addBlock(humanblock);

		EiBlockMeta eiMetadata;
		EiColumn eiColumn;
		// 添加标志
		eiMetadata = new EiBlockMeta();
		eiColumn = new EiColumn("flagid");
		eiMetadata.addMeta(eiColumn);
		eiColumn = new EiColumn("flagname");
		eiMetadata.addMeta(eiColumn);
		EiBlock flagblock = new EiBlock("flagList");
		flagblock.setBlockMeta(eiMetadata);
		List<Map> list1 = new ArrayList<>();
		Map<String, String> map0 = new HashMap<String, String>();
		map0.put("flagid", "");
		map0.put("flagname", "");
		list1.add(map0);
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
		// 因子
		String portid = StringUtils.isBlank(list.get(0).getPortid()) ? "空" : list.get(0).getPortid();
		EiBlock factorblock = new EiBlock("factors");
		factorblock.setBlockMeta((new Tduhb10()).eiMetadata);
		Map<String, String> map = new HashMap<String, String>();
		map.put("siteid", siteid);
		List list3 = this.dao.query("DUHC230202.queryBySiteid", map);
		factorblock.setRows(list3);
		outInfo.setBlock(factorblock);

        //监测频率
        outInfo.addBlock(SelectUtil.getTextAndValue(dao,"timeDime","1"));

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
		outInfo.setMsg("页面加载完成");
		return outInfo;
	}

	private List<DUHC2302> getPlanById(String siteid, String planid) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("siteid", siteid);
			map.put("planid", planid);
			List<DUHC2302> list = this.dao.query("DUHC2302.query1", map);
			if (list.size() > 0) {
				return list;
			}
		} catch (Exception ex) {
			throw ex;
		}
		return null;
	}

	private List<DUHC230202> getSiteById(String siteid, String factorid) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("siteid", siteid);
			map.put("factorid", factorid);
			map.put("type", "1");
			List<DUHC230202> list = this.dao.query("tduhb0702.query", map);
			if (list.size() > 0) {
				return list;
			}
		} catch (Exception ex) {
			throw ex;
		}
		return null;
	}

	public EiInfo queryHuman(EiInfo inInfo) {
		EiInfo outInfo=new EiInfo();
		//EiBlock humanBlock=outInfo.addBlock("human");
		EiBlock humanBlock = new EiBlock("human");
		humanBlock.setBlockMeta(new DUHC230202().eiMetadata);
		String planid = inInfo.getString("inqu_status-0-planid");
		if (StringUtils.isBlank(planid)) {
			inInfo.setMsg("计划ID为空");
			return inInfo;
		}
		List<DUHC230202> list = this.dao.query("DUHC230202.query",inInfo.getBlock("inqu_status").getRow(0), 0, -999999);
		humanBlock.setRows(list);
		humanBlock.set("limit", 1000000);
		outInfo.addBlock(humanBlock);
		//EiInfo outInfo = super.query(inInfo, "DUHC230202.query", new DUHC230202(), false, null, EiConstant.queryBlock,"human", "human");
		return outInfo;
	}

	public EiInfo delete(EiInfo inInfo) {
		EiInfo outInfo = super.delete(inInfo, "DUHC230202.delete", "human");
		EiInfo humanInfo = queryHuman(inInfo);
		outInfo.setBlock(humanInfo.getBlock("human"));
		return outInfo;
	}

	public EiInfo updateHuman(EiInfo inInfo) {
		StringBuffer buffer = new StringBuffer();
		StringBuffer detail = new StringBuffer();
		String currentUser = String.valueOf(UserSession.getLoginName());
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Dao dao = this.getDao();
		int updateSuccessCount = 0;
		int updateFailCount = 0;
		for (int i = 0; i < inInfo.getBlock("human").getRowCount(); i++) {
			inInfo.setCell("human", i, "modifier", currentUser);
			inInfo.setCell("human", i, "updatedTime", dateTimeFormat.format(new Date()));
			try {
				dao.update("DUHC230202.update", inInfo.getBlock("human").getRow(i));
				updateSuccessCount++;
			} catch (Exception ex) {
				buffer.append("更新:" + inInfo.getBlock("human").getCell(i, "factorid") + "的记录失败\n");
				inInfo.setStatus(-1);
				detail.append(ex.getCause().toString());
				updateFailCount++;
			}
		}
		if (updateSuccessCount > 0)
			buffer.insert(0, "更新成功" + updateSuccessCount + "条记录！\n");
		if (updateFailCount > 0)
			buffer.insert(0, "更新失败" + updateFailCount + "条记录！\n");
		EiInfo outInfo = queryHuman(inInfo);
		outInfo.setMsg(buffer.toString());
		outInfo.setDetailMsg(detail.toString());
		return outInfo;
	}

	public EiInfo insert(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		String siteid = inInfo.getString("inqu_status-0-siteid");
		String planid = inInfo.getString("inqu_status-0-planid");
		String monitorid = inInfo.getString("inqu_status-0-monitorid");
		StringBuffer buffer = new StringBuffer();
		StringBuffer detail = new StringBuffer();
		Dao dao = this.getDao();
		String currentUser = String.valueOf(UserSession.getLoginName());
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int insertSuccessCount = 0;
		int insertFailCount = 0;
		for (int i = 0; i < inInfo.getBlock("human").getRowCount(); i++) {
			try {
				inInfo.setCell("human", i, "siteid", siteid);
				inInfo.setCell("human", i, "planid", planid);
				inInfo.setCell("human", i, "monitorid", monitorid);
				inInfo.setCell("human", i, "type", "1");
				inInfo.setCell("human", i, "creator", currentUser);
				inInfo.setCell("human", i, "description", "");
				inInfo.setCell("human", i, "createdTime", dateTimeFormat.format(new Date()));
				if (getSiteById(siteid, inInfo.getBlock("human").getCellStr(i, "factorid")) == null) {
					dao.insert("tduhb0702.insert", inInfo.getBlock("human").getRow(i));
				}
				if (getfactorByplanidAndFactorid(planid, inInfo.getBlock("human").getCellStr(i, "factorid")) == null) {
					dao.insert("DUHC230202.insert", inInfo.getBlock("human").getRow(i));
					insertSuccessCount++;
				} else {
					outInfo.setMsg("新增失败，该因子已存在！");
					outInfo.setStatus(2);
					return outInfo;
				}

			} catch (Exception ex) {
				buffer.append("新增第" + i + "条记录失败\n");
				inInfo.setStatus(-1);
				detail.append(ex.getCause().toString());
				insertFailCount++;
			}
		}
		if (insertSuccessCount > 0)
			buffer.insert(0, "新增成功" + insertSuccessCount + "条记录！\n");
		if (insertFailCount > 0)
			buffer.insert(0, "新增失败" + insertFailCount + "条记录！\n");
		outInfo = queryHuman(inInfo);
		outInfo.setMsg(buffer.toString());
		outInfo.setDetailMsg(detail.toString());
		return outInfo;
	}

	private Object getfactorByplanidAndFactorid(String planid, String factorid) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("planid", planid);
			map.put("factorid", factorid);
			List<DUHC230202> list = this.dao.query("DUHC230202.queryByPlanidAndFactorid", map);
			if (list.size() > 0) {
				return list;
			}
		} catch (Exception ex) {
			throw ex;
		}
		return null;
	}
}
