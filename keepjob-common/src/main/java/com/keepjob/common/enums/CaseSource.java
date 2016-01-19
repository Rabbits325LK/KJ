package com.keepjob.common.enums;

public enum CaseSource {
	PERSONAL("1", "个人"),HOSPITAL("2", "医院");
	
	private String value;
	private String name;
	
	private CaseSource(String value, String name){
		this.value=value;
		this.name=name;
	}
	
	public String getValue(){
		return this.value;
	}
	
	public String getName(){
		return this.name;
	}
}
