package com.baosight.sggk.du.hb.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.sggk.common.du.domain.Tduhb50;
import org.springframework.security.core.parameters.P;

public class ServiceDUHB50 extends ServiceBase {

    public EiInfo initLoad(EiInfo eiInfo){
        return query(eiInfo);
    }


    public EiInfo query(EiInfo eiInfo){
        EiInfo outInfo = super.query(eiInfo, "tduhb50.query", new Tduhb50());
        return outInfo;
    }
}
