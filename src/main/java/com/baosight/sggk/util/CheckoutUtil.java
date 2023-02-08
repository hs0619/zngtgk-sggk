package com.baosight.sggk.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckoutUtil {

    public static final String REGEX_DATE ="(\\\\d{4}\\\\-\\\\d{1,2}\\\\-\\\\d{1,2})+";


    //校验时间正则表达式
    public static boolean isMobile(String mobile) {
        Pattern p = Pattern.compile("(\\d{4}\\-\\d{1,2}\\-\\d{1,2})+");
        Matcher m = p.matcher(mobile);
        if (m.find()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 利用正则表达式判断字符串是否是数字或者小数
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]+");
        Matcher isNum = pattern.matcher(str);
        if (str.contains("%"))
            return true;
        if (!isNum.matches()) return false;
        return true;
    }


    /**
     * 利用正则表达式判断正数、负数、和小数：
     *
     * @param str
     * @return
     */
    public static boolean isMinus(String str) {
        Pattern pattern = Pattern.compile("^(\\-|\\+)?\\d+(\\.\\d+)?$");
        Matcher isNum = pattern.matcher(str);
        if (str.contains("%"))
            return true;
        if (!isNum.matches()) return false;
        return true;
    }

    /**
     * 去除一个字符串里面的数字和小数点以外的字符；
     * @param num
     * @return
     */
    public static String getReplace(String num) {
        num = num.replaceAll("[^\\d.]","");
        return num;
    }


}
