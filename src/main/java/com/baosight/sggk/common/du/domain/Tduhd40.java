/**
* Generate time : 2020-12-01 13:26:19
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhd40
* 
*/
public class Tduhd40 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String clock = " ";		//项目日期
    //private String computerclock = " ";//公式日期
    private String itemcode = " ";		
    private String itemname = " ";		
    private String monitorid = " ";		
    private String departmentid = " ";
    private String departmentname = " ";
    private String procedureid = " ";	
    private String procedurename = " ";	
    private String dischargeid = " ";		
    private String licenceid = " ";		
    private String istotal = " ";		
    private String istax = " ";		
    private String pkid = " ";		
    private String taxsourcecode = " ";		
    private String youzuzhi = " ";
    private String portname = " ";

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
        
//        eiColumn = new EiColumn("computerclock");
//        eiColumn.setFieldLength(7);
//        eiColumn.setDescName(" ");
//        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemcode");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemname");
        eiColumn.setFieldLength(200);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("monitorid");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("departmentid");
        eiColumn.setFieldLength(64);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("departmentname");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("procedureid");
        eiColumn.setFieldLength(64);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("procedurename");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dischargeid");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("licenceid");
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

        eiColumn = new EiColumn("pkid");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("taxsourcecode");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("youzuzhi");
        eiColumn.setFieldLength(1);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("portname");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

    }

    /**
     * the constructor
     */
    public Tduhd40() {
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
    
//	public String getComputerclock() {
//		return this.computerclock;
//	}
//
//	public void setComputerclock(String computerclock) {
//		this.computerclock = computerclock;
//	}

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
     * get the itemname 
     * @return the itemname
     */
    public String getItemname() {
        return this.itemname;
    }

    /**
     * set the itemname 
     */
    public void setItemname(String itemname) {
        this.itemname = itemname;
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
     * get the departmentid 
     * @return the departmentid
     */
    public String getDepartmentid() {
        return this.departmentid;
    }

    /**
     * set the departmentname 
     */
    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }
    
    /**
     * get the departmentname 
     * @return the departmentname
     */
    public String getDepartmentname() {
        return this.departmentname;
    }

    /**
     * set the departmentid 
     */
    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    /**
     * get the procedureid 
     * @return the procedureid
     */
    public String getProcedureid() {
        return this.procedureid;
    }

    /**
     * set the procedurename 
     */
    public void setProcedurename(String procedurename) {
        this.procedurename = procedurename;
    }

    /**
     * get the procedureid 
     * @return the procedureid
     */
    public String getProcedurename() {
        return this.procedurename;
    }

    /**
     * set the procedureid 
     */
    public void setProcedureid(String procedureid) {
        this.procedureid = procedureid;
    }

    
    /**
     * get the dischargeid 
     * @return the dischargeid
     */
    public String getDischargeid() {
        return this.dischargeid;
    }

    /**
     * set the dischargeid 
     */
    public void setDischargeid(String dischargeid) {
        this.dischargeid = dischargeid;
    }

    /**
     * get the licenceid 
     * @return the licenceid
     */
    public String getLicenceid() {
        return this.licenceid;
    }

    /**
     * set the licenceid 
     */
    public void setLicenceid(String licenceid) {
        this.licenceid = licenceid;
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
     * get the pkid 
     * @return the pkid
     */
    public String getPkid() {
        return this.pkid;
    }

    /**
     * set the pkid 
     */
    public void setPkid(String pkid) {
        this.pkid = pkid;
    }

    /**
     * get the taxsourcecode 
     * @return the taxsourcecode
     */
    public String getTaxsourcecode() {
        return this.taxsourcecode;
    }

    /**
     * set the taxsourcecode 
     */
    public void setTaxsourcecode(String taxsourcecode) {
        this.taxsourcecode = taxsourcecode;
    }

    /**
     * get the youzuzhi 
     * @return the youzuzhi
     */
    public String getYouzuzhi() {
        return this.youzuzhi;
    }

    /**
     * set the youzuzhi 
     */
    public void setYouzuzhi(String youzuzhi) {
        this.youzuzhi = youzuzhi;
    }
    /**
     * get the portname 
     * @return the portname
     */
    public String getPortname() {
        return this.portname;
    }

    /**
     * set the portname 
     */
    public void setPortname(String portname) {
        this.portname = portname;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setClock(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("clock")), clock));
        //setComputerclock(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("computerclock")), computerclock));
        setItemcode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemcode")), itemcode));
        setItemname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemname")), itemname));
        setMonitorid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("monitorid")), monitorid));
        setDepartmentid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentid")), departmentid));
        setProcedureid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("procedureid")), procedureid));
        setDepartmentname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentname")), departmentname));
        setProcedurename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("procedurename")), procedurename));
        setDischargeid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dischargeid")), dischargeid));
        setLicenceid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("licenceid")), licenceid));
        setIstotal(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("istotal")), istotal));
        setIstax(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("istax")), istax));
        setPkid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("pkid")), pkid));
        setTaxsourcecode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("taxsourcecode")), taxsourcecode));
        setYouzuzhi(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("youzuzhi")), youzuzhi));
        setPortname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("portname")), portname));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("clock", StringUtils.toString(clock, eiMetadata.getMeta("clock")));
        //map.put("computerclock",StringUtils.toString(computerclock, eiMetadata.getMeta("computerclock")));
        map.put("itemcode", StringUtils.toString(itemcode, eiMetadata.getMeta("itemcode")));
        map.put("itemname", StringUtils.toString(itemname, eiMetadata.getMeta("itemname")));
        map.put("monitorid", StringUtils.toString(monitorid, eiMetadata.getMeta("monitorid")));
        map.put("departmentid", StringUtils.toString(departmentid, eiMetadata.getMeta("departmentid")));
        map.put("procedureid", StringUtils.toString(procedureid, eiMetadata.getMeta("procedureid")));
        map.put("departmentname", StringUtils.toString(departmentname, eiMetadata.getMeta("departmentname")));
        map.put("procedurename", StringUtils.toString(procedurename, eiMetadata.getMeta("procedurename")));
        map.put("dischargeid", StringUtils.toString(dischargeid, eiMetadata.getMeta("dischargeid")));
        map.put("licenceid", StringUtils.toString(licenceid, eiMetadata.getMeta("licenceid")));
        map.put("istotal", StringUtils.toString(istotal, eiMetadata.getMeta("istotal")));
        map.put("istax", StringUtils.toString(istax, eiMetadata.getMeta("istax")));
        map.put("pkid", StringUtils.toString(pkid, eiMetadata.getMeta("pkid")));
        map.put("taxsourcecode", StringUtils.toString(taxsourcecode, eiMetadata.getMeta("taxsourcecode")));
        map.put("youzuzhi", StringUtils.toString(youzuzhi, eiMetadata.getMeta("youzuzhi")));
        map.put("portname", StringUtils.toString(portname, eiMetadata.getMeta("portname")));
        return map;
    }
}