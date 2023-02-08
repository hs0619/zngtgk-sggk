package com.baosight.sggk.du.hb.service;

import java.text.SimpleDateFormat;
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
import com.baosight.sggk.common.du.domain.Tduhb08;
import com.baosight.sggk.common.du.domain.Tduhb10;
import com.baosight.sggk.common.du.domain.Tduhb14;
import com.baosight.sggk.common.du.domain.Tduhb15;
import com.baosight.sggk.util.StrUtil;

public class ServiceDUHB1501 extends ServiceEPBase {
	
	private static final Logger logger = Logger.getLogger(ServiceDUHB1501.class);
	
	public EiInfo initSelectData(EiInfo inInfo) {
		
		EiBlock pfmonitorblock = inInfo.getBlock("pfmonitor");
		if(pfmonitorblock == null) {
			pfmonitorblock = new EiBlock("pfmonitor");
			pfmonitorblock.setBlockMeta((new Tduhb08()).eiMetadata);
		}
		EiBlock pffactorblock = inInfo.getBlock("pffactor");
		if(pffactorblock == null) {
			pffactorblock = new EiBlock("pffactor");
			pffactorblock.setBlockMeta((new Tduhb10()).eiMetadata);
		}
		String oprationType = StringUtils.isBlank((String) inInfo.get("oprationType")) ? "" : (String) inInfo.get("oprationType");
		List pfmonitorlist = this.dao.query("tduhb08.query", new HashMap());
	    pfmonitorblock.setRows(pfmonitorlist);
	    inInfo.setBlock(pfmonitorblock);
		String monitorid = "";
		if("insert".equals(oprationType)) {
			if(pfmonitorlist.size() > 0) {
				Tduhb08 tduhb08 = (Tduhb08)pfmonitorlist.get(0);
				if(tduhb08 != null) {
					monitorid = StrUtil.trimToString(tduhb08.getMonitorid());
					if(StringUtils.isBlank(monitorid)) {
						monitorid = "空";
					}
					Map pffactormap = new HashMap();
				    pffactormap.put("monitorid",monitorid);
			        List pffactorlist = this.dao.query("tduhb10.queryfactor", pffactormap);
			        pffactorblock.setRows(pffactorlist);
				}
			}
		}else if("update".equals(oprationType)) {
			monitorid = StringUtils.isBlank((String) inInfo.get("monitorid")) ? "空" : (String) inInfo.get("monitorid");
			String factorid = StringUtils.isBlank((String) inInfo.get("factorid")) ? "" : (String) inInfo.get("factorid");
			if(!"空".equals(monitorid)) {
				inInfo.set("inqu_status-0-pfmonitorid", monitorid);
			}
			if(!"".equals(monitorid)) {
				inInfo.set("inqu_status-0-pffactorid", factorid);
			}
			Map pffactormap = new HashMap();
		    pffactormap.put("monitorid",monitorid);
	        List pffactorlist = this.dao.query("tduhb10.queryfactor", pffactormap);
	        pffactorblock.setRows(pffactorlist);
		}
        inInfo.setBlock(pffactorblock);
	    
		return inInfo;
	}
	
	public EiInfo initLoad( EiInfo inInfo )
	{
		EiInfo outInfo = new EiInfo();
		
		String oprationType = (String) inInfo.get("oprationType");
		String portid = StringUtils.isBlank((String) inInfo.get("portid")) ? "" : (String) inInfo.get("portid");
		String portname = "";
		Tduhb14 tduhb14 = getDischargeportById(portid);
        if(!StringUtils.isBlank(portid) && tduhb14 != null) {
        	portname = StrUtil.trimToString(tduhb14.getDischargeportname());
        }else {
			outInfo.setMsg("排口参数错误");
			return outInfo;
		}
		String factorid = StringUtils.isBlank((String) inInfo.get("factorid")) ? "" : (String) inInfo.get("factorid");
		
		
		EiBlock queryblock = new EiBlock(EiConstant.queryBlock);
		EiBlockMeta metadata = new EiBlockMeta();		 
	    EiColumn eiColumn = new EiColumn("pfportid");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("pfportname");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("pfmonitorid");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("pffactorid");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("highlimit");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("lowlimit");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("nkhighlimit");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("nklowlimit");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("description");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("status");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("oprationType");
	    metadata.addMeta(eiColumn);
	    queryblock.setBlockMeta(metadata);  
	    
	    EiBlock pfmonitorblock = new EiBlock("pfmonitor");
		pfmonitorblock.setBlockMeta((new Tduhb08()).eiMetadata);
	    outInfo.setBlock(pfmonitorblock);
		
		EiBlock pffactorblock = new EiBlock("pffactor");
        pffactorblock.setBlockMeta((new Tduhb10()).eiMetadata);
        outInfo.setBlock(pffactorblock);
		
	    Map<String,String> row = new HashMap<String,String>();
     	row.put("pfportid", portid);
    	row.put("pfportname", portname);
    	
        if("insert".equals(oprationType)) {
        	row.put("oprationType", "insert");
        }else if("update".equals(oprationType)) {
        	row.put("oprationType", "update");
        	List list = getFactorById(portid,factorid);
    		if(list.size() > 0) {
    			Tduhb15 tduhb15 = (Tduhb15)list.get(0);
    		    row.put("pfmonitorid", tduhb15.getMonitorid());
    		    row.put("pffactorid", tduhb15.getFactorid());
    		    row.put("highlimit", tduhb15.getHighlimit());
    		    row.put("lowlimit", tduhb15.getLowlimit());
    		    row.put("nkhighlimit", tduhb15.getNkhighlimit());
    		    row.put("nklowlimit", tduhb15.getNklowlimit());
    		    row.put("description", tduhb15.getDescription());
    		    row.put("status", tduhb15.getStatus());
    		}
        }
        queryblock.addRow(row);
		outInfo.setBlock(queryblock);
        
		outInfo.setMsg("页面加载完成");
		return outInfo;
	}
	
	private List getFactorById(String portid,String factorid) 
	{
		try {
			if(StringUtils.isBlank(portid)) {
				portid = "空";
			}
			if(StringUtils.isBlank(factorid)) {
				factorid = "空";
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("portid", portid);
			map.put("factorid", factorid);
	        List list = this.dao.query("tduhb15.queryById", map);
			return list;
		}
		catch( Exception ex )
		{
		    throw ex;
		}
	}
	
	//根据类型切换因子
    public EiInfo queryFactor(EiInfo inInfo) {
		
    	EiBlock pffactorblock = inInfo.getBlock("pffactor");
		if(pffactorblock == null) {
			pffactorblock = new EiBlock("pffactor");
			pffactorblock.setBlockMeta((new Tduhb10()).eiMetadata);
		}
		String monitorid = StringUtils.isBlank((String) inInfo.get("monitorid")) ? "空" : (String) inInfo.get("monitorid");
		Map pffactormap = new HashMap();
	    pffactormap.put("monitorid",monitorid);
        List pffactorlist = this.dao.query("tduhb10.queryfactor", pffactormap);
        pffactorblock.setRows(pffactorlist);
        inInfo.addBlock(pffactorblock);
        
		return inInfo;
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
    
    //保存因子
    public EiInfo save(EiInfo inInfo) {
		
    	StringBuffer buffer = new StringBuffer();
		String currentUser = String.valueOf(UserSession.getLoginName());
	    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Dao dao = this.getDao();
		try
        {
			String oprationType = StrUtil.trimToString(inInfo.get("inqu_status-0-oprationType"));
			String portid =  StringUtils.isBlank((String) inInfo.get("inqu_status-0-pfportid")) ? "空" : (String) inInfo.get("inqu_status-0-pfportid");
			String monitorid = StrUtil.trimToString(inInfo.get("inqu_status-0-pfmonitorid"));
			String factorid =  StringUtils.isBlank((String) inInfo.get("inqu_status-0-pffactorid")) ? "空" : (String) inInfo.get("inqu_status-0-pffactorid");
			String highlimit = StrUtil.trimToString(inInfo.get("inqu_status-0-highlimit"));
			String lowlimit = StrUtil.trimToString(inInfo.get("inqu_status-0-lowlimit"));
			String nkhighlimit = StrUtil.trimToString(inInfo.get("inqu_status-0-nkhighlimit"));
			String nklowlimit = StrUtil.trimToString(inInfo.get("inqu_status-0-nklowlimit"));
			String description = StrUtil.trimToString(inInfo.get("inqu_status-0-description"));
			String status = StrUtil.trimToString(inInfo.get("inqu_status-0-status"));
			Map<String, String> map = new HashMap<String, String>();
			map.put("monitorid", monitorid);
			map.put("factorid", factorid);
			map.put("portid", portid);
			map.put("highlimit", highlimit);
			map.put("lowlimit", lowlimit);
			map.put("nkhighlimit", nkhighlimit);
			map.put("nklowlimit", nklowlimit);
			map.put("description", description);
			map.put("status", status);
			if("insert".equals(oprationType)) {
				map.put("creator", currentUser);
				map.put("createdTime", dateTimeFormat.format(new Date()));
				dao.insert( "tduhb15.insert", map);
				buffer.insert(0, "记录保存成功\n");
			}else if("update".equals(oprationType)) {
				map.put("modifier", currentUser);
				map.put("updatedTime", dateTimeFormat.format(new Date()));
	        	dao.update( "tduhb15.update", map);
	        	buffer.insert(0, "记录保存成功\n");
			}else {
				buffer.insert(0, "记录保存失败\n");
			}
        }
        catch( Exception ex )
        {
           buffer.insert(0, "记录保存失败\n" + ex.toString() );
           inInfo.setStatus( -1 );
        }
		inInfo.setMsg(buffer.toString() );
        
		return inInfo;
	}

}
