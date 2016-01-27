package com.keepjob.core.webmodel;

import java.util.List;

import com.keepjob.common.pagination.PaginationResultSet;
import com.keepjob.core.webmodel.vo.WebModelAVO;

public interface WebModelADAO {
	
	public WebModelA getWebModelA(Integer id);
	
	public PaginationResultSet<WebModelA> findWebModelAByPagination(WebModelAVO vo);
	
	public boolean saveWebModelA(WebModelA record);
	
	public boolean updateWebModelA(WebModelA record);
	
	public boolean deleteWebModelAs(List<Integer> ids);
}
