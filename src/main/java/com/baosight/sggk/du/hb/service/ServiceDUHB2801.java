package com.baosight.sggk.du.hb.service;

import java.util.HashMap;
import java.util.List;

import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.ef.ui.tree.MenuTreeService;

public class ServiceDUHB2801 extends MenuTreeService {

	private EiBlockMeta eiMetadata = new EiBlockMeta();

    @Override
    public List getTopNodes() {
    	HashMap params = new HashMap();
        String stmt = "tduhb0101.queryTopNodes";
        List ret = dao.query(stmt, params);
        return ret;
    }

    @Override
    public List getChildNodes(String parentid) {
        HashMap params = new HashMap();
        List ret = null;
        if("root".equals(parentid)) {
        	params.put("parentid", parentid);
        	String stmt = "tduhb0101.queryChildNodes";
            ret = dao.query(stmt, params);
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

}
