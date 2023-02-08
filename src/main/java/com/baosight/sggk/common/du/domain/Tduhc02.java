/**
* Generate time : 2021-03-05 16:36:10
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhf6801
* 
*/
public class Tduhc02 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String annexId = " ";		
    private String declareId = "";		
    private String annexPath = "";		
    private String fileSize = "";		
    private String uploader = "";		
    private String uploadTime = "";		
    private String annexName = "";		
    private String annexType = "";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("annexId");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("declareId");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("annexPath");
        eiColumn.setFieldLength(200);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("fileSize");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("uploader");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("uploadTime");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("annexName");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("annexType");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public Tduhc02() {
        initMetaData();
    }

    /**
     * get the annexId 
     * @return the annexId
     */
    public String getAnnexId() {
        return this.annexId;
    }

    /**
     * set the annexId 
     */
    public void setAnnexId(String annexId) {
        this.annexId = annexId;
    }

    /**
     * get the declareId 
     * @return the declareId
     */
    public String getDeclareId() {
        return this.declareId;
    }

    /**
     * set the declareId 
     */
    public void setDeclareId(String declareId) {
        this.declareId = declareId;
    }

    /**
     * get the annexPath 
     * @return the annexPath
     */
    public String getAnnexPath() {
        return this.annexPath;
    }

    /**
     * set the annexPath 
     */
    public void setAnnexPath(String annexPath) {
        this.annexPath = annexPath;
    }

    /**
     * get the fileSize 
     * @return the fileSize
     */
    public String getFileSize() {
        return this.fileSize;
    }

    /**
     * set the fileSize 
     */
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * get the uploader 
     * @return the uploader
     */
    public String getUploader() {
        return this.uploader;
    }

    /**
     * set the uploader 
     */
    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    /**
     * get the uploadTime 
     * @return the uploadTime
     */
    public String getUploadTime() {
        return this.uploadTime;
    }

    /**
     * set the uploadTime 
     */
    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    /**
     * get the annexName 
     * @return the annexName
     */
    public String getAnnexName() {
        return this.annexName;
    }

    /**
     * set the annexName 
     */
    public void setAnnexName(String annexName) {
        this.annexName = annexName;
    }

    /**
     * get the annexType 
     * @return the annexType
     */
    public String getAnnexType() {
        return this.annexType;
    }

    /**
     * set the annexType 
     */
    public void setAnnexType(String annexType) {
        this.annexType = annexType;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setAnnexId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("annexId")), annexId));
        setDeclareId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("declareId")), declareId));
        setAnnexPath(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("annexPath")), annexPath));
        setFileSize(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fileSize")), fileSize));
        setUploader(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("uploader")), uploader));
        setUploadTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("uploadTime")), uploadTime));
        setAnnexName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("annexName")), annexName));
        setAnnexType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("annexType")), annexType));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("annexId", StringUtils.toString(annexId, eiMetadata.getMeta("annexId")));
        map.put("declareId", StringUtils.toString(declareId, eiMetadata.getMeta("declareId")));
        map.put("annexPath", StringUtils.toString(annexPath, eiMetadata.getMeta("annexPath")));
        map.put("fileSize", StringUtils.toString(fileSize, eiMetadata.getMeta("fileSize")));
        map.put("uploader", StringUtils.toString(uploader, eiMetadata.getMeta("uploader")));
        map.put("uploadTime", StringUtils.toString(uploadTime, eiMetadata.getMeta("uploadTime")));
        map.put("annexName", StringUtils.toString(annexName, eiMetadata.getMeta("annexName")));
        map.put("annexType", StringUtils.toString(annexType, eiMetadata.getMeta("annexType")));
        return map;
    }
}