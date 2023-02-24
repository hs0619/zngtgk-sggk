/**
* Generate time : 2021-07-06 18:01:45
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.du.hf.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* DUHF75
* 
*/
public class DUHF75 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String logicid = " ";		/* 辐射工作人员ID*/
    private String status = " ";		/* 状态*/
    private String workerid = " ";//工号
    private String workername = " ";		/* 姓名*/
    private String gender = " ";		/* 性别*/
    private String birthdate = " ";		/* 出生日期*/
    private String certificatetype = " ";		/* 证件类型*/
    private String certificatenumber = " ";		/* 证件号码*/
    private String departmentid = " ";		/* 单位*/
    private String operatingpost = " ";		/* 工作岗位*/
    private String graschool = " ";		/* 毕业院校*/
    private String education = " ";		/* 学历*/
    private String eventtype = " ";		/* 专业*/
    private String deadlinedate = " ";		/* 有效期*/
    private String certificateno = " ";		/* 培训/考试编号*/
    private String certificatename = " ";		/* 培训机构*/
    private String remark = " ";		/* 备注*/
    private String createdid = " ";		/* 创建人*/
    private String createdman = " ";		/* 创建人*/
    private String createdtime = " ";		/* 创建时间*/
    private String updatedman = " ";		/* 更新人*/
    private String updatedtime = " ";		/* 更新时间*/

    private String iswork = " ";
    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("logicid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(36);
        eiColumn.setDescName("主键id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("status");
        eiColumn.setFieldLength(5);
        eiColumn.setDescName("状态");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("workerid");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName("工号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("workername");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName("姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("gender");
        eiColumn.setFieldLength(5);
        eiColumn.setDescName("性别");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("birthdate");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("出生日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("certificatetype");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("证件类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("certificatenumber");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName("证件号码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("departmentid");
        eiColumn.setFieldLength(36);
        eiColumn.setDescName("单位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("operatingpost");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName("工作岗位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("graschool");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName("毕业院校");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("education");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("学历");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("eventtype");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName("专业");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("deadlinedate");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("有效期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("certificateno");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName("培训/考试编号");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("certificatename");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName("培训机构");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setFieldLength(512);
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("createdid");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("创建人id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createdman");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createdtime");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updatedman");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("更新人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updatedtime");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("更新时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("iswork");
        eiColumn.setFieldLength(36);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);



    }

    public String getLogicid() {
        return logicid;
    }

    public void setLogicid(String logicid) {
        this.logicid = logicid;
    }

    public String getWorkerid() {
        return workerid;
    }

    public void setWorkerid(String workerid) {
        this.workerid = workerid;
    }

    public String getWorkername() {
        return workername;
    }

    public void setWorkername(String workername) {
        this.workername = workername;
    }

    /**
     * the constructor
     */
    public DUHF75() {
        initMetaData();
    }


    /**
     * get the status - 状态
     * @return the status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * set the status - 状态
     */
    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * get the gender - 性别
     * @return the gender
     */
    public String getGender() {
        return this.gender;
    }

    /**
     * set the gender - 性别
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * get the birthdate - 出生日期
     * @return the birthdate
     */
    public String getBirthdate() {
        return this.birthdate;
    }

    /**
     * set the birthdate - 出生日期
     */
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * get the certificatetype - 证件类型
     * @return the certificatetype
     */
    public String getCertificatetype() {
        return this.certificatetype;
    }

    /**
     * set the certificatetype - 证件类型
     */
    public void setCertificatetype(String certificatetype) {
        this.certificatetype = certificatetype;
    }

    /**
     * get the certificatenumber - 证件号码
     * @return the certificatenumber
     */
    public String getCertificatenumber() {
        return this.certificatenumber;
    }

    /**
     * set the certificatenumber - 证件号码
     */
    public void setCertificatenumber(String certificatenumber) {
        this.certificatenumber = certificatenumber;
    }

   

    public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

	/**
     * get the operatingpost - 工作岗位
     * @return the operatingpost
     */
    public String getOperatingpost() {
        return this.operatingpost;
    }

    /**
     * set the operatingpost - 工作岗位
     */
    public void setOperatingpost(String operatingpost) {
        this.operatingpost = operatingpost;
    }

    /**
     * get the graschool - 毕业院校
     * @return the graschool
     */
    public String getGraschool() {
        return this.graschool;
    }

    /**
     * set the graschool - 毕业院校
     */
    public void setGraschool(String graschool) {
        this.graschool = graschool;
    }

    /**
     * get the education - 学历
     * @return the education
     */
    public String getEducation() {
        return this.education;
    }

    /**
     * set the education - 学历
     */
    public void setEducation(String education) {
        this.education = education;
    }

    /**
     * get the eventtype - 专业
     * @return the eventtype
     */
    public String getEventtype() {
        return this.eventtype;
    }

    /**
     * set the eventtype - 专业
     */
    public void setEventtype(String eventtype) {
        this.eventtype = eventtype;
    }

    /**
     * get the deadlinedate - 有效期
     * @return the deadlinedate
     */
    public String getDeadlinedate() {
        return this.deadlinedate;
    }

    /**
     * set the deadlinedate - 有效期
     */
    public void setDeadlinedate(String deadlinedate) {
        this.deadlinedate = deadlinedate;
    }

    /**
     * get the certificateno - 培训/考试编号
     * @return the certificateno
     */
    public String getCertificateno() {
        return this.certificateno;
    }

    /**
     * set the certificateno - 培训/考试编号
     */
    public void setCertificateno(String certificateno) {
        this.certificateno = certificateno;
    }

    /**
     * get the remark - 备注
     * @return the remark
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * set the remark - 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * get the createdman - 创建人
     * @return the createdman
     */
    public String getCreatedman() {
        return this.createdman;
    }

    /**
     * set the createdman - 创建人
     */
    public void setCreatedman(String createdman) {
        this.createdman = createdman;
    }

    /**
     * get the createdtime - 创建时间
     * @return the createdtime
     */
    public String getCreatedtime() {
        return this.createdtime;
    }

    /**
     * set the createdtime - 创建时间
     */
    public void setCreatedtime(String createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * get the updatedman - 更新人
     * @return the updatedman
     */
    public String getUpdatedman() {
        return this.updatedman;
    }

    /**
     * set the updatedman - 更新人
     */
    public void setUpdatedman(String updatedman) {
        this.updatedman = updatedman;
    }

    /**
     * get the updatedtime - 更新时间
     * @return the updatedtime
     */
    public String getUpdatedtime() {
        return this.updatedtime;
    }

    /**
     * set the updatedtime - 更新时间
     */
    public void setUpdatedtime(String updatedtime) {
        this.updatedtime = updatedtime;
    }


    public String getCertificatename() {
		return certificatename;
	}

	public void setCertificatename(String certificatename) {
		this.certificatename = certificatename;
	}

	public String getCreatedid() {
		return createdid;
	}

	public void setCreatedid(String createdid) {
		this.createdid = createdid;
	}

	public String getIswork() {
		return iswork;
	}

	public void setIswork(String iswork) {
		this.iswork = iswork;
	}



	/**
     * get the value from Map
     */
    public void fromMap(Map map) {
        setLogicid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("logicid")), logicid));
        setWorkerid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("workerid")), workerid));
        setWorkername(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("workername")), workername));
        setStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("status")), status));
        setGender(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("gender")), gender));
        setBirthdate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("birthdate")), birthdate));
        setCertificatetype(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("certificatetype")), certificatetype));
        setCertificatenumber(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("certificatenumber")), certificatenumber));
        setDepartmentid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentid")), departmentid));
        setOperatingpost(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("operatingpost")), operatingpost));
        setGraschool(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("graschool")), graschool));
        setEducation(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("education")), education));
        setEventtype(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("eventtype")), eventtype));
        setDeadlinedate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deadlinedate")), deadlinedate));
        setCertificateno(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("certificateno")), certificateno));
        setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
        setCreatedman(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createdman")), createdman));
        setCreatedtime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createdtime")), createdtime));
        setUpdatedman(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updatedman")), updatedman));
        setUpdatedtime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updatedtime")), updatedtime));
        setIswork(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("iswork")), iswork));
        
        setCreatedid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createdid")), createdid));
        setCertificatename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("certificatename")), certificatename));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("logicid", StringUtils.toString(logicid, eiMetadata.getMeta("logicid")));
        map.put("workerid", StringUtils.toString(workerid, eiMetadata.getMeta("workerid")));
        map.put("workername", StringUtils.toString(workername, eiMetadata.getMeta("workername")));
        map.put("status", StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("gender", StringUtils.toString(gender, eiMetadata.getMeta("gender")));
        map.put("birthdate", StringUtils.toString(birthdate, eiMetadata.getMeta("birthdate")));
        map.put("certificatetype", StringUtils.toString(certificatetype, eiMetadata.getMeta("certificatetype")));
        map.put("certificatenumber", StringUtils.toString(certificatenumber, eiMetadata.getMeta("certificatenumber")));
        map.put("departmentid", StringUtils.toString(departmentid, eiMetadata.getMeta("departmentid")));
        map.put("operatingpost", StringUtils.toString(operatingpost, eiMetadata.getMeta("operatingpost")));
        map.put("graschool", StringUtils.toString(graschool, eiMetadata.getMeta("graschool")));
        map.put("education", StringUtils.toString(education, eiMetadata.getMeta("education")));
        map.put("eventtype", StringUtils.toString(eventtype, eiMetadata.getMeta("eventtype")));
        map.put("deadlinedate", StringUtils.toString(deadlinedate, eiMetadata.getMeta("deadlinedate")));
        map.put("certificateno", StringUtils.toString(certificateno, eiMetadata.getMeta("certificateno")));
        map.put("remark", StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("createdman", StringUtils.toString(createdman, eiMetadata.getMeta("createdman")));
        map.put("createdtime", StringUtils.toString(createdtime, eiMetadata.getMeta("createdtime")));
        map.put("updatedman", StringUtils.toString(updatedman, eiMetadata.getMeta("updatedman")));
        map.put("updatedtime", StringUtils.toString(updatedtime, eiMetadata.getMeta("updatedtime")));
        map.put("iswork", StringUtils.toString(iswork, eiMetadata.getMeta("iswork")));
        
        map.put("createdid", StringUtils.toString(createdid, eiMetadata.getMeta("createdid")));
        map.put("certificatename", StringUtils.toString(certificatename, eiMetadata.getMeta("certificatename")));
        return map;
    }
}