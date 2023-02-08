
package com.baosight.sggk.du.hb.domain;

import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;


public class DUHB40 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String loginname = " ";
    private String username = " ";
    private String departid = " ";
    private String departname = " ";					

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("loginname");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("username");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("departid");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("departname");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

    }

    /**
     * the constructor
     */
    public DUHB40() {
        initMetaData();
    }

    

    public String getLoginname() {
		return this.loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDepartid() {
		return this.departid;
	}

	public void setDepartid(String departid) {
		this.departid = departid;
	}

	public String getDepartname() {
		return this.departname;
	}

	public void setDepartname(String departname) {
		this.departname = departname;
	}

	/**
     * get the value from Map
     */
    public void fromMap(Map map) {

    	setLoginname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("loginname")), loginname));
    	setUsername(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("username")), username));
    	setDepartid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departid")), departid));
    	setDepartname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departname")), departname));
        
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("loginname",StringUtils.toString(loginname, eiMetadata.getMeta("loginname")));
        map.put("username",StringUtils.toString(username, eiMetadata.getMeta("username")));
        map.put("departid",StringUtils.toString(departid, eiMetadata.getMeta("departid")));
        map.put("departname",StringUtils.toString(departname, eiMetadata.getMeta("departname")));
        return map;
    }
}