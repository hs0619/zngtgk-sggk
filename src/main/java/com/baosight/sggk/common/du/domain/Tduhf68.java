/**
* Generate time : 2021-06-10 14:56:33
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhf68
* 
*/
public class Tduhf68 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String declareId = " ";		
    private String companyId = "";		
    private String companyName = "";		
    private String declareStatus = "";		
    private String auditStatus = "";		
    private String portId = "";		
    private String portName = "";		
    private String controlLine = "";		
    private String faultType = "";		
    private String declareReason = "";		
    private String abnormalStartTime = "";		
    private String abnormalEndTime = "";		
    private String factorNames = "";
    private String factorIds = "";	
    private String treatmentMeasures = "";		
    private String declarant = "";		
    private String phone = "";		
    private String reportTime = "";		
    private String creator = "";		
    private String createdTime = "";		
    private String modifier = "";		
    private String updatedTime = "";		
    private String auditStatusName = "";		
    private String faultStatus = "";		
    private String reviewer = "";		
    private String auditTime = "";		
    private String auditOpinion = "";		
    
    private String auditId = "";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("declareId");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("companyId");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("companyName");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("declareStatus");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("auditStatus");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("portId");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("portName");
        eiColumn.setFieldLength(200);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("controlLine");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("faultType");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("declareReason");
        eiColumn.setFieldLength(2000);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("abnormalStartTime");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("abnormalEndTime");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("factorNames");
        eiColumn.setFieldLength(200);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("treatmentMeasures");
        eiColumn.setFieldLength(2000);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("declarant");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("phone");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("reportTime");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creator");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createdTime");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("modifier");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updatedTime");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("auditStatusName");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("faultStatus");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("reviewer");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("auditTime");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("auditOpinion");
        eiColumn.setFieldLength(500);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("auditId");
        eiColumn.setFieldLength(500);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("factorIds");
        eiColumn.setFieldLength(500);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public Tduhf68() {
        initMetaData();
    }

    /**
     * get the declareId 
     * @return the declareId
     */
    public String getDeclareId() {
        return this.declareId;
    }

    /**
     * set the declareId 
     */
    public void setDeclareId(String declareId) {
        this.declareId = declareId;
    }

    /**
     * get the companyId 
     * @return the companyId
     */
    public String getCompanyId() {
        return this.companyId;
    }

    /**
     * set the companyId 
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    /**
     * get the companyName 
     * @return the companyName
     */
    public String getCompanyName() {
        return this.companyName;
    }

    /**
     * set the companyName 
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * get the declareStatus 
     * @return the declareStatus
     */
    public String getDeclareStatus() {
        return this.declareStatus;
    }

    /**
     * set the declareStatus 
     */
    public void setDeclareStatus(String declareStatus) {
        this.declareStatus = declareStatus;
    }

    /**
     * get the auditStatus 
     * @return the auditStatus
     */
    public String getAuditStatus() {
        return this.auditStatus;
    }

    /**
     * set the auditStatus 
     */
    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * get the portId 
     * @return the portId
     */
    public String getPortId() {
        return this.portId;
    }

    /**
     * set the portId 
     */
    public void setPortId(String portId) {
        this.portId = portId;
    }

    /**
     * get the portName 
     * @return the portName
     */
    public String getPortName() {
        return this.portName;
    }

    /**
     * set the portName 
     */
    public void setPortName(String portName) {
        this.portName = portName;
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
     * get the faultType 
     * @return the faultType
     */
    public String getFaultType() {
        return this.faultType;
    }

    /**
     * set the faultType 
     */
    public void setFaultType(String faultType) {
        this.faultType = faultType;
    }

    /**
     * get the declareReason 
     * @return the declareReason
     */
    public String getDeclareReason() {
        return this.declareReason;
    }

    /**
     * set the declareReason 
     */
    public void setDeclareReason(String declareReason) {
        this.declareReason = declareReason;
    }

    /**
     * get the abnormalStartTime 
     * @return the abnormalStartTime
     */
    public String getAbnormalStartTime() {
        return this.abnormalStartTime;
    }

    /**
     * set the abnormalStartTime 
     */
    public void setAbnormalStartTime(String abnormalStartTime) {
        this.abnormalStartTime = abnormalStartTime;
    }

    /**
     * get the abnormalEndTime 
     * @return the abnormalEndTime
     */
    public String getAbnormalEndTime() {
        return this.abnormalEndTime;
    }

    /**
     * set the abnormalEndTime 
     */
    public void setAbnormalEndTime(String abnormalEndTime) {
        this.abnormalEndTime = abnormalEndTime;
    }

    /**
     * get the factorNames 
     * @return the factorNames
     */
    public String getFactorNames() {
        return this.factorNames;
    }

    /**
     * set the factorNames 
     */
    public void setFactorNames(String factorNames) {
        this.factorNames = factorNames;
    }

    /**
     * get the treatmentMeasures 
     * @return the treatmentMeasures
     */
    public String getTreatmentMeasures() {
        return this.treatmentMeasures;
    }

    /**
     * set the treatmentMeasures 
     */
    public void setTreatmentMeasures(String treatmentMeasures) {
        this.treatmentMeasures = treatmentMeasures;
    }

    /**
     * get the declarant 
     * @return the declarant
     */
    public String getDeclarant() {
        return this.declarant;
    }

    /**
     * set the declarant 
     */
    public void setDeclarant(String declarant) {
        this.declarant = declarant;
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
     * get the reportTime 
     * @return the reportTime
     */
    public String getReportTime() {
        return this.reportTime;
    }

    /**
     * set the reportTime 
     */
    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
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
     * get the auditStatusName 
     * @return the auditStatusName
     */
    public String getAuditStatusName() {
        return this.auditStatusName;
    }

    /**
     * set the auditStatusName 
     */
    public void setAuditStatusName(String auditStatusName) {
        this.auditStatusName = auditStatusName;
    }

    /**
     * get the faultStatus 
     * @return the faultStatus
     */
    public String getFaultStatus() {
        return this.faultStatus;
    }

    /**
     * set the faultStatus 
     */
    public void setFaultStatus(String faultStatus) {
        this.faultStatus = faultStatus;
    }

    /**
     * get the reviewer 
     * @return the reviewer
     */
    public String getReviewer() {
        return this.reviewer;
    }

    /**
     * set the reviewer 
     */
    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    /**
     * get the auditTime 
     * @return the auditTime
     */
    public String getAuditTime() {
        return this.auditTime;
    }

    /**
     * set the auditTime 
     */
    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    /**
     * get the auditOpinion 
     * @return the auditOpinion
     */
    public String getAuditOpinion() {
        return this.auditOpinion;
    }

    /**
     * set the auditOpinion 
     */
    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }

    public String getAuditId() {
		return auditId;
	}

	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	public String getFactorIds() {
		return factorIds;
	}

	public void setFactorIds(String factorIds) {
		this.factorIds = factorIds;
	}

	/**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setDeclareId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("declareId")), declareId));
        setCompanyId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("companyId")), companyId));
        setCompanyName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("companyName")), companyName));
        setDeclareStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("declareStatus")), declareStatus));
        setAuditStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("auditStatus")), auditStatus));
        setPortId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("portId")), portId));
        setPortName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("portName")), portName));
        setControlLine(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("controlLine")), controlLine));
        setFaultType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("faultType")), faultType));
        setDeclareReason(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("declareReason")), declareReason));
        setAbnormalStartTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("abnormalStartTime")), abnormalStartTime));
        setAbnormalEndTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("abnormalEndTime")), abnormalEndTime));
        setFactorNames(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("factorNames")), factorNames));
        setTreatmentMeasures(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("treatmentMeasures")), treatmentMeasures));
        setDeclarant(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("declarant")), declarant));
        setPhone(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("phone")), phone));
        setReportTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("reportTime")), reportTime));
        setCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creator")), creator));
        setCreatedTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createdTime")), createdTime));
        setModifier(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("modifier")), modifier));
        setUpdatedTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updatedTime")), updatedTime));
        setAuditStatusName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("auditStatusName")), auditStatusName));
        setFaultStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("faultStatus")), faultStatus));
        setReviewer(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("reviewer")), reviewer));
        setAuditTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("auditTime")), auditTime));
        setAuditOpinion(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("auditOpinion")), auditOpinion));
        
        setAuditId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("auditId")), auditId));
        
        setFactorIds(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("factorIds")), factorIds));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("declareId", StringUtils.toString(declareId, eiMetadata.getMeta("declareId")));
        map.put("companyId", StringUtils.toString(companyId, eiMetadata.getMeta("companyId")));
        map.put("companyName", StringUtils.toString(companyName, eiMetadata.getMeta("companyName")));
        map.put("declareStatus", StringUtils.toString(declareStatus, eiMetadata.getMeta("declareStatus")));
        map.put("auditStatus", StringUtils.toString(auditStatus, eiMetadata.getMeta("auditStatus")));
        map.put("portId", StringUtils.toString(portId, eiMetadata.getMeta("portId")));
        map.put("portName", StringUtils.toString(portName, eiMetadata.getMeta("portName")));
        map.put("controlLine", StringUtils.toString(controlLine, eiMetadata.getMeta("controlLine")));
        map.put("faultType", StringUtils.toString(faultType, eiMetadata.getMeta("faultType")));
        map.put("declareReason", StringUtils.toString(declareReason, eiMetadata.getMeta("declareReason")));
        map.put("abnormalStartTime", StringUtils.toString(abnormalStartTime, eiMetadata.getMeta("abnormalStartTime")));
        map.put("abnormalEndTime", StringUtils.toString(abnormalEndTime, eiMetadata.getMeta("abnormalEndTime")));
        map.put("factorNames", StringUtils.toString(factorNames, eiMetadata.getMeta("factorNames")));
        map.put("treatmentMeasures", StringUtils.toString(treatmentMeasures, eiMetadata.getMeta("treatmentMeasures")));
        map.put("declarant", StringUtils.toString(declarant, eiMetadata.getMeta("declarant")));
        map.put("phone", StringUtils.toString(phone, eiMetadata.getMeta("phone")));
        map.put("reportTime", StringUtils.toString(reportTime, eiMetadata.getMeta("reportTime")));
        map.put("creator", StringUtils.toString(creator, eiMetadata.getMeta("creator")));
        map.put("createdTime", StringUtils.toString(createdTime, eiMetadata.getMeta("createdTime")));
        map.put("modifier", StringUtils.toString(modifier, eiMetadata.getMeta("modifier")));
        map.put("updatedTime", StringUtils.toString(updatedTime, eiMetadata.getMeta("updatedTime")));
        map.put("auditStatusName", StringUtils.toString(auditStatusName, eiMetadata.getMeta("auditStatusName")));
        map.put("faultStatus", StringUtils.toString(faultStatus, eiMetadata.getMeta("faultStatus")));
        map.put("reviewer", StringUtils.toString(reviewer, eiMetadata.getMeta("reviewer")));
        map.put("auditTime", StringUtils.toString(auditTime, eiMetadata.getMeta("auditTime")));
        map.put("auditOpinion", StringUtils.toString(auditOpinion, eiMetadata.getMeta("auditOpinion")));
        
        map.put("auditId", StringUtils.toString(auditId, eiMetadata.getMeta("auditId")));
        map.put("factorIds", StringUtils.toString(factorIds, eiMetadata.getMeta("factorIds")));
        
        return map;
    }
}