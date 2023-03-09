/**
 *
 */
package com.baosight.sggk.du.hc.service;

import com.baosight.sggk.du.hc.domain.DUHC230201;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

//

public class ServiceDUHC2305 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHC2305.class);

	// 获取配置文件里的参数
	public ResourceBundle dbPro = ResourceBundle.getBundle("application");
	public String DbSchema = dbPro.getString("hbSchema");

	//
	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		//监测类型
		EiBlock monitorBlock=outInfo.addBlock("monitor");
		Map<String, Object> monitorMap=new HashMap<>();
		monitorMap.put("monitorid", "00");
		monitorMap.put("monitorname", "其他");
		monitorBlock.addRow(monitorMap);
		List monitorlist = this.dao.query("tduhb08.query", new HashMap());
		monitorBlock.addRows(monitorlist);
		
		//标志
		EiBlock flagBlock=outInfo.addBlock("flag");
		Map<String, Object> map=new HashMap<>();
		map.put("flagid", "1");
		map.put("flagname", "是");
		flagBlock.addRow(map);
		
		map=new HashMap<>();
		map.put("flagid", "0");
		map.put("flagname", "否");
		flagBlock.addRow(map);
		return outInfo;
	}
	
	
	public EiInfo query(EiInfo inInfo) {
		EiInfo outInfo = super.query(inInfo, "DUHC230201.query", new DUHC230201());
		return outInfo;
	}
	
	public EiInfo delete(EiInfo inInfo) {
		EiInfo outInfo = super.delete(inInfo, "DUHC230201.delete");
		return outInfo;
	}
}
