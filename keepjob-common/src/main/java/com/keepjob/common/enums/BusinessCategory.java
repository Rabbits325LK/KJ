package com.keepjob.common.enums;

public enum BusinessCategory {
	CONSULTATION("01", "疑难病例会诊"),
	ECG_DIAGNOSE("02", "远程心电诊断"),
	MEDICAL_IMAGE_DIAGNOSE("03","远程影像诊断"),
	ULTRASONIC_DIAGNOSE("04", "远程超声诊断"),
	PATHOLOGY_DIAGNOSE("05", "远程病理诊断"),
	PHARMACY_DIAGNOSE("06", "远程药学会诊");
	
	private String value;
	private String name;
	private BusinessCategory(String value, String name){
		this.value=value;
		this.name=name;
	}
	
	public String getValue(){
		return value;
	}
	
	public String getName(){
		return name;
	}
}
