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
 * Tduhm32 
 * table comment : 每月废气工序排放
 */
public class Tduhm32 extends DaoEPBase {

      private String fmuuid="";  /*  编号  */
      private String aggregateName="";  /*  类型  */
      private String exhaustemissionGross="";  /*  尾气排放总量  */
      private String dust="";  /*  尘  */
      private String sulfurDioxide="";  /*  二氧化硫  */
      private String oxynitride="";  /*  氮氧化物  */
      private String yields="";  /*  产量  */
      private String productDust="";  /*  尘  */
      private String productSulfurdioxide="";  /*  二氧化硫  */
      private String productOxynitride="";  /*  产品氮氧化物  */
      private String smuuid="";
      private String amuuid="";
        private String date="";
    private String basicsPlus="";
   /**
    * initialize the metadata 
    */
	public void initMetaData() {

	          EiColumn eiColumn;

              eiColumn = new EiColumn("fmuuid");
              eiColumn.setFieldLength(255);
              eiColumn.setDescName("编号");
              eiMetadata.addMeta(eiColumn);
	   	   
              eiColumn = new EiColumn("aggregateName");
              eiColumn.setFieldLength(255);	  
              eiColumn.setDescName("类型");
              eiMetadata.addMeta(eiColumn);
	   	   
              eiColumn = new EiColumn("exhaustemissionGross");
              eiColumn.setFieldLength(255);	  
              eiColumn.setDescName("尾气排放总量");
              eiMetadata.addMeta(eiColumn);
	   	   
              eiColumn = new EiColumn("dust");
              eiColumn.setFieldLength(255);	  
              eiColumn.setDescName("尘");
              eiMetadata.addMeta(eiColumn);
	   	   
              eiColumn = new EiColumn("sulfurDioxide");
              eiColumn.setFieldLength(255);	  
              eiColumn.setDescName("二氧化硫");
              eiMetadata.addMeta(eiColumn);
	   	   
              eiColumn = new EiColumn("oxynitride");
              eiColumn.setFieldLength(255);	  
              eiColumn.setDescName("氮氧化物");
              eiMetadata.addMeta(eiColumn);
	   	   
              eiColumn = new EiColumn("yields");
              eiColumn.setFieldLength(255);	  
              eiColumn.setDescName("产量");
              eiMetadata.addMeta(eiColumn);
	   	   
              eiColumn = new EiColumn("productDust");
              eiColumn.setFieldLength(255);	  
              eiColumn.setDescName("尘");
              eiMetadata.addMeta(eiColumn);
	   	   
              eiColumn = new EiColumn("productSulfurdioxide");
              eiColumn.setFieldLength(255);	  
              eiColumn.setDescName("二氧化硫");
              eiMetadata.addMeta(eiColumn);
	   	   
              eiColumn = new EiColumn("productOxynitride");
              eiColumn.setFieldLength(255);	  
              eiColumn.setDescName("产品氮氧化物");
              eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("smuuid");
        eiColumn.setFieldLength(255);
        eiColumn.setDescName("关联");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("amuuid");
        eiColumn.setFieldLength(255);
        eiColumn.setDescName("编号");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("date");
        eiColumn.setFieldLength(255);
        eiColumn.setDescName("时间名字");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("basicsPlus");
        eiColumn.setFieldLength(255);
        eiColumn.setDescName("是否加入总计");
        eiMetadata.addMeta(eiColumn);
	   	   
	}



    /**
    * the constructor
    */
	public Tduhm32() {
		initMetaData();
	}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    /**
    *get the muuid   -编号
    *@return the muuid
    */
	 public String getFmuuid(){
	      return this.fmuuid;
	 }
   /**
    *set the muuid  -编号
    */
	 public void setFmuuid(String fmuuid){
	      this.fmuuid=fmuuid;
	 }
   /**
    *get the aggregateName   -总计
    *@return the aggregateName
    */
	 public String getAggregateName(){
	      return this.aggregateName;
	 }
   /**
    *set the aggregateName  -总计
    */
	 public void setAggregateName(String aggregateName){
	      this.aggregateName=aggregateName;
	 }
   /**
    *get the exhaustemissionGross   -尾气排放总量
    *@return the exhaustemissionGross
    */
	 public String getExhaustemissionGross(){
	      return this.exhaustemissionGross;
	 }
   /**
    *set the exhaustemissionGross  -尾气排放总量
    */
	 public void setExhaustemissionGross(String exhaustemissionGross){
	      this.exhaustemissionGross=exhaustemissionGross;
	 }
   /**
    *get the dust   -尘
    *@return the dust
    */
	 public String getDust(){
	      return this.dust;
	 }
   /**
    *set the dust  -尘
    */
	 public void setDust(String dust){
	      this.dust=dust;
	 }
   /**
    *get the sulfurDioxide   -二氧化硫
    *@return the sulfurDioxide
    */
	 public String getSulfurDioxide(){
	      return this.sulfurDioxide;
	 }
   /**
    *set the sulfurDioxide  -二氧化硫
    */
	 public void setSulfurDioxide(String sulfurDioxide){
	      this.sulfurDioxide=sulfurDioxide;
	 }
   /**
    *get the oxynitride   -氮氧化物
    *@return the oxynitride
    */
	 public String getOxynitride(){
	      return this.oxynitride;
	 }
   /**
    *set the oxynitride  -氮氧化物
    */
	 public void setOxynitride(String oxynitride){
	      this.oxynitride=oxynitride;
	 }
   /**
    *get the yields   -产量
    *@return the yields
    */
	 public String getYields(){
	      return this.yields;
	 }
   /**
    *set the yields  -产量
    */
	 public void setYields(String yields){
	      this.yields=yields;
	 }
   /**
    *get the productDust   -尘
    *@return the productDust
    */
	 public String getProductDust(){
	      return this.productDust;
	 }
   /**
    *set the productDust  -尘
    */
	 public void setProductDust(String productDust){
	      this.productDust=productDust;
	 }
   /**
    *get the productSulfurdioxide   -二氧化硫
    *@return the productSulfurdioxide
    */
	 public String getProductSulfurdioxide(){
	      return this.productSulfurdioxide;
	 }
   /**
    *set the productSulfurdioxide  -二氧化硫
    */
	 public void setProductSulfurdioxide(String productSulfurdioxide){
	      this.productSulfurdioxide=productSulfurdioxide;
	 }
   /**
    *get the productOxynitride   -产品氮氧化物
    *@return the productOxynitride
    */
	 public String getProductOxynitride(){
	      return this.productOxynitride;
	 }
   /**
    *set the productOxynitride  -产品氮氧化物
    */
	 public void setProductOxynitride(String productOxynitride){
	      this.productOxynitride=productOxynitride;
	 }
   /**
    * get the value from Map
    */


    /**
     *get the muuid   -编号
     *@return the muuid
     */
    public String getAmuuid(){
        return this.amuuid;
    }
    /**
     *set the muuid  -编号
     */
    public void setAmuuid(String amuuid){
        this.amuuid=amuuid;
    }
    public String getBasicsPlus() {
        return basicsPlus;
    }

    public void setBasicsPlus(String basicsPlus) {
        this.basicsPlus = basicsPlus;
    }


   public String getSmuuid(){
       return this.smuuid;
   }
    /**
     *set the muuid  -编号
     */
    public void setSmuuid(String smuuid){
        this.smuuid=smuuid;
    }

	public void fromMap(Map map) {
          setFmuuid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("fmuuid")), fmuuid));
          setAggregateName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("aggregateName")), aggregateName));
          setExhaustemissionGross(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("exhaustemissionGross")), exhaustemissionGross));
          setDust(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dust")), dust));
          setSulfurDioxide(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sulfurDioxide")), sulfurDioxide));
          setOxynitride(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("oxynitride")), oxynitride));
          setYields(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("yields")), yields));
          setProductDust(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("productDust")), productDust));
          setProductSulfurdioxide(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("productSulfurdioxide")), productSulfurdioxide));
          setProductOxynitride(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("productOxynitride")), productOxynitride));
        setSmuuid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("smuuid")), smuuid));
        setAmuuid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("muuid")), amuuid));
        setDate(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("date")), date));
        setBasicsPlus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("basicsPlus")), basicsPlus));
    }
	
   /**
    * set the value to Map
    */
	public Map toMap() {
        Map map = new HashMap();
	    map.put("fmuuid", StringUtils.toString(fmuuid, eiMetadata.getMeta("fmuuid")));
	    map.put("aggregateName", StringUtils.toString(aggregateName, eiMetadata.getMeta("aggregateName")));
	    map.put("exhaustemissionGross", StringUtils.toString(exhaustemissionGross, eiMetadata.getMeta("exhaustemissionGross")));
	    map.put("dust", StringUtils.toString(dust, eiMetadata.getMeta("dust")));
	    map.put("sulfurDioxide", StringUtils.toString(sulfurDioxide, eiMetadata.getMeta("sulfurDioxide")));
	    map.put("oxynitride", StringUtils.toString(oxynitride, eiMetadata.getMeta("oxynitride")));
	    map.put("yields", StringUtils.toString(yields, eiMetadata.getMeta("yields")));
	    map.put("productDust", StringUtils.toString(productDust, eiMetadata.getMeta("productDust")));
	    map.put("productSulfurdioxide", StringUtils.toString(productSulfurdioxide, eiMetadata.getMeta("productSulfurdioxide")));
	    map.put("productOxynitride", StringUtils.toString(productOxynitride, eiMetadata.getMeta("productOxynitride")));
        map.put("smuuid", StringUtils.toString(smuuid, eiMetadata.getMeta("smuuid")));
        map.put("amuuid", StringUtils.toString(amuuid, eiMetadata.getMeta("amuuid")));
        map.put("date", StringUtils.toString(date, eiMetadata.getMeta("date")));
        map.put("basicsPlus", StringUtils.toString(basicsPlus, eiMetadata.getMeta("basicsPlus")));
        return map;
	
	}
	}