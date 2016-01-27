package com.keepjob.core.members;

import java.util.List;

import com.keepjob.common.pagination.PaginationResultSet;
import com.keepjob.core.members.vo.MembersVO;

public interface MembersDAO {
	
	public Members getMembers(Integer id);
	
	public Members getMembersByPhone(String phone);
	
	public Members getMembersByEmail(String email);
	
	public Members getMembersByUniqueCode(String uniqueCode);
	
	public PaginationResultSet<Members> findMembersByPagination(MembersVO vo);
	
	public boolean saveMembers(Members record);
	
	public boolean updateMembers(Members record);
	
	public boolean deletesMembers(List<Integer> ids);
	
	public boolean existsPhone(String phone);
	
	public boolean existsEmail(String email);
}
