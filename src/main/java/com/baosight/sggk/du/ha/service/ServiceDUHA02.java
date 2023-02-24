/**
 *
 */
package com.baosight.sggk.du.ha.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.sggk.du.ha.domain.DUHA02;
import com.baosight.sggk.util.SelectUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ServiceDUHA02 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHA02.class);

	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
        //类型
		EiBlock typeBlock=outInfo.addBlock("type");
        typeBlock.addRows(dao.query("DUHA02.queryType", new HashMap<>()));
		return outInfo;
	}

    public EiInfo query(EiInfo inInfo) {
        EiInfo outInfo = new EiInfo();
        outInfo=super.query(inInfo,"DUHA02.query",new DUHA02());
        return outInfo;
    }

    public EiInfo insert(EiInfo inInfo) {
        EiInfo outInfo = new EiInfo();
        int count=0;
        try{
            if(inInfo.getBlock("result").getRowCount()>0){
                for (int i=0;i<inInfo.getBlock("result").getRowCount();i++){
                    inInfo.getBlock("result").setCell(i,"logicid", UUID.randomUUID().toString());
                    dao.insert("DUHA02.insert",inInfo.getBlock("result").getRow(i));
                    count++;
                }
                outInfo.setMsg("新增成功"+count+"条记录！");
                outInfo.setStatus(1);
            }
        }catch (Exception ex){
            outInfo.setMsg("新增失败！");
            outInfo.setStatus(-1);
        }
        return outInfo;
    }

    public EiInfo update(EiInfo inInfo) {
        EiInfo outInfo = new EiInfo();
        outInfo=super.update(inInfo,"DUHA02.update");
        return outInfo;
    }

    public EiInfo delete(EiInfo inInfo) {
        EiInfo outInfo = new EiInfo();
        outInfo=super.delete(inInfo,"DUHA02.delete");
        return outInfo;
    }
}
