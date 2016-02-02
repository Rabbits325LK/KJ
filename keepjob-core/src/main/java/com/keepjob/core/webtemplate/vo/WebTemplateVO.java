package com.keepjob.core.webtemplate.vo;

import com.keepjob.common.pagination.PaginationBasicVO;

public class WebTemplateVO extends PaginationBasicVO{
	 private String uniqueCode;
	 
	 private String explainTitle;
	 
	 private String introductionTitle;
	 
	 private String status;
	 
	 private String modelType;
	 
	 private String creater;

	 public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getCreaterCode() {
		return createrCode;
	}

	public void setCreaterCode(String createrCode) {
		this.createrCode = createrCode;
	}

	private String createrCode;

	public String getUniqueCode() {
		return uniqueCode;
	}

	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}

	public String getExplainTitle() {
		return explainTitle;
	}

	public void setExplainTitle(String explainTitle) {
		this.explainTitle = explainTitle;
	}

	public String getIntroductionTitle() {
		return introductionTitle;
	}

	public void setIntroductionTitle(String introductionTitle) {
		this.introductionTitle = introductionTitle;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}
}
