/**
 *
 */
package com.baosight.sggk.du.hc.service;

import com.baosight.sggk.common.du.domain.Tduhb07;
import com.baosight.sggk.util.PermissionUtil;
import com.baosight.sggk.util.StrUtil;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.*;

public class ServiceDUHC21 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHC21.class);

	//获取配置文件里的参数
	ResourceBundle dbPro = ResourceBundle.getBundle("application");
	String DbSchema = dbPro.getString("hbSchema");
	 
	
	public EiInfo initLoad(EiInfo inInfo) {
		  EiInfo outInfo = new EiInfo();
		  String status = PermissionUtil.getUserDepart(this.dao, UserSession.getLoginName());
		  outInfo.set("inqu_status-0-departmentid", status);
		  
		  outInfo.setMsg("页面加载完成");
		  return outInfo;
	}
	
    
    public EiInfo query(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		String monitorid = StringUtils.isBlank((String) inInfo.get("inqu_status-0-monitorid")) ? "空" : (String) inInfo.get("inqu_status-0-monitorid");
		String porttypeid = StringUtils.isBlank((String) inInfo.get("inqu_status-0-porttypeid")) ? "空" : (String) inInfo.get("inqu_status-0-porttypeid");
 		String departid = StringUtils.isBlank((String) inInfo.get("inqu_status-0-departmentid")) ? "空" : (String) inInfo.get("inqu_status-0-departmentid");
		String siteid = StringUtils.isBlank(inInfo.getString("inqu_status-0-siteid")) ? "%" : inInfo.getString("inqu_status-0-siteid");
		String isquery = inInfo.getString("isquery");
		try {
			Integer oloffset = 0,ollimit = 50;
			if(!"true".equals(isquery)) {
				if(inInfo.getBlock("oldata") != null) {
					ollimit = inInfo.getBlock("oldata").getInt("limit");
					oloffset = inInfo.getBlock("oldata").getInt("offset");
				}
			}
			EiBlock olinfoBlock = getFactorsBySite(monitorid);
			outInfo.setBlock(olinfoBlock);
			EiBlock oldataheaderBlock = getDataHeaderByFactors(olinfoBlock);
			outInfo.setBlock(oldataheaderBlock);
			
			if("%".equals(siteid)) {
				Map qsitemap = new HashMap();
		 		qsitemap.put("isonline", "1");
		 		qsitemap.put("monitorid", monitorid);
		 		if(!"%".equals(departid)) {
		 			qsitemap.put("departid", departid);
		 		}
		 		switch (porttypeid) {
		 		case "country":
		 			qsitemap.put("countrypoint", "1");
		 			break;
		 		case "city":
		 			qsitemap.put("citypoint", "1");
		 			break;
		 		case "company":
		 			qsitemap.put("companypoint", "1");
		 			break;
		 		default:
		 			break;
		 		}
		 		qsitemap.put("status", "1");
		 		List sitelist = this.dao.query("tduhb07.queryInfo", qsitemap, oloffset, ollimit);
			    List<Map<String, String>> mnList = getAllOnlineDataLastTime(sitelist);
			    if(mnList.size() > 0) {
			    	for (int i = 0; i < mnList.size(); i++) {		
			    		Map<String, String> tabledatemap = mnList.get(i);
			    		outInfo = getDataByFactors(outInfo, olinfoBlock, "2011", tabledatemap);
			    	}
			    }
			    EiBlock block = outInfo.getBlock("oldata");
			    block.set("offset",oloffset);
                block.set("limit",ollimit);
                block.set("count",sitelist.size());
        	}else {
        		Map qsitemap = new HashMap();
        		qsitemap.put("siteid", siteid);
		 		qsitemap.put("isonline", "1");
		 		qsitemap.put("monitorid", monitorid);
		 		qsitemap.put("status", "1");
		 		List sitelist = this.dao.query("tduhb07.queryInfo", qsitemap, oloffset, ollimit);
			    List<Map<String, String>> mnList = getAllOnlineDataLastTime(sitelist);
			    if(mnList.size() > 0) {
			    	for (int i = 0; i < mnList.size(); i++) {		
			    		Map<String, String> tabledatemap = mnList.get(i);
			    		outInfo = getDataByFactors(outInfo, olinfoBlock, "2011", tabledatemap);
			    	}
			    }
			    EiBlock block = outInfo.getBlock("oldata");
			    block.set("offset",oloffset);
                block.set("limit",ollimit);
                block.set("count",sitelist.size());
        	}

			outInfo.setMsg("数据加载完成");
        }
        catch(Exception ex)
        {
        	outInfo.setStatus(-1);
        	outInfo.setMsg(ex.toString());
        }
		return outInfo;
	}
    
  //根据站点查询监测因子
    private EiBlock getFactorsBySite(String monitorid)
    {
        EiBlock block = new EiBlock("olinfo");
        EiBlockMeta metadata = new EiBlockMeta();		 
		EiColumn eiColumn = new EiColumn("factorid");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("mnid");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("highlimit");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("lowlimit");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("monitorid");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("factorname");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("unit");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("factordesc");
		metadata.addMeta(eiColumn);
		block.setBlockMeta(metadata); 
		List<Map> listdata = new ArrayList<>();
        try {
        	 String factorSql = "select t1.FACTORID,t2.FACTORNAME,t2.UNIT from " + DbSchema + ".t_ha_olshowfactor t1 " + 
        	 " LEFT JOIN " + DbSchema + ".t_ha_factor t2 on t1.FACTORID = t2.FACTORID where t1.MONITORID = '" + monitorid + "' and t1.STATUS = '1' ";
             Map sqlmap = new HashMap();
             sqlmap.put("sqlMap", factorSql);
             List list = this.dao.query("DUHC20.query", sqlmap);
             if (list != null && list.size() > 0)
             {
             	for (int i = 0; i < list.size(); i++) {									
             		Map mapdata = (HashMap)list.get(i);
            		Map map = new HashMap();
            		map.put("factorid", StrUtil.trimToString(mapdata.get("FACTORID")));
            		map.put("mnid", "");
            		map.put("highlimit", "");
            		map.put("lowlimit", "");
            		map.put("monitorid", monitorid);
            		map.put("factorname", StrUtil.trimToString(mapdata.get("FACTORNAME")));
            		map.put("unit", StrUtil.trimToString(mapdata.get("UNIT")));
            		map.put("factordesc", StrUtil.trimToString(mapdata.get("FACTORNAME")) + "(" + StrUtil.trimToString(mapdata.get("UNIT")) + ")");
            		listdata.add(map);
             	}
             }
             block.setRows(listdata);
        }
        catch (Exception ex)
        {
            throw ex;
        }
        return block;
    }
    
  //根据因子信息绑定grid头信息
    private EiBlock getDataHeaderByFactors(EiBlock factorsBlock)   {
        EiBlock block = new EiBlock("oldata");
        block.set("count",1);
        EiBlockMeta metadata = new EiBlockMeta();
        EiColumn eiColumn = null;
        eiColumn = new EiColumn("siteid");
		eiColumn.setDescName("监测点ID");
		eiColumn.setVisible(false);
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("sitename");
		eiColumn.setDescName("监测点名称");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("datatime");
		eiColumn.setDescName("日期");
		metadata.addMeta(eiColumn);
        eiColumn = new EiColumn("offline");
		eiColumn.setVisible(false);
		metadata.addMeta(eiColumn);
        
        try   {
            if (factorsBlock != null && factorsBlock.getRowCount() > 0)   {
                String temMN = StrUtil.trimToString(factorsBlock.getRow(0).get("mnid"));
                for (int i = 0; i < factorsBlock.getRowCount(); i++) {
                    String temfactorid = StrUtil.trimToString(factorsBlock.getRow(i).get("factorid"));
                    String temfactorname = StrUtil.trimToString(factorsBlock.getRow(i).get("factorname"));
                    eiColumn = new EiColumn("ol" + temfactorid);
                    if(StringUtils.isNotBlank(StrUtil.trimToString(factorsBlock.getRow(i).get("unit")))) {
                    	temfactorname = temfactorname + "(" + StrUtil.trimToString(factorsBlock.getRow(i).get("unit")) + ")";
                    }
                    eiColumn.setDescName(temfactorname);
            		metadata.addMeta(eiColumn);
            		eiColumn = new EiColumn("ol" + temfactorid + "limit");
            		eiColumn.setVisible(false);
            		metadata.addMeta(eiColumn);
                }
                block.setBlockMeta(metadata); 
            }
        }
        catch (Exception e)
        {
            throw e;
        }
        return block;
    }
    
  //加载在线数据
    private EiInfo getDataByFactors(EiInfo outInfo, EiBlock factorsBlock, String datatype,
                                    Map<String, String> tabledatemap) throws Exception  {
        EiBlock block = outInfo.getBlock("oldata");
        List<Map> listdata = new ArrayList<>();
        try  {
        	String temsiteid = StrUtil.trimToString(tabledatemap.get("siteid"));
    		String temsitename = StrUtil.trimToString(tabledatemap.get("sitename"));
        	if(tabledatemap != null && !StringUtils.isBlank(tabledatemap.get("tablename"))) {
                SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        		if (factorsBlock != null && factorsBlock.getRowCount() > 0)
                {
                    String factorSql = "select ";
                    for (int i = 0; i < factorsBlock.getRowCount(); i++)
                    {
                        String temfactorid = StrUtil.trimToString(factorsBlock.getRow(i).get("factorid"));
                        //实时值
                        if ("2011".equals(datatype))
                        {
                            factorSql += "max( CASE WHEN t5.FACTORID = '" + temfactorid + "' THEN t5.RTD END ) T" + temfactorid.trim() + ",max( CASE WHEN t5.FACTORID = '" + temfactorid + "' THEN t5.ZSRTD END ) TZS" + temfactorid.trim() + ",";
                        }
                        else
                        {
                            factorSql += "max( CASE WHEN t5.FACTORID = '" + temfactorid + "' THEN t5.AVG END ) T" + temfactorid.trim() + ",max( CASE WHEN t5.FACTORID = '" + temfactorid + "' THEN t5.ZSAVG END ) TZS" + temfactorid.trim() + ",";
                        }
                    }
                    factorSql += "t5.DATATIME from (";

                    factorSql += getDataSqlByType(tabledatemap.get("mn"), datatype, tabledatemap.get("tablename"), tabledatemap.get("datetime"));

                    factorSql += ") t5 group by t5.DATATIME order by t5.DATATIME desc ";

                    Map sqlmap = new HashMap();
                    sqlmap.put("sqlMap", factorSql);

                    List list = this.dao.query("DUHC20.query", sqlmap);
                    
                    if (list != null && list.size() > 0)
                    {
                    	for (int i = 0; i < list.size(); i++) {									
                    		Map mapdata = (HashMap)list.get(i);
                    		Map map = new HashMap();
                    		map.put("siteid", temsiteid);
                    		map.put("sitename", temsitename);
							
                    		String temdatatime = StrUtil.trimToString(mapdata.get("DATATIME"));
                    		map.put("datatime", temdatatime);
                    		if(!"".equals(temdatatime)) {
                    			Date oldDateTime = dateTimeFormat.parse(temdatatime);
                    			Long dateSpan = (new Date().getTime() - oldDateTime.getTime())/60000;
                        		if(dateSpan >= 60) {//120分钟没数据则离线
                        			map.put("offline", "1");
                        		}else {
                        			map.put("offline", "0");
                        		}
                    		}else {
                    			map.put("offline", "1");
                    		}
                    		for (int j = 0; j < factorsBlock.getRowCount(); j++)
                            {
                                String temfactorid = StrUtil.trimToString(factorsBlock.getRow(j).get("factorid"));
                                Map<String, String> limitmap = getFactorLimitBySite(temfactorid, temsiteid);
                                if("1".equals(limitmap.get("usezs"))) {
                                	map.put("ol" + temfactorid, StrUtil.trimToString(mapdata.get("TZS" + temfactorid.toUpperCase())));
                                }else {
                                	map.put("ol" + temfactorid, StrUtil.trimToString(mapdata.get("T" + temfactorid.toUpperCase())));
                                }
                                map.put("ol" + temfactorid + "limit", limitmap.get("limit"));
                            }
                    		listdata.add(map);
                    	}
                    }else {
                		Map map = new HashMap();
                		map.put("siteid", temsiteid);
                		map.put("sitename", temsitename);
                		map.put("datatime", "");
                        map.put("offline", "1");
                		for (int j = 0; j < factorsBlock.getRowCount(); j++)
                        {
                            String temfactorid = StrUtil.trimToString(factorsBlock.getRow(j).get("factorid"));
                            map.put("ol" + temfactorid, "");
                            map.put("ol" + temfactorid + "limit", "");
                        }
                		listdata.add(map);
                    }
                }
               
        	}else {
        		Map map = new HashMap();
        		map.put("siteid", temsiteid);
        		map.put("sitename", temsitename);
        		map.put("datatime", "");
                map.put("offline", "1");
        		for (int j = 0; j < factorsBlock.getRowCount(); j++)
                {
                    String temfactorid = StrUtil.trimToString(factorsBlock.getRow(j).get("factorid"));
                    map.put("ol" + temfactorid, "");
                    map.put("ol" + temfactorid + "limit", "");
                }
        		listdata.add(map);
        	}
        	block.addRows(listdata);
        	outInfo.setBlock(block);
        }
        catch (Exception e)
        {
        	outInfo.setBlock(block);
            throw e;
        }
        return outInfo;
    }
    
    //根据因子和站点获取对应的限值
    private Map<String,String>  getFactorLimitBySite(String factorid, String siteid){
    	Map<String,String> map = new HashMap<String, String>();
		map.put("limit", "");
		map.put("usezs", "");
    	try {

    		String factorSql = "select  t3.*,t4.USEZS from " + DbSchema + ".t_ha_portfactor t3 " + 
    		" inner join (select t1.FACTORID,t1.USEZS from " + DbSchema + ".T_HA_SITEFACTOR t1 where t1.TYPE='2' AND t1.SITEID = '" + siteid + "' and t1.FACTORID = '" + factorid + "') t4 on t4.FACTORID = t3.FACTORID " + 
    		" where t3.PORTID = (select max(t2.PORTID) from " + DbSchema + ".t_ha_site t2 where t2.SITEID = '" + siteid + "') ";
			
			Map sqlmap = new HashMap();
			sqlmap.put("sqlMap", factorSql);
			
			List list = this.dao.query("DUHC20.query", sqlmap);
			if (list != null && list.size() > 0) {
				 String temhighlimit = StrUtil.trimToString(((HashMap)list.get(0)).get("HIGHLIMIT"));
				 String temlowlimit = StrUtil.trimToString(((HashMap)list.get(0)).get("LOWLIMIT"));
				 String temusezs = StrUtil.trimToString(((HashMap)list.get(0)).get("USEZS"));
				 if(!StringUtils.isBlank(temhighlimit) && !StringUtils.isBlank(temlowlimit)) {
					 map.put("limit", temlowlimit + "-" + temhighlimit);
				 }else {
					 map.put("limit", temhighlimit);
				 }
				 map.put("usezs", temusezs);
			}
		}
		catch( Exception ex )
	     {
	        throw ex;
	     }
    	return map;
    }
    
    private Tduhb07 getSiteById(String siteid) 
	{
		try {
			if(StringUtils.isBlank(siteid)) {
				siteid = "空";
			}
			Tduhb07 tduhb07 = null;
			Map<String, String> map = new HashMap<String, String>();
			map.put("siteid", siteid);
	        List list = this.dao.query("tduhb07.query", map);
	        if(list.size() > 0) {
	        	tduhb07 = (Tduhb07)list.get(0);
	        }
			return tduhb07;
		}
		catch( Exception ex )
	     {
	        throw ex;
	     }
	}
    
    //返回所有配置的 MNID 在线数据最近一次数据的时间集
    private List<Map<String, String>> getAllOnlineDataLastTime(List sitelist){
    	List<Map<String, String>> list = new ArrayList<Map<String, String>>();
    	try {
    		if(sitelist != null && sitelist.size() > 0) {
    			for (int i = 0; i < sitelist.size(); i++) {	
    				Map<String, String> map = new HashMap<String, String>();
		    		String temmn = StrUtil.trimToString(((Tduhb07)sitelist.get(i)).getMnid());
		    		String temsiteid = StrUtil.trimToString(((Tduhb07)sitelist.get(i)).getSiteid());
		    		String temsitename = StrUtil.trimToString(((Tduhb07)sitelist.get(i)).getSitename());
		    		Map<String, String> tablemap = getOnlineDataLastTimeByMN(temmn,"2011");
		    		map.put("mn", temmn);
		    		map.put("siteid", temsiteid);
		    		map.put("sitename", temsitename);
		    		if(tablemap != null && tablemap.size() > 0) {
		    			map.put("tablename", tablemap.get("tablename"));
		    			map.put("datetime", tablemap.get("datetime"));
		    		}else {
		    			map.put("tablename", "");
		    			map.put("datetime", "");
		    		}
		    		list.add(map);
		    	}
    		}
    	}
    	catch (Exception e)
        {
            throw e;
        }
    	return list;
    }
    
    //返回某个 MNID 在线数据最近一次数据的时间
    private Map<String, String> getOnlineDataLastTimeByMN(String mnid, String datatype)
    {
    	Map<String, String> map = new HashMap<String, String>();
    	String tableSql = "select NAME from sysibm.systables  where type = 'T' and creator like '%" + DbSchema.toUpperCase() + "%' and NAME like '%DATATABLE_MIN_%' order by NAME desc ";
    	switch (datatype)
        {
            case "2011":
            	tableSql = "select NAME from sysibm.systables  where type = 'T' and creator like '%" + DbSchema.toUpperCase() + "%' and NAME like '%DATATABLE_RTD_%' order by NAME desc ";
                break;
            case "2051":
            	tableSql = "select NAME from sysibm.systables  where type = 'T' and creator like '%" + DbSchema.toUpperCase() + "%' and NAME like '%DATATABLE_MIN_%' order by NAME desc ";
                break;
            case "2061":
            	tableSql = "select NAME from sysibm.systables  where type = 'T' and creator like '%" + DbSchema.toUpperCase() + "%' and NAME like '%DATATABLE_HOUR_%' order by NAME desc ";
                break;
            case "2031":
            	tableSql = "select NAME from sysibm.systables  where type = 'T' and creator like '%" + DbSchema.toUpperCase() + "%' and NAME like '%DATATABLE_DAY_%' order by NAME desc ";
                break;
        }
    	
    	Map sqlmap = new HashMap();
        sqlmap.put("sqlMap", tableSql);
        List list = this.dao.query("DUHC20.query", sqlmap);
        if (list != null && list.size() > 0)
        {
        	for (int i = 0; i < list.size(); i++) {									
        		String temtablename = StrUtil.trimToString(((HashMap)list.get(i)).get("NAME"));
        		String dateSql = "select  DATATIME from " + DbSchema + "." + temtablename + " where MN='" + mnid + "'  ORDER BY datatime desc";
        		sqlmap.put("sqlMap", dateSql);
                List datelist = this.dao.query("DUHC20.query", sqlmap);
                if(datelist != null && datelist.size() > 0) {
                	String datetime = StrUtil.trimToString(((HashMap)datelist.get(0)).get("DATATIME"));
                	map.put("tablename", temtablename);
	    			map.put("datetime", datetime);
                	break;
                }
        	}
        }
    	return map;
    }
    
    public String getDataSqlByType(String mn, String datatype, String tablename, String datetime) throws Exception
    {
    	String sql = "";
    	try {
    		
            switch (datatype)
            {
                case "2011":
                	sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.RTD,t1.ZSRTD from " + DbSchema + "." + tablename + " t1 where t1.MN = '" + mn + "' and t1.DATATIME = '" + datetime + "' ";
                    break;
                case "2051":
                	sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.AVG,t1.ZSAVG from " + DbSchema + "." + tablename + " t1 where t1.MN = '" + mn + "' and t1.DATATIME = '" +datetime + "' ";
                    break;
                case "2061":
                	sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.AVG,t1.ZSAVG from " + DbSchema + "." + tablename + " t1 where t1.MN = '" + mn + "' and t1.DATATIME = '" + datetime + "' ";
                    break;
                case "2031":
                	sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.AVG,t1.ZSAVG from " + DbSchema + "." + tablename + " t1 where t1.MN = '" + mn + "' and t1.DATATIME = '" + datetime + "' ";
                    break;
            }
    	}
    	catch (Exception e)
        {
            throw e;
        }
        return sql;
    }
    
}
