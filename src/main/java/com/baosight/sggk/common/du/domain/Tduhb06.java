/**
* Generate time : 2021-08-08 18:01:16
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhb06
* 
*/
public class Tduhb06 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String monitorid = " ";		
    private String factorid = " ";		
    private String portid = " ";		
    private String highlimit = " ";		
    private String lowlimit = " ";		
    private String nkhighlimit = " ";		
    private String nklowlimit = " ";		
    private String description = " ";		
    private String status = " ";		
    private String creator = " ";		
    private String createdTime = " ";		
    private String modifier = " ";		
    private String updatedTime = " ";		
    private String detectionLimit = " ";
    private String unit = " ";


    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("monitorid");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("factorid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("portid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("highlimit");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("lowlimit");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("nkhighlimit");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("nklowlimit");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("description");
        eiColumn.setFieldLength(255);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("status");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creator");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createdTime");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("modifier");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updatedTime");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("detectionLimit");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("unit");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public Tduhb06() {
        initMetaData();
    }

    /**
     * get the monitorid 
     * @return the monitorid
     */
    public String getMonitorid() {
        return this.monitorid;
    }

    /**
     * set the monitorid 
     */
    public void setMonitorid(String monitorid) {
        this.monitorid = monitorid;
    }

    /**
     * get the factorid 
     * @return the factorid
     */
    public String getFactorid() {
        return this.factorid;
    }

    /**
     * set the factorid 
     */
    public void setFactorid(String factorid) {
        this.factorid = factorid;
    }

    /**
     * get the portid 
     * @return the portid
     */
    public String getPortid() {
        return this.portid;
    }

    /**
     * set the portid 
     */
    public void setPortid(String portid) {
        this.portid = portid;
    }

    /**
     * get the highlimit 
     * @return the highlimit
     */
    public String getHighlimit() {
        return this.highlimit;
    }

    /**
     * set the highlimit 
     */
    public void setHighlimit(String highlimit) {
        this.highlimit = highlimit;
    }

    /**
     * get the lowlimit 
     * @return the lowlimit
     */
    public String getLowlimit() {
        return this.lowlimit;
    }

    /**
     * set the lowlimit 
     */
    public void setLowlimit(String lowlimit) {
        this.lowlimit = lowlimit;
    }

    /**
     * get the nkhighlimit 
     * @return the nkhighlimit
     */
    public String getNkhighlimit() {
        return this.nkhighlimit;
    }

    /**
     * set the nkhighlimit 
     */
    public void setNkhighlimit(String nkhighlimit) {
        this.nkhighlimit = nkhighlimit;
    }

    /**
     * get the nklowlimit 
     * @return the nklowlimit
     */
    public String getNklowlimit() {
        return this.nklowlimit;
    }

    /**
     * set the nklowlimit 
     */
    public void setNklowlimit(String nklowlimit) {
        this.nklowlimit = nklowlimit;
    }

    /**
     * get the description 
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * set the description 
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * get the status 
     * @return the status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * set the status 
     */
    public void setStatus(String status) {
        this.status = status;
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
     * get the detectionLimit 
     * @return the detectionLimit
     */
    public String getDetectionLimit() {
        return this.detectionLimit;
    }

    /**
     * set the detectionLimit 
     */
    public void setDetectionLimit(String detectionLimit) {
        this.detectionLimit = detectionLimit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setMonitorid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("monitorid")), monitorid));
        setFactorid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("factorid")), factorid));
        setPortid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("portid")), portid));
        setHighlimit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("highlimit")), highlimit));
        setLowlimit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("lowlimit")), lowlimit));
        setNkhighlimit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("nkhighlimit")), nkhighlimit));
        setNklowlimit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("nklowlimit")), nklowlimit));
        setDescription(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("description")), description));
        setStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("status")), status));
        setCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creator")), creator));
        setCreatedTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createdTime")), createdTime));
        setModifier(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("modifier")), modifier));
        setUpdatedTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updatedTime")), updatedTime));
        setDetectionLimit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("detectionLimit")), detectionLimit));
        setUnit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("unit")), unit));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("monitorid", StringUtils.toString(monitorid, eiMetadata.getMeta("monitorid")));
        map.put("factorid", StringUtils.toString(factorid, eiMetadata.getMeta("factorid")));
        map.put("portid", StringUtils.toString(portid, eiMetadata.getMeta("portid")));
        map.put("highlimit", StringUtils.toString(highlimit, eiMetadata.getMeta("highlimit")));
        map.put("lowlimit", StringUtils.toString(lowlimit, eiMetadata.getMeta("lowlimit")));
        map.put("nkhighlimit", StringUtils.toString(nkhighlimit, eiMetadata.getMeta("nkhighlimit")));
        map.put("nklowlimit", StringUtils.toString(nklowlimit, eiMetadata.getMeta("nklowlimit")));
        map.put("description", StringUtils.toString(description, eiMetadata.getMeta("description")));
        map.put("status", StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("creator", StringUtils.toString(creator, eiMetadata.getMeta("creator")));
        map.put("createdTime", StringUtils.toString(createdTime, eiMetadata.getMeta("createdTime")));
        map.put("modifier", StringUtils.toString(modifier, eiMetadata.getMeta("modifier")));
        map.put("updatedTime", StringUtils.toString(updatedTime, eiMetadata.getMeta("updatedTime")));
        map.put("detectionLimit", StringUtils.toString(detectionLimit, eiMetadata.getMeta("detectionLimit")));
        map.put("unit", StringUtils.toString(unit, eiMetadata.getMeta("unit")));
        return map;
    }
}