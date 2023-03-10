/**
 *
 */
package com.baosight.sggk.du.hc.service;

import com.baosight.sggk.common.du.domain.Tduhb07;
import com.baosight.sggk.util.BaseUtil;
import com.baosight.sggk.util.DateUtil;
import com.baosight.sggk.util.PermissionUtil;
import com.baosight.sggk.util.StrUtil;
import com.baosight.iplat4j.core.ei.*;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.*;

public class ServiceDUHC2002 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHC2002.class);

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
		  String siteid = StringUtils.isBlank((String) inInfo.get("siteid")) ? "" : (String) inInfo.get("siteid");
		  outInfo.set("siteid", siteid);
		  
		  EiBlock queryblock = new EiBlock(EiConstant.queryBlock);
		  EiBlockMeta metadata = new EiBlockMeta();		 
		  EiColumn eiColumn = new EiColumn("departmentid");
		  metadata.addMeta(eiColumn);
		  eiColumn = new EiColumn("porttypeid");
		  metadata.addMeta(eiColumn);
		  eiColumn = new EiColumn("siteid");
		  metadata.addMeta(eiColumn);
		  eiColumn = new EiColumn("typeid");
		  metadata.addMeta(eiColumn);
		  eiColumn = new EiColumn("startdate");
		  metadata.addMeta(eiColumn);
		  eiColumn = new EiColumn("enddate");
		  metadata.addMeta(eiColumn);
		  queryblock.setBlockMeta(metadata);        
		  SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd");
		  Map<String,String> row = new HashMap<String,String>();
		  row.put("porttypeid", "%");
		  row.put("departmentid", status);
		  row.put("siteid", siteid);
		  row.put("startdate", dateTimeFormat.format(new Date()));
		  row.put("enddate", dateTimeFormat.format(new Date()));
		  row.put("type", "2011");
		  queryblock.addRow(row);
		  outInfo.addBlock(queryblock);
		  
		  //初始化oldataGrid
		  outInfo.addBlock("oldata");
		  
		  outInfo.setMsg("页面加载完成");
		  return outInfo;
	}
	
	
	public EiInfo query(EiInfo inInfo){
		EiInfo outInfo = new EiInfo();
		String siteid = inInfo.getString("inqu_status-0-siteid");
		String type = inInfo.getString("inqu_status-0-typeid");
		String startdate = inInfo.getString("inqu_status-0-startdate");
		String enddate = inInfo.getString("inqu_status-0-enddate");
		String isquery = inInfo.getString("isquery");
        String iscdp = inInfo.getString("iscdp");
		try{
			EiBlock olinfoBlock = getFactorsBySite(siteid,iscdp);
			outInfo.setBlock(olinfoBlock);
			EiBlock oldataheaderBlock = getDataHeaderByFactors(olinfoBlock);
			outInfo.setBlock(oldataheaderBlock);

			Map<String,String> timemap=new HashMap<>();
			String sql="SELECT REALBEGINTIME||':00' AS TWOBEGINTIME,REALENDTIME||':00' AS TWOENDTIME FROM "+DbSchema+".T_HF_HALTDECLARE " +
                    " WHERE STATUS='s3' AND SITEID='"+siteid+"'"+
                    " and REALBEGINTIME<='"+enddate+" 23:59:59'"+
                    " and REALENDTIME>='"+startdate+" 00:00:00'";
			timemap.put("sqlMap",sql);
            List list = this.dao.query("DUHA01.query", timemap, 0, -999999);
            List<Map<String,String>> timePeriodList= DateUtil.getTimePeriodByTimeAndTimelist(startdate+" 00:00:00",enddate+" 23:59:59",list);
			//加载在线数据
			outInfo = getDataByFactors(outInfo,timePeriodList, olinfoBlock, type, startdate, enddate,iscdp);
			outInfo.setMsg("数据加载完成");
        }catch(Exception ex) {
        	outInfo.setStatus(-1);
        	outInfo.setMsg(ex.toString());
        }
		return outInfo;
	}
	
	//根据站点查询监测因子
    private EiBlock getFactorsBySite(String siteid, String iscdp){
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
        eiColumn = new EiColumn("nkhighlimit");
        metadata.addMeta(eiColumn);
        eiColumn = new EiColumn("nklowlimit");
        metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("monitorid");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("factorname");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("unit");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("usezs");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("factordesc");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("limitdesc");
		metadata.addMeta(eiColumn);
		block.setBlockMeta(metadata); 
		List<Map> listdata = new ArrayList<>();
        try {
            if (StringUtils.isNotBlank(siteid)) {
            	String sql = "select t2.FACTORID,t2.USEZS,t4.FACTORNAME,t3.MNID,t3.MONITORID,t4.UNIT,t5.HIGHLIMIT,t5.LOWLIMIT,t5.NKHIGHLIMIT,t5.NKLOWLIMIT from " +
    			"(select t1.FACTORID,t1.SITEID,t1.DESCRIPTION,t1.USEZS from " + DbSchema + ".t_ha_sitefactor t1 where t1.SITEID = '" + siteid + "' and t1.TYPE = '2' and t1.STATUS = '1') t2 " + 
    			"LEFT JOIN " + DbSchema + ".t_ha_site t3 on t2.SITEID = t3.SITEID LEFT JOIN " + DbSchema + ".t_ha_factor t4 on t2.FACTORID= t4.FACTORID " + 
    			"LEFT JOIN " + DbSchema + ".t_ha_portfactor t5 on t3.PORTID = t5.PORTID and t2.FACTORID = t5.FACTORID ORDER BY t2.FACTORID ";

                Map sqlmap = new HashMap();
                sqlmap.put("sqlMap", sql);
                List list = this.dao.query("DUHA01.query", sqlmap);
                if (list != null && list.size() > 0) {
                	for (int i = 0; i < list.size(); i++) {									
                		Map mapdata = (HashMap)list.get(i);
                		Map map = new HashMap();
                		map.put("factorid", StrUtil.trimToString(mapdata.get("FACTORID")));
                		map.put("mnid", StrUtil.trimToString(mapdata.get("MNID")));
                		map.put("highlimit", StrUtil.trimToString(mapdata.get("HIGHLIMIT")));
                		map.put("lowlimit", StrUtil.trimToString(mapdata.get("LOWLIMIT")));
                        map.put("nkhighlimit", StrUtil.trimToString(mapdata.get("NKHIGHLIMIT")));
                        map.put("nklowlimit", StrUtil.trimToString(mapdata.get("NKLOWLIMIT")));
                		map.put("monitorid", StrUtil.trimToString(mapdata.get("MONITORID")));
                		map.put("factorname", StrUtil.trimToString(mapdata.get("FACTORNAME")));
                		map.put("unit", StrUtil.trimToString(mapdata.get("UNIT")));
                		map.put("usezs", StrUtil.trimToString(mapdata.get("USEZS")));
                		if("true".equals(iscdp)){
                            if (StringUtils.isNotBlank(StrUtil.trimToString(mapdata.get("NKHIGHLIMIT")))) {
                                if (StringUtils.isNotBlank(StrUtil.trimToString(mapdata.get("NKLOWLIMIT")))){
                                    map.put("factordesc", StrUtil.trimToString(mapdata.get("FACTORNAME")) + "(" + StrUtil.trimToString(mapdata.get("UNIT")) + ")" + "(" + StrUtil.trimToString(mapdata.get("NKLOWLIMIT")) + "-" + StrUtil.trimToString(mapdata.get("NKHIGHLIMIT")) + ")");
                                    map.put("limitdesc", StrUtil.trimToString(mapdata.get("NKLOWLIMIT")) + "-" + StrUtil.trimToString(mapdata.get("NKHIGHLIMIT")));
                                } else  {
                                    map.put("factordesc", StrUtil.trimToString(mapdata.get("FACTORNAME")) + "(" + StrUtil.trimToString(mapdata.get("UNIT")) + ")" + "(<" + StrUtil.trimToString(mapdata.get("NKHIGHLIMIT")) + ")");
                                    map.put("limitdesc", "<" + StrUtil.trimToString(mapdata.get("NKHIGHLIMIT")));
                                }
                            } else{
                                map.put("factordesc", StrUtil.trimToString(mapdata.get("FACTORNAME")) + "(" + StrUtil.trimToString(mapdata.get("UNIT")) + ")");
                                map.put("limitdesc", "");
                            }
                        }else{
                            if (StringUtils.isNotBlank(StrUtil.trimToString(mapdata.get("HIGHLIMIT")))) {
                                if (StringUtils.isNotBlank(StrUtil.trimToString(mapdata.get("LOWLIMIT")))){
                                    map.put("factordesc", StrUtil.trimToString(mapdata.get("FACTORNAME")) + "(" + StrUtil.trimToString(mapdata.get("UNIT")) + ")" + "(" + StrUtil.trimToString(mapdata.get("LOWLIMIT")) + "-" + StrUtil.trimToString(mapdata.get("HIGHLIMIT")) + ")");
                                    map.put("limitdesc", StrUtil.trimToString(mapdata.get("LOWLIMIT")) + "-" + StrUtil.trimToString(mapdata.get("HIGHLIMIT")));
                                } else  {
                                    map.put("factordesc", StrUtil.trimToString(mapdata.get("FACTORNAME")) + "(" + StrUtil.trimToString(mapdata.get("UNIT")) + ")" + "(<" + StrUtil.trimToString(mapdata.get("HIGHLIMIT")) + ")");
                                    map.put("limitdesc", "<" + StrUtil.trimToString(mapdata.get("HIGHLIMIT")));
                                }
                            } else{
                                map.put("factordesc", StrUtil.trimToString(mapdata.get("FACTORNAME")) + "(" + StrUtil.trimToString(mapdata.get("UNIT")) + ")");
                                map.put("limitdesc", "");
                            }
                        }

                		listdata.add(map);
    				}
                }
            }
            block.setRows(listdata);
        }catch (Exception ex) {
            throw ex;
        }
        return block;
    }
    
    //根据因子信息绑定grid头信息
    private EiBlock getDataHeaderByFactors(EiBlock factorsBlock) {
        EiBlock block = new EiBlock("oldata");
        block.set("count",1);
        EiBlockMeta metadata = new EiBlockMeta();		 
		EiColumn eiColumn = new EiColumn("datatime");
        eiColumn.setDescName("日期");
        eiColumn.setWidth(160);
        metadata.addMeta(eiColumn);

        eiColumn=new EiColumn("flag");
        eiColumn.setDescName("是否停运期间");
        eiColumn.setWidth(60);
        eiColumn.setVisible(false);
        metadata.addMeta(eiColumn);
        try {
            if (factorsBlock != null && factorsBlock.getRowCount() > 0){
                String temMN = StrUtil.trimToString(factorsBlock.getRow(0).get("mnid"));
                for (int i = 0; i < factorsBlock.getRowCount(); i++){
                    String temfactorid = StrUtil.trimToString(factorsBlock.getRow(i).get("factorid"));
                    String temfactorname = StrUtil.trimToString(factorsBlock.getRow(i).get("factorname"));
                    eiColumn = new EiColumn("ol" + temfactorid);
                    if(StringUtils.isNotBlank(StrUtil.trimToString(factorsBlock.getRow(i).get("unit")))) {
                    	temfactorname = temfactorname + "<br/>(" + StrUtil.trimToString(factorsBlock.getRow(i).get("unit")) + ")";
                    }
                    eiColumn.setDescName(temfactorname);
            		metadata.addMeta(eiColumn);

            		eiColumn = new EiColumn("ol" + temfactorid + "limit");
            		eiColumn.setVisible(false);
            		metadata.addMeta(eiColumn);
            		String temusezs = StrUtil.trimToString(factorsBlock.getRow(i).get("usezs"));
            		//if("1".equals(temusezs)) {//是否使用折算值
            		//颗粒物，二氧化硫，氮氧化物显示折算值
            		if("01".equals(temfactorid)||"02".equals(temfactorid)||"03".equals(temfactorid)) {
            			eiColumn = new EiColumn("olzs" + temfactorid);
                        String temfactornamezs = StrUtil.trimToString(factorsBlock.getRow(i).get("factorname"))+"(折算)";
                        if(StringUtils.isNotBlank(StrUtil.trimToString(factorsBlock.getRow(i).get("unit")))) {
                            temfactornamezs = temfactornamezs + "<br/>(" + StrUtil.trimToString(factorsBlock.getRow(i).get("unit")) + ")";
                        }
                        eiColumn.setDescName(temfactornamezs);
                		metadata.addMeta(eiColumn);


            		}

                    //状态:颗粒物、SO2、NOX、氧含量、烟气流速
                    if("01".equals(temfactorid)||"02".equals(temfactorid)||"03".equals(temfactorid)
                            ||"S01".equals(temfactorid)||"S02".equals(temfactorid)) {
                        eiColumn = new EiColumn("T" + temfactorid+"FLAG");
                        eiColumn.setDescName(StrUtil.trimToString(factorsBlock.getRow(i).get("factorname"))+"<br/>状态");
                        metadata.addMeta(eiColumn);
                    }
                }
                block.setBlockMeta(metadata); 
            }
        }catch (Exception e) {
            throw e;
        }
        return block;
    }
    
    private List<String> getShowFactorList(String monitorid){
    	List<String> factormap = new ArrayList<String>();
    	try{
	    	String factorsql = "SELECT DISTINCT(FACTORID) FROM " + DbSchema + ".T_HA_OLSHOWFACTOR WHERE MONITORID = '" + monitorid + "' ";
			Map sqlmap = new HashMap();
	        sqlmap.put("sqlMap", factorsql);
	        List showfactorlist = this.dao.query("DUHA01.query", sqlmap);
	
	        if(showfactorlist != null && showfactorlist.size() > 0) {
	        	for (int i = 0; i < showfactorlist.size(); i++) {
	        		String temfactor = (String)((HashMap)showfactorlist.get(i)).get("FACTORID");
	        		if(!"B01".equals(temfactor) && !"B02".equals(temfactor)) {
	        			factormap.add(temfactor);
	        		}		
	        	}
	        }
        }catch (Exception e){
            throw e;
        }
        return factormap;
    }
    
    //加载在线数据
    private EiInfo getDataByFactors(EiInfo outInfo, List<Map<String,String>> timePeriodList, EiBlock factorsBlock, String datatype, String startdate,
                                    String enddate, String iscdp) throws Exception{
        EiBlock block = new EiBlock("oldata");
        EiBlockMeta metadata = new EiBlockMeta();		 
		EiColumn eiColumn = new EiColumn("datatime");
		eiColumn.setDescName("日期");
		eiColumn.setWidth(160);
		metadata.addMeta(eiColumn);

        eiColumn=new EiColumn("flag");
        eiColumn.setDescName("是否在停运期间");
        eiColumn.setWidth(60);
        eiColumn.setVisible(false);//隐藏
        metadata.addMeta(eiColumn);
        
        try {
            if (factorsBlock != null && factorsBlock.getRowCount() > 0){
                String factorSql = "select ";
                String temMN = StrUtil.trimToString(factorsBlock.getRow(0).get("mnid"));
                for (int i = 0; i < factorsBlock.getRowCount(); i++){
                    String temfactorid = StrUtil.trimToString(factorsBlock.getRow(i).get("factorid"));
                    String temfactorname = StrUtil.trimToString(factorsBlock.getRow(i).get("factorname"));
                    eiColumn = new EiColumn("ol" + temfactorid);
                    if(StringUtils.isNotBlank(StrUtil.trimToString(factorsBlock.getRow(i).get("unit")))) {
                    	temfactorname = temfactorname + "<br/>(" + StrUtil.trimToString(factorsBlock.getRow(i).get("unit")) + ")";
                    	if(StringUtils.isNotBlank(StrUtil.trimToString(factorsBlock.getRow(i).get("limitdesc")))) {
                    		temfactorname = temfactorname + "<br/>(" + StrUtil.trimToString(factorsBlock.getRow(i).get("limitdesc")) + ")";
                    	}
                    }else if("001".equals(temfactorid)&&"pH值".equals(temfactorname)) {
                    	if(StringUtils.isNotBlank(StrUtil.trimToString(factorsBlock.getRow(i).get("limitdesc")))) {
                    		temfactorname = temfactorname + "<br/>(" + StrUtil.trimToString(factorsBlock.getRow(i).get("limitdesc")) + ")";
                    	}
                    }
                    eiColumn.setDescName(temfactorname);
            		metadata.addMeta(eiColumn);

            		eiColumn = new EiColumn("ol" + temfactorid + "limit");
            		eiColumn.setVisible(false);
            		metadata.addMeta(eiColumn);
            		String temusezs = StrUtil.trimToString(factorsBlock.getRow(i).get("usezs"));
            		//if("1".equals(temusezs)) {
            		if("01".equals(temfactorid)||"02".equals(temfactorid)||"03".equals(temfactorid)) {
            			eiColumn = new EiColumn("olzs" + temfactorid);
            			String temfactornamezs = StrUtil.trimToString(factorsBlock.getRow(i).get("factorname"))+"(折算)";
                        if(StringUtils.isNotBlank(StrUtil.trimToString(factorsBlock.getRow(i).get("unit")))) {
                        	temfactornamezs = temfactornamezs + "<br/>(" + StrUtil.trimToString(factorsBlock.getRow(i).get("unit")) + ")";
                        	if(StringUtils.isNotBlank(StrUtil.trimToString(factorsBlock.getRow(i).get("limitdesc")))) {
                        		temfactornamezs = temfactornamezs + "<br/>(" + StrUtil.trimToString(factorsBlock.getRow(i).get("limitdesc"))+ ")";
                        	}
                        }
                        eiColumn.setDescName(temfactornamezs);
                		metadata.addMeta(eiColumn);


            		}
            		//累计风量
            		if("B02".equals(temfactorid)&&!"2011".equals(datatype)) {
            			eiColumn = new EiColumn("ol" + temfactorid+"cou");
            			eiColumn.setDescName("累计风量"+"</br>"+"(m3)");
                 		metadata.addMeta(eiColumn);
            		}

                    //状态:颗粒物、SO2、NOX、氧含量、烟气流速
                    if("01".equals(temfactorid)||"02".equals(temfactorid)||"03".equals(temfactorid)
                            ||"S01".equals(temfactorid)||"S02".equals(temfactorid)) {
                        eiColumn = new EiColumn("T" + temfactorid+"FLAG");
                        eiColumn.setDescName(StrUtil.trimToString(factorsBlock.getRow(i).get("factorname"))+"<br/>状态");
                        metadata.addMeta(eiColumn);
                    }


            		//标记位
                    factorSql +="max( CASE WHEN t5.FACTORID = '" + temfactorid + "' THEN t5.FLAG END ) T" + temfactorid +"FLAG"+",";
                    if ("2011".equals(datatype)){
                        //factorSql += "max( CASE t5.FACTORID WHEN '" + temfactorid + "' THEN t5.RTD ELSE 0 END ) '" + temfactorid + "',";
                        factorSql += "max( CASE WHEN t5.FACTORID = '" + temfactorid + "' THEN t5.RTD END ) T" + temfactorid.trim() + ",max( CASE WHEN t5.FACTORID = '" + temfactorid + "' THEN t5.ZSRTD END ) TZS" + temfactorid.trim() + ",";
                    } else{
                        //factorSql += "max( CASE t5.FACTORID WHEN '" + temfactorid + "' THEN t5.AVG ELSE 0 END ) '" + temfactorid + "',";
                        factorSql += "max( CASE WHEN t5.FACTORID = '" + temfactorid + "' THEN t5.AVG END ) T" + temfactorid.trim() + ",max( CASE WHEN t5.FACTORID = '" + temfactorid + "' THEN t5.ZSAVG END ) TZS" + temfactorid.trim() + ",max( CASE WHEN t5.FACTORID = '" + temfactorid + "' THEN t5.COU END ) TCOU" + temfactorid.trim()+ ",";
                    }
                }
                block.setBlockMeta(metadata); 
                factorSql += "t5.DATATIME from (";
                factorSql += getDataSqlByType(temMN, datatype, startdate, enddate);
                factorSql += ") t5 group by t5.DATATIME order by t5.DATATIME desc ";

                Map sqlmap = new HashMap();
                sqlmap.put("sqlMap", factorSql);
                List list = this.dao.query("DUHA01.query", sqlmap, 0, -999999);
                block.set("count",list.size());
                List<Map> listdata = new ArrayList<>();
                SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if (list != null && list.size() > 0)  {
                	for (int i = 0; i < list.size(); i++) {									
                		Map mapdata = (HashMap)list.get(i);
                		Map map = new HashMap();
                		String datatime=StrUtil.trimToString(mapdata.get("DATATIME"));
                		map.put("datatime",datatime);
                		if(StrUtil.listIsNotNullOrEmpty(timePeriodList)){
                		    for (int k=0;k<timePeriodList.size();k++){
                		        Map timemap=timePeriodList.get(k);
                		        if((sdf.parse(String.valueOf(timemap.get("begintime"))).getTime()<=sdf.parse(datatime).getTime()&&
                                        sdf.parse(String.valueOf(timemap.get("endtime"))).getTime()>=sdf.parse(datatime).getTime())
                                        ||(StringUtils.isNotBlank(String.valueOf(mapdata.get("T01FLAG")))&&"F".equals(mapdata.get("T01FLAG")))
                                        ||(StringUtils.isNotBlank(String.valueOf(mapdata.get("T02FLAG")))&&"F".equals(mapdata.get("T02FLAG")))
                                        ||(StringUtils.isNotBlank(String.valueOf(mapdata.get("T03FLAG")))&&"F".equals(mapdata.get("T03FLAG")))
                                ){
                                    map.put("flag","DY");//停运期间
                                    break;
                                }else{
                                    map.put("flag","NODY");//非停运期间
                                }
                            }
                        }else{
                            map.put("flag","NODY");//非停运期间
                        }
                		for (int j = 0; j < factorsBlock.getRowCount(); j++) {
                            String temfactorid = StrUtil.trimToString(factorsBlock.getRow(j).get("factorid"));
                            map.put("ol" + temfactorid, StrUtil.trimToString(mapdata.get("T" + temfactorid.toUpperCase())));
                            //累计风量
                            if("B02".equals(temfactorid)&&!"2011".equals(datatype)) {
                            	map.put("ol" + temfactorid+"cou", StrUtil.trimToString(mapdata.get("TCOU" + temfactorid.toUpperCase())));
                            }
                            String temusezs = StrUtil.trimToString(factorsBlock.getRow(j).get("usezs"));
                            //if("1".equals(temusezs)) {
                    		if("01".equals(temfactorid)||"02".equals(temfactorid)||"03".equals(temfactorid)) {
                    			map.put("olzs" + temfactorid, StrUtil.trimToString(mapdata.get("TZS" + temfactorid.toUpperCase())));

                    		}
                    		//是否超低排
                    		if("true".equals(iscdp)){
                                if(!StringUtils.isBlank((String)factorsBlock.getRow(j).get("nkhighlimit")) && !StringUtils.isBlank((String)factorsBlock.getRow(j).get("nklowlimit"))) {
                                    map.put("ol" + temfactorid + "limit", StrUtil.trimToString(factorsBlock.getRow(j).get("nklowlimit"))+"-"+StrUtil.trimToString(factorsBlock.getRow(j).get("nklowlimit")));
                                }else {
                                    map.put("ol" + temfactorid + "limit", StrUtil.trimToString(factorsBlock.getRow(j).get("nkhighlimit")));
                                }
                            }else{
                                if(!StringUtils.isBlank((String)factorsBlock.getRow(j).get("highlimit")) && !StringUtils.isBlank((String)factorsBlock.getRow(j).get("lowlimit"))) {
                                    map.put("ol" + temfactorid + "limit", StrUtil.trimToString(factorsBlock.getRow(j).get("lowlimit"))+"-"+StrUtil.trimToString(factorsBlock.getRow(j).get("highlimit")));
                                }else {
                                    map.put("ol" + temfactorid + "limit", StrUtil.trimToString(factorsBlock.getRow(j).get("highlimit")));
                                }
                            }

                            //状态:颗粒物、SO2、NOX、氧含量、烟气流速
                            if("01".equals(temfactorid)||"02".equals(temfactorid)||"03".equals(temfactorid)
                                    ||"S01".equals(temfactorid)||"S02".equals(temfactorid)) {
                                map.put("T" + temfactorid+"FLAG", StrUtil.trimToString(mapdata.get("T" + temfactorid+"FLAG")));
                            }

                        }
                		listdata.add(map);
                	}
                }else {
            		Map map = new HashMap();
            		map.put("datatime", "");
                    map.put("flag","NODY");//非停运期间
            		for (int j = 0; j < factorsBlock.getRowCount(); j++){
                        String temfactorid = StrUtil.trimToString(factorsBlock.getRow(j).get("factorid"));
                        map.put("ol" + temfactorid, "");
                        
                        map.put("ol" + temfactorid + "limit", "");
                        if("1".equals(StrUtil.trimToString(factorsBlock.getRow(j).get("usezs")))) {
                        	map.put("olzs" + temfactorid, "");
                		}
                    }
            		listdata.add(map);
                }
                block.setRows(listdata);
            }
            outInfo.setBlock(block);
        }catch (Exception e){
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
                    if (startcalendar.get(Calendar.MONTH) == endcalendar.get(Calendar.MONTH)){
                        sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.RTD,t1.ZSRTD,t1.flag from " + DbSchema + ".DATATABLE_RTD_" + tableFormat.format(startcalendar.getTime()) + " t1 where t1.MN = '" + mn + "' and t1.DATATIME >= '" + startdatetime + "' and t1.DATATIME <= '" + enddatetime + "'";
                    }else {
                        sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.RTD,t1.ZSRTD,t1.flag from " + DbSchema + ".DATATABLE_RTD_" + tableFormat.format(startcalendar.getTime()) + " t1 where t1.MN = '" + mn + "' and t1.DATATIME >= '" + startdatetime + "' and t1.DATATIME <= '" + enddatetime + "'";
                        sql += " union select t2.MN,t2.FACTORID,t2.DATATIME,t2.RTD,t2.ZSRTD,t2.flag from " + DbSchema + ".DATATABLE_RTD_" + tableFormat.format(endcalendar.getTime()) + " t2 where t2.MN = '" + mn + "' and t2.DATATIME >= '" + startdatetime + "' and t2.DATATIME <= '" + enddatetime + "'";
                    }
                    break;
                case "2051":
                	if (startcalendar.get(Calendar.MONTH) == endcalendar.get(Calendar.MONTH))  {
                		sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.AVG,t1.ZSAVG,t1.COU,t1.ZSCOU,t1.flag from " + DbSchema + ".DATATABLE_MIN_" + tableFormat.format(startcalendar.getTime()) + " t1 where t1.MN = '" + mn + "' and t1.DATATIME >= '" + startdatetime + "' and t1.DATATIME <= '" + enddatetime + "'";
                    } else {
                    	sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.AVG,t1.ZSAVG,t1.COU,t1.ZSCOU,t1.flag from " + DbSchema + ".DATATABLE_MIN_" + tableFormat.format(startcalendar.getTime()) + " t1 where t1.MN = '" + mn + "' and t1.DATATIME >= '" + startdatetime + "' and t1.DATATIME <= '" + enddatetime + "'";
                        sql += " union select t2.MN,t2.FACTORID,t2.DATATIME,t2.AVG,t2.ZSAVG,t2.COU,t2.ZSCOU ,t2.flag from " + DbSchema + ".DATATABLE_MIN_" + tableFormat.format(endcalendar.getTime()) + " t2 where t2.MN = '" + mn + "' and t2.DATATIME >= '" + startdatetime + "' and t2.DATATIME <= '" + enddatetime + "'";
                    }
                    break;
                case "2061":
                    if (startcalendar.get(Calendar.YEAR) == endcalendar.get(Calendar.YEAR)){
                        sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.AVG,t1.ZSAVG,t1.COU,t1.ZSCOU,t1.flag from " + DbSchema + ".DATATABLE_HOUR_" + startcalendar.get(Calendar.YEAR) + " t1 where t1.MN = '" + mn + "' and t1.DATATIME >= '" + startdatetime + "' and t1.DATATIME <= '" + enddatetime + "'";
                    }else{
                        sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.AVG,t1.ZSAVG,t1.COU,t1.ZSCOU,t1.flag from " + DbSchema + ".DATATABLE_HOUR_" + startcalendar.get(Calendar.YEAR) + " t1 where t1.MN = '" + mn + "' and t1.DATATIME >= '" + startdatetime + "' and t1.DATATIME <= '" + enddatetime + "'";
                        sql += " union select t2.MN,t2.FACTORID,t2.DATATIME,t2.AVG,t2.ZSAVG,t2.COU,t2.ZSCOU,t2.flag from " + DbSchema + ".DATATABLE_HOUR_" + endcalendar.get(Calendar.YEAR) + " t2 where t2.MN = '" + mn + "' and t2.DATATIME >= '" + startdatetime + "' and t2.DATATIME <= '" + enddatetime + "'";
                    }
                    break;
                case "2031":
                    if (startcalendar.get(Calendar.YEAR) == endcalendar.get(Calendar.YEAR)){
                        sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.AVG,t1.ZSAVG,t1.COU,t1.ZSCOU,t1.flag from " + DbSchema + ".DATATABLE_DAY_" + startcalendar.get(Calendar.YEAR) + " t1 where t1.MN = '" + mn + "' and t1.DATATIME >= '" + startdatetime + "' and t1.DATATIME <= '" + enddatetime + "'";
                    } else {
                        sql = "select t1.MN,t1.FACTORID,t1.DATATIME,t1.AVG,t1.ZSAVG,t1.COU,t1.ZSCOU,t1.flag  from " + DbSchema + ".DATATABLE_DAY_" + startcalendar.get(Calendar.YEAR) + " t1 where t1.MN = '" + mn + "' and t1.DATATIME >= '" + startdatetime + "' and t1.DATATIME <= '" + enddatetime + "'";
                        sql += " union select t2.MN,t2.FACTORID,t2.DATATIME,t2.AVG,t2.ZSAVG,t2.COU,t2.ZSCOU,t2.flag from " + DbSchema + ".DATATABLE_DAY_" + endcalendar.get(Calendar.YEAR) + " t2 where t2.MN = '" + mn + "' and t2.DATATIME >= '" + startdatetime + "' and t2.DATATIME <= '" + enddatetime + "'";
                    }
                    break;
            }
    	}catch (Exception e){
            throw e;
        }
        return sql;
    }
    
    private Tduhb07 getSiteById(String siteid) {
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
   		}catch( Exception ex ) {
   	        throw ex;
   	     }
   	}
}
