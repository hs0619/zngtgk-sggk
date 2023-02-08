package com.baosight.sggk.du.hm.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

//固废信息表
public class DUHM41 extends DaoEPBase {

//序号 固废名称 固废代码 固废类别编号 固废总产量 固废总利用量 固废利用率
    private String solidId=" ";//序号
    private String solidName=" ";//固废名称
    private String solidCode=" ";// 固废代码
    private String solidCodes=" ";//固废类别编号
    private String solidType=" ";//固废总产量
    private String state=" ";//处置情况
    private String rank=" ";//固废利用率
    private String solidState=" ";
    private String remark=" ";
    private String industrialDisposal=" ";//工业垃圾处置量(1:否.2是)
    private String solidProduct=" ";//委外综合利用量（固废产品化量）(1:否2是)
    private String ironMud=" ";//含铁泥产生量(1否2是)
    private String combinedUtilization=" ";//委外综合利用量
    public DUHM41() {
        initMetaData();
    }

    public void initMetaData() {

        EiColumn eiColumn;
        eiColumn = new EiColumn("solidId");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(255);
        eiColumn.setDescName("solidId");
        eiMetadata.addMeta(eiColumn);



        eiColumn = new EiColumn("solidName");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("固废名称");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("solidCode");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("固废代码");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("solidCodes");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("固废区别代码");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("solidType");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("父级名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rank");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("序号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("state");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("处置情况");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("solidState");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("industrialDisposal");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("工业垃圾处置量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("solidProduct");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("固废产品化量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("ironMud");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("含铁泥产生量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("combinedUtilization");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("委外综合利用量");
        eiMetadata.addMeta(eiColumn);
    }


    public String getSolidId(){
        return this.solidId;
    }

    public void setSolidId(String solidId){
        this.solidId=solidId;
    }



    public String getSolidName(){
        return this.solidName;
    }

    public void setSolidName(String solidName){
        this.solidName=solidName;
    }



    public String getSolidCode(){
        return this.solidCode;
    }

    public void setSolidCode(String solidCode){
        this.solidCode=solidCode;
    }



    public String getSolidCodes(){
        return this.solidCodes;
    }

    public void setSolidCodes(String solidCodes){
        this.solidCodes=solidCodes;
    }



    public String getSolidType(){
        return this.solidType;
    }

    public void setSolidType(String solidType){
        this.solidType=solidType;
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



    public String getRemark(){
        return this.remark;
    }

    public void setRemark(String remark){
        this.remark=remark;
    }


    public String getSolidState(){
        return this.solidState;
    }

    public void setSolidState(String solidState){
        this.solidState=solidState;
    }

    public String getIndustrialDisposal() {
        return industrialDisposal;
    }

    public void setIndustrialDisposal(String industrialDisposal) {
        this.industrialDisposal = industrialDisposal;
    }

    public String getSolidProduct() {
        return solidProduct;
    }

    public void setSolidProduct(String solidProduct) {
        this.solidProduct = solidProduct;
    }

    public String getIronMud() {
        return ironMud;
    }

    public void setIronMud(String ironMud) {
        this.ironMud = ironMud;
    }

    public String getCombinedUtilization() {
        return combinedUtilization;
    }

    public void setCombinedUtilization(String combinedUtilization) {
        this.combinedUtilization = combinedUtilization;
    }

    public void fromMap(Map map) {
        setSolidId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("solidId")), solidId));
        setSolidName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("solidName")), solidName));
        setSolidCode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("solidCode")), solidCode));
        setSolidCodes(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("solidCodes")), solidCodes));
        setSolidType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("solidType")), solidType));
        setState(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("state")), state));
        setRank(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rank")), rank));
        setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
        setSolidState(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("solidState")), solidState));

        setIndustrialDisposal(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("industrialDisposal")), industrialDisposal));
        setSolidProduct(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("solidProduct")), solidProduct));
        setIronMud(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("ironMud")), ironMud));
        setCombinedUtilization(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("combinedUtilization")), combinedUtilization));

    }

    public Map toMap() {
        Map map = new HashMap();
        map.put("solidId", StringUtils.toString(solidId, eiMetadata.getMeta("solidId")));
        map.put("solidName", StringUtils.toString(solidName, eiMetadata.getMeta("solidName")));
        map.put("solidCode", StringUtils.toString(solidCode, eiMetadata.getMeta("solidCode")));
        map.put("solidCodes", StringUtils.toString(solidCodes, eiMetadata.getMeta("solidCodes")));
        map.put("solidType", StringUtils.toString(solidType, eiMetadata.getMeta("solidType")));
        map.put("state", StringUtils.toString(state, eiMetadata.getMeta("state")));
        map.put("rank", StringUtils.toString(rank, eiMetadata.getMeta("rank")));
        map.put("remark", StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("solidState", StringUtils.toString(solidState, eiMetadata.getMeta("solidState")));

        map.put("industrialDisposal", StringUtils.toString(industrialDisposal, eiMetadata.getMeta("industrialDisposal")));
        map.put("solidProduct", StringUtils.toString(solidProduct, eiMetadata.getMeta("solidProduct")));
        map.put("ironMud", StringUtils.toString(ironMud, eiMetadata.getMeta("ironMud")));
        map.put("combinedUtilization", StringUtils.toString(combinedUtilization, eiMetadata.getMeta("combinedUtilization")));
        return map;
    }



    public EiBlockMeta getMetaData() {
        return eiMetadata;
    }


}