package com.keepjob.core.ship;

public enum ShipStatus {

	NORMAL("1", "正常"),IN_USE("2", "使用中"), REPAIR("0", "维修");

	private String code;
	private String name;

	private ShipStatus(String code, String name){
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
