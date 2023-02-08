package com.baosight.sggk.du.hb.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.sggk.common.du.domain.Tduhb07;
import com.baosight.sggk.common.du.domain.Tduhb0702;
import com.baosight.sggk.common.du.domain.Tduhb10;
import com.baosight.sggk.util.StrUtil;

public class ServiceDUHB0702 extends ServiceEPBase {
	
	private static final Logger logger = Logger.getLogger(ServiceDUHB0702.class);

	public EiInfo initLoad( EiInfo inInfo )
	{
		EiInfo outInfo = new EiInfo();
		
		String siteid = StringUtils.isBlank((String) inInfo.get("siteid")) ? "" : (String) inInfo.get("siteid");

		Tduhb07 tduhb07 = getSiteById(siteid);
        if(StringUtils.isNotBlank(siteid) && tduhb07 != null) {
        	outInfo.set("inqu_status-0-siteid", tduhb07.getSiteid());
        	outInfo.set("inqu_status-0-sitename", tduhb07.getSitename());
        }else {
			outInfo.setMsg("监测点参数错误");
			return outInfo;
		}
        
		EiBlockMeta eiMetadata;
		EiColumn eiColumn;
		
		//加载手工子表
		EiBlock humanblock = new EiBlock("human");
		humanblock.addBlockMeta(new Tduhb0702().eiMetadata);
		outInfo.addBlock(humanblock);
		//加载在线子表
		EiBlock onlineblock = new EiBlock("online");
		onlineblock.addBlockMeta(new Tduhb0702().eiMetadata);
		outInfo.addBlock(onlineblock);
		
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
		  
	    String portid = StringUtils.isBlank(tduhb07.getPortid()) ? "空" : tduhb07.getPortid();
		EiBlock factorblock = new EiBlock("factors");
	    factorblock.setBlockMeta((new Tduhb10()).eiMetadata);
	    Map<String, String> map = new HashMap<String, String>();
		map.put("portid", portid);
		List list3 = this.dao.query("tduhb0702.queryDetail", map);
		factorblock.setRows(list3);
		outInfo.setBlock(factorblock);

		outInfo.setMsg("页面加载完成");
		return outInfo;
	}

	public EiInfo queryHuman(EiInfo inInfo)
	{
		EiInfo outInfo  = new EiInfo();
		EiBlock humanBlock = new EiBlock("human");
		humanBlock.setBlockMeta(new Tduhb0702().eiMetadata);
		
		String siteid = inInfo.getString("inqu_status-0-siteid");
		if(StringUtils.isBlank(siteid)) {
			inInfo.setMsg("监测点ID为空");
			return inInfo;
		}
		inInfo.set("inqu_status-0-type", "1");
		List<Tduhb0702> list = this.dao.query("tduhb0702.query",inInfo.getBlock("inqu_status").getRow(0), 0, -999999);
		humanBlock.setRows(list);
		outInfo.addBlock(humanBlock);
		//EiInfo outInfo = super.query(inInfo, "tduhb0702.query", new Tduhb0702(), false, null, EiConstant.queryBlock, "human", "human");
		return outInfo;
	}
	
	public EiInfo queryOnline(EiInfo inInfo)
	{
		EiInfo outInfo  = new EiInfo();
		EiBlock onlineBlock = new EiBlock("online");
		onlineBlock.setBlockMeta(new Tduhb0702().eiMetadata);
		
		String siteid = inInfo.getString("inqu_status-0-siteid");
		if(StringUtils.isBlank(siteid)) {
			inInfo.setMsg("监测点ID为空");
			return inInfo;
		}
		inInfo.set("inqu_status-0-type", "2");
		List<Tduhb0702> list = this.dao.query("tduhb0702.query",inInfo.getBlock("inqu_status").getRow(0), 0, -999999);
		onlineBlock.setRows(list);
		outInfo.addBlock(onlineBlock);
		//EiInfo outInfo = super.query(inInfo, "tduhb0702.query", new Tduhb0702(), false, null, EiConstant.queryBlock, "online", "online");
		return outInfo;
	}
	
	
	public EiInfo deleteHuman( EiInfo inInfo )
	{
		EiInfo outInfo = super.delete(inInfo, "tduhb0702.delete", "human");
		EiInfo humanInfo = queryHuman(inInfo);
		outInfo.setBlock(humanInfo.getBlock("human"));
		return outInfo;
	}
	
	public EiInfo deleteOnline( EiInfo inInfo )
	{
		EiInfo outInfo = super.delete(inInfo, "tduhb0702.delete", "online");
		EiInfo onlineInfo = queryOnline(inInfo);
		outInfo.setBlock(onlineInfo.getBlock("online"));
		return outInfo;
	}
	
	public EiInfo updateHuman( EiInfo inInfo )
	{
		StringBuffer buffer = new StringBuffer();
		StringBuffer detail = new StringBuffer();
		String currentUser = String.valueOf(UserSession.getLoginName());
	    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Dao dao = this.getDao();
		int updateSuccessCount = 0;
		int updateFailCount = 0;
		for( int i = 0; i < inInfo.getBlock( "human" ).getRowCount(); i++ )
		{
			inInfo.setCell("human", i, "modifier", currentUser);
            inInfo.setCell("human", i, "updatedTime", dateTimeFormat.format(new Date()));
            try
            {
            	dao.update( "tduhb0702.update", inInfo.getBlock( "human" ).getRow( i ) );
            	updateSuccessCount++;
            }
            catch( Exception ex )
            {
               buffer.append( "更新:" + inInfo.getBlock("human").getCell(i, "factorid") + "的记录失败\n" );
               inInfo.setStatus( -1 );
               detail.append( ex.getCause().toString() );
               updateFailCount++;
            }
		}
		if(updateSuccessCount >0)
			buffer.insert(0, "更新成功"+ updateSuccessCount + "条记录！\n");
		if(updateFailCount >0)
			buffer.insert(0, "更新失败"+ updateFailCount + "条记录！\n");
		EiInfo outInfo = queryHuman(inInfo);
		outInfo.setMsg( buffer.toString() );
		outInfo.setDetailMsg( detail.toString() );
	    return outInfo;
	}
	
	public EiInfo updateOnline( EiInfo inInfo )
	{
		StringBuffer buffer = new StringBuffer();
		StringBuffer detail = new StringBuffer();
		String currentUser = String.valueOf(UserSession.getLoginName());
	    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Dao dao = this.getDao();
		int updateSuccessCount = 0;
		int updateFailCount = 0;
		for( int i = 0; i < inInfo.getBlock( "online" ).getRowCount(); i++ )
		{
			inInfo.setCell("online", i, "modifier", currentUser);
            inInfo.setCell("online", i, "updatedTime", dateTimeFormat.format(new Date()));
            try
            {
            	dao.update( "tduhb0702.update", inInfo.getBlock( "online" ).getRow( i ) );
            	updateSuccessCount++;
            }
            catch( Exception ex )
            {
               buffer.append( "更新:" + inInfo.getBlock("online").getCell(i, "factorid") + "的记录失败\n" );
               inInfo.setStatus( -1 );
               detail.append( ex.getCause().toString() );
               updateFailCount++;
            }
		}
		if(updateSuccessCount >0)
			buffer.insert(0, "更新成功"+ updateSuccessCount + "条记录！\n");
		if(updateFailCount >0)
			buffer.insert(0, "更新失败"+ updateFailCount + "条记录！\n");
		EiInfo outInfo = queryOnline(inInfo);
		outInfo.setMsg( buffer.toString() );
		outInfo.setDetailMsg( detail.toString() );
	    return outInfo;
	}
	
	public EiInfo insertHuman( EiInfo inInfo )
	   {
		  String siteid = inInfo.getString("inqu_status-0-siteid");
	      StringBuffer buffer = new StringBuffer();
	      StringBuffer detail = new StringBuffer();
	      Dao dao = this.getDao();
	      String currentUser = String.valueOf(UserSession.getLoginName());
	      SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      int insertSuccessCount = 0;
		  int insertFailCount = 0;
		  int repeatCount = 0;
	      for( int i = 0; i < inInfo.getBlock( "human" ).getRowCount(); i++ )
	      {
	         try
	         {
	        	inInfo.setCell("human", i, "siteid", siteid);
	        	inInfo.setCell("human", i, "type", "1");
	            inInfo.setCell("human", i, "creator", currentUser);
	            inInfo.setCell("human", i, "createdTime", dateTimeFormat.format(new Date()));
	            //插入数据前，先判断要插入的 记录是否存在，若存在，反馈该记录已存在。
	            String factorid = inInfo.getCellStr("human", i, "factorid");
	            boolean flag = siteFactorIsExist(factorid, siteid, "1");
	            if (flag) {
	            	repeatCount++;
	            	continue;
				}
	            
	            dao.insert( "tduhb0702.insert", inInfo.getBlock( "human" ).getRow( i ) );
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
		  if(repeatCount >0)
				buffer.insert(0, "新增失败，"+ repeatCount + "条记录已存在！\n");
		  EiInfo outInfo = queryHuman(inInfo);
		  outInfo.setMsg( buffer.toString() );
		  outInfo.setDetailMsg( detail.toString() );
	      return outInfo;
	   }
	
	public EiInfo insertOnline( EiInfo inInfo )
	   {
		  String siteid = inInfo.getString("inqu_status-0-siteid");
	      StringBuffer buffer = new StringBuffer();
	      StringBuffer detail = new StringBuffer();
	      Dao dao = this.getDao();
	      String currentUser = String.valueOf(UserSession.getLoginName());
	      SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      int insertSuccessCount = 0;
		  int insertFailCount = 0;
		  int repeatCount = 0;
	      for( int i = 0; i < inInfo.getBlock( "online" ).getRowCount(); i++ )
	      {
	         try
	         {
	            inInfo.setCell("online", i, "siteid", siteid);
	        	inInfo.setCell("online", i, "type", "2");
	            inInfo.setCell("online", i, "creator", currentUser);
	            inInfo.setCell("online", i, "createdTime", dateTimeFormat.format(new Date()));
	            //插入数据前，先判断要插入的 记录是否存在，若存在，反馈该记录已存在。
	            String factorid = inInfo.getCellStr("online", i, "factorid");
	            boolean flag = siteFactorIsExist(factorid, siteid, "2");
	            if (flag) {
	            	repeatCount++;
	            	continue;
				}
	            
	            dao.insert( "tduhb0702.insert", inInfo.getBlock( "online" ).getRow( i ) );
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
		  if(repeatCount >0)
				buffer.insert(0, "新增失败，"+ repeatCount + "条记录已存在！\n");
		  EiInfo outInfo = queryOnline(inInfo);
		  outInfo.setMsg( buffer.toString() );
		  outInfo.setDetailMsg( detail.toString() );
	      return outInfo;
	   }
	
	private Tduhb07 getSiteById(String siteid) 
	{
		try {
			if(StringUtils.isBlank(siteid)) {
				siteid = "空";
			}
			Tduhb07 tduhb07 = null;
			Map<String, String> map = new HashMap<String, String>();
			map.put("siteid", siteid);
	        List list = this.dao.query("tduhb07.query", map);
	        if(list.size() > 0) {
	        	tduhb07 = (Tduhb07)list.get(0);
	        }
			return tduhb07;
		}
		catch( Exception ex )
	     {
	        throw ex;
	     }
	}
	
	/**
	 * 判断当前添加的监测站点因子是否存在，存在则返回true
	 * @param fatorid
	 * @param siteid
	 * @param type
	 * @return
	 */
	private Boolean siteFactorIsExist(String factorid,String siteid,String type) {
		boolean flag = false;
		Map<String,String> map = new HashMap<>();
		map.put("factorid", factorid);
		map.put("siteid", siteid);
		map.put("type", type);
		List<Tduhb0702> list = this.dao.query("tduhb0702.query",map);
		if (StrUtil.listIsNotNullOrEmpty(list)) {
			flag = true;
		}
		
		return flag;
	}
	
	
}
