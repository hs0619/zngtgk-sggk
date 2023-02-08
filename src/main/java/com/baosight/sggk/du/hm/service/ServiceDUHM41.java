/**
 *
 * Generate time : 2021-08-11 14:23:25
 */
package com.baosight.sggk.du.hm.service;

import com.baosight.sggk.du.hm.domain.DUHM41;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceDUHM41 extends ServiceEPBase {

	public EiInfo initLoad(EiInfo inInfo) {
		inInfo.addBlock(EiConstant.resultBlock);
		inInfo.getBlock(EiConstant.resultBlock).addBlockMeta(new DUHM41().eiMetadata);

		EiBlock qstatusBlock = inInfo.addBlock("qsolidType");
		qstatusBlock.setBlockMeta((new DUHM41()).eiMetadata);
		List<DUHM41> list2 =this.dao.query("DUHM41.querySolidType",new HashMap<>());
		DUHM41 duhm41 = new DUHM41();
		duhm41.setSolidCodes("固废名称");
		duhm41.setSolidName("固废名称");
		list2.add(0,duhm41);
		qstatusBlock.addRows(list2);
		return inInfo;
	}

	public EiInfo query(EiInfo inInfo) {
		Map<String,Object> params = new HashMap<>();
		params.put("node",inInfo.getCellStr("inqu_status",0,"node"));
		List <Map<String,String>>query = super.getDao().query("DUHM41.query", params);
		inInfo.addBlock("result").setBlockMeta(new DUHM41().eiMetadata);
		inInfo.getBlock("result").setRows(query);
		String pEname = inInfo.getCellStr(EiConstant.queryBlock, 0, "node");
		inInfo.getBlocks().put(pEname, inInfo.getBlock(EiConstant.resultBlock));
		inInfo.getBlocks().remove(EiConstant.resultBlock);
		return inInfo;
	}

	public EiInfo search(EiInfo inInfo) {
		String Sname = inInfo.getCellStr("inqu_status",0,"Sname");
		String solidProduct = inInfo.getCellStr("inqu_status",0,"solidProduct");
		String ironMud = inInfo.getCellStr("inqu_status",0,"ironMud");
		if (Sname.equals("")){
			if("%".equals(solidProduct)){
				inInfo.setCell("inqu_status",0,"solidProduct",null);
			}
			if("%".equals(ironMud)){
				inInfo.setCell("inqu_status",0,"ironMud",null);
			}
//			Map map = inInfo.getBlock("result").getAttr();
//			map.put("limit",100);
			EiInfo outInfo=super.query(inInfo,"DUHM41.search",new DUHM41());
			//List list = this.dao.query("DUHM41.search",new HashMap<>(),1,100);
			return outInfo;
		}else{
			if("%".equals(solidProduct)){
				inInfo.setCell("inqu_status",0,"solidProduct",null);
			}
			if("%".equals(ironMud)){
				inInfo.setCell("inqu_status",0,"ironMud",null);
			}
			String solidName = "%"+inInfo.getCellStr("inqu_status",0,"Sname")+"%";
			inInfo.setCell("inqu_status",0,"solidName",solidName);
			EiInfo outInfo=super.query(inInfo,"DUHM41.searchA",new DUHM41());
			return outInfo;
		}
	}


	private String getID() {
		try {
			String logicid = "S";
			List list = this.dao.query("DUHM41.queryId", new HashMap<String, String>());
			int count = 1;
			if (list != null && list.size() > 0) {
				count = Integer.parseInt(((DUHM41) list.get(0)).getSolidId().substring(1)) + 1;
				logicid = logicid + String.format("%05d", count);
			} else {
				logicid = "S00001";
			}
			return logicid;
		} catch (Exception ex) {
			throw ex;
		}
	}




	public EiInfo delete(EiInfo inInfo) {
		StringBuffer buffer = new StringBuffer();
		List<Map<String,String>> list = inInfo.getBlock("result").getRows();
		Map<String,String> map = new HashMap<>();
		//判断是否存在子集
		for (int i=0;i<list.size();i++){
			String solidType = list.get(i).get("solidName");
			map.put("solidType",solidType);
			List solidTypeList = this.dao.query("DUHM41.query",map);
			if(solidTypeList.size()>0){
				buffer.append("删除失败,必须先删除子集");
				inInfo.setStatus(-1);
				inInfo.setMsg(buffer.toString());
				return inInfo;
			}
		}
		inInfo =  super.delete(inInfo, "DUHM41.delete");
		buffer.append("删除成功");
		inInfo.setStatus(1);
		inInfo.setMsg(buffer.toString());
		return inInfo;
	}

	public EiInfo update(EiInfo inInfo) {

		return super.update(inInfo, "DUHM41.update");
	}

	public EiInfo insert(EiInfo inInfo) {
		inInfo.getBlock("result").setCell(0,"solidState","启用");
		String outinfo=getID();
		inInfo.getBlock("result").setCell(0,"solidId",outinfo);
		String ptype=String.valueOf(inInfo.getRow("result", 0).get("solidType"));

		if(ptype.equals(" ")){
			return insert(inInfo, "DUHM41.insert");
		}
		else if(ptype.equals("固废名称")){
			return insert(inInfo, "DUHM41.insert1");
		}else{
			return insert(inInfo, "DUHM41.insert2");
		}

	}

}
