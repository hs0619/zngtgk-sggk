/**
* Generate time : 2021-04-29 22:52:09
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhb27
* 
*/
public class Tduhb27 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String siteid = " ";		
    private String issend = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("siteid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("issend");
        eiColumn.setFieldLength(5);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public Tduhb27() {
        initMetaData();
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
     * get the issend 
     * @return the issend
     */
    public String getIssend() {
        return this.issend;
    }

    /**
     * set the issend 
     */
    public void setIssend(String issend) {
        this.issend = issend;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setSiteid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("siteid")), siteid));
        setIssend(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("issend")), issend));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("siteid", StringUtils.toString(siteid, eiMetadata.getMeta("siteid")));
        map.put("issend", StringUtils.toString(issend, eiMetadata.getMeta("issend")));
        return map;
    }
}