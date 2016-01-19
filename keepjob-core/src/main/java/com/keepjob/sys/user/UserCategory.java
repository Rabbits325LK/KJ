package com.keepjob.sys.user;

public enum UserCategory {
	
	BASIC_HOSPITAL_USER("01", "基层医院用户"), MAVIN_USER("02", "专家用户"), CENTER_MANAGE_USER("03", "中心管理用户"), ADMIN_MANAGE_USER("04", "行政管理用户"), 
	INTERFACE_USER("05", "行政管理用户");
	
	private String code;
	private String name;
	
	private UserCategory(String code, String name){
		this.code = code;
		this.name = name;
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
