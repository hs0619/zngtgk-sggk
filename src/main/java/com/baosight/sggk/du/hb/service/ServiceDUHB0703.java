/**
 *
 */
package com.baosight.sggk.du.hb.service;

import com.baosight.sggk.du.hb.domain.DUHB0703;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.*;

public class ServiceDUHB0703 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHB0703.class);

	public static String DbSchema = ResourceBundle.getBundle("application").getString("hbSchema");

	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		String loginName = String.valueOf(UserSession.getLoginName());

		/*String sql = "select LOGIN_NAME,DEPARTMENT_ID,DEPARTMENT_NAME from " + DbSchema + ".VIEW_T_HA_XS_USER_EX where LOGIN_NAME = '" + loginName + "' ";
		Map sqlmap = new HashMap();
		sqlmap.put("sqlMap", sql);
		List list = dao.query("DUHC20.query", sqlmap);
		if(list.size() > 0) {
			String departname = ((HashMap<String, String>)list.get(0)).get("DEPARTMENT_NAME");
			outInfo.set("inqu_sattus-0-departname",departname);
		}else {
			outInfo.set("inqu_sattus-0-departname","");
		}*/

		return outInfo;
	}

	public EiInfo query(EiInfo inInfo) {

		return inInfo;
	}

	/**
	 *  图标加载
	 * @param inInfo
	 * @return
	 */
	public EiInfo initSelectData(EiInfo inInfo) {
//		//获取因子名称
//		List<Tduhb10> list = this.dao.query("tduhd40.queryTotalFactor", new HashMap());
//
//		Map params = new HashMap<>();
//		params.put("monitorid", "01");
//		//获取当前月份
//		String clock = dateConversion(new Date());
//		params.put("clock", "03");
//		// 获取因子个数
//		List<Tduhd41> dataList = dao.query("tduhd41.queryTotal", params, 0, -999999);
//		List countList = new ArrayList();
//		//计算不同因子个数
//		for (int i=0;i<list.size();i++){
//			DUHB0703 map = new DUHB0703();
//			 int count  = 0 ;
//			for (int y=0;y<dataList.size();y++){
//				if(list.get(i).getFactorid().equals(dataList.get(y).getFactorid())){
//					count++;
//					map.setMonitoringType(list.get(i).getFactorname());
//					map.setTotal(String.valueOf(count));
//				}
//			}
//			if(StringUtil.isNotEmpty(map.getMonitoringType())){
//				countList.add(map);
//			}
//		}
//		System.out.println(dataList);
//		EiBlock qstatusBlock = inInfo.addBlock("result");
//		qstatusBlock.setBlockMeta((new DUHB0703()).eiMetadata);
//		qstatusBlock.addRows(countList);
//		inInfo.set("data",countList);

		//改进
		//废气排扣
		Map params1 = new HashMap();
		params1.put("monitorid","01");
		params1.put("isformal","1");
		params1.put("dischargeClassify","其它");
		List dischargeportQas = this.dao.query("tduhb07.queryDischargeport", params1);
		//废水排扣
		params1.put("monitorid","02");
		params1.put("isformal",null);
		List dischargeportShui = this.dao.query("tduhb07.queryDischargeport", params1);
		//获取在线的站点数

		params1.put("isonline","1");//在线
		List dataList1 = this.dao.query("tduhb07.querySum", params1);
		params1.put("isartificial","1");//人工
		params1.put("isonline",null);//人工
		List dataList2 = this.dao.query("tduhb07.querySum", params1);


		EiBlock qstatusBlock1 = inInfo.addBlock("result1");
		qstatusBlock1.setBlockMeta((new DUHB0703()).eiMetadata);
		inInfo.set("data1",dischargeportQas);
		inInfo.set("data2",dischargeportShui);
		inInfo.set("data3",dataList1);
		inInfo.set("data4",dataList2);
		return inInfo;
	}


	//时间转化
	public String dateConversion(Date currentTime){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
}
