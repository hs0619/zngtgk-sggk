/**
* Generate time : 2021-03-04 11:00:30
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhb18
* 
*/
public class Tduhb18 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String envdeviceid = " ";		
    private String deviceid = " ";		
    private String devicename = " ";		
    private String sourcename = " ";		
    private String factorname = " ";		
    private String outtype = " ";		
    private String processid = " ";		
    private String processname = " ";		
    private String processtype = " ";		
    private String canuse = " ";		
    private String processinfo = " ";		
    private String portid = " ";		
    private String portname = " ";		
    private String isright = " ";		
    private String porttype = " ";		
    private String other = " ";		
    private String devicetype = " ";		
    private String outto = " ";		
    private String portouttype = " ";		
    private String outrule = " ";		
    private String status = " ";		
    private String creator = " ";		
    private String createdTime = " ";		
    private String modifier = " ";		
    private String updatedTime = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("envdeviceid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deviceid");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("devicename");
        eiColumn.setFieldLength(255);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sourcename");
        eiColumn.setFieldLength(255);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("factorname");
        eiColumn.setFieldLength(255);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outtype");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("processid");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("processname");
        eiColumn.setFieldLength(255);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("processtype");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("canuse");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("processinfo");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("portid");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("portname");
        eiColumn.setFieldLength(255);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isright");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("porttype");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("other");
        eiColumn.setFieldLength(500);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("devicetype");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outto");
        eiColumn.setFieldLength(255);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("portouttype");
        eiColumn.setFieldLength(255);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outrule");
        eiColumn.setFieldLength(255);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("status");
        eiColumn.setFieldLength(2);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creator");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createdTime");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("modifier");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updatedTime");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public Tduhb18() {
        initMetaData();
    }

    /**
     * get the envdeviceid 
     * @return the envdeviceid
     */
    public String getEnvdeviceid() {
        return this.envdeviceid;
    }

    /**
     * set the envdeviceid 
     */
    public void setEnvdeviceid(String envdeviceid) {
        this.envdeviceid = envdeviceid;
    }

    /**
     * get the deviceid 
     * @return the deviceid
     */
    public String getDeviceid() {
        return this.deviceid;
    }

    /**
     * set the deviceid 
     */
    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    /**
     * get the devicename 
     * @return the devicename
     */
    public String getDevicename() {
        return this.devicename;
    }

    /**
     * set the devicename 
     */
    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    /**
     * get the sourcename 
     * @return the sourcename
     */
    public String getSourcename() {
        return this.sourcename;
    }

    /**
     * set the sourcename 
     */
    public void setSourcename(String sourcename) {
        this.sourcename = sourcename;
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
     * get the processid 
     * @return the processid
     */
    public String getProcessid() {
        return this.processid;
    }

    /**
     * set the processid 
     */
    public void setProcessid(String processid) {
        this.processid = processid;
    }

    /**
     * get the processname 
     * @return the processname
     */
    public String getProcessname() {
        return this.processname;
    }

    /**
     * set the processname 
     */
    public void setProcessname(String processname) {
        this.processname = processname;
    }

    /**
     * get the processtype 
     * @return the processtype
     */
    public String getProcesstype() {
        return this.processtype;
    }

    /**
     * set the processtype 
     */
    public void setProcesstype(String processtype) {
        this.processtype = processtype;
    }

    /**
     * get the canuse 
     * @return the canuse
     */
    public String getCanuse() {
        return this.canuse;
    }

    /**
     * set the canuse 
     */
    public void setCanuse(String canuse) {
        this.canuse = canuse;
    }

    /**
     * get the processinfo 
     * @return the processinfo
     */
    public String getProcessinfo() {
        return this.processinfo;
    }

    /**
     * set the processinfo 
     */
    public void setProcessinfo(String processinfo) {
        this.processinfo = processinfo;
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
     * get the isright 
     * @return the isright
     */
    public String getIsright() {
        return this.isright;
    }

    /**
     * set the isright 
     */
    public void setIsright(String isright) {
        this.isright = isright;
    }

    /**
     * get the porttype 
     * @return the porttype
     */
    public String getPorttype() {
        return this.porttype;
    }

    /**
     * set the porttype 
     */
    public void setPorttype(String porttype) {
        this.porttype = porttype;
    }

    /**
     * get the other 
     * @return the other
     */
    public String getOther() {
        return this.other;
    }

    /**
     * set the other 
     */
    public void setOther(String other) {
        this.other = other;
    }

    /**
     * get the devicetype 
     * @return the devicetype
     */
    public String getDevicetype() {
        return this.devicetype;
    }

    /**
     * set the devicetype 
     */
    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
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
     * get the portouttype 
     * @return the portouttype
     */
    public String getPortouttype() {
        return this.portouttype;
    }

    /**
     * set the portouttype 
     */
    public void setPortouttype(String portouttype) {
        this.portouttype = portouttype;
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
     * get the creator 
     * @return the creator
     */
    public String getCreator() {
        return this.creator;
    }

    /**
     * set the creator 
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * get the createdTime 
     * @return the createdTime
     */
    public String getCreatedTime() {
        return this.createdTime;
    }

    /**
     * set the createdTime 
     */
    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * get the modifier 
     * @return the modifier
     */
    public String getModifier() {
        return this.modifier;
    }

    /**
     * set the modifier 
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    /**
     * get the updatedTime 
     * @return the updatedTime
     */
    public String getUpdatedTime() {
        return this.updatedTime;
    }

    /**
     * set the updatedTime 
     */
    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setEnvdeviceid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("envdeviceid")), envdeviceid));
        setDeviceid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deviceid")), deviceid));
        setDevicename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("devicename")), devicename));
        setSourcename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sourcename")), sourcename));
        setFactorname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("factorname")), factorname));
        setOuttype(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outtype")), outtype));
        setProcessid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("processid")), processid));
        setProcessname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("processname")), processname));
        setProcesstype(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("processtype")), processtype));
        setCanuse(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("canuse")), canuse));
        setProcessinfo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("processinfo")), processinfo));
        setPortid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("portid")), portid));
        setPortname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("portname")), portname));
        setIsright(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isright")), isright));
        setPorttype(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("porttype")), porttype));
        setOther(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("other")), other));
        setDevicetype(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("devicetype")), devicetype));
        setOutto(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outto")), outto));
        setPortouttype(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("portouttype")), portouttype));
        setOutrule(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outrule")), outrule));
        setStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("status")), status));
        setCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creator")), creator));
        setCreatedTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createdTime")), createdTime));
        setModifier(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("modifier")), modifier));
        setUpdatedTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updatedTime")), updatedTime));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("envdeviceid", StringUtils.toString(envdeviceid, eiMetadata.getMeta("envdeviceid")));
        map.put("deviceid", StringUtils.toString(deviceid, eiMetadata.getMeta("deviceid")));
        map.put("devicename", StringUtils.toString(devicename, eiMetadata.getMeta("devicename")));
        map.put("sourcename", StringUtils.toString(sourcename, eiMetadata.getMeta("sourcename")));
        map.put("factorname", StringUtils.toString(factorname, eiMetadata.getMeta("factorname")));
        map.put("outtype", StringUtils.toString(outtype, eiMetadata.getMeta("outtype")));
        map.put("processid", StringUtils.toString(processid, eiMetadata.getMeta("processid")));
        map.put("processname", StringUtils.toString(processname, eiMetadata.getMeta("processname")));
        map.put("processtype", StringUtils.toString(processtype, eiMetadata.getMeta("processtype")));
        map.put("canuse", StringUtils.toString(canuse, eiMetadata.getMeta("canuse")));
        map.put("processinfo", StringUtils.toString(processinfo, eiMetadata.getMeta("processinfo")));
        map.put("portid", StringUtils.toString(portid, eiMetadata.getMeta("portid")));
        map.put("portname", StringUtils.toString(portname, eiMetadata.getMeta("portname")));
        map.put("isright", StringUtils.toString(isright, eiMetadata.getMeta("isright")));
        map.put("porttype", StringUtils.toString(porttype, eiMetadata.getMeta("porttype")));
        map.put("other", StringUtils.toString(other, eiMetadata.getMeta("other")));
        map.put("devicetype", StringUtils.toString(devicetype, eiMetadata.getMeta("devicetype")));
        map.put("outto", StringUtils.toString(outto, eiMetadata.getMeta("outto")));
        map.put("portouttype", StringUtils.toString(portouttype, eiMetadata.getMeta("portouttype")));
        map.put("outrule", StringUtils.toString(outrule, eiMetadata.getMeta("outrule")));
        map.put("status", StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("creator", StringUtils.toString(creator, eiMetadata.getMeta("creator")));
        map.put("createdTime", StringUtils.toString(createdTime, eiMetadata.getMeta("createdTime")));
        map.put("modifier", StringUtils.toString(modifier, eiMetadata.getMeta("modifier")));
        map.put("updatedTime", StringUtils.toString(updatedTime, eiMetadata.getMeta("updatedTime")));
        return map;
    }
}