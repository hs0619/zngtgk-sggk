package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

//固废信息表
public class Tduhm4001 extends DaoEPBase {

    private String targetId=" ";
    private String targetName=" ";
    private String targetCode=" ";
    private String targetCodes=" ";
    private String unit=" ";
    private String targetType=" ";
    private String rank=" ";
    private String state=" ";
    private String remark=" ";


    public Tduhm4001() {
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


        eiColumn = new EiColumn("targetType");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("父级代码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rank");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("排序");
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



    public String getTargetCode(){
        return this.targetCode;
    }

    public void setTargetCode(String targetCode){
        this.targetCode=targetCode;
    }



    public String getTargetCodes(){
        return this.targetCodes;
    }

    public void setTargetCodes(String targetCodes){
        this.targetCodes=targetCodes;
    }


    public String getUnit(){
        return this.unit;
    }

    public void setUnit(String unit){
        this.unit=unit;
    }



    public String getTargetType(){
        return this.targetType;
    }

    public void setTargetType(String targetType){
        this.targetType=targetType;
    }



    public String getRemark(){
        return this.remark;
    }

    public void setRemark(String remark){
        this.remark=remark;
    }



    public String getState(){
        return this.state;
    }

    public void setState(String state){
        this.state=state;
    }


    public String getRank(){
        return this.rank;
    }

    public void setRank(String rank){
        this.rank=rank;
    }


    public void fromMap(Map map) {
        setTargetId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("targetId")), targetId));
        setTargetName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("targetName")), targetName));
        setTargetCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("targetCode")), targetCode));
        setTargetCodes(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("targetCodes")), targetCodes));
        setUnit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("unit")), unit));
        setTargetType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("targetType")), targetType));
        setRank(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rank")), rank));
        setState(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("state")), state));
        setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
  }

    public Map toMap() {
        Map map = new HashMap();
        map.put("targetId", StringUtils.toString(targetId, eiMetadata.getMeta("targetId")));
        map.put("targetName", StringUtils.toString(targetName, eiMetadata.getMeta("targetName")));
        map.put("targetCode", StringUtils.toString(targetCode, eiMetadata.getMeta("targetCode")));
        map.put("targetCodes", StringUtils.toString(targetCodes, eiMetadata.getMeta("targetCodes")));
        map.put("unit", StringUtils.toString(unit, eiMetadata.getMeta("unit")));
        map.put("targetType", StringUtils.toString(targetType, eiMetadata.getMeta("targetType")));
        map.put("rank", StringUtils.toString(rank, eiMetadata.getMeta("rank")));
        map.put("state", StringUtils.toString(state, eiMetadata.getMeta("state")));
        map.put("remark", StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        return map;
    }



    public EiBlockMeta getMetaData() {
        return eiMetadata;
    }


}