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
import com.baosight.sggk.common.du.domain.Tduhb05;
import com.baosight.sggk.common.du.domain.Tduhb06;
import com.baosight.sggk.common.du.domain.Tduhb07;
import com.baosight.sggk.common.du.domain.Tduhb0702;
import com.baosight.sggk.common.du.domain.Tduhb08;
import com.baosight.sggk.common.du.domain.Tduhb10;

import com.baosight.sggk.util.StrUtil;

public class ServiceDUHB0601 extends ServiceEPBase {
	
	private static final Logger logger = Logger.getLogger(ServiceDUHB0601.class);
	
	public EiInfo initSelectData(EiInfo inInfo) {
		
		//添加标志
	    EiBlock flagblock = inInfo.getBlock("flagList");
		if(flagblock == null) {
			flagblock = new EiBlock("flagList");
			EiBlockMeta eiMetadata = new EiBlockMeta();
			EiColumn eiColumn = new EiColumn("flagid");
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
		if("insert".equals(oprationType)) {
			if(pfmonitorlist.size() > 0) {
				Tduhb08 tduhb08 = (Tduhb08)pfmonitorlist.get(0);
				if(tduhb08 != null) {
					Map pffactormap = new HashMap();
				    pffactormap.put("monitorid",tduhb08.getMonitorid());
			        List pffactorlist = this.dao.query("tduhb10.queryfactor", pffactormap);
			        pffactorblock.setRows(pffactorlist);
				}
			}
			inInfo.set("inqu_status-0-status", "1");
		}else if("update".equals(oprationType)) {
			String portid = StringUtils.isBlank((String) inInfo.get("pfportid")) ? "空" : (String) inInfo.get("pfportid");
			String factorid = StringUtils.isBlank((String) inInfo.get("factorid")) ? "空" : (String) inInfo.get("factorid");
			List list = getFactorById(portid,factorid);
    		if(list.size() > 0) {
    			Tduhb06 tduhb06 = (Tduhb06)list.get(0);
    			if(StringUtils.isNotBlank(tduhb06.getMonitorid())) {
    				Map pffactormap = new HashMap();
    			    pffactormap.put("monitorid",tduhb06.getMonitorid());
    		        List pffactorlist = this.dao.query("tduhb10.queryfactor", pffactormap);
    		        pffactorblock.setRows(pffactorlist);
    			}
    			inInfo.set("inqu_status-0-pfmonitorid", tduhb06.getMonitorid());
        		inInfo.set("inqu_status-0-pffactorid", tduhb06.getFactorid());
        		inInfo.set("inqu_status-0-status", tduhb06.getStatus());
    		}
    		
		}
        inInfo.setBlock(pffactorblock);
	    
		return inInfo;
	}
	
	public EiInfo initLoad( EiInfo inInfo )
	{
		EiInfo outInfo = new EiInfo();
		
		String oprationType = (String) inInfo.get("oprationType");
		String portid = StringUtils.isBlank((String) inInfo.get("portid")) ? "空" : (String) inInfo.get("portid");
		String portname = "";
		Tduhb05 tduhb05 = getDischargeportById(portid);
        if(!StringUtils.isBlank(portid) && tduhb05 != null) {
        	portname = StrUtil.trimToString(tduhb05.getDischargeportname());
        }else {
			outInfo.setMsg("排口参数错误");
			return outInfo;
		}
		String factorid = StringUtils.isBlank((String) inInfo.get("factorid")) ? "空" : (String) inInfo.get("factorid");
		
		
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
        
      //添加标志
        metadata = new EiBlockMeta();
	    eiColumn = new EiColumn("flagid");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("flagname");
	    metadata.addMeta(eiColumn);
	    EiBlock flagblock = new EiBlock("flagList");
	    flagblock.setBlockMeta(metadata);
	    outInfo.setBlock(flagblock);
		
	    Map<String,String> row = new HashMap<String,String>();
     	row.put("pfportid", portid);
    	row.put("pfportname", portname);
    	
        if("insert".equals(oprationType)) {
        	row.put("oprationType", "insert");
        	row.put("pfmonitorid", "01");
		    row.put("pffactorid", "");
		    row.put("highlimit", "");
		    row.put("lowlimit", "");
		    row.put("nkhighlimit", "");
		    row.put("nklowlimit", "");
		    row.put("description", "");
		    row.put("status", "");
        }else if("update".equals(oprationType)) {
        	row.put("oprationType", "update");
        	List list = getFactorById(portid,factorid);
    		if(list.size() > 0) {
    			Tduhb06 tduhb06 = (Tduhb06)list.get(0);
    		    row.put("pfmonitorid", tduhb06.getMonitorid());
    		    row.put("pffactorid", tduhb06.getFactorid());
    		    row.put("highlimit", tduhb06.getHighlimit());
    		    row.put("lowlimit", tduhb06.getLowlimit());
    		    row.put("nkhighlimit", tduhb06.getNkhighlimit());
    		    row.put("nklowlimit", tduhb06.getNklowlimit());
    		    row.put("description", tduhb06.getDescription());
    		    row.put("status", tduhb06.getStatus());
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
	        List list = this.dao.query("tduhb06.queryById", map);
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
    
    private Tduhb05 getDischargeportById(String dischargeportid) 
	{
		try {
			if(StringUtils.isBlank(dischargeportid)) {
				dischargeportid = "空";
			}
			Tduhb05 tduhb05 = null;
			Map<String, String> map = new HashMap<String, String>();
			map.put("dischargeportid", dischargeportid);
	        List list = this.dao.query("tduhb05.query", map);
	        if(list.size() > 0) {
	        	tduhb05 = (Tduhb05)list.get(0);
	        }
			return tduhb05;
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
			String factorid =  StringUtils.isBlank((String) inInfo.get("inqu_status-0-pffactorid")) ? "" : (String) inInfo.get("inqu_status-0-pffactorid");
			String highlimit = StrUtil.trimToString(inInfo.get("inqu_status-0-highlimit"));
			String lowlimit = StrUtil.trimToString(inInfo.get("inqu_status-0-lowlimit"));
			String nkhighlimit = StrUtil.trimToString(inInfo.get("inqu_status-0-nkhighlimit"));
			String nklowlimit = StrUtil.trimToString(inInfo.get("inqu_status-0-nklowlimit"));
			String description = StrUtil.trimToString(inInfo.get("inqu_status-0-description"));
			String status = StrUtil.trimToString(inInfo.get("inqu_status-0-status"));
			Map<String, String> map = new HashMap<String, String>();
			map.put("monitorid", monitorid);
			map.put("portid", portid);
			map.put("highlimit", highlimit);
			map.put("lowlimit", lowlimit);
			map.put("nkhighlimit", nkhighlimit);
			map.put("nklowlimit", nklowlimit);
			map.put("description", description);
			map.put("status", status);
			
			if("insert".equals(oprationType)) {
				String [] factors=factorid.split(",");
				for (String factor : factors) {
					map.put("factorid", factor);
					map.put("creator", currentUser);
					map.put("createdTime", dateTimeFormat.format(new Date()));
					//插入排口因子前，需判断该因子是否已经存在于排口-因子表中，若存在，则跳过此因子，继续下一因子的操作
					boolean flag = portFactorIsExist(factor,portid);
					if (!flag) {
						dao.insert("tduhb06.insert", map);
						//排口因子添加完成后，查询对应的监测站点，添加排口因子到监测站点对应的因子当中
						addPortFactorToSiteFactor(portid,factor);
					}else {
						continue;
					}
					
				}
				buffer.insert(0, "记录保存成功\n");
			}
//			else if("update".equals(oprationType)) {
//				map.put("modifier", currentUser);
//				map.put("updatedTime", dateTimeFormat.format(new Date()));
//	        	dao.update( "tduhb15.update", map);
//	        	buffer.insert(0, "记录保存成功\n");
//			}
			else {
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
    
    /**
     * 添加排口因子到对应的监测站点下
     * @param portid
     * @param siteid
     */
    private void addPortFactorToSiteFactor(String portid,String factorid) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	//1.根据排口id查询对应的排口下的监测站点列表信息
    	Map<String, String> pMap = new HashMap<>();
    	pMap.put("portid", portid);
    	List<Tduhb07> siteList = this.dao.query("tduhb07.query",pMap);
    	if (StrUtil.listIsNotNullOrEmpty(siteList)) {
			for (int i = 0,length = siteList.size(); i < length; i++) {
				String 	siteid = siteList.get(i).getSiteid();
				String isartificial = siteList.get(i).getIsartificial();
				String isonline = siteList.get(i).getIsonline();
				//判断监测站点用户是否已经进行维护，
				//如果isartificial = 0，isonline = 0,表明还未进行维护，否则进行了维护
				if ("0".equals(isartificial) && "0".equals(isonline)) {	
			    	Map<String, String> sfMap = new HashMap<>();
			    	sfMap.put("factorid", factorid);
			    	sfMap.put("siteid", siteid);
			    	sfMap.put("type", "1");
			    	sfMap.put("description", "");
			    	sfMap.put("status", "1");
			    	sfMap.put("creator", String.valueOf(UserSession.getLoginCName()));
			    	sfMap.put("createdTime", sdf.format(new Date()));
			    	sfMap.put("usezs", "0");
					this.dao.insert("tduhb0702.insert",sfMap);
					
			    	Map<String, String> sfMap1 = new HashMap<>();
			    	sfMap1.put("factorid", factorid);
			    	sfMap1.put("siteid", siteid);
			    	sfMap1.put("type", "2");
			    	sfMap1.put("description", "");
			    	sfMap1.put("status", "1");
			    	sfMap1.put("creator", String.valueOf(UserSession.getLoginCName()));
			    	sfMap1.put("createdTime", sdf.format(new Date()));
			    	sfMap1.put("usezs", "0");
					this.dao.insert("tduhb0702.insert",sfMap1);
					
				}else {//用户处理过的站点，根据是否为手工和在线，当添加排口因子时去添加对应的站点因子
					if ("1".equals(isartificial)) {
						//添加因子前，需要判断该因子是否已经存在，若存在，则不添加该因子
						boolean flag = siteFactorIsExist(siteid, factorid, "1");
						if (!flag) {
					    	Map<String, String> sfMap = new HashMap<>();
					    	sfMap.put("factorid", factorid);
					    	sfMap.put("siteid", siteid);
					    	sfMap.put("type", "1");
					    	sfMap.put("description", "");
					    	sfMap.put("status", "1");
					    	sfMap.put("creator", String.valueOf(UserSession.getLoginCName()));
					    	sfMap.put("createdTime", sdf.format(new Date()));
					    	sfMap.put("usezs", "0");
					    	
							this.dao.insert("tduhb0702.insert",sfMap);
						}
					}
					
					if ("1".equals(isonline)) {
						boolean flag = siteFactorIsExistForOnline(siteid, factorid, "2");
						if (!flag) {
					    	Map<String, String> sfMap = new HashMap<>();
					    	sfMap.put("factorid", factorid);
					    	sfMap.put("siteid", siteid);
					    	sfMap.put("type", "2");
					    	sfMap.put("description", "");
					    	sfMap.put("status", "1");
					    	sfMap.put("creator", String.valueOf(UserSession.getLoginCName()));
					    	sfMap.put("createdTime", sdf.format(new Date()));
					    	sfMap.put("usezs", "0");
					    	
							this.dao.insert("tduhb0702.insert",sfMap);
						}
						
					}

				}
				
			}
    		
    		
		}
    	
    }
     
    
    
    /**
     * s判断该因子是否存在于排口-因子表中，存在则返回true
     * @param siteid
     * @param factorid
     * @param type
     * @return
     */
    public boolean portFactorIsExist(String factorid,String portid) {
    	Map<String, String> pMap = new HashMap<>();
    	pMap.put("portid", portid);
    	pMap.put("factorid", factorid);
    	List<Tduhb06> pfList = this.dao.query("tduhb06.queryById",pMap);
    	if (StrUtil.listIsNotNullOrEmpty(pfList)) {
			return true;
		}
    	return false;
    }
    
    /**
     * s判断该因子是否存在于监测站点因子表中，存在则返回true
     * @param siteid
     * @param factorid
     * @param type
     * @return
     */
    public boolean siteFactorIsExist(String siteid,String factorid,String type) {
    	Map<String, String> pMap = new HashMap<>();
    	pMap.put("siteid", siteid);
    	pMap.put("factorid", factorid);
    	pMap.put("type", type);
    	List<Tduhb0702> sfList = this.dao.query("tduhb0702.query",pMap);
    	if (StrUtil.listIsNotNullOrEmpty(sfList)) {
			return true;
		}
    	return false;
    }
    
    
    /**
     * s判断该因子是否存在于监测站点因子表中，存在则返回true
     * @param siteid
     * @param factorid
     * @param type
     * @return
     */
    public boolean siteFactorIsExistForOnline(String siteid,String factorid,String type) {
    	
    	List<String> fidList = onlineMonitorFactors();
    	if (fidList.contains(factorid)) {
			
		}
    	
    	Map<String, String> pMap = new HashMap<>();
    	pMap.put("siteid", siteid);
    	pMap.put("factorid", factorid);
    	pMap.put("type", type);
    	List<Tduhb0702> sfList = this.dao.query("tduhb0702.query",pMap);
    	if (StrUtil.listIsNotNullOrEmpty(sfList)) {
			return true;
		}else if (!fidList.contains(factorid)) {
			return true;
		}
    	return false;
    }
    
    /**
     * j在线监测的站点的所有因子
     * @return
     */
    public List<String> onlineMonitorFactors() {
    	List<String> factoridList = new ArrayList<>();
    	//废气
    	factoridList.add("01");//颗粒物
    	factoridList.add("02");//二氧化硫
    	factoridList.add("03");//氮氧化物
    	factoridList.add("S03");//烟气温度
    	factoridList.add("S08");//烟气压力
    	factoridList.add("S05");//烟气湿度
    	factoridList.add("S02");//烟气流速
    	factoridList.add("S01");//O2含量
    	factoridList.add("B02");//标态风量
    	//废水
    	factoridList.add("001");//PH值
    	factoridList.add("060");//氨氮
    	factoridList.add("011");//COD化学需氧量
    	factoridList.add("B01");//污水流量

    	return factoridList;
    }
    
    
    

}
