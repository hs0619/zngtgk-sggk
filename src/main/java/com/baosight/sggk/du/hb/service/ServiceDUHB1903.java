package com.baosight.sggk.du.hb.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.sggk.common.du.domain.Tduhb01;
import com.baosight.sggk.common.du.domain.Tduhb05;
import com.baosight.sggk.common.du.domain.Tduhb07;
import com.baosight.sggk.common.du.domain.Tduhb08;
import com.baosight.sggk.common.du.domain.Tduhb18;
import com.baosight.sggk.common.du.domain.Tduhb21;
import com.baosight.sggk.util.StrUtil;

public class ServiceDUHB1903 extends ServiceBase {
	
	private static final Logger logger = Logger.getLogger(ServiceDUHB1903.class);

	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		String envdeviceid = StrUtil.isNullToStr(inInfo.getString("envdeviceid"));
		if ("".equals(envdeviceid)) {
			outInfo.setStatus(-1);
			outInfo.setMsg("系统参数错误");
			return outInfo;
		}
		
		EiBlock facilityBlock = outInfo.addBlock("result");
		facilityBlock.setBlockMeta((new Tduhb05()).eiMetadata);
		
		Map<String, String> enidMap = new HashMap<>();
		enidMap.put("envdeviceid", envdeviceid);
		List<Tduhb18> listhb18 = this.dao.query("tduhb18.query",enidMap);
		if (StrUtil.listIsNotNullOrEmpty(listhb18)) {
			String processid = listhb18.get(0).getProcessid();
			if (StrUtil.paramIsNotNullOrEmpty(processid)) {
				List<String> portidList = new ArrayList<>();
				List<String> facilityidList = new ArrayList<>(Arrays.asList(processid.split(",")));
				if (StrUtil.listIsNotNullOrEmpty(facilityidList)) {
					for (String facilityid : facilityidList) {
						Map<String, String> facidMap = new HashMap<>();
						facidMap.put("facilityid", facilityid);
						//根据设施id，查询排口信息
						List<Tduhb21> list21 = this.dao.query("tduhb21.query",facidMap);
						if (StrUtil.listIsNotNullOrEmpty(list21)) {
							for (int j = 0; j < list21.size(); j++) {
								String portid = list21.get(j).getDischargeportid();
								if (!portidList.contains(portid)) {
									portidList.add(portid);
								}
							}
						}
					}
				}
				
				if (StrUtil.listIsNotNullOrEmpty(portidList)) {
					for (String dischargeportid: portidList) {
						Map<String, String> portidMap = new HashMap<>();
						portidMap.put("dischargeportid", dischargeportid);
						List<Tduhb05> list05 = this.dao.query("tduhb05.query",portidMap);
						if (StrUtil.listIsNotNullOrEmpty(list05)) {
							facilityBlock.addRow(list05.get(0));
						}
					}
				}
			}
		}
	
		return outInfo;
	}
	// 根据装置id查询设施信息
		public Tduhb05 getByDischargeportcode(String dischargeportcode) {
			Tduhb05 tduhb05 = null;
			Map<String, String> map = new HashMap<String, String>();
			if (!StringUtils.isBlank(dischargeportcode)) {
				map.put("dischargeportcode", dischargeportcode);
				List<Tduhb05> list = this.dao.query("tduhb05.query", map);
				if (list.size() > 0 && list != null) {
					tduhb05 = list.get(0);
				}
			}
			return tduhb05;
		}
	
	public EiInfo loadMsg(EiInfo inInfo) {
		String dischargeportid = StrUtil.isNullToStr(inInfo.getString("dischargeportid"));
		if ("".equals(dischargeportid)) {
			inInfo.setStatus(-1);
			inInfo.setMsg("系统参数错误！");
			return inInfo;
		}
		
	    Map querymap = new HashMap();
		querymap.put("dischargeportid", dischargeportid);
	    List list = this.dao.query("tduhb05.query", querymap);
		if(list.size() > 0) {
			Tduhb05 tduhb05 = (Tduhb05)list.get(0);
			inInfo.set("dischargeportcode", tduhb05.getDischargeportcode());
			String val = tduhb05.getDischargeportname().replace(",","，");
			inInfo.set("dischargeportname", val);
			
			inInfo.set("isformal", tduhb05.getIsformal());
			inInfo.set("status", tduhb05.getStatus());
			inInfo.set("monitorid", tduhb05.getMonitorid());
			inInfo.set("departid", tduhb05.getDepartid());
			inInfo.set("procid", tduhb05.getProcid());
			inInfo.set("position", tduhb05.getPosition());
			inInfo.set("longitude", tduhb05.getLongitude());
			inInfo.set("latitude", tduhb05.getLatitude());
			inInfo.set("dischargemode", tduhb05.getDischargemode());
			inInfo.set("outto", tduhb05.getOutto());
			inInfo.set("outtype", tduhb05.getOuttype());
			inInfo.set("outrule", tduhb05.getOutrule());
			inInfo.set("exhaustheight", tduhb05.getExhaustheight());
			inInfo.set("exhaustinside", tduhb05.getExhaustinside());
			inInfo.set("importantport", tduhb05.getImportantport());
			inInfo.set("setright", tduhb05.getSetright());
			inInfo.set("cleanpoint", tduhb05.getCleanpoint());
			inInfo.set("ismap", tduhb05.getIsmap());
			inInfo.set("countrypoint", tduhb05.getCountrypoint());
			inInfo.set("citypoint", tduhb05.getCitypoint());
			inInfo.set("companypoint", tduhb05.getCompanypoint());
			inInfo.set("signform", tduhb05.getSignform());
			inInfo.set("executionstandard", tduhb05.getExecutionstandard());
			inInfo.set("description", tduhb05.getDescription());
			inInfo.set("dischargeportid", tduhb05.getDischargeportid());
			inInfo.set("oprationType", "update");
			
			inInfo.set("dischargeClassify", tduhb05.getDischargeClassify());
			inInfo.set("portTemperature", tduhb05.getPortTemperature());
			
			//添加工序
			EiBlock procedureblock = inInfo.getBlock("procedureList");
			if(procedureblock == null) {
				procedureblock = new EiBlock("procedureList");
				  procedureblock.setBlockMeta(new Tduhb01().eiMetadata);
			}
			querymap = new HashMap();
			querymap.put("parentid", tduhb05.getDepartid());
			querymap.put("type", "P1");
			List procedurelist = this.dao.query("tduhb01.query", querymap);
			procedureblock.addRows(procedurelist);
			inInfo.setBlock(procedureblock);
			
			inInfo.set("status", 1);
			inInfo.set("message", "加载成功！");
		}else {
			inInfo.set("status", 2);
			inInfo.set("message", "未查询到相关内容！");
		}
//		queryblock.addRow(row);
//		inInfo.setBlock(queryblock);
		
		inInfo.setMsg("页面加载完成");
		return inInfo;
	}
public EiInfo initSelectData(EiInfo inInfo) {
		
		EiBlockMeta eiMetadata;		 
	    EiColumn eiColumn;
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
	  //添加厂部
	    EiBlock departblock = inInfo.getBlock("departList");
		if(departblock == null) {
			departblock = new EiBlock("departList");
			departblock.setBlockMeta(new Tduhb01().eiMetadata);
		}
		Map querymap = new HashMap();
		querymap.put("parentid", "root");
		List departlist = this.dao.query("tduhb01.query", querymap);
//		Map<String, Object> Map = new HashMap<>();
//		Map.put("departmentId", "%");
//		Map.put("departmentName", "请选择");
//		departlist.add(0, Map);
		departblock.addRows(departlist);
		inInfo.setBlock(departblock);
		//监测类型
		EiBlock monitorblock = inInfo.getBlock("monitorList");
		if(monitorblock == null) {
			monitorblock = new EiBlock("monitorList");
			monitorblock.setBlockMeta((new Tduhb08()).eiMetadata);
		}
		List monitorlist = this.dao.query("tduhb08.query", new HashMap());
//		Map<String, Object> Map1 = new HashMap<>();
//		Map1.put("monitorid", "%");
//		Map1.put("monitorname", "请选择");
//		monitorlist.add(0, Map1);
		monitorblock.setRows(monitorlist);
		inInfo.setBlock(monitorblock);
		//工序
	    EiBlock typeBlock = inInfo.addBlock("procedureList");
    	typeBlock.setBlockMeta((new Tduhb01()).eiMetadata);
    	String dischargeportid = StringUtils.isBlank((String)inInfo.get("dischargeportid")) ? "空" : (String)inInfo.get("dischargeportid");
    	Map procMap = new HashMap();
    	procMap.put("type", "P1");
		List list2 = this.dao.query("tduhb01.query", procMap);
//		Map<String, Object> typeMap = new HashMap<>();
//		typeMap.put("typeid", "%");
//		typeMap.put("typename", "请选择");
//		list2.add(0, typeMap);
		typeBlock.addRows(list2);
		
	    return inInfo;
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
	
	//保存因子
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
			String countrypoint = (String)inInfo.get("inqu_status-0-countrypoint");
			String citypoint = (String)inInfo.get("inqu_status-0-citypoint");
			String companypoint = (String)inInfo.get("inqu_status-0-companypoint");
			String cleanpoint = (String)inInfo.get("inqu_status-0-cleanpoint");
			String status = (String)inInfo.get("inqu_status-0-status");
			Map<String, String> map = new HashMap<String, String>();
									
		    map.put("dischargeportcode", dischargeportcode);
		    map.put("dischargeportname", dischargeportname);
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
		    map.put("countrypoint", countrypoint);
		    map.put("citypoint", citypoint);
		    map.put("companypoint", companypoint);
		    map.put("cleanpoint", cleanpoint);
		    map.put("status", status);
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
			    map.put("isartificial", "0");
			    map.put("isonline", "0");
			    map.put("classifyid", monitorid);
			    map.put("monitorid", monitorid);
			    map.put("mnid", "");
			    map.put("description", "");
			    map.put("sort", "");
			    map.put("status", "1");
				map.put("siteid", getSiteId());
				map.put("creator", currentUser);
				map.put("createdTime", dateTimeFormat.format(new Date()));
				dao.insert( "tduhb07.insert", map);
			}else if("update".equals(oprationType)) {
				map.put("dischargeportid", dischargeportid);
				map.put("modifier", currentUser);
				map.put("updatedTime", dateTimeFormat.format(new Date()));
	        	dao.update( "tduhb05.update", map);
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
	
    private String getDischargePortId () throws Exception
	{
    	String  dischargeportid ="DCP";
        List list = this.dao.query("tduhb05.queryId", new HashMap<String, String>());
        int count = 1;
        if(list != null && list.size() > 0) {
        	count = Integer.parseInt(((Tduhb05)list.get(0)).getDischargeportid().substring(3)) + 1;
        }
		return dischargeportid + String.format("%07d", count);
	}
    
    private String getSiteId () throws Exception
	{
    	String  dischargeportid ="SI";
        List list = this.dao.query("tduhb07.queryId", new HashMap<String, String>());
        int count = 1;
        if(list != null && list.size() > 0) {
        	count = Integer.parseInt(((Tduhb07)list.get(0)).getSiteid().substring(2)) + 1;
        }
		return dischargeportid + String.format("%08d", count);
	}
	
}
