/**
* Generate time : 2021-02-27 15:31:52
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.du.hc.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* DUHC23
* 
*/
public class DUHC23 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String siteid = " ";	
    private String sitename = " ";	
    private String monitorid = " ";	
    private String monitorname = " ";	
    private String factorid = " ";		
    private String factorname = " ";	
    
    private String itemtime = " ";		
    private String itemvalue = " ";		
    private String itemvaluezs = " ";		
    private String itemunit = " ";		
    private String itemlimit = " ";		
    private String planid = " ";//年计划编号，数据绑定使用
    private String planname = " ";
    private String plantype = " ";//计划类型：计划内/计划外
    private String overlimitinfo = " ";	//超标说明
    private String itemmark = " ";	//备注
    private String departmentid = " ";
    private String createid = " ";
    private String createname = " ";		
    private String createtime = " ";		
    private String updateid = " ";
    private String updatename = " ";	
    private String updatetime = " ";	
    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("siteid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("sitename");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("monitorid");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("monitorname");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("factorid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("factorname");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemtime");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemvalue");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemvaluezs");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemunit");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemlimit");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("acttime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("planid");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("planname");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("itemman");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("itemfilltime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        

        eiColumn = new EiColumn("isretest");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("retesttime");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("retestman");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("retestinfo");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("checkperson");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("checkinfo");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("checkflag");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("plantype");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("compare");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("overlimitinfo");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("itemmark");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("departmentid");
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("createid");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createname");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createtime");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updateid");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updatename");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updatetime");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

    }

    public String getSitename() {
		return sitename;
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
	}

	public String getMonitorname() {
		return monitorname;
	}

	public void setMonitorname(String monitorname) {
		this.monitorname = monitorname;
	}

	public String getFactorname() {
		return factorname;
	}

	public void setFactorname(String factorname) {
		this.factorname = factorname;
	}

	/**
     * the constructor
     */
    public DUHC23() {
        initMetaData();
    }

    /**
     * get the siteid 
     * @return the siteid
     */
    public String getSiteid() {
        return this.siteid;
    }

    /**
     * set the siteid 
     */
    public void setSiteid(String siteid) {
        this.siteid = siteid;
    }

    /**
     * get the monitorid 
     * @return the monitorid
     */
    public String getMonitorid() {
        return this.monitorid;
    }

    /**
     * set the monitorid 
     */
    public void setMonitorid(String monitorid) {
        this.monitorid = monitorid;
    }

    /**
     * get the factorid 
     * @return the factorid
     */
    public String getFactorid() {
        return this.factorid;
    }

    /**
     * set the factorid 
     */
    public void setFactorid(String factorid) {
        this.factorid = factorid;
    }

    /**
     * get the itemtime 
     * @return the itemtime
     */
    public String getItemtime() {
        return this.itemtime;
    }

    /**
     * set the itemtime 
     */
    public void setItemtime(String itemtime) {
        this.itemtime = itemtime;
    }

    /**
     * get the itemvalue 
     * @return the itemvalue
     */
    public String getItemvalue() {
        return this.itemvalue;
    }

    /**
     * set the itemvalue 
     */
    public void setItemvalue(String itemvalue) {
        this.itemvalue = itemvalue;
    }

    /**
     * get the itemvaluezs 
     * @return the itemvaluezs
     */
    public String getItemvaluezs() {
        return this.itemvaluezs;
    }

    /**
     * set the itemvaluezs 
     */
    public void setItemvaluezs(String itemvaluezs) {
        this.itemvaluezs = itemvaluezs;
    }

    /**
     * get the itemunit 
     * @return the itemunit
     */
    public String getItemunit() {
        return this.itemunit;
    }

    /**
     * set the itemunit 
     */
    public void setItemunit(String itemunit) {
        this.itemunit = itemunit;
    }

    /**
     * get the itemlimit 
     * @return the itemlimit
     */
    public String getItemlimit() {
        return this.itemlimit;
    }

    /**
     * set the itemlimit 
     */
    public void setItemlimit(String itemlimit) {
        this.itemlimit = itemlimit;
    }

    /**
     * get the itemmark 
     * @return the itemmark
     */
    public String getItemmark() {
        return this.itemmark;
    }

    /**
     * set the itemmark 
     */
    public void setItemmark(String itemmark) {
        this.itemmark = itemmark;
    }

    /**
     * get the planid 
     * @return the planid
     */
    public String getPlanid() {
        return this.planid;
    }

    /**
     * set the planid 
     */
    public void setPlanid(String planid) {
        this.planid = planid;
    }
    
    
    public String getPlanname() {
        return this.planname;
    }

   
    public void setPlanname(String planname) {
        this.planname = planname;
    }


    
    public String getPlantype() {
        return this.plantype;
    }

    public void setPlantype(String plantype) {
        this.plantype = plantype;
    }
    
    public String getOverlimitinfo() {
        return this.overlimitinfo;
    }

    public void setOverlimitinfo(String overlimitinfo) {
        this.overlimitinfo = overlimitinfo;
    }
	
    public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}
	
	

	public String getCreateid() {
		return createid;
	}

	public void setCreateid(String createid) {
		this.createid = createid;
	}

	public String getCreatename() {
		return createname;
	}

	public void setCreatename(String createname) {
		this.createname = createname;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getUpdateid() {
		return updateid;
	}

	public void setUpdateid(String updateid) {
		this.updateid = updateid;
	}

	public String getUpdatename() {
		return updatename;
	}

	public void setUpdatename(String updatename) {
		this.updatename = updatename;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	/**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setSiteid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("siteid")), siteid));
        setSitename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sitename")), sitename));
        setMonitorid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("monitorid")), monitorid));
        setMonitorname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("monitorname")), monitorname));
        setFactorid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("factorid")), factorid));
        setFactorname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("factorname")), factorname));
        setItemtime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemtime")), itemtime));
        setItemvalue(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemvalue")), itemvalue));
        setItemvaluezs(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemvaluezs")), itemvaluezs));
        setItemunit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemunit")), itemunit));
        setItemlimit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemlimit")), itemlimit));

        setPlanid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("planid")), planid));
        setPlanname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("planname")), planname));

        setPlantype(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("plantype")), plantype));
        setOverlimitinfo(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("overlimitinfo")), overlimitinfo));
        
        setItemmark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemmark")), itemmark));
        
        setDepartmentid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentid")), departmentid));
        
        setCreateid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createid")), createid));
        setCreatename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createname")), createname));
        setCreatetime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createtime")), createtime));
        
        setUpdateid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updateid")), updateid));
        setUpdatename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updatename")), updatename));
        setUpdatetime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updatetime")), updatetime));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("siteid", StringUtils.toString(siteid, eiMetadata.getMeta("siteid")));
        map.put("sitename", StringUtils.toString(sitename, eiMetadata.getMeta("sitename")));
        map.put("monitorid", StringUtils.toString(monitorid, eiMetadata.getMeta("monitorid")));
        map.put("monitorname", StringUtils.toString(monitorname, eiMetadata.getMeta("monitorname")));
        map.put("factorid", StringUtils.toString(factorid, eiMetadata.getMeta("factorid")));
        map.put("factorname", StringUtils.toString(factorname, eiMetadata.getMeta("factorname")));
        
        map.put("itemtime", StringUtils.toString(itemtime, eiMetadata.getMeta("itemtime")));
        map.put("itemvalue", StringUtils.toString(itemvalue, eiMetadata.getMeta("itemvalue")));
        map.put("itemvaluezs", StringUtils.toString(itemvaluezs, eiMetadata.getMeta("itemvaluezs")));
        map.put("itemunit", StringUtils.toString(itemunit, eiMetadata.getMeta("itemunit")));
        map.put("itemlimit", StringUtils.toString(itemlimit, eiMetadata.getMeta("itemlimit")));
        map.put("planid", StringUtils.toString(planid, eiMetadata.getMeta("planid")));
        map.put("planname", StringUtils.toString(planname, eiMetadata.getMeta("planname")));


        map.put("plantype", StringUtils.toString(plantype, eiMetadata.getMeta("plantype")));
        map.put("overlimitinfo", StringUtils.toString(overlimitinfo, eiMetadata.getMeta("overlimitinfo")));
        
        map.put("itemmark", StringUtils.toString(itemmark, eiMetadata.getMeta("itemmark")));
        
        map.put("departmentid", StringUtils.toString(departmentid, eiMetadata.getMeta("departmentid")));
        map.put("createid", StringUtils.toString(createid, eiMetadata.getMeta("createid")));
        map.put("createname", StringUtils.toString(createname, eiMetadata.getMeta("createname")));
        map.put("createtime", StringUtils.toString(createtime, eiMetadata.getMeta("createtime")));
        
        map.put("updateid", StringUtils.toString(updateid, eiMetadata.getMeta("updateid")));
        map.put("updatename", StringUtils.toString(updatename, eiMetadata.getMeta("updatename")));
        map.put("updatetime", StringUtils.toString(updatetime, eiMetadata.getMeta("updatetime")));
        return map;
    }
}