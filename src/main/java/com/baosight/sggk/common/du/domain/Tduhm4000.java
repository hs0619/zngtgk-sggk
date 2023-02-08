package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

//固废信息表
public class Tduhm4000 extends DaoEPBase {

    private String muuId=" ";
    private String year=" ";
    private String month=" ";
    private String fatherId=" ";
    private String targetId=" ";
    private String targetName=" ";
    private String targetCode=" ";
    private String targetCodes=" ";
    private String unit=" ";
    private String targetType=" ";
    private String rank=" ";
    private String state=" ";
    private String baseNumber=" ";
    private String yesteryearValue=" ";
    private String thisyearValue=" ";
    private String increase=" ";
    private String remark=" ";


    public Tduhm4000() {
        initMetaData();
    }

    public void initMetaData() {

    EiColumn eiColumn = new EiColumn("targetName");
        eiColumn.setDescName("指标名称");
        eiColumn.setNullable(false);
        eiColumn.setPrimaryKey(true);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("unit");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("计量单位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("targetCode");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("代码");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("baseNumber");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("基期数");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("yesteryearValue");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("上年累计完成值");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("thisyearValue");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("本年累计完成值");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("increase");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("同期比增减");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);

    }


    public String getTargetId(){
        return this.targetId;
    }

    public void setTargetId(String targetId){
        this.targetId=targetId;
    }



    public String getTargetName(){
        return this.targetName;
    }

    public void setTargetName(String targetName){
        this.targetName=targetName;
    }



    public String getMuuId(){
        return this.muuId;
    }

    public void setMuuId(String muuId){
        this.muuId=muuId;
    }





    public String getRemark(){
        return this.remark;
    }

    public void setRemark(String remark){
        this.remark=remark;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public String getTargetCode() {
        return targetCode;
    }

    public void setTargetCode(String targetCode) {
        this.targetCode = targetCode;
    }

    public String getTargetCodes() {
        return targetCodes;
    }

    public void setTargetCodes(String targetCodes) {
        this.targetCodes = targetCodes;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBaseNumber() {
        return baseNumber;
    }

    public void setBaseNumber(String baseNumber) {
        this.baseNumber = baseNumber;
    }

    public String getYesteryearValue() {
        return yesteryearValue;
    }

    public void setYesteryearValue(String yesteryearValue) {
        this.yesteryearValue = yesteryearValue;
    }

    public String getThisyearValue() {
        return thisyearValue;
    }

    public void setThisyearValue(String thisyearValue) {
        this.thisyearValue = thisyearValue;
    }

    public String getIncrease() {
        return increase;
    }

    public void setIncrease(String increase) {
        this.increase = increase;
    }

    public void fromMap(Map map) {
        setTargetId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("targetId")), targetId));
        setTargetName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("targetName")), targetName));
        setTargetCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("targetCode")), targetCode));
        setUnit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("unit")), unit));
        setTargetType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("targetType")), targetType));
        setRank(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rank")), rank));
        setState(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("state")), state));
        setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
        setBaseNumber(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("baseNumber")), baseNumber));
  }

    public Map toMap() {
        Map map = new HashMap();
        map.put("targetId", StringUtils.toString(targetId, eiMetadata.getMeta("targetId")));
        map.put("targetName", StringUtils.toString(targetName, eiMetadata.getMeta("targetName")));
        map.put("targetCode", StringUtils.toString(targetCode, eiMetadata.getMeta("targetCode")));
        map.put("unit", StringUtils.toString(unit, eiMetadata.getMeta("unit")));
        map.put("targetType", StringUtils.toString(targetType, eiMetadata.getMeta("targetType")));
        map.put("rank", StringUtils.toString(rank, eiMetadata.getMeta("rank")));
        map.put("state", StringUtils.toString(state, eiMetadata.getMeta("state")));
        map.put("baseNumber", StringUtils.toString(baseNumber, eiMetadata.getMeta("baseNumber")));
        return map;
    }



    public EiBlockMeta getMetaData() {
        return eiMetadata;
    }


}