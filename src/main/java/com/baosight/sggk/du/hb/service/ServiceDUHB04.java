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
import com.baosight.sggk.util.StrUtil;


public class ServiceDUHB04 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHB04.class);

    public EiInfo initSelectData(EiInfo inInfo) {
		
		EiBlockMeta metadata;		 
	    EiColumn eiColumn;
		EiBlock typeblock = inInfo.getBlock("typeList");
		if(typeblock == null) {
			typeblock = new EiBlock("typeList");
			metadata = new EiBlockMeta();		 
		    eiColumn = new EiColumn("typeid");
		    metadata.addMeta(eiColumn);
		    eiColumn = new EiColumn("typename");
		    metadata.addMeta(eiColumn);
		    typeblock.setBlockMeta(metadata);
		}
		List<Map> list2 = new ArrayList<>();
/*		Map<String, String> map3 = new HashMap<String, String>();
		map3.put("typeid", "D1");
		map3.put("typename", "厂部");
		list2.add(map3);*/
		Map<String, String> map4 = new HashMap<String, String>();
		map4.put("typeid", "P1");
		map4.put("typename", "工序");
		list2.add(map4);
		typeblock.setRows(list2);
		inInfo.setBlock(typeblock);
		
		EiBlock flagblock = inInfo.getBlock("flagList");
		if(flagblock == null) {
			flagblock = new EiBlock("flagList");
			metadata = new EiBlockMeta();		 
		    eiColumn = new EiColumn("flagid");
		    metadata.addMeta(eiColumn);
		    eiColumn = new EiColumn("flagname");
		    metadata.addMeta(eiColumn);
		    flagblock.setBlockMeta(metadata);
		}
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
		inInfo.setBlock(flagblock);
		
		EiBlock departblock = inInfo.getBlock("departList");
		if(departblock == null) {
			departblock = new EiBlock("departList");
			departblock.setBlockMeta(new Tduhb01().eiMetadata);
		}
		Map querymap = new HashMap();
		querymap.put("parentid", "root");
	    List list3 = this.dao.query("tduhb01.query", querymap);
	    departblock.addRows(list3);
		inInfo.setBlock(departblock);
		
		String oprationType = StringUtils.isBlank((String) inInfo.get("oprationType")) ? "" : (String) inInfo.get("oprationType");
		
		
		if("insert".equals(oprationType)) {
			String parentid = (String) inInfo.get("parentid");
			inInfo.set("inqu_status-0-type", "P1");
			inInfo.set("inqu_status-0-status", "1");
			inInfo.set("inqu_status-0-parentid", parentid);
		}else if("update".equals(oprationType)) {
			String departmentId = (String) inInfo.get("departmentId");
			querymap = new HashMap();
    		querymap.put("departmentId", departmentId);
    	    List list = this.dao.query("tduhb01.query", querymap);
    		if(list.size() > 0) {
    			inInfo.set("inqu_status-0-parentid", ((Tduhb01)list.get(0)).getParentid());
    			inInfo.set("inqu_status-0-type", ((Tduhb01)list.get(0)).getType());
    			inInfo.set("inqu_status-0-status", ((Tduhb01)list.get(0)).getStatus());
    		}
		}
		 
		return inInfo;
	}

	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		  
		String oprationType = (String) inInfo.get("oprationType");
		String parentid = StringUtils.isBlank((String) inInfo.get("parentid")) ? "" : (String) inInfo.get("parentid");
        if(StringUtils.isBlank(oprationType)) {
        	outInfo.setMsg("厂部参数错误");
			return outInfo;
        }
        
		EiBlock queryblock = new EiBlock(EiConstant.queryBlock);
		EiBlockMeta metadata = new EiBlockMeta();		 
	    EiColumn eiColumn = new EiColumn("departmentId");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("departmentName");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("depProId");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("parentid");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("type");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("level");
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
	    
	    EiBlock typeblock = new EiBlock("typeList");
	    metadata = new EiBlockMeta();		 
	    eiColumn = new EiColumn("typeid");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("typename");
	    metadata.addMeta(eiColumn);
	    typeblock.setBlockMeta(metadata);
	    outInfo.setBlock(typeblock);
	    
	    EiBlock flagblock = new EiBlock("flagList");
	    metadata = new EiBlockMeta();		 
	    eiColumn = new EiColumn("flagid");
	    metadata.addMeta(eiColumn);
	    eiColumn = new EiColumn("flagname");
	    metadata.addMeta(eiColumn);
	    flagblock.setBlockMeta(metadata);
	    outInfo.setBlock(flagblock);
	    
	    EiBlock departblock = new EiBlock("departList");
	    departblock.setBlockMeta(new Tduhb01().eiMetadata);
	    outInfo.setBlock(departblock);
        
	    Map<String,String> row = new HashMap<String,String>();
    	
        if("insert".equals(oprationType)) {
        	row.put("oprationType", "insert");
        	row.put("departmentId", "");
        	row.put("depProId", "");
		    row.put("departmentName", "");
		    row.put("parentid", parentid);
		    row.put("type", "");
		    row.put("level", "");
		    row.put("description", "");
		    row.put("sort", "");
		    row.put("status", "1");
        }else if("update".equals(oprationType)) {
        	row.put("oprationType", "update");
        	String departmentId = StringUtils.isBlank((String)inInfo.get("departmentId")) ? "空" : (String)inInfo.get("departmentId");
    		Map querymap = new HashMap();
    		querymap.put("departmentId", departmentId);
    	    List list = this.dao.query("tduhb01.query", querymap);
    		if(list.size() > 0) {
    			Tduhb01 tduhb01 = (Tduhb01)list.get(0);
    			row.put("departmentId", tduhb01.getDepartmentId());
    			row.put("depProId", tduhb01.getDepProId());
    		    row.put("departmentName", tduhb01.getDepartmentName());
    		    row.put("parentid", tduhb01.getParentid());
    		    row.put("type", tduhb01.getType());
    		    row.put("level", tduhb01.getLevel());
    		    row.put("description", tduhb01.getDescription());
    		    row.put("sort", tduhb01.getSort());
    		    row.put("status", tduhb01.getStatus());
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
			String departmentId = (String)inInfo.get("inqu_status-0-departmentId");
			String depProId = (String)inInfo.get("inqu_status-0-depProId");
			String departmentName = (String)inInfo.get("inqu_status-0-departmentName");
			String parentid = (String)inInfo.get("inqu_status-0-parentid");
			String type = (String)inInfo.get("inqu_status-0-type");
			String level = (String)inInfo.get("inqu_status-0-level");
			level = StrUtil.isNullToStr(level);
			String description = (String)inInfo.get("inqu_status-0-description");
			String sort = (String)inInfo.get("inqu_status-0-sort");
			String status = (String)inInfo.get("inqu_status-0-status");
			Map<String, String> map = new HashMap<String, String>();

		    map.put("departmentName", departmentName);
		    map.put("parentid", parentid);
		    map.put("type", type);
		    map.put("level", level);
		    map.put("description", description);
		    
		    map.put("status", status);
			if("insert".equals(oprationType)) {
				int maxSort = Integer.parseInt(getMaxSort(parentid,type));
				if (maxSort < Integer.parseInt(sort)) {
					sort = String.valueOf(maxSort + 1);
				}
				
				String gDepartmentId = getDepartmentId();
				sortIsExist(sort,gDepartmentId,parentid);
				map.put("sort", sort);
				map.put("departmentId", gDepartmentId);
				map.put("depProId", getDepProNumber(parentid));
				map.put("creator", currentUser);
				map.put("createdTime", dateTimeFormat.format(new Date()));
				dao.insert( "tduhb01.insert", map);
				buffer.insert(0, "记录保存成功\n");
			}else if("update".equals(oprationType)) {
				//根据厂部id得到排序值
				String oldSort = "";
				Map<String, String> map1 = new HashMap<String, String>();
				map1.put("departmentId", departmentId);
				List<Tduhb01> list1 = this.dao.query("tduhb01.query",map1);
				if (StrUtil.listIsNotNullOrEmpty(list1)) {
					oldSort = list1.get(0).getSort();
				}
				
				sortIsExistUpdate(sort,parentid,oldSort,departmentId);
				map.put("departmentId", departmentId);
				map.put("depProId", depProId);
				map.put("modifier", currentUser);
				map.put("updatedTime", dateTimeFormat.format(new Date()));
	        	dao.update( "tduhb01.updateProceInfo", map);
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
	
    private String getDepartmentId () throws Exception
	{
    	String  departmentId ="PRO";
    	Map<String, String> pMap = new HashMap<String, String>();
    	pMap.put("departmentId", departmentId);
        List list = this.dao.query("tduhb01.queryId_pro", pMap);
        int count = 1;
        if(list != null && list.size() > 0) {
        	count = Integer.parseInt(((Tduhb01)list.get(0)).getDepartmentId().substring(3)) + 1;
        }
		return departmentId + String.format("%05d", count);
	}
    
    /**
     * 生成  厂部-工序  编号
     * @param parentid
     * @return
     * @throws Exception
     */
    private String getDepProNumber(String parentid) throws Exception
	{
    	Map<String, String> pMap = new HashMap<String, String>();
    	pMap.put("parentid", parentid);
        List list = this.dao.query("tduhb01.queryDepPro", pMap);
        int count = 1;
        if(list != null && list.size() > 0) {
        	count = Integer.parseInt(((Tduhb01)list.get(0)).getDepProId().substring(9)) + 1;
        }
		return parentid +"_" + String.format("%02d", count);
	}
    
    
    
    public void sortIsExist(String sortNum,String departmentid,String parentid) {
    	//1.查询该排序值是否存在
    	Map<String,String> pMap = new HashMap<>();
    	pMap.put("sort", sortNum);
    	pMap.put("parentid", parentid);
    	pMap.put("type", "P1");
    	List<Tduhb01> sortList  = this.dao.query("tduhb01.sortIsExist",pMap);
    	if (StrUtil.listIsNotNullOrEmpty(sortList)) {
			//2.如果该排序值已经存在，则需查出大于该排序值的所有编号，倒叙排列
        	Map pMap1 = new HashMap<>();
        	pMap1.put("sort", Integer.parseInt(sortNum));
        	pMap1.put("parentid", parentid);
        	pMap1.put("type", "P1");
			List<Tduhb01> bigList = this.dao.query("tduhb01.queryBigSortList",pMap1);
			if (StrUtil.listIsNotNullOrEmpty(bigList)) {
				for (int i = 0; i < bigList.size(); i++) {
					Tduhb01 tduhb01 = bigList.get(i);
					String uDepartmentid = tduhb01.getDepartmentId();
					String uSortStr = tduhb01.getSort();
					//为保持程序的健壮性，当数据为空、null、含字符串时，会出现转换异常的问题，
					//可取出数据表内最大值，将最大值+1，作为该数据的排序值
					int uSort = 0;
					try {
						uSort = Integer.parseInt(uSortStr) + 1;
					} catch (Exception e) {
						uSort = Integer.parseInt(bigList.get(0).getSort()) + 2;
					}
					//3.将所有的排序值执行update操作，进行加1
					Map<String, String> pMap2 = new HashMap<>();
					pMap2.put("departmentId", uDepartmentid);
					pMap2.put("sort", String.valueOf(uSort));
					this.dao.update("tduhb01.updateSortVal",pMap2);
				}
			}
	
		}

    }
    
    
    /**
     * 获取厂部中最大的排序值
     * @return
     * @throws Exception
     */
    private String getMaxSort(String parentid,String type)
	{
    	String sortVal = "999";
    	Map<String,String> pMap =  new HashMap<>();
    	pMap.put("parentid", parentid);
    	pMap.put("type", type);
        List<Tduhb01> list = this.dao.query("tduhb01.queryMaxSort", pMap);
        if (StrUtil.listIsNotNullOrEmpty(list)) {
			sortVal = list.get(0).getSort();
		}
        return sortVal;
	}
    
    /** 修改时的排序方法
     * 根据传递的排序值，去查询是否有对应的排序值存在，
     * 如果存在，将数据表中的id值，依次加1，并将传递的参数排序值加入数据表中，
     * 如果不存在，则直接插入数据库
     * @param num
     */
    public void sortIsExistUpdate(String sortNum,String parentid,String oldSort,String departmentId) {
    	int sortNew = Integer.parseInt(sortNum);
    	int sortOld = Integer.parseInt(oldSort);
    	int sortMax = Integer.parseInt(getMaxSort(parentid,"P1"));
    	

    	if (sortOld != sortNew ) {
    		if (sortNew > sortMax) {
				sortNew = sortMax;
			}
    		
    		Map map = new HashMap<>();
    		map.put("sortOld", sortOld);
    		map.put("sortNew", sortNew);
    		map.put("parentid", parentid);
    		map.put("type", "P1");
    		
			if (sortOld > sortNew) {
				this.dao.update("tduhb01.sortBigToSmall",map);
			}else {
				this.dao.update("tduhb01.sortSmallToBig",map);
				
			}
    		
	    	Map pMap = new HashMap<>();
	    	pMap.put("sort", sortNew);
	    	pMap.put("departmentId", departmentId);
    		this.dao.update("tduhb01.updateSortVal",pMap);
    		
		}
    }
    
    
    
    
}
