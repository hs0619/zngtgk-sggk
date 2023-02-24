/**
* Generate time : 2021-07-14 16:17:01
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.du.hf.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* DUHF7102
* 
*/
public class DUHF7102 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String typeid = " ";		/* 类型id*/
    private String typename = " ";		/* 类型名称*/
    private String sort = " ";		/* 排序*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("typeid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(36);
        eiColumn.setDescName("类型id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("typename");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("类型名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sort");
        eiColumn.setFieldLength(2);
        eiColumn.setDescName("排序");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public DUHF7102() {
        initMetaData();
    }

    /**
     * get the typeid - 类型id
     * @return the typeid
     */
    public String getTypeid() {
        return this.typeid;
    }

    /**
     * set the typeid - 类型id
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

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setTypeid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typeid")), typeid));
        setTypename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typename")), typename));
        setSort(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sort")), sort));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("typeid", StringUtils.toString(typeid, eiMetadata.getMeta("typeid")));
        map.put("typename", StringUtils.toString(typename, eiMetadata.getMeta("typename")));
        map.put("sort", StringUtils.toString(sort, eiMetadata.getMeta("sort")));
        return map;
    }
}