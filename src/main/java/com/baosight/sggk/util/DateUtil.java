package com.baosight.sggk.util;



import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期处理工具类
 * 
 * @author CaoJinGe
 *
 */
public class DateUtil {
	
	public static final String STANDARD_FORMAT_D = "yyyy-MM-dd";
    public static final String STANDARD_FORMAT_YMDH = "yyyy-MM-dd HH";
    public static final String STANDARD_FORMAT_YMDHM = "yyyy-MM-dd HH:mm";
    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 根据传入的日期，获取当前日期所在月份的天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getDaysOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 根据传递的日期字符串和天数，获取当前传递日期往后或者向前的dayNum数
	 * 
	 * @param dayNum
	 * @return
	 * @throws ParseException
	 */
	public static String getNextDayByNum(String startDate, int dayNum) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = "";
		try {
			if (StrUtil.paramIsNotNullOrEmpty(startDate)) {
				Date date = sdf.parse(startDate);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				// 把日期往后增加一天.整数往后推,负数往前移动
				calendar.add(calendar.DATE, dayNum);
				date = calendar.getTime();
				dateStr = sdf.format(date);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dateStr;
	}

	/**
	 * 根据开始时间，结束时间字符串获取，时间间隔
	 * 
	 * @param sDateStr
	 * @param eDateStr
	 * @return
	 * @throws ParseException
	 */
	public static int getBetweenDays(String sDateStr, String eDateStr,String datetype) {
		int clocks = -1;
		if (StrUtil.paramIsNotNullOrEmpty(sDateStr) && StrUtil.paramIsNotNullOrEmpty(eDateStr)) {
			try {
				switch (datetype) {
				case "DAY":
					SimpleDateFormat sdfday = new SimpleDateFormat("yyyy-MM-dd");
					Date sDateday = sdfday.parse(sDateStr);
					Date eDateday = sdfday.parse(eDateStr);
					Long clockday = (eDateday.getTime() - sDateday.getTime()) / (24 * 60 * 60 * 1000);
					clocks = clockday.intValue();
					break;
				case "HOUR":
					SimpleDateFormat sdfhour = new SimpleDateFormat("yyyy-MM-dd HH");
					Date sDatehour = sdfhour.parse(sDateStr);
					Date eDatehour = sdfhour.parse(eDateStr);
					Long clockhour = (eDatehour.getTime() - sDatehour.getTime()) / ( 60 * 60 * 1000);
					clocks = clockhour.intValue();
					break;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return clocks;
	}

	
//	public static int getBetweenDays(String sDateStr, String eDateStr,String datetype) {
//		int days = -1;
//		if (StrUtil.paramIsNotNullOrEmpty(sDateStr) && StrUtil.paramIsNotNullOrEmpty(eDateStr)) {
//			try {
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//				Date sDate = sdf.parse(sDateStr);
//				Date eDate = sdf.parse(eDateStr);
//				Long day = (eDate.getTime() - sDate.getTime()) / (24 * 60 * 60 * 1000);
//				days = day.intValue();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return days;
//	}
	/**
	 * 根据日期类型type获取当前日期加或减去指定日期后的日期字符串
	 * 
	 * @param type
	 * @param addcount
	 * @return
	 */
	public static String addDate(String type, int addcount) {
		String clock = "";
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date); // 设置为当前时间
		switch (type) {
		case "MONTH":
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + addcount);
			clock = new SimpleDateFormat("yyyy-MM").format(calendar.getTime());
			break;
		case "DAY":
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + addcount);
			clock = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
			break;
		case "YEAR":
			calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + addcount);
			clock = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
			break;
		}
		return clock;
	}

	/**
	 * 根据日期类型type获取当前日期加或减去指定日期后的日期
	 * 
	 * @param date
	 * @param type
	 * @param addcount
	 * @return
	 */
	public static Date addDateTime(Date date, String type, int addcount) {
		Date clock = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date); // 设置为当前时间
		switch (type) {
		case "MONTH":
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + addcount);
			clock = calendar.getTime();
			break;
		case "DAY":
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + addcount);
			clock = calendar.getTime();
			break;
		case "YEAR":
			calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + addcount);
			clock = calendar.getTime();
			break;
		}

		return clock;
	}
	
	/**
     *  时间校验
     * @param
     * @return
     */
    public static boolean dateformat(int y,int m,int d){
        boolean p=false;
        if(m<1||m>12) {
            System.out.print("月份不合法");
            p=false;}
        else if(m==1||m==3||m==5||m==7||m==8||m==10||m==12) {
            if(d<=31) {
                p=true;}
            else {
                p=false;
                System.out.print("日期不合法");
            }
        }
        else if(m==2) {
            if(y%400==0||(y%4==0&&y%100!=0)) {
                if(d<=29) {
                    p=true;
                }
                else {
                    p=false;
                    System.out.print("日期不合法");
                }
            }
            else {
                if(d<=28){
                    p=true;
                }
                else {
                    p=false;
                    System.out.print("日期不合法");
                }
            }
        }
        else {
            if(d<=30){
                p=true;
            }
            else {
                p=false;
                System.out.print("日期不合法");
            }
        }
        return p;
    }
    
 // 时间转化
    public static   String dateConversion(Date currentTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    
  
    /**
     * 增加小时
     * @param date
     * @param hour
     * @return
     */
    public static Date addHour(Date date,int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        date = calendar.getTime();
        return date;
    }
    
    /**
     * 增加分钟
     * @param date
     * @param min
     * @return
     */
   
    
    public static Date addMin(Date date, int min) {
    	 Calendar calendar = Calendar.getInstance();
         calendar.setTime(date);
         calendar.add(Calendar.MINUTE, min);
         date = calendar.getTime();
         return date;
	}
    
    
    public static   String dateConversionM(Date currentTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateString = formatter.format(currentTime);
        return dateString;
    }




    /**
     * 获取某个年份前后list<map>集合
     */
    public static List<Map<String, Object>> getYearMap(String year,int mun) {
    	List<Map<String, Object>> yearList=new ArrayList();
    	Map<String, Object> yearMap=new HashMap<>();
    	for (int i = 0; i < mun*2+1; i++) {
    		yearMap=new HashMap<>();
    		yearMap.put("yearkey", Integer.parseInt(year)+i-mun);
    		yearMap.put("yearvalue", Integer.parseInt(year)+i-mun);
    		yearList.add(yearMap);
		}
        return yearList;
    }
    
    /**
    2 * 获取某月的时间,支持自定义时间格式
    3 * @param simpleDateFormat 时间格式,yyyy-MM-dd HH:mm:ss
    4 * @param index 为正表示当前时间加天数，为负表示当前时间减天数
    5 * @return String
    6 */
   public static String getTimeDay(String simpleDateFormat,int index){

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat fmt = new SimpleDateFormat(simpleDateFormat);
        calendar.add(Calendar.MONTH,index);
        String date = fmt.format(calendar.getTime());
        return date;
    }

    /**
     * 获取传入时间季度内开始日期和结束日期
     * 例：in 2022-02  return 2022-01-01&2022-03-31
     * @param time
     * @return
     */
    public static String getInQuarterBeginTimeAndEndTime(String time){
        Integer month=StrUtil.trimToInteger(time.substring(5,7));
        String year=time.substring(0,4);
        if(month>=1 && month<=3){
            time=year+"-01-01&"+year+"-03-31";
        }else if(month>=4 && month<=6){
            time=year+"-04-01&"+year+"-06-30";
        }else if(month>=7 && month<=9){
            time=year+"-07-01&"+year+"-09-30";
        }else if(month>=10 && month<=12){
            time=year+"-10-01&"+year+"-12-31";
        }
        return time;
    }

    /**
     * 判断两个时间段是否有交集并返回
     * @return
     */
    public static String [] getTimePeriod(Date oneBeginTime,Date oneEndTime,Date twoBeginTime,Date twoEndTime){
        String [] timePeriod=new String[2];
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if(oneBeginTime.getTime()<=twoBeginTime.getTime()){
                timePeriod[0]=sdf.format(twoBeginTime);
                if(oneEndTime.getTime()<=twoEndTime.getTime()){
                    timePeriod[1]=sdf.format(oneEndTime);
                }else{
                    timePeriod[1]=sdf.format(twoEndTime);
                }
            }else {
                timePeriod[0]=sdf.format(oneBeginTime);
                if(oneEndTime.getTime()<=twoEndTime.getTime()){
                    timePeriod[1]=sdf.format(oneEndTime);
                }else{
                    timePeriod[1]=sdf.format(twoEndTime);
                }
            }
        }catch (Exception ex){
            throw  ex;
        }
        return timePeriod;
    }

    /**
     * 根据开始时间、结束时间、时间段集合，返回时间交集集合
     * @return
     */
    public static List<Map<String,String>> getTimePeriodByTimeAndTimelist(String oneBeginTime,String oneEndTime,List timeList) throws ParseException {
        List<Map<String,String>> timePeriodList=new ArrayList<>();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date twoBeginTime=null;
            Date twoEndTime=null;
            String [] timePeriodChildren=null;
            Map<String,String> timePeriodmap=null;
            for (int i=0;i<timeList.size();i++){
                Map map= (Map) timeList.get(i);
                twoBeginTime=sdf.parse(StrUtil.trimToString(map.get("TWOBEGINTIME")));
                twoEndTime=sdf.parse(StrUtil.trimToString(map.get("TWOENDTIME")));
                timePeriodChildren=getTimePeriod(sdf.parse(oneBeginTime),sdf.parse(oneEndTime),twoBeginTime,twoEndTime);
                if(StringUtils.isNotBlank(timePeriodChildren[0])&&StringUtils.isNotBlank(timePeriodChildren[1])){
                    timePeriodmap=new HashMap<>();
                    timePeriodmap.put("begintime",timePeriodChildren[0]);
                    timePeriodmap.put("endtime",timePeriodChildren[1]);
                    timePeriodList.add(timePeriodmap);
                }
            }
        }catch (Exception ex){
            throw  ex;
        }
        return timePeriodList;
    }
}
