/**
* Generate time : 2021-04-07 13:30:19
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhf04
* 
*/
public class Tduhf04 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String dischargeportid = " ";		
    private String dischargeportname = " ";		
    private String isformal = " ";		
    private String procid = " ";		
    private String monitorid = " ";		
    private String signform = " ";		
    private String dischargemode = " ";		
    private String longitude = " ";		
    private String latitude = " ";		
    private String position = " ";		
    private String executionstandard = " ";		
    private String setright = " ";		
    private String importantport = " ";		
    private String ismap = " ";		
    private String description = " ";		
    private String outto = " ";		
    private String outtype = " ";		
    private String outrule = " ";		
    private String countrypoint = " ";		
    private String citypoint = " ";		
    private String companypoint = " ";		
    private String cleanpoint = " ";		
    private String status = " ";		
    private String dischargeportcode = " ";		
    private String exhaustheight = " ";		
    private String exhaustinside = " ";		
    private String procedureName = " ";		
    private String departmentid = " ";		
    private String departmentname = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("dischargeportid");
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

        eiColumn = new EiColumn("monitorid");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("signform");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dischargemode");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("longitude");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("latitude");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("position");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("executionstandard");
        eiColumn.setFieldLength(255);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("setright");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("importantport");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("ismap");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("description");
        eiColumn.setFieldLength(255);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outto");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outtype");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outrule");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("countrypoint");
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

        eiColumn = new EiColumn("cleanpoint");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("status");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dischargeportcode");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("exhaustheight");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("exhaustinside");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("procedureName");
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
    public Tduhf04() {
        initMetaData();
    }

    /**
     * get the dischargeportid 
     * @return the dischargeportid
     */
    public String getDischargeportid() {
        return this.dischargeportid;
    }

    /**
     * set the dischargeportid 
     */
    public void setDischargeportid(String dischargeportid) {
        this.dischargeportid = dischargeportid;
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
     * get the signform 
     * @return the signform
     */
    public String getSignform() {
        return this.signform;
    }

    /**
     * set the signform 
     */
    public void setSignform(String signform) {
        this.signform = signform;
    }

    /**
     * get the dischargemode 
     * @return the dischargemode
     */
    public String getDischargemode() {
        return this.dischargemode;
    }

    /**
     * set the dischargemode 
     */
    public void setDischargemode(String dischargemode) {
        this.dischargemode = dischargemode;
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
     * get the position 
     * @return the position
     */
    public String getPosition() {
        return this.position;
    }

    /**
     * set the position 
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * get the executionstandard 
     * @return the executionstandard
     */
    public String getExecutionstandard() {
        return this.executionstandard;
    }

    /**
     * set the executionstandard 
     */
    public void setExecutionstandard(String executionstandard) {
        this.executionstandard = executionstandard;
    }

    /**
     * get the setright 
     * @return the setright
     */
    public String getSetright() {
        return this.setright;
    }

    /**
     * set the setright 
     */
    public void setSetright(String setright) {
        this.setright = setright;
    }

    /**
     * get the importantport 
     * @return the importantport
     */
    public String getImportantport() {
        return this.importantport;
    }

    /**
     * set the importantport 
     */
    public void setImportantport(String importantport) {
        this.importantport = importantport;
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
     * get the description 
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * set the description 
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * get the outto 
     * @return the outto
     */
    public String getOutto() {
        return this.outto;
    }

    /**
     * set the outto 
     */
    public void setOutto(String outto) {
        this.outto = outto;
    }

    /**
     * get the outtype 
     * @return the outtype
     */
    public String getOuttype() {
        return this.outtype;
    }

    /**
     * set the outtype 
     */
    public void setOuttype(String outtype) {
        this.outtype = outtype;
    }

    /**
     * get the outrule 
     * @return the outrule
     */
    public String getOutrule() {
        return this.outrule;
    }

    /**
     * set the outrule 
     */
    public void setOutrule(String outrule) {
        this.outrule = outrule;
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
     * get the cleanpoint 
     * @return the cleanpoint
     */
    public String getCleanpoint() {
        return this.cleanpoint;
    }

    /**
     * set the cleanpoint 
     */
    public void setCleanpoint(String cleanpoint) {
        this.cleanpoint = cleanpoint;
    }

    /**
     * get the status 
     * @return the status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * set the status 
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * get the dischargeportcode 
     * @return the dischargeportcode
     */
    public String getDischargeportcode() {
        return this.dischargeportcode;
    }

    /**
     * set the dischargeportcode 
     */
    public void setDischargeportcode(String dischargeportcode) {
        this.dischargeportcode = dischargeportcode;
    }

    /**
     * get the exhaustheight 
     * @return the exhaustheight
     */
    public String getExhaustheight() {
        return this.exhaustheight;
    }

    /**
     * set the exhaustheight 
     */
    public void setExhaustheight(String exhaustheight) {
        this.exhaustheight = exhaustheight;
    }

    /**
     * get the exhaustinside 
     * @return the exhaustinside
     */
    public String getExhaustinside() {
        return this.exhaustinside;
    }

    /**
     * set the exhaustinside 
     */
    public void setExhaustinside(String exhaustinside) {
        this.exhaustinside = exhaustinside;
    }

    /**
     * get the procedureName 
     * @return the procedureName
     */
    public String getProcedureName() {
        return this.procedureName;
    }

    /**
     * set the procedureName 
     */
    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
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

        setDischargeportid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dischargeportid")), dischargeportid));
        setDischargeportname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dischargeportname")), dischargeportname));
        setIsformal(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isformal")), isformal));
        setProcid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("procid")), procid));
        setMonitorid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("monitorid")), monitorid));
        setSignform(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("signform")), signform));
        setDischargemode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dischargemode")), dischargemode));
        setLongitude(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("longitude")), longitude));
        setLatitude(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("latitude")), latitude));
        setPosition(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("position")), position));
        setExecutionstandard(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("executionstandard")), executionstandard));
        setSetright(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("setright")), setright));
        setImportantport(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("importantport")), importantport));
        setIsmap(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("ismap")), ismap));
        setDescription(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("description")), description));
        setOutto(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outto")), outto));
        setOuttype(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outtype")), outtype));
        setOutrule(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outrule")), outrule));
        setCountrypoint(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("countrypoint")), countrypoint));
        setCitypoint(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("citypoint")), citypoint));
        setCompanypoint(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("companypoint")), companypoint));
        setCleanpoint(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("cleanpoint")), cleanpoint));
        setStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("status")), status));
        setDischargeportcode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dischargeportcode")), dischargeportcode));
        setExhaustheight(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("exhaustheight")), exhaustheight));
        setExhaustinside(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("exhaustinside")), exhaustinside));
        setProcedureName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("procedureName")), procedureName));
        setDepartmentid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentid")), departmentid));
        setDepartmentname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentname")), departmentname));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("dischargeportid", StringUtils.toString(dischargeportid, eiMetadata.getMeta("dischargeportid")));
        map.put("dischargeportname", StringUtils.toString(dischargeportname, eiMetadata.getMeta("dischargeportname")));
        map.put("isformal", StringUtils.toString(isformal, eiMetadata.getMeta("isformal")));
        map.put("procid", StringUtils.toString(procid, eiMetadata.getMeta("procid")));
        map.put("monitorid", StringUtils.toString(monitorid, eiMetadata.getMeta("monitorid")));
        map.put("signform", StringUtils.toString(signform, eiMetadata.getMeta("signform")));
        map.put("dischargemode", StringUtils.toString(dischargemode, eiMetadata.getMeta("dischargemode")));
        map.put("longitude", StringUtils.toString(longitude, eiMetadata.getMeta("longitude")));
        map.put("latitude", StringUtils.toString(latitude, eiMetadata.getMeta("latitude")));
        map.put("position", StringUtils.toString(position, eiMetadata.getMeta("position")));
        map.put("executionstandard", StringUtils.toString(executionstandard, eiMetadata.getMeta("executionstandard")));
        map.put("setright", StringUtils.toString(setright, eiMetadata.getMeta("setright")));
        map.put("importantport", StringUtils.toString(importantport, eiMetadata.getMeta("importantport")));
        map.put("ismap", StringUtils.toString(ismap, eiMetadata.getMeta("ismap")));
        map.put("description", StringUtils.toString(description, eiMetadata.getMeta("description")));
        map.put("outto", StringUtils.toString(outto, eiMetadata.getMeta("outto")));
        map.put("outtype", StringUtils.toString(outtype, eiMetadata.getMeta("outtype")));
        map.put("outrule", StringUtils.toString(outrule, eiMetadata.getMeta("outrule")));
        map.put("countrypoint", StringUtils.toString(countrypoint, eiMetadata.getMeta("countrypoint")));
        map.put("citypoint", StringUtils.toString(citypoint, eiMetadata.getMeta("citypoint")));
        map.put("companypoint", StringUtils.toString(companypoint, eiMetadata.getMeta("companypoint")));
        map.put("cleanpoint", StringUtils.toString(cleanpoint, eiMetadata.getMeta("cleanpoint")));
        map.put("status", StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("dischargeportcode", StringUtils.toString(dischargeportcode, eiMetadata.getMeta("dischargeportcode")));
        map.put("exhaustheight", StringUtils.toString(exhaustheight, eiMetadata.getMeta("exhaustheight")));
        map.put("exhaustinside", StringUtils.toString(exhaustinside, eiMetadata.getMeta("exhaustinside")));
        map.put("procedureName", StringUtils.toString(procedureName, eiMetadata.getMeta("procedureName")));
        map.put("departmentid", StringUtils.toString(departmentid, eiMetadata.getMeta("departmentid")));
        map.put("departmentname", StringUtils.toString(departmentname, eiMetadata.getMeta("departmentname")));
        return map;
    }
}