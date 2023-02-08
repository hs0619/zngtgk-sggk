package com.baosight.sggk.du.hb.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.sggk.common.du.domain.Tduhb10;
import com.baosight.sggk.util.StrUtil;

public class ServiceDUHB10 extends ServiceEPBase {
	
	private static final Logger logger = Logger.getLogger(ServiceDUHB10.class);

	public EiInfo initLoad( EiInfo inInfo )
	{
        EiInfo outInfo = new EiInfo();
		
//		EiBlock block = new EiBlock(EiConstant.resultBlock);
//		block.addBlockMeta(new Tduhb10().eiMetadata);
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
		  
		outInfo.setMsg("页面加载完成");
		return outInfo;
	}

	public EiInfo query( EiInfo inInfo )
	{
		EiInfo outInfo = super.query(inInfo,"tduhb10.query",new Tduhb10());
		outInfo.getBlock(EiConstant.resultBlock);
		return outInfo;
	}

	public EiInfo delete( EiInfo inInfo )
	{
		EiInfo outInfo = super.delete(inInfo, "tduhb10.delete");
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
            	dao.update( "tduhb10.update", inInfo.getBlock( "result" ).getRow( i ) );
            	updateSuccessCount++;
            }
            catch( Exception ex )
            {
               buffer.append( "更新:" + inInfo.getBlock("result").getCell(i, "factorid") + "的记录失败\n" );
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

	public EiInfo insert(EiInfo inInfo) {
		StringBuffer buffer = new StringBuffer();
		StringBuffer detail = new StringBuffer();
		Dao dao = this.getDao();
		String currentUser = String.valueOf(UserSession.getLoginName());
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int insertSuccessCount = 0;
		int insertFailCount = 0;
		for (int i = 0; i < inInfo.getBlock("result").getRowCount(); i++) {
			try {
				String factorid = inInfo.getCellStr("result", i, "factorid");
				Map<String, String> idMap = new HashMap<>();
				idMap.put("factorid", factorid);
				List<Tduhb10> list10 = this.dao.query("tduhb10.query",idMap);
				if (!StrUtil.listIsNotNullOrEmpty(list10)) {
					inInfo.setCell("result", i, "creator", currentUser);
					inInfo.setCell("result", i, "createdTime", dateTimeFormat.format(new Date()));

					dao.insert("tduhb10.insert", inInfo.getBlock("result").getRow(i));
					insertSuccessCount++;
				}
				
			} catch (Exception ex) {
				buffer.append("新增第" + i + "条记录失败\n");
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

		inInfo.setMsg(buffer.toString());
		return inInfo;
	}
}
