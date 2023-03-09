/**
* Generate time : 2021-07-15 14:55:23
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.du.hc.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* DUHC230202
* 
*/
public class DUHC230202 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String planid = " ";		
    private String siteid = " ";	
    private String sitename = " ";
    private String monitorid = " ";		
    private String factorid = " ";		
    private String licenserate = " ";		
    private String licensecount = " ";		
    private String planname = " ";
    private String factorname = " ";	
    
    private String unit = " ";		
    private String highlimit = " ";
    private String lowlimit = " ";	
    
    private String itemunit = " ";		
    private String itemlimit = " ";	
    
    private String portid = " ";	
    private String status = " ";	
    private String type = "1";	
    private String usezs = "1";	
    
    private String monitorname = "";	
    
    private Integer allcount=0;
    
    private String iscompare = "";	

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

        eiColumn = new EiColumn("siteid");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("sitename");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("monitorid");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("factorid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("licenserate");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("licensecount");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("planname");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("factorname");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("unit");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("highlimit");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("lowlimit");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("itemunit");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("itemlimit");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("portid");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("status");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("type");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("usezs");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        
        eiColumn = new EiColumn("allcount");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("monitorname");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("iscompare");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public DUHC230202() {
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

    public String getPlanname() {
		return planname;
	}

	public void setPlanname(String planname) {
		this.planname = planname;
	}

	
	public String getFactorname() {
		return factorname;
	}

	public void setFactorname(String factorname) {
		this.factorname = factorname;
	}
	
	

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getHighlimit() {
		return highlimit;
	}

	public void setHighlimit(String highlimit) {
		this.highlimit = highlimit;
	}

	public String getLowlimit() {
		return lowlimit;
	}

	public void setLowlimit(String lowlimit) {
		this.lowlimit = lowlimit;
	}
	
	

	public String getItemunit() {
		return itemunit;
	}

	public void setItemunit(String itemunit) {
		this.itemunit = itemunit;
	}

	public String getItemlimit() {
		return itemlimit;
	}

	public void setItemlimit(String itemlimit) {
		this.itemlimit = itemlimit;
	}

	public String getSitename() {
		return sitename;
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
	}

	public String getPortid() {
		return portid;
	}

	public void setPortid(String portid) {
		this.portid = portid;
	}
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsezs() {
		return usezs;
	}

	public void setUsezs(String usezs) {
		this.usezs = usezs;
	}

	

	public Integer getAllcount() {
		return allcount;
	}

	public void setAllcount(Integer allcount) {
		this.allcount = allcount;
	}

	public String getMonitorname() {
		return monitorname;
	}

	public void setMonitorname(String monitorname) {
		this.monitorname = monitorname;
	}

	public String getIscompare() {
		return iscompare;
	}

	public void setIscompare(String iscompare) {
		this.iscompare = iscompare;
	}

	/**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setPlanid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("planid")), planid));
        setSiteid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("siteid")), siteid));
        setMonitorid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("monitorid")), monitorid));
        setFactorid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("factorid")), factorid));
        setLicenserate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("licenserate")), licenserate));
        setLicensecount(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("licensecount")), licensecount));
        setPlanname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("planname")), planname));
        setFactorname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("factorname")), factorname));
        
        setUnit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("unit")), unit));
        setHighlimit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("highlimit")), highlimit));
        setLowlimit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("lowlimit")), lowlimit));
        
        setItemlimit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemlimit")), itemlimit));
        setItemunit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemunit")), itemunit));
        setSitename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sitename")), sitename));
        setPortid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("portid")), portid));
        setStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("status")), status));
        setType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("type")), type));
        setUsezs(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("usezs")), usezs));
        
        setMonitorname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("monitorname")), monitorname));
        setIscompare(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("iscompare")), iscompare));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("planid", StringUtils.toString(planid, eiMetadata.getMeta("planid")));
        map.put("siteid", StringUtils.toString(siteid, eiMetadata.getMeta("siteid")));
        map.put("monitorid", StringUtils.toString(monitorid, eiMetadata.getMeta("monitorid")));
        map.put("factorid", StringUtils.toString(factorid, eiMetadata.getMeta("factorid")));
        map.put("licenserate", StringUtils.toString(licenserate, eiMetadata.getMeta("licenserate")));
        map.put("licensecount", StringUtils.toString(licensecount, eiMetadata.getMeta("licensecount")));
        map.put("planname", StringUtils.toString(planname, eiMetadata.getMeta("planname")));
        map.put("factorname", StringUtils.toString(factorname, eiMetadata.getMeta("factorname")));
        
        map.put("unit", StringUtils.toString(unit, eiMetadata.getMeta("unit")));
        map.put("highlimit", StringUtils.toString(highlimit, eiMetadata.getMeta("highlimit")));
        map.put("lowlimit", StringUtils.toString(lowlimit, eiMetadata.getMeta("lowlimit")));
        
        map.put("itemlimit", StringUtils.toString(itemlimit, eiMetadata.getMeta("itemlimit")));
        map.put("itemunit", StringUtils.toString(itemunit, eiMetadata.getMeta("itemunit")));
        map.put("sitename", StringUtils.toString(sitename, eiMetadata.getMeta("sitename")));
        map.put("portid", StringUtils.toString(portid, eiMetadata.getMeta("portid")));
        map.put("status", StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("type", StringUtils.toString(type, eiMetadata.getMeta("type")));
        map.put("usezs", StringUtils.toString(usezs, eiMetadata.getMeta("usezs")));
        map.put("monitorname", StringUtils.toString(monitorname, eiMetadata.getMeta("monitorname")));
        map.put("iscompare", StringUtils.toString(iscompare, eiMetadata.getMeta("iscompare")));
        return map;
    }
}