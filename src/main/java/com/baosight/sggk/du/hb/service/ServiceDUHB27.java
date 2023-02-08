package com.baosight.sggk.du.hb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.sggk.common.du.domain.Tduhb01;
import com.baosight.sggk.common.du.domain.Tduhb27;
import com.baosight.sggk.util.StrUtil;

public class ServiceDUHB27 extends ServiceEPBase {
	private static final Logger logger = Logger.getLogger(ServiceDUHB27.class);
	
	public EiInfo initLoad(EiInfo inInfo) {
		
		inInfo = query(inInfo);
		this.initCompanyBlock(inInfo.addBlock("companyBlock"));
		return inInfo;
	}
	
	
	public EiInfo query(EiInfo inInfo) {
		inInfo = super.query(inInfo, "tduhb27.query");
		
		try {
			//获取所有的已选择打开短信发送的监测站点,从中间表中获取
			List<Tduhb27> list27 = this.dao.query("tduhb27.queryOpenSend",new HashMap<>());
			//获取返回的所有的监测站点的列表，从View-T-ha-site监测站点视图中获取
			for (int i = 0; i < inInfo.getBlock("result").getRowCount(); i++) {
				String vsiteid = inInfo.getCellStr("result", i, "siteid");
				if (StrUtil.listIsNotNullOrEmpty(list27)) {
					for (int j = 0; j < list27.size(); j++) {
						Tduhb27 duhb27 = list27.get(j);
						String siteid = duhb27.getSiteid();
						String issend = duhb27.getIssend();
						if (StrUtil.paramIsNotNullOrEmpty(issend) && "1".equals(issend) && siteid.equals(vsiteid)) {
							inInfo.setCell("result", i, "ISSEND", "1");
						}
						
					}

				}

			}
			
		} catch (Exception e) {
			inInfo.setStatus(-1);
			inInfo.setMsg("数据加载失败！");
		}
		
		return inInfo;
	}
	
	
	
	/**
	 * 初始化单位信息的Block,加载完成后，可供用户进行下拉选择公司
	 */
	private void initCompanyBlock(EiBlock companyBlcok) {
		EiBlockMeta meta = new EiBlockMeta();
		
		EiColumn eiColumn = new EiColumn("departmentid");
		eiColumn.setDescName("单位编号");
		meta.addMeta(eiColumn);
		eiColumn = new EiColumn("departmentName");
		eiColumn.setDescName("单位名称");
		meta.addMeta(eiColumn);
		companyBlcok.addBlockMeta(meta);
		
		Map map = new HashMap<>();
		map.put("parentid", "root");
		List<Tduhb01> list = this.dao.query("tduhb01.query",map);
		if (StrUtil.listIsNotNullOrEmpty(list)) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> row = new HashMap<>();
				row.put("departmentid", list.get(i).getDepartmentId());
				row.put("departmentName", list.get(i).getDepartmentName());
				companyBlcok.addRow(row);
			}
		}
		
	}
	
	/**
	 * 改变发送短信的状态
	 * @param inInfo
	 * @return
	 */
	public EiInfo changeSendStatus(EiInfo inInfo) {
		String siteid = StrUtil.paramIsNotNullOrEmpty(inInfo.getString("siteid"))?inInfo.getString("siteid") : "";
		String issend = StrUtil.paramIsNotNullOrEmpty(inInfo.getString("issend"))?inInfo.getString("issend") : "";
		if ("".equals(siteid) || "".equals(issend)) {
			inInfo.setStatus(-1);
			inInfo.setMsg("系统参数错误");
			return inInfo;
		}
		try {
			Map<String, String> pMap = new HashMap<>();
			pMap.put("siteid", siteid);
			pMap.put("issend", issend);
			//判断issend的值，"0"关闭状态，删除配置表中信息; "1"打开，新增操作
			if ("0".equals(issend)) {
				this.dao.delete("tduhb27.delete",pMap);
			}else if ("1".equals(issend)) {
				this.dao.insert("tduhb27.insert",pMap);
			}
			
		} catch (Exception e) {
			inInfo.setStatus(-1);
			inInfo.setMsg("系统错误，修改状态失败！");
		}
		
		return inInfo;
	}
	
	/**
	 * 根据查询区域条件查询信息
	 * @param inInfo
	 * @return
	 */
	public EiInfo queryInfoByCondition(EiInfo inInfo) {
		String mnid = StrUtil.paramIsNotNullOrEmpty(inInfo.getString("mnid"))?inInfo.getString("mnid") : "";
		String departmentid = StrUtil.paramIsNotNullOrEmpty(inInfo.getString("departmentid"))?inInfo.getString("departmentid") : "";
		if ("".equals(mnid) || "".equals(departmentid)) {
			inInfo.setStatus(-1);
			inInfo.setMsg("系统参数错误");
			return inInfo;
		}
		try {
			inInfo.set("inqu_status-0-mnid", mnid);
			inInfo.set("inqu_status-0-departmentid", departmentid);
			inInfo = super.query(inInfo,"tduhb27.query");
			
		} catch (Exception e) {
			inInfo.setStatus(-1);
			inInfo.setMsg("系统错误，修改状态失败！");
		}
		
		return inInfo;
	}
	
	
	

}
