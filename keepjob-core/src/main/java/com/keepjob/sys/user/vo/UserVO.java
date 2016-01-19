package com.keepjob.sys.user.vo;

import com.keepjob.common.pagination.PaginationBasicVO;

public class UserVO extends PaginationBasicVO {
	//姓名或查询码
	private String name;
	//所属机构名
	private String organName;
	//用户类型
	private String userType;
	//状态
	private String status;
	//当当前用户为中心管理用户时， 该值为当前用户所在机构编码
	private String centerCode;
	//当前用户为行政管理用户时，该值为当前用户所在机构行政区划编码
	private String areaCode;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCenterCode() {
		return centerCode;
	}
	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
}
