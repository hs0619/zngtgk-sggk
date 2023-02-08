/**
* Generate time : 2021-03-08 10:41:30
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhb07
* 
*/
public class Tduhb07 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String siteid = " ";		
    private String sitename = " ";		
    private String departid = " ";		
    private String departname = " ";	
    private String procid = " ";	
    private String procname = " ";	
    private String portid = " ";		
    private String portname = " ";	
    private String isartificial = " ";		
    private String isonline = " ";		
    private String classifyid = " ";		
    private String monitorid = " ";		
    private String mnid = " ";		
    private String description = " ";		
    private String sort = " ";		
    private String status = " ";		
    private String creator = " ";		
    private String createdTime = " ";		
    private String modifier = " ";		
    private String updatedTime = " ";		
    
    private String dischargeportcode = " ";

    private String useflag = " ";

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("dischargeportcode");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("siteid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sitename");
        eiColumn.setFieldLength(255);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("departid");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("departname");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("procid");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("procname");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("portid");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("portname");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isartificial");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isonline");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("classifyid");
        eiColumn.setFieldLength(10);
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

        eiColumn = new EiColumn("description");
        eiColumn.setFieldLength(255);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("sort");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("status");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("creator");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("createdTime");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("modifier");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("updatedTime");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("useflag");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
    }
    
    

    /**
     * the constructor
     */
    public Tduhb07() {
        initMetaData();
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
     * get the departid 
     * @return the departid
     */
    public String getDepartid() {
        return this.departid;
    }

    /**
     * set the departid 
     */
    public void setDepartid(String departid) {
        this.departid = departid;
    }

    public String getDepartname() {
		return this.departname;
	}

	public void setDepartname(String departname) {
		this.departname = departname;
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
    
    public String getProcname() {
		return this.procname;
	}

	public void setProcname(String procname) {
		this.procname = procname;
	}

    /**
     * get the portid 
     * @return the portid
     */
    public String getPortid() {
        return this.portid;
    }

    /**
     * set the portid 
     */
    public void setPortid(String portid) {
        this.portid = portid;
    }

    public String getPortname() {
		return this.portname;
	}

	public void setPortname(String portname) {
		this.portname = portname;
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
     * get the classifyid 
     * @return the classifyid
     */
    public String getClassifyid() {
        return this.classifyid;
    }

    /**
     * set the classifyid 
     */
    public void setClassifyid(String classifyid) {
        this.classifyid = classifyid;
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
     * get the description 
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * set the description 
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * get the sort 
     * @return the sort
     */
    public String getSort() {
        return this.sort;
    }

    /**
     * set the sort 
     */
    public void setSort(String sort) {
        this.sort = sort;
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
     * get the creator 
     * @return the creator
     */
    public String getCreator() {
        return this.creator;
    }

    /**
     * set the creator 
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * get the createdTime 
     * @return the createdTime
     */
    public String getCreatedTime() {
        return this.createdTime;
    }

    /**
     * set the createdTime 
     */
    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * get the modifier 
     * @return the modifier
     */
    public String getModifier() {
        return this.modifier;
    }

    /**
     * set the modifier 
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    /**
     * get the updatedTime 
     * @return the updatedTime
     */
    public String getUpdatedTime() {
        return this.updatedTime;
    }

    /**
     * set the updatedTime 
     */
    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    
    
    public String getDischargeportcode() {
		return dischargeportcode;
	}



	public void setDischargeportcode(String dischargeportcode) {
		this.dischargeportcode = dischargeportcode;
	}


    public String getUseflag() {
        return useflag;
    }

    public void setUseflag(String useflag) {
        this.useflag = useflag;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setSiteid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("siteid")), siteid));
        setSitename(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sitename")), sitename));
        setDepartid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departid")), departid));
        setDepartname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departname")), departname));
        setProcid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("procid")), procid));
        setProcname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("procname")), procname));
        setPortid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("portid")), portid));
        setPortname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("portname")), portname));
        setIsartificial(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isartificial")), isartificial));
        setIsonline(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isonline")), isonline));
        setClassifyid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("classifyid")), classifyid));
        setMonitorid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("monitorid")), monitorid));
        setMnid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("mnid")), mnid));
        setDescription(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("description")), description));
        setSort(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("sort")), sort));
        setStatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("status")), status));
        setCreator(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("creator")), creator));
        setCreatedTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("createdTime")), createdTime));
        setModifier(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("modifier")), modifier));
        setUpdatedTime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("updatedTime")), updatedTime));
        
        setDischargeportcode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dischargeportcode")), dischargeportcode));
        setUseflag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("useflag")), useflag));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("siteid", StringUtils.toString(siteid, eiMetadata.getMeta("siteid")));
        map.put("sitename", StringUtils.toString(sitename, eiMetadata.getMeta("sitename")));
        map.put("departid", StringUtils.toString(departid, eiMetadata.getMeta("departid")));
        map.put("departname", StringUtils.toString(departname, eiMetadata.getMeta("departname")));
        map.put("procid", StringUtils.toString(procid, eiMetadata.getMeta("procid")));
        map.put("procname", StringUtils.toString(procname, eiMetadata.getMeta("procname")));
        map.put("portid", StringUtils.toString(portid, eiMetadata.getMeta("portid")));
        map.put("portname", StringUtils.toString(portname, eiMetadata.getMeta("portname")));
        map.put("isartificial", StringUtils.toString(isartificial, eiMetadata.getMeta("isartificial")));
        map.put("isonline", StringUtils.toString(isonline, eiMetadata.getMeta("isonline")));
        map.put("classifyid", StringUtils.toString(classifyid, eiMetadata.getMeta("classifyid")));
        map.put("monitorid", StringUtils.toString(monitorid, eiMetadata.getMeta("monitorid")));
        map.put("mnid", StringUtils.toString(mnid, eiMetadata.getMeta("mnid")));
        map.put("description", StringUtils.toString(description, eiMetadata.getMeta("description")));
        map.put("sort", StringUtils.toString(sort, eiMetadata.getMeta("sort")));
        map.put("status", StringUtils.toString(status, eiMetadata.getMeta("status")));
        map.put("creator", StringUtils.toString(creator, eiMetadata.getMeta("creator")));
        map.put("createdTime", StringUtils.toString(createdTime, eiMetadata.getMeta("createdTime")));
        map.put("modifier", StringUtils.toString(modifier, eiMetadata.getMeta("modifier")));
        map.put("updatedTime", StringUtils.toString(updatedTime, eiMetadata.getMeta("updatedTime")));
        
        map.put("dischargeportcode", StringUtils.toString(dischargeportcode, eiMetadata.getMeta("dischargeportcode")));
        map.put("useflag", StringUtils.toString(useflag, eiMetadata.getMeta("useflag")));
        return map;
    }
}