/**
* Generate time : 2023-02-17 19:46:30
* Version : 1.0
*/
package com.baosight.sggk.common.du.domain;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* THaFuel
* 
*/
public class Tduhb51 extends DaoEPBase {

                private String rfId = " ";		/* 主键ID*/
                private String rfName = " ";		/* 燃料名称*/
                private String rfAsh = " ";		/* 灰分(%)*/
                private String rfSulfurContent = " ";		/* 硫分(%)*/
                private String rfVolatile = " ";		/* 挥发分(%)*/
                private String rfHeatValue = " ";		/* 热值(MJ/kg、MJ/m³)*/
                private String rfMaxAmount = " ";		/* 年最大使用量（万t/a、万m3/a）*/
                private String rfOtherInformation = " ";		/* 其他信息*/
/**
* initialize the metadata
*/
public void initMetaData() {
EiColumn eiColumn;

        eiColumn = new EiColumn("rfId");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rfName");
        eiColumn.setDescName("燃料名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rfAsh");
        eiColumn.setDescName("灰分(%)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rfSulfurContent");
        eiColumn.setDescName("硫分(%)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rfVolatile");
        eiColumn.setDescName("挥发分(%)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rfHeatValue");
        eiColumn.setDescName("热值(MJ/kg、MJ/m³)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rfMaxAmount");
        eiColumn.setDescName("年最大使用量（万t/a、万m3/a）");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rfOtherInformation");
        eiColumn.setDescName("其他信息");
        eiMetadata.addMeta(eiColumn);


}
/**
* the constructor
*/
public Tduhb51() {
initMetaData();
}

        /**
        * get the rfId - 主键ID
        * @return the rfId
        */
        public String getRfId() {
        return this.rfId;
        }

        /**
        * set the rfId - 主键ID
        */
        public void setRfId(String rfId) {
        this.rfId = rfId;
        }
        /**
        * get the rfName - 燃料名称
        * @return the rfName
        */
        public String getRfName() {
        return this.rfName;
        }

        /**
        * set the rfName - 燃料名称
        */
        public void setRfName(String rfName) {
        this.rfName = rfName;
        }
        /**
        * get the rfAsh - 灰分(%)
        * @return the rfAsh
        */
        public String getRfAsh() {
        return this.rfAsh;
        }

        /**
        * set the rfAsh - 灰分(%)
        */
        public void setRfAsh(String rfAsh) {
        this.rfAsh = rfAsh;
        }
        /**
        * get the rfSulfurContent - 硫分(%)
        * @return the rfSulfurContent
        */
        public String getRfSulfurContent() {
        return this.rfSulfurContent;
        }

        /**
        * set the rfSulfurContent - 硫分(%)
        */
        public void setRfSulfurContent(String rfSulfurContent) {
        this.rfSulfurContent = rfSulfurContent;
        }
        /**
        * get the rfVolatile - 挥发分(%)
        * @return the rfVolatile
        */
        public String getRfVolatile() {
        return this.rfVolatile;
        }

        /**
        * set the rfVolatile - 挥发分(%)
        */
        public void setRfVolatile(String rfVolatile) {
        this.rfVolatile = rfVolatile;
        }
        /**
        * get the rfHeatValue - 热值(MJ/kg、MJ/m³)
        * @return the rfHeatValue
        */
        public String getRfHeatValue() {
        return this.rfHeatValue;
        }

        /**
        * set the rfHeatValue - 热值(MJ/kg、MJ/m³)
        */
        public void setRfHeatValue(String rfHeatValue) {
        this.rfHeatValue = rfHeatValue;
        }
        /**
        * get the rfMaxAmount - 年最大使用量（万t/a、万m3/a）
        * @return the rfMaxAmount
        */
        public String getRfMaxAmount() {
        return this.rfMaxAmount;
        }

        /**
        * set the rfMaxAmount - 年最大使用量（万t/a、万m3/a）
        */
        public void setRfMaxAmount(String rfMaxAmount) {
        this.rfMaxAmount = rfMaxAmount;
        }
        /**
        * get the rfOtherInformation - 其他信息
        * @return the rfOtherInformation
        */
        public String getRfOtherInformation() {
        return this.rfOtherInformation;
        }

        /**
        * set the rfOtherInformation - 其他信息
        */
        public void setRfOtherInformation(String rfOtherInformation) {
        this.rfOtherInformation = rfOtherInformation;
        }
/**
* get the value from Map
*/
public void fromMap(Map map) {

                setRfId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rfId")), rfId));
                setRfName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rfName")), rfName));
                setRfAsh(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rfAsh")), rfAsh));
                setRfSulfurContent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rfSulfurContent")), rfSulfurContent));
                setRfVolatile(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rfVolatile")), rfVolatile));
                setRfHeatValue(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rfHeatValue")), rfHeatValue));
                setRfMaxAmount(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rfMaxAmount")), rfMaxAmount));
                setRfOtherInformation(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rfOtherInformation")), rfOtherInformation));
}

/**
* set the value to Map
*/
public Map toMap() {

Map map = new HashMap();
                map.put("rfId",StringUtils.toString(rfId, eiMetadata.getMeta("rfId")));
                map.put("rfName",StringUtils.toString(rfName, eiMetadata.getMeta("rfName")));
                map.put("rfAsh",StringUtils.toString(rfAsh, eiMetadata.getMeta("rfAsh")));
                map.put("rfSulfurContent",StringUtils.toString(rfSulfurContent, eiMetadata.getMeta("rfSulfurContent")));
                map.put("rfVolatile",StringUtils.toString(rfVolatile, eiMetadata.getMeta("rfVolatile")));
                map.put("rfHeatValue",StringUtils.toString(rfHeatValue, eiMetadata.getMeta("rfHeatValue")));
                map.put("rfMaxAmount",StringUtils.toString(rfMaxAmount, eiMetadata.getMeta("rfMaxAmount")));
                map.put("rfOtherInformation",StringUtils.toString(rfOtherInformation, eiMetadata.getMeta("rfOtherInformation")));

return map;

}
}