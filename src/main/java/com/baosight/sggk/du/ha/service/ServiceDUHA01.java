/**
 *
 */
package com.baosight.sggk.du.ha.service;

import com.baosight.sggk.util.SelectUtil;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class ServiceDUHA01 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHA01.class);

	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();

		return outInfo;
	}

	/**
	 * 初始化下拉框
	 * 
	 * @param inInfo
	 * @return
	 */
	public EiInfo initSelectData(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		//需要的初始化下拉框（1、厂部 2、厂部+监测类型 3、厂部+监测因子类型+站点 4、厂部+监测因子类型+监测点类型
		String haveType = inInfo.getString("haveType");
		if ("1".equals(haveType)) {
			outInfo.setBlock(SelectUtil.getDepartment(dao, inInfo));
		} else if ("2".equals(haveType)) {
			outInfo.setBlock(SelectUtil.getDepartment(dao, inInfo));
            outInfo.setBlock(SelectUtil.getSite(dao, inInfo));
			//getSite(inInfo);
		} else if ("3".equals(haveType)) {
			outInfo.setBlock(SelectUtil.getMonitor(dao, inInfo));
			outInfo.setBlock(SelectUtil.getDepartment(dao, inInfo));
			outInfo.setBlock(SelectUtil.getSite(dao, inInfo));
		}else if("4".equals(haveType)) {
			outInfo.setBlock(SelectUtil.GetSiteTypes(inInfo.getBlock("porttype"), true, "online"));
			outInfo.setBlock(SelectUtil.getMonitor(dao, inInfo));
			outInfo.setBlock(SelectUtil.getDepartment(dao, inInfo));
			outInfo.setBlock(SelectUtil.getSite(dao, inInfo));
		}
		return outInfo;
	}
	
	/**
	 * 获取站点
	 * 
	 * @param inInfo
	 * @return
	 */
	public EiInfo getSite(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		inInfo.set("siteid", inInfo.getString("inqu_status-0-siteid"));
		inInfo.set("isartificial", inInfo.getString("inqu_status-0-isartificial"));
		inInfo.set("site_isonline", inInfo.getString("inqu_status-0-site_isonline"));
		inInfo.set("classifyid", inInfo.getString("inqu_status-0-classifyid"));
		inInfo.set("monitorid", inInfo.getString("inqu_status-0-monitorid"));
		inInfo.set("departmentid_site", inInfo.getString("inqu_status-0-departmentid_site"));
		inInfo.set("procid", inInfo.getString("inqu_status-0-procid"));
		
		String porttypeid=inInfo.getString("inqu_status-0-porttypeid");
		if (StringUtils.isNotBlank(porttypeid)) {
			switch (porttypeid) {
	 		case "country":
	 			inInfo.set("countrypoint", "1");
	 			break;
	 		case "city":
	 			inInfo.set("citypoint", "1");
	 			break;
	 		case "company":
	 			inInfo.set("companypoint", "1");
	 			break;
	 		default:
	 			break;
	 		}
		}
		outInfo.setBlock(SelectUtil.getSite(dao, inInfo));
		return outInfo;
	}
	

}
