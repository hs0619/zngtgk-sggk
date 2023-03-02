package com.baosight.sggk.du.hf.service;


import com.alibaba.dubbo.common.utils.StringUtils;
import com.baosight.sggk.du.hf.domain.DUHF77;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.sggk.util.StrUtil;
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
		EiBlock block = outInfo.addBlock("filetype");
		List<Map> List2 = new ArrayList<>();
		Map<String, String> map = new HashMap<String, String>();
        map.put("filetypeId", "1");
        map.put("filetypeName", "年度评估报告");
		List2.add(map);
        map = new HashMap<String, String>();
        map.put("filetypeId", "2");
        map.put("filetypeName", "年度辐射监测报告");
		List2.add(map);
        map = new HashMap<String, String>();
        map.put("filetypeId", "3");
        map.put("filetypeName", "年度合规性报告");
        List2.add(map);
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
		String filetype = inInfo.getString("inqu_status-0-filetype");
		if("%".equals(filetype) || filetype == null) {
			inInfo.set("inqu_status-0-filetype", null);
		}else {
			inInfo.set("inqu_status-0-filetype", filetype);
		}
		EiInfo outInfo = super.query(inInfo,"DUHF77.query",new DUHF77());
		return outInfo;
	}

    /**
     * 根据文件名称查找文件
     * @param inInfo
     * @return
     */
	public EiInfo checkFileByName(EiInfo inInfo) {
		String filename = inInfo.getString("filename");
		Map<String, Object> map = new HashMap();
		map.put("filename", filename);
		try{
            List list = this.dao.query("DUHF77.query", map);
            if(StrUtil.listIsNotNullOrEmpty(list)) {
                inInfo.set("status", 2);
                inInfo.setMsg("同名文件已存在，请删除原文件或更换文件名后重试!");
            }else{
                inInfo.set("status",1);
            }
        }catch (Exception ex){
            inInfo.set("status", -1);
            inInfo.setMsg("文件检索失败!");
        }

		return inInfo;
	}
	
	//上传
	public EiInfo importExcel(EiInfo inInfo) {
		try {
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
				try {
					saveFile(inInfo, reportPath);
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
		String filetype = inInfo.getString("result_status-0-filetype");
		if (!StringUtils.isBlank(filetype)) {
			if ("".equals(filename) || "".equals(filepath)) {
				inInfo.setStatus(-1);
				inInfo.setMsg("附件参数出错");
				return inInfo;
			}else {
				String userName = String.valueOf(UserSession.getLoginCName());
				SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Map<String, Object> map = new HashMap();
				map.put("fileid", UUID.randomUUID().toString());
				map.put("filetype", filetype);
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
