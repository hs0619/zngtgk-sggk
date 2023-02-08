/**
* Generate time : 2021-07-20 19:55:54
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhb01
* 
*/
public class Tduhb01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String departmentId = " ";		
    private String departmentName = " ";		
    private String parentid = " ";		
    private String description = " ";		
    private String level = " ";		
    private String sort = " ";		
    private String status = " ";		
    private String creator = " ";		
    private String createdTime = " ";		
    private String modifier = " ";		
    private String updatedTime = " ";		
    private String totalplan = " ";		
    private String type = "D1";		
    private String depProId = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("departmentId");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("departmentName");
        eiColumn.setFieldLength(200);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("parentid");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("description");
        eiColumn.setFieldLength(255);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("level");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sort");
        eiColumn.setFieldLength(10);
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

        eiColumn = new EiColumn("totalplan");
        eiColumn.setFieldLength(1);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("type");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("depProId");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public Tduhb01() {
        initMetaData();
    }

    /**
     * get the departmentId 
     * @return the departmentId
     */
    public String getDepartmentId() {
        return this.departmentId;
    }

    /**
     * set the departmentId 
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * get the departmentName 
     * @return the departmentName
     */
    public String getDepartmentName() {
        return this.departmentName;
    }

    /**
     * set the departmentName 
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * get the parentid 
     * @return the parentid
     */
    public String getParentid() {
        return this.parentid;
    }

    /**
     * set the parentid 
     */
    public void setParentid(String parentid) {
        this.parentid = parentid;
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
     * get the level 
     * @return the level
     */
    public String getLevel() {
        return this.level;
    }

    /**
     * set the level 
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * get the sort 
     * @return the sort
     */
    public String getSort() {
        return this.sort;
    }

    /**
     * set the sort 
     */
    public void setSort(String sort) {
        this.sort = sort;
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
     * get the totalplan 
     * @return the totalplan
     */
    public String getTotalplan() {
        return this.totalplan;
    }

    /**
     * set the totalplan 
     */
    public void setTotalplan(String totalplan) {
        this.totalplan = totalplan;
    }

    /**
     * get the type 
     * @return the type
     */
    public String getType() {
        return this.type;
    }

    /**
     * set the type 
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * get the depProId 
     * @return the depProId
     */
    public String getDepProId() {
        return this.depProId;
    }

    /**
     * set the depProId 
     */
    public void setDepProId(String depProId) {
        this.depProId = depProId;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setDepartmentId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentId")), departmentId));
        setDepartmentName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentName")), departmentName));
        setParentid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("parentid")), parentid));
        setDescription(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("description")), description));
        setLevel(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("level")), level));
        setSort(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sort")), sort));
        setStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("status")), status));
        setCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creator")), creator));
        setCreatedTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createdTime")), createdTime));
        setModifier(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("modifier")), modifier));
        setUpdatedTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updatedTime")), updatedTime));
        setTotalplan(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("totalplan")), totalplan));
        setType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("type")), type));
        setDepProId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("depProId")), depProId));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("departmentId", StringUtils.toString(departmentId, eiMetadata.getMeta("departmentId")));
        map.put("departmentName", StringUtils.toString(departmentName, eiMetadata.getMeta("departmentName")));
        map.put("parentid", StringUtils.toString(parentid, eiMetadata.getMeta("parentid")));
        map.put("description", StringUtils.toString(description, eiMetadata.getMeta("description")));
        map.put("level", StringUtils.toString(level, eiMetadata.getMeta("level")));
        map.put("sort", StringUtils.toString(sort, eiMetadata.getMeta("sort")));
        map.put("status", StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("creator", StringUtils.toString(creator, eiMetadata.getMeta("creator")));
        map.put("createdTime", StringUtils.toString(createdTime, eiMetadata.getMeta("createdTime")));
        map.put("modifier", StringUtils.toString(modifier, eiMetadata.getMeta("modifier")));
        map.put("updatedTime", StringUtils.toString(updatedTime, eiMetadata.getMeta("updatedTime")));
        map.put("totalplan", StringUtils.toString(totalplan, eiMetadata.getMeta("totalplan")));
        map.put("type", StringUtils.toString(type, eiMetadata.getMeta("type")));
        map.put("depProId", StringUtils.toString(depProId, eiMetadata.getMeta("depProId")));
        return map;
    }
}