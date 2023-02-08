/**
 * Generate time : 2021-08-27 11:24:32
 */
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Tduhm310 
 * table comment : 综合指标实绩
 */
public class Tduhm3200 extends DaoEPBase {

      private String smuuid="";  /*  编号  */
	private String year="";  /*  年  */
	private String month=" ";  /*  年  */
      private String type="";  /*  状态  */
      private String remark="";  /*  备注  */

   /**
    * initialize the metadata
    */
	public void initMetaData() {

	          EiColumn eiColumn;

              eiColumn = new EiColumn("smuuid");
              eiColumn.setFieldLength(255);
              eiColumn.setDescName("时间编号");
              eiMetadata.addMeta(eiColumn);


		eiColumn = new EiColumn("year");
		eiColumn.setFieldLength(255);
		eiColumn.setDescName("年");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("month");
		eiColumn.setFieldLength(255);
		eiColumn.setDescName("月");
		eiMetadata.addMeta(eiColumn);



              eiColumn = new EiColumn("type");
              eiColumn.setFieldLength(255);
              eiColumn.setDescName("录入状态");
              eiMetadata.addMeta(eiColumn);

              eiColumn = new EiColumn("remark");
              eiColumn.setFieldLength(255);
              eiColumn.setDescName("详细");
              eiMetadata.addMeta(eiColumn);

	}
   /**
    * the constructor
    */
	public Tduhm3200() {
		initMetaData();
	}
	
   /**
    *get the muuid   -编号
    *@return the muuid
    */
	 public String getSmuuid(){
	      return this.smuuid;
	 }
   /**
    *set the muuid  -编号
    */
	 public void setSmuuid(String smuuid){
	      this.smuuid=smuuid;
	 }
	/**
	 *get the year   -年
	 *@return the year
	 */
	public String getYear(){
		return this.year;
	}
	/**
	 *set the year  -年
	 */
	public void setYear(String year){
		this.year=year;
	}
	/**
	 *get the month   -月
	 *@return the month
	 */
	public String getMonth(){
		return this.month;
	}
	/**
	 *set the month  -月
	 */
	public void setMonth(String month){
		this.month=month;
	}
   /**
    *get the type   -状态
    *@return the type
    */
	 public String getType(){
	      return this.type;
	 }
   /**
    *set the type  -状态
    */
	 public void setType(String type){
	      this.type=type;
	 }
   /**
    *get the remark   -备注
    *@return the remark
    */
	 public String getRemark(){
	      return this.remark;
	 }
   /**
    *set the remark  -备注
    */
	 public void setRemark(String remark){
	      this.remark=remark;
	 }
   /**
    * get the value from Map
    */
	public void fromMap(Map map) {
          setSmuuid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("smuuid")), smuuid));
		setYear(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("year")), year));
		setMonth(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("month")), month));
		setType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("type")), type));
          setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
	}
	
   /**
    * set the value to Map
    */
	public Map toMap() {
        Map map = new HashMap();
	   map.put("smuuid", StringUtils.toString(smuuid,eiMetadata.getMeta("smuuid")));
		map.put("year", StringUtils.toString(year, eiMetadata.getMeta("year")));
		map.put("month", StringUtils.toString(month, eiMetadata.getMeta("month")));
		map.put("type", StringUtils.toString(type, eiMetadata.getMeta("type")));
	    map.put("remark", StringUtils.toString(remark, eiMetadata.getMeta("remark")));
		return map;
	
	}
	}