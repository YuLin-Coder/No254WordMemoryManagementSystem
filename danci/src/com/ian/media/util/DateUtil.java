package com.ian.media.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.time.DateFormatUtils;

/**
 * 日期工具类
 * User: ZhangKe
 * Date: 12-8-4
 * Time: 上午10:51
 * Version: 1.0
 */
public class DateUtil {
	
	/**
	 * 获取当前系统时间
	 * @return Long类型
	 */
	public static Long getCurrentTime() {
		return System.currentTimeMillis();
	}
	
	/**
	 * 将timestamp类型转换为长整型毫秒数
	 * @param time
	 * @return
	 */
	public static Long getTime(Timestamp time) {
		if(StringUtil.isNotBlank(time)) {
			return time.getTime();
		} else {
			return null;
		}
	}
	
	
    /**
     * getDateStr get a string with format YYYY-MM-DD from a Date object
     *
     * @param date date
     * @return String
     */
    static public String getDateStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    static public String getYear(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return format.format(date);
    }

    static public String getDateStrC(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);
    }

    static public String getDateStrCompact(Date date) {
        if (date == null)
            return "";
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String str = format.format(date);
        return str;
    }

    /**
     * 传入指定年份，指定月份返回该年份该月份多少天
     *
     * @param year
     * @param mon
     * @return
     */
    public static int getDaysByYearhAndMonth(int year, int mon) {
        int days = 0;
        boolean bool = true;
        bool = mon == 0;
        do {
            int niansum = 0;
            for (int i = 1900; i < year; i++) {
                if ((i % 4 == 0 && i % 100 != 0) || i % 400 == 0) {
                    niansum = niansum + 366;
                } else {
                    niansum = niansum + 365;
                }
            }
            int sumdays = 0;
            int zonghe;
            for (int i = 1; i <= mon; i++) {
                switch (i) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        days = 31;
                        break;
                    case 2:
                        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                            days = 29;
                        } else {
                            days = 28;
                        }
                        break;
                    default:
                        days = 30;
                }
                if (i < mon) {
                    sumdays = sumdays + days;
                }
            }

        } while (mon < 13 && bool);
        return days;
    }

    /**
     * getDateStr get a string with format YYYY-MM-DD HH:mm:ss from a Date
     * object
     *
     * @param date date
     * @return String
     */
    static public String getDateTimeStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    static public String getDateTimeStrC(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        return format.format(date);
    }

    public static String getCurDateStr(String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(new Date());
    }

    /**
     * Parses text in 'YYYY-MM-DD' format to produce a date.
     *
     * @param s the text
     * @return Date
     * @throws java.text.ParseException
     */
    static public Date parseDate(String s) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(s);
    }
    
   
      
    static public Date parseDateC(String s) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.parse(s);
    }

    /**
     * Parses text in 'YYYY-MM-DD' format to produce a date.
     *
     * @param s the text
     * @return Date
     * @throws java.text.ParseException
     */
    static public Date parseDateTime(String s) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.parse(s);
    }

    static public Date parseDateTimeC(String s) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        return format.parse(s);
    }

    /**
     * Parses text in 'HH:mm:ss' format to produce a time.
     *
     * @param s the text
     * @return Date
     * @throws java.text.ParseException
     * 
     */
    public static String currentDateTimeToStrings()
    {
      return date2String(new Date(), "yyyyMMddHHmmss");
    }
    
     static String date2String(Date date, String pattern)
    {
      if (date == null)
        return null;

      return DateFormatUtils.format(date, pattern);
    }
    
    static public Date parseTime(String s) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.parse(s);
    }

    static public Date parseTimeC(String s) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("HH时mm分ss秒");
        return format.parse(s);
    }

    static public int yearOfDate(Date s) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String d = format.format(s);
        return Integer.parseInt(d.substring(0, 4));
    }

    static public int monthOfDate(Date s) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String d = format.format(s);
        return Integer.parseInt(d.substring(5, 7));
    }

    static public int dayOfDate(Date s) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String d = format.format(s);
        return Integer.parseInt(d.substring(8, 10));
    }

    static public String getDateTimeStr(java.sql.Date date, double time) {
        int year = date.getYear() + 1900;
        int month = date.getMonth() + 1;
        int day = date.getDate();
        String dateStr = year + "-" + month + "-" + day;
        Double d = new Double(time);
        String timeStr = String.valueOf(d.intValue()) + ":00:00";

        return dateStr + " " + timeStr;
    }

    /**
     * Get the total month from two date.
     *
     * @param sd the start date
     * @param ed the end date
     * @return int month form the start to end date
     * @throws java.text.ParseException
     */
    static public int diffDateM(Date sd, Date ed) throws ParseException {
        return (ed.getYear() - sd.getYear()) * 12 + ed.getMonth()
                - sd.getMonth() + 1;
    }

    static public int diffDateD(Date sd, Date ed) throws ParseException {
        return Math.round((ed.getTime() - sd.getTime()) / 86400000) + 1;
    }

    static public int diffDateM(int sym, int eym) throws ParseException {
        return (Math.round(eym / 100) - Math.round(sym / 100)) * 12
                + (eym % 100 - sym % 100) + 1;
    }

    static public java.sql.Date getNextMonthFirstDate(java.sql.Date date)
            throws ParseException {
        Calendar scalendar = new GregorianCalendar();
        scalendar.setTime(date);
        scalendar.add(Calendar.MONTH, 1);
        scalendar.set(Calendar.DATE, 1);
        return new java.sql.Date(scalendar.getTime().getTime());
    }

    static public java.sql.Date getFrontDateByDayCount(java.sql.Date date,
                                                       int dayCount) throws ParseException {
        Calendar scalendar = new GregorianCalendar();
        scalendar.setTime(date);
        scalendar.add(Calendar.DATE, -dayCount);
        return new java.sql.Date(scalendar.getTime().getTime());
    }

    /**
     * Get first day of the month.
     *
     * @param year  the year
     * @param month the month
     * @return Date first day of the month.
     * @throws java.text.ParseException
     */
    static public Date getFirstDay(String year, String month)
            throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(year + "-" + month + "-1");
    }

    static public Date getFirstDay(int year, int month) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(year + "-" + month + "-1");
    }

    static public Date getLastDay(String year, String month)
            throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(year + "-" + month + "-1");

        Calendar scalendar = new GregorianCalendar();
        scalendar.setTime(date);
        scalendar.add(Calendar.MONTH, 1);
        scalendar.add(Calendar.DATE, -1);
        date = scalendar.getTime();
        return date;
    }

    static public Date getLastDay(int year, int month) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(year + "-" + month + "-1");

            Calendar scalendar = new GregorianCalendar();
            scalendar.setTime(date);
            scalendar.add(Calendar.MONTH, 1);
            scalendar.add(Calendar.DATE, -1);
            date = scalendar.getTime();
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    /**
     * getToday get todat string with format YYYY-MM-DD from a Date object
     *
     * @return String
     */

    static public String getTodayStr() {
        Calendar calendar = Calendar.getInstance();
        return getDateStr(calendar.getTime());
    }

    static public Date getToday() {
        Date date = null;
        try {
            date = new Date(System.currentTimeMillis());
            return date;
        } catch (Exception e) {
            e.printStackTrace();
            return new Date();
        }
    }

    static public String getTodayAndTime() {
        return new Timestamp(System.currentTimeMillis()).toString();
    }

    static public String getTodayC() {
        Calendar calendar = Calendar.getInstance();
        return getDateStrC(calendar.getTime());
    }

    static public int getThisYearMonth() {
        Date today = Calendar.getInstance().getTime();
        return (today.getYear() + 1900) * 100 + today.getMonth() + 1;
    }

    static public int getYearMonth(Date date) {
        return (date.getYear() + 1900) * 100 + date.getMonth() + 1;
    }

    // 获取相隔月数
    static public long getDistinceMonth(String beforedate, String afterdate)
            throws ParseException {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        long monthCount = 0;
        try {
            Date d1 = d.parse(beforedate);
            Date d2 = d.parse(afterdate);

            monthCount = (d2.getYear() - d1.getYear()) * 12 + d2.getMonth()
                    - d1.getMonth();
            // dayCount = (d2.getTime()-d1.getTime())/(30*24*60*60*1000);

        } catch (ParseException e) {
            // throw e;
        }
        return monthCount;
    }

    // 获取相隔天数
    static public long getDistinceDay(String beforedate, String afterdate)
            throws ParseException {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        long dayCount = 0;
        try {
            Date d1 = d.parse(beforedate);
            Date d2 = d.parse(afterdate);

            dayCount = (d2.getTime() - d1.getTime()) / (24 * 60 * 60 * 1000);

        } catch (ParseException e) {
            // throw e;
        }
        return dayCount;
    }

    // 获取相隔天数
    static public long getDistinceDay(Date beforedate, Date afterdate)
            throws ParseException {
        long dayCount = 0;

        try {
            dayCount = (afterdate.getTime() - beforedate.getTime())
                    / (24 * 60 * 60 * 1000);

        } catch (Exception e) {
            // // throw e;
        }
        return dayCount;
    }

    static public long getDistinceDay(java.sql.Date beforedate,
                                      java.sql.Date afterdate) throws ParseException {
        long dayCount = 0;

        try {
            dayCount = (afterdate.getTime() - beforedate.getTime())
                    / (24 * 60 * 60 * 1000);

        } catch (Exception e) {
            // // throw e;
        }
        return dayCount;
    }

    // 获取相隔天数
    static public long getDistinceDay(String beforedate) throws ParseException {
        return getDistinceDay(beforedate, getTodayStr());
    }

    // 获取相隔时间数
    static public long getDistinceTime(String beforeDateTime,
                                       String afterDateTime) throws ParseException {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        long timeCount = 0;
        try {
            Date d1 = d.parse(beforeDateTime);
            Date d2 = d.parse(afterDateTime);

            timeCount = (d2.getTime() - d1.getTime()) / (60 * 60 * 1000);

        } catch (ParseException e) {
            throw e;
        }
        return timeCount;
    }

    // 获取相隔时间数
    static public long getDistinceTime(String beforeDateTime)
            throws ParseException {
        return getDistinceTime(beforeDateTime, new Timestamp(System
                .currentTimeMillis()).toLocaleString());
    }

    /**
     * 获得当前时间的下几天，
     *
     * @param nextCount 可以是负数
     * @return
     */
    public static String getNextDay(int nextCount) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String[] time = format.format(now).split("-");
        int year = Integer.parseInt(time[0]);
        int month = Integer.parseInt(time[1]);
        int day = Integer.parseInt(time[2]);
        Calendar can = Calendar.getInstance();
        can.set(year, month - 1, day);
        can.add(Calendar.DAY_OF_MONTH, nextCount);
        return format.format(can.getTime());
    }

    /**
     * 指定日期的下几天是多少号
     *
     * @param inputTime
     * @param nextCount
     * @return
     */
    public static String getNextDay(Date inputTime, int nextCount) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // Date now = new Date();
        String[] time = format.format(inputTime).split("-");
        int year = Integer.parseInt(time[0]);
        int month = Integer.parseInt(time[1]);
        int day = Integer.parseInt(time[2]);
        Calendar can = Calendar.getInstance();
        can.set(year, month - 1, day);
        can.add(Calendar.DAY_OF_MONTH, nextCount);
        return format.format(can.getTime());
    }

    /**
     * 获得星期几
     *
     * @param date
     * @return
     */
    public static String getWeekday(String date) {// 必须yyyy-MM-dd
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdw = new SimpleDateFormat("E");
        Date d = null;
        try {
            d = sd.parse(date);

        } catch (ParseException e) {

            e.printStackTrace();

        }
        return sdw.format(d);
    }

    /**
     * 指定日期是一周中的第几天
     *
     * @param date
     * @return
     */
    public static int getWeekdayIndex(String date) {// 必须yyyy-MM-dd
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdw = new SimpleDateFormat("E");
        String dayNames[] = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
        Date d = null;
        try {
            d = sd.parse(date);

        } catch (ParseException e) {

            e.printStackTrace();

        }
        int index = 0;
        for (int i = 0; i < dayNames.length; i++) {
            if (dayNames[i].equals(sdw.format(d))) {
                index = i + 1;
                break;
            }
        }
        return index;
    }
    
    /**
     * 日期于当前相差多少天
     *
     * @param string
     * @return
     * @throws ParseException 
     */
    public static boolean getDifference(String date) throws ParseException {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date newDate =  new  Date();
        Date oldDate =  d.parse(date);
    	long date1 = oldDate.getTime();
    	long date2 =  newDate.getTime();
    	long days = (date2 - date1)/ (1000 * 60 * 60 * 24);
    	if(days<3){
    		return true;
    	}
    	return false;
    	
    }
}
