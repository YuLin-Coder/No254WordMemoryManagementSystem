package com.ian.media.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Time {
	public static String nowDateToString(){
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return format.format(date);
	}
	
	public static String nowDateToStringForName(){
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		
		return format.format(date);
	}


    public static String long2Date(long time){
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static Date String2Date(String time){
        Date date = null;
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date=formatter.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    //获得当天0点时间
    public static int getDayBegin(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (int) (cal.getTimeInMillis()/1000);
    }

    public static Long date2Long(Date date){
        return date.getTime();
    }
	
	public static void main(String[] args){
		System.out.println(getDayBegin());
	}
}
