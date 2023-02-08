/**
* Generate time : 2021-03-02 15:25:04
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhd42
* 
*/
public class Tduhd47 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String monitorid = " ";
    private String factorid = " ";
    private String clock = " ";
    private String report = " ";
    private String unit = " ";

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("monitorid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("factorid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("unit");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("departmentid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("clock");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(7);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("report");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);




    }

    /**
     * the constructor
     */
    public Tduhd47() {
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
    

    public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}


    /**
     * get the clock 
     * @return the clock
     */
    public String getClock() {
        return this.clock;
    }

    /**
     * set the clock 
     */
    public void setClock(String clock) {
        this.clock = clock;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setMonitorid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("monitorid")), monitorid));
        setFactorid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("factorid")), factorid));
        setUnit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("unit")), unit));
        setClock(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("clock")), clock));
        setReport(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("report")), report));

    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("monitorid", StringUtils.toString(monitorid, eiMetadata.getMeta("monitorid")));
        map.put("factorid", StringUtils.toString(factorid, eiMetadata.getMeta("factorid")));
        map.put("unit", StringUtils.toString(unit, eiMetadata.getMeta("unit")));
        map.put("clock", StringUtils.toString(clock, eiMetadata.getMeta("clock")));
        map.put("report", StringUtils.toString(report, eiMetadata.getMeta("report")));

        return map;
    }
}