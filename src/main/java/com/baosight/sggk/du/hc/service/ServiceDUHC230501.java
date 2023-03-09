/**
 *
 */
package com.baosight.sggk.du.hc.service;

import com.baosight.sggk.common.du.domain.Tduhb10;
import com.baosight.sggk.du.hc.domain.DUHC230201;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

//

public class ServiceDUHC230501 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHC230501.class);

	// 获取配置文件里的参数
	public ResourceBundle dbPro = ResourceBundle.getBundle("application");
	public String DbSchema = dbPro.getString("hbSchema");

	//
	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		
		
		outInfo.addBlock(getFactor(outInfo).getBlock("factor"));
		
		String monitorid=inInfo.getString("monitorid");
		String factorid=inInfo.getString("factorid");
		List<DUHC230201> list=queryBykey(monitorid,factorid);
		if(list!=null && list.size()>0) {
			EiBlock inquBlock=outInfo.addBlock("inqu_status");
			inquBlock.addRows(list);
		}
		
		
		return outInfo;
	}
	
	public EiInfo getFactor(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		
		String monitorid=inInfo.getString("inqu_status-0-monitorid");
		EiBlock factorBlock=outInfo.addBlock("factor");
		factorBlock.setBlockMeta((new Tduhb10()).eiMetadata);
		Map pffactormap = new HashMap();
	    pffactormap.put("monitorid",StringUtils.isBlank(monitorid)?"":monitorid);
		List factorlist = this.dao.query("tduhb10.queryfactor", pffactormap);
		factorBlock.addRows(factorlist);
		return outInfo;
				
	}
	
	
	private List<DUHC230201> queryBykey(String monitorid, String factorid) {
		List<DUHC230201> list=null;
		try {
			Map<String, Object> map=new HashMap<>();
			map.put("monitorid", monitorid);
			map.put("factorid", factorid);
			list=dao.query("DUHC230201.queryBykey",map);
		} catch (Exception e) {
			return list;
		}
		return list;
	}


	public EiInfo save(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		try {
			List<DUHC230201> list=dao.query("DUHC230201.queryBykey",inInfo.getBlock("inqu_status").getRow(0));
			if (list!=null && list.size()>0) {
				dao.update("DUHC230201.update",inInfo.getBlock("inqu_status").getRow(0));
				outInfo.setMsg("保存成功，修改一条记录！");
			}else {
				dao.insert("DUHC230201.insert",inInfo.getBlock("inqu_status").getRow(0));
				outInfo.setMsg("保存成功，新增一条记录！");
			}
			outInfo.setStatus(1);
		} catch (Exception e) {
			outInfo.setStatus(-1);
			outInfo.setMsg("保存失败！");
		}
		return outInfo;
	}
}
