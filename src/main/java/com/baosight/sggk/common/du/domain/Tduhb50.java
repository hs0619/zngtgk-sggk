/**
* Generate time : 2023-02-09 15:54:43
* Version : 1.0
*/
package com.baosight.sggk.common.du.domain;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* THaRawMaterial
* 
*/
public class Tduhb50 extends DaoEPBase {

                private String rmId = " ";		/* 主键ID*/
                private String rmType = " ";		/* 原辅料类别*/
                private String rmName = " ";		/* 原辅料名称*/
                private String rmMaxAmount = " ";		/* 年最大使用量*/
                private String rmUnit = " ";		/* 单位*/
                private String rmSulfurContent = " ";		/* 硫分(%)*/
                private String rmVolatile = " ";		/* 挥发分(%)*/
                private String rmOtherInformation = " ";		/* 其他信息*/
/**
* initialize the metadata
*/
public void initMetaData() {
EiColumn eiColumn;

        eiColumn = new EiColumn("rmId");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rmType");
        eiColumn.setDescName("原辅料类别");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rmName");
        eiColumn.setDescName("原辅料名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rmMaxAmount");
        eiColumn.setDescName("年最大使用量");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rmUnit");
        eiColumn.setDescName("单位");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rmSulfurContent");
        eiColumn.setDescName("硫分(%)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rmVolatile");
        eiColumn.setDescName("挥发分(%)");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("rmOtherInformation");
        eiColumn.setDescName("其他信息");
        eiMetadata.addMeta(eiColumn);


}
/**
* the constructor
*/
public Tduhb50() {
initMetaData();
}

        /**
        * get the rmId - 主键ID
        * @return the rmId
        */
        public String getRmId() {
        return this.rmId;
        }

        /**
        * set the rmId - 主键ID
        */
        public void setRmId(String rmId) {
        this.rmId = rmId;
        }
        /**
        * get the rmType - 原辅料类别
        * @return the rmType
        */
        public String getRmType() {
        return this.rmType;
        }

        /**
        * set the rmType - 原辅料类别
        */
        public void setRmType(String rmType) {
        this.rmType = rmType;
        }
        /**
        * get the rmName - 原辅料名称
        * @return the rmName
        */
        public String getRmName() {
        return this.rmName;
        }

        /**
        * set the rmName - 原辅料名称
        */
        public void setRmName(String rmName) {
        this.rmName = rmName;
        }
        /**
        * get the rmMaxAmount - 年最大使用量
        * @return the rmMaxAmount
        */
        public String getRmMaxAmount() {
        return this.rmMaxAmount;
        }

        /**
        * set the rmMaxAmount - 年最大使用量
        */
        public void setRmMaxAmount(String rmMaxAmount) {
        this.rmMaxAmount = rmMaxAmount;
        }
        /**
        * get the rmUnit - 单位
        * @return the rmUnit
        */
        public String getRmUnit() {
        return this.rmUnit;
        }

        /**
        * set the rmUnit - 单位
        */
        public void setRmUnit(String rmUnit) {
        this.rmUnit = rmUnit;
        }
        /**
        * get the rmSulfurContent - 硫分(%)
        * @return the rmSulfurContent
        */
        public String getRmSulfurContent() {
        return this.rmSulfurContent;
        }

        /**
        * set the rmSulfurContent - 硫分(%)
        */
        public void setRmSulfurContent(String rmSulfurContent) {
        this.rmSulfurContent = rmSulfurContent;
        }
        /**
        * get the rmVolatile - 挥发分(%)
        * @return the rmVolatile
        */
        public String getRmVolatile() {
        return this.rmVolatile;
        }

        /**
        * set the rmVolatile - 挥发分(%)
        */
        public void setRmVolatile(String rmVolatile) {
        this.rmVolatile = rmVolatile;
        }
        /**
        * get the rmOtherInformation - 其他信息
        * @return the rmOtherInformation
        */
        public String getRmOtherInformation() {
        return this.rmOtherInformation;
        }

        /**
        * set the rmOtherInformation - 其他信息
        */
        public void setRmOtherInformation(String rmOtherInformation) {
        this.rmOtherInformation = rmOtherInformation;
        }
/**
* get the value from Map
*/
public void fromMap(Map map) {

                setRmId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rmId")), rmId));
                setRmType(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rmType")), rmType));
                setRmName(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rmName")), rmName));
                setRmMaxAmount(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rmMaxAmount")), rmMaxAmount));
                setRmUnit(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rmUnit")), rmUnit));
                setRmSulfurContent(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rmSulfurContent")), rmSulfurContent));
                setRmVolatile(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rmVolatile")), rmVolatile));
                setRmOtherInformation(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("rmOtherInformation")), rmOtherInformation));
}

/**
* set the value to Map
*/
public Map toMap() {

Map map = new HashMap();
                map.put("rmId",StringUtils.toString(rmId, eiMetadata.getMeta("rmId")));
                map.put("rmType",StringUtils.toString(rmType, eiMetadata.getMeta("rmType")));
                map.put("rmName",StringUtils.toString(rmName, eiMetadata.getMeta("rmName")));
                map.put("rmMaxAmount",StringUtils.toString(rmMaxAmount, eiMetadata.getMeta("rmMaxAmount")));
                map.put("rmUnit",StringUtils.toString(rmUnit, eiMetadata.getMeta("rmUnit")));
                map.put("rmSulfurContent",StringUtils.toString(rmSulfurContent, eiMetadata.getMeta("rmSulfurContent")));
                map.put("rmVolatile",StringUtils.toString(rmVolatile, eiMetadata.getMeta("rmVolatile")));
                map.put("rmOtherInformation",StringUtils.toString(rmOtherInformation, eiMetadata.getMeta("rmOtherInformation")));

return map;

}
}