/**
 *
 */
package com.baosight.sggk.du.hb.service;

import com.baosight.sggk.common.du.domain.Tduhb01;
import com.baosight.iplat4j.core.ei.*;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceDUHB01 extends ServiceEPBase {

	private static final Logger logger = Logger.getLogger(ServiceDUHB01.class);

	public EiInfo initLoad(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();

		EiBlock block = new EiBlock(EiConstant.resultBlock);
		block.addBlockMeta(new Tduhb01().eiMetadata);
		outInfo.setBlock(block);

		// 添加标志
		EiBlockMeta eiMetadata = new EiBlockMeta();
		EiColumn eiColumn = new EiColumn("flagid");
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

		// 添加类型
		eiMetadata = new EiBlockMeta();
		eiColumn = new EiColumn("typeid");
		eiMetadata.addMeta(eiColumn);
		eiColumn = new EiColumn("typename");
		eiMetadata.addMeta(eiColumn);
		EiBlock typeblock = new EiBlock("typeList");
		typeblock.setBlockMeta(eiMetadata);
		List<Map> list2 = new ArrayList<>();
		Map<String, String> map3 = new HashMap<String, String>();
		map3.put("typeid", "D1");
		map3.put("typename", "厂部");
		list2.add(map3);
		Map<String, String> map4 = new HashMap<String, String>();
		map4.put("typeid", "P1");
		map4.put("typename", "工序");
		list2.add(map4);
		typeblock.setRows(list2);
		outInfo.setBlock(typeblock);
		outInfo.set("inqu_status-0-parentid", "root");

		outInfo.setMsg("页面加载完成");
		return outInfo;
	}

	public EiInfo query(EiInfo inInfo) {
		inInfo.set("inqu_status-0-type", "D1");//厂部
		EiInfo outInfo = super.query(inInfo, "tduhb01.queryInfo", new Tduhb01());
		EiBlock resultblock = outInfo.getBlock(EiConstant.resultBlock);

		for (int i = 0; i < outInfo.getBlock("result").getRowCount(); i++) {
			String parentid = outInfo.getCell("result", i, "parentid").toString().trim();
			if ("root".equals(parentid)) {
				outInfo.setCell("result", i, "parentname", "中南钢铁");
			}
		}
		return outInfo;
	}
	
	/**
	 * 通用方法
	 * @param inInfo
	 * @return
	 */
	public EiInfo queryCommon(EiInfo inInfo) {
		EiInfo outInfo=new EiInfo();
		EiBlock departmentBlock=outInfo.addBlock("depart");
		departmentBlock.addBlockMeta((new Tduhb01()).eiMetadata);
		Map<String, Object> map=new HashMap<>();
		try {
			String totalplan=inInfo.getString("totalplan");
			String level=inInfo.getString("level");
			String type=inInfo.getString("type");
			String status=inInfo.getString("status");
			map.put("type", StringUtils.isBlank(type)?"D1":type);// D1:厂部   	P1:工序
			map.put("status", StringUtils.isBlank(status)?"1":status);//1:启用
			map.put("level", StringUtils.isBlank(level)?"1":level);//1:一级厂部
			map.put("totalplan", StringUtils.isBlank(totalplan)?"1":totalplan);//标志位
			List<Tduhb01> list=dao.query("tduhb01.queryInfo",map);
			departmentBlock.addRows(list);
		} catch (Exception e) {
			throw e;
		}
		return outInfo;
	}

	public EiInfo delete(EiInfo inInfo) {
		try {
			for (int i = 0; i < inInfo.getBlock("result").getRowCount(); i++) {
				String departmentid = StringUtils.isBlank((String) inInfo.getCell("result", i, "departmentId")) ? "空"
						: (String) inInfo.getCell("result", i, "departmentId");
				HashMap params = new HashMap();
				params.put("parentid", departmentid);
				List<Tduhb01> departlist = dao.query("tduhb01.query", params);
				int countDep = 0;
				int countPro = 0;
				if (departlist.size() > 0) {
					for (int j = 0, length = departlist.size(); j < length; j++) {
						String type = departlist.get(j).getType();
						if ("D1".equals(type)) {
							countDep++;
						} else if ("P1".equals(type)) {
							countPro++;
						}
					}

					if (countDep > 0 && countPro > 0) {
						inInfo.setStatus(-1);
						inInfo.setMsg("记录下面有子级厂部和工序，请先删除子级厂部和工序");
						return inInfo;
					} else if (countDep == 0 && countPro > 0) {
						inInfo.setStatus(-1);
						inInfo.setMsg("记录下面有工序，请先删除对应的工序");
						return inInfo;
					} else if (countDep > 0 && countPro == 0) {
						inInfo.setStatus(-1);
						inInfo.setMsg("记录下面有子级厂部，请先删除子级厂部");
						return inInfo;
					}

				}
			}
		} catch (Exception ex) {
			inInfo.setStatus(-1);
			inInfo.setMsg(ex.toString());
			return inInfo;
		}

		EiInfo outInfo = super.delete(inInfo, "tduhb01.delete");
		EiInfo out = query(inInfo);
		out.setMsg(outInfo.getMsg());
		out.setDetailMsg(outInfo.getDetailMsg());
		return out;
	}

}
