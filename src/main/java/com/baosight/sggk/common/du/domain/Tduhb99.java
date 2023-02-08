/**
* Generate time : 2021-10-20 18:57:00
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhb99
* 
*/
public class Tduhb99 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String messageId = " ";		
    private String messageType = " ";		
    private String jumpPage = " ";		
    private String title = " ";		
    private String content = " ";		
    private String pushTime = " ";		
    private String remarks = " ";		
    private String status = " ";		
    private String groupName = " ";		
    private String userId = " ";		
    private String creator = " ";		
    private String createdTime = " ";		
    private String solvePerson = " ";		
    private String solvedTime = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("messageId");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("messageType");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("jumpPage");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("title");
        eiColumn.setFieldLength(200);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("content");
        eiColumn.setFieldLength(500);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("pushTime");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("remarks");
        eiColumn.setFieldLength(200);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("status");
        eiColumn.setFieldLength(5);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("groupName");
        eiColumn.setFieldLength(200);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("userId");
        eiColumn.setFieldLength(200);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creator");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createdTime");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("solvePerson");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("solvedTime");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public Tduhb99() {
        initMetaData();
    }

    /**
     * get the messageId 
     * @return the messageId
     */
    public String getMessageId() {
        return this.messageId;
    }

    /**
     * set the messageId 
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    /**
     * get the messageType 
     * @return the messageType
     */
    public String getMessageType() {
        return this.messageType;
    }

    /**
     * set the messageType 
     */
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    /**
     * get the jumpPage 
     * @return the jumpPage
     */
    public String getJumpPage() {
        return this.jumpPage;
    }

    /**
     * set the jumpPage 
     */
    public void setJumpPage(String jumpPage) {
        this.jumpPage = jumpPage;
    }

    /**
     * get the title 
     * @return the title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * set the title 
     */
    public void setTitle(String title) {
        this.title = title;
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
     * get the pushTime 
     * @return the pushTime
     */
    public String getPushTime() {
        return this.pushTime;
    }

    /**
     * set the pushTime 
     */
    public void setPushTime(String pushTime) {
        this.pushTime = pushTime;
    }

    /**
     * get the remarks 
     * @return the remarks
     */
    public String getRemarks() {
        return this.remarks;
    }

    /**
     * set the remarks 
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * get the status 
     * @return the status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * set the status 
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * get the groupName 
     * @return the groupName
     */
    public String getGroupName() {
        return this.groupName;
    }

    /**
     * set the groupName 
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * get the userId 
     * @return the userId
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * set the userId 
     */
    public void setUserId(String userId) {
        this.userId = userId;
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
     * get the solvePerson 
     * @return the solvePerson
     */
    public String getSolvePerson() {
        return this.solvePerson;
    }

    /**
     * set the solvePerson 
     */
    public void setSolvePerson(String solvePerson) {
        this.solvePerson = solvePerson;
    }

    /**
     * get the solvedTime 
     * @return the solvedTime
     */
    public String getSolvedTime() {
        return this.solvedTime;
    }

    /**
     * set the solvedTime 
     */
    public void setSolvedTime(String solvedTime) {
        this.solvedTime = solvedTime;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setMessageId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("messageId")), messageId));
        setMessageType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("messageType")), messageType));
        setJumpPage(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("jumpPage")), jumpPage));
        setTitle(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("title")), title));
        setContent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("content")), content));
        setPushTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("pushTime")), pushTime));
        setRemarks(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remarks")), remarks));
        setStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("status")), status));
        setGroupName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("groupName")), groupName));
        setUserId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("userId")), userId));
        setCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creator")), creator));
        setCreatedTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createdTime")), createdTime));
        setSolvePerson(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("solvePerson")), solvePerson));
        setSolvedTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("solvedTime")), solvedTime));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("messageId", StringUtils.toString(messageId, eiMetadata.getMeta("messageId")));
        map.put("messageType", StringUtils.toString(messageType, eiMetadata.getMeta("messageType")));
        map.put("jumpPage", StringUtils.toString(jumpPage, eiMetadata.getMeta("jumpPage")));
        map.put("title", StringUtils.toString(title, eiMetadata.getMeta("title")));
        map.put("content", StringUtils.toString(content, eiMetadata.getMeta("content")));
        map.put("pushTime", StringUtils.toString(pushTime, eiMetadata.getMeta("pushTime")));
        map.put("remarks", StringUtils.toString(remarks, eiMetadata.getMeta("remarks")));
        map.put("status", StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("groupName", StringUtils.toString(groupName, eiMetadata.getMeta("groupName")));
        map.put("userId", StringUtils.toString(userId, eiMetadata.getMeta("userId")));
        map.put("creator", StringUtils.toString(creator, eiMetadata.getMeta("creator")));
        map.put("createdTime", StringUtils.toString(createdTime, eiMetadata.getMeta("createdTime")));
        map.put("solvePerson", StringUtils.toString(solvePerson, eiMetadata.getMeta("solvePerson")));
        map.put("solvedTime", StringUtils.toString(solvedTime, eiMetadata.getMeta("solvedTime")));
        return map;
    }
}