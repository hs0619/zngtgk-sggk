/**
* Generate time : 2021-01-28 17:58:38
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhd41
* 
*/
public class Tduhd4103 extends DaoEPBase {

    private static final long serialVersionUID = 1L;
    private String departmentname = " ";//产部
    private String clock = " ";//时间
    private String finalvalue = " ";//总数

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;



        eiColumn = new EiColumn("clock");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("finalvalue");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("departmentname");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    public String getFinalvalue() {
        return finalvalue;
    }

    public void setFinalvalue(String finalvalue) {
        this.finalvalue = finalvalue;
    }

    /**
     * the constructor
     */
    public Tduhd4103() {
        initMetaData();
    }



    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {
        setDepartmentname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentname")), departmentname));
        setClock(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("clock")), clock));
        setFinalvalue(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("finalvalue")), finalvalue));

    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("departmentname", StringUtils.toString(departmentname, eiMetadata.getMeta("departmentname")));
        map.put("clock", StringUtils.toString(clock, eiMetadata.getMeta("clock")));
        map.put("finalvalue", StringUtils.toString(finalvalue, eiMetadata.getMeta("finalvalue")));
        return map;
    }
}