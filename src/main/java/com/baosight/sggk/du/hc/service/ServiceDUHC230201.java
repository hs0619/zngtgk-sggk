/**
 *
 */
package com.baosight.sggk.du.hc.service;

import com.baosight.sggk.common.du.domain.Tduhb06;
import com.baosight.sggk.common.du.domain.Tduhb07;
import com.baosight.sggk.du.hc.domain.DUHC2302;
import com.baosight.sggk.du.hc.domain.DUHC230202;
import com.baosight.sggk.util.PermissionUtil;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.sggk.util.SelectUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.*;

public class ServiceDUHC230201 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHC230201.class);

	// 获取配置文件里的参数
	public ResourceBundle dbPro = ResourceBundle.getBundle("application");
	public String DbSchema = dbPro.getString("hbSchema");
	//登录人工号
	String loginName= UserSession.getLoginName();
	//登录人名称
	String loginCName= UserSession.getLoginCName();

	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
		String planid = StringUtils.isBlank(inInfo.getString("planid"))?"空":inInfo.getString("planid");
		List<DUHC2302> list = queryById(planid);
		if (list.size() > 0 && list != null) {
			EiBlock resultBlock = outInfo.addBlock("inqu_status");
			resultBlock.addRows(list);
			outInfo.set("inqu_status-0-oprationType", "update");
		}else {
			outInfo.set("inqu_status-0-clockyear", sdf.format(new Date()));
			outInfo.set("inqu_status-0-licensecount", "1");
			outInfo.set("inqu_status-0-monitorid", "01");
			// 登录人
			String loginName = String.valueOf(UserSession.getLoginName());
			outInfo.set("inqu_status-0-departmentid", PermissionUtil.getUserDepart(dao, loginName));
		}
        //监测频率
        outInfo.addBlock(SelectUtil.getTextAndValue(dao,"timeDime","1"));

		outInfo.setMsg("页面加载完成");
		return outInfo;
	}

	public List<DUHC2302> queryById(String planid) {
		List<DUHC2302> list = null;
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("planid", planid);
			list = dao.query("DUHC2302.query1", map);
		} catch (Exception e) {
			throw e;
		}
		return list;
	}

	// 保存计划配置信息
	public EiInfo savePlanInof(EiInfo inInfo) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String oprationType = (String) inInfo.get("inqu_status-0-oprationType");
			// 原则 P2021-SI00000170-SEASON-01-1
			// P+年份+站点编号+周期+类型ID+计划类型（计划内1，计划外2）
			String clockyear = (String) inInfo.get("inqu_status-0-clockyear");
			String copyyear = (String) inInfo.get("inqu_status-0-copyyear");
			String copyname = (String) inInfo.get("inqu_status-0-copyname");
			String siteid = (String) inInfo.get("inqu_status-0-siteid");
			String licenserate = (String) inInfo.get("inqu_status-0-licenserate");
			String monitorid = (String) inInfo.get("inqu_status-0-monitorid");
			String plantype = (String) inInfo.get("inqu_status-0-plantype");
			String planid = StringUtils.isBlank(inInfo.getString("planid"))?"空":inInfo.getString("planid");
			// 注意sql语句拼的时候，结尾不能添加分号
			if (oprationType.equals("insert")) {
				planid= "P" + clockyear + "-" + siteid + "-" + licenserate + "-" + monitorid + "-" + plantype;
				List<DUHC2302> list = queryById(planid);
				if (list != null && list.size() > 0 ) {
					inInfo.setMsg("该记录已存在，请确认年份、厂部、类别、测点名称、监测频率是否唯一");
					inInfo.setStatus(2);
					return inInfo;
				}else {
					inInfo.getBlock("inqu_status").setCell(0, "planid", planid);
					inInfo.getBlock("inqu_status").setCell(0, "createid", loginName);
					inInfo.getBlock("inqu_status").setCell(0, "createname", loginCName);
					inInfo.getBlock("inqu_status").setCell(0, "createtime", sdf.format(new Date()));
					dao.insert("DUHC2302.insert1",inInfo.getBlock("inqu_status").getRow(0));
					inInfo.setMsg("保存成功，新增一条记录！");
					
					insertPlanFactor(inInfo,siteid);
				}
			} else if(oprationType.equals("update")) {
				inInfo.getBlock("inqu_status").setCell(0, "updateid", loginName);
				inInfo.getBlock("inqu_status").setCell(0, "updatename", loginCName);
				inInfo.getBlock("inqu_status").setCell(0, "updatetime", sdf.format(new Date()));
				dao.update("DUHC2302.update1",inInfo.getBlock("inqu_status").getRow(0));
				inInfo.setMsg("保存成功，修改一条记录！");
			} else if(oprationType.equals("copy")) {
				planid= "P" + copyyear + "-" + siteid + "-" + licenserate + "-" + monitorid + "-" + plantype;
				List<DUHC2302> list = queryById(planid);
				if (list != null && list.size() > 0  ) {
					inInfo.setMsg("复制失败，该年份的计划已存在！");
					inInfo.setStatus(2);
					return inInfo;
				}else {
					inInfo.getBlock("inqu_status").setCell(0, "planid", planid);
					inInfo.getBlock("inqu_status").setCell(0, "planname", copyname);
					inInfo.getBlock("inqu_status").setCell(0, "clockyear", copyyear);
					inInfo.getBlock("inqu_status").setCell(0, "createid", loginName);
					inInfo.getBlock("inqu_status").setCell(0, "createname", loginCName);
					inInfo.getBlock("inqu_status").setCell(0, "createtime", sdf.format(new Date()));
					dao.insert("DUHC2302.insert1",inInfo.getBlock("inqu_status").getRow(0));
					inInfo.setMsg("复制成功，新增一条记录！");
					
					insertPlanFactor(inInfo,siteid);
				}
			}
		} catch (Exception ex) {
			inInfo.setMsg("数据保存报错：" + ex.getMessage());
		}
		return inInfo;
	}

	//新增计划因子
	private void insertPlanFactor(EiInfo inInfo, String siteid) {
		Map<String,Object> map=new HashMap<>();
		map.put("siteid", siteid);
		try {
			//获取排口id
			List<Tduhb07> portlist=dao.query("tduhb07.queryBySiteid",map);
			if (portlist!=null && portlist.size()>0) {
				String portid=portlist.get(0).getPortid();
				map.put("portid", portid);
				List<Tduhb06> list=dao.query("tduhb06.query",map);
				if (list!=null && list.size()>0) {
					for (int i = 0; i < list.size(); i++) {
						inInfo.getBlock("inqu_status").setCell(0, "factorid", list.get(i).getFactorid());
                        inInfo.getBlock("inqu_status").setCell(0, "unit", list.get(i).getUnit());
						inInfo.getBlock("inqu_status").setCell(0, "highlimit", list.get(i).getHighlimit());
						inInfo.getBlock("inqu_status").setCell(0, "lowlimit", list.get(i).getLowlimit());
						dao.insert("DUHC230202.insert" ,inInfo.getBlock("inqu_status").getRow(0));
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}
	//复制计划
	public EiInfo copyplan(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		String copyyear=inInfo.getString("copyyear");
		String lastyear=inInfo.getString("lastyear");
		Map<String, Object> map=new HashMap<>();
		map.put("clockyear", lastyear);
		int exitcount=0,copycount=0;
		List<DUHC2302> lastPlanList=dao.query("DUHC2302.query1",map);
		if (lastPlanList!=null && lastPlanList.size()>0) {
			EiBlock copyBlock=outInfo.addBlock("inqu_status");
			copyBlock.addRows(lastPlanList);
			String lastplanid="",lastplanname="";
			String copyplanid="",copyplanname="";
			for (int i = 0; i < copyBlock.getRowCount(); i++) {
				lastplanid=copyBlock.getCellStr(i, "planid");
				copyplanid="P"+copyyear+lastplanid.substring(5);
				lastplanname=copyBlock.getCellStr(i, "planname");
				copyplanname=copyyear+lastplanname.substring(4);
				copyBlock.setCell(i, "planid", copyplanid);
				copyBlock.setCell(i, "planname", copyplanname);
				copyBlock.setCell(i, "clockyear", copyyear);
				//判断是否存在计划
				map.put("planid", copyplanid);
				List<DUHC2302> planList=dao.query("DUHC2302.queryByPlanid",map);
				if (planList!=null && planList.size()>0) {
					exitcount++;
				}else {
					dao.insert("DUHC2302.insert1",outInfo.getBlock("inqu_status").getRow(i));
					copycount++;
					map.put("planid", lastplanid);
					map.put("monitorid", copyBlock.getCellStr(i, "monitorid"));
					//去年计划因子
					List<DUHC230202> planFactorlist=dao.query("DUHC230202.query",map);
					EiBlock planFactorBlock=null;
					if (planFactorlist!=null && planFactorlist.size()>0) {
						planFactorBlock=new EiBlock("planFactor");
						planFactorBlock.addRows(planFactorlist);
						//改变计划id年份
						for (int j = 0; j < planFactorBlock.getRowCount(); j++) {
							planFactorBlock.setCell(j, "planid", copyplanid);
						}
						
						for (int j = 0; j < planFactorBlock.getRowCount(); j++) {
							dao.insert("DUHC230202.insert" ,planFactorBlock.getRow(j));
						}
					}
				}
			}
			outInfo.setMsg("复制成功，"+copyyear+"年份已存在"+exitcount+"份与"+lastyear+"年份同类型监测计划，成功将"+lastyear+"年份"+copycount+"份监测计划复制到"+copyyear+"年份");
			outInfo.setStatus(1);
		}else {
			outInfo.setMsg("复制失败，"+lastyear+"年份没有监测计划");
			outInfo.setStatus(2);
		}
		
		return outInfo;
	}
}
