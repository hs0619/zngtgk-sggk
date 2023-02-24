/**
* Generate time : 2021-07-06 9:45:59
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.du.hf.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* DUHF71
* 
*/
public class DUHF71 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String radiateid = " ";		/* 放射源ID*/
    private String radiatename = " ";		/* 放射源装置名称*/
    private String radiatetype = " ";		/* 类型*/
    private String status = " ";		/* 状态（0，暂存，1提交）*/
    private String departmentid = " ";		/* 单位*/
    private String nuclide = " ";		/* 核素*/
    private String number = " ";		/* 数量*/
    private String comedate = " ";		/* 出厂日期*/
    private String comeactivity = " ";		/* 出厂活度*/
    private String radiatecode = " ";		/* 编码*/
    private String iswork = " ";		/* 是否在岗（0离岗，1在岗）*/
    private String purpose = " ";		/* 用途*/
    private String address = " ";		/* 使用位置*/
    private String supplier = " ";		/* 供应商*/
    private String prounit = " ";		/* 生产单位*/
    private String recorddate = " ";		/* 登记日期*/
    private String recordid = " ";		/* 登记人*/
    private String recordname = " ";		/* 登记人*/
    private String remark = " ";		/* 备注*/
    private String updatedman = " ";		/* 更新人*/
    private String updatedtime = " ";		/* 更新时间*/


    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("radiateid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(36);
        eiColumn.setDescName("放射源ID");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("radiatename");
        eiColumn.setFieldLength(36);
        eiColumn.setDescName("放射源装置名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("departmentid");
        eiColumn.setFieldLength(36);
        eiColumn.setDescName("单位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("number");
        eiColumn.setFieldLength(128);
        eiColumn.setDescName("装置数量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("nuclide");
        eiColumn.setFieldLength(128);
        eiColumn.setDescName("核素");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("comedate");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("出厂日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("comeactivity");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("出厂活度");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("radiatecode");
        eiColumn.setFieldLength(64);
        eiColumn.setDescName("编码");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("radiatetype");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("purpose");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("用途");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("address");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("使用位置");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("supplier");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("供应商");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("prounit");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("生产单位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recorddate");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("登记日期");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recordid");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("登记人工号");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("recordname");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("登记人名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remark");
        eiColumn.setFieldLength(300);
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("updatedman");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("更新人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updatedtime");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("更新时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("status");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("状态");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("iswork");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("是否在岗");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public DUHF71() {
        initMetaData();
    }

    /**
     * get the radiateid - 放射源ID
     * @return the radiateid
     */
    public String getRadiateid() {
        return this.radiateid;
    }

    /**
     * set the radiateid - 放射源ID
     */
    public void setRadiateid(String radiateid) {
        this.radiateid = radiateid;
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


    public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}


    /**
     * get the nuclide - 核素
     * @return the nuclide
     */
    public String getNuclide() {
        return this.nuclide;
    }

    /**
     * set the nuclide - 核素
     */
    public void setNuclide(String nuclide) {
        this.nuclide = nuclide;
    }

    /**
     * get the comedate - 出厂日期
     * @return the comedate
     */
    public String getComedate() {
        return this.comedate;
    }

    /**
     * set the comedate - 出厂日期
     */
    public void setComedate(String comedate) {
        this.comedate = comedate;
    }

    /**
     * get the comeactivity - 出厂活度
     * @return the comeactivity
     */
    public String getComeactivity() {
        return this.comeactivity;
    }

    /**
     * set the comeactivity - 出厂活度
     */
    public void setComeactivity(String comeactivity) {
        this.comeactivity = comeactivity;
    }

    /**
     * get the radiatecode - 编码
     * @return the radiatecode
     */
    public String getRadiatecode() {
        return this.radiatecode;
    }

    /**
     * set the radiatecode - 编码
     */
    public void setRadiatecode(String radiatecode) {
        this.radiatecode = radiatecode;
    }

    /**
     * get the radiatetype - 类型
     * @return the radiatetype
     */
    public String getRadiatetype() {
        return this.radiatetype;
    }

    /**
     * set the radiatetype - 类型
     */
    public void setRadiatetype(String radiatetype) {
        this.radiatetype = radiatetype;
    }

    /**
     * get the purpose - 用途
     * @return the purpose
     */
    public String getPurpose() {
        return this.purpose;
    }

    /**
     * set the purpose - 用途
     */
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    /**
     * get the address - 场所
     * @return the address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * set the address - 场所
     */
    public void setAddress(String address) {
        this.address = address;
    }


    /**
     * get the recorddate - 登记日期
     * @return the recorddate
     */
    public String getRecorddate() {
        return this.recorddate;
    }

    /**
     * set the recorddate - 登记日期
     */
    public void setRecorddate(String recorddate) {
        this.recorddate = recorddate;
    }

    /**
     * get the recordname - 登记人
     * @return the recordname
     */
    public String getRecordname() {
        return this.recordname;
    }

    /**
     * set the recordname - 登记人
     */
    public void setRecordname(String recordname) {
        this.recordname = recordname;
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


    public String getRecordid() {
		return recordid;
	}

	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}

    public String getRadiatename() {
        return radiatename;
    }

    public void setRadiatename(String radiatename) {
        this.radiatename = radiatename;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIswork() {
        return iswork;
    }

    public void setIswork(String iswork) {
        this.iswork = iswork;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getProunit() {
        return prounit;
    }

    public void setProunit(String prounit) {
        this.prounit = prounit;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setRadiateid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("radiateid")), radiateid));
        setDepartmentid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentid")), departmentid));
        setNuclide(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("nuclide")), nuclide));
        setComedate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("comedate")), comedate));
        setComeactivity(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("comeactivity")), comeactivity));
        setRadiatecode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("radiatecode")), radiatecode));
        setRadiatetype(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("radiatetype")), radiatetype));
        setPurpose(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("purpose")), purpose));
        setAddress(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("address")), address));
        setRecorddate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recorddate")), recorddate));
        setRecordid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recordid")), recordid));
        setRecordname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recordname")), recordname));
        setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
        setUpdatedman(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updatedman")), updatedman));
        setUpdatedtime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updatedtime")), updatedtime));
        
        setStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("status")), status));

        setRadiatename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("radiatename")), radiatename));
        setNumber(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("number")), number));
        setSupplier(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("supplier")), supplier));
        setProunit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("prounit")), prounit));
        setIswork(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("iswork")), iswork));

    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("radiateid", StringUtils.toString(radiateid, eiMetadata.getMeta("radiateid")));
        map.put("departmentid", StringUtils.toString(departmentid, eiMetadata.getMeta("departmentid")));
        map.put("nuclide", StringUtils.toString(nuclide, eiMetadata.getMeta("nuclide")));
        map.put("comedate", StringUtils.toString(comedate, eiMetadata.getMeta("comedate")));
        map.put("comeactivity", StringUtils.toString(comeactivity, eiMetadata.getMeta("comeactivity")));
        map.put("radiatecode", StringUtils.toString(radiatecode, eiMetadata.getMeta("radiatecode")));
        map.put("radiatetype", StringUtils.toString(radiatetype, eiMetadata.getMeta("radiatetype")));
        map.put("purpose", StringUtils.toString(purpose, eiMetadata.getMeta("purpose")));
        map.put("address", StringUtils.toString(address, eiMetadata.getMeta("address")));
        map.put("recorddate", StringUtils.toString(recorddate, eiMetadata.getMeta("recorddate")));
        map.put("recordid", StringUtils.toString(recordid, eiMetadata.getMeta("recordid")));
        map.put("recordname", StringUtils.toString(recordname, eiMetadata.getMeta("recordname")));
        map.put("remark", StringUtils.toString(remark, eiMetadata.getMeta("remark")));
        map.put("updatedman", StringUtils.toString(updatedman, eiMetadata.getMeta("updatedman")));
        map.put("updatedtime", StringUtils.toString(updatedtime, eiMetadata.getMeta("updatedtime")));
        
        map.put("status", StringUtils.toString(status, eiMetadata.getMeta("status")));

        map.put("radiatename", StringUtils.toString(radiatename, eiMetadata.getMeta("radiatename")));
        map.put("number", StringUtils.toString(number, eiMetadata.getMeta("number")));
        map.put("supplier", StringUtils.toString(supplier, eiMetadata.getMeta("supplier")));
        map.put("prounit", StringUtils.toString(prounit, eiMetadata.getMeta("prounit")));
        map.put("iswork", StringUtils.toString(iswork, eiMetadata.getMeta("iswork")));

        
        return map;
    }
}