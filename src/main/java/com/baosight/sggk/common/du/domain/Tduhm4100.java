package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class Tduhm4100 extends DaoEPBase {

    private String muuId=" ";
    private String year=" ";
    private String month=" ";
    private String factory=" ";
    private String state=" ";
    private String remark=" ";

    public void initMetaData() {

        EiColumn eiColumn;

        eiColumn = new EiColumn("period");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("时间");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("year");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("month");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("factory");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("厂部");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("state");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("状态");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("remark");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);
        
       

    }


    public Tduhm4100() {
        initMetaData();
    }


    public String getMuuId(){
        return this.muuId;
    }


    public void setMuuId(String muuId){
        this.muuId=muuId;
    }


    public String getYear(){
        return this.year;
    }


    public void setYear(String year){
        this.year=year;
    }


    public String getMonth(){
        return this.month;
    }


    public void setMonth(String month){
        this.month=month;
    }


    public String getFactory(){
        return this.factory;
    }


    public void setFactory(String factory){
        this.factory=factory;
    }


    public String getState(){
        return this.state;
    }


    public void setState(String state){
        this.state=state;
    }


    public String getRemark(){
        return this.remark;
    }


    public void setRemark(String remark){
        this.remark=remark;
    }


    public void fromMap(Map map) {
        setYear(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("year")), year));
        setMonth(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("month")), month));
        setFactory(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("factory")), factory));
        setState(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("state")), state));
        setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
    }


    public Map toMap() {
        Map map = new HashMap();
        map.put("year", StringUtils.toString(year, eiMetadata.getMeta("year")));
        map.put("month", StringUtils.toString(month, eiMetadata.getMeta("month")));
        map.put("factory", StringUtils.toString(factory, eiMetadata.getMeta("factory")));
        map.put("state", StringUtils.toString(state, eiMetadata.getMeta("state")));
        map.put("remark", StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        return map;

    }
}