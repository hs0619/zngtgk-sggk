/**
* Generate time : 2021-07-15 11:07:05
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.du.hc.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* DUHC230201
* 
*/
public class DUHC230201 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String monitorid = " ";		
    private String factorid = " ";		
    private String factorname = " ";		
    private String isplaninner = " ";		
    private String isplanout = " ";		
    private String israte = " ";		
    private String unit = " ";		
    private String iscompare = " ";
    
    private String itemunit = " ";		
    private String itemlimit = " ";	

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("monitorid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("factorid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("factorname");
        eiColumn.setFieldLength(255);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isplaninner");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isplanout");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("israte");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("unit");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("iscompare");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemunit");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemlimit");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public DUHC230201() {
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

    /**
     * get the factorname 
     * @return the factorname
     */
    public String getFactorname() {
        return this.factorname;
    }

    /**
     * set the factorname 
     */
    public void setFactorname(String factorname) {
        this.factorname = factorname;
    }

    /**
     * get the isplaninner 
     * @return the isplaninner
     */
    public String getIsplaninner() {
        return this.isplaninner;
    }

    /**
     * set the isplaninner 
     */
    public void setIsplaninner(String isplaninner) {
        this.isplaninner = isplaninner;
    }

    /**
     * get the isplanout 
     * @return the isplanout
     */
    public String getIsplanout() {
        return this.isplanout;
    }

    /**
     * set the isplanout 
     */
    public void setIsplanout(String isplanout) {
        this.isplanout = isplanout;
    }

    /**
     * get the israte 
     * @return the israte
     */
    public String getIsrate() {
        return this.israte;
    }

    /**
     * set the israte 
     */
    public void setIsrate(String israte) {
        this.israte = israte;
    }

    /**
     * get the unit 
     * @return the unit
     */
    public String getUnit() {
        return this.unit;
    }

    /**
     * set the unit 
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    
    
    public String getItemunit() {
		return itemunit;
	}

	public void setItemunit(String itemunit) {
		this.itemunit = itemunit;
	}

	public String getItemlimit() {
		return itemlimit;
	}

	public void setItemlimit(String itemlimit) {
		this.itemlimit = itemlimit;
	}

	public String getIscompare() {
		return iscompare;
	}

	public void setIscompare(String iscompare) {
		this.iscompare = iscompare;
	}

	/**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setMonitorid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("monitorid")), monitorid));
        setFactorid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("factorid")), factorid));
        setFactorname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("factorname")), factorname));
        setIsplaninner(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isplaninner")), isplaninner));
        setIsplanout(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isplanout")), isplanout));
        setIsrate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("israte")), israte));
        setUnit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("unit")), unit));
        setIscompare(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("iscompare")), iscompare));
        
        setItemlimit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemlimit")), itemlimit));
        setItemunit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemunit")), itemunit));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("monitorid", StringUtils.toString(monitorid, eiMetadata.getMeta("monitorid")));
        map.put("factorid", StringUtils.toString(factorid, eiMetadata.getMeta("factorid")));
        map.put("factorname", StringUtils.toString(factorname, eiMetadata.getMeta("factorname")));
        map.put("isplaninner", StringUtils.toString(isplaninner, eiMetadata.getMeta("isplaninner")));
        map.put("isplanout", StringUtils.toString(isplanout, eiMetadata.getMeta("isplanout")));
        map.put("israte", StringUtils.toString(israte, eiMetadata.getMeta("israte")));
        map.put("unit", StringUtils.toString(unit, eiMetadata.getMeta("unit")));
        map.put("iscompare", StringUtils.toString(iscompare, eiMetadata.getMeta("iscompare")));
        
        map.put("itemlimit", StringUtils.toString(itemlimit, eiMetadata.getMeta("itemlimit")));
        map.put("itemunit", StringUtils.toString(itemunit, eiMetadata.getMeta("itemunit")));
        return map;
    }
}