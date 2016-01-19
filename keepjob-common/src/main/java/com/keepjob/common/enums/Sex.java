package com.keepjob.common.enums;


public enum Sex {
	
	MALE("1","男性"),	FAMALE("2","女性"), OTHER("9","其他");
	
	private String value;
	private String name;
	
	private Sex(String value, String name){
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
		if(null != value){
			for(Sex item : Sex.values()){
				if(value.equals(item.getValue())){
					return item.getName();
				}
			}
		}
		return "";
	}

}
