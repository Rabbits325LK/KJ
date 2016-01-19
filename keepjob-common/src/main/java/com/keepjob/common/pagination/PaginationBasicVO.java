package com.keepjob.common.pagination;

import com.keepjob.common.Constant;
import com.keepjob.common.util.NumberUtils;
import com.keepjob.common.util.StringUtils;

public class PaginationBasicVO {
	private final static String START_TIME = "00:00:00";
	private final static String END_TIME = "23:59:59";
	private String startDate;
	private String endDate;
	private Integer rows;
	private String sort;
	private String order;
	private Integer page = 1;
	
	public String getStartDate() {
		if(StringUtils.isNotBlank(startDate)){
			startDate = startDate+" "+START_TIME;
		}
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		if(StringUtils.isNotBlank(endDate)){
			endDate = endDate+" "+END_TIME;
		}
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public Integer getSkip() {
		return ((NumberUtils.replaceEmpty(page, 1) - 1) * NumberUtils.replaceEmpty(rows, Constant.DEFAULT_PAGE_ROWS));
	}
	
	public Integer getPage() {
		return page;
	}
	
	public void setPage(Integer page) {
		this.page = page;
	}
	
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
}
