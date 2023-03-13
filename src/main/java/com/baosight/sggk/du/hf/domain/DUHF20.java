/**
 * Generate time : 2023-03-06 13:47:44
 * Version : 1.0
 */
package com.baosight.sggk.du.hf.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * DUHF78
 *
 */
public class DUHF20 extends DaoEPBase {

    private String reportid = " ";        /* 报表id*/
    private String departmentid = " ";        /* 区域单位*/
    private String year = " ";        /* 年份*/
    private String quarter = " ";        /* 季度*/
    private String score = " ";        /* 评价得分*/
    private String scoringcontent = " ";        /* 评价分项内容*/
    private String standardscore = " ";        /* 评价标准分*/

    private String departmentname = " ";        /* 厂部名称*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("reportid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("报表id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("departmentid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("区域单位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("year");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("年份");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("quarter");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("季度");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("score");
        eiColumn.setDescName("评价得分");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("scoringcontent");
        eiColumn.setDescName("评价分项内容");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("standardscore");
        eiColumn.setDescName("评价标准分");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("departmentname");
        eiColumn.setDescName("厂部名称");
        eiMetadata.addMeta(eiColumn);

    }

    /**
     * the constructor
     */
    public DUHF20() {
        initMetaData();
    }

    /**
     * get the reportid - 报表id
     * @return the reportid
     */
    public String getReportid() {
        return this.reportid;
    }

    /**
     * set the reportid - 报表id
     */
    public void setReportid(String reportid) {
        this.reportid = reportid;
    }

    /**
     * get the departmentid - 区域单位
     * @return the departmentid
     */
    public String getDepartmentid() {
        return this.departmentid;
    }

    /**
     * set the departmentid - 区域单位
     */
    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    /**
     * get the year - 年份
     * @return the year
     */
    public String getYear() {
        return this.year;
    }

    /**
     * set the year - 年份
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * get the quarter - 季度
     * @return the quarter
     */
    public String getQuarter() {
        return this.quarter;
    }

    /**
     * set the quarter - 季度
     */
    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    /**
     * get the score - 评价得分
     * @return the score
     */
    public String getScore() {
        return this.score;
    }

    /**
     * set the score - 评价得分
     */
    public void setScore(String score) {
        this.score = score;
    }

    /**
     * get the scoringcontent - 评价分项内容
     * @return the scoringcontent
     */
    public String getScoringcontent() {
        return this.scoringcontent;
    }

    /**
     * set the scoringcontent - 评价分项内容
     */
    public void setScoringcontent(String scoringcontent) {
        this.scoringcontent = scoringcontent;
    }

    /**
     * get the standardscore - 评价标准分
     * @return the standardscore
     */
    public String getStandardscore() {
        return this.standardscore;
    }

    /**
     * set the standardscore - 评价标准分
     */
    public void setStandardscore(String standardscore) {
        this.standardscore = standardscore;
    }

    /**
     * set the standardscore - 厂部名称
     */
    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setReportid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("reportid")), reportid));
        setDepartmentid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentid")), departmentid));
        setYear(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("year")), year));
        setQuarter(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("quarter")), quarter));
        setScore(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("score")), score));
        setScoringcontent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("scoringcontent")), scoringcontent));
        setStandardscore(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("standardscore")), standardscore));
        setDepartmentname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentname")), departmentname));

    }

    /**
     * set the value to Map
     */
    public Map toMap() {

        Map map = new HashMap();
        map.put("reportid", StringUtils.toString(reportid, eiMetadata.getMeta("reportid")));
        map.put("departmentid", StringUtils.toString(departmentid, eiMetadata.getMeta("departmentid")));
        map.put("year", StringUtils.toString(year, eiMetadata.getMeta("year")));
        map.put("quarter", StringUtils.toString(quarter, eiMetadata.getMeta("quarter")));
        map.put("score", StringUtils.toString(score, eiMetadata.getMeta("score")));
        map.put("scoringcontent", StringUtils.toString(scoringcontent, eiMetadata.getMeta("scoringcontent")));
        map.put("standardscore", StringUtils.toString(standardscore, eiMetadata.getMeta("standardscore")));
        map.put("departmentname", StringUtils.toString(departmentname, eiMetadata.getMeta("departmentname")));

        return map;

    }
}