package com.baosight.sggk.du.hb.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.sggk.common.du.domain.Tduhb50;
import com.baosight.sggk.common.du.domain.Tduhb51;

public class ServiceDUHB51 extends ServiceBase {

    public EiInfo initLoad(EiInfo eiInfo){
        return query(eiInfo);
    }


    public EiInfo query(EiInfo eiInfo){
        EiInfo outInfo = super.query(eiInfo, "tduhb51.query", new Tduhb51(), false, new Tduhb50().eiMetadata, EiConstant.queryBlock, "result", "result");
        return outInfo;
    }

    public EiInfo delete(EiInfo eiInfo){
        EiInfo outInfo = super.delete(eiInfo, "tduhb51.delete", new Tduhb51(), false, "result");
        return outInfo;
    }

    public EiInfo insert(EiInfo eiInfo){
        EiInfo outInfo = super.insert(eiInfo, "tduhb51.insert", new Tduhb51(), false, "inqu_status");
        return outInfo;
    }

    public EiInfo update(EiInfo eiInfo){
        EiInfo outInfo = super.update(eiInfo, "tduhb51.update", new Tduhb51(), false, "inqu_status");
        return outInfo;
    }
}
