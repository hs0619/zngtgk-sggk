/**
* Generate time : 2021-04-27 15:30:39
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhb11
* 
*/
public class Tduhb11 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String factorid = " ";		
    private String usezs = " ";		
    private String datasource = " ";		
    private String siteid = " ";		
    private String sitename = " ";		
    private String monitorid = " ";		
    private String mnid = " ";		
    private String isonline = " ";		
    private String isartificial = " ";		
    private String factorname = " ";		
    private String unit = " ";		
    private String monitorname = " ";		
    private String dischargeportid = " ";		
    private String dischargeportname = " ";		
    private String procid = " ";		
    private String countrypoint = " ";		
    private String citypoint = " ";		
    private String companypoint = " ";		
    private String highlimit = " ";		
    private String lowlimit = " ";		
    private String nkhighlimit = " ";		
    private String nklowlimit = " ";		
    private String procedureId = " ";		
    private String procedureName = " ";		
    private String departmentid = " ";		
    private String departmentName = " ";	
    
    private String itemunit = " ";		
    private String itemlimit = " ";		

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("factorid");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("usezs");
        eiColumn.setFieldLength(1);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("datasource");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("siteid");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sitename");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("monitorid");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("mnid");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isonline");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isartificial");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("factorname");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("unit");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("monitorname");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dischargeportid");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("dischargeportname");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("procid");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("countrypoint");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("citypoint");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("companypoint");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("highlimit");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("lowlimit");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("nkhighlimit");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("nklowlimit");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("procedureId");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("procedureName");
        eiColumn.setFieldLength(200);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("departmentid");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("departmentName");
        eiColumn.setFieldLength(200);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemunit");
        eiColumn.setDescName("");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("itemlimit");
        eiColumn.setDescName("");
        eiMetadata.addMeta(eiColumn);
    }

    /**
     * the constructor
     */
    public Tduhb11() {
        initMetaData();
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
     * get the usezs 
     * @return the usezs
     */
    public String getUsezs() {
        return this.usezs;
    }

    /**
     * set the usezs 
     */
    public void setUsezs(String usezs) {
        this.usezs = usezs;
    }

    /**
     * get the datasource 
     * @return the datasource
     */
    public String getDatasource() {
        return this.datasource;
    }

    /**
     * set the datasource 
     */
    public void setDatasource(String datasource) {
        this.datasource = datasource;
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
     * get the sitename 
     * @return the sitename
     */
    public String getSitename() {
        return this.sitename;
    }

    /**
     * set the sitename 
     */
    public void setSitename(String sitename) {
        this.sitename = sitename;
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
     * get the mnid 
     * @return the mnid
     */
    public String getMnid() {
        return this.mnid;
    }

    /**
     * set the mnid 
     */
    public void setMnid(String mnid) {
        this.mnid = mnid;
    }

    /**
     * get the isonline 
     * @return the isonline
     */
    public String getIsonline() {
        return this.isonline;
    }

    /**
     * set the isonline 
     */
    public void setIsonline(String isonline) {
        this.isonline = isonline;
    }

    /**
     * get the isartificial 
     * @return the isartificial
     */
    public String getIsartificial() {
        return this.isartificial;
    }

    /**
     * set the isartificial 
     */
    public void setIsartificial(String isartificial) {
        this.isartificial = isartificial;
    }

    /**
     * get the factorname 
     * @return the factorname
     */
    public String getFactorname() {
        return this.factorname;
    }

    /**
     * set the factorname 
     */
    public void setFactorname(String factorname) {
        this.factorname = factorname;
    }

    /**
     * get the unit 
     * @return the unit
     */
    public String getUnit() {
        return this.unit;
    }

    /**
     * set the unit 
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * get the monitorname 
     * @return the monitorname
     */
    public String getMonitorname() {
        return this.monitorname;
    }

    /**
     * set the monitorname 
     */
    public void setMonitorname(String monitorname) {
        this.monitorname = monitorname;
    }

    /**
     * get the dischargeportid 
     * @return the dischargeportid
     */
    public String getDischargeportid() {
        return this.dischargeportid;
    }

    /**
     * set the dischargeportid 
     */
    public void setDischargeportid(String dischargeportid) {
        this.dischargeportid = dischargeportid;
    }

    /**
     * get the dischargeportname 
     * @return the dischargeportname
     */
    public String getDischargeportname() {
        return this.dischargeportname;
    }

    /**
     * set the dischargeportname 
     */
    public void setDischargeportname(String dischargeportname) {
        this.dischargeportname = dischargeportname;
    }

    /**
     * get the procid 
     * @return the procid
     */
    public String getProcid() {
        return this.procid;
    }

    /**
     * set the procid 
     */
    public void setProcid(String procid) {
        this.procid = procid;
    }

    /**
     * get the countrypoint 
     * @return the countrypoint
     */
    public String getCountrypoint() {
        return this.countrypoint;
    }

    /**
     * set the countrypoint 
     */
    public void setCountrypoint(String countrypoint) {
        this.countrypoint = countrypoint;
    }

    /**
     * get the citypoint 
     * @return the citypoint
     */
    public String getCitypoint() {
        return this.citypoint;
    }

    /**
     * set the citypoint 
     */
    public void setCitypoint(String citypoint) {
        this.citypoint = citypoint;
    }

    /**
     * get the companypoint 
     * @return the companypoint
     */
    public String getCompanypoint() {
        return this.companypoint;
    }

    /**
     * set the companypoint 
     */
    public void setCompanypoint(String companypoint) {
        this.companypoint = companypoint;
    }

    /**
     * get the highlimit 
     * @return the highlimit
     */
    public String getHighlimit() {
        return this.highlimit;
    }

    /**
     * set the highlimit 
     */
    public void setHighlimit(String highlimit) {
        this.highlimit = highlimit;
    }

    /**
     * get the lowlimit 
     * @return the lowlimit
     */
    public String getLowlimit() {
        return this.lowlimit;
    }

    /**
     * set the lowlimit 
     */
    public void setLowlimit(String lowlimit) {
        this.lowlimit = lowlimit;
    }

    /**
     * get the nkhighlimit 
     * @return the nkhighlimit
     */
    public String getNkhighlimit() {
        return this.nkhighlimit;
    }

    /**
     * set the nkhighlimit 
     */
    public void setNkhighlimit(String nkhighlimit) {
        this.nkhighlimit = nkhighlimit;
    }

    /**
     * get the nklowlimit 
     * @return the nklowlimit
     */
    public String getNklowlimit() {
        return this.nklowlimit;
    }

    /**
     * set the nklowlimit 
     */
    public void setNklowlimit(String nklowlimit) {
        this.nklowlimit = nklowlimit;
    }

    /**
     * get the procedureId 
     * @return the procedureId
     */
    public String getProcedureId() {
        return this.procedureId;
    }

    /**
     * set the procedureId 
     */
    public void setProcedureId(String procedureId) {
        this.procedureId = procedureId;
    }

    /**
     * get the procedureName 
     * @return the procedureName
     */
    public String getProcedureName() {
        return this.procedureName;
    }

    /**
     * set the procedureName 
     */
    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
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
     * get the departmentName 
     * @return the departmentName
     */
    public String getDepartmentName() {
        return this.departmentName;
    }

    /**
     * set the departmentName 
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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

	/**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setFactorid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("factorid")), factorid));
        setUsezs(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("usezs")), usezs));
        setDatasource(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("datasource")), datasource));
        setSiteid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("siteid")), siteid));
        setSitename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sitename")), sitename));
        setMonitorid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("monitorid")), monitorid));
        setMnid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mnid")), mnid));
        setIsonline(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isonline")), isonline));
        setIsartificial(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isartificial")), isartificial));
        setFactorname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("factorname")), factorname));
        setUnit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("unit")), unit));
        setMonitorname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("monitorname")), monitorname));
        setDischargeportid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dischargeportid")), dischargeportid));
        setDischargeportname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dischargeportname")), dischargeportname));
        setProcid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("procid")), procid));
        setCountrypoint(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("countrypoint")), countrypoint));
        setCitypoint(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("citypoint")), citypoint));
        setCompanypoint(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("companypoint")), companypoint));
        setHighlimit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("highlimit")), highlimit));
        setLowlimit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("lowlimit")), lowlimit));
        setNkhighlimit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("nkhighlimit")), nkhighlimit));
        setNklowlimit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("nklowlimit")), nklowlimit));
        setProcedureId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("procedureId")), procedureId));
        setProcedureName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("procedureName")), procedureName));
        setDepartmentid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentid")), departmentid));
        setDepartmentName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentName")), departmentName));
        
        setItemunit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemunit")), itemunit));
        setItemlimit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemlimit")), itemlimit));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("factorid", StringUtils.toString(factorid, eiMetadata.getMeta("factorid")));
        map.put("usezs", StringUtils.toString(usezs, eiMetadata.getMeta("usezs")));
        map.put("datasource", StringUtils.toString(datasource, eiMetadata.getMeta("datasource")));
        map.put("siteid", StringUtils.toString(siteid, eiMetadata.getMeta("siteid")));
        map.put("sitename", StringUtils.toString(sitename, eiMetadata.getMeta("sitename")));
        map.put("monitorid", StringUtils.toString(monitorid, eiMetadata.getMeta("monitorid")));
        map.put("mnid", StringUtils.toString(mnid, eiMetadata.getMeta("mnid")));
        map.put("isonline", StringUtils.toString(isonline, eiMetadata.getMeta("isonline")));
        map.put("isartificial", StringUtils.toString(isartificial, eiMetadata.getMeta("isartificial")));
        map.put("factorname", StringUtils.toString(factorname, eiMetadata.getMeta("factorname")));
        map.put("unit", StringUtils.toString(unit, eiMetadata.getMeta("unit")));
        map.put("monitorname", StringUtils.toString(monitorname, eiMetadata.getMeta("monitorname")));
        map.put("dischargeportid", StringUtils.toString(dischargeportid, eiMetadata.getMeta("dischargeportid")));
        map.put("dischargeportname", StringUtils.toString(dischargeportname, eiMetadata.getMeta("dischargeportname")));
        map.put("procid", StringUtils.toString(procid, eiMetadata.getMeta("procid")));
        map.put("countrypoint", StringUtils.toString(countrypoint, eiMetadata.getMeta("countrypoint")));
        map.put("citypoint", StringUtils.toString(citypoint, eiMetadata.getMeta("citypoint")));
        map.put("companypoint", StringUtils.toString(companypoint, eiMetadata.getMeta("companypoint")));
        map.put("highlimit", StringUtils.toString(highlimit, eiMetadata.getMeta("highlimit")));
        map.put("lowlimit", StringUtils.toString(lowlimit, eiMetadata.getMeta("lowlimit")));
        map.put("nkhighlimit", StringUtils.toString(nkhighlimit, eiMetadata.getMeta("nkhighlimit")));
        map.put("nklowlimit", StringUtils.toString(nklowlimit, eiMetadata.getMeta("nklowlimit")));
        map.put("procedureId", StringUtils.toString(procedureId, eiMetadata.getMeta("procedureId")));
        map.put("procedureName", StringUtils.toString(procedureName, eiMetadata.getMeta("procedureName")));
        map.put("departmentid", StringUtils.toString(departmentid, eiMetadata.getMeta("departmentid")));
        map.put("departmentName", StringUtils.toString(departmentName, eiMetadata.getMeta("departmentName")));
        
        map.put("itemunit", StringUtils.toString(itemunit, eiMetadata.getMeta("itemunit")));
        map.put("itemlimit", StringUtils.toString(itemlimit, eiMetadata.getMeta("itemlimit")));
        
        return map;
    }
}