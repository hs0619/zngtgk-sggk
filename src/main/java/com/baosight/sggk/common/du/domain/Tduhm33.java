/**
 * Generate time : 2021-08-25 14:47:02
 */
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Tduhm33 
 * table comment : 累计废气工序排放
 */
public class Tduhm33 extends DaoEPBase {

      private String muuid="0";  /*  编号  */
      private String aggregateName="0";  /*  总计  */
      private String exhaustemissionGross="0";  /*  尾气排放总量  */
      private String dust="0";  /*  尘  */
      private String sulfurDioxide="0";  /*  二氧化硫  */
      private String oxynitride="0";  /*  氮氧化物  */
      private String yields="0";  /*  产量  */
      private String productDust="0";  /*  尘  */
      private String productSulfurdioxide="0";  /*  二氧化硫  */
      private String productOxynitride="0";  /*  产品氮氧化物  */

   /**
    * initialize the metadata 
    */
	public void initMetaData() {

	          EiColumn eiColumn;

              eiColumn = new EiColumn("muuid");
              eiColumn.setFieldLength(255);	  
              eiColumn.setDescName("编号");
              eiMetadata.addMeta(eiColumn);
	   	   
              eiColumn = new EiColumn("aggregateName");
              eiColumn.setFieldLength(255);	  
              eiColumn.setDescName("项目名称");
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
	   	   
	}
   /**
    * the constructor
    */
	public Tduhm33() {
		initMetaData();
	}
	
   /**
    *get the muuid   -编号
    *@return the muuid
    */
	 public String getMuuid(){
	      return this.muuid;
	 }
   /**
    *set the muuid  -编号
    */
	 public void setMuuid(String muuid){
	      this.muuid=muuid;
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
	public void fromMap(Map map) {
          setMuuid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("muuid")), muuid));
          setAggregateName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("aggregateName")), aggregateName));
          setExhaustemissionGross(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("exhaustemissionGross")), exhaustemissionGross));
          setDust(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dust")), dust));
          setSulfurDioxide(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sulfurDioxide")), sulfurDioxide));
          setOxynitride(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("oxynitride")), oxynitride));
          setYields(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("yields")), yields));
          setProductDust(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("productDust")), productDust));
          setProductSulfurdioxide(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("productSulfurdioxide")), productSulfurdioxide));
          setProductOxynitride(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("productOxynitride")), productOxynitride));
	}
	
   /**
    * set the value to Map
    */
	public Map toMap() {
        Map map = new HashMap();
	    map.put("muuid", StringUtils.toString(muuid, eiMetadata.getMeta("muuid")));
	    map.put("aggregateName", StringUtils.toString(aggregateName, eiMetadata.getMeta("aggregateName")));
	    map.put("exhaustemissionGross", StringUtils.toString(exhaustemissionGross, eiMetadata.getMeta("exhaustemissionGross")));
	    map.put("dust", StringUtils.toString(dust, eiMetadata.getMeta("dust")));
	    map.put("sulfurDioxide", StringUtils.toString(sulfurDioxide, eiMetadata.getMeta("sulfurDioxide")));
	    map.put("oxynitride", StringUtils.toString(oxynitride, eiMetadata.getMeta("oxynitride")));
	    map.put("yields", StringUtils.toString(yields, eiMetadata.getMeta("yields")));
	    map.put("productDust", StringUtils.toString(productDust, eiMetadata.getMeta("productDust")));
	    map.put("productSulfurdioxide", StringUtils.toString(productSulfurdioxide, eiMetadata.getMeta("productSulfurdioxide")));
	    map.put("productOxynitride", StringUtils.toString(productOxynitride, eiMetadata.getMeta("productOxynitride")));
		return map;
	
	}
	}