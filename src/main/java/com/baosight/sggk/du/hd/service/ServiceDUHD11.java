package com.baosight.sggk.du.hd.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.sggk.du.hd.domain.DUHD11;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ServiceDUHD11 extends ServiceBase {

    public EiInfo initLoad(EiInfo inInfo){
        return query(inInfo);
    }

    public EiInfo query(EiInfo inInfo){
        EiInfo outInfo = super.query(inInfo, "DUHD11.query");
        return outInfo;
    }

    public EiInfo update(EiInfo inInfo){
        EiInfo outInfo = super.update(inInfo, "DUHD11.update");
        return outInfo;
    }

    public EiInfo delete(EiInfo inInfo){
        EiInfo outInfo = super.delete(inInfo, "DUHD11.delete");
        return outInfo;
    }

    public EiInfo insert(EiInfo inInfo){
        String formulaid = getInsertId();
//        inInfo.getBlock("result").set("inqu_status-0-formulaid",formulaid);
        inInfo.getBlock("result").setCell(0,"formulaid",formulaid);
        EiInfo outInfo = super.insert(inInfo, "DUHD11.insert");
        return outInfo;
    }

    //获取新的主键
    private String getInsertId() {
        String formulaid = "";
        List list = this.dao.query("DUHD11.selectMaxId", new HashMap<>());
        formulaid = (String) list.get(0);
        int teamRmId = Integer.parseInt(formulaid.substring(2));
        formulaid = "FC" + String.format("%04d", teamRmId + 1);
        return formulaid;
    }
}
