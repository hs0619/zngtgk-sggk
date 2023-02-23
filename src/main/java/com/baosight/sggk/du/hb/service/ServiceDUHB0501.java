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

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.sggk.common.du.domain.Tduhb01;
import com.baosight.sggk.common.du.domain.Tduhb05;
import com.baosight.sggk.common.du.domain.Tduhb07;
import com.baosight.sggk.common.du.domain.Tduhb08;
import com.baosight.sggk.util.StrUtil;


public class ServiceDUHB0501 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHB0501.class);

public EiInfo initSelectData(EiInfo inInfo) {
		
		EiBlockMeta eiMetadata;		 
	    EiColumn eiColumn;
	     //添加厂部
	    EiBlock departblock = inInfo.getBlock("departList");
		if(departblock == null) {
			departblock = new EiBlock("departList");
			departblock.setBlockMeta(new Tduhb01().eiMetadata);
		}
		Map querymap = new HashMap();
		querymap.put("parentid", "root");
		List departlist = this.dao.query("tduhb01.query", querymap);
		departblock.addRows(departlist);
		inInfo.setBlock(departblock);
	
		//添加类别
		EiBlock monitorblock = inInfo.getBlock("monitorList");
		if(monitorblock == null) {
			monitorblock = new EiBlock("monitorList");
			monitorblock.setBlockMeta((new Tduhb08()).eiMetadata);
		}
		List monitorlist = this.dao.query("tduhb08.query", new HashMap());
		monitorblock.setRows(monitorlist);
		inInfo.setBlock(monitorblock);
			
		//添加类别
		EiBlock formalblock = inInfo.getBlock("formalList");
		if(formalblock == null) {
			formalblock = new EiBlock("formalList");
			eiMetadata = new EiBlockMeta();
			eiColumn = new EiColumn("formalid");
			eiMetadata.addMeta(eiColumn);
		    eiColumn = new EiColumn("formalname");
			eiMetadata.addMeta(eiColumn);
			formalblock.setBlockMeta(eiMetadata);
		}
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
	    inInfo.setBlock(formalblock);
			
	    //添加标志
	    EiBlock flagblock = inInfo.getBlock("flagList");
		if(flagblock == null) {
			flagblock = new EiBlock("flagList");
			eiMetadata = new EiBlockMeta();
		    eiColumn = new EiColumn("flagid");
		    eiMetadata.addMeta(eiColumn);
		    eiColumn = new EiColumn("flagname");
		    eiMetadata.addMeta(eiColumn);
		    flagblock.setBlockMeta(eiMetadata);
		}
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
	    inInfo.setBlock(flagblock);
		
		String oprationType = StringUtils.isBlank((String) inInfo.get("oprationType")) ? "" : (String) inInfo.get("oprationType");
		
		String departid = "空";
		if("insert".equals(oprationType)) {
			inInfo.set("inqu_status-0-isformal", "1");
			inInfo.set("inqu_status-0-setright", "1");
			inInfo.set("inqu_status-0-ismap", "0");
			inInfo.set("inqu_status-0-countrypoint", "0");
			inInfo.set("inqu_status-0-citypoint", "0");
			inInfo.set("inqu_status-0-companypoint", "0");
			inInfo.set("inqu_status-0-cleanpoint", "0");
			inInfo.set("inqu_status-0-status", "1");
			inInfo.set("inqu_status-0-signform", "0");
			if(departlist.size() > 0) {
				departid = ((Tduhb01)departlist.get(0)).getDepartmentId();
			}
		}else if("update".equals(oprationType)) {
			String dischargeportid = StringUtils.isBlank((String)inInfo.get("dischargeportid")) ? "空" : (String)inInfo.get("dischargeportid");
			querymap = new HashMap();
    		querymap.put("dischargeportid", dischargeportid);
    	    List list = this.dao.query("tduhb05.query", querymap);
    		if(list.size() > 0) {
    			Tduhb05 tduhb05 = (Tduhb05)list.get(0);
    			departid = tduhb05.getDepartid();
    			inInfo.set("inqu_status-0-departid", tduhb05.getDepartid());
    			inInfo.set("inqu_status-0-procid", tduhb05.getProcid());
    			inInfo.set("inqu_status-0-monitorid", tduhb05.getMonitorid());
    			inInfo.set("inqu_status-0-isformal", tduhb05.getIsformal());
    			inInfo.set("inqu_status-0-setright", tduhb05.getSetright());
    			inInfo.set("inqu_status-0-ismap", tduhb05.getIsmap());
    			inInfo.set("inqu_status-0-countrypoint", tduhb05.getCountrypoint());
    			inInfo.set("inqu_status-0-citypoint", tduhb05.getCitypoint());
    			inInfo.set("inqu_status-0-companypoint", tduhb05.getCompanypoint());
    			inInfo.set("inqu_status-0-cleanpoint", tduhb05.getCleanpoint());
    			inInfo.set("inqu_status-0-status", tduhb05.getStatus());
    			inInfo.set("inqu_status-0-signform", tduhb05.getSignform());
    			
    		}
		}
		
		//添加工序
		EiBlock procedureblock = inInfo.getBlock("procedureList");
		if(procedureblock == null) {
			procedureblock = new EiBlock("procedureList");
			  procedureblock.setBlockMeta(new Tduhb01().eiMetadata);
		}
		querymap = new HashMap();
		querymap.put("parentid", departid);
		querymap.put("type", "P1");
		List procedurelist = this.dao.query("tduhb01.query", querymap);
		procedureblock.addRows(procedurelist);
		inInfo.setBlock(procedureblock);
		 
		return inInfo;
	}

	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		  
		String oprationType = (String) inInfo.get("oprationType");
        if(StringUtils.isBlank(oprationType)) {
        	outInfo.setMsg("排口参数错误");
			return outInfo;
        }
        
		EiBlock queryblock = new EiBlock(EiConstant.queryBlock);
		EiBlockMeta metadata = new EiBlockMeta();		 
	    EiColumn eiColumn = new EiColumn("dischargeportid");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("dischargeportcode");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("dischargeportname");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("dischargeClassify");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("isformal");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("departid");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("procid");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("monitorid");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("signform");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("dischargemode");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("longitude");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("latitude");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("position");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("executionstandard");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("setright");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("importantport");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("ismap");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("description");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("outto");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("outtype");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("outrule");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("exhaustheight");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("exhaustinside");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("portTemperature");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("countrypoint");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("citypoint");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("companypoint");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("cleanpoint");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("status");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("oprationType");
	    metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("controlPoint");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("dischargeStatus");
		metadata.addMeta(eiColumn);
	    queryblock.setBlockMeta(metadata);  
        
	    Map<String,String> row = new HashMap<String,String>();
    	
        if("insert".equals(oprationType)) {
        	row.put("oprationType", "insert");
        	row.put("dischargeportid", "");
		    row.put("dischargeportcode", "");
		    row.put("dischargeportname", "");
		    row.put("isformal", "");
		    row.put("departid", "");
		    row.put("procid", "");
		    row.put("monitorid", "");
		    row.put("signform", "");
		    row.put("dischargemode", "");
		    row.put("longitude", "");
		    row.put("latitude", "");
		    row.put("position", "");
		    row.put("executionstandard", "");
		    row.put("setright", "");
		    row.put("importantport", "");
		    row.put("ismap", "");
		    row.put("description", "");
		    row.put("outto", "");
		    row.put("outtype", "");
		    row.put("outrule", "");
		    row.put("exhaustheight", "");
		    row.put("exhaustinside", "");
		    row.put("countrypoint", "");
		    row.put("citypoint", "");
		    row.put("companypoint", "");
		    row.put("cleanpoint", "");
		    row.put("status", "");
			row.put("controlPoint","");
			row.put("dischargeStatus","");
        }else if("update".equals(oprationType)) {
        	row.put("oprationType", "update");
    		String dischargeportid = StringUtils.isBlank((String)inInfo.get("dischargeportid")) ? "空" : (String)inInfo.get("dischargeportid");
    		Map querymap = new HashMap();
    		querymap.put("dischargeportid", dischargeportid);
    	    List list = this.dao.query("tduhb05.query", querymap);
    		if(list.size() > 0) {
    			Tduhb05 tduhb05 = (Tduhb05)list.get(0);
    			row.put("dischargeportid", tduhb05.getDischargeportid());
    		    row.put("dischargeportcode", tduhb05.getDischargeportcode());
    		    row.put("dischargeportname", tduhb05.getDischargeportname());
    		    row.put("dischargeClassify", tduhb05.getDischargeClassify());
    		    row.put("isformal", tduhb05.getIsformal());
    		    row.put("departid", tduhb05.getDepartid());
    		    row.put("procid", tduhb05.getProcid());
    		    row.put("monitorid", tduhb05.getMonitorid());
    		    row.put("signform", tduhb05.getSignform());
    		    row.put("dischargemode", tduhb05.getDischargemode());
    		    row.put("longitude", tduhb05.getLongitude());
    		    row.put("latitude", tduhb05.getLatitude());
    		    row.put("position", tduhb05.getPosition());
    		    row.put("executionstandard", tduhb05.getExecutionstandard());
    		    row.put("setright", tduhb05.getSetright());
    		    row.put("importantport", tduhb05.getImportantport());
    		    row.put("ismap", tduhb05.getIsmap());
    		    row.put("description", tduhb05.getDescription());
    		    row.put("outto", tduhb05.getOutto());
    		    row.put("outtype", tduhb05.getOuttype());
    		    row.put("outrule", tduhb05.getOutrule());
    		    row.put("exhaustheight", tduhb05.getExhaustheight());
    		    row.put("exhaustinside", tduhb05.getExhaustinside());
    		    row.put("portTemperature", tduhb05.getPortTemperature());
    		    row.put("countrypoint", tduhb05.getCountrypoint());
    		    row.put("citypoint", tduhb05.getCitypoint());
    		    row.put("companypoint", tduhb05.getCompanypoint());
    		    row.put("cleanpoint", tduhb05.getCleanpoint());
    		    row.put("status", tduhb05.getStatus());
				row.put("controlPoint",tduhb05.getControlPoint());
				row.put("dischargeStatus",tduhb05.getDischargeStatus());
    		}
        }
        queryblock.addRow(row);
		outInfo.setBlock(queryblock);
		
		//添加厂部
		EiBlock departblock = new EiBlock("departList");
		departblock.setBlockMeta(new Tduhb01().eiMetadata);
		outInfo.setBlock(departblock);
	      
	    //添加工序
		EiBlock procedureblock = new EiBlock("procedureList");
		procedureblock.setBlockMeta(new Tduhb01().eiMetadata);
		outInfo.setBlock(procedureblock);
			
			
		//添加类别
		EiBlock monitorblock = new EiBlock("monitorList");
		monitorblock.setBlockMeta((new Tduhb08()).eiMetadata);
		outInfo.setBlock(monitorblock);
			
		//添加类别
		metadata = new EiBlockMeta();
	    eiColumn = new EiColumn("formalid");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("formalname");
	    metadata.addMeta(eiColumn);
	    EiBlock formalblock = new EiBlock("formalList");
	    formalblock.setBlockMeta(metadata);
	    outInfo.setBlock(formalblock);
		
		//添加标志
	    metadata = new EiBlockMeta();
	    eiColumn = new EiColumn("flagid");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("flagname");
	    metadata.addMeta(eiColumn);
	    EiBlock flagblock = new EiBlock("flagList");
	    flagblock.setBlockMeta(metadata);
	    outInfo.setBlock(flagblock);

		outInfo.setMsg("页面加载完成");
		return outInfo;
	}
	

	//根据厂部切换工序
    public EiInfo queryProcedureByDepart(EiInfo inInfo) {

    	String departid = StringUtils.isBlank((String)inInfo.get("departid")) ? "空" : (String)inInfo.get("departid");
    	//添加工序
		EiBlock procedureblock = inInfo.getBlock("procedureList");
		if(procedureblock == null) {
			procedureblock = new EiBlock("procedureList");
			  procedureblock.setBlockMeta(new Tduhb01().eiMetadata);
		}
		Map querymap = new HashMap();
		querymap.put("parentid", departid);
		querymap.put("type", "P1");
		List procedurelist = this.dao.query("tduhb01.query", querymap);
		procedureblock.addRows(procedurelist);
		inInfo.setBlock(procedureblock);
        
		return inInfo;
	}
	
	/**
	 * 保存排口信息
	 * @param inInfo
	 * @return
	 */
    public EiInfo save(EiInfo inInfo) {
		
    	StringBuffer buffer = new StringBuffer();
		String currentUser = String.valueOf(UserSession.getLoginName());
	    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Dao dao = this.getDao();
		try
        {
			String oprationType = (String)inInfo.get("inqu_status-0-oprationType");
			String dischargeportid = (String)inInfo.get("inqu_status-0-dischargeportid");
			String dischargeportcode = (String)inInfo.get("inqu_status-0-dischargeportcode");
			String dischargeportname = (String)inInfo.get("inqu_status-0-dischargeportname");
			String dischargeClassify = (String)inInfo.get("inqu_status-0-dischargeClassify");
			String isformal = (String)inInfo.get("inqu_status-0-isformal");
			String departid = (String)inInfo.get("inqu_status-0-departid");
			String procid = (String)inInfo.get("inqu_status-0-procid");
			String monitorid = (String)inInfo.get("inqu_status-0-monitorid");
			String signform = (String)inInfo.get("inqu_status-0-signform");
			String dischargemode = (String)inInfo.get("inqu_status-0-dischargemode");
			String longitude = (String)inInfo.get("inqu_status-0-longitude");
			String latitude = (String)inInfo.get("inqu_status-0-latitude");
			String position = (String)inInfo.get("inqu_status-0-position");
			String executionstandard = (String)inInfo.get("inqu_status-0-executionstandard");
			String setright = (String)inInfo.get("inqu_status-0-setright");
			String importantport = (String)inInfo.get("inqu_status-0-importantport");
			String ismap = (String)inInfo.get("inqu_status-0-ismap");
			String description = (String)inInfo.get("inqu_status-0-description");
			String outto = (String)inInfo.get("inqu_status-0-outto");
			String outtype = (String)inInfo.get("inqu_status-0-outtype");
			String outrule = (String)inInfo.get("inqu_status-0-outrule");
			String exhaustheight = (String)inInfo.get("inqu_status-0-exhaustheight");
			String exhaustinside = (String)inInfo.get("inqu_status-0-exhaustinside");
			String portTemperature = (String)inInfo.get("inqu_status-0-portTemperature");
			
			String countrypoint = (String)inInfo.get("inqu_status-0-countrypoint");
			String citypoint = (String)inInfo.get("inqu_status-0-citypoint");
			String companypoint = (String)inInfo.get("inqu_status-0-companypoint");
			String cleanpoint = (String)inInfo.get("inqu_status-0-cleanpoint");

			String controlPoint = (String)inInfo.get("inqu_status-0-controlPoint");
			String dischargeStatus = (String)inInfo.get("inqu_status-0-dischargeStatus");

			String status = (String)inInfo.get("inqu_status-0-status");
			Map<String, String> map = new HashMap<String, String>();
									
		    map.put("dischargeportcode", dischargeportcode);
		    map.put("dischargeportname", dischargeportname);
		    map.put("dischargeClassify", dischargeClassify);
		    map.put("isformal", isformal);
		    map.put("departid", departid);
		    map.put("procid", procid);
		    map.put("monitorid", monitorid);
		    map.put("signform", signform);
		    map.put("dischargemode", dischargemode);
		    map.put("longitude", longitude);
		    map.put("latitude", latitude);
		    map.put("position", position);
		    map.put("executionstandard", executionstandard);
		    map.put("setright", setright);
		    map.put("importantport", importantport);
		    map.put("ismap", ismap);
		    map.put("description", description);
		    map.put("outto", outto);
		    map.put("outtype", outtype);
		    map.put("outrule", outrule);
		    map.put("exhaustheight", exhaustheight);
		    map.put("exhaustinside", exhaustinside);
		    map.put("portTemperature", portTemperature);
			map.put("countrypoint", countrypoint);
			map.put("citypoint", "");
			map.put("companypoint", companypoint);
			map.put("cleanpoint", "");
		    map.put("status", status);
		    map.put("controlPoint", controlPoint);
		    map.put("dischargeStatus", dischargeStatus);
			if("insert".equals(oprationType)) {
				String temdischargeportid = getDischargePortId();

				map.put("dischargeportid", temdischargeportid);
				map.put("creator", currentUser);
				map.put("createdTime", dateTimeFormat.format(new Date()));
				dao.insert( "tduhb05.insert", map);
				buffer.insert(0, "记录保存成功\n");
				Map<String, String> sitemap = new HashMap<String, String>();
			    map.put("sitename", dischargeportname);
			    map.put("departid", departid);
			    map.put("procid", procid);
			    map.put("portid", temdischargeportid);
			    map.put("isartificial", "1");
			    map.put("isonline", "0");
			    map.put("classifyid", monitorid);
			    map.put("monitorid", monitorid);
			    map.put("mnid", "");
			    map.put("description", "");
//			    map.put("sort", getMaxSiteSort());
			    map.put("status", "1");
				map.put("siteid", getSiteId());
				map.put("creator", currentUser);
				map.put("createdTime", dateTimeFormat.format(new Date()));
				dao.insert( "tduhb07.insert", map);
			}else if("update".equals(oprationType)) {
				if (StrUtil.paramIsNotNullOrEmpty(dischargeportid)) {
					String oldDepartid = "";
					String oldProcid = "";
					Map<String, String> oldPMap = new HashMap<>();
					oldPMap.put("dischargeportid", dischargeportid);
			    	List<Tduhb05> list = this.dao.query("tduhb05.query",oldPMap);
			    	if (StrUtil.listIsNotNullOrEmpty(list)) {
						oldDepartid = list.get(0).getDepartid();
						oldProcid = list.get(0).getProcid();
			    	}
					
					/*Map<String, String> pMap = new HashMap<>();
					pMap.put("dischargeportid", dischargeportid);
					List<Tduhb05> uList = this.dao.query("tduhb05.query",pMap);
					if (StrUtil.listIsNotNullOrEmpty(uList)) {
						Tduhb05 tduhb05 = uList.get(0);
						countrypoint = StrUtil.isNullToStr(tduhb05.getCountrypoint());
						citypoint = StrUtil.isNullToStr(tduhb05.getCitypoint());
						companypoint = StrUtil.isNullToStr(tduhb05.getCompanypoint());
						cleanpoint = StrUtil.isNullToStr(tduhb05.getCleanpoint());
					}
				    map.put("countrypoint", countrypoint);
				    map.put("citypoint", citypoint);
				    map.put("companypoint",companypoint);
				    map.put("cleanpoint", cleanpoint);*/
					
					map.put("dischargeportid", dischargeportid);
					map.put("modifier", currentUser);
					map.put("updatedTime", dateTimeFormat.format(new Date()));
		        	dao.update( "tduhb05.updatePortInfo", map);
		        	//同步修改厂部信息
		        	updateDepAndProByPortid(departid , procid , oldDepartid, oldProcid, dischargeportid);
		        	buffer.insert(0, "记录保存成功\n");
				}else {
					inInfo.setStatus(-1);
					inInfo.setMsg("系统参数错误");
					return inInfo;
				}
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
	
    private String getDischargePortId () throws Exception
	{
//    	String  dischargeportid ="DCP";
//        List list = this.dao.query("tduhb05.queryId", new HashMap<String, String>());
//        int count = 1;
//        if(list != null && list.size() > 0) {
//        	count = Integer.parseInt(((Tduhb05)list.get(0)).getDischargeportid().substring(3)) + 1;
//        }
//		return dischargeportid + String.format("%07d", count);
		String dischargeportid = "";
		List list = this.dao.query("tduhb05.selectMaxId", new HashMap<>());
		dischargeportid = (String)list.get(0);
		int teamId = Integer.parseInt(dischargeportid.substring(3));
		dischargeportid = "DCP"+String.format("%07d",teamId+1);
		return dischargeportid;
	}
    
    private String getSiteId () throws Exception
	{
//    	String  dischargeportid ="SI";
//        List list = this.dao.query("tduhb07.queryId", new HashMap<String, String>());
//        int count = 1;
//        if(list != null && list.size() > 0) {
//        	count = Integer.parseInt(((Tduhb07)list.get(0)).getSiteid().substring(2)) + 1;
//        }
//		return dischargeportid + String.format("%08d", count);
		String dischargeportid = "";
		List list = this.dao.query("tduhb07.selectMaxId", new HashMap<>());
		dischargeportid = (String)list.get(0);
		int teamId = Integer.parseInt(dischargeportid.substring(2));
		dischargeportid = "SI"+String.format("%08d",teamId+1);
		return dischargeportid;

	}
    
    /**
     * 获取当前监测站点列表中最大的排序值
     * @return
     */
    private String getMaxSiteSort() {
    	String maxSort = "1";
    	List<Tduhb07> list = this.dao.query("tduhb07.queryBySortDesc",new HashMap<>());
    	if (StrUtil.listIsNotNullOrEmpty(list)) {
			String sortStr = list.get(0).getSort();
			if (StrUtil.paramIsNotNullOrEmpty(sortStr)) {
				int maxVal = Integer.parseInt(sortStr) + 1;
				maxSort = String.valueOf(maxVal);
			}
		}
    	return maxSort;
    }
    
    /**
     * //当排口信息修改完成的时候，根据前台传递过来的厂部id，工序id，以及由当前排口id得到的未修改前的排口id做比对，
     * //当排口对应的厂部和工序id，发生改变的时候，同步修改与排口信息相关的内容
     *  //当前同步修改的内容有，1.监测站点厂部、工序信息；2.临时计划对应信息修改
     * @param departid
     * @param procid
     * @param portid
     */
    public  void updateDepAndProByPortid(String departid,String procid,String oldDepartid,String oldProcid,String dischargeportid) {
		//首先根据排口id查询旧的厂部、工序id，判断厂部、工序是否发生改变
		if ( (!departid.equals(oldDepartid)) || (!procid.equals(oldProcid)) ){
			//修改对应的监测站点厂部，工序id
			Map<String, String> oldMap = new HashMap<>();
			oldMap.put("portid", dischargeportid);
			oldMap.put("departid", departid);
			oldMap.put("procid", procid);
			this.dao.update("tduhb07.updateDepAndProByPortid",oldMap);
			
			//修改临时计划排口对应的工序信息
//			Map<String, String> oldMap2 =  new HashMap<>();
//			oldMap2.put("dischargeid", dischargeportid);
//			oldMap2.put("produceid", procid);
//			this.dao.update("tduhc2604.updateProduceByPortid",oldMap2);
		}
    	
	}
    
    
}
