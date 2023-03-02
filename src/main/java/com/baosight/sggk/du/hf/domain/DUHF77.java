/**
* Generate time : 2021-07-08 10:02:13
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.du.hf.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* DUHF77
* 
*/
public class DUHF77 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String fileid = " ";		/* 辐射报告ID*/
    private String filetype = " ";		/* 辐射报告类型ID*/
    private String filename = " ";		/* 附件名称*/
    private String filepath = " ";		/* 附件路径*/
    private String uploadman = " ";		/* 上传人*/
    private String uploadtime = " ";		/* 上传时间*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("fileid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(36);
        eiColumn.setDescName("辐射报告ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("filetype");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName("辐射报告类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("filename");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName("附件名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("filepath");
        eiColumn.setFieldLength(300);
        eiColumn.setDescName("附件路径");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("uploadman");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("上传人");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("uploadtime");
        eiColumn.setFieldLength(32);
        eiColumn.setDescName("上传时间");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DUHF77() {
        initMetaData();
    }

    /**
     * get the fileid - 辐射报告ID
     * @return the fileid
     */
    public String getFileid() {
        return this.fileid;
    }

    /**
     * set the fileid - 辐射报告ID
     */
    public void setFileid(String fileid) {
        this.fileid = fileid;
    }


    /**
     * get the filename - 附件名称
     * @return the filename
     */
    public String getFilename() {
        return this.filename;
    }

    /**
     * set the filename - 附件名称
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * get the filepath - 附件路径
     * @return the filepath
     */
    public String getFilepath() {
        return this.filepath;
    }

    /**
     * set the filepath - 附件路径
     */
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    /**
     * get the uploadman - 上传人
     * @return the uploadman
     */
    public String getUploadman() {
        return this.uploadman;
    }

    /**
     * set the uploadman - 上传人
     */
    public void setUploadman(String uploadman) {
        this.uploadman = uploadman;
    }

    /**
     * get the uploadtime - 上传时间
     * @return the uploadtime
     */
    public String getUploadtime() {
        return this.uploadtime;
    }

    /**
     * set the uploadtime - 上传时间
     */
    public void setUploadtime(String uploadtime) {
        this.uploadtime = uploadtime;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setFileid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fileid")), fileid));
        setFiletype(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("filetype")), filetype));
        setFilename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("filename")), filename));
        setFilepath(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("filepath")), filepath));
        setUploadman(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("uploadman")), uploadman));
        setUploadtime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("uploadtime")), uploadtime));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("fileid", StringUtils.toString(fileid, eiMetadata.getMeta("fileid")));
        map.put("filetype", StringUtils.toString(filetype, eiMetadata.getMeta("filetype")));
        map.put("filename", StringUtils.toString(filename, eiMetadata.getMeta("filename")));
        map.put("filepath", StringUtils.toString(filepath, eiMetadata.getMeta("filepath")));
        map.put("uploadman", StringUtils.toString(uploadman, eiMetadata.getMeta("uploadman")));
        map.put("uploadtime", StringUtils.toString(uploadtime, eiMetadata.getMeta("uploadtime")));
        return map;
    }
}