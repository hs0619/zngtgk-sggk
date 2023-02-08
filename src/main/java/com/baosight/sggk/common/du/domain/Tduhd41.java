/**
* Generate time : 2021-01-28 17:58:38
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhd41
* 
*/
public class Tduhd41 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String clock = " ";		
    private String itemcode = " ";	
    private String itemname = " ";
    private String procedureid = " ";
    private String departmentid = " ";
    private String licenceid = " ";
    private String monitorid = " ";		
    private String factorid = " ";
    private String computermode = " ";
    private String pkid = " ";
    private String youzuzhi = " ";
	
	private String limit = " ";	

    private String runtime = " ";		
    private String avgfeiqi = " ";		
    private String totalfeiqi = " ";		
    private String avgnongdu = " ";		
    private String finalvalue = " ";		
    private String highdatatime = " ";		
    private String highnongdu = " ";		
    private String hasoverlimit = " ";
    
    	
    private String equivalent = " ";		
    private String totalequivalent = " ";		
    private String tax = " ";		
    private String totaltax = " ";		
    private String reductiontax = " ";
    private String thismonthdata = " ";
    private String needtaxmark = " ";
    private String taxhighnongduchange = " ";
    

	private String daorxiao = " ";
    private String computeman = " ";		
    private String computetime = " ";	
    
    private String totaltype = " ";	
    
    private String realtax = " ";



    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("clock");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemcode");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(64);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("monitorid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("factorid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("runtime");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("avgfeiqi");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("totalfeiqi");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("avgnongdu");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("finalvalue");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("highdatatime");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("highnongdu");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("hasoverlimit");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        
        eiColumn = new EiColumn("equivalent");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("totalequivalent");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("tax");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("totaltax");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("reductiontax");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("realtax");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("limit");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("thismonthdata");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

       

        eiColumn = new EiColumn("daorxiao");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("computeman");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("computetime");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("needtaxmark");
        eiColumn.setFieldLength(1);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        

        eiColumn = new EiColumn("taxhighnongduchange");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("itemname");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("procedureid");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("departmentid");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("licenceid");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("computermode");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("totaltype");
        eiColumn.setFieldLength(2);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("pkid");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("youzuzhi");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
    }
    
    public String getTotaltype() {
		return totaltype;
	}



	public void setTotaltype(String totaltype) {
		this.totaltype = totaltype;
	}

    public String getClock() {
		return clock;
	}



	public void setClock(String clock) {
		this.clock = clock;
	}



	public String getItemcode() {
		return itemcode;
	}



	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}



	public String getItemname() {
		return itemname;
	}



	public void setItemname(String itemname) {
		this.itemname = itemname;
	}



	public String getProcedureid() {
		return procedureid;
	}



	public void setProcedureid(String procedureid) {
		this.procedureid = procedureid;
	}



	public String getDepartmentid() {
		return departmentid;
	}



	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}



	public String getLicenceid() {
		return licenceid;
	}



	public void setLicenceid(String licenceid) {
		this.licenceid = licenceid;
	}



	public String getMonitorid() {
		return monitorid;
	}



	public void setMonitorid(String monitorid) {
		this.monitorid = monitorid;
	}



	public String getFactorid() {
		return factorid;
	}



	public void setFactorid(String factorid) {
		this.factorid = factorid;
	}



	public String getComputermode() {
		return computermode;
	}



	public void setComputermode(String computermode) {
		this.computermode = computermode;
	}



	public String getLimit() {
		return limit;
	}



	public void setLimit(String limit) {
		this.limit = limit;
	}



	public String getRuntime() {
		return runtime;
	}



	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}



	public String getAvgfeiqi() {
		return avgfeiqi;
	}



	public void setAvgfeiqi(String avgfeiqi) {
		this.avgfeiqi = avgfeiqi;
	}



	public String getTotalfeiqi() {
		return totalfeiqi;
	}



	public void setTotalfeiqi(String totalfeiqi) {
		this.totalfeiqi = totalfeiqi;
	}



	public String getAvgnongdu() {
		return avgnongdu;
	}



	public void setAvgnongdu(String avgnongdu) {
		this.avgnongdu = avgnongdu;
	}



	public String getFinalvalue() {
		return finalvalue;
	}



	public void setFinalvalue(String finalvalue) {
		this.finalvalue = finalvalue;
	}



	public String getHighdatatime() {
		return highdatatime;
	}



	public void setHighdatatime(String highdatatime) {
		this.highdatatime = highdatatime;
	}



	public String getHighnongdu() {
		return highnongdu;
	}



	public void setHighnongdu(String highnongdu) {
		this.highnongdu = highnongdu;
	}



	public String getHasoverlimit() {
		return hasoverlimit;
	}



	public void setHasoverlimit(String hasoverlimit) {
		this.hasoverlimit = hasoverlimit;
	}



	public String getEquivalent() {
		return equivalent;
	}



	public void setEquivalent(String equivalent) {
		this.equivalent = equivalent;
	}



	public String getTotalequivalent() {
		return totalequivalent;
	}



	public void setTotalequivalent(String totalequivalent) {
		this.totalequivalent = totalequivalent;
	}



	public String getTax() {
		return tax;
	}



	public void setTax(String tax) {
		this.tax = tax;
	}



	public String getTotaltax() {
		return totaltax;
	}



	public void setTotaltax(String totaltax) {
		this.totaltax = totaltax;
	}



	public String getReductiontax() {
		return reductiontax;
	}



	public void setReductiontax(String reductiontax) {
		this.reductiontax = reductiontax;
	}

	public String getRealtax() {
		return realtax;
	}



	public void setRealtax(String realtax) {
		this.realtax = realtax;
	}

	public String getThismonthdata() {
		return thismonthdata;
	}



	public void setThismonthdata(String thismonthdata) {
		this.thismonthdata = thismonthdata;
	}



	public String getNeedtaxmark() {
		return needtaxmark;
	}



	public void setNeedtaxmark(String needtaxmark) {
		this.needtaxmark = needtaxmark;
	}



	public String getDaorxiao() {
		return daorxiao;
	}



	public void setDaorxiao(String daorxiao) {
		this.daorxiao = daorxiao;
	}



	public String getComputeman() {
		return computeman;
	}



	public void setComputeman(String computeman) {
		this.computeman = computeman;
	}



	public String getComputetime() {
		return computetime;
	}



	public void setComputetime(String computetime) {
		this.computetime = computetime;
	}

	public String getTaxhighnongduchange() {
		return taxhighnongduchange;
	}

	public void setTaxhighnongduchange(String taxhighnongduchange) {
		this.taxhighnongduchange = taxhighnongduchange;
	}

    public String getPkid() {
        return pkid;
    }

    public void setPkid(String pkid) {
        this.pkid = pkid;
    }

    public String getYouzuzhi() {
        return youzuzhi;
    }

    public void setYouzuzhi(String youzuzhi) {
        this.youzuzhi = youzuzhi;
    }

    /**
     * the constructor
     */
    public Tduhd41() {
        initMetaData();
    }

    
    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setClock(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("clock")), clock));
        setItemcode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemcode")), itemcode));
        setMonitorid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("monitorid")), monitorid));
        setFactorid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("factorid")), factorid));
        setRuntime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("runtime")), runtime));
        setAvgfeiqi(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("avgfeiqi")), avgfeiqi));
        setTotalfeiqi(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("totalfeiqi")), totalfeiqi));
        setAvgnongdu(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("avgnongdu")), avgnongdu));
        setFinalvalue(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("finalvalue")), finalvalue));
        setHighdatatime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("highdatatime")), highdatatime));
        setHighnongdu(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("highnongdu")), highnongdu));
        setHasoverlimit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("hasoverlimit")), hasoverlimit));
        setEquivalent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("equivalent")), equivalent));
        setTotalequivalent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("totalequivalent")), totalequivalent));
        setTax(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("tax")), tax));
        setTotaltax(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("totaltax")), totaltax));
        setReductiontax(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("reductiontax")), reductiontax));
        setRealtax(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("realtax")), realtax));
        setLimit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("limit")), limit));
        setThismonthdata(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("thismonthdata")), thismonthdata));
        setDaorxiao(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("daorxiao")), daorxiao));
        setComputeman(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("computeman")), computeman));
        setComputetime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("computetime")), computetime));
        setNeedtaxmark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("needtaxmark")), needtaxmark));
        setTaxhighnongduchange(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("taxhighnongduchange")), taxhighnongduchange));
        
        setItemname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemname")), itemname));
        setProcedureid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("procedureid")), procedureid));
        setDepartmentid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentid")), departmentid));
        setLicenceid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("licenceid")), licenceid));
        setComputermode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("computermode")), computermode));
        setTotaltype(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("totaltype")), totaltype));

        setPkid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("pkid")), pkid));
        setYouzuzhi(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("youzuzhi")), youzuzhi));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("clock", StringUtils.toString(clock, eiMetadata.getMeta("clock")));
        map.put("itemcode", StringUtils.toString(itemcode, eiMetadata.getMeta("itemcode")));
        map.put("monitorid", StringUtils.toString(monitorid, eiMetadata.getMeta("monitorid")));
        map.put("factorid", StringUtils.toString(factorid, eiMetadata.getMeta("factorid")));
        map.put("runtime", StringUtils.toString(runtime, eiMetadata.getMeta("runtime")));
        map.put("avgfeiqi", StringUtils.toString(avgfeiqi, eiMetadata.getMeta("avgfeiqi")));
        map.put("totalfeiqi", StringUtils.toString(totalfeiqi, eiMetadata.getMeta("totalfeiqi")));
        map.put("avgnongdu", StringUtils.toString(avgnongdu, eiMetadata.getMeta("avgnongdu")));
        map.put("finalvalue", StringUtils.toString(finalvalue, eiMetadata.getMeta("finalvalue")));
        map.put("highdatatime", StringUtils.toString(highdatatime, eiMetadata.getMeta("highdatatime")));
        map.put("highnongdu", StringUtils.toString(highnongdu, eiMetadata.getMeta("highnongdu")));
        map.put("hasoverlimit", StringUtils.toString(hasoverlimit, eiMetadata.getMeta("hasoverlimit")));
        
        map.put("equivalent", StringUtils.toString(equivalent, eiMetadata.getMeta("equivalent")));
        map.put("totalequivalent", StringUtils.toString(totalequivalent, eiMetadata.getMeta("totalequivalent")));
        map.put("tax", StringUtils.toString(tax, eiMetadata.getMeta("tax")));
        map.put("totaltax", StringUtils.toString(totaltax, eiMetadata.getMeta("totaltax")));
        map.put("reductiontax", StringUtils.toString(reductiontax, eiMetadata.getMeta("reductiontax")));
        map.put("realtax", StringUtils.toString(realtax, eiMetadata.getMeta("realtax")));
        map.put("limit", StringUtils.toString(limit, eiMetadata.getMeta("limit")));
        map.put("thismonthdata", StringUtils.toString(thismonthdata, eiMetadata.getMeta("thismonthdata")));
        map.put("daorxiao", StringUtils.toString(daorxiao, eiMetadata.getMeta("daorxiao")));
        map.put("computeman", StringUtils.toString(computeman, eiMetadata.getMeta("computeman")));
        map.put("computetime", StringUtils.toString(computetime, eiMetadata.getMeta("computetime")));
        map.put("needtaxmark", StringUtils.toString(needtaxmark, eiMetadata.getMeta("needtaxmark")));
        map.put("taxhighnongduchange", StringUtils.toString(taxhighnongduchange, eiMetadata.getMeta("taxhighnongduchange")));
        
        map.put("itemname", StringUtils.toString(itemname, eiMetadata.getMeta("itemname")));
        map.put("procedureid", StringUtils.toString(procedureid, eiMetadata.getMeta("procedureid")));
        map.put("departmentid", StringUtils.toString(departmentid, eiMetadata.getMeta("departmentid")));
        map.put("licenceid", StringUtils.toString(licenceid, eiMetadata.getMeta("licenceid")));
        map.put("computermode", StringUtils.toString(computermode, eiMetadata.getMeta("computermode")));
        map.put("totaltype", StringUtils.toString(totaltype, eiMetadata.getMeta("totaltype")));

        map.put("pkid", StringUtils.toString(pkid, eiMetadata.getMeta("pkid")));
        map.put("youzuzhi", StringUtils.toString(youzuzhi, eiMetadata.getMeta("youzuzhi")));
        return map;
    }
}