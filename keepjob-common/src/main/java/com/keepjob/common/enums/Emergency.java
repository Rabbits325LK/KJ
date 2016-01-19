package com.keepjob.common.enums;

import com.keepjob.common.util.StringUtils;

public enum Emergency {
	EMERGENCY("1","急诊"),
	GENERAL("0","普诊");
	
	private String value=null;
	private String name=null;
	
	private Emergency(String value, String name){
		this.value=value;
		this.name=name;
	}
	
	public String getValue(){
		return value;
	}
	
	public String getName(){
		return name;
	}
	
	public static String getName(String value){
		if(StringUtils.isNotEmpty(value)){
			for(Emergency item : Emergency.values()){
				if(StringUtils.trimToEmpty(value).equals(item.getValue())){
					return item.getName();
				}
			}
		}
		return "";
	}
}
