package com.baosight.sggk.du.hc.service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.baosight.sggk.common.du.domain.Tduhb01;
import com.baosight.sggk.du.hc.domain.DUHC23;
import com.baosight.sggk.du.hc.domain.DUHC2302;
import com.baosight.sggk.du.hc.domain.DUHC230201;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ServiceDUHC230101 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHC230101.class);

	// 获取配置文件里的参数
	ResourceBundle dbPro = ResourceBundle.getBundle("application");
	String DbSchema = dbPro.getString("hbSchema");


	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		String excelTemplatePath = dbPro.getString("manualFilePath");
		outInfo.set("inqu_status-0-excelTemplatePath", excelTemplatePath);
		outInfo.setMsg("页面加载完成");
		return outInfo;
	}

	/**
	 * 初始化下拉框
	 * 
	 * @param inInfo
	 * @return
	 */
	public EiInfo initSelectData(EiInfo inInfo) {
		// 一级单位
		EiBlock qdepartBlock = inInfo.addBlock("qdepart");
		qdepartBlock.setBlockMeta((new Tduhb01()).eiMetadata);
		Map<String, Object> paramMap1 = new HashMap<>();
		paramMap1.put("parentid", "root");
		List departlist = this.dao.query("tduhb01.query", paramMap1);
		qdepartBlock.addRows(departlist);
		inInfo.setBlock(qdepartBlock);
		return inInfo;
	}

	/**
	 * 导入
	 * 
	 * @param inInfo
	 * @return
	 * @throws IOException
	 */
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
					String msg = saveTempletInfoIntoDB(filename, reportPath);
					inInfo.setStatus(1);
					inInfo.setMsg(msg);
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

	// 读取excle的数据
	public String saveTempletInfoIntoDB(String filename, String reportPath) throws Exception {
		StringBuffer buffer = new StringBuffer();
		File excelFile = new File(reportPath);
		FileInputStream is = new FileInputStream(excelFile);
		Workbook wb = WorkbookFactory.create(is);
		try {
			if (wb != null) {
				// 获取第一个sheet
				// Sheet sheet = wb.getSheetAt(0);
				Sheet sheet = wb.getSheet("人工监测数据");
				buffer.append(getheetData(sheet));
			}
		} catch (Exception ex) {
			buffer.insert(0, "导入文件不是所需文件，请重新选择！");
		}

		return buffer.toString();
	}

	//
	private String getheetData(Sheet sheet) {
		String logingName = UserSession.getLoginName();
		String logingCName = UserSession.getLoginCName();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = sdf.format(new Date());
		StringBuffer buffer = new StringBuffer();
		int insertSuccessCount = 0;
		int insertFailCount = 0;
		int updateSuccessCount = 0;
		int totalRows = sheet.getPhysicalNumberOfRows()-2;
		int RowCells = sheet.getRow(1).getPhysicalNumberOfCells();
		String cellData;
		Row row = sheet.getRow(1);
		for (int i = 0; i < totalRows; i++) {
			try {
				row = sheet.getRow(2 + i);
				if (row != null) {
					List<String> params = new ArrayList<>();
					for (int j = 0; j < RowCells; j++) {
						cellData = getCellValue(row.getCell(j));
						params.add(cellData);
					}
					Map<String, String> map = new HashMap<String, String>();
					//计划id，采样时间，实测值不为空
					if(!StringUtils.isBlank(params.get(1))&&!StringUtils.isBlank(params.get(9))&&
							!StringUtils.isBlank(params.get(11))) {
						map.put("planid", params.get(1));
						map.put("siteid", params.get(2));
						map.put("monitorid",params.get(3));
						map.put("factorid", params.get(4));
						map.put("itemunit", params.get(8));
						map.put("itemtime", params.get(9));
						map.put("itemlimit", params.get(10));
						map.put("itemvalue", params.get(11));
						map.put("itemvaluezs", params.get(12));
						map.put("overlimitinfo", params.get(13));
						map.put("itemmark", params.get(14));
						List<DUHC23> list = dao.query("DUHC23.queryImport", map);
						if (list != null && list.size() > 0) {
							map.put("updateid", logingName);
							map.put("updatename", logingCName);
							map.put("updatetime", nowTime);
							try {
								dao.update("DUHC23.update", map);
								updateSuccessCount++;
							} catch (Exception e) {
								updateSuccessCount++;
								continue;
							}
						} else {
							map.put("createid", logingName);
							map.put("createname", logingCName);
							map.put("createtime", nowTime);
							try {
								dao.insert("DUHC23.insert", map);
								insertSuccessCount++;
							} catch (Exception e) {
								insertFailCount++;
								continue;
							}
						}
					}
				}
			} catch (Exception ex) {
				buffer.insert(0, "导入失败！");
				return buffer.toString();
			}
		}
		if (insertSuccessCount > 0)
			buffer.insert(0, "新增成功" + insertSuccessCount + "条人工监测记录！\n");
		if (insertFailCount > 0)
			buffer.insert(0, "新增失败" + insertFailCount + "条人工监测记录！\n");
		if (updateSuccessCount > 0)
			buffer.insert(0, "修改成功" + updateSuccessCount + "条人工监测记录！\n");
		return buffer.toString();
	}

	private List<DUHC230201> getFactorid(Map<String, String> map) {
		List<DUHC230201> list=null;
		try {
			list=dao.query("DUHC230201.getfactorid",map);
		} catch (Exception e) {
			throw e;
		}
		return list;
	}


	private List<DUHC2302> getplanid(Map<String, String> map) {
		List<DUHC2302> list=null;
		try {
			list=dao.query("DUHC2302.getPlanid",map);
		} catch (Exception e) {
			throw e;
		}
		return list;
	}


	public String getCellValue(Cell cell) {
		String value = ""; // 以下是判断数据的类型
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING: // 字符串
			value = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			value = cell.getNumericCellValue() + "";
			if (DateUtil.isCellDateFormatted(cell)) {
				Date date = cell.getDateCellValue();
				if (date != null) {
					value = new SimpleDateFormat("yyyy-MM-dd mm:hh:ss").format(date);
				} else {
					value = "";
				}
			} else {
				DataFormatter dataFormatter = new DataFormatter();
				value = dataFormatter.formatCellValue(cell);
				// value = new DecimalFormat("0").format(cell.getNumericCellValue());
			}
			break;

		case Cell.CELL_TYPE_BOOLEAN: //
			value = cell.getBooleanCellValue() + "";
			break;
		case Cell.CELL_TYPE_FORMULA: // 公式
			value = cell.getCellFormula() + "";
			break;
		case Cell.CELL_TYPE_BLANK: // 空值
			value = "";
			break;
		case Cell.CELL_TYPE_ERROR: // 故障
			value = "非法字符";
			break;
		default:
			value = "未知类型";
			break;
		}
		return value;
	}

}
