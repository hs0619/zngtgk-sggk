/**
* Generate time : 2021-12-23 14:29:39
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduha03
* 
*/
public class Tduha03 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String memberId = " ";		/* 成员ID*/
    private String parentId = " ";		/* 父节点ID*/
    private String memberType = " ";		/* 授权类别:USER,GROUP*/
    private Long sortIndex = new Long(0);	/* 排序*/
    private String path = " ";		/* 来源*/
    private String recCreator = " ";		/* 创建人*/
    private String recCreateTime = " ";		/* 创建时间*/
    private String recRevisor = " ";		/* 修改人*/
    private String recReviseTime = " ";		/* 修改时间*/
    private String archiveFlag = " ";		/* 归档标记*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("memberId");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("成员ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("parentId");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("父节点ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("memberType");
        eiColumn.setFieldLength(16);
        eiColumn.setDescName("授权类别:USER,GROUP");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sortIndex");
        eiColumn.setType("N");
        eiColumn.setScaleLength(0);
        eiColumn.setFieldLength(11);
        eiColumn.setDescName("排序");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("path");
        eiColumn.setFieldLength(255);
        eiColumn.setDescName("来源");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("创建人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setFieldLength(14);
        eiColumn.setDescName("创建时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("修改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setFieldLength(14);
        eiColumn.setDescName("修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setFieldLength(1);
        eiColumn.setDescName("归档标记");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public Tduha03() {
        initMetaData();
    }

    /**
     * get the memberId - 成员ID
     * @return the memberId
     */
    public String getMemberId() {
        return this.memberId;
    }

    /**
     * set the memberId - 成员ID
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    /**
     * get the parentId - 父节点ID
     * @return the parentId
     */
    public String getParentId() {
        return this.parentId;
    }

    /**
     * set the parentId - 父节点ID
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * get the memberType - 授权类别:USER,GROUP
     * @return the memberType
     */
    public String getMemberType() {
        return this.memberType;
    }

    /**
     * set the memberType - 授权类别:USER,GROUP
     */
    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    /**
     * get the sortIndex - 排序
     * @return the sortIndex
     */
    public Long getSortIndex() {
        return this.sortIndex;
    }

    /**
     * set the sortIndex - 排序
     */
    public void setSortIndex(Long sortIndex) {
        this.sortIndex = sortIndex;
    }

    /**
     * get the path - 来源
     * @return the path
     */
    public String getPath() {
        return this.path;
    }

    /**
     * set the path - 来源
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * get the recCreator - 创建人
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator - 创建人
     */
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recCreateTime - 创建时间
     * @return the recCreateTime
     */
    public String getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime - 创建时间
     */
    public void setRecCreateTime(String recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    /**
     * get the recRevisor - 修改人
     * @return the recRevisor
     */
    public String getRecRevisor() {
        return this.recRevisor;
    }

    /**
     * set the recRevisor - 修改人
     */
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
    }

    /**
     * get the recReviseTime - 修改时间
     * @return the recReviseTime
     */
    public String getRecReviseTime() {
        return this.recReviseTime;
    }

    /**
     * set the recReviseTime - 修改时间
     */
    public void setRecReviseTime(String recReviseTime) {
        this.recReviseTime = recReviseTime;
    }

    /**
     * get the archiveFlag - 归档标记
     * @return the archiveFlag
     */
    public String getArchiveFlag() {
        return this.archiveFlag;
    }

    /**
     * set the archiveFlag - 归档标记
     */
    public void setArchiveFlag(String archiveFlag) {
        this.archiveFlag = archiveFlag;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setMemberId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("memberId")), memberId));
        setParentId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("parentId")), parentId));
        setMemberType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("memberType")), memberType));
        setSortIndex(NumberUtils.toLong(StringUtils.toString(map.get("sortIndex")), sortIndex));
        setPath(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("path")), path));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("memberId", StringUtils.toString(memberId, eiMetadata.getMeta("memberId")));
        map.put("parentId", StringUtils.toString(parentId, eiMetadata.getMeta("parentId")));
        map.put("memberType", StringUtils.toString(memberType, eiMetadata.getMeta("memberType")));
        map.put("sortIndex", StringUtils.toString(sortIndex, eiMetadata.getMeta("sortIndex")));
        map.put("path", StringUtils.toString(path, eiMetadata.getMeta("path")));
        map.put("recCreator", StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime", StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor", StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime", StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("archiveFlag", StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        return map;
    }
}