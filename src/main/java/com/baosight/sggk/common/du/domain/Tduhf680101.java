/**
* Generate time : 2021-04-15 16:47:06
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhf680101
* 
*/
public class Tduhf680101 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String siteid = " ";		
    private String type = " ";		
    private String content = " ";		
    private String reason = " ";		
    private String measure = " ";		
    private String result = " ";		
    private String userid = " ";		
    private String begintime = " ";		
    private String endtime = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("siteid");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("type");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("content");
        eiColumn.setFieldLength(300);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("reason");
        eiColumn.setFieldLength(300);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("measure");
        eiColumn.setFieldLength(300);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("result");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("userid");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("begintime");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("endtime");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public Tduhf680101() {
        initMetaData();
    }

    /**
     * get the siteid 
     * @return the siteid
     */
    public String getSiteid() {
        return this.siteid;
    }

    /**
     * set the siteid 
     */
    public void setSiteid(String siteid) {
        this.siteid = siteid;
    }

    /**
     * get the type 
     * @return the type
     */
    public String getType() {
        return this.type;
    }

    /**
     * set the type 
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * get the content 
     * @return the content
     */
    public String getContent() {
        return this.content;
    }

    /**
     * set the content 
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * get the reason 
     * @return the reason
     */
    public String getReason() {
        return this.reason;
    }

    /**
     * set the reason 
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * get the measure 
     * @return the measure
     */
    public String getMeasure() {
        return this.measure;
    }

    /**
     * set the measure 
     */
    public void setMeasure(String measure) {
        this.measure = measure;
    }

    /**
     * get the result 
     * @return the result
     */
    public String getResult() {
        return this.result;
    }

    /**
     * set the result 
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * get the userid 
     * @return the userid
     */
    public String getUserid() {
        return this.userid;
    }

    /**
     * set the userid 
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * get the begintime 
     * @return the begintime
     */
    public String getBegintime() {
        return this.begintime;
    }

    /**
     * set the begintime 
     */
    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    /**
     * get the endtime 
     * @return the endtime
     */
    public String getEndtime() {
        return this.endtime;
    }

    /**
     * set the endtime 
     */
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setSiteid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("siteid")), siteid));
        setType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("type")), type));
        setContent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("content")), content));
        setReason(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("reason")), reason));
        setMeasure(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("measure")), measure));
        setResult(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("result")), result));
        setUserid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("userid")), userid));
        setBegintime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("begintime")), begintime));
        setEndtime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("endtime")), endtime));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("siteid", StringUtils.toString(siteid, eiMetadata.getMeta("siteid")));
        map.put("type", StringUtils.toString(type, eiMetadata.getMeta("type")));
        map.put("content", StringUtils.toString(content, eiMetadata.getMeta("content")));
        map.put("reason", StringUtils.toString(reason, eiMetadata.getMeta("reason")));
        map.put("measure", StringUtils.toString(measure, eiMetadata.getMeta("measure")));
        map.put("result", StringUtils.toString(result, eiMetadata.getMeta("result")));
        map.put("userid", StringUtils.toString(userid, eiMetadata.getMeta("userid")));
        map.put("begintime", StringUtils.toString(begintime, eiMetadata.getMeta("begintime")));
        map.put("endtime", StringUtils.toString(endtime, eiMetadata.getMeta("endtime")));
        return map;
    }
}