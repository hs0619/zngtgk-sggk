/**
 *
 */
package com.baosight.sggk.du.hc.service;

import com.baosight.sggk.common.du.domain.Tduhb08;
import com.baosight.sggk.util.PermissionUtil;
import com.baosight.sggk.util.StrUtil;
import com.baosight.iplat4j.core.ei.*;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.*;

public class ServiceDUHC53 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHC53.class);

	// 获取配置文件里的参数
	ResourceBundle dbPro = ResourceBundle.getBundle("application");
	String DbSchema = dbPro.getString("hbSchema");

	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();

		EiBlock queryblock = new EiBlock(EiConstant.queryBlock);
		EiBlockMeta metadata = new EiBlockMeta();
		EiColumn eiColumn = new EiColumn("monitorid");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("porttypeid");
		metadata.addMeta(eiColumn);
		queryblock.setBlockMeta(metadata);
		Map<String, String> row = new HashMap<String, String>();
		row.put("monitorid", "%");
		row.put("porttypeid", "country");
		queryblock.addRow(row);
		outInfo.addBlock(queryblock);

		EiBlock monitorblock = new EiBlock("monitor");
		monitorblock.setBlockMeta((new Tduhb08()).eiMetadata);

		List list = new ArrayList<>();
		Map params = new HashMap();
		params.put("monitorid", "%");
		params.put("monitorname", "全部");
		list.add(params);
		params = new HashMap();
		params.put("monitorid", "01");
		params.put("monitorname", "废气");
		list.add(params);
		params = new HashMap();
		params.put("monitorid", "02");
		params.put("monitorname", "废水");
		list.add(params);

		monitorblock.addRows(list);
		outInfo.setBlock(monitorblock);

		outInfo.setBlock(getPortTypeBlock());

		outInfo.setMsg("数据加载完成");
		return outInfo;
	}

	public EiInfo query(EiInfo inInfo) {
		// String userDept = getUserDeptId();// 获取用户所属厂部
		EiInfo outInfo = new EiInfo();
		String monitorid = StringUtils.isBlank(inInfo.getString("inqu_status-0-monitorid")) ? "%"
				: inInfo.getString("inqu_status-0-monitorid");
		String siteid = StringUtils.isBlank(inInfo.getString("inqu_status-0-siteid")) ? "%"
				: inInfo.getString("inqu_status-0-siteid");
		String porttypeid = StringUtils.isBlank(inInfo.getString("inqu_status-0-porttypeid")) ? "%"
				: inInfo.getString("inqu_status-0-porttypeid");
		try {
			EiBlock oldataheaderBlock = getDataHeaderByFactors();
			outInfo.setBlock(oldataheaderBlock);
			String sql = "SELECT DEPARTMENTID,siteid,sitename,monitorid,monitorname,mnid,portid,dischargeportname,case when departmentname!='' then departmentname else '其它' end AS \"DEPARTMENTNAME\" FROM "
					+ DbSchema + ".VIEW_T_HA_SITE t1  where t1.ISONLINE = '1' and t1.STATUS = '1' ";
			if (!"%".equals(monitorid.trim())) {
				if (!"%".equals(siteid.trim())) {
					sql += " and t1.MONITORID = '" + monitorid + "' and t1.SITEID = '" + siteid + "' ";
				} else {
					sql += " and t1.MONITORID = '" + monitorid + "' ";
				}
			} else {
				if (!"%".equals(siteid.trim())) {
					sql += " and t1.SITEID = '" + siteid + "' ";
				}
			}
			String status = PermissionUtil.getUserDepart(this.dao, UserSession.getLoginName());
			// 如果不是特权管理组 则查询该厂部下的监测点
			sql += " and t1.DEPARTMENTID like '" + status + "' ";

			switch (porttypeid) {
			case "country":
				sql += " and t1.COUNTRYPOINT = '1' ";
				break;
			case "city":
				sql += " and t1.CITYPOINT = '1' ";
				break;
			case "company":
				sql += " and t1.COMPANYPOINT = '1' ";
				break;
			default:
				break;
			}
			sql += " order by DEPARTMENTID ";
			//sql += " order by t1.DEPARTMENTNAME,t1.MONITORID,t1.SITEID ";
			Map sqlmap = new HashMap();
			sqlmap.put("sqlMap", sql);
			List sitelist = this.dao.query("DUHA01.query", sqlmap);
			//默认查询一段时间的数据，进行比较是否存在数据
            Calendar startcalendar = Calendar.getInstance();
        	startcalendar.add(Calendar.HOUR_OF_DAY, -2);
        	Calendar endcalendar = Calendar.getInstance();	
        	Map<String, String> compareMap =  getAllDataSqlByDateSpan("2011",startcalendar,endcalendar);
            if(sitelist.size() > 0) {
		    	for (int i = 0; i < sitelist.size(); i++) {		
		    		Map tabledatemap = (HashMap)sitelist.get(i);
		    		EiBlock olinfoBlock = getFactorsBySite(StrUtil.trimToString(tabledatemap.get("MONITORID")));
		    		outInfo = getDataByDateSpan(outInfo, olinfoBlock, "2011", tabledatemap, compareMap);
		    	}
		    }
			outInfo.setMsg("数据加载完成");
		} catch (Exception ex) {
			outInfo.setStatus(-1);
			outInfo.setMsg(ex.toString());
		}
		return outInfo;
	}

	//返回所有配置的 MNID 在线数据最近一次数据的时间集
    private List<Map<String, String>> getAllOnlineDataLastTime(List sitelist) throws Exception 
    {
    	List<Map<String, String>> list = new ArrayList<Map<String, String>>();
    	if(sitelist != null && sitelist.size() > 0) {
			for (int i = 0; i < sitelist.size(); i++) {	
				Map mapdata = (HashMap)sitelist.get(i);
				Map<String, String> map = new HashMap<String, String>();
	    		String temmn = StrUtil.trimToString(mapdata.get("MNID"));
	    		String temonitorid = StrUtil.trimToString(mapdata.get("MONITORID"));
	    		String temonitorname = StrUtil.trimToString(mapdata.get("MONITORNAME"));
	    		String departmentname = StrUtil.trimToString(mapdata.get("DEPARTMENTNAME"));
	    		String temsiteid = StrUtil.trimToString(mapdata.get("SITEID"));
	    		String temsitename = StrUtil.trimToString(mapdata.get("SITENAME"));
	    		Map<String, String> tablemap = getOnlineDataLastTimeByMN(temmn,"2011");
	    		map.put("mn", temmn);
	    		map.put("monitorid", temonitorid);
	    		map.put("monitorname", temonitorname);
	    		map.put("departmentname", departmentname);
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
    	return list;
    }
    
  //返回某个 MNID 在线数据最近一次数据的时间
    private Map<String, String> getOnlineDataLastTimeByMN(String mnid, String datatype) throws Exception
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
        List list = this.dao.query("DUHA01.query", sqlmap);
        if (list != null && list.size() > 0)
        {
        	for (int i = 0; i < list.size(); i++) {									
        		String temtablename = StrUtil.trimToString(((HashMap)list.get(i)).get("NAME"));
        		String dateSql = "select max(DATATIME) as DATATIME from " + DbSchema + "." + temtablename + " where MN='" + mnid + "' ";
        		sqlmap.put("sqlMap", dateSql);
                List datelist = this.dao.query("DUHA01.query", sqlmap);
                if(datelist != null && datelist.size() > 0) {
                	String datetime = StrUtil.trimToString(((HashMap)datelist.get(i)).get("DATATIME"));
                	map.put("tablename", temtablename);
	    			map.put("datetime", datetime);
                	break;
                }
        	}
        }
    	return map;
    }
     
  //根据站点查询监测因子
    private EiBlock getFactorsBySite(String monitorid) throws Exception
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
         List list = this.dao.query("DUHA01.query", sqlmap);
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
        return block;
    }
    
  //加载在线数据
    private EiInfo getDataByFactors(EiInfo outInfo, EiBlock factorsBlock, String datatype, Map<String, String> tabledatemap) throws Exception
    {
        EiBlock block = outInfo.getBlock("oldata");
        List<Map> listdata = new ArrayList<>();
        String temdepartmentname = StrUtil.trimToString(tabledatemap.get("departmentname"));
        String temsiteid = StrUtil.trimToString(tabledatemap.get("siteid"));
		String temsitename = StrUtil.trimToString(tabledatemap.get("sitename"));
		String temmonitorid = StrUtil.trimToString(tabledatemap.get("monitorid"));
		String temmonitorname = StrUtil.trimToString(tabledatemap.get("monitorname"));
		/*StringBuffer buffer = new StringBuffer(temsitename);
		if(temsitename.length() > 13) {
			buffer.insert(12, "<br>");
		}else {
			buffer.append("<br><br>");
		}
		temsitename = buffer.toString();*/
    	if(tabledatemap != null && !StringUtils.isBlank(tabledatemap.get("tablename"))) {
            SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		if (factorsBlock != null && factorsBlock.getRowCount() > 0)
            {
                String factorSql = "select ";
                for (int i = 0; i < factorsBlock.getRowCount(); i++)
                {
                    String temfactorid = StrUtil.trimToString(factorsBlock.getRow(i).get("factorid"));
                    if ("2011".equals(datatype))
                    {
                        factorSql += "max( CASE WHEN t5.FACTORID = '" + temfactorid + "' THEN t5.RTD END ) T" + temfactorid.trim() + ",";
                    }
                    else
                    {
                        factorSql += "max( CASE WHEN t5.FACTORID = '" + temfactorid + "' THEN t5.AVG END ) T" + temfactorid.trim() + ",";
                    }
                }
                factorSql += "t5.DATATIME from (";

                factorSql += getDataSqlByType(tabledatemap.get("mn"), datatype, tabledatemap.get("tablename"), tabledatemap.get("datetime"));

                factorSql += ") t5 group by t5.DATATIME order by t5.DATATIME desc ";

                Map sqlmap = new HashMap();
                sqlmap.put("sqlMap", factorSql);

                List list = this.dao.query("DUHA01.query", sqlmap);
                
                if (list != null && list.size() > 0)
                {
                	for (int i = 0; i < list.size(); i++) {									
                		Map mapdata = (HashMap)list.get(i);
                		Map map = new HashMap();
                		map.put("departmentname", temdepartmentname);
                		map.put("siteid", temsiteid);
                		map.put("sitename", temsitename);
                		map.put("monitorid", temmonitorid);
                		map.put("monitorname", temmonitorname);
						
                		String temdatatime = StrUtil.trimToString(mapdata.get("DATATIME"));
                		map.put("datatime", temdatatime);
                		map.put("overline", "0");
                		if(!"".equals(temdatatime)) {
                			Date oldDateTime = dateTimeFormat.parse(temdatatime);
                			Long dateSpan = (new Date().getTime() - oldDateTime.getTime())/60000;
                    		if(dateSpan >= 60) {
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
                            String factorvalue = StrUtil.trimToString(mapdata.get("T" + temfactorid.toUpperCase()));
                            String overline = getFactorLimitBySite(factorvalue, temfactorid, temsiteid);
                            if(!"0".equals(overline)) {
                            	map.put("overline", "1");
                            }
                        }
                		map.put("imgname", temmonitorid.trim() + StrUtil.trimToString(map.get("overline")) + StrUtil.trimToString(map.get("offline")) + ".jpg");
                		listdata.add(map);
                	}
                }else {
            		Map map = new HashMap();
            		map.put("departmentname", temdepartmentname);
            		map.put("siteid", temsiteid);
            		map.put("sitename", temsitename);
            		map.put("monitorid", temmonitorid);
            		map.put("monitorname", temmonitorname);
            		map.put("datatime", "");
            		map.put("overline", "0");
                    map.put("offline", "1");
                    map.put("imgname", temmonitorid.trim() + "01.jpg");
            		listdata.add(map);
                }
            }
           
    	}else {
    		Map map = new HashMap();
    		map.put("departmentname", temdepartmentname);
    		map.put("siteid", temsiteid);
    		map.put("sitename", temsitename);
    		map.put("monitorid", temmonitorid);
    		map.put("monitorname", temmonitorname);
    		map.put("datatime", "");
    		map.put("overline", "0");
            map.put("offline", "1");
            map.put("imgname", temmonitorid.trim() + "01.jpg");
    		listdata.add(map);
    	}
    	block.addRows(listdata);
    	outInfo.setBlock(block);
        return outInfo;
    }
    
  //根据因子和站点获取对应的限值
    private String getFactorLimitBySite(String avg, String factorid, String siteid) throws Exception
    {
    	String overline = "0";
    	
		String factorSql = "select t3.*,t5.USEZS from (select t1.* from " + DbSchema + ".t_ha_portfactor t1 where t1.PORTID = (select t2.PORTID from " + DbSchema + ".t_ha_site t2 where t2.SITEID = '" + siteid + "') and t1.FACTORID = '" + factorid + "') t3 left join (select t4.FACTORID,t4.USEZS from " + DbSchema + ".T_HA_SITEFACTOR  t4 where t4.TYPE='2' AND t4.SITEID = '" + siteid + "' and t4.FACTORID = '" + factorid + "') t5 on t3.FACTORID = t5.FACTORID ";
		
		Map sqlmap = new HashMap();
		sqlmap.put("sqlMap", factorSql);
		
		List list = this.dao.query("DUHA01.query", sqlmap);
		if (list != null && list.size() > 0) {
			 String temhighlimit = StrUtil.trimToString(((HashMap)list.get(0)).get("HIGHLIMIT"));
			 String temlowlimit = StrUtil.trimToString(((HashMap)list.get(0)).get("LOWLIMIT"));
			 String temusezs = StrUtil.trimToString(((HashMap)list.get(0)).get("USEZS"));
			 if(!StringUtils.isBlank(temhighlimit)) {
				 Double highvalue = StrUtil.trimToDouble(temhighlimit);
				 
				 String avgStr = "1".equals(temusezs) ? avg.substring((avg.indexOf("&")+1)) : avg.substring(0,avg.indexOf("&"));
				//暂时全用实测值
				 //String avgStr = avg.substring(0,avg.indexOf("&"));
				 Double temavg = StrUtil.trimToDouble(avgStr);
				 if(!StringUtils.isBlank(temlowlimit)) {
					 Double lowvalue = StrUtil.trimToDouble(temlowlimit);
					 if(temavg >= highvalue || temavg <= lowvalue) {
						 overline = "1";
					 }
				 }else {
					 if(temavg >= highvalue) {
						 overline = "1";
					 }
				 }
			 }
		}
        return overline;
    }
    
    public String getDataSqlByType(String mn, String datatype, String tablename, String datetime) throws Exception
    {
    	String sql = "";
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
        return sql;
    }
    
  //根据因子信息绑定grid头信息
    private EiBlock getDataHeaderByFactors() 
    {
        EiBlock block = new EiBlock("oldata");
        EiBlockMeta metadata = new EiBlockMeta();
        EiColumn eiColumn = new EiColumn("siteid");
		eiColumn.setDescName("监测点ID");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("sitename");
		eiColumn.setDescName("监测点名称");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("departmentname");
		eiColumn.setDescName("部门名称");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("monitorid");
		eiColumn.setDescName("监测类型ID");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("monitorname");
		eiColumn.setDescName("监测类型名称");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("datatime");
		eiColumn.setDescName("日期");
		metadata.addMeta(eiColumn);
        eiColumn = new EiColumn("offline");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("overline");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("imgname");
		metadata.addMeta(eiColumn);
        return block;
    }
    
    
    //加载在线数据根据时间段
    private EiInfo getDataByDateSpan(EiInfo outInfo, EiBlock factorsBlock, String datatype, Map tabledatemap, Map<String, String> compareMap) throws Exception
    {
        EiBlock block = outInfo.getBlock("oldata");
        List<Map> listdata = new ArrayList<>();
        String temmn = StrUtil.trimToString(tabledatemap.get("MNID"));
		String temmonitorid = StrUtil.trimToString(tabledatemap.get("MONITORID"));
		String temmonitorname = StrUtil.trimToString(tabledatemap.get("MONITORNAME"));
		String temdepartmentname = StrUtil.trimToString(tabledatemap.get("DEPARTMENTNAME"));
		String temsiteid = StrUtil.trimToString(tabledatemap.get("SITEID"));
		String temsitename = StrUtil.trimToString(tabledatemap.get("SITENAME"));
		Map map = new HashMap();
		map.put("departmentname", temdepartmentname);
		map.put("siteid", temsiteid);
		map.put("sitename", temsitename);
		map.put("monitorid", temmonitorid);
		map.put("monitorname", temmonitorname);
		map.put("datatime", "");
		map.put("overline", "0");
		map.put("offline", "1");
		map.put("imgname", temmonitorid.trim() + "01.jpg");
		if (factorsBlock != null && factorsBlock.getRowCount() > 0)
        {
            for (int i = 0; i < factorsBlock.getRowCount(); i++)
            {
            	String temfactorid = StrUtil.trimToString(factorsBlock.getRow(i).get("factorid"));
                if(compareMap.containsKey(temmn + "&" + temfactorid)) {
                	map.put("offline", "0");
                	String overline = getFactorLimitBySite(compareMap.get(temmn + "&" + temfactorid), temfactorid, temsiteid);
                    if(!"0".equals(overline)) {
                    	map.put("overline", "1");
                    }
                }
            }
            map.put("imgname", temmonitorid.trim() + StrUtil.trimToString(map.get("overline")) + StrUtil.trimToString(map.get("offline")) + ".jpg");
        }
		listdata.add(map);
    	block.addRows(listdata);
    	outInfo.setBlock(block);
        return outInfo;
    }
    
    //获取某段时间内单个监测点在线数据sql语句
    public Map<String, String> getOneDataSqlByDateSpan(String datatype, String mn,Calendar startcalendar, Calendar endcalendar) throws Exception
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
                    sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.RTD,t1.ZSRTD from " + DbSchema + ".DATATABLE_RTD_" + tableFormat.format(startcalendar.getTime()) + " t1 where t1.MN = '" + mn + "' and t1.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t1.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
                }
                else
                {
                    sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.RTD,t1.ZSRTD from " + DbSchema + ".DATATABLE_RTD_" + tableFormat.format(startcalendar.getTime()) + " t1 where t1.MN = '" + mn + "' and t1.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t1.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
                    sql += " union select t2.MN,t2.FACTORID,t2.DATATIME,t2.RTD,t2.ZSRTD from " + DbSchema + ".DATATABLE_RTD_" + tableFormat.format(endcalendar.getTime()) + " t2 where t2.MN = '" + mn + "' and t2.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t2.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
                }
                break;
            case "2051":
            	if (startcalendar.get(Calendar.MONTH) == endcalendar.get(Calendar.MONTH))
                {
            		sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.AVG,t1.ZSAVG from " + DbSchema + ".DATATABLE_MIN_" + tableFormat.format(startcalendar.getTime()) + " t1 where t1.MN = '" + mn + "' and t1.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t1.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
                }
                else
                {
                	sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.AVG,t1.ZSAVG from " + DbSchema + ".DATATABLE_MIN_" + tableFormat.format(startcalendar.getTime()) + " t1 where t1.MN = '" + mn + "' and t1.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t1.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
                    sql += " union select t2.MN,t2.FACTORID,t2.DATATIME,t2.AVG,t2.ZSAVG from " + DbSchema + ".DATATABLE_MIN_" + tableFormat.format(endcalendar.getTime()) + " t2 where t2.MN = '" + mn + "' and t2.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t2.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
                }
                break;
            case "2061":
                if (startcalendar.get(Calendar.YEAR) == endcalendar.get(Calendar.YEAR))
                {
                    sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.AVG,t1.ZSAVG from " + DbSchema + ".DATATABLE_HOUR_" + startcalendar.get(Calendar.YEAR) + " t1 where t1.MN = '" + mn + "' and t1.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t1.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
                }
                else
                {
                    sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.AVG,t1.ZSAVG from " + DbSchema + ".DATATABLE_HOUR_" + startcalendar.get(Calendar.YEAR) + " t1 where t1.MN = '" + mn + "' and t1.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t1.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
                    sql += " union select t2.MN,t2.FACTORID,t2.DATATIME,t2.AVG,t2.ZSAVG from " + DbSchema + ".DATATABLE_HOUR_" + endcalendar.get(Calendar.YEAR) + " t2 where t2.MN = '" + mn + "' and t2.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t2.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
                }
                break;
            case "2031":
                if (startcalendar.get(Calendar.YEAR) == endcalendar.get(Calendar.YEAR))
                {
                    sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.AVG,t1.ZSAVG from " + DbSchema + ".DATATABLE_DAY_" + startcalendar.get(Calendar.YEAR) + " t1 where t1.MN = '" + mn + "' and t1.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t1.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
                }
                else
                {
                    sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.AVG,t1.ZSAVG from " + DbSchema + ".DATATABLE_DAY_" + startcalendar.get(Calendar.YEAR) + " t1 where t1.MN = '" + mn + "' and t1.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t1.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "'";
                    sql += " union select t2.MN,t2.FACTORID,t2.DATATIME,t2.AVG,t2.ZSAVG from " + DbSchema + ".DATATABLE_DAY_" + endcalendar.get(Calendar.YEAR) + " t2 where t2.MN = '" + mn + "' and t2.DATATIME >= '" + dateTimeFormat.format(startcalendar.getTime()) + "' and t2.DATATIME <= '" + dateTimeFormat.format(endcalendar.getTime()) + "' order by DATATIME desc";
                }
                break;
        }
        
        Map sqlmap = new HashMap();
        sqlmap.put("sqlMap", sql);
        List datalist = this.dao.query("DUHA01.query", sqlmap);
        if (datalist != null && datalist.size() > 0)
        {
        	for (int i = 0; i < datalist.size(); i++) {									
        		Map mapdata = (HashMap)datalist.get(i);
        		String temMN = StrUtil.trimToString(mapdata.get("MN"));
        		String temfactorid = StrUtil.trimToString(mapdata.get("FACTORID"));
        		String temavg = StrUtil.trimToString(mapdata.get("AVG"));
        		String temmnfactor = temMN + "&" + temfactorid;
        		if(!compareMap.containsKey(temmnfactor)) {
        			compareMap.put(temmnfactor, temavg);
        		}
        	}
        }
        return compareMap;
    }
    
  //获取某段时间内在线数据sql语句
    public Map<String, String> getAllDataSqlByDateSpan(String datatype, Calendar startcalendar, Calendar endcalendar) throws Exception
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
       
        List datalist = this.dao.query("DUHA01.query", sqlmap,0,-999999);
        
        
        if (datalist != null && datalist.size() > 0)
        {
        	for (int i = 0; i < datalist.size(); i++) {									
        		Map mapdata = (HashMap)datalist.get(i);
        		String temMN = StrUtil.trimToString(mapdata.get("MN"));
        		String temfactorid = StrUtil.trimToString(mapdata.get("FACTORID"));
        		String temmnfactor = temMN + "&" + temfactorid;
        		if(!compareMap.containsKey(temmnfactor)) {
        			
        			if("2011".equals(datatype)) {
        				String temavg = StrUtil.trimToString(mapdata.get("RTD")) + "&" + StrUtil.trimToString(mapdata.get("ZSRTD"));
        				compareMap.put(temmnfactor, temavg);
        			}else {
        				String temavg = StrUtil.trimToString(mapdata.get("AVG")) + "&" + StrUtil.trimToString(mapdata.get("ZSAVG"));
        				compareMap.put(temmnfactor, temavg);
        			}
        		}
        	}
        }
        return compareMap;
    }
    
    private EiBlock getPortTypeBlock()
    {
        EiBlock block = new EiBlock("porttype");
        EiBlockMeta metadata = new EiBlockMeta();
        EiColumn eiColumn = new EiColumn("typeid");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("typename");
		metadata.addMeta(eiColumn);
		List list=new ArrayList<>();
		Map params=new HashMap();
		params.put("typeid", "%");
		params.put("typename", "全部");
		list.add(params);
		params=new HashMap();
		params.put("typeid", "country");
		params.put("typename", "国控点");
		list.add(params);
//		params=new HashMap();
//		params.put("typeid", "city");
//		params.put("typename", "市控点");
//		list.add(params);
		params=new HashMap();
		params.put("typeid", "company");
		params.put("typename", "内控点");
		list.add(params);
		block.addRows(list);
        return block;
    }
    
    public  String getUserDeptId() {
		String departmentid = "";
		String userid = UserSession.getLoginName();
		String Sql = "select DEPARTMENT_ID AS \"DEPARTMENTID\" from " + DbSchema
				+ ".t_ha_xs_user_ex WHERE user_id ='" + userid + "' ";
		Map sqlmap = new HashMap();
		sqlmap.put("sqlMap", Sql);
		List list = this.dao.query("DUHA01.query", sqlmap);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				departmentid = StrUtil.trimToString(((HashMap) list.get(i)).get("DEPARTMENTID"));
			}
		}
		return departmentid;
	}
}
