/**
 *
 */
package com.baosight.sggk.du.hb.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.sggk.common.du.domain.Tduhb05;
import com.baosight.sggk.common.du.domain.Tduhb18;
import com.baosight.sggk.common.du.domain.Tduhb20;
import com.baosight.sggk.util.StrUtil;

public class ServiceDUHB19 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHB19.class);

	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();

//		  EiBlock block = new EiBlock(EiConstant.resultBlock);
//		  block.addBlockMeta(new Tduhb18().eiMetadata);
//		  outInfo.setBlock(block);

		EiBlock block = new EiBlock("portList");
		Map params = new HashMap();
		params.put("monitorid", "02");
		List result = this.dao.query("tduhb05.query", params);
		block.setBlockMeta(new Tduhb05().eiMetadata);
		block.setRows(result);
		outInfo.addBlock(block);

		outInfo.setMsg("页面加载完成");
		return outInfo;
	}
	
	public EiInfo queryinfo(EiInfo inInfo) {
		EiInfo outInfo=new EiInfo();
		inInfo.set("inqu_status-0-devicetype", "02");
		String portidinqu=inInfo.getString("inqu_status-0-portid");
		String portnameinqu=inInfo.getString("inqu_status-0-portname");
		//EiInfo outInfo = super.query(inInfo, "tduhb18.query", new Tduhb18());
//		EiInfo outInfo = super.query(inInfo, "tduhb18.query", new Tduhb18(),false, null, EiConstant.queryBlock,
//			"oldresult", "oldresult");
		EiBlock oldresult=outInfo.addBlock("oldresult");
		oldresult.setBlockMeta((new Tduhb18()).eiMetadata);
		List<Tduhb18>list= dao.query("tduhb18.query", inInfo.getBlock("inqu_status").getRow(0), 0, -999999);
		oldresult.addRows(list);
		outInfo.set("result-offset", 0);
		outInfo.set("result-limit", 1000);
		for (int i = 0; i < outInfo.getBlock("oldresult").getRowCount(); i++) {
			//修改展示环保治理设施信息id，名称
			String processid = outInfo.getCellStr("oldresult", i, "processid");
			if (StrUtil.paramIsNotNullOrEmpty(processid)) {
				String resProcessid = "";
				String resProcessname = "";
				String resPortid = "";
				String resPortname = "";
				String resDescription = "";
				List<String> facilityIdList = new ArrayList<>(Arrays.asList(processid.split(",")));
				List<String> codeList = new ArrayList<>();
				List<String> nameList = new ArrayList<>();
				
				List<String> portCodeList = new ArrayList<>();
				List<String> portNameList = new ArrayList<>();
				List<String> resDescriptionList = new ArrayList<>();
				for (String facilityid : facilityIdList) {
					Map<String, String> pMap = new HashMap<>();
					pMap.put("facilityid", facilityid);
					//根据设施id查询治理设施数据信息
					List<Tduhb20> listhb20 = this.dao.query("tduhb20.query",pMap);
					if (StrUtil.listIsNotNullOrEmpty(listhb20)) {
						String facilitycode = StrUtil.isNullToStr(listhb20.get(0).getFacilitycode());
						String facilityname = StrUtil.isNullToStr(listhb20.get(0).getFacilityname());
						if (!codeList.contains(facilitycode)) {
							if (StringUtils.isNotBlank(facilitycode)) {
								codeList.add(facilitycode);
							}
							if (StringUtils.isNotBlank(facilityname)) {
								nameList.add(facilityname);	
							}
						}
					}
					
					//根据设施id，查询排口信息
					List<Map<String,String>> list21 = this.dao.query("tduhb21.queryByHb18",pMap);
					if (StrUtil.listIsNotNullOrEmpty(list21)) {
						for (int j = 0; j < list21.size(); j++) {
							String portCode = list21.get(j).get("dischargeportcode");
							String portname = list21.get(j).get("dischargeportname");
							String description = list21.get(j).get("description");
							if (!portCodeList.contains(portCode)) {
								if (StringUtils.isNotBlank(portCode)) {
									portCodeList.add(portCode);
								}
								if (StringUtils.isNotBlank(portname)) {
									portNameList.add(portname);
								}
								if (StringUtils.isNotBlank(description)) {
									resDescriptionList.add(description);
								}
							}
						}
					}
						
				}
				
				if (StrUtil.listIsNotNullOrEmpty(codeList)) {
					resProcessid = StringUtils.join(codeList , ",");
					resProcessname = StringUtils.join(nameList , ",");
				}
				
				outInfo.setCell("oldresult", i, "processid", resProcessid);
				outInfo.setCell("oldresult", i, "processname", resProcessname);
				
				if (StrUtil.listIsNotNullOrEmpty(portCodeList)) {
					resPortid = StringUtils.join(portCodeList , ",");
					resPortname = StringUtils.join(portNameList , ",");
					resDescription = StringUtils.join(resDescriptionList , ";");
				}
				
				outInfo.setCell("oldresult", i, "portid", resPortid);
				outInfo.setCell("oldresult", i, "portname", resPortname);
				outInfo.setCell("oldresult", i, "description", resDescription);
			}
				
		}
		//根据排放口编号、名称筛选
		EiBlock result=outInfo.addBlock("result");
		result.setBlockMeta((new Tduhb18()).eiMetadata);
		if (outInfo.getBlock("oldresult").getRowCount()>0) {
			for (int j = 0; j < outInfo.getBlock("oldresult").getRowCount(); j++) {
				
				String portidstr=outInfo.getBlock("oldresult").getCellStr(j, "portid");
				String portnamestr=outInfo.getBlock("oldresult").getCellStr(j, "portname");
				//剔除不符合条件的行
				
				if ((StringUtils.isNotBlank(portidinqu)&& !portidstr.contains(portidinqu))||
					(StringUtils.isNotBlank(portnameinqu)&& !portnamestr.contains(portnameinqu))) {
					
				}else {
					result.addRow(outInfo.getBlock("oldresult").getRow(j));
				}
				
			}
		}
		if (outInfo.getBlock("result").getRowCount()>0) {
			outInfo.setMsg("查询成功，本次查询返回"+outInfo.getBlock("result").getRowCount()+"条记录！");
		}else {
			outInfo.setMsg("未查询到记录！");
		}
		return outInfo;
		
	}

//	public EiInfo query(EiInfo inInfo) {
//		inInfo.set("inqu_status-0-devicetype", "02");
//		EiInfo outInfo = super.query(inInfo, "tduhb18.query", new Tduhb18());
//		for (int i = 0; i < outInfo.getBlock("result").getRowCount(); i++) {
//			//修改展示环保治理设施信息id，名称
//			String processid = outInfo.getCellStr("result", i, "processid");
//			if (StrUtil.paramIsNotNullOrEmpty(processid)) {
//				String resProcessid = "";
//				String resProcessname = "";
//				String resPortid = "";
//				String resPortname = "";
//				List<String> facilityIdList = new ArrayList<>(Arrays.asList(processid.split(",")));
//				List<String> codeList = new ArrayList<>();
//				List<String> nameList = new ArrayList<>();
//				List<String> portCodeList = new ArrayList<>();
//				List<String> portNameList = new ArrayList<>();
//				for (String facilityid : facilityIdList) {
//					Map<String, String> pMap = new HashMap<>();
//					pMap.put("facilityid", facilityid);
//					List<Tduhb20> listhb20 = this.dao.query("tduhb20.query",pMap);
//					if (StrUtil.listIsNotNullOrEmpty(listhb20)) {
//						String facilitycode = StrUtil.isNullToStr(listhb20.get(0).getFacilitycode());
//						String facilityname = StrUtil.isNullToStr(listhb20.get(0).getFacilityname());
////						codeList.add(facilitycode);
////						nameList.add(facilityname);	
//						if (!codeList.contains(facilitycode)) {
//							if (StringUtils.isNotBlank(facilitycode)) {
//								codeList.add(facilitycode);
//							}
//							if (StringUtils.isNotBlank(facilityname)) {
//								nameList.add(facilityname);	
//							}
//						}
//					}
//					
//					//根据设施id，查询排口信息
//					List<Map<String,String>> list21 = this.dao.query("tduhb21.queryByHb18",pMap);
//					if (StrUtil.listIsNotNullOrEmpty(list21)) {
//						for (int j = 0; j < list21.size(); j++) {
//							String portCode = list21.get(j).get("dischargeportcode");
//							String portname = list21.get(j).get("dischargeportname");
//							if (!portCodeList.contains(portCode)) {
//								portCodeList.add(portCode);
//								portNameList.add(portname);
//							}
//						}
//					}
//				}
//				
//				if (StrUtil.listIsNotNullOrEmpty(codeList)) {
//					resProcessid = StringUtils.join(codeList , ",");
//					resProcessname = StringUtils.join(nameList , ",");
//				}
//				
//				outInfo.setCell("result", i, "processid", resProcessid);
//				outInfo.setCell("result", i, "processname", resProcessname);
//				
//				if (StrUtil.listIsNotNullOrEmpty(portCodeList)) {
//					resPortid = StringUtils.join(portCodeList , ",");
//					resPortname = StringUtils.join(portNameList , ",");
//				}
//				
//				outInfo.setCell("result", i, "portid", resPortid);
//				outInfo.setCell("result", i, "portname", resPortname);
//			}
//				
//		}
//		return outInfo;
//	}

	public EiInfo delete(EiInfo inInfo) {
		EiInfo outInfo = super.delete(inInfo, "tduhb18.delete");
		EiInfo out = queryinfo(inInfo);
		out.setMsg(outInfo.getMsg());
		out.setDetailMsg(outInfo.getDetailMsg());
		return out;
	}

	public EiInfo queryFacilityInfo(EiInfo inInfo) {
		StringBuffer buffer = new StringBuffer();
		try {
			String processid = StringUtils.isBlank((String) inInfo.get("processid")) ? "空"
					: (String) inInfo.get("processid");
			Map querymap = new HashMap();
			querymap.put("facilitycode", processid);
			List list = this.dao.query("tduhb20.query", querymap);
			if (list.size() > 0) {
				inInfo.set("inqu_status-0-flag", "1");
				inInfo.set("inqu_status-0-facilityid", ((Tduhb20) list.get(0)).getFacilityid());
			}
		} catch (Exception ex) {
			buffer.insert(0, "记录查询失败\n" + ex.toString());
			inInfo.setStatus(-1);
		}
		inInfo.setMsg(buffer.toString());

		return inInfo;
	}

}
