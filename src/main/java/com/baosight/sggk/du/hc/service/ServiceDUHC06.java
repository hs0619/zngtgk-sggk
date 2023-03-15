package com.baosight.sggk.du.hc.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.sggk.du.hc.domain.DUHC06;
import org.apache.log4j.Logger;

import java.util.UUID;

/**
 * @Author : yangqingxin
 * @Date 2023/3/13 17:35
 * @Version 1.0
 */
public class ServiceDUHC06 extends ServiceEPBase {
    private static final Logger logger = Logger.getLogger(ServiceDUHC06.class);

    @Override
    public EiInfo initLoad(EiInfo inInfo) {
//        EiInfo outInfo = new EiInfo();
//        outInfo.addBlock("userblock");
//        String loginName = UserSession.getLoginName();
//        outInfo.setCell("userblock",0,"username",loginName);
        return query(inInfo);
    }

    @Override
    public EiInfo query(EiInfo inInfo) {
        return super.query(inInfo,"DUHC06.query",new DUHC06());
    }

    @Override
    public EiInfo delete(EiInfo inInfo) {
        return super.delete(inInfo,"DUHC06.delete");
    }

    @Override
    public EiInfo update(EiInfo inInfo) {
        return super.update(inInfo,"DUHC06.update");
    }

    @Override
    public EiInfo insert(EiInfo inInfo) {
        String loginCName = UserSession.getLoginCName();
        String userId = UserSession.getUserId();
        String id = UUID.randomUUID().toString();
        inInfo.setCell("result",0,"username",loginCName);
        inInfo.setCell("result",0,"userid",userId);
        inInfo.setCell("result",0,"id",id);

        return super.insert(inInfo,"DUHC06.insert");
    }
}
