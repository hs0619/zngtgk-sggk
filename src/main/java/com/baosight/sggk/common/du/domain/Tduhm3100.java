/**
 * Generate time : 2021-09-13 16:46:46
 */
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Tduhm3100 
 * table comment : 基础项目录入
 */
public class Tduhm3100 extends DaoEPBase {

      private String xMuuid="";  /*  项目编号  */
      private String shareProject=" ";  /*  项目  */


   /**
    * initialize the metadata 
    */
	public void initMetaData() {

	          EiColumn eiColumn;

              eiColumn = new EiColumn("xMuuid");
              eiColumn.setFieldLength(255);	  
              eiColumn.setDescName("项目编号");
              eiMetadata.addMeta(eiColumn);
	   	   
              eiColumn = new EiColumn("shareProject");
              eiColumn.setFieldLength(255);
              eiColumn.setDescName("项目");
              eiMetadata.addMeta(eiColumn);
	   	   


	}
   /**
    * the constructor
    */
	public Tduhm3100() {
		initMetaData();
	}
	
   /**
    *get the xMuuid   -项目编号
    *@return the xMuuid
    */
	 public String getXMuuid(){
	      return this.xMuuid;
	 }
   /**
    *set the xMuuid  -项目编号
    */
	 public void setXMuuid(String xMuuid){
	      this.xMuuid=xMuuid;
	 }
   /**
    *get the shareProject   -项目
    *@return the shareProject
    */
	 public String getShareProject(){
	      return this.shareProject;
	 }
   /**
    *set the shareProject  -项目
    */
	 public void setShareProject(String shareProject){
	      this.shareProject=shareProject;
	 }
   /**
    *get the pMuuid   -实绩编号
    *@return the pMuuid
    */

   /**
    * get the value from Map
    */
	public void fromMap(Map map) {
          setXMuuid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("xMuuid")), xMuuid));
          setShareProject(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("shareProject")), shareProject));

	}
	
   /**
    * set the value to Map
    */
	public Map toMap() {
        Map map = new HashMap();
	    map.put("xMuuid", StringUtils.toString(xMuuid, eiMetadata.getMeta("xMuuid")));
	    map.put("shareProject", StringUtils.toString(shareProject, eiMetadata.getMeta("shareProject")));
		return map;
	
	}
	}