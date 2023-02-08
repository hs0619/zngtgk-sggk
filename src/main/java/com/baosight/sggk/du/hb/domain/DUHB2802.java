/**
* Generate time : 2021-04-25 16:09:19
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.du.hb.domain;

import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* DUHB2802
* 
*/
public class DUHB2802 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String userId = " ";		/* 用户ID*/
    private String loginName = " ";		
    private String password = " ";		
    private String status = " ";		
    private String userName = " ";		
    private String gender = "1";		/* 性别1男2女*/
    private String mobile = " ";		
    private String email = " ";		
    private String userType = " ";		
    private String accountExpireDate = " ";		
    private String pwdExpireDate = " ";		
    private String isLocked = " ";		
    private Long sortIndex = new Long(0);	/* 排序*/
    private String recCreator = " ";		
    private String recCreateTime = " ";		/* 创建时间*/
    private String recRevisor = " ";		/* 修改人*/
    private String recReviseTime = " ";		/* 修改时间*/
    private String pwdReviseDate = " ";		/* 密码修改时间*/
    private String pwdRevisor = " ";		/* 密码修改人*/
    private String archiveFlag = " ";		/* 归档标记*/
    private String userGroupEname = " ";		/* 用户组*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("userId");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("用户ID");
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
        eiColumn.setDescName("性别1男2女");
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
        eiColumn.setDescName("排序");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("recCreator");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName(" ");
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

        eiColumn = new EiColumn("pwdReviseDate");
        eiColumn.setFieldLength(14);
        eiColumn.setDescName("密码修改时间");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("pwdRevisor");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("密码修改人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("archiveFlag");
        eiColumn.setFieldLength(1);
        eiColumn.setDescName("归档标记");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("userGroupEname");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("用户组");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DUHB2802() {
        initMetaData();
    }

    /**
     * get the userId - 用户ID
     * @return the userId
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * set the userId - 用户ID
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
     * get the gender - 性别1男2女
     * @return the gender
     */
    public String getGender() {
        return this.gender;
    }

    /**
     * set the gender - 性别1男2女
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
     * get the pwdReviseDate - 密码修改时间
     * @return the pwdReviseDate
     */
    public String getPwdReviseDate() {
        return this.pwdReviseDate;
    }

    /**
     * set the pwdReviseDate - 密码修改时间
     */
    public void setPwdReviseDate(String pwdReviseDate) {
        this.pwdReviseDate = pwdReviseDate;
    }

    /**
     * get the pwdRevisor - 密码修改人
     * @return the pwdRevisor
     */
    public String getPwdRevisor() {
        return this.pwdRevisor;
    }

    /**
     * set the pwdRevisor - 密码修改人
     */
    public void setPwdRevisor(String pwdRevisor) {
        this.pwdRevisor = pwdRevisor;
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
     * get the userGroupEname - 用户组
     * @return the userGroupEname
     */
    public String getUserGroupEname() {
        return this.userGroupEname;
    }

    /**
     * set the userGroupEname - 用户组
     */
    public void setUserGroupEname(String userGroupEname) {
        this.userGroupEname = userGroupEname;
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
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("userId",StringUtils.toString(userId, eiMetadata.getMeta("userId")));
        map.put("loginName",StringUtils.toString(loginName, eiMetadata.getMeta("loginName")));
        map.put("password",StringUtils.toString(password, eiMetadata.getMeta("password")));
        map.put("status",StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("userName",StringUtils.toString(userName, eiMetadata.getMeta("userName")));
        map.put("gender",StringUtils.toString(gender, eiMetadata.getMeta("gender")));
        map.put("mobile",StringUtils.toString(mobile, eiMetadata.getMeta("mobile")));
        map.put("email",StringUtils.toString(email, eiMetadata.getMeta("email")));
        map.put("userType",StringUtils.toString(userType, eiMetadata.getMeta("userType")));
        map.put("accountExpireDate",StringUtils.toString(accountExpireDate, eiMetadata.getMeta("accountExpireDate")));
        map.put("pwdExpireDate",StringUtils.toString(pwdExpireDate, eiMetadata.getMeta("pwdExpireDate")));
        map.put("isLocked",StringUtils.toString(isLocked, eiMetadata.getMeta("isLocked")));
        map.put("sortIndex",StringUtils.toString(sortIndex, eiMetadata.getMeta("sortIndex")));
        map.put("recCreator",StringUtils.toString(recCreator, eiMetadata.getMeta("recCreator")));
        map.put("recCreateTime",StringUtils.toString(recCreateTime, eiMetadata.getMeta("recCreateTime")));
        map.put("recRevisor",StringUtils.toString(recRevisor, eiMetadata.getMeta("recRevisor")));
        map.put("recReviseTime",StringUtils.toString(recReviseTime, eiMetadata.getMeta("recReviseTime")));
        map.put("pwdReviseDate",StringUtils.toString(pwdReviseDate, eiMetadata.getMeta("pwdReviseDate")));
        map.put("pwdRevisor",StringUtils.toString(pwdRevisor, eiMetadata.getMeta("pwdRevisor")));
        map.put("archiveFlag",StringUtils.toString(archiveFlag, eiMetadata.getMeta("archiveFlag")));
        map.put("userGroupEname",StringUtils.toString(userGroupEname, eiMetadata.getMeta("userGroupEname")));
        return map;
    }
}