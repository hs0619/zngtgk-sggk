/**
* Generate time : 2021-05-27 15:20:24
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhc5104
* 
*/
public class Tduhc5104 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String abnormalId = " ";		
    private String siteid = " ";		
    private String sitename = " ";		
    private String situationStatus = " ";		
    private String abnormalStatus = " ";		
    private String abnormalReason = " ";		
    private String factorName = " ";		
    private String remarkStatus = " ";		
    private String remarksName = " ";		
    private String remarksContent = " ";		
    private String abnormalStartTime = " ";		
    private String abnormalEndTime = " ";		
    private String creator = " ";		
    private String createdTime = " ";		
    private String modifier = " ";		
    private String updatedTime = " ";		
    private String departmentid = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("abnormalId");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("siteid");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sitename");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("situationStatus");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("abnormalStatus");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("abnormalReason");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("factorName");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remarkStatus");
        eiColumn.setFieldLength(5);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remarksName");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remarksContent");
        eiColumn.setFieldLength(500);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("abnormalStartTime");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("abnormalEndTime");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creator");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createdTime");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("modifier");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updatedTime");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("departmentid");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public Tduhc5104() {
        initMetaData();
    }

    /**
     * get the abnormalId 
     * @return the abnormalId
     */
    public String getAbnormalId() {
        return this.abnormalId;
    }

    /**
     * set the abnormalId 
     */
    public void setAbnormalId(String abnormalId) {
        this.abnormalId = abnormalId;
    }

    /**
     * get the siteid 
     * @return the siteid
     */
    public String getSiteid() {
        return this.siteid;
    }

    /**
     * set the siteid 
     */
    public void setSiteid(String siteid) {
        this.siteid = siteid;
    }

    /**
     * get the sitename 
     * @return the sitename
     */
    public String getSitename() {
        return this.sitename;
    }

    /**
     * set the sitename 
     */
    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    /**
     * get the situationStatus 
     * @return the situationStatus
     */
    public String getSituationStatus() {
        return this.situationStatus;
    }

    /**
     * set the situationStatus 
     */
    public void setSituationStatus(String situationStatus) {
        this.situationStatus = situationStatus;
    }

    /**
     * get the abnormalStatus 
     * @return the abnormalStatus
     */
    public String getAbnormalStatus() {
        return this.abnormalStatus;
    }

    /**
     * set the abnormalStatus 
     */
    public void setAbnormalStatus(String abnormalStatus) {
        this.abnormalStatus = abnormalStatus;
    }

    /**
     * get the abnormalReason 
     * @return the abnormalReason
     */
    public String getAbnormalReason() {
        return this.abnormalReason;
    }

    /**
     * set the abnormalReason 
     */
    public void setAbnormalReason(String abnormalReason) {
        this.abnormalReason = abnormalReason;
    }

    /**
     * get the factorName 
     * @return the factorName
     */
    public String getFactorName() {
        return this.factorName;
    }

    /**
     * set the factorName 
     */
    public void setFactorName(String factorName) {
        this.factorName = factorName;
    }

    /**
     * get the remarkStatus 
     * @return the remarkStatus
     */
    public String getRemarkStatus() {
        return this.remarkStatus;
    }

    /**
     * set the remarkStatus 
     */
    public void setRemarkStatus(String remarkStatus) {
        this.remarkStatus = remarkStatus;
    }

    /**
     * get the remarksName 
     * @return the remarksName
     */
    public String getRemarksName() {
        return this.remarksName;
    }

    /**
     * set the remarksName 
     */
    public void setRemarksName(String remarksName) {
        this.remarksName = remarksName;
    }

    /**
     * get the remarksContent 
     * @return the remarksContent
     */
    public String getRemarksContent() {
        return this.remarksContent;
    }

    /**
     * set the remarksContent 
     */
    public void setRemarksContent(String remarksContent) {
        this.remarksContent = remarksContent;
    }

    /**
     * get the abnormalStartTime 
     * @return the abnormalStartTime
     */
    public String getAbnormalStartTime() {
        return this.abnormalStartTime;
    }

    /**
     * set the abnormalStartTime 
     */
    public void setAbnormalStartTime(String abnormalStartTime) {
        this.abnormalStartTime = abnormalStartTime;
    }

    /**
     * get the abnormalEndTime 
     * @return the abnormalEndTime
     */
    public String getAbnormalEndTime() {
        return this.abnormalEndTime;
    }

    /**
     * set the abnormalEndTime 
     */
    public void setAbnormalEndTime(String abnormalEndTime) {
        this.abnormalEndTime = abnormalEndTime;
    }

    /**
     * get the creator 
     * @return the creator
     */
    public String getCreator() {
        return this.creator;
    }

    /**
     * set the creator 
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * get the createdTime 
     * @return the createdTime
     */
    public String getCreatedTime() {
        return this.createdTime;
    }

    /**
     * set the createdTime 
     */
    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * get the modifier 
     * @return the modifier
     */
    public String getModifier() {
        return this.modifier;
    }

    /**
     * set the modifier 
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    /**
     * get the updatedTime 
     * @return the updatedTime
     */
    public String getUpdatedTime() {
        return this.updatedTime;
    }

    /**
     * set the updatedTime 
     */
    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * get the departmentid 
     * @return the departmentid
     */
    public String getDepartmentid() {
        return this.departmentid;
    }

    /**
     * set the departmentid 
     */
    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setAbnormalId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("abnormalId")), abnormalId));
        setSiteid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("siteid")), siteid));
        setSitename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sitename")), sitename));
        setSituationStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("situationStatus")), situationStatus));
        setAbnormalStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("abnormalStatus")), abnormalStatus));
        setAbnormalReason(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("abnormalReason")), abnormalReason));
        setFactorName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("factorName")), factorName));
        setRemarkStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remarkStatus")), remarkStatus));
        setRemarksName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remarksName")), remarksName));
        setRemarksContent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remarksContent")), remarksContent));
        setAbnormalStartTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("abnormalStartTime")), abnormalStartTime));
        setAbnormalEndTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("abnormalEndTime")), abnormalEndTime));
        setCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creator")), creator));
        setCreatedTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createdTime")), createdTime));
        setModifier(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("modifier")), modifier));
        setUpdatedTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updatedTime")), updatedTime));
        setDepartmentid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentid")), departmentid));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("abnormalId", StringUtils.toString(abnormalId, eiMetadata.getMeta("abnormalId")));
        map.put("siteid", StringUtils.toString(siteid, eiMetadata.getMeta("siteid")));
        map.put("sitename", StringUtils.toString(sitename, eiMetadata.getMeta("sitename")));
        map.put("situationStatus", StringUtils.toString(situationStatus, eiMetadata.getMeta("situationStatus")));
        map.put("abnormalStatus", StringUtils.toString(abnormalStatus, eiMetadata.getMeta("abnormalStatus")));
        map.put("abnormalReason", StringUtils.toString(abnormalReason, eiMetadata.getMeta("abnormalReason")));
        map.put("factorName", StringUtils.toString(factorName, eiMetadata.getMeta("factorName")));
        map.put("remarkStatus", StringUtils.toString(remarkStatus, eiMetadata.getMeta("remarkStatus")));
        map.put("remarksName", StringUtils.toString(remarksName, eiMetadata.getMeta("remarksName")));
        map.put("remarksContent", StringUtils.toString(remarksContent, eiMetadata.getMeta("remarksContent")));
        map.put("abnormalStartTime", StringUtils.toString(abnormalStartTime, eiMetadata.getMeta("abnormalStartTime")));
        map.put("abnormalEndTime", StringUtils.toString(abnormalEndTime, eiMetadata.getMeta("abnormalEndTime")));
        map.put("creator", StringUtils.toString(creator, eiMetadata.getMeta("creator")));
        map.put("createdTime", StringUtils.toString(createdTime, eiMetadata.getMeta("createdTime")));
        map.put("modifier", StringUtils.toString(modifier, eiMetadata.getMeta("modifier")));
        map.put("updatedTime", StringUtils.toString(updatedTime, eiMetadata.getMeta("updatedTime")));
        map.put("departmentid", StringUtils.toString(departmentid, eiMetadata.getMeta("departmentid")));
        return map;
    }
}