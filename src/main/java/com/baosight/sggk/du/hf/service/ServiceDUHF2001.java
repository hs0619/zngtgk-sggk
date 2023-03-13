package com.baosight.sggk.du.hf.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.sggk.du.hf.domain.DUHF2001;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;

/**
 * @Author : yangqingxin
 * @Date 2023/3/10 13:55
 * @Version 1.0
 */
public class ServiceDUHF2001 extends ServiceEPBase {
    private static final Logger logger = Logger.getLogger(ServiceDUHF2001.class);

    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return query(inInfo);
    }

    @Override
    public EiInfo query(EiInfo inInfo) {
        return super.query(inInfo,"DUHF2001.query", new DUHF2001());
    }

    @Override
    public EiInfo delete(EiInfo inInfo) {
        return super.delete(inInfo,"DUHF2001.delete");
    }

    @Override
    public EiInfo update(EiInfo inInfo) {
        return super.update(inInfo,"DUHF2001.update");
    }

    @Override
    public EiInfo insert(EiInfo inInfo) {
        List query = this.dao.query("DUHF2001.query", new HashMap<>());
        //构造主键id
        int size = query.size()+1;
        String reportId = "r"+size;
        inInfo.setCell("result",0,"reportid",reportId);
        return super.insert(inInfo,"DUHF2001.insert");
    }
}
