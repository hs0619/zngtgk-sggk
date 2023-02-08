/**
 *
 */
package com.baosight.sggk.du.hb.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.baosight.sggk.common.du.domain.Tduhb18;
import com.baosight.sggk.common.du.domain.Tduhb20;
import com.baosight.sggk.util.StrUtil;


public class ServiceDUHB1901 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHB1901.class);

	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		  
		String oprationType = (String) inInfo.get("oprationType");
        if(StringUtils.isBlank(oprationType)) {
        	outInfo.setMsg("废水产排污节点参数错误");
			return outInfo;
        }
        
		EiBlock queryblock = new EiBlock(EiConstant.queryBlock);
		EiBlockMeta metadata = new EiBlockMeta();		 
	    EiColumn eiColumn = new EiColumn("envdeviceid");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("devicename");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("factorname");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("processid");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("processname");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("canuse");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("processinfo");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("outto");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("portouttype");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("outrule");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("portid");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("portname");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("isright");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("porttype");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("other");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("oprationType");
	    metadata.addMeta(eiColumn);
	    queryblock.setBlockMeta(metadata);  
        
	    Map<String,String> row = new HashMap<String,String>();
    	
        if("insert".equals(oprationType)) {
        	row.put("oprationType", "insert");
        	row.put("envdeviceid", "");
		    row.put("devicename", "");
		    row.put("factorname", "");
		    row.put("processid", "");
		    row.put("processname", "");
		    row.put("canuse", "");
		    row.put("processinfo", "");
		    row.put("outto", "");
		    row.put("portouttype", "");
		    row.put("outrule", "");
		    row.put("portid", "");
		    row.put("portname", "");
		    row.put("isright", "");
		    row.put("porttype", "");
		    row.put("other", "");
        }else if("update".equals(oprationType)) {
        	row.put("oprationType", "update");
        	String envdeviceid = StringUtils.isBlank((String)inInfo.get("envdeviceid")) ? "空" : (String)inInfo.get("envdeviceid");
    		Map querymap = new HashMap();
    		querymap.put("envdeviceid", envdeviceid);
    	    List list = this.dao.query("tduhb18.query", querymap);
    		if(list.size() > 0) {
    			Tduhb18 tduhb18 = (Tduhb18)list.get(0);
    			row.put("envdeviceid", tduhb18.getEnvdeviceid());
    			 row.put("deviceid", tduhb18.getDeviceid());
    		    row.put("devicename", tduhb18.getDevicename());
    		    row.put("factorname", tduhb18.getFactorname());
    		    row.put("processid", getFacilityCodeById(tduhb18.getProcessid()));
    		    row.put("processname", getFacilityNameById(tduhb18.getProcessid()));
    		    row.put("canuse", tduhb18.getCanuse());
    		    row.put("processinfo", tduhb18.getProcessinfo());
    		    row.put("outto", tduhb18.getOutto());
    		    row.put("portouttype", tduhb18.getPortouttype());
    		    row.put("outrule", tduhb18.getOutrule());
    		    row.put("portid", getPortCodeById(envdeviceid));
    		    row.put("portname", getPortNameById(envdeviceid));
    		    row.put("isright", tduhb18.getIsright());
    		    row.put("porttype", tduhb18.getPorttype());
    		    row.put("other", tduhb18.getOther());
    		}
        }
        queryblock.addRow(row);
		outInfo.setBlock(queryblock);

		outInfo.setMsg("页面加载完成");
		return outInfo;
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
			String envdeviceid = (String)inInfo.get("inqu_status-0-envdeviceid");
			String deviceid = (String)inInfo.get("inqu_status-0-deviceid");
			String devicename = (String)inInfo.get("inqu_status-0-devicename");
			String factorname = (String)inInfo.get("inqu_status-0-factorname");
			String processid = (String)inInfo.get("inqu_status-0-processid");
			String processname = (String)inInfo.get("inqu_status-0-processname");
			//String canuse = (String)inInfo.get("inqu_status-0-canuse");
			//String processinfo = (String)inInfo.get("inqu_status-0-processinfo");
			String outto = (String)inInfo.get("inqu_status-0-outto");
			String portouttype = (String)inInfo.get("inqu_status-0-portouttype");
			String outrule = (String)inInfo.get("inqu_status-0-outrule");
			String portid = (String)inInfo.get("inqu_status-0-portid");
			String portname = (String)inInfo.get("inqu_status-0-portname");
			//String isright = (String)inInfo.get("inqu_status-0-isright");
			//String porttype = (String)inInfo.get("inqu_status-0-porttype");
			String other = (String)inInfo.get("inqu_status-0-other");
			Map<String, String> map = new HashMap<String, String>();
									
		    map.put("deviceid", "");
		    map.put("devicename", devicename);
		    map.put("sourcename", "");
		    map.put("factorname", factorname);
		    map.put("outtype", "");
		    map.put("processid", processid);
		    map.put("processname", processname);
		    map.put("processtype", "");

		    map.put("portid", portid);
		    map.put("portname", portname);

		    map.put("other", other);
		    map.put("outto", outto);
		    map.put("portouttype", portouttype);
		    map.put("outrule", outrule);
		    
		    map.put("devicetype", "02");
		    map.put("status", "1");
			if("insert".equals(oprationType)) {
			    map.put("canuse", "");
			    map.put("processinfo", "");
			    map.put("isright", "");
			    map.put("porttype", "");
			    envdeviceid=getEnvDeviceId();
				map.put("envdeviceid", envdeviceid);
				map.put("deviceid", envdeviceid);
				map.put("creator", currentUser);
				map.put("createdTime", dateTimeFormat.format(new Date()));
				dao.insert( "tduhb18.insert", map);
				buffer.insert(0, "记录保存成功\n");
			}else if("update".equals(oprationType)) {
				map.put("envdeviceid", envdeviceid);
				map.put("modifier", currentUser);
				map.put("updatedTime", dateTimeFormat.format(new Date()));
	        	dao.update( "tduhb18.updateForFQDevice", map);
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
	
    private String getEnvDeviceId () throws Exception
	{
    	String  facilityid ="EDI";
        List list = this.dao.query("tduhb18.queryId", new HashMap<String, String>());
        int count = 1;
        if(list != null && list.size() > 0) {
        	count = Integer.parseInt(((Tduhb18)list.get(0)).getEnvdeviceid().substring(3)) + 1;
        }
		return facilityid + String.format("%07d", count);
	}
    
    /**
     * .根据治理设施id，查询对应的设施编号
     * @param facilityIds
     * @return
     */
    public String getFacilityCodeById(String facilityIds) {
    	String facilityCodeStr = "";
    	if (StrUtil.paramIsNotNullOrEmpty(facilityIds)) {
    		List<String> facilityIdList = new ArrayList<>(Arrays.asList(facilityIds.split(",")));
			List<String> codeList = new ArrayList<>();
			for (String facilityid : facilityIdList) {
				Map<String, String> pMap = new HashMap<>();
				pMap.put("facilityid", facilityid);
				//根据设施id查询治理设施数据信息
				List<Tduhb20> listhb20 = this.dao.query("tduhb20.query",pMap);
				if (StrUtil.listIsNotNullOrEmpty(listhb20)) {
					String facilitycode = StrUtil.isNullToStr(listhb20.get(0).getFacilitycode());
					if (!codeList.contains(facilitycode)) {
						codeList.add(facilitycode);
					}
				}
				
			}
			
			if (StrUtil.listIsNotNullOrEmpty(codeList)) {
				facilityCodeStr = StringUtils.join(codeList , ",");
			}
    	}
    	
    	return facilityCodeStr;
    }
    
    /**
     * .根据治理设施id，查询对应的设施名称
     * @param facilityIds
     * @return
     */
    public String getFacilityNameById(String facilityIds) {
    	String facilityNameStr = "";
    	if (StrUtil.paramIsNotNullOrEmpty(facilityIds)) {
    		List<String> facilityIdList = new ArrayList<>(Arrays.asList(facilityIds.split(",")));
			List<String> nameList = new ArrayList<>();
			for (String facilityid : facilityIdList) {
				Map<String, String> pMap = new HashMap<>();
				pMap.put("facilityid", facilityid);
				//根据设施id查询治理设施数据信息
				List<Tduhb20> listhb20 = this.dao.query("tduhb20.query",pMap);
				if (StrUtil.listIsNotNullOrEmpty(listhb20)) {
					String facilityname = StrUtil.isNullToStr(listhb20.get(0).getFacilityname());
					if (!nameList.contains(facilityname)) {
						nameList.add(facilityname);
					}
				}
				
			}
			
			if (StrUtil.listIsNotNullOrEmpty(nameList)) {
				facilityNameStr = StringUtils.join(nameList , ",");
			}
    	}
    	
    	return facilityNameStr;
    }
    
    /**
     * .根据产污设施id，查询对应的排口编号
     * @param facilityIds
     * @return
     */
    public String getPortCodeById(String envdeviceid) {
    	String portCodeStr = "";
    	if (StrUtil.paramIsNotNullOrEmpty(envdeviceid)) {
			Map<String, String> enMap = new HashMap<>();
			enMap.put("envdeviceid", envdeviceid);
			List<Tduhb18> listhb18 = this.dao.query("tduhb18.query",enMap);
			if (StrUtil.listIsNotNullOrEmpty(listhb18)) {
				String facilityIds = listhb18.get(0).getProcessid();
		    	if (StrUtil.paramIsNotNullOrEmpty(facilityIds)) {
		    		List<String> facilityIdList = new ArrayList<>(Arrays.asList(facilityIds.split(",")));
					List<String> portCodeList = new ArrayList<>();

					for (String facilityid : facilityIdList) {
						Map<String, String> pMap = new HashMap<>();
						pMap.put("facilityid", facilityid);
						//根据设施id，查询排口信息
						List<Map<String,String>> list21 = this.dao.query("tduhb21.queryByHb18",pMap);
						if (StrUtil.listIsNotNullOrEmpty(list21)) {
							for (int j = 0; j < list21.size(); j++) {
								String portCode = list21.get(j).get("dischargeportcode");
								if (!portCodeList.contains(portCode)) {
									portCodeList.add(portCode);
								}
							}
						}
						
					}
					
					if (StrUtil.listIsNotNullOrEmpty(portCodeList)) {
						portCodeStr = StringUtils.join(portCodeList , ",");
					}
		    	}

			}
		}
	
    	return portCodeStr;
    }
    
    
    /**
     * .根据产污设施id，查询对应的排口名称字符串
     * @param facilityIds
     * @return
     */
    public String getPortNameById(String envdeviceid) {
    	String portNameStr = "";
    	if (StrUtil.paramIsNotNullOrEmpty(envdeviceid)) {
			Map<String, String> enMap = new HashMap<>();
			enMap.put("envdeviceid", envdeviceid);
			List<Tduhb18> listhb18 = this.dao.query("tduhb18.query",enMap);
			if (StrUtil.listIsNotNullOrEmpty(listhb18)) {
				String facilityIds = listhb18.get(0).getProcessid();
		    	if (StrUtil.paramIsNotNullOrEmpty(facilityIds)) {
		    		List<String> facilityIdList = new ArrayList<>(Arrays.asList(facilityIds.split(",")));
					List<String> portNameList = new ArrayList<>();
					for (String facilityid : facilityIdList) {
						Map<String, String> pMap = new HashMap<>();
						pMap.put("facilityid", facilityid);
						//根据设施id，查询排口信息
						List<Map<String,String>> list21 = this.dao.query("tduhb21.queryByHb18",pMap);
						if (StrUtil.listIsNotNullOrEmpty(list21)) {
							for (int j = 0; j < list21.size(); j++) {
								String portname = list21.get(j).get("dischargeportname");
								if (!portNameList.contains(portname)) {
									portNameList.add(portname);
								}
							}
						}
						
					}
					
					if (StrUtil.listIsNotNullOrEmpty(portNameList)) {
						portNameStr = StringUtils.join(portNameList , ",");
					}
		    	}

			}
		}
	
    	return portNameStr;
    }
    
}
