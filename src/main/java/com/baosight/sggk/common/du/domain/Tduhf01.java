/**
* Generate time : 2021-01-26 17:29:22
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhf01
* 
*/
public class Tduhf01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String typeid = " ";		
    private String typename = " ";		
    private String createman = " ";		
    private String createtime = " ";		
    private String updateman = " ";		
    private String updatetime = " ";	
    private String flag = " ";		
    private String createid = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("typeid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(200);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("typename");
        eiColumn.setFieldLength(200);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createman");
        eiColumn.setFieldLength(200);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createtime");
        eiColumn.setFieldLength(200);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updateman");
        eiColumn.setFieldLength(200);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updatetime");
        eiColumn.setFieldLength(200);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("flag");
        eiColumn.setFieldLength(200);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("createid");
        eiColumn.setFieldLength(200);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public Tduhf01() {
        initMetaData();
    }

    /**
     * get the typeid 
     * @return the typeid
     */
    public String getTypeid() {
        return this.typeid;
    }

    /**
     * set the typeid 
     */
    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    /**
     * get the typename 
     * @return the typename
     */
    public String getTypename() {
        return this.typename;
    }

    /**
     * set the typename 
     */
    public void setTypename(String typename) {
        this.typename = typename;
    }

    /**
     * get the createman 
     * @return the createman
     */
    public String getCreateman() {
        return this.createman;
    }

    /**
     * set the createman 
     */
    public void setCreateman(String createman) {
        this.createman = createman;
    }

    /**
     * get the createtime 
     * @return the createtime
     */
    public String getCreatetime() {
        return this.createtime;
    }

    /**
     * set the createtime 
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    /**
     * get the updateman 
     * @return the updateman
     */
    public String getUpdateman() {
        return this.updateman;
    }

    /**
     * set the updateman 
     */
    public void setUpdateman(String updateman) {
        this.updateman = updateman;
    }

    /**
     * get the updatetime 
     * @return the updatetime
     */
    public String getUpdatetime() {
        return this.updatetime;
    }

    /**
     * set the updatetime 
     */
    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getCreateid() {
		return createid;
	}

	public void setCreateid(String createid) {
		this.createid = createid;
	}

	/**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setTypeid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typeid")), typeid));
        setTypename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("typename")), typename));
        setCreateman(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createman")), createman));
        setCreatetime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createtime")), createtime));
        setUpdateman(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updateman")), updateman));
        setUpdatetime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updatetime")), updatetime));
        setFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("flag")), flag));
        setCreateid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createid")), createid));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("typeid", StringUtils.toString(typeid, eiMetadata.getMeta("typeid")));
        map.put("typename", StringUtils.toString(typename, eiMetadata.getMeta("typename")));
        map.put("createman", StringUtils.toString(createman, eiMetadata.getMeta("createman")));
        map.put("createtime", StringUtils.toString(createtime, eiMetadata.getMeta("createtime")));
        map.put("updateman", StringUtils.toString(updateman, eiMetadata.getMeta("updateman")));
        map.put("updatetime", StringUtils.toString(updatetime, eiMetadata.getMeta("updatetime")));
        map.put("flag", StringUtils.toString(flag, eiMetadata.getMeta("flag")));
        map.put("createid", StringUtils.toString(createid, eiMetadata.getMeta("createid")));
        return map;
    }
}