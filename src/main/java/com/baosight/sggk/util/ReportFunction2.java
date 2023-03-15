package com.baosight.sggk.util;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiInfo;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.*;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReportFunction2 {
    private static final Logger logger = Logger.getLogger(ReportFunction2.class);

    String DbSchema;
    Dao dao;

    public ReportFunction2(Dao dao, String DbSchema) {
        this.dao = dao;
        this.DbSchema = DbSchema;
    }

    /**
     * 韶钢（集团）公司厂界噪声监测季报
     * 根据查询条件，查询在线实时数据，将数据保存到Excel文件中
     *
     * @param wb
     * @param inInfo
     */
    public void saveDataToSheet_ZSJC(HSSFWorkbook wb, EiInfo inInfo) {

        HSSFSheet sheet = wb.createSheet("厂界噪声监测");

        HSSFDataFormat format = wb.createDataFormat();

        //标题样式
        HSSFCellStyle headerCellStyle = wb.createCellStyle();//单元格样式
        headerCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headerCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headerCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        headerCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headerCellStyle.setDataFormat(format.getFormat("@"));
        headerCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headerCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        headerCellStyle.setWrapText(true);
        HSSFFont headerFont = wb.createFont();//字体样式
        headerFont.setFontHeightInPoints((short) 14);//字体大小
        headerFont.setFontName("宋体");//字体
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
        headerCellStyle.setFont(headerFont);

        //内容样式
        HSSFCellStyle conCellStyle = wb.createCellStyle();
        conCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        conCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        conCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        conCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        conCellStyle.setDataFormat(format.getFormat("@"));
        conCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        conCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        conCellStyle.setWrapText(true);
        HSSFFont conFont = wb.createFont();
        conFont.setFontHeightInPoints((short) 12);
        conFont.setFontName("宋体");
        conFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        conCellStyle.setFont(conFont);

        HSSFCellStyle conCellStyle1 = wb.createCellStyle();
        conCellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        conCellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        conCellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);
        conCellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);
        conCellStyle1.setDataFormat(format.getFormat("@"));
        conCellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        conCellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        conCellStyle1.setWrapText(true);
        HSSFFont conFont1 = wb.createFont();
        conFont1.setFontHeightInPoints((short) 12);
        conFont1.setFontName("宋体");
        conCellStyle1.setFont(conFont1);

        HSSFRow headerRow = sheet.createRow(0);
        headerRow.setHeightInPoints(30);//设置行高
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 4));
        HSSFCell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("韶钢（集团）公司噪声监测季报表\r\n（2022年第三季度）");
        headerCell.setCellStyle(headerCellStyle);

        HSSFRow row1 = sheet.createRow(2);
        row1.setHeightInPoints(30);
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 4));
        HSSFCell cell1 = row1.createCell(0);
        cell1.setCellValue("编号：R/JL-168                                             单位：dB(A)");
        cell1.setCellStyle(conCellStyle);

        HSSFRow conRow = sheet.createRow(3);
        conRow.setHeightInPoints(30);
        HSSFCell conCell1 = conRow.createCell(0);
        conCell1.setCellValue("方位");
        conCell1.setCellStyle(conCellStyle);

        HSSFCell conCell2 = conRow.createCell(1);
        conCell2.setCellValue("监测点位");
        conCell2.setCellStyle(conCellStyle);

        HSSFCell conCell3 = conRow.createCell(2);
        conCell3.setCellValue("监测日期");
        conCell3.setCellStyle(conCellStyle);

        HSSFCell conCell4 = conRow.createCell(3);
        conCell4.setCellValue("昼间 LAeq");
        conCell4.setCellStyle(conCellStyle);

        HSSFCell conCell5 = conRow.createCell(4);
        conCell5.setCellValue("夜间 LAeq");
        conCell5.setCellStyle(conCellStyle);

        Map<String, String> pMap = new HashMap<>();
        pMap.put("code", inInfo.getString("inqu_status-0-code"));
        pMap.put("startdate", inInfo.getString("inqu_status-0-startdate"));
        pMap.put("enddate", inInfo.getString("inqu_status-0-enddate"));
        List<Map<String, Object>> list = this.dao.query("DUHD62.query", pMap);
        double daytimeValue = 0;
        double nirhttimeValue = 0;
        if (StrUtil.listIsNotNullOrEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> data = list.get(i);
                sheet.setColumnWidth(i, 18 * 256);//设置行宽
                HSSFRow dataRow = sheet.createRow(i + 4);
                dataRow.setHeightInPoints(30);

                HSSFCell dataCell0 = dataRow.createCell(0);
                dataCell0.setCellValue(data.get("direction").toString());
                dataCell0.setCellStyle(conCellStyle1);

                HSSFCell dataCell1 = dataRow.createCell(1);
                dataCell1.setCellValue(data.get("position").toString());
                dataCell1.setCellStyle(conCellStyle1);

                HSSFCell dataCell2 = dataRow.createCell(2);
                dataCell2.setCellValue(data.get("datetime").toString());
                dataCell2.setCellStyle(conCellStyle1);

                HSSFCell dataCell3 = dataRow.createCell(3);
                dataCell3.setCellValue(data.get("daytimeValue").toString());
                dataCell3.setCellStyle(conCellStyle1);

                HSSFCell dataCell4 = dataRow.createCell(4);
                dataCell4.setCellValue(data.get("nirhttimeValue").toString());
                dataCell4.setCellStyle(conCellStyle1);

                daytimeValue += Double.parseDouble(data.get("daytimeValue").toString());
                nirhttimeValue += Double.parseDouble(data.get("nirhttimeValue").toString());
            }
        }

        HSSFRow avgRow = sheet.createRow(list.size() + 4);
        avgRow.setHeightInPoints(30);
        CellRangeAddress avgRangeAddress = new CellRangeAddress(list.size() + 4, list.size() + 4, 0, 2);
        sheet.addMergedRegion(avgRangeAddress);
        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, avgRangeAddress, sheet, wb);
        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, avgRangeAddress, sheet, wb);
        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, avgRangeAddress, sheet, wb);
        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, avgRangeAddress, sheet, wb);
        HSSFCell avgCell = avgRow.createCell(0);
        avgCell.setCellValue("平均值");
        avgCell.setCellStyle(conCellStyle1);

        DecimalFormat df = new DecimalFormat("#.0");

        HSSFCell avgCell1 = avgRow.createCell(3);
        avgCell1.setCellValue(df.format(daytimeValue / list.size()));
        avgCell1.setCellStyle(conCellStyle1);

        HSSFCell avgCell2 = avgRow.createCell(4);
        avgCell2.setCellValue(df.format(nirhttimeValue / list.size()));
        avgCell2.setCellStyle(conCellStyle1);

        HSSFRow lastRow = sheet.createRow(list.size() + 5);
        lastRow.setHeightInPoints(30);
        CellRangeAddress lastRangeAddress = new CellRangeAddress(list.size() + 5, list.size() + 5, 0, 4);
        sheet.addMergedRegion(lastRangeAddress);
        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, lastRangeAddress, sheet, wb);
        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, lastRangeAddress, sheet, wb);
        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, lastRangeAddress, sheet, wb);
        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, lastRangeAddress, sheet, wb);
        HSSFCell lastCell = lastRow.createCell(0);
        lastCell.setCellValue("制表人：                          审核人：                         年  月  日");
        lastCell.setCellStyle(conCellStyle1);
    }


    /**
     * 韶钢（集团）公司厂界噪声监测季报
     * 根据查询条件，查询在线实时数据，将数据保存到Excel文件中
     * 根据模板导出
     *
     * @param wb
     * @param inInfo
     */
    public void saveDataToReport_ZSJC(HSSFWorkbook wb, EiInfo inInfo) {

        HSSFSheet sheet = wb.getSheetAt(0);
        //sheet.removeMergedRegion(2);//取消合并单元格
        //sheet.removeMergedRegion(1);//取消合并单元格

        HSSFDataFormat format = wb.createDataFormat();

        //标题样式
        HSSFCellStyle headerCellStyle = wb.createCellStyle();//单元格样式
        headerCellStyle.setDataFormat(format.getFormat("@"));//设置文本格式
        headerCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headerCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        headerCellStyle.setWrapText(true);
        HSSFFont headerFont = wb.createFont();//字体样式
        headerFont.setFontHeightInPoints((short) 16);//字体大小
        headerFont.setFontName("宋体");//字体
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
        headerCellStyle.setFont(headerFont);

        //内容样式
        HSSFCellStyle conCellStyle1 = wb.createCellStyle();
        conCellStyle1.setDataFormat(format.getFormat("@"));
        conCellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        conCellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        conCellStyle1.setWrapText(true);
        HSSFFont conFont1 = wb.createFont();
        conFont1.setFontHeightInPoints((short) 12);
        conFont1.setFontName("宋体");
        conCellStyle1.setFont(conFont1);

        //内容样式
        HSSFCellStyle conCellStyle2 = wb.createCellStyle();
        conCellStyle2.setDataFormat(format.getFormat("#,##0.0"));//设置保留一位小数
        conCellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        conCellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        conCellStyle2.setWrapText(true);
        HSSFFont conFont2 = wb.createFont();
        conFont2.setFontHeightInPoints((short) 12);
        conFont2.setFontName("宋体");
        conCellStyle2.setFont(conFont2);

        HSSFCellStyle conCellStyle3 = wb.createCellStyle();
        conCellStyle3.setDataFormat(format.getFormat("@"));
        conCellStyle3.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        conCellStyle3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        conCellStyle3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        conCellStyle3.setWrapText(true);
        HSSFFont conFont3 = wb.createFont();
        conFont3.setFontHeightInPoints((short) 12);
        conFont3.setFontName("宋体");
        conCellStyle3.setFont(conFont3);

        Map<String, String> pMap = new HashMap<>();
        String reportDate = inInfo.getString("reportDate");
        String year = reportDate.substring(0, 4);
        int intQuarter = Integer.parseInt(reportDate.substring(reportDate.length() - 1));
        String quarter = "";
        switch (intQuarter) {
            case 1:
                pMap.put("startmonth", year + "-01");
                pMap.put("endmonth", year + "-03");
                quarter = "1";
                break;
            case 2:
                pMap.put("startmonth", year + "-04");
                pMap.put("endmonth", year + "-06");
                quarter = "2";
                break;
            case 3:
                pMap.put("startmonth", year + "-07");
                pMap.put("endmonth", year + "-09");
                quarter = "3";
                break;
            case 4:
                pMap.put("startmonth", year + "-10");
                pMap.put("endmonth", year + "-12");
                quarter = "4";
                break;
        }
        pMap.put("monitorname", "噪声");
        List<Map<String, Object>> list = this.dao.query("DUHD62.queryNoise", pMap);

        HSSFRow headerRow = sheet.createRow(0);
        headerRow.setHeightInPoints(30);//设置行高
        CellRangeAddress headerRangeAddress = new CellRangeAddress(0, 1, 0, 4);
        sheet.addMergedRegion(headerRangeAddress);
        HSSFCell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("韶钢（集团）公司噪声监测季报表\r\n（" + year + "年" + quarter + "季度）");
        headerCell.setCellStyle(headerCellStyle);

        int rowindex = 4;
        if (StrUtil.listIsNotNullOrEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> data = list.get(i);

                if (i >= 8) {
                    if (sheet.getRow(i + 4) != null) {
                        int lastRowNum = sheet.getLastRowNum();
                        sheet.shiftRows(i + 4, lastRowNum, 1);//12-14行整体下移一行
                    }
                }

                HSSFRow dataRow = sheet.createRow(rowindex);
                dataRow.setHeightInPoints(30);

                HSSFCell dataCell0 = dataRow.createCell(0);
                dataCell0.setCellValue(data.get("DESCRIPTION").toString());
                dataCell0.setCellStyle(conCellStyle1);

                HSSFCell dataCell1 = dataRow.createCell(1);
                dataCell1.setCellValue(data.get("SITENAME").toString());
                dataCell1.setCellStyle(conCellStyle1);

                HSSFCell dataCell2 = dataRow.createCell(2);
                dataCell2.setCellValue(data.get("actTime").toString());
                dataCell2.setCellStyle(conCellStyle1);

                HSSFCell dataCell3 = dataRow.createCell(3);
                dataCell3.setCellValue(Double.parseDouble(data.get("daytimeValue").toString()));
                dataCell3.setCellStyle(conCellStyle2);

                HSSFCell dataCell4 = dataRow.createCell(4);
                dataCell4.setCellValue(Double.parseDouble(data.get("nirhttimeValue").toString()));
                dataCell4.setCellStyle(conCellStyle2);
                rowindex++;
            }
        }

        HSSFRow avgRow = null;
        if (list.size() + 4 < 12) {
            avgRow = sheet.getRow(12);
        } else {
            avgRow = sheet.getRow(list.size() + 4);
        }
        avgRow.setHeightInPoints(30);
        HSSFCell avgCell = avgRow.getCell(0);
        avgCell.setCellStyle(conCellStyle1);

//        HSSFCell avgCell1 = avgRow.getCell(3);
//        int num1 = 4 + list.size();
//        avgCell1.setCellFormula("AVERAGE(D5:D" + num1 + ")");
//        avgCell1.setCellStyle(conCellStyle2);
//
//        HSSFCell avgCell2 = avgRow.getCell(4);
//        avgCell2.setCellFormula("AVERAGE(E5:E" + num1 + ")");
//        avgCell2.setCellStyle(conCellStyle2);

        HSSFCell avgCell1 = avgRow.getCell(3);
        avgCell1.setCellFormula("65");
        avgCell1.setCellStyle(conCellStyle2);

        HSSFCell avgCell2 = avgRow.getCell(4);
        avgCell2.setCellFormula("55");
        avgCell2.setCellStyle(conCellStyle2);


        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        HSSFRow lastRow = sheet.createRow(list.size() + 5);
        lastRow.setHeightInPoints(30);
        HSSFCell lastCell = lastRow.createCell(0);
        CellRangeAddress lastRangeAddress = new CellRangeAddress(list.size() + 5, list.size() + 5, 0, 4);
        sheet.addMergedRegion(lastRangeAddress);
        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_MEDIUM, lastRangeAddress, sheet, wb);
        lastCell.setCellValue("   制表人：                        审核人：                       "+sdf.format(date)+"  ");
        lastCell.setCellStyle(conCellStyle3);
        //sheet.protectSheet("");
    }

    /**
     * 对外废气监测报告
     * 根据查询条件，查询在线实时数据，将数据保存到Excel文件中
     * 根据模板导出
     *
     * @param wb
     * @param inInfo
     */
    public void saveDataToReport_FQJC(HSSFWorkbook wb, EiInfo inInfo) {

        HSSFSheet sheet = wb.getSheetAt(0);
        //sheet.removeMergedRegion(2);//取消合并单元格
        //sheet.removeMergedRegion(1);//取消合并单元格

        HSSFDataFormat format = wb.createDataFormat();

        //标题样式
        HSSFCellStyle headerCellStyle = wb.createCellStyle();//单元格样式
        headerCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headerCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headerCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        headerCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headerCellStyle.setDataFormat(format.getFormat("@"));//设置文本格式
        headerCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headerCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        headerCellStyle.setWrapText(true);
        HSSFFont headerFont = wb.createFont();//字体样式
        headerFont.setFontHeightInPoints((short) 18);//字体大小
        headerFont.setFontName("宋体");//字体
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
        headerCellStyle.setFont(headerFont);

        //内容样式
        HSSFCellStyle conCellStyle1 = wb.createCellStyle();
        conCellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        conCellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        conCellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);
        conCellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);
        conCellStyle1.setDataFormat(format.getFormat("@"));
        conCellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        conCellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        conCellStyle1.setWrapText(true);
        HSSFFont conFont1 = wb.createFont();
        conFont1.setFontHeightInPoints((short) 12);
        conFont1.setFontName("宋体");
        conCellStyle1.setFont(conFont1);


        Map<String, String> pMap = new HashMap<>();
        String reportDate = inInfo.getString("reportDate");
        pMap.put("reportDate", reportDate);
        pMap.put("monitorname", "废气");
        String year = reportDate.substring(0, 4);
        int intMonth = Integer.parseInt(reportDate.substring(reportDate.length() - 2));

        List<Map<String, Object>> list = this.dao.query("DUHD62.queryFactor", pMap);

        HSSFRow headerRow = sheet.createRow(1);

        CellRangeAddress headerRangeAddress = new CellRangeAddress(1, 1, 0, 5);
        sheet.addMergedRegion(headerRangeAddress);
        //合并单元格加边框
        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, headerRangeAddress, sheet, wb);
        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, headerRangeAddress, sheet, wb);
        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, headerRangeAddress, sheet, wb);
        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, headerRangeAddress, sheet, wb);
        headerRow.setHeightInPoints(24);//设置行高
        HSSFCell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("                                                    监测月份：" + year + "年 " + intMonth + "月");
        headerCell.setCellStyle(conCellStyle1);

        int rowindex = 3;
        int itemindex = 3;
        if (StrUtil.listIsNotNullOrEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> data = list.get(i);

                Map<String, String> map = new HashMap<>();
                String factorId = data.get("factorId").toString();
                map.put("factorId", factorId);
                map.put("reportDate", reportDate);
                List<Map<String, Object>> list3 = this.dao.query("DUHD62.query3", map);

                Boolean appraise = true;
                if (StrUtil.listIsNotNullOrEmpty(list3)) {
                    for (int j = 0; j < list3.size(); j++) {
                        Map<String, Object> data3 = list3.get(j);

                        if (rowindex >= 7) {
                            if (sheet.getRow(rowindex) != null) {
                                int lastRowNum = sheet.getLastRowNum();
                                sheet.shiftRows(rowindex, lastRowNum, 1);//12-14行整体下移一行
                            }
                        }

                        String time = data3.get("actTime").toString();
                        String siteId = data3.get("siteId").toString();

                        EntityUtil eu = new EntityUtil();
                        String limit = eu.getHighLimitByDatatime(time, siteId, factorId, dao);

                        HSSFRow dataRow = sheet.createRow(rowindex);

                        HSSFCell dataCell2 = dataRow.createCell(2);
                        dataCell2.setCellValue(data3.get("siteName").toString());
                        dataCell2.setCellStyle(conCellStyle1);

                        HSSFCell dataCell3 = dataRow.createCell(3);
                        dataCell3.setCellValue(data3.get("itemValue") != null ? data3.get("itemValue").toString() : " ");
                        dataCell3.setCellStyle(conCellStyle1);

                        HSSFCell dataCell4 = dataRow.createCell(4);
                        dataCell4.setCellValue(limit != null ? limit : " ");
                        dataCell4.setCellStyle(conCellStyle1);

                        if (StrUtil.paramIsNotNullOrEmpty(limit)) {
                            if (data3.get("itemValue") != null) {
                                if (Double.parseDouble(data3.get("itemValue").toString()) > Double.parseDouble(limit)) {
                                    appraise = false;
                                }
                            }
                        }

                        rowindex++;
                    }
                }

                HSSFRow dataRow2 = sheet.getRow(itemindex);

                CellRangeAddress rowRangeAddress = new CellRangeAddress(itemindex, itemindex + list3.size() - 1, 0, 0);
                sheet.addMergedRegion(rowRangeAddress);
                //合并单元格加边框
                RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                HSSFCell dataCell = dataRow2.createCell(0);
                dataCell.setCellValue(data.get("factorName").toString());
                dataCell.setCellStyle(conCellStyle1);

                CellRangeAddress rowRangeAddress1 = new CellRangeAddress(itemindex, itemindex + list3.size() - 1, 1, 1);
                sheet.addMergedRegion(rowRangeAddress1);
                //合并单元格加边框
                RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress1, sheet, wb);
                RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress1, sheet, wb);
                RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress1, sheet, wb);
                RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress1, sheet, wb);
                HSSFCell dataCell1 = dataRow2.createCell(1);
                dataCell1.setCellValue(data.get("unit") != null ? data.get("unit").toString() : " ");
                dataCell1.setCellStyle(conCellStyle1);

                CellRangeAddress rowRangeAddress2 = new CellRangeAddress(itemindex, itemindex + list3.size() - 1, 5, 5);
                sheet.addMergedRegion(rowRangeAddress2);
                //合并单元格加边框
                RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                HSSFCell dataCell5 = dataRow2.createCell(5);
                if (appraise) {
                    dataCell5.setCellValue("达标");
                } else {
                    dataCell5.setCellValue("不达标");
                }
                dataCell5.setCellStyle(conCellStyle1);

                itemindex = rowindex;
            }
            int lastindex = sheet.getLastRowNum() - 1;
            HSSFRow remarkRow = sheet.getRow(lastindex);
            remarkRow.setHeightInPoints(84);//设置行高

            HSSFRow bottomRow = sheet.getRow(rowindex + 1);
            bottomRow.setHeightInPoints(24);//设置行高
        }

        //sheet.protectSheet("");
    }

    /**
     * 对外水质监测报告
     * 根据查询条件，查询在线实时数据，将数据保存到Excel文件中
     * 根据模板导出
     *
     * @param wb
     * @param inInfo
     */
    public void saveDataToReport_SZJC(HSSFWorkbook wb, EiInfo inInfo) {
        HSSFSheet sheet = wb.getSheetAt(0);
        //sheet.removeMergedRegion(2);//取消合并单元格
        //sheet.removeMergedRegion(1);//取消合并单元格

        HSSFDataFormat format = wb.createDataFormat();

        //标题样式
        HSSFCellStyle headerCellStyle = wb.createCellStyle();//单元格样式
        headerCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headerCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headerCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        headerCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headerCellStyle.setDataFormat(format.getFormat("@"));//设置文本格式
        headerCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headerCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        headerCellStyle.setWrapText(true);
        HSSFFont headerFont = wb.createFont();//字体样式
        headerFont.setFontHeightInPoints((short) 18);//字体大小
        headerFont.setFontName("宋体");//字体
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
        headerCellStyle.setFont(headerFont);

        //内容样式
        HSSFCellStyle conCellStyle1 = wb.createCellStyle();
        conCellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        conCellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        conCellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);
        conCellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);
        conCellStyle1.setDataFormat(format.getFormat("@"));
        conCellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        conCellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        conCellStyle1.setWrapText(true);
        HSSFFont conFont1 = wb.createFont();
        conFont1.setFontHeightInPoints((short) 12);
        conFont1.setFontName("宋体");
        conCellStyle1.setFont(conFont1);


        Map<String, String> pMap = new HashMap<>();
        String reportDate = inInfo.getString("reportDate");
        pMap.put("reportDate", reportDate);
        pMap.put("monitorname", "废水");
        String year = reportDate.substring(0, 4);
        int intMonth = Integer.parseInt(reportDate.substring(reportDate.length() - 2));

        List<Map<String, Object>> list = this.dao.query("DUHD62.queryFactor", pMap);

        HSSFRow headerRow = sheet.createRow(1);

        CellRangeAddress headerRangeAddress = new CellRangeAddress(1, 1, 0, 6);
        sheet.addMergedRegion(headerRangeAddress);
        //合并单元格加边框
        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, headerRangeAddress, sheet, wb);
        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, headerRangeAddress, sheet, wb);
        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, headerRangeAddress, sheet, wb);
        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, headerRangeAddress, sheet, wb);
        headerRow.setHeightInPoints(24);//设置行高
        HSSFCell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("                                                    监测月份：" + year + "年 " + intMonth + "月");
        headerCell.setCellStyle(conCellStyle1);

        int rowindex = 3;
        int itemindex = 3;
        if (StrUtil.listIsNotNullOrEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> data = list.get(i);

                Map<String, String> map = new HashMap<>();
                String factorId = data.get("factorId").toString();
                map.put("factorId", factorId);
                map.put("reportDate", reportDate);
                List<Map<String, Object>> list3 = this.dao.query("DUHD62.query3", map);

                Boolean appraise = true;
                if (StrUtil.listIsNotNullOrEmpty(list3)) {
                    for (int j = 0; j < list3.size(); j++) {
                        Map<String, Object> data3 = list3.get(j);

                        if (rowindex >= 7) {
                            if (sheet.getRow(rowindex) != null) {
                                int lastRowNum = sheet.getLastRowNum();
                                sheet.shiftRows(rowindex, lastRowNum, 1);//最后2行整体下移一行
                            }
                        }

                        String time = data3.get("actTime").toString();
                        String siteId = data3.get("siteId").toString();

                        EntityUtil eu = new EntityUtil();
                        String limit = eu.getHighLimitByDatatime(time, siteId, factorId, dao);

                        HSSFRow dataRow = sheet.createRow(rowindex);

                        HSSFCell dataCell2 = dataRow.createCell(2);
                        dataCell2.setCellValue(data3.get("siteName").toString());
                        dataCell2.setCellStyle(conCellStyle1);

                        HSSFCell dataCell3 = dataRow.createCell(3);
                        dataCell3.setCellValue(data3.get("itemValue") != null ? data3.get("itemValue").toString() : " ");
                        dataCell3.setCellStyle(conCellStyle1);

                        HSSFCell dataCell4 = dataRow.createCell(4);
                        dataCell4.setCellValue(limit != null ? limit : " ");
                        dataCell4.setCellStyle(conCellStyle1);

                        if (StrUtil.paramIsNotNullOrEmpty(limit)) {
                            if (data3.get("itemValue") != null) {
                                if (Double.parseDouble(data3.get("itemValue").toString()) > Double.parseDouble(limit)) {
                                    appraise = false;
                                }
                            }
                        }

                        rowindex++;
                    }
                }

                HSSFRow dataRow2 = sheet.getRow(itemindex);

                CellRangeAddress rowRangeAddress = new CellRangeAddress(itemindex, itemindex + list3.size() - 1, 0, 0);
                sheet.addMergedRegion(rowRangeAddress);
                //合并单元格加边框
                RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                HSSFCell dataCell = dataRow2.createCell(0);
                dataCell.setCellValue(data.get("factorName").toString());
                dataCell.setCellStyle(conCellStyle1);

                CellRangeAddress rowRangeAddress1 = new CellRangeAddress(itemindex, itemindex + list3.size() - 1, 1, 1);
                sheet.addMergedRegion(rowRangeAddress1);
                //合并单元格加边框
                RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress1, sheet, wb);
                RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress1, sheet, wb);
                RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress1, sheet, wb);
                RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress1, sheet, wb);
                HSSFCell dataCell1 = dataRow2.createCell(1);
                dataCell1.setCellValue(data.get("unit") != null ? data.get("unit").toString() : " ");
                dataCell1.setCellStyle(conCellStyle1);

                CellRangeAddress rowRangeAddress2 = new CellRangeAddress(itemindex, itemindex + list3.size() - 1, 5, 5);
                sheet.addMergedRegion(rowRangeAddress2);
                //合并单元格加边框
                RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                HSSFCell dataCell5 = dataRow2.createCell(5);
                if (appraise) {
                    dataCell5.setCellValue("达标");
                } else {
                    dataCell5.setCellValue("不达标");
                }
                dataCell5.setCellStyle(conCellStyle1);

                CellRangeAddress rowRangeAddress3 = new CellRangeAddress(itemindex, itemindex + list3.size() - 1, 6, 6);
                sheet.addMergedRegion(rowRangeAddress3);
                //合并单元格加边框
                RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress3, sheet, wb);
                RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress3, sheet, wb);
                RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress3, sheet, wb);
                RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress3, sheet, wb);

                itemindex = rowindex;
            }
            int lastindex = sheet.getLastRowNum() - 1;
            HSSFRow remarkRow = sheet.getRow(lastindex);
            remarkRow.setHeightInPoints(40);//设置行高

            HSSFRow bottomRow = sheet.getRow(rowindex + 1);
            bottomRow.setHeightInPoints(24);//设置行高
        }

        //sheet.protectSheet("");
    }

    /**
     * 韶钢集团公司自行监测手工报告_废气
     * 根据查询条件，查询在线实时数据，将数据保存到Excel文件中
     * 根据模板导出
     *
     * @param wb
     * @param inInfo
     */
    public void saveDataToReport_JCFQ(HSSFWorkbook wb, EiInfo inInfo) {
        HSSFSheet sheet = wb.getSheetAt(0);
        //sheet.removeMergedRegion(2);//取消合并单元格
        //sheet.removeMergedRegion(1);//取消合并单元格

        HSSFDataFormat format = wb.createDataFormat();

        //标题样式
        HSSFCellStyle headerCellStyle = wb.createCellStyle();//单元格样式
        headerCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headerCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headerCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        headerCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headerCellStyle.setDataFormat(format.getFormat("@"));//设置文本格式
        headerCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headerCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        headerCellStyle.setWrapText(true);
        HSSFFont headerFont = wb.createFont();//字体样式
        headerFont.setFontHeightInPoints((short) 16);//字体大小
        headerFont.setFontName("宋体");//字体
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
        headerCellStyle.setFont(headerFont);

        //内容样式
        HSSFCellStyle conCellStyle1 = wb.createCellStyle();
        conCellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        conCellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        conCellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);
        conCellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);
        conCellStyle1.setDataFormat(format.getFormat("@"));
        conCellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        conCellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        conCellStyle1.setWrapText(true);
        HSSFFont conFont1 = wb.createFont();
        conFont1.setFontHeightInPoints((short) 12);
        conFont1.setFontName("宋体");
        conCellStyle1.setFont(conFont1);


        Map<String, String> pMap = new HashMap<>();
        String reportDate = inInfo.getString("reportDate");
        pMap.put("reportDate", reportDate);
//        pMap.put("monitorname", "废气");
        String year = reportDate.substring(0, 4);//2023-03
        int intMonth = Integer.parseInt(reportDate.substring(reportDate.length() - 2));

        List<Map<String, Object>> list = this.dao.query("DUHD22.querySiteIdAndSiteName", pMap);

        HSSFRow headerRow = sheet.createRow(0);

        CellRangeAddress headerRangeAddress = new CellRangeAddress(0, 0, 0, 11);
        sheet.addMergedRegion(headerRangeAddress);
        //合并单元格加边框
        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, headerRangeAddress, sheet, wb);
        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, headerRangeAddress, sheet, wb);
        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, headerRangeAddress, sheet, wb);
        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, headerRangeAddress, sheet, wb);
        headerRow.setHeightInPoints(70);//设置行高
        HSSFCell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("广东省中南钢铁（集团）有限责任公司\r\n" + year + "年 " + intMonth + "月（废气）自行监测数据表");
        headerCell.setCellStyle(headerCellStyle);

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        int rowindex = 2;
        int itemindex = 2;
        if (StrUtil.listIsNotNullOrEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> data = list.get(i);

                Map<String, String> map = new HashMap<>();
                String siteId = data.get("siteId").toString();
                map.put("siteId", siteId);
                map.put("reportDate", reportDate);
                List<Map<String, Object>> list3 = this.dao.query("DUHD22.queryData", map);

                List<Map<String, Object>> bList = new ArrayList<>();
                List<Map<String, Object>> oList = new ArrayList<>();
                if (StrUtil.listIsNotNullOrEmpty(list3)) {
                    for (int j = 0; j < list3.size(); j++) {
                        Map<String, Object> data3 = list3.get(j);
                        if (data3.get("factorId").toString().equals("B02")) {   //标态风量
                            bList.add(data3);
                        }
                        if (data3.get("factorId").toString().equals("S01")) {   //含氧量
                            oList.add(data3);
                        }
                    }
                }
                if (StrUtil.listIsNotNullOrEmpty(list3)) {
                    for (int j = 0; j < list3.size(); j++) {
                        Map<String, Object> data3 = list3.get(j);

                        if(data3.get("factorId").toString().equals("B02") || data3.get("factorId").toString().equals("S01")) {

                        }else {

                            String time = data3.get("createTime").toString();
                            String factorId = data3.get("factorId").toString();

                            EntityUtil eu = new EntityUtil();
                            String limit = eu.getHighLimitByDatatime(time, siteId, factorId, dao);

                            HSSFRow dataRow = sheet.createRow(rowindex);

                            HSSFCell dataCell = dataRow.createCell(1);
                            dataCell.setCellValue(data3.get("createTime").toString());
                            dataCell.setCellStyle(conCellStyle1);

                            HSSFCell dataCell2 = dataRow.createCell(2);
                            dataCell2.setCellValue(date);
                            dataCell2.setCellStyle(conCellStyle1);

                            HSSFCell dataCell3 = dataRow.createCell(3);
                            dataCell3.setCellValue(data3.get("factorName").toString());
                            dataCell3.setCellStyle(conCellStyle1);

                            if (StrUtil.listIsNotNullOrEmpty(bList)) {
                                for (int l = 0; l < bList.size(); l++) {
                                    Map<String, Object> bData = bList.get(l);
                                    if (data3.get("createTime").toString().equals(bData.get("createTime").toString())) {
                                        HSSFCell dataCell4 = dataRow.createCell(4);
                                        dataCell4.setCellValue(bData.get("itemValue") != null ? bData.get("itemValue").toString() : "/");
                                        dataCell4.setCellStyle(conCellStyle1);
                                    } else {
                                        HSSFCell dataCell4 = dataRow.createCell(4);
                                        dataCell4.setCellValue("/");
                                        dataCell4.setCellStyle(conCellStyle1);
                                    }
                                }
                            } else {
                                HSSFCell dataCell4 = dataRow.createCell(4);
                                dataCell4.setCellValue("/");
                                dataCell4.setCellStyle(conCellStyle1);
                            }

//                            HSSFCell dataCell5 = dataRow.createCell(5);
//                            dataCell5.setCellValue("");
//                            dataCell5.setCellStyle(conCellStyle1);

//                            if (StrUtil.listIsNotNullOrEmpty(oList)) {
//                                for (int l = 0; l < oList.size(); l++) {
//                                    Map<String, Object> oData = oList.get(l);
//                                    if (data3.get("createTime").toString().equals(oData.get("createTime").toString())) {
//                                        HSSFCell dataCell6 = dataRow.createCell(6);
//                                        dataCell6.setCellValue(oData.get("itemValue") != null ? oData.get("itemValue").toString() : "/");
//                                        dataCell6.setCellStyle(conCellStyle1);
//                                    } else {
//                                        HSSFCell dataCell6 = dataRow.createCell(6);
//                                        dataCell6.setCellValue("/");
//                                        dataCell6.setCellStyle(conCellStyle1);
//                                    }
//                                }
//                            } else {
//                                HSSFCell dataCell6 = dataRow.createCell(6);
//                                dataCell6.setCellValue("/");
//                                dataCell6.setCellStyle(conCellStyle1);
//                            }
                            if (StrUtil.listIsNotNullOrEmpty(oList)) {
                                for (int l = 0; l < oList.size(); l++) {
                                    Map<String, Object> oData = oList.get(l);
                                    if (data3.get("createTime").toString().equals(oData.get("createTime").toString())) {
                                        HSSFCell dataCell5 = dataRow.createCell(5);
                                        dataCell5.setCellValue(oData.get("itemValue") != null ? oData.get("itemValue").toString() : "/");
                                        dataCell5.setCellStyle(conCellStyle1);
                                    } else {
                                        HSSFCell dataCell5 = dataRow.createCell(5);
                                        dataCell5.setCellValue("/");
                                        dataCell5.setCellStyle(conCellStyle1);
                                    }
                                }
                            } else {
                                HSSFCell dataCell5 = dataRow.createCell(5);
                                dataCell5.setCellValue("/");
                                dataCell5.setCellStyle(conCellStyle1);
                            }

                            HSSFCell dataCell6 = dataRow.createCell(6);
                            dataCell6.setCellValue(data3.get("itemValue") != null ? data3.get("itemValue").toString() : "/");
                            dataCell6.setCellStyle(conCellStyle1);


//                            HSSFCell dataCell7 = dataRow.createCell(7);
//                            dataCell7.setCellValue(data3.get("itemValue") != null ? data3.get("itemValue").toString() : "/");
//                            dataCell7.setCellStyle(conCellStyle1);

                            HSSFCell dataCell7 = dataRow.createCell(7);
                            dataCell7.setCellValue(data3.get("itemValueZs") != null ? data3.get("itemValueZs").toString() : "/");
                            dataCell7.setCellStyle(conCellStyle1);

//                            HSSFCell dataCell8 = dataRow.createCell(8);
//                            dataCell8.setCellValue(data3.get("itemValueZs") != null ? data3.get("itemValueZs").toString() : "/");
//                            dataCell8.setCellStyle(conCellStyle1);


                            HSSFCell dataCell8 = dataRow.createCell(8);
                            dataCell8.setCellValue(limit != null ? limit : " ");
                            dataCell8.setCellStyle(conCellStyle1);

//                            HSSFCell dataCell9 = dataRow.createCell(9);
//                            dataCell9.setCellValue(limit != null ? limit : " ");
//                            dataCell9.setCellStyle(conCellStyle1);


                            if (StrUtil.paramIsNotNullOrEmpty(limit) && data3.get("itemValue") != null) {
                                if (Double.parseDouble(data3.get("itemValue").toString()) > Double.parseDouble(limit)) {
                                    HSSFCell dataCell9 = dataRow.createCell(9);
                                    dataCell9.setCellValue("否");
                                    dataCell9.setCellStyle(conCellStyle1);
                                } else {
                                    HSSFCell dataCell9 = dataRow.createCell(9);
                                    dataCell9.setCellValue("是");
                                    dataCell9.setCellStyle(conCellStyle1);
                                }
                                HSSFCell dataCell10 = dataRow.createCell(10);
                                DecimalFormat df = new DecimalFormat("#0.0");
                                Double multiple = Double.parseDouble(data3.get("itemValue").toString()) / Double.parseDouble(limit);
                                dataCell10.setCellValue(df.format(multiple));
                                dataCell10.setCellStyle(conCellStyle1);

                            } else {
                                HSSFCell dataCell9 = dataRow.createCell(9);
                                dataCell9.setCellValue("");
                                dataCell9.setCellStyle(conCellStyle1);

                                HSSFCell dataCell10 = dataRow.createCell(10);
                                dataCell10.setCellValue("");
                                dataCell10.setCellStyle(conCellStyle1);
                            }
//                            if (StrUtil.paramIsNotNullOrEmpty(limit) && data3.get("itemValue") != null) {
//                                if (Double.parseDouble(data3.get("itemValue").toString()) > Double.parseDouble(limit)) {
//                                    HSSFCell dataCell10 = dataRow.createCell(10);
//                                    dataCell10.setCellValue("否");
//                                    dataCell10.setCellStyle(conCellStyle1);
//                                } else {
//                                    HSSFCell dataCell10 = dataRow.createCell(10);
//                                    dataCell10.setCellValue("是");
//                                    dataCell10.setCellStyle(conCellStyle1);
//                                }
//                                HSSFCell dataCell11 = dataRow.createCell(11);
//                                DecimalFormat df = new DecimalFormat("#0.0");
//                                Double multiple = Double.parseDouble(data3.get("itemValue").toString()) / Double.parseDouble(limit);
//                                dataCell11.setCellValue(df.format(multiple));
//                                dataCell11.setCellStyle(conCellStyle1);
//
//                            } else {
//                                HSSFCell dataCell10 = dataRow.createCell(10);
//                                dataCell10.setCellValue("");
//                                dataCell10.setCellStyle(conCellStyle1);
//
//                                HSSFCell dataCell11 = dataRow.createCell(11);
//                                dataCell11.setCellValue("");
//                                dataCell11.setCellStyle(conCellStyle1);
//                            }

                            HSSFCell dataCell11 = dataRow.createCell(11);
                            dataCell11.setCellValue("");
                            dataCell11.setCellStyle(conCellStyle1);

                            rowindex++;
                        }
                    }
                }

                HSSFRow dataRow2 = sheet.getRow(itemindex);

                CellRangeAddress rowRangeAddress = new CellRangeAddress(itemindex, rowindex-1, 0, 0);
                sheet.addMergedRegion(rowRangeAddress);
                //合并单元格加边框
                RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                HSSFCell dataCell = dataRow2.createCell(0);
                dataCell.setCellValue(data.get("siteName").toString());
                dataCell.setCellStyle(conCellStyle1);

                itemindex = rowindex;
            }
        }

        //sheet.protectSheet("");
    }

    /**
     * 韶钢集团公司自行监测手工报告_废水
     * 根据查询条件，查询在线实时数据，将数据保存到Excel文件中
     * 根据模板导出
     *
     * @param wb
     * @param inInfo
     */
    public void saveDataToReport_JCFS(HSSFWorkbook wb, EiInfo inInfo) {
        HSSFSheet sheet = wb.getSheetAt(0);
        //sheet.removeMergedRegion(2);//取消合并单元格
        //sheet.removeMergedRegion(1);//取消合并单元格

        HSSFDataFormat format = wb.createDataFormat();

        //标题样式
        HSSFCellStyle headerCellStyle = wb.createCellStyle();//单元格样式
        headerCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headerCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headerCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        headerCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headerCellStyle.setDataFormat(format.getFormat("@"));//设置文本格式
        headerCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headerCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        headerCellStyle.setWrapText(true);
        HSSFFont headerFont = wb.createFont();//字体样式
        headerFont.setFontHeightInPoints((short) 16);//字体大小
        headerFont.setFontName("宋体");//字体
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
        headerCellStyle.setFont(headerFont);

        //内容样式
        HSSFCellStyle conCellStyle1 = wb.createCellStyle();
        conCellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        conCellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        conCellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);
        conCellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);
        conCellStyle1.setDataFormat(format.getFormat("@"));
        conCellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        conCellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        conCellStyle1.setWrapText(true);
        HSSFFont conFont1 = wb.createFont();
        conFont1.setFontHeightInPoints((short) 12);
        conFont1.setFontName("宋体");
        conCellStyle1.setFont(conFont1);


        Map<String, String> pMap = new HashMap<>();
        String reportDate = inInfo.getString("reportDate");
        pMap.put("reportDate", reportDate);
        pMap.put("monitorname", "废水");
        String year = reportDate.substring(0, 4);
        int intMonth = Integer.parseInt(reportDate.substring(reportDate.length() - 2));

        List<Map<String, Object>> list = this.dao.query("DUHD62.querySite", pMap);

        HSSFRow headerRow = sheet.createRow(0);

        CellRangeAddress headerRangeAddress = new CellRangeAddress(0, 0, 0, 9);
        sheet.addMergedRegion(headerRangeAddress);
        //合并单元格加边框
        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, headerRangeAddress, sheet, wb);
        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, headerRangeAddress, sheet, wb);
        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, headerRangeAddress, sheet, wb);
        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, headerRangeAddress, sheet, wb);
        headerRow.setHeightInPoints(70);//设置行高
        HSSFCell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("福建省韶钢（集团）有限责任公司\r\n" + year + "年 " + intMonth + "月（废水）自行监测数据表");
        headerCell.setCellStyle(headerCellStyle);

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        int rowindex = 2;
        int itemindex = 2;
        if (StrUtil.listIsNotNullOrEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> data = list.get(i);

                Map<String, String> map = new HashMap<>();
                String siteId = data.get("siteId").toString();
                map.put("siteId", siteId);
                map.put("reportDate", reportDate);
                List<Map<String, Object>> list3 = this.dao.query("DUHD62.queryData", map);


                if (StrUtil.listIsNotNullOrEmpty(list3)) {
                    for (int j = 0; j < list3.size(); j++) {
                        Map<String, Object> data3 = list3.get(j);


                        String time = data3.get("actTime").toString();
                        String factorId = data3.get("factorId").toString();

                        EntityUtil eu = new EntityUtil();
                        String limit = eu.getHighLimitByDatatime(time, siteId, factorId, dao);

                        HSSFRow dataRow = sheet.createRow(rowindex);


                        HSSFCell dataCell = dataRow.createCell(1);
                        dataCell.setCellValue(data3.get("factorName").toString());
                        dataCell.setCellStyle(conCellStyle1);

                        HSSFCell dataCell2 = dataRow.createCell(2);
                        dataCell2.setCellValue(data3.get("actTime").toString());
                        dataCell2.setCellStyle(conCellStyle1);

                        HSSFCell dataCell3 = dataRow.createCell(3);
                        dataCell3.setCellValue(date);
                        dataCell3.setCellStyle(conCellStyle1);


                        HSSFCell dataCell4 = dataRow.createCell(4);
                        dataCell4.setCellValue("");
                        dataCell4.setCellStyle(conCellStyle1);


                        HSSFCell dataCell5 = dataRow.createCell(5);
                        dataCell5.setCellValue("");
                        dataCell5.setCellStyle(conCellStyle1);


                        HSSFCell dataCell6 = dataRow.createCell(6);
                        dataCell6.setCellValue(data3.get("itemValue") != null ? data3.get("itemValue").toString() : " ");
                        dataCell6.setCellStyle(conCellStyle1);

                        HSSFCell dataCell7 = dataRow.createCell(7);
                        dataCell7.setCellValue(limit != null ? limit : " ");
                        dataCell7.setCellStyle(conCellStyle1);

                        if (StrUtil.paramIsNotNullOrEmpty(limit) && data3.get("itemValue") != null) {

                            if (Double.parseDouble(data3.get("itemValue").toString()) > Double.parseDouble(limit)) {
                                HSSFCell dataCell8 = dataRow.createCell(8);
                                dataCell8.setCellValue("否");
                                dataCell8.setCellStyle(conCellStyle1);
                            } else {
                                HSSFCell dataCell8 = dataRow.createCell(8);
                                dataCell8.setCellValue("是");
                                dataCell8.setCellStyle(conCellStyle1);
                            }

                            HSSFCell dataCell9 = dataRow.createCell(9);
                            DecimalFormat df = new DecimalFormat("#0.0");
                            Double multiple = Double.parseDouble(data3.get("itemValue").toString()) / Double.parseDouble(limit);
                            dataCell9.setCellValue(df.format(multiple));
                            dataCell9.setCellStyle(conCellStyle1);

                        } else {
                            HSSFCell dataCell8 = dataRow.createCell(8);
                            dataCell8.setCellValue("");
                            dataCell8.setCellStyle(conCellStyle1);

                            HSSFCell dataCell9 = dataRow.createCell(9);
                            dataCell9.setCellValue("");
                            dataCell9.setCellStyle(conCellStyle1);
                        }

                        rowindex++;
                    }
                }

                HSSFRow dataRow2 = sheet.getRow(itemindex);

                CellRangeAddress rowRangeAddress = new CellRangeAddress(itemindex, itemindex + list3.size() - 1, 0, 0);
                sheet.addMergedRegion(rowRangeAddress);
                //合并单元格加边框
                RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                HSSFCell dataCell = dataRow2.createCell(0);
                dataCell.setCellValue(data.get("siteName").toString());
                dataCell.setCellStyle(conCellStyle1);

                itemindex = rowindex;
            }
        }

        //sheet.protectSheet("");
    }

    /**
     * 韶钢集团公司自行监测手工报告_无组织
     * 根据查询条件，查询在线实时数据，将数据保存到Excel文件中
     * 根据模板导出
     *
     * @param wb
     * @param inInfo
     */
    public void saveDataToReport_JCWZZ(HSSFWorkbook wb, EiInfo inInfo) {
        HSSFSheet sheet = wb.getSheetAt(0);
        //sheet.removeMergedRegion(2);//取消合并单元格
        //sheet.removeMergedRegion(1);//取消合并单元格

        HSSFDataFormat format = wb.createDataFormat();

        //标题样式
        HSSFCellStyle headerCellStyle = wb.createCellStyle();//单元格样式
        headerCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headerCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headerCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        headerCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headerCellStyle.setDataFormat(format.getFormat("@"));//设置文本格式
        headerCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headerCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        headerCellStyle.setWrapText(true);
        HSSFFont headerFont = wb.createFont();//字体样式
        headerFont.setFontHeightInPoints((short) 16);//字体大小
        headerFont.setFontName("宋体");//字体
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
        headerCellStyle.setFont(headerFont);

        //内容样式
        HSSFCellStyle conCellStyle1 = wb.createCellStyle();
        conCellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        conCellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        conCellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);
        conCellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);
        conCellStyle1.setDataFormat(format.getFormat("@"));
        conCellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        conCellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        conCellStyle1.setWrapText(true);
        HSSFFont conFont1 = wb.createFont();
        conFont1.setFontHeightInPoints((short) 12);
        conFont1.setFontName("宋体");
        conCellStyle1.setFont(conFont1);


        Map<String, String> pMap = new HashMap<>();
        String reportDate = inInfo.getString("reportDate");
        pMap.put("reportDate", reportDate);
        pMap.put("monitorname", "无组织");
        String year = reportDate.substring(0, 4);
        int intMonth = Integer.parseInt(reportDate.substring(reportDate.length() - 2));

        List<Map<String, Object>> list = this.dao.query("DUHD62.querySite", pMap);

        HSSFRow headerRow = sheet.createRow(0);

        CellRangeAddress headerRangeAddress = new CellRangeAddress(0, 0, 0, 8);
        sheet.addMergedRegion(headerRangeAddress);
        //合并单元格加边框
        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, headerRangeAddress, sheet, wb);
        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, headerRangeAddress, sheet, wb);
        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, headerRangeAddress, sheet, wb);
        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, headerRangeAddress, sheet, wb);
        headerRow.setHeightInPoints(70);//设置行高
        HSSFCell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("福建省韶钢（集团）有限责任公司\r\n" + year + "年 " + intMonth + "月（无组织）自行监测数据表");
        headerCell.setCellStyle(headerCellStyle);

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        int rowindex = 2;
        int itemindex = 2;
        if (StrUtil.listIsNotNullOrEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> data = list.get(i);

                Map<String, String> map = new HashMap<>();
                String siteId = data.get("siteId").toString();
                map.put("siteId", siteId);
                map.put("reportDate", reportDate);
                List<Map<String, Object>> list3 = this.dao.query("DUHD62.queryData", map);


                if (StrUtil.listIsNotNullOrEmpty(list3)) {
                    for (int j = 0; j < list3.size(); j++) {
                        Map<String, Object> data3 = list3.get(j);


                        String time = data3.get("actTime").toString();
                        String factorId = data3.get("factorId").toString();

                        EntityUtil eu = new EntityUtil();
                        String limit = eu.getHighLimitByDatatime(time, siteId, factorId, dao);

                        HSSFRow dataRow = sheet.createRow(rowindex);


                        HSSFCell dataCell = dataRow.createCell(1);
                        dataCell.setCellValue(data3.get("actTime").toString());
                        dataCell.setCellStyle(conCellStyle1);

                        HSSFCell dataCell2 = dataRow.createCell(2);
                        dataCell2.setCellValue(date);
                        dataCell2.setCellStyle(conCellStyle1);

                        HSSFCell dataCell3 = dataRow.createCell(3);
                        dataCell3.setCellValue(data3.get("factorName").toString());
                        dataCell3.setCellStyle(conCellStyle1);


                        HSSFCell dataCell4 = dataRow.createCell(4);
                        dataCell4.setCellValue(data3.get("itemValue") != null ? data3.get("itemValue").toString() : " ");
                        dataCell4.setCellStyle(conCellStyle1);


                        HSSFCell dataCell5 = dataRow.createCell(5);
                        dataCell5.setCellValue(limit != null ? limit : " ");
                        dataCell5.setCellStyle(conCellStyle1);

                        if (StrUtil.paramIsNotNullOrEmpty(limit) && data3.get("itemValue") != null) {

                            if (Double.parseDouble(data3.get("itemValue").toString()) > Double.parseDouble(limit)) {
                                HSSFCell dataCell6 = dataRow.createCell(6);
                                dataCell6.setCellValue("否");
                                dataCell6.setCellStyle(conCellStyle1);
                            } else {
                                HSSFCell dataCell6 = dataRow.createCell(6);
                                dataCell6.setCellValue("是");
                                dataCell6.setCellStyle(conCellStyle1);
                            }

                            HSSFCell dataCell7 = dataRow.createCell(7);
                            DecimalFormat df = new DecimalFormat("#0.0");
                            Double multiple = Double.parseDouble(data3.get("itemValue").toString()) / Double.parseDouble(limit);
                            dataCell7.setCellValue(df.format(multiple));
                            dataCell7.setCellStyle(conCellStyle1);


                        } else {
                            HSSFCell dataCell6 = dataRow.createCell(6);
                            dataCell6.setCellValue("");
                            dataCell6.setCellStyle(conCellStyle1);

                            HSSFCell dataCell7 = dataRow.createCell(7);
                            dataCell7.setCellValue("");
                            dataCell7.setCellStyle(conCellStyle1);
                        }

                        HSSFCell dataCell8 = dataRow.createCell(8);
                        dataCell8.setCellValue("");
                        dataCell8.setCellStyle(conCellStyle1);

                        rowindex++;
                    }
                }

                HSSFRow dataRow2 = sheet.getRow(itemindex);

                CellRangeAddress rowRangeAddress = new CellRangeAddress(itemindex, itemindex + list3.size() - 1, 0, 0);
                sheet.addMergedRegion(rowRangeAddress);
                //合并单元格加边框
                RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                HSSFCell dataCell = dataRow2.createCell(0);
                dataCell.setCellValue(data.get("siteName").toString());
                dataCell.setCellStyle(conCellStyle1);

                itemindex = rowindex;
            }
        }

        //sheet.protectSheet("");
    }

    /**
     * 韶钢（集团）公司水质监测报表
     * 根据查询条件，查询在线实时数据，将数据保存到Excel文件中
     * 根据模板导出
     *
     * @param xb
     * @param inInfo
     */
    public void saveDataToReport_JCSZ(XSSFWorkbook xb, EiInfo inInfo) {
        XSSFSheet sheet = xb.getSheetAt(0);
        //sheet.removeMergedRegion(2);//取消合并单元格
        //sheet.removeMergedRegion(1);//取消合并单元格

        XSSFDataFormat format = xb.createDataFormat();

        //标题样式
        XSSFCellStyle headerCellStyle = xb.createCellStyle();//单元格样式
        headerCellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        headerCellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        headerCellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
        headerCellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
        headerCellStyle.setDataFormat(format.getFormat("@"));//设置文本格式
        headerCellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        headerCellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        headerCellStyle.setWrapText(true);
        XSSFFont headerFont = xb.createFont();//字体样式
        headerFont.setFontHeightInPoints((short) 14);//字体大小
        headerFont.setFontName("宋体");//字体
        headerFont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);//加粗
        headerCellStyle.setFont(headerFont);

        //内容样式
        XSSFCellStyle conCellStyle1 = xb.createCellStyle();
        conCellStyle1.setDataFormat(format.getFormat("@"));
        conCellStyle1.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        conCellStyle1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        conCellStyle1.setWrapText(false);
        XSSFFont conFont1 = xb.createFont();
        conFont1.setFontHeightInPoints((short) 11);
        conFont1.setFontName("宋体");
        conCellStyle1.setFont(conFont1);

        XSSFCellStyle conCellStyle2 = xb.createCellStyle();
        conCellStyle2.setDataFormat(format.getFormat("0.000_ "));//设置保留2位小数
        conCellStyle2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        conCellStyle2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        conCellStyle2.setWrapText(true);
        XSSFFont conFont2 = xb.createFont();
        conFont2.setFontHeightInPoints((short) 10);
        conFont2.setFontName("Times New Roman");
        conCellStyle2.setFont(conFont2);

        XSSFCellStyle conCellStyle3 = xb.createCellStyle();
        conCellStyle3.setDataFormat(format.getFormat("0.000_ "));//设置保留2位小数
        conCellStyle3.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        conCellStyle3.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        conCellStyle3.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        conCellStyle3.setWrapText(true);
        XSSFFont conFont3 = xb.createFont();
        conFont3.setFontHeightInPoints((short) 10);
        conFont3.setFontName("Times New Roman");
        conCellStyle3.setFont(conFont3);

        XSSFCellStyle conCellStyle4 = xb.createCellStyle();
        conCellStyle4.setDataFormat(format.getFormat("@"));//设置保留2位小数
        conCellStyle4.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        conCellStyle4.setBorderTop(XSSFCellStyle.BORDER_THIN);
        conCellStyle4.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        conCellStyle4.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        conCellStyle4.setWrapText(true);
        XSSFFont conFont4 = xb.createFont();
        conFont4.setFontHeightInPoints((short) 11);
        conFont4.setFontName("Times New Roman");
        conCellStyle4.setFont(conFont4);

        XSSFCellStyle conCellStyle5 = xb.createCellStyle();
        conCellStyle5.setDataFormat(format.getFormat("0.00000_ "));//设置保留2位小数
        conCellStyle5.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        conCellStyle5.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        conCellStyle5.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        conCellStyle5.setWrapText(true);
        XSSFFont conFont5 = xb.createFont();
        conFont5.setFontHeightInPoints((short) 10);
        conFont5.setFontName("Times New Roman");
        conCellStyle5.setFont(conFont5);

        XSSFCellStyle conCellStyle6 = xb.createCellStyle();
        conCellStyle6.setDataFormat(format.getFormat("0.00000_ "));//设置保留2位小数
        conCellStyle6.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        conCellStyle6.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        conCellStyle6.setWrapText(true);
        XSSFFont conFont6 = xb.createFont();
        conFont6.setFontHeightInPoints((short) 10);
        conFont6.setFontName("Times New Roman");
        conCellStyle6.setFont(conFont6);

        Map<String, String> pMap = new HashMap<>();
        String reportDate = inInfo.getString("reportDate");
        pMap.put("reportDate", reportDate);
        pMap.put("monitorname", "废水");
        String year = reportDate.substring(0, 4);
        int intMonth = Integer.parseInt(reportDate.substring(reportDate.length() - 2));

        List<Map<String, Object>> factorList = this.dao.query("DUHD62.queryFactor", pMap);

        int maxCol = 2+factorList.size();
        CellRangeAddress titleRangeAddress = new CellRangeAddress(0, 0, 0, maxCol);
        sheet.addMergedRegion(titleRangeAddress);


        XSSFRow headerRow = sheet.createRow(1);
        CellRangeAddress headerRangeAddress = new CellRangeAddress(1, 1, 0, maxCol);
        sheet.addMergedRegion(headerRangeAddress);
        headerRow.setHeightInPoints(21);//设置行高
        XSSFCell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("（" + year + "年 " + intMonth + "月）");
        headerCell.setCellStyle(conCellStyle1);

        XSSFRow row2 = sheet.createRow(2);
        XSSFCell cell1 = row2.createCell(0);
        cell1.setCellValue("   R/JL-164");
        cell1.setCellStyle(conCellStyle1);
        XSSFCell cell2 = row2.createCell(maxCol-1);
        cell2.setCellValue("单位mg/L（pH除外）");
        cell2.setCellStyle(conCellStyle1);

        XSSFRow row = sheet.getRow(3);
        List<String> factoridList = new ArrayList<>();
        if(StrUtil.listIsNotNullOrEmpty(factorList)){
            for (int i = 0; i < factorList.size(); i++) {
                Map<String, Object> factor = factorList.get(i);
                XSSFCell cell = row.createCell(3+i);
                cell.setCellValue(factor.get("factorName").toString());
                cell.setCellStyle(conCellStyle4);
                factoridList.add(factor.get("factorId").toString());
            }
        }

        List<Map<String, Object>> list = this.dao.query("DUHD62.querySite", pMap);

        int rowindex = 4;
        int itemindex = 4;
        if (StrUtil.listIsNotNullOrEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> data = list.get(i);

                Map<String, String> map = new HashMap<>();
                String siteId = data.get("siteId").toString();
                map.put("siteId", siteId);
                map.put("reportDate", reportDate);
                List<Map<String, Object>> dateList = this.dao.query("DUHD62.queryDate", map);
                List<Map<String, Object>> list3 = this.dao.query("DUHD62.queryData", map);

                if (StrUtil.listIsNotNullOrEmpty(dateList)) {
                    for (int j = 0; j < dateList.size(); j++) {
                        Map<String, Object> dateMap = dateList.get(j);

                        XSSFRow dataRow = sheet.createRow(rowindex);
                        XSSFCell dataCell2 = dataRow.createCell(2);
                        dataCell2.setCellValue(dateMap.get("actTime").toString().substring(5));
                        dataCell2.setCellStyle(conCellStyle2);

                        if (StrUtil.listIsNotNullOrEmpty(list3)) {
                            for (int l = 0; l < list3.size(); l++) {
                                Map<String, Object> data3 = list3.get(l);

                                if (dateMap.get("actTime").toString().equals(data3.get("actTime").toString())) {

                                    for (int k = 0; k < factoridList.size(); k++) {
                                        if (data3.get("factorId").toString().equals(factoridList.get(k))) {
                                            XSSFCell dataCell = dataRow.createCell(3 + k);
                                            if (data3.get("itemValue") != null) {
                                                dataCell.setCellValue(Double.parseDouble(data3.get("itemValue").toString()));
                                            } else {
                                                dataCell.setCellValue("");
                                            }
                                            if(data3.get("factorId").toString().equals("022") || data3.get("factorId").toString().equals("027") ||data3.get("factorId").toString().equals("029") ){
                                                dataCell.setCellStyle(conCellStyle6);
                                            }else {
                                                dataCell.setCellStyle(conCellStyle2);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        XSSFRow dataRow1 = sheet.getRow(rowindex);

                        for (int s = 3; s < maxCol+1; s++) {
                            XSSFCell dataCell = dataRow1.getCell(s);
                            if (dataCell == null) {
                                dataCell = dataRow.createCell(s);
                                dataCell.setCellValue("");
                                dataCell.setCellStyle(conCellStyle2);
                            }
                        }
                        rowindex++;
                    }
                }


                XSSFRow dataRow2 = sheet.getRow(itemindex);

                CellRangeAddress rowRangeAddress = new CellRangeAddress(itemindex, itemindex + dateList.size() - 1, 1, 1);
                sheet.addMergedRegion(rowRangeAddress);
                XSSFCell dataCell = dataRow2.createCell(1);
                dataCell.setCellValue(data.get("siteName").toString());
                dataCell.setCellStyle(conCellStyle3);

                XSSFRow avgRow = sheet.createRow(rowindex);
                CellRangeAddress avgRangeAddress = new CellRangeAddress(rowindex, rowindex, 1, 2);
                sheet.addMergedRegion(avgRangeAddress);
                RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN, avgRangeAddress, sheet, xb);
                XSSFCell avgCell = avgRow.createCell(1);
                avgCell.setCellValue("平均值");
                avgCell.setCellStyle(conCellStyle3);

                List<String> cell = new ArrayList<>();

                cell.add("D");
                cell.add("E");
                cell.add("F");
                cell.add("G");
                cell.add("H");
                cell.add("I");
                cell.add("J");
                cell.add("K");
                cell.add("L");
                cell.add("M");
                cell.add("N");
                cell.add("O");
                cell.add("P");
                cell.add("Q");
                cell.add("R");
                cell.add("S");
                cell.add("T");
                cell.add("U");
                cell.add("V");
                cell.add("W");
                cell.add("X");
                cell.add("Y");
                cell.add("Z");

                for (int j = 0; j < factorList.size(); j++) {
                    boolean lock = false;
                    for (int k = itemindex; k < rowindex; k++) {
                        XSSFRow valueRow = sheet.getRow(k);
                        XSSFCell  valueCell = valueRow.getCell(j + 3);
                        if (valueCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                            lock = true;
                        }
                    }
                    XSSFCell avgCell1 = avgRow.createCell(j + 3);
                    int num1 = itemindex + 1;
                    int num2 = itemindex + dateList.size();
                    if(lock){
                        avgCell1.setCellFormula("AVERAGE(" + cell.get(j) + num1 + ":" + cell.get(j) + num2 + ")");
                    }else {
                        avgCell1.setCellValue("");
                    }

                    String title = row.getCell(j + 3).getStringCellValue();
                    if (title.equals("总铅") || title.equals("总镉") || title.equals("总铜")) {
                        avgCell1.setCellStyle(conCellStyle5);
                    } else {
                        avgCell1.setCellStyle(conCellStyle3);
                    }

                }


                CellRangeAddress rangeAddress = new CellRangeAddress(itemindex, itemindex + dateList.size(), 0, 0);
                sheet.addMergedRegion(rangeAddress);
                RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN, rangeAddress, sheet, xb);
                XSSFCell sortCell = dataRow2.createCell(0);
                sortCell.setCellValue(i + 1);
                sortCell.setCellStyle(conCellStyle1);

                itemindex = rowindex + 1;
                rowindex++;
            }
        }

        XSSFRow lastRow = sheet.createRow(itemindex);
        XSSFCell lastCell1 = lastRow.createCell(1);
        lastCell1.setCellValue("制表人：");
        lastCell1.setCellStyle(conCellStyle1);

        XSSFCell lastCell2 = lastRow.createCell(maxCol/2);
        lastCell2.setCellValue("审核人：");
        lastCell2.setCellStyle(conCellStyle1);

        XSSFCell lastCell3 = lastRow.createCell(maxCol-2);
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        lastCell3.setCellValue("打印日期：" + date);
        lastCell3.setCellStyle(conCellStyle1);
        //sheet.protectSheet("");
    }


    /**
     * 循环水质监测报告单
     * 根据查询条件，查询在线实时数据，将数据保存到Excel文件中
     * 根据模板导出
     *
     * @param xb
     * @param inInfo
     */
    public void saveDataToReport_JCXHS(XSSFWorkbook xb, EiInfo inInfo) {
        XSSFSheet sheet = xb.getSheetAt(0);
        //sheet.removeMergedRegion(2);//取消合并单元格
        //sheet.removeMergedRegion(1);//取消合并单元格

        XSSFDataFormat format = xb.createDataFormat();

        //标题样式
        XSSFCellStyle headerCellStyle = xb.createCellStyle();//单元格样式
        headerCellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        headerCellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        headerCellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
        headerCellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
        headerCellStyle.setDataFormat(format.getFormat("@"));//设置文本格式
        headerCellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        headerCellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        headerCellStyle.setWrapText(true);
        XSSFFont headerFont = xb.createFont();//字体样式
        headerFont.setFontHeightInPoints((short) 14);//字体大小
        headerFont.setFontName("宋体");//字体
        headerFont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);//加粗
        headerCellStyle.setFont(headerFont);

        //内容样式
        XSSFCellStyle conCellStyle1 = xb.createCellStyle();
        conCellStyle1.setDataFormat(format.getFormat("@"));
        conCellStyle1.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        conCellStyle1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        conCellStyle1.setWrapText(true);
        XSSFFont conFont1 = xb.createFont();
        conFont1.setFontHeightInPoints((short) 11);
        conFont1.setFontName("宋体");
        conCellStyle1.setFont(conFont1);

        XSSFCellStyle conCellStyle2 = xb.createCellStyle();
        conCellStyle2.setDataFormat(format.getFormat("@"));
        conCellStyle2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        conCellStyle2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        conCellStyle2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        conCellStyle2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        conCellStyle2.setBorderRight(XSSFCellStyle.BORDER_THIN);
        conCellStyle2.setBorderTop(XSSFCellStyle.BORDER_THIN);
        conCellStyle2.setWrapText(true);
        XSSFFont conFont2 = xb.createFont();
        conFont2.setFontHeightInPoints((short) 10);
        conFont2.setFontName("宋体");
        conCellStyle2.setFont(conFont2);

        XSSFCellStyle conCellStyle3 = xb.createCellStyle();
        conCellStyle3.setDataFormat(format.getFormat("0.0_ "));//设置保留2位小数
        conCellStyle3.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        conCellStyle3.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        conCellStyle3.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        conCellStyle3.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        conCellStyle3.setBorderRight(XSSFCellStyle.BORDER_THIN);
        conCellStyle3.setBorderTop(XSSFCellStyle.BORDER_THIN);
        conCellStyle3.setWrapText(true);
        XSSFFont conFont3 = xb.createFont();
        conFont3.setFontHeightInPoints((short) 10);
        conFont3.setFontName("Times New Roman");
        conCellStyle3.setFont(conFont3);

        XSSFCellStyle conCellStyle4 = xb.createCellStyle();
        conCellStyle4.setDataFormat(format.getFormat("0.00_ "));//设置保留2位小数
        conCellStyle4.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        conCellStyle4.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        conCellStyle4.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        conCellStyle4.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        conCellStyle4.setBorderRight(XSSFCellStyle.BORDER_THIN);
        conCellStyle4.setBorderTop(XSSFCellStyle.BORDER_THIN);
        conCellStyle4.setWrapText(true);
        XSSFFont conFont4 = xb.createFont();
        conFont4.setFontHeightInPoints((short) 10);
        conFont4.setFontName("Times New Roman");
        conCellStyle4.setFont(conFont4);

        XSSFCellStyle conCellStyle5 = xb.createCellStyle();
        conCellStyle5.setDataFormat(format.getFormat("0.000_ "));
        conCellStyle5.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        conCellStyle5.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        conCellStyle5.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        conCellStyle5.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        conCellStyle5.setBorderRight(XSSFCellStyle.BORDER_THIN);
        conCellStyle5.setBorderTop(XSSFCellStyle.BORDER_THIN);
        conCellStyle5.setWrapText(true);
        XSSFFont conFont5 = xb.createFont();
        conFont5.setFontHeightInPoints((short) 10);
        conFont5.setFontName("Times New Roman");
        conCellStyle5.setFont(conFont5);

        Map<String, String> pMap = new HashMap<>();
        String reportDate = inInfo.getString("reportDate");
        pMap.put("reportDate", reportDate);
        pMap.put("monitorname", "循环水");
        String year = reportDate.substring(0, 4);
        int intMonth = Integer.parseInt(reportDate.substring(reportDate.length() - 2));

        List<Map<String, Object>> list = this.dao.query("DUHD62.querySite", pMap);

        XSSFRow headerRow = sheet.createRow(1);

        CellRangeAddress headerRangeAddress = new CellRangeAddress(0, 0, 0, 19);
        sheet.addMergedRegion(headerRangeAddress);
        headerRow.setHeightInPoints(21);//设置行高
        XSSFCell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("（" + year + "年 " + intMonth + "月）");
        headerCell.setCellStyle(conCellStyle1);


        int rowindex = 3;
        int itemindex = 3;
        if (StrUtil.listIsNotNullOrEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> data = list.get(i);

                Map<String, String> map = new HashMap<>();
                String siteId = data.get("siteId").toString();
                map.put("siteId", siteId);
                map.put("reportDate", reportDate);
                List<Map<String, Object>> dateList = this.dao.query("DUHD62.queryDate", map);
                List<Map<String, Object>> list3 = this.dao.query("DUHD62.queryData", map);

                String time = null;

                if (StrUtil.listIsNotNullOrEmpty(dateList)) {
                    for (int j = 0; j < dateList.size(); j++) {
                        Map<String, Object> dateMap = dateList.get(j);

                        time = dateMap.get("actTime").toString();
                        XSSFRow dataRow = sheet.createRow(rowindex);
                        XSSFCell dataCell2 = dataRow.createCell(2);
                        dataCell2.setCellValue(dateMap.get("actTime").toString());
                        dataCell2.setCellStyle(conCellStyle2);


                        if (StrUtil.listIsNotNullOrEmpty(list3)) {
                            for (int l = 0; l < list3.size(); l++) {
                                Map<String, Object> data3 = list3.get(l);

                                if (dateMap.get("actTime").toString().equals(data3.get("actTime").toString())) {

                                    if (data3.get("factorId").toString().equals("001")) {
                                        XSSFCell dataCell = dataRow.createCell(3);
                                        if (data3.get("itemValue") != null) {
                                            dataCell.setCellValue(Double.parseDouble(data3.get("itemValue").toString()));
                                        } else {
                                            dataCell.setCellValue("");
                                        }
                                        dataCell.setCellStyle(conCellStyle3);
                                    } else if (data3.get("factorId").toString().equals("w01003")) {
                                        XSSFCell dataCell = dataRow.createCell(4);
                                        if (data3.get("itemValue") != null) {
                                            dataCell.setCellValue(Double.parseDouble(data3.get("itemValue").toString()));
                                        } else {
                                            dataCell.setCellValue("");
                                        }
                                        dataCell.setCellStyle(conCellStyle4);
                                    } else if (data3.get("factorId").toString().equals("003")) {
                                        XSSFCell dataCell = dataRow.createCell(5);
                                        if (data3.get("itemValue") != null) {
                                            dataCell.setCellValue(Double.parseDouble(data3.get("itemValue").toString()));
                                        } else {
                                            dataCell.setCellValue("");
                                        }
                                        dataCell.setCellStyle(conCellStyle2);
                                    } else if (data3.get("factorId").toString().equals("w01007")) {
                                        XSSFCell dataCell = dataRow.createCell(6);
                                        if (data3.get("itemValue") != null) {
                                            dataCell.setCellValue(Double.parseDouble(data3.get("itemValue").toString()));
                                        } else {
                                            dataCell.setCellValue("");
                                        }
                                        dataCell.setCellStyle(conCellStyle2);
                                    } else if (data3.get("factorId").toString().equals("032")) {
                                        XSSFCell dataCell = dataRow.createCell(7);
                                        if (data3.get("itemValue") != null) {
                                            dataCell.setCellValue(Double.parseDouble(data3.get("itemValue").toString()));
                                        } else {
                                            dataCell.setCellValue("");
                                        }
                                        dataCell.setCellStyle(conCellStyle5);
                                    } else if (data3.get("factorId").toString().equals("w21022")) {
                                        XSSFCell dataCell = dataRow.createCell(8);
                                        if (data3.get("itemValue") != null) {
                                            dataCell.setCellValue(Double.parseDouble(data3.get("itemValue").toString()));
                                        } else {
                                            dataCell.setCellValue("");
                                        }
                                        dataCell.setCellStyle(conCellStyle2);
                                    }
                                }
                            }
                        }

                        XSSFRow dataRow1 = sheet.getRow(rowindex);

                        for (int s = 3; s < 9; s++) {
                            XSSFCell dataCell = dataRow1.getCell(s);
                            if (dataCell == null) {
                                dataCell = dataRow.createCell(s);
                                dataCell.setCellValue("");
                                dataCell.setCellStyle(conCellStyle2);
                            }
                        }

                        rowindex++;
                    }
                }

                XSSFRow limitRow = sheet.createRow(rowindex);
                XSSFCell limitCell = limitRow.createCell(2);
                limitCell.setCellValue("控制标准");
                limitCell.setCellStyle(conCellStyle2);

                List<String> factor = new ArrayList<>();
                factor.add("001");
                factor.add("w01003");
                factor.add("003");
                factor.add("w01007");
                factor.add("032");
                factor.add("w21022");

                for (int j = 0; j < 6; j++) {
                    XSSFCell limitCell1 = limitRow.createCell(j + 3);
                    EntityUtil eu = new EntityUtil();
                    String limit = eu.getHighLimitByDatatime(time, siteId, factor.get(j), dao);
                    limitCell1.setCellValue(limit != null ? limit : "");
                    limitCell1.setCellStyle(conCellStyle3);
                }


                XSSFRow dataRow2 = sheet.getRow(itemindex);
                CellRangeAddress rowRangeAddress = new CellRangeAddress(itemindex, itemindex + dateList.size(), 1, 1);
                sheet.addMergedRegion(rowRangeAddress);
                RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, xb);
                RegionUtil.setBorderLeft(XSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, xb);
                RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, xb);
                RegionUtil.setBorderTop(XSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, xb);
                XSSFCell dataCell = dataRow2.createCell(1);
                dataCell.setCellValue(data.get("siteName").toString());
                dataCell.setCellStyle(conCellStyle2);


                CellRangeAddress rangeAddress = new CellRangeAddress(itemindex, itemindex + dateList.size(), 0, 0);
                sheet.addMergedRegion(rangeAddress);
                RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN, rangeAddress, sheet, xb);
                RegionUtil.setBorderLeft(XSSFCellStyle.BORDER_THIN, rangeAddress, sheet, xb);
                RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN, rangeAddress, sheet, xb);
                RegionUtil.setBorderTop(XSSFCellStyle.BORDER_THIN, rangeAddress, sheet, xb);
                XSSFCell sortCell = dataRow2.createCell(0);
                sortCell.setCellValue(i + 1);
                sortCell.setCellStyle(conCellStyle1);

                itemindex = rowindex + 1;
                rowindex++;
            }
        }

        XSSFRow lastRow = sheet.createRow(itemindex);
        CellRangeAddress lastRangeAddress = new CellRangeAddress(itemindex, itemindex, 0, 8);
        sheet.addMergedRegion(lastRangeAddress);
        XSSFCell lastCell = lastRow.createCell(0);
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        lastCell.setCellValue("制表人：                          审核人：                        打印日期：" + date);
        lastCell.setCellStyle(conCellStyle1);

        //sheet.protectSheet("");
    }


    /**
     * 韶钢固体废弃物管理台账
     * 根据查询条件，查询在线实时数据，将数据保存到Excel文件中
     * 根据模板导出
     *
     * @param wb
     * @param inInfo
     */
    public void saveDataToReport_GTFQW(HSSFWorkbook wb, EiInfo inInfo) {
        HSSFSheet sheet = wb.getSheetAt(0);
        //sheet.removeMergedRegion(2);//取消合并单元格
        //sheet.removeMergedRegion(1);//取消合并单元格


        HSSFDataFormat format = wb.createDataFormat();

        //标题样式
        HSSFCellStyle headerCellStyle = wb.createCellStyle();//单元格样式
        headerCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headerCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headerCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        headerCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headerCellStyle.setDataFormat(format.getFormat("@"));//设置文本格式
        headerCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headerCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        headerCellStyle.setWrapText(true);
        HSSFFont headerFont = wb.createFont();//字体样式
        headerFont.setFontHeightInPoints((short) 16);//字体大小
        headerFont.setFontName("宋体");//字体
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
        headerCellStyle.setFont(headerFont);

        //内容样式
        HSSFCellStyle conCellStyle1 = wb.createCellStyle();
        conCellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        conCellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        conCellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);
        conCellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);
        conCellStyle1.setDataFormat(format.getFormat("@"));
        conCellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        conCellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        conCellStyle1.setWrapText(true);
        HSSFFont conFont1 = wb.createFont();
        conFont1.setFontHeightInPoints((short) 12);
        conFont1.setFontName("宋体");
        conCellStyle1.setFont(conFont1);

        HSSFCellStyle conCellStyle2 = wb.createCellStyle();
        conCellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        conCellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        conCellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        conCellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        conCellStyle2.setDataFormat(format.getFormat("0.0_ "));
        conCellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        conCellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        conCellStyle2.setWrapText(true);
        HSSFFont conFont2 = wb.createFont();
        conFont2.setFontHeightInPoints((short) 12);
        conFont2.setFontName("宋体");
        conCellStyle2.setFont(conFont2);


        Map<String, String> pMap = new HashMap<>();
        String reportDate = inInfo.getString("reportDate");
        pMap.put("reportDate", reportDate);
        List<Map<String, Object>> list = this.dao.query("DUHD62.queryHwaste", pMap);

        String year = reportDate.substring(0, 4);
        int intMonth = Integer.parseInt(reportDate.substring(reportDate.length() - 2));

        HSSFRow headerRow = sheet.createRow(0);

        CellRangeAddress headerRangeAddress = new CellRangeAddress(0, 0, 0, 8);
        sheet.addMergedRegion(headerRangeAddress);
        //合并单元格加边框
        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, headerRangeAddress, sheet, wb);
        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, headerRangeAddress, sheet, wb);
        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, headerRangeAddress, sheet, wb);
        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, headerRangeAddress, sheet, wb);
        headerRow.setHeightInPoints(36);//设置行高
        HSSFCell headerCell = headerRow.createCell(0);
        headerCell.setCellValue(year + "年" + intMonth + "月韶钢固体废弃物管理台账");
        headerCell.setCellStyle(headerCellStyle);

        int rowindex = 4;
        if (StrUtil.listIsNotNullOrEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> data = list.get(i);

                HSSFRow dataRow = null;

                if (i >= 33) {
                    if (sheet.getRow(i + 4) != null) {
                        int lastRowNum = sheet.getLastRowNum();
                        sheet.shiftRows(i + 4, lastRowNum, 1);//12-14行整体下移一行
                    }

                    dataRow = sheet.createRow(rowindex);

                    HSSFCell dataCell = dataRow.createCell(0);
                    dataCell.setCellValue(i);
                    dataCell.setCellStyle(conCellStyle1);

                    HSSFCell dataCell1 = dataRow.createCell(1);
                    dataCell1.setCellValue(data.get("hwasteName").toString());
                    dataCell1.setCellStyle(conCellStyle1);

                    HSSFCell dataCell2 = dataRow.createCell(2);
                    dataCell2.setCellValue(Double.parseDouble(data.get("output").toString()));
                    dataCell2.setCellStyle(conCellStyle2);

                    HSSFCell dataCell3 = dataRow.createCell(3);
                    dataCell3.setCellValue(Double.parseDouble(data.get("outputTotal").toString()));
                    dataCell3.setCellStyle(conCellStyle2);

                    HSSFCell dataCell4 = dataRow.createCell(4);
                    dataCell4.setCellValue(data.get("sourceprocess").toString());
                    dataCell4.setCellStyle(conCellStyle1);

                    HSSFCell dataCell5 = dataRow.createCell(5);
                    dataCell5.setCellValue(Double.parseDouble(data.get("dispose").toString()));
                    dataCell5.setCellStyle(conCellStyle2);

                    HSSFCell dataCell6 = dataRow.createCell(6);
                    dataCell6.setCellValue(Double.parseDouble(data.get("disposeTotal").toString()));
                    dataCell6.setCellStyle(conCellStyle2);

                    HSSFCell dataCell7 = dataRow.createCell(7);
                    dataCell7.setCellValue(Double.parseDouble(data.get("storage").toString()));
                    dataCell7.setCellStyle(conCellStyle2);

                    HSSFCell dataCell8 = dataRow.createCell(8);
                    dataCell8.setCellValue("");
                    dataCell8.setCellStyle(conCellStyle1);

                    HSSFCell dataCell9 = dataRow.createCell(9);
                    dataCell9.setCellValue("");
                    dataCell9.setCellStyle(conCellStyle1);

                    HSSFCell dataCell10 = dataRow.createCell(10);
                    dataCell10.setCellValue(data.get("hwasteType").toString());
                    dataCell10.setCellStyle(conCellStyle1);

                } else {
                    dataRow = sheet.getRow(rowindex);
                    HSSFCell dataCell = dataRow.getCell(0);
                    dataCell.setCellValue(i + 1);
                    dataCell.setCellStyle(conCellStyle1);

                    HSSFCell dataCell1 = dataRow.getCell(1);
                    dataCell1.setCellValue(data.get("hwasteName").toString());
                    dataCell1.setCellStyle(conCellStyle1);

                    HSSFCell dataCell2 = dataRow.getCell(2);
                    if (data.get("hwasteId").toString().equals("HW001") || data.get("hwasteId").toString().equals("HW002")) {
                        dataCell2.setCellValue(data.get("output").toString() + "(个)");
                    } else {
                        dataCell2.setCellValue(Double.parseDouble(data.get("output").toString()));
                    }
                    dataCell2.setCellStyle(conCellStyle2);

                    HSSFCell dataCell3 = dataRow.getCell(3);
                    if (data.get("hwasteId").toString().equals("HW001") || data.get("hwasteId").toString().equals("HW002")) {
                        dataCell3.setCellValue(data.get("outputTotal").toString() + "(个)");
                    } else {
                        dataCell3.setCellValue(Double.parseDouble(data.get("outputTotal").toString()));
                    }
                    dataCell3.setCellStyle(conCellStyle2);

                    HSSFCell dataCell4 = dataRow.getCell(4);
                    dataCell4.setCellValue(data.get("sourceprocess").toString());
                    dataCell4.setCellStyle(conCellStyle1);

                    HSSFCell dataCell5 = dataRow.getCell(5);
                    if (data.get("hwasteId").toString().equals("HW001") || data.get("hwasteId").toString().equals("HW002")) {
                        dataCell5.setCellValue(data.get("dispose").toString() + "(个)");
                    } else {
                        dataCell5.setCellValue(Double.parseDouble(data.get("dispose").toString()));
                    }
                    dataCell5.setCellStyle(conCellStyle2);

                    HSSFCell dataCell6 = dataRow.getCell(6);
                    if (data.get("hwasteId").toString().equals("HW001") || data.get("hwasteId").toString().equals("HW002")) {
                        dataCell6.setCellValue(data.get("disposeTotal").toString() + "(个)");
                    } else {
                        dataCell6.setCellValue(Double.parseDouble(data.get("disposeTotal").toString()));
                    }
                    dataCell6.setCellStyle(conCellStyle2);

                    HSSFCell dataCell7 = dataRow.getCell(7);
                    if (data.get("hwasteId").toString().equals("HW001") || data.get("hwasteId").toString().equals("HW002")) {
                        dataCell7.setCellValue(data.get("storage").toString() + "(个)");
                    } else {
                        dataCell7.setCellValue(Double.parseDouble(data.get("storage").toString()));
                    }
                    dataCell7.setCellStyle(conCellStyle2);

                    HSSFCell dataCell9 = dataRow.getCell(9);
                    dataCell9.setCellValue("");
                    dataCell9.setCellStyle(conCellStyle1);

                    HSSFCell dataCell10 = dataRow.getCell(10);
                    dataCell10.setCellValue(data.get("hwasteType").toString());
                    dataCell10.setCellStyle(conCellStyle1);
                }

                rowindex++;

            }
        }
        //sheet.protectSheet("");
    }

    /**
     * 韶钢环保设施运行月报表
     * 根据查询条件，查询在线实时数据，将数据保存到Excel文件中
     * 根据模板导出
     *
     * @param wb
     * @param inInfo
     */
    public void saveDataToReport_HBSS(HSSFWorkbook wb, EiInfo inInfo) throws ParseException {

        Map<String, String> pMap = new HashMap<>();
        String reportDate = inInfo.getString("reportDate");
        pMap.put("reportDate", reportDate);
        List<Map<String, Object>> factoryList = this.dao.query("DUHD62.queryFactory", pMap);
        if (StrUtil.listIsNotNullOrEmpty(factoryList)) {
            for (int i = 0; i < factoryList.size(); i++) {
                wb.setSheetHidden(i, false);
                HSSFSheet sheet = wb.getSheetAt(i);

                HSSFDataFormat format = wb.createDataFormat();

                //标题样式
                HSSFCellStyle headerCellStyle = wb.createCellStyle();//单元格样式
                headerCellStyle.setDataFormat(format.getFormat("@"));//设置文本格式
                headerCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                headerCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                headerCellStyle.setWrapText(true);
                HSSFFont headerFont = wb.createFont();//字体样式
                headerFont.setFontHeightInPoints((short) 16);//字体大小
                headerFont.setFontName("宋体");//字体
                headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
                headerCellStyle.setFont(headerFont);

                //内容样式
                HSSFCellStyle conCellStyle1 = wb.createCellStyle();
                conCellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                conCellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                conCellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);
                conCellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);
                conCellStyle1.setDataFormat(format.getFormat("@"));
                conCellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                conCellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                conCellStyle1.setWrapText(true);
                HSSFFont conFont1 = wb.createFont();
                conFont1.setFontHeightInPoints((short) 12);
                conFont1.setFontName("宋体");
                conCellStyle1.setFont(conFont1);

                HSSFCellStyle conCellStyle2 = wb.createCellStyle();
                conCellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                conCellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                conCellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
                conCellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
                conCellStyle2.setDataFormat(format.getFormat("0.0_ "));
                conCellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                conCellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                conCellStyle2.setWrapText(true);
                HSSFFont conFont2 = wb.createFont();
                conFont2.setFontHeightInPoints((short) 12);
                conFont2.setFontName("宋体");
                conCellStyle2.setFont(conFont2);

                HSSFCellStyle conCellStyle3 = wb.createCellStyle();
                conCellStyle3.setDataFormat(format.getFormat("@"));
                conCellStyle3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                conCellStyle3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                conCellStyle3.setWrapText(true);
                HSSFFont conFont3 = wb.createFont();
                conFont3.setFontHeightInPoints((short) 12);
                conFont3.setFontName("宋体");
                conCellStyle3.setFont(conFont3);

                HSSFCellStyle conCellStyle4 = wb.createCellStyle();
                conCellStyle4.setDataFormat(format.getFormat("@"));
                conCellStyle4.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                conCellStyle4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                conCellStyle4.setWrapText(true);
                HSSFFont conFont4 = wb.createFont();
                conFont4.setFontHeightInPoints((short) 12);
                conFont4.setFontName("宋体");
                conCellStyle4.setFont(conFont4);

                Map<String, Object> factoryData = factoryList.get(i);

                String year = reportDate.substring(0, 4);
                int intMonth = Integer.parseInt(reportDate.substring(reportDate.length() - 2));

                HSSFRow headerRow = sheet.createRow(0);

                CellRangeAddress headerRangeAddress = new CellRangeAddress(0, 0, 0, 10);
                sheet.addMergedRegion(headerRangeAddress);
                headerRow.setHeightInPoints(40);//设置行高
                HSSFCell headerCell = headerRow.createCell(0);
                headerCell.setCellValue("韶钢" + factoryData.get("departmentName").toString() + "环保设施运行月报表");
                headerCell.setCellStyle(headerCellStyle);

                HSSFRow dateRow = sheet.createRow(1);
                CellRangeAddress dateRangeAddress = new CellRangeAddress(1, 1, 0, 10);
                sheet.addMergedRegion(dateRangeAddress);
                dateRow.setHeightInPoints(20);//设置行高
                HSSFCell dateCell = dateRow.createCell(0);
                dateCell.setCellValue("（" + year + "年 " + intMonth + "月）");
                dateCell.setCellStyle(conCellStyle3);

                HSSFRow deptRow = sheet.createRow(2);
                CellRangeAddress deptRangeAddress = new CellRangeAddress(2, 2, 0, 10);
                sheet.addMergedRegion(deptRangeAddress);
                deptRow.setHeightInPoints(25);//设置行高
                HSSFCell deptCell = deptRow.createCell(0);
                deptCell.setCellValue("    填报单位：" + factoryData.get("departmentName").toString());
                deptCell.setCellStyle(conCellStyle4);

                String departmentId = factoryData.get("departmentId").toString();
                Map<String, String> map = new HashMap<>();
                map.put("reportDate", reportDate);
                map.put("departmentId", departmentId);
                List<Map<String, Object>> dataList = this.dao.query("DUHD62.queryHBSS", map);

                DecimalFormat df = new DecimalFormat("#.0");

                int rowindex = 4;
                int index = 4;
                if (StrUtil.listIsNotNullOrEmpty(dataList)) {
                    for (int j = 0; j < dataList.size(); j++) {
                        Map<String, Object> data = dataList.get(j);

                        String factors = data.get("pollutantname").toString();
                        String mnid = data.get("mnid").toString();

                        List<Map<String, Object>> factorData = getFactorValue(mnid, factors, reportDate);
                        HSSFRow dataRow = null;
                        if (StrUtil.listIsNotNullOrEmpty(factorData)) {
                            for (int l = 0; l < factorData.size(); l++) {
                                Map<String, Object> data2 = factorData.get(l);
                                HSSFRow factorRow = sheet.createRow(rowindex);

                                HSSFCell dataCell5 = factorRow.createCell(5);
                                dataCell5.setCellValue(data2.get("factorName").toString());
                                dataCell5.setCellStyle(conCellStyle1);

                                HSSFCell dataCell6 = factorRow.createCell(6);
                                dataCell6.setCellValue(df.format(Double.parseDouble(data2.get("value").toString())));
                                dataCell6.setCellStyle(conCellStyle1);

                                rowindex++;
                            }
                            dataRow = sheet.getRow(index);
                        } else {
                            rowindex++;
                            dataRow = sheet.createRow(index);

                            HSSFCell dataCell5 = dataRow.createCell(5);
                            dataCell5.setCellValue("");
                            dataCell5.setCellStyle(conCellStyle1);

                            HSSFCell dataCell6 = dataRow.createCell(6);
                            dataCell6.setCellValue("");
                            dataCell6.setCellStyle(conCellStyle1);
                        }

                        Double runTime1 = Double.parseDouble(data.get("itemvalue").toString());
                        Double runTime2 = Double.parseDouble(data.get("hbruntime").toString());

                        CellRangeAddress cellRangeAddress = new CellRangeAddress(index, rowindex - 1, 0, 0);
                        sheet.addMergedRegion(cellRangeAddress);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, cellRangeAddress, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, cellRangeAddress, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, cellRangeAddress, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, cellRangeAddress, sheet, wb);
                        HSSFCell dataCell = dataRow.createCell(0);
                        dataCell.setCellValue(j + 1);
                        dataCell.setCellStyle(conCellStyle1);

                        CellRangeAddress cellRangeAddress1 = new CellRangeAddress(index, rowindex - 1, 1, 1);
                        sheet.addMergedRegion(cellRangeAddress1);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, cellRangeAddress1, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, cellRangeAddress1, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, cellRangeAddress1, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, cellRangeAddress1, sheet, wb);
                        HSSFCell dataCell1 = dataRow.createCell(1);
                        dataCell1.setCellValue(data.get("facilityname").toString());
                        dataCell1.setCellStyle(conCellStyle1);

                        CellRangeAddress cellRangeAddress2 = new CellRangeAddress(index, rowindex - 1, 2, 2);
                        sheet.addMergedRegion(cellRangeAddress2);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, cellRangeAddress2, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, cellRangeAddress2, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, cellRangeAddress2, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, cellRangeAddress2, sheet, wb);
                        HSSFCell dataCell2 = dataRow.createCell(2);
                        dataCell2.setCellValue(runTime1);
                        dataCell2.setCellStyle(conCellStyle2);

                        CellRangeAddress cellRangeAddress3 = new CellRangeAddress(index, rowindex - 1, 3, 3);
                        sheet.addMergedRegion(cellRangeAddress3);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, cellRangeAddress3, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, cellRangeAddress3, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, cellRangeAddress3, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, cellRangeAddress3, sheet, wb);
                        HSSFCell dataCell3 = dataRow.createCell(3);
                        dataCell3.setCellValue(runTime2);
                        dataCell3.setCellStyle(conCellStyle2);

                        CellRangeAddress cellRangeAddress4 = new CellRangeAddress(index, rowindex - 1, 4, 4);
                        sheet.addMergedRegion(cellRangeAddress4);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, cellRangeAddress4, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, cellRangeAddress4, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, cellRangeAddress4, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, cellRangeAddress4, sheet, wb);
                        HSSFCell dataCell4 = dataRow.createCell(4);
                        if (runTime2 == 0) {
                            dataCell4.setCellValue(0);
                        } else {
                            dataCell4.setCellValue(df.format(runTime1 / runTime2 * 100) + "%");
                        }
                        dataCell4.setCellStyle(conCellStyle2);

                        String tableName = "DATATABLE_HOUR_" + year;
                        String siteid = data.get("siteid").toString();
                        int hourNum = getBadData(mnid, siteid, factors, reportDate, tableName);
                        CellRangeAddress cellRangeAddress7 = new CellRangeAddress(index, rowindex - 1, 7, 7);
                        sheet.addMergedRegion(cellRangeAddress7);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, cellRangeAddress7, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, cellRangeAddress7, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, cellRangeAddress7, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, cellRangeAddress7, sheet, wb);
                        HSSFCell dataCell7 = dataRow.createCell(7);
                        dataCell7.setCellValue(hourNum);
                        dataCell7.setCellStyle(conCellStyle1);

                        String tableName2 = "DATATABLE_DAY_" + year;
                        int dayNum = getBadData(mnid, siteid, factors, reportDate, tableName2);
                        CellRangeAddress cellRangeAddress8 = new CellRangeAddress(index, rowindex - 1, 8, 8);
                        sheet.addMergedRegion(cellRangeAddress8);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, cellRangeAddress8, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, cellRangeAddress8, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, cellRangeAddress8, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, cellRangeAddress8, sheet, wb);
                        HSSFCell dataCell8 = dataRow.createCell(8);
                        dataCell8.setCellValue(dayNum);
                        dataCell8.setCellStyle(conCellStyle1);

                        CellRangeAddress cellRangeAddress9 = new CellRangeAddress(index, rowindex - 1, 9, 9);
                        sheet.addMergedRegion(cellRangeAddress9);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, cellRangeAddress9, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, cellRangeAddress9, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, cellRangeAddress9, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, cellRangeAddress9, sheet, wb);
                        HSSFCell dataCell9 = dataRow.createCell(9);
                        dataCell9.setCellValue("");
                        dataCell9.setCellStyle(conCellStyle1);

                        Map<String, Object> data3 = getGoodData(mnid, factors, reportDate);
                        CellRangeAddress cellRangeAddress10 = new CellRangeAddress(index, rowindex - 1, 10, 10);
                        sheet.addMergedRegion(cellRangeAddress10);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, cellRangeAddress10, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, cellRangeAddress10, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, cellRangeAddress10, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, cellRangeAddress10, sheet, wb);
                        HSSFCell dataCell10 = dataRow.createCell(10);
                        if (Double.parseDouble(data3.get("total").toString()) == 0) {
                            dataCell10.setCellValue("");
                        } else {
                            dataCell10.setCellValue(df.format(Double.parseDouble(data3.get("num").toString()) / Double.parseDouble(data3.get("total").toString()) * 100) + "%");

                        }
                        dataCell10.setCellStyle(conCellStyle1);

                        index = rowindex;
                    }
                }

                HSSFRow lastRow = sheet.createRow(rowindex);
                CellRangeAddress lastRangeAddress = new CellRangeAddress(rowindex, rowindex, 0, 10);
                sheet.addMergedRegion(lastRangeAddress);
                lastRow.setHeightInPoints(28);//设置行高
                HSSFCell lastCell = lastRow.createCell(0);
                lastCell.setCellValue(" 审核：                                              填表人：                                            填报日期：");
                lastCell.setCellStyle(conCellStyle3);

                //sheet.protectSheet("");
            }
        }
    }

    /**
     * 降尘量监测统计表格
     * 根据查询条件，查询在线实时数据，将数据保存到Excel文件中
     * 根据模板导出
     *
     * @param wb
     * @param inInfo
     */
    public void saveDataToReport_JCLJC(HSSFWorkbook wb, EiInfo inInfo) {
        HSSFSheet sheet = wb.getSheetAt(0);
        //sheet.removeMergedRegion(2);//取消合并单元格
        //sheet.removeMergedRegion(1);//取消合并单元格


        HSSFDataFormat format = wb.createDataFormat();

        //标题样式
        HSSFCellStyle headerCellStyle = wb.createCellStyle();//单元格样式
        headerCellStyle.setDataFormat(format.getFormat("@"));//设置文本格式
        headerCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headerCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        headerCellStyle.setWrapText(true);
        HSSFFont headerFont = wb.createFont();//字体样式
        headerFont.setFontHeightInPoints((short) 14);//字体大小
        headerFont.setFontName("Times New Roman");//字体
        headerCellStyle.setFont(headerFont);

        //内容样式
        HSSFCellStyle conCellStyle1 = wb.createCellStyle();
        conCellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        conCellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        conCellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);
        conCellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);
        conCellStyle1.setDataFormat(format.getFormat("0.00_ "));
        conCellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        conCellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        conCellStyle1.setWrapText(true);
        HSSFFont conFont1 = wb.createFont();
        conFont1.setFontHeightInPoints((short) 12);
        conFont1.setFontName("Times New Roman");
        conCellStyle1.setFont(conFont1);


        Map<String, String> pMap = new HashMap<>();
        String reportDate = inInfo.getString("reportDate");
        pMap.put("reportDate", reportDate);
        List<Map<String, Object>> list = this.dao.query("DUHD62.queryJCL", pMap);

        HSSFRow headerRow = sheet.createRow(0);
        CellRangeAddress headerRangeAddress = new CellRangeAddress(0, 0, 3, 10);
        sheet.addMergedRegion(headerRangeAddress);
        headerRow.setHeightInPoints(27);//设置行高
        HSSFCell headerCell = headerRow.createCell(3);
        headerCell.setCellValue(reportDate + "年厂区月降尘监测统计结果");
        headerCell.setCellStyle(headerCellStyle);

        HSSFRow headerRow2 = sheet.createRow(19);
        CellRangeAddress headerRangeAddress2 = new CellRangeAddress(19, 19, 3, 10);
        sheet.addMergedRegion(headerRangeAddress2);
        headerRow2.setHeightInPoints(37);//设置行高
        HSSFCell headerCell2 = headerRow2.createCell(3);
        headerCell2.setCellValue(reportDate + "年厂区重点区域月降尘监测统计结果");
        headerCell2.setCellStyle(headerCellStyle);

        List<String> siteName = new ArrayList<>();
        siteName.add("信息技术中心办公楼");
        siteName.add("焦化综合办公楼");
        siteName.add("厂区中心食堂");
        siteName.add("棒材办公楼");
        siteName.add("高线办公楼");
        siteName.add("中板办公楼");
        siteName.add("安全环保部办公楼");
        siteName.add("一炼钢污泥压榨间");
        siteName.add("烧结200系统主控楼");
        siteName.add("烧结220系统主控楼");
        siteName.add("新区料场主控楼");
        siteName.add("三化中心化验楼");
        siteName.add("矿山气烧窑平台");

        List<String> siteName2 = new ArrayList<>();
        siteName2.add("焦化综合办公楼");
        siteName2.add("厂区中心食堂");
        siteName2.add("烧结200系统主控楼");
        siteName2.add("烧结220系统主控楼");
        siteName2.add("一炼钢污泥压榨间");
        siteName2.add("矿山气烧窑平台");

        if (StrUtil.listIsNotNullOrEmpty(list)) {
            for (int i = 0; i < siteName.size(); i++) {
                for (int j = 0; j < list.size(); j++) {
                    Map<String, Object> data = list.get(j);
                    if (siteName.get(i).equals(data.get("SITENAME").toString())) {
                        HSSFRow dataRow = sheet.getRow(3 + i);

                        if (Double.parseDouble(data.get("month1").toString()) != 0) {
                            HSSFCell dataCell = dataRow.getCell(2);
                            dataCell.setCellValue(Double.parseDouble(data.get("month1").toString()));
                            dataCell.setCellStyle(conCellStyle1);
                        }

                        if (Double.parseDouble(data.get("month2").toString()) != 0) {
                            HSSFCell dataCell = dataRow.getCell(3);
                            dataCell.setCellValue(Double.parseDouble(data.get("month2").toString()));
                            dataCell.setCellStyle(conCellStyle1);
                        }

                        if (Double.parseDouble(data.get("month3").toString()) != 0) {
                            HSSFCell dataCell = dataRow.getCell(4);
                            dataCell.setCellValue(Double.parseDouble(data.get("month3").toString()));
                            dataCell.setCellStyle(conCellStyle1);
                        }

                        if (Double.parseDouble(data.get("month4").toString()) != 0) {
                            HSSFCell dataCell = dataRow.getCell(5);
                            dataCell.setCellValue(Double.parseDouble(data.get("month4").toString()));
                            dataCell.setCellStyle(conCellStyle1);
                        }

                        if (Double.parseDouble(data.get("month5").toString()) != 0) {
                            HSSFCell dataCell = dataRow.getCell(6);
                            dataCell.setCellValue(Double.parseDouble(data.get("month5").toString()));
                            dataCell.setCellStyle(conCellStyle1);
                        }

                        if (Double.parseDouble(data.get("month6").toString()) != 0) {
                            HSSFCell dataCell = dataRow.getCell(7);
                            dataCell.setCellValue(Double.parseDouble(data.get("month6").toString()));
                            dataCell.setCellStyle(conCellStyle1);
                        }

                        if (Double.parseDouble(data.get("month7").toString()) != 0) {
                            HSSFCell dataCell = dataRow.getCell(8);
                            dataCell.setCellValue(Double.parseDouble(data.get("month7").toString()));
                            dataCell.setCellStyle(conCellStyle1);
                        }

                        if (Double.parseDouble(data.get("month8").toString()) != 0) {
                            HSSFCell dataCell = dataRow.getCell(9);
                            dataCell.setCellValue(Double.parseDouble(data.get("month8").toString()));
                            dataCell.setCellStyle(conCellStyle1);
                        }

                        if (Double.parseDouble(data.get("month9").toString()) != 0) {
                            HSSFCell dataCell = dataRow.getCell(10);
                            dataCell.setCellValue(Double.parseDouble(data.get("month9").toString()));
                            dataCell.setCellStyle(conCellStyle1);
                        }

                        if (Double.parseDouble(data.get("month10").toString()) != 0) {
                            HSSFCell dataCell = dataRow.getCell(11);
                            dataCell.setCellValue(Double.parseDouble(data.get("month10").toString()));
                            dataCell.setCellStyle(conCellStyle1);
                        }

                        if (Double.parseDouble(data.get("month11").toString()) != 0) {
                            HSSFCell dataCell = dataRow.getCell(12);
                            dataCell.setCellValue(Double.parseDouble(data.get("month11").toString()));
                            dataCell.setCellStyle(conCellStyle1);
                        }

                        if (Double.parseDouble(data.get("month12").toString()) != 0) {
                            HSSFCell dataCell = dataRow.getCell(13);
                            dataCell.setCellValue(Double.parseDouble(data.get("month12").toString()));
                            dataCell.setCellStyle(conCellStyle1);
                        }
                    }

                }
            }

            for (int i = 0; i < siteName2.size(); i++) {
                for (int j = 0; j < list.size(); j++) {
                    Map<String, Object> data = list.get(j);
                    if (siteName2.get(i).equals(data.get("SITENAME").toString())) {
                        HSSFRow dataRow = sheet.getRow(22 + i);

                        if (Double.parseDouble(data.get("month1").toString()) != 0) {
                            HSSFCell dataCell = dataRow.getCell(2);
                            dataCell.setCellValue(Double.parseDouble(data.get("month1").toString()));
                            dataCell.setCellStyle(conCellStyle1);
                        }

                        if (Double.parseDouble(data.get("month2").toString()) != 0) {
                            HSSFCell dataCell = dataRow.getCell(3);
                            dataCell.setCellValue(Double.parseDouble(data.get("month2").toString()));
                            dataCell.setCellStyle(conCellStyle1);
                        }

                        if (Double.parseDouble(data.get("month3").toString()) != 0) {
                            HSSFCell dataCell = dataRow.getCell(4);
                            dataCell.setCellValue(Double.parseDouble(data.get("month3").toString()));
                            dataCell.setCellStyle(conCellStyle1);
                        }

                        if (Double.parseDouble(data.get("month4").toString()) != 0) {
                            HSSFCell dataCell = dataRow.getCell(5);
                            dataCell.setCellValue(Double.parseDouble(data.get("month4").toString()));
                            dataCell.setCellStyle(conCellStyle1);
                        }

                        if (Double.parseDouble(data.get("month5").toString()) != 0) {
                            HSSFCell dataCell = dataRow.getCell(6);
                            dataCell.setCellValue(Double.parseDouble(data.get("month5").toString()));
                            dataCell.setCellStyle(conCellStyle1);
                        }

                        if (Double.parseDouble(data.get("month6").toString()) != 0) {
                            HSSFCell dataCell = dataRow.getCell(7);
                            dataCell.setCellValue(Double.parseDouble(data.get("month6").toString()));
                            dataCell.setCellStyle(conCellStyle1);
                        }

                        if (Double.parseDouble(data.get("month7").toString()) != 0) {
                            HSSFCell dataCell = dataRow.getCell(8);
                            dataCell.setCellValue(Double.parseDouble(data.get("month7").toString()));
                            dataCell.setCellStyle(conCellStyle1);
                        }

                        if (Double.parseDouble(data.get("month8").toString()) != 0) {
                            HSSFCell dataCell = dataRow.getCell(9);
                            dataCell.setCellValue(Double.parseDouble(data.get("month8").toString()));
                            dataCell.setCellStyle(conCellStyle1);
                        }

                        if (Double.parseDouble(data.get("month9").toString()) != 0) {
                            HSSFCell dataCell = dataRow.getCell(10);
                            dataCell.setCellValue(Double.parseDouble(data.get("month9").toString()));
                            dataCell.setCellStyle(conCellStyle1);
                        }

                        if (Double.parseDouble(data.get("month10").toString()) != 0) {
                            HSSFCell dataCell = dataRow.getCell(11);
                            dataCell.setCellValue(Double.parseDouble(data.get("month10").toString()));
                            dataCell.setCellStyle(conCellStyle1);
                        }

                        if (Double.parseDouble(data.get("month11").toString()) != 0) {
                            HSSFCell dataCell = dataRow.getCell(12);
                            dataCell.setCellValue(Double.parseDouble(data.get("month11").toString()));
                            dataCell.setCellStyle(conCellStyle1);
                        }

                        if (Double.parseDouble(data.get("month12").toString()) != 0) {
                            HSSFCell dataCell = dataRow.getCell(13);
                            dataCell.setCellValue(Double.parseDouble(data.get("month12").toString()));
                            dataCell.setCellStyle(conCellStyle1);
                        }
                    }

                }
            }
        }

        //sheet.protectSheet("");
    }

    /**
     * 韶钢污染物排放量统计表
     * 根据查询条件，查询在线实时数据，将数据保存到Excel文件中
     * 根据模板导出
     *
     * @param wb
     * @param inInfo
     */
    public void saveDataToReport_WRWPFL(HSSFWorkbook wb, EiInfo inInfo) {
        HSSFSheet sheet = wb.getSheetAt(0);
        saveSheet_ZB(sheet, wb, inInfo);

        HSSFSheet sheet1 = wb.getSheetAt(1);
        saveSheet_HD(sheet1, wb, inInfo);

        HSSFSheet sheet2 = wb.getSheetAt(2);
        saveSheet_LJ(sheet2, wb, inInfo);

        HSSFSheet sheet3 = wb.getSheetAt(3);
        saveSheet_GT(sheet3, wb, inInfo);

        HSSFSheet sheet4 = wb.getSheetAt(4);
        saveSheet_SWRW(sheet4, wb, inInfo);
    }

    /**
     * 韶钢污染物排放量统计表--总表
     */
    public void saveSheet_ZB(HSSFSheet sheet, HSSFWorkbook wb, EiInfo inInfo) {

        String reportDate = inInfo.getString("reportDate");

        HSSFDataFormat format = wb.createDataFormat();

        //内容样式
        HSSFCellStyle conCellStyle = wb.createCellStyle();
        conCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        conCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        conCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        conCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        conCellStyle.setDataFormat(format.getFormat("0.00_ "));
        conCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        conCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        conCellStyle.setWrapText(true);
        HSSFFont conFont = wb.createFont();
        conFont.setFontHeightInPoints((short) 12);
        conFont.setFontName("宋体");
        conCellStyle.setFont(conFont);

        List<String> factor = new ArrayList();
        factor.add("01");
        factor.add("02");
        factor.add("03");
        factor.add("011");
        factor.add("060");


        int rowIndex = 4;
        for (int i = 0; i < factor.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("reportDate", reportDate);
            map.put("factorId", factor.get(i));
            List<Map<String, Object>> dataList = this.dao.query("DUHD62.queryZL", map);

            if (StrUtil.listIsNotNullOrEmpty(dataList)) {
                for (int j = 0; j < dataList.size(); j++) {
                    Map<String, Object> data = dataList.get(j);
                    if (data.get("factorId").toString().equals("01")) {
                        if (data.get("youzuzhi").toString().equals("1")) {
                            HSSFRow dataRow = sheet.getRow(4);

                            if (data.get("clock").toString().equals("01")) {
                                HSSFCell dataCell = dataRow.getCell(3);
                                dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                                dataCell.setCellStyle(conCellStyle);
                            }

                            if (data.get("clock").toString().equals("02")) {
                                HSSFCell dataCell = dataRow.getCell(5);
                                dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                                dataCell.setCellStyle(conCellStyle);
                            }

                            if (data.get("clock").toString().equals("03")) {
                                HSSFCell dataCell = dataRow.getCell(7);
                                dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                                dataCell.setCellStyle(conCellStyle);
                            }

                            if (data.get("clock").toString().equals("04")) {
                                HSSFCell dataCell = dataRow.getCell(9);
                                dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                                dataCell.setCellStyle(conCellStyle);
                            }

                            if (data.get("clock").toString().equals("05")) {
                                HSSFCell dataCell = dataRow.getCell(11);
                                dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                                dataCell.setCellStyle(conCellStyle);
                            }

                            if (data.get("clock").toString().equals("06")) {
                                HSSFCell dataCell = dataRow.getCell(13);
                                dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                                dataCell.setCellStyle(conCellStyle);
                            }

                            if (data.get("clock").toString().equals("07")) {
                                HSSFCell dataCell = dataRow.getCell(15);
                                dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                                dataCell.setCellStyle(conCellStyle);
                            }

                            if (data.get("clock").toString().equals("08")) {
                                HSSFCell dataCell = dataRow.getCell(17);
                                dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                                dataCell.setCellStyle(conCellStyle);
                            }

                            if (data.get("clock").toString().equals("09")) {
                                HSSFCell dataCell = dataRow.getCell(19);
                                dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                                dataCell.setCellStyle(conCellStyle);
                            }

                            if (data.get("clock").toString().equals("10")) {
                                HSSFCell dataCell = dataRow.getCell(21);
                                dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                                dataCell.setCellStyle(conCellStyle);
                            }

                            if (data.get("clock").toString().equals("11")) {
                                HSSFCell dataCell = dataRow.getCell(23);
                                dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                                dataCell.setCellStyle(conCellStyle);
                            }

                            if (data.get("clock").toString().equals("12")) {
                                HSSFCell dataCell = dataRow.getCell(25);
                                dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                                dataCell.setCellStyle(conCellStyle);
                            }

                        } else {
                            HSSFRow dataRow = sheet.getRow(3);

                            if (data.get("clock").toString().equals("01")) {
                                HSSFCell dataCell = dataRow.getCell(3);
                                dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                                dataCell.setCellStyle(conCellStyle);
                            }

                            if (data.get("clock").toString().equals("02")) {
                                HSSFCell dataCell = dataRow.getCell(5);
                                dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                                dataCell.setCellStyle(conCellStyle);
                            }

                            if (data.get("clock").toString().equals("03")) {
                                HSSFCell dataCell = dataRow.getCell(7);
                                dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                                dataCell.setCellStyle(conCellStyle);
                            }

                            if (data.get("clock").toString().equals("04")) {
                                HSSFCell dataCell = dataRow.getCell(9);
                                dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                                dataCell.setCellStyle(conCellStyle);
                            }

                            if (data.get("clock").toString().equals("05")) {
                                HSSFCell dataCell = dataRow.getCell(11);
                                dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                                dataCell.setCellStyle(conCellStyle);
                            }

                            if (data.get("clock").toString().equals("06")) {
                                HSSFCell dataCell = dataRow.getCell(13);
                                dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                                dataCell.setCellStyle(conCellStyle);
                            }

                            if (data.get("clock").toString().equals("07")) {
                                HSSFCell dataCell = dataRow.getCell(15);
                                dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                                dataCell.setCellStyle(conCellStyle);
                            }

                            if (data.get("clock").toString().equals("08")) {
                                HSSFCell dataCell = dataRow.getCell(17);
                                dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                                dataCell.setCellStyle(conCellStyle);
                            }

                            if (data.get("clock").toString().equals("09")) {
                                HSSFCell dataCell = dataRow.getCell(19);
                                dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                                dataCell.setCellStyle(conCellStyle);
                            }

                            if (data.get("clock").toString().equals("10")) {
                                HSSFCell dataCell = dataRow.getCell(21);
                                dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                                dataCell.setCellStyle(conCellStyle);
                            }

                            if (data.get("clock").toString().equals("11")) {
                                HSSFCell dataCell = dataRow.getCell(23);
                                dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                                dataCell.setCellStyle(conCellStyle);
                            }

                            if (data.get("clock").toString().equals("12")) {
                                HSSFCell dataCell = dataRow.getCell(25);
                                dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                                dataCell.setCellStyle(conCellStyle);
                            }
                        }
                    } else {
                        HSSFRow dataRow = sheet.getRow(rowIndex);

                        if (data.get("clock").toString().equals("01")) {
                            HSSFCell dataCell = dataRow.getCell(2);
                            dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                            dataCell.setCellStyle(conCellStyle);
                        }

                        if (data.get("clock").toString().equals("02")) {
                            HSSFCell dataCell = dataRow.getCell(4);
                            dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                            dataCell.setCellStyle(conCellStyle);
                        }

                        if (data.get("clock").toString().equals("03")) {
                            HSSFCell dataCell = dataRow.getCell(6);
                            dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                            dataCell.setCellStyle(conCellStyle);
                        }

                        if (data.get("clock").toString().equals("04")) {
                            HSSFCell dataCell = dataRow.getCell(8);
                            dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                            dataCell.setCellStyle(conCellStyle);
                        }

                        if (data.get("clock").toString().equals("05")) {
                            HSSFCell dataCell = dataRow.getCell(10);
                            dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                            dataCell.setCellStyle(conCellStyle);
                        }

                        if (data.get("clock").toString().equals("06")) {
                            HSSFCell dataCell = dataRow.getCell(12);
                            dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                            dataCell.setCellStyle(conCellStyle);
                        }

                        if (data.get("clock").toString().equals("07")) {
                            HSSFCell dataCell = dataRow.getCell(14);
                            dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                            dataCell.setCellStyle(conCellStyle);
                        }

                        if (data.get("clock").toString().equals("08")) {
                            HSSFCell dataCell = dataRow.getCell(16);
                            dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                            dataCell.setCellStyle(conCellStyle);
                        }

                        if (data.get("clock").toString().equals("09")) {
                            HSSFCell dataCell = dataRow.getCell(18);
                            dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                            dataCell.setCellStyle(conCellStyle);
                        }

                        if (data.get("clock").toString().equals("10")) {
                            HSSFCell dataCell = dataRow.getCell(20);
                            dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                            dataCell.setCellStyle(conCellStyle);
                        }

                        if (data.get("clock").toString().equals("11")) {
                            HSSFCell dataCell = dataRow.getCell(22);
                            dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                            dataCell.setCellStyle(conCellStyle);
                        }

                        if (data.get("clock").toString().equals("12")) {
                            HSSFCell dataCell = dataRow.getCell(24);
                            dataCell.setCellValue(Double.parseDouble(data.get("finalValue").toString()));
                            dataCell.setCellStyle(conCellStyle);
                        }
                    }
                }
            }
            rowIndex++;
        }

        //sheet.protectSheet("");
    }

    /**
     * 韶钢污染物排放量统计表--火电
     */
    public void saveSheet_HD(HSSFSheet sheet, HSSFWorkbook wb, EiInfo inInfo) {
        String reportDate = inInfo.getString("reportDate");

        HSSFDataFormat format = wb.createDataFormat();

        //内容样式
        HSSFCellStyle conCellStyle = wb.createCellStyle();
        conCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        conCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        conCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        conCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        conCellStyle.setDataFormat(format.getFormat("0.00_ "));
        conCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        conCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        conCellStyle.setWrapText(true);
        HSSFFont conFont = wb.createFont();
        conFont.setFontHeightInPoints((short) 10);
        conFont.setFontName("宋体");
        conCellStyle.setFont(conFont);

        HSSFCellStyle conCellStyle2 = wb.createCellStyle();
        conCellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        conCellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        conCellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        conCellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        conCellStyle2.setDataFormat(format.getFormat("@"));
        conCellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        conCellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        conCellStyle2.setWrapText(true);
        HSSFFont conFont2 = wb.createFont();
        conFont2.setFontHeightInPoints((short) 12);
        conFont2.setFontName("黑体");
        conCellStyle2.setFont(conFont2);

        Map<String, String> pMap = new HashMap<>();
        pMap.put("reportDate", reportDate);
        pMap.put("departId", "DEP00008");
        pMap.put("monitorid", "01");

        List<Map<String, Object>> list = this.dao.query("DUHD62.queryHdDischargePort", pMap);

        int rowIndex = 5;
        int itemIndex = 5;
        if (StrUtil.listIsNotNullOrEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> dischargePort = list.get(i);
                dischargePort.put("reportDate", reportDate);

                List<Map<String, Object>> factorList = this.dao.query("DUHD62.queryTotalFactor", dischargePort);
                List<Map<String, Object>> dataList = this.dao.query("DUHD62.queryHdData", dischargePort);


                if (StrUtil.listIsNotNullOrEmpty(factorList)) {
                    for (int j = 0; j < factorList.size(); j++) {
                        Map<String, Object> factorMap = factorList.get(j);
                        String factorName = factorMap.get("factorName").toString();

                        HSSFRow dataRow = sheet.createRow(rowIndex);

                        HSSFCell dataCell = dataRow.createCell(2);
                        dataCell.setCellValue(factorName);
                        dataCell.setCellStyle(conCellStyle);

                        for (int k = 3; k < 51; k++) {
                            HSSFCell cell = dataRow.createCell(k);
                            cell.setCellValue("");
                            cell.setCellStyle(conCellStyle);
                        }

                        if (StrUtil.listIsNotNullOrEmpty(dataList)) {
                            for (int l = 0; l < dataList.size(); l++) {
                                Map<String, Object> data = dataList.get(l);
                                String factorName2 = data.get("factorname").toString();
                                if (factorName.equals(factorName2)) {

                                    if (data.get("clock").toString().equals("01")) {
                                        HSSFCell dataCell3 = dataRow.getCell(5);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(6);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("02")) {
                                        HSSFCell dataCell3 = dataRow.getCell(9);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(10);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("03")) {
                                        HSSFCell dataCell3 = dataRow.getCell(13);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(14);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("04")) {
                                        HSSFCell dataCell3 = dataRow.getCell(17);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(18);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("05")) {
                                        HSSFCell dataCell3 = dataRow.getCell(21);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(22);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("06")) {
                                        HSSFCell dataCell3 = dataRow.getCell(25);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(26);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("07")) {
                                        HSSFCell dataCell3 = dataRow.getCell(29);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(30);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("08")) {
                                        HSSFCell dataCell3 = dataRow.getCell(33);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(34);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("09")) {
                                        HSSFCell dataCell3 = dataRow.getCell(37);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(38);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("10")) {
                                        HSSFCell dataCell3 = dataRow.getCell(41);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(42);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("11")) {
                                        HSSFCell dataCell3 = dataRow.getCell(45);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(46);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("12")) {
                                        HSSFCell dataCell3 = dataRow.getCell(49);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(50);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }
                                }
                            }
                        }

                        HSSFCell cell = dataRow.createCell(51);
                        int index = rowIndex + 1;
                        cell.setCellFormula("SUM(G" + index + ",K" + index + ",O" + index + ",S" + index + ",W" +
                                index + ",AA" + index + ",AE" + index + ",AI" + index + ",AM" + index + ",AQ" +
                                index + ",AU" + index + ",AY" + index + ")");
                        cell.setCellStyle(conCellStyle);

                        rowIndex++;
                    }

                    HSSFRow dataRow = sheet.getRow(itemIndex);
                    for (int j = 0; j < dataList.size(); j++) {
                        Map<String, Object> data = dataList.get(j);


                        CellRangeAddress rowRangeAddress = new CellRangeAddress(itemIndex, rowIndex - 1, 3, 3);
                        sheet.addMergedRegion(rowRangeAddress);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);

                        CellRangeAddress rowRangeAddress2 = new CellRangeAddress(itemIndex, rowIndex - 1, 4, 4);
                        sheet.addMergedRegion(rowRangeAddress2);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);

                        if (data.get("clock").toString().equals("01")) {
                            HSSFCell dataCell1 = dataRow.getCell(3);
                            dataCell1.setCellValue(Double.parseDouble(data.get("runTime").toString()));
                            dataCell1.setCellStyle(conCellStyle);

                            HSSFCell dataCell2 = dataRow.getCell(4);
                            dataCell2.setCellValue(Double.parseDouble(data.get("avgfeiqi").toString()));
                            dataCell2.setCellStyle(conCellStyle);
                        }


                        CellRangeAddress rowRangeAddress3 = new CellRangeAddress(itemIndex, rowIndex - 1, 7, 7);
                        sheet.addMergedRegion(rowRangeAddress3);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress3, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress3, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress3, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress3, sheet, wb);

                        CellRangeAddress rowRangeAddress4 = new CellRangeAddress(itemIndex, rowIndex - 1, 8, 8);
                        sheet.addMergedRegion(rowRangeAddress4);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress4, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress4, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress4, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress4, sheet, wb);

                        if (data.get("clock").toString().equals("02")) {
                            HSSFCell dataCell1 = dataRow.getCell(7);
                            dataCell1.setCellValue(Double.parseDouble(data.get("runTime").toString()));
                            dataCell1.setCellStyle(conCellStyle);

                            HSSFCell dataCell2 = dataRow.getCell(8);
                            dataCell2.setCellValue(Double.parseDouble(data.get("avgfeiqi").toString()));
                            dataCell2.setCellStyle(conCellStyle);


                        }


                        CellRangeAddress rowRangeAddress5 = new CellRangeAddress(itemIndex, rowIndex - 1, 11, 11);
                        sheet.addMergedRegion(rowRangeAddress5);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress5, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress5, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress5, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress5, sheet, wb);

                        CellRangeAddress rowRangeAddress6 = new CellRangeAddress(itemIndex, rowIndex - 1, 12, 12);
                        sheet.addMergedRegion(rowRangeAddress6);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress6, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress6, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress6, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress6, sheet, wb);
                        if (data.get("clock").toString().equals("03")) {
                            HSSFCell dataCell1 = dataRow.getCell(11);
                            dataCell1.setCellValue(Double.parseDouble(data.get("runTime").toString()));
                            dataCell1.setCellStyle(conCellStyle);

                            HSSFCell dataCell2 = dataRow.getCell(12);
                            dataCell2.setCellValue(Double.parseDouble(data.get("avgfeiqi").toString()));
                            dataCell2.setCellStyle(conCellStyle);


                        }


                        CellRangeAddress rowRangeAddress7 = new CellRangeAddress(itemIndex, rowIndex - 1, 15, 15);
                        sheet.addMergedRegion(rowRangeAddress7);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress7, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress7, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress7, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress7, sheet, wb);

                        CellRangeAddress rowRangeAddress8 = new CellRangeAddress(itemIndex, rowIndex - 1, 16, 16);
                        sheet.addMergedRegion(rowRangeAddress8);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress8, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress8, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress8, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress8, sheet, wb);
                        if (data.get("clock").toString().equals("04")) {
                            HSSFCell dataCell1 = dataRow.getCell(15);
                            dataCell1.setCellValue(Double.parseDouble(data.get("runTime").toString()));
                            dataCell1.setCellStyle(conCellStyle);

                            HSSFCell dataCell2 = dataRow.getCell(16);
                            dataCell2.setCellValue(Double.parseDouble(data.get("avgfeiqi").toString()));
                            dataCell2.setCellStyle(conCellStyle);


                        }


                        CellRangeAddress rowRangeAddress9 = new CellRangeAddress(itemIndex, rowIndex - 1, 19, 19);
                        sheet.addMergedRegion(rowRangeAddress9);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress9, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress9, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress9, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress9, sheet, wb);

                        CellRangeAddress rowRangeAddress10 = new CellRangeAddress(itemIndex, rowIndex - 1, 20, 20);
                        sheet.addMergedRegion(rowRangeAddress10);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress10, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress10, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress10, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress10, sheet, wb);
                        if (data.get("clock").toString().equals("05")) {
                            HSSFCell dataCell1 = dataRow.getCell(19);
                            dataCell1.setCellValue(Double.parseDouble(data.get("runTime").toString()));
                            dataCell1.setCellStyle(conCellStyle);

                            HSSFCell dataCell2 = dataRow.getCell(20);
                            dataCell2.setCellValue(Double.parseDouble(data.get("avgfeiqi").toString()));
                            dataCell2.setCellStyle(conCellStyle);


                        }


                        CellRangeAddress rowRangeAddress11 = new CellRangeAddress(itemIndex, rowIndex - 1, 23, 23);
                        sheet.addMergedRegion(rowRangeAddress11);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress11, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress11, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress11, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress11, sheet, wb);

                        CellRangeAddress rowRangeAddress12 = new CellRangeAddress(itemIndex, rowIndex - 1, 24, 24);
                        sheet.addMergedRegion(rowRangeAddress12);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress12, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress12, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress12, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress12, sheet, wb);
                        if (data.get("clock").toString().equals("06")) {
                            HSSFCell dataCell1 = dataRow.getCell(23);
                            dataCell1.setCellValue(Double.parseDouble(data.get("runTime").toString()));
                            dataCell1.setCellStyle(conCellStyle);

                            HSSFCell dataCell2 = dataRow.getCell(24);
                            dataCell2.setCellValue(Double.parseDouble(data.get("avgfeiqi").toString()));
                            dataCell2.setCellStyle(conCellStyle);


                        }


                        CellRangeAddress rowRangeAddress13 = new CellRangeAddress(itemIndex, rowIndex - 1, 27, 27);
                        sheet.addMergedRegion(rowRangeAddress13);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress13, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress13, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress13, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress13, sheet, wb);

                        CellRangeAddress rowRangeAddress14 = new CellRangeAddress(itemIndex, rowIndex - 1, 28, 28);
                        sheet.addMergedRegion(rowRangeAddress14);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress14, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress14, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress14, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress14, sheet, wb);
                        if (data.get("clock").toString().equals("07")) {
                            HSSFCell dataCell1 = dataRow.getCell(27);
                            dataCell1.setCellValue(Double.parseDouble(data.get("runTime").toString()));
                            dataCell1.setCellStyle(conCellStyle);

                            HSSFCell dataCell2 = dataRow.getCell(28);
                            dataCell2.setCellValue(Double.parseDouble(data.get("avgfeiqi").toString()));
                            dataCell2.setCellStyle(conCellStyle);


                        }


                        CellRangeAddress rowRangeAddress15 = new CellRangeAddress(itemIndex, rowIndex - 1, 31, 31);
                        sheet.addMergedRegion(rowRangeAddress15);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress15, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress15, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress15, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress15, sheet, wb);

                        CellRangeAddress rowRangeAddress16 = new CellRangeAddress(itemIndex, rowIndex - 1, 32, 32);
                        sheet.addMergedRegion(rowRangeAddress16);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress16, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress16, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress16, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress16, sheet, wb);
                        if (data.get("clock").toString().equals("08")) {
                            HSSFCell dataCell1 = dataRow.getCell(31);
                            dataCell1.setCellValue(Double.parseDouble(data.get("runTime").toString()));
                            dataCell1.setCellStyle(conCellStyle);

                            HSSFCell dataCell2 = dataRow.getCell(32);
                            dataCell2.setCellValue(Double.parseDouble(data.get("avgfeiqi").toString()));
                            dataCell2.setCellStyle(conCellStyle);


                        }


                        CellRangeAddress rowRangeAddress17 = new CellRangeAddress(itemIndex, rowIndex - 1, 35, 35);
                        sheet.addMergedRegion(rowRangeAddress17);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress17, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress17, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress17, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress17, sheet, wb);

                        CellRangeAddress rowRangeAddress18 = new CellRangeAddress(itemIndex, rowIndex - 1, 36, 36);
                        sheet.addMergedRegion(rowRangeAddress18);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress18, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress18, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress18, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress18, sheet, wb);
                        if (data.get("clock").toString().equals("09")) {
                            HSSFCell dataCell1 = dataRow.getCell(35);
                            dataCell1.setCellValue(Double.parseDouble(data.get("runTime").toString()));
                            dataCell1.setCellStyle(conCellStyle);

                            HSSFCell dataCell2 = dataRow.getCell(36);
                            dataCell2.setCellValue(Double.parseDouble(data.get("avgfeiqi").toString()));
                            dataCell2.setCellStyle(conCellStyle);


                        }


                        CellRangeAddress rowRangeAddress19 = new CellRangeAddress(itemIndex, rowIndex - 1, 39, 39);
                        sheet.addMergedRegion(rowRangeAddress19);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress19, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress19, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress19, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress19, sheet, wb);

                        CellRangeAddress rowRangeAddress20 = new CellRangeAddress(itemIndex, rowIndex - 1, 40, 40);
                        sheet.addMergedRegion(rowRangeAddress20);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress20, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress20, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress20, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress20, sheet, wb);
                        if (data.get("clock").toString().equals("10")) {
                            HSSFCell dataCell1 = dataRow.getCell(39);
                            dataCell1.setCellValue(Double.parseDouble(data.get("runTime").toString()));
                            dataCell1.setCellStyle(conCellStyle);

                            HSSFCell dataCell2 = dataRow.getCell(40);
                            dataCell2.setCellValue(Double.parseDouble(data.get("avgfeiqi").toString()));
                            dataCell2.setCellStyle(conCellStyle);


                        }


                        CellRangeAddress rowRangeAddress21 = new CellRangeAddress(itemIndex, rowIndex - 1, 43, 43);
                        sheet.addMergedRegion(rowRangeAddress21);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress21, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress21, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress21, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress21, sheet, wb);

                        CellRangeAddress rowRangeAddress22 = new CellRangeAddress(itemIndex, rowIndex - 1, 44, 44);
                        sheet.addMergedRegion(rowRangeAddress22);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress22, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress22, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress22, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress22, sheet, wb);

                        if (data.get("clock").toString().equals("11")) {
                            HSSFCell dataCell1 = dataRow.getCell(43);
                            dataCell1.setCellValue(Double.parseDouble(data.get("runTime").toString()));
                            dataCell1.setCellStyle(conCellStyle);

                            HSSFCell dataCell2 = dataRow.getCell(44);
                            dataCell2.setCellValue(Double.parseDouble(data.get("avgfeiqi").toString()));
                            dataCell2.setCellStyle(conCellStyle);


                        }


                        CellRangeAddress rowRangeAddress23 = new CellRangeAddress(itemIndex, rowIndex - 1, 47, 47);
                        sheet.addMergedRegion(rowRangeAddress23);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress23, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress23, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress23, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress23, sheet, wb);

                        CellRangeAddress rowRangeAddress24 = new CellRangeAddress(itemIndex, rowIndex - 1, 48, 48);
                        sheet.addMergedRegion(rowRangeAddress24);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress24, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress24, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress24, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress24, sheet, wb);
                        if (data.get("clock").toString().equals("12")) {
                            HSSFCell dataCell1 = dataRow.getCell(47);
                            dataCell1.setCellValue(Double.parseDouble(data.get("runTime").toString()));
                            dataCell1.setCellStyle(conCellStyle);

                            HSSFCell dataCell2 = dataRow.getCell(48);
                            dataCell2.setCellValue(Double.parseDouble(data.get("avgfeiqi").toString()));
                            dataCell2.setCellStyle(conCellStyle);

                        }
                    }

                    CellRangeAddress rowRangeAddress = new CellRangeAddress(itemIndex, rowIndex - 1, 0, 0);
                    sheet.addMergedRegion(rowRangeAddress);
                    RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                    RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                    RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                    RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                    HSSFCell dataCell = dataRow.createCell(0);
                    dataCell.setCellValue(dischargePort.get("dischargePortCode").toString());
                    dataCell.setCellStyle(conCellStyle);

                    CellRangeAddress rowRangeAddress2 = new CellRangeAddress(itemIndex, rowIndex - 1, 1, 1);
                    sheet.addMergedRegion(rowRangeAddress2);
                    RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                    RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                    RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                    RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                    HSSFCell dataCell2 = dataRow.createCell(1);
                    dataCell2.setCellValue(dischargePort.get("dischargePortName").toString());
                    dataCell2.setCellStyle(conCellStyle);

                    itemIndex = rowIndex;
                }

            }

            HSSFRow dataRow = sheet.getRow(5);
            CellRangeAddress rowRangeAddress = new CellRangeAddress(5, rowIndex - 1, 52, 52);
            sheet.addMergedRegion(rowRangeAddress);
            RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
            RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);

            CellRangeAddress rowRangeAddress2 = new CellRangeAddress(5, rowIndex - 1, 53, 53);
            sheet.addMergedRegion(rowRangeAddress2);
            RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
            RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);

            CellRangeAddress rowRangeAddress3 = new CellRangeAddress(5, rowIndex - 1, 54, 54);
            sheet.addMergedRegion(rowRangeAddress3);
            RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress3, sheet, wb);
            RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress3, sheet, wb);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress3, sheet, wb);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress3, sheet, wb);

            List<Map<String, Object>> hdList = this.dao.query("DUHD62.queryHdTotal", pMap);

            if (StrUtil.listIsNotNullOrEmpty(hdList)) {
                for (int i = 0; i < hdList.size(); i++) {
                    Map<String, Object> hdMap = hdList.get(i);

                    if (hdMap.get("factorid").toString().equals("01")) {
                        HSSFCell dataCell = dataRow.createCell(52);
                        dataCell.setCellValue(Double.parseDouble(hdMap.get("finalValue").toString()));
                        dataCell.setCellStyle(conCellStyle);
                    }

                    if (hdMap.get("factorid").toString().equals("02")) {
                        HSSFCell dataCell = dataRow.createCell(53);
                        dataCell.setCellValue(Double.parseDouble(hdMap.get("finalValue").toString()));
                        dataCell.setCellStyle(conCellStyle);
                    }

                    if (hdMap.get("factorid").toString().equals("03")) {
                        HSSFCell dataCell = dataRow.createCell(54);
                        dataCell.setCellValue(Double.parseDouble(hdMap.get("finalValue").toString()));
                        dataCell.setCellStyle(conCellStyle);
                    }
                }
            }

        }

        HSSFRow row2 = sheet.createRow(rowIndex + 2);

        CellRangeAddress range3 = new CellRangeAddress(rowIndex + 2, rowIndex + 2, 3, 15);
        sheet.addMergedRegion(range3);
        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, range3, sheet, wb);
        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, range3, sheet, wb);
        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, range3, sheet, wb);
        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, range3, sheet, wb);
        HSSFCell cell4 = row2.createCell(3);
        cell4.setCellValue(reportDate + "年实际排放量（吨）");
        cell4.setCellStyle(conCellStyle2);

        HSSFRow dataRow3 = sheet.createRow(rowIndex + 3);

        HSSFCell monthCell = dataRow3.createCell(3);
        monthCell.setCellValue("1月份");
        monthCell.setCellStyle(conCellStyle2);

        HSSFCell monthCell2 = dataRow3.createCell(4);
        monthCell2.setCellValue("2月份");
        monthCell2.setCellStyle(conCellStyle2);

        HSSFCell monthCell3 = dataRow3.createCell(5);
        monthCell3.setCellValue("3月份");
        monthCell3.setCellStyle(conCellStyle2);

        HSSFCell monthCell4 = dataRow3.createCell(6);
        monthCell4.setCellValue("4月份");
        monthCell4.setCellStyle(conCellStyle2);

        HSSFCell monthCell5 = dataRow3.createCell(7);
        monthCell5.setCellValue("5月份");
        monthCell5.setCellStyle(conCellStyle2);

        HSSFCell monthCell6 = dataRow3.createCell(8);
        monthCell6.setCellValue("6月份");
        monthCell6.setCellStyle(conCellStyle2);

        HSSFCell monthCell7 = dataRow3.createCell(9);
        monthCell7.setCellValue("7月份");
        monthCell7.setCellStyle(conCellStyle2);

        HSSFCell monthCell8 = dataRow3.createCell(10);
        monthCell8.setCellValue("8月份");
        monthCell8.setCellStyle(conCellStyle2);

        HSSFCell monthCell9 = dataRow3.createCell(11);
        monthCell9.setCellValue("9月份");
        monthCell9.setCellStyle(conCellStyle2);

        HSSFCell monthCell10 = dataRow3.createCell(12);
        monthCell10.setCellValue("10月份");
        monthCell10.setCellStyle(conCellStyle2);

        HSSFCell monthCell11 = dataRow3.createCell(13);
        monthCell11.setCellValue("11月份");
        monthCell11.setCellStyle(conCellStyle2);

        HSSFCell monthCell12 = dataRow3.createCell(14);
        monthCell12.setCellValue("12月份");
        monthCell12.setCellStyle(conCellStyle2);

        HSSFCell monthCell13 = dataRow3.createCell(15);
        monthCell13.setCellValue("合计");
        monthCell13.setCellStyle(conCellStyle2);

        HSSFRow row3 = sheet.getRow(rowIndex + 2);
        CellRangeAddress range = new CellRangeAddress(rowIndex + 2, rowIndex + 3, 0, 1);
        sheet.addMergedRegion(range);
        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, range, sheet, wb);
        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, range, sheet, wb);
        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, range, sheet, wb);
        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, range, sheet, wb);
        HSSFCell cell2 = row3.createCell(0);
        cell2.setCellValue("工序");
        cell2.setCellStyle(conCellStyle2);

        CellRangeAddress range2 = new CellRangeAddress(rowIndex + 2, rowIndex + 3, 2, 2);
        sheet.addMergedRegion(range2);
        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, range2, sheet, wb);
        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, range2, sheet, wb);
        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, range2, sheet, wb);
        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, range2, sheet, wb);
        HSSFCell cell3 = row3.createCell(2);
        cell3.setCellValue("污染物种类");
        cell3.setCellStyle(conCellStyle2);

        List<Map<String, Object>> procedureList = this.dao.query("DUHD62.queryHdProcedure", pMap);

        List<String> factors = new ArrayList<>();
        factors.add("颗粒物");
        factors.add("二氧化硫");
        factors.add("氮氧化物");

        int rowIndex2 = rowIndex + 4;
        int itemIndex2 = rowIndex + 4;
        if (StrUtil.listIsNotNullOrEmpty(procedureList)) {
            for (int i = 0; i < procedureList.size(); i++) {
                Map<String, Object> procedure = procedureList.get(i);
                procedure.put("reportDate", reportDate);

                List<Map<String, Object>> dataList2 = this.dao.query("DUHD62.queryHdData2", procedure);


                if (StrUtil.listIsNotNullOrEmpty(factors)) {
                    for (int j = 0; j < factors.size(); j++) {
                        String factor = factors.get(j);

                        HSSFRow totalRow = sheet.createRow(rowIndex2);

                        HSSFCell totalCell = totalRow.createCell(2);
                        totalCell.setCellValue(factor);
                        totalCell.setCellStyle(conCellStyle);

                        for (int k = 3; k < 16; k++) {
                            HSSFCell cell = totalRow.createCell(k);
                            cell.setCellValue("");
                            cell.setCellStyle(conCellStyle);
                        }

                        if (StrUtil.listIsNotNullOrEmpty(dataList2)) {
                            for (int l = 0; l < dataList2.size(); l++) {
                                Map<String, Object> data = dataList2.get(l);
                                String factorName2 = data.get("factorname").toString();
                                if (factor.equals(factorName2)) {

                                    if (data.get("clock").toString().equals("01")) {
                                        HSSFCell dataCell1 = totalRow.getCell(3);
                                        dataCell1.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell1.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("02")) {
                                        HSSFCell dataCell1 = totalRow.getCell(4);
                                        dataCell1.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell1.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("03")) {
                                        HSSFCell dataCell1 = totalRow.getCell(5);
                                        dataCell1.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell1.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("04")) {
                                        HSSFCell dataCell1 = totalRow.getCell(6);
                                        dataCell1.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell1.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("05")) {
                                        HSSFCell dataCell1 = totalRow.getCell(7);
                                        dataCell1.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell1.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("06")) {
                                        HSSFCell dataCell1 = totalRow.getCell(8);
                                        dataCell1.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell1.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("07")) {
                                        HSSFCell dataCell1 = totalRow.getCell(9);
                                        dataCell1.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell1.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("08")) {
                                        HSSFCell dataCell1 = totalRow.getCell(10);
                                        dataCell1.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell1.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("09")) {
                                        HSSFCell dataCell1 = totalRow.getCell(11);
                                        dataCell1.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell1.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("10")) {
                                        HSSFCell dataCell1 = totalRow.getCell(12);
                                        dataCell1.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell1.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("11")) {
                                        HSSFCell dataCell1 = totalRow.getCell(13);
                                        dataCell1.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell1.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("12")) {
                                        HSSFCell dataCell1 = totalRow.getCell(14);
                                        dataCell1.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell1.setCellStyle(conCellStyle);
                                    }
                                }
                            }
                        }

                        HSSFCell cell = totalRow.createCell(15);
                        int index = rowIndex2 + 1;
                        cell.setCellFormula("SUM(D" + index + ",E" + index + ",F" + index + ",G" + index + ",H" +
                                index + ",I" + index + ",J" + index + ",K" + index + ",L" + index + ",M" +
                                index + ",N" + index + ",O" + index + ")");
                        cell.setCellStyle(conCellStyle);

                        rowIndex2++;
                    }

                    HSSFRow totalRow = sheet.getRow(itemIndex2);
                    CellRangeAddress rowRange = new CellRangeAddress(itemIndex2, rowIndex2 - 1, 0, 1);
                    sheet.addMergedRegion(rowRange);
                    RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRange, sheet, wb);
                    RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRange, sheet, wb);
                    RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRange, sheet, wb);
                    RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRange, sheet, wb);
                    HSSFCell totalCell = totalRow.createCell(0);
                    totalCell.setCellValue(procedure.get("procedureName").toString());
                    totalCell.setCellStyle(conCellStyle);


                    itemIndex2 = rowIndex2;
                }

            }
        }

    }

    /**
     * 韶钢污染物排放量统计表--炼焦
     */
    public void saveSheet_LJ(HSSFSheet sheet, HSSFWorkbook wb, EiInfo inInfo) {

        String reportDate = inInfo.getString("reportDate");

        HSSFDataFormat format = wb.createDataFormat();

        //内容样式
        HSSFCellStyle conCellStyle = wb.createCellStyle();
        conCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        conCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        conCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        conCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        conCellStyle.setDataFormat(format.getFormat("0.00_ "));
        conCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        conCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        conCellStyle.setWrapText(true);
        HSSFFont conFont = wb.createFont();
        conFont.setFontHeightInPoints((short) 10);
        conFont.setFontName("宋体");
        conCellStyle.setFont(conFont);

        HSSFCellStyle conCellStyle2 = wb.createCellStyle();
        conCellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        conCellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        conCellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        conCellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        conCellStyle2.setDataFormat(format.getFormat("@"));
        conCellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        conCellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        conCellStyle2.setWrapText(true);
        HSSFFont conFont2 = wb.createFont();
        conFont2.setFontHeightInPoints((short) 12);
        conFont2.setFontName("黑体");
        conCellStyle2.setFont(conFont2);

        Map<String, String> pMap = new HashMap<>();
        pMap.put("reportDate", reportDate);
        pMap.put("departId", "DEP00001");
        pMap.put("monitorid", "01");
        pMap.put("computermode", "AUTO");

        List<Map<String, Object>> list = this.dao.query("DUHD62.queryLjDischargePort", pMap);

        int rowIndex = 4;
        int itemIndex = 4;
        if (StrUtil.listIsNotNullOrEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> dischargePort = list.get(i);
                dischargePort.put("reportDate", reportDate);
                dischargePort.put("computermode", "AUTO");

                List<Map<String, Object>> factorList = this.dao.query("DUHD62.queryTotalFactor", dischargePort);
                List<Map<String, Object>> dataList = this.dao.query("DUHD62.queryLjData", dischargePort);


                if (StrUtil.listIsNotNullOrEmpty(factorList)) {
                    for (int j = 0; j < factorList.size(); j++) {
                        Map<String, Object> factorMap = factorList.get(j);
                        String factorName = factorMap.get("factorName").toString();

                        HSSFRow dataRow = sheet.createRow(rowIndex);


                        if (sheet.getRow(rowIndex + 8) != null) {
                            int lastRowNum = sheet.getLastRowNum();
                            sheet.shiftRows(rowIndex + 8, lastRowNum, 1);//12-14行整体下移一行
                        }


                        HSSFCell dataCell = dataRow.createCell(2);
                        dataCell.setCellValue(factorName);
                        dataCell.setCellStyle(conCellStyle);

                        for (int k = 3; k < 51; k++) {
                            HSSFCell cell = dataRow.createCell(k);
                            cell.setCellValue("");
                            cell.setCellStyle(conCellStyle);
                        }

                        if (StrUtil.listIsNotNullOrEmpty(dataList)) {
                            for (int l = 0; l < dataList.size(); l++) {
                                Map<String, Object> data = dataList.get(l);
                                String factorName2 = data.get("factorname").toString();
                                if (factorName.equals(factorName2)) {

                                    if (data.get("clock").toString().equals("01")) {

                                        HSSFCell dataCell3 = dataRow.getCell(5);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(6);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("02")) {


                                        HSSFCell dataCell3 = dataRow.getCell(9);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(10);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("03")) {


                                        HSSFCell dataCell3 = dataRow.getCell(13);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(14);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("04")) {


                                        HSSFCell dataCell3 = dataRow.getCell(17);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(18);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("05")) {


                                        HSSFCell dataCell3 = dataRow.getCell(21);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(22);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("06")) {


                                        HSSFCell dataCell3 = dataRow.getCell(25);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(26);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("07")) {


                                        HSSFCell dataCell3 = dataRow.getCell(29);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(30);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("08")) {


                                        HSSFCell dataCell3 = dataRow.getCell(33);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(34);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("09")) {

                                        HSSFCell dataCell3 = dataRow.getCell(37);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(38);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("10")) {


                                        HSSFCell dataCell3 = dataRow.getCell(41);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(42);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("11")) {


                                        HSSFCell dataCell3 = dataRow.getCell(45);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(46);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("12")) {

                                        HSSFCell dataCell3 = dataRow.getCell(49);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(50);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }
                                }
                            }
                        }

                        HSSFCell cell = dataRow.createCell(51);
                        int index = rowIndex + 1;
                        cell.setCellFormula("SUM(G" + index + ",K" + index + ",O" + index + ",S" + index + ",W" +
                                index + ",AA" + index + ",AE" + index + ",AI" + index + ",AM" + index + ",AQ" +
                                index + ",AU" + index + ",AY" + index + ")");
                        cell.setCellStyle(conCellStyle);

                        rowIndex++;
                    }

                    HSSFRow dataRow = sheet.getRow(itemIndex);
                    for (int j = 0; j < dataList.size(); j++) {
                        Map<String, Object> data = dataList.get(j);


                        CellRangeAddress rowRangeAddress = new CellRangeAddress(itemIndex, rowIndex - 1, 3, 3);
                        sheet.addMergedRegion(rowRangeAddress);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);

                        CellRangeAddress rowRangeAddress2 = new CellRangeAddress(itemIndex, rowIndex - 1, 4, 4);
                        sheet.addMergedRegion(rowRangeAddress2);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);

                        if (data.get("clock").toString().equals("01")) {
                            HSSFCell dataCell1 = dataRow.getCell(3);
                            dataCell1.setCellValue(Double.parseDouble(data.get("runTime").toString()));
                            dataCell1.setCellStyle(conCellStyle);

                            HSSFCell dataCell2 = dataRow.getCell(4);
                            dataCell2.setCellValue(Double.parseDouble(data.get("avgfeiqi").toString()));
                            dataCell2.setCellStyle(conCellStyle);
                        }


                        CellRangeAddress rowRangeAddress3 = new CellRangeAddress(itemIndex, rowIndex - 1, 7, 7);
                        sheet.addMergedRegion(rowRangeAddress3);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress3, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress3, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress3, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress3, sheet, wb);

                        CellRangeAddress rowRangeAddress4 = new CellRangeAddress(itemIndex, rowIndex - 1, 8, 8);
                        sheet.addMergedRegion(rowRangeAddress4);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress4, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress4, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress4, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress4, sheet, wb);

                        if (data.get("clock").toString().equals("02")) {
                            HSSFCell dataCell1 = dataRow.getCell(7);
                            dataCell1.setCellValue(Double.parseDouble(data.get("runTime").toString()));
                            dataCell1.setCellStyle(conCellStyle);

                            HSSFCell dataCell2 = dataRow.getCell(8);
                            dataCell2.setCellValue(Double.parseDouble(data.get("avgfeiqi").toString()));
                            dataCell2.setCellStyle(conCellStyle);


                        }


                        CellRangeAddress rowRangeAddress5 = new CellRangeAddress(itemIndex, rowIndex - 1, 11, 11);
                        sheet.addMergedRegion(rowRangeAddress5);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress5, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress5, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress5, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress5, sheet, wb);

                        CellRangeAddress rowRangeAddress6 = new CellRangeAddress(itemIndex, rowIndex - 1, 12, 12);
                        sheet.addMergedRegion(rowRangeAddress6);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress6, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress6, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress6, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress6, sheet, wb);
                        if (data.get("clock").toString().equals("03")) {
                            HSSFCell dataCell1 = dataRow.getCell(11);
                            dataCell1.setCellValue(Double.parseDouble(data.get("runTime").toString()));
                            dataCell1.setCellStyle(conCellStyle);

                            HSSFCell dataCell2 = dataRow.getCell(12);
                            dataCell2.setCellValue(Double.parseDouble(data.get("avgfeiqi").toString()));
                            dataCell2.setCellStyle(conCellStyle);


                        }


                        CellRangeAddress rowRangeAddress7 = new CellRangeAddress(itemIndex, rowIndex - 1, 15, 15);
                        sheet.addMergedRegion(rowRangeAddress7);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress7, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress7, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress7, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress7, sheet, wb);

                        CellRangeAddress rowRangeAddress8 = new CellRangeAddress(itemIndex, rowIndex - 1, 16, 16);
                        sheet.addMergedRegion(rowRangeAddress8);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress8, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress8, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress8, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress8, sheet, wb);
                        if (data.get("clock").toString().equals("04")) {
                            HSSFCell dataCell1 = dataRow.getCell(15);
                            dataCell1.setCellValue(Double.parseDouble(data.get("runTime").toString()));
                            dataCell1.setCellStyle(conCellStyle);

                            HSSFCell dataCell2 = dataRow.getCell(16);
                            dataCell2.setCellValue(Double.parseDouble(data.get("avgfeiqi").toString()));
                            dataCell2.setCellStyle(conCellStyle);


                        }


                        CellRangeAddress rowRangeAddress9 = new CellRangeAddress(itemIndex, rowIndex - 1, 19, 19);
                        sheet.addMergedRegion(rowRangeAddress9);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress9, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress9, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress9, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress9, sheet, wb);

                        CellRangeAddress rowRangeAddress10 = new CellRangeAddress(itemIndex, rowIndex - 1, 20, 20);
                        sheet.addMergedRegion(rowRangeAddress10);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress10, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress10, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress10, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress10, sheet, wb);
                        if (data.get("clock").toString().equals("05")) {
                            HSSFCell dataCell1 = dataRow.getCell(19);
                            dataCell1.setCellValue(Double.parseDouble(data.get("runTime").toString()));
                            dataCell1.setCellStyle(conCellStyle);

                            HSSFCell dataCell2 = dataRow.getCell(20);
                            dataCell2.setCellValue(Double.parseDouble(data.get("avgfeiqi").toString()));
                            dataCell2.setCellStyle(conCellStyle);


                        }


                        CellRangeAddress rowRangeAddress11 = new CellRangeAddress(itemIndex, rowIndex - 1, 23, 23);
                        sheet.addMergedRegion(rowRangeAddress11);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress11, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress11, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress11, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress11, sheet, wb);

                        CellRangeAddress rowRangeAddress12 = new CellRangeAddress(itemIndex, rowIndex - 1, 24, 24);
                        sheet.addMergedRegion(rowRangeAddress12);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress12, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress12, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress12, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress12, sheet, wb);
                        if (data.get("clock").toString().equals("06")) {
                            HSSFCell dataCell1 = dataRow.getCell(23);
                            dataCell1.setCellValue(Double.parseDouble(data.get("runTime").toString()));
                            dataCell1.setCellStyle(conCellStyle);

                            HSSFCell dataCell2 = dataRow.getCell(24);
                            dataCell2.setCellValue(Double.parseDouble(data.get("avgfeiqi").toString()));
                            dataCell2.setCellStyle(conCellStyle);


                        }


                        CellRangeAddress rowRangeAddress13 = new CellRangeAddress(itemIndex, rowIndex - 1, 27, 27);
                        sheet.addMergedRegion(rowRangeAddress13);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress13, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress13, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress13, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress13, sheet, wb);

                        CellRangeAddress rowRangeAddress14 = new CellRangeAddress(itemIndex, rowIndex - 1, 28, 28);
                        sheet.addMergedRegion(rowRangeAddress14);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress14, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress14, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress14, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress14, sheet, wb);
                        if (data.get("clock").toString().equals("07")) {
                            HSSFCell dataCell1 = dataRow.getCell(27);
                            dataCell1.setCellValue(Double.parseDouble(data.get("runTime").toString()));
                            dataCell1.setCellStyle(conCellStyle);

                            HSSFCell dataCell2 = dataRow.getCell(28);
                            dataCell2.setCellValue(Double.parseDouble(data.get("avgfeiqi").toString()));
                            dataCell2.setCellStyle(conCellStyle);


                        }


                        CellRangeAddress rowRangeAddress15 = new CellRangeAddress(itemIndex, rowIndex - 1, 31, 31);
                        sheet.addMergedRegion(rowRangeAddress15);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress15, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress15, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress15, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress15, sheet, wb);

                        CellRangeAddress rowRangeAddress16 = new CellRangeAddress(itemIndex, rowIndex - 1, 32, 32);
                        sheet.addMergedRegion(rowRangeAddress16);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress16, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress16, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress16, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress16, sheet, wb);
                        if (data.get("clock").toString().equals("08")) {
                            HSSFCell dataCell1 = dataRow.getCell(31);
                            dataCell1.setCellValue(Double.parseDouble(data.get("runTime").toString()));
                            dataCell1.setCellStyle(conCellStyle);

                            HSSFCell dataCell2 = dataRow.getCell(32);
                            dataCell2.setCellValue(Double.parseDouble(data.get("avgfeiqi").toString()));
                            dataCell2.setCellStyle(conCellStyle);


                        }


                        CellRangeAddress rowRangeAddress17 = new CellRangeAddress(itemIndex, rowIndex - 1, 35, 35);
                        sheet.addMergedRegion(rowRangeAddress17);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress17, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress17, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress17, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress17, sheet, wb);

                        CellRangeAddress rowRangeAddress18 = new CellRangeAddress(itemIndex, rowIndex - 1, 36, 36);
                        sheet.addMergedRegion(rowRangeAddress18);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress18, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress18, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress18, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress18, sheet, wb);
                        if (data.get("clock").toString().equals("09")) {
                            HSSFCell dataCell1 = dataRow.getCell(35);
                            dataCell1.setCellValue(Double.parseDouble(data.get("runTime").toString()));
                            dataCell1.setCellStyle(conCellStyle);

                            HSSFCell dataCell2 = dataRow.getCell(36);
                            dataCell2.setCellValue(Double.parseDouble(data.get("avgfeiqi").toString()));
                            dataCell2.setCellStyle(conCellStyle);


                        }


                        CellRangeAddress rowRangeAddress19 = new CellRangeAddress(itemIndex, rowIndex - 1, 39, 39);
                        sheet.addMergedRegion(rowRangeAddress19);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress19, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress19, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress19, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress19, sheet, wb);

                        CellRangeAddress rowRangeAddress20 = new CellRangeAddress(itemIndex, rowIndex - 1, 40, 40);
                        sheet.addMergedRegion(rowRangeAddress20);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress20, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress20, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress20, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress20, sheet, wb);
                        if (data.get("clock").toString().equals("10")) {
                            HSSFCell dataCell1 = dataRow.getCell(39);
                            dataCell1.setCellValue(Double.parseDouble(data.get("runTime").toString()));
                            dataCell1.setCellStyle(conCellStyle);

                            HSSFCell dataCell2 = dataRow.getCell(40);
                            dataCell2.setCellValue(Double.parseDouble(data.get("avgfeiqi").toString()));
                            dataCell2.setCellStyle(conCellStyle);


                        }


                        CellRangeAddress rowRangeAddress21 = new CellRangeAddress(itemIndex, rowIndex - 1, 43, 43);
                        sheet.addMergedRegion(rowRangeAddress21);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress21, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress21, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress21, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress21, sheet, wb);

                        CellRangeAddress rowRangeAddress22 = new CellRangeAddress(itemIndex, rowIndex - 1, 44, 44);
                        sheet.addMergedRegion(rowRangeAddress22);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress22, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress22, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress22, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress22, sheet, wb);

                        if (data.get("clock").toString().equals("11")) {
                            HSSFCell dataCell1 = dataRow.getCell(43);
                            dataCell1.setCellValue(Double.parseDouble(data.get("runTime").toString()));
                            dataCell1.setCellStyle(conCellStyle);

                            HSSFCell dataCell2 = dataRow.getCell(44);
                            dataCell2.setCellValue(Double.parseDouble(data.get("avgfeiqi").toString()));
                            dataCell2.setCellStyle(conCellStyle);


                        }


                        CellRangeAddress rowRangeAddress23 = new CellRangeAddress(itemIndex, rowIndex - 1, 47, 47);
                        sheet.addMergedRegion(rowRangeAddress23);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress23, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress23, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress23, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress23, sheet, wb);

                        CellRangeAddress rowRangeAddress24 = new CellRangeAddress(itemIndex, rowIndex - 1, 48, 48);
                        sheet.addMergedRegion(rowRangeAddress24);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress24, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress24, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress24, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress24, sheet, wb);
                        if (data.get("clock").toString().equals("12")) {
                            HSSFCell dataCell1 = dataRow.getCell(47);
                            dataCell1.setCellValue(Double.parseDouble(data.get("runTime").toString()));
                            dataCell1.setCellStyle(conCellStyle);

                            HSSFCell dataCell2 = dataRow.getCell(48);
                            dataCell2.setCellValue(Double.parseDouble(data.get("avgfeiqi").toString()));
                            dataCell2.setCellStyle(conCellStyle);

                        }
                    }


                    CellRangeAddress rowRangeAddress = new CellRangeAddress(itemIndex, rowIndex - 1, 0, 0);
                    sheet.addMergedRegion(rowRangeAddress);
                    RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                    RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                    RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                    RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                    HSSFCell dataCell = dataRow.createCell(0);
                    dataCell.setCellValue(dischargePort.get("dischargePortCode").toString());
                    dataCell.setCellStyle(conCellStyle);

                    CellRangeAddress rowRangeAddress2 = new CellRangeAddress(itemIndex, rowIndex - 1, 1, 1);
                    sheet.addMergedRegion(rowRangeAddress2);
                    RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                    RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                    RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                    RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                    HSSFCell dataCell2 = dataRow.createCell(1);
                    dataCell2.setCellValue(dischargePort.get("dischargePortName").toString());
                    dataCell2.setCellStyle(conCellStyle);

                    itemIndex = rowIndex;
                }


            }

            HSSFRow dataRow = sheet.getRow(4);
            CellRangeAddress rowRangeAddress = new CellRangeAddress(4, rowIndex - 1, 52, 52);
            sheet.addMergedRegion(rowRangeAddress);
            RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
            RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);

            CellRangeAddress rowRangeAddress2 = new CellRangeAddress(4, rowIndex - 1, 53, 53);
            sheet.addMergedRegion(rowRangeAddress2);
            RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
            RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);

            CellRangeAddress rowRangeAddress3 = new CellRangeAddress(4, rowIndex - 1, 54, 54);
            sheet.addMergedRegion(rowRangeAddress3);
            RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress3, sheet, wb);
            RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress3, sheet, wb);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress3, sheet, wb);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress3, sheet, wb);

            List<Map<String, Object>> hdList = this.dao.query("DUHD62.queryLjTotal", pMap);

            if (StrUtil.listIsNotNullOrEmpty(hdList)) {
                for (int i = 0; i < hdList.size(); i++) {
                    Map<String, Object> hdMap = hdList.get(i);

                    if (hdMap.get("factorid").toString().equals("01")) {
                        HSSFCell dataCell = dataRow.createCell(52);
                        dataCell.setCellValue(Double.parseDouble(hdMap.get("finalValue").toString()));
                        dataCell.setCellStyle(conCellStyle);
                    }

                    if (hdMap.get("factorid").toString().equals("02")) {
                        HSSFCell dataCell = dataRow.createCell(53);
                        dataCell.setCellValue(Double.parseDouble(hdMap.get("finalValue").toString()));
                        dataCell.setCellStyle(conCellStyle);
                    }

                    if (hdMap.get("factorid").toString().equals("03")) {
                        HSSFCell dataCell = dataRow.createCell(54);
                        dataCell.setCellValue(Double.parseDouble(hdMap.get("finalValue").toString()));
                        dataCell.setCellStyle(conCellStyle);
                    }
                }
            }


            List<String> factors = new ArrayList<>();
            factors.add("颗粒物");
            factors.add("二氧化硫");
            factors.add("氮氧化物");

            for (int i = 0; i < factors.size(); i++) {
                String factorName = factors.get(i);
                Map<String, String> map = new HashMap<>();
                map.put("reportDate", reportDate);
                map.put("departId", "DEP00001");
                map.put("monitorid", "01");
                map.put("factorName", factorName);
                map.put("computermode", "AUTO");
                List<Map<String, Object>> monthTotal = this.dao.query("DUHD62.queryLjTotal2", map);

                HSSFRow totalRow = sheet.createRow(rowIndex);
                for (int k = 3; k < 51; k++) {
                    HSSFCell cell = totalRow.createCell(k);
                    cell.setCellValue("");
                    cell.setCellStyle(conCellStyle);
                }

                HSSFCell dataCell = totalRow.createCell(2);
                dataCell.setCellValue(factorName);
                dataCell.setCellStyle(conCellStyle);

                if (StrUtil.listIsNotNullOrEmpty(monthTotal)) {
                    for (int j = 0; j < monthTotal.size(); j++) {
                        Map<String, Object> ljTotal = monthTotal.get(j);

                        if (ljTotal.get("clock").toString().equals("01")) {
                            HSSFCell dataCell1 = totalRow.getCell(6);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                        if (ljTotal.get("clock").toString().equals("02")) {
                            HSSFCell dataCell1 = totalRow.getCell(10);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                        if (ljTotal.get("clock").toString().equals("03")) {
                            HSSFCell dataCell1 = totalRow.getCell(14);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                        if (ljTotal.get("clock").toString().equals("04")) {
                            HSSFCell dataCell1 = totalRow.getCell(18);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                        if (ljTotal.get("clock").toString().equals("05")) {
                            HSSFCell dataCell1 = totalRow.getCell(22);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                        if (ljTotal.get("clock").toString().equals("06")) {
                            HSSFCell dataCell1 = totalRow.getCell(26);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                        if (ljTotal.get("clock").toString().equals("07")) {
                            HSSFCell dataCell1 = totalRow.getCell(30);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                        if (ljTotal.get("clock").toString().equals("08")) {
                            HSSFCell dataCell1 = totalRow.getCell(34);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                        if (ljTotal.get("clock").toString().equals("09")) {
                            HSSFCell dataCell1 = totalRow.getCell(38);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                        if (ljTotal.get("clock").toString().equals("10")) {
                            HSSFCell dataCell1 = totalRow.getCell(42);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                        if (ljTotal.get("clock").toString().equals("11")) {
                            HSSFCell dataCell1 = totalRow.getCell(46);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                        if (ljTotal.get("clock").toString().equals("12")) {
                            HSSFCell dataCell1 = totalRow.getCell(50);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                    }
                }
                rowIndex++;
            }

            HSSFRow totalRow2 = sheet.getRow(itemIndex);
            CellRangeAddress rowRange = new CellRangeAddress(itemIndex, rowIndex - 1, 0, 1);
            sheet.addMergedRegion(rowRange);
            RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRange, sheet, wb);
            RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRange, sheet, wb);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRange, sheet, wb);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRange, sheet, wb);
            HSSFCell totalCell = totalRow2.getCell(0);
            totalCell.setCellValue("月合计");
            totalCell.setCellStyle(conCellStyle);
        }


        Map<String, String> pMap2 = new HashMap<>();
        pMap2.put("reportDate", reportDate);
        pMap2.put("departId", "DEP00001");
        pMap2.put("monitorid", "01");
        pMap2.put("computermode", "XS");

        List<Map<String, Object>> list2 = this.dao.query("DUHD62.queryLjProcedure", pMap2);

        int rowIndex2 = rowIndex + 9;
        int itemIndex2 = rowIndex + 9;
        int itemIndex3 = rowIndex + 9;
        if (StrUtil.listIsNotNullOrEmpty(list2)) {
            for (int i = 0; i < list2.size(); i++) {
                Map<String, Object> procedurePort = list2.get(i);
                procedurePort.put("reportDate", reportDate);
                procedurePort.put("computermode", "XS");

                List<Map<String, Object>> factorList = this.dao.query("DUHD62.queryLjFactor", procedurePort);
                List<Map<String, Object>> dataList = this.dao.query("DUHD62.queryLjData2", procedurePort);
                List<Map<String, Object>> clList = this.dao.query("DUHD62.queryLjClData", procedurePort);


                if (StrUtil.listIsNotNullOrEmpty(factorList)) {
                    for (int j = 0; j < factorList.size(); j++) {
                        Map<String, Object> factorMap = factorList.get(j);
                        String factorName = factorMap.get("factorName").toString();

                        HSSFRow dataRow = sheet.createRow(rowIndex2);

                        HSSFCell dataCell = dataRow.createCell(2);
                        dataCell.setCellValue(factorName);
                        dataCell.setCellStyle(conCellStyle);

                        for (int k = 3; k < 39; k++) {
                            HSSFCell cell = dataRow.createCell(k);
                            cell.setCellValue("");
                            cell.setCellStyle(conCellStyle);
                        }

                        if (StrUtil.listIsNotNullOrEmpty(dataList)) {
                            for (int l = 0; l < dataList.size(); l++) {
                                Map<String, Object> data = dataList.get(l);
                                String factorName2 = data.get("factorname").toString();
                                if (factorName.equals(factorName2)) {

                                    if (data.get("clock").toString().equals("01")) {
                                        HSSFCell dataCell3 = dataRow.getCell(5);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell3.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("02")) {
                                        HSSFCell dataCell3 = dataRow.getCell(8);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell3.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("03")) {
                                        HSSFCell dataCell3 = dataRow.getCell(11);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell3.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("04")) {
                                        HSSFCell dataCell3 = dataRow.getCell(14);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell3.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("05")) {
                                        HSSFCell dataCell3 = dataRow.getCell(17);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                    }

                                    if (data.get("clock").toString().equals("06")) {
                                        HSSFCell dataCell3 = dataRow.getCell(20);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell3.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("07")) {
                                        HSSFCell dataCell3 = dataRow.getCell(23);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                    }

                                    if (data.get("clock").toString().equals("08")) {
                                        HSSFCell dataCell3 = dataRow.getCell(26);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell3.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("09")) {
                                        HSSFCell dataCell3 = dataRow.getCell(29);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell3.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("10")) {
                                        HSSFCell dataCell3 = dataRow.getCell(32);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell3.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("11")) {
                                        HSSFCell dataCell3 = dataRow.getCell(35);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell3.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("12")) {
                                        HSSFCell dataCell3 = dataRow.getCell(38);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell3.setCellStyle(conCellStyle);
                                    }
                                }
                            }
                        }

                        HSSFCell cell = dataRow.createCell(39);
                        int index = rowIndex2 + 1;
                        cell.setCellFormula("SUM(F" + index + ",I" + index + ",L" + index + ",O" + index + ",R" +
                                index + ",U" + index + ",X" + index + ",AA" + index + ",AD" + index + ",AG" +
                                index + ",AJ" + index + ",AM" + index + ")");
                        cell.setCellStyle(conCellStyle);

                        rowIndex2++;
                    }

                    HSSFRow dataRow = sheet.getRow(itemIndex2);
                    for (int j = 0; j < clList.size(); j++) {
                        Map<String, Object> data = clList.get(j);


                        CellRangeAddress rowRangeAddress2 = new CellRangeAddress(itemIndex2, rowIndex2 - 1, 4, 4);
                        sheet.addMergedRegion(rowRangeAddress2);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);

                        if (data.get("clock").toString().equals("01")) {
                            HSSFCell dataCell2 = dataRow.getCell(4);
                            dataCell2.setCellValue(Double.parseDouble(data.get("itemValue").toString()));
                            dataCell2.setCellStyle(conCellStyle);
                        }


                        CellRangeAddress rowRangeAddress3 = new CellRangeAddress(itemIndex2, rowIndex2 - 1, 7, 7);
                        sheet.addMergedRegion(rowRangeAddress3);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress3, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress3, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress3, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress3, sheet, wb);

                        if (data.get("clock").toString().equals("02")) {
                            HSSFCell dataCell1 = dataRow.getCell(7);
                            dataCell1.setCellValue(Double.parseDouble(data.get("itemValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }


                        CellRangeAddress rowRangeAddress5 = new CellRangeAddress(itemIndex2, rowIndex2 - 1, 10, 10);
                        sheet.addMergedRegion(rowRangeAddress5);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress5, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress5, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress5, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress5, sheet, wb);
                        if (data.get("clock").toString().equals("03")) {
                            HSSFCell dataCell1 = dataRow.getCell(10);
                            dataCell1.setCellValue(Double.parseDouble(data.get("itemValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }


                        CellRangeAddress rowRangeAddress7 = new CellRangeAddress(itemIndex2, rowIndex2 - 1, 13, 13);
                        sheet.addMergedRegion(rowRangeAddress7);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress7, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress7, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress7, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress7, sheet, wb);
                        if (data.get("clock").toString().equals("04")) {
                            HSSFCell dataCell1 = dataRow.getCell(13);
                            dataCell1.setCellValue(Double.parseDouble(data.get("itemValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }


                        CellRangeAddress rowRangeAddress9 = new CellRangeAddress(itemIndex2, rowIndex2 - 1, 16, 16);
                        sheet.addMergedRegion(rowRangeAddress9);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress9, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress9, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress9, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress9, sheet, wb);
                        if (data.get("clock").toString().equals("05")) {
                            HSSFCell dataCell1 = dataRow.getCell(16);
                            dataCell1.setCellValue(Double.parseDouble(data.get("itemValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }


                        CellRangeAddress rowRangeAddress11 = new CellRangeAddress(itemIndex2, rowIndex2 - 1, 19, 19);
                        sheet.addMergedRegion(rowRangeAddress11);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress11, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress11, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress11, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress11, sheet, wb);
                        if (data.get("clock").toString().equals("06")) {
                            HSSFCell dataCell1 = dataRow.getCell(19);
                            dataCell1.setCellValue(Double.parseDouble(data.get("itemValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }


                        CellRangeAddress rowRangeAddress13 = new CellRangeAddress(itemIndex2, rowIndex2 - 1, 22, 22);
                        sheet.addMergedRegion(rowRangeAddress13);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress13, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress13, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress13, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress13, sheet, wb);
                        if (data.get("clock").toString().equals("07")) {
                            HSSFCell dataCell1 = dataRow.getCell(22);
                            dataCell1.setCellValue(Double.parseDouble(data.get("itemValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }


                        CellRangeAddress rowRangeAddress15 = new CellRangeAddress(itemIndex2, rowIndex2 - 1, 25, 25);
                        sheet.addMergedRegion(rowRangeAddress15);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress15, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress15, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress15, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress15, sheet, wb);
                        if (data.get("clock").toString().equals("08")) {
                            HSSFCell dataCell1 = dataRow.getCell(25);
                            dataCell1.setCellValue(Double.parseDouble(data.get("itemValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }


                        CellRangeAddress rowRangeAddress17 = new CellRangeAddress(itemIndex2, rowIndex2 - 1, 28, 28);
                        sheet.addMergedRegion(rowRangeAddress17);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress17, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress17, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress17, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress17, sheet, wb);
                        if (data.get("clock").toString().equals("09")) {
                            HSSFCell dataCell1 = dataRow.getCell(28);
                            dataCell1.setCellValue(Double.parseDouble(data.get("itemValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }


                        CellRangeAddress rowRangeAddress19 = new CellRangeAddress(itemIndex2, rowIndex2 - 1, 31, 31);
                        sheet.addMergedRegion(rowRangeAddress19);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress19, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress19, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress19, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress19, sheet, wb);
                        if (data.get("clock").toString().equals("10")) {
                            HSSFCell dataCell1 = dataRow.getCell(31);
                            dataCell1.setCellValue(Double.parseDouble(data.get("itemValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }


                        CellRangeAddress rowRangeAddress21 = new CellRangeAddress(itemIndex2, rowIndex2 - 1, 34, 34);
                        sheet.addMergedRegion(rowRangeAddress21);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress21, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress21, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress21, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress21, sheet, wb);
                        if (data.get("clock").toString().equals("11")) {
                            HSSFCell dataCell1 = dataRow.getCell(34);
                            dataCell1.setCellValue(Double.parseDouble(data.get("itemValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }


                        CellRangeAddress rowRangeAddress23 = new CellRangeAddress(itemIndex2, rowIndex2 - 1, 37, 37);
                        sheet.addMergedRegion(rowRangeAddress23);
                        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress23, sheet, wb);
                        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress23, sheet, wb);
                        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress23, sheet, wb);
                        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress23, sheet, wb);
                        if (data.get("clock").toString().equals("12")) {
                            HSSFCell dataCell1 = dataRow.getCell(37);
                            dataCell1.setCellValue(Double.parseDouble(data.get("itemValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                    }


                    CellRangeAddress rowRangeAddress = new CellRangeAddress(itemIndex2, rowIndex2 - 1, 0, 0);
                    sheet.addMergedRegion(rowRangeAddress);
                    RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                    RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                    RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                    RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                    HSSFCell dataCell = dataRow.createCell(0);
                    dataCell.setCellValue("");
                    dataCell.setCellStyle(conCellStyle);

                    CellRangeAddress rowRangeAddress2 = new CellRangeAddress(itemIndex2, rowIndex2 - 1, 1, 1);
                    sheet.addMergedRegion(rowRangeAddress2);
                    RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                    RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                    RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                    RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                    HSSFCell dataCell2 = dataRow.createCell(1);
                    dataCell2.setCellValue(procedurePort.get("procedureName").toString());
                    dataCell2.setCellStyle(conCellStyle);

                    itemIndex2 = rowIndex2;
                }


            }

            HSSFRow dataRow = sheet.getRow(itemIndex3);
            CellRangeAddress rowRangeAddress = new CellRangeAddress(itemIndex3, rowIndex2 - 1, 40, 40);
            sheet.addMergedRegion(rowRangeAddress);
            RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
            RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);

            CellRangeAddress rowRangeAddress2 = new CellRangeAddress(itemIndex3, rowIndex2 - 1, 41, 41);
            sheet.addMergedRegion(rowRangeAddress2);
            RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
            RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);

            CellRangeAddress rowRangeAddress3 = new CellRangeAddress(itemIndex3, rowIndex2 - 1, 42, 42);
            sheet.addMergedRegion(rowRangeAddress3);
            RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress3, sheet, wb);
            RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress3, sheet, wb);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress3, sheet, wb);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress3, sheet, wb);

            List<Map<String, Object>> hdList = this.dao.query("DUHD62.queryLjTotal", pMap2);

            if (StrUtil.listIsNotNullOrEmpty(hdList)) {
                for (int i = 0; i < hdList.size(); i++) {
                    Map<String, Object> hdMap = hdList.get(i);

                    if (hdMap.get("factorid").toString().equals("01")) {
                        HSSFCell dataCell = dataRow.createCell(40);
                        dataCell.setCellValue(Double.parseDouble(hdMap.get("finalValue").toString()));
                        dataCell.setCellStyle(conCellStyle);
                    }

                    if (hdMap.get("factorid").toString().equals("02")) {
                        HSSFCell dataCell = dataRow.createCell(41);
                        dataCell.setCellValue(Double.parseDouble(hdMap.get("finalValue").toString()));
                        dataCell.setCellStyle(conCellStyle);
                    }

                    if (hdMap.get("factorid").toString().equals("03")) {
                        HSSFCell dataCell = dataRow.createCell(42);
                        dataCell.setCellValue(Double.parseDouble(hdMap.get("finalValue").toString()));
                        dataCell.setCellStyle(conCellStyle);
                    }
                }
            }


            List<String> factors = new ArrayList<>();
            factors.add("颗粒物");
            factors.add("二氧化硫");
            factors.add("氮氧化物");

            for (int i = 0; i < factors.size(); i++) {
                String factorName = factors.get(i);
                Map<String, String> map = new HashMap<>();
                map.put("reportDate", reportDate);
                map.put("departId", "DEP00001");
                map.put("monitorid", "01");
                map.put("factorName", factorName);
                map.put("computermode", "XS");
                List<Map<String, Object>> monthTotal = this.dao.query("DUHD62.queryLjTotal2", map);

                HSSFRow totalRow = sheet.createRow(rowIndex2);
                for (int k = 3; k < 39; k++) {
                    HSSFCell cell = totalRow.createCell(k);
                    cell.setCellValue("");
                    cell.setCellStyle(conCellStyle);
                }

                HSSFCell dataCell = totalRow.createCell(2);
                dataCell.setCellValue(factorName);
                dataCell.setCellStyle(conCellStyle);

                if (StrUtil.listIsNotNullOrEmpty(monthTotal)) {
                    for (int j = 0; j < monthTotal.size(); j++) {
                        Map<String, Object> ljTotal = monthTotal.get(j);

                        if (ljTotal.get("clock").toString().equals("01")) {
                            HSSFCell dataCell1 = totalRow.getCell(5);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                        if (ljTotal.get("clock").toString().equals("02")) {
                            HSSFCell dataCell1 = totalRow.getCell(8);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                        if (ljTotal.get("clock").toString().equals("03")) {
                            HSSFCell dataCell1 = totalRow.getCell(11);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                        if (ljTotal.get("clock").toString().equals("04")) {
                            HSSFCell dataCell1 = totalRow.getCell(14);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                        if (ljTotal.get("clock").toString().equals("05")) {
                            HSSFCell dataCell1 = totalRow.getCell(17);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                        if (ljTotal.get("clock").toString().equals("06")) {
                            HSSFCell dataCell1 = totalRow.getCell(20);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                        if (ljTotal.get("clock").toString().equals("07")) {
                            HSSFCell dataCell1 = totalRow.getCell(23);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                        if (ljTotal.get("clock").toString().equals("08")) {
                            HSSFCell dataCell1 = totalRow.getCell(26);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                        if (ljTotal.get("clock").toString().equals("09")) {
                            HSSFCell dataCell1 = totalRow.getCell(29);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                        if (ljTotal.get("clock").toString().equals("10")) {
                            HSSFCell dataCell1 = totalRow.getCell(32);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                        if (ljTotal.get("clock").toString().equals("11")) {
                            HSSFCell dataCell1 = totalRow.getCell(35);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                        if (ljTotal.get("clock").toString().equals("12")) {
                            HSSFCell dataCell1 = totalRow.getCell(38);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                    }
                }
                rowIndex2++;
            }

            HSSFRow totalRow2 = sheet.getRow(itemIndex2);
            CellRangeAddress rowRange = new CellRangeAddress(itemIndex2, rowIndex2 - 1, 0, 1);
            sheet.addMergedRegion(rowRange);
            RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRange, sheet, wb);
            RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRange, sheet, wb);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRange, sheet, wb);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRange, sheet, wb);
            HSSFCell totalCell = totalRow2.getCell(0);
            totalCell.setCellValue("月合计");
            totalCell.setCellStyle(conCellStyle);
        }
    }

    /**
     * 韶钢污染物排放量统计表--钢铁
     */
    public void saveSheet_GT(HSSFSheet sheet, HSSFWorkbook wb, EiInfo inInfo) {
        String reportDate = inInfo.getString("reportDate");

        HSSFDataFormat format = wb.createDataFormat();

        //内容样式
        HSSFCellStyle conCellStyle = wb.createCellStyle();
        conCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        conCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        conCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        conCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        conCellStyle.setDataFormat(format.getFormat("0.00_ "));
        conCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        conCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        conCellStyle.setWrapText(true);
        HSSFFont conFont = wb.createFont();
        conFont.setFontHeightInPoints((short) 10);
        conFont.setFontName("宋体");
        conCellStyle.setFont(conFont);

        HSSFCellStyle conCellStyle2 = wb.createCellStyle();
        conCellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        conCellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        conCellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        conCellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        conCellStyle2.setDataFormat(format.getFormat("@"));
        conCellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        conCellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        conCellStyle2.setWrapText(true);
        HSSFFont conFont2 = wb.createFont();
        conFont2.setFontHeightInPoints((short) 12);
        conFont2.setFontName("黑体");
        conCellStyle2.setFont(conFont2);

        List<String> dept = new ArrayList<>();
        dept.add("DEP00002");
        dept.add("DEP00003");
        dept.add("DEP00004");
        dept.add("DEP00005");
        dept.add("DEP00006");
        dept.add("DEP00007");

        Map<String, Object> pMap = new HashMap<>();
        pMap.put("reportDate", reportDate);
        pMap.put("dept", dept);
        pMap.put("monitorid", "01");
        pMap.put("factorId", "01");

        List<Map<String, Object>> procedureList = this.dao.query("DUHD62.queryGtWzzProcedure", pMap);
        int rowIndex = 10;
        int itemIndex = 10;
        if (StrUtil.listIsNotNullOrEmpty(procedureList)) {
            for (int i = 0; i < procedureList.size(); i++) {
                Map<String, Object> procedureMap = procedureList.get(i);

                procedureMap.put("reportDate", reportDate);
                procedureMap.put("factorId", "01");

                List<Map<String, Object>> wzzList = this.dao.query("DUHD62.queryGtWzzData", procedureMap);
                List<Map<String, Object>> clList = this.dao.query("DUHD62.queryGtClData", procedureMap);


                if (StrUtil.listIsNotNullOrEmpty(wzzList) || StrUtil.listIsNotNullOrEmpty(clList)) {

                    if (sheet.getRow(rowIndex + 2) != null) {
                        int lastRowNum = sheet.getLastRowNum();
                        sheet.shiftRows(rowIndex + 2, lastRowNum, 1);//12-14行整体下移一行
                    }

                    HSSFRow dataRow = sheet.createRow(rowIndex);

                    String procedureName = procedureMap.get("procedureName").toString();
                    String procedureId = procedureMap.get("procedureId").toString();
                    HSSFCell dataCell = dataRow.createCell(0);
                    dataCell.setCellValue(procedureName);
                    dataCell.setCellStyle(conCellStyle);

                    for (int k = 1; k < 26; k++) {
                        HSSFCell cell = dataRow.createCell(k);
                        cell.setCellValue("");
                        cell.setCellStyle(conCellStyle);
                    }

                    if (StrUtil.listIsNotNullOrEmpty(wzzList)) {
                        for (int j = 0; j < wzzList.size(); j++) {
                            Map<String, Object> wzzData = wzzList.get(j);
                            String procedureId2 = wzzData.get("procedureId").toString();
                            if (procedureId.equals(procedureId2)) {

                                if (wzzData.get("clock").toString().equals("01")) {
                                    HSSFCell cell = dataRow.getCell(3);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);

                                }

                                if (wzzData.get("clock").toString().equals("02")) {
                                    HSSFCell cell = dataRow.getCell(5);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("03")) {
                                    HSSFCell cell = dataRow.getCell(7);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("04")) {
                                    HSSFCell cell = dataRow.getCell(9);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("05")) {
                                    HSSFCell cell = dataRow.getCell(11);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("06")) {
                                    HSSFCell cell = dataRow.getCell(13);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("07")) {
                                    HSSFCell cell = dataRow.getCell(15);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("08")) {
                                    HSSFCell cell = dataRow.getCell(17);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("09")) {
                                    HSSFCell cell = dataRow.getCell(19);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("10")) {
                                    HSSFCell cell = dataRow.getCell(21);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("11")) {
                                    HSSFCell cell = dataRow.getCell(23);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("12")) {
                                    HSSFCell cell = dataRow.getCell(25);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }
                            }
                        }
                    }

                    if (StrUtil.listIsNotNullOrEmpty(clList)) {
                        for (int j = 0; j < clList.size(); j++) {
                            Map<String, Object> clData = clList.get(j);
                            String procedureId2 = clData.get("procedureId").toString();
                            if (procedureId.equals(procedureId2)) {

                                if (clData.get("clock").toString().equals("01")) {
                                    HSSFCell cell = dataRow.getCell(2);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);

                                }

                                if (clData.get("clock").toString().equals("02")) {
                                    HSSFCell cell = dataRow.getCell(4);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("03")) {
                                    HSSFCell cell = dataRow.getCell(6);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("04")) {
                                    HSSFCell cell = dataRow.getCell(8);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("05")) {
                                    HSSFCell cell = dataRow.getCell(10);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("06")) {
                                    HSSFCell cell = dataRow.getCell(12);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("07")) {
                                    HSSFCell cell = dataRow.getCell(14);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("08")) {
                                    HSSFCell cell = dataRow.getCell(16);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("09")) {
                                    HSSFCell cell = dataRow.getCell(18);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("10")) {
                                    HSSFCell cell = dataRow.getCell(20);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("11")) {
                                    HSSFCell cell = dataRow.getCell(22);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("12")) {
                                    HSSFCell cell = dataRow.getCell(24);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }
                            }
                        }
                    }

                    HSSFCell cell = dataRow.getCell(1);
                    int index = rowIndex + 1;
                    cell.setCellFormula("D" + index + "/0.001/C" + index);
                    cell.setCellStyle(conCellStyle);

                    rowIndex++;
                }

            }

            HSSFRow yhjRow = sheet.createRow(rowIndex);
            for (int k = 1; k < 26; k++) {
                HSSFCell cell = yhjRow.createCell(k);
                cell.setCellValue("");
                cell.setCellStyle(conCellStyle);
            }

            CellRangeAddress range = new CellRangeAddress(rowIndex, rowIndex, 0, 1);
            sheet.addMergedRegion(range);
            RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, range, sheet, wb);
            RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, range, sheet, wb);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, range, sheet, wb);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, range, sheet, wb);
            HSSFCell monthCell = yhjRow.getCell(0);
            monthCell.setCellValue("月合计");
            monthCell.setCellStyle(conCellStyle);

            int firstIndex = itemIndex + 1;
            HSSFCell cell3 = yhjRow.createCell(3);
            cell3.setCellFormula("SUM(D" + firstIndex + ":D" + rowIndex + ")");
            cell3.setCellStyle(conCellStyle);

            HSSFCell cell5 = yhjRow.createCell(5);
            cell5.setCellFormula("SUM(F" + firstIndex + ":F" + rowIndex + ")");
            cell5.setCellStyle(conCellStyle);

            HSSFCell cell7 = yhjRow.createCell(7);
            cell7.setCellFormula("SUM(H" + firstIndex + ":H" + rowIndex + ")");
            cell7.setCellStyle(conCellStyle);

            HSSFCell cell9 = yhjRow.createCell(9);
            cell9.setCellFormula("SUM(J" + firstIndex + ":J" + rowIndex + ")");
            cell9.setCellStyle(conCellStyle);

            HSSFCell cell11 = yhjRow.createCell(11);
            cell11.setCellFormula("SUM(L" + firstIndex + ":L" + rowIndex + ")");
            cell11.setCellStyle(conCellStyle);

            HSSFCell cell13 = yhjRow.createCell(13);
            cell13.setCellFormula("SUM(N" + firstIndex + ":N" + rowIndex + ")");
            cell13.setCellStyle(conCellStyle);

            HSSFCell cell15 = yhjRow.createCell(15);
            cell15.setCellFormula("SUM(P" + firstIndex + ":P" + rowIndex + ")");
            cell15.setCellStyle(conCellStyle);

            HSSFCell cell17 = yhjRow.createCell(17);
            cell17.setCellFormula("SUM(R" + firstIndex + ":R" + rowIndex + ")");
            cell17.setCellStyle(conCellStyle);

            HSSFCell cell19 = yhjRow.createCell(19);
            cell19.setCellFormula("SUM(T" + firstIndex + ":T" + rowIndex + ")");
            cell19.setCellStyle(conCellStyle);

            HSSFCell cell21 = yhjRow.createCell(21);
            cell21.setCellFormula("SUM(V" + firstIndex + ":V" + rowIndex + ")");
            cell21.setCellStyle(conCellStyle);

            HSSFCell cell23 = yhjRow.createCell(23);
            cell23.setCellFormula("SUM(X" + firstIndex + ":X" + rowIndex + ")");
            cell23.setCellStyle(conCellStyle);

            HSSFCell cell25 = yhjRow.createCell(25);
            cell25.setCellFormula("SUM(Z" + firstIndex + ":Z" + rowIndex + ")");
            cell25.setCellStyle(conCellStyle);

            HSSFRow totalRow = sheet.getRow(itemIndex);
            CellRangeAddress totalRange = new CellRangeAddress(itemIndex, rowIndex, 26, 26);
            sheet.addMergedRegion(totalRange);
            RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, totalRange, sheet, wb);
            RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, totalRange, sheet, wb);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, totalRange, sheet, wb);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, totalRange, sheet, wb);
            HSSFCell totalCell = totalRow.createCell(26);
            totalCell.setCellFormula("SUM(D" + rowIndex + ",F" + rowIndex + ",H" + rowIndex + ",J" + rowIndex + ",L" +
                    rowIndex + ",N" + rowIndex + ",P" + rowIndex + ",R" + rowIndex + ",T" + rowIndex + ",V" +
                    rowIndex + ",X" + rowIndex + ",Z" + rowIndex + ")");
            totalCell.setCellStyle(conCellStyle);
        }


        List<Map<String, Object>> ybProcedureList = this.dao.query("DUHD62.queryGtYbProcedure", pMap);
        rowIndex = rowIndex + 5;
        itemIndex = rowIndex;
        if (StrUtil.listIsNotNullOrEmpty(ybProcedureList)) {
            for (int i = 0; i < ybProcedureList.size(); i++) {
                Map<String, Object> procedureMap = ybProcedureList.get(i);

                procedureMap.put("reportDate", reportDate);
                procedureMap.put("factorId", "01");

                List<Map<String, Object>> wzzList = this.dao.query("DUHD62.queryGtYbData", procedureMap);
                List<Map<String, Object>> clList = this.dao.query("DUHD62.queryGtClData", procedureMap);


                if (StrUtil.listIsNotNullOrEmpty(wzzList) || StrUtil.listIsNotNullOrEmpty(clList)) {

                    if (sheet.getRow(rowIndex + 2) != null) {
                        int lastRowNum = sheet.getLastRowNum();
                        sheet.shiftRows(rowIndex + 2, lastRowNum, 1);//12-14行整体下移一行
                    }

                    HSSFRow dataRow = sheet.createRow(rowIndex);

                    String procedureName = procedureMap.get("procedureName").toString();
                    String procedureId = procedureMap.get("procedureId").toString();
                    HSSFCell dataCell = dataRow.createCell(0);
                    dataCell.setCellValue(procedureName);
                    dataCell.setCellStyle(conCellStyle);

                    for (int k = 1; k < 26; k++) {
                        HSSFCell cell = dataRow.createCell(k);
                        cell.setCellValue("");
                        cell.setCellStyle(conCellStyle);
                    }

                    if (StrUtil.listIsNotNullOrEmpty(wzzList)) {
                        for (int j = 0; j < wzzList.size(); j++) {
                            Map<String, Object> wzzData = wzzList.get(j);
                            String procedureId2 = wzzData.get("procedureId").toString();
                            if (procedureId.equals(procedureId2)) {

                                if (wzzData.get("clock").toString().equals("01")) {
                                    HSSFCell cell = dataRow.getCell(3);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);

                                }

                                if (wzzData.get("clock").toString().equals("02")) {
                                    HSSFCell cell = dataRow.getCell(5);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("03")) {
                                    HSSFCell cell = dataRow.getCell(7);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("04")) {
                                    HSSFCell cell = dataRow.getCell(9);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("05")) {
                                    HSSFCell cell = dataRow.getCell(11);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("06")) {
                                    HSSFCell cell = dataRow.getCell(13);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("07")) {
                                    HSSFCell cell = dataRow.getCell(15);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("08")) {
                                    HSSFCell cell = dataRow.getCell(17);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("09")) {
                                    HSSFCell cell = dataRow.getCell(19);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("10")) {
                                    HSSFCell cell = dataRow.getCell(21);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("11")) {
                                    HSSFCell cell = dataRow.getCell(23);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("12")) {
                                    HSSFCell cell = dataRow.getCell(25);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }
                            }
                        }
                    }
                    if (StrUtil.listIsNotNullOrEmpty(clList)) {
                        for (int j = 0; j < clList.size(); j++) {
                            Map<String, Object> clData = clList.get(j);
                            String procedureId2 = clData.get("procedureId").toString();
                            if (procedureId.equals(procedureId2)) {

                                if (clData.get("clock").toString().equals("01")) {
                                    HSSFCell cell = dataRow.getCell(2);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);

                                }

                                if (clData.get("clock").toString().equals("02")) {
                                    HSSFCell cell = dataRow.getCell(4);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("03")) {
                                    HSSFCell cell = dataRow.getCell(6);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("04")) {
                                    HSSFCell cell = dataRow.getCell(8);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("05")) {
                                    HSSFCell cell = dataRow.getCell(10);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("06")) {
                                    HSSFCell cell = dataRow.getCell(12);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("07")) {
                                    HSSFCell cell = dataRow.getCell(14);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("08")) {
                                    HSSFCell cell = dataRow.getCell(16);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("09")) {
                                    HSSFCell cell = dataRow.getCell(18);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("10")) {
                                    HSSFCell cell = dataRow.getCell(20);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("11")) {
                                    HSSFCell cell = dataRow.getCell(22);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("12")) {
                                    HSSFCell cell = dataRow.getCell(24);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }
                            }
                        }
                    }

                    HSSFCell cell = dataRow.getCell(1);
                    int index = rowIndex + 1;
                    cell.setCellFormula("D" + index + "/0.001/C" + index);
                    cell.setCellStyle(conCellStyle);

                    rowIndex++;
                }

            }

            HSSFRow yhjRow = sheet.createRow(rowIndex);
            for (int k = 1; k < 26; k++) {
                HSSFCell cell = yhjRow.createCell(k);
                cell.setCellValue("");
                cell.setCellStyle(conCellStyle);
            }

            CellRangeAddress range = new CellRangeAddress(rowIndex, rowIndex, 0, 1);
            sheet.addMergedRegion(range);
            RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, range, sheet, wb);
            RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, range, sheet, wb);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, range, sheet, wb);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, range, sheet, wb);
            HSSFCell monthCell = yhjRow.getCell(0);
            monthCell.setCellValue("月合计");
            monthCell.setCellStyle(conCellStyle);

            int firstIndex = itemIndex + 1;
            HSSFCell cell3 = yhjRow.createCell(3);
            cell3.setCellFormula("SUM(D" + firstIndex + ":D" + rowIndex + ")");
            cell3.setCellStyle(conCellStyle);

            HSSFCell cell5 = yhjRow.createCell(5);
            cell5.setCellFormula("SUM(F" + firstIndex + ":F" + rowIndex + ")");
            cell5.setCellStyle(conCellStyle);

            HSSFCell cell7 = yhjRow.createCell(7);
            cell7.setCellFormula("SUM(H" + firstIndex + ":H" + rowIndex + ")");
            cell7.setCellStyle(conCellStyle);

            HSSFCell cell9 = yhjRow.createCell(9);
            cell9.setCellFormula("SUM(J" + firstIndex + ":J" + rowIndex + ")");
            cell9.setCellStyle(conCellStyle);

            HSSFCell cell11 = yhjRow.createCell(11);
            cell11.setCellFormula("SUM(L" + firstIndex + ":L" + rowIndex + ")");
            cell11.setCellStyle(conCellStyle);

            HSSFCell cell13 = yhjRow.createCell(13);
            cell13.setCellFormula("SUM(N" + firstIndex + ":N" + rowIndex + ")");
            cell13.setCellStyle(conCellStyle);

            HSSFCell cell15 = yhjRow.createCell(15);
            cell15.setCellFormula("SUM(P" + firstIndex + ":P" + rowIndex + ")");
            cell15.setCellStyle(conCellStyle);

            HSSFCell cell17 = yhjRow.createCell(17);
            cell17.setCellFormula("SUM(R" + firstIndex + ":R" + rowIndex + ")");
            cell17.setCellStyle(conCellStyle);

            HSSFCell cell19 = yhjRow.createCell(19);
            cell19.setCellFormula("SUM(T" + firstIndex + ":T" + rowIndex + ")");
            cell19.setCellStyle(conCellStyle);

            HSSFCell cell21 = yhjRow.createCell(21);
            cell21.setCellFormula("SUM(V" + firstIndex + ":V" + rowIndex + ")");
            cell21.setCellStyle(conCellStyle);

            HSSFCell cell23 = yhjRow.createCell(23);
            cell23.setCellFormula("SUM(X" + firstIndex + ":X" + rowIndex + ")");
            cell23.setCellStyle(conCellStyle);

            HSSFCell cell25 = yhjRow.createCell(25);
            cell25.setCellFormula("SUM(Z" + firstIndex + ":Z" + rowIndex + ")");
            cell25.setCellStyle(conCellStyle);

            HSSFRow totalRow = sheet.getRow(itemIndex);
            CellRangeAddress totalRange = new CellRangeAddress(itemIndex, rowIndex, 26, 26);
            sheet.addMergedRegion(totalRange);
            RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, totalRange, sheet, wb);
            RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, totalRange, sheet, wb);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, totalRange, sheet, wb);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, totalRange, sheet, wb);
            HSSFCell totalCell = totalRow.createCell(26);
            totalCell.setCellFormula("SUM(D" + rowIndex + ",F" + rowIndex + ",H" + rowIndex + ",J" + rowIndex + ",L" +
                    rowIndex + ",N" + rowIndex + ",P" + rowIndex + ",R" + rowIndex + ",T" + rowIndex + ",V" +
                    rowIndex + ",X" + rowIndex + ",Z" + rowIndex + ")");
            totalCell.setCellStyle(conCellStyle);
        }


        Map<String, Object> pMap2 = new HashMap<>();
        pMap2.put("reportDate", reportDate);
        pMap2.put("dept", dept);
        pMap2.put("monitorid", "01");
        pMap2.put("factorId", "02");
        List<Map<String, Object>> ybProcedureList2 = this.dao.query("DUHD62.queryGtYbProcedure", pMap2);
        rowIndex = rowIndex + 4;
        itemIndex = rowIndex;
        if (StrUtil.listIsNotNullOrEmpty(ybProcedureList2)) {
            for (int i = 0; i < ybProcedureList2.size(); i++) {
                Map<String, Object> procedureMap = ybProcedureList2.get(i);

                procedureMap.put("reportDate", reportDate);
                procedureMap.put("factorId", "02");

                List<Map<String, Object>> wzzList = this.dao.query("DUHD62.queryGtYbData", procedureMap);
                List<Map<String, Object>> clList = this.dao.query("DUHD62.queryGtClData", procedureMap);


                if (StrUtil.listIsNotNullOrEmpty(wzzList) || StrUtil.listIsNotNullOrEmpty(clList)) {

                    if (sheet.getRow(rowIndex + 2) != null) {
                        int lastRowNum = sheet.getLastRowNum();
                        sheet.shiftRows(rowIndex + 2, lastRowNum, 1);//12-14行整体下移一行
                    }

                    HSSFRow dataRow = sheet.createRow(rowIndex);

                    String procedureName = procedureMap.get("procedureName").toString();
                    String procedureId = procedureMap.get("procedureId").toString();
                    HSSFCell dataCell = dataRow.createCell(0);
                    dataCell.setCellValue(procedureName);
                    dataCell.setCellStyle(conCellStyle);

                    for (int k = 1; k < 26; k++) {
                        HSSFCell cell = dataRow.createCell(k);
                        cell.setCellValue("");
                        cell.setCellStyle(conCellStyle);
                    }

                    if (StrUtil.listIsNotNullOrEmpty(wzzList)) {
                        for (int j = 0; j < wzzList.size(); j++) {
                            Map<String, Object> wzzData = wzzList.get(j);
                            String procedureId2 = wzzData.get("procedureId").toString();
                            if (procedureId.equals(procedureId2)) {

                                if (wzzData.get("clock").toString().equals("01")) {
                                    HSSFCell cell = dataRow.getCell(3);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);

                                }

                                if (wzzData.get("clock").toString().equals("02")) {
                                    HSSFCell cell = dataRow.getCell(5);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("03")) {
                                    HSSFCell cell = dataRow.getCell(7);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("04")) {
                                    HSSFCell cell = dataRow.getCell(9);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("05")) {
                                    HSSFCell cell = dataRow.getCell(11);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("06")) {
                                    HSSFCell cell = dataRow.getCell(13);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("07")) {
                                    HSSFCell cell = dataRow.getCell(15);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("08")) {
                                    HSSFCell cell = dataRow.getCell(17);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("09")) {
                                    HSSFCell cell = dataRow.getCell(19);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("10")) {
                                    HSSFCell cell = dataRow.getCell(21);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("11")) {
                                    HSSFCell cell = dataRow.getCell(23);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("12")) {
                                    HSSFCell cell = dataRow.getCell(25);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }
                            }
                        }
                    }
                    if (StrUtil.listIsNotNullOrEmpty(clList)) {
                        for (int j = 0; j < clList.size(); j++) {
                            Map<String, Object> clData = clList.get(j);
                            String procedureId2 = clData.get("procedureId").toString();
                            if (procedureId.equals(procedureId2)) {

                                if (clData.get("clock").toString().equals("01")) {
                                    HSSFCell cell = dataRow.getCell(2);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);

                                }

                                if (clData.get("clock").toString().equals("02")) {
                                    HSSFCell cell = dataRow.getCell(4);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("03")) {
                                    HSSFCell cell = dataRow.getCell(6);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("04")) {
                                    HSSFCell cell = dataRow.getCell(8);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("05")) {
                                    HSSFCell cell = dataRow.getCell(10);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("06")) {
                                    HSSFCell cell = dataRow.getCell(12);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("07")) {
                                    HSSFCell cell = dataRow.getCell(14);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("08")) {
                                    HSSFCell cell = dataRow.getCell(16);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("09")) {
                                    HSSFCell cell = dataRow.getCell(18);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("10")) {
                                    HSSFCell cell = dataRow.getCell(20);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("11")) {
                                    HSSFCell cell = dataRow.getCell(22);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("12")) {
                                    HSSFCell cell = dataRow.getCell(24);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }
                            }
                        }
                    }

                    HSSFCell cell = dataRow.getCell(1);
                    int index = rowIndex + 1;
                    cell.setCellFormula("D" + index + "/0.001/C" + index);
                    cell.setCellStyle(conCellStyle);

                    rowIndex++;
                }

            }

            HSSFRow yhjRow = sheet.createRow(rowIndex);
            for (int k = 1; k < 26; k++) {
                HSSFCell cell = yhjRow.createCell(k);
                cell.setCellValue("");
                cell.setCellStyle(conCellStyle);
            }

            CellRangeAddress range = new CellRangeAddress(rowIndex, rowIndex, 0, 1);
            sheet.addMergedRegion(range);
            RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, range, sheet, wb);
            RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, range, sheet, wb);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, range, sheet, wb);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, range, sheet, wb);
            HSSFCell monthCell = yhjRow.getCell(0);
            monthCell.setCellValue("月合计");
            monthCell.setCellStyle(conCellStyle);

            int firstIndex = itemIndex + 1;
            HSSFCell cell3 = yhjRow.createCell(3);
            cell3.setCellFormula("SUM(D" + firstIndex + ":D" + rowIndex + ")");
            cell3.setCellStyle(conCellStyle);

            HSSFCell cell5 = yhjRow.createCell(5);
            cell5.setCellFormula("SUM(F" + firstIndex + ":F" + rowIndex + ")");
            cell5.setCellStyle(conCellStyle);

            HSSFCell cell7 = yhjRow.createCell(7);
            cell7.setCellFormula("SUM(H" + firstIndex + ":H" + rowIndex + ")");
            cell7.setCellStyle(conCellStyle);

            HSSFCell cell9 = yhjRow.createCell(9);
            cell9.setCellFormula("SUM(J" + firstIndex + ":J" + rowIndex + ")");
            cell9.setCellStyle(conCellStyle);

            HSSFCell cell11 = yhjRow.createCell(11);
            cell11.setCellFormula("SUM(L" + firstIndex + ":L" + rowIndex + ")");
            cell11.setCellStyle(conCellStyle);

            HSSFCell cell13 = yhjRow.createCell(13);
            cell13.setCellFormula("SUM(N" + firstIndex + ":N" + rowIndex + ")");
            cell13.setCellStyle(conCellStyle);

            HSSFCell cell15 = yhjRow.createCell(15);
            cell15.setCellFormula("SUM(P" + firstIndex + ":P" + rowIndex + ")");
            cell15.setCellStyle(conCellStyle);

            HSSFCell cell17 = yhjRow.createCell(17);
            cell17.setCellFormula("SUM(R" + firstIndex + ":R" + rowIndex + ")");
            cell17.setCellStyle(conCellStyle);

            HSSFCell cell19 = yhjRow.createCell(19);
            cell19.setCellFormula("SUM(T" + firstIndex + ":T" + rowIndex + ")");
            cell19.setCellStyle(conCellStyle);

            HSSFCell cell21 = yhjRow.createCell(21);
            cell21.setCellFormula("SUM(V" + firstIndex + ":V" + rowIndex + ")");
            cell21.setCellStyle(conCellStyle);

            HSSFCell cell23 = yhjRow.createCell(23);
            cell23.setCellFormula("SUM(X" + firstIndex + ":X" + rowIndex + ")");
            cell23.setCellStyle(conCellStyle);

            HSSFCell cell25 = yhjRow.createCell(25);
            cell25.setCellFormula("SUM(Z" + firstIndex + ":Z" + rowIndex + ")");
            cell25.setCellStyle(conCellStyle);

            HSSFRow totalRow = sheet.getRow(itemIndex);
            CellRangeAddress totalRange = new CellRangeAddress(itemIndex, rowIndex, 26, 26);
            sheet.addMergedRegion(totalRange);
            RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, totalRange, sheet, wb);
            RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, totalRange, sheet, wb);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, totalRange, sheet, wb);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, totalRange, sheet, wb);
            HSSFCell totalCell = totalRow.createCell(26);
            totalCell.setCellFormula("SUM(D" + rowIndex + ",F" + rowIndex + ",H" + rowIndex + ",J" + rowIndex + ",L" +
                    rowIndex + ",N" + rowIndex + ",P" + rowIndex + ",R" + rowIndex + ",T" + rowIndex + ",V" +
                    rowIndex + ",X" + rowIndex + ",Z" + rowIndex + ")");
            totalCell.setCellStyle(conCellStyle);
        }


        Map<String, Object> pMap3 = new HashMap<>();
        pMap3.put("reportDate", reportDate);
        pMap3.put("dept", dept);
        pMap3.put("monitorid", "01");
        pMap3.put("factorId", "03");
        List<Map<String, Object>> ybProcedureList3 = this.dao.query("DUHD62.queryGtYbProcedure", pMap3);
        rowIndex = rowIndex + 4;
        itemIndex = rowIndex;
        if (StrUtil.listIsNotNullOrEmpty(ybProcedureList3)) {
            for (int i = 0; i < ybProcedureList3.size(); i++) {
                Map<String, Object> procedureMap = ybProcedureList3.get(i);

                procedureMap.put("reportDate", reportDate);
                procedureMap.put("factorId", "03");

                List<Map<String, Object>> wzzList = this.dao.query("DUHD62.queryGtYbData", procedureMap);
                List<Map<String, Object>> clList = this.dao.query("DUHD62.queryGtClData", procedureMap);


                if (StrUtil.listIsNotNullOrEmpty(wzzList) || StrUtil.listIsNotNullOrEmpty(clList)) {

                    if (sheet.getRow(rowIndex + 2) != null) {
                        int lastRowNum = sheet.getLastRowNum();
                        sheet.shiftRows(rowIndex + 2, lastRowNum, 1);//12-14行整体下移一行
                    }

                    HSSFRow dataRow = sheet.createRow(rowIndex);

                    String procedureName = procedureMap.get("procedureName").toString();
                    String procedureId = procedureMap.get("procedureId").toString();
                    HSSFCell dataCell = dataRow.createCell(0);
                    dataCell.setCellValue(procedureName);
                    dataCell.setCellStyle(conCellStyle);

                    for (int k = 1; k < 26; k++) {
                        HSSFCell cell = dataRow.createCell(k);
                        cell.setCellValue("");
                        cell.setCellStyle(conCellStyle);
                    }

                    if (StrUtil.listIsNotNullOrEmpty(wzzList)) {
                        for (int j = 0; j < wzzList.size(); j++) {
                            Map<String, Object> wzzData = wzzList.get(j);
                            String procedureId2 = wzzData.get("procedureId").toString();
                            if (procedureId.equals(procedureId2)) {

                                if (wzzData.get("clock").toString().equals("01")) {
                                    HSSFCell cell = dataRow.getCell(3);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);

                                }

                                if (wzzData.get("clock").toString().equals("02")) {
                                    HSSFCell cell = dataRow.getCell(5);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("03")) {
                                    HSSFCell cell = dataRow.getCell(7);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("04")) {
                                    HSSFCell cell = dataRow.getCell(9);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("05")) {
                                    HSSFCell cell = dataRow.getCell(11);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("06")) {
                                    HSSFCell cell = dataRow.getCell(13);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("07")) {
                                    HSSFCell cell = dataRow.getCell(15);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("08")) {
                                    HSSFCell cell = dataRow.getCell(17);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("09")) {
                                    HSSFCell cell = dataRow.getCell(19);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("10")) {
                                    HSSFCell cell = dataRow.getCell(21);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("11")) {
                                    HSSFCell cell = dataRow.getCell(23);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (wzzData.get("clock").toString().equals("12")) {
                                    HSSFCell cell = dataRow.getCell(25);
                                    cell.setCellValue(Double.parseDouble(wzzData.get("finalvalue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }
                            }
                        }
                    }
                    if (StrUtil.listIsNotNullOrEmpty(clList)) {
                        for (int j = 0; j < clList.size(); j++) {
                            Map<String, Object> clData = clList.get(j);
                            String procedureId2 = clData.get("procedureId").toString();
                            if (procedureId.equals(procedureId2)) {

                                if (clData.get("clock").toString().equals("01")) {
                                    HSSFCell cell = dataRow.getCell(2);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);

                                }

                                if (clData.get("clock").toString().equals("02")) {
                                    HSSFCell cell = dataRow.getCell(4);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("03")) {
                                    HSSFCell cell = dataRow.getCell(6);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("04")) {
                                    HSSFCell cell = dataRow.getCell(8);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("05")) {
                                    HSSFCell cell = dataRow.getCell(10);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("06")) {
                                    HSSFCell cell = dataRow.getCell(12);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("07")) {
                                    HSSFCell cell = dataRow.getCell(14);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("08")) {
                                    HSSFCell cell = dataRow.getCell(16);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("09")) {
                                    HSSFCell cell = dataRow.getCell(18);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("10")) {
                                    HSSFCell cell = dataRow.getCell(20);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("11")) {
                                    HSSFCell cell = dataRow.getCell(22);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }

                                if (clData.get("clock").toString().equals("12")) {
                                    HSSFCell cell = dataRow.getCell(24);
                                    cell.setCellValue(Double.parseDouble(clData.get("itemValue").toString()));
                                    cell.setCellStyle(conCellStyle);
                                }
                            }
                        }
                    }

                    HSSFCell cell = dataRow.getCell(1);
                    int index = rowIndex + 1;
                    cell.setCellFormula("D" + index + "/0.001/C" + index);
                    cell.setCellStyle(conCellStyle);

                    rowIndex++;
                }

            }

            HSSFRow yhjRow = sheet.createRow(rowIndex);
            for (int k = 1; k < 26; k++) {
                HSSFCell cell = yhjRow.createCell(k);
                cell.setCellValue("");
                cell.setCellStyle(conCellStyle);
            }

            CellRangeAddress range = new CellRangeAddress(rowIndex, rowIndex, 0, 1);
            sheet.addMergedRegion(range);
            RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, range, sheet, wb);
            RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, range, sheet, wb);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, range, sheet, wb);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, range, sheet, wb);
            HSSFCell monthCell = yhjRow.getCell(0);
            monthCell.setCellValue("月合计");
            monthCell.setCellStyle(conCellStyle);

            int firstIndex = itemIndex + 1;
            HSSFCell cell3 = yhjRow.createCell(3);
            cell3.setCellFormula("SUM(D" + firstIndex + ":D" + rowIndex + ")");
            cell3.setCellStyle(conCellStyle);

            HSSFCell cell5 = yhjRow.createCell(5);
            cell5.setCellFormula("SUM(F" + firstIndex + ":F" + rowIndex + ")");
            cell5.setCellStyle(conCellStyle);

            HSSFCell cell7 = yhjRow.createCell(7);
            cell7.setCellFormula("SUM(H" + firstIndex + ":H" + rowIndex + ")");
            cell7.setCellStyle(conCellStyle);

            HSSFCell cell9 = yhjRow.createCell(9);
            cell9.setCellFormula("SUM(J" + firstIndex + ":J" + rowIndex + ")");
            cell9.setCellStyle(conCellStyle);

            HSSFCell cell11 = yhjRow.createCell(11);
            cell11.setCellFormula("SUM(L" + firstIndex + ":L" + rowIndex + ")");
            cell11.setCellStyle(conCellStyle);

            HSSFCell cell13 = yhjRow.createCell(13);
            cell13.setCellFormula("SUM(N" + firstIndex + ":N" + rowIndex + ")");
            cell13.setCellStyle(conCellStyle);

            HSSFCell cell15 = yhjRow.createCell(15);
            cell15.setCellFormula("SUM(P" + firstIndex + ":P" + rowIndex + ")");
            cell15.setCellStyle(conCellStyle);

            HSSFCell cell17 = yhjRow.createCell(17);
            cell17.setCellFormula("SUM(R" + firstIndex + ":R" + rowIndex + ")");
            cell17.setCellStyle(conCellStyle);

            HSSFCell cell19 = yhjRow.createCell(19);
            cell19.setCellFormula("SUM(T" + firstIndex + ":T" + rowIndex + ")");
            cell19.setCellStyle(conCellStyle);

            HSSFCell cell21 = yhjRow.createCell(21);
            cell21.setCellFormula("SUM(V" + firstIndex + ":V" + rowIndex + ")");
            cell21.setCellStyle(conCellStyle);

            HSSFCell cell23 = yhjRow.createCell(23);
            cell23.setCellFormula("SUM(X" + firstIndex + ":X" + rowIndex + ")");
            cell23.setCellStyle(conCellStyle);

            HSSFCell cell25 = yhjRow.createCell(25);
            cell25.setCellFormula("SUM(Z" + firstIndex + ":Z" + rowIndex + ")");
            cell25.setCellStyle(conCellStyle);

            HSSFRow totalRow = sheet.getRow(itemIndex);
            CellRangeAddress totalRange = new CellRangeAddress(itemIndex, rowIndex, 26, 26);
            sheet.addMergedRegion(totalRange);
            RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, totalRange, sheet, wb);
            RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, totalRange, sheet, wb);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, totalRange, sheet, wb);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, totalRange, sheet, wb);
            HSSFCell totalCell = totalRow.createCell(26);
            totalCell.setCellFormula("SUM(D" + rowIndex + ",F" + rowIndex + ",H" + rowIndex + ",J" + rowIndex + ",L" +
                    rowIndex + ",N" + rowIndex + ",P" + rowIndex + ",R" + rowIndex + ",T" + rowIndex + ",V" +
                    rowIndex + ",X" + rowIndex + ",Z" + rowIndex + ")");
            totalCell.setCellStyle(conCellStyle);
        }


        Map<String, Object> pMap4 = new HashMap<>();
        pMap4.put("reportDate", reportDate);
        pMap4.put("dept", dept);
        pMap4.put("monitorid", "01");
        List<Map<String, Object>> dischargePortList = this.dao.query("DUHD62.queryGtZyDischargePort", pMap4);
        rowIndex = rowIndex + 5;
        itemIndex = rowIndex;
        int itemIndex2 = rowIndex;
        if (StrUtil.listIsNotNullOrEmpty(dischargePortList)) {
            for (int i = 0; i < dischargePortList.size(); i++) {
                Map<String, Object> dischargePort = dischargePortList.get(i);
                dischargePort.put("reportDate", reportDate);

                List<Map<String, Object>> factorList = this.dao.query("DUHD62.queryGtZyFactor", dischargePort);
                List<Map<String, Object>> dataList = this.dao.query("DUHD62.queryGtZyData", dischargePort);


                if (StrUtil.listIsNotNullOrEmpty(factorList)) {
                    for (int j = 0; j < factorList.size(); j++) {
                        Map<String, Object> factorMap = factorList.get(j);
                        String factorName = factorMap.get("factorName").toString();

                        HSSFRow dataRow = sheet.createRow(rowIndex);

                        HSSFCell dataCell = dataRow.createCell(2);
                        dataCell.setCellValue(factorName);
                        dataCell.setCellStyle(conCellStyle);

                        for (int k = 3; k < 39; k++) {
                            HSSFCell cell = dataRow.createCell(k);
                            cell.setCellValue("");
                            cell.setCellStyle(conCellStyle);
                        }

                        if (StrUtil.listIsNotNullOrEmpty(dataList)) {
                            for (int l = 0; l < dataList.size(); l++) {
                                Map<String, Object> data = dataList.get(l);
                                String factorName2 = data.get("factorname").toString();
                                if (factorName.equals(factorName2)) {

                                    if (data.get("clock").toString().equals("01")) {

                                        HSSFCell dataCell2 = dataRow.getCell(3);
                                        dataCell2.setCellValue(Double.parseDouble(data.get("totalfeiqi").toString()));
                                        dataCell2.setCellStyle(conCellStyle);

                                        HSSFCell dataCell3 = dataRow.getCell(4);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(5);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("02")) {
                                        HSSFCell dataCell2 = dataRow.getCell(6);
                                        dataCell2.setCellValue(Double.parseDouble(data.get("totalfeiqi").toString()));
                                        dataCell2.setCellStyle(conCellStyle);

                                        HSSFCell dataCell3 = dataRow.getCell(7);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(8);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("03")) {
                                        HSSFCell dataCell2 = dataRow.getCell(9);
                                        dataCell2.setCellValue(Double.parseDouble(data.get("totalfeiqi").toString()));
                                        dataCell2.setCellStyle(conCellStyle);

                                        HSSFCell dataCell3 = dataRow.getCell(10);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(11);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("04")) {
                                        HSSFCell dataCell2 = dataRow.getCell(12);
                                        dataCell2.setCellValue(Double.parseDouble(data.get("totalfeiqi").toString()));
                                        dataCell2.setCellStyle(conCellStyle);

                                        HSSFCell dataCell3 = dataRow.getCell(13);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(14);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("05")) {
                                        HSSFCell dataCell2 = dataRow.getCell(15);
                                        dataCell2.setCellValue(Double.parseDouble(data.get("totalfeiqi").toString()));
                                        dataCell2.setCellStyle(conCellStyle);

                                        HSSFCell dataCell3 = dataRow.getCell(16);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(17);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("06")) {
                                        HSSFCell dataCell2 = dataRow.getCell(18);
                                        dataCell2.setCellValue(Double.parseDouble(data.get("totalfeiqi").toString()));
                                        dataCell2.setCellStyle(conCellStyle);

                                        HSSFCell dataCell3 = dataRow.getCell(19);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(20);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("07")) {
                                        HSSFCell dataCell2 = dataRow.getCell(21);
                                        dataCell2.setCellValue(Double.parseDouble(data.get("totalfeiqi").toString()));
                                        dataCell2.setCellStyle(conCellStyle);

                                        HSSFCell dataCell3 = dataRow.getCell(22);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(23);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("08")) {
                                        HSSFCell dataCell2 = dataRow.getCell(24);
                                        dataCell2.setCellValue(Double.parseDouble(data.get("totalfeiqi").toString()));
                                        dataCell2.setCellStyle(conCellStyle);

                                        HSSFCell dataCell3 = dataRow.getCell(25);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(26);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("09")) {
                                        HSSFCell dataCell2 = dataRow.getCell(27);
                                        dataCell2.setCellValue(Double.parseDouble(data.get("totalfeiqi").toString()));
                                        dataCell2.setCellStyle(conCellStyle);

                                        HSSFCell dataCell3 = dataRow.getCell(28);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(29);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("10")) {
                                        HSSFCell dataCell2 = dataRow.getCell(30);
                                        dataCell2.setCellValue(Double.parseDouble(data.get("totalfeiqi").toString()));
                                        dataCell2.setCellStyle(conCellStyle);

                                        HSSFCell dataCell3 = dataRow.getCell(31);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(32);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("11")) {
                                        HSSFCell dataCell2 = dataRow.getCell(33);
                                        dataCell2.setCellValue(Double.parseDouble(data.get("totalfeiqi").toString()));
                                        dataCell2.setCellStyle(conCellStyle);

                                        HSSFCell dataCell3 = dataRow.getCell(34);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(35);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("12")) {
                                        HSSFCell dataCell2 = dataRow.getCell(36);
                                        dataCell2.setCellValue(Double.parseDouble(data.get("totalfeiqi").toString()));
                                        dataCell2.setCellStyle(conCellStyle);

                                        HSSFCell dataCell3 = dataRow.getCell(37);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell3.setCellStyle(conCellStyle);

                                        HSSFCell dataCell4 = dataRow.getCell(38);
                                        dataCell4.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell4.setCellStyle(conCellStyle);
                                    }
                                }
                            }
                        }

                        HSSFCell cell = dataRow.createCell(39);
                        int index = rowIndex + 1;
                        cell.setCellFormula("SUM(F" + index + ",I" + index + ",L" + index + ",O" + index + ",R" +
                                index + ",U" + index + ",X" + index + ",AA" + index + ",AD" + index + ",AG" +
                                index + ",AJ" + index + ",AM" + index + ")");
                        cell.setCellStyle(conCellStyle);

                        rowIndex++;
                    }

                    HSSFRow dataRow = sheet.getRow(itemIndex);

                    CellRangeAddress rowRangeAddress = new CellRangeAddress(itemIndex, rowIndex - 1, 0, 0);
                    sheet.addMergedRegion(rowRangeAddress);
                    RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                    RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                    RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                    RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                    HSSFCell dataCell = dataRow.createCell(0);
                    dataCell.setCellValue(dischargePort.get("dischargePortCode").toString());
                    dataCell.setCellStyle(conCellStyle);

                    CellRangeAddress rowRangeAddress2 = new CellRangeAddress(itemIndex, rowIndex - 1, 1, 1);
                    sheet.addMergedRegion(rowRangeAddress2);
                    RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                    RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                    RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                    RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                    HSSFCell dataCell2 = dataRow.createCell(1);
                    dataCell2.setCellValue(dischargePort.get("dischargePortName").toString());
                    dataCell2.setCellStyle(conCellStyle);

                    itemIndex = rowIndex;
                }


            }

            HSSFRow dataRow = sheet.getRow(itemIndex2);
            CellRangeAddress rowRangeAddress = new CellRangeAddress(itemIndex2, rowIndex - 1, 40, 40);
            sheet.addMergedRegion(rowRangeAddress);
            RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
            RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);

            CellRangeAddress rowRangeAddress2 = new CellRangeAddress(itemIndex2, rowIndex - 1, 41, 41);
            sheet.addMergedRegion(rowRangeAddress2);
            RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
            RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);

            CellRangeAddress rowRangeAddress3 = new CellRangeAddress(itemIndex2, rowIndex - 1, 42, 42);
            sheet.addMergedRegion(rowRangeAddress3);
            RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress3, sheet, wb);
            RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress3, sheet, wb);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress3, sheet, wb);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress3, sheet, wb);

            List<Map<String, Object>> zyList = this.dao.query("DUHD62.queryGtZyTotal", pMap4);

            if (StrUtil.listIsNotNullOrEmpty(zyList)) {
                for (int i = 0; i < zyList.size(); i++) {
                    Map<String, Object> zyMap = zyList.get(i);

                    if (zyMap.get("factorid").toString().equals("01")) {
                        HSSFCell dataCell = dataRow.createCell(40);
                        dataCell.setCellValue(Double.parseDouble(zyMap.get("finalValue").toString()));
                        dataCell.setCellStyle(conCellStyle);
                    }

                    if (zyMap.get("factorid").toString().equals("02")) {
                        HSSFCell dataCell = dataRow.createCell(41);
                        dataCell.setCellValue(Double.parseDouble(zyMap.get("finalValue").toString()));
                        dataCell.setCellStyle(conCellStyle);
                    }

                    if (zyMap.get("factorid").toString().equals("03")) {
                        HSSFCell dataCell = dataRow.createCell(42);
                        dataCell.setCellValue(Double.parseDouble(zyMap.get("finalValue").toString()));
                        dataCell.setCellStyle(conCellStyle);
                    }
                }
            }


            List<String> factors = new ArrayList<>();
            factors.add("颗粒物");
            factors.add("二氧化硫");
            factors.add("氮氧化物");

            for (int i = 0; i < factors.size(); i++) {
                String factorName = factors.get(i);
                Map<String, Object> map = new HashMap<>();
                map.put("reportDate", reportDate);
                map.put("dept", dept);
                map.put("monitorid", "01");
                map.put("factorName", factorName);
                List<Map<String, Object>> monthTotal = this.dao.query("DUHD62.queryGtZyTotal2", map);

                HSSFRow totalRow = sheet.createRow(rowIndex);
                for (int k = 3; k < 39; k++) {
                    HSSFCell cell = totalRow.createCell(k);
                    cell.setCellValue("");
                    cell.setCellStyle(conCellStyle);
                }

                HSSFCell dataCell = totalRow.createCell(2);
                dataCell.setCellValue(factorName);
                dataCell.setCellStyle(conCellStyle);

                if (StrUtil.listIsNotNullOrEmpty(monthTotal)) {
                    for (int j = 0; j < monthTotal.size(); j++) {
                        Map<String, Object> ljTotal = monthTotal.get(j);

                        if (ljTotal.get("clock").toString().equals("01")) {
                            HSSFCell dataCell1 = totalRow.getCell(5);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                        if (ljTotal.get("clock").toString().equals("02")) {
                            HSSFCell dataCell1 = totalRow.getCell(8);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                        if (ljTotal.get("clock").toString().equals("03")) {
                            HSSFCell dataCell1 = totalRow.getCell(11);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                        if (ljTotal.get("clock").toString().equals("04")) {
                            HSSFCell dataCell1 = totalRow.getCell(14);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                        if (ljTotal.get("clock").toString().equals("05")) {
                            HSSFCell dataCell1 = totalRow.getCell(17);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                        if (ljTotal.get("clock").toString().equals("06")) {
                            HSSFCell dataCell1 = totalRow.getCell(20);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                        if (ljTotal.get("clock").toString().equals("07")) {
                            HSSFCell dataCell1 = totalRow.getCell(23);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                        if (ljTotal.get("clock").toString().equals("08")) {
                            HSSFCell dataCell1 = totalRow.getCell(26);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                        if (ljTotal.get("clock").toString().equals("09")) {
                            HSSFCell dataCell1 = totalRow.getCell(29);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                        if (ljTotal.get("clock").toString().equals("10")) {
                            HSSFCell dataCell1 = totalRow.getCell(32);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                        if (ljTotal.get("clock").toString().equals("11")) {
                            HSSFCell dataCell1 = totalRow.getCell(35);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                        if (ljTotal.get("clock").toString().equals("12")) {
                            HSSFCell dataCell1 = totalRow.getCell(38);
                            dataCell1.setCellValue(Double.parseDouble(ljTotal.get("finalValue").toString()));
                            dataCell1.setCellStyle(conCellStyle);
                        }
                    }
                }
                rowIndex++;
            }

            HSSFRow totalRow2 = sheet.getRow(itemIndex);
            CellRangeAddress rowRange = new CellRangeAddress(itemIndex, rowIndex - 1, 0, 1);
            sheet.addMergedRegion(rowRange);
            RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRange, sheet, wb);
            RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRange, sheet, wb);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRange, sheet, wb);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRange, sheet, wb);
            HSSFCell totalCell = totalRow2.getCell(0);
            totalCell.setCellValue("月合计");
            totalCell.setCellStyle(conCellStyle);
        }

    }

    /**
     * 韶钢污染物排放量统计表--水污染物
     */
    public void saveSheet_SWRW(HSSFSheet sheet, HSSFWorkbook wb, EiInfo inInfo) {
        String reportDate = inInfo.getString("reportDate");

        HSSFDataFormat format = wb.createDataFormat();

        //内容样式
        HSSFCellStyle conCellStyle = wb.createCellStyle();
        conCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        conCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        conCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        conCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        conCellStyle.setDataFormat(format.getFormat("0.00_ "));
        conCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        conCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        conCellStyle.setWrapText(true);
        HSSFFont conFont = wb.createFont();
        conFont.setFontHeightInPoints((short) 10);
        conFont.setFontName("宋体");
        conCellStyle.setFont(conFont);

        Map<String, String> pMap = new HashMap<>();
        pMap.put("reportDate", reportDate);
        pMap.put("monitorname", "废水");

        List<Map<String, Object>> list = this.dao.query("DUHD62.queryDischargePort", pMap);

        int rowIndex = 3;
        int itemIndex = 3;
        if (StrUtil.listIsNotNullOrEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> dischargePort = list.get(i);
                dischargePort.put("reportDate", reportDate);

                List<Map<String, Object>> factorList = this.dao.query("DUHD62.queryTotalFactor", dischargePort);
                List<Map<String, Object>> dataList = this.dao.query("DUHD62.querySWRW", dischargePort);


                if (StrUtil.listIsNotNullOrEmpty(factorList)) {
                    for (int j = 0; j < factorList.size(); j++) {
                        Map<String, Object> factorMap = factorList.get(j);
                        String factorName = factorMap.get("factorName").toString();

                        HSSFRow dataRow = sheet.createRow(rowIndex);

                        HSSFCell dataCell = dataRow.createCell(2);
                        dataCell.setCellValue(factorName);
                        dataCell.setCellStyle(conCellStyle);

                        for (int k = 3; k < 39; k++) {
                            HSSFCell cell = dataRow.createCell(k);
                            cell.setCellValue("");
                            cell.setCellStyle(conCellStyle);
                        }

                        if (StrUtil.listIsNotNullOrEmpty(dataList)) {
                            for (int l = 0; l < dataList.size(); l++) {
                                Map<String, Object> data = dataList.get(l);
                                String factorName2 = data.get("factorname").toString();
                                if (factorName.equals(factorName2)) {

                                    if (data.get("clock").toString().equals("01")) {
                                        HSSFCell dataCell1 = dataRow.getCell(3);
                                        dataCell1.setCellValue(Double.parseDouble(data.get("totalfeiqi").toString()));
                                        dataCell1.setCellStyle(conCellStyle);

                                        HSSFCell dataCell2 = dataRow.getCell(4);
                                        dataCell2.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell2.setCellStyle(conCellStyle);

                                        HSSFCell dataCell3 = dataRow.getCell(5);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell3.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("02")) {
                                        HSSFCell dataCell1 = dataRow.getCell(6);
                                        dataCell1.setCellValue(Double.parseDouble(data.get("totalfeiqi").toString()));
                                        dataCell1.setCellStyle(conCellStyle);

                                        HSSFCell dataCell2 = dataRow.getCell(7);
                                        dataCell2.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell2.setCellStyle(conCellStyle);

                                        HSSFCell dataCell3 = dataRow.getCell(8);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell3.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("03")) {
                                        HSSFCell dataCell1 = dataRow.getCell(9);
                                        dataCell1.setCellValue(Double.parseDouble(data.get("totalfeiqi").toString()));
                                        dataCell1.setCellStyle(conCellStyle);

                                        HSSFCell dataCell2 = dataRow.getCell(10);
                                        dataCell2.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell2.setCellStyle(conCellStyle);

                                        HSSFCell dataCell3 = dataRow.getCell(11);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell3.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("04")) {
                                        HSSFCell dataCell1 = dataRow.getCell(12);
                                        dataCell1.setCellValue(Double.parseDouble(data.get("totalfeiqi").toString()));
                                        dataCell1.setCellStyle(conCellStyle);

                                        HSSFCell dataCell2 = dataRow.getCell(13);
                                        dataCell2.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell2.setCellStyle(conCellStyle);

                                        HSSFCell dataCell3 = dataRow.getCell(14);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell3.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("05")) {
                                        HSSFCell dataCell1 = dataRow.getCell(15);
                                        dataCell1.setCellValue(Double.parseDouble(data.get("totalfeiqi").toString()));
                                        dataCell1.setCellStyle(conCellStyle);

                                        HSSFCell dataCell2 = dataRow.getCell(16);
                                        dataCell2.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell2.setCellStyle(conCellStyle);

                                        HSSFCell dataCell3 = dataRow.getCell(17);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell3.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("06")) {
                                        HSSFCell dataCell1 = dataRow.getCell(18);
                                        dataCell1.setCellValue(Double.parseDouble(data.get("totalfeiqi").toString()));
                                        dataCell1.setCellStyle(conCellStyle);

                                        HSSFCell dataCell2 = dataRow.getCell(19);
                                        dataCell2.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell2.setCellStyle(conCellStyle);

                                        HSSFCell dataCell3 = dataRow.getCell(20);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell3.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("07")) {
                                        HSSFCell dataCell1 = dataRow.getCell(21);
                                        dataCell1.setCellValue(Double.parseDouble(data.get("totalfeiqi").toString()));
                                        dataCell1.setCellStyle(conCellStyle);

                                        HSSFCell dataCell2 = dataRow.getCell(22);
                                        dataCell2.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell2.setCellStyle(conCellStyle);

                                        HSSFCell dataCell3 = dataRow.getCell(23);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell3.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("08")) {
                                        HSSFCell dataCell1 = dataRow.getCell(24);
                                        dataCell1.setCellValue(Double.parseDouble(data.get("totalfeiqi").toString()));
                                        dataCell1.setCellStyle(conCellStyle);

                                        HSSFCell dataCell2 = dataRow.getCell(25);
                                        dataCell2.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell2.setCellStyle(conCellStyle);

                                        HSSFCell dataCell3 = dataRow.getCell(26);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell3.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("09")) {
                                        HSSFCell dataCell1 = dataRow.getCell(27);
                                        dataCell1.setCellValue(Double.parseDouble(data.get("totalfeiqi").toString()));
                                        dataCell1.setCellStyle(conCellStyle);

                                        HSSFCell dataCell2 = dataRow.getCell(28);
                                        dataCell2.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell2.setCellStyle(conCellStyle);

                                        HSSFCell dataCell3 = dataRow.getCell(29);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell3.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("10")) {
                                        HSSFCell dataCell1 = dataRow.getCell(30);
                                        dataCell1.setCellValue(Double.parseDouble(data.get("totalfeiqi").toString()));
                                        dataCell1.setCellStyle(conCellStyle);

                                        HSSFCell dataCell2 = dataRow.getCell(31);
                                        dataCell2.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell2.setCellStyle(conCellStyle);

                                        HSSFCell dataCell3 = dataRow.getCell(32);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell3.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("11")) {
                                        HSSFCell dataCell1 = dataRow.getCell(33);
                                        dataCell1.setCellValue(Double.parseDouble(data.get("totalfeiqi").toString()));
                                        dataCell1.setCellStyle(conCellStyle);

                                        HSSFCell dataCell2 = dataRow.getCell(34);
                                        dataCell2.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell2.setCellStyle(conCellStyle);

                                        HSSFCell dataCell3 = dataRow.getCell(35);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell3.setCellStyle(conCellStyle);
                                    }

                                    if (data.get("clock").toString().equals("12")) {
                                        HSSFCell dataCell1 = dataRow.getCell(36);
                                        dataCell1.setCellValue(Double.parseDouble(data.get("totalfeiqi").toString()));
                                        dataCell1.setCellStyle(conCellStyle);

                                        HSSFCell dataCell2 = dataRow.getCell(37);
                                        dataCell2.setCellValue(Double.parseDouble(data.get("avgnongdu").toString()));
                                        dataCell2.setCellStyle(conCellStyle);

                                        HSSFCell dataCell3 = dataRow.getCell(38);
                                        dataCell3.setCellValue(Double.parseDouble(data.get("finalvalue").toString()));
                                        dataCell3.setCellStyle(conCellStyle);
                                    }
                                }
                            }
                        }

                        HSSFCell cell = dataRow.createCell(39);
                        int index = rowIndex + 1;
                        cell.setCellFormula("SUM(AM" + index + ",AJ" + index + ",AG" + index + ",AD" + index + ",AA" +
                                index + ",X" + index + ",U" + index + ",R" + index + ",O" + index + ",L" +
                                index + ",I" + index + ",F" + index + ")");
                        cell.setCellStyle(conCellStyle);

                        rowIndex++;
                    }

                    HSSFRow dataRow = sheet.getRow(itemIndex);
                    CellRangeAddress rowRangeAddress = new CellRangeAddress(itemIndex, rowIndex - 1, 0, 0);
                    sheet.addMergedRegion(rowRangeAddress);
                    RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                    RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                    RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                    RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
                    HSSFCell dataCell = dataRow.createCell(0);
                    dataCell.setCellValue(dischargePort.get("dischargePortCode").toString());
                    dataCell.setCellStyle(conCellStyle);

                    CellRangeAddress rowRangeAddress2 = new CellRangeAddress(itemIndex, rowIndex - 1, 1, 1);
                    sheet.addMergedRegion(rowRangeAddress2);
                    RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                    RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                    RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                    RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
                    HSSFCell dataCell2 = dataRow.createCell(1);
                    dataCell2.setCellValue(dischargePort.get("dischargePortName").toString());
                    dataCell2.setCellStyle(conCellStyle);

                    itemIndex = rowIndex;
                }

            }
            HSSFRow dataRow = sheet.getRow(3);
            CellRangeAddress rowRangeAddress = new CellRangeAddress(3, rowIndex - 1, 40, 40);
            sheet.addMergedRegion(rowRangeAddress);
            RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
            RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress, sheet, wb);

            CellRangeAddress rowRangeAddress2 = new CellRangeAddress(3, rowIndex - 1, 41, 41);
            sheet.addMergedRegion(rowRangeAddress2);
            RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
            RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
            RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);
            RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, rowRangeAddress2, sheet, wb);

            List<Map<String, Object>> fsList = this.dao.query("DUHD62.queryFSTotal", pMap);

            if (StrUtil.listIsNotNullOrEmpty(fsList)) {
                for (int i = 0; i < fsList.size(); i++) {
                    Map<String, Object> fsMap = fsList.get(i);

                    if (fsMap.get("factorid").toString().equals("011")) {
                        HSSFCell dataCell = dataRow.createCell(40);
                        dataCell.setCellValue(Double.parseDouble(fsMap.get("finalValue").toString()));
                        dataCell.setCellStyle(conCellStyle);
                    }

                    if (fsMap.get("factorid").toString().equals("060")) {
                        HSSFCell dataCell = dataRow.createCell(41);
                        dataCell.setCellValue(Double.parseDouble(fsMap.get("finalValue").toString()));
                        dataCell.setCellStyle(conCellStyle);
                    }
                }
            }
        }
    }


    public List<Map<String, Object>> getFactorValue(String mnid, String factors, String reportDate) {
        List<Map<String, Object>> list = new ArrayList<>();
        String[] factorList = factors.split(",");
        String year = reportDate.substring(0, 4);
        String tableName = "DATATABLE_HOUR_" + year;
        for (int i = 0; i < factorList.length; i++) {
            String factorId = factorList[i];
            Map<String, String> map = new HashMap<>();
            map.put("mnid", mnid);
            map.put("factorId", factorId);
            map.put("reportDate", reportDate);
            map.put("tableName", tableName);
            List<Map<String, Object>> dataList = this.dao.query("DUHD62.queryFactorValue", map);
            if (StrUtil.listIsNotNullOrEmpty(dataList)) {
                for (int j = 0; j < dataList.size(); j++) {
                    Map<String, Object> data = dataList.get(j);
                    list.add(data);
                }
            }

        }
        return list;
    }

    public Map<String, Object> getGoodData(String mnid, String factors, String reportDate) {
        Map<String, Object> dMap = new HashMap<>();
        String[] factorList = factors.split(",");
        String year = reportDate.substring(0, 4);
        String tableName = "DATATABLE_HOUR_" + year;
        int total = 0;
        int num = 0;
        for (int i = 0; i < factorList.length; i++) {
            String factorId = factorList[i];
            Map<String, String> map = new HashMap<>();
            map.put("mnid", mnid);
            map.put("factorId", factorId);
            map.put("reportDate", reportDate);
            map.put("tableName", tableName);
            List<Map<String, Object>> dataList = this.dao.query("DUHD62.queryTotal", map);
            if (StrUtil.listIsNotNullOrEmpty(dataList)) {
                for (int j = 0; j < dataList.size(); j++) {
                    Map<String, Object> data = dataList.get(j);
                    total = total + Integer.parseInt(data.get("total").toString());
                    num = num + Integer.parseInt(data.get("num").toString());
                }
            }
        }
        dMap.put("total", total);
        dMap.put("num", num);

        return dMap;
    }

    public int getBadData(String mnid, String siteid, String factors, String reportDate, String tableName) throws ParseException {
        Map<String, Object> dMap = new HashMap<>();
        String[] factorList = factors.split(",");
        int total = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = reportDate + "-01";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format.parse(date));
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        String time = format.format(calendar.getTime());

        for (int i = 0; i < factorList.length; i++) {
            String factorId = factorList[i];
            EntityUtil eu = new EntityUtil();
            String limit = eu.getHighLimitByDatatime(time, siteid, factorId, dao);
            if (limit != null && limit != "") {
                Map<String, String> map = new HashMap<>();
                map.put("mnid", mnid);
                map.put("factorId", factorId);
                map.put("reportDate", reportDate);
                map.put("tableName", tableName);
                map.put("limit", limit);
                List<Map<String, Object>> dataList = this.dao.query("DUHD62.queryOverproofNum", map);
                if (StrUtil.listIsNotNullOrEmpty(dataList)) {
                    for (int j = 0; j < dataList.size(); j++) {
                        Map<String, Object> data = dataList.get(j);
                        total = total + Integer.parseInt(data.get("total").toString());
                    }
                }
            }
        }
        return total;
    }

}
