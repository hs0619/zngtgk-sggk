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
import com.baosight.sggk.common.du.domain.Tduhb06;
import com.baosight.sggk.common.du.domain.Tduhb07;
import com.baosight.sggk.common.du.domain.Tduhb08;
import com.baosight.sggk.util.StrUtil;


public class ServiceDUHB0701 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHB0701.class);

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
		String procid = "空";
		if("insert".equals(oprationType)) {
			inInfo.set("inqu_status-0-isartificial", "0");
			inInfo.set("inqu_status-0-isonline", "1");
			inInfo.set("inqu_status-0-status", "1");
			if(departlist.size() > 0) {
				departid = ((Tduhb01)departlist.get(0)).getDepartmentId();
			}
		}else if("update".equals(oprationType)) {
			String siteid = StringUtils.isBlank((String)inInfo.get("siteid")) ? "空" : (String)inInfo.get("siteid");
			querymap = new HashMap();
    		querymap.put("siteid", siteid);
    	    List list = this.dao.query("tduhb07.query", querymap);
    		if(list.size() > 0) {
    			Tduhb07 tduhb07 = (Tduhb07)list.get(0);
    			departid = tduhb07.getDepartid();
    			procid = tduhb07.getProcid();
    			inInfo.set("inqu_status-0-departid", tduhb07.getDepartid());
    			inInfo.set("inqu_status-0-procid", tduhb07.getProcid());
    			inInfo.set("inqu_status-0-portid", tduhb07.getPortid());
    			inInfo.set("inqu_status-0-isartificial", tduhb07.getIsartificial());
    			inInfo.set("inqu_status-0-isonline", tduhb07.getIsonline());
    			inInfo.set("inqu_status-0-classifyid", tduhb07.getClassifyid());
    			inInfo.set("inqu_status-0-monitorid", tduhb07.getMonitorid());
    			inInfo.set("inqu_status-0-status", tduhb07.getStatus());
    			
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
		
		//添加排口
		EiBlock portblock = inInfo.getBlock("portList");
		if(portblock == null) {
			portblock = new EiBlock("portList");
			portblock.setBlockMeta(new Tduhb05().eiMetadata);
		}
		querymap = new HashMap();
		querymap.put("procid", procid);
		List<Tduhb05> portlist = this.dao.query("tduhb05.query", querymap);
		portblock.setRows(portlist);
		inInfo.setBlock(portblock);
		 
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
	    EiColumn eiColumn = new EiColumn("siteid");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("sitename");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("departid");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("procid");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("portid");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("isartificial");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("isonline");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("classifyid");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("monitorid");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("mnid");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("description");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("sort");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("status");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("oprationType");
	    metadata.addMeta(eiColumn);
	    queryblock.setBlockMeta(metadata);  
        
	    Map<String,String> row = new HashMap<String,String>();
    	
        if("insert".equals(oprationType)) {
        	row.put("oprationType", "insert");
        	row.put("siteid", "");
		    row.put("sitename", "");
		    row.put("departid", "");
		    row.put("procid", "");
		    row.put("portid", "");
		    row.put("isartificial", "");
		    row.put("isonline", "");
		    row.put("classifyid", "");
		    row.put("monitorid", "");
		    row.put("mnid", "");
		    row.put("description", "");
		    row.put("sort", "");
		    row.put("status", "");
        }else if("update".equals(oprationType)) {
        	row.put("oprationType", "update");
    		String siteid = StringUtils.isBlank((String)inInfo.get("siteid")) ? "空" : (String)inInfo.get("siteid");
    		Map querymap = new HashMap();
    		querymap.put("siteid", siteid);
    	    List list = this.dao.query("tduhb07.query", querymap);
    		if(list.size() > 0) {
    			Tduhb07 tduhb07 = (Tduhb07)list.get(0);
    			row.put("siteid", tduhb07.getSiteid());
    		    row.put("sitename", tduhb07.getSitename());
    		    row.put("departid", tduhb07.getDepartid());
    		    row.put("procid", tduhb07.getProcid());
    		    row.put("portid", tduhb07.getPortid());
    		    row.put("isartificial", tduhb07.getIsartificial());
    		    row.put("isonline", tduhb07.getIsonline());
    		    row.put("classifyid", tduhb07.getClassifyid());
    		    row.put("monitorid", tduhb07.getMonitorid());
    		    row.put("mnid", tduhb07.getMnid());
    		    row.put("description", tduhb07.getDescription());
    		    row.put("sort", tduhb07.getSort());
    		    row.put("status", tduhb07.getStatus());
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
		
		//添加排口
		EiBlock portblock = new EiBlock("portList");
		portblock.setBlockMeta(new Tduhb05().eiMetadata);
		outInfo.setBlock(portblock);
			
		//添加类别
		EiBlock monitorblock = new EiBlock("monitorList");
		monitorblock.setBlockMeta((new Tduhb08()).eiMetadata);
		outInfo.setBlock(monitorblock);
		
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
		List procedureblocklist = this.dao.query("tduhb01.query", querymap);
		procedureblock.addRows(procedureblocklist);
		inInfo.setBlock(procedureblock);
        
		return inInfo;
	}
    
    //根据工序切换排口
    public EiInfo queryPortByProcedure(EiInfo inInfo) {

    	String procid = StringUtils.isBlank((String)inInfo.get("procid")) ? "空" : (String)inInfo.get("procid");
    	//添加排口
		EiBlock portblock = inInfo.getBlock("portList");
		if(portblock == null) {
			portblock = new EiBlock("portList");
			portblock.setBlockMeta(new Tduhb05().eiMetadata);
		}
		Map querymap = new HashMap();
		querymap.put("procid", procid);
		List procedurelist = this.dao.query("tduhb05.query", querymap);
		portblock.addRows(procedurelist);
		inInfo.setBlock(portblock);
        
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
			String siteid = (String)inInfo.get("inqu_status-0-siteid");
			String sitename = (String)inInfo.get("inqu_status-0-sitename");
			String departid = (String)inInfo.get("inqu_status-0-departid");
			String procid = (String)inInfo.get("inqu_status-0-procid");
			String portid = (String)inInfo.get("inqu_status-0-portid");
			String isartificial = (String)inInfo.get("inqu_status-0-isartificial");
			String isonline = (String)inInfo.get("inqu_status-0-isonline");
			String classifyid = (String)inInfo.get("inqu_status-0-classifyid");
			String monitorid = (String)inInfo.get("inqu_status-0-monitorid");
			String mnid = (String)inInfo.get("inqu_status-0-mnid");
			String description = (String)inInfo.get("inqu_status-0-description");
			String sort = (String)inInfo.get("inqu_status-0-sort");
			String status = (String)inInfo.get("inqu_status-0-status");
			Map<String, String> map = new HashMap<String, String>();
									
		    map.put("sitename", sitename);
		    map.put("departid", departid);
		    map.put("procid", procid);
		    map.put("portid", portid);
		    map.put("isartificial", isartificial);
		    map.put("isonline", isonline);
		    map.put("classifyid", classifyid);
		    map.put("monitorid", monitorid);
		    map.put("mnid", mnid);
		    map.put("description", description);
		    map.put("status", status);
		    
			if("insert".equals(oprationType)) {
				int maxSort = Integer.parseInt(getMaxSort());
				if (maxSort < Integer.parseInt(sort)) {
					sort = String.valueOf(maxSort + 1);
				}
				sortIsExist(sort);
				String pSiteid = getSiteId();
				map.put("siteid", pSiteid);
				map.put("sort", sort);
				map.put("creator", currentUser);
				map.put("createdTime", dateTimeFormat.format(new Date()));
				dao.insert( "tduhb07.insert", map);
				buffer.insert(0, "记录保存成功\n");
				addPortFactorToSite(portid, pSiteid, isartificial, isonline);//生成一个排口的同时，添加一个监测点
			}else if("update".equals(oprationType)) {
				//根据当前监测点，查询对应的未改变前的排序值
				String oldSort = getSortBySiteid(siteid);
				sortIsExistUpdate(sort,oldSort,siteid);
				map.put("siteid", siteid);
				map.put("modifier", currentUser);
				map.put("updatedTime", dateTimeFormat.format(new Date()));
	        	dao.update( "tduhb07.updateTwo", map);
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
    
    /**
     * 添加排口的因子到监测点
     */
    private void addPortFactorToSite(String portid,String siteid,String isartificial,String isonline) {
		String currentUser = String.valueOf(UserSession.getLoginCName());
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	//1.取出portid对应的所有的监测因子
    	Map<String, String> pMap1 = new HashMap<>();
    	pMap1.put("portid", portid);
    	List<Tduhb06> list06 = this.dao.query("tduhb06.queryById",pMap1);
    	if (StrUtil.listIsNotNullOrEmpty(list06)) {
			for (int i = 0,length = list06.size(); i < length; i++) {
				Tduhb06 tduhb06 = list06.get(i);
				String factorid = tduhb06.getFactorid();
				Map<String, String> pMap2 = new HashMap<>();
				pMap2.put("factorid", factorid);
				pMap2.put("siteid", siteid);
				pMap2.put("description", "");
				pMap2.put("status", "1");
				pMap2.put("creator", currentUser);
				pMap2.put("createdTime", sdf.format(new Date()));
				pMap2.put("usezs", "0");
				//判断监测站点监测类型为手工还是在线
				if ("1".equals(isartificial)) {
					pMap2.put("type", "1");
					this.dao.insert("tduhb0702.insert",pMap2);
				}
				
				if ("1".equals(isonline)) {
					pMap2.put("type", "2");
					this.dao.insert("tduhb0702.insert",pMap2);
				}
				
			}
		}
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
    
    /**
     * 判断排序值是否存在
     * @param sortNum
     * @param departmentid
     * @param parentid
     */
    public void sortIsExist(String sortNum) {
    	Map<String,String> pMap = new HashMap<>();
    	pMap.put("sort", sortNum);
    	List<Tduhb07> sortList = this.dao.query("tduhb07.sortIsExist" , pMap);
    	if (StrUtil.listIsNotNullOrEmpty(sortList)) {
        	Map pMap1 = new HashMap<>();
        	pMap1.put("sort", Integer.parseInt(sortNum));
        	List<Tduhb07> bigList = dao.query("tduhb07.queryBigSortList",pMap1);
        	if (StrUtil.listIsNotNullOrEmpty(bigList)) {
				for (int i = 0; i < bigList.size(); i++) {
					Tduhb07 tduhb07 = bigList.get(i);
					String siteid = tduhb07.getSiteid();
					String uSort = tduhb07.getSort();
					int sort = Integer.parseInt(uSort) + 1;
					Map<String, String> pMap2 = new HashMap<>();
					pMap2.put("siteid", siteid);
					pMap2.put("sort", String.valueOf(sort));
					this.dao.update("tduhb07.updateSortVal",pMap2);
				}
        	}
    	}
    	
    }
    
    
    public void sortIsExistUpdate(String newSort,String oldSort,String siteid) {
    	int sortNew = Integer.parseInt(newSort);
    	int sortOld = Integer.parseInt(oldSort);
    	int sortMax = Integer.parseInt(getMaxSort());
    	

    	if (sortOld != sortNew ) {
    		if (sortNew > sortMax) {
				sortNew = sortMax;
			}
    		
    		Map map = new HashMap<>();
    		map.put("sortOld", sortOld);
    		map.put("sortNew", sortNew);
    		
			if (sortOld > sortNew) {
				this.dao.update("tduhb07.sortBigToSmall",map);
			}else {
				this.dao.update("tduhb07.sortSmallToBig",map);
			}
    		
	    	Map pMap = new HashMap<>();
	    	pMap.put("sort", sortNew);
	    	pMap.put("siteid", siteid);
    		this.dao.update("tduhb07.updateSortVal",pMap);
    		
		}
    }
    
    
    /**
     * 获取列表中最大的排序值
     * @return
     * @throws Exception
     */
    private String getMaxSort ()
	{
    	String sortVal = "999";
    	Map<String,String> pMap =  new HashMap<>();
        List<Tduhb07> list = this.dao.query("tduhb07.queryBySortDesc", pMap);
        if (StrUtil.listIsNotNullOrEmpty(list)) {
			sortVal = list.get(0).getSort();
		}
        return sortVal;
	}
    
    
    /**
     * g根据监测站点id获取对应的排序值
     * @return
     * 
     */
    private String getSortBySiteid (String siteid)
	{
    	String sortVal = "99";
    	Map<String,String> pMap =  new HashMap<>();
    	pMap.put("siteid", siteid);
        List<Tduhb07> list = this.dao.query("tduhb07.query", pMap);
        if (StrUtil.listIsNotNullOrEmpty(list)) {
			sortVal = list.get(0).getSort();
		}
        return sortVal;
	}
    
    /**
     **根据排口id，获取对应的排口类型
     * @param inInfo
     * @return
     */
    public EiInfo queryPortTypeById(EiInfo inInfo) {
    	String portid = inInfo.getString("portid");
    	String portType = "";
    	if (StrUtil.paramIsNotNullOrEmpty(portid)) {
			Map<String, String> pMap = new HashMap<>();
			pMap.put("dischargeportid", portid);
			List<Tduhb05> portList  = this.dao.query("tduhb05.query",pMap);
			if (StrUtil.listIsNotNullOrEmpty(portList)) {
				portType = portList.get(0).getMonitorid();
			}
		}
    	inInfo.set("portType", portType);
    	return inInfo;
    }
    
    
    
    
}
