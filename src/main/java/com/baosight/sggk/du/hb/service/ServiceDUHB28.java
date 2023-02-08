package com.baosight.sggk.du.hb.service;

import com.baosight.sggk.common.du.domain.Tduhb01;
import com.baosight.sggk.du.hb.domain.DUHB28;
import com.baosight.sggk.du.hb.domain.DUHB2802;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceDUHB28 extends ServiceEPBase {

	 public EiInfo initLoad(EiInfo inInfo) {
		 EiInfo outInfo = new EiInfo();
		  
		  EiBlock block = new EiBlock(EiConstant.resultBlock);
		  block.addBlockMeta(new Tduhb01().eiMetadata);
		  outInfo.setBlock(block);
		// 单位
			EiBlock qdepartBlock = outInfo.addBlock("qdepart");
			qdepartBlock.setBlockMeta((new Tduhb01()).eiMetadata);
			Map<String, Object> levelMap = new HashMap<>();
			levelMap.put("level", 1);
			List list1 = this.dao.query("tduhb01.queryByLevel", levelMap);
			
			qdepartBlock.addRows(list1);
		  outInfo.setMsg("页面加载完成！");
	        return outInfo;
	    }
	 public EiInfo query( EiInfo inInfo )
		{
			//inInfo.set("inqu_status-0-type", "D1");
			
			//String s = (String) inInfo.get("parentid");
		 	String userId = (String) inInfo.get("inqu_status-0-userId");
		 	if("null".equals(userId)) {
		 		inInfo.set("inqu_status-0-userId", null);
		 	}else {
		 		inInfo.set("inqu_status-0-userId", userId);
		 	}
			EiInfo outInfo = super.query(inInfo,"DUHB28.queryByDepartmentId",new DUHB28());
	        EiBlock resultblock = outInfo.getBlock(EiConstant.resultBlock);
	        int j = outInfo.getBlock( "result" ).getRowCount();
			for( int i = 0; i < outInfo.getBlock( "result" ).getRowCount(); i++ )
			{
				
				String loginName = (String) outInfo.getBlock("result").getCell(i, "userId");
				DUHB2802 duhb2802 = queryByUserId(loginName);
				outInfo.setCell("result", i, "userName", duhb2802.getUserName());
				outInfo.setCell("result", i, "mobile", duhb2802.getMobile());
				outInfo.setCell("result", i, "email", duhb2802.getEmail());
//				String parentid = outInfo.getCell("result", i, "parentid").toString().trim();
//				if("root".equals(parentid)) {
//					outInfo.setCell("result", i, "parentname", "马钢股份");
//				}
			}

			return outInfo;
		}
	 
	//根据用户id查询用户相关信息
		public DUHB2802 queryByUserId(String loginName) {
			DUHB2802 duhb2802 = null;
			Map<String, String> map = new HashMap<String, String>();
			if(!StringUtils.isBlank(loginName)) {
				map.put("loginName", loginName);
				List<DUHB2802> list = this.dao.query("DUHB2802.query",map);
				if(list.size()>0 && list!=null) {
					duhb2802 = list.get(0);
				}
			}
			return duhb2802;
		}
		public EiInfo delete(EiInfo inInfo) {
			String rid = (String) inInfo.getBlock("result").getCell(0, "rid");
			EiInfo outInfo = super.delete(inInfo,"DUHB28.delete");
			
			EiInfo eiInfo = query(inInfo);
			eiInfo.setMsg(outInfo.getMsg());
			eiInfo.setDetailMsg(outInfo.getDetailMsg());
			return eiInfo;
		}
}
