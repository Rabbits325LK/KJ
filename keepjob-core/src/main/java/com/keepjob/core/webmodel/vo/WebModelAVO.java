package com.keepjob.core.webmodel.vo;

import com.keepjob.common.pagination.PaginationBasicVO;

public class WebModelAVO extends PaginationBasicVO{

	private String status;
	
	private String createrCode;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreaterCode() {
		return createrCode;
	}

	public void setCreaterCode(String createrCode) {
		this.createrCode = createrCode;
	} 
}
