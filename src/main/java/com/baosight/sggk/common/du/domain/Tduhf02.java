/**
* Generate time : 2021-03-01 13:58:45
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhf02
* 
*/
public class Tduhf02 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String departmentid = "NULL";		
    private String departmentname = " ";		
    private String name = " ";		
    private String tel = "NULL";		
    private String phone = "NULL";		
    private String job = " ";		
    private String isalarm = "NULL";		
    private String ismanager = "NULL";		
    private String status = "NULL";
    
    private String phone_name="";

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("departmentid");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("departmentname");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("name");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("tel");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("phone");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("job");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isalarm");
        eiColumn.setFieldLength(5);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("ismanager");
        eiColumn.setFieldLength(5);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("status");
        eiColumn.setFieldLength(5);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("phone_name");
        eiColumn.setFieldLength(5);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public Tduhf02() {
        initMetaData();
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
     * get the name 
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * set the name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get the tel 
     * @return the tel
     */
    public String getTel() {
        return this.tel;
    }

    /**
     * set the tel 
     */
    public void setTel(String tel) {
        this.tel = tel;
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
     * get the job 
     * @return the job
     */
    public String getJob() {
        return this.job;
    }

    /**
     * set the job 
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * get the isalarm 
     * @return the isalarm
     */
    public String getIsalarm() {
        return this.isalarm;
    }

    /**
     * set the isalarm 
     */
    public void setIsalarm(String isalarm) {
        this.isalarm = isalarm;
    }

    /**
     * get the ismanager 
     * @return the ismanager
     */
    public String getIsmanager() {
        return this.ismanager;
    }

    /**
     * set the ismanager 
     */
    public void setIsmanager(String ismanager) {
        this.ismanager = ismanager;
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

    public String getPhone_name() {
		return phone_name;
	}

	public void setPhone_name(String phone_name) {
		this.phone_name = phone_name;
	}

	/**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setDepartmentid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentid")), departmentid));
        setDepartmentname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentname")), departmentname));
        setName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("name")), name));
        setTel(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("tel")), tel));
        setPhone(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("phone")), phone));
        setJob(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("job")), job));
        setIsalarm(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isalarm")), isalarm));
        setIsmanager(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("ismanager")), ismanager));
        setStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("status")), status));
        
        setPhone_name(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("phone_name")), phone_name));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("departmentid", StringUtils.toString(departmentid, eiMetadata.getMeta("departmentid")));
        map.put("departmentname", StringUtils.toString(departmentname, eiMetadata.getMeta("departmentname")));
        map.put("name", StringUtils.toString(name, eiMetadata.getMeta("name")));
        map.put("tel", StringUtils.toString(tel, eiMetadata.getMeta("tel")));
        map.put("phone", StringUtils.toString(phone, eiMetadata.getMeta("phone")));
        map.put("job", StringUtils.toString(job, eiMetadata.getMeta("job")));
        map.put("isalarm", StringUtils.toString(isalarm, eiMetadata.getMeta("isalarm")));
        map.put("ismanager", StringUtils.toString(ismanager, eiMetadata.getMeta("ismanager")));
        map.put("status", StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("phone_name", StringUtils.toString(phone_name, eiMetadata.getMeta("phone_name")));
        return map;
    }
}