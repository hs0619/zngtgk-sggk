/**
* Generate time : 2021-04-23 15:49:58
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.du.hc.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* DUHC2302
* 
*/
public class DUHC2302 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String planid = " ";		
    private String planname = " ";		
    private String monitorid = " ";		
    private String departmentid = " ";		
    private String siteid = " ";		
    private String clockyear = " ";
    private String plantype = " ";		
    private String licenserate = " ";		
    private String iscompare = " ";		
    private String mark = " ";		
    private String status = " ";		
    private String licensecount = " ";		
    private String checkrate = " ";
    private String checkcount = " ";		
    
    private String monitorname = " ";		
    private String departmentname = " ";	
    private String sitename = " ";	
    
    private String createid = " ";		
    private String createname = " ";		
    private String createtime = " ";		
    
    private String updateid = " ";		
    private String updatename = " ";	
    private String updatetime = " ";	
    
    private String portid = " ";
    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("planid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("planname");
        eiColumn.setFieldLength(1000);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("monitorid");
        eiColumn.setFieldLength(5);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("departmentid");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("siteid");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isqualified");
        eiColumn.setFieldLength(5);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("clockyear");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("plantype");
        eiColumn.setFieldLength(5);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("licenserate");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("iscompare");
        eiColumn.setFieldLength(1);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mark");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("status");
        eiColumn.setFieldLength(1);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("licensecount");
        eiColumn.setFieldLength(5);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("checkrate");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("checkcount");
        eiColumn.setFieldLength(5);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("monitorname");
        eiColumn.setFieldLength(1);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("departmentname");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sitename");
        eiColumn.setFieldLength(5);
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
        
        eiColumn = new EiColumn("portid");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public DUHC2302() {
        initMetaData();
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

    /**
     * get the planname 
     * @return the planname
     */
    public String getPlanname() {
        return this.planname;
    }

    /**
     * set the planname 
     */
    public void setPlanname(String planname) {
        this.planname = planname;
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
     * get the departmentid 
     * @return the departmentid
     */
    public String getDepartmentid() {
        return this.departmentid;
    }

    /**
     * set the departmentid 
     */
    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
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
     * get the clockyear 
     * @return the clockyear
     */
    public String getClockyear() {
        return this.clockyear;
    }

    /**
     * set the clockyear 
     */
    public void setClockyear(String clockyear) {
        this.clockyear = clockyear;
    }

    /**
     * get the plantype 
     * @return the plantype
     */
    public String getPlantype() {
        return this.plantype;
    }

    /**
     * set the plantype 
     */
    public void setPlantype(String plantype) {
        this.plantype = plantype;
    }

    /**
     * get the licenserate 
     * @return the licenserate
     */
    public String getLicenserate() {
        return this.licenserate;
    }

    /**
     * set the licenserate 
     */
    public void setLicenserate(String licenserate) {
        this.licenserate = licenserate;
    }

    /**
     * get the iscompare 
     * @return the iscompare
     */
    public String getIscompare() {
        return this.iscompare;
    }

    /**
     * set the iscompare 
     */
    public void setIscompare(String iscompare) {
        this.iscompare = iscompare;
    }

    /**
     * get the mark 
     * @return the mark
     */
    public String getMark() {
        return this.mark;
    }

    /**
     * set the mark 
     */
    public void setMark(String mark) {
        this.mark = mark;
    }

    /**
     * get the status 
     * @return the status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * set the status 
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * get the licensecount 
     * @return the licensecount
     */
    public String getLicensecount() {
        return this.licensecount;
    }

    /**
     * set the licensecount 
     */
    public void setLicensecount(String licensecount) {
        this.licensecount = licensecount;
    }


    /**
     * get the checkrate 
     * @return the checkrate
     */
    public String getCheckrate() {
        return this.checkrate;
    }

    /**
     * set the checkrate 
     */
    public void setCheckrate(String checkrate) {
        this.checkrate = checkrate;
    }

    /**
     * get the checkcount 
     * @return the checkcount
     */
    public String getCheckcount() {
        return this.checkcount;
    }

    /**
     * set the checkcount 
     */
    public void setCheckcount(String checkcount) {
        this.checkcount = checkcount;
    }

    public String getMonitorname() {
		return monitorname;
	}

	public void setMonitorname(String monitorname) {
		this.monitorname = monitorname;
	}

	public String getDepartmentname() {
		return departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}

	public String getSitename() {
		return sitename;
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
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

	
	public String getPortid() {
		return portid;
	}

	public void setPortid(String portid) {
		this.portid = portid;
	}

	/**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setPlanid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("planid")), planid));
        setPlanname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("planname")), planname));
        setMonitorid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("monitorid")), monitorid));
        setDepartmentid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentid")), departmentid));
        setSiteid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("siteid")), siteid));
        setClockyear(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("clockyear")), clockyear));
        setPlantype(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("plantype")), plantype));
        setLicenserate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("licenserate")), licenserate));
        setIscompare(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("iscompare")), iscompare));
        setMark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mark")), mark));
        setStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("status")), status));
        setLicensecount(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("licensecount")), licensecount));
        setCheckrate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("checkrate")), checkrate));
        setCheckcount(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("checkcount")), checkcount));
        
        setMonitorname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("monitorname")), monitorname));
        setDepartmentname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentname")), departmentname));
        setSitename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sitename")), sitename));
        
        setCreateid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createid")), createid));
        setCreatename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createname")), createname));
        setCreatetime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createtime")), createtime));
        
        setUpdateid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updateid")), updateid));
        setUpdatename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updatename")), updatename));
        setUpdatetime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updatetime")), updatetime));
        
        setPortid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("portid")), portid));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("planid", StringUtils.toString(planid, eiMetadata.getMeta("planid")));
        map.put("planname", StringUtils.toString(planname, eiMetadata.getMeta("planname")));
        map.put("monitorid", StringUtils.toString(monitorid, eiMetadata.getMeta("monitorid")));
        map.put("departmentid", StringUtils.toString(departmentid, eiMetadata.getMeta("departmentid")));
        map.put("siteid", StringUtils.toString(siteid, eiMetadata.getMeta("siteid")));
        map.put("clockyear", StringUtils.toString(clockyear, eiMetadata.getMeta("clockyear")));
        map.put("plantype", StringUtils.toString(plantype, eiMetadata.getMeta("plantype")));
        map.put("licenserate", StringUtils.toString(licenserate, eiMetadata.getMeta("licenserate")));
        map.put("iscompare", StringUtils.toString(iscompare, eiMetadata.getMeta("iscompare")));
        map.put("mark", StringUtils.toString(mark, eiMetadata.getMeta("mark")));
        map.put("status", StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("licensecount", StringUtils.toString(licensecount, eiMetadata.getMeta("licensecount")));
        map.put("checkrate", StringUtils.toString(checkrate, eiMetadata.getMeta("checkrate")));
        map.put("checkcount", StringUtils.toString(checkcount, eiMetadata.getMeta("checkcount")));
        
        map.put("monitorname", StringUtils.toString(monitorname, eiMetadata.getMeta("monitorname")));
        map.put("departmentname", StringUtils.toString(departmentname, eiMetadata.getMeta("departmentname")));
        map.put("sitename", StringUtils.toString(sitename, eiMetadata.getMeta("sitename")));
        
        map.put("createid", StringUtils.toString(createid, eiMetadata.getMeta("createid")));
        map.put("createname", StringUtils.toString(createname, eiMetadata.getMeta("createname")));
        map.put("createtime", StringUtils.toString(createtime, eiMetadata.getMeta("createtime")));
        
        map.put("updateid", StringUtils.toString(updateid, eiMetadata.getMeta("updateid")));
        map.put("updatename", StringUtils.toString(updatename, eiMetadata.getMeta("updatename")));
        map.put("updatetime", StringUtils.toString(updatetime, eiMetadata.getMeta("updatetime")));
        map.put("portid", StringUtils.toString(portid, eiMetadata.getMeta("portid")));
        return map;
    }
}