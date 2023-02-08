/**
* Generate time : 2021-03-02 15:25:04
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
* Tduhd42
* 
*/
public class Tduhd42 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String monitorid = " ";		
    private String factorid = " ";		
    private String departmentid = " ";		
    private String clock = " ";		
    private BigDecimal quarter1 = null;		
    private BigDecimal quarter2 = null;		
    private BigDecimal quarter3 = null;		
    private BigDecimal quarter4 = null;
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

        eiColumn = new EiColumn("quarter1");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("quarter2");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("quarter3");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("quarter4");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public Tduhd42() {
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
     * get the departmentid 
     * @return the departmentid
     */
    public String getDepartmentid() {
        return this.departmentid;
    }

    /**
     * set the departmentid 
     */
    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
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

    /**
     * get the quarter1 
     * @return the quarter1
     */
    public BigDecimal getQuarter1() {
        return this.quarter1;
    }

    /**
     * set the quarter1 
     */
    public void setQuarter1(BigDecimal quarter1) {
        this.quarter1 = quarter1;
    }

    /**
     * get the quarter2 
     * @return the quarter2
     */
    public BigDecimal getQuarter2() {
        return this.quarter2;
    }

    /**
     * set the quarter2 
     */
    public void setQuarter2(BigDecimal quarter2) {
        this.quarter2 = quarter2;
    }

    /**
     * get the quarter3 
     * @return the quarter3
     */
    public BigDecimal getQuarter3() {
        return this.quarter3;
    }

    /**
     * set the quarter3 
     */
    public void setQuarter3(BigDecimal quarter3) {
        this.quarter3 = quarter3;
    }

    /**
     * get the quarter4 
     * @return the quarter4
     */
    public BigDecimal getQuarter4() {
        return this.quarter4;
    }

    /**
     * set the quarter4 
     */
    public void setQuarter4(BigDecimal quarter4) {
        this.quarter4 = quarter4;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setMonitorid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("monitorid")), monitorid));
        setFactorid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("factorid")), factorid));
        setUnit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("unit")), unit));
        setDepartmentid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentid")), departmentid));
        setClock(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("clock")), clock));
        setQuarter1(NumberUtils.toBigDecimal(StringUtils.toString(map.get("quarter1")), quarter1));
        setQuarter2(NumberUtils.toBigDecimal(StringUtils.toString(map.get("quarter2")), quarter2));
        setQuarter3(NumberUtils.toBigDecimal(StringUtils.toString(map.get("quarter3")), quarter3));
        setQuarter4(NumberUtils.toBigDecimal(StringUtils.toString(map.get("quarter4")), quarter4));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("monitorid", StringUtils.toString(monitorid, eiMetadata.getMeta("monitorid")));
        map.put("factorid", StringUtils.toString(factorid, eiMetadata.getMeta("factorid")));
        map.put("unit", StringUtils.toString(unit, eiMetadata.getMeta("unit")));
        map.put("departmentid", StringUtils.toString(departmentid, eiMetadata.getMeta("departmentid")));
        map.put("clock", StringUtils.toString(clock, eiMetadata.getMeta("clock")));
        map.put("quarter1", StringUtils.toString(quarter1, eiMetadata.getMeta("quarter1")));
        map.put("quarter2", StringUtils.toString(quarter2, eiMetadata.getMeta("quarter2")));
        map.put("quarter3", StringUtils.toString(quarter3, eiMetadata.getMeta("quarter3")));
        map.put("quarter4", StringUtils.toString(quarter4, eiMetadata.getMeta("quarter4")));
        return map;
    }
}