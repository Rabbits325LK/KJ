package com.keepjob.common.enums;
/**
 * 医疗机构资质级别
 * @author wolf
 *
 */
public enum Grader {
	
	LEVEL0("0", "无资质级别"),LEVEL1("1", "一级医院"), LEVEL2("2", "二级医院"), LEVEL3("3", "三级医院");
	
	private String code;
	
	private String name;
	
	private Grader(String code, String name){
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
