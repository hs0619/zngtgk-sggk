/**
* Generate time : 2021-03-18 11:17:23
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhc5102
* 
*/
public class Tduhc5102 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String siteid = " ";		
    private String sitename = " ";		
    private String portid = " ";		
    private String isartificial = " ";		
    private String isonline = " ";		
    private String classifyid = " ";		
    private String monitorid = " ";		
    private String mnid = " ";		
    private String dischargeportname = " ";		
    private String isformal = " ";		
    private String procid = " ";		
    private String latitude = " ";		
    private String longitude = " ";		
    private String ismap = " ";		
    private String citypoint = " ";		
    private String companypoint = " ";		
    private String countrypoint = " ";		
    private String monitorname = " ";		
    private String procedurename = " ";		
    private String departmentid = " ";		
    private String departmentname = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("siteid");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sitename");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("portid");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isartificial");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isonline");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("classifyid");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("monitorid");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mnid");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dischargeportname");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isformal");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("procid");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("latitude");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("longitude");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("ismap");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("citypoint");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("companypoint");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("countrypoint");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("monitorname");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("procedurename");
        eiColumn.setFieldLength(200);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("departmentid");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("departmentname");
        eiColumn.setFieldLength(200);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public Tduhc5102() {
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
     * get the sitename 
     * @return the sitename
     */
    public String getSitename() {
        return this.sitename;
    }

    /**
     * set the sitename 
     */
    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    /**
     * get the portid 
     * @return the portid
     */
    public String getPortid() {
        return this.portid;
    }

    /**
     * set the portid 
     */
    public void setPortid(String portid) {
        this.portid = portid;
    }

    /**
     * get the isartificial 
     * @return the isartificial
     */
    public String getIsartificial() {
        return this.isartificial;
    }

    /**
     * set the isartificial 
     */
    public void setIsartificial(String isartificial) {
        this.isartificial = isartificial;
    }

    /**
     * get the isonline 
     * @return the isonline
     */
    public String getIsonline() {
        return this.isonline;
    }

    /**
     * set the isonline 
     */
    public void setIsonline(String isonline) {
        this.isonline = isonline;
    }

    /**
     * get the classifyid 
     * @return the classifyid
     */
    public String getClassifyid() {
        return this.classifyid;
    }

    /**
     * set the classifyid 
     */
    public void setClassifyid(String classifyid) {
        this.classifyid = classifyid;
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
     * get the mnid 
     * @return the mnid
     */
    public String getMnid() {
        return this.mnid;
    }

    /**
     * set the mnid 
     */
    public void setMnid(String mnid) {
        this.mnid = mnid;
    }

    /**
     * get the dischargeportname 
     * @return the dischargeportname
     */
    public String getDischargeportname() {
        return this.dischargeportname;
    }

    /**
     * set the dischargeportname 
     */
    public void setDischargeportname(String dischargeportname) {
        this.dischargeportname = dischargeportname;
    }

    /**
     * get the isformal 
     * @return the isformal
     */
    public String getIsformal() {
        return this.isformal;
    }

    /**
     * set the isformal 
     */
    public void setIsformal(String isformal) {
        this.isformal = isformal;
    }

    /**
     * get the procid 
     * @return the procid
     */
    public String getProcid() {
        return this.procid;
    }

    /**
     * set the procid 
     */
    public void setProcid(String procid) {
        this.procid = procid;
    }

    /**
     * get the latitude 
     * @return the latitude
     */
    public String getLatitude() {
        return this.latitude;
    }

    /**
     * set the latitude 
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * get the longitude 
     * @return the longitude
     */
    public String getLongitude() {
        return this.longitude;
    }

    /**
     * set the longitude 
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * get the ismap 
     * @return the ismap
     */
    public String getIsmap() {
        return this.ismap;
    }

    /**
     * set the ismap 
     */
    public void setIsmap(String ismap) {
        this.ismap = ismap;
    }

    /**
     * get the citypoint 
     * @return the citypoint
     */
    public String getCitypoint() {
        return this.citypoint;
    }

    /**
     * set the citypoint 
     */
    public void setCitypoint(String citypoint) {
        this.citypoint = citypoint;
    }

    /**
     * get the companypoint 
     * @return the companypoint
     */
    public String getCompanypoint() {
        return this.companypoint;
    }

    /**
     * set the companypoint 
     */
    public void setCompanypoint(String companypoint) {
        this.companypoint = companypoint;
    }

    /**
     * get the countrypoint 
     * @return the countrypoint
     */
    public String getCountrypoint() {
        return this.countrypoint;
    }

    /**
     * set the countrypoint 
     */
    public void setCountrypoint(String countrypoint) {
        this.countrypoint = countrypoint;
    }

    /**
     * get the monitorname 
     * @return the monitorname
     */
    public String getMonitorname() {
        return this.monitorname;
    }

    /**
     * set the monitorname 
     */
    public void setMonitorname(String monitorname) {
        this.monitorname = monitorname;
    }

    /**
     * get the procedurename 
     * @return the procedurename
     */
    public String getProcedurename() {
        return this.procedurename;
    }

    /**
     * set the procedurename 
     */
    public void setProcedurename(String procedurename) {
        this.procedurename = procedurename;
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
     * get the departmentname 
     * @return the departmentname
     */
    public String getDepartmentname() {
        return this.departmentname;
    }

    /**
     * set the departmentname 
     */
    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setSiteid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("siteid")), siteid));
        setSitename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sitename")), sitename));
        setPortid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("portid")), portid));
        setIsartificial(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isartificial")), isartificial));
        setIsonline(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isonline")), isonline));
        setClassifyid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("classifyid")), classifyid));
        setMonitorid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("monitorid")), monitorid));
        setMnid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mnid")), mnid));
        setDischargeportname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dischargeportname")), dischargeportname));
        setIsformal(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isformal")), isformal));
        setProcid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("procid")), procid));
        setLatitude(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("latitude")), latitude));
        setLongitude(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("longitude")), longitude));
        setIsmap(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("ismap")), ismap));
        setCitypoint(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("citypoint")), citypoint));
        setCompanypoint(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("companypoint")), companypoint));
        setCountrypoint(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("countrypoint")), countrypoint));
        setMonitorname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("monitorname")), monitorname));
        setProcedurename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("procedurename")), procedurename));
        setDepartmentid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentid")), departmentid));
        setDepartmentname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentname")), departmentname));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("siteid", StringUtils.toString(siteid, eiMetadata.getMeta("siteid")));
        map.put("sitename", StringUtils.toString(sitename, eiMetadata.getMeta("sitename")));
        map.put("portid", StringUtils.toString(portid, eiMetadata.getMeta("portid")));
        map.put("isartificial", StringUtils.toString(isartificial, eiMetadata.getMeta("isartificial")));
        map.put("isonline", StringUtils.toString(isonline, eiMetadata.getMeta("isonline")));
        map.put("classifyid", StringUtils.toString(classifyid, eiMetadata.getMeta("classifyid")));
        map.put("monitorid", StringUtils.toString(monitorid, eiMetadata.getMeta("monitorid")));
        map.put("mnid", StringUtils.toString(mnid, eiMetadata.getMeta("mnid")));
        map.put("dischargeportname", StringUtils.toString(dischargeportname, eiMetadata.getMeta("dischargeportname")));
        map.put("isformal", StringUtils.toString(isformal, eiMetadata.getMeta("isformal")));
        map.put("procid", StringUtils.toString(procid, eiMetadata.getMeta("procid")));
        map.put("latitude", StringUtils.toString(latitude, eiMetadata.getMeta("latitude")));
        map.put("longitude", StringUtils.toString(longitude, eiMetadata.getMeta("longitude")));
        map.put("ismap", StringUtils.toString(ismap, eiMetadata.getMeta("ismap")));
        map.put("citypoint", StringUtils.toString(citypoint, eiMetadata.getMeta("citypoint")));
        map.put("companypoint", StringUtils.toString(companypoint, eiMetadata.getMeta("companypoint")));
        map.put("countrypoint", StringUtils.toString(countrypoint, eiMetadata.getMeta("countrypoint")));
        map.put("monitorname", StringUtils.toString(monitorname, eiMetadata.getMeta("monitorname")));
        map.put("procedurename", StringUtils.toString(procedurename, eiMetadata.getMeta("procedurename")));
        map.put("departmentid", StringUtils.toString(departmentid, eiMetadata.getMeta("departmentid")));
        map.put("departmentname", StringUtils.toString(departmentname, eiMetadata.getMeta("departmentname")));
        return map;
    }
}