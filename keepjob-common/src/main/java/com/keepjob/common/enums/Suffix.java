package com.keepjob.common.enums;

public enum Suffix {
	XML("xml"),	PNG("png"),	JPG("jpg"), TXT("txt"), SWF("swf"), PDF("pdf"), 
	DOC("doc"), DOCX("docx"), XLS("xls"), XLSX("xlsx"), PPT("ppt"), PPTX("pptx"), 
	HTML("html"), DATBAS("datbas"), DICOM("dcm"), ZIP("zip");
	
	private String suffix;
	
	Suffix(String suffix){
		this.suffix=suffix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
}
