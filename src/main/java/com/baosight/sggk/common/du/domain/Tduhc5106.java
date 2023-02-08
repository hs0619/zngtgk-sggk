/**
* Generate time : 2021-11-02 22:46:51
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhc5106
* 
*/
public class Tduhc5106 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String abnormalId = " ";		
    private String abnormalTypeName = " ";		
    private String sort = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("abnormalId");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("abnormalTypeName");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sort");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public Tduhc5106() {
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
     * get the abnormalTypeName 
     * @return the abnormalTypeName
     */
    public String getAbnormalTypeName() {
        return this.abnormalTypeName;
    }

    /**
     * set the abnormalTypeName 
     */
    public void setAbnormalTypeName(String abnormalTypeName) {
        this.abnormalTypeName = abnormalTypeName;
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
     * get the value from Map
     */
    public void fromMap(Map map) {

        setAbnormalId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("abnormalId")), abnormalId));
        setAbnormalTypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("abnormalTypeName")), abnormalTypeName));
        setSort(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sort")), sort));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("abnormalId", StringUtils.toString(abnormalId, eiMetadata.getMeta("abnormalId")));
        map.put("abnormalTypeName", StringUtils.toString(abnormalTypeName, eiMetadata.getMeta("abnormalTypeName")));
        map.put("sort", StringUtils.toString(sort, eiMetadata.getMeta("sort")));
        return map;
    }
}