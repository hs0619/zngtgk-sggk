/**
* Generate time : 2020-09-07 21:55:08
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhb14
* 
*/
public class Tduhb14 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String dischargeportid = " ";		/* 排口编号*/
    private String dischargeportname = " ";		/* 排口名称*/
    private String isformal = " ";		/* 是否正式*/
    private String procid = " ";		/* 所属工序*/
    private String monitorid = " ";		/* 排口类型*/
    private String signform = " ";		/* 标志牌形式*/
    private String dischargemode = " ";		/* 排放方式*/
    private String longitude = " ";		/* 排放口经度*/
	private String latitude = " ";		/* 排放口纬度*/
    private String position = " ";		/* 所在位置*/
    private String executionstandard = " ";		/* 执行标准*/
    private String setright = " ";		/* 设置是否符合要求*/
    private String importantport = " ";		/* 排口重要分级*/
    private String ismap = " ";		/* 地图展示*/
    private String description = " ";		/* 备注*/
    private String outto = " ";		/* 排放去向废水*/
    private String outtype = " ";		/* 排放形式废水*/
    private String outrule = " ";		/* 排放规律废水*/
    private String countrypoint = " ";		/* 国控点*/
    private String citypoint = " ";		/* 市控点*/
    private String companypoint = " ";		/* 内控点*/
    private String cleanpoint = " ";		/* 超低排放点*/
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

        eiColumn = new EiColumn("dischargeportid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("排口编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dischargeportname");
        eiColumn.setDescName("排口名称");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("isformal");
        eiColumn.setDescName("是否正式");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("procid");
        eiColumn.setDescName("所属工序");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("monitorid");
        eiColumn.setDescName("排口类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("signform");
        eiColumn.setDescName("标志牌形式");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dischargemode");
        eiColumn.setDescName("排放方式");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("longitude");
        eiColumn.setDescName("排放口经度");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("latitude");
        eiColumn.setDescName("排放口纬度");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("position");
        eiColumn.setDescName("所在位置");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("executionstandard");
        eiColumn.setDescName("执行标准");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("setright");
        eiColumn.setDescName("设置是否符合要求");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("importantport");
        eiColumn.setDescName("排口重要分级");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("ismap");
        eiColumn.setDescName("地图展示");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("description");
        eiColumn.setDescName("备注");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outto");
        eiColumn.setDescName("排放去向废水");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outtype");
        eiColumn.setDescName("排放形式废水");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("outrule");
        eiColumn.setDescName("排放规律废水");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("countrypoint");
        eiColumn.setDescName("国控点");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("citypoint");
        eiColumn.setDescName("市控点");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("companypoint");
        eiColumn.setDescName("内控点");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("cleanpoint");
        eiColumn.setDescName("超低排放点");
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
    public Tduhb14() {
        initMetaData();
    }

    /**
     * get the dischargeportid - 排口编号
     * @return the dischargeportid
     */
    public String getDischargeportid() {
        return this.dischargeportid;
    }

    /**
     * set the dischargeportid - 排口编号
     */
    public void setDischargeportid(String dischargeportid) {
        this.dischargeportid = dischargeportid;
    }

    /**
     * get the dischargeportname - 排口名称
     * @return the dischargeportname
     */
    public String getDischargeportname() {
        return this.dischargeportname;
    }

    /**
     * set the dischargeportname - 排口名称
     */
    public void setDischargeportname(String dischargeportname) {
        this.dischargeportname = dischargeportname;
    }
    
    public String getIsformal() {
		return this.isformal;
	}

	public void setIsformal(String isformal) {
		this.isformal = isformal;
	}

    /**
     * get the procid - 所属工序
     * @return the procid
     */
    public String getProcid() {
        return this.procid;
    }

    /**
     * set the procid - 所属工序
     */
    public void setProcid(String procid) {
        this.procid = procid;
    }

    /**
     * get the monitorid - 排口类型
     * @return the monitorid
     */
    public String getMonitorid() {
        return this.monitorid;
    }

    /**
     * set the monitorid - 排口类型
     */
    public void setMonitorid(String monitorid) {
        this.monitorid = monitorid;
    }

    /**
     * get the signform - 标志牌形式
     * @return the signform
     */
    public String getSignform() {
        return this.signform;
    }

    /**
     * set the signform - 标志牌形式
     */
    public void setSignform(String signform) {
        this.signform = signform;
    }

    /**
     * get the dischargemode - 排放方式
     * @return the dischargemode
     */
    public String getDischargemode() {
        return this.dischargemode;
    }

    /**
     * set the dischargemode - 排放方式
     */
    public void setDischargemode(String dischargemode) {
        this.dischargemode = dischargemode;
    }

    /**
     * get the longitude - 排放口经度
     * @return the longitude
     */
    public String getLongitude() {
        return this.longitude;
    }

    /**
     * set the longitude - 排放口经度
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * get the latitude - 排放口纬度
     * @return the latitude
     */
    public String getLatitude() {
        return this.latitude;
    }

    /**
     * set the latitude - 排放口纬度
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * get the position - 所在位置
     * @return the position
     */
    public String getPosition() {
        return this.position;
    }

    /**
     * set the position - 所在位置
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * get the executionstandard - 执行标准
     * @return the executionstandard
     */
    public String getExecutionstandard() {
        return this.executionstandard;
    }

    /**
     * set the executionstandard - 执行标准
     */
    public void setExecutionstandard(String executionstandard) {
        this.executionstandard = executionstandard;
    }

    /**
     * get the setright - 设置是否符合要求
     * @return the setright
     */
    public String getSetright() {
        return this.setright;
    }

    /**
     * set the setright - 设置是否符合要求
     */
    public void setSetright(String setright) {
        this.setright = setright;
    }

    /**
     * get the importantport - 排口重要分级
     * @return the importantport
     */
    public String getImportantport() {
        return this.importantport;
    }

    /**
     * set the importantport - 排口重要分级
     */
    public void setImportantport(String importantport) {
        this.importantport = importantport;
    }

    /**
     * get the ismap - 地图展示
     * @return the ismap
     */
    public String getIsmap() {
        return this.ismap;
    }

    /**
     * set the ismap - 地图展示
     */
    public void setIsmap(String ismap) {
        this.ismap = ismap;
    }

    /**
     * get the description - 备注
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * set the description - 备注
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * get the outto - 排放去向废水
     * @return the outto
     */
    public String getOutto() {
        return this.outto;
    }

    /**
     * set the outto - 排放去向废水
     */
    public void setOutto(String outto) {
        this.outto = outto;
    }

    /**
     * get the outtype - 排放形式废水
     * @return the outtype
     */
    public String getOuttype() {
        return this.outtype;
    }

    /**
     * set the outtype - 排放形式废水
     */
    public void setOuttype(String outtype) {
        this.outtype = outtype;
    }

    /**
     * get the outrule - 排放规律废水
     * @return the outrule
     */
    public String getOutrule() {
        return this.outrule;
    }

    /**
     * set the outrule - 排放规律废水
     */
    public void setOutrule(String outrule) {
        this.outrule = outrule;
    }

    /**
     * get the countrypoint - 国控点
     * @return the countrypoint
     */
    public String getCountrypoint() {
        return this.countrypoint;
    }

    /**
     * set the countrypoint - 国控点
     */
    public void setCountrypoint(String countrypoint) {
        this.countrypoint = countrypoint;
    }

    /**
     * get the citypoint - 市控点
     * @return the citypoint
     */
    public String getCitypoint() {
        return this.citypoint;
    }

    /**
     * set the citypoint - 市控点
     */
    public void setCitypoint(String citypoint) {
        this.citypoint = citypoint;
    }

    /**
     * get the companypoint - 内控点
     * @return the companypoint
     */
    public String getCompanypoint() {
        return this.companypoint;
    }

    /**
     * set the companypoint - 内控点
     */
    public void setCompanypoint(String companypoint) {
        this.companypoint = companypoint;
    }

    /**
     * get the cleanpoint - 超低排放点
     * @return the cleanpoint
     */
    public String getCleanpoint() {
        return this.cleanpoint;
    }

    /**
     * set the cleanpoint - 超低排放点
     */
    public void setCleanpoint(String cleanpoint) {
        this.cleanpoint = cleanpoint;
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

        setDischargeportid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dischargeportid")), dischargeportid));
        setDischargeportname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dischargeportname")), dischargeportname));
        setIsformal(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isformal")), isformal));
        setProcid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("procid")), procid));
        setMonitorid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("monitorid")), monitorid));
        setSignform(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("signform")), signform));
        setDischargemode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dischargemode")), dischargemode));
        setLongitude(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("longitude")), longitude));
        setLatitude(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("latitude")), latitude));
        setPosition(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("position")), position));
        setExecutionstandard(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("executionstandard")), executionstandard));
        setSetright(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("setright")), setright));
        setImportantport(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("importantport")), importantport));
        setIsmap(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("ismap")), ismap));
        setDescription(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("description")), description));
        setOutto(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outto")), outto));
        setOuttype(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outtype")), outtype));
        setOutrule(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("outrule")), outrule));
        setCountrypoint(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("countrypoint")), countrypoint));
        setCitypoint(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("citypoint")), citypoint));
        setCompanypoint(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("companypoint")), companypoint));
        setCleanpoint(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("cleanpoint")), cleanpoint));
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
        map.put("dischargeportid", StringUtils.toString(dischargeportid, eiMetadata.getMeta("dischargeportid")));
        map.put("dischargeportname", StringUtils.toString(dischargeportname, eiMetadata.getMeta("dischargeportname")));
        map.put("isformal", StringUtils.toString(isformal, eiMetadata.getMeta("isformal")));
        map.put("procid", StringUtils.toString(procid, eiMetadata.getMeta("procid")));
        map.put("monitorid", StringUtils.toString(monitorid, eiMetadata.getMeta("monitorid")));
        map.put("signform", StringUtils.toString(signform, eiMetadata.getMeta("signform")));
        map.put("dischargemode", StringUtils.toString(dischargemode, eiMetadata.getMeta("dischargemode")));
        map.put("longitude", StringUtils.toString(longitude, eiMetadata.getMeta("longitude")));
        map.put("latitude", StringUtils.toString(latitude, eiMetadata.getMeta("latitude")));
        map.put("position", StringUtils.toString(position, eiMetadata.getMeta("position")));
        map.put("executionstandard", StringUtils.toString(executionstandard, eiMetadata.getMeta("executionstandard")));
        map.put("setright", StringUtils.toString(setright, eiMetadata.getMeta("setright")));
        map.put("importantport", StringUtils.toString(importantport, eiMetadata.getMeta("importantport")));
        map.put("ismap", StringUtils.toString(ismap, eiMetadata.getMeta("ismap")));
        map.put("description", StringUtils.toString(description, eiMetadata.getMeta("description")));
        map.put("outto", StringUtils.toString(outto, eiMetadata.getMeta("outto")));
        map.put("outtype", StringUtils.toString(outtype, eiMetadata.getMeta("outtype")));
        map.put("outrule", StringUtils.toString(outrule, eiMetadata.getMeta("outrule")));
        map.put("countrypoint", StringUtils.toString(countrypoint, eiMetadata.getMeta("countrypoint")));
        map.put("citypoint", StringUtils.toString(citypoint, eiMetadata.getMeta("citypoint")));
        map.put("companypoint", StringUtils.toString(companypoint, eiMetadata.getMeta("companypoint")));
        map.put("cleanpoint", StringUtils.toString(cleanpoint, eiMetadata.getMeta("cleanpoint")));
        map.put("status", StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("creator", StringUtils.toString(creator, eiMetadata.getMeta("creator")));
        map.put("createdTime", StringUtils.toString(createdTime, eiMetadata.getMeta("createdTime")));
        map.put("modifier", StringUtils.toString(modifier, eiMetadata.getMeta("modifier")));
        map.put("updatedTime", StringUtils.toString(updatedTime, eiMetadata.getMeta("updatedTime")));
        return map;
    }
}