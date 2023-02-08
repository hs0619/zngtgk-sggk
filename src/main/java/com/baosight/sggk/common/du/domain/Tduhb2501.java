/**
* Generate time : 2021-04-25 16:15:30
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
* Tduhb2501
* 
*/
public class Tduhb2501 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String userId = " ";		
    private String loginName = " ";		
    private String password = " ";		
    private String status = " ";		
    private String userName = " ";		
    private String gender = " ";		
    private String mobile = " ";		
    private String email = " ";		
    private String userType = " ";		
    private String accountExpireDate = " ";		
    private String pwdExpireDate = " ";		
    private String isLocked = " ";		
    private Long sortIndex = new Long(0);	
    private String recCreator = " ";		
    private String recCreateTime = " ";		
    private String recRevisor = " ";		
    private String recReviseTime = " ";		
    private String pwdReviseDate = " ";		
    private String pwdRevisor = " ";		
    private String archiveFlag = " ";		
    private String userGroupEname = " ";		
    private String departmentId = " ";		
    private String departmentName = " ";		
    private String parentid = " ";		
    private String description = " ";		
    private String level = " ";		
    private String sort = " ";		
    private String type = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("userId");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("loginName");
        eiColumn.setFieldLength(64);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("password");
        eiColumn.setFieldLength(255);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("status");
        eiColumn.setFieldLength(16);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("userName");
        eiColumn.setFieldLength(128);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("gender");
        eiColumn.setFieldLength(2);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mobile");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("email");
        eiColumn.setFieldLength(128);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("userType");
        eiColumn.setFieldLength(16);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("accountExpireDate");
        eiColumn.setFieldLength(14);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("pwdExpireDate");
        eiColumn.setFieldLength(14);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isLocked");
        eiColumn.setFieldLength(2);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sortIndex");
        eiColumn.setType("N");
        eiColumn.setScaleLength(0);
        eiColumn.setFieldLength(11);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreateTime");
        eiColumn.setFieldLength(14);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recRevisor");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recReviseTime");
        eiColumn.setFieldLength(14);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("pwdReviseDate");
        eiColumn.setFieldLength(14);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("pwdRevisor");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setFieldLength(1);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("userGroupEname");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("departmentId");
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

        eiColumn = new EiColumn("description");
        eiColumn.setFieldLength(255);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("level");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sort");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("type");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public Tduhb2501() {
        initMetaData();
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
     * get the loginName 
     * @return the loginName
     */
    public String getLoginName() {
        return this.loginName;
    }

    /**
     * set the loginName 
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * get the password 
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * set the password 
     */
    public void setPassword(String password) {
        this.password = password;
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
     * get the userName 
     * @return the userName
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * set the userName 
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * get the gender 
     * @return the gender
     */
    public String getGender() {
        return this.gender;
    }

    /**
     * set the gender 
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * get the mobile 
     * @return the mobile
     */
    public String getMobile() {
        return this.mobile;
    }

    /**
     * set the mobile 
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * get the email 
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * set the email 
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * get the userType 
     * @return the userType
     */
    public String getUserType() {
        return this.userType;
    }

    /**
     * set the userType 
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * get the accountExpireDate 
     * @return the accountExpireDate
     */
    public String getAccountExpireDate() {
        return this.accountExpireDate;
    }

    /**
     * set the accountExpireDate 
     */
    public void setAccountExpireDate(String accountExpireDate) {
        this.accountExpireDate = accountExpireDate;
    }

    /**
     * get the pwdExpireDate 
     * @return the pwdExpireDate
     */
    public String getPwdExpireDate() {
        return this.pwdExpireDate;
    }

    /**
     * set the pwdExpireDate 
     */
    public void setPwdExpireDate(String pwdExpireDate) {
        this.pwdExpireDate = pwdExpireDate;
    }

    /**
     * get the isLocked 
     * @return the isLocked
     */
    public String getIsLocked() {
        return this.isLocked;
    }

    /**
     * set the isLocked 
     */
    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked;
    }

    /**
     * get the sortIndex 
     * @return the sortIndex
     */
    public Long getSortIndex() {
        return this.sortIndex;
    }

    /**
     * set the sortIndex 
     */
    public void setSortIndex(Long sortIndex) {
        this.sortIndex = sortIndex;
    }

    /**
     * get the recCreator 
     * @return the recCreator
     */
    public String getRecCreator() {
        return this.recCreator;
    }

    /**
     * set the recCreator 
     */
    public void setRecCreator(String recCreator) {
        this.recCreator = recCreator;
    }

    /**
     * get the recCreateTime 
     * @return the recCreateTime
     */
    public String getRecCreateTime() {
        return this.recCreateTime;
    }

    /**
     * set the recCreateTime 
     */
    public void setRecCreateTime(String recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    /**
     * get the recRevisor 
     * @return the recRevisor
     */
    public String getRecRevisor() {
        return this.recRevisor;
    }

    /**
     * set the recRevisor 
     */
    public void setRecRevisor(String recRevisor) {
        this.recRevisor = recRevisor;
    }

    /**
     * get the recReviseTime 
     * @return the recReviseTime
     */
    public String getRecReviseTime() {
        return this.recReviseTime;
    }

    /**
     * set the recReviseTime 
     */
    public void setRecReviseTime(String recReviseTime) {
        this.recReviseTime = recReviseTime;
    }

    /**
     * get the pwdReviseDate 
     * @return the pwdReviseDate
     */
    public String getPwdReviseDate() {
        return this.pwdReviseDate;
    }

    /**
     * set the pwdReviseDate 
     */
    public void setPwdReviseDate(String pwdReviseDate) {
        this.pwdReviseDate = pwdReviseDate;
    }

    /**
     * get the pwdRevisor 
     * @return the pwdRevisor
     */
    public String getPwdRevisor() {
        return this.pwdRevisor;
    }

    /**
     * set the pwdRevisor 
     */
    public void setPwdRevisor(String pwdRevisor) {
        this.pwdRevisor = pwdRevisor;
    }

    /**
     * get the archiveFlag 
     * @return the archiveFlag
     */
    public String getArchiveFlag() {
        return this.archiveFlag;
    }

    /**
     * set the archiveFlag 
     */
    public void setArchiveFlag(String archiveFlag) {
        this.archiveFlag = archiveFlag;
    }

    /**
     * get the userGroupEname 
     * @return the userGroupEname
     */
    public String getUserGroupEname() {
        return this.userGroupEname;
    }

    /**
     * set the userGroupEname 
     */
    public void setUserGroupEname(String userGroupEname) {
        this.userGroupEname = userGroupEname;
    }

    /**
     * get the departmentId 
     * @return the departmentId
     */
    public String getDepartmentId() {
        return this.departmentId;
    }

    /**
     * set the departmentId 
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
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
     * get the description 
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * set the description 
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * get the level 
     * @return the level
     */
    public String getLevel() {
        return this.level;
    }

    /**
     * set the level 
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * get the sort 
     * @return the sort
     */
    public String getSort() {
        return this.sort;
    }

    /**
     * set the sort 
     */
    public void setSort(String sort) {
        this.sort = sort;
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
     * get the value from Map
     */
    public void fromMap(Map map) {

        setUserId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("userId")), userId));
        setLoginName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("loginName")), loginName));
        setPassword(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("password")), password));
        setStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("status")), status));
        setUserName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("userName")), userName));
        setGender(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("gender")), gender));
        setMobile(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mobile")), mobile));
        setEmail(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("email")), email));
        setUserType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("userType")), userType));
        setAccountExpireDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("accountExpireDate")), accountExpireDate));
        setPwdExpireDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("pwdExpireDate")), pwdExpireDate));
        setIsLocked(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isLocked")), isLocked));
        setSortIndex(NumberUtils.toLong(StringUtils.toString(map.get("sortIndex")), sortIndex));
        setRecCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreator")), recCreator));
        setRecCreateTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recCreateTime")), recCreateTime));
        setRecRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recRevisor")), recRevisor));
        setRecReviseTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("recReviseTime")), recReviseTime));
        setPwdReviseDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("pwdReviseDate")), pwdReviseDate));
        setPwdRevisor(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("pwdRevisor")), pwdRevisor));
        setArchiveFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("archiveFlag")), archiveFlag));
        setUserGroupEname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("userGroupEname")), userGroupEname));
        setDepartmentId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentId")), departmentId));
        setDepartmentName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentName")), departmentName));
        setParentid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("parentid")), parentid));
        setDescription(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("description")), description));
        setLevel(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("level")), level));
        setSort(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sort")), sort));
        setType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("type")), type));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("userId", StringUtils.toString(userId, eiMetadata.getMeta("userId")));
        map.put("loginName", StringUtils.toString(loginName, eiMetadata.getMeta("loginName")));
        map.put("password", StringUtils.toString(password, eiMetadata.getMeta("password")));
        map.put("status", StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("userName", StringUtils.toString(userName, eiMetadata.getMeta("userName")));
        map.put("gender", StringUtils.toString(gender, eiMetadata.getMeta("gender")));
        map.put("mobile", StringUtils.toString(mobile, eiMetadata.getMeta("mobile")));
        map.put("email", StringUtils.toString(email, eiMetadata.getMeta("email")));
        map.put("userType", StringUtils.toString(userType, eiMetadata.getMeta("userType")));
        map.put("accountExpireDate", StringUtils.toString(accountExpireDate, eiMetadata.getMeta("accountExpireDate")));
        map.put("pwdExpireDate", StringUtils.toString(pwdExpireDate, eiMetadata.getMeta("pwdExpireDate")));
        map.put("isLocked", StringUtils.toString(isLocked, eiMetadata.getMeta("isLocked")));
        map.put("sortIndex", StringUtils.toString(sortIndex, eiMetadata.getMeta("sortIndex")));
        map.put("recCreator", StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime", StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor", StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime", StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("pwdReviseDate", StringUtils.toString(pwdReviseDate, eiMetadata.getMeta("pwdReviseDate")));
        map.put("pwdRevisor", StringUtils.toString(pwdRevisor, eiMetadata.getMeta("pwdRevisor")));
        map.put("archiveFlag", StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("userGroupEname", StringUtils.toString(userGroupEname, eiMetadata.getMeta("userGroupEname")));
        map.put("departmentId", StringUtils.toString(departmentId, eiMetadata.getMeta("departmentId")));
        map.put("departmentName", StringUtils.toString(departmentName, eiMetadata.getMeta("departmentName")));
        map.put("parentid", StringUtils.toString(parentid, eiMetadata.getMeta("parentid")));
        map.put("description", StringUtils.toString(description, eiMetadata.getMeta("description")));
        map.put("level", StringUtils.toString(level, eiMetadata.getMeta("level")));
        map.put("sort", StringUtils.toString(sort, eiMetadata.getMeta("sort")));
        map.put("type", StringUtils.toString(type, eiMetadata.getMeta("type")));
        return map;
    }
}