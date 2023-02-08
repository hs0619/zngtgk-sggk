/**
* Generate time : 2021-02-20 10:58:12
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
* Tduhd01
* 
*/
public class Tduhd01 extends DaoEPBase {

    private static final long serialVersionUID = 1L;

    private String departmentid = " ";		
    private String itemcode = " ";		
    private String itemname = " ";		
    private String gran = " ";		
    private String unit = " ";			
    private String isruntime = " ";
    private String istotal = " ";		
    private String procedureid = " ";
    private String seq = "";
    private String deviceid = "";
    private String runtimetype = "";
    private String olorhand = "";
    private String procUnit = "";

    private String itemid = "";
    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("departmentid");
        eiColumn.setFieldLength(50);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemcode");
        eiColumn.setPrimaryKey(true);
        eiColumn.setFieldLength(15);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemname");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("gran");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("unit");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("seq");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("isruntime");
        eiColumn.setFieldLength(1);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("istotal");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("procedureid");
        eiColumn.setFieldLength(10);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("deviceid");
        eiColumn.setFieldLength(20);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("runtimetype");
        eiColumn.setFieldLength(5);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("olorhand");
        eiColumn.setFieldLength(5);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);
        
        eiColumn = new EiColumn("procUnit");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("itemid");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

    }

    /**
     * the constructor
     */
    public Tduhd01() {
        initMetaData();
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
     * get the itemcode 
     * @return the itemcode
     */
    public String getItemcode() {
        return this.itemcode;
    }

    /**
     * set the itemcode 
     */
    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    /**
     * get the itemname 
     * @return the itemname
     */
    public String getItemname() {
        return this.itemname;
    }

    /**
     * set the itemname 
     */
    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    /**
     * get the gran 
     * @return the gran
     */
    public String getGran() {
        return this.gran;
    }

    /**
     * set the gran 
     */
    public void setGran(String gran) {
        this.gran = gran;
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
     * get the seq 
     * @return the seq
     */
    public String getSeq() {
        return this.seq;
    }

    /**
     * set the seq 
     */
    public void setSeq(String seq) {
        this.seq = seq;
    }

    /**
     * get the isruntime 
     * @return the isruntime
     */
    public String getIsruntime() {
        return this.isruntime;
    }

    /**
     * set the isruntime 
     */
    public void setIsruntime(String isruntime) {
        this.isruntime = isruntime;
    }

    public String getIstotal() {
		return istotal;
	}

	public void setIstotal(String istotal) {
		this.istotal = istotal;
	}

	/**
     * get the procedureid 
     * @return the procedureid
     */
    public String getProcedureid() {
        return this.procedureid;
    }

    /**
     * set the procedureid 
     */
    public void setProcedureid(String procedureid) {
        this.procedureid = procedureid;
    }
    

    public String getDeviceid() {
		return this.deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	

	public String getRuntimetype() {
		return this.runtimetype;
	}

	public void setRuntimetype(String runtimetype) {
		this.runtimetype = runtimetype;
	}

	public String getOlorhand() {
		return this.olorhand;
	}

	public void setOlorhand(String olorhand) {
		this.olorhand = olorhand;
	}

	public String getProcUnit() {
		return procUnit;
	}

	public void setProcUnit(String procUnit) {
		this.procUnit = procUnit;
	}

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

	/**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setDepartmentid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentid")), departmentid));
        setItemcode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemcode")), itemcode));
        setItemname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemname")), itemname));
        setGran(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("gran")), gran));
        setUnit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("unit")), unit));
        setSeq(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("seq")), seq));
        setIsruntime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isruntime")), isruntime));
        setIstotal(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("istotal")), istotal));
        setProcedureid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("procedureid")), procedureid));
        setDeviceid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("deviceid")), deviceid));
        setRuntimetype(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("runtimetype")), runtimetype));
        setOlorhand(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("olorhand")), olorhand));
        
        setProcUnit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("procUnit")), procUnit));
        setItemid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemid")), itemid));
    }


    /**
     * set the value to Map
     */
    public Map toMap() {
        Map map = new HashMap();
        map.put("departmentid", StringUtils.toString(departmentid, eiMetadata.getMeta("departmentid")));
        map.put("itemcode", StringUtils.toString(itemcode, eiMetadata.getMeta("itemcode")));
        map.put("itemname", StringUtils.toString(itemname, eiMetadata.getMeta("itemname")));
        map.put("gran", StringUtils.toString(gran, eiMetadata.getMeta("gran")));
        map.put("unit", StringUtils.toString(unit, eiMetadata.getMeta("unit")));
        map.put("seq", StringUtils.toString(seq, eiMetadata.getMeta("seq")));
        map.put("isruntime", StringUtils.toString(isruntime, eiMetadata.getMeta("isruntime")));
        map.put("istotal", StringUtils.toString(istotal, eiMetadata.getMeta("istotal")));
        map.put("procedureid", StringUtils.toString(procedureid, eiMetadata.getMeta("procedureid")));
        map.put("deviceid", StringUtils.toString(deviceid, eiMetadata.getMeta("deviceid")));
        map.put("runtimetype", StringUtils.toString(runtimetype, eiMetadata.getMeta("runtimetype")));
        map.put("olorhand", StringUtils.toString(olorhand, eiMetadata.getMeta("olorhand")));
        
        map.put("procUnit", StringUtils.toString(procUnit, eiMetadata.getMeta("procUnit")));

        map.put("itemid", StringUtils.toString(itemid, eiMetadata.getMeta("itemid")));
        return map;
    }
}