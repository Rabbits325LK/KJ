package com.keepjob.common.enums;

public enum ExamineMark {
	UNPASSED("0", "审核不通过"),PASSED("1", "审核通过");
	
	private String value;
	private String name;
	
	private ExamineMark(String value, String name){
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
