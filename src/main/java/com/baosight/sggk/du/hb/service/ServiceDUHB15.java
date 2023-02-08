package com.baosight.sggk.du.hb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.sggk.common.du.domain.Tduhb05;
import com.baosight.sggk.common.du.domain.Tduhb14;
import com.baosight.sggk.common.du.domain.Tduhb15;

public class ServiceDUHB15 extends ServiceEPBase {
	
	private static final Logger logger = Logger.getLogger(ServiceDUHB15.class);

	public EiInfo initLoad( EiInfo inInfo )
	{
		EiInfo outInfo = new EiInfo();
		
		String dischargeportid = StringUtils.isBlank((String) inInfo.get("dischargeportid")) ? "" : (String) inInfo.get("dischargeportid");
		
		String monitorid = "空";
		String dischargeportname = "";
		Tduhb14 tduhb14 = getDischargeportById(dischargeportid);
		if(!StringUtils.isBlank(dischargeportid) && tduhb14 != null) {
			dischargeportname = tduhb14.getDischargeportname();
			monitorid = tduhb14.getMonitorid();
		}else {
			outInfo.setMsg("排口参数错误");
			return outInfo;
		}
		
		EiBlockMeta eiMetadata = null;
		EiColumn eiColumn = null;
		
		//初始化查询区
		EiBlock queryblock = new EiBlock(EiConstant.queryBlock);
		eiMetadata = new EiBlockMeta();		 
	    eiColumn = new EiColumn("dischargeportid");
	    eiMetadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("dischargeportname");
	    eiMetadata.addMeta(eiColumn);
	    queryblock.setBlockMeta(eiMetadata);       
	    Map<String,String> row = new HashMap<String,String>();
	    row.put("dischargeportid", dischargeportid);
	    row.put("dischargeportname", dischargeportname);
	    queryblock.addRow(row);
	    outInfo.setBlock(queryblock);
		
		//加载因子表
		EiBlock portfsblock = new EiBlock("portfs");
		portfsblock.addBlockMeta(new Tduhb15().eiMetadata);
		outInfo.setBlock(portfsblock);
		
		//添加标志
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

	    outInfo.setMsg("页面加载完成");
		return outInfo;
	}
	
	//查询排口因子
	public EiInfo queryPORTFS(EiInfo inInfo)
	{
		String dischargeportid = inInfo.getString("inqu_status-0-dischargeportid");
		if(StringUtils.isBlank(dischargeportid)) {
			inInfo.setMsg("排口ID为空");
			return inInfo;
		}
		inInfo.set("inqu_status-0-portid", dischargeportid);
		EiInfo outInfo = super.query(inInfo, "tduhb15.queryDetail", new Tduhb15(), false, null, EiConstant.queryBlock, "portfs", "portfs");
		return outInfo;
	}
	
	//查询监测点
	public EiInfo querySITEFS(EiInfo inInfo)
	{
		String dischargeportid = inInfo.getString("inqu_status-0-dischargeportid");
		if(StringUtils.isBlank(dischargeportid)) {
			inInfo.setMsg("排口ID为空");
			return inInfo;
		}
		inInfo.set("inqu_status-0-portid", dischargeportid);
		EiInfo outInfo = super.query(inInfo, "tduhb15.querySiteDetail", new Tduhb05(), false, null, EiConstant.queryBlock, "sitefs", "sitefs");
		return outInfo;
	}
	
	public EiInfo deletePORTFS(EiInfo inInfo)
	{
		
		StringBuffer buffer = new StringBuffer();
	    Dao dao = this.getDao();
	    int deleteSuccessCount = 0;
		int deleteFailCount = 0;
	    for( int i = 0; i < inInfo.getBlock( "portfs" ).getRowCount(); i++ )
	    {
	         try
	         {
	        	 String portid =  StringUtils.isBlank((String) inInfo.getCell("portfs", i, "portid")) ? "空" : (String) inInfo.getCell("portfs", i, "portid");
				 String factorid =  StringUtils.isBlank((String) inInfo.getCell("portfs", i, "factorid")) ? "空" : (String) inInfo.getCell("portfs", i, "factorid");
				 inInfo.setCell("portfs", i, "portid", portid);
		         inInfo.setCell("portfs", i, "factorid", factorid);
	            
	            dao.delete( "tduhb15.delete", inInfo.getBlock( "portfs" ).getRow( i ) );
	            deleteSuccessCount++;
	         }
	         catch( Exception ex )
	         {
	            buffer.append( "删除第" + i + "条记录失败\n" );
	            inInfo.setStatus( -1 );
	            deleteFailCount++;
	         }
	    }
	    if(deleteSuccessCount >0)
			buffer.insert(0, "删除成功"+ deleteSuccessCount + "条记录\n");
		if(deleteFailCount >0)
		    buffer.insert(0, "删除失败"+ deleteFailCount + "条记录\n");
	   
		EiInfo outInfo = queryPORTFS(inInfo);
		outInfo.setMsg( buffer.toString() );
		return outInfo;
	}
	
	private Tduhb14 getDischargeportById(String dischargeportid) 
	{
		try {
			if(StringUtils.isBlank(dischargeportid)) {
				dischargeportid = "空";
			}
			Tduhb14 tduhb14 = null;
			Map<String, String> map = new HashMap<String, String>();
			map.put("dischargeportid", dischargeportid);
	        List list = this.dao.query("tduhb14.query", map);
	        if(list.size() > 0) {
	        	tduhb14 = (Tduhb14)list.get(0);
	        }
			return tduhb14;
		}
		catch( Exception ex )
	     {
	        throw ex;
	     }
	}
}
