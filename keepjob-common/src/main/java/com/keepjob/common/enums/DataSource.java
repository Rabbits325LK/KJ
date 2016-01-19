package com.keepjob.common.enums;

public enum DataSource {
	
	RMHS("1", "系统录入"),IMPL("2", "接口传入");
	
	private String value;
	private String name;
	
	private DataSource(String value, String name){
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
