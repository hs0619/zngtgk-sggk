package com.baosight.sggk.du.hd.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.sggk.common.du.domain.Tduhb01;
import com.baosight.sggk.du.hd.domain.DUHD10;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceDUHD10 extends ServiceBase {

    public EiInfo initLoad(EiInfo inInfo){
        EiInfo outInfo = new EiInfo();
        outInfo.addBlock(getDeptBlock("deptblock"));
        outInfo.addBlock(getProcedureBlock("procedureblock"));
        return outInfo;
    }

    public EiInfo query(EiInfo inInfo){
        EiInfo outInfo = super.query(inInfo, "DUHD10.query",new DUHD10());
        return outInfo;
    }

    public EiInfo query2(EiInfo inInfo) {
        EiInfo outInfo = new EiInfo();
        outInfo.addBlock("result2");
        Map<String, Object> map = new HashMap<>();
        if(inInfo.getBlock("inqu_status") !=null){
            map =inInfo.getBlock("inqu_status").getRow(0);
        }
        int offset = (Integer) inInfo.getBlock("result2").get("offset") != null ? (Integer) inInfo.getBlock("result2").get("offset") : 0;
        int limit =  (Integer) inInfo.getBlock("result2").get("limit");
        List result = this.dao.query("DUHD10.query2",map,offset,limit);
        outInfo.getBlock("result2").setRows(result);
        return outInfo;
    }

//    public EiInfo insert(EiInfo inInfo){
//        EiInfo outInfo = super.insert(inInfo, "DUHD10.insert");
//        return outInfo;
//    }
//
//    public EiInfo insert2(EiInfo inInfo){
//        EiInfo outInfo = super.insert(inInfo, "DUHD10.insert");
//        return outInfo;
//    }


    public EiInfo update(EiInfo inInfo) {
        EiInfo outInfo =new EiInfo();
        StringBuffer buffer = new StringBuffer();
        int insertCount = 0;
        int updateCount = 0;
        try {
            for (int i = 0; i < inInfo.getBlock("result").getRows().size(); i++) {
                String isupdate=inInfo.getBlock("result").getCellStr(i,"isupdate");
                if ("yes".equals(isupdate)) {
                    dao.update("DUHD10.update", inInfo.getBlock("result").getRow(i));
                    updateCount++;
                } else {
                    inInfo.getBlock("result").setCell(i, "isupdate", "yes");
                    dao.insert("DUHD10.insert", inInfo.getBlock("result").getRow(i));
                    insertCount++;
                }

            }
            outInfo.set("status", 1);
        } catch (Exception e) {
            outInfo.set("status", -1);
            outInfo.setMsg("保存失败！");
        }
        outInfo.addBlock(query(inInfo).getBlock("result"));
        if (insertCount > 0) {
            buffer.insert(0, "新增成功" + insertCount + "条记录！\n");
        }
        if (updateCount > 0) {
            buffer.insert(0, "修改成功" + updateCount + "条记录！\n");
        }
        outInfo.setMsg(buffer.toString());
        //outInfo.getBlock("result").set("limit", 100);
        return outInfo;
    }

    public EiInfo update2(EiInfo inInfo) {
//        EiBlock eiBlock = inInfo.getBlock("result2");
//        EiInfo outInfo = null;
//        if(eiBlock!=null){
//            inInfo.addBlock("result").setRows(inInfo.getBlock("result2").getRows());
//            List<Map> result = inInfo.getBlock("result").getRows();
//            if(result.size()>0){
//                for (int i = 0; i < result.size(); i++) {
//                    Map map = result.get(i);
//                    List data = this.dao.query("DUHD10.queryData", map);
//                    if(data.size()>0){
//                        outInfo = super.update(inInfo, "DUHD10.update");
//                    }else {
//                        outInfo = super.insert(inInfo, "DUHD10.insert");
//                    }
//                }
//            }
//        }
//        return outInfo;
        EiInfo outInfo =new EiInfo();
        StringBuffer buffer = new StringBuffer();
        int insertCount = 0;
        int updateCount = 0;
        try {
            EiBlock eiBlock = inInfo.getBlock("result2");
            if (eiBlock != null){
                inInfo.addBlock("result").setRows(inInfo.getBlock("result2").getRows());
                List result = inInfo.getBlock("result").getRows();
                for (int i = 0; i < result.size(); i++) {
                    String isupdate=inInfo.getBlock("result2").getCellStr(i,"isupdate");
                    if ("yes".equals(isupdate)) {
                        dao.update("DUHD10.update", inInfo.getBlock("result2").getRow(i));
                        updateCount++;
                    } else {
                        inInfo.getBlock("result2").setCell(i, "isupdate", "yes");
                        dao.insert("DUHD10.insert", inInfo.getBlock("result2").getRow(i));
                        insertCount++;
                    }

                }
            }

            outInfo.set("status", 1);
        } catch (Exception e) {
            outInfo.set("status", -1);
            outInfo.setMsg("保存失败！");
        }
        outInfo.addBlock(query(inInfo).getBlock("result2"));
        if (insertCount > 0) {
            buffer.insert(0, "新增成功" + insertCount + "条记录！\n");
        }
        if (updateCount > 0) {
            buffer.insert(0, "修改成功" + updateCount + "条记录！\n");
        }
        outInfo.setMsg(buffer.toString());
        //outInfo.getBlock("result").set("limit", 100);
        return outInfo;
    }

    public EiInfo delete(EiInfo inInfo){
        EiInfo outInfo = super.delete(inInfo, "DUHD10.delete");
        return outInfo;
    }


    public EiInfo delete2(EiInfo inInfo){
        EiBlock eiBlock = inInfo.getBlock("result2");
        inInfo.addBlock(eiBlock).setRows(eiBlock.getRows());
        EiInfo outInfo = super.delete(inInfo, "DUHD10.delete");
        return outInfo;
    }

    private EiBlock getProcedureBlock(String blockName) {
        EiBlock eiBlock = new EiBlock(blockName);
        Map map = new HashMap();
        map.put("type","P1");
        List list = this.dao.query("tduhb01.query", map);
        eiBlock.setBlockMeta(new Tduhb01().eiMetadata);
        eiBlock.setRows(list);
        return eiBlock;
    }

    private EiBlock getDeptBlock(String blockName) {
        EiBlock eiBlock = new EiBlock(blockName);
        Map map = new HashMap();
        map.put("type","D1");
        List list = this.dao.query("tduhb01.query", map);
        eiBlock.setBlockMeta(new Tduhb01().eiMetadata);
        eiBlock.setRows(list);
        return eiBlock;
    }


    /**
     * 根据厂部id，查询对应的工序列表
     *
     * @param inInfo
     * @return
     */
    public EiInfo queryProcByDepid(EiInfo inInfo) {
        EiBlock procedureblock = inInfo.getBlock("procedureList");
        if (procedureblock == null) {
            procedureblock = new EiBlock("procedureList");
            procedureblock.setBlockMeta(new Tduhb01().eiMetadata);
        }

        String departmentId = inInfo.getString("departmentid");
        Map querymap = new HashMap();
        querymap.put("type", "P1");
        querymap.put("parentid", departmentId);
        List procedureblocklist = this.dao.query("tduhb01.query", querymap);
        procedureblock.addRows(procedureblocklist);
        inInfo.setBlock(procedureblock);
        return inInfo;
    }
}
