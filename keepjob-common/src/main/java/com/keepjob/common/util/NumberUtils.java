package com.keepjob.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 数字相关处理类
 * @author leolian
 * 
 */
public class NumberUtils {

	private NumberUtils() {
	}

	/**
	 * @param oneValue
	 * @param twoValue
	 * @return
	 */
	public static boolean isEquation(double oneValue, double twoValue) {
		return isEquation(oneValue, twoValue, 0.000001d);
	}

	/**
	 * @param oneValue
	 * @param twoValue
	 * @param critical
	 * @return
	 */
	public static boolean isEquation(double oneValue, double twoValue,
			double critical) {
		return (Math.abs(oneValue - twoValue) <= critical) ? true : false;
	}

	/**
	 * @param oneValue
	 * @param twoValue
	 * @return
	 */
	public static double addDouble(double oneValue, double twoValue) {
		BigDecimal one = new BigDecimal(String.valueOf(replaceEmpty(oneValue)));
		BigDecimal two = new BigDecimal(String.valueOf(replaceEmpty(twoValue)));
		return one.add(two).doubleValue();

	}

	/**
	 * @param value
	 * @param digit
	 * @return
	 */
	public static String patchZeroToString(long value, int digit) {
		StringBuffer result = new StringBuffer("");
		int index = ((int) Math.log10(value)) + 1;
		if (index < digit) {
			for (int i = 0; i < digit - index; i++) {
				result.append("0");
			}
		}
		result.append(value);
		return result.toString();
	}

	public static Double formatDoubleDigitDecimal(double src) {
		DecimalFormat df = new DecimalFormat("#.##");
		try {
			return Double.parseDouble(df.format(src));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0.0;
	}
	
	public static String formatDouble(double src) {
		DecimalFormat df = new DecimalFormat("#.##");
		try {
			return df.format(src);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "0.0";
	}
	
	public static BigDecimal formatBigDecimalDigitDecimal(double src) {
		DecimalFormat df = new DecimalFormat("#.##");
		try {
			return new BigDecimal(df.format(src));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new BigDecimal("0.0");
	}

	/**
	 * @param src
	 * @return
	 */
	public static Double replaceEmpty(Double src) {
		return null == src ? 0.0D : src;
	}
	
	public static Double replaceDouble(String src) {
		if (null == src || "".equals(src) || "NULL".equals(src.toUpperCase())) {
			return 0.0D;
		}
		try {
			return Double.parseDouble(StringUtils.trimToEmpty(src));
		} catch (Exception e) {
			return 0.0D;
		}
	}
	
	public static Double replaceEmpty(Double src, Double defaultValue) {
		return (null == src || 0.0D == src) ? defaultValue : src;
	}
	
	public static Double toEmpty(Double src, Double defaultValue) {
		return null == src ? defaultValue : src;
	}
	
	public static Long replaceLong(String src) {
		if (null == src || "".equals(src) || "NULL".equals(src.toUpperCase())) {
			return 0L;
		}
		try {
			return Long.parseLong(src);
		} catch (Exception e) {
			return 0L;
		}

	}
	public static Integer replaceInteger(String src) {
		if (null == src || "".equals(src) || "NULL".equals(src.toUpperCase())) {
			return 0;
		}
		try {
			return Integer.parseInt(src);
		} catch (Exception e) {
			return 0;
		}

	}
	public static Double roundDouble(double src, int precision) {
		Double ret = null;
		try {
			double factor = Math.pow(10, precision);
			ret = Math.floor(src * factor + 0.5) / factor;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static Double replaceEmpty(BigDecimal src) {
		return null == src ? 0.0D : src.doubleValue();
	}

	public static Long replaceEmpty(Long src) {
		return null == src ? 0L : src;
	}

	public static Integer replaceEmpty(Integer src) {
		return null == src ? 0 : src;
	}

	public static Integer replaceEmpty(Integer src, Integer defaultValue) {
		return null == src ? defaultValue : src;
	}
	
	public static boolean isEmpty(Long src) {
		return (null == src || 0L == src || -1L == src) ? Boolean.TRUE
				: Boolean.FALSE;
	}
	
	public static boolean isEmpty(Integer src) {
		return (null == src || 0 == src || -1 == src) ? Boolean.TRUE
				: Boolean.FALSE;
	}
	
	public static boolean isEmpty(Short src) {
		return (null == src || 0 == src || -1 == src) ? Boolean.TRUE
				: Boolean.FALSE;
	}
	
	public static boolean isEmpty(Double src) {
		return (null == src || 0.0D == src || isEquation(src, 0.0D)) ? Boolean.TRUE
				: Boolean.FALSE;
	}
	
	public static String convertToString(Double  src){
		return String.valueOf(replaceEmpty(src));
	}
	
	public static String convertToString(BigDecimal  src){
		return String.valueOf(replaceEmpty(src));
	}
}
