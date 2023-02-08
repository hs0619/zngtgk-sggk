package com.baosight.sggk.du.hb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baosight.sggk.common.du.domain.Tduhb08;
import com.baosight.sggk.common.du.domain.Tduhb09;
import com.baosight.sggk.common.du.domain.Tduhb10;
import com.baosight.sggk.util.StrUtil;

public class ServiceDUHB09 extends ServiceEPBase {
	
	private static final Logger logger = Logger.getLogger(ServiceDUHB09.class);

	public EiInfo initLoad( EiInfo inInfo )
	{
        EiInfo outInfo = new EiInfo();
		
//		EiBlock block = new EiBlock(EiConstant.resultBlock);
//		block.addBlockMeta(new Tduhb09().eiMetadata);
//		outInfo.setBlock(block);
		
		//添加标志
	    EiBlockMeta eiMetadata = new EiBlockMeta();
	    EiColumn eiColumn = null;
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
	    
	    // 添加类别
	    EiBlock monitorblock = new EiBlock("monitor");
	    monitorblock.setBlockMeta((new Tduhb08()).eiMetadata);
	 	List monitorlist = this.dao.query("tduhb08.query", new HashMap());
	 	monitorblock.setRows(monitorlist);
	 	outInfo.setBlock(monitorblock);
	 	
	 	EiBlock factorblock = new EiBlock("factor");
	 	factorblock.setBlockMeta((new Tduhb10()).eiMetadata);
	 	List factorlist = this.dao.query("tduhb10.query", new HashMap());
	 	factorblock.setRows(factorlist);
	 	outInfo.setBlock(factorblock);
	 	
	 	EiBlock factorblock1 = new EiBlock("factor1");
	 	factorblock1.setBlockMeta((new Tduhb10()).eiMetadata);
	 	List factorlist1 = this.dao.query("tduhb10.query", new HashMap());
	 	factorblock1.setRows(factorlist1);
	 	outInfo.setBlock(factorblock1);

	 	outInfo.setMsg("页面加载完成");
		return outInfo;
	}

	public EiInfo query( EiInfo inInfo )
	{
		EiInfo outInfo = super.query(inInfo,"tduhb09.query",new Tduhb08());
		return outInfo;
	}

	public EiInfo delete( EiInfo inInfo )
	{
		EiInfo outInfo = super.delete(inInfo, "tduhb09.delete");
		return outInfo;
	}

	public EiInfo update( EiInfo inInfo )
	{
        EiInfo outInfo=super.update(inInfo,"tduhb09.update");
		return outInfo;
	}
	
	/**
	 * 自定义保存信息
	 * @param inInfo
	 * @return
	 */
	public EiInfo saveInsert(EiInfo inInfo) {
		StringBuffer buffer = new StringBuffer();
		int insertSuccessCount = 0;
		int insertFailCount = 0;
		String obj = inInfo.getString("obj");
		JSONArray jsonArray =  JSONArray.parseArray(obj);
		int count = 1;
		for (Object monitorFactor : jsonArray) {
			JSONObject mfObj = (JSONObject) monitorFactor;
			String monitorid = mfObj.getString("monitorid");
			String factorid = mfObj.getString("factorid");
			String status = mfObj.getString("status");
			Map<String, String> idMap = new HashMap<>();
			idMap.put("monitorid", monitorid);
			idMap.put("factorid", factorid);
			List<Tduhb09> list09 = this.dao.query("tduhb09.query", idMap);
			if (!StrUtil.listIsNotNullOrEmpty(list09)) {
				idMap.put("status", status);
				try {
					dao.insert("tduhb09.insert", idMap);
					insertSuccessCount++;
				} catch (Exception e) {
					buffer.append("新增第" + count + "条记录失败\n");
					inInfo.setStatus(-1);
					insertFailCount++;
				}

			}
			
			if (insertSuccessCount > 0) {
				buffer.insert(0, "新增成功" + insertSuccessCount + "条记录！\n");
			} else {
				if (insertFailCount > 0) {
					buffer.insert(0, "新增失败" + insertFailCount + "条记录！\n");
				} else {
					inInfo.setStatus(-1);
					buffer.insert(0, "新增记录失败，该记录已存在");
				}
			}
			
			count++;
		}
		
		inInfo.setMsg(buffer.toString());
		return inInfo;
	}
	

//	public EiInfo insert( EiInfo inInfo )
//	{
//		StringBuffer buffer = new StringBuffer();
//		Dao dao = this.getDao();
//		int insertSuccessCount = 0;
//		int insertFailCount = 0;
//		for (int i = 0; i < inInfo.getBlock("result").getRowCount(); i++) {
//			try {
//				String monitorid = inInfo.getCellStr("result", i, "monitorid");
//				String factorid = inInfo.getCellStr("result", i, "factorid");
//				Map<String, String> idMap = new HashMap<>();
//				idMap.put("monitorid", monitorid);
//				idMap.put("factorid", factorid);
//				List<Tduhb09> list09 = this.dao.query("tduhb09.query", idMap);
//				if (!StrUtil.listIsNotNullOrEmpty(list09)) {
//					dao.insert("tduhb09.insert", inInfo.getBlock("result").getRow(i));
//					insertSuccessCount++;
//				}
//
//			} catch (Exception ex) {
//				buffer.append("新增第" + i + "条记录失败\n");
//				inInfo.setStatus(-1);
//				insertFailCount++;
//			}
//		}
//		if (insertSuccessCount > 0) {
//			buffer.insert(0, "新增成功" + insertSuccessCount + "条记录！\n");
//		} else {
//			if (insertFailCount > 0) {
//				buffer.insert(0, "新增失败" + insertFailCount + "条记录！\n");
//			} else {
//				inInfo.setStatus(-1);
//				buffer.insert(0, "新增记录失败，该记录已存在");
//			}
//		}
//
//		inInfo.setMsg(buffer.toString());
//		return inInfo;
//		
//	}
	
	public EiInfo getFactor(EiInfo inInfo) {
		
		EiInfo outInfo=new EiInfo();
		String monitorid = StrUtil.isNullToStr(inInfo.get("monitorid").toString());
		EiBlock block = new EiBlock("factor");
		block.setBlockMeta(new Tduhb10().eiMetadata);
		List factorlist = new ArrayList<>();
		if ("".equals(monitorid)) {
			factorlist = this.dao.query("tduhb10.query", new HashMap());
		}else {
			Map params= new HashMap();
			params.put("monitorid",monitorid);
			factorlist = this.dao.query("tduhb10.queryfactor", params);
		}
		block.setRows(factorlist);
		outInfo.setBlock(block);
		return outInfo;
	};
}
