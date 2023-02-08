package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

//固废信息表
public class Tduhm4105 extends DaoEPBase {

    private String muuId=" ";
    private String period=" ";
    private String fatherId=" ";
    private String solidId=" ";
    private String output=" ";
    private String utilization=" ";
    private String utilizationRate=" ";
    private String backOutput=" ";
    private String backRate=" ";
    private String backPrice=" ";
    private String backTotal=" ";
    private String outerValue=" ";
    private String outerPrice=" ";
    private String outerTotal=" ";
    private String disposeValue=" ";
    private String disposeRate=" ";


    public Tduhm4105() {
        initMetaData();
    }

    public void initMetaData() {

        EiColumn eiColumn = new EiColumn("solidType");
        eiColumn.setDescName("固废类型");
        eiColumn.setNullable(false);
        eiColumn.setPrimaryKey(true);
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("solidName");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("固废名称");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("output");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("产生量");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("backOutput");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("内部利用量(返生产利用量)");
        eiMetadata.addMeta(eiColumn);



        eiColumn = new EiColumn("backPrice");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("返生产利用单价");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("backTotal");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("返生产利用总效益");
        eiMetadata.addMeta(eiColumn);



        eiColumn = new EiColumn("outerPrice");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("外销单价");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("outerTotal");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("外销总效益");
        eiMetadata.addMeta(eiColumn);


    }

    public String getBackTotal() {
        return backTotal;
    }

    public void setBackTotal(String backTotal) {
        this.backTotal = backTotal;
    }

    public String getOuterTotal() {
        return outerTotal;
    }

    public void setOuterTotal(String outerTotal) {
        this.outerTotal = outerTotal;
    }

    public String getMuuId() {
        return muuId;
    }

    public void setMuuId(String muuId) {
        this.muuId = muuId;
    }

    public String getPeriod() {
        return period;
    }

    public String getUtilizationRate() {
        return utilizationRate;
    }

    public void setUtilizationRate(String utilizationRate) {
        this.utilizationRate = utilizationRate;
    }

    public String getBackOutput() {
        return backOutput;
    }

    public void setBackOutput(String backOutput) {
        this.backOutput = backOutput;
    }

    public String getBackRate() {
        return backRate;
    }

    public void setBackRate(String backRate) {
        this.backRate = backRate;
    }

    public String getOuterValue() {
        return outerValue;
    }

    public void setOuterValue(String outerValue) {
        this.outerValue = outerValue;
    }

    public String getDisposeValue() {
        return disposeValue;
    }

    public void setDisposeValue(String disposeValue) {
        this.disposeValue = disposeValue;
    }

    public String getDisposeRate() {
        return disposeRate;
    }

    public void setDisposeRate(String disposeRate) {
        this.disposeRate = disposeRate;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public String getSolidId() {
        return solidId;
    }

    public void setSolidId(String solidId) {
        this.solidId = solidId;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getUtilization() {
        return utilization;
    }

    public void setUtilization(String utilization) {
        this.utilization = utilization;
    }

    public String getBackPrice() {
        return backPrice;
    }

    public void setBackPrice(String backPrice) {
        this.backPrice = backPrice;
    }



    public String getOuterPrice() {
        return outerPrice;
    }

    public void setOuterPrice(String outerPrice) {
        this.outerPrice = outerPrice;
    }



    public EiBlockMeta getMetaData() {
        return eiMetadata;
    }


    public void fromMap(Map map) {
        setMuuId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("muuId")), muuId));
        setPeriod(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("period")), period));
        setFatherId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fatherId")), fatherId));
        setSolidId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("solidId")), solidId));
        setOutput(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("output")), output));
        setUtilization(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("utilization")), utilization));
        setUtilizationRate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("utilizationRate")), utilizationRate));
        setBackOutput(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("backOutput")), backOutput));
        setBackRate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("backRate")), backRate));
        setBackPrice(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("backPrice")), backPrice));
        setBackTotal(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("backTotal")), backTotal));
        setOuterValue(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outerValue")), outerValue));
        setOuterPrice(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outerPrice")), outerPrice));
        setOuterTotal(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outerTotal")), outerTotal));
        setDisposeValue(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("disposeValue")), disposeValue));
        setDisposeRate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("disposeRate")), disposeRate));
    }


    public Map toMap() {
        Map map = new HashMap();
        map.put("muuId", StringUtils.toString(muuId, eiMetadata.getMeta("muuId")));
        map.put("period", StringUtils.toString(period, eiMetadata.getMeta("period")));
        map.put("fatherId", StringUtils.toString(fatherId, eiMetadata.getMeta("fatherId")));
        map.put("solidId", StringUtils.toString(solidId, eiMetadata.getMeta("solidId")));
        map.put("output", StringUtils.toString(output, eiMetadata.getMeta("output")));
        map.put("utilization", StringUtils.toString(utilization, eiMetadata.getMeta("utilization")));
        map.put("utilizationRate", StringUtils.toString(utilizationRate, eiMetadata.getMeta("utilizationRate")));
        map.put("backOutput", StringUtils.toString(backOutput, eiMetadata.getMeta("backOutput")));
        map.put("backRate", StringUtils.toString(backRate, eiMetadata.getMeta("backRate")));
        map.put("backPrice", StringUtils.toString(backPrice, eiMetadata.getMeta("backPrice")));
        map.put("backTotal", StringUtils.toString(backTotal, eiMetadata.getMeta("backTotal")));
        map.put("outerValue", StringUtils.toString(outerValue, eiMetadata.getMeta("outerValue")));
        map.put("outerPrice", StringUtils.toString(outerPrice, eiMetadata.getMeta("outerPrice")));
        map.put("outerTotal", StringUtils.toString(outerTotal, eiMetadata.getMeta("outerTotal")));
        map.put("disposeValue", StringUtils.toString(disposeValue, eiMetadata.getMeta("disposeValue")));
        map.put("disposeRate", StringUtils.toString(disposeRate, eiMetadata.getMeta("disposeRate")));
        return map;

    }

}