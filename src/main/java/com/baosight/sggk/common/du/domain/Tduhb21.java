/**
* Generate time : 2021-07-15 15:49:57
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhb21
* 
*/
public class Tduhb21 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String dischargeportid = " ";		
    private String facilityid = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("dischargeportid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("facilityid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public Tduhb21() {
        initMetaData();
    }

    /**
     * get the dischargeportid 
     * @return the dischargeportid
     */
    public String getDischargeportid() {
        return this.dischargeportid;
    }

    /**
     * set the dischargeportid 
     */
    public void setDischargeportid(String dischargeportid) {
        this.dischargeportid = dischargeportid;
    }

    /**
     * get the facilityid 
     * @return the facilityid
     */
    public String getFacilityid() {
        return this.facilityid;
    }

    /**
     * set the facilityid 
     */
    public void setFacilityid(String facilityid) {
        this.facilityid = facilityid;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setDischargeportid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dischargeportid")), dischargeportid));
        setFacilityid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("facilityid")), facilityid));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("dischargeportid", StringUtils.toString(dischargeportid, eiMetadata.getMeta("dischargeportid")));
        map.put("facilityid", StringUtils.toString(facilityid, eiMetadata.getMeta("facilityid")));
        return map;
    }
}