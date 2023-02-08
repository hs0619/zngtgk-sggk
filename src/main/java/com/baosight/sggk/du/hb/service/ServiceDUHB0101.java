package com.baosight.sggk.du.hb.service;

import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.ef.ui.tree.MenuTreeService;

import java.util.HashMap;
import java.util.List;


public class ServiceDUHB0101 extends MenuTreeService {

	private EiBlockMeta eiMetadata = new EiBlockMeta();

    @Override
    public List getTopNodes() {
    	return this.getChildNodes("root");
    }

    @Override
    public List getChildNodes(String parentid) {
        HashMap params = new HashMap();
        params.put("parentid", parentid);
        String stmt = "tduhb0101.queryChildNodes";
        List ret = dao.query(stmt, params);
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

