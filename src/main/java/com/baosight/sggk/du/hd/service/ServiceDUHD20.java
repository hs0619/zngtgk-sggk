package com.baosight.sggk.du.hd.service;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.sggk.du.hd.domain.DUHD20;
import com.baosight.sggk.util.StrUtil;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ServiceDUHD20 extends ServiceBase {
    private static final Logger logger = Logger.getLogger(ServiceDUHD20.class);
    // 获取配置文件里的参数
    public ResourceBundle dbPro = ResourceBundle.getBundle("application");
    public String DbSchema = dbPro.getString("hbSchema");


    public EiInfo initLoad(EiInfo inInfo) {
        EiInfo outInfo = new EiInfo();
        outInfo.addBlock(getReportTypeBlock());
        outInfo.set("inqu_status-0-reporttype", "DAY");
        String filepath = StrUtil.getExcelTemplatePath();
        outInfo.set("inqu_status-0-file", filepath);
        return outInfo;
    }

    public EiInfo query(EiInfo inInfo) {
        EiInfo outInfo = super.query(inInfo, "DUHD20.query", new DUHD20());
        return outInfo;
    }

    public EiInfo update(EiInfo inInfo) {
        EiInfo outInfo = super.update(inInfo, "DUHD20.update");
        return outInfo;
    }


    public EiInfo delete(EiInfo inInfo) {
        EiInfo outInfo = super.delete(inInfo, "DUHD20.delete");
        //删除该报表的所有公式
        super.delete(inInfo, "DUHD20.deleteFormula", "result");
        EiBlock result = query(inInfo).getBlock("result");
        outInfo.setBlock(result);
        return outInfo;
    }

    /**
     * 上传附件到服务器后，保存附件信息
     */
    public EiInfo importExcel(EiInfo inInfo) {
        String reportname = StrUtil.paramIsNotNullOrEmpty(inInfo.getString("reportname")) ? inInfo.getString("reportname") : "";
        String reporttype = StrUtil.paramIsNotNullOrEmpty(inInfo.getString("reporttype")) ? inInfo.getString("reporttype") : "";
        String filepath = StrUtil.paramIsNotNullOrEmpty(inInfo.getString("filepath")) ? inInfo.getString("filepath") : "";

        if ("".equals(reportname) || "".equals(reporttype)) {
            inInfo.setStatus(-2);
            return inInfo;
        }
        //根据报表的类型查询对应的类型排序值
        Integer timetype = 0;
        Map<String, String> pMap = new HashMap();
        pMap.put("reporttype", reporttype);
        List<Map> typeList = this.dao.query("DUHD20.queryReportSEQByType", pMap);
        if (typeList != null && typeList.size() > 0) {
            timetype = Integer.parseInt(typeList.get(0).get("seq").toString());
        }
        String loginCName = String.valueOf(UserSession.getLoginCName());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, String> params = new HashMap<>();
        params.put("reportname", reportname);
        params.put("reporttype", reporttype);
        params.put("timetype", String.valueOf(timetype));
        params.put("timeinfo", "");
        params.put("uploadman", loginCName);
        params.put("uploadtime", sdf.format(new Date()));
        boolean flag = this.templateIsExist(reportname);
        try {
            if (flag) {
                inInfo.setStatus(2);
            } else {
                this.dao.insert("DUHD20.insert", params);
                inInfo.setStatus(1);
                saveExcelFormulaIntoDB(reportname, filepath);
            }
        } catch (Exception e) {
            inInfo.setStatus(-1);
        }
        return inInfo;
    }


    /**
     * 保存Excel模板中的公式信息到数据库中
     *
     * @param filename
     * @param reportPath
     * @throws Exception
     */
    private void saveExcelFormulaIntoDB(String filename, String reportPath) throws Exception {
        File excelFile = new File(reportPath + "\\" + filename);
        FileInputStream is = new FileInputStream(excelFile);
        Workbook wb = WorkbookFactory.create(is);
        if (wb != null) {
            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                Sheet sheet = wb.getSheetAt(i);
                if (sheet != null) {
                    int firstRowNum = sheet.getFirstRowNum();//第一行
                    int lastRowNum = sheet.getLastRowNum();//最后一行
                    String cellValue = "";
                    for (int r = firstRowNum; r <= lastRowNum; r++) {
                        Row row = sheet.getRow(r);
                        if (row == null) {
                            continue;
                        }
                        for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                            Cell cell = row.getCell(j);
                            if (cell != null) {
                                cellValue = cell.getStringCellValue().trim();
                                if (cellValue.length() > 0) {
                                    if (cellValue.charAt(0) == '{' && cellValue.charAt(cellValue.length() - 1) == '}') {
                                        cellValue = cellValue.replace("{", "");
                                        cellValue = cellValue.replace("}", "");
                                        SaveFormulaInfo(r + 1, j + 1, cellValue, filename, i + 1);
                                    } else {
                                        continue;
                                    }
                                } else {
                                    continue;
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    /**
     * 保存模公式信息
     *
     * @param iRow
     * @param iCol
     * @param strGetStr
     * @param strTempletName
     * @param intSheetNum
     */
    public void SaveFormulaInfo(int iRow, int iCol, String strGetStr, String strTempletName, int intSheetNum) {
        String sql = "insert into " + DbSchema + ".t_hd_report_template ";
        sql = sql + " ( REPORTNAME, MYROW, MYCOL, EXPVALUE, SHEETNUM ) ";
        sql = sql + " VALUES ( '" + strTempletName + "'," + iRow + "," + iCol;
        sql = sql + ",'" + strGetStr + "'," + intSheetNum + ")";
        Map sqlmap = new HashMap();
        sqlmap.put("sqlMap", sql);
        dao.insert("DUHD21.insert", sqlmap);
    }

    /**
     * 判断文件是否存在
     */
    private boolean templateIsExist(String templateName) {
        boolean flag = false;
        //查出表内所有数据
        List<DUHD20> templateList = this.dao.query("DUHD20.query", new HashMap<>());
        if (templateList != null && templateList.size() > 0) {
            for (int i = 0; i < templateList.size(); i++) {
                DUHD20 duhd20 = templateList.get(i);
                String reportname = duhd20.getReportname();
                if (templateName.equals(reportname)) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    private EiBlock getReportTypeBlock() {
        EiBlock eiBlock = new EiBlock("reportTypeBlock");
        EiColumn eiColumn = null;
        EiBlockMeta eiBlockMeta = new EiBlockMeta();
        eiColumn = new EiColumn("reporttype");
        eiBlockMeta.addMeta(eiColumn);
        eiColumn = new EiColumn("reportname");
        eiBlockMeta.addMeta(eiColumn);
        eiBlock.setBlockMeta(eiBlockMeta);

        List list = this.dao.query("DUHD20.queryReportType", new HashMap<>());
        eiBlock.setRows(list);
        return eiBlock;
    }


    /**
     * 根据类型设定时间
     *
     * @param type
     * @param addcount
     * @return
     */
    public String addDate(String type, int addcount) {
        String clock = "";
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);//设置为当前时间
        switch (type) {
            case "MONTH":
                calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + addcount);
                clock = new SimpleDateFormat("yyyy-MM").format(calendar.getTime());
                break;
            case "DAY":
                calendar.set(Calendar.DATE,calendar.get(Calendar.DATE)+addcount);
                clock = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
                break;
            case "YEAR":
                calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR)+addcount);
                clock = new SimpleDateFormat("yyyy").format(calendar.getTime());
                break;
        }
        return clock;
    }
}
