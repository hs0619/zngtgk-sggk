/**
* Generate time : 2020-12-02 14:44:13
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhd4001
* 
*/
public class Tduhd4001 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String clock = " ";		
    private String itemcode = " ";		
    private String monitorid = " ";		
    private String factorid = " ";	
    private String factorname = " ";	
    private String computermode = " ";		
    private String shareitem = " ";		
    private String istotal = " ";		
    private String istax = " ";		
    private String limit = " ";		
    private String equivalue = " ";		

    private String taxsourcecode = " ";	
    private String unittax = " ";	
    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("clock");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(7);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemcode");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

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
        
        eiColumn = new EiColumn("factorname");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("computermode");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("shareitem");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("istotal");
        eiColumn.setFieldLength(1);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("istax");
        eiColumn.setFieldLength(1);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("limit");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("equivalue");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("taxsourcecode");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("unittax");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public Tduhd4001() {
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
    public String getFactorname() {
		return factorname;
	}

	public void setFactorname(String factorname) {
		this.factorname = factorname;
	}
    /**
     * get the computermode 
     * @return the computermode
     */
    public String getComputermode() {
        return this.computermode;
    }

    /**
     * set the computermode 
     */
    public void setComputermode(String computermode) {
        this.computermode = computermode;
    }

    /**
     * get the shareitem 
     * @return the shareitem
     */
    public String getShareitem() {
        return this.shareitem;
    }

    /**
     * set the shareitem 
     */
    public void setShareitem(String shareitem) {
        this.shareitem = shareitem;
    }

    /**
     * get the istotal 
     * @return the istotal
     */
    public String getIstotal() {
        return this.istotal;
    }

    /**
     * set the istotal 
     */
    public void setIstotal(String istotal) {
        this.istotal = istotal;
    }

    /**
     * get the istax 
     * @return the istax
     */
    public String getIstax() {
        return this.istax;
    }

    /**
     * set the istax 
     */
    public void setIstax(String istax) {
        this.istax = istax;
    }

    /**
     * get the limit 
     * @return the limit
     */
    public String getLimit() {
        return this.limit;
    }

    /**
     * set the limit 
     */
    public void setLimit(String limit) {
        this.limit = limit;
    }
    
    

    public String getEquivalue() {
		return this.equivalue;
	}

	public void setEquivalue(String equivalue) {
		this.equivalue = equivalue;
	}

	public String getTaxsourcecode() {
		return taxsourcecode;
	}

	public void setTaxsourcecode(String taxsourcecode) {
		this.taxsourcecode = taxsourcecode;
	}

	public String getUnittax() {
		return unittax;
	}

	public void setUnittax(String unittax) {
		this.unittax = unittax;
	}

	/**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setClock(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("clock")), clock));
        setItemcode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemcode")), itemcode));
        setMonitorid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("monitorid")), monitorid));
        setFactorid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("factorid")), factorid));
        setFactorname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("factorname")), factorname));
        setComputermode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("computermode")), computermode));
        setShareitem(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("shareitem")), shareitem));
        setIstotal(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("istotal")), istotal));
        setIstax(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("istax")), istax));
        setLimit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("limit")), limit));
        setEquivalue(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("equivalue")), equivalue));
        
        setTaxsourcecode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("taxsourcecode")), taxsourcecode));
        setUnittax(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("unittax")), unittax));
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
        map.put("factorname", StringUtils.toString(factorname, eiMetadata.getMeta("factorname")));
        map.put("computermode", StringUtils.toString(computermode, eiMetadata.getMeta("computermode")));
        map.put("shareitem", StringUtils.toString(shareitem, eiMetadata.getMeta("shareitem")));
        map.put("istotal", StringUtils.toString(istotal, eiMetadata.getMeta("istotal")));
        map.put("istax", StringUtils.toString(istax, eiMetadata.getMeta("istax")));
        map.put("limit", StringUtils.toString(limit, eiMetadata.getMeta("limit")));
        map.put("equivalue", StringUtils.toString(equivalue, eiMetadata.getMeta("equivalue")));
        
        map.put("taxsourcecode", StringUtils.toString(taxsourcecode, eiMetadata.getMeta("taxsourcecode")));
        map.put("unittax", StringUtils.toString(unittax, eiMetadata.getMeta("unittax")));
        return map;
    }
}