package com.baosight.sggk.du.hd.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class DUHD21 extends DaoEPBase {


    private String reportname = " ";
    private String reporttype = " ";
    private String filename = " ";
    private String exist = " ";
    private String createtime = " ";
    private String datatime = " ";
    private String filepath = " ";

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("reportname");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(60);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("reporttype");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("filename");
        eiColumn.setFieldLength(60);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("exist");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createtime");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("datatime");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("filepath");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DUHD21() {
        initMetaData();
    }



    public String getReportname() {
        return reportname;
    }

    public void setReportname(String reportname) {
        this.reportname = reportname;
    }

    public String getReporttype() {
        return reporttype;
    }

    public void setReporttype(String reporttype) {
        this.reporttype = reporttype;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getExist() {
        return exist;
    }

    public void setExist(String exist) {
        this.exist = exist;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getDatatime() {
        return datatime;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }


    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setReportname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("reportname")), reportname));
        setReporttype(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("reporttype")), reporttype));
        setFilename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("filename")), filename));
        setExist(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("exist")), exist));
        setCreatetime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createtime")), createtime));
        setDatatime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("datatime")), datatime));
        setFilepath(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("filepath")), filepath));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("reportname",StringUtils.toString(reportname, eiMetadata.getMeta("reportname")));
        map.put("reporttype",StringUtils.toString(reporttype, eiMetadata.getMeta("reporttype")));
        map.put("filename",StringUtils.toString(filename, eiMetadata.getMeta("filename")));
        map.put("exist",StringUtils.toString(exist, eiMetadata.getMeta("exist")));
        map.put("createtime",StringUtils.toString(createtime, eiMetadata.getMeta("createtime")));
        map.put("datatime",StringUtils.toString(datatime, eiMetadata.getMeta("datatime")));
        map.put("filepath",StringUtils.toString(filepath, eiMetadata.getMeta("filepath")));
        return map;
    }
}
