package com.baosight.sggk.du.hb.service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.baosight.sggk.common.du.domain.Tduhb01;
import com.baosight.sggk.du.hb.domain.DUHB28;
import com.baosight.sggk.du.hb.domain.DUHB2802;
import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.data.id.UUIDHexIdGenerator;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceDUHB2802 extends ServiceBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHB2802.class);
	
	 public EiInfo initLoad(EiInfo inInfo) {
		 EiInfo outInfo = new EiInfo();
		// 单位
		EiBlock qdepartBlock = outInfo.addBlock("qdepart");
		qdepartBlock.setBlockMeta((new Tduhb01()).eiMetadata);
		Map<String, Object> levelMap = new HashMap<>();
		levelMap.put("level", 1);
		List list1 = this.dao.query("tduhb01.queryByLevel", levelMap);
		Map<String, Object> Map = new HashMap<>();
		Map.put("departmentId", "root");
		Map.put("departmentName", "马钢股份");
		list1.add(0, Map);
		qdepartBlock.addRows(list1);
		//用户
		EiBlock quserBlock = outInfo.addBlock("quser");
		quserBlock.setBlockMeta((new DUHB2802()).eiMetadata);
		List list2 = this.dao.query("DUHB2802.queryAll", new HashMap());
		Map<String, Object> Map1 = new HashMap<>();
		Map1.put("loginName", "%");
		Map1.put("userName", "请选择");
		list2.add(0, Map1);
		quserBlock.addRows(list2);
		String departmentId = (String) inInfo.get("departmentId");
		outInfo.set("inqu_status-0-departmentId", departmentId);
		 return outInfo;
	 }
	 public EiInfo insert(EiInfo inInfo) {
		 	String departmentId = inInfo.getString("inqu_status-0-departmentId");
		 	String userId = inInfo.getString("inqu_status-0-user");
			String currentUser = String.valueOf(UserSession.getLoginName());
			StringBuffer buffer = new StringBuffer();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Map<String, String> map = new HashMap<String, String>();
			Dao dao = this.getDao();
			//设置新增成功的初始值
			int insertSuccessCount=0;
			//设置新增失败的初始值
			int insertFailCount = 0;
			
			//获取生成器单例对象
			UUIDHexIdGenerator idGenerator = UUIDHexIdGenerator.getInstance();
			//生成UUID唯一编码
			String uuid = idGenerator.generate().toString();
			DUHB28 duhb28 = queryByUserId(userId);
			if(duhb28 == null) {
				inInfo.setCell("inqu_status", 0, "rid", departmentId+"-"+userId);
				inInfo.setCell("inqu_status", 0, "departmentId", departmentId);
				inInfo.setCell("inqu_status", 0, "userId", userId);
				try {
					dao.insert("DUHB28.insert",inInfo.getBlock("inqu_status").getRow(0));
					//dao.insert("DUHB2801.insert",map);
					insertSuccessCount++;
					inInfo.set("status", 1);
				} catch (Exception e) {
					buffer.append(e.getCause().toString());
					inInfo.set("status", -1);
					insertFailCount++;
				}
				if (insertSuccessCount > 0)
					buffer.insert(0, "保存成功，保存成功" + insertSuccessCount + "条记录！\n");
				if (insertFailCount > 0)
					buffer.insert(0, "保存失败，保存失败" + insertFailCount + "条记录！\n");
				inInfo.set("message", buffer.toString());
			}else {
				inInfo.set("status", 2);
				inInfo.set("message", "该用户已与厂部关联！");
			}
//			map.put("userId", uuid);
//			map.put("departmentId", departmentId);
//			map.put("loginName", duhb2802.getLoginName());
//			map.put("userName", duhb2802.getUserName());
//			map.put("mobile", duhb2802.getMobile());
//			map.put("email", duhb2802.getEmail());
			
			return inInfo;
	 }
	 
	//根据用户id查询用户相关信息
	public DUHB28 queryByUserId(String userId) {
		DUHB28 duhb28 = null;
		Map<String, String> map = new HashMap<String, String>();
		if(!StringUtils.isBlank(userId)) {
			map.put("userId", userId);
			List<DUHB28> list = this.dao.query("DUHB28.queryByUserId",map);
			if(list.size()>0 && list!=null) {
				duhb28 = list.get(0);
			}
		}
		return duhb28;
	}
}
