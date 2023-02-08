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

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.sggk.common.du.domain.Tduhb01;
import com.baosight.sggk.common.du.domain.Tduhb07;
import com.baosight.sggk.common.du.domain.Tduhb08;

public class ServiceDUHB0602 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHB0602.class);

	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		String portid = inInfo.get("portid").toString();
		if(StringUtils.isBlank(portid)) {
			outInfo.setMsg("参数错误,未从正确页面进入");
			return outInfo;
		}
		outInfo.set("portid", portid);
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

		outInfo.set("inqu_status-0-type","");
		outInfo.set("inqu_status-0-status","1");
		outInfo.set("inqu_status-0-monitorid","");
		
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
		EiInfo outInfo = super.query(inInfo, "tduhb07.queryInfo", new Tduhb07());
		return outInfo;
	}

	public EiInfo updatePortId(EiInfo inInfo)
	{
		EiInfo outInfo =new EiInfo();
		String portid = inInfo.get("portid").toString();
		if(StringUtils.isBlank(portid)) {
			outInfo.setMsg("参数错误,未从正确页面进入");
			return outInfo;
		}
		String currentUser = String.valueOf(UserSession.getLoginName());
	    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for( int i = 0; i < inInfo.getBlock("result").getRowCount(); i++ )
		{
			inInfo.setCell("result", i, "portid", portid);
			inInfo.setCell("result", i, "modifier", currentUser);
			inInfo.setCell("result", i, "updatedTime", dateTimeFormat.format(new Date()));
		}
		outInfo = super.update(inInfo, "tduhb07.updatePortId");
	    return outInfo;
	}
}
