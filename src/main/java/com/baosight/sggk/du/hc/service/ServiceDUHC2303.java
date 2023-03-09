/**
 *
 */
package com.baosight.sggk.du.hc.service;

import com.baosight.sggk.util.PermissionUtil;
import com.baosight.sggk.util.StrUtil;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.*;

//

public class ServiceDUHC2303 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHC2303.class);

	// 获取配置文件里的参数
	public ResourceBundle dbPro = ResourceBundle.getBundle("application");
	public String DbSchema = dbPro.getString("hbSchema");

	//
	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		// 登录人
		String loginName = String.valueOf(UserSession.getLoginName());
		String status= PermissionUtil.getUserDepart(dao, loginName);
		outInfo.set("inqu_status-0-departmentid",status);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		outInfo.set("inqu_status-0-endtime", sdf.format(date));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date); // 设置为当前时间
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
		date = calendar.getTime();
		outInfo.set("inqu_status-0-starttime", sdf.format(date));

		return outInfo;
	}

	/**
	 * 查询
	 */
	public EiInfo query(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String departmentid = inInfo.getString("inqu_status-0-departmentid");
		String siteid = inInfo.getString("inqu_status-0-siteid");
		String plantype = inInfo.getString("inqu_status-0-plantype");
		String[] monitor_type = plantype.split("-");
		String classifyid = "";
		String isplaninner = "";
		String isplanout = "";
		String planid = "";
		if (monitor_type.length > 0) {
			classifyid = monitor_type[0];
			if ("1".equals(monitor_type[1])) {
				isplaninner = "1";
				planid = "1";
			} else {
//				qsitemap.put("sitename", "计划外");
				isplanout = "1";
				planid = "2";
			}
		}
		String starttime = inInfo.getString("inqu_status-0-starttime");
		String endtime = inInfo.getString("inqu_status-0-endtime");
		if (!StringUtils.isBlank(starttime)) {
			starttime = starttime + " 00:00";
		}

		if (!StringUtils.isBlank(endtime)) {
			endtime = endtime + " 23:59";
		}
		String isquery = inInfo.getString("isquery");
		try {
			Integer oloffset = 0, ollimit = 1000;
			if (!"true".equals(isquery)) {
				if (inInfo.getBlock("ardata") != null) {
					ollimit = inInfo.getBlock("ardata").getInt("limit");
					oloffset = inInfo.getBlock("ardata").getInt("offset");
				}
			}
			EiBlock arinfoBlock = getFactorsBySite(departmentid,siteid,classifyid, isplaninner, isplanout,starttime,endtime);
			if(arinfoBlock.getRowCount()>0){
                outInfo.setBlock(arinfoBlock);
                EiBlock ardataheaderBlock = getDataHeaderByFactors(arinfoBlock);
                outInfo.setBlock(ardataheaderBlock);
                String factorSql = "select DISTINCT t1.SITEID ,t2.SITENAME,t1.ITEMTIME,t3.PLANNAME from " + DbSchema + ".T_HC_MANUALVALUE t1 "
                        + " right join " + DbSchema + ".T_HA_SITE t2 on t1.siteid=t2.siteid "
                        + " LEFT JOIN " + DbSchema +".T_HC_MANUALPLAN t3 ON t1.PLANID=t3.PLANID where 1=1";
                if (StringUtils.isNotBlank((departmentid)) && !"%".equals(departmentid)) {
                    factorSql += " and t2.departid='" + departmentid + "'";
                }
                if (StringUtils.isNotBlank((siteid))&& !"%".equals(siteid)) {
                    factorSql += " and t1.siteid = '" + siteid + "'";
                }
                factorSql += " and t1.monitorid='" + classifyid + "' and t1.planid like '%" + planid
                        + "' and t1.ITEMTIME >= '" + starttime + "' and t1.ITEMTIME <= '" + endtime+"'";
                Map sqlmap = new HashMap();
                sqlmap.put("sqlMap", factorSql);
                List sitelist = this.dao.query("DUHA01.query", sqlmap, oloffset, ollimit);
                outInfo = getDataByFactors(outInfo, arinfoBlock, sitelist, starttime, endtime, planid);
                EiBlock block = outInfo.getBlock("ardata");
                block.set("offset", oloffset);
                block.set("limit", ollimit);
                block.set("count", sitelist.size());
                outInfo.setStatus(1);
                outInfo.setMsg("数据加载完成！");
            }else{
			    outInfo.setStatus(2);
                outInfo.setMsg("未查到数据！");
            }

		} catch (Exception ex) {
			outInfo.setStatus(-1);
			outInfo.setMsg(ex.toString());
		}
		return outInfo;
	}

	// 根据类型查询监测因子
	private EiBlock getFactorsBySite(String departmentid,String siteid,String classifyid, String isplaninner, String isplanout,
            String starttime,String endtime) {
		EiBlock block = new EiBlock("artificial");
		EiBlockMeta metadata = new EiBlockMeta();
		EiColumn eiColumn = new EiColumn("factorid");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("monitorid");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("factorname");
		metadata.addMeta(eiColumn);
		eiColumn = new EiColumn("unit");
		metadata.addMeta(eiColumn);
		block.setBlockMeta(metadata);
		try {
            String factorSql ="select DISTINCT t1.monitorid as 'monitorid', t1.FACTORID as 'factorid'," +
                    " t2.FACTORNAME as 'factorname',t1.ITEMUNIT as 'unit' from iplat4j.t_hc_manualvalue t1" +
                    " left join iplat4j.t_ha_factor t2 on t1.FACTORID=t2.FACTORID " +
                    " left join iplat4j.t_ha_site t3 on t1.siteid=t3.SITEID " +
                    " where t1.monitorid='"+classifyid+"'"+" and itemtime>='"+starttime+"'" +
                    " and itemtime<='"+endtime+"'";
            if(StringUtils.isNotBlank(departmentid)&&!"%".equals(departmentid)){
                factorSql += " and t3.departid='"+departmentid+"'";
            }
            if(StringUtils.isNotBlank(siteid)&&!"%".equals(siteid)){
                factorSql += " and t1.siteid='"+siteid+"'";
            }
            if ("1".equals(isplaninner)) {
                factorSql += " and t1.planid like '%"+isplaninner+"'";
            } else if ("1".equals(isplanout)) {
                factorSql += " and t1.planid like '%"+isplanout+"'";
            }

			Map sqlmap = new HashMap();
			sqlmap.put("sqlMap", factorSql);
			List list = this.dao.query("DUHA01.query", sqlmap);
			block.addRows(list);
		} catch (Exception ex) {
			throw ex;
		}
		return block;
	}

	// 根据因子信息绑定grid头信息
	private EiBlock getDataHeaderByFactors(EiBlock factorsBlock) {
		EiBlock block = new EiBlock("ardata");
		block.set("count", 1);
		EiBlockMeta metadata = new EiBlockMeta();
		EiColumn eiColumn = null;
		eiColumn = new EiColumn("siteid");
		eiColumn.setDescName("监测点ID");
		eiColumn.setVisible(false);
		metadata.addMeta(eiColumn);

		eiColumn = new EiColumn("itemtime");
		eiColumn.setDescName("日期");
		eiColumn.setWidth(140);
		metadata.addMeta(eiColumn);

		eiColumn = new EiColumn("sitename");
		eiColumn.setDescName("监测点名称");
		eiColumn.setWidth(300);
		metadata.addMeta(eiColumn);
		
		eiColumn = new EiColumn("planname");
		eiColumn.setDescName("计划名称");
		eiColumn.setWidth(350);
		metadata.addMeta(eiColumn);

		eiColumn = new EiColumn("offline");
		eiColumn.setVisible(false);
		eiColumn.setDescName("判断是否有数据");
		metadata.addMeta(eiColumn);

		try {
			if (factorsBlock != null && factorsBlock.getRowCount() > 0) {
				for (int i = 0; i < factorsBlock.getRowCount(); i++) {
					String temfactorid = StrUtil.trimToString(factorsBlock.getRow(i).get("factorid"));
					String temfactorname = StrUtil.trimToString(factorsBlock.getRow(i).get("factorname"));
					String temzsfactorname = "";
					eiColumn = new EiColumn("ar" + temfactorid);
					if (StringUtils.isNotBlank(StrUtil.trimToString(factorsBlock.getRow(i).get("unit")))) {
						if ("01".equals(temfactorid) || "02".equals(temfactorid) || "03".equals(temfactorid)) {
							temzsfactorname = temfactorname + "(折算)" + "<br/>" + "("
									+ StrUtil.trimToString(factorsBlock.getRow(i).get("unit")) + ")";
						}
						temfactorname = temfactorname + "<br/>" + "("
								+ StrUtil.trimToString(factorsBlock.getRow(i).get("unit")) + ")";
					}
					eiColumn.setWidth(153);
					eiColumn.setDescName(temfactorname);
					metadata.addMeta(eiColumn);

					eiColumn = new EiColumn("ar" + temfactorid + "limit");
					eiColumn.setVisible(false);
					eiColumn.setDescName("限值");
					metadata.addMeta(eiColumn);

					if ("01".equals(temfactorid) || "02".equals(temfactorid) || "03".equals(temfactorid)) {
						eiColumn = new EiColumn("arzs" + temfactorid);
						eiColumn.setWidth(160);
						eiColumn.setDescName(temzsfactorname);
						metadata.addMeta(eiColumn);

						eiColumn = new EiColumn("arzs" + temfactorid + "limit");
						eiColumn.setVisible(false);
						eiColumn.setDescName("折算限值");
						metadata.addMeta(eiColumn);
					}

				}
				block.setBlockMeta(metadata);
			}
		} catch (Exception e) {
			throw e;
		}
		return block;
	}

	// 加载手工数据
	private EiInfo getDataByFactors(EiInfo outInfo, EiBlock factorsBlock, List siteList, String starttime,
                                    String endtime, String planid) throws Exception {
		EiBlock block = outInfo.getBlock("ardata");
		List<Map> listdata = new ArrayList<>();
		try {
			if (siteList.size() > 0) {
				for (int k = 0; k < siteList.size(); k++) {
					Map sitemap = (HashMap) siteList.get(k);
					String temsiteid = StrUtil.trimToString(sitemap.get("SITEID"));
					String temsitename = StrUtil.trimToString(sitemap.get("SITENAME"));
					String templanname = StrUtil.trimToString(sitemap.get("PLANNAME"));
					String ITEMTIME = StrUtil.trimToString(sitemap.get("ITEMTIME"));

					if (temsiteid != null && !StringUtils.isBlank(temsiteid)) {
						if (factorsBlock != null && factorsBlock.getRowCount() > 0) {
							String factorSql = "select ";
							for (int i = 0; i < factorsBlock.getRowCount(); i++) {
								String temfactorid = StrUtil.trimToString(factorsBlock.getRow(i).get("factorid"));
								factorSql += " max( CASE WHEN t5.FACTORID = '" + temfactorid
										+ "' THEN t5.itemvalue END ) T" + temfactorid.trim() + ",";
								factorSql += " max( CASE WHEN t5.FACTORID = '" + temfactorid
										+ "' THEN t5.itemlimit END ) T" + temfactorid.trim() + "limit" + ",";
								// 折算值
								if ("01".equals(temfactorid) || "02".equals(temfactorid) || "03".equals(temfactorid)) {
									factorSql += " max( CASE WHEN t5.FACTORID = '" + temfactorid
											+ "' THEN t5.itemvaluezs END ) TZS" + temfactorid.trim() + ",";
									factorSql += " max( CASE WHEN t5.FACTORID = '" + temfactorid
											+ "' THEN t5.itemlimit END ) TZS" + temfactorid.trim() + "limit" + ",";
								}
							}
							factorSql += "t5.itemtime from (SELECT t1.FACTORID,t1.itemtime,t1.itemvalue ,t1.itemvaluezs ,t1.itemlimit  "
									+ "from "+DbSchema+".T_HC_MANUALVALUE t1" + "  where t1.siteid='" + temsiteid
									+ "' and t1.planid like'%" + planid + "' and t1.itemtime ='" + ITEMTIME
									+ "') t5 group by t5.itemtime order by t5.itemtime asc";

							Map sqlmap = new HashMap();
							sqlmap.put("sqlMap", factorSql);

							List list = this.dao.query("DUHA01.query", sqlmap);
							if (list != null && list.size() > 0) {
								for (int i = 0; i < list.size(); i++) {
									Map mapdata = (HashMap) list.get(i);
									Map map = new HashMap();
									map.put("siteid", temsiteid);
									map.put("sitename", temsitename);
									map.put("planname", templanname);
									String temitemtime = StrUtil.trimToString(mapdata.get("itemtime"));
									map.put("itemtime", temitemtime);
									for (int j = 0; j < factorsBlock.getRowCount(); j++) {
										String temfactorid = StrUtil
												.trimToString(factorsBlock.getRow(j).get("factorid"));
										map.put("ar" + temfactorid,
												StrUtil.trimToString(mapdata.get("T" + temfactorid.toUpperCase())));
										map.put("ar" + temfactorid + "limit", StrUtil
												.trimToString(mapdata.get("T" + temfactorid.toUpperCase() + "LIMIT")));
										// map.put("ar" + temfactorid + "limit", getFactorLimitBySite(temfactorid,
										// temsiteid));
										if ("01".equals(temfactorid) || "02".equals(temfactorid)
												|| "03".equals(temfactorid)) {
											map.put("arzs" + temfactorid, StrUtil
													.trimToString(mapdata.get("TZS" + temfactorid.toUpperCase())));
											map.put("arzs" + temfactorid + "limit", StrUtil.trimToString(
													mapdata.get("TZS" + temfactorid.toUpperCase() + "LIMIT")));
										}

									}
									listdata.add(map);
								}
							} else {
								Map map = new HashMap();
								map.put("siteid", temsiteid);
								map.put("sitename", temsitename);
								map.put("planname", templanname);
								map.put("itemtime", "");
								map.put("offline", "1");
								for (int j = 0; j < factorsBlock.getRowCount(); j++) {
									String temfactorid = StrUtil.trimToString(factorsBlock.getRow(j).get("factorid"));
									map.put("ar" + temfactorid, "");
									map.put("ar" + temfactorid + "limit", "");
								}
								listdata.add(map);
							}
						}
					}
				}
			}
			block.addRows(listdata);
			outInfo.setBlock(block);
		} catch (Exception e) {
			outInfo.setBlock(block);
			throw e;
		}
		return outInfo;
	}

	// 根据因子和站点获取对应的限值
	private String getFactorLimitBySite(String factorid, String siteid) {
		try {
			String limit = "";
			String factorSql = "select * from " + DbSchema + ".T_HC_MANFACTOR_LIMIT " + " where FACTORID = '" + factorid
					+ "' and SITEID = '" + siteid + "' ";

			Map sqlmap = new HashMap();
			sqlmap.put("sqlMap", factorSql);

			List list = this.dao.query("DUHA01.query", sqlmap);
			if (list != null && list.size() > 0) {
				String temhighlimit = StrUtil.trimToString(((HashMap) list.get(0)).get("HIGHLIMIT"));
				String temlowlimit = StrUtil.trimToString(((HashMap) list.get(0)).get("LOWLIMIT"));
				if (!StringUtils.isBlank(temhighlimit) && !StringUtils.isBlank(temlowlimit)) {
					limit = temlowlimit + "-" + temhighlimit;
				} else {
					limit = temhighlimit;
				}
			}

			return limit;
		} catch (Exception ex) {
			throw ex;
		}

	}
}
