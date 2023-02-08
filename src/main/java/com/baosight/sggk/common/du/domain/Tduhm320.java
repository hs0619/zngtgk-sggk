/**
 * Generate time : 2021-08-30 18:07:36
 */
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Tduhm320 
 * table comment : 废气工序基础数据
 */
public class Tduhm320 extends DaoEPBase {

      private String amuuid="";  /*  编号  */
      private String basicsName="";  /*  废气工序  */
      private String basicsType="";  /*  父级名称  */
      private String basicsRank="";  /*  排序  */
      private String basicsPlus="";  /*  是否加入总计  */
      private String basicsInvoke="";  /*  启用/停用  */
      private String basicsState="";  /*  备注  */

   /**
    * initialize the metadata 
    */
	public void initMetaData() {

	          EiColumn eiColumn;

              eiColumn = new EiColumn("amuuid");
              eiColumn.setFieldLength(255);	  
              eiColumn.setDescName("编号");
              eiMetadata.addMeta(eiColumn);
	   	   
              eiColumn = new EiColumn("basicsName");
              eiColumn.setFieldLength(255);	  
              eiColumn.setDescName("废气工序");
              eiMetadata.addMeta(eiColumn);
	   	   
              eiColumn = new EiColumn("basicsType");
              eiColumn.setFieldLength(255);	  
              eiColumn.setDescName("父级名称");
              eiMetadata.addMeta(eiColumn);
	   	   
              eiColumn = new EiColumn("basicsRank");
              eiColumn.setFieldLength(255);	  
              eiColumn.setDescName("排序");
              eiMetadata.addMeta(eiColumn);
	   	   
              eiColumn = new EiColumn("basicsPlus");
              eiColumn.setFieldLength(255);	  
              eiColumn.setDescName("是否加入总计");
              eiMetadata.addMeta(eiColumn);
	   	   
              eiColumn = new EiColumn("basicsInvoke");
              eiColumn.setFieldLength(255);	  
              eiColumn.setDescName("启用/停用");
              eiMetadata.addMeta(eiColumn);
	   	   
              eiColumn = new EiColumn("basicsState");
              eiColumn.setFieldLength(255);	  
              eiColumn.setDescName("备注");
              eiMetadata.addMeta(eiColumn);
	   	   
	}
   /**
    * the constructor
    */
	public Tduhm320() {
		initMetaData();
	}
	
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
   /**
    *get the basicsName   -废气工序
    *@return the basicsName
    */
	 public String getBasicsName(){
	      return this.basicsName;
	 }
   /**
    *set the basicsName  -废气工序
    */
	 public void setBasicsName(String basicsName){
	      this.basicsName=basicsName;
	 }
   /**
    *get the basicsType   -父级名称
    *@return the basicsType
    */
	 public String getBasicsType(){
	      return this.basicsType;
	 }
   /**
    *set the basicsType  -父级名称
    */
	 public void setBasicsType(String basicsType){
	      this.basicsType=basicsType;
	 }
   /**
    *get the basicsRank   -排序
    *@return the basicsRank
    */
	 public String getBasicsRank(){
	      return this.basicsRank;
	 }
   /**
    *set the basicsRank  -排序
    */
	 public void setBasicsRank(String basicsRank){
	      this.basicsRank=basicsRank;
	 }
   /**
    *get the basicsPlus   -是否加入总计
    *@return the basicsPlus
    */
	 public String getBasicsPlus(){
	      return this.basicsPlus;
	 }
   /**
    *set the basicsPlus  -是否加入总计
    */
	 public void setBasicsPlus(String basicsPlus){
	      this.basicsPlus=basicsPlus;
	 }
   /**
    *get the basicsInvoke   -启用/停用
    *@return the basicsInvoke
    */
	 public String getBasicsInvoke(){
	      return this.basicsInvoke;
	 }
   /**
    *set the basicsInvoke  -启用/停用
    */
	 public void setBasicsInvoke(String basicsInvoke){
	      this.basicsInvoke=basicsInvoke;
	 }
   /**
    *get the basicsState   -备注
    *@return the basicsState
    */
	 public String getBasicsState(){
	      return this.basicsState;
	 }
   /**
    *set the basicsState  -备注
    */
	 public void setBasicsState(String basicsState){
	      this.basicsState=basicsState;
	 }
   /**
    * get the value from Map
    */
	public void fromMap(Map map) {
          setAmuuid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("muuid")), amuuid));
          setBasicsName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("basicsName")), basicsName));
          setBasicsType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("basicsType")), basicsType));
          setBasicsRank(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("basicsRank")), basicsRank));
          setBasicsPlus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("basicsPlus")), basicsPlus));
          setBasicsInvoke(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("basicsInvoke")), basicsInvoke));
          setBasicsState(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("basicsState")), basicsState));
	}
	
   /**
    * set the value to Map
    */
	public Map toMap() {
        Map map = new HashMap();
	    map.put("amuuid", StringUtils.toString(amuuid, eiMetadata.getMeta("amuuid")));
	    map.put("basicsName", StringUtils.toString(basicsName, eiMetadata.getMeta("basicsName")));
	    map.put("basicsType", StringUtils.toString(basicsType, eiMetadata.getMeta("basicsType")));
	    map.put("basicsRank", StringUtils.toString(basicsRank, eiMetadata.getMeta("basicsRank")));
	    map.put("basicsPlus", StringUtils.toString(basicsPlus, eiMetadata.getMeta("basicsPlus")));
	    map.put("basicsInvoke", StringUtils.toString(basicsInvoke, eiMetadata.getMeta("basicsInvoke")));
	    map.put("basicsState", StringUtils.toString(basicsState, eiMetadata.getMeta("basicsState")));
		return map;
	
	}
    public EiBlockMeta getMetaData() {
        return eiMetadata;
    }
	}