/**
 *
 */
package com.baosight.sggk.du.hc.service;

import com.baosight.sggk.common.du.domain.Tduhb01;
import com.baosight.sggk.du.hc.domain.DUHC23;
import com.baosight.sggk.du.hc.domain.DUHC230202;
import com.baosight.sggk.util.CheckoutUtil;
import com.baosight.sggk.util.PermissionUtil;
import com.baosight.sggk.util.StrUtil;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.StringUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

//
//

public class ServiceDUHC23 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHC23.class);

	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		// 日期
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cale = null;
		cale = Calendar.getInstance();
		cale.add(Calendar.MONTH, -1);
		// cale.set(Calendar.DAY_OF_MONTH, 1);
		String firstday = sdf1.format(cale.getTime());
		outInfo.set("inqu_status-0-startdate", firstday);
		outInfo.set("inqu_status-0-enddate", sdf1.format(new Date()));

		// 登录人
		String loginName = String.valueOf(UserSession.getLoginName());
		outInfo.set("inqu_status-0-departmentid", PermissionUtil.getUserDepart(dao, loginName));
		outInfo.setMsg("页面加载完成");
		return outInfo;
	}

	public EiInfo query(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		String siteid = inInfo.getString("inqu_status-0-siteid");
		if ("%".equals(siteid)) {
			inInfo.set("inqu_status-0-siteid", null);
		}

		String startdate = StringUtils.isBlank(inInfo.getString("inqu_status-0-startdate")) ? ""
				: inInfo.getString("inqu_status-0-startdate").trim();
		if (!StringUtils.isBlank(startdate)) {
			inInfo.set("inqu_status-0-startdate", startdate + " 00:00");
		}
		String enddate = StringUtils.isBlank(inInfo.getString("inqu_status-0-enddate")) ? ""
				: inInfo.getString("inqu_status-0-enddate").trim();
		if (!StringUtils.isBlank(enddate)) {
			inInfo.set("inqu_status-0-enddate", enddate + " 23:59");
		}
		// 业务分类
		String monitorid = inInfo.getString("inqu_status-0-monitorid");
		if ("%".equals(monitorid)) {
			inInfo.set("inqu_status-0-monitorid", null);
		}
		// 厂部单位
		String departmentid = inInfo.getString("inqu_status-0-departmentid");
		if ("%".equals(departmentid)) {
			inInfo.set("inqu_status-0-departmentid", null);
		}
		// 计划类型
		String plantype = inInfo.getString("inqu_status-0-plantype");
		if ("%".equals(plantype)) {
			inInfo.set("inqu_status-0-plantype", null);
		}
		//
		// 计划类型
		String valuetype = inInfo.getString("inqu_status-0-valuetype");
		try {
			if ("max-sc".equals(valuetype)) {
				List<DUHC23> zong = maxMin(inInfo, "max-sc", "da");
				inInfo.getBlock("result").setRows(zong);
				outInfo = super.query(inInfo, "DUHC23.queryDetailmaxsc", new DUHC23());
			} else if ("max-zs".equals(valuetype)) {
				List<DUHC23> zong = maxMin(inInfo, "max-zs", "da");
				inInfo.getBlock("result").setRows(zong);
				// inInfo= super.query(inInfo, "DUHC23.queryDetailmaxzs", new DUHC23());
			} else if ("min-sc".equals(valuetype)) {
				List<DUHC23> zong = maxMin(inInfo, "min-sc", "xiao");
				inInfo.getBlock("result").setRows(zong);
				// inInfo= super.query(inInfo, "DUHC23.queryDetailminsc", new DUHC23());
			} else if ("min-zs".equals(valuetype)) {
				List<DUHC23> zong = maxMin(inInfo, "min-zs", "xiao");
				inInfo.getBlock("result").setRows(zong);
				// inInfo= super.query(inInfo, "DUHC23.queryDetailminzs", new DUHC23());
			} else if ("avg-sc".equals(valuetype)) {
				List<Map<String, String>> avgSc = avgUtil(inInfo, "avg-sc");
				inInfo.getBlock("result").setRows(avgSc);
				// inInfo= super.query(inInfo, "DUHC23.queryDetailavgsc", new DUHC23());
			} else if ("avg-zs".equals(valuetype)) {
				List<Map<String, String>> avgSc = avgUtil(inInfo, "avg-zs");
				inInfo.getBlock("result").setRows(avgSc);
				// inInfo= super.query(inInfo, "DUHC23.queryDetailavgzs", new DUHC23());
			} else {
				inInfo = super.query(inInfo, "DUHC23.queryDetail", new DUHC23());
			}
		} catch (Exception e) {
			e.printStackTrace();
			inInfo.addBlock("result");
			inInfo.setMsg("查询失败，请确保查询数据中值不为空！");
			inInfo.setStatus(-1);
		}

		return inInfo;
	}

	public List<DUHC23> maxMin(EiInfo inInfo, String type, String size) {
		List<DUHC23> list = new ArrayList<>();
		Double numbre = new Double(0.0);
		String start = (String) inInfo.get("inqu_status-0-startdate");
		String end = (String) inInfo.get("inqu_status-0-enddate");
		Map<String, String> conditionMap = new HashMap<>();
		conditionMap.put("startdate", start);
		conditionMap.put("enddate", end);
		Map<String, Double> maxscMap = new HashMap<>();
		Double maxsc = new Double(0);
		// 查询所有的t_hc_manualvalue数据
		if ("max-sc".equals(type) || "min-sc".equals(type)) {// 最大/小折算值
			list = this.dao.query("DUHC23.queryItemvalue", conditionMap, 0, -999999);
		}
		if ("max-zs".equals(type) || "min-zs".equals(type)) {// 最大/小折算值
			list = this.dao.query("DUHC23.queryItemvaluezs", conditionMap, 0, -999999);
		}
		List<DUHC23> li = limit(inInfo, list);
		// 通过FACTORID分组算
		Map<String, List<DUHC23>> listDuhc23 = li.stream().collect(Collectors.groupingBy(DUHC23::getFactorid));
		// 计算最大值
		for (Map.Entry<String, List<DUHC23>> entry : listDuhc23.entrySet()) {
			maxscMap.put(entry.getKey(), maxsc);
			List<DUHC23> allList = entry.getValue();
			for (int i = 0; i < allList.size(); i++) {
				// 去除一个字符串里面的数字和小数点以外的字符；
				if ("max-sc".equals(type) || "min-sc".equals(type)) {// 最大实测值
					String value = new String();
					if ("S08".equals(entry.getKey())) {
						System.out.println(entry.getValue());
					}
					Boolean is = CheckoutUtil.isMinus(allList.get(i).getItemvalue());
					if (is) {
						value = allList.get(i).getItemvalue();
					} else {
						// 会删除负号
						value = CheckoutUtil.getReplace(allList.get(i).getItemvalue());
					}
					if (StringUtils.isNotBlank(value)) {
						numbre = Double.valueOf(value);
						// 设置最大值
						if ("da".equals(size)) {
							if (numbre > maxsc) {
								maxsc = numbre;
								// 设置最大值
								maxscMap.put(entry.getKey(), maxsc);
							}
						}
						if ("xiao".equals(size)) {
							if (entry.getKey().equals("S08")) {
								System.out.println(numbre);
							}
							if (numbre < 0) {
								System.out.println("324");
							}

							if (i == 0) {
								maxsc = numbre;
							}
							if (numbre < maxsc || maxsc == numbre) {
								maxsc = numbre;
								// 设置最小值
								maxscMap.put(entry.getKey(), maxsc);
							}
						}
					}

				}
				if ("max-zs".equals(type) || "min-zs".equals(type)) {// 最小折算值
					String value = new String();
					Boolean is = CheckoutUtil.isMinus(allList.get(i).getItemvaluezs());
					if (is) {
						value = allList.get(i).getItemvaluezs();
					} else {
						// 会删除负号
						value = CheckoutUtil.getReplace(allList.get(i).getItemvaluezs());
					}
					if (StringUtils.isNotBlank(value)) {
						numbre = Double.valueOf(value);
						// 设置最大值
						if ("da".equals(size)) {
							if (numbre > maxsc) {
								maxsc = numbre;
								// 设置最大值
								maxscMap.put(entry.getKey(), maxsc);
							}
						}
						if ("xiao".equals(size)) {
							if (i == 0) {
								maxsc = numbre;
							}
							if (numbre < maxsc || maxsc == numbre) {
								maxsc = numbre;
								// 设置最小值
								maxscMap.put(entry.getKey(), maxsc);
							}
						}
					}

				}
			}
			maxsc = 0.0;
		}
		List<DUHC23> zong = new ArrayList<>();
		// 查询t_hc_manualvalue表
		List<DUHC23> allDatelist = this.dao.query("DUHC23.queryAllDate", conditionMap, 0, -999999);
		// 匹配 t_hc_manualvalue表的最大值
		String itemvalue = new String();
		for (int i = 0; i < allDatelist.size(); i++) {
			for (Map.Entry<String, Double> entry : maxscMap.entrySet()) {
				// 去除一个字符串里面的数字和小数点以外的字符；
				if ("max-sc".equals(type) || "min-sc".equals(type)) {
					String value = new String();
					Boolean is = CheckoutUtil.isMinus(allDatelist.get(i).getItemvalue());
					if (is) {
						value = allDatelist.get(i).getItemvalue();
					} else {
						// 会删除负号
						value = CheckoutUtil.getReplace(allDatelist.get(i).getItemvalue());
					}
					itemvalue = StringUtils.isNotBlank(value) ? value : "0";
				}
				if ("max-zs".equals(type) || "min-zs".equals(type)) {
					String value = new String();
					Boolean is = CheckoutUtil.isMobile(allDatelist.get(i).getItemvaluezs());
					if (is) {
						value = allDatelist.get(i).getItemvalue();
					} else {
						// 会删除负号
						value = CheckoutUtil.getReplace(allDatelist.get(i).getItemvaluezs());
					}
					itemvalue = StringUtils.isNotBlank(value) ? value : "0";

				}
				String factorid = allDatelist.get(i).getFactorid();
				if (String.valueOf(entry.getKey()).equals(factorid) && Double
						.doubleToLongBits(entry.getValue()) == Double.doubleToLongBits(Double.valueOf(itemvalue))) {
					if ("max-sc".equals(type) || "min-sc".equals(type)) {
						allDatelist.get(i).setItemvalue(itemvalue);
					}
					if ("max-zs".equals(type) || "min-zs".equals(type)) {
						allDatelist.get(i).setItemvaluezs(itemvalue);
					}
					zong.add(allDatelist.get(i));
				}
			}
		}
		return zong;
	}

	/**
	 * 分组为了在前台显示
	 * 
	 * @param info
	 * @param listDUHC23
	 * @return
	 */
	public List<DUHC23> limit(EiInfo info, List<DUHC23> listDUHC23) {
		List<DUHC23> li = new ArrayList<>();
		info = super.query(info, "DUHC23.queryGroup", new DUHC23());
		List<Map<String, String>> lsit = info.getBlock("result").getRows();
		for (int i = 0; i < lsit.size(); i++) {
			for (int j = 0; j < listDUHC23.size(); j++) {
				if (lsit.get(i).get("factorid").equals(listDUHC23.get(j).getFactorid())) {
					li.add(listDUHC23.get(j));
				}
			}
		}
		return li;
	}

	/**
	 * 计算平均值
	 * 
	 * @param inInfo
	 * @param type
	 */
	public List<Map<String, String>> avgUtil(EiInfo inInfo, String type) {
		List<DUHC23> allList = new ArrayList<>();
		Double avg = new Double(0.0);
		Integer quantity = new Integer(0);
		String start = (String) inInfo.get("inqu_status-0-startdate");
		String end = (String) inInfo.get("inqu_status-0-enddate");
		Map<String, String> conditionMap = new HashMap<>();
		conditionMap.put("startdate", start);
		conditionMap.put("enddate", end);
		// 分组
//		List<DUHC23> avgList  =   this.dao.query("DUHC23.queryAvg",conditionMap,0,-999999);
		inInfo = super.query(inInfo, "DUHC23.queryAvg", new DUHC23());
		List<Map<String, String>> avgList = inInfo.getBlock("result").getRows();
		// 查询所有值
		if ("avg-sc".equals(type)) {
			allList = this.dao.query("DUHC23.queryAvgsc", conditionMap, 0, -999999);
		}
		if ("avg-zs".equals(type)) {
			allList = this.dao.query("DUHC23.queryAvgzs", conditionMap, 0, -999999);
		}
		String value = new String();
		for (int i = 0; i < avgList.size(); i++) {
			for (int j = 0; j < allList.size(); j++) {
				// 判断Factorid是否一样
				if (StringUtils.isNotBlank(avgList.get(i).get("siteid"))
						&& StringUtils.isNotBlank(avgList.get(i).get("factorid"))
						&& StringUtils.isNotBlank(avgList.get(i).get("itemunit"))
						&& StringUtils.isNotBlank(avgList.get(i).get("sitename"))
						&& StringUtils.isNotBlank(avgList.get(i).get("factorname"))
						&& StringUtils.isNotBlank(avgList.get(i).get("planname"))) {
					// 判断这些字段是否相同
					if (avgList.get(i).get("siteid").equals(allList.get(j).getSiteid())
							&& avgList.get(i).get("factorid").equals(allList.get(j).getFactorid())
							&& avgList.get(i).get("itemunit").equals(allList.get(j).getItemunit())
							&& avgList.get(i).get("sitename").equals(allList.get(j).getSitename())
							&& avgList.get(i).get("factorname").equals(allList.get(j).getFactorname())
							&& avgList.get(i).get("planname").equals(allList.get(j).getPlanname())) {
						if (StringUtils.isNotBlank(allList.get(j).getItemvalue())
								|| StringUtils.isNotBlank(allList.get(j).getItemvaluezs())) {

							if ("avg-sc".equals(type)) {// 实测平均值
								Boolean is = CheckoutUtil.isMinus(allList.get(j).getItemvalue());
								if (is) {
									value = allList.get(j).getItemvalue();
								} else {
									// 会删除负号
									value = CheckoutUtil.getReplace(allList.get(j).getItemvalue());
								}
								Double itemvalue = Double.valueOf(value);
								avg += itemvalue;
								quantity++;
							}
							if ("avg-zs".equals(type)) {// 折算平均值
								Boolean is = CheckoutUtil.isMinus(allList.get(j).getItemvaluezs());// 判断是否是自然数包括0
								if (is) {
									value = allList.get(j).getItemvaluezs();
								} else {
									// 会删除负号
									value = CheckoutUtil.getReplace(allList.get(j).getItemvaluezs());// 去除符号
								}
								Double itemvalue = Double.valueOf(value);
								avg += itemvalue;
								quantity++;
							}
						}
					}
				}
			}
			// 计算平均值
			String avgDvi = div(String.valueOf(avg), String.valueOf(quantity), 2);
			if ("avg-sc".equals(type)) {// 实测平均值
				avgList.get(i).put("itemvalue", avgDvi);
			}
			if ("avg-zs".equals(type)) {// 实测平均值
				avgList.get(i).put("itemvaluezs", avgDvi);
			}
			avg = 0.0;
			quantity = 0;
		}
		return avgList;
	}

	public static String div(String v1, String v2, int scale) {
		BigDecimal a = new BigDecimal(StringUtils.isBlank(v1) ? "0" : v1);
		BigDecimal b = new BigDecimal(StringUtils.isBlank(v2) ? "0" : v2);
		if (Double.valueOf(b.toString()) == 0) {
			return "0";
		}
		return a.divide(b, scale, BigDecimal.ROUND_HALF_UP).toString();
	}

	public EiInfo delete(EiInfo inInfo) {
		EiInfo outInfo = super.delete(inInfo, "DUHC23.delete");
		return outInfo;
	}

	/**
	 * 创建Excel表格，生产报表文件
	 * 
	 * @param inInfo
	 * @return
	 * @throws IOException
	 */
	public EiInfo exportExcel(EiInfo inInfo) throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String startdate = inInfo.getString("inqu_status-0-startdate");
		String year = startdate.substring(0, 4);
		String departmentid = inInfo.getString("inqu_status-0-departmentid");
		String monitorid = inInfo.getString("inqu_status-0-monitorid");
		String monitoridname = "";
		if ("01".equals(monitorid)) {
			monitoridname = "废气";
		} else if ("02".equals(monitorid)) {
			monitoridname = "废水";
		} else if ("03".equals(monitorid)) {
			monitoridname = "噪声";
		} else if ("04".equals(monitorid)) {
			monitoridname = "空气质量";
		} else if ("05".equals(monitorid)) {
			monitoridname = "土壤监测";
		} else if ("06".equals(monitorid)) {
			monitoridname = "无组织";
		}
		String plantype = inInfo.getString("inqu_status-0-plantype");
		String plantypename = "";
		if ("1".equals(plantype)) {
			plantypename = "计划内";
		} else if ("0".equals(plantype)) {
			plantypename = "计划外";
		}
		String departmentname = getDepartmentname(departmentid);
		try {
			// 导出路径
			String path = this.getPath();

			String reportName = departmentname + "-" + monitoridname + "人工监测数据" + plantypename + ".xls";
			File buildFile = new File(path);
			if (!buildFile.exists() && !buildFile.isDirectory()) {
				buildFile.mkdir();
			}
			String reportPath = path + "/" + reportName;

			// 获取模板文件
			ResourceBundle dbPro = ResourceBundle.getBundle("application");
			String excelTemplatePath = dbPro.getString("manualFilePath");
			excelTemplatePath += "hjjc/rgjc/excelTemplate/";
			String fileName = "人工监测数据.xls";
			File tempFile = new File(excelTemplatePath);
			if (!tempFile.exists() && !tempFile.isDirectory()) {
				tempFile.mkdir();
			}
			POIFSFileSystem fSystem = new POIFSFileSystem(new FileInputStream(excelTemplatePath + fileName));
			HSSFWorkbook wb = new HSSFWorkbook(fSystem);
			// 数据集合
			Map<String, Object> map = new HashMap<>();
			map.put("departmentid", "%".equals(departmentid) ? "" : departmentid);
			map.put("monitorid", monitorid);
			map.put("plantype", plantype);
			map.put("year", year);
			List<DUHC230202> list = dao.query("DUHC230202.export", map);
			// 创建HSSFWorkbook对象(excel的文档对象)
			pwsbSheet(wb, list);
			FileOutputStream out = new FileOutputStream(reportPath);
			wb.write(out);
			out.close();
			inInfo.setStatus(1);
			inInfo.set("reportPath", reportPath);
			inInfo.set("excelName", reportName);
			inInfo.set("message", "报表导出成功！");
		} catch (Exception e) {
			e.printStackTrace();
			inInfo.set("status", -1);
			inInfo.set("message", "报表生成失败:" + e.toString());
			logger.error("报表生成失败:", e.getCause());
		}

		return inInfo;
	}

	private String getDepartmentname(String departmentid) {
		Map<String, Object> map = new HashMap<>();
		map.put("departmentId", departmentid);
		List<Tduhb01> departlist = this.dao.query("tduhb01.query", map);
		if (departlist != null && departlist.size() > 0) {
			return departlist.get(0).getDepartmentName();
		}
		return "各分厂";
	}

	private HSSFCellStyle getStyle(HSSFWorkbook wb, boolean islocked) {
		HSSFCellStyle style1 = wb.createCellStyle();
		HSSFDataFormat format = wb.createDataFormat();
		style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
		// 设置锁定
		style1.setLocked(islocked ? true : false);
		// 文本格式
		style1.setDataFormat(format.getFormat("@"));
		// 居中
		style1.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style1.setAlignment(CellStyle.ALIGN_CENTER);
		return style1;
	}

	/**
	 * 找到需要插入的行的下标，并返回改行的row的实例
	 * 
	 * @param sheet
	 * @param rowIndex
	 * @return
	 */
	private static HSSFRow createRow(HSSFSheet sheet, Integer rowIndex) {
		HSSFRow row = null;
		if (sheet.getRow(rowIndex) != null) {
			int lastRowNum = sheet.getLastRowNum();
			sheet.shiftRows(rowIndex, lastRowNum, 1);
		}
		row = sheet.createRow(rowIndex);
		return row;
	}

	/**
	 * @param wb
	 * @param
	 * @param
	 * @return
	 */
	private void pwsbSheet(HSSFWorkbook wb, List<DUHC230202> list) {
		HSSFSheet sheet = wb.getSheet("人工监测数据");
		HSSFCellStyle style1 = getStyle(wb, true);// 锁定
		HSSFCellStyle style2 = getStyle(wb, false);// 不锁定
		if (list != null && list.size() > 0) {
			String tempPlanid = list.get(0).getPlanid();
			List<DUHC230202> resList = new ArrayList<>();
			HSSFRow row = null;
			for (int i = 0; i < list.size(); i++) {
				String planid = list.get(i).getPlanid();
				if (tempPlanid.equals(planid)) {
					resList.add(list.get(i));
				} else {
					tempPlanid = list.get(i).getPlanid();// 将缓存中的planid替换
					if (StrUtil.listIsNotNullOrEmpty(resList)) {
						for (int j = 0; j < resList.size(); j++) {
							row = createRow(sheet, 2);
							row.createCell(0).setCellValue(resList.get(j).getSitename());
							row.getCell(0).setCellStyle(style1);
							row.createCell(1).setCellValue(resList.get(j).getPlanid());
							row.getCell(1).setCellStyle(style1);
							row.createCell(2).setCellValue(resList.get(j).getSiteid());
							row.getCell(2).setCellStyle(style1);
							row.createCell(3).setCellValue(resList.get(j).getMonitorid());
							row.getCell(3).setCellStyle(style1);
							row.createCell(4).setCellValue(resList.get(j).getFactorid());
							row.getCell(4).setCellStyle(style1);
							row.createCell(5).setCellValue(resList.get(j).getPlanname());
							row.getCell(5).setCellStyle(style1);
							row.createCell(6).setCellValue(resList.get(j).getMonitorname());
							row.getCell(6).setCellStyle(style1);
							row.createCell(7).setCellValue(resList.get(j).getFactorname());
							row.getCell(7).setCellStyle(style1);
							row.createCell(8).setCellValue(resList.get(j).getUnit());
							row.getCell(8).setCellStyle(style1);
							// 采样时间
							row.createCell(9).setCellValue("");
							row.getCell(9).setCellStyle(style2);
							String highlimit = resList.get(j).getHighlimit();
							String lowlimit = resList.get(j).getLowlimit();
							if (StringUtils.isNotBlank(lowlimit) && StringUtils.isNotBlank(highlimit)) {
								row.createCell(10).setCellValue(lowlimit + "~" + highlimit);
							} else {
								row.createCell(10).setCellValue(highlimit);
							}
							row.getCell(10).setCellStyle(style2);
							row.createCell(11).setCellValue("");
							row.getCell(11).setCellStyle(style2);
							row.createCell(12).setCellValue("");
							row.getCell(12).setCellStyle(style2);
							row.createCell(13).setCellValue("");
							row.getCell(13).setCellStyle(style2);
							row.createCell(14).setCellValue("");
							row.getCell(14).setCellStyle(style2);
						}
					}
					// 合并单元格
					sheet.addMergedRegion(new CellRangeAddress(2, resList.size() + 1, 5, 5));
					sheet.addMergedRegion(new CellRangeAddress(2, resList.size() + 1, 6, 6));

					resList.clear();
					resList.add(list.get(i));
				}
			}

			if (StrUtil.listIsNotNullOrEmpty(resList)) {
				for (int j = 0; j < resList.size(); j++) {
					row = createRow(sheet, 2);
					row.createCell(0).setCellValue(resList.get(j).getSitename());
					row.getCell(0).setCellStyle(style1);
					row.createCell(1).setCellValue(resList.get(j).getPlanid());
					row.getCell(1).setCellStyle(style1);
					row.createCell(2).setCellValue(resList.get(j).getSiteid());
					row.getCell(2).setCellStyle(style1);
					row.createCell(3).setCellValue(resList.get(j).getMonitorid());
					row.getCell(3).setCellStyle(style1);
					row.createCell(4).setCellValue(resList.get(j).getFactorid());
					row.getCell(4).setCellStyle(style1);
					row.createCell(5).setCellValue(resList.get(j).getPlanname());
					row.getCell(5).setCellStyle(style1);
					row.createCell(6).setCellValue(resList.get(j).getMonitorname());
					row.getCell(6).setCellStyle(style1);
					row.createCell(7).setCellValue(resList.get(j).getFactorname());
					row.getCell(7).setCellStyle(style1);
					row.createCell(8).setCellValue(resList.get(j).getUnit());
					row.getCell(8).setCellStyle(style1);
					// 采样时间
					row.createCell(9).setCellValue("");
					row.getCell(9).setCellStyle(style2);
					String highlimit = resList.get(j).getHighlimit();
					String lowlimit = resList.get(j).getLowlimit();
					if (StringUtils.isNotBlank(lowlimit) && StringUtils.isNotBlank(highlimit)) {
						row.createCell(10).setCellValue(lowlimit + "~" + highlimit);
					} else {
						row.createCell(10).setCellValue(highlimit);
					}
					row.getCell(10).setCellStyle(style2);
					row.createCell(11).setCellValue("");
					row.getCell(11).setCellStyle(style2);
					row.createCell(12).setCellValue("");
					row.getCell(12).setCellStyle(style2);
					row.createCell(13).setCellValue("");
					row.getCell(13).setCellStyle(style2);
					row.createCell(14).setCellValue("");
					row.getCell(14).setCellStyle(style2);
				}
			}
			// 合并单元格
			sheet.addMergedRegion(new CellRangeAddress(2, resList.size() + 1, 5, 5));
			sheet.addMergedRegion(new CellRangeAddress(2, resList.size() + 1, 6, 6));

			resList.clear();
		}
		// 设置工作表保护密码
		sheet.protectSheet("123");
	}

	// 下载路径
	public String getPath() {
		String OS = System.getProperty("os.name");
		String path = "";
		// 判断是mac 还是 window
		if (OS.contains("Mac OS X")) {
			path = "/sggk/";
		} else {
			ResourceBundle dbPro = ResourceBundle.getBundle("application");
			String filePath = dbPro.getString("manualFilePath");
			path = filePath + "hjjc//rgjc//downloadExcel";
			File buildFile = new File(path);
			if (!buildFile.exists() && !buildFile.isDirectory()) {
				buildFile.mkdirs();
			}
		}
		return path;
	}

	/**
	 * @param wb
	 * @param
	 * @param b
	 * @return
	 */
//	private void pwsbSheet(HSSFWorkbook wb, List<DUHC230202> list) {
//		HSSFSheet sheet = wb.getSheet("人工监测数据");
//		HSSFCellStyle style1 = getStyle(wb);
//		if (list != null && list.size() > 0) {
//			HSSFRow row = null;
//			for (int i = 0; i < list.size(); i++) {
//				row = sheet.createRow(i + 2);
//				row.createCell(0).setCellValue(list.get(i).getSitename());
//				row.getCell(0).setCellStyle(style1);
//				row.createCell(1).setCellValue(list.get(i).getPlanid());
//				row.getCell(1).setCellStyle(style1);
//				row.createCell(2).setCellValue(list.get(i).getSiteid());
//				row.getCell(2).setCellStyle(style1);
//				row.createCell(3).setCellValue(list.get(i).getMonitorid());
//				row.getCell(3).setCellStyle(style1);
//				row.createCell(4).setCellValue(list.get(i).getFactorid());
//				row.getCell(4).setCellStyle(style1);
//				row.createCell(5).setCellValue(list.get(i).getPlanname());
//				row.getCell(5).setCellStyle(style1);
//				row.createCell(6).setCellValue(list.get(i).getMonitorname());
//				row.getCell(6).setCellStyle(style1);
//				row.createCell(7).setCellValue(list.get(i).getFactorname());
//				row.getCell(7).setCellStyle(style1);
//				row.createCell(8).setCellValue(list.get(i).getUnit());
//				row.getCell(8).setCellStyle(style1);
//				// 采样时间
//				row.createCell(9).setCellValue("");
//				row.getCell(9).setCellStyle(style1);
//				String highlimit = list.get(i).getHighlimit();
//				String lowlimit = list.get(i).getLowlimit();
//				if (StringUtils.isNotBlank(lowlimit) && StringUtils.isNotBlank(highlimit)) {
//					row.createCell(10).setCellValue( lowlimit+ "~" +highlimit );
//				} else {
//					row.createCell(10).setCellValue(highlimit);
//				}
//				row.getCell(10).setCellStyle(style1);
//				row.createCell(11).setCellValue("");
//				row.getCell(11).setCellStyle(style1);
//				row.createCell(12).setCellValue("");
//				row.getCell(12).setCellStyle(style1);
//				row.createCell(13).setCellValue("");
//				row.getCell(13).setCellStyle(style1);
//				row.createCell(14).setCellValue("");
//				row.getCell(14).setCellStyle(style1);
//			}
//		}
//	}

}
