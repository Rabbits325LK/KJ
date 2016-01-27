package com.keepjob.core.webmodel;

import java.util.List;

import com.keepjob.common.pagination.PaginationResultSet;
import com.keepjob.core.members.Members;
import com.keepjob.core.webmodel.vo.WebModelAVO;

public interface WebModelAHandler {

	public WebModelA getWebModelA(Integer id);
	
	public PaginationResultSet<WebModelA> findWebModelAPagination(WebModelAVO vo);
	
	public boolean saveOrUpdateWebModelA(Members members, WebModelA record);
	
	public boolean deleteWebModelAs(Members members, List<Integer> ids);
}
