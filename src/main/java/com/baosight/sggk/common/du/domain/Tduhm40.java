package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;

public class Tduhm40 extends DaoEPBase {

    private String muuId=" ";
    private String period=" ";
    private String targetName=" ";
    private String output=" ";

    /**
     * initialize the metadata
     */
    public void initMetaData() {

        EiColumn eiColumn;

        eiColumn = new EiColumn("period");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("targetName");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("指标名称");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("output");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("产生量");
        eiMetadata.addMeta(eiColumn);
    }
    /**
     * the constructor
     */
    public Tduhm40() {
        initMetaData();
    }

    public String getMuuId(){
        return this.muuId;
    }

    public void setMuuId(String muuId){
        this.muuId=muuId;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}