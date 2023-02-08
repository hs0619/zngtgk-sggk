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
* Tduha02
* 
*/
public class Tduha02 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String id = " ";		/* 用户群组ID*/
    private String groupEname = " ";		
    private String groupCname = " ";		/* 群组中文名*/
    private String groupType = " ";		/* 群组类型*/
    private Long sortIndex = new Long(0);	/* 排序*/
    private String recCreator = " ";		/* 创建人*/
    private String recCreateTime = " ";		/* 创建时间*/
    private String recRevisor = " ";		/* 修改人*/
    private String recReviseTime = " ";		/* 修改时间*/
    private String archiveFlag = " ";		/* 归档标记*/
    private String manageGroupEname = " ";		/* 管辖组*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("用户群组ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("groupEname");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("groupCname");
        eiColumn.setFieldLength(128);
        eiColumn.setDescName("群组中文名");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("groupType");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("群组类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sortIndex");
        eiColumn.setType("N");
        eiColumn.setScaleLength(0);
        eiColumn.setFieldLength(11);
        eiColumn.setDescName("排序");
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

        eiColumn = new EiColumn("manageGroupEname");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("管辖组");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public Tduha02() {
        initMetaData();
    }

    /**
     * get the id - 用户群组ID
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 用户群组ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the groupEname 
     * @return the groupEname
     */
    public String getGroupEname() {
        return this.groupEname;
    }

    /**
     * set the groupEname 
     */
    public void setGroupEname(String groupEname) {
        this.groupEname = groupEname;
    }

    /**
     * get the groupCname - 群组中文名
     * @return the groupCname
     */
    public String getGroupCname() {
        return this.groupCname;
    }

    /**
     * set the groupCname - 群组中文名
     */
    public void setGroupCname(String groupCname) {
        this.groupCname = groupCname;
    }

    /**
     * get the groupType - 群组类型
     * @return the groupType
     */
    public String getGroupType() {
        return this.groupType;
    }

    /**
     * set the groupType - 群组类型
     */
    public void setGroupType(String groupType) {
        this.groupType = groupType;
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
     * get the manageGroupEname - 管辖组
     * @return the manageGroupEname
     */
    public String getManageGroupEname() {
        return this.manageGroupEname;
    }

    /**
     * set the manageGroupEname - 管辖组
     */
    public void setManageGroupEname(String manageGroupEname) {
        this.manageGroupEname = manageGroupEname;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setGroupEname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("groupEname")), groupEname));
        setGroupCname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("groupCname")), groupCname));
        setGroupType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("groupType")), groupType));
        setSortIndex(NumberUtils.toLong(StringUtils.toString(map.get("sortIndex")), sortIndex));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
        setManageGroupEname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("manageGroupEname")), manageGroupEname));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("id", StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("groupEname", StringUtils.toString(groupEname, eiMetadata.getMeta("groupEname")));
        map.put("groupCname", StringUtils.toString(groupCname, eiMetadata.getMeta("groupCname")));
        map.put("groupType", StringUtils.toString(groupType, eiMetadata.getMeta("groupType")));
        map.put("sortIndex", StringUtils.toString(sortIndex, eiMetadata.getMeta("sortIndex")));
        map.put("recCreator", StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime", StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor", StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime", StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("archiveFlag", StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("manageGroupEname", StringUtils.toString(manageGroupEname, eiMetadata.getMeta("manageGroupEname")));
        return map;
    }
}