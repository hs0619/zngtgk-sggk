/**
* Generate time : 2023-03-09 15:12:45
* Version : 1.0
*/
package com.baosight.sggk.du.hd.domain;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* THdReportInfo
* 
*/
public class DUHD20 extends DaoEPBase {

                private String reportname = " ";		/* 报表名称*/
                private String reporttype = " ";		/* 报表类型*/
                private String timetype = " ";		/* 对应数字*/
                private String timeinfo = " ";		
                private String uploadman = " ";		/* 上传人员*/
                private String uploadtime = " ";		/* 上传时间*/
/**
* initialize the metadata
*/
public void initMetaData() {
EiColumn eiColumn;

        eiColumn = new EiColumn("reportname");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("报表名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("reporttype");
        eiColumn.setDescName("报表类型");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("timetype");
        eiColumn.setDescName("对应数字");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("timeinfo");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("uploadman");
        eiColumn.setDescName("上传人员");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("uploadtime");
        eiColumn.setDescName("上传时间");
        eiMetadata.addMeta(eiColumn);


}
/**
* the constructor
*/
public DUHD20() {
initMetaData();
}

        /**
        * get the reportname - 报表名称
        * @return the reportname
        */
        public String getReportname() {
        return this.reportname;
        }

        /**
        * set the reportname - 报表名称
        */
        public void setReportname(String reportname) {
        this.reportname = reportname;
        }
        /**
        * get the reporttype - 报表类型
        * @return the reporttype
        */
        public String getReporttype() {
        return this.reporttype;
        }

        /**
        * set the reporttype - 报表类型
        */
        public void setReporttype(String reporttype) {
        this.reporttype = reporttype;
        }
        /**
        * get the timetype - 对应数字
        * @return the timetype
        */
        public String getTimetype() {
        return this.timetype;
        }

        /**
        * set the timetype - 对应数字
        */
        public void setTimetype(String timetype) {
        this.timetype = timetype;
        }
        /**
        * get the timeinfo 
        * @return the timeinfo
        */
        public String getTimeinfo() {
        return this.timeinfo;
        }

        /**
        * set the timeinfo 
        */
        public void setTimeinfo(String timeinfo) {
        this.timeinfo = timeinfo;
        }
        /**
        * get the uploadman - 上传人员
        * @return the uploadman
        */
        public String getUploadman() {
        return this.uploadman;
        }

        /**
        * set the uploadman - 上传人员
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
/**
* get the value from Map
*/
public void fromMap(Map map) {

                setReportname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("reportname")), reportname));
                setReporttype(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("reporttype")), reporttype));
                setTimetype(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("timetype")), timetype));
                setTimeinfo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("timeinfo")), timeinfo));
                setUploadman(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("uploadman")), uploadman));
                setUploadtime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("uploadtime")), uploadtime));
}

/**
* set the value to Map
*/
public Map toMap() {

Map map = new HashMap();
                map.put("reportname",StringUtils.toString(reportname, eiMetadata.getMeta("reportname")));
                map.put("reporttype",StringUtils.toString(reporttype, eiMetadata.getMeta("reporttype")));
                map.put("timetype",StringUtils.toString(timetype, eiMetadata.getMeta("timetype")));
                map.put("timeinfo",StringUtils.toString(timeinfo, eiMetadata.getMeta("timeinfo")));
                map.put("uploadman",StringUtils.toString(uploadman, eiMetadata.getMeta("uploadman")));
                map.put("uploadtime",StringUtils.toString(uploadtime, eiMetadata.getMeta("uploadtime")));

return map;

}
}