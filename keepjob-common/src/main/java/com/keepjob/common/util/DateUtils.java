package com.keepjob.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.keepjob.common.exception.ApplicationException;


public final class DateUtils {

	private final static Logger logger = LoggerFactory.getLogger(DateUtils.class);

	public static String DATE_FORMAT = "yyyy-MM-dd";
	
	public static String TIME_FORMAT = "HH:mm:ss";
	
	public static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static String FULL_FORMAT_SPEPARATE="yyyy-MM-dd HH:mm:ss.SSS";
	/**
	 * 
	 * 
	 * @param value
	 *            String yyyy-MM-dd
	 * @return Date
	 */
	public static Date string2date(String value) {
		if (value == null || value.trim().equals("")) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		try {
			return sdf.parse(value);
		} catch (ParseException e) {
			logger.error("{}", e);
			throw new RuntimeException(e);
		}
	}

	public static Date string2dateTime(String value) {
		if (value == null || value.trim().equals("")) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
		try {
			return sdf.parse(value);
		} catch (ParseException e) {
			logger.error("{}", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将字符串转换成指定格式的日期值
	 * @param value
	 *            String 需转换为日期的字符串
	 * @param format
	 *            String 日期格式
	 * @return Date 返回日期值
	 */
	public static Date string2date(String value, String format) {
		if (value == null || value.trim().equals("")) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(value);
		} catch (ParseException e) {
			logger.error("{}", e);
			throw new RuntimeException(e);
		}
	}

	public static String date2string(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		return sdf.format(date);
	}

	/**
	 * 日期转指定格式字符串
	 * @param date 日期
	 * @param format 格式
	 * @return
	 */
	public static String date2string(Date date, String format) {
		if (date == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static String getNowDate(String format) {
		String dateTime = "";
		try {
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			dateTime = sdf.format(now);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return dateTime;
	}

	public static Date getCurrentDateTime(){
		return DateUtils.string2dateTime(DateUtils.getNowDateTime());
	}
	
	public static String getNowDate() {
		return getNowDate(DATE_FORMAT);
	}

	public static String getNowDateTime() {
		return getNowDate(DATE_TIME_FORMAT);
	}

	public static long getDaysInterval(Date date, Date date2) {
		return (date2.getTime() - date.getTime()) / 86400000;
	}

	public static java.sql.Timestamp getTimestamp() {
		return java.sql.Timestamp.valueOf(getNowDateTime());
	}

	public static String getDate(int days) {
		Date date = new Date();
		date.setTime(date.getTime() + 86400000L * days);
		return date2string(date);
	}

	public static String getDaydiffDate(Date date, int days) {
		Date result = new Date();
		result.setTime(date.getTime() + 86400000L * days);
		return date2string(result);
	}

	public static String getDaydiffDate(String date, int days) {
		Date result = new Date();
		Date temp = string2date(date);
		result.setTime(temp.getTime() + 86400000L * days);
		return date2string(result);
	}

	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	public static int getNowYear() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar.get(Calendar.YEAR);
	}

	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	public static int getNowMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar.get(Calendar.MONTH) + 1;
	}

	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static int getNowDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static int getAge(Date birthDay) {
		if(null != birthDay){
			Calendar sysCalendar = Calendar.getInstance();
			Calendar birCalendar = Calendar.getInstance();
			birCalendar.setTime(birthDay);
			if (sysCalendar.get(Calendar.MONTH) > birCalendar.get(Calendar.MONTH)){
				return sysCalendar.get(Calendar.YEAR) - birCalendar.get(Calendar.YEAR) + 1;
			}else{
				return sysCalendar.get(Calendar.YEAR) - birCalendar.get(Calendar.YEAR);
			}
		}
		return 0;
	}
	
	public static Date getBirthDay(String idCard){
		String tempStr = "";
		idCard = StringUtils.trimToEmpty(idCard);
		if(idCard.length() != 15 && idCard.length() != 18){
			throw new ApplicationException("身份证号格式错误.");
		}
		
		if(idCard.length() == 15){
			tempStr = "19" + idCard.substring(6, 12);
		}else{
			tempStr = idCard.substring(6, 14);
		}
		tempStr = tempStr.substring(0, 4) + "-" + tempStr.substring(4, 6) + "-" + tempStr.substring(6);
		return DateUtils.string2date(tempStr);
	}
	
	public static Date addDays(int days){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}
	
	public static Date addDays(Date date, int days){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}
	
	public static Date addMonths(int months){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}
	
	public static Date addYears(int years){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.YEAR, years);
		return calendar.getTime();
	}
	
	public static Date addMonths(Date date, int months){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}
	
	public static Date addYears(Date date, int years){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, years);
		return calendar.getTime();
	}
	
	public static String getLunarForStr(Date date){
		Calendar today = Calendar.getInstance();
		today.setTime(date);
		Lunar lunar=new Lunar(today);
		return lunar.getDate();
	}
	
	public static Date getLunarForDate(Date date){
		return string2date(getLunarForStr(date));
	}
	
	public static Date getFullStartDateTime(Date time){
		String start=DateUtils.date2string(time, DateUtils.DATE_FORMAT)+" 00:00:00.000";
		return DateUtils.string2date(start, DateUtils.FULL_FORMAT_SPEPARATE);
	}
	
	public static Date getFullStartDateTime(String date){
		return DateUtils.string2date(date+" 00:00:00.000", DateUtils.FULL_FORMAT_SPEPARATE);
	}
	
	public static Date getFullEndDateTime(Date time){
		String end=DateUtils.date2string(time, DateUtils.DATE_FORMAT)+" 23:59:59.999";
		return DateUtils.string2date(end, DateUtils.FULL_FORMAT_SPEPARATE);
	}
	
	public static Date getFullEndDateTime(String date){
		return DateUtils.string2date(date+" 23:59:59.999", DateUtils.FULL_FORMAT_SPEPARATE);
	}
}
