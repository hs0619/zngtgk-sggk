
package com.baosight.sggk.du.hb.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;


public class DUHB0703 extends DaoEPBase {

    private static final long serialVersionUID = 1L;


    private String monitoringType = " ";//监测类型
    private String total = " ";//总数

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;



        eiColumn = new EiColumn("monitoringType");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("total");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DUHB0703() {
        initMetaData();
    }



    public String getMonitoringType() {
        return monitoringType;
    }

    public void setMonitoringType(String monitoringType) {
        this.monitoringType = monitoringType;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

    	setMonitoringType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("monitoringType")), monitoringType));
    	setTotal(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("total")), total));

    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("monitoringType",StringUtils.toString(monitoringType, eiMetadata.getMeta("monitoringType")));
        map.put("total",StringUtils.toString(total, eiMetadata.getMeta("total")));
        return map;
    }
}