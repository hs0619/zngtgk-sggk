package com.baosight.sggk.util;


import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yql
 * @date 2020/5/25
 * @description
 */
public class StrUtil {

	public static String ToArrayStr(String str, String separatorStr) {
		if (str.isEmpty())
			return str;
		if (separatorStr.isEmpty())
			return "'" + str + "'";
		StringBuilder sb = new StringBuilder();
		String[] strs = str.split(separatorStr);
		for (int i = 0; i < strs.length; i++) {
			sb.append("'" + strs[i] + "'" + separatorStr);
		}
		return trimend(sb.toString(), separatorStr);
	}

	/*
	 * 删除开头字符串
	 */
	public static String trimstart(String inStr, String prefix) {
		if (inStr.startsWith(prefix)) {
			return (inStr.substring(prefix.length()));
		}
		return inStr;
	}

	/*
	 * 删除末尾字符串
	 */
	public static String trimend(String inStr, String suffix) {
		if (inStr.endsWith(suffix)) {
			return (inStr.substring(0, inStr.length() - suffix.length()));
		}
		return inStr;
	}

	/*
	 * object转String,null转空字符串
	 */
	public static String trimToString(Object obj) {
		return (obj == null) ? "" : obj.toString().trim();
	}

	/*
	 * object转Double,转换失败默认为0
	 */
	public static Double trimToDouble(Object obj) {
		try {
			return Double.parseDouble(StrUtil.trimToString(obj));
		} catch (Exception ex) {
			return 0d;
		}
	}

	/*
	 * object转Integer,转换失败默认为0
	 */
	public static Integer trimToInteger(Object obj) {
		try {
			return Integer.parseInt(StrUtil.trimToString(obj));
		} catch (Exception ex) {
			return 0;
		}
	}

	/*
	 * object转Float,转换失败默认为0
	 */
	public static Float trimToFloat(Object obj) {
		try {
			return Float.parseFloat(StrUtil.trimToString(obj));
		} catch (Exception ex) {
			return 0f;
		}
	}

	/*
	 * 判断返回的list结果集是否为空
	 */
	public static boolean listISNullOrEmpty(List list) {
		if (list != null && list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 判断返回的list结果集是否为空
	 */
	public static boolean listISNotNullOrEmpty(List list) {
		if (list != null && list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断一个变量是否为空
	 */
	public static boolean paramIsNullOrEmpty(String param) {
		if (param != null && (!"".equals(param)) && param != "undefined") {
			return true;
		} else {
			return false;
		}

	}
	/**
	 * 判断当前系统为windows还是linux，并根据不同的系统获取不同的excel模板路径
	 * @return
	 */
	public static String getExcelTemplatePath() {
		ResourceBundle dbPro = ResourceBundle.getBundle("application");
		String path = "";
		if (System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS") == -1) {
			path = dbPro.getString("linuxExcelTemplatePath");
		} else {
			String filePath = dbPro.getString("reportPath");
			path = filePath + "excelTemplate/";  //  E:/IplatFile/report/excelTemplate/
			File buildFile = new File(path);
			if (!buildFile.exists() && !buildFile.isDirectory()) {
				buildFile.mkdir();
			}
		}
		return path;

	}

	/**
	 * 获取生成的报表文件的路径
	 * @return
	 */
	public static String getExcelReportPath() {
		String reportDirPath = "";
		ResourceBundle dbPro = ResourceBundle.getBundle("application");
		if (System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS") == -1) {
			reportDirPath = dbPro.getString("linuxExcelDownloadPath");
		} else {
			String filePath = dbPro.getString("reportPath");
			reportDirPath = filePath + "reportFile/";   //  E:/IplatFile/report/reportFile/
			File buildFile = new File(reportDirPath);
			if (!buildFile.exists() && !buildFile.isDirectory()) {
				buildFile.mkdir();
			}
		}
		return reportDirPath;
	}

	/**
	 * 判断一个变量是否为空
	 */
	public static boolean paramIsNotNullOrEmpty(String param) {
		if (param != null && (!"".equals(param)) && param != "undefined") {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * double转string保留几位小数
	 * 
	 * @param
	 * @return
	 */
	public static String getDecimals(double param, int num) {
		String paramString = "";
		if (num == 1) {
			paramString = String.format("%.1f", param);
		} else if (num == 2) {
			paramString = String.format("%.2f", param);
		} else if (num == 3) {
			paramString = String.format("%.3f", param);
		} else if (num == 4) {
			paramString = String.format("%.4f", param);
		} else {
			paramString = String.format("%.3f", param);
		}
		return paramString;
	}

	public static boolean listIsNotNullOrEmpty(List list) {
		boolean istrue = false;
		if (list != null && list.size() > 0) {
			istrue = true;
		}
		return istrue;
	}

	/**
	 * 如果参数为null或者undefined,返回字符串为" "
	 * 
	 * @param param
	 * @return
	 */
	public static String isNullToStr(String param) {
		if (param == null || "undefined".equals(param)) {
			param = "";
		}
		return param;

	}

	// 获取一个uuid
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		// 去掉"-"符号
		return uuid.replaceAll("-", "");
	}

	/**
	 * 
	 */
	private static String[] parsePatterns = { "yyyy-MM-dd", "yyyy年MM月dd日", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
			"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyyMMdd" };


	/**
	 * 判断当前系统为windows还是linux，并根据不同的系统获取不同的文件上传路径
	 * 
	 * @return
	 */
	public static String getUploadPath() {
		ResourceBundle dbPro = ResourceBundle.getBundle("application");
		String path = "";
		if (System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS") == -1) {
			path = dbPro.getString("linuxUserFilesPath");
		} else {
			String filePath = dbPro.getString("manualFilePath");
			path = filePath + "/upload/";
			File buildFile = new File(path);
			if (!buildFile.exists() && !buildFile.isDirectory()) {
				buildFile.mkdir();
			}
		}
		return path;

	}

	/**
	 * 判断一个string值是否为数字
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNumber(String s) {// 合法数字返回true
		try {
			// 这个正则表达式能够过滤0.0.0、8-99这种不合法的数字
			// String reg="^[-\\\\+]?([0-9]+\\\\.?)?[0-9]+$";
			s=s.trim();
			String reg = "^(\\-|\\+)?\\d+(\\.\\d+)?$";
			return s.matches(reg);
		} catch (Exception e) {
			return false;
		}
	}

    /**
     * 返回剔除特殊字符后的字符串
     * ( [  { / ^ - $  ¦ } ] ) ? * + .等在正则表达式中有特殊意义的字符需要转义 字符前加\\
     * @param regStr 特殊字符集
     * @param str 需要替换的字符串
     * @return
     */
	public static String isNoSpecialStr(String regStr ,String str){
	    try{
            if (StringUtils.isNotBlank(str)) {
                Pattern p = Pattern.compile(regStr);
                Matcher m = p.matcher(str);
                return m.replaceAll("_");
            }
        }catch (Exception ex){
            return null;
        }
	    return str;
    }
}
