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
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.sggk.common.du.domain.Tduhb18;
import com.baosight.sggk.common.du.domain.Tduhb20;
import com.baosight.sggk.util.StrUtil;


public class ServiceDUHB1802 extends ServiceBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHB1802.class);

	public EiInfo initLoad(EiInfo inInfo) {
		StringBuffer buffer = new StringBuffer();
		String processid = inInfo.getString("processid");
		inInfo.set("inqu_status-0-processid", processid);
		inInfo = query(inInfo);
		return inInfo;
	}
	
	
	public EiInfo query(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		StringBuffer buffer = new StringBuffer();
		String envdeviceid = StrUtil.isNullToStr(inInfo.getString("envdeviceid"));
		if ("".equals(envdeviceid)) {
			outInfo.setStatus(-1);
			outInfo.setMsg("系统参数错误");
			return outInfo;
		}
		
		EiBlock facilityBlock = outInfo.addBlock("result");
		facilityBlock.setBlockMeta((new Tduhb18()).eiMetadata);
		
		Map<String, String> enidMap = new HashMap<>();
		enidMap.put("envdeviceid", envdeviceid);
		List<Tduhb18> listhb18 = this.dao.query("tduhb18.query",enidMap);
		if (StrUtil.listIsNotNullOrEmpty(listhb18)) {
			String processid = listhb18.get(0).getProcessid();
			if (StrUtil.paramIsNotNullOrEmpty(processid)) {
				List<String> facilityidList = new ArrayList<>(Arrays.asList(processid.split(",")));
				if (StrUtil.listIsNotNullOrEmpty(facilityidList)) {
					for (String facilityid : facilityidList) {
						Map<String, String> facidMap = new HashMap<>();
						facidMap.put("facilityid", facilityid);
						List<Tduhb20> list20 = this.dao.query("tduhb20.query",facidMap);
						if (StrUtil.listIsNotNullOrEmpty(list20)) {
							facilityBlock.addRow(list20.get(0));
						}
					}
				}
			}
		}
			
		return outInfo;
	}
	
	
	
	
	// 根据装置id查询设施信息
	public Tduhb20 getByFacilitycode(String facilitycode) {
		Tduhb20 tduhb20 = null;
		Map<String, String> map = new HashMap<String, String>();
		if (!StringUtils.isBlank(facilitycode)) {
			map.put("facilitycode", facilitycode);
			List<Tduhb20> list = this.dao.query("tduhb20.query", map);
			if (list.size() > 0 && list != null) {
				tduhb20 = list.get(0);
			}
		}
		return tduhb20;
	}

	public EiInfo loadMsg(EiInfo inInfo) {
		String facilityid = StrUtil.isNullToStr(inInfo.getString("facilityid"));
		if ("".equals(facilityid)) {
			inInfo.setStatus(-1);
			inInfo.setMsg("系统参数错误！");
			return inInfo;
		}
		
	    Map querymap = new HashMap();
		querymap.put("facilityid", facilityid);
	    List list = this.dao.query("tduhb20.query", querymap);
		if(list.size() > 0) {
			Tduhb20 tduhb20 = (Tduhb20)list.get(0);
			inInfo.set("facilityid", tduhb20.getFacilityid());
			inInfo.set("facilitycode", tduhb20.getFacilitycode());
			String val = tduhb20.getFacilityname().replace(",","，");
			inInfo.set("facilityname", val);
			inInfo.set("facilitytype", tduhb20.getFacilitytype());
			inInfo.set("runtime", tduhb20.getRuntime());
			inInfo.set("pollutantname", tduhb20.getPollutantname());
			inInfo.set("handability", tduhb20.getHandability());
			inInfo.set("handabilityAct", tduhb20.getHandabilityAct());
			inInfo.set("devicearea", tduhb20.getDevicearea());
			inInfo.set("deviceflow", tduhb20.getDeviceflow());
			inInfo.set("isexecute", tduhb20.getIsexecute());
			inInfo.set("remark", tduhb20.getRemark());
			inInfo.set("devicecode", getEnvdevicCodeById(tduhb20.getDevicecode()));
			inInfo.set("devicenames", getEnvdevicNameById(tduhb20.getDevicecode()));
			inInfo.set("situation", tduhb20.getSituation());
			inInfo.set("devicemodel", tduhb20.getDevicemodel());
			inInfo.set("motormodel", tduhb20.getMotormodel());
			inInfo.set("mark", tduhb20.getMark());
			inInfo.set("craft", tduhb20.getCraft());
			inInfo.set("filter", tduhb20.getFilter());
			inInfo.set("departmentname", tduhb20.getDepartmentname());
			inInfo.set("procedurename", tduhb20.getProcedurename());
			inInfo.set("oprationType", "update");
			
			inInfo.set("status", 1);
			inInfo.set("message", "加载成功！");
		}else {
			inInfo.set("status", 2);
			inInfo.set("message", "未查询到相关内容！");
		}
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
			String facilityid = (String)inInfo.get("inqu_status-0-facilityid");
			String facilitycode = (String)inInfo.get("inqu_status-0-facilitycode");
			String facilityname = (String)inInfo.get("inqu_status-0-facilityname");
			String pollutantname = (String)inInfo.get("inqu_status-0-pollutantname");
			String facilitytype = (String)inInfo.get("inqu_status-0-facilitytype");
			String numbers = (String)inInfo.get("inqu_status-0-numbers");
			String portid = (String)inInfo.get("inqu_status-0-portid");
			String portname = (String)inInfo.get("inqu_status-0-portname");
			String handability = (String)inInfo.get("inqu_status-0-handability");
			String runtime = (String)inInfo.get("inqu_status-0-runtime");
			String devicecode = (String)inInfo.get("inqu_status-0-devicecode");
			String devicemodel = (String)inInfo.get("inqu_status-0-devicemodel");
			String motormodel = (String)inInfo.get("inqu_status-0-motormodel");
			String controllermodel = (String)inInfo.get("inqu_status-0-controllermodel");
			String situation = (String)inInfo.get("inqu_status-0-situation");
			String outstandard = (String)inInfo.get("inqu_status-0-outstandard");
			String isexecute = (String)inInfo.get("inqu_status-0-isexecute");
			String remark = (String)inInfo.get("inqu_status-0-remark");
			String facilitydepart = (String)inInfo.get("inqu_status-0-facilitydepart");
			Map<String, String> map = new HashMap<String, String>();
									
		    map.put("facilitycode", facilitycode);
		    map.put("facilityname", facilityname);
		    map.put("pollutantname", pollutantname);
		    map.put("facilitytype", facilitytype);
		    map.put("origin", "");
		    map.put("handmethod", "");
		    map.put("numbers", numbers);
		    map.put("handfactors", "");
		    map.put("portid", portid);
		    map.put("portname", portname);
		    map.put("handability", handability);
		    map.put("shandnum", "");
		    map.put("runtime", runtime);
		    map.put("handfactors", "");
		    map.put("devicecode", devicecode);
		    map.put("devicemodel", devicemodel);
		    map.put("motormodel", motormodel);
		    map.put("controllermodel", controllermodel);
		    map.put("situation", situation);
		    map.put("outstandard", outstandard);
		    map.put("isexecute", isexecute);
		    map.put("remark", remark);
		    map.put("facilitydepart", facilitydepart);
		    map.put("type", "01");
		    map.put("status", "1");
			if("insert".equals(oprationType)) {
				map.put("facilityid", getFacilityId());
				map.put("creator", currentUser);
				map.put("createdTime", dateTimeFormat.format(new Date()));
				dao.insert( "tduhb20.insert", map);
				buffer.insert(0, "记录保存成功\n");
			}else if("update".equals(oprationType)) {
				map.put("facilityid", facilityid);
				map.put("modifier", currentUser);
				map.put("updatedTime", dateTimeFormat.format(new Date()));
	        	dao.update( "tduhb20.update", map);
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
	
    private String getFacilityId () throws Exception
	{
    	String  facilityid ="FI";
        List list = this.dao.query("tduhb20.queryId", new HashMap<String, String>());
        int count = 1;
        if(list != null && list.size() > 0) {
        	count = Integer.parseInt(((Tduhb20)list.get(0)).getFacilityid().substring(2)) + 1;
        }
		return facilityid + String.format("%06d", count);
	}
	
    
	/**
	 * 将产排污设施id,转换为编码
	 * @param devicecode，多个id在一起的字符串
	 * @return
	 */
	public  String getEnvdevicCodeById(String devicecode) {
		List<String> codeList = new ArrayList<>();
		if (StrUtil.paramIsNotNullOrEmpty(devicecode)) {
			String[] envdeviceidArr = devicecode.split(",");
			for (int j = 0; j < envdeviceidArr.length; j++) {
				//根据产排污设备id，查询对应的设备编码
				Map<String, String> pMap = new HashMap<>();
				pMap.put("envdeviceid", envdeviceidArr[j]);
				List<Tduhb18> list = this.dao.query("tduhb18.query",pMap);
				if (StrUtil.listIsNotNullOrEmpty(list)) {
					String code = list.get(0).getDeviceid();
					codeList.add(code);
				}
				
			}
		}
		
		if (StrUtil.listIsNotNullOrEmpty(codeList)) {
			return StringUtils.join(codeList, ",");
		}else {
			return "";
		}
	}
	
	
	/**
	 * 将产排污设施id,转换为名称
	 * @param devicecode，多个产污设施名称，拼在一起的字符串
	 * @return
	 */
	public  String getEnvdevicNameById(String devicecode) {
		List<String> nameList = new ArrayList<>();
		if (StrUtil.paramIsNotNullOrEmpty(devicecode)) {
			String[] envdeviceidArr = devicecode.split(",");
			for (int j = 0; j < envdeviceidArr.length; j++) {
				//根据产排污设备id，查询对应的设备编码
				Map<String, String> pMap = new HashMap<>();
				pMap.put("envdeviceid", envdeviceidArr[j]);
				List<Tduhb18> list = this.dao.query("tduhb18.query",pMap);
				if (StrUtil.listIsNotNullOrEmpty(list)) {
					String name = list.get(0).getDevicename();
					nameList.add(name);
				}
				
			}
		}
		
		if (StrUtil.listIsNotNullOrEmpty(nameList)) {
			return StringUtils.join(nameList, ",");
		}else {
			return "";
		}
	}

	
}
