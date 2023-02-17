package com.baosight.sggk.du.hb.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.sggk.common.du.domain.Tduhb50;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceDUHB5101 extends ServiceBase {

    public EiInfo initLoad(EiInfo inInfo) {
        EiInfo outInfo = new EiInfo();

        String oprationType = StringUtils.isBlank((String) inInfo.get("oprationType")) ? "" : (String) inInfo.get("oprationType");
        String rfId = StringUtils.isBlank((String) inInfo.get("rfId")) ? "" : (String) inInfo.get("rfId");


        if (oprationType.equals("update")) {
            EiBlock inqu = query(rfId);
            outInfo.setBlock(inqu);
        }

        outInfo.set("inqu_status-0-type", oprationType);
        return outInfo;
    }

    public EiBlock query(String rfId) {
        EiBlock eiBlock = new EiBlock("inqu_status");
        Map map = new HashMap();
        map.put("rfId", rfId);
        List list = this.dao.query("tduhb51.selectByRfId", map);
        eiBlock.setRows(list);
        return eiBlock;
    }

    public EiInfo insert(EiInfo inInfo) {
        String newRfId = getInsertRfId();
        inInfo.set("inqu_status-0-rfId", newRfId);
        EiInfo outInfo = super.insert(inInfo, "tduhb51.insert", new Tduhb50(), false, "inqu_status");
        return outInfo;
    }

    public EiInfo update(EiInfo inInfo) {
        EiInfo outInfo = super.update(inInfo, "tduhb51.update", new Tduhb50(), false, "inqu_status");
        return outInfo;
    }

    //获取新的主键
    private String getInsertRfId() {
        String rfId = "";
        List list = this.dao.query("tduhb51.selectMaxRfId", new HashMap<>());
        rfId = (String) list.get(0);
        int teamRmId = Integer.parseInt(rfId.substring(2));
        rfId = "RM" + String.format("%05d", teamRmId + 1);
        return rfId;
    }
}
