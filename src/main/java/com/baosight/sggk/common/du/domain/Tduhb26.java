/**
* Generate time : 2021-04-23 15:29:54
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhb26
* 
*/
public class Tduhb26 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String avdid = " ";		
    private String personid = " ";		
    private String dischargeportid = " ";		
    private String siteid = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("avdid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("personid");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dischargeportid");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("siteid");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public Tduhb26() {
        initMetaData();
    }

    /**
     * get the avdid 
     * @return the avdid
     */
    public String getAvdid() {
        return this.avdid;
    }

    /**
     * set the avdid 
     */
    public void setAvdid(String avdid) {
        this.avdid = avdid;
    }

    /**
     * get the personid 
     * @return the personid
     */
    public String getPersonid() {
        return this.personid;
    }

    /**
     * set the personid 
     */
    public void setPersonid(String personid) {
        this.personid = personid;
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
     * get the value from Map
     */
    public void fromMap(Map map) {

        setAvdid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("avdid")), avdid));
        setPersonid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("personid")), personid));
        setDischargeportid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dischargeportid")), dischargeportid));
        setSiteid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("siteid")), siteid));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("avdid", StringUtils.toString(avdid, eiMetadata.getMeta("avdid")));
        map.put("personid", StringUtils.toString(personid, eiMetadata.getMeta("personid")));
        map.put("dischargeportid", StringUtils.toString(dischargeportid, eiMetadata.getMeta("dischargeportid")));
        map.put("siteid", StringUtils.toString(siteid, eiMetadata.getMeta("siteid")));
        return map;
    }
}