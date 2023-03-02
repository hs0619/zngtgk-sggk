/**
* Generate time : 2023-03-02 17:06:50
* Version : 1.0
*/
package com.baosight.sggk.du.hd.domain;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.data.DaoEPBase;
import java.util.HashMap;
import java.util.Map;
import com.baosight.iplat4j.core.util.StringUtils;

/**
* THdFacilityCostsFormula
* 
*/
public class DUHD11 extends DaoEPBase {

                private String formulaid = " ";		/* 主键ID*/
                private String formulaname = " ";		/* 费用名称*/
                private String formula = " ";		/* 计算公式*/
/**
* initialize the metadata
*/
public void initMetaData() {
EiColumn eiColumn;

        eiColumn = new EiColumn("formulaid");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键ID");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("formulaname");
        eiColumn.setDescName("费用名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("formula");
        eiColumn.setDescName("计算公式");
        eiMetadata.addMeta(eiColumn);


}
/**
* the constructor
*/
public DUHD11() {
initMetaData();
}

        /**
        * get the formulaid - 主键ID
        * @return the formulaid
        */
        public String getFormulaid() {
        return this.formulaid;
        }

        /**
        * set the formulaid - 主键ID
        */
        public void setFormulaid(String formulaid) {
        this.formulaid = formulaid;
        }
        /**
        * get the formulaname - 费用名称
        * @return the formulaname
        */
        public String getFormulaname() {
        return this.formulaname;
        }

        /**
        * set the formulaname - 费用名称
        */
        public void setFormulaname(String formulaname) {
        this.formulaname = formulaname;
        }
        /**
        * get the formula - 计算公式
        * @return the formula
        */
        public String getFormula() {
        return this.formula;
        }

        /**
        * set the formula - 计算公式
        */
        public void setFormula(String formula) {
        this.formula = formula;
        }
/**
* get the value from Map
*/
public void fromMap(Map map) {

                setFormulaid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("formulaid")), formulaid));
                setFormulaname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("formulaname")), formulaname));
                setFormula(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("formula")), formula));
}

/**
* set the value to Map
*/
public Map toMap() {

Map map = new HashMap();
                map.put("formulaid",StringUtils.toString(formulaid, eiMetadata.getMeta("formulaid")));
                map.put("formulaname",StringUtils.toString(formulaname, eiMetadata.getMeta("formulaname")));
                map.put("formula",StringUtils.toString(formula, eiMetadata.getMeta("formula")));

return map;

}
}