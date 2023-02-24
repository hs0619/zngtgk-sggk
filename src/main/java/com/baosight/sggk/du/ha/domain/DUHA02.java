/**
 * Generate time : 2023-02-23 16:11:00
 * Version : 1.0
 */
package com.baosight.sggk.du.ha.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;

import java.util.HashMap;
import java.util.Map;

import com.baosight.iplat4j.core.util.StringUtils;

/**
 * DUHA02
 *
 */
public class DUHA02 extends DaoEPBase {

    private String logicid = " ";        /* 主键id*/
    private String typeid = " ";        /* 类型*/
    private String typename = " ";        /* 类型名称*/
    private String valuefield = " ";        /* 实际值*/
    private String textfield = " ";        /* 显示值*/
    private String status = " ";        /* 状态*/
    private String sort = " ";        /* 排序*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("logicid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("typeid");
        eiColumn.setDescName("类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("typename");
        eiColumn.setDescName("类型名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("valuefield");
        eiColumn.setDescName("实际值");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("textfield");
        eiColumn.setDescName("显示值");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("status");
        eiColumn.setDescName("状态");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sort");
        eiColumn.setDescName("排序");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DUHA02() {
        initMetaData();
    }

    /**
     * get the logicid - 主键id
     * @return the logicid
     */
    public String getLogicid() {
        return this.logicid;
    }

    /**
     * set the logicid - 主键id
     */
    public void setLogicid(String logicid) {
        this.logicid = logicid;
    }

    /**
     * get the typeid - 类型
     * @return the typeid
     */
    public String getTypeid() {
        return this.typeid;
    }

    /**
     * set the typeid - 类型
     */
    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    /**
     * get the typename - 类型名称
     * @return the typename
     */
    public String getTypename() {
        return this.typename;
    }

    /**
     * set the typename - 类型名称
     */
    public void setTypename(String typename) {
        this.typename = typename;
    }

    /**
     * get the valuefield - 实际值
     * @return the valuefield
     */
    public String getValuefield() {
        return this.valuefield;
    }

    /**
     * set the valuefield - 实际值
     */
    public void setValuefield(String valuefield) {
        this.valuefield = valuefield;
    }

    /**
     * get the textfield - 显示值
     * @return the textfield
     */
    public String getTextfield() {
        return this.textfield;
    }

    /**
     * set the textfield - 显示值
     */
    public void setTextfield(String textfield) {
        this.textfield = textfield;
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
     * get the sort - 排序
     * @return the sort
     */
    public String getSort() {
        return this.sort;
    }

    /**
     * set the sort - 排序
     */
    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setLogicid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("logicid")), logicid));
        setTypeid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typeid")), typeid));
        setTypename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typename")), typename));
        setValuefield(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("valuefield")), valuefield));
        setTextfield(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("textfield")), textfield));
        setStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("status")), status));
        setSort(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sort")), sort));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {

        Map map = new HashMap();
        map.put("logicid", StringUtils.toString(logicid, eiMetadata.getMeta("logicid")));
        map.put("typeid", StringUtils.toString(typeid, eiMetadata.getMeta("typeid")));
        map.put("typename", StringUtils.toString(typename, eiMetadata.getMeta("typename")));
        map.put("valuefield", StringUtils.toString(valuefield, eiMetadata.getMeta("valuefield")));
        map.put("textfield", StringUtils.toString(textfield, eiMetadata.getMeta("textfield")));
        map.put("status", StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("sort", StringUtils.toString(sort, eiMetadata.getMeta("sort")));

        return map;

    }
}