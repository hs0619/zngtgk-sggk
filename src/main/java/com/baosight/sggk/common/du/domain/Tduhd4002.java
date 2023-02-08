/**
* Generate time : 2021-01-18 10:51:59
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhd4002
* 
*/
public class Tduhd4002 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String clock = " ";		
    private String itemcode = " ";		
    private String monitorid = " ";		
    private String factorid = " ";		
    private String category = " ";		
    private String propertycode = " ";		
    private String formulavalue = " ";	
    private String valueformat = " ";	
    private Integer seq = new Integer(0);		
    private String totaltype = " ";	
    private String datavalue = " ";	
    private String datatime = " ";	

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("clock");
        eiColumn.setFieldLength(7);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemcode");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("monitorid");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("factorid");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("category");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("propertycode");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("formulavalue");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("valueformat");
        eiColumn.setFieldLength(60);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("seq");
        eiColumn.setType("N");
        eiColumn.setScaleLength(0);
        eiColumn.setFieldLength(0);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("totaltype");
        eiColumn.setFieldLength(2);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("datavalue");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("datatime");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public Tduhd4002() {
        initMetaData();
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
     * get the itemcode 
     * @return the itemcode
     */
    public String getItemcode() {
        return this.itemcode;
    }

    /**
     * set the itemcode 
     */
    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
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
     * get the category 
     * @return the category
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * set the category 
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * get the propertycode 
     * @return the propertycode
     */
    public String getPropertycode() {
        return this.propertycode;
    }

    /**
     * set the propertycode 
     */
    public void setPropertycode(String propertycode) {
        this.propertycode = propertycode;
    }

    /**
     * get the value 
     * @return the value
     */
    public String getFormulavalue() {
        return this.formulavalue;
    }

    /**
     * set the value 
     */
    public void setFormulavalue(String formulavalue) {
        this.formulavalue = formulavalue;
    }
    
    /**
     * get the valueformat 
     * @return the valueformat
     */
    public String getValueformat() {
        return this.valueformat;
    }

    /**
     * set the valueformat 
     */
    public void setValueformat(String valueformat) {
        this.valueformat = valueformat;
    }

    /**
     * get the seq 
     * @return the seq
     */
    public Integer getSeq() {
        return this.seq;
    }

    /**
     * set the seq 
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    /**
     * get the totaltype 
     * @return the totaltype
     */
    public String getTotaltype() {
        return this.totaltype;
    }

    /**
     * set the totaltype 
     */
    public void setTotaltype(String totaltype) {
        this.totaltype = totaltype;
    }
    
    

    public String getDatavalue() {
		return datavalue;
	}

	public void setDatavalue(String datavalue) {
		this.datavalue = datavalue;
	}

	public String getDatatime() {
		return datatime;
	}

	public void setDatatime(String datatime) {
		this.datatime = datatime;
	}

	/**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setClock(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("clock")), clock));
        setItemcode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemcode")), itemcode));
        setMonitorid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("monitorid")), monitorid));
        setFactorid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("factorid")), factorid));
        setCategory(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("category")), category));
        setPropertycode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("propertycode")), propertycode));
        setFormulavalue(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("formulavalue")), formulavalue));
        setValueformat(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("valueformat")), valueformat));
        setSeq(NumberUtils.toInteger(StringUtils.toString(map.get("seq")), seq));
        setTotaltype(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("totaltype")), totaltype));
        setDatavalue(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("datavalue")), datavalue));
        setDatatime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("datatime")), datatime));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("clock", StringUtils.toString(clock, eiMetadata.getMeta("clock")));
        map.put("itemcode", StringUtils.toString(itemcode, eiMetadata.getMeta("itemcode")));
        map.put("monitorid", StringUtils.toString(monitorid, eiMetadata.getMeta("monitorid")));
        map.put("factorid", StringUtils.toString(factorid, eiMetadata.getMeta("factorid")));
        map.put("category", StringUtils.toString(category, eiMetadata.getMeta("category")));
        map.put("propertycode", StringUtils.toString(propertycode, eiMetadata.getMeta("propertycode")));
        map.put("formulavalue", StringUtils.toString(formulavalue, eiMetadata.getMeta("formulavalue")));
        map.put("valueformat", StringUtils.toString(valueformat, eiMetadata.getMeta("valueformat")));
        map.put("seq", StringUtils.toString(seq, eiMetadata.getMeta("seq")));
        map.put("totaltype", StringUtils.toString(totaltype, eiMetadata.getMeta("totaltype")));
        map.put("datavalue", StringUtils.toString(datavalue, eiMetadata.getMeta("datavalue")));
        map.put("datatime", StringUtils.toString(datatime, eiMetadata.getMeta("datatime")));
        return map;
    }
}