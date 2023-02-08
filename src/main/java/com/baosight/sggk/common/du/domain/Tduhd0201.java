/**
* Generate time : 2021-02-25 15:58:06
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
* Tduhd0201
* 
*/
public class Tduhd0201 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String clock = " ";	
    private String dataclock = " ";	
    private String itemcode = " ";	
    private String itemname = " ";	
    private BigDecimal itemvalue = null;		
    private String remark = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("clock");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("dataclock");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemcode");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("itemname");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemvalue");
        eiColumn.setScaleLength(2);
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setFieldLength(200);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public Tduhd0201() {
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

    
    public String getDataclock() {
		return dataclock;
	}

	public void setDataclock(String dataclock) {
		this.dataclock = dataclock;
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
     * get the itemvalue 
     * @return the itemvalue
     */
    public BigDecimal getItemvalue() {
        return this.itemvalue;
    }

    /**
     * set the itemvalue 
     */
    public void setItemvalue(BigDecimal itemvalue) {
        this.itemvalue = itemvalue;
    }

    /**
     * get the remark 
     * @return the remark
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * set the remark 
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    
    public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	/**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setClock(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("clock")), clock));
        setDataclock(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataclock")), dataclock));
        setItemcode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemcode")), itemcode));
        setItemname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemname")), itemname));
        setItemvalue(NumberUtils.toBigDecimal(StringUtils.toString(map.get("itemvalue")), itemvalue));
        setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("clock", StringUtils.toString(clock, eiMetadata.getMeta("clock")));
        map.put("dataclock", StringUtils.toString(dataclock, eiMetadata.getMeta("dataclock")));
        map.put("itemcode", StringUtils.toString(itemcode, eiMetadata.getMeta("itemcode")));
        map.put("itemname", StringUtils.toString(itemname, eiMetadata.getMeta("itemname")));
        map.put("itemvalue", StringUtils.toString(itemvalue, eiMetadata.getMeta("itemvalue")));
        map.put("remark", StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        return map;
    }
}