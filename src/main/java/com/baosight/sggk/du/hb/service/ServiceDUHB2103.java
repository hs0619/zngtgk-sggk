package com.baosight.sggk.du.hb.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.sggk.common.du.domain.Tduhb18;
import com.baosight.sggk.common.du.domain.Tduhb20;
import com.baosight.sggk.util.StrUtil;


public class ServiceDUHB2103 extends ServiceBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHB2103.class);

	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		
		String facilityid = StrUtil.paramIsNotNullOrEmpty(inInfo.getString("facilityid")) ?  inInfo.getString("facilityid") : "";
		if ("".equals(facilityid)) {
			outInfo.setStatus(-1);
			return outInfo;
		}
		outInfo.set("inqu_status-0-facilityid", facilityid);
		
		//查询所有的产排污设备信息列表
		Map<String, String> enMap = new HashMap<>();
		enMap.put("devicetype", "02");
		List<Tduhb18> allList = this.dao.query("tduhb18.query",enMap);
		List<Tduhb18> deviceList = new ArrayList<>();
		//根据环保治理设施id，去设施表中查询对应的治理设施id字符串
		Map<String, String> pMap = new HashMap<>();
		pMap.put("facilityid", facilityid);
		List<Tduhb20> listhb20 = this.dao.query("tduhb20.query",pMap);
		if (StrUtil.listIsNotNullOrEmpty(listhb20)) {
			String devicecode = listhb20.get(0).getDevicecode();
			if (StrUtil.paramIsNotNullOrEmpty(devicecode)) {
				String[] envdeviceidArr = devicecode.split(",");
				for (int i = 0; i < envdeviceidArr.length; i++) {
					if (StrUtil.listIsNotNullOrEmpty(allList)) {
						for (int j = 0; j < allList.size(); j++) {
							String allEnvdeviceid = allList.get(j).getEnvdeviceid();
							if (envdeviceidArr[i].equals(allEnvdeviceid)) {
								allList.remove(j);
								j--;
							}
						}
					}
					
				}
			}	
		}
		
		//添加产排污设施的列表信息
		EiBlock envdevicBlock = outInfo.addBlock("envdevic");
		envdevicBlock.addBlockMeta(new Tduhb18().eiMetadata);
		envdevicBlock.addRows(allList);

		return outInfo;
	}
	
	
	public EiInfo query(EiInfo inInfo) {
		String facilityid = StrUtil.paramIsNotNullOrEmpty(inInfo.getString("inqu_status-0-facilityid")) ?  inInfo.getString("inqu_status-0-facilityid") : "";
		if ("".equals(facilityid)) {
			inInfo.setStatus(-1);
			inInfo.setMsg("系统参数错误");
			return inInfo;
		}
		inInfo.set("inqu_status-0-facilityid", facilityid);
		
		String deviceid = inInfo.getString("inqu_status-0-deviceid");
		String devicename = inInfo.getString("inqu_status-0-devicename");
		
		//查询所有的产排污设备信息列表
		Map<String, String> enMap = new HashMap<>();
		enMap.put("devicetype", "02");
		enMap.put("deviceid", deviceid);
		enMap.put("devicename", devicename);
		List<Tduhb18> allList = this.dao.query("tduhb18.query",enMap,0, -999999);
		List<Tduhb18> deviceList = new ArrayList<>();
		//根据环保治理设施id，去设施表中查询对应的治理设施id字符串
		Map<String, String> pMap = new HashMap<>();
		pMap.put("facilityid", facilityid);
		List<Tduhb20> listhb20 = this.dao.query("tduhb20.query",pMap);
		if (StrUtil.listIsNotNullOrEmpty(listhb20)) {
			String devicecode = listhb20.get(0).getDevicecode();
			if (StrUtil.paramIsNotNullOrEmpty(devicecode)) {
				String[] envdeviceidArr = devicecode.split(",");
				for (int i = 0; i < envdeviceidArr.length; i++) {
					if (StrUtil.listIsNotNullOrEmpty(allList)) {
						for (int j = 0;j < allList.size(); j++) {
							String allEnvdeviceid = allList.get(j).getEnvdeviceid();
							if (envdeviceidArr[i].equals(allEnvdeviceid)) {
								allList.remove(j);
							}
						}
					}
					
				}
			}	
		}
		
		//添加产排污设施的列表信息
		EiBlock envdevicBlock = inInfo.addBlock("envdevic");
		envdevicBlock.addBlockMeta(new Tduhb18().eiMetadata);
		envdevicBlock.setRows(allList);
		inInfo.set("envdevic-offset", 0);
		inInfo.set("envdevic-limit", 1000);
		return inInfo;
	}
	
	
	
	
	// 根据装置id查询设施信息
	public Tduhb20 getByFacilitycode(String facilitycode) {
		Tduhb20 tduhb20 = null;
		Map<String, String> map = new HashMap<String, String>();
		if (!StringUtils.isBlank(facilitycode)) {
			map.put("facilitycode", facilitycode);
			List<Tduhb20> list = this.dao.query("tduhb20.query", map);
			if (list.size() > 0 && list != null) {
				tduhb20 = list.get(0);
			}
		}
		return tduhb20;
	}
	
	/**
	 * 保存勾选的产污设备信息
	 * @param inInfo
	 * @return
	 */
	public EiInfo saveEnvdeviceInfo(EiInfo inInfo) {
		String facilityid = StrUtil.paramIsNotNullOrEmpty(inInfo.getString("facilityid")) ?  inInfo.getString("facilityid") : "";
		String envdeviceid = StrUtil.paramIsNotNullOrEmpty(inInfo.getString("envdeviceid")) ?  inInfo.getString("envdeviceid") : "";
		if ("".equals(facilityid)) {
			inInfo.setStatus(-1);
			inInfo.setMsg("系统参数错误");
			return inInfo;
		}
		
		//首先根据设施id，查询对应的治理设施的id，将新增的id添加到id字符串的后面
		Map<String, String> pMap = new HashMap<>();
		pMap.put("facilityid", facilityid);
		List<Tduhb20> listhb20 = this.dao.query("tduhb20.query",pMap);
		if (StrUtil.listIsNotNullOrEmpty(listhb20)) {
			String oldEnvdevic = StrUtil.isNullToStr(listhb20.get(0).getDevicecode());
			if (!"".equals(oldEnvdevic)) {
				envdeviceid = oldEnvdevic + "," + envdeviceid;
			}
		}
		
		pMap.put("devicecode", envdeviceid);
		try {
			this.dao.update("tduhb20.updateEnvdevicInfo",pMap);
			envdeviceidIsExist(envdeviceid , facilityid);
			inInfo.setStatus(1);
		} catch (Exception e) {
			inInfo.setMsg("添加排污设备关联出错");
			inInfo.setStatus(-1);
		}
		
		
		return inInfo;
	}

	/**
	 *  //判断添加的产污设施关联，产污表中关联的治理设施的字符串中，若不存在，则添加
	 * @param envdeviceid
	 * @param facilityid
	 */
	public void envdeviceidIsExist(String envdeviceid,String facilityid) {
		if (StrUtil.paramIsNotNullOrEmpty(envdeviceid)) {
			List<String> envdevicidList = new ArrayList<>(Arrays.asList(envdeviceid.split(",")));
			if (StrUtil.listIsNotNullOrEmpty(envdevicidList)) {
				for (String envdeId : envdevicidList) {
					Map<String, String> pMap = new HashMap<>();
					pMap.put("envdeviceid", envdeId);
					List<Tduhb18> listhb18 = this.dao.query("tduhb18.query",pMap);
					if (StrUtil.listIsNotNullOrEmpty(listhb18)) {
						String processid = listhb18.get(0).getProcessid();
						if (StrUtil.paramIsNotNullOrEmpty(processid) && processid.indexOf(facilityid) == -1) {
							processid += "," + facilityid;
							pMap.put("processid", processid);
							this.dao.update("tduhb18.updateProcessidInfo",pMap);
						}else {//若字段的内容为空，即没有添加过治理设施id，则需将治理设施id，添加进去
							pMap.put("processid", facilityid);
							this.dao.update("tduhb18.updateProcessidInfo",pMap);
						}
					}
				}
			}
		}
	}
	

	
}
