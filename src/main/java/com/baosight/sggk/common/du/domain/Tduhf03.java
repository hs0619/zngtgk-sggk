/**
* Generate time : 2021-03-10 15:16:20
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhf03
* 
*/
public class Tduhf03 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String departmentid = " ";		
    private String departmentName = " ";		
    private String parentid = " ";		
    private String env = " ";		
    private String envCoef = " ";		
    private String envWeight = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("departmentid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("departmentName");
        eiColumn.setFieldLength(200);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("parentid");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("env");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("envCoef");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("envWeight");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public Tduhf03() {
        initMetaData();
    }

    /**
     * get the departmentId 
     * @return the departmentId
     */
    public String getDepartmentId() {
        return this.departmentid;
    }

    /**
     * set the departmentId 
     */
    public void setDepartmentId(String departmentid) {
        this.departmentid = departmentid;
    }

    /**
     * get the departmentName 
     * @return the departmentName
     */
    public String getDepartmentName() {
        return this.departmentName;
    }

    /**
     * set the departmentName 
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * get the parentid 
     * @return the parentid
     */
    public String getParentid() {
        return this.parentid;
    }

    /**
     * set the parentid 
     */
    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    /**
     * get the env 
     * @return the env
     */
    public String getEnv() {
        return this.env;
    }

    /**
     * set the env 
     */
    public void setEnv(String env) {
        this.env = env;
    }

    /**
     * get the envCoef 
     * @return the envCoef
     */
    public String getEnvCoef() {
        return this.envCoef;
    }

    /**
     * set the envCoef 
     */
    public void setEnvCoef(String envCoef) {
        this.envCoef = envCoef;
    }

    /**
     * get the envWeight 
     * @return the envWeight
     */
    public String getEnvWeight() {
        return this.envWeight;
    }

    /**
     * set the envWeight 
     */
    public void setEnvWeight(String envWeight) {
        this.envWeight = envWeight;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setDepartmentId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentid")), departmentid));
        setDepartmentName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentName")), departmentName));
        setParentid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("parentid")), parentid));
        setEnv(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("env")), env));
        setEnvCoef(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("envCoef")), envCoef));
        setEnvWeight(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("envWeight")), envWeight));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("departmentid", StringUtils.toString(departmentid, eiMetadata.getMeta("departmentid")));
        map.put("departmentName", StringUtils.toString(departmentName, eiMetadata.getMeta("departmentName")));
        map.put("parentid", StringUtils.toString(parentid, eiMetadata.getMeta("parentid")));
        map.put("env", StringUtils.toString(env, eiMetadata.getMeta("env")));
        map.put("envCoef", StringUtils.toString(envCoef, eiMetadata.getMeta("envCoef")));
        map.put("envWeight", StringUtils.toString(envWeight, eiMetadata.getMeta("envWeight")));
        return map;
    }
}