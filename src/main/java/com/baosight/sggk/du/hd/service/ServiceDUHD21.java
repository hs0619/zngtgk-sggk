package com.baosight.sggk.du.hd.service;

import com.baosight.iplat4j.core.ei.*;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.sggk.du.hd.domain.DUHD20;
import com.baosight.sggk.du.hd.domain.DUHD21;
import com.baosight.sggk.util.DateUtil;
import com.baosight.sggk.util.StrUtil;
import org.apache.log4j.Logger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class ServiceDUHD21 extends ServiceBase {
    private static final Logger logger = Logger.getLogger(ServiceDUHD20.class);
    // 获取配置文件里的参数
    public ResourceBundle dbPro = ResourceBundle.getBundle("application");
    public String DbSchema = dbPro.getString("hbSchema");

    public EiInfo initLoad(EiInfo inInfo){
        EiInfo outInfo = new EiInfo();
        outInfo.addBlock(getReportTypeBlock());
        outInfo.set("inqu_status-0-reporttype","DAY");
        EiBlock reportInfoBlock = getReportInfo(outInfo).getBlock("reportInfoBlock");
        outInfo.addBlock(reportInfoBlock);
        if (reportInfoBlock.getRowCount()>0){
            outInfo.set("inqu_status-0-reportname",reportInfoBlock.getRow(0).get("reportname"));
        }
        //默认开始时间为当前时间的前7天
        outInfo.set("inqu_status-0-startdate", DateUtil.addDate("DAY",-7));
        //结束时间为当前时间
        outInfo.set("inqu_status-0-enddate",DateUtil.addDate("DAY",0));

        EiBlock resultblock = new EiBlock("resul");
        resultblock.addBlockMeta(new DUHD21().eiMetadata);
        outInfo.addBlock(resultblock);
        return outInfo;
    }

    public EiInfo query(EiInfo inInfo){
        EiInfo outInfo = new EiInfo();
        String reporttype = inInfo.getString("inqu_status-0-reporttype");
        String reportname = inInfo.getString("inqu_status-0-reportname");
        String startdate = inInfo.getString("inqu_status-0-startdate");
        String enddate = inInfo.getString("inqu_status-0-enddate");

        EiBlock resultblock = inInfo.getBlock("resul");
        if (resultblock == null){
            resultblock = new EiBlock("resul");
            resultblock.addBlockMeta(new DUHD21().eiMetadata);
        }

        List<DUHD21> result = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (sdf.parse(startdate).after(sdf.parse(enddate))){
                outInfo.setMsg("终止时间不可小于起始时间");
                return outInfo;
            }

            Map params = new HashMap();
            params.put("reporttype",reporttype);
            params.put("reportname",reportname);
            List<DUHD20> list = dao.query("DUHD20.query", params, 0, -999999);
            if (list.size()>0){
                DUHD20 reportInfo = list.get(0);
                String filename = reportInfo.getReportname();
                reporttype = reportInfo.getReporttype();

                int position = filename.lastIndexOf(".");
                String fileNoSuffixName = filename.substring(0, position);
                String fileSuffix = filename.substring(position, filename.length());

                Date dtTmpTime = sdf.parse(startdate);
                Date endTime = sdf.parse(enddate);

                if (reporttype.equals("DAY")){
                    do {
                        DUHD21 row = new DUHD21();
                        String datatime = sdf.format(dtTmpTime);
                        row.setReportname(filename);
                        row.setReporttype(reporttype);
                        row.setDatatime(datatime);
                        row.setFilename(fileNoSuffixName + "_" + datatime + fileSuffix);
                        result.add(row);
                        dtTmpTime = DateUtil.addDateTime(dtTmpTime, "DAY", 1);
                    }while (dtTmpTime.compareTo(endTime) <= 0);
                }

                if (reporttype.equals("MONTH")) {
                    do {
                        DUHD21 row = new DUHD21();
                        String datatime = new SimpleDateFormat("yyyy-MM").format(dtTmpTime);
                        row.setReportname(reportname);
                        row.setReporttype(reporttype);
                        row.setDatatime(datatime);
                        row.setFilename(fileNoSuffixName + "_" + datatime + fileSuffix);
                        result.add(row);

                        dtTmpTime = DateUtil.addDateTime(dtTmpTime, "MONTH", 1);
                    } while (dtTmpTime.compareTo(endTime) <= 0);
                }

                if (reporttype.equals("QUARTER")) {
                    String strQuarter = "一季度";
                    String intQuarter = "Q1";

                    do {
                        Calendar c = Calendar.getInstance();
                        c.setTime(dtTmpTime);
                        int month = c.get(Calendar.MONTH) + 1;
                        int year = c.get(Calendar.YEAR);
                        switch (month) {
                            case 1:
                            case 2:
                            case 3:
                                intQuarter = "Q1";
                                strQuarter = "一季度";
                                break;
                            case 4:
                            case 5:
                            case 6:
                                intQuarter = "Q2";
                                strQuarter = "二季度";
                                break;
                            case 7:
                            case 8:
                            case 9:
                                intQuarter = "Q3";
                                strQuarter = "三季度";
                                break;
                            case 10:
                            case 11:
                            case 12:
                                intQuarter = "Q4";
                                strQuarter = "四季度";
                                break;
                        }
                        DUHD21 row = new DUHD21();
                        String datatime = year + intQuarter;
                        row.setReportname(reportname);
                        row.setReporttype(reporttype);
                        row.setDatatime(datatime);
                        row.setFilename(fileNoSuffixName + "_" + year + strQuarter + fileSuffix);
                        result.add(row);

                        dtTmpTime = DateUtil.addDateTime(dtTmpTime, "MONTH", 3);
                    } while (dtTmpTime.compareTo(endTime) <= 0);
                }

                if (reporttype.equals("YEAR")) {

                    do {
                        DUHD21 row = new DUHD21();
                        String datatime = new SimpleDateFormat("yyyy").format(dtTmpTime);
                        row.setReportname(reportname);
                        row.setReporttype(reporttype);
                        row.setDatatime(datatime);
                        row.setFilename(fileNoSuffixName + "_" + datatime + fileSuffix);
                        result.add(row);

                        dtTmpTime = DateUtil.addDateTime(dtTmpTime, "YEAR", 1);
                    } while (dtTmpTime.compareTo(endTime) <= 0);
                }
            }

            String reportDirPath = StrUtil.getExcelReportPath();// 获取报表所在文件目录
            for (int i = 0; i < result.size(); i++) {
                DUHD21 info = result.get(i);
                String reportpath = reportDirPath +   info.getReporttype() + "/" + info.getFilename();
                File file = new File(reportpath);
                if (file.exists()) {
                    info.setExist("已生成");
                    info.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
                } else {
                    info.setExist("未生成");
                }
                info.setFilepath(reportpath);
            }

        }catch (Exception e){
            e.printStackTrace();
            outInfo.setMsg("报表查询出错");
        }


        int offset = resultblock.getInt(EiConstant.offsetStr);
        int limit = resultblock.getInt(EiConstant.limitStr);

//		if(limit == 0)limit = EiConstant.defaultLimit;

        int formindex=offset>result.size()?result.size():offset;
        int toindex=offset+limit>result.size()?result.size():offset+limit;
        List<DUHD21> rows =result.subList(formindex,toindex);

        resultblock.setRows(rows);
        resultblock.set(EiConstant.countStr,result.size());
        outInfo.addBlock(resultblock);
        return outInfo;
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

    public EiInfo getReportInfo(EiInfo inInfo) {
        EiInfo outInfo = new EiInfo();
        String reporttype = inInfo.getString("inqu_status-0-reporttype").equals("") ? "空"
                : inInfo.getString("inqu_status-0-reporttype");
        EiBlock block = new EiBlock("reportInfoBlock");
        block.setBlockMeta(new DUHD20().eiMetadata);
        Map params = new HashMap();
        params.put("reporttype", reporttype);

        List list = this.dao.query("DUHD20.query", params);
        block.setRows(list);
        outInfo.setBlock(block);
        return outInfo;
    }
}
