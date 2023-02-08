package com.baosight.sggk.du.hb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.sggk.common.du.domain.Tduhb25;
import com.baosight.sggk.common.du.domain.Tduhb26;
import com.baosight.sggk.util.StrUtil;

public class ServiceDUHB26 extends ServiceEPBase {
	private static final Logger logger = Logger.getLogger(ServiceDUHB26.class);
	
	public EiInfo initLoad(EiInfo inInfo) {
		Map map = new HashMap<>();
		List<Tduhb25> list25 = this.dao.query("tduhb25.query",map);
		if (StrUtil.listIsNotNullOrEmpty(list25)) {
			inInfo.set("phoneList", list25);
		}
		return inInfo;
	}
	
	/**
	 * 点击左侧联系人，加载右侧菜单树
	 * @param inInfo
	 * @return
	 */
	public EiInfo changeMenuById(EiInfo inInfo) {
		//查询出树形菜单中需要设置默认选中的选项
    	String personId = inInfo.getString("personId");
    	Map<String, String> personMap = new HashMap<>();
    	personMap.put("personid", personId);
    	List<Tduhb26> list26 = this.dao.query("tduhb26.query",personMap);
		
		//1.记载出整个树形结构，首先加载一级数目录
		List<Map> topList = this.dao.query("tduhb2601.loadTopNodes",new HashMap<>());
		if (StrUtil.listIsNotNullOrEmpty(topList)) {
			//创建一个对象数组用于存放目录
			List<Map> menuList = new ArrayList<>();
			for (int i = 0; i < topList.size(); i++) {
				Map<String, Object> menuMap = new HashMap<>();
				String id = (String) topList.get(i).get("id");
				String text = (String) topList.get(i).get("text");
				Integer leaf = (Integer) topList.get(i).get("leaf");
				menuMap.put("id", id);
				menuMap.put("text", text);		
				
				if (leaf == 0) {//0表示有二级菜单，则加载二级目录
					Map<String, Object> childMap = new HashMap<>();
					childMap.put("pid", id);
					List<Map> childList = this.dao.query("tduhb2601.loadChildNodes",childMap);
					if (StrUtil.listIsNotNullOrEmpty(childList)) {
						for (int j = 0; j < childList.size(); j++) {
							String childId = (String) childList.get(j).get("id");
							if (StrUtil.listIsNotNullOrEmpty(list26)) {
								for (int k = 0; k < list26.size(); k++) {
									String siteid = list26.get(k).getSiteid();
									if (childId.equals(siteid)) {
										childList.get(j).put("checked", "true");
									}
								}
							}
	
						}

					}
					
					
					menuMap.put("items", childList);
					
				}
				menuList.add(menuMap);
			}
			inInfo.set("menuData", menuList);
			
		}		
		return inInfo;
	}
	
	/**
	 * 点击保存选中的树形菜单的信息
	 * @param inInfo
	 * @return
	 */
	public EiInfo saveCheckMenu(EiInfo inInfo) {
		String dcpIds = inInfo.getString("dcpIds");  
		String personId = StrUtil.paramIsNotNullOrEmpty(inInfo.getString("personId"))?inInfo.getString("personId") : "";
		if ("".equals(personId)) {
			inInfo.setStatus(-1);
			inInfo.setMsg("系统参数错误！");
			return inInfo;
		}
		
		try {
			//首先根据personid查询出当前点击的联系人所选中的所有的菜单树，
			//然后删除所有以前选中的菜单列表，将新选中的菜单列表新增到数据库
			Map pMap = new HashMap<>();
			pMap.put("personid", personId);
			this.dao.delete("tduhb26.deleteByPersonId",pMap);
			
			//将新选择的树形菜单保存到数据库
			if (!"".equals(dcpIds)) {
				String[] decIdArr = dcpIds.split(";");
				for (int i = 0; i < decIdArr.length; i++) {
					String siteid = decIdArr[i];
					//根据siteid查询对应的portid
					Map portParam = new HashMap<>();
					portParam.put("siteid", siteid);
					List<Map> portidMap = this.dao.query("tduhb26.queryPortIdBySiteId",portParam);
					String portid = "";
					if (StrUtil.listIsNotNullOrEmpty(portidMap)) {
						portid = (String) portidMap.get(0).get("PORTID");
					}
					
					Map params = new HashMap<>();
					params.put("avdid", getUUID());
					params.put("personid", personId);
					params.put("siteid", siteid);
					params.put("dischargeportid", portid);
					this.dao.insert("tduhb26.insert" , params);	

				}	

			}
			inInfo.setStatus(1);	
		} catch (Exception e) {
			inInfo.setStatus(-1);
		}
		
		return inInfo;
	}
	
	
	
	//获取一个uuid
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		//去掉"-"符号
		return uuid.replaceAll("-", "");
	}
	
}
