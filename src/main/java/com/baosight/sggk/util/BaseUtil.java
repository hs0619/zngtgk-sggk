package com.baosight.sggk.util;

import com.baosight.sggk.common.du.domain.Tduhb08;
import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseUtil {

	/**   
	* 项目名称：project_name   
	* 类名称：type_name   
	* 类描述：   flag为true，添加全部
	* param 分三种all查全部，allonline查废气废水空气质量，online查废气废水
	* 创建人：gaoming   
	* 创建时间：2021年6月29日 上午10:53:14   
	* @version        
	*/
	public static EiBlock GetMonitorTypes (EiBlock block, Dao dao, Boolean flag, String param) {
		if(block == null) {
			block = new EiBlock("monitor");
			block.setBlockMeta((new Tduhb08()).eiMetadata);
		}
		if(flag) {
			List<Map> list = new ArrayList<Map>();
			Map<String, String> map = new HashMap<String, String>();
			map.put("monitorname", "全部");
			map.put("monitorid", "%");
			list.add(map);
			block.addRows(list);
		}
		switch (param) {
 		case "all":
 			List list = dao.query("tduhb08.query", new HashMap());
 			block.addRows(list);
 			break;
 		case "allonline":
 			List<Map> list0 = new ArrayList<Map>();
 		    Map<String, String> map0 = new HashMap<String, String>();
 			map0.put("monitorid", "01");
 			map0.put("monitorname", "废气");
 			list0.add(map0);
 			map0 = new HashMap<String, String>();
 			map0.put("monitorid", "02");
 			map0.put("monitorname", "废水");
 			list0.add(map0);
 			map0 = new HashMap<String, String>();
 			map0.put("monitorid", "04");
 			map0.put("monitorname", "空气质量");
 			list0.add(map0);
 			block.addRows(list0);
 			break;
 		case "online":
 			List<Map> list1 = new ArrayList<Map>();
 		    Map<String, String> map1 = new HashMap<String, String>();
 			map1.put("monitorid", "01");
 			map1.put("monitorname", "废气");
 			list1.add(map1);
 			map1 = new HashMap<String, String>();
 			map1.put("monitorid", "02");
 			map1.put("monitorname", "废水");
 			list1.add(map1);
 			block.addRows(list1);
 			break;
 		default:
 			break;
 		}
		return block;
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
	
	/**   
	* 项目名称：project_name   
	* 类名称：type_name   
	* 类描述：   flag为true，添加全部
	* param 分四种rtd查实时分钟小时日，min查分钟小时日，hour查小时日，day查日
	* 创建人：gaoming   
	* 创建时间：2021年6月29日 上午11:33:46   
	* @version        
	*/
	public static EiBlock GetDataTypes (EiBlock block, Boolean flag, String param) {
		if(block == null) {
			block = new EiBlock("type");
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
 		case "rtd":
 			List<Map> list0 = new ArrayList<Map>();
 			Map<String, String> map0 = new HashMap<String, String>();
 			map0.put("typeid", "2011");
 			map0.put("typename", "实时值");
 			list0.add(map0);
 			map0 = new HashMap<String, String>();
 			map0.put("typeid", "2051");
 			map0.put("typename", "分钟值");
 			list0.add(map0);
 			map0 = new HashMap<String, String>();
 			map0.put("typeid", "2061");
 			map0.put("typename", "小时值");
 			list0.add(map0);
 			map0 = new HashMap<String, String>();
 			map0.put("typeid", "2031");
 			map0.put("typename", "日均值");
 			list0.add(map0);
			block.addRows(list0);
 			break;
 		case "min":
 			List<Map> list1 = new ArrayList<Map>();
 			Map<String, String> map1 = new HashMap<String, String>();
 			map1.put("typeid", "2051");
 			map1.put("typename", "分钟值");
 			list1.add(map1);
 			map1 = new HashMap<String, String>();
 			map1.put("typeid", "2061");
 			map1.put("typename", "小时均值");
 			list1.add(map1);
 			map1 = new HashMap<String, String>();
 			map1.put("typeid", "2031");
 			map1.put("typename", "日均值");
 			list1.add(map1);
			block.addRows(list1);
 			break;
 		case "hour":
 			List<Map> list2 = new ArrayList<Map>();
 			Map<String, String> map2 = new HashMap<String, String>();
 			map2.put("typeid", "2061");
 			map2.put("typename", "小时均值");
 			list2.add(map2);
 			map2 = new HashMap<String, String>();
 			map2.put("typeid", "2031");
 			map2.put("typename", "日均值");
 			list2.add(map2);
			block.addRows(list2);
 			break;
 		case "day":
 			List<Map> list3 = new ArrayList<Map>();
 			Map<String, String> map3 = new HashMap<String, String>();
 			map3.put("typeid", "2031");
 			map3.put("typename", "日均值");
 			list3.add(map3);
			block.addRows(list3);
 			break;
 		default:
 			break;
 		}
		return block;
	}
}
