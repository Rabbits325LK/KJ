package com.keepjob.tysd.store.vo;

import com.keepjob.common.pagination.PaginationBasicVO;

public class StoreVO extends PaginationBasicVO {

	private String uniqueCode;

	private String name;

	private String status;

	private String type;

	public String getUniqueCode() {
		return uniqueCode;
	}

	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
