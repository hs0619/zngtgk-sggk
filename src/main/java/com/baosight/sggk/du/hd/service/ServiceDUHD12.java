package com.baosight.sggk.du.hd.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceDUHD12 extends ServiceBase {

    public EiInfo initLoad(EiInfo inInfo){
        EiInfo outInfo = new EiInfo();
        //添加标志
        EiBlockMeta eiMetadata = new EiBlockMeta();
        EiColumn eiColumn = null;
        eiColumn = new EiColumn("flagid");
        eiMetadata.addMeta(eiColumn);
        eiColumn = new EiColumn("flagname");
        eiMetadata.addMeta(eiColumn);
        EiBlock flagblock = new EiBlock("flagList");
        flagblock.setBlockMeta(eiMetadata);
        List<Map> list1 = new ArrayList<>();
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("flagid", "1");
        map1.put("flagname", "是");
        list1.add(map1);
        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("flagid", "0");
        map2.put("flagname", "否");
        list1.add(map2);
        flagblock.setRows(list1);
        outInfo.setBlock(flagblock);

        outInfo.setMsg("页面加载完成");
        return outInfo;
    }

    public EiInfo query(EiInfo inInfo){
        EiInfo outInfo = super.query(inInfo, "DUHD12.query");
        return outInfo;
    }

    public EiInfo update(EiInfo inInfo){
        EiInfo outInfo = super.update(inInfo, "DUHD12.update");
        return outInfo;
    }

    public EiInfo delete(EiInfo inInfo){
        EiInfo outInfo = super.delete(inInfo, "DUHD12.delete");
        return outInfo;
    }

    public EiInfo insert(EiInfo inInfo){
        String indexId = getInsertId();
        inInfo.getBlock("result").setCell(0,"indexId",indexId);
        EiInfo outInfo = super.insert(inInfo, "DUHD12.insert");
        return outInfo;
    }

    //获取新的主键
    private String getInsertId() {
        String indexId = "";
        List list = this.dao.query("DUHD12.selectMaxId", new HashMap<>());
        indexId = (String) list.get(0);
        int teamRmId = Integer.parseInt(indexId.substring(2));
        indexId = "IT" + String.format("%04d", teamRmId + 1);
        return indexId;
    }


}
