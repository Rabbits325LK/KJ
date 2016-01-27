package com.keepjob.app.associator;

import java.util.List;

import com.keepjob.app.associator.vo.AssociatorVO;
import com.keepjob.common.pagination.PaginationResultSet;

public interface AssociatorDAO {

	public Associator getAssociator(Integer id);
	
	public Associator getAssociatorByUserName(String username);
	
	public Associator getAssociatorByUniqueCode(String uniqueCode);
	
	public PaginationResultSet<Associator> findAssociatorByPagination(AssociatorVO vo);
	
	public List<Associator> findAssociators(Associator param);
	
	public boolean saveAssociator(Associator record);
	
	public boolean updateAssociator(Associator record);
	
	public boolean deleteAssociators(List<Integer> ids);
	
	public boolean existsUserName(String username);
	
	public boolean existsIdCard(String idCard);
	
	public boolean exisisEmail(String email);
	
	public boolean exisisPhone(String phone);
}
