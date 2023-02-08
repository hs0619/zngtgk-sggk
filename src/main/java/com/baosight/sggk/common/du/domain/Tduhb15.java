/**
* Generate time : 2020-09-08 10:09:34
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhb15
* 
*/
public class Tduhb15 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String monitorid = " ";		
    private String monitorname = " ";		
    private String factorid = " ";		/* 因子编号*/
    private String factorname = " ";		
    private String portid = " ";		/* 对应排口编号*/
    private String highlimit = " ";		/* 高限*/
    private String lowlimit = " ";		/* 低限*/
    private String nkhighlimit = " ";		/* 内控高限*/
    private String nklowlimit = " ";		/* 内控低限*/
    private String description = " ";		/* 描述*/
    private String status = " ";		/* 状态位*/
    private String creator = " ";		/* 新增人*/
    private String createdTime = " ";		/* 新增时间*/
    private String modifier = " ";		/* 修改人*/
    private String updatedTime = " ";		/* 修改时间*/

	/**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("monitorid");
        eiColumn.setDescName("监测类型编号");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("monitorname");
        eiColumn.setDescName("监测类型名称");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("factorid");
        eiColumn.setDescName("因子编号");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("factorname");
        eiColumn.setDescName("因子名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("portid");
        eiColumn.setDescName("对应排口编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("highlimit");
        eiColumn.setDescName("高限");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("lowlimit");
        eiColumn.setDescName("低限");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("nkhighlimit");
        eiColumn.setDescName("内控高限");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("nklowlimit");
        eiColumn.setDescName("内控低限");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("description");
        eiColumn.setDescName("描述");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("status");
        eiColumn.setDescName("状态位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creator");
        eiColumn.setDescName("新增人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createdTime");
        eiColumn.setDescName("新增时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("modifier");
        eiColumn.setDescName("修改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updatedTime");
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public Tduhb15() {
        initMetaData();
    }

    public String getMonitorid() {
		return this.monitorid;
	}

	public void setMonitorid(String monitorid) {
		this.monitorid = monitorid;
	}

	public String getMonitorname() {
		return this.monitorname;
	}

	public void setMonitorname(String monitorname) {
		this.monitorname = monitorname;
	}
	
    /**
     * get the factorid - 因子编号
     * @return the factorid
     */
    public String getFactorid() {
        return this.factorid;
    }

    /**
     * set the factorid - 因子编号
     */
    public void setFactorid(String factorid) {
        this.factorid = factorid;
    }
    
    public String getFactorname() {
		return this.factorname;
	}

	public void setFactorname(String factorname) {
		this.factorname = factorname;
	}

    /**
     * get the portid - 对应排口编号
     * @return the portid
     */
    public String getPortid() {
        return this.portid;
    }

    /**
     * set the portid - 对应排口编号
     */
    public void setPortid(String portid) {
        this.portid = portid;
    }

    /**
     * get the highlimit - 高限
     * @return the highlimit
     */
    public String getHighlimit() {
        return this.highlimit;
    }

    /**
     * set the highlimit - 高限
     */
    public void setHighlimit(String highlimit) {
        this.highlimit = highlimit;
    }

    /**
     * get the lowlimit - 低限
     * @return the lowlimit
     */
    public String getLowlimit() {
        return this.lowlimit;
    }

    /**
     * set the lowlimit - 低限
     */
    public void setLowlimit(String lowlimit) {
        this.lowlimit = lowlimit;
    }

    /**
     * get the nkhighlimit - 内控高限
     * @return the nkhighlimit
     */
    public String getNkhighlimit() {
        return this.nkhighlimit;
    }

    /**
     * set the nkhighlimit - 内控高限
     */
    public void setNkhighlimit(String nkhighlimit) {
        this.nkhighlimit = nkhighlimit;
    }

    /**
     * get the nklowlimit - 内控低限
     * @return the nklowlimit
     */
    public String getNklowlimit() {
        return this.nklowlimit;
    }

    /**
     * set the nklowlimit - 内控低限
     */
    public void setNklowlimit(String nklowlimit) {
        this.nklowlimit = nklowlimit;
    }

    /**
     * get the description - 描述
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * set the description - 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * get the status - 状态位
     * @return the status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * set the status - 状态位
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * get the creator - 新增人
     * @return the creator
     */
    public String getCreator() {
        return this.creator;
    }

    /**
     * set the creator - 新增人
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * get the createdTime - 新增时间
     * @return the createdTime
     */
    public String getCreatedTime() {
        return this.createdTime;
    }

    /**
     * set the createdTime - 新增时间
     */
    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * get the modifier - 修改人
     * @return the modifier
     */
    public String getModifier() {
        return this.modifier;
    }

    /**
     * set the modifier - 修改人
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    /**
     * get the updatedTime - 修改时间
     * @return the updatedTime
     */
    public String getUpdatedTime() {
        return this.updatedTime;
    }

    /**
     * set the updatedTime - 修改时间
     */
    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {
    	setMonitorid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("monitorid")), monitorid));
    	setMonitorname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("monitorname")), monitorname));
        setFactorid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("factorid")), factorid));
        setFactorname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("factorname")), factorname));
        setPortid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("portid")), portid));
        setHighlimit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("highlimit")), highlimit));
        setLowlimit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("lowlimit")), lowlimit));
        setNkhighlimit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("nkhighlimit")), nkhighlimit));
        setNklowlimit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("nklowlimit")), nklowlimit));
        setDescription(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("description")), description));
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
        map.put("monitorid", StringUtils.toString(monitorid, eiMetadata.getMeta("monitorid")));
        map.put("monitorname", StringUtils.toString(monitorname, eiMetadata.getMeta("monitorname")));
        map.put("factorid", StringUtils.toString(factorid, eiMetadata.getMeta("factorid")));
        map.put("factorname", StringUtils.toString(factorname, eiMetadata.getMeta("factorname")));
        map.put("portid", StringUtils.toString(portid, eiMetadata.getMeta("portid")));
        map.put("highlimit", StringUtils.toString(highlimit, eiMetadata.getMeta("highlimit")));
        map.put("lowlimit", StringUtils.toString(lowlimit, eiMetadata.getMeta("lowlimit")));
        map.put("nkhighlimit", StringUtils.toString(nkhighlimit, eiMetadata.getMeta("nkhighlimit")));
        map.put("nklowlimit", StringUtils.toString(nklowlimit, eiMetadata.getMeta("nklowlimit")));
        map.put("description", StringUtils.toString(description, eiMetadata.getMeta("description")));
        map.put("status", StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("creator", StringUtils.toString(creator, eiMetadata.getMeta("creator")));
        map.put("createdTime", StringUtils.toString(createdTime, eiMetadata.getMeta("createdTime")));
        map.put("modifier", StringUtils.toString(modifier, eiMetadata.getMeta("modifier")));
        map.put("updatedTime", StringUtils.toString(updatedTime, eiMetadata.getMeta("updatedTime")));
        return map;
    }
}