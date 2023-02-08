/**
 *
 */
package com.baosight.sggk.du.hb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.sggk.common.du.domain.Tduhb01;
import com.baosight.sggk.common.du.domain.Tduhb0702;
import com.baosight.sggk.common.du.domain.Tduhb08;
import com.baosight.sggk.util.StrUtil;

public class ServiceDUHB07 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHB07.class);

	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();

//		  EiBlock block = new EiBlock(EiConstant.resultBlock);
//		  block.addBlockMeta(new Tduhb07().eiMetadata);
//		  outInfo.setBlock(block);

		// 添加类别
		EiBlock monitorblock = new EiBlock("monitorList");
		monitorblock.setBlockMeta((new Tduhb08()).eiMetadata);
		List monitorlist = this.dao.query("tduhb08.query", new HashMap());
		monitorblock.setRows(monitorlist);
		outInfo.setBlock(monitorblock);

		// 添加厂部
		EiBlock departblock = new EiBlock("departList");
		departblock.setBlockMeta(new Tduhb01().eiMetadata);
		Map querymap = new HashMap();
		querymap.put("parentid", "root");
		List departlist = this.dao.query("tduhb01.query", querymap);
		departblock.addRows(departlist);
		outInfo.setBlock(departblock);

		// 工序  下拉框初始化
		EiBlock produceblock = inInfo.getBlock("procedureList");
		if(produceblock == null) {
			produceblock = new EiBlock("procedureList");
			produceblock.setBlockMeta((new Tduhb01()).eiMetadata);
		}
		
		Map<String, String> proMap = new HashMap<>();
		proMap.put("type", "P1");
		proMap.put("status", "1");
	    List proList = this.dao.query("tduhb01.query", proMap);
	    produceblock.addRows(proList);
	    outInfo.addBlock(produceblock);
		
		// 添加标志
		EiBlockMeta eiMetadata = new EiBlockMeta();
		EiColumn eiColumn = new EiColumn("flagid");
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

		//outInfo.set("inqu_status-0-type","01");
		outInfo.set("inqu_status-0-status","1");
		outInfo.set("inqu_status-0-monitorid","");


		Map params1 = new HashMap();
		String isonline = (String)inInfo.get("isonline");

		if("01".equals(isonline)){
			outInfo.addBlock(EiConstant.resultBlock);
			outInfo.set("inqu_status-0-isonline","1");
			params1.put("isonline","1");//在线
			List dataList1 = this.dao.query("tduhb07.querySumDate", params1);
			outInfo.setStatus(1);
			outInfo.setMsg("页面加载"+dataList1.size()+"条");
			outInfo.getBlock("result").setRows(dataList1);
			return outInfo;
		}
		if("02".equals(isonline)){
			outInfo.addBlock(EiConstant.resultBlock);
			outInfo.set("inqu_status-0-isonline","0");
			//outInfo.set("inqu_status-0-isartificial","1");
			params1.put("isartificial","1");//人工
			params1.put("isonline",null);//人工
			List dataList2 = this.dao.query("tduhb07.querySumDate", params1);
			outInfo.setStatus(1);
			outInfo.setMsg("页面加载"+dataList2.size()+"条");
			outInfo.getBlock("result").setRows(dataList2);
			return outInfo;
		}
		outInfo.setMsg("页面加载完成");
		return outInfo;
	}

	public EiInfo query(EiInfo inInfo) {
		String type = inInfo.get("inqu_status-0-type").toString();
		String monitorid = inInfo.get("inqu_status-0-monitorid").toString();
		if (type.equals("01")) {//查詢在线站点
			inInfo.set("inqu_status-0-isonline", "1");
		}else if (type.equals("02")){//查詢手工站点
			inInfo.set("inqu_status-0-isartificial", "1");
			inInfo.set("inqu_status-0-monitorid", "");
			inInfo.set("inqu_status-0-classifyid", monitorid);
		}
		String isonline = inInfo.getCellStr("inqu_status",0,"isonline");
		if("0".equals(isonline)){//人工
			inInfo.set("inqu_status-0-isartificial", "1");
			inInfo.set("inqu_status-0-isonline", "");
		}
		if("1".equals(isonline)){//在线
			inInfo.set("inqu_status-0-isartificial", "");
			inInfo.set("inqu_status-0-isonline", "1");
		}
		EiInfo outInfo = super.query(inInfo, "tduhb07.queryInfo");
		return outInfo;
	}

	public EiInfo delete(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		//获取监测站点的id集合
		for( int i = 0; i < inInfo.getBlock( "result" ).getRowCount(); i++ ) {
			String siteid = inInfo.getCellStr("result", i, "siteid");
			//根据监测站点id，去siteFactor表中去查询是否还存在对应的siteid，如果不存在，才可删除
			Map<String, String> map = new HashMap<>();
			map.put("siteid", siteid);
			List<Tduhb0702> list = this.dao.query("tduhb0702.query",map);
			if (StrUtil.listIsNotNullOrEmpty(list)) {
				outInfo.setStatus(-1);
				outInfo.setMsg("监测站点下有监测因子，请先删除监测因子！");
				return outInfo;
			}
		}
		
		outInfo = super.delete(inInfo, "tduhb07.delete");
		EiInfo out = query(inInfo);
		out.setMsg(outInfo.getMsg());
		out.setDetailMsg(outInfo.getDetailMsg());
		return out;
	}
	
	
	

}
