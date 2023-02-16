package com.baosight.sggk.du.hb.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.sggk.common.du.domain.Tduhb50;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceDUHB5001 extends ServiceBase {

    public EiInfo initLoad(EiInfo inInfo) {
        EiInfo outInfo = new EiInfo();

        String oprationType = StringUtils.isBlank((String) inInfo.get("oprationType")) ? "" : (String) inInfo.get("oprationType");
        String rmId = StringUtils.isBlank((String) inInfo.get("rmId")) ? "" : (String) inInfo.get("rmId");


        if (oprationType.equals("update")) {
            EiBlock inqu = query(rmId);
            outInfo.setBlock(inqu);
        }

        outInfo.set("inqu_status-0-type", oprationType);
        return outInfo;
    }

    public EiBlock query(String rmId) {
        EiBlock eiBlock = new EiBlock("inqu_status");
        Map map = new HashMap();
        map.put("rmId", rmId);
        List list = this.dao.query("tduhb50.selectByRmId", map);
        eiBlock.setRows(list);
        return eiBlock;
    }

    public EiInfo insert(EiInfo inInfo) {
        String newRmId = getInsertRmId();
//        String rsId = UUID.randomUUID().toString();
        inInfo.set("inqu_status-0-rmId", newRmId);
        EiInfo outInfo = super.insert(inInfo, "tduhb50.insert", new Tduhb50(), false, "inqu_status");
        return outInfo;
    }

    //获取新的主键
    private String getInsertRmId() {
        String rmId = "";
        List list = this.dao.query("tduhb50.selectMaxRsId", new HashMap<>());
        rmId = (String) list.get(0);
        int teamRmId = Integer.parseInt(rmId.substring(2));
        rmId = "RM" + String.format("%05d", teamRmId + 1);
        return rmId;
    }
}
