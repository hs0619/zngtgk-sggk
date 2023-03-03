package com.baosight.sggk.du.hm.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.sggk.du.hm.domain.DUHM43;
import com.baosight.sggk.util.SelectUtil;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class ServiceDUHM43 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHM43.class);

	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();

        //厂部
        inInfo.set("type","D1");
        inInfo.set("level","1");
        inInfo.set("totalplan","1");
        outInfo.addBlock(SelectUtil.getDepartment(dao,inInfo));

		// 形态
		outInfo.addBlock(SelectUtil.getTextAndValue(dao,"form","1"));

		// 日期
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
		Calendar cale = null;
		cale = Calendar.getInstance();
		cale.add(Calendar.MONTH, -1);
		// cale.set(Calendar.DAY_OF_MONTH, 1);
		String firstday = sdf1.format(cale.getTime());
		outInfo.set("inqu_status-0-datatime", firstday);

		return outInfo;
	}



	/**
	 * 查询功能
	 *
	 * @param inInfo
	 * @return
	 */
	public EiInfo query(EiInfo inInfo) {
	    EiInfo outinfo=new EiInfo();
        outinfo = super.query(inInfo,"DUHM43.queryInfo", new DUHM43());
        return outinfo;
	}

    // 保存
    public EiInfo saveInfo(EiInfo inInfo) {
        EiInfo outInfo =new EiInfo();
        StringBuffer buffer = new StringBuffer();
        int insertCount = 0;
        int updateCount = 0;
        try {
            for (int i = 0; i < inInfo.getBlock("result").getRows().size(); i++) {
                String isupdate=inInfo.getBlock("result").getCellStr(i,"isupdate");
                if ("yes".equals(isupdate)) {
                    dao.update("DUHM43.update", inInfo.getBlock("result").getRow(i));
                    updateCount++;
                } else {
                    inInfo.getBlock("result").setCell(i, "isupdate", "yes");
                    dao.insert("DUHM43.insert", inInfo.getBlock("result").getRow(i));
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


	public EiInfo delete(EiInfo inInfo) {
		/*StringBuffer buffer = new StringBuffer();
		int count = 0;
		String loginName = String.valueOf(UserSession.getLoginName());
		try {
			for (int j = 0; j < inInfo.getBlock("result").getRowCount(); j++) {
				String recordid = inInfo.getCellStr("result", j, "recordid");
				if (loginName.equals(recordid)) {
					String status = inInfo.getCellStr("result", j, "status");
					if("0".equals(status)) {
						dao.delete("DUHF71.delete", inInfo.getBlock("result").getRow(j));
						count++;
						inInfo.setStatus(1);
					}else {
						buffer.insert(0, "删除失败，“提交状态”的记录不可以删除！\n");
						inInfo.setStatus(-1);
						break;
					}
				} else {
					buffer.insert(0, "删除失败，删除记录中有不是您本人登记的记录！\n");
					inInfo.setStatus(-1);
					break;
				}
			}
		} catch (Exception e) {
			throw e;
		}
		if (inInfo.getStatus() == 1) {
			buffer.insert(0, "删除成功" + count + "条记录!");
		}
		inInfo.setMsg(buffer.toString());*/
		inInfo=super.delete(inInfo,"DUHM43.delete");
		return inInfo;

	}
}
