/**
* Generate time : 2021-03-11 13:38:36
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhc51
* 
*/
public class Tduhc51 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String datatime = " ";		
    private String mn = " ";		
    private String siteid = "NULL";		
    private String sitename = "NULL";		
    private String factorid = " ";		
    private String factorname = "NULL";		
    private String qn = "NULL";		
    private String st = "NULL";		
    private String cn = " ";		
    private String valuezs = "NULL";		
    private String alarmtype = "NULL";		
    private String alarmstatus = "NULL";		
    private String alarminfo = "NULL";		
    private String sendtime = "NULL";		
    private String sendstatus = "NULL";		
    private String filltime = "NULL";		
    private String fillstatus = "NULL";		
    private String fillman = "NULL";		
    private String fillid = "NULL";		
    private String portname = "NULL";		
    private String portid = "NULL";		
    private String hlimit = "NULL";		
    private String lowlimit = "NULL";		
    private String nkhlimit = "NULL";		
    private String nklowlimit = "NULL";		
    private String controlLine = "";		
    private String standardValue = "";		
    private String valueReal = "NULL";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("datatime");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mn");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("siteid");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sitename");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("factorid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("factorname");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("qn");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("st");
        eiColumn.setFieldLength(5);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("cn");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("valuezs");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("alarmtype");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("alarmstatus");
        eiColumn.setFieldLength(1);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("alarminfo");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sendtime");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sendstatus");
        eiColumn.setFieldLength(1);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("filltime");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fillstatus");
        eiColumn.setFieldLength(1);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fillman");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fillid");
        eiColumn.setFieldLength(500);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("portname");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("portid");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("hlimit");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("lowlimit");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("nkhlimit");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("nklowlimit");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("controlLine");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("standardValue");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("valueReal");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public Tduhc51() {
        initMetaData();
    }

    /**
     * get the datatime 
     * @return the datatime
     */
    public String getDatatime() {
        return this.datatime;
    }

    /**
     * set the datatime 
     */
    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }

    /**
     * get the mn 
     * @return the mn
     */
    public String getMn() {
        return this.mn;
    }

    /**
     * set the mn 
     */
    public void setMn(String mn) {
        this.mn = mn;
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
     * get the qn 
     * @return the qn
     */
    public String getQn() {
        return this.qn;
    }

    /**
     * set the qn 
     */
    public void setQn(String qn) {
        this.qn = qn;
    }

    /**
     * get the st 
     * @return the st
     */
    public String getSt() {
        return this.st;
    }

    /**
     * set the st 
     */
    public void setSt(String st) {
        this.st = st;
    }

    /**
     * get the cn 
     * @return the cn
     */
    public String getCn() {
        return this.cn;
    }

    /**
     * set the cn 
     */
    public void setCn(String cn) {
        this.cn = cn;
    }

    /**
     * get the valuezs 
     * @return the valuezs
     */
    public String getValuezs() {
        return this.valuezs;
    }

    /**
     * set the valuezs 
     */
    public void setValuezs(String valuezs) {
        this.valuezs = valuezs;
    }

    /**
     * get the alarmtype 
     * @return the alarmtype
     */
    public String getAlarmtype() {
        return this.alarmtype;
    }

    /**
     * set the alarmtype 
     */
    public void setAlarmtype(String alarmtype) {
        this.alarmtype = alarmtype;
    }

    /**
     * get the alarmstatus 
     * @return the alarmstatus
     */
    public String getAlarmstatus() {
        return this.alarmstatus;
    }

    /**
     * set the alarmstatus 
     */
    public void setAlarmstatus(String alarmstatus) {
        this.alarmstatus = alarmstatus;
    }

    /**
     * get the alarminfo 
     * @return the alarminfo
     */
    public String getAlarminfo() {
        return this.alarminfo;
    }

    /**
     * set the alarminfo 
     */
    public void setAlarminfo(String alarminfo) {
        this.alarminfo = alarminfo;
    }

    /**
     * get the sendtime 
     * @return the sendtime
     */
    public String getSendtime() {
        return this.sendtime;
    }

    /**
     * set the sendtime 
     */
    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

    /**
     * get the sendstatus 
     * @return the sendstatus
     */
    public String getSendstatus() {
        return this.sendstatus;
    }

    /**
     * set the sendstatus 
     */
    public void setSendstatus(String sendstatus) {
        this.sendstatus = sendstatus;
    }

    /**
     * get the filltime 
     * @return the filltime
     */
    public String getFilltime() {
        return this.filltime;
    }

    /**
     * set the filltime 
     */
    public void setFilltime(String filltime) {
        this.filltime = filltime;
    }

    /**
     * get the fillstatus 
     * @return the fillstatus
     */
    public String getFillstatus() {
        return this.fillstatus;
    }

    /**
     * set the fillstatus 
     */
    public void setFillstatus(String fillstatus) {
        this.fillstatus = fillstatus;
    }

    /**
     * get the fillman 
     * @return the fillman
     */
    public String getFillman() {
        return this.fillman;
    }

    /**
     * set the fillman 
     */
    public void setFillman(String fillman) {
        this.fillman = fillman;
    }

    /**
     * get the fillid 
     * @return the fillid
     */
    public String getFillid() {
        return this.fillid;
    }

    /**
     * set the fillid 
     */
    public void setFillid(String fillid) {
        this.fillid = fillid;
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
     * get the hlimit 
     * @return the hlimit
     */
    public String getHlimit() {
        return this.hlimit;
    }

    /**
     * set the hlimit 
     */
    public void setHlimit(String hlimit) {
        this.hlimit = hlimit;
    }

    /**
     * get the lowlimit 
     * @return the lowlimit
     */
    public String getLowlimit() {
        return this.lowlimit;
    }

    /**
     * set the lowlimit 
     */
    public void setLowlimit(String lowlimit) {
        this.lowlimit = lowlimit;
    }

    /**
     * get the nkhlimit 
     * @return the nkhlimit
     */
    public String getNkhlimit() {
        return this.nkhlimit;
    }

    /**
     * set the nkhlimit 
     */
    public void setNkhlimit(String nkhlimit) {
        this.nkhlimit = nkhlimit;
    }

    /**
     * get the nklowlimit 
     * @return the nklowlimit
     */
    public String getNklowlimit() {
        return this.nklowlimit;
    }

    /**
     * set the nklowlimit 
     */
    public void setNklowlimit(String nklowlimit) {
        this.nklowlimit = nklowlimit;
    }

    /**
     * get the controlLine 
     * @return the controlLine
     */
    public String getControlLine() {
        return this.controlLine;
    }

    /**
     * set the controlLine 
     */
    public void setControlLine(String controlLine) {
        this.controlLine = controlLine;
    }

    /**
     * get the standardValue 
     * @return the standardValue
     */
    public String getStandardValue() {
        return this.standardValue;
    }

    /**
     * set the standardValue 
     */
    public void setStandardValue(String standardValue) {
        this.standardValue = standardValue;
    }

    /**
     * get the valueReal 
     * @return the valueReal
     */
    public String getValueReal() {
        return this.valueReal;
    }

    /**
     * set the valueReal 
     */
    public void setValueReal(String valueReal) {
        this.valueReal = valueReal;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setDatatime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("datatime")), datatime));
        setMn(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mn")), mn));
        setSiteid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("siteid")), siteid));
        setSitename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sitename")), sitename));
        setFactorid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("factorid")), factorid));
        setFactorname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("factorname")), factorname));
        setQn(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("qn")), qn));
        setSt(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("st")), st));
        setCn(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("cn")), cn));
        setValuezs(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("valuezs")), valuezs));
        setAlarmtype(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("alarmtype")), alarmtype));
        setAlarmstatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("alarmstatus")), alarmstatus));
        setAlarminfo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("alarminfo")), alarminfo));
        setSendtime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sendtime")), sendtime));
        setSendstatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sendstatus")), sendstatus));
        setFilltime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("filltime")), filltime));
        setFillstatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fillstatus")), fillstatus));
        setFillman(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fillman")), fillman));
        setFillid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fillid")), fillid));
        setPortname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("portname")), portname));
        setPortid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("portid")), portid));
        setHlimit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("hlimit")), hlimit));
        setLowlimit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("lowlimit")), lowlimit));
        setNkhlimit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("nkhlimit")), nkhlimit));
        setNklowlimit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("nklowlimit")), nklowlimit));
        setControlLine(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("controlLine")), controlLine));
        setStandardValue(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("standardValue")), standardValue));
        setValueReal(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("valueReal")), valueReal));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("datatime", StringUtils.toString(datatime, eiMetadata.getMeta("datatime")));
        map.put("mn", StringUtils.toString(mn, eiMetadata.getMeta("mn")));
        map.put("siteid", StringUtils.toString(siteid, eiMetadata.getMeta("siteid")));
        map.put("sitename", StringUtils.toString(sitename, eiMetadata.getMeta("sitename")));
        map.put("factorid", StringUtils.toString(factorid, eiMetadata.getMeta("factorid")));
        map.put("factorname", StringUtils.toString(factorname, eiMetadata.getMeta("factorname")));
        map.put("qn", StringUtils.toString(qn, eiMetadata.getMeta("qn")));
        map.put("st", StringUtils.toString(st, eiMetadata.getMeta("st")));
        map.put("cn", StringUtils.toString(cn, eiMetadata.getMeta("cn")));
        map.put("valuezs", StringUtils.toString(valuezs, eiMetadata.getMeta("valuezs")));
        map.put("alarmtype", StringUtils.toString(alarmtype, eiMetadata.getMeta("alarmtype")));
        map.put("alarmstatus", StringUtils.toString(alarmstatus, eiMetadata.getMeta("alarmstatus")));
        map.put("alarminfo", StringUtils.toString(alarminfo, eiMetadata.getMeta("alarminfo")));
        map.put("sendtime", StringUtils.toString(sendtime, eiMetadata.getMeta("sendtime")));
        map.put("sendstatus", StringUtils.toString(sendstatus, eiMetadata.getMeta("sendstatus")));
        map.put("filltime", StringUtils.toString(filltime, eiMetadata.getMeta("filltime")));
        map.put("fillstatus", StringUtils.toString(fillstatus, eiMetadata.getMeta("fillstatus")));
        map.put("fillman", StringUtils.toString(fillman, eiMetadata.getMeta("fillman")));
        map.put("fillid", StringUtils.toString(fillid, eiMetadata.getMeta("fillid")));
        map.put("portname", StringUtils.toString(portname, eiMetadata.getMeta("portname")));
        map.put("portid", StringUtils.toString(portid, eiMetadata.getMeta("portid")));
        map.put("hlimit", StringUtils.toString(hlimit, eiMetadata.getMeta("hlimit")));
        map.put("lowlimit", StringUtils.toString(lowlimit, eiMetadata.getMeta("lowlimit")));
        map.put("nkhlimit", StringUtils.toString(nkhlimit, eiMetadata.getMeta("nkhlimit")));
        map.put("nklowlimit", StringUtils.toString(nklowlimit, eiMetadata.getMeta("nklowlimit")));
        map.put("controlLine", StringUtils.toString(controlLine, eiMetadata.getMeta("controlLine")));
        map.put("standardValue", StringUtils.toString(standardValue, eiMetadata.getMeta("standardValue")));
        map.put("valueReal", StringUtils.toString(valueReal, eiMetadata.getMeta("valueReal")));
        return map;
    }
}