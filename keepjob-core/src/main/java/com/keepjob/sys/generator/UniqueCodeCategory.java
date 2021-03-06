package com.keepjob.sys.generator;

public enum UniqueCodeCategory {
	USER_CODE("U"), //用户
	ROLE_CODE("R"), //角色
	STORE_CODE("STORE"),//店铺编码
	CARTE_CODE("CARTE"),//菜谱编码
	APP_ASSOCIATOR_CODE("AAC"),//app会员
	KEEPJOB_MEMBERS_CODE("KMC"),//成员
	KEEPJOB_WEB_MODEL_CODE("KWMC");//模版
	private String value;
	
	private UniqueCodeCategory(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
