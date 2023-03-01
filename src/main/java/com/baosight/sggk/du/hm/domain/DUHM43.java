/**
 * Generate time : 2023-02-27 14:44:50
 * Version : 1.0
 */
package com.baosight.sggk.du.hm.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;

import java.util.HashMap;
import java.util.Map;

import com.baosight.iplat4j.core.util.StringUtils;

/**
 * DUHM43
 *
 */
public class DUHM43 extends DaoEPBase {

    private String datatime = " ";        /* 日期*/
    private String departmentid = " ";        /* 厂部id*/
    private String dangertype = " ";        /* 危废类别*/
    private String dangercode = " ";        /* 危废代码*/
    private String dangername = " ";        /* 危废名称*/
    private String prodresource = " ";        /* 产生源*/
    private String dangerform = " ";        /* 危废形态（solid固体、semisolid半固体、liquid液体）*/
    private String promethod = " ";        /* 产生方式（1生产、2清理）*/
    private String output = " ";        /* 产生量*/
    private String disposeValue = " ";        /* 处置量*/
    private String disposeMethod = " ";        /* 处置方式*/
    private String disposeUnit = " ";        /* 处置单位*/
    private String storgeValue = " ";        /* 贮存量*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("datatime");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("departmentid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("厂部id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dangertype");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("危废类别");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dangercode");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("危废代码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dangername");
        eiColumn.setDescName("危废名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("prodresource");
        eiColumn.setDescName("产生源");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dangerform");
        eiColumn.setDescName("危废形态（solid固体、semisolid半固体、liquid液体）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("promethod");
        eiColumn.setDescName("产生方式（1生产、2清理）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("output");
        eiColumn.setDescName("产生量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("disposeValue");
        eiColumn.setDescName("处置量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("disposeMethod");
        eiColumn.setDescName("处置方式");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("disposeUnit");
        eiColumn.setDescName("处置单位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("storgeValue");
        eiColumn.setDescName("贮存量");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DUHM43() {
        initMetaData();
    }

    /**
     * get the datatime - 日期
     * @return the datatime
     */
    public String getDatatime() {
        return this.datatime;
    }

    /**
     * set the datatime - 日期
     */
    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }

    /**
     * get the departmentid - 厂部id
     * @return the departmentid
     */
    public String getDepartmentid() {
        return this.departmentid;
    }

    /**
     * set the departmentid - 厂部id
     */
    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    /**
     * get the dangertype - 危废类别
     * @return the dangertype
     */
    public String getDangertype() {
        return this.dangertype;
    }

    /**
     * set the dangertype - 危废类别
     */
    public void setDangertype(String dangertype) {
        this.dangertype = dangertype;
    }

    /**
     * get the dangercode - 危废代码
     * @return the dangercode
     */
    public String getDangercode() {
        return this.dangercode;
    }

    /**
     * set the dangercode - 危废代码
     */
    public void setDangercode(String dangercode) {
        this.dangercode = dangercode;
    }

    /**
     * get the dangername - 危废名称
     * @return the dangername
     */
    public String getDangername() {
        return this.dangername;
    }

    /**
     * set the dangername - 危废名称
     */
    public void setDangername(String dangername) {
        this.dangername = dangername;
    }

    /**
     * get the prodresource - 产生源
     * @return the prodresource
     */
    public String getProdresource() {
        return this.prodresource;
    }

    /**
     * set the prodresource - 产生源
     */
    public void setProdresource(String prodresource) {
        this.prodresource = prodresource;
    }

    /**
     * get the dangerform - 危废形态（solid固体、semisolid半固体、liquid液体）
     * @return the dangerform
     */
    public String getDangerform() {
        return this.dangerform;
    }

    /**
     * set the dangerform - 危废形态（solid固体、semisolid半固体、liquid液体）
     */
    public void setDangerform(String dangerform) {
        this.dangerform = dangerform;
    }

    /**
     * get the promethod - 产生方式（1生产、2清理）
     * @return the promethod
     */
    public String getPromethod() {
        return this.promethod;
    }

    /**
     * set the promethod - 产生方式（1生产、2清理）
     */
    public void setPromethod(String promethod) {
        this.promethod = promethod;
    }

    /**
     * get the output - 产生量
     * @return the output
     */
    public String getOutput() {
        return this.output;
    }

    /**
     * set the output - 产生量
     */
    public void setOutput(String output) {
        this.output = output;
    }

    /**
     * get the disposeValue - 处置量
     * @return the disposeValue
     */
    public String getDisposeValue() {
        return this.disposeValue;
    }

    /**
     * set the disposeValue - 处置量
     */
    public void setDisposeValue(String disposeValue) {
        this.disposeValue = disposeValue;
    }

    /**
     * get the disposeMethod - 处置方式
     * @return the disposeMethod
     */
    public String getDisposeMethod() {
        return this.disposeMethod;
    }

    /**
     * set the disposeMethod - 处置方式
     */
    public void setDisposeMethod(String disposeMethod) {
        this.disposeMethod = disposeMethod;
    }

    /**
     * get the disposeUnit - 处置单位
     * @return the disposeUnit
     */
    public String getDisposeUnit() {
        return this.disposeUnit;
    }

    /**
     * set the disposeUnit - 处置单位
     */
    public void setDisposeUnit(String disposeUnit) {
        this.disposeUnit = disposeUnit;
    }

    /**
     * get the storgeValue - 贮存量
     * @return the storgeValue
     */
    public String getStorgeValue() {
        return this.storgeValue;
    }

    /**
     * set the storgeValue - 贮存量
     */
    public void setStorgeValue(String storgeValue) {
        this.storgeValue = storgeValue;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setDatatime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("datatime")), datatime));
        setDepartmentid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentid")), departmentid));
        setDangertype(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dangertype")), dangertype));
        setDangercode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dangercode")), dangercode));
        setDangername(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dangername")), dangername));
        setProdresource(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("prodresource")), prodresource));
        setDangerform(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dangerform")), dangerform));
        setPromethod(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("promethod")), promethod));
        setOutput(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("output")), output));
        setDisposeValue(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("disposeValue")), disposeValue));
        setDisposeMethod(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("disposeMethod")), disposeMethod));
        setDisposeUnit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("disposeUnit")), disposeUnit));
        setStorgeValue(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("storgeValue")), storgeValue));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {

        Map map = new HashMap();
        map.put("datatime", StringUtils.toString(datatime, eiMetadata.getMeta("datatime")));
        map.put("departmentid", StringUtils.toString(departmentid, eiMetadata.getMeta("departmentid")));
        map.put("dangertype", StringUtils.toString(dangertype, eiMetadata.getMeta("dangertype")));
        map.put("dangercode", StringUtils.toString(dangercode, eiMetadata.getMeta("dangercode")));
        map.put("dangername", StringUtils.toString(dangername, eiMetadata.getMeta("dangername")));
        map.put("prodresource", StringUtils.toString(prodresource, eiMetadata.getMeta("prodresource")));
        map.put("dangerform", StringUtils.toString(dangerform, eiMetadata.getMeta("dangerform")));
        map.put("promethod", StringUtils.toString(promethod, eiMetadata.getMeta("promethod")));
        map.put("output", StringUtils.toString(output, eiMetadata.getMeta("output")));
        map.put("disposeValue", StringUtils.toString(disposeValue, eiMetadata.getMeta("disposeValue")));
        map.put("disposeMethod", StringUtils.toString(disposeMethod, eiMetadata.getMeta("disposeMethod")));
        map.put("disposeUnit", StringUtils.toString(disposeUnit, eiMetadata.getMeta("disposeUnit")));
        map.put("storgeValue", StringUtils.toString(storgeValue, eiMetadata.getMeta("storgeValue")));

        return map;

    }
}