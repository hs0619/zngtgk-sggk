package com.baosight.sggk.util;

/**
 * 消息推送
 */
public enum EnumUtil {
	
    ZERO_OFF_STREAM("0","不查看"),
    ONE_OFF_STREAM("1","查看");
  //  TWO_OFF_STREAM("2","驳回等待");
    public String  key;
    public String  val;

    public String getKey() {
        return key;
    }


    public String getVal() {
        return val;
    }

    EnumUtil(String key, String val) {
        this.key = key;
        this.val = val;
    }
}
