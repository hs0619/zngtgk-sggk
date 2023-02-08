/**
* Generate time : 2021-03-30 17:34:09
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhb04
* 
*/
public class Tduhb04 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String facilityid = " ";		
    private String facilitycode = " ";		
    private String facilityname = " ";		
    private String pollutantname = " ";		
    private String facilitytype = " ";		
    private String origin = " ";		
    private String handmethod = " ";		
    private String numbers = " ";		
    private String handability = " ";		
    private String shandnum = " ";		
    private String runtime = " ";		
    private String handfactors = " ";		
    private String portid = " ";		
    private String portname = " ";		
    private String devicecode = " ";		
    private String devicemodel = " ";		
    private String motormodel = " ";		
    private String controllermodel = " ";		
    private String situation = " ";		
    private String outstandard = " ";		
    private String facilitydepart = " ";		
    private String isexecute = " ";		
    private String remark = " ";		
    private String type = " ";		
    private String status = " ";		
    private String creator = " ";		
    private String createdTime = " ";		
    private String modifier = " ";		
    private String updatedTime = " ";		
    private String departmentid = " ";		
    private String procedureid = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("facilityid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("facilitycode");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("facilityname");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("pollutantname");
        eiColumn.setFieldLength(255);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("facilitytype");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("origin");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("handmethod");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("numbers");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("handability");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("shandnum");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("runtime");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("handfactors");
        eiColumn.setFieldLength(255);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("portid");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("portname");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("devicecode");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("devicemodel");
        eiColumn.setFieldLength(255);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("motormodel");
        eiColumn.setFieldLength(255);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("controllermodel");
        eiColumn.setFieldLength(255);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("situation");
        eiColumn.setFieldLength(255);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outstandard");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("facilitydepart");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isexecute");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setFieldLength(255);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("type");
        eiColumn.setFieldLength(5);
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

        eiColumn = new EiColumn("departmentid");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("procedureid");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public Tduhb04() {
        initMetaData();
    }

    /**
     * get the facilityid 
     * @return the facilityid
     */
    public String getFacilityid() {
        return this.facilityid;
    }

    /**
     * set the facilityid 
     */
    public void setFacilityid(String facilityid) {
        this.facilityid = facilityid;
    }

    /**
     * get the facilitycode 
     * @return the facilitycode
     */
    public String getFacilitycode() {
        return this.facilitycode;
    }

    /**
     * set the facilitycode 
     */
    public void setFacilitycode(String facilitycode) {
        this.facilitycode = facilitycode;
    }

    /**
     * get the facilityname 
     * @return the facilityname
     */
    public String getFacilityname() {
        return this.facilityname;
    }

    /**
     * set the facilityname 
     */
    public void setFacilityname(String facilityname) {
        this.facilityname = facilityname;
    }

    /**
     * get the pollutantname 
     * @return the pollutantname
     */
    public String getPollutantname() {
        return this.pollutantname;
    }

    /**
     * set the pollutantname 
     */
    public void setPollutantname(String pollutantname) {
        this.pollutantname = pollutantname;
    }

    /**
     * get the facilitytype 
     * @return the facilitytype
     */
    public String getFacilitytype() {
        return this.facilitytype;
    }

    /**
     * set the facilitytype 
     */
    public void setFacilitytype(String facilitytype) {
        this.facilitytype = facilitytype;
    }

    /**
     * get the origin 
     * @return the origin
     */
    public String getOrigin() {
        return this.origin;
    }

    /**
     * set the origin 
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * get the handmethod 
     * @return the handmethod
     */
    public String getHandmethod() {
        return this.handmethod;
    }

    /**
     * set the handmethod 
     */
    public void setHandmethod(String handmethod) {
        this.handmethod = handmethod;
    }

    /**
     * get the numbers 
     * @return the numbers
     */
    public String getNumbers() {
        return this.numbers;
    }

    /**
     * set the numbers 
     */
    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    /**
     * get the handability 
     * @return the handability
     */
    public String getHandability() {
        return this.handability;
    }

    /**
     * set the handability 
     */
    public void setHandability(String handability) {
        this.handability = handability;
    }

    /**
     * get the shandnum 
     * @return the shandnum
     */
    public String getShandnum() {
        return this.shandnum;
    }

    /**
     * set the shandnum 
     */
    public void setShandnum(String shandnum) {
        this.shandnum = shandnum;
    }

    /**
     * get the runtime 
     * @return the runtime
     */
    public String getRuntime() {
        return this.runtime;
    }

    /**
     * set the runtime 
     */
    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    /**
     * get the handfactors 
     * @return the handfactors
     */
    public String getHandfactors() {
        return this.handfactors;
    }

    /**
     * set the handfactors 
     */
    public void setHandfactors(String handfactors) {
        this.handfactors = handfactors;
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
     * get the devicecode 
     * @return the devicecode
     */
    public String getDevicecode() {
        return this.devicecode;
    }

    /**
     * set the devicecode 
     */
    public void setDevicecode(String devicecode) {
        this.devicecode = devicecode;
    }

    /**
     * get the devicemodel 
     * @return the devicemodel
     */
    public String getDevicemodel() {
        return this.devicemodel;
    }

    /**
     * set the devicemodel 
     */
    public void setDevicemodel(String devicemodel) {
        this.devicemodel = devicemodel;
    }

    /**
     * get the motormodel 
     * @return the motormodel
     */
    public String getMotormodel() {
        return this.motormodel;
    }

    /**
     * set the motormodel 
     */
    public void setMotormodel(String motormodel) {
        this.motormodel = motormodel;
    }

    /**
     * get the controllermodel 
     * @return the controllermodel
     */
    public String getControllermodel() {
        return this.controllermodel;
    }

    /**
     * set the controllermodel 
     */
    public void setControllermodel(String controllermodel) {
        this.controllermodel = controllermodel;
    }

    /**
     * get the situation 
     * @return the situation
     */
    public String getSituation() {
        return this.situation;
    }

    /**
     * set the situation 
     */
    public void setSituation(String situation) {
        this.situation = situation;
    }

    /**
     * get the outstandard 
     * @return the outstandard
     */
    public String getOutstandard() {
        return this.outstandard;
    }

    /**
     * set the outstandard 
     */
    public void setOutstandard(String outstandard) {
        this.outstandard = outstandard;
    }

    /**
     * get the facilitydepart 
     * @return the facilitydepart
     */
    public String getFacilitydepart() {
        return this.facilitydepart;
    }

    /**
     * set the facilitydepart 
     */
    public void setFacilitydepart(String facilitydepart) {
        this.facilitydepart = facilitydepart;
    }

    /**
     * get the isexecute 
     * @return the isexecute
     */
    public String getIsexecute() {
        return this.isexecute;
    }

    /**
     * set the isexecute 
     */
    public void setIsexecute(String isexecute) {
        this.isexecute = isexecute;
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

    /**
     * get the type 
     * @return the type
     */
    public String getType() {
        return this.type;
    }

    /**
     * set the type 
     */
    public void setType(String type) {
        this.type = type;
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
     * get the procedureid 
     * @return the procedureid
     */
    public String getProcedureid() {
        return this.procedureid;
    }

    /**
     * set the procedureid 
     */
    public void setProcedureid(String procedureid) {
        this.procedureid = procedureid;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setFacilityid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("facilityid")), facilityid));
        setFacilitycode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("facilitycode")), facilitycode));
        setFacilityname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("facilityname")), facilityname));
        setPollutantname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("pollutantname")), pollutantname));
        setFacilitytype(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("facilitytype")), facilitytype));
        setOrigin(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("origin")), origin));
        setHandmethod(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("handmethod")), handmethod));
        setNumbers(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("numbers")), numbers));
        setHandability(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("handability")), handability));
        setShandnum(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("shandnum")), shandnum));
        setRuntime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("runtime")), runtime));
        setHandfactors(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("handfactors")), handfactors));
        setPortid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("portid")), portid));
        setPortname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("portname")), portname));
        setDevicecode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("devicecode")), devicecode));
        setDevicemodel(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("devicemodel")), devicemodel));
        setMotormodel(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("motormodel")), motormodel));
        setControllermodel(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("controllermodel")), controllermodel));
        setSituation(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("situation")), situation));
        setOutstandard(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outstandard")), outstandard));
        setFacilitydepart(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("facilitydepart")), facilitydepart));
        setIsexecute(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isexecute")), isexecute));
        setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
        setType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("type")), type));
        setStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("status")), status));
        setCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creator")), creator));
        setCreatedTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createdTime")), createdTime));
        setModifier(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("modifier")), modifier));
        setUpdatedTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updatedTime")), updatedTime));
        setDepartmentid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentid")), departmentid));
        setProcedureid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("procedureid")), procedureid));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("facilityid", StringUtils.toString(facilityid, eiMetadata.getMeta("facilityid")));
        map.put("facilitycode", StringUtils.toString(facilitycode, eiMetadata.getMeta("facilitycode")));
        map.put("facilityname", StringUtils.toString(facilityname, eiMetadata.getMeta("facilityname")));
        map.put("pollutantname", StringUtils.toString(pollutantname, eiMetadata.getMeta("pollutantname")));
        map.put("facilitytype", StringUtils.toString(facilitytype, eiMetadata.getMeta("facilitytype")));
        map.put("origin", StringUtils.toString(origin, eiMetadata.getMeta("origin")));
        map.put("handmethod", StringUtils.toString(handmethod, eiMetadata.getMeta("handmethod")));
        map.put("numbers", StringUtils.toString(numbers, eiMetadata.getMeta("numbers")));
        map.put("handability", StringUtils.toString(handability, eiMetadata.getMeta("handability")));
        map.put("shandnum", StringUtils.toString(shandnum, eiMetadata.getMeta("shandnum")));
        map.put("runtime", StringUtils.toString(runtime, eiMetadata.getMeta("runtime")));
        map.put("handfactors", StringUtils.toString(handfactors, eiMetadata.getMeta("handfactors")));
        map.put("portid", StringUtils.toString(portid, eiMetadata.getMeta("portid")));
        map.put("portname", StringUtils.toString(portname, eiMetadata.getMeta("portname")));
        map.put("devicecode", StringUtils.toString(devicecode, eiMetadata.getMeta("devicecode")));
        map.put("devicemodel", StringUtils.toString(devicemodel, eiMetadata.getMeta("devicemodel")));
        map.put("motormodel", StringUtils.toString(motormodel, eiMetadata.getMeta("motormodel")));
        map.put("controllermodel", StringUtils.toString(controllermodel, eiMetadata.getMeta("controllermodel")));
        map.put("situation", StringUtils.toString(situation, eiMetadata.getMeta("situation")));
        map.put("outstandard", StringUtils.toString(outstandard, eiMetadata.getMeta("outstandard")));
        map.put("facilitydepart", StringUtils.toString(facilitydepart, eiMetadata.getMeta("facilitydepart")));
        map.put("isexecute", StringUtils.toString(isexecute, eiMetadata.getMeta("isexecute")));
        map.put("remark", StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("type", StringUtils.toString(type, eiMetadata.getMeta("type")));
        map.put("status", StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("creator", StringUtils.toString(creator, eiMetadata.getMeta("creator")));
        map.put("createdTime", StringUtils.toString(createdTime, eiMetadata.getMeta("createdTime")));
        map.put("modifier", StringUtils.toString(modifier, eiMetadata.getMeta("modifier")));
        map.put("updatedTime", StringUtils.toString(updatedTime, eiMetadata.getMeta("updatedTime")));
        map.put("departmentid", StringUtils.toString(departmentid, eiMetadata.getMeta("departmentid")));
        map.put("procedureid", StringUtils.toString(procedureid, eiMetadata.getMeta("procedureid")));
        return map;
    }
}