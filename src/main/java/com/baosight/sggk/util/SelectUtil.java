package com.baosight.sggk.util;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * @program: 下拉框工具类
 * @description:
 * @author: Huang-Song
 * @create: 2021/11/23 21:49
 */
public class SelectUtil {

	public static String DbSchema = ResourceBundle.getBundle("application").getString("hbSchema");
	
	/**
	 * 获取监测类型信息
	 * @param dao
	 * @param inInfo
	 * @return
	 */
    public static EiBlock getMonitor(Dao dao, EiInfo inInfo){
        EiBlock monitorblock=new EiBlock("monitor");
        
        Map<String, Object> map=new HashMap<>();
		try {
			String monitorid=inInfo.getString("monitorid");
			String isonline=inInfo.getString("mon_isonline");
			String istotal=inInfo.getString("istotal");
			String 	sql="select monitorid,monitorname from "+DbSchema+".T_HA_MONITOR where status='1'";
			if (StringUtils.isNotBlank(monitorid) && !"%".equals(monitorid)) {
				sql+=" and monitorid in ("+monitorid+")";
			}else {
				//sql+=" and monitorid='1'";
			}
			if (StringUtils.isNotBlank(isonline)) {
				sql+=" and isonline in ("+isonline+")";
			}else {
				//sql+=" and isonline='1'";
			}
			if (StringUtils.isNotBlank(istotal)) {
				sql+=" and istotal in ("+istotal+")";
			}else {
				//sql+=" and istotal='1'";
			}
			sql+=" order by CAST(SORT as INT)  asc";
			map.put("sqlMap", sql);
			List list=dao.query("DUHC20.query",map);
			monitorblock.addRows(list);
		} catch (Exception e) {
			throw e;
		}
        return monitorblock;
    }
    
	/**
	 * 获取厂部信息
	 * @param dao
	 * @param inInfo
	 * @return
	 */
    public static EiBlock getDepartment(Dao dao, EiInfo inInfo){
        EiBlock departblock=new EiBlock("depart");
        
        Map<String, Object> map=new HashMap<>();
		try {
			String level=inInfo.getString("level");
			String type=inInfo.getString("type");
			String totalplan=inInfo.getString("totalplan");
			String departmentid=inInfo.getString("departmentid_dep");
			String 	sql="select department_id,department_name from "+DbSchema+".T_HA_DEPARTMENT where status='1'";
			if (StringUtils.isNotBlank(level)) {
				sql+=" and level='"+level+"'";
			}else {
				sql+=" and level='1'";
			}
			if (StringUtils.isNotBlank(type)) {
				sql+=" and type='"+type+"'";
			}else {
				sql+=" and type='D1'";
			}
			if (StringUtils.isNotBlank(totalplan)) {
				sql+=" and totalplan in ("+totalplan+")";
			}else {
				sql+=" and totalplan='1'";
			}
			if (StringUtils.isNotBlank(departmentid) && !"%".equals(departmentid) && !"''".equals(departmentid)
					&& !"'%'".equals(departmentid)) {
				sql+=" and DEPARTMENT_ID in ("+departmentid+")";
			}else {
				//sql+=" and departmentid='1'";
			}
			sql+=" order by CAST(SORT as INT)  asc";
			map.put("sqlMap", sql);
			List list=dao.query("DUHC20.query",map);
			departblock.addRows(list);
		} catch (Exception e) {
			throw e;
		}
        return departblock;
    }
    
    /**
	 * 获取站点信息
	 * @param dao
	 * @param inInfo
	 * @return
	 */
    public static EiBlock getSite(Dao dao, EiInfo inInfo){
        EiBlock siteblock=new EiBlock("site");
        
        Map<String, Object> map=new HashMap<>();
		try {
			String siteid=inInfo.getString("siteid");
			String isartificial=inInfo.getString("isartificial");//是否人工
			String isonline=inInfo.getString("site_isonline");//是否在线
			String classifyid=inInfo.getString("classifyid");//人工监测类型
			String monitorid=inInfo.getString("monitorid");//在线监测类型
			String departmentid=inInfo.getString("departmentid_site");//厂部
			String procid=inInfo.getString("procid");//工序
			String countrypoint=inInfo.getString("countrypoint");//国控点
			String companypoint=inInfo.getString("companypoint");//市控点
			String 	sql="select siteid,sitename from "+DbSchema+".VIEW_T_HA_SITE where status='1'";
			if (StringUtils.isNotBlank(siteid) && !"%".equals(siteid)) {
				sql+=" and siteid ='"+siteid+"'";
			}
			if (StringUtils.isNotBlank(isartificial)) {
				sql+=" and isartificial='"+isartificial+"'";
			}
			if (StringUtils.isNotBlank(isonline)) {
				sql+=" and isonline='"+isonline+"'";
			}
			if (StringUtils.isNotBlank(classifyid)) {
				sql+=" and classifyid in ("+classifyid+")";
			}
			if (StringUtils.isNotBlank(monitorid) && !"%".equals(monitorid)) {
				sql+=" and monitorid in ("+monitorid+")";
			}
			if (StringUtils.isNotBlank(departmentid) && !"%".equals(departmentid)) {
				sql+=" and departmentid='"+departmentid+"'";
			}
			if (StringUtils.isNotBlank(procid)) {
				sql+=" and procid='"+procid+"'";
			}
			if (StringUtils.isNotBlank(countrypoint) && !"%".equals(countrypoint)) {
				sql+=" and countrypoint='"+countrypoint+"'";
			}
			if (StringUtils.isNotBlank(companypoint) && !"%".equals(companypoint)) {
				sql+=" and companypoint='"+companypoint+"'";
			}
			sql+=" order by siteid  asc";//CAST(SORT as INT)
			map.put("sqlMap", sql);
			List list=dao.query("DUHC20.query",map);
			siteblock.addRows(list);
		} catch (Exception e) {
			throw e;
		}
        return siteblock;
    }
    
    /**   
	* 项目名称：project_name   
	* 类名称：type_name   
	* 类描述：   flag为true，添加全部
	* param 分两种all查国控市控内控，online查国控内控
	* 创建人：gaoming   
	* 创建时间：2021年6月29日 上午10:53:14   
	* @version        
	*/
	public static EiBlock GetSiteTypes (EiBlock block, Boolean flag, String param) {
		if(block == null) {
			block = new EiBlock("porttype");
			EiBlockMeta metadata = new EiBlockMeta();
			EiColumn eiColumn = new EiColumn("typeid");
			metadata.addMeta(eiColumn);
			eiColumn = new EiColumn("typename");
			metadata.addMeta(eiColumn);
			block.setBlockMeta(metadata);
		}
		if(flag) {
			List<Map> list = new ArrayList<Map>();
			Map<String, String> map = new HashMap<String, String>();
			map.put("typeid", "%");
			map.put("typename", "全部");
			list.add(map);
			block.addRows(list);
		}
		switch (param) {
 		case "all":
 			List<Map> list0 = new ArrayList<Map>();
 			Map<String, String> map0 = new HashMap<String, String>();
 			map0.put("typeid", "country");
 			map0.put("typename", "国控点");
 			list0.add(map0);
 			map0 = new HashMap<String, String>();
 			map0.put("typeid", "city");
 			map0.put("typename", "市控点");
 			list0.add(map0);
			map0 = new HashMap<String, String>();
			map0.put("typeid", "company");
			map0.put("typename", "内控点");
			list0.add(map0);
			block.addRows(list0);
 			break;
 		case "online":
 			List<Map> list1 = new ArrayList<Map>();
 			Map<String, String> map1 = new HashMap<String, String>();
 			map1.put("typeid", "country");
 			map1.put("typename", "国控点");
 			list1.add(map1);
			map1 = new HashMap<String, String>();
			map1.put("typeid", "company");
			map1.put("typename", "内控点");
			list1.add(map1);
			block.addRows(list1);
 			break;
 		default:
 			break;
 		}
		return block;
	}
}
