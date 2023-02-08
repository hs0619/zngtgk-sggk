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
public class Tduhd43 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String departmentid = " ";		
    private String procedureid = " ";	
    private String itemname = " ";	
    private String itemcode = " ";		
    private String licenceid = " ";		
    private String factorid = " ";	
    private String clock = " ";	
    private String finalvalue = " ";
    private String equivalent = " ";		
    private String tax = " ";		
    private String totalequivalent = " ";		
    private String totaltax = " ";
    private String reductiontax = " ";

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("itemcode");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("clock");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("factorid");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("finalvalue");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("equivalent");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("tax");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("totalequivalent");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("totaltax");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("reductiontax");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("totalequivalent");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("departmentid");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("procedureid");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("itemname");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("licenceid");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public Tduhd43() {
        initMetaData();
    }

    

    public String getItemcode() {
		return itemcode;
	}

	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}

	public String getClock() {
		return clock;
	}

	public void setClock(String clock) {
		this.clock = clock;
	}

	public String getFactorid() {
		return factorid;
	}

	public void setFactorid(String factorid) {
		this.factorid = factorid;
	}

	public String getFinalvalue() {
		return finalvalue;
	}

	public void setFinalvalue(String finalvalue) {
		this.finalvalue = finalvalue;
	}

	public String getEquivalent() {
		return equivalent;
	}

	public void setEquivalent(String equivalent) {
		this.equivalent = equivalent;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getTotalequivalent() {
		return totalequivalent;
	}

	public void setTotalequivalent(String totalequivalent) {
		this.totalequivalent = totalequivalent;
	}

	public String getTotaltax() {
		return totaltax;
	}

	public void setTotaltax(String totaltax) {
		this.totaltax = totaltax;
	}

	public String getReductiontax() {
		return reductiontax;
	}

	public void setReductiontax(String reductiontax) {
		this.reductiontax = reductiontax;
	}

	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

	public String getProcedureid() {
		return procedureid;
	}

	public void setProcedureid(String procedureid) {
		this.procedureid = procedureid;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	

	public String getLicenceid() {
		return licenceid;
	}

	public void setLicenceid(String licenceid) {
		this.licenceid = licenceid;
	}

	/**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setItemcode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemcode")), itemcode));
        setClock(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("clock")), clock));
        setFactorid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("factorid")), factorid));
        setFinalvalue(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("finalvalue")), finalvalue));
        setEquivalent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("equivalent")), equivalent));
        setTax(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("tax")), tax));
        setTotalequivalent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("totalequivalent")), totalequivalent));
        setTotaltax(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("totaltax")), totaltax));
        setReductiontax(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("reductiontax")), reductiontax));
        setDepartmentid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentid")), departmentid));
        setProcedureid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("procedureid")), procedureid));
        setItemname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemname")), itemname));
        setLicenceid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("licenceid")), licenceid));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("itemcode", StringUtils.toString(itemcode, eiMetadata.getMeta("itemcode")));
        map.put("clock", StringUtils.toString(clock, eiMetadata.getMeta("clock")));
        map.put("factorid", StringUtils.toString(factorid, eiMetadata.getMeta("factorid")));
        map.put("finalvalue", StringUtils.toString(finalvalue, eiMetadata.getMeta("finalvalue")));
        map.put("equivalent", StringUtils.toString(equivalent, eiMetadata.getMeta("equivalent")));
        map.put("tax", StringUtils.toString(tax, eiMetadata.getMeta("tax")));
        map.put("totalequivalent", StringUtils.toString(totalequivalent, eiMetadata.getMeta("totalequivalent")));
        map.put("totaltax", StringUtils.toString(totaltax, eiMetadata.getMeta("totaltax")));
        map.put("reductiontax", StringUtils.toString(reductiontax, eiMetadata.getMeta("reductiontax")));
        map.put("departmentid", StringUtils.toString(departmentid, eiMetadata.getMeta("departmentid")));
        map.put("procedureid", StringUtils.toString(procedureid, eiMetadata.getMeta("procedureid")));
        map.put("itemname", StringUtils.toString(itemname, eiMetadata.getMeta("itemname")));
        map.put("licenceid", StringUtils.toString(licenceid, eiMetadata.getMeta("licenceid")));
        return map;
    }
}