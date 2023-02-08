/**
* Generate time : 2021-05-07 15:53:28
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhb25
* 
*/
public class Tduhb25 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String alarmPersonIdentity = " ";		
    private String alarmPersonLoginname = " ";		
    private String alarmPersonName = " ";		
    private String departmentid = " ";		
    private String departmentName = " ";		
    private String phone = " ";		
    private String monitorType = " ";		
    private String alarmStartTime = " ";		
    private String alarmEndTime = " ";		
    private String status = " ";		
    private String creator = " ";		
    private String createdtime = " ";		
    private String modifier = " ";		
    private String updatedtime = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("alarmPersonIdentity");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("alarmPersonLoginname");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("alarmPersonName");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("departmentid");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("departmentName");
        eiColumn.setFieldLength(200);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("phone");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("monitorType");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("alarmStartTime");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("alarmEndTime");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("status");
        eiColumn.setFieldLength(5);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creator");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createdtime");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("modifier");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updatedtime");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public Tduhb25() {
        initMetaData();
    }

    /**
     * get the alarmPersonIdentity 
     * @return the alarmPersonIdentity
     */
    public String getAlarmPersonIdentity() {
        return this.alarmPersonIdentity;
    }

    /**
     * set the alarmPersonIdentity 
     */
    public void setAlarmPersonIdentity(String alarmPersonIdentity) {
        this.alarmPersonIdentity = alarmPersonIdentity;
    }

    /**
     * get the alarmPersonLoginname 
     * @return the alarmPersonLoginname
     */
    public String getAlarmPersonLoginname() {
        return this.alarmPersonLoginname;
    }

    /**
     * set the alarmPersonLoginname 
     */
    public void setAlarmPersonLoginname(String alarmPersonLoginname) {
        this.alarmPersonLoginname = alarmPersonLoginname;
    }

    /**
     * get the alarmPersonName 
     * @return the alarmPersonName
     */
    public String getAlarmPersonName() {
        return this.alarmPersonName;
    }

    /**
     * set the alarmPersonName 
     */
    public void setAlarmPersonName(String alarmPersonName) {
        this.alarmPersonName = alarmPersonName;
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
     * get the departmentName 
     * @return the departmentName
     */
    public String getDepartmentName() {
        return this.departmentName;
    }

    /**
     * set the departmentName 
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * get the phone 
     * @return the phone
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * set the phone 
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * get the monitorType 
     * @return the monitorType
     */
    public String getMonitorType() {
        return this.monitorType;
    }

    /**
     * set the monitorType 
     */
    public void setMonitorType(String monitorType) {
        this.monitorType = monitorType;
    }

    /**
     * get the alarmStartTime 
     * @return the alarmStartTime
     */
    public String getAlarmStartTime() {
        return this.alarmStartTime;
    }

    /**
     * set the alarmStartTime 
     */
    public void setAlarmStartTime(String alarmStartTime) {
        this.alarmStartTime = alarmStartTime;
    }

    /**
     * get the alarmEndTime 
     * @return the alarmEndTime
     */
    public String getAlarmEndTime() {
        return this.alarmEndTime;
    }

    /**
     * set the alarmEndTime 
     */
    public void setAlarmEndTime(String alarmEndTime) {
        this.alarmEndTime = alarmEndTime;
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
     * get the createdtime 
     * @return the createdtime
     */
    public String getCreatedtime() {
        return this.createdtime;
    }

    /**
     * set the createdtime 
     */
    public void setCreatedtime(String createdtime) {
        this.createdtime = createdtime;
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
     * get the updatedtime 
     * @return the updatedtime
     */
    public String getUpdatedtime() {
        return this.updatedtime;
    }

    /**
     * set the updatedtime 
     */
    public void setUpdatedtime(String updatedtime) {
        this.updatedtime = updatedtime;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setAlarmPersonIdentity(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("alarmPersonIdentity")), alarmPersonIdentity));
        setAlarmPersonLoginname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("alarmPersonLoginname")), alarmPersonLoginname));
        setAlarmPersonName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("alarmPersonName")), alarmPersonName));
        setDepartmentid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentid")), departmentid));
        setDepartmentName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentName")), departmentName));
        setPhone(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("phone")), phone));
        setMonitorType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("monitorType")), monitorType));
        setAlarmStartTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("alarmStartTime")), alarmStartTime));
        setAlarmEndTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("alarmEndTime")), alarmEndTime));
        setStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("status")), status));
        setCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creator")), creator));
        setCreatedtime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createdtime")), createdtime));
        setModifier(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("modifier")), modifier));
        setUpdatedtime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updatedtime")), updatedtime));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("alarmPersonIdentity", StringUtils.toString(alarmPersonIdentity, eiMetadata.getMeta("alarmPersonIdentity")));
        map.put("alarmPersonLoginname", StringUtils.toString(alarmPersonLoginname, eiMetadata.getMeta("alarmPersonLoginname")));
        map.put("alarmPersonName", StringUtils.toString(alarmPersonName, eiMetadata.getMeta("alarmPersonName")));
        map.put("departmentid", StringUtils.toString(departmentid, eiMetadata.getMeta("departmentid")));
        map.put("departmentName", StringUtils.toString(departmentName, eiMetadata.getMeta("departmentName")));
        map.put("phone", StringUtils.toString(phone, eiMetadata.getMeta("phone")));
        map.put("monitorType", StringUtils.toString(monitorType, eiMetadata.getMeta("monitorType")));
        map.put("alarmStartTime", StringUtils.toString(alarmStartTime, eiMetadata.getMeta("alarmStartTime")));
        map.put("alarmEndTime", StringUtils.toString(alarmEndTime, eiMetadata.getMeta("alarmEndTime")));
        map.put("status", StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("creator", StringUtils.toString(creator, eiMetadata.getMeta("creator")));
        map.put("createdtime", StringUtils.toString(createdtime, eiMetadata.getMeta("createdtime")));
        map.put("modifier", StringUtils.toString(modifier, eiMetadata.getMeta("modifier")));
        map.put("updatedtime", StringUtils.toString(updatedtime, eiMetadata.getMeta("updatedtime")));
        return map;
    }
}