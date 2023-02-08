/**
 * Generate time : 2021-08-24 13:46:02
 */
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Tduhm31 
 * table comment : 月环保综合指标实绩
 */
public class Tduhm31 extends DaoEPBase {

      private String pmuuid="";  /*  编号  */
    private String shareProject="";//项目名称
      private String yearActualperformance=" ";  /*  年份实绩  */
      private String yearPlan=" ";  /*  年份计划  */
      private String lastmonthActualperformance=" ";  /*  上月实绩  */
      private String thismonthActualperformance=" ";  /*  本月实绩  */
      private String grandtotalActualperformance=" ";  /*  累计实绩  */
      private String sameperiodlastyearGrandtotal=" ";  /*  去年同期累计  */
      private String contemporaryComparison=" ";
      private String muuid="";
      private String xMuuid="";
      private String muuidName="";

   /**
    * initialize the metadata 
    */
	public void initMetaData() {

	          EiColumn eiColumn;

              eiColumn = new EiColumn("pmuuid");
              eiColumn.setPrimaryKey(true);
              eiColumn.setFieldLength(255);	  
              eiColumn.setDescName("编号");
              eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("shareProject");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(255);
        eiColumn.setDescName("项目名称");
        eiMetadata.addMeta(eiColumn);

              eiColumn = new EiColumn("yearActualperformance");
              eiColumn.setFieldLength(1000);	  
              eiColumn.setDescName("年份实绩");
              eiMetadata.addMeta(eiColumn);
	   	   
              eiColumn = new EiColumn("yearPlan");
              eiColumn.setFieldLength(1000);	  
              eiColumn.setDescName("年份计划");
              eiMetadata.addMeta(eiColumn);
	   	   
              eiColumn = new EiColumn("lastmonthActualperformance");
              eiColumn.setFieldLength(1000);	  
              eiColumn.setDescName("上月实绩");
              eiMetadata.addMeta(eiColumn);
	   	   
              eiColumn = new EiColumn("thismonthActualperformance");
              eiColumn.setFieldLength(1000);	  
              eiColumn.setDescName("本月实绩");
              eiMetadata.addMeta(eiColumn);
	   	   
              eiColumn = new EiColumn("grandtotalActualperformance");
              eiColumn.setFieldLength(1000);	  
              eiColumn.setDescName("累计实绩");
              eiMetadata.addMeta(eiColumn);
	   	   
              eiColumn = new EiColumn("sameperiodlastyearGrandtotal");
              eiColumn.setFieldLength(1000);	  
              eiColumn.setDescName("去年同期累计");
              eiMetadata.addMeta(eiColumn);
	   	   
              eiColumn = new EiColumn("contemporaryComparison");
              eiColumn.setFieldLength(1000);	  
              eiColumn.setDescName("累计同期比较");
              eiMetadata.addMeta(eiColumn);


        eiColumn = new EiColumn("muuid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(255);
        eiColumn.setDescName("时间ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("xMuuid");
        eiColumn.setFieldLength(255);
        eiColumn.setDescName("项目编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("muuidName");
        eiColumn.setFieldLength(255);
        eiColumn.setDescName("时间名字");
        eiMetadata.addMeta(eiColumn);


    }


   /**
    * the constructor
    */
	public Tduhm31() {
		initMetaData();
	}
	
   /**
    *get the muuid   -编号
    *@return the muuid
    */
	 public String getPmuuid(){
	      return this.pmuuid;
	 }
   /**
    *set the muuid  -编号
    */
	 public void setPmuuid(String pmuuid){
	      this.pmuuid=pmuuid;
	 }

   /**
    *get the yearActualperformance   -年份实绩
    *@return the yearActualperformance
    */
	 public String getYearActualperformance(){
	      return this.yearActualperformance;
	 }
   /**
    *set the yearActualperformance  -年份实绩
    */
	 public void setYearActualperformance(String yearActualperformance){
	      this.yearActualperformance=yearActualperformance;
	 }
   /**
    *get the yearPlan   -年份计划
    *@return the yearPlan
    */
	 public String getYearPlan(){
	      return this.yearPlan;
	 }
   /**
    *set the yearPlan  -年份计划
    */
	 public void setYearPlan(String yearPlan){
	      this.yearPlan=yearPlan;
	 }
   /**
    *get the lastmonthActualperformance   -上月实绩
    *@return the lastmonthActualperformance
    */
	 public String getLastmonthActualperformance(){
	      return this.lastmonthActualperformance;
	 }
   /**
    *set the lastmonthActualperformance  -上月实绩
    */
	 public void setLastmonthActualperformance(String lastmonthActualperformance){
	      this.lastmonthActualperformance=lastmonthActualperformance;
	 }
   /**
    *get the thismonthActualperformance   -本月实绩
    *@return the thismonthActualperformance
    */
	 public String getThismonthActualperformance(){
	      return this.thismonthActualperformance;
	 }
   /**
    *set the thismonthActualperformance  -本月实绩
    */
	 public void setThismonthActualperformance(String thismonthActualperformance){
	      this.thismonthActualperformance=thismonthActualperformance;
	 }
   /**
    *get the grandtotalActualperformance   -累计实绩
    *@return the grandtotalActualperformance
    */
	 public String getGrandtotalActualperformance(){
	      return this.grandtotalActualperformance;
	 }
   /**
    *set the grandtotalActualperformance  -累计实绩
    */
	 public void setGrandtotalActualperformance(String grandtotalActualperformance){
	      this.grandtotalActualperformance=grandtotalActualperformance;
	 }
   /**
    *get the sameperiodlastyearGrandtotal   -去年同期累计
    *@return the sameperiodlastyearGrandtotal
    */
	 public String getSameperiodlastyearGrandtotal(){
	      return this.sameperiodlastyearGrandtotal;
	 }
   /**
    *set the sameperiodlastyearGrandtotal  -去年同期累计
    */
	 public void setSameperiodlastyearGrandtotal(String sameperiodlastyearGrandtotal){
	      this.sameperiodlastyearGrandtotal=sameperiodlastyearGrandtotal;
	 }
   /**
    *get the contemporaryComparison   -累计同期比较
    *@return the contemporaryComparison
    */
	 public String getContemporaryComparison(){
	      return this.contemporaryComparison;
	 }
   /**
    *set the contemporaryComparison  -累计同期比较
    */
	 public void setContemporaryComparison(String contemporaryComparison){
	      this.contemporaryComparison=contemporaryComparison;
	 }

    public String getMuuid() {
        return muuid;
    }

    public void setMuuid(String muuid) {
        this.muuid = muuid;
    }

    public String getShareProject() {
        return shareProject;
    }

    public void setShareProject(String shareProject) {
        this.shareProject = shareProject;
    }

    /**
     *get the year   -年
     *@return the year
     */

    public String getxMuuid() {
        return xMuuid;
    }

    public void setxMuuid(String xMuuid) {
        this.xMuuid = xMuuid;
    }

    public String getMuuidName() {
        return muuidName;
    }

    public void setMuuidName(String muuidName) {
        this.muuidName = muuidName;
    }

    public void fromMap(Map map) {
          setPmuuid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("pmuuid")), pmuuid));
        setShareProject(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("shareProject")), shareProject));
          setYearActualperformance(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("yearActualperformance")), yearActualperformance));
          setYearPlan(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("yearPlan")), yearPlan));
          setLastmonthActualperformance(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("lastmonthActualperformance")), lastmonthActualperformance));
          setThismonthActualperformance(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("thismonthActualperformance")), thismonthActualperformance));
          setGrandtotalActualperformance(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("grandtotalActualperformance")), grandtotalActualperformance));
          setSameperiodlastyearGrandtotal(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sameperiodlastyearGrandtotal")), sameperiodlastyearGrandtotal));
          setContemporaryComparison(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("contemporaryComparison")), contemporaryComparison));
        setMuuid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("muuid")), muuid));
        setxMuuid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("xMuuid")), xMuuid));
        setMuuidName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("muuidName")), muuidName));
	}
	
   /**
    * set the value to Map
    */
	public Map toMap() {
        Map map = new HashMap();
	    map.put("pmuuid", StringUtils.toString(pmuuid, eiMetadata.getMeta("pmuuid")));
        map.put("shareProject", StringUtils.toString(shareProject, eiMetadata.getMeta("shareProject")));
        map.put("yearActualperformance", StringUtils.toString(yearActualperformance, eiMetadata.getMeta("yearActualperformance")));
	    map.put("yearPlan", StringUtils.toString(yearPlan, eiMetadata.getMeta("yearPlan")));
	    map.put("lastmonthActualperformance", StringUtils.toString(lastmonthActualperformance, eiMetadata.getMeta("lastmonthActualperformance")));
	    map.put("thismonthActualperformance", StringUtils.toString(thismonthActualperformance, eiMetadata.getMeta("thismonthActualperformance")));
	    map.put("grandtotalActualperformance", StringUtils.toString(grandtotalActualperformance, eiMetadata.getMeta("grandtotalActualperformance")));
	    map.put("sameperiodlastyearGrandtotal", StringUtils.toString(sameperiodlastyearGrandtotal, eiMetadata.getMeta("sameperiodlastyearGrandtotal")));
	    map.put("contemporaryComparison", StringUtils.toString(contemporaryComparison, eiMetadata.getMeta("contemporaryComparison")));
        map.put("muuid", StringUtils.toString(muuid, eiMetadata.getMeta("muuid")));
        map.put("xMuuid", StringUtils.toString(xMuuid, eiMetadata.getMeta("xMuuid")));
        map.put("muuidName", StringUtils.toString(muuidName, eiMetadata.getMeta("muuidName")));
        return map;
	
	}
	}