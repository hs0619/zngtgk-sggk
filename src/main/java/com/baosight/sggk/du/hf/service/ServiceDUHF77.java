package com.baosight.sggk.du.hf.service;


import com.alibaba.dubbo.common.utils.StringUtils;
import com.baosight.sggk.du.hf.domain.DUHF77;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import org.apache.log4j.Logger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class ServiceDUHF77 extends ServiceEPBase {
	
	private static final Logger logger = Logger.getLogger(ServiceDUHF77.class);

	public EiInfo initLoad(EiInfo inInfo) {
    	EiInfo outInfo = new EiInfo();
    	//上传模板路径
		ResourceBundle dbPro = ResourceBundle.getBundle("application");
		String excelTemplatePath = dbPro.getString("manualFilePath");
		outInfo.set("inqu_status-0-excelTemplatePath", excelTemplatePath);
    	
    	//报告类型
		EiBlock block = outInfo.addBlock("radiationReportType");
		List<Map> List2 = new ArrayList<>();
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("radiationReportId", "1");
		map2.put("radiationReportName", "辐射安全年度评估报告");
		List2.add(map2);
		Map<String, String> map3 = new HashMap<String, String>();
		map3.put("radiationReportId", "2");
		map3.put("radiationReportName", "辐射年度监测报告");
		List2.add(map3);
		block.setRows(List2);
		outInfo.addBlock(block);
    	
		return outInfo;
    }

    /**
    * 查询功能
    *
    * @param inInfo
    * @return
    */
	public EiInfo query(EiInfo inInfo) {
		String radiationReportType = inInfo.getString("inqu_status-0-radiationReportType");
		if("%".equals(radiationReportType) || radiationReportType == null) {
			inInfo.set("inqu_status-0-radiationReportType", null);
		}else {
			inInfo.set("inqu_status-0-radiationReportType", radiationReportType);
		}
		EiInfo outInfo = super.query(inInfo,"DUHF77.query",new DUHF77());
		return outInfo;
	}
	public EiInfo getFile(EiInfo inInfo) {
		String filename = inInfo.getString("filename");
		//filename.substr(filename.lastIndexOf("\") + 1);
		Map<String, Object> map = new HashMap();
		map.put("filename", filename);
		List list = this.dao.query("DUHF77.count", map);
		int i = (int) list.get(0);
		if(i == 0) {
			inInfo.set("status", 1);
			inInfo.setMsg("该文件已存在，请删除原文件或更换文件名后重试!");
		}else {
			inInfo.set("status", -1);
			inInfo.setMsg("该文件已存在，请删除原文件或更换文件名后重试!");
		}
		return inInfo;
	}
	
	//上传
	public EiInfo importExcel(EiInfo inInfo) {
		try {
			// 获取配置文件里的参数
			ResourceBundle dbPro = ResourceBundle.getBundle("application");
			String configpath = dbPro.getString("manualFilePath");

			String filename = StringUtils.isBlank((String) inInfo.get("filename")) ? ""
					: (String) inInfo.get("filename");
			String filepath = StringUtils.isBlank((String) inInfo.get("filepath")) ? ""
					: (String) inInfo.get("filepath");
			if ("".equals(filename) || "".equals(filepath)) {
				inInfo.setStatus(-1);
				inInfo.setMsg("报表参数出错");
				return inInfo;
			}

			File buildFile = new File(filepath);
			if (buildFile.exists() && buildFile.isDirectory()) {
				String reportPath = filepath + "/" + filename;

				File excelFile = new File(reportPath);
				//File targetFile = new File(configpath + "土壤监测/");

//						 File f = new File(this.getClass().getResource("/").getPath());
//						 File f2 = new File(this.getClass().getResource("").getPath());

				try {
					// String msg = saveTempletInfoIntoDB(filename, reportPath);
					saveFile(inInfo, reportPath);
					// 复制文件
					//FileUtil.copy(excelFile, targetFile);
					// inInfo.setMsg(msg);
				} catch (Exception ex) {
					inInfo.setStatus(-1);
					inInfo.setMsg("报表导入出错！" + ex.toString());
				}
			} else {
				inInfo.setStatus(-1);
				inInfo.setMsg("报表参数出错！");
			}
		} catch (Exception ex) {
			inInfo.setStatus(-1);
			inInfo.setMsg("报表导入出错" + ex.toString());
		}
		return inInfo;
	}

	// 保存文件
	public EiInfo saveFile(EiInfo inInfo, String reportPath) {
		String filename = StringUtils.isBlank((String) inInfo.get("filename")) ? ""
				: ((String) inInfo.get("filename")).trim();
		String filepath = StringUtils.isBlank((String) inInfo.get("filepath")) ? ""
				: ((String) inInfo.get("filepath")).trim();
		String radiationReportType = inInfo.getString("result_status-0-radiationReportType");
		if (!StringUtils.isBlank(radiationReportType)) {
			if ("".equals(filename) || "".equals(filepath)) {
				inInfo.setStatus(-1);
				inInfo.setMsg("附件参数出错");
				return inInfo;
			}else {
				String userName = String.valueOf(UserSession.getLoginCName());
				SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Map<String, Object> map = new HashMap();
				map.put("fileid", UUID.randomUUID().toString());
				map.put("radiationReportType", radiationReportType);
				map.put("filepath", reportPath);
				map.put("filename", filename);
				map.put("uploadman", userName);
				map.put("uploadtime", dateTimeFormat.format(new Date()));
				try {
					this.dao.insert("DUHF77.insert", map);
					String fileid = (String) map.get("fileid");
					inInfo.set("fileid", fileid);
					inInfo.setStatus(1);
					inInfo.setMsg("文件上传成功");
				} catch (Exception e) {
					inInfo.setStatus(-1);
					inInfo.setMsg("附件上传出错" + e.getCause().toString());
				}
			
			}
		} else {
			inInfo.setStatus(-1);
			inInfo.setMsg("参数出错!");
		}
		return inInfo;
	}
	
	public EiInfo delete(EiInfo inInfo) {
		String fileid = (String) inInfo.getBlock("result").getCell(0, "fileid");
		for (int i = 0; i < inInfo.getBlock("result").getRowCount(); i++) {
			String filepath = (String) inInfo.getBlock("result").getCell(i, "filepath");
					File file = new File(filepath);
					file.delete();
		}
		inInfo.set("inqu_status-0-fileid", fileid);
		EiInfo outInfo = super.delete(inInfo,"DUHF77.delete");
		
		EiInfo eiInfo = query(inInfo);
		eiInfo.setMsg(outInfo.getMsg());
		eiInfo.setDetailMsg(outInfo.getDetailMsg());
		return eiInfo;
	}
}
