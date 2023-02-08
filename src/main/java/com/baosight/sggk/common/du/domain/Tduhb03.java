/**
* Generate time : 2020-09-07 21:10:51
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhb03
* 
*/
public class Tduhb03 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String procedureId = " ";		/* 工序编号*/
    private String procedureName = " ";		/* 工序名称*/
    private String departmentid = " ";		/* 部门编号*/
    private String description = " ";		/* 描述*/
    private String sort = " ";		/* 排序*/
    private String status = " ";		/* 状态*/
    private String creator = " ";		/* 新增人*/
    private String createdTime = " ";		/* 新增时间*/
    private String modifier = " ";		/* 修改人*/
    private String updatedTime = " ";		/* 修改时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("procedureId");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("工序编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("procedureName");
        eiColumn.setDescName("工序名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("departmentid");
        eiColumn.setDescName("部门编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("description");
        eiColumn.setDescName("描述");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sort");
        eiColumn.setDescName("排序");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("status");
        eiColumn.setDescName("状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creator");
        eiColumn.setDescName("新增人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createdTime");
        eiColumn.setDescName("新增时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("modifier");
        eiColumn.setDescName("修改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updatedTime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public Tduhb03() {
        initMetaData();
    }

    /**
     * get the procedureId - 工序编号
     * @return the procedureId
     */
    public String getProcedureId() {
        return this.procedureId;
    }

    /**
     * set the procedureId - 工序编号
     */
    public void setProcedureId(String procedureId) {
        this.procedureId = procedureId;
    }

    /**
     * get the procedureName - 工序名称
     * @return the procedureName
     */
    public String getProcedureName() {
        return this.procedureName;
    }

    /**
     * set the procedureName - 工序名称
     */
    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    /**
     * get the departmentid - 部门编号
     * @return the departmentid
     */
    public String getDepartmentid() {
        return this.departmentid;
    }

    /**
     * set the departmentid - 部门编号
     */
    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    /**
     * get the description - 描述
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * set the description - 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * get the sort - 排序
     * @return the sort
     */
    public String getSort() {
        return this.sort;
    }

    /**
     * set the sort - 排序
     */
    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * get the status - 状态
     * @return the status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * set the status - 状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * get the creator - 新增人
     * @return the creator
     */
    public String getCreator() {
        return this.creator;
    }

    /**
     * set the creator - 新增人
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * get the createdTime - 新增时间
     * @return the createdTime
     */
    public String getCreatedTime() {
        return this.createdTime;
    }

    /**
     * set the createdTime - 新增时间
     */
    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * get the modifier - 修改人
     * @return the modifier
     */
    public String getModifier() {
        return this.modifier;
    }

    /**
     * set the modifier - 修改人
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    /**
     * get the updatedTime - 修改时间
     * @return the updatedTime
     */
    public String getUpdatedTime() {
        return this.updatedTime;
    }

    /**
     * set the updatedTime - 修改时间
     */
    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setProcedureId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("procedureId")), procedureId));
        setProcedureName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("procedureName")), procedureName));
        setDepartmentid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentid")), departmentid));
        setDescription(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("description")), description));
        setSort(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sort")), sort));
        setStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("status")), status));
        setCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creator")), creator));
        setCreatedTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createdTime")), createdTime));
        setModifier(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("modifier")), modifier));
        setUpdatedTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updatedTime")), updatedTime));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("procedureId", StringUtils.toString(procedureId, eiMetadata.getMeta("procedureId")));
        map.put("procedureName", StringUtils.toString(procedureName, eiMetadata.getMeta("procedureName")));
        map.put("departmentid", StringUtils.toString(departmentid, eiMetadata.getMeta("departmentid")));
        map.put("description", StringUtils.toString(description, eiMetadata.getMeta("description")));
        map.put("sort", StringUtils.toString(sort, eiMetadata.getMeta("sort")));
        map.put("status", StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("creator", StringUtils.toString(creator, eiMetadata.getMeta("creator")));
        map.put("createdTime", StringUtils.toString(createdTime, eiMetadata.getMeta("createdTime")));
        map.put("modifier", StringUtils.toString(modifier, eiMetadata.getMeta("modifier")));
        map.put("updatedTime", StringUtils.toString(updatedTime, eiMetadata.getMeta("updatedTime")));
        return map;
    }
}