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
import com.baosight.sggk.util.StrUtil;

public class ServiceDUHB25 extends ServiceEPBase {
	private static final Logger logger = Logger.getLogger(ServiceDUHB25.class);
	
	public EiInfo initLoad(EiInfo inInfo) {
		
		inInfo = query(inInfo);
		this.initCompanyBlock(inInfo.addBlock("companyBlock"));
		return inInfo;
	}
	
	
	public EiInfo query(EiInfo inInfo) {
		inInfo = super.query(inInfo, "tduhb25.query");
		try {
			for (int i = 0; i < inInfo.getBlock("result").getRowCount(); i++) {
				String status = inInfo.getCellStr("result", i, "status");
				String monitorType =  inInfo.getCellStr("result", i, "monitorType");
				
				if (StrUtil.paramIsNotNullOrEmpty(status)) {
					if ("1".equals(status)) {
						inInfo.setCell("result", i, "status", "正在使用");
					}else {
						inInfo.setCell("result", i, "status", "未使用");
					}
				}
				
				
				if (StrUtil.paramIsNotNullOrEmpty(monitorType)) {
					if ("99".equals(monitorType)) {
						inInfo.setCell("result", i, "monitorType", "全部");
					}else if ("1".equals(monitorType)) {
						inInfo.setCell("result", i, "monitorType", "实时监控");
					}else if ("2".equals(monitorType)) {
						inInfo.setCell("result", i, "monitorType", "小时监控");
					}else if ("3".equals(monitorType)) {
						inInfo.setCell("result", i, "monitorType", "日均监控");
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

}
