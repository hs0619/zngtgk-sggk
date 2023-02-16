package com.baosight.sggk.du.hb.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.sggk.common.du.domain.Tduhb50;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.security.core.parameters.P;

public class ServiceDUHB50 extends ServiceBase {

    public EiInfo initLoad(EiInfo eiInfo){
        return query(eiInfo);
    }


    public EiInfo query(EiInfo eiInfo){
        EiInfo outInfo = super.query(eiInfo, "tduhb50.query", new Tduhb50(), false, new Tduhb50().eiMetadata, EiConstant.queryBlock, "result", "result");
        return outInfo;
    }

    public EiInfo delete(EiInfo eiInfo){
        EiInfo outInfo = super.delete(eiInfo, "tduhb50.delete", new Tduhb50(), false, "result");
        return outInfo;
    }

    public EiInfo insert(EiInfo eiInfo){
        EiInfo outInfo = super.insert(eiInfo, "tduhb50.insert", new Tduhb50(), false, "inqu_status");
        return outInfo;
    }

    public EiInfo update(EiInfo eiInfo){
        EiInfo outInfo = super.update(eiInfo, "tduhb50.update", new Tduhb50(), false, "inqu_status");
        return outInfo;
    }
}
