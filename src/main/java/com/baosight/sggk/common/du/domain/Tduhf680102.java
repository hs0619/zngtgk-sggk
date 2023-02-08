/**
* Generate time : 2021-04-15 15:13:01
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhf680102
* 
*/
public class Tduhf680102 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String pollid = " ";		/* 申报编号*/
    private String departmentid = " ";		/* 单位*/
    private String status = " ";		/* 当前状态*/
    private String title = " ";		/* 申报标题*/
    private String begintime = " ";		/* 申请开始时间*/
    private String endtime = " ";		/* 申请结束时间*/
    private String reason = " ";		/* 变更原因*/
    private String change = " ";		/* 变更内容*/
    private String service = " ";		/* 维护检修内容*/
    private String measure = " ";		/* 环保应急措施*/
    private String declarename = " ";		/* 填报人姓名*/
    private String phone = " ";		/* 联系电话*/
    private String declaretime = " ";		/* 报送时间*/
    private String opinion = " ";		/* 审核意见*/
    private String auditor = " ";		/* 审核人*/
    private String audittime = " ";		/* 审核时间*/
    private String sumup = " ";		/* 排放情况小结*/
    private String realbegintime = " ";		
    private String realendtime = " ";		
    private String dischargeportid = " ";		
    private String facilityid = " ";		
    private String condition = " ";		
    private String istong = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("pollid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("申报编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("departmentid");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("单位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("status");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("当前状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("title");
        eiColumn.setFieldLength(1024);
        eiColumn.setDescName("申报标题");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("begintime");
        eiColumn.setFieldLength(1024);
        eiColumn.setDescName("申请开始时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("endtime");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("申请结束时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("reason");
        eiColumn.setFieldLength(1024);
        eiColumn.setDescName("变更原因");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("change");
        eiColumn.setFieldLength(1024);
        eiColumn.setDescName("变更内容");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("service");
        eiColumn.setFieldLength(1024);
        eiColumn.setDescName("维护检修内容");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("measure");
        eiColumn.setFieldLength(1024);
        eiColumn.setDescName("环保应急措施");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("declarename");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("填报人姓名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("phone");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("联系电话");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("declaretime");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("报送时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("opinion");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("审核意见");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("auditor");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("审核人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("audittime");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("审核时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sumup");
        eiColumn.setFieldLength(1024);
        eiColumn.setDescName("排放情况小结");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("realbegintime");
        eiColumn.setFieldLength(64);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("realendtime");
        eiColumn.setFieldLength(64);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dischargeportid");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("facilityid");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("condition");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("istong");
        eiColumn.setFieldLength(2);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public Tduhf680102() {
        initMetaData();
    }

    /**
     * get the pollid - 申报编号
     * @return the pollid
     */
    public String getPollid() {
        return this.pollid;
    }

    /**
     * set the pollid - 申报编号
     */
    public void setPollid(String pollid) {
        this.pollid = pollid;
    }

    /**
     * get the departmentid - 单位
     * @return the departmentid
     */
    public String getDepartmentid() {
        return this.departmentid;
    }

    /**
     * set the departmentid - 单位
     */
    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    /**
     * get the status - 当前状态
     * @return the status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * set the status - 当前状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * get the title - 申报标题
     * @return the title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * set the title - 申报标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * get the begintime - 申请开始时间
     * @return the begintime
     */
    public String getBegintime() {
        return this.begintime;
    }

    /**
     * set the begintime - 申请开始时间
     */
    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    /**
     * get the endtime - 申请结束时间
     * @return the endtime
     */
    public String getEndtime() {
        return this.endtime;
    }

    /**
     * set the endtime - 申请结束时间
     */
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    /**
     * get the reason - 变更原因
     * @return the reason
     */
    public String getReason() {
        return this.reason;
    }

    /**
     * set the reason - 变更原因
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * get the change - 变更内容
     * @return the change
     */
    public String getChange() {
        return this.change;
    }

    /**
     * set the change - 变更内容
     */
    public void setChange(String change) {
        this.change = change;
    }

    /**
     * get the service - 维护检修内容
     * @return the service
     */
    public String getService() {
        return this.service;
    }

    /**
     * set the service - 维护检修内容
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * get the measure - 环保应急措施
     * @return the measure
     */
    public String getMeasure() {
        return this.measure;
    }

    /**
     * set the measure - 环保应急措施
     */
    public void setMeasure(String measure) {
        this.measure = measure;
    }

    /**
     * get the declarename - 填报人姓名
     * @return the declarename
     */
    public String getDeclarename() {
        return this.declarename;
    }

    /**
     * set the declarename - 填报人姓名
     */
    public void setDeclarename(String declarename) {
        this.declarename = declarename;
    }

    /**
     * get the phone - 联系电话
     * @return the phone
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * set the phone - 联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * get the declaretime - 报送时间
     * @return the declaretime
     */
    public String getDeclaretime() {
        return this.declaretime;
    }

    /**
     * set the declaretime - 报送时间
     */
    public void setDeclaretime(String declaretime) {
        this.declaretime = declaretime;
    }

    /**
     * get the opinion - 审核意见
     * @return the opinion
     */
    public String getOpinion() {
        return this.opinion;
    }

    /**
     * set the opinion - 审核意见
     */
    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    /**
     * get the auditor - 审核人
     * @return the auditor
     */
    public String getAuditor() {
        return this.auditor;
    }

    /**
     * set the auditor - 审核人
     */
    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    /**
     * get the audittime - 审核时间
     * @return the audittime
     */
    public String getAudittime() {
        return this.audittime;
    }

    /**
     * set the audittime - 审核时间
     */
    public void setAudittime(String audittime) {
        this.audittime = audittime;
    }

    /**
     * get the sumup - 排放情况小结
     * @return the sumup
     */
    public String getSumup() {
        return this.sumup;
    }

    /**
     * set the sumup - 排放情况小结
     */
    public void setSumup(String sumup) {
        this.sumup = sumup;
    }

    /**
     * get the realbegintime 
     * @return the realbegintime
     */
    public String getRealbegintime() {
        return this.realbegintime;
    }

    /**
     * set the realbegintime 
     */
    public void setRealbegintime(String realbegintime) {
        this.realbegintime = realbegintime;
    }

    /**
     * get the realendtime 
     * @return the realendtime
     */
    public String getRealendtime() {
        return this.realendtime;
    }

    /**
     * set the realendtime 
     */
    public void setRealendtime(String realendtime) {
        this.realendtime = realendtime;
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
     * get the condition 
     * @return the condition
     */
    public String getCondition() {
        return this.condition;
    }

    /**
     * set the condition 
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * get the istong 
     * @return the istong
     */
    public String getIstong() {
        return this.istong;
    }

    /**
     * set the istong 
     */
    public void setIstong(String istong) {
        this.istong = istong;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setPollid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("pollid")), pollid));
        setDepartmentid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentid")), departmentid));
        setStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("status")), status));
        setTitle(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("title")), title));
        setBegintime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("begintime")), begintime));
        setEndtime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("endtime")), endtime));
        setReason(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("reason")), reason));
        setChange(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("change")), change));
        setService(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("service")), service));
        setMeasure(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("measure")), measure));
        setDeclarename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("declarename")), declarename));
        setPhone(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("phone")), phone));
        setDeclaretime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("declaretime")), declaretime));
        setOpinion(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("opinion")), opinion));
        setAuditor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("auditor")), auditor));
        setAudittime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("audittime")), audittime));
        setSumup(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sumup")), sumup));
        setRealbegintime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("realbegintime")), realbegintime));
        setRealendtime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("realendtime")), realendtime));
        setDischargeportid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dischargeportid")), dischargeportid));
        setFacilityid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("facilityid")), facilityid));
        setCondition(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("condition")), condition));
        setIstong(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("istong")), istong));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("pollid", StringUtils.toString(pollid, eiMetadata.getMeta("pollid")));
        map.put("departmentid", StringUtils.toString(departmentid, eiMetadata.getMeta("departmentid")));
        map.put("status", StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("title", StringUtils.toString(title, eiMetadata.getMeta("title")));
        map.put("begintime", StringUtils.toString(begintime, eiMetadata.getMeta("begintime")));
        map.put("endtime", StringUtils.toString(endtime, eiMetadata.getMeta("endtime")));
        map.put("reason", StringUtils.toString(reason, eiMetadata.getMeta("reason")));
        map.put("change", StringUtils.toString(change, eiMetadata.getMeta("change")));
        map.put("service", StringUtils.toString(service, eiMetadata.getMeta("service")));
        map.put("measure", StringUtils.toString(measure, eiMetadata.getMeta("measure")));
        map.put("declarename", StringUtils.toString(declarename, eiMetadata.getMeta("declarename")));
        map.put("phone", StringUtils.toString(phone, eiMetadata.getMeta("phone")));
        map.put("declaretime", StringUtils.toString(declaretime, eiMetadata.getMeta("declaretime")));
        map.put("opinion", StringUtils.toString(opinion, eiMetadata.getMeta("opinion")));
        map.put("auditor", StringUtils.toString(auditor, eiMetadata.getMeta("auditor")));
        map.put("audittime", StringUtils.toString(audittime, eiMetadata.getMeta("audittime")));
        map.put("sumup", StringUtils.toString(sumup, eiMetadata.getMeta("sumup")));
        map.put("realbegintime", StringUtils.toString(realbegintime, eiMetadata.getMeta("realbegintime")));
        map.put("realendtime", StringUtils.toString(realendtime, eiMetadata.getMeta("realendtime")));
        map.put("dischargeportid", StringUtils.toString(dischargeportid, eiMetadata.getMeta("dischargeportid")));
        map.put("facilityid", StringUtils.toString(facilityid, eiMetadata.getMeta("facilityid")));
        map.put("condition", StringUtils.toString(condition, eiMetadata.getMeta("condition")));
        map.put("istong", StringUtils.toString(istong, eiMetadata.getMeta("istong")));
        return map;
    }
}