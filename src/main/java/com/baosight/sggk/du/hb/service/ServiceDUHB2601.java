package com.baosight.sggk.du.hb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.ef.ui.tree.MenuTreeService;
import com.baosight.sggk.common.du.domain.Tduhb26;
import com.baosight.sggk.util.StrUtil;

public class ServiceDUHB2601 extends MenuTreeService {

	private EiBlockMeta eiMetadata = new EiBlockMeta();

    @Override
    public List getTopNodes() {
    	//return this.getChildNodes("root");
    	Map<String, String> pMap = new HashMap<>();
    	List topList = this.dao.query("tduhb2601.queryTopNodes",pMap);
    	return topList;
    }

    /**
     * 点击上级菜单的时候，EFTree插件会获取父级菜单的id，传递到方法中，根据父级id,可以查询到子级菜单
     */
    @Override
    public List getChildNodes(EiInfo inInfo, String parentid) {
    	String personId = inInfo.getString("personId");
    	Map<String, String> personMap = new HashMap<>();
    	personMap.put("personid", personId);
    	List<Tduhb26> list26 = this.dao.query("tduhb26.query",personMap);
    	
        HashMap params = new HashMap();
        params.put("parentid", parentid);
        String stmt = "tduhb2601.queryChildNodes";
        List<Map> ret = dao.query(stmt, params);
        if (StrUtil.listIsNotNullOrEmpty(ret)) {
			for (int i = 0; i < ret.size(); i++) {
				String label = (String) ret.get(i).get("label");
				if (StrUtil.listIsNotNullOrEmpty(list26)) {
					for (int j = 0; j < list26.size(); j++) {
						String dischargeportid = list26.get(j).getDischargeportid();
						if (label.equals(dischargeportid)) {
							ret.get(i).put("checked", "true");
						}
					}
				}

			}
		}
        return ret;
    }

    @Override
    public EiBlockMeta initMetaData() {
        if(this.eiMetadata == null) {
            this.eiMetadata = new EiBlockMeta();
            EiColumn eiColumn = new EiColumn("label");
            eiColumn.setDescName("label");
            eiColumn.setNullable(false);
            eiColumn.setPrimaryKey(false);
            this.eiMetadata.addMeta(eiColumn);
            eiColumn = new EiColumn("text");
            eiColumn.setDescName("text");
            eiColumn.setNullable(false);
            eiColumn.setPrimaryKey(false);
            this.eiMetadata.addMeta(eiColumn);
            eiColumn = new EiColumn("leaf");
            eiColumn.setDescName("leaf");
            eiColumn.setType(EiConstant.COLUMN_TYPE_NUMBER);
            eiColumn.setNullable(false);
            eiColumn.setPrimaryKey(false);
            this.eiMetadata.addMeta(eiColumn);
        }
        return eiMetadata;
    }

	@Override
	public List getChildNodes(String p) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
