/**
 *
 */
package com.baosight.sggk.du.hb.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.sggk.util.PermissionUtil;


public class ServiceDUHB9901 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHB9901.class);

	//获取配置文件里的参数
	ResourceBundle dbPro = ResourceBundle.getBundle("application");
	String DbSchema = dbPro.getString("hbSchema");
	
	//API判断是否登陆
	public EiInfo checkUserInfo(EiInfo inInfo) {

		String loginName = inInfo.getString("loginName");
		String password = inInfo.getString("password");
		EiInfo eiInfo = new EiInfo();
		eiInfo.set(EiConstant.serviceId,"S_XS_08");
		eiInfo.set("loginName",loginName);
		eiInfo.set("password",password);
		EiInfo outInfo = XServiceManager.call(eiInfo);
		return outInfo;
	}
	
	//API获取厂部的国控点超标信息
	public EiInfo getAllDepList(EiInfo inInfo)
	{
		String loginName = inInfo.getString("loginName");

		EiInfo outInfo = new EiInfo();
		EiBlock block = new EiBlock("departinfo");
        EiBlockMeta metadata = new EiBlockMeta();		 
		EiColumn eiColumn = new EiColumn("departid");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("departname");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("offline");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("overline");
		metadata.addMeta(eiColumn);
		block.setBlockMeta(metadata); 
		outInfo.setBlock(block);
		outInfo.set("total", 0);
		try {
			String status = PermissionUtil.getUserDepart(this.dao, loginName);
			if("".equals(status)) {
				outInfo.setMsg("查询出错");
				outInfo.setStatus(-1);
				return outInfo;
			}else {
				//默认查询一段时间的数据，进行比较是否存在数据
	            Calendar startcalendar = Calendar.getInstance();
	        	startcalendar.add(Calendar.HOUR_OF_DAY, -4);
	        	Calendar endcalendar = Calendar.getInstance();	
	        	Map<String, String> compareMap =  getAllDataSqlByDateSpan("2061",startcalendar,endcalendar);
	        	Map<String, String> limitMap =  getAllFactoLimit();
	        	
	        	String sql;
	        	if("%".equals(status)) {
	        		sql = "select distinct DEPARTMENTID,DEPARTMENTNAME from "+DbSchema+".VIEW_T_HA_SITE_EX1 where ISONLINE = '1' and STATUS = '1' and COUNTRYPOINT = '1' ";
	        	}else {
	        		sql = "select distinct DEPARTMENTID,DEPARTMENTNAME from "+DbSchema+".VIEW_T_HA_SITE_EX1 where ISONLINE = '1' and STATUS = '1' and COUNTRYPOINT = '1' and DEPARTMENTID = '"+status+"'  ";
	        	}
	        	Map sqlmap = new HashMap();
	            sqlmap.put("sqlMap", sql);
	            List departlist = this.dao.query("DUHC20.query", sqlmap);
	            if(departlist.size() > 0) {
			    	for (int i = 0; i < departlist.size(); i++) {		
			    		Map<String,String> map = (HashMap<String,String>)departlist.get(i);
			    		outInfo = getDepartData(outInfo, map, compareMap, limitMap);
			    	}
			    }
	            outInfo.setMsg("查询成功");
			}
		} catch (Exception e) {
			outInfo.setMsg("查询出错:" + e.toString());
			outInfo.setStatus(-1);
		}
		return outInfo;
	}
	
	public EiInfo getDepInfo(EiInfo inInfo)
	{
		String loginName = inInfo.getString("loginName");

		EiInfo outInfo = new EiInfo();
		EiBlock block = new EiBlock("departinfo");
      EiBlockMeta metadata = new EiBlockMeta();		 
		EiColumn eiColumn = new EiColumn("departid");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("departname");
		metadata.addMeta(eiColumn);
		block.setBlockMeta(metadata); 
		
		try {
			String status = PermissionUtil.getUserDepart(this.dao, loginName);
			if("".equals(status)) {
				outInfo.setMsg("查询出错");
				outInfo.setStatus(-1);
				return outInfo;
			}else {
	
	        	String sql;
	        	if("%".equals(status)) {
	        		sql = "select distinct DEPARTMENTID,DEPARTMENTNAME from "+DbSchema+".VIEW_T_HA_SITE_EX1 where ISONLINE = '1' and STATUS = '1' ";
	        	}else {
	        		sql = "select distinct DEPARTMENTID,DEPARTMENTNAME from "+DbSchema+".VIEW_T_HA_SITE_EX1 where ISONLINE = '1' and STATUS = '1' and DEPARTMENTID = '"+status+"'  ";
	        	}
	        	Map sqlmap = new HashMap();
	            sqlmap.put("sqlMap", sql);
	            List departlist = this.dao.query("DUHC20.query", sqlmap);
	            if(departlist.size() > 0) {
			    	for (int i = 0; i < departlist.size(); i++) {		
			    		Map<String,String> map = (HashMap<String,String>)departlist.get(i);
			    		Map<String,String> datamap = new HashMap<String,String>();
			    		datamap.put("departid", map.get("DEPARTMENTID"));
			    		datamap.put("departname", map.get("DEPARTMENTNAME"));
			    		block.addRow(datamap);
			    	}
			    }
	            
	            outInfo.setMsg("查询成功");
			}
		} catch (Exception e) {
			outInfo.setMsg("查询出错:" + e.toString());
			outInfo.setStatus(-1);
		}
		outInfo.setBlock(block);
		return outInfo;
	}
	
	//API获取监测点信息根据厂部id加载
	public EiInfo getDepSiteList(EiInfo inInfo)
	{
		String departid = inInfo.getString("departid");
		
		EiInfo outInfo = new EiInfo();
		EiBlock fqblock = new EiBlock("fqinfo");
		EiBlock fsblock = new EiBlock("fsinfo");
        EiBlockMeta metadata = new EiBlockMeta();		 
		EiColumn eiColumn = new EiColumn("siteid");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("sitename");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("monitorid");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("offline");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("overline");
		metadata.addMeta(eiColumn);
		fqblock.setBlockMeta(metadata); 
		fsblock.setBlockMeta(metadata); 
		outInfo.setBlock(fqblock);
		outInfo.setBlock(fsblock);
		try {
			//默认查询一段时间的数据，进行比较是否存在数据
            Calendar startcalendar = Calendar.getInstance();
        	startcalendar.add(Calendar.HOUR_OF_DAY, -4);
        	Calendar endcalendar = Calendar.getInstance();	
        	Map<String, String> compareMap =  getAllDataSqlByDateSpan("2061",startcalendar,endcalendar);
        	Map<String, String> limitMap =  getAllFactoLimit();
        	
			String sql = "select * from "+DbSchema+".VIEW_T_HA_SITE_EX1 where ISONLINE = '1' and STATUS = '1' and COUNTRYPOINT = '1' and DEPARTMENTID = '"+departid+"' ";
			Map sqlmap = new HashMap();
            sqlmap.put("sqlMap", sql);
            List sitelist = this.dao.query("DUHC20.query", sqlmap);
            if(sitelist.size() > 0) {
		    	for (int i = 0; i < sitelist.size(); i++) {		
		    		Map tabledatemap = (HashMap)sitelist.get(i);
		    		EiBlock olinfoBlock = getFactorsByMonitor(ObjToString(tabledatemap.get("MONITORID")));
		    		outInfo = getSiteDataByDateSpan(outInfo, olinfoBlock, "2061", tabledatemap, compareMap, limitMap);
		    	}
		    }
            outInfo.setMsg("查询成功");
		} catch (Exception e) {
			outInfo.setMsg("查询出错:" + e.toString());
			outInfo.setStatus(-1);
		}
		return outInfo;
	}	
	
	//API获取污染物信息根据监测点id加载
		public EiInfo getSiteFactorList(EiInfo inInfo)
		{
			String siteid = inInfo.getString("siteid");
			
			EiInfo outInfo = new EiInfo();
			
			
			try {
				
	            Calendar startcalendar = Calendar.getInstance();
	        	startcalendar.set(Calendar.HOUR_OF_DAY,0);
	        	startcalendar.set(Calendar.MINUTE,0);
	        	startcalendar.set(Calendar.SECOND,0);
	        	Calendar endcalendar = Calendar.getInstance();	
	        	
	        	EiBlock siteblock = getOneSiteFactoLimit(siteid);
				outInfo.setBlock(siteblock);
				if(siteblock !=null && siteblock.getRows().size() > 0) {
					String mnid = ObjToString(siteblock.getRow(0).get("mnid"));
					outInfo =  getOneSiteDataSqlByDateSpan(outInfo,"2061",startcalendar,endcalendar,mnid);
				}
	            outInfo.setMsg("查询成功");
			} catch (Exception e) {
				outInfo.setMsg("查询出错:" + e.toString());
				outInfo.setStatus(-1);
			}
			return outInfo;
		}	
	
	
	
	
	
	
	
	
	private EiInfo getDepartData(EiInfo outInfo, Map<String,String> depart, Map<String, String> compareMap, Map<String, String> limitMap) throws Exception
	{ 
		Integer total = Integer.valueOf(ObjToString(outInfo.get("total")));
		Map<String,String> map = new HashMap<String,String>();
		map.put("departid", depart.get("DEPARTMENTID"));
		map.put("departname", depart.get("DEPARTMENTNAME"));
		map.put("offline", "0");
		map.put("overline", "0");
		String sql = "select * from "+DbSchema+".VIEW_T_HA_SITE_EX1 where ISONLINE = '1' and STATUS = '1' and COUNTRYPOINT = '1' and DEPARTMENTID = '"+depart.get("DEPARTMENTID")+"' ";
		Map sqlmap = new HashMap();
        sqlmap.put("sqlMap", sql);
        List sitelist = this.dao.query("DUHC20.query", sqlmap);
        if(sitelist.size() > 0) {
        	Integer sum = 0;
	    	for (int i = 0; i < sitelist.size(); i++) {		
	    		Map tabledatemap = (HashMap)sitelist.get(i);
	    		EiBlock olinfoBlock = getFactorsByMonitor(ObjToString(tabledatemap.get("MONITORID")));
	    		Integer count = getDataByDateSpan(olinfoBlock, "2061", tabledatemap, compareMap, limitMap);
	    		sum += count;
	    	}
	    	map.put("overline", ObjToString(sum));
	    	total = total + sum;
	    	outInfo.set("total", total);
	    }
        EiBlock block = outInfo.getBlock("departinfo");
        block.addRow(map);
        outInfo.setBlock(block);
		return outInfo;
	}
	
	//获取某段时间内在线数据sql语句
    private Map<String, String> getAllDataSqlByDateSpan(String datatype, Calendar startcalendar, Calendar endcalendar) throws Exception
    {
    	Map<String, String> compareMap =  new HashMap<String, String>();
    	String sql = "";
    	SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	SimpleDateFormat tableFormat = new SimpleDateFormat("yyyyMM");
        switch (datatype)
        {
            case "2011":
                if (startcalendar.get(Calendar.MONTH) == endcalendar.get(Calendar.MONTH))
                {
                    sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.RTD,t1.ZSRTD from " + DbSchema + ".DATATABLE_RTD_" + tableFormat.format(startcalendar.getTime()) + " t1 where t1.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t1.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
                }
                else
                {
                    sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.RTD,t1.ZSRTD from " + DbSchema + ".DATATABLE_RTD_" + tableFormat.format(startcalendar.getTime()) + " t1 where t1.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t1.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
                    sql += " union select t2.MN,t2.FACTORID,t2.DATATIME,t2.RTD,t2.ZSRTD from " + DbSchema + ".DATATABLE_RTD_" + tableFormat.format(endcalendar.getTime()) + " t2 where and t2.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t2.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
                }
                break;
            case "2051":
            	if (startcalendar.get(Calendar.MONTH) == endcalendar.get(Calendar.MONTH))
                {
            		sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.AVG,t1.ZSAVG from " + DbSchema + ".DATATABLE_MIN_" + tableFormat.format(startcalendar.getTime()) + " t1 where t1.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t1.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
                }
                else
                {
                	sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.AVG,t1.ZSAVG from " + DbSchema + ".DATATABLE_MIN_" + tableFormat.format(startcalendar.getTime()) + " t1 where t1.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t1.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
                    sql += " union select t2.MN,t2.FACTORID,t2.DATATIME,t2.AVG,t2.ZSAVG from " + DbSchema + ".DATATABLE_MIN_" + tableFormat.format(endcalendar.getTime()) + " t2 where t2.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t2.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
                }
                break;
            case "2061":
                if (startcalendar.get(Calendar.YEAR) == endcalendar.get(Calendar.YEAR))
                {
                    sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.AVG,t1.ZSAVG from " + DbSchema + ".DATATABLE_HOUR_" + startcalendar.get(Calendar.YEAR) + " t1 where t1.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t1.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
                }
                else
                {
                    sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.AVG,t1.ZSAVG from " + DbSchema + ".DATATABLE_HOUR_" + startcalendar.get(Calendar.YEAR) + " t1 where t1.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t1.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
                    sql += " union select t2.MN,t2.FACTORID,t2.DATATIME,t2.AVG,t2.ZSAVG from " + DbSchema + ".DATATABLE_HOUR_" + endcalendar.get(Calendar.YEAR) + " t2 where t2.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t2.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
                }
                break;
            case "2031":
                if (startcalendar.get(Calendar.YEAR) == endcalendar.get(Calendar.YEAR))
                {
                    sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.AVG,t1.ZSAVG from " + DbSchema + ".DATATABLE_DAY_" + startcalendar.get(Calendar.YEAR) + " t1 where t1.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t1.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
                }
                else
                {
                    sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.AVG,t1.ZSAVG from " + DbSchema + ".DATATABLE_DAY_" + startcalendar.get(Calendar.YEAR) + " t1 where t1.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t1.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "'";
                    sql += " union select t2.MN,t2.FACTORID,t2.DATATIME,t2.AVG,t2.ZSAVG from " + DbSchema + ".DATATABLE_DAY_" + endcalendar.get(Calendar.YEAR) + " t2 where t2.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t2.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
                }
                break;
        }
        Map sqlmap = new HashMap();
        sqlmap.put("sqlMap", sql);
       
        List datalist = this.dao.query("DUHC20.query", sqlmap,0,-999999);
        
        
        if (datalist != null && datalist.size() > 0)
        {
        	for (int i = 0; i < datalist.size(); i++) {									
        		Map mapdata = (HashMap)datalist.get(i);
        		String temMN = ObjToString(mapdata.get("MN"));
        		String temfactorid = ObjToString(mapdata.get("FACTORID"));
        		String temmnfactor = temMN + "&" + temfactorid;
        		if(!compareMap.containsKey(temmnfactor)) {
        			
        			if("2011".equals(datatype)) {
        				String temavg = ObjToString(mapdata.get("RTD")) + "&" + ObjToString(mapdata.get("ZSRTD"));
        				compareMap.put(temmnfactor, temavg);
        			}else {
        				String temavg = ObjToString(mapdata.get("AVG")) + "&" + ObjToString(mapdata.get("ZSAVG"));
        				compareMap.put(temmnfactor, temavg);
        			}
        		}
        	}
        }
        return compareMap;
    }
    
    private Map<String, String> getAllFactoLimit() throws Exception
    {
    	Map<String, String> limitMap =  new HashMap<String, String>();
    	String sql = "select t1.FACTORID,t1.SITEID,t1.USEZS,t3.HIGHLIMIT,t3.LOWLIMIT from " + DbSchema + ".T_HA_SITEFACTOR t1 left join " + DbSchema + ".T_HA_SITE t2 on t1.SITEID = t2.SITEID left join " + DbSchema + ".T_HA_PORTFACTOR t3 on t2.PORTID = t3.PORTID and t1.FACTORID = t3.FACTORID where t2.ISONLINE = '1' and t2.STATUS = '1' ";
        Map sqlmap = new HashMap();
        sqlmap.put("sqlMap", sql);
        List datalist = this.dao.query("DUHC20.query", sqlmap,0,-999999);
        
        if (datalist != null && datalist.size() > 0)
        {
        	for (int i = 0; i < datalist.size(); i++) {									
        		Map mapdata = (HashMap)datalist.get(i);
        		String temmnkey = ObjToString(mapdata.get("SITEID")) + "&" + ObjToString(mapdata.get("FACTORID"));
        		String temmnvalue = ObjToString(mapdata.get("USEZS")) + "&" + ObjToString(mapdata.get("HIGHLIMIT")) + "&" + ObjToString(mapdata.get("LOWLIMIT")  + "&");
        		limitMap.put(temmnkey, temmnvalue);
        	}
        }
        return limitMap;
    }
    
  //根据站点查询监测因子
    private EiBlock getFactorsByMonitor(String monitorid) throws Exception
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
        		map.put("factorid", ObjToString(mapdata.get("FACTORID")));
        		map.put("mnid", "");
        		map.put("highlimit", "");
        		map.put("lowlimit", "");
        		map.put("monitorid", monitorid);
        		map.put("factorname", ObjToString(mapdata.get("FACTORNAME")));
        		map.put("unit", ObjToString(mapdata.get("UNIT")));
        		map.put("factordesc", ObjToString(mapdata.get("FACTORNAME")) + "(" + ObjToString(mapdata.get("UNIT")) + ")");
        		listdata.add(map);
         	}
         }
         block.setRows(listdata);
        return block;
    }
    
  //加载在线数据根据时间段
    private Integer getDataByDateSpan(EiBlock factorsBlock, String datatype, Map tabledatemap, Map<String, String> compareMap, Map<String, String> limitMap) throws Exception
    {
    	Integer count = 0;
        String temmn = ObjToString(tabledatemap.get("MNID"));
        String temsiteid = ObjToString(tabledatemap.get("SITEID"));
		if (factorsBlock != null && factorsBlock.getRowCount() > 0)
        {
            for (int i = 0; i < factorsBlock.getRowCount(); i++)
            {
            	String temfactorid = ObjToString(factorsBlock.getRow(i).get("factorid"));
                if(compareMap.containsKey(temmn + "&" + temfactorid)) {
                	String avg = compareMap.get(temmn + "&" + temfactorid);
                	if(limitMap.containsKey(temsiteid + "&" + temfactorid)) {
                		String overData = limitMap.get(temsiteid + "&" + temfactorid);
                    	String[] overDataList = overData.split("&",-1);
                    	String temhighlimit = overDataList[1];
    	       			String temlowlimit = overDataList[2];
    	       			String temusezs = overDataList[0];
    	       			if(!StringUtils.isBlank(temhighlimit)) {
    	       				 Double highvalue = ObjToDouble(temhighlimit);
    	       				 
    	       				 String avgStr = "1".equals(temusezs) ? avg.substring((avg.indexOf("&")+1)) : avg.substring(0,avg.indexOf("&"));
    	       				 Double temavg = ObjToDouble(avgStr);
    	       				 if(!StringUtils.isBlank(temlowlimit) && !"null".equals(temhighlimit)) {
    	       					 Double lowvalue = ObjToDouble(temlowlimit);
    	       					 if(temavg >= highvalue || temavg <= lowvalue) {
    	       						count = count + 1;
    	       					 }
    	       				 }else {
    	       					 if(temavg >= highvalue) {
    	       						count = count + 1;
    	       					 }
    	       				 }
    	       			 }
                	}
                }
            }
        }
        return count;
    }
    
    private String ObjToString(Object obj) {
		try {
			return obj == null ? "" : obj.toString();
		} catch (Exception ex) {
			return "";
		}
	}
    
    private Double ObjToDouble(Object obj) {
		try {
			return Double.parseDouble(ObjToString(obj));
		} catch (Exception ex) {
			return 0d;
		}
	}
    
    //加载在线数据根据时间段
    private EiInfo getSiteDataByDateSpan(EiInfo outInfo, EiBlock factorsBlock, String datatype, Map tabledatemap, Map<String, String> compareMap, Map<String, String> limitMap) throws Exception
    {
        String temmn = ObjToString(tabledatemap.get("MNID"));
        String temsiteid = ObjToString(tabledatemap.get("SITEID"));
        String temsitename = ObjToString(tabledatemap.get("SITENAME"));
        Map<String,String> map = new HashMap<String,String>();
		map.put("siteid", temsiteid);
		map.put("sitename", temsitename);
		map.put("monitorid", ObjToString(tabledatemap.get("MONITORID")));
		map.put("offline", "1");
		map.put("overline", "0");
		if (factorsBlock != null && factorsBlock.getRowCount() > 0)
        {
            for (int i = 0; i < factorsBlock.getRowCount(); i++)
            {
            	String temfactorid = ObjToString(factorsBlock.getRow(i).get("factorid"));
                if(compareMap.containsKey(temmn + "&" + temfactorid)) {
                	map.put("offline", "0");
                	String avg = compareMap.get(temmn + "&" + temfactorid);
                	if(limitMap.containsKey(temsiteid + "&" + temfactorid)) {
                		String overData = limitMap.get(temsiteid + "&" + temfactorid);
                    	String[] overDataList = overData.split("&",-1);
                    	String temhighlimit = overDataList[1];
    	       			String temlowlimit = overDataList[2];
    	       			String temusezs = overDataList[0];
    	       			if(!StringUtils.isBlank(temhighlimit) && !"null".equals(temhighlimit)) {
    	       				 Double highvalue = ObjToDouble(temhighlimit);
    	       				 
    	       				 String avgStr = "1".equals(temusezs) ? avg.substring((avg.indexOf("&")+1)) : avg.substring(0,avg.indexOf("&"));
    	       				 Double temavg = ObjToDouble(avgStr);
    	       				 if(!StringUtils.isBlank(temhighlimit)) {
    	       					 Double lowvalue = ObjToDouble(temlowlimit);
    	       					 if(temavg >= highvalue || temavg <= lowvalue) {
    	       						map.put("overline", "1");
    	       					 }
    	       				 }else {
    	       					 if(temavg >= highvalue) {
    	       						map.put("overline", "1");
    	       					 }
    	       				 }
    	       			 }
                	}
                }
            }
        }
		if("01".equals(ObjToString(tabledatemap.get("MONITORID")))) {
			EiBlock fqblock = outInfo.getBlock("fqinfo");
			fqblock.addRow(map);
			outInfo.setBlock(fqblock);
		}
        if("02".equals(ObjToString(tabledatemap.get("MONITORID")))) {
        	EiBlock fsblock = outInfo.getBlock("fsinfo");
        	fsblock.addRow(map);
			outInfo.setBlock(fsblock);
		}
        return outInfo;
    }
  
    
    //根据监测点获取限值
    private EiBlock getOneSiteFactoLimit(String siteid) throws Exception
    {
    	EiBlock siteblock = new EiBlock("factorlist");
		EiBlockMeta metadata = new EiBlockMeta();		 
		EiColumn eiColumn = new EiColumn("siteid");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("sitename");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("mnid");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("factorid");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("factorname");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("factordesc");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("datatime");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("avg");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("zsavg");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("usezs");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("highlimit");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("lowlimit");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("unit");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("isover");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("limitdesc");
		metadata.addMeta(eiColumn);
		siteblock.setBlockMeta(metadata); 
		Map<String, String> map;
    	String sql = "select t1.FACTORID,t1.SITEID,t2.SITENAME,t2.MNID,t1.USEZS,t3.HIGHLIMIT,t3.LOWLIMIT,t4.FACTORNAME,t4.UNIT from " + DbSchema + ".T_HA_SITEFACTOR t1 left join " + DbSchema + ".T_HA_SITE t2 on t1.SITEID = t2.SITEID left join " + DbSchema + ".T_HA_PORTFACTOR t3 on t2.PORTID = t3.PORTID and t1.FACTORID = t3.FACTORID left join "+DbSchema+".T_HA_FACTOR t4 on t1.FACTORID = t4.FACTORID where t1.SITEID = '"+siteid+"' ";
        Map sqlmap = new HashMap();
        sqlmap.put("sqlMap", sql);
        List datalist = this.dao.query("DUHC20.query", sqlmap,0,-999999);
        
        if (datalist != null && datalist.size() > 0)
        {
        	for (int i = 0; i < datalist.size(); i++) {									
        		Map mapdata = (HashMap)datalist.get(i);
        		map = new HashMap<String, String>();
        		map.put("siteid", ObjToString(mapdata.get("SITEID")));
        		map.put("sitename", ObjToString(mapdata.get("SITENAME")));
        		map.put("mnid", ObjToString(mapdata.get("MNID")));
        		map.put("factorid", ObjToString(mapdata.get("FACTORID")));
        		map.put("factorname", ObjToString(mapdata.get("FACTORNAME")));
        		map.put("factordesc", "f" + ObjToString(mapdata.get("FACTORID")) + "list");
        		map.put("usezs", ObjToString(mapdata.get("USEZS")));
        		map.put("highlimit", ObjToString(mapdata.get("HIGHLIMIT")));
        		map.put("lowlimit", ObjToString(mapdata.get("LOWLIMIT")));
        		if(!StringUtils.isBlank(ObjToString(mapdata.get("HIGHLIMIT")))) {
        			if(!StringUtils.isBlank(ObjToString(mapdata.get("LOWLIMIT")))) {
        				map.put("limitdesc", ObjToString(mapdata.get("LOWLIMIT")) + "-" + ObjToString(mapdata.get("HIGHLIMIT")));
        			}else {
        				map.put("limitdesc", ObjToString(mapdata.get("HIGHLIMIT")));
        			}
        		}else {
        			map.put("limitdesc", "");
        		}
        		map.put("unit", ObjToString(mapdata.get("UNIT")));
        		map.put("datatime", "");
        		map.put("avg", "");
        		map.put("zsavg", "");
        		map.put("isover", "2");
        		siteblock.addRow(map);
        	}
        }
        return siteblock;
    }
    
  //获取某段时间内在线数据sql语句
    private EiInfo getOneSiteDataSqlByDateSpan(EiInfo outInfo, String datatype, Calendar startcalendar, Calendar endcalendar, String mnid) throws Exception
    {
    	EiBlockMeta fmetadata = new EiBlockMeta();
		EiColumn eiColumn = new EiColumn("datatime");
        fmetadata.addMeta(eiColumn);
		eiColumn = new EiColumn("factorid");
		fmetadata.addMeta(eiColumn);
		eiColumn = new EiColumn("avg");
		fmetadata.addMeta(eiColumn);
		eiColumn = new EiColumn("zsavg");
		fmetadata.addMeta(eiColumn);
		eiColumn = new EiColumn("usezs");
		fmetadata.addMeta(eiColumn);
		eiColumn = new EiColumn("highlimit");
		fmetadata.addMeta(eiColumn);
		eiColumn = new EiColumn("lowlimit");
		fmetadata.addMeta(eiColumn);
		eiColumn = new EiColumn("factorname");
		fmetadata.addMeta(eiColumn);
		eiColumn = new EiColumn("unit");
		fmetadata.addMeta(eiColumn);
		Map<String, List<String>> factorslist = new HashMap<String, List<String>>();
    	EiBlock siteblock = outInfo.getBlock("factorlist");
    	if(siteblock.getRows().size() > 0) {
    		for(int i = 0; i < siteblock.getRows().size(); i++) {
        		String factorid = ObjToString(siteblock.getRow(i).get("factorid"));
        		factorslist.put(factorid, new ArrayList<String>());
    		}
    	}
    	
    	
    	Map<String, String> compareMap =  new HashMap<String, String>();
    	String sql = "";
    	SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	SimpleDateFormat tableFormat = new SimpleDateFormat("yyyyMM");
        switch (datatype)
        {
            case "2011":
                if (startcalendar.get(Calendar.MONTH) == endcalendar.get(Calendar.MONTH))
                {
                    sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.RTD,t1.ZSRTD from " + DbSchema + ".DATATABLE_RTD_" + tableFormat.format(startcalendar.getTime()) + " t1 where t1.MN = '"+mnid+"' and t1.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t1.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
                }
                else
                {
                    sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.RTD,t1.ZSRTD from " + DbSchema + ".DATATABLE_RTD_" + tableFormat.format(startcalendar.getTime()) + " t1 where t1.MN = '"+mnid+"' and t1.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t1.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
                    sql += " union select t2.MN,t2.FACTORID,t2.DATATIME,t2.RTD,t2.ZSRTD from " + DbSchema + ".DATATABLE_RTD_" + tableFormat.format(endcalendar.getTime()) + " t2 where t2.MN = '"+mnid+"' and t2.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t2.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
                }
                break;
            case "2051":
            	if (startcalendar.get(Calendar.MONTH) == endcalendar.get(Calendar.MONTH))
                {
            		sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.AVG,t1.ZSAVG from " + DbSchema + ".DATATABLE_MIN_" + tableFormat.format(startcalendar.getTime()) + " t1 where t1.MN = '"+mnid+"' and t1.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t1.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
                }
                else
                {
                	sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.AVG,t1.ZSAVG from " + DbSchema + ".DATATABLE_MIN_" + tableFormat.format(startcalendar.getTime()) + " t1 where t1.MN = '"+mnid+"' and t1.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t1.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
                    sql += " union select t2.MN,t2.FACTORID,t2.DATATIME,t2.AVG,t2.ZSAVG from " + DbSchema + ".DATATABLE_MIN_" + tableFormat.format(endcalendar.getTime()) + " t2 where t2.MN = '"+mnid+"' and t2.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t2.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
                }
                break;
            case "2061":
                if (startcalendar.get(Calendar.YEAR) == endcalendar.get(Calendar.YEAR))
                {
                    sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.AVG,t1.ZSAVG from " + DbSchema + ".DATATABLE_HOUR_" + startcalendar.get(Calendar.YEAR) + " t1 where t1.MN = '"+mnid+"' and t1.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t1.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
                }
                else
                {
                    sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.AVG,t1.ZSAVG from " + DbSchema + ".DATATABLE_HOUR_" + startcalendar.get(Calendar.YEAR) + " t1 where t1.MN = '"+mnid+"' and t1.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t1.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
                    sql += " union select t2.MN,t2.FACTORID,t2.DATATIME,t2.AVG,t2.ZSAVG from " + DbSchema + ".DATATABLE_HOUR_" + endcalendar.get(Calendar.YEAR) + " t2 where t2.MN = '"+mnid+"' and t2.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t2.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
                }
                break;
            case "2031":
                if (startcalendar.get(Calendar.YEAR) == endcalendar.get(Calendar.YEAR))
                {
                    sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.AVG,t1.ZSAVG from " + DbSchema + ".DATATABLE_DAY_" + startcalendar.get(Calendar.YEAR) + " t1 where t1.MN = '"+mnid+"' andt1.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t1.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
                }
                else
                {
                    sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.AVG,t1.ZSAVG from " + DbSchema + ".DATATABLE_DAY_" + startcalendar.get(Calendar.YEAR) + " t1 where t1.MN = '"+mnid+"' andt1.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t1.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "'";
                    sql += " union select t2.MN,t2.FACTORID,t2.DATATIME,t2.AVG,t2.ZSAVG from " + DbSchema + ".DATATABLE_DAY_" + endcalendar.get(Calendar.YEAR) + " t2 where t2.MN = '"+mnid+"' andt2.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t2.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
                }
                break;
        }
        Map sqlmap = new HashMap();
        sqlmap.put("sqlMap", sql);
       
        List datalist = this.dao.query("DUHC20.query", sqlmap,0,-999999);
        
        
        if (datalist != null && datalist.size() > 0)
        {
        	for (int i = 0; i < datalist.size(); i++) {									
        		Map mapdata = (HashMap)datalist.get(i);
        		String temfactorid = ObjToString(mapdata.get("FACTORID"));
        		String temavg = "";
        		if("2011".equals(datatype)) {
    				temavg = ObjToString(mapdata.get("DATATIME")) + "&" + ObjToString(mapdata.get("RTD")) + "&" + ObjToString(mapdata.get("ZSRTD"));
    			}else {
    				temavg = ObjToString(mapdata.get("DATATIME")) + "&" + ObjToString(mapdata.get("AVG")) + "&" + ObjToString(mapdata.get("ZSAVG"));
    			}
        		if(!compareMap.containsKey(temfactorid)) {
        			compareMap.put(temfactorid, temavg);
        		}
        		
        		List<String> temlist =  (ArrayList<String>)factorslist.get(temfactorid);
        		if(temlist != null) {
        			temlist.add(temavg);
        			factorslist.put(temfactorid, temlist);
        		}
        	}
        }
        if(siteblock.getRows().size() > 0) {
    		for(int i = 0; i < siteblock.getRows().size(); i++) {
    			Map mapdata = (HashMap)siteblock.getRow(i);
        		String factorid = ObjToString(mapdata.get("factorid"));
        		String newdata = ObjToString(compareMap.get(factorid));
        		if(StringUtils.isNotBlank(newdata) && newdata.contains("&")) {
        			String[] newdataList = newdata.split("&",-1);
        			if("".endsWith(newdataList[0])) {
        				mapdata.put("datatime", "");
            			mapdata.put("avg", "");
                		mapdata.put("zsavg", "");
                		mapdata.put("isover", "2");
        			}else {
        				mapdata.put("datatime", newdataList[0]);
            			mapdata.put("avg", newdataList[1]);
                		mapdata.put("zsavg", newdataList[2]);
                		mapdata.put("isover", "0");
                		String temhighlimit = ObjToString(mapdata.get("highlimit"));
    	       			String temlowlimit = ObjToString(mapdata.get("lowlimit"));
    	       			String temusezs = ObjToString(mapdata.get("usezs"));
                		if(!StringUtils.isBlank(temhighlimit)) {
    	       				 Double highvalue = ObjToDouble(temhighlimit);
    	       				 
    	       				 String avgStr = "1".equals(temusezs) ? newdataList[2] : newdataList[1];
    	       				 Double temavg = ObjToDouble(avgStr);
    	       				 if(!StringUtils.isBlank(temhighlimit)) {
    	       					 Double lowvalue = ObjToDouble(temlowlimit);
    	       					 if(temavg >= highvalue || temavg <= lowvalue) {
    	       						mapdata.put("isover", "1");
    	       					 }
    	       				 }else {
    	       					 if(temavg >= highvalue) {
    	       						mapdata.put("isover", "1");
    	       					 }
    	       				 }
    	       			 }
        			}
        			
        		}
        		String temfactordesc = "f" + factorid + "list";
        		String factordesc = ObjToString(mapdata.get("factordesc"));
        		if(temfactordesc.equals(factordesc)) {
        			EiBlock block = new EiBlock(factordesc);
        			block.setBlockMeta(fmetadata); 
        			List<String> temlist =  (ArrayList<String>)factorslist.get(factorid);
        			if(temlist.size() > 0) {
        				Map<String, String> map;
        				for(int j=0;j<temlist.size();j++) {
        					String[] newdataList = temlist.get(j).split("&",-1);
        					map = new HashMap<String, String>();
        	        		map.put("datatime", getHourByDateTime(newdataList[0]));
        	        		map.put("factorid", factorid);
        	        		map.put("avg", newdataList[1]);
        	        		map.put("zsavg", newdataList[2]);
        	        		map.put("usezs", ObjToString(mapdata.get("usezs")));
        	        		map.put("highlimit", ObjToString(mapdata.get("highlimit")));
        	        		map.put("lowlimit", ObjToString(mapdata.get("lowlimit")));
        	        		map.put("factorname", ObjToString(mapdata.get("factorname")));
        	        		map.put("unit", ObjToString(mapdata.get("unit")));
        	        		block.addRow(map);
        				}
        			}
        			outInfo.setBlock(block);
        		}
        		//factorslist.put(factorid, new ArrayList<String>());
    		}
    	}
        outInfo.setBlock(siteblock);
        return outInfo;
    }
    
    private String getHourByDateTime(String datetime){
    	try {
			return datetime.substring(11,13);
		} catch (Exception ex) {
			return "";
		}
	}
    
    
    
    
  	
    //API获取历史监测点信息根据厂部id加载
  	public EiInfo getHistoryDepSiteList(EiInfo inInfo)
  	{
  		String departid = inInfo.getString("departid");
  		
  		EiInfo outInfo = new EiInfo();
  		EiBlock fqblock = new EiBlock("fqinfo");
  		EiBlock fsblock = new EiBlock("fsinfo");
  		EiBlock kqblock = new EiBlock("kqinfo");
        EiBlockMeta metadata = new EiBlockMeta();		 
  		EiColumn eiColumn = new EiColumn("siteid");
  		metadata.addMeta(eiColumn);
  		eiColumn = new EiColumn("sitename");
  		metadata.addMeta(eiColumn);
  		fqblock.setBlockMeta(metadata); 
  		fsblock.setBlockMeta(metadata); 
  		kqblock.setBlockMeta(metadata); 
  		
  		try {  	
  			String sql = "select * from "+DbSchema+".VIEW_T_HA_SITE_EX1 where ISONLINE = '1' and STATUS = '1' and DEPARTMENTID = '"+departid+"' ";
  			Map sqlmap = new HashMap();
              sqlmap.put("sqlMap", sql);
              List sitelist = this.dao.query("DUHC20.query", sqlmap);
              if(sitelist.size() > 0) {
  		    	for (int i = 0; i < sitelist.size(); i++) {		
  		    		Map tabledatemap = (HashMap)sitelist.get(i);
  		    		Map<String,String> map = new HashMap<String,String>();
  		   		    map.put("siteid",  ObjToString(tabledatemap.get("SITEID")));
  		   		    map.put("sitename",  ObjToString(tabledatemap.get("SITENAME")));
  		   		    String monitorid = ObjToString(tabledatemap.get("MONITORID"));
  		   		    if("01".equals(monitorid)) {
  		   		        fqblock.addRow(map);
  		   		    } else if("02".equals(monitorid)) {
  		   		        fsblock.addRow(map);
  		   		    } else if("04".equals(monitorid)) {
  		   		         kqblock.addRow(map);	
  		   		    }
  		    	}
  		    }
              outInfo.setMsg("查询成功");
  		} catch (Exception e) {
  			outInfo.setMsg("查询出错:" + e.toString());
  			outInfo.setStatus(-1);
  		}
  		outInfo.setBlock(fqblock);
  		outInfo.setBlock(fsblock);
  		outInfo.setBlock(kqblock);
  		return outInfo;
  	}	

  	
    //API获取历史监测点信息根据厂部id加载
  	public EiInfo getWarnCameraInfo(EiInfo inInfo)
  	{
  		String startDate = inInfo.getString("startDate").trim();
  		String endDate = inInfo.getString("endDate").trim();
  		
  		startDate = startDate + " 00:00:00";
  		endDate = endDate + " 23:59:59";
  		
  		EiInfo outInfo = new EiInfo();
  		EiBlock wcqblock = new EiBlock("wcqblock");
        EiBlockMeta metadata = new EiBlockMeta();		 
  		EiColumn eiColumn = new EiColumn("cameraid");
  		metadata.addMeta(eiColumn);
  		eiColumn = new EiColumn("objtime");
  		metadata.addMeta(eiColumn);
  		eiColumn = new EiColumn("picurl");
  		metadata.addMeta(eiColumn);
  		eiColumn = new EiColumn("cameraname");
  		metadata.addMeta(eiColumn);
  		eiColumn = new EiColumn("cameraip");
  		metadata.addMeta(eiColumn);
  		eiColumn = new EiColumn("departmentname");
  		metadata.addMeta(eiColumn);
  		wcqblock.setBlockMeta(metadata);  
  		
  		try {  	
  			String sql = " select t1.*,t2.* from "+DbSchema+".CAMERA_AI_RESULT t1 left join "+DbSchema+".T_HF_CAMERA t2 on t1.CAMERAID = t2.CAMERA_ID " + 
  					" where t1.OBJTIME >= '"+startDate+"' and t1.OBJTIME <= '"+endDate+"'and t2.ISAI = '1' order by t1.OBJTIME desc";
  			Map sqlmap = new HashMap();
            sqlmap.put("sqlMap", sql);
            List cameralist = this.dao.query("DUHC20.query", sqlmap);
            if(cameralist.size() > 0) {
				for (int i = 0; i < cameralist.size(); i++) {		
					Map tabledatemap = (HashMap)cameralist.get(i);
					Map<String,String> map = new HashMap<String,String>();
				    map.put("cameraid",  ObjToString(tabledatemap.get("CAMERAID")));
				    map.put("objtime",  ObjToString(tabledatemap.get("OBJTIME")));
				    map.put("picurl",  ObjToString(tabledatemap.get("PICURL")));
					map.put("cameraname",  ObjToString(tabledatemap.get("CAMERA_NAME")));
					map.put("cameraip",  ObjToString(tabledatemap.get("CAMERAIP")));
					map.put("departmentname",  ObjToString(tabledatemap.get("DEPARTMENTNAME")));
					wcqblock.addRow(map);	
				}
  		    }
            outInfo.setMsg("查询成功");
  		} catch (Exception e) {
  			outInfo.setMsg("查询出错:" + e.toString());
  			outInfo.setStatus(-1);
  		}
  		outInfo.setBlock(wcqblock);
  		return outInfo;
  	}	
  	
  //API获取污染物信息根据监测点id加载
  		public EiInfo getHistorySiteFactorList(EiInfo inInfo)
  		{
  			String siteid = ObjToString(inInfo.getString("siteid"));
  			String datatype = ObjToString(inInfo.getString("datatype"));
  			String factorid = ObjToString(inInfo.getString("factorid"));
  			String startDate = ObjToString(inInfo.getString("startDate"));
  	  		String endDate = ObjToString(inInfo.getString("endDate"));
  	  		
  	  		startDate = startDate + " 00:00:00";
  	  		endDate = endDate + " 23:59:59";
  			
  			EiInfo outInfo = new EiInfo();

  			try {
  				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  	            Calendar startcalendar = Calendar.getInstance();
  	            startcalendar.setTime(format.parse(startDate));
  	            Calendar endcalendar = Calendar.getInstance();
  	            endcalendar.setTime(format.parse(endDate));
  	        	EiBlock siteblock = getHistoryOneSiteFactoLimit(siteid,factorid);
  				outInfo.setBlock(siteblock);
  				if(siteblock !=null && siteblock.getRows().size() > 0) {
  					String mnid = ObjToString(siteblock.getRow(0).get("mnid"));
  					outInfo =  getHistoryOneSiteDataSqlByDateSpan(outInfo,datatype,startcalendar,endcalendar,mnid);
  				}
  	            outInfo.setMsg("查询成功");
  			} catch (Exception e) {
  				outInfo.setMsg("查询出错:" + e.toString());
  				outInfo.setStatus(-1);
  			}
  			return outInfo;
  		}	
  		
  		//获取某段时间内在线数据sql语句
  	    private EiInfo getHistoryOneSiteDataSqlByDateSpan(EiInfo outInfo, String datatype, Calendar startcalendar, Calendar endcalendar, String mnid) throws Exception
  	    {
  	    	EiBlockMeta fmetadata = new EiBlockMeta();
  			EiColumn eiColumn = new EiColumn("datatime");
  	        fmetadata.addMeta(eiColumn);
  			eiColumn = new EiColumn("factorid");
  			fmetadata.addMeta(eiColumn);
  			eiColumn = new EiColumn("avg");
  			fmetadata.addMeta(eiColumn);
  			eiColumn = new EiColumn("zsavg");
  			fmetadata.addMeta(eiColumn);
  			eiColumn = new EiColumn("usezs");
  			fmetadata.addMeta(eiColumn);
  			eiColumn = new EiColumn("highlimit");
  			fmetadata.addMeta(eiColumn);
  			eiColumn = new EiColumn("lowlimit");
  			fmetadata.addMeta(eiColumn);
  			eiColumn = new EiColumn("factorname");
  			fmetadata.addMeta(eiColumn);
  			eiColumn = new EiColumn("unit");
  			fmetadata.addMeta(eiColumn);
  			Map<String, List<String>> factorslist = new HashMap<String, List<String>>();
  	    	EiBlock siteblock = outInfo.getBlock("factorlist");
  	    	if(siteblock.getRows().size() > 0) {
  	    		for(int i = 0; i < siteblock.getRows().size(); i++) {
  	    			String isshow = ObjToString(siteblock.getRow(i).get("isshow"));
  	    			if("1".equals(isshow)) {
  	    				String factorid = ObjToString(siteblock.getRow(i).get("factorid"));
  	  	        		factorslist.put(factorid, new ArrayList<String>());
  	    			}
  	    		}
  	    	}
  	    	
  	    	Map<String, String> compareMap =  new HashMap<String, String>();
  	    	String sql = "";
  	    	SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  	    	SimpleDateFormat tableFormat = new SimpleDateFormat("yyyyMM");
  	        switch (datatype)
  	        {
  	            case "2011":
  	                if (startcalendar.get(Calendar.MONTH) == endcalendar.get(Calendar.MONTH))
  	                {
  	                    sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.RTD,t1.ZSRTD from " + DbSchema + ".DATATABLE_RTD_" + tableFormat.format(startcalendar.getTime()) + " t1 where t1.MN = '"+mnid+"' and t1.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t1.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
  	                }
  	                else
  	                {
  	                    sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.RTD,t1.ZSRTD from " + DbSchema + ".DATATABLE_RTD_" + tableFormat.format(startcalendar.getTime()) + " t1 where t1.MN = '"+mnid+"' and t1.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t1.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
  	                    sql += " union select t2.MN,t2.FACTORID,t2.DATATIME,t2.RTD,t2.ZSRTD from " + DbSchema + ".DATATABLE_RTD_" + tableFormat.format(endcalendar.getTime()) + " t2 where t2.MN = '"+mnid+"' and t2.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t2.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
  	                }
  	                break;
  	            case "2051":
  	            	if (startcalendar.get(Calendar.MONTH) == endcalendar.get(Calendar.MONTH))
  	                {
  	            		sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.AVG,t1.ZSAVG from " + DbSchema + ".DATATABLE_MIN_" + tableFormat.format(startcalendar.getTime()) + " t1 where t1.MN = '"+mnid+"' and t1.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t1.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
  	                }
  	                else
  	                {
  	                	sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.AVG,t1.ZSAVG from " + DbSchema + ".DATATABLE_MIN_" + tableFormat.format(startcalendar.getTime()) + " t1 where t1.MN = '"+mnid+"' and t1.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t1.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
  	                    sql += " union select t2.MN,t2.FACTORID,t2.DATATIME,t2.AVG,t2.ZSAVG from " + DbSchema + ".DATATABLE_MIN_" + tableFormat.format(endcalendar.getTime()) + " t2 where t2.MN = '"+mnid+"' and t2.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t2.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
  	                }
  	                break;
  	            case "2061":
  	                if (startcalendar.get(Calendar.YEAR) == endcalendar.get(Calendar.YEAR))
  	                {
  	                    sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.AVG,t1.ZSAVG from " + DbSchema + ".DATATABLE_HOUR_" + startcalendar.get(Calendar.YEAR) + " t1 where t1.MN = '"+mnid+"' and t1.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t1.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
  	                }
  	                else
  	                {
  	                    sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.AVG,t1.ZSAVG from " + DbSchema + ".DATATABLE_HOUR_" + startcalendar.get(Calendar.YEAR) + " t1 where t1.MN = '"+mnid+"' and t1.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t1.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
  	                    sql += " union select t2.MN,t2.FACTORID,t2.DATATIME,t2.AVG,t2.ZSAVG from " + DbSchema + ".DATATABLE_HOUR_" + endcalendar.get(Calendar.YEAR) + " t2 where t2.MN = '"+mnid+"' and t2.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t2.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
  	                }
  	                break;
  	            case "2031":
  	                if (startcalendar.get(Calendar.YEAR) == endcalendar.get(Calendar.YEAR))
  	                {
  	                    sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.AVG,t1.ZSAVG from " + DbSchema + ".DATATABLE_DAY_" + startcalendar.get(Calendar.YEAR) + " t1 where t1.MN = '"+mnid+"' and t1.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t1.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
  	                }
  	                else
  	                {
  	                    sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.AVG,t1.ZSAVG from " + DbSchema + ".DATATABLE_DAY_" + startcalendar.get(Calendar.YEAR) + " t1 where t1.MN = '"+mnid+"' and t1.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t1.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
  	                    sql += " union select t2.MN,t2.FACTORID,t2.DATATIME,t2.AVG,t2.ZSAVG from " + DbSchema + ".DATATABLE_DAY_" + endcalendar.get(Calendar.YEAR) + " t2 where t2.MN = '"+mnid+"' and t2.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t2.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
  	                }
  	                break;
  	        }
  	        Map sqlmap = new HashMap();
  	        sqlmap.put("sqlMap", sql);
  	       
  	        List datalist = this.dao.query("DUHC20.query", sqlmap,0,-999999);
  	        
  	        
  	        if (datalist != null && datalist.size() > 0)
  	        {
  	        	for (int i = 0; i < datalist.size(); i++) {									
  	        		Map mapdata = (HashMap)datalist.get(i);
  	        		String temfactorid = ObjToString(mapdata.get("FACTORID"));
  	        		String temavg = "";
  	        		if("2011".equals(datatype)) {
  	    				temavg = ObjToString(mapdata.get("DATATIME")) + "&" + ObjToString(mapdata.get("RTD")) + "&" + ObjToString(mapdata.get("ZSRTD"));
  	    			}else {
  	    				temavg = ObjToString(mapdata.get("DATATIME")) + "&" + ObjToString(mapdata.get("AVG")) + "&" + ObjToString(mapdata.get("ZSAVG"));
  	    			}
  	        		if(!compareMap.containsKey(temfactorid)) {
  	        			compareMap.put(temfactorid, temavg);
  	        		}
  	        		
  	        		List<String> temlist =  (ArrayList<String>)factorslist.get(temfactorid);
  	        		if(temlist != null) {
  	        			temlist.add(temavg);
  	        			factorslist.put(temfactorid, temlist);
  	        		}
  	        	}
  	        }
  	        if(siteblock.getRows().size() > 0) {
  	    		for(int i = 0; i < siteblock.getRows().size(); i++) {
  	    			Map mapdata = (HashMap)siteblock.getRow(i);
  	        		String factorid = ObjToString(mapdata.get("factorid"));
  	        		String newdata = ObjToString(compareMap.get(factorid));
  	        		if(StringUtils.isNotBlank(newdata) && newdata.contains("&")) {
  	        			String[] newdataList = newdata.split("&",-1);
  	        			if("".endsWith(newdataList[0])) {
  	        				mapdata.put("datatime", "");
  	            			mapdata.put("avg", "");
  	                		mapdata.put("zsavg", "");
  	                		mapdata.put("isover", "2");
  	        			}else {
  	        				mapdata.put("datatime", newdataList[0]);
  	            			mapdata.put("avg", newdataList[1]);
  	                		mapdata.put("zsavg", newdataList[2]);
  	                		mapdata.put("isover", "0");
  	                		String temhighlimit = ObjToString(mapdata.get("highlimit"));
  	    	       			String temlowlimit = ObjToString(mapdata.get("lowlimit"));
  	    	       			String temusezs = ObjToString(mapdata.get("usezs"));
  	                		if(!StringUtils.isBlank(temhighlimit)) {
  	    	       				 Double highvalue = ObjToDouble(temhighlimit);
  	    	       				 
  	    	       				 String avgStr = "1".equals(temusezs) ? newdataList[2] : newdataList[1];
  	    	       				 Double temavg = ObjToDouble(avgStr);
  	    	       				 if(!StringUtils.isBlank(temhighlimit)) {
  	    	       					 Double lowvalue = ObjToDouble(temlowlimit);
  	    	       					 if(temavg >= highvalue || temavg <= lowvalue) {
  	    	       						mapdata.put("isover", "1");
  	    	       					 }
  	    	       				 }else {
  	    	       					 if(temavg >= highvalue) {
  	    	       						mapdata.put("isover", "1");
  	    	       					 }
  	    	       				 }
  	    	       			 }
  	        			}
  	        			
  	        		}
  	        		String temfactordesc = "f" + factorid + "list";
  	        		String factordesc = ObjToString(mapdata.get("factordesc"));
  	        		if(temfactordesc.equals(factordesc)) {
  	        			EiBlock block = new EiBlock(factordesc);
  	        			block.setBlockMeta(fmetadata); 
  	        			List<String> temlist =  (ArrayList<String>)factorslist.get(factorid);
  	        			if(temlist.size() > 0) {
  	        				Map<String, String> map;
  	        				for(int j=0;j<temlist.size();j++) {
  	        					String[] newdataList = temlist.get(j).split("&",-1);
  	        					map = new HashMap<String, String>();
  	        	        		map.put("datatime", newdataList[0]);
  	        	        		map.put("factorid", factorid);
  	        	        		map.put("avg", newdataList[1]);
  	        	        		map.put("zsavg", newdataList[2]);
  	        	        		map.put("usezs", ObjToString(mapdata.get("usezs")));
  	        	        		map.put("highlimit", ObjToString(mapdata.get("highlimit")));
  	        	        		map.put("lowlimit", ObjToString(mapdata.get("lowlimit")));
  	        	        		map.put("factorname", ObjToString(mapdata.get("factorname")));
  	        	        		map.put("unit", ObjToString(mapdata.get("unit")));
  	        	        		block.addRow(map);
  	        				}
  	        			}
  	        			outInfo.setBlock(block);
  	        		}
  	    		}
  	    	}
  	        outInfo.setBlock(siteblock);
  	        return outInfo;
  	    }
  	    
  	//根据监测点获取限值
  	    private EiBlock getHistoryOneSiteFactoLimit(String siteid,String factorid) throws Exception
  	    {
  	    	EiBlock siteblock = new EiBlock("factorlist");
  			EiBlockMeta metadata = new EiBlockMeta();		 
  			EiColumn eiColumn = new EiColumn("siteid");
  			metadata.addMeta(eiColumn);
  			eiColumn = new EiColumn("sitename");
  			metadata.addMeta(eiColumn);
  			eiColumn = new EiColumn("mnid");
  			metadata.addMeta(eiColumn);
  			eiColumn = new EiColumn("factorid");
  			metadata.addMeta(eiColumn);
  			eiColumn = new EiColumn("factorname");
  			metadata.addMeta(eiColumn);
  			eiColumn = new EiColumn("factordesc");
  			metadata.addMeta(eiColumn);
  			eiColumn = new EiColumn("datatime");
  			metadata.addMeta(eiColumn);
  			eiColumn = new EiColumn("avg");
  			metadata.addMeta(eiColumn);
  			eiColumn = new EiColumn("zsavg");
  			metadata.addMeta(eiColumn);
  			eiColumn = new EiColumn("usezs");
  			metadata.addMeta(eiColumn);
  			eiColumn = new EiColumn("highlimit");
  			metadata.addMeta(eiColumn);
  			eiColumn = new EiColumn("lowlimit");
  			metadata.addMeta(eiColumn);
  			eiColumn = new EiColumn("unit");
  			metadata.addMeta(eiColumn);
  			eiColumn = new EiColumn("isover");
  			metadata.addMeta(eiColumn);
  			eiColumn = new EiColumn("limitdesc");
  			metadata.addMeta(eiColumn);
  			eiColumn = new EiColumn("isshow");
  			metadata.addMeta(eiColumn);
  			siteblock.setBlockMeta(metadata); 
  			Map<String, String> map;
  	    	String sql = "select t1.FACTORID,t1.SITEID,t2.SITENAME,t2.MNID,t1.USEZS,t3.HIGHLIMIT,t3.LOWLIMIT,t4.FACTORNAME,t4.UNIT from " + DbSchema + ".T_HA_SITEFACTOR t1 left join " + DbSchema + ".T_HA_SITE t2 on t1.SITEID = t2.SITEID left join " + DbSchema + ".T_HA_PORTFACTOR t3 on t2.PORTID = t3.PORTID and t1.FACTORID = t3.FACTORID left join "+DbSchema+".T_HA_FACTOR t4 on t1.FACTORID = t4.FACTORID where t1.SITEID = '"+siteid+"' ";
  	        Map sqlmap = new HashMap();
  	        sqlmap.put("sqlMap", sql);
  	        List datalist = this.dao.query("DUHC20.query", sqlmap,0,-999999);
  	        
  	        if (datalist != null && datalist.size() > 0)
  	        {
  	        	if("".equals(factorid)) {
  	        		factorid = ObjToString(((HashMap)datalist.get(0)).get("FACTORID"));
  	        	}
  	        	for (int i = 0; i < datalist.size(); i++) {									
  	        		Map mapdata = (HashMap)datalist.get(i);
  	        		map = new HashMap<String, String>();
  	        		map.put("siteid", ObjToString(mapdata.get("SITEID")));
  	        		map.put("sitename", ObjToString(mapdata.get("SITENAME")));
  	        		map.put("mnid", ObjToString(mapdata.get("MNID")));
  	        		map.put("factorid", ObjToString(mapdata.get("FACTORID")));
  	        		map.put("factorname", ObjToString(mapdata.get("FACTORNAME")));
  	        		
  	        		map.put("usezs", ObjToString(mapdata.get("USEZS")));
  	        		map.put("highlimit", ObjToString(mapdata.get("HIGHLIMIT")));
  	        		map.put("lowlimit", ObjToString(mapdata.get("LOWLIMIT")));
  	        		if(!StringUtils.isBlank(ObjToString(mapdata.get("HIGHLIMIT")))) {
  	        			if(!StringUtils.isBlank(ObjToString(mapdata.get("LOWLIMIT")))) {
  	        				map.put("limitdesc", ObjToString(mapdata.get("LOWLIMIT")) + "-" + ObjToString(mapdata.get("HIGHLIMIT")));
  	        			}else {
  	        				map.put("limitdesc", ObjToString(mapdata.get("HIGHLIMIT")));
  	        			}
  	        		}else {
  	        			map.put("limitdesc", "");
  	        		}
  	        		map.put("unit", ObjToString(mapdata.get("UNIT")));
  	        		map.put("datatime", "");
  	        		map.put("avg", "");
  	        		map.put("zsavg", "");
  	        		map.put("isover", "2");
  	        		if(factorid.equals(ObjToString(mapdata.get("FACTORID")))) {
  	        			map.put("factordesc", "f" + ObjToString(mapdata.get("FACTORID")) + "list");
  	        			map.put("isshow", "1");
  	        		}else {
  	        			map.put("factordesc", "");
  	        			map.put("isshow", "0");
  	        		}
  	        		siteblock.addRow(map);
  	        	}
  	        }
  	        return siteblock;
  	    }
}
