/**
 * Generate time : 2023-02-28 11:14:45
 * Version : 1.0
 */
package com.baosight.sggk.du.hd.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;

import java.util.HashMap;
import java.util.Map;

import com.baosight.iplat4j.core.util.StringUtils;

/**
 * THdFacilityinfo
 *
 */
public class DUHD10 extends DaoEPBase {

    private String facilityid = " ";        /* 主键ID*/
    private String djRatedpower = " ";        /* 电机额定功率（kw）*/
    private String ratedpower = " ";        /* 额度功率（kw）*/
    private String frequency = " ";        /* 变频(hz)*/
    private String capacity = " ";        /* 脱除能力(吨/年)、处理能力(万立或万吨)、处理量(万立/时)*/
    private String technique = " ";        /* 治理方式*/
    private String spec = " ";        /* 烧结机规格（m²）*/
    private String height = " ";        /* 焦炉炭化室高度*/
    private String hole = " ";        /* 焦炉孔数*/
    private String volume = " ";        /* 高炉有效容积(m³)*/
    private String weight = " ";        /* 转炉公称容量(吨)*/
    private String variety = " ";        /* 轧机品种及规格*/
    private String istl = " ";        /* 是否脱硫   01：是   02：否*/
    private String istx = " ";        /* 是否脱销    01：是   02：否*/

    private String facilitycode = " ";
    private String facilityname = " ";
    private String facilitytype = " ";
    private String departmentid = " ";
    private String procedureid = " ";

    private String isupdate = " ";

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("facilityid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("djRatedpower");
        eiColumn.setDescName("电机额定功率（kw）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("ratedpower");
        eiColumn.setDescName("额度功率（kw）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("frequency");
        eiColumn.setDescName("变频(hz)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("capacity");
        eiColumn.setDescName("脱除能力(吨/年)、处理能力(万立或万吨)、处理量(万立/时)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("technique");
        eiColumn.setDescName("治理方式");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("spec");
        eiColumn.setDescName("烧结机规格（m²）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("height");
        eiColumn.setDescName("焦炉炭化室高度");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("hole");
        eiColumn.setDescName("焦炉孔数");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("volume");
        eiColumn.setDescName("高炉有效容积(m³)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("weight");
        eiColumn.setDescName("转炉公称容量(吨)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("variety");
        eiColumn.setDescName("轧机品种及规格");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("istl");
        eiColumn.setDescName("是否脱硫   01：是   02：否");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("istx");
        eiColumn.setDescName("是否脱销    01：是   02：否");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("facilitycode");
        eiColumn.setDescName("设施编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("facilityname");
        eiColumn.setDescName("设施名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("facilitytype");
        eiColumn.setDescName("设施类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("departmentid");
        eiColumn.setDescName("厂部");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("procedureid");
        eiColumn.setDescName("工序");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isupdate");
        eiColumn.setDescName("是否修改");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public DUHD10() {
        initMetaData();
    }

    /**
     * get the facilityid - 主键ID
     * @return the facilityid
     */
    public String getFacilityid() {
        return this.facilityid;
    }

    /**
     * set the facilityid - 主键ID
     */
    public void setFacilityid(String facilityid) {
        this.facilityid = facilityid;
    }

    /**
     * get the djRatedpower - 电机额定功率（kw）
     * @return the djRatedpower
     */
    public String getDjRatedpower() {
        return this.djRatedpower;
    }

    /**
     * set the djRatedpower - 电机额定功率（kw）
     */
    public void setDjRatedpower(String djRatedpower) {
        this.djRatedpower = djRatedpower;
    }

    /**
     * get the ratedpower - 额度功率（kw）
     * @return the ratedpower
     */
    public String getRatedpower() {
        return this.ratedpower;
    }

    /**
     * set the ratedpower - 额度功率（kw）
     */
    public void setRatedpower(String ratedpower) {
        this.ratedpower = ratedpower;
    }

    /**
     * get the frequency - 变频(hz)
     * @return the frequency
     */
    public String getFrequency() {
        return this.frequency;
    }

    /**
     * set the frequency - 变频(hz)
     */
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    /**
     * get the capacity - 脱除能力(吨/年)、处理能力(万立或万吨)、处理量(万立/时)
     * @return the capacity
     */
    public String getCapacity() {
        return this.capacity;
    }

    /**
     * set the capacity - 脱除能力(吨/年)、处理能力(万立或万吨)、处理量(万立/时)
     */
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    /**
     * get the technique - 治理方式
     * @return the technique
     */
    public String getTechnique() {
        return this.technique;
    }

    /**
     * set the technique - 治理方式
     */
    public void setTechnique(String technique) {
        this.technique = technique;
    }

    /**
     * get the spec - 烧结机规格（m²）
     * @return the spec
     */
    public String getSpec() {
        return this.spec;
    }

    /**
     * set the spec - 烧结机规格（m²）
     */
    public void setSpec(String spec) {
        this.spec = spec;
    }

    /**
     * get the height - 焦炉炭化室高度
     * @return the height
     */
    public String getHeight() {
        return this.height;
    }

    /**
     * set the height - 焦炉炭化室高度
     */
    public void setHeight(String height) {
        this.height = height;
    }

    /**
     * get the hole - 焦炉孔数
     * @return the hole
     */
    public String getHole() {
        return this.hole;
    }

    /**
     * set the hole - 焦炉孔数
     */
    public void setHole(String hole) {
        this.hole = hole;
    }

    /**
     * get the volume - 高炉有效容积(m³)
     * @return the volume
     */
    public String getVolume() {
        return this.volume;
    }

    /**
     * set the volume - 高炉有效容积(m³)
     */
    public void setVolume(String volume) {
        this.volume = volume;
    }

    /**
     * get the weight - 转炉公称容量(吨)
     * @return the weight
     */
    public String getWeight() {
        return this.weight;
    }

    /**
     * set the weight - 转炉公称容量(吨)
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     * get the variety - 轧机品种及规格
     * @return the variety
     */
    public String getVariety() {
        return this.variety;
    }

    /**
     * set the variety - 轧机品种及规格
     */
    public void setVariety(String variety) {
        this.variety = variety;
    }

    /**
     * get the istl - 是否脱硫   01：是   02：否
     * @return the istl
     */
    public String getIstl() {
        return this.istl;
    }

    /**
     * set the istl - 是否脱硫   01：是   02：否
     */
    public void setIstl(String istl) {
        this.istl = istl;
    }

    /**
     * get the istx - 是否脱销    01：是   02：否
     * @return the istx
     */
    public String getIstx() {
        return this.istx;
    }

    /**
     * set the istx - 是否脱销    01：是   02：否
     */
    public void setIstx(String istx) {
        this.istx = istx;
    }

    public String getFacilitycode() {
        return facilitycode;
    }

    public void setFacilitycode(String facilitycode) {
        this.facilitycode = facilitycode;
    }

    public String getFacilityname() {
        return facilityname;
    }

    public void setFacilityname(String facilityname) {
        this.facilityname = facilityname;
    }

    public String getFacilitytype() {
        return facilitytype;
    }

    public void setFacilitytype(String facilitytype) {
        this.facilitytype = facilitytype;
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    public String getProcedureid() {
        return procedureid;
    }

    public void setProcedureid(String procedureid) {
        this.procedureid = procedureid;
    }

    public String getIsupdate() {
        return isupdate;
    }

    public void setIsupdate(String isupdate) {
        this.isupdate = isupdate;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setFacilityid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("facilityid")), facilityid));
        setDjRatedpower(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("djRatedpower")), djRatedpower));
        setRatedpower(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("ratedpower")), ratedpower));
        setFrequency(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("frequency")), frequency));
        setCapacity(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("capacity")), capacity));
        setTechnique(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("technique")), technique));
        setSpec(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("spec")), spec));
        setHeight(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("height")), height));
        setHole(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("hole")), hole));
        setVolume(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("volume")), volume));
        setWeight(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("weight")), weight));
        setVariety(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("variety")), variety));
        setIstl(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("istl")), istl));
        setIstx(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("istx")), istx));

        setFacilitycode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("facilitycode")), facilitycode));
        setFacilityname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("facilityname")), facilityname));
        setFacilitytype(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("facilitytype")), facilitytype));
        setDepartmentid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentid")), departmentid));
        setProcedureid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("procedureid")), procedureid));

        setIsupdate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isupdate")), isupdate));

    }

    /**
     * set the value to Map
     */
    public Map toMap() {

        Map map = new HashMap();
        map.put("facilityid", StringUtils.toString(facilityid, eiMetadata.getMeta("facilityid")));
        map.put("djRatedpower", StringUtils.toString(djRatedpower, eiMetadata.getMeta("djRatedpower")));
        map.put("ratedpower", StringUtils.toString(ratedpower, eiMetadata.getMeta("ratedpower")));
        map.put("frequency", StringUtils.toString(frequency, eiMetadata.getMeta("frequency")));
        map.put("capacity", StringUtils.toString(capacity, eiMetadata.getMeta("capacity")));
        map.put("technique", StringUtils.toString(technique, eiMetadata.getMeta("technique")));
        map.put("spec", StringUtils.toString(spec, eiMetadata.getMeta("spec")));
        map.put("height", StringUtils.toString(height, eiMetadata.getMeta("height")));
        map.put("hole", StringUtils.toString(hole, eiMetadata.getMeta("hole")));
        map.put("volume", StringUtils.toString(volume, eiMetadata.getMeta("volume")));
        map.put("weight", StringUtils.toString(weight, eiMetadata.getMeta("weight")));
        map.put("variety", StringUtils.toString(variety, eiMetadata.getMeta("variety")));
        map.put("istl", StringUtils.toString(istl, eiMetadata.getMeta("istl")));
        map.put("istx", StringUtils.toString(istx, eiMetadata.getMeta("istx")));

        map.put("facilitycode", StringUtils.toString(facilitycode, eiMetadata.getMeta("facilitycode")));
        map.put("facilityname", StringUtils.toString(facilityname, eiMetadata.getMeta("facilityname")));
        map.put("facilitytype", StringUtils.toString(facilitytype, eiMetadata.getMeta("facilitytype")));
        map.put("departmentid", StringUtils.toString(departmentid, eiMetadata.getMeta("departmentid")));
        map.put("procedureid", StringUtils.toString(procedureid, eiMetadata.getMeta("procedureid")));

        map.put("isupdate", StringUtils.toString(isupdate, eiMetadata.getMeta("isupdate")));



        return map;

    }
}