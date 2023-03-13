package com.baosight.sggk.du.hf.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.sggk.common.du.domain.Tduhb01;
import com.baosight.sggk.du.hf.domain.DUHF20;
import com.baosight.sggk.util.PermissionUtil;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import java.util.*;

public class ServiceDUHF20 extends ServiceEPBase {
    private static final Logger logger = Logger.getLogger(ServiceDUHF20.class);


    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        EiInfo outInfo = new EiInfo();
        // 单位
        EiBlock qdepartBlock = outInfo.addBlock("qdepart");
        qdepartBlock.setBlockMeta((new Tduhb01()).eiMetadata);
        //根据权限拉厂部
        String status = PermissionUtil.getUserDepart(this.dao, UserSession.getLoginName());
        if (!"".equals(status)) {
            if ("%".equals(status)) {
//                List<Map> dlist1 = new ArrayList<>();
//                Map<String, String> dmap1 = new HashMap<String, String>();
//                dmap1.put("departmentName", "全部");
//                dmap1.put("departmentId", "%");
//                dlist1.add(dmap1);
//                qdepartBlock.addRows(dlist1);
                Map dquerymap = new HashMap();
                dquerymap.put("tpye", "D1");
                dquerymap.put("parentid", "root");
                dquerymap.put("status", "1");
                List dlist2 = this.dao.query("tduhb01.query", dquerymap);
                qdepartBlock.addRows(dlist2);
            } else {
                Map dquerymap = new HashMap();
                dquerymap.put("type", "D1");
                dquerymap.put("departmentId", status);
                dquerymap.put("status", "1");
                List dlist2 = this.dao.query("tduhb01.query", dquerymap);
                qdepartBlock.addRows(dlist2);
            }
        }

        if (qdepartBlock.getRowCount() > 0) {
            outInfo.set("inqu_status-0-departmentid", qdepartBlock.getRow(0).get("departmentId"));
        }
        //获取当前年份,季度
        Calendar calendar = Calendar.getInstance();
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        int month = calendar.get(Calendar.MONTH) + 1;
        String quarter = getQuarter(month);
        outInfo.set("inqu_status-0-year", year);
        outInfo.set("inqu_status-0-quarter", quarter);

        return outInfo;
    }


    /**
     * 查询功能
     *
     * @param inInfo
     * @return
     */
    @Override
    public EiInfo query(EiInfo inInfo) {
        String departmentid = inInfo.getString("inqu_status-0-departmentid");
        if ("%".equals(departmentid)) {
            inInfo.set("inqu_status-0-departmentid", "");
        }
        inInfo = super.query(inInfo, "DUHF20.query1", new DUHF20());

        return inInfo;
    }

    @Override
    public EiInfo delete(EiInfo inInfo) {
        return super.delete(inInfo, "DUHF20.delete");
    }

    @Override
    public EiInfo insert(EiInfo inInfo) {
        return super.insert(inInfo);
    }

    /**
     * 新增及修改根据条件判断
     *
     * @param inInfo
     * @return
     */
    @Override
    public EiInfo update(EiInfo inInfo) {
        List query = this.dao.query("DUHF20.query", inInfo.getRow("result", 0));

        if (CollectionUtils.isEmpty(query)) {

            return super.insert(inInfo, "DUHF20.insert");
        }

        return super.update(inInfo, "DUHF20.update");

    }

    /**
     * 根据月份计算季度
     *
     * @param month
     * @return
     */
    private String getQuarter(int month) {
        if (month >= 1 && month <= 3) {
            return "q1";
        } else if (month >= 4 && month <= 6) {
            return "q2";
        } else if (month >= 7 && month <= 9) {
            return "q3";
        } else if (month >= 10 && month <= 12) {
            return "q4";
        }
        return "q1";
    }

}
