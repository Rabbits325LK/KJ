package com.keepjob.sys.logger;

public enum ActionType {
	INSERT("01", "新增"), 
	UPDATE("02", "修改"), 
	DELETE("03","删除"), 
	START("04","启用"), 
	STOP("05","停用"),
	OTHER("99","其他");
	
	private String code;
	private String name;
	
	private ActionType(String code, String name){
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
