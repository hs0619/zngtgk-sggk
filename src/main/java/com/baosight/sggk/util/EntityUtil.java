package com.baosight.sggk.util;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.sggk.common.du.domain.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityUtil {

	/**
	 * 根据监测类型id，获取类型实体类
	 * @param dao
	 * @param monitorid
	 * @return
	 */
	public static Tduhb08 getMonitorEntityById(Dao dao, String monitorid) {
		Tduhb08 tduhb08 = new Tduhb08();
		if (monitorid != null && monitorid != "") {
			Map<String, String> pMap = new HashMap<>();
			pMap.put("monitorid", monitorid);
			List<Tduhb08> list = dao.query("tduhb08.query",pMap);
			if (StrUtil.listIsNotNullOrEmpty(list)) {
				tduhb08 = list.get(0);
			}
		}
		
		return tduhb08;
	}
	
	
	/**
	 * 根据监测因子id，获取监测因子实体类
	 * @param dao
	 * @param
	 * @return
	 */
	public static Tduhb10 getFactorEntityByid(Dao dao,String factorid) {
		Tduhb10 tduhb10 = new Tduhb10();
		if (!"".equals(factorid)) {
			Map<String, String> pMap = new HashMap<>();
			pMap.put("factorid", factorid);
			List<Tduhb10> list = dao.query("tduhb10.query",pMap);
			if (StrUtil.listIsNotNullOrEmpty(list)) {
				tduhb10 = list.get(0);
			}
		}
		return tduhb10;
		
	}
	
	/**
	 * 根据厂部/工序id，获取厂部/工序实体类
	 * @param dao
	 * @param
	 * @return
	 */
	public static Tduhb01 getDepOrProceEntityByid(Dao dao,String departmentId) {
		Tduhb01 tduhb01 = new Tduhb01();
		if (!"".equals(departmentId)) {
			Map<String, String> pMap = new HashMap<>();
			pMap.put("departmentId", departmentId);
			List<Tduhb01> list = dao.query("tduhb01.query",pMap);
			if (StrUtil.listIsNotNullOrEmpty(list)) {
				tduhb01 = list.get(0);
			}
		}
		return tduhb01;
		
	}
	
	/**
	 * 根据监测站点获取监测点名称
	 * @param siteid
	 * @param dao
	 * @return
	 */
	public static String getSiteNameById(String siteid,Dao dao) {
		String sitename = "";
		if (StrUtil.paramIsNotNullOrEmpty(siteid)) {
			Map<String, String> siteMap = new HashMap<>();
			siteMap.put("siteid", siteid);
			List<Tduhb07> siteList  = dao.query("tduhb07.query",siteMap);
			if (StrUtil.listIsNotNullOrEmpty(siteList)) {
				sitename = siteList.get(0).getSitename();
			}
		}
		return sitename;
	}
	
	
	/**
	 * 根据监测站点id，获取对应的厂部名称
	 * @param siteid
	 * @param dao
	 * @return
	 */
	public static String getDepartmentNameBySiteid(String siteid,Dao dao) {
		String departmentName = "";
		if (StrUtil.paramIsNotNullOrEmpty(siteid)) {
			Map<String, String> siteMap = new HashMap<>();
			siteMap.put("siteid", siteid);
			List<Map> siteList  = dao.query("tduhb07.queryDepartmentNameBySiteid",siteMap);
			if (StrUtil.listIsNotNullOrEmpty(siteList)) {
				departmentName = (String) siteList.get(0).get("departmentname");
			}
		}
		return departmentName;
	}
	
	
	/**
	 * 根据厂部id，获取厂部名称
	 * @param dao
	 * @param departmentId
	 * @return
	 */
	public static String getDepartmentNameById(Dao dao,String departmentId) {
		String departmentName = "";
		if (!"".equals(departmentId)) {
			Map<String, String> pMap = new HashMap<>();
			pMap.put("departmentId", departmentId);
			List<Tduhb01> list = dao.query("tduhb01.query",pMap);
			if (StrUtil.listIsNotNullOrEmpty(list)) {
				departmentName = list.get(0).getDepartmentName();
			}
		}
		return departmentName;
		
	}

	/**
	 * 根据监测站点id，污染源因子id查询当前因子的上限限值（此方法用于查询最新的上限限值）
	 * @param siteid
	 * @param factorid
	 * @param dao
	 * @return
	 */
	public static String getHihgLimitBySiteidAndFactorid(String siteid,String factorid,Dao dao){
		Map<String,String> paramsMap  = new HashMap<>();
		paramsMap.put("siteid",siteid);
		paramsMap.put("factorid",factorid);
		paramsMap.put("starttime",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		List<Tduhb0605> list = dao.query("tduhb0605.getLatestHighlimit",paramsMap);
		if (list != null && list.size() > 0){
//			String limit = list.get(0).getLimit();
//			return limit;
		}

		return "";
	}

	/**
	 * 根据监测站点id，污染源因子id查询当前因子的下限限值（此方法用于查询最新的下限限值）
	 * @param siteid
	 * @param factorid
	 * @param dao
	 * @return
	 */
	public static String getLowLimitBySiteidAndFactorid(String siteid,String factorid,Dao dao){
		Map<String,String> paramsMap  = new HashMap<>();
		paramsMap.put("siteid",siteid);
		paramsMap.put("factorid",factorid);
		paramsMap.put("starttime",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		List<Tduhb0605> list = dao.query("tduhb0605.getLatestHighlimit",paramsMap);
		if (list != null && list.size() > 0){
//			String lowLimit = list.get(0).getLowlimit();
//			return lowLimit;
		}

		return "";
	}

	/**
	 * 根据监测站点id，污染源因子id,当前因子所在的时间点，查询当前因子的下限限值
	 * （此方法根据当前因子所携带的时间点去判断所使用限值的时间区间，然后取此区间的下限（最早的）时间点）
	 * @param siteid
	 * @param factorid
	 * @param dao
	 * @return
	 */
	public static String getLowLimitByDatatime(String datatime,String siteid,String factorid,Dao dao){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(datatime);
			Map<String,String> paramsMap  = new HashMap<>();
			paramsMap.put("siteid",siteid);
			paramsMap.put("factorid",factorid);
			paramsMap.put("starttime",sdf.format(date));
			List<Tduhb0605> list = dao.query("tduhb0605.getLimitByDatatime",paramsMap);
			if (list != null && list.size() > 0){
//				String lowLimit = list.get(0).getLowlimit();
//				return lowLimit;
			}
		}catch (Exception e){
			return "";
		}
		return "";
	}


	/**
	 * 根据监测站点id，污染源因子id,当前因子所在的时间点，查询当前因子的下限限值
	 * （此方法根据当前因子所携带的时间点去判断所使用限值的时间区间，然后取此区间的下限（最早的）时间点）
	 * @param siteid
	 * @param factorid
	 * @param dao
	 * @return
	 */
	public static String getHighLimitByDatatime(String datatime,String siteid,String factorid,Dao dao)  {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(datatime);
			Map<String,String> paramsMap  = new HashMap<>();
			paramsMap.put("siteid",siteid);
			paramsMap.put("factorid",factorid);

			List<String> list = dao.query("DUHD22.getLimit", paramsMap);
			if (list!=null && list.size()>0){
				String limit = list.get(0);
				return limit;
			}

//			paramsMap.put("starttime",sdf.format(date));
//			List<Tduhb0605> list = dao.query("tduhb0605.getLimitByDatatime",paramsMap);
//			if (list != null && list.size() > 0){
//				String limit = list.get(0).getLimit();
//				return limit;
//			}

		}catch (Exception e){
			return "";
		}
		return "";
	}

	/**
	 * 根据污染源因子id获取因子单位
	 * @param factorid
	 * @return
	 */
	public static String getUnitByFactorid(String factorid,Dao dao) {
		String factorUnit = "";
		Map<String, String> map = new HashMap<>();
		map.put("factorid", factorid);
		List<Tduhb10> list = dao.query("tduhb10.query",map);
		if (StrUtil.listIsNotNullOrEmpty(list)) {
			factorUnit = list.get(0).getUnit();
		}
		return factorUnit;
	}


	/**
	 * 根据监测点id，因子id，站点类型（1 手工，2 在线），判断当前因子是否使用了折算值
	 * @param siteid
	 * @param factorid
	 * @param dao
	 * @return
	 */
	public static String getFactorIsUsezs(String siteid,String factorid,String siteTyoe,Dao dao){
		String usezs = "0";
		Map<String, String> isUsezsMap = new HashMap<>();
		isUsezsMap.put("siteid", siteid);
		isUsezsMap.put("factorid", factorid);
		isUsezsMap.put("type", siteTyoe);
		isUsezsMap.put("status", "1");
		List<Tduhb0702> listhb0702 = dao.query("tduhb0702.query",isUsezsMap);
		if (StrUtil.listIsNotNullOrEmpty(listhb0702)) {
			usezs = listhb0702.get(0).getUsezs();
		}
		return usezs;
	}

	
}
