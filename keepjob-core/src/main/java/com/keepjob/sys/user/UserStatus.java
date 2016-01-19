package com.keepjob.sys.user;

public enum UserStatus {
	DISABLE("0", "停用"), ENABLE("1", "启用"), LOCKED("2","锁定"), DELETED("9","删除");
	
	private String code;
	private String name;
	
	private UserStatus(String code, String name){
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
