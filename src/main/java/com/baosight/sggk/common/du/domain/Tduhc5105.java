/**
* Generate time : 2021-09-16 10:33:33
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhc5105
* 
*/
public class Tduhc5105 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String abnormalId = " ";		
    private String abnormalName = " ";		
    private String alarmtypeId = " ";		
    private String alarmtypeName = " ";		
    private String sort = " ";		
    private String status = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("abnormalId");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("abnormalName");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("alarmtypeId");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("alarmtypeName");
        eiColumn.setFieldLength(50);
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


    }

    /**
     * the constructor
     */
    public Tduhc5105() {
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
     * get the abnormalName 
     * @return the abnormalName
     */
    public String getAbnormalName() {
        return this.abnormalName;
    }

    /**
     * set the abnormalName 
     */
    public void setAbnormalName(String abnormalName) {
        this.abnormalName = abnormalName;
    }

    /**
     * get the alarmtypeId 
     * @return the alarmtypeId
     */
    public String getAlarmtypeId() {
        return this.alarmtypeId;
    }

    /**
     * set the alarmtypeId 
     */
    public void setAlarmtypeId(String alarmtypeId) {
        this.alarmtypeId = alarmtypeId;
    }

    /**
     * get the alarmtypeName 
     * @return the alarmtypeName
     */
    public String getAlarmtypeName() {
        return this.alarmtypeName;
    }

    /**
     * set the alarmtypeName 
     */
    public void setAlarmtypeName(String alarmtypeName) {
        this.alarmtypeName = alarmtypeName;
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
     * get the value from Map
     */
    public void fromMap(Map map) {

        setAbnormalId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("abnormalId")), abnormalId));
        setAbnormalName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("abnormalName")), abnormalName));
        setAlarmtypeId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("alarmtypeId")), alarmtypeId));
        setAlarmtypeName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("alarmtypeName")), alarmtypeName));
        setSort(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sort")), sort));
        setStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("status")), status));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("abnormalId", StringUtils.toString(abnormalId, eiMetadata.getMeta("abnormalId")));
        map.put("abnormalName", StringUtils.toString(abnormalName, eiMetadata.getMeta("abnormalName")));
        map.put("alarmtypeId", StringUtils.toString(alarmtypeId, eiMetadata.getMeta("alarmtypeId")));
        map.put("alarmtypeName", StringUtils.toString(alarmtypeName, eiMetadata.getMeta("alarmtypeName")));
        map.put("sort", StringUtils.toString(sort, eiMetadata.getMeta("sort")));
        map.put("status", StringUtils.toString(status, eiMetadata.getMeta("status")));
        return map;
    }
}