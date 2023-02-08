package com.baosight.sggk.du.hb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.baosight.sggk.common.du.domain.Tduhb01;
import com.baosight.sggk.du.hb.domain.DUHB40;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;

public class ServiceDUHB40 extends ServiceEPBase {
	
	private static final Logger logger = Logger.getLogger(ServiceDUHB40.class);

	public EiInfo initLoad( EiInfo inInfo )
	{
        EiInfo outInfo = new EiInfo();
        
        EiBlock block = new EiBlock(EiConstant.resultBlock);
		block.addBlockMeta(new DUHB40().eiMetadata);
		outInfo.setBlock(block);
		
        EiBlock departblock = new EiBlock("departList");
		departblock.setBlockMeta(new Tduhb01().eiMetadata);
		List<Map> list1 = new ArrayList<>();
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("departmentId", "");
		map1.put("departmentName", "");
		list1.add(map1);
		departblock.addRows(list1);
		Map querymap = new HashMap();
		querymap.put("parentid", "root");
	    List list3 = this.dao.query("tduhb01.query", querymap);
	    departblock.addRows(list3);
	    outInfo.setBlock(departblock);
	    
		outInfo.setMsg("页面加载完成");
		return outInfo;
	}

	public EiInfo query( EiInfo inInfo )
	{
		EiInfo outInfo = super.query(inInfo,"DUHB40.query",new DUHB40());
		outInfo.getBlock(EiConstant.resultBlock);
		return outInfo;
	}
	
    public EiInfo update(EiInfo inInfo) {
		
    	StringBuffer buffer = new StringBuffer();
		StringBuffer detail = new StringBuffer();
		int updateSuccessCount = 0;
		int updateFailCount = 0;
		for( int i = 0; i < inInfo.getBlock( "result" ).getRowCount(); i++ )
		{
			String rid = String.valueOf(inInfo.getBlock("result").getCell(i, "departid")).trim() + "-" + String.valueOf(inInfo.getBlock("result").getCell(i, "loginname")).trim();
			inInfo.setCell("result", i, "rid", rid);
            
            try
            {
            	this.dao.delete( "DUHB40.delete", inInfo.getBlock( "result" ).getRow( i ) );
            	this.dao.insert( "DUHB40.insert", inInfo.getBlock( "result" ).getRow( i ) );
            	updateSuccessCount++;
            }
            catch( Exception ex )
            {
               buffer.append( "更新:" + inInfo.getBlock("result").getCell(i, "loginname") + "的记录失败\n" );
               inInfo.setStatus( -1 );
               detail.append( ex.getCause().toString() );
               updateFailCount++;
            }
		}
		if(updateSuccessCount >0)
			buffer.insert(0, "更新成功"+ updateSuccessCount + "条记录！\n");
		if(updateFailCount >0)
			buffer.insert(0, "更新失败"+ updateFailCount + "条记录！\n");
		EiInfo out = query(inInfo);
		out.setMsg(buffer.toString());
		out.setDetailMsg(detail.toString());
		return out;
	}
	
}
