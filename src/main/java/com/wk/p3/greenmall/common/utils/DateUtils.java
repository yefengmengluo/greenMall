/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @author ThinkGem
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	private static String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	public static String formatDate2(Date date){
		return formatDate(date);
	}

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0)
		{
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	public static String formatDate(Date date, String pattern) {
		String formatDate = null;
		if (pattern != null)
		{
			formatDate = DateFormatUtils.format(date, pattern.toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}
	/**
	 * 得到当前时间字符串 格式（HH:mm）
	 */
	public static String getTimeHm(Date date) {
		return formatDate(date, "HH:mm");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	
	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t>0?t/(24*60*60*1000):0;
	}

	/**
	 * 获取指定时间与现在的天数差
	 * @param date
	 * @return
	 */
	public static String getDifferDays(Date date) {
		long t = date.getTime()-new Date().getTime();
		return String.valueOf(t/(24*60*60*1000));
	}

	/**
	 * 获取过去的小时
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*60*1000);
	}
	
	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}
	
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }
	
	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}
	public static String fmtDate(Date myDate,String fmt){
		String newDate="";
		SimpleDateFormat sDateformat = new SimpleDateFormat(fmt);
		newDate= sDateformat.format(myDate).toString();
		return newDate;
	}

	/**
	 * 前台展示时间差
	 * @return
	 */
	public static String getTimeDiffer(Date d){

		long l=new Date().getTime()-d.getTime();
		long day=l/(24*60*60*1000);
		long hour=(l/(60*60*1000)-day*24);
		long min=((l/(60*1000))-day*24*60-hour*60);
		long s=(l/1000-day*24*60*60-hour*60*60-min*60);
		//System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒");
		StringBuffer sb = new StringBuffer("");
		//用于前台展示时间

//		if(day>0){
//			sb.append(day+"天");
//			return sb.toString();
//		}
//		if(hour>0){
//			sb.append(hour+"小时");
//			return sb.toString();
//		}
//		if(min>0){
//			sb.append(min+"分钟");
//			return sb.toString();
//		}
//		if(sb.toString().equals("")){
//			sb.append("1分钟");
//			return sb.toString();
//		}

		if(day>0){
			sb.append(day+"天");
		}else if(hour>0 && !(day > 0)){
			sb.append(hour+"小时");
		}else if(min>0 && !(day > 0) && !(hour > 0)){
			sb.append(min+"分钟");
		}else{
			sb.append("1分钟");
		}
		return sb.toString();
	}


	/**
	 * 前台展示时间差(年)
	 * @return
	 */
	public static String getYearDiffer(Date d){

		long l=new Date().getTime()-d.getTime();
		long day=l/(24*60*60*1000);
		StringBuffer sb = new StringBuffer("");
		long year=day/365;
		if(year>0){
			sb.append((year+1)+"年");
		}else{
			sb.append("1年");
		}
		return sb.toString();
	}

	
	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
//		System.out.println(formatDate(parseDate("2010/3/6")));
//		System.out.println(getDate("yyyy年MM月dd日 E"));
//		long time = new Date().getTime()-parseDate("2012-11-19").getTime();
//		System.out.println(time/(24*60*60*1000));
		System.out.println(formatDate(new Date(),"yyyy-MM-dd"));
	}



	/**
	 * 将文字行的date格式成Date类型
	 * @param myDate
	 * @param fmt
	 * @return
	 */
	public static Date fmtDate(String myDate,String fmt){

		Date newDate=null;
		if((myDate != null) && (fmt != null)){
			Calendar cal = getCal(myDate, fmt);
			newDate=cal.getTime();
		}

		return newDate;

	}
	/**
	 * 计算指定日期经过多少小时后的日期
	 *
	 * @param deftime
	 *            自定日期
	 * @param oldfmt
	 *            日期格式
	 * @param timediff
	 *            小时为单位
	 * @param newfmt
	 *            返回值的格式
	 * @return 返回日期，timediff >0向前计算，timediff<0向后计算
	 */
	public static String getBeforeTimeByH(String deftime, String oldfmt, int timediff,
								   String newfmt) {
		String rq = null;
		try {
			if ((deftime != null) && (!deftime.equals(""))) {
				Calendar cal = getCal(deftime, oldfmt);
				if (cal != null) {
					cal.add(12, -timediff * 60);
					SimpleDateFormat sdf = new SimpleDateFormat(newfmt);
					rq = sdf.format(cal.getTime());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rq;
	}
	private static Calendar getCal(String strdate, String fmt) {
		Calendar cal = null;
		try {
			if ((strdate != null) && (fmt != null)) {

				SimpleDateFormat nowDate = new SimpleDateFormat(fmt);
				Date d = nowDate.parse(strdate, new ParsePosition(0));
				if (d != null) {
					cal = Calendar.getInstance();
					cal.clear();
					cal.setTime(d);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return cal;
	}

	public static Date formatString(String dateStr){
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", java.util.Locale.US);
		Date date = null;
		try{
			date = sdf.parse(dateStr);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return date;
	}
}
