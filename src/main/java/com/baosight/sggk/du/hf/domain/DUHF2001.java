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
 * DUHF7801
 *
 */
public class DUHF2001 extends DaoEPBase {

    private String reportid = " ";        /* 报表id*/
    private String scoringcontent = " ";        /* 评价分项内容*/
    private String standardscore = " ";        /* 评价标准分*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("reportid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("报表id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("scoringcontent");
        eiColumn.setDescName("评价分项内容");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("standardscore");
        eiColumn.setDescName("评价标准分");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DUHF2001() {
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
     * get the value from Map
     */
    public void fromMap(Map map) {

        setReportid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("reportid")), reportid));
        setScoringcontent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("scoringcontent")), scoringcontent));
        setStandardscore(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("standardscore")), standardscore));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {

        Map map = new HashMap();
        map.put("reportid", StringUtils.toString(reportid, eiMetadata.getMeta("reportid")));
        map.put("scoringcontent", StringUtils.toString(scoringcontent, eiMetadata.getMeta("scoringcontent")));
        map.put("standardscore", StringUtils.toString(standardscore, eiMetadata.getMeta("standardscore")));

        return map;

    }
}