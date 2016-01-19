package com.keepjob.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class StringUtils extends org.apache.commons.lang.StringUtils {
	public static String isoToGbk(String src) {
		if (src == null) {
			return null;
		}
		try {
			return new String(src.getBytes("ISO-8859-1"), "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String isoToUtf(String src) {
		if (src == null) {
			return null;
		}
		try {
			return new String(src.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String gbkToUtf(String src) {
		if (src == null) {
			return null;
		}
		try {
			return URLEncoder.encode(src, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String utfToGbk(String src) {
		if (src == null) {
			return null;
		}
		try {
			return URLDecoder.decode(src, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 字符串前补零操作。
	 * @param src
	 * @param length
	 * @return
	 */
	public static String patchZeroToString(String src, int length){
		StringBuffer result= new StringBuffer("");
		int index = src.trim().length();
		if(index<length){
			for(int i=(length-index);i>0;i--){
				result.append("0");	
			}
		}
		result.append(src.trim());
		
		return result.toString();
	}
	
	/**
	 * 字符串后补零操作。
	 * @param src
	 * @param length
	 * @return
	 */
	public static String afterZeroToString(String src, int length){
		StringBuffer result= new StringBuffer("");
		int index = src.trim().length();
		result.append(src.trim());
		if(index<length){
			for(int i=(length-index);i>0;i--){
				result.append("0");	
			}
		}
		
		return result.toString();
	}
	/**
	 * 货币金额小写转大写
	 * 
	 * @param money
	 *            货币金额
	 * @return
	 */
	public static String moneyLToU(String money) {
		String result = "";
		StringBuffer item = new StringBuffer("");
		int index = 1;
		int len = 0;
		if (null == money)
			throw new RuntimeException("The input param is null");

		money = money.trim();
		if ("".equals(money))
			throw new java.lang.RuntimeException("The input param is empty");

		try {
			Float.parseFloat(money);
		} catch (NumberFormatException ex) {
			throw ex;
		}

		if (money.indexOf(".") >= 0 && money.length() - 1 >= money.indexOf(".") + 2)
			money = money.substring(0, money.indexOf(".") + 3);
		else if (money.indexOf(".") >= 0 && money.length() - 1 < money.indexOf(".") + 2)
			money = money + "0";
		else
			money = money + ".00";

		len = money.length();
		for (; index <= len; index++) {
			switch (money.substring(len - index, len - index + 1).charAt(0)) {
				case '.':
					item.append("元");
					break;
				case '0':
					item.append("零");
					break;
				case '1':
					item.append("壹");
					break;
				case '2':
					item.append("贰");
					break;
				case '3':
					item.append("叁");
					break;
				case '4':
					item.append("肆");
					break;
				case '5':
					item.append("伍");
					break;
				case '6':
					item.append("陆");
					break;
				case '7':
					item.append("柒");
					break;
				case '8':
					item.append("捌");
					break;
				case '9':
					item.append("玖");
					break;
			}

			switch (index) {
				case 1:
					item.append("分");
					break;
				case 2:
					item.append("角");
					break;
				case 3:
					item.append("");
					break;
				case 4:
					item.append("");
					break;
				case 5:
					item.append("拾");
					break;
				case 6:
					item.append("佰");
					break;
				case 7:
					item.append("仟");
					break;
				case 8:
					item.append("万");
					break;
				case 9:
					item.append("拾");
					break;
				case 10:
					item.append("佰");
					break;
				case 11:
					item.append("仟");
					break;
				case 12:
					item.append("亿");
					break;
				case 13:
					item.append("拾");
					break;
				case 14:
					item.append("佰");
					break;
				case 15:
					item.append("仟");
					break;
				case 16:
					item.append("万");
					break;
			}
			result = item.toString() + result;
			item.delete(0, item.length());
		}
		result = result.replaceAll("零拾", "零");
		result = result.replaceAll("零佰", "零");
		result = result.replaceAll("零仟", "零");
		result = result.replaceAll("零零零", "零");
		result = result.replaceAll("零零", "零");
		result = result.replaceAll("零角零分", "整");
		result = result.replaceAll("零分", "整");
		result = result.replaceAll("零角", "零");
		result = result.replaceAll("零亿零万零元", "亿元");
		result = result.replaceAll("亿零万零元", "亿元");
		result = result.replaceAll("零亿零万", "亿");
		result = result.replaceAll("零万零元", "万元");
		result = result.replaceAll("万零元", "万元");
		result = result.replaceAll("零亿", "亿");
		result = result.replaceAll("零万", "万");
		result = result.replaceAll("零元", "元");
		result = result.replaceAll("零零", "零");
		if ("元".equals(result.substring(0, 1)))
			result = result.substring(1);
		if ("零".equals(result.substring(0, 1)))
			result = result.substring(1);
		if ("角".equals(result.substring(0, 1)))
			result = result.substring(1);
		if ("分".equals(result.substring(0, 1)))
			result = result.substring(1);
		if ("整".equals(result.substring(0, 1)))
			result = "零元整";
		return result;
	}
	
	public static String substring(String src, int length){
		src = StringUtils.trimToEmpty(src);
		Integer size = src.length();
		if(size > length){
			return src.substring(0, length)+"...";
		}
		return src;
	}
	
	/**
	 * 字符串前补零操作。
	 * @param src
	 * @param length
	 * @return
	 */
	public static String patchZeroToString(Long src, int length){
		StringBuffer result= new StringBuffer("");
		int index = String.valueOf(NumberUtils.replaceEmpty(src)).length();
		if(index<length){
			for(int i=(length-index);i>0;i--){
				result.append("0");	
			}
		}
		result.append(src);
		
		return result.toString();
	}
	
	public static String patchCharToString(Long src, int length, char fillChar){
		StringBuffer result= new StringBuffer("");
		int index = String.valueOf(NumberUtils.replaceEmpty(src)).length();
		if(index<length){
			for(int i=(length-index);i>0;i--){
				result.append(fillChar);	
			}
		}
		result.append(String.valueOf(NumberUtils.replaceEmpty(src)));
		
		return result.toString();
	}
}
