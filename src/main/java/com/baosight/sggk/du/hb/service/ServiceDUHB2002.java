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


public class ServiceDUHB2002 extends ServiceBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHB2002.class);

	public EiInfo initLoad(EiInfo inInfo) {
		
		String facilityid = StrUtil.paramIsNotNullOrEmpty(inInfo.getString("facilityid")) ?  inInfo.getString("facilityid") : "";
		if ("".equals(facilityid)) {
			inInfo.setStatus(-1);
			return inInfo;
		}
		inInfo.set("inqu_status-0-facilityid", facilityid);
		inInfo = query(inInfo);
		
		return inInfo;
	}
	
	
	public EiInfo query(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		String facilityid = StrUtil.paramIsNotNullOrEmpty(inInfo.getString("inqu_status-0-facilityid")) ?  inInfo.getString("inqu_status-0-facilityid") : "";
		if ("".equals(facilityid)) {
			outInfo.setStatus(-1);
			return outInfo;
		}
		outInfo.set("inqu_status-0-facilityid", facilityid);
		
		List<Tduhb18> deviceList = new ArrayList<>();
		//根据环保治理设施id，去设施表中查询对应的治理设施id字符串
		Map<String, String> pMap = new HashMap<>();
		pMap.put("facilityid", facilityid);
		List<Tduhb20> listhb20 = this.dao.query("tduhb20.query",pMap);
		if (StrUtil.listIsNotNullOrEmpty(listhb20)) {
			String devicecode = listhb20.get(0).getDevicecode();
			if (StrUtil.paramIsNotNullOrEmpty(devicecode)) {
				String[] envdeviceidArr = devicecode.split(",");
				for (int i = 0; i < envdeviceidArr.length; i++) {
					Map<String, String> fMap = new HashMap<>();
					fMap.put("envdeviceid", envdeviceidArr[i]);
					List<Tduhb18> listhb18 = this.dao.query("tduhb18.query",fMap,0,-999999);
					if (StrUtil.listIsNotNullOrEmpty(listhb18)) {
						deviceList.add(listhb18.get(0));
					}
				}
			}	
		}
		
		//添加产排污设施的列表信息
		EiBlock envdevicBlock = outInfo.addBlock("envdevic");
		envdevicBlock.addBlockMeta(new Tduhb18().eiMetadata);
		envdevicBlock.addRows(deviceList);
		
		
		//默认显示列表第一条数据信息
		if (StrUtil.listIsNotNullOrEmpty(deviceList)) {
			Tduhb18 tduhb18 = deviceList.get(0);
			String envdeviceid = tduhb18.getEnvdeviceid();
			String deviceid = tduhb18.getDeviceid();
			String devicename = tduhb18.getDevicename();
			String sourcename = tduhb18.getSourcename();
			String outtype = tduhb18.getOuttype();
			String factorname = tduhb18.getFactorname();
			
			outInfo.set("inqu_status-0-envdeviceid", envdeviceid);
			outInfo.set("inqu_status-0-deviceid", deviceid);
			outInfo.set("inqu_status-0-devicename", devicename);
			outInfo.set("inqu_status-0-sourcename", sourcename);
			outInfo.set("inqu_status-0-outtype", outtype);
			outInfo.set("inqu_status-0-factorname", factorname);
			
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
		String envdeviceid = inInfo.getString("envdeviceid");
	    Map querymap = new HashMap();
		querymap.put("envdeviceid", envdeviceid);
	    List<Tduhb18> list = this.dao.query("tduhb18.query", querymap);
	    if (StrUtil.listIsNotNullOrEmpty(list)) {
			Tduhb18 tduhb18 = list.get(0);
			inInfo.set("deviceid", tduhb18.getDeviceid());
			inInfo.set("devicename", tduhb18.getDevicename());
			inInfo.set("sourcename", tduhb18.getSourcename());
			inInfo.set("outtype", tduhb18.getOuttype());
			inInfo.set("factorname", tduhb18.getFactorname());
			inInfo.set("portid", tduhb18.getPortid());
			inInfo.set("portname", tduhb18.getPortname());
			inInfo.set("other", tduhb18.getOther());
	    	
	    	inInfo.setStatus(1);
	    	inInfo.setMsg("设备信息加载成功");
		}else {
			inInfo.setStatus(2);
			inInfo.setMsg("未查询到相关内容！");
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
     *  /移除治理设施和环保设施之间的关联
     * @param inInfo
     * @return
     */
    public EiInfo removeConection(EiInfo inInfo) {
    	String envdeviceid = StrUtil.isNullToStr(inInfo.getString("envdeviceid"));
    	String facilityid = StrUtil.isNullToStr(inInfo.getString("facilityid"));
    	if ("".equals(envdeviceid) || "".equals(facilityid)) {
			inInfo.setStatus(-2);
			return inInfo;
		}
    	
    	//首先移除环保设施表中，devicecode字段里的对应的envdeviceid
    	//aa,bb,cc,dd,ee,ff
    	Map<String, String> pMap = new HashMap<>();
    	pMap.put("facilityid", facilityid);
    	List<Tduhb20> listhb20 = this.dao.query("tduhb20.query",pMap);
    	if (StrUtil.listIsNotNullOrEmpty(listhb20)) {
			String devicecode = listhb20.get(0).getDevicecode();
			if (StrUtil.paramIsNotNullOrEmpty(devicecode)) {
				List<String> envdevicidList = new ArrayList<>(Arrays.asList(devicecode.split(",")));
				if (StrUtil.listIsNotNullOrEmpty(envdevicidList) && envdevicidList.contains(envdeviceid)) {
					envdevicidList.remove(envdeviceid);
					devicecode = StringUtils.join(envdevicidList, ",");
					pMap.put("devicecode", devicecode);
					this.dao.update("tduhb20.updateEnvdevicInfo" , pMap);
					
					//去产污设备表中，将对应的环保设施移除
					removeFacilityConection(envdeviceid,facilityid);
					inInfo.setStatus(1);
				}
			}
		}
    	return inInfo;
    }
    
    
    public void removeFacilityConection(String envdeviceid,String facilityid) {
    	Map<String, String> pMap = new HashMap<>();
    	pMap.put("envdeviceid", envdeviceid);
    	List<Tduhb18> list = this.dao.query("tduhb18.query",pMap);
    	if (StrUtil.listIsNotNullOrEmpty(list)) {
			String processid = list.get(0).getProcessid();
			if (StrUtil.paramIsNotNullOrEmpty(processid)) {
				List<String> facilityidList = new ArrayList<>(Arrays.asList(processid.split(",")));
				if (StrUtil.listIsNotNullOrEmpty(facilityidList) && facilityidList.contains(facilityid)) {
					facilityidList.remove(facilityid);
					processid = StringUtils.join(facilityidList, ",");
					pMap.put("processid", processid);
					this.dao.update("tduhb18.updateProcessidInfo",pMap);
				}
			}
		}
    	
    	
    	
    }
	

	
}
