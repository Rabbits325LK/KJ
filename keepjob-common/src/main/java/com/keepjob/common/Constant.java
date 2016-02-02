package com.keepjob.common;

import com.keepjob.common.util.DateUtils;

public final class Constant {
	public static final String PROPERTY_FILE_NAME="META-INF/spring";
	public static final String SPRING_CONFIG_FILE="applicationContext-Core.xml";
	public static final String APPLICATION_JSON="application/json; charset=UTF-8";
	public static final String APPLICATION_JSON_NOCHARSET="application/json";
	public static final String TEXT_HTML="text/html; charset=UTF-8";
	public static final String TEXT_HTML_NOCHARSET="text/html";
	public static final String TEXT_PLAIN="text/plain; charset=UTF-8";
	public static final String TEXT_PLAIN_NOCHARSET="text/plain";
	public static final String DEFAULT_CHARSET="UTF-8";
	
	public static final String USER_KEY="currentuser";
	
	public static final String MEMBERS_KEY="currentmembers";
	
	public static final String BR="\n";
	
	public static final String GBK ="GBK";
	
	public static String CURRENT_YEAR_START_DATE = String.valueOf(DateUtils.getNowYear())+"-01-01";
	public static String CURRENT_YEAR_END_DATE = String.valueOf(DateUtils.getNowYear())+"-12-31";
	
	public static String CURRENT_YEAR_START_DATETIME = String.valueOf(DateUtils.getNowYear())+"-01-01 00:00:00";
	public static String CURRENT_YEAR_END_DATETIME = String.valueOf(DateUtils.getNowYear())+"-12-31 23:59:59";
		
	public static final Integer DEFAULT_TIMES=999;
	public static final Integer DEFAULT_DISK_SPACE = 300;
	
	public static final String DEFAULT_CARD_TYPE = "0";
	
	//public static final String FTP_FILE_ROOT_DIR="ftp_file_root_dir";
	
	//public static final String TEMPORARY_FILE_ROOT_DIR="temporary_file_root_dir";
	
	//public static final String REPORT_FILE_ROOT_DIR="report_file_root_dir";
	
	//public static final String CASE_FILE_ROOT_DIR="case_file_root_dir";
	
	//public static final String PICTURE_FILE_ROOT_DIR="picture_file_root_dir";
	
	//public static final String IMAGE_SERVER_URL="image_server_url";
	
	public final static String SEPARATOR="/";
	
	public final static String POINT=".";
	
	public final static Integer DEFAULT_PAGE_ROWS = 20;	
	
	public final static String SUCESSED = "1";
	
	public final static String FAILED = "0";
}
