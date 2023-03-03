/**
 *
 * Generate time : 2021-08-11 14:23:25
 */
package com.baosight.sggk.du.hm.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.sggk.du.hm.domain.DUHM41;
import com.baosight.sggk.du.hm.domain.DUHM4301;
import com.baosight.sggk.util.SelectUtil;
import com.baosight.sggk.util.StrUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceDUHM4301 extends ServiceEPBase {

	public EiInfo initLoad(EiInfo inInfo) {
        inInfo.addBlock(EiConstant.resultBlock);
        inInfo.getBlock(EiConstant.resultBlock).addBlockMeta(new DUHM4301().eiMetadata);
	    //厂部
	    inInfo.set("type","D1");
        inInfo.set("level","1");
        inInfo.set("totalplan","1");
		inInfo.addBlock(SelectUtil.getDepartment(dao,inInfo));

		//危废形态
        inInfo.addBlock(SelectUtil.getTextAndValue(dao,"form","1"));
		return inInfo;
	}



    @Override
    public EiInfo query(EiInfo inInfo) {
        String parentid=inInfo.getString("inqu_status-0-node");
        if("root".equals(parentid)){
            inInfo.set("inqu_status-0-parentid","");
        }else{
            inInfo.set("inqu_status-0-parentid",parentid);
        }
        EiInfo outInfo = super.query(inInfo, "DUHM4301.queryDepat", new DUHM4301());
        String pEname = outInfo.getCellStr(EiConstant.queryBlock, 0, "node");

        outInfo.getBlocks().put(pEname, outInfo.getBlock(EiConstant.resultBlock));
        outInfo.getBlocks().remove(EiConstant.resultBlock);
        return outInfo;
    }

    public EiInfo queryInfo(EiInfo inInfo){
        String departmentid=inInfo.getString("inqu_status-0-departmentid");
        if("root".equals(departmentid)){
            inInfo.set("inqu_status-0-departmentid","");
        }
        return super.query(inInfo,"DUHM4301.query",new DUHM4301());
    }

    public EiInfo insert(EiInfo inInfo){
	    EiInfo outInfo=new EiInfo();
	    int count=0;
	    try{
	        List<DUHM4301> duhm4301List=null;
	        if (inInfo.getBlock("result").getRowCount()>0){
	            for (int i=0;i<inInfo.getBlock("result").getRowCount();i++){
                    duhm4301List=dao.query("DUHM4301.queryBykey",inInfo.getBlock("result").getRow(i));
                    if (StrUtil.listIsNotNullOrEmpty(duhm4301List)){
                        outInfo.setStatus(-1);
                        outInfo.setMsg("保存失败，保存记录中有以存在的记录！");
                        count=0;
                        break;
                    }else{
                        dao.insert("DUHM4301.insert",inInfo.getBlock("result").getRow(i));
                        count++;
                    }
	            }
            }
        }catch (Exception ex){
            count=0;
            outInfo.setStatus(-1);
            outInfo.setMsg("保存失败！");
        }
        if (count>0){
            outInfo.setMsg("保存成功，新增"+count+"条记录！");
        }
        outInfo.addBlock(queryInfo(inInfo).getBlock("result"));
	    return outInfo;
    }

    public EiInfo update(EiInfo inInfo){
        return super.update(inInfo,"DUHM4301.update");
    }

    public EiInfo delete(EiInfo inInfo){
        return super.delete(inInfo,"DUHM4301.delete");
    }

}
