package com.ian.media.util.excel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;

/**
 * <p>
 * 文件名称: DateConvert.java
 * </p>
 * <p>
 * 文件描述: 用于日期/时间的各种类型转换
 * </p>
 * <p>
 * </p>
 * <p>
 * </p>
 * <p>
 * 内容摘要: 将日期参数通过指定的格式转换后，返回相应的字符串（包括日期/时间中文格式的转换）
 * </p>
 * <p>
 * 其他说明: 1、将日期全部转换成中文字符串；2、日期之间的大小比较
 * </p>
 * <p>
 * </p>
 * 
 */

public class DateUtil implements java.io.Serializable {

	private static final long serialVersionUID = -7292443579994483922L;

	/**
	 * 日志文件定义
	 */
	private static Logger log = Logger.getLogger(DateUtil.class);

	/**
	 * 转换格式的定义
	 */
	private static SimpleDateFormat dateformat = new SimpleDateFormat();;

	/**
	 * 构造器
	 * 
	 */
	public DateUtil() {
	}

	/**
	 * 将字符串形式的日期/时间转换成java.util.Date类型
	 * 
	 * @param strDate
	 *            String 字符串形式的日期/时间
	 * @param pattern
	 *            String 需要转换的格式(如：yyyy-MM-dd)
	 * @return 返回Date类型的日期
	 */
	public static Date parseDateFormat(String strDate, String pattern) {
		Date date = null;
		dateformat.applyPattern(pattern);
		try {
			date = dateformat.parse(strDate);
		} catch (Exception e) {
			log.error(e);
		}
		return date;
	}

	/**
	 * 将日期/时间参数转换成<yyyy-MM-dd HH:mm:ss,SSS>格式的字符串
	 * 
	 * @param date
	 *            java.util.Date 时间参数
	 * @return 转换后的时间字符串
	 */
	public String getDateMilliFormat(Date date) {
		return getDateFormat(date, "yyyy-MM-dd HH:mm:ss,SSS");
	}

	/**
	 * 将日期/时间参数转换成<yyyy-MM-dd HH:mm:ss>格式的字符串
	 * 
	 * @param date
	 *            java.util.Date 时间参数
	 * @return 转换后的时间字符串
	 */
	public static String getDateSecondFormat(Date date) {
		return getDateFormat(date, "yyyy-MM-dd HH:mm:ss");
	}
	
	public static String getDateThreeFormat(Date date){
		return getDateFormat(date, "yyyy-MM-dd HH时mm分ss秒");
	}

	/**
	 * 将日期/时间参数转换成<yyyy-MM-dd HH:mm>格式的字符串
	 * 
	 * @param date
	 *            java.util.Date 时间参数
	 * @return 转换后的时间字符串
	 */
	public String getDateMinuteFormat(Date date) {
		return getDateFormat(date, "yyyy-MM-dd HH:mm");
	}

	/**
	 * 将日期/时间参数转换成<yyyy-MM-dd>格式的字符串
	 * 
	 * @param date
	 *            java.util.Date 时间参数
	 * @return 转换后的时间字符串
	 */
	public String getShortDateFormat(Date date) {
		return getDateFormat(date, "yyyy-MM-dd");
	}

	/**
	 * 将日期/时间参数转换成<yyyyMMdd>格式的字符串
	 * 
	 * @param date
	 *            java.util.Date 时间参数
	 * @return 转换后的时间字符串
	 */
	public String getSuperShortDateFormat(Date date) {
		return getDateFormat(date, "yyyyMMdd");
	}

	/**
	 * 获取指定格式的日期/时间字符串
	 * 
	 * @param date
	 *            java.util.Date 日期/时间参数
	 * @param pattern
	 *            String 需要转换的格式
	 * @return 转换后的日期/时间字符串
	 */
	public static String getDateFormat(java.util.Date date, String pattern) {
		if(date==null){
			return "";
		}else{			
			dateformat.applyPattern(pattern);
			return dateformat.format(date);
		}
	}

	/**
	 * 将时间参数转换成<b>长</b>时间格式的字符串 时间格式：2010年05月19日 08时35分18秒
	 * 
	 * @param date
	 *            java.util.Date 时间参数
	 * @return 转换后的时间字符串
	 */
	public String toLongDateString(Date date) {
		dateformat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
		return dateformat.format(date);
	}

	/**
	 * 将时间参数转换成<b>年份或月份</b>时间格式的字符串 时间格式：2011或01
	 * 
	 * @param date
	 *            java.util.Date 时间参数
	 * @param flag
	 *            String 年分/月份标记
	 * @return String 转换后的年份或月份
	 */
	public String getDateYearFormat(Date date, String flag) {
		if (flag.equals("year"))
			return getDateFormat(date, "yyyy");
		else
			return getDateFormat(date, "MM");
	}

	/**
	 * 将时间参数转换成<b>短</b>时间格式的字符串 时间格式：2010年05月19日
	 * 
	 * @param date
	 *            java.util.Date 时间参数
	 * @return 转换后的时间字符串
	 */
	public String toShortDateString(Date date) {
		dateformat = new SimpleDateFormat("yyyy年MM月dd日");
		return dateformat.format(date);
	}

	/**
	 * 判断当前时间是否在时间date2之前 时间格式 2005-4-21 16:16:34
	 * 
	 * @param date2
	 *            String：被比较时间
	 * @return 如果当前时间在date2之前，返回真；否则，返回假
	 */
	public boolean isDateBefore(String date2) {
		try {
			Date date1 = new Date();
			DateFormat df = DateFormat.getDateTimeInstance();
			return date1.before(df.parse(date2));
		} catch (ParseException e) {
			log.error("判断当前时间是否在时间" + date2 + "之前", e);
			return false;
		}
	}

	/**
	 * 判断时间date1是否在时间date2之前 时间格式 2005-4-21 16:16:34
	 * 
	 * @param date1
	 *            String：比较时间
	 * @param date2
	 *            String：被比较时间
	 * @return 如果date1在date2之前，返回真；否则，返回假
	 */
	public boolean isDateBefore(String date1, String date2) {
		try {
			DateFormat df = DateFormat.getDateTimeInstance();
			return df.parse(date1).before(df.parse(date2));
		} catch (ParseException e) {
			log.error("判断时间" + date1 + "是否在时间" + date2 + "之前", e);
			return false;
		}
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param enddate
	 *            String 后一个日期
	 * @param startdate
	 *            String 前一个日期
	 * @return int 返回两个日期之间相差的天数（正整数）
	 */
	public int getHowLongDays(String enddate, String startdate) {
		try {
			Calendar start = Calendar.getInstance();
			Calendar end = Calendar.getInstance();

			start.set(Integer.parseInt(startdate.substring(0, 4)), Integer
					.parseInt(startdate.substring(4, 6)) - 1, Integer
					.parseInt(startdate.substring(6, 8)));

			end.set(Integer.parseInt(enddate.substring(0, 4)), Integer
					.parseInt(enddate.substring(4, 6)) - 1, Integer
					.parseInt(enddate.substring(6, 8)));

			long starttime = start.getTimeInMillis();
			long endtime = end.getTimeInMillis();
			int howlongdays = new Long((endtime - starttime)
					/ (1000 * 60 * 60 * 24)).intValue();
			return howlongdays;
		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * 对于日期以.0结尾的日期字符串去掉后面的.0
	 * 
	 * @param dataStr
	 * @return
	 */
	public String getDateStr(String dataStr) {
		if (!"".equals(dataStr)) {
			if (dataStr.endsWith(".0"))
				return dataStr.substring(0, dataStr.lastIndexOf(".0"));
			else
				return dataStr;
		} else {
			return "";
		}
	}

	/**
	 * 获取当前期日是星期几
	 * 
	 * @param dt
	 * @return
	 */
	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;

		return weekDays[w];
	}

	public static String getAMPM(Date dt) {
		Calendar c = Calendar.getInstance();
		return Calendar.AM == c.get(Calendar.AM_PM) ? "上午" : "下午";
	}

	public static String getDifferMinutesAndSecond(Date began, Date end) {
		long between = (end.getTime() - began.getTime()) / 1000;
		long minute = between % 3600 / 60;
		long second = between % 60 / 60;
		return minute + "分" + second + "秒";
	}

	public static long getDifferMinutes(Date began, Date end) {
		long between = (end.getTime() - began.getTime()) / 1000;
		long minute = between % 3600 / 60;
		return minute;
	}

	/**
	 * 根据指定的日期时间格式，将一个日期时间字符串转化为时间， 并增加或减少相应天数，最后将新时间以字符串形式返回
	 * 
	 * @param dateStr
	 *            当前日期时间字符串
	 * @param amount
	 *            增加或减少的天数（正数为增加，负数为减小）
	 * @param pattern
	 *            指定的时间格式
	 * @return
	 */
//	public static String addDays(String dateStr, int amount, String pattern) {
//		Date currentDate = parseDateFormat(dateStr, pattern);
//		currentDate = DateUtils.addDays(currentDate, amount);
//		String newDateStr = getDateFormat(currentDate, pattern);
//		return newDateStr;
//	}
	
	/**
	 * 将标准英文格式的时间字符串转化为Date对象
	 * @param dateStr 格式固定为：Thu Jul 25 20:49:00 CST 2013
	 * @return
	 */
	public static Date getDateFromForeginStr(String dateStr){
		if(dateStr != null && dateStr.length() == 28 && dateStr.indexOf("CST") == 20){
			String year = dateStr.substring(24, 28);
			String date = dateStr.substring(8, 10);
			String time = dateStr.substring(11, 19);
			String month = dateStr.substring(4,7);
			if(month.equals("Jan")){
			dateStr = year + "-" + "01" + "-" + date+" "+time;
			}
			if(month.equals("Feb")){
				dateStr = year + "-" + "02" + "-" + date+" "+time;
			}
			if(month.equals("Mar")){
				dateStr = year + "-" + "03" + "-" + date+" "+time;
			}
			if(month.equals("Apr")){
				dateStr = year + "-" + "04" + "-" + date+" "+time;
			}
			if(month.equals("May")){
				dateStr = year + "-" + "05" + "-" + date+" "+time;
			}
			if(month.equals("Jun")){
				dateStr = year + "-" + "06" + "-" + date+" "+time;
			}
			if(month.equals("Jul")){
				dateStr = year + "-" + "07" + "-" + date+" "+time;
			}
			if(month.equals("Aug")){
				dateStr = year + "-" + "08" + "-" + date+" "+time;
			}
			if(month.equals("Sep")){
				dateStr = year + "-" + "09" + "-" + date+" "+time;
			}
			if(month.equals("Oct")){
				dateStr = year + "-" + "10" + "-" + date+" "+time;
			}
			if(month.equals("Nov")){
				dateStr = year + "-" + "11" + "-" + date+" "+time;
			}
			if(month.equals("Dec")){
				dateStr = year + "-" + "12" + "-" + date+" "+time;
			}
			return DateUtil.parseDateFormat(dateStr,"yyyy-MM-dd HH:mm:ss");
			}
		return null;
	}

	public static void main(String args[]) {
		// System.out.println("test");
		// System.out.println(getDateFormat(new Date(), "yyyyMMddHHmmsss"));
//		Date date = parseDateFormat("2013-2-28", "yyyy-MM-dd");
//		date = DateUtils.addDays(date, -1);
//		System.out.println(date);
		String dateStr="Thu Jul 25 20:49:00 CST 2013";
		Date d=getDateFromForeginStr(dateStr);
	}
}
