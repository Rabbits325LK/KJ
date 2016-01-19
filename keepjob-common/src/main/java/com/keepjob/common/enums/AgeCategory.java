package com.keepjob.common.enums;

public enum AgeCategory {

	YEAR("1","年"),
	MONTH("2","月"),
	DAY("3","日");
	
	private String val=null;
	
	private String name=null;
	
	private AgeCategory(String val,String name){
		this.val = val;
		this.name = name;
	}
	
	public String getValue(){
		return val;
	}
	
	public String getName(){
		return name;
	}
	
	public static String getName(String val){
		if(null != val){
			for(AgeCategory item : AgeCategory.values()){
				if(val.trim().equals(item.getValue())){
					return item.getName();
				}
			}
		}
		return "";
	}
	
}
