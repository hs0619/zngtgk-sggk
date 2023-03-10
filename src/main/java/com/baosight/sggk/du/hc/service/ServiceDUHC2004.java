/**
 *
 */
package com.baosight.sggk.du.hc.service;

import com.baosight.sggk.common.du.domain.Tduhb07;
import com.baosight.sggk.util.BaseUtil;
import com.baosight.sggk.util.DateUtil;
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
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class ServiceDUHC2004 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHC2004.class);

	//获取配置文件里的参数
	ResourceBundle dbPro = ResourceBundle.getBundle("application");
	String DbSchema = dbPro.getString("hbSchema");
	
    public EiInfo initSelectData(EiInfo inInfo) {
 		EiBlock typeblock = BaseUtil.GetDataTypes(inInfo.getBlock("type"), false, "rtd");
		inInfo.setBlock(typeblock);
		  
		return inInfo;
	}

	  
	public EiInfo initLoad(EiInfo inInfo) {
		  EiInfo outInfo = new EiInfo();
		  String status = PermissionUtil.getUserDepart(this.dao, UserSession.getLoginName());
		  outInfo.set("inqu_status-0-departmentid", status);
		  
		  SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd");
		  outInfo.set("inqu_status-0-startdate", dateTimeFormat.format(new Date()));
		  outInfo.set("inqu_status-0-enddate", dateTimeFormat.format(new Date()));
		  outInfo.setMsg("页面加载完成");
		  return outInfo;
	}
	
	
	public EiInfo query(EiInfo inInfo){
		EiInfo outInfo = new EiInfo();
		String siteid = inInfo.getString("inqu_status-0-siteid");
		String typeid = inInfo.getString("inqu_status-0-typeid");
		String startdate = inInfo.getString("inqu_status-0-startdate");
		String enddate = inInfo.getString("inqu_status-0-enddate");
		try {
			
			EiBlock olinfoBlock = getFactorsBySite(siteid);
			outInfo.setBlock(olinfoBlock);

			//加载在线数据
			outInfo = getDataByFactors(outInfo, olinfoBlock, typeid, startdate, enddate);
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
    private EiBlock getFactorsBySite(String siteid)  {
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
		eiColumn = new EiColumn("usezs");
		metadata.addMeta(eiColumn);
		block.setBlockMeta(metadata); 
		List<Map> listdata = new ArrayList<>();
        try  {
            if (StringUtils.isNotBlank(siteid))  {
            	String sql = "select t2.FACTORID,t2.USEZS,t4.FACTORNAME,t3.MNID,t3.MONITORID,t4.UNIT,t5.HIGHLIMIT,t5.LOWLIMIT from " + 
    			"(select t1.FACTORID,t1.SITEID,t1.DESCRIPTION,t1.USEZS from " + DbSchema + ".t_ha_sitefactor t1 where t1.SITEID = '" + siteid + "' and t1.TYPE = '2' and t1.STATUS = '1') t2 " + 
    			"LEFT JOIN " + DbSchema + ".t_ha_site t3 on t2.SITEID = t3.SITEID LEFT JOIN " + DbSchema + ".t_ha_factor t4 on t2.FACTORID= t4.FACTORID " + 
    			"LEFT JOIN " + DbSchema + ".t_ha_portfactor t5 on t3.PORTID = t5.PORTID and t2.FACTORID = t5.FACTORID ORDER BY t2.FACTORID ";

                Map sqlmap = new HashMap();
                sqlmap.put("sqlMap", sql);
                List list = this.dao.query("DUHA01.query", sqlmap);
                if (list != null && list.size() > 0)  {
                	for (int i = 0; i < list.size(); i++) {									
                		Map mapdata = (HashMap)list.get(i);
                		Map map = new HashMap();
                		map.put("factorid", StrUtil.trimToString(mapdata.get("FACTORID")));
                		map.put("mnid", StrUtil.trimToString(mapdata.get("MNID")));
                		map.put("highlimit", StrUtil.trimToString(mapdata.get("HIGHLIMIT")));
                		map.put("lowlimit", StrUtil.trimToString(mapdata.get("LOWLIMIT")));
                		map.put("monitorid", StrUtil.trimToString(mapdata.get("MONITORID")));
                		map.put("factorname", StrUtil.trimToString(mapdata.get("FACTORNAME")));
                		map.put("unit", StrUtil.trimToString(mapdata.get("UNIT")));
                		map.put("usezs", StrUtil.trimToString(mapdata.get("USEZS")));
                		listdata.add(map);
    				}
                }
            }
            block.setRows(listdata);
        }
        catch (Exception ex)  {
            throw ex;
        }
        return block;
    }
    
  //加载在线数据
    private EiInfo getDataByFactors(EiInfo outInfo, EiBlock factorsBlock, String datatype, String startdate,
                                    String enddate) throws Exception {
        EiBlock block = new EiBlock("oldbdata");
        EiBlockMeta metadata = new EiBlockMeta();	
        EiColumn eiColumn = new EiColumn("indextime");
		eiColumn.setDescName("筛选日期");
		eiColumn.setVisible(false);
		metadata.addMeta(eiColumn);
		
	    eiColumn = new EiColumn("datatime");
		eiColumn.setDescName("日期");
		eiColumn.setWidth(201);
		metadata.addMeta(eiColumn);
        
		
        try  {
            if (factorsBlock != null && factorsBlock.getRowCount() > 0)  {
                String factorSql = "select ";
                String temMN = StrUtil.trimToString(factorsBlock.getRow(0).get("mnid"));
                for (int i = 0; i < factorsBlock.getRowCount(); i++)  {
                    String temfactorid = StrUtil.trimToString(factorsBlock.getRow(i).get("factorid"));
                    String temfactorname = StrUtil.trimToString(factorsBlock.getRow(i).get("factorname"));
            		String temusezs = StrUtil.trimToString(factorsBlock.getRow(i).get("usezs"));
            		String temunit = StrUtil.trimToString(factorsBlock.getRow(i).get("unit"));
                    if ("2011".equals(datatype))  {//实时值
                    	 eiColumn = new EiColumn("olavg" + temfactorid);
                    	 if("001".equals(temfactorid)) {//ph
                    		 eiColumn.setDescName(temfactorname+"(平均值)");
                    	 }else {
                    		 eiColumn.setDescName(temfactorname+"(平均值)" + "</br>("+temunit+")");
						 }
                         eiColumn.setWidth(201);
                         metadata.addMeta(eiColumn);
                       //if("1".equals(temusezs)) {
                 		if("01".equals(temfactorid)||"02".equals(temfactorid)||"03".equals(temfactorid)) {
                 			 eiColumn = new EiColumn("olzsavg" + temfactorid);
                              eiColumn.setDescName(temfactorname+"(折算平均值)" + "</br>("+temunit+")");
                              eiColumn.setWidth(201);
                              metadata.addMeta(eiColumn);
                 		}
                        factorSql += "max( CASE WHEN t5.FACTORID = '" + temfactorid + "' THEN t5.RTD END ) TAVG" + temfactorid.trim() + ",max( CASE WHEN t5.FACTORID = '" + temfactorid + "' THEN t5.ZSRTD END ) TZSAVG" + temfactorid.trim() + ",";
                    }
                    else  {//其他
                    	 if("001".equals(temfactorid)) {//ph
                    		 eiColumn = new EiColumn("olavg" + temfactorid);
                             eiColumn.setDescName(temfactorname+"(平均值)");
                             eiColumn.setWidth(201);
                             metadata.addMeta(eiColumn);
                             eiColumn = new EiColumn("olmin" + temfactorid);
                             eiColumn.setDescName(temfactorname+"(最小值)");
                             eiColumn.setWidth(201);
                             metadata.addMeta(eiColumn);
                             eiColumn = new EiColumn("olmax" + temfactorid);
                             eiColumn.setDescName(temfactorname+"(最大值)" );
                             eiColumn.setWidth(201);
                             metadata.addMeta(eiColumn);
                             eiColumn = new EiColumn("olcou" + temfactorid);
                             eiColumn.setDescName(temfactorname+"(累计值)");
                             eiColumn.setWidth(201);
                             metadata.addMeta(eiColumn);
                    	 }else {
                    		 eiColumn = new EiColumn("olavg" + temfactorid);
                             eiColumn.setDescName(temfactorname+"(平均值)" + "</br>("+temunit+")");
                             eiColumn.setWidth(201);
                             metadata.addMeta(eiColumn);
                             eiColumn = new EiColumn("olmin" + temfactorid);
                             eiColumn.setDescName(temfactorname+"(最小值)" + "</br>("+temunit+")");
                             eiColumn.setWidth(201);
                             metadata.addMeta(eiColumn);
                             eiColumn = new EiColumn("olmax" + temfactorid);
                             eiColumn.setDescName(temfactorname+"(最大值)" + "</br>("+temunit+")");
                             eiColumn.setWidth(201);
                             metadata.addMeta(eiColumn);
                             eiColumn = new EiColumn("olcou" + temfactorid);
                             eiColumn.setDescName(temfactorname+"(累计值)" + "</br>("+temunit+")");
                             eiColumn.setWidth(201);
                             metadata.addMeta(eiColumn);
						 }
                    	 
                         
                       //if("1".equals(temusezs)) {
                 		if("01".equals(temfactorid)||"02".equals(temfactorid)||"03".equals(temfactorid)) {
                 			 eiColumn = new EiColumn("olzsavg" + temfactorid);
                              eiColumn.setDescName(temfactorname+"(折算平均值)" + "</br>("+temunit+")");
                              eiColumn.setWidth(201);
                              metadata.addMeta(eiColumn);
                              eiColumn = new EiColumn("olzsmin" + temfactorid);
                              eiColumn.setDescName(temfactorname+"(折算最小值)" + "</br>("+temunit+")");
                              eiColumn.setWidth(201);
                              metadata.addMeta(eiColumn);
                              eiColumn = new EiColumn("olzsmax" + temfactorid);
                              eiColumn.setDescName(temfactorname+"(折算最大值)" + "</br>("+temunit+")");
                              eiColumn.setWidth(201);
                              metadata.addMeta(eiColumn);
                              eiColumn = new EiColumn("olzscou" + temfactorid);
                              eiColumn.setDescName(temfactorname+"(折算累计值)" + "</br>("+temunit+")");
                              eiColumn.setWidth(201);
                              metadata.addMeta(eiColumn);
                 		}
                        factorSql += "max( CASE WHEN t5.FACTORID = '" + temfactorid + "' THEN t5.AVG END ) TAVG" + temfactorid.trim() + ",max( CASE WHEN t5.FACTORID = '" + temfactorid + "' THEN t5.ZSAVG END ) TZSAVG" + temfactorid.trim() + ",max( CASE WHEN t5.FACTORID = '" + temfactorid + "' THEN t5.MIN END ) TMIN" + temfactorid.trim() + ",max( CASE WHEN t5.FACTORID = '" + temfactorid + "' THEN t5.ZSMIN END ) TZSMIN" + temfactorid.trim() + ",max( CASE WHEN t5.FACTORID = '" + temfactorid + "' THEN t5.MAX END ) TMAX" + temfactorid.trim() + ",max( CASE WHEN t5.FACTORID = '" + temfactorid + "' THEN t5.ZSMAX END ) TZSMAX" + temfactorid.trim() + ",max( CASE WHEN t5.FACTORID = '" + temfactorid + "' THEN t5.COU END ) TCOU" + temfactorid.trim() + ",max( CASE WHEN t5.FACTORID = '" + temfactorid + "' THEN t5.ZSCOU END ) TZSCOU" + temfactorid.trim() + ",";
                    }
                }
                block.setBlockMeta(metadata); 
                factorSql += "t5.DATATIME ,t5.indextime  from (";

                factorSql += getDataSqlByType(temMN, datatype, startdate, enddate);

                factorSql += ") t5 group by t5.DATATIME,t5.indextime order by t5.DATATIME asc ";

                Map sqlmap = new HashMap();
                sqlmap.put("sqlMap", factorSql);

                List list = this.dao.query("DUHA01.query", sqlmap, 0, -999999);

                block.set("count",list.size());
                List<Map> listdata = new ArrayList<>();
                if (list != null && list.size() > 0)  {
                	Map map;
                	if ("2011".equals(datatype)) {//实时值
                		for (int i = 0; i < list.size(); i++) {									
                    		Map mapdata = (HashMap)list.get(i);
                    		map = new HashMap();
                    		map.put("indextime", StrUtil.trimToString(mapdata.get("INDEXTIME")));
                    		map.put("datatime", StrUtil.trimToString(mapdata.get("DATATIME")));
                    		for (int j = 0; j < factorsBlock.getRowCount(); j++)  {
                                String temfactorid = StrUtil.trimToString(factorsBlock.getRow(j).get("factorid"));
                                map.put("olavg" + temfactorid, StrUtil.trimToString(mapdata.get("TAVG" + temfactorid.toUpperCase())));
                                String temusezs = StrUtil.trimToString(factorsBlock.getRow(j).get("usezs"));
                              //if("1".equals(temusezs)) {
                        		if("01".equals(temfactorid)||"02".equals(temfactorid)||"03".equals(temfactorid)) {
                        			map.put("olzsavg" + temfactorid, StrUtil.trimToString(mapdata.get("TZSAVG" + temfactorid.toUpperCase())));
                        		}
                            }
                    		listdata.add(map);
                    	}
                    }
                    else  {//其他
                    	for (int i = 0; i < list.size(); i++) {									
                    		Map mapdata = (HashMap)list.get(i);
                    	    map = new HashMap();
                    	    map.put("indextime", StrUtil.trimToString(mapdata.get("INDEXTIME")));
                    		map.put("datatime", StrUtil.trimToString(mapdata.get("DATATIME")));
                    		for (int j = 0; j < factorsBlock.getRowCount(); j++)   {
                                String temfactorid = StrUtil.trimToString(factorsBlock.getRow(j).get("factorid"));
                                map.put("olavg" + temfactorid, StrUtil.trimToString(mapdata.get("TAVG" + temfactorid.toUpperCase())));
                                map.put("olmin" + temfactorid, StrUtil.trimToString(mapdata.get("TMIN" + temfactorid.toUpperCase())));
                                map.put("olmax" + temfactorid, StrUtil.trimToString(mapdata.get("TMAX" + temfactorid.toUpperCase())));
                                map.put("olcou" + temfactorid, StrUtil.trimToString(mapdata.get("TCOU" + temfactorid.toUpperCase())));
                                String temusezs = StrUtil.trimToString(factorsBlock.getRow(j).get("usezs"));
                              //if("1".equals(temusezs)) {
                        		if("01".equals(temfactorid)||"02".equals(temfactorid)||"03".equals(temfactorid)) {
                        			map.put("olzsavg" + temfactorid, StrUtil.trimToString(mapdata.get("TZSAVG" + temfactorid.toUpperCase())));
                        			map.put("olzsmin" + temfactorid, StrUtil.trimToString(mapdata.get("TZSMIN" + temfactorid.toUpperCase())));
                        			map.put("olzsmax" + temfactorid, StrUtil.trimToString(mapdata.get("TZSMAX" + temfactorid.toUpperCase())));
                        			map.put("olzscou" + temfactorid, StrUtil.trimToString(mapdata.get("TZSCOU" + temfactorid.toUpperCase())));
                        		}
                            }
                    		listdata.add(map);
                    	}
                    }
                	
                }else {
            		Map map = new HashMap();
            		map.put("datatime", "");
            		if ("2011".equals(datatype))  {//实时值
            			for (int j = 0; j < factorsBlock.getRowCount(); j++)   {
                			String temfactorid = StrUtil.trimToString(factorsBlock.getRow(j).get("factorid"));
                            map.put("olavg" + temfactorid, "");
                            String temusezs = StrUtil.trimToString(factorsBlock.getRow(j).get("usezs"));
                          //if("1".equals(temusezs)) {
                    		if("01".equals(temfactorid)||"02".equals(temfactorid)||"03".equals(temfactorid)) {
                    			map.put("olzsavg" + temfactorid, "");
                    		}
                        }
                    }
                    else  {//其他
                    	for (int j = 0; j < factorsBlock.getRowCount(); j++)  {
                			String temfactorid = StrUtil.trimToString(factorsBlock.getRow(j).get("factorid"));
                            map.put("olavg" + temfactorid, "");
                            map.put("olmin" + temfactorid, "");
                            map.put("olmax" + temfactorid, "");
                            map.put("olcou" + temfactorid, "");
                            String temusezs = StrUtil.trimToString(factorsBlock.getRow(j).get("usezs"));
                          //if("1".equals(temusezs)) {
                    		if("01".equals(temfactorid)||"02".equals(temfactorid)||"03".equals(temfactorid)) {
                    			map.put("olzsavg" + temfactorid, "");
                    			map.put("olzsmin" + temfactorid, "");
                    			map.put("olzsmax" + temfactorid, "");
                    			map.put("olzscou" + temfactorid, "");
                    		}
                        }
                    }
            		
            		listdata.add(map);
                }
                block.setRows(listdata);
            }
            outInfo.setBlock(block);
        }
        catch (Exception e)  {
        	outInfo.setBlock(block);
            throw e;
        }
        return outInfo;
    }
    
    public String getDataSqlByType(String mn, String datatype, String startdate, String enddate) throws Exception {
    	String sql = "";
    	try {
    		String startdatetime = startdate.trim() + " 00:00:00";
    		String enddatetime = enddate.trim() + " 23:59:59";
    		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	Calendar startcalendar = Calendar.getInstance();
        	startcalendar.setTime(dateTimeFormat.parse(startdatetime));	
        	Calendar endcalendar = Calendar.getInstance();
        	endcalendar.setTime(dateTimeFormat.parse(enddatetime));	
        	
        	SimpleDateFormat tableFormat = new SimpleDateFormat("yyyyMM");
        	Date startDate = startcalendar.getTime();
        	Date endDate = endcalendar.getTime();
            switch (datatype){
                case "2011":
                    if (startcalendar.get(Calendar.MONTH) == endcalendar.get(Calendar.MONTH))  {
                        sql = "select t1.MN,t1.FACTORID,t1.DATATIME,substr(t1.DATATIME,1,13) indextime,t1.RTD,t1.ZSRTD from " + DbSchema + ".DATATABLE_RTD_" + tableFormat.format(startcalendar.getTime()) + " t1 where t1.MN = '" + mn + "' and t1.DATATIME >= '" + startdatetime + "' and t1.DATATIME <= '" + enddatetime + "'";
                    } else  {
                        sql = "select t1.MN,t1.FACTORID,t1.DATATIME,substr(t1.DATATIME,1,13) indextime,t1.RTD,t1.ZSRTD from " + DbSchema + ".DATATABLE_RTD_" + tableFormat.format(startcalendar.getTime()) + " t1 where t1.MN = '" + mn + "' and t1.DATATIME >= '" + startdatetime + "' and t1.DATATIME <= '" + enddatetime + "'";
                        sql += " union select t2.MN,t2.FACTORID,t2.DATATIME,substr(t1.DATATIME,1,13) indextime,t2.RTD,t2.ZSRTD from " + DbSchema + ".DATATABLE_RTD_" + tableFormat.format(endcalendar.getTime()) + " t2 where t2.MN = '" + mn + "' and t2.DATATIME >= '" + startdatetime + "' and t2.DATATIME <= '" + enddatetime + "'";
                    }
                    break;
                case "2051":
                	if (startcalendar.get(Calendar.MONTH) == endcalendar.get(Calendar.MONTH)) {
                		sql = "select t1.MN,t1.FACTORID,t1.DATATIME,substr(t1.DATATIME,1,16) indextime,t1.AVG,t1.ZSAVG,t1.MIN,t1.ZSMIN,t1.MAX,t1.ZSMAX,t1.COU,t1.ZSCOU from " + DbSchema + ".DATATABLE_MIN_" + tableFormat.format(startcalendar.getTime()) + " t1 where t1.MN = '" + mn + "' and t1.DATATIME >= '" + startdatetime + "' and t1.DATATIME <= '" + enddatetime + "'";
                    } else {
                    	sql = "select t1.MN,t1.FACTORID,t1.DATATIME,substr(t1.DATATIME,1,16) indextime,t1.AVG,t1.ZSAVG,t1.MIN,t1.ZSMIN,t1.MAX,t1.ZSMAX,t1.COU,t1.ZSCOU from " + DbSchema + ".DATATABLE_MIN_" + tableFormat.format(startcalendar.getTime()) + " t1 where t1.MN = '" + mn + "' and t1.DATATIME >= '" + startdatetime + "' and t1.DATATIME <= '" + enddatetime + "'";
                        sql += " union select t2.MN,t2.FACTORID,t2.DATATIME,substr(t1.DATATIME,1,16) indextime,t2.AVG,t2.ZSAVG,t2.MIN,t2.ZSMIN,t2.MAX,t2.ZSMAX,t2.COU,t2.ZSCOU from " + DbSchema + ".DATATABLE_MIN_" + tableFormat.format(endcalendar.getTime()) + " t2 where t2.MN = '" + mn + "' and t2.DATATIME >= '" + startdatetime + "' and t2.DATATIME <= '" + enddatetime + "'";
                    }
                    break;
                case "2061":
                    if (startcalendar.get(Calendar.YEAR) == endcalendar.get(Calendar.YEAR)) {
                        sql = "select t1.MN,t1.FACTORID,t1.DATATIME,substr(t1.DATATIME,1,13) indextime,t1.AVG,t1.ZSAVG,t1.MIN,t1.ZSMIN,t1.MAX,t1.ZSMAX,t1.COU,t1.ZSCOU from " + DbSchema + ".DATATABLE_HOUR_" + startcalendar.get(Calendar.YEAR) + " t1 where t1.MN = '" + mn + "' and t1.DATATIME >= '" + startdatetime + "' and t1.DATATIME <= '" + enddatetime + "'";
                    } else  {
                        sql = "select t1.MN,t1.FACTORID,t1.DATATIME,substr(t1.DATATIME,1,13) indextime,t1.AVG,t1.ZSAVG,t1.MIN,t1.ZSMIN,t1.MAX,t1.ZSMAX,t1.COU,t1.ZSCOU from " + DbSchema + ".DATATABLE_HOUR_" + startcalendar.get(Calendar.YEAR) + " t1 where t1.MN = '" + mn + "' and t1.DATATIME >= '" + startdatetime + "' and t1.DATATIME <= '" + enddatetime + "'";
                        sql += " union select t2.MN,t2.FACTORID,t2.DATATIME,substr(t1.DATATIME,1,13) indextime,t2.AVG,t2.ZSAVG,t2.MIN,t2.ZSMIN,t2.MAX,t2.ZSMAX,t2.COU,t2.ZSCOU from " + DbSchema + ".DATATABLE_HOUR_" + endcalendar.get(Calendar.YEAR) + " t2 where t2.MN = '" + mn + "' and t2.DATATIME >= '" + startdatetime + "' and t2.DATATIME <= '" + enddatetime + "'";
                    }
                    break;
                case "2031":
                    if (startcalendar.get(Calendar.YEAR) == endcalendar.get(Calendar.YEAR)) {
                        sql = "select t1.MN,t1.FACTORID,t1.DATATIME,substr(t1.DATATIME,1,10) indextime,t1.AVG,t1.ZSAVG,t1.MIN,t1.ZSMIN,t1.MAX,t1.ZSMAX,t1.COU,t1.ZSCOU from " + DbSchema + ".DATATABLE_DAY_" + startcalendar.get(Calendar.YEAR) + " t1 where t1.MN = '" + mn + "' and t1.DATATIME >= '" + startdatetime + "' and t1.DATATIME <= '" + enddatetime + "'";
                    } else  {
                        sql = "select t1.MN,t1.FACTORID,t1.DATATIME,substr(t1.DATATIME,1,10) indextime,t1.AVG,t1.ZSAVG,t1.MIN,t1.ZSMIN,t1.MAX,t1.ZSMAX,t1.COU,t1.ZSCOU from " + DbSchema + ".DATATABLE_DAY_" + startcalendar.get(Calendar.YEAR) + " t1 where t1.MN = '" + mn + "' and t1.DATATIME >= '" + startdatetime + "' and t1.DATATIME <= '" + enddatetime + "'";
                        sql += " union select t2.MN,t2.FACTORID,t2.DATATIME,substr(t1.DATATIME,1,10) indextime,t2.AVG,t2.ZSAVG,t2.MIN,t2.ZSMIN,t2.MAX,t2.ZSMAX,t2.COU,t2.ZSCOU from " + DbSchema + ".DATATABLE_DAY_" + endcalendar.get(Calendar.YEAR) + " t2 where t2.MN = '" + mn + "' and t2.DATATIME >= '" + startdatetime + "' and t2.DATATIME <= '" + enddatetime + "'";
                    }
                    break;
            }
    	}
    	catch (Exception e) {
            throw e;
        }
        return sql;
    }
    
    /**
	 * 创建Excel表格，生产报表文件
	 * @param inInfo
	 * @return
	 * @throws IOException 
	 */
	public EiInfo exportExcel(EiInfo inInfo) throws Exception {
		try {
			
			//获取配置文件里的参数
			ResourceBundle dbPro = ResourceBundle.getBundle("application");
			String path = dbPro.getString("manualFilePath") + "onlineExport/";
	        String reportName = "onlineData.xlsx";
	        File buildFile = new File(path);
	        if (!buildFile.exists() && !buildFile.isDirectory()) {
	            buildFile.mkdir();
	        }
 
	        File newFile = new File(path + reportName);
	        if (newFile.exists() && newFile.isFile()) {
	        	newFile.delete();
	        } 
	        onlineInfoIntoExcel(inInfo, path, reportName);
	        inInfo.set("filepath", path + reportName);
            inInfo.setStatus(1);
			inInfo.setMsg("报表生成成功");
		} catch (Exception ex) {
			inInfo.setStatus(-1);
		    inInfo.setMsg("报表导出出错" + ex.toString());
		}
		return inInfo;
	}
	
	public void onlineInfoIntoExcel(EiInfo inInfo, String path, String reportName) throws Exception {
		File excelFile = new File(path + reportName);
        Workbook wb = new SXSSFWorkbook();
        if(wb != null){
        	String siteid = inInfo.getString("inqu_status-0-siteid");
        	String typeid = inInfo.getString("inqu_status-0-typeid");
        	String startdate = inInfo.getString("inqu_status-0-startdate");
        	String enddate = inInfo.getString("inqu_status-0-enddate");
			String sitename = getSiteName(siteid);
            //获取第一个sheet
			Sheet sheet = wb.createSheet(sitename);
			EiInfo outInfo = query(inInfo);
			
			EiBlock block = outInfo.getBlock("oldbdata");
			
			if(block != null && block.getRowCount() > 0) {
				List<String> factors = new ArrayList<String>();
				Map meta = block.getBlockMeta().getMetas();
				Row titlerow = sheet.createRow(0);
				int count = 0;
				CellStyle style1 = wb.createCellStyle();
				style1.setWrapText(true);//自动换行
				for(Object key : meta.keySet()) {
					EiColumn column = (EiColumn)meta.get(String.valueOf(key));
					Cell cell = titlerow.createCell(count);
					cell.setCellStyle(style1);
					cell.setCellValue(String.valueOf(column.getDescName()).replace("</br>", "\n"));
					factors.add(String.valueOf(column.getName()));
					count++;
				}
				 List<Map<String, String>> dataList= new ArrayList<Map<String,String>>();
				for(int i = 0; i < block.getRowCount(); i++ ){
					 Map map = (HashMap<String, String>)block.getRow(i);
					 dataList.add(map);
				}
				//时间差：天
				Integer time=DateUtil.getBetweenDays(startdate, enddate,"DAY")+1;
				
				List<Map<String, Object>> timeList= new ArrayList<Map<String,Object>>();//数据缺失集合
				Map<String, Object> timemap=null;
            	int index=0;
				switch (typeid){
	                case "2011"://实时值
	                	for(int i = 0; i < block.getRowCount(); i++ ) {
	    					 Map map = (HashMap<String, String>)block.getRow(i);
	    			         Row row = sheet.createRow(i+1);
	    			         for(int j = 0; j < factors.size(); j++ )  {
	    			        	 Cell cell = row.createCell(j);
	    					     if(j == 1) {
	    					    	 String datetime = String.valueOf(map.get(factors.get(j))).replace("-", "/");
	    					    	 cell.setCellValue(datetime);
	    			        	 }else {
	    			        		 cell.setCellValue(StrUtil.trimToDouble(map.get(factors.get(j))));
	    			        	 }
	    					 }
	    				}
	                	
	                	
	                	//缺失数据
	                	if(time*288!=block.getRowCount()) {
	                		for (int i = 0; i < time*24; i++) {
	                			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH");
		                		startdate+=" 00";
		                		Date indextimedate=DateUtil.addHour(sdf.parse(startdate), i);
		                		String indextime=sdf.format(indextimedate);
		                		//获取对应时间的记录list
		                		List<Map<String, String>> indexList=dataList.stream().filter(data -> Objects.equals(data.get("indextime"),indextime)).collect(Collectors.toList());
		                		if(!StrUtil.listIsNotNullOrEmpty(indexList)||(StrUtil.listIsNotNullOrEmpty(indexList)&&indexList.size()<12)) {
		                			timemap=new HashMap<>();
		                			timemap.put("index"+String.format("%03d", index), indextime+","+(12-indexList.size()));
		                			timeList.add(timemap);
		                			index++;
		                		}
	                		}
	                		
	                		String times="";
	                		//备注行
	                		Row remakerow = sheet.createRow(block.getRowCount()+1);
	                		Cell cell1 = remakerow.createCell(1);
	                		cell1.setCellValue("备注-数据丢失小时段");
	                		
	                		Row lostRow = null;
	                		Cell timecell1 =null;
	                		Cell timecell2 =null;
	                		for (int i=0; i<timeList.size();i++) {
	                			lostRow = sheet.createRow(block.getRowCount()+2+i);
	                			times=(String) timeList.get(i).get("index"+String.format("%03d", i));
                				timecell1 = lostRow.createCell(1);
                				timecell1.setCellValue(times.substring(0,times.indexOf(",")).replace("-", "/"));
								
                				timecell2 = lostRow.createCell(2);
                				timecell2.setCellValue("数据丢失-"+times.substring(times.indexOf(",")+1));
							}
	                	}
	                    break;
	                case "2051"://分钟值:10min一条
	                	for (int i = 0; i < time*24*6; i++) {
	                		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	                		startdate+=" 00:00";
	                		Row row = sheet.createRow(i+1);
	                		Date indextimedate=DateUtil.addMin(sdf.parse(startdate), i*10);
	                		String indextime=sdf.format(indextimedate);
	                		//获取对应时间的记录list
	                		List<Map<String, String>> indexList=dataList.stream().filter(data -> Objects.equals(data.get("indextime"),indextime)).collect(Collectors.toList());
	                		if (StrUtil.listIsNotNullOrEmpty(indexList)) {
	                			for(int j = 0; j < factors.size(); j++ ) {
		       			        	 Cell cell = row.createCell(j);
		       					     if(j == 1) {
		       					    	 String datetime = String.valueOf(indexList.get(0).get(factors.get(j))).replace("-", "/");
		       					    	 cell.setCellValue(datetime.substring(0,16));
		       			        	 }else {
		       			        		 //cell.setCellValue(String.valueOf(indexList.get(0).get(factors.get(j))));
		       			        		cell.setCellValue(StrUtil.trimToDouble(indexList.get(0).get(factors.get(j))));
		       			        	 }
		       					 }
							}else {
								 Cell cell = row.createCell(1);
								 cell.setCellValue(indextime.replace("-", "/"));
							}
	                	}
	                	//缺失数据
	                	if(time*144!=block.getRowCount()) {
	                		for (int i = 0; i < time*24; i++) {
	                			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH");
		                		startdate+=" 00";
		                		Date indextimedate=DateUtil.addHour(sdf.parse(startdate), i);
		                		String indextime=sdf.format(indextimedate);
		                		//获取对应时间的记录list
		                		List<Map<String, String>> indexList=dataList.stream().filter(data -> Objects.equals(data.get("indextime").substring(0,13),indextime)).collect(Collectors.toList());
		                		//分钟值1h小于6有缺失数据
		                		if(!StrUtil.listIsNotNullOrEmpty(indexList)||(StrUtil.listIsNotNullOrEmpty(indexList)&&indexList.size()<6)) {
		                			timemap=new HashMap<>();
		                			timemap.put("index"+String.format("%03d", index), indextime+","+(6-indexList.size()));
		                			timeList.add(timemap);
		                			index++;
		                		}
	                		}
	                		
	                		String times="";
	                		//备注行
	                		//Row remakerow = sheet.createRow(block.getRowCount()+1);
	                		Row remakerow = sheet.createRow(time*144+1);
	                		Cell cell1 = remakerow.createCell(1);
	                		cell1.setCellValue("备注-数据丢失小时段");
	                		
	                		Row lostRow = null;
	                		Cell timecell1 =null;
	                		Cell timecell2 =null;
	                		for (int i=0; i<timeList.size();i++) {
	                			lostRow = sheet.createRow(time*144+2+i);
	                			times=(String) timeList.get(i).get("index"+String.format("%03d", i));
                				timecell1 = lostRow.createCell(1);
                				timecell1.setCellValue(times.substring(0,times.indexOf(",")).replace("-", "/"));
								
                				timecell2 = lostRow.createCell(2);
                				timecell2.setCellValue("数据丢失-"+times.substring(times.indexOf(",")+1));
							}
	                	}
	                    break;								
	                case "2061":
	                	for (int i = 0; i < time*24; i++) {
	                		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH");
	                		startdate+=" 00";
	                		Row row = sheet.createRow(i+1);
	                		Date indextimedate=DateUtil.addHour(sdf.parse(startdate), i);
	                		String indextime=sdf.format(indextimedate);
	                		//获取对应时间的记录list
	                		List<Map<String, String>> indexList=dataList.stream().filter(data -> Objects.equals(data.get("indextime"),indextime)).collect(Collectors.toList());
	                		if (StrUtil.listIsNotNullOrEmpty(indexList)) {
	                			for(int j = 0; j < factors.size(); j++ ) {
		       			        	 Cell cell = row.createCell(j);
		       					     if(j == 1) {
		       					    	 String datetime = String.valueOf(indexList.get(0).get(factors.get(j))).replace("-", "/");
		       					    	 cell.setCellValue(datetime.substring(0,13));
		       			        	 }else {
		       			        		 cell.setCellValue(StrUtil.trimToDouble(indexList.get(0).get(factors.get(j))));
		       			        	 }
		       					 }
							}else {
								 Cell cell = row.createCell(1);
								 cell.setCellValue(indextime.replace("-", "/"));
							}
	                	}
	                	//缺失数据
	                	if(time*24!=block.getRowCount()) {
	                		for (int i = 0; i < time*24; i++) {
	                			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH");
		                		startdate+=" 00";
		                		Date indextimedate=DateUtil.addHour(sdf.parse(startdate), i);
		                		String indextime=sdf.format(indextimedate);
		                		//获取对应时间的记录list
		                		List<Map<String, String>> indexList=dataList.stream().filter(data -> Objects.equals(data.get("indextime"),indextime)).collect(Collectors.toList());
		                		//小时值1h小于1有缺失数据
		                		if(!StrUtil.listIsNotNullOrEmpty(indexList)||(StrUtil.listIsNotNullOrEmpty(indexList)&&indexList.size()<1)) {
		                			timemap=new HashMap<>();
		                			timemap.put("index"+String.format("%03d", index), indextime+","+(1-indexList.size()));
		                			timeList.add(timemap);
		                			index++;
		                		}
	                		}
	                		
	                		String times="";
	                		//备注行
	                		//Row remakerow = sheet.createRow(block.getRowCount()+1);
	                		Row remakerow = sheet.createRow(time*24+1);
	                		Cell cell1 = remakerow.createCell(1);
	                		cell1.setCellValue("备注-数据丢失小时段");
	                		
	                		Row lostRow = null;
	                		Cell timecell1 =null;
	                		Cell timecell2 =null;
	                		for (int i=0; i<timeList.size();i++) {
	                			lostRow = sheet.createRow(time*24+2+i);
	                			times=(String) timeList.get(i).get("index"+String.format("%03d", i));
                				timecell1 = lostRow.createCell(1);
                				timecell1.setCellValue(times.substring(0,times.indexOf(",")).replace("-", "/"));
								
                				timecell2 = lostRow.createCell(2);
                				timecell2.setCellValue("数据丢失-"+times.substring(times.indexOf(",")+1));
							}
	                	}
	                    break;
	                case "2031":
	                	for (int i = 0; i < time; i++) {
	                		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	                		Row row = sheet.createRow(i+1);
	                		Date indextimedate=DateUtil.addDateTime(sdf.parse(startdate),"DAY", i);
	                		String indextime=sdf.format(indextimedate);
	                		//获取对应时间的记录list
	                		List<Map<String, String>> indexList=dataList.stream().filter(data -> Objects.equals(data.get("indextime"),indextime)).collect(Collectors.toList());
	                		if (StrUtil.listIsNotNullOrEmpty(indexList)) {
	                			for(int j = 0; j < factors.size(); j++ ) {
		       			        	 Cell cell = row.createCell(j);
		       					     if(j == 1) {
		       					    	 String datetime = String.valueOf(indexList.get(0).get(factors.get(j))).replace("-", "/");
		       					    	 cell.setCellValue(datetime.substring(0,10));
		       			        	 }else {
		       			        		 //cell.setCellValue(String.valueOf(indexList.get(0).get(factors.get(j))));
		       			        		cell.setCellValue(StrUtil.trimToDouble(indexList.get(0).get(factors.get(j))));
		       			        	 }
		       					 }
							}else {
								 Cell cell = row.createCell(1);
								 cell.setCellValue(indextime.replace("-", "/"));
							}
	                	}
	                	//缺失数据
	                	if(time!=block.getRowCount()) {
	                		for (int i = 0; i < time; i++) {
	                			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		                		Date indextimedate=DateUtil.addDateTime(sdf.parse(startdate),"DAY", i);
		                		String indextime=sdf.format(indextimedate);
		                		//获取对应时间的记录list
		                		List<Map<String, String>> indexList=dataList.stream().filter(data -> Objects.equals(data.get("indextime"),indextime)).collect(Collectors.toList());
		                		//日均值1d小于1有缺失数据
		                		if(!StrUtil.listIsNotNullOrEmpty(indexList)||(StrUtil.listIsNotNullOrEmpty(indexList)&&indexList.size()<1)) {
		                			timemap=new HashMap<>();
		                			timemap.put("index"+String.format("%03d", index), indextime+","+(1-indexList.size()));
		                			timeList.add(timemap);
		                			index++;
		                		}
	                		}
	                		
	                		String times="";
	                		//备注行
	                		//Row remakerow = sheet.createRow(block.getRowCount()+1);
	                		Row remakerow = sheet.createRow(time+1);
	                		Cell cell1 = remakerow.createCell(1);
	                		cell1.setCellValue("备注-数据丢失日期");
	                		
	                		Row lostRow = null;
	                		Cell timecell1 =null;
	                		Cell timecell2 =null;
	                		for (int i=0; i<timeList.size();i++) {
	                			lostRow = sheet.createRow(time+2+i);
	                			times=(String) timeList.get(i).get("index"+String.format("%03d", i));
                				timecell1 = lostRow.createCell(1);
                				timecell1.setCellValue(times.substring(0,times.indexOf(",")).replace("-", "/"));
								
                				timecell2 = lostRow.createCell(2);
                				timecell2.setCellValue("数据丢失-"+times.substring(times.indexOf(",")+1));
							}
	                	}
	                    break;
				}
			}
			
			//列宽
			//sheet.setColumnWidth(1, 240);
			sheet.setColumnWidth(0, 256*50+184);
			//隐藏列
			sheet.setColumnHidden(0, true);
        }
        FileOutputStream out = new FileOutputStream(excelFile);
        wb.write(out);
        out.close();
     }
	
	private String getSiteName (String siteid) throws Exception
	{
    	if(StringUtils.isBlank(siteid)) {
    		return "";
    	}
    	String siteName = "";
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("siteid", siteid);
        List list = this.dao.query("tduhb07.query", map);
        if(list != null && list.size() > 0) {
        	siteName = String.valueOf(((Tduhb07)list.get(0)).getSitename());
        }
		return siteName;
	}
}
