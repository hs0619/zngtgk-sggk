package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class TDUHM51 extends DaoEPBase {
    private String muuid = "";
    private String myName = "";


    public void initMetaData() {
        EiColumn eiColumn;
        eiColumn = new EiColumn("muuid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(255);
        eiColumn.setDescName("编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("myName");
        eiColumn.setFieldLength(255);
        eiColumn.setDescName("名称");
        eiMetadata.addMeta(eiColumn);

    }

    public TDUHM51() {
        initMetaData();
    }

    public String getMuuid() {
        return muuid;
    }

    public void setMuuid(String muuid) {
        this.muuid = muuid;
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public void fromMap(Map map) {
        setMuuid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("muuid")), muuid));
        setMyName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("myName")), myName));
    }

    public Map toMap() {
        Map map = new HashMap();
        map.put("muuid", StringUtils.toString(muuid, eiMetadata.getMeta("muuid")));
        map.put("myName", StringUtils.toString(myName, eiMetadata.getMeta("myName")));
        return map;
    }
}