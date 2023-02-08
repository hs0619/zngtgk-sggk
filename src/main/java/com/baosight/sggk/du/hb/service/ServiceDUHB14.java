package com.baosight.sggk.du.hb.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.sggk.common.du.domain.Tduhb03;
import com.baosight.sggk.common.du.domain.Tduhb08;
import com.baosight.sggk.common.du.domain.Tduhb14;

public class ServiceDUHB14 extends ServiceEPBase {
	
	private static final Logger logger = Logger.getLogger(ServiceDUHB14.class);

	public EiInfo initLoad( EiInfo inInfo )
	{
        EiInfo outInfo = new EiInfo();
		
		EiBlock block = new EiBlock(EiConstant.resultBlock);
		block.addBlockMeta(new Tduhb14().eiMetadata);
		outInfo.setBlock(block);
		
		//添加工序
		EiBlock procedureblock = new EiBlock("procedureList");
		procedureblock.setBlockMeta((new Tduhb03()).eiMetadata);
		List procedurelist = this.dao.query("tduhb03.query", new HashMap());
		procedureblock.setRows(procedurelist);
		outInfo.setBlock(procedureblock);
		
		//添加类别
		EiBlock monitorblock = new EiBlock("monitorList");
		monitorblock.setBlockMeta((new Tduhb08()).eiMetadata);
		List monitorlist = this.dao.query("tduhb08.query", new HashMap());
		monitorblock.setRows(monitorlist);
		outInfo.setBlock(monitorblock);
		
		//添加类别
	    EiBlockMeta eiMetadata = new EiBlockMeta();
	    EiColumn eiColumn = null;
	    eiColumn = new EiColumn("formalid");
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
		
		//添加标志
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
		  
		outInfo.setMsg("页面加载完成");
		return outInfo;
	}

	public EiInfo query( EiInfo inInfo )
	{
		EiInfo outInfo = super.query(inInfo,"tduhb14.query",new Tduhb14());
		outInfo.getBlock(EiConstant.resultBlock);
		return outInfo;
	}

	public EiInfo delete( EiInfo inInfo )
	{

		try
	    {
			for( int i = 0; i < inInfo.getBlock( "result" ).getRowCount(); i++ )
			{
				String dischargeportid = StringUtils.isBlank((String)inInfo.getCell("result", i, "dischargeportid")) ? "空" : (String)inInfo.getCell("result", i, "dischargeportid");
				HashMap params = new HashMap();
		        params.put("portid", dischargeportid);
		        List list = dao.query("tduhb15.queryById", params);
		        if(list.size() > 0) {
					inInfo.setStatus(-1);
				    inInfo.setMsg("记录下面有其他子级记录");
				    return inInfo;
				}
			}
	    }
	    catch( Exception ex )
	    {
	       inInfo.setStatus(-1);
	       inInfo.setMsg(ex.toString());
	       return inInfo;
	    }
		EiInfo outInfo = super.delete(inInfo, "tduhb14.delete");
		EiInfo out = query(inInfo);
		out.setMsg(outInfo.getMsg());
		out.setDetailMsg(outInfo.getDetailMsg());
		return out;
	}

	public EiInfo update( EiInfo inInfo )
	{
		StringBuffer buffer = new StringBuffer();
		StringBuffer detail = new StringBuffer();
		String currentUser = String.valueOf(UserSession.getLoginName());
	    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Dao dao = this.getDao();
		int updateSuccessCount = 0;
		int updateFailCount = 0;
		for( int i = 0; i < inInfo.getBlock( "result" ).getRowCount(); i++ )
		{
			inInfo.setCell("result", i, "modifier", currentUser);
            inInfo.setCell("result", i, "updatedTime", dateTimeFormat.format(new Date()));
            
            try
            {
            	dao.update( "tduhb14.update", inInfo.getBlock( "result" ).getRow( i ) );
            	updateSuccessCount++;
            }
            catch( Exception ex )
            {
               buffer.append( "更新:" + inInfo.getBlock("result").getCell(i, "dischargeportid") + "的记录失败\n" );
               inInfo.setStatus( -1 );
               detail.append( ex.getCause().toString() );
               updateFailCount++;
            }
		}
		if(updateSuccessCount >0)
			buffer.insert(0, "更新成功"+ updateSuccessCount + "条记录！\n");
		if(updateFailCount >0)
			buffer.insert(0, "更新失败"+ updateFailCount + "条记录！\n");
		inInfo.setMsg( buffer.toString() );
		inInfo.setDetailMsg( detail.toString() );
		return inInfo;
	}

	public EiInfo insert( EiInfo inInfo )
   {
      StringBuffer buffer = new StringBuffer();
      StringBuffer detail = new StringBuffer();
      Dao dao = this.getDao();
      String currentUser = String.valueOf(UserSession.getLoginName());
      SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      int insertSuccessCount = 0;
	  int insertFailCount = 0;
      for( int i = 0; i < inInfo.getBlock( "result" ).getRowCount(); i++ )
      {
         try
         {
            inInfo.setCell("result", i, "creator", currentUser);
            inInfo.setCell("result", i, "createdTime", dateTimeFormat.format(new Date()));
            
            dao.insert( "tduhb14.insert", inInfo.getBlock( "result" ).getRow( i ) );
            insertSuccessCount++;
         }
         catch( Exception ex )
         {
            buffer.append( "新增第" + i + "条记录失败\n" );
            inInfo.setStatus( -1 );
            detail.append( ex.getCause().toString() );
            insertFailCount++;
         }
      }
      if(insertSuccessCount >0)
			buffer.insert(0, "新增成功"+ insertSuccessCount + "条记录！\n");
	  if(insertFailCount >0)
			buffer.insert(0, "新增失败"+ insertFailCount + "条记录！\n");
      inInfo.setMsg( buffer.toString() );
      inInfo.setDetailMsg( detail.toString() );
      return inInfo;
   }
}
