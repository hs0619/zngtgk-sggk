/**
* Generate time : 2021-02-23 17:54:15
* Version : 6.0.731.201809200158
*/
package com.baosight.sggk.common.du.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.iplat4j.core.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Tduhd02
 * 
 */
public class Tduhd02 extends DaoEPBase {

	private static final long serialVersionUID = 1L;

	private String clock = " ";
	private String dataclock = " ";
	private String itemcode = " ";
	private BigDecimal itemvalue = null;
	private String userid = " ";
	private String inserttime = " ";
	private String itemstatus = " ";
	private String remark = " ";
	private String hbruntime = " ";
	private String flag = " ";

	private String departmentid;
	private String procedureid;
	private String gran;
	private String itemname;
	private String unit;
	private String isruntime;
	private String dataValue;
	
	private String matWt;

    private String totalDataValue;
    private String totalItemvalue;

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

		eiColumn = new EiColumn("dataclock");
		eiColumn.setFieldLength(10);
		eiColumn.setDescName(" ");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("itemcode");
		eiColumn.setPrimaryKey(true);
		eiColumn.setFieldLength(15);
		eiColumn.setDescName(" ");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("itemvalue");
		eiColumn.setScaleLength(3);
		eiColumn.setFieldLength(20);
		eiColumn.setDescName(" ");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("userid");
		eiColumn.setFieldLength(20);
		eiColumn.setDescName(" ");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("inserttime");
		eiColumn.setFieldLength(20);
		eiColumn.setDescName(" ");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("itemstatus");
		eiColumn.setFieldLength(10);
		eiColumn.setDescName(" ");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("remark");
		eiColumn.setFieldLength(400);
		eiColumn.setDescName(" ");
		eiMetadata.addMeta(eiColumn);
		
		eiColumn = new EiColumn("hbruntime");
		eiColumn.setFieldLength(40);
		eiColumn.setDescName(" ");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("departmentid");
		eiColumn.setFieldLength(50);
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

		eiColumn = new EiColumn("isruntime");
		eiColumn.setFieldLength(10);
		eiColumn.setDescName(" ");
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("procedureid");
		eiColumn.setFieldLength(10);
		eiColumn.setDescName(" ");
		eiMetadata.addMeta(eiColumn);
		
		eiColumn = new EiColumn("dataValue");
		eiColumn.setFieldLength(10);
		eiColumn.setDescName(" ");
		eiMetadata.addMeta(eiColumn);
		
		eiColumn = new EiColumn("flag");
		eiColumn.setFieldLength(10);
		eiColumn.setDescName(" ");
		eiMetadata.addMeta(eiColumn);
		
		eiColumn = new EiColumn("matWt");
		eiColumn.setFieldLength(10);
		eiColumn.setDescName(" ");
		eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("totalDataValue");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("totalItemvalue");
        eiColumn.setFieldLength(100);
        eiColumn.setDescName(" ");
        eiMetadata.addMeta(eiColumn);

	}

	/**
	 * the constructor
	 */
	public Tduhd02() {
		initMetaData();
	}

	/**
	 * get the clock
	 * 
	 * @return the clock
	 */
	public String getClock() {
		return this.clock;
	}

	/**
	 * set the clock
	 */
	public void setClock(String clock) {
		this.clock = clock;
	}

	public String getDataclock() {
		return dataclock;
	}

	public void setDataclock(String dataclock) {
		this.dataclock = dataclock;
	}

	/**
	 * get the itemcode
	 * 
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
	 * get the itemvalue
	 * 
	 * @return the itemvalue
	 */
	public BigDecimal getItemvalue() {
		return this.itemvalue;
	}

	/**
	 * set the itemvalue
	 */
	public void setItemvalue(BigDecimal itemvalue) {
		this.itemvalue = itemvalue;
	}

	/**
	 * get the userid
	 * 
	 * @return the userid
	 */
	public String getUserid() {
		return this.userid;
	}

	/**
	 * set the userid
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * get the inserttime
	 * 
	 * @return the inserttime
	 */
	public String getInserttime() {
		return this.inserttime;
	}

	/**
	 * set the inserttime
	 */
	public void setInserttime(String inserttime) {
		this.inserttime = inserttime;
	}

	/**
	 * get the itemstatus
	 * 
	 * @return the itemstatus
	 */
	public String getItemstatus() {
		return this.itemstatus;
	}

	/**
	 * set the itemstatus
	 */
	public void setItemstatus(String itemstatus) {
		this.itemstatus = itemstatus;
	}

	/**
	 * get the remark
	 * 
	 * @return the remark
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * set the remark
	 */
	public void setHbruntime(String hbruntime) {
		this.hbruntime = hbruntime;
	}
	
	/**
	 * get the remark
	 * 
	 * @return the remark
	 */
	public String getHbruntime() {
		return this.hbruntime;
	}

	/**
	 * set the remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

	public String getProcedureid() {
		return procedureid;
	}

	public void setProcedureid(String procedureid) {
		this.procedureid = procedureid;
	}

	public String getGran() {
		return gran;
	}

	public void setGran(String gran) {
		this.gran = gran;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getIsruntime() {
		return isruntime;
	}

	public void setIsruntime(String isruntime) {
		this.isruntime = isruntime;
	}

	public String getDataValue() {
		return dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMatWt() {
		return matWt;
	}

	public void setMatWt(String matWt) {
		this.matWt = matWt;
	}

    public String getTotalDataValue() {
        return totalDataValue;
    }

    public void setTotalDataValue(String totalDataValue) {
        this.totalDataValue = totalDataValue;
    }

    public String getTotalItemvalue() {
        return totalItemvalue;
    }

    public void setTotalItemvalue(String totalItemvalue) {
        this.totalItemvalue = totalItemvalue;
    }

    /**
	 * get the value from Map
	 */
	public void fromMap(Map map) {

		setClock(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("clock")), clock));
		setDataclock(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataclock")), dataclock));
		setItemcode(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemcode")), itemcode));
		setItemvalue(NumberUtils.toBigDecimal(StringUtils.toString(map.get("itemvalue")), itemvalue));
		setUserid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("userid")), userid));
		setInserttime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("inserttime")), inserttime));
		setItemstatus(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemstatus")), itemstatus));
		setRemark(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("remark")), remark));
		setHbruntime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("hbruntime")), hbruntime));

		setDepartmentid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("departmentid")), departmentid));
		setItemname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("itemname")), itemname));
		setGran(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("gran")), gran));
		setUnit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("unit")), unit));
		setIsruntime(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("isruntime")), isruntime));
		setProcedureid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("procedureid")), procedureid));
		setDataValue(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("dataValue")), dataValue));
		
		setFlag(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("flag")), flag));
		setMatWt(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("matWt")), matWt));

        setTotalDataValue(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("totalDataValue")), totalDataValue));
        setTotalItemvalue(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("totalItemvalue")), totalItemvalue));
	}

	/**
	 * set the value to Map
	 */
	public Map toMap() {
		Map map = new HashMap();
		map.put("clock", StringUtils.toString(clock, eiMetadata.getMeta("clock")));
		map.put("dataclock", StringUtils.toString(dataclock, eiMetadata.getMeta("dataclock")));
		map.put("itemcode", StringUtils.toString(itemcode, eiMetadata.getMeta("itemcode")));
		map.put("itemvalue", StringUtils.toString(itemvalue, eiMetadata.getMeta("itemvalue")));
		map.put("userid", StringUtils.toString(userid, eiMetadata.getMeta("userid")));
		map.put("inserttime", StringUtils.toString(inserttime, eiMetadata.getMeta("inserttime")));
		map.put("itemstatus", StringUtils.toString(itemstatus, eiMetadata.getMeta("itemstatus")));
		map.put("remark", StringUtils.toString(remark, eiMetadata.getMeta("remark")));
		map.put("hbruntime", StringUtils.toString(hbruntime, eiMetadata.getMeta("hbruntime")));

		map.put("departmentid", StringUtils.toString(departmentid, eiMetadata.getMeta("departmentid")));
		map.put("itemname", StringUtils.toString(itemname, eiMetadata.getMeta("itemname")));
		map.put("gran", StringUtils.toString(gran, eiMetadata.getMeta("gran")));
		map.put("unit", StringUtils.toString(unit, eiMetadata.getMeta("unit")));
		map.put("isruntime", StringUtils.toString(isruntime, eiMetadata.getMeta("isruntime")));
		map.put("procedureid", StringUtils.toString(procedureid, eiMetadata.getMeta("procedureid")));
		map.put("dataValue", StringUtils.toString(dataValue, eiMetadata.getMeta("dataValue")));
		
		map.put("flag", StringUtils.toString(flag, eiMetadata.getMeta("flag")));
		map.put("matWt", StringUtils.toString(matWt, eiMetadata.getMeta("matWt")));

        map.put("totalDataValue", StringUtils.toString(totalDataValue, eiMetadata.getMeta("totalDataValue")));
        map.put("totalItemvalue", StringUtils.toString(totalItemvalue, eiMetadata.getMeta("totalItemvalue")));
		return map;
	}
}