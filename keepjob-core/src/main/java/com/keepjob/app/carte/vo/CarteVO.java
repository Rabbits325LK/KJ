package com.keepjob.app.carte.vo;

import com.keepjob.common.pagination.PaginationBasicVO;

public class CarteVO extends PaginationBasicVO{
	
	public String carteName;
	
	public String carteType;
	
	public String creater;
	
	public String createrCode;
	
	public String uniqueCode;
	
	public String status;
	
	public String getStatus(){
		return status;
	}

	public void setStatus(String status){
		this.status = status;
	}
	
	public String getCarteName() {
		return carteName;
	}

	public void setCarteName(String carteName) {
		this.carteName = carteName;
	}

	public String getCarteType() {
		return carteType;
	}

	public void setCarteType(String carteType) {
		this.carteType = carteType;
	}

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

	public String getUniqueCode() {
		return uniqueCode;
	}

	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}
	
	
}
