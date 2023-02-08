package com.baosight.sggk.util;

public interface Common {

    //权限组名字
    enum Group {
//        HJSJSHZ("jczgshz"),//环境事件审核组
        HJSJSHZ("hjsjshz"),//环境事件申报组
        HJSJCXZ("hjsjcxz"),//环境事件查询组
        JCZGSHZ("jczgshz"),//监察整改审核组
        JCZGSBZ("jczgsbz"),//监察整改申报组
        PWBGSHZ("pwbgshz"),//排污变更审核组
        PWBGSBZ("pwbgsbz"),//排污变更申报组
        SSTYSHZ("sstyshz"),//设施停运审核组
        SSTYLRZ("sstylrz"),//设施录入审核组
        TSGLSBZ("tsglsbz"),//投诉管理申报组
        TSGLSHZ("tsglshz"),//投诉管理审核组
        HBGLSHZ("hbglshz"),//环保管理审核组
        HBGLSBZ("hbglsbz"),//环保管理申报组

//        HJCBCXZ("hbglshz"),//环境成本查询组
//        HJCBSBZ("hbglsbz"),//环境成本申报组
//        HJCBSHZ("hbglshz"),//环境成本审核组

        TSGLZ("stsglz"),//三同时管理组(审核组)
        TSLRZ("stslrz"),//三同时录入组
        HBJXCXZ("hbjxcxz"),//环保绩效查询组
        HBJXLRZ("hbjxlrz"),//环保绩效录入组
        HBJX_Y_S("hbjx_y_s"),//环保绩效一级审核组
        HBJX_E_S("hbjx_e_s"),//环保绩效二级审核组
        HBSSYXFYSBZ("hbssyxfysbz"),//环保设施运行费预算申报组
        HBSSYXFYSHZ("hbssyxfysz"),//环保设施运行费预算审核组h
        //TJPWCXZ("tjpwcxz"),//停机排污查询组
        TQGLZ("tqglz");//特权管理组

        private String param;

        Group(String param) {
            this.param = param;
        }

        public String getParam() {
            return param;
        }
    }

}
