package com.keepjob.common.pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页结果集
 * 
 * @author 
 * 
 */
public class PaginationResultSet<T> implements java.io.Serializable {

	private static final long serialVersionUID = -1899073000478725615L;
	private Long total = 0L;
	private List<T> rows = new ArrayList<T>();

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
