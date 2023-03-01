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
 * DUHM42
 *
 */
public class DUHM42 extends DaoEPBase {

    private String datatime = " ";        /* 日期*/
    private String solidId = " ";        /* 固废id*/
    private String output = " ";        /* 产生量*/
    private String utilization = " ";        /* 利用量*/
    private String backOutput = " ";        /* 反生产利用量*/
    private String outerValue = " ";        /* 外销量*/
    private String disposeValue = " ";        /* 处置量*/

    private String solidName=" ";//固废名称
    private String solidCode=" ";// 固废代码
    private String solidType=" ";
    private String rank=" ";

    private String isupdate=" ";
    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("datatime");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("solidId");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("固废id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("output");
        eiColumn.setDescName("产生量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("utilization");
        eiColumn.setDescName("利用量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("backOutput");
        eiColumn.setDescName("反生产利用量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outerValue");
        eiColumn.setDescName("外销量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("disposeValue");
        eiColumn.setDescName("处置量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("solidName");
        eiColumn.setDescName("固废名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("solidCode");
        eiColumn.setDescName("固废代码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("solidType");
        eiColumn.setDescName("固废类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rank");
        eiColumn.setDescName("排序");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isupdate");
        eiColumn.setDescName("是否修改");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public DUHM42() {
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
     * get the solidId - 固废id
     * @return the solidId
     */
    public String getSolidId() {
        return this.solidId;
    }

    /**
     * set the solidId - 固废id
     */
    public void setSolidId(String solidId) {
        this.solidId = solidId;
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
     * get the utilization - 利用量
     * @return the utilization
     */
    public String getUtilization() {
        return this.utilization;
    }

    /**
     * set the utilization - 利用量
     */
    public void setUtilization(String utilization) {
        this.utilization = utilization;
    }

    /**
     * get the backOutput - 反生产利用量
     * @return the backOutput
     */
    public String getBackOutput() {
        return this.backOutput;
    }

    /**
     * set the backOutput - 反生产利用量
     */
    public void setBackOutput(String backOutput) {
        this.backOutput = backOutput;
    }

    /**
     * get the outerValue - 外销量
     * @return the outerValue
     */
    public String getOuterValue() {
        return this.outerValue;
    }

    /**
     * set the outerValue - 外销量
     */
    public void setOuterValue(String outerValue) {
        this.outerValue = outerValue;
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

    public String getSolidName() {
        return solidName;
    }

    public void setSolidName(String solidName) {
        this.solidName = solidName;
    }

    public String getSolidCode() {
        return solidCode;
    }

    public void setSolidCode(String solidCode) {
        this.solidCode = solidCode;
    }

    public String getSolidType() {
        return solidType;
    }

    public void setSolidType(String solidType) {
        this.solidType = solidType;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getIsupdate() {
        return isupdate;
    }

    public void setIsupdate(String isupdate) {
        this.isupdate = isupdate;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setDatatime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("datatime")), datatime));
        setSolidId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("solidId")), solidId));
        setOutput(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("output")), output));
        setUtilization(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("utilization")), utilization));
        setBackOutput(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("backOutput")), backOutput));
        setOuterValue(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outerValue")), outerValue));
        setDisposeValue(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("disposeValue")), disposeValue));

        setSolidName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("solidName")), solidName));
        setSolidCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("solidCode")), solidCode));
        setSolidType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("solidType")), solidType));
        setRank(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rank")), rank));

        setIsupdate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isupdate")), isupdate));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {

        Map map = new HashMap();
        map.put("datatime", StringUtils.toString(datatime, eiMetadata.getMeta("datatime")));
        map.put("solidId", StringUtils.toString(solidId, eiMetadata.getMeta("solidId")));
        map.put("output", StringUtils.toString(output, eiMetadata.getMeta("output")));
        map.put("utilization", StringUtils.toString(utilization, eiMetadata.getMeta("utilization")));
        map.put("backOutput", StringUtils.toString(backOutput, eiMetadata.getMeta("backOutput")));
        map.put("outerValue", StringUtils.toString(outerValue, eiMetadata.getMeta("outerValue")));
        map.put("disposeValue", StringUtils.toString(disposeValue, eiMetadata.getMeta("disposeValue")));

        map.put("solidName", StringUtils.toString(solidName, eiMetadata.getMeta("solidName")));
        map.put("solidCode", StringUtils.toString(solidCode, eiMetadata.getMeta("solidCode")));
        map.put("solidType", StringUtils.toString(solidType, eiMetadata.getMeta("solidType")));
        map.put("rank", StringUtils.toString(rank, eiMetadata.getMeta("rank")));

        map.put("isupdate", StringUtils.toString(isupdate, eiMetadata.getMeta("isupdate")));
        return map;

    }
}