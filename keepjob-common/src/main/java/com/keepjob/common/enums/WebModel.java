package com.keepjob.common.enums;

public enum WebModel {

	TEMEPORARY("0","暂存"),RELEASE("1","发布"),PRIVATE("2","私有");
	
	private String code;
	private String name;
	
	private WebModel(String code, String name){
		this.code = code;
		this.name= name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
