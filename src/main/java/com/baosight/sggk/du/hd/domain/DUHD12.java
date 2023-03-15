/**
 * Generate time : 2023-03-06 15:15:05
 * Version : 1.0
 */
package com.baosight.sggk.du.hd.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;

import java.util.HashMap;
import java.util.Map;

import com.baosight.iplat4j.core.util.StringUtils;

/**
 * THdIndexTable
 *
 */
public class DUHD12 extends DaoEPBase {

    private String indexId = " ";        /* 主键ID*/
    private String indexName = " ";        /* 指标名称*/
    private String status = " ";        /* 状态*/
    private String description = " ";   /* 备注 */

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("indexId");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("indexName");
        eiColumn.setDescName("指标名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("status");
        eiColumn.setDescName("状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("description");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DUHD12() {
        initMetaData();
    }

    /**
     * get the indexId - 主键ID
     * @return the indexId
     */
    public String getIndexId() {
        return this.indexId;
    }

    /**
     * set the indexId - 主键ID
     */
    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }

    /**
     * get the indexName - 指标名称
     * @return the indexName
     */
    public String getIndexName() {
        return this.indexName;
    }

    /**
     * set the indexName - 指标名称
     */
    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setIndexId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("indexId")), indexId));
        setIndexName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("indexName")), indexName));
        setStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("status")), status));
        setDescription(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("description")), description));

    }

    /**
     * set the value to Map
     */
    public Map toMap() {

        Map map = new HashMap();
        map.put("indexId", StringUtils.toString(indexId, eiMetadata.getMeta("indexId")));
        map.put("indexName", StringUtils.toString(indexName, eiMetadata.getMeta("indexName")));
        map.put("status", StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("description", StringUtils.toString(description, eiMetadata.getMeta("description")));

        return map;

    }
}