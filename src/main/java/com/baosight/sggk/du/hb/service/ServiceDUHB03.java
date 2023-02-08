/**
 *
 */
package com.baosight.sggk.du.hb.service;

import com.baosight.sggk.common.du.domain.Tduhb01;
import com.baosight.iplat4j.core.ei.*;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ServiceDUHB03 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHB03.class);

	public EiInfo initLoad(EiInfo inInfo) {
		  EiInfo outInfo = new EiInfo();
		  
		  EiBlock block = new EiBlock(EiConstant.resultBlock);
		  block.addBlockMeta(new Tduhb01().eiMetadata);
		  outInfo.setBlock(block);
		  
		  //添加标志
		  EiBlockMeta eiMetadata = new EiBlockMeta();
		  EiColumn eiColumn = new EiColumn("flagid");
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
		  outInfo.set("inqu_status-0-parentid","DEP00014");
		  
		  //添加类型
		  eiMetadata = new EiBlockMeta();
		  eiColumn = new EiColumn("typeid");
		  eiMetadata.addMeta(eiColumn);
		  eiColumn = new EiColumn("typename");
		  eiMetadata.addMeta(eiColumn);
		  EiBlock typeblock = new EiBlock("typeList");
		  typeblock.setBlockMeta(eiMetadata);
		  List<Map> list2 = new ArrayList<>();
		  Map<String, String> map3 = new HashMap<String, String>();
		  map3.put("typeid", "D1");
		  map3.put("typename", "厂部");
		  list2.add(map3);
		  Map<String, String> map4 = new HashMap<String, String>();
		  map4.put("typeid", "P1");
		  map4.put("typename", "工序");
		  list2.add(map4);
		  typeblock.setRows(list2);
		  outInfo.setBlock(typeblock);
		  
		  outInfo.setMsg("页面加载完成");
		  return outInfo;
	}
	
	
	public EiInfo query( EiInfo inInfo )
	{
		inInfo.set("inqu_status-0-type", "P1");
		EiInfo outInfo = super.query(inInfo,"tduhb01.queryInfo");
		return outInfo;
	}

	public EiInfo delete( EiInfo inInfo )
	{
		EiInfo outInfo = super.delete(inInfo, "tduhb01.delete");
		EiInfo out = query(inInfo);
		out.setMsg(outInfo.getMsg());
		out.setDetailMsg(outInfo.getDetailMsg());
		return out;
	}

}
