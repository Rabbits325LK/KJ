package com.keepjob.core.employee;

public enum EmployeeStatus {

	BOUND("1", "已绑定"),NOT_BOUND("2", "未绑定"), DISABLE("0", "停用");

	private String code;
	private String name;

	private EmployeeStatus(String code, String name){
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
