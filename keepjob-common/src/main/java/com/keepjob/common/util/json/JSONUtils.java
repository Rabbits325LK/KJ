package com.keepjob.common.util.json;

import java.sql.Timestamp;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

public class JSONUtils {

	public static JsonConfig configDateJson(String[] excludes) {
		JsonConfig jsonConfig = new JsonConfig();
		setConfig(jsonConfig,excludes);
		jsonConfig.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor());
		return jsonConfig;
	}
	
	public static JsonConfig configDateTimeJson(String[] excludes) {
		JsonConfig jsonConfig = new JsonConfig();
		setConfig(jsonConfig,excludes);
		jsonConfig.registerJsonValueProcessor(Date.class,new DateTimeJsonValueProcessor());
		jsonConfig.registerJsonValueProcessor(Timestamp.class,new DateTimeJsonValueProcessor());
		return jsonConfig;
	}
	
	public static String jsonObjectToString(Object object,String [] excludes){
		JSONObject jsonObject = JSONObject.fromObject(object,configExcludes(excludes));
		return jsonObject.toString();
	}
	
	public static String jsonArrayToString(Object object,String [] excludes){
		JSONArray jsonObject = JSONArray.fromObject(object,configExcludes(excludes));
		return jsonObject.toString();
	}
	
	/**
	 * 
	 * @param excludes String []
	 * @return
	 */
	public static JsonConfig configExcludes(String [] excludes){
		JsonConfig jsonConfig=new JsonConfig();
		setConfig(jsonConfig,excludes);
		return jsonConfig;
	}
	
	private static void setConfig(JsonConfig jsonConfig,String[] excludes){
		jsonConfig.setExcludes(excludes);
		jsonConfig.setIgnoreDefaultExcludes(false);
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
	}
	
}
