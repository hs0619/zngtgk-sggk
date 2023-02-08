package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class TDUHM52 extends DaoEPBase {

    private String muuid = "";

    public void initMetaData() {

        EiColumn eiColumn;

        eiColumn = new EiColumn("muuid");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName("编号");
        eiMetadata.addMeta(eiColumn);

    }

    public TDUHM52() {
        initMetaData();
    }

    public String getMuuid() {
        return muuid;
    }

    public void setMuuid(String muuid) {
        this.muuid = muuid;
    }


    public void fromMap(Map map) {
        setMuuid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("muuid")), muuid));
    }

    public Map toMap() {
        Map map = new HashMap();
        map.put("muuid", StringUtils.toString(muuid, eiMetadata.getMeta("muuid")));
        return map;
    }
}