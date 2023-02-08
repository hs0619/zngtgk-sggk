/**
* Generate time : 2021-04-25 10:11:17
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.du.hb.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* DUHB28
* 
*/
public class DUHB28 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String rid = " ";		/* 唯一ID*/
    private String userId = " ";		/* 系统账户*/
    private String departmentId = " ";		/* 部门编号*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("rid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(20);
        eiColumn.setDescName("唯一ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("userId");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("系统账户");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("departmentId");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName("部门编号");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DUHB28() {
        initMetaData();
    }

    /**
     * get the rid - 唯一ID
     * @return the rid
     */
    public String getRid() {
        return this.rid;
    }

    /**
     * set the rid - 唯一ID
     */
    public void setRid(String rid) {
        this.rid = rid;
    }

    /**
     * get the userId - 系统账户
     * @return the userId
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * set the userId - 系统账户
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * get the departmentId - 部门编号
     * @return the departmentId
     */
    public String getDepartmentId() {
        return this.departmentId;
    }

    /**
     * set the departmentId - 部门编号
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setRid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rid")), rid));
        setUserId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("userId")), userId));
        setDepartmentId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentId")), departmentId));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("rid",StringUtils.toString(rid, eiMetadata.getMeta("rid")));
        map.put("userId",StringUtils.toString(userId, eiMetadata.getMeta("userId")));
        map.put("departmentId",StringUtils.toString(departmentId, eiMetadata.getMeta("departmentId")));
        return map;
    }
}