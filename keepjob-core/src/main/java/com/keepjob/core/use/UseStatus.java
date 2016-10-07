package com.keepjob.core.use;

public enum UseStatus {

	REGISTER("1", "登记"),IMPLEMENT("2", "执行"), COMPLETE("3", "完成"),CANCEL("0", "取消");

	private String code;
	private String name;

	private UseStatus(String code, String name){
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
