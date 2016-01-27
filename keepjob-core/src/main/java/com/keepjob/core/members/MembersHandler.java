package com.keepjob.core.members;

import java.util.List;

import com.keepjob.common.pagination.PaginationResultSet;
import com.keepjob.core.members.vo.MembersVO;
import com.keepjob.sys.user.User;

public interface MembersHandler {

	public Members getMembers(String queryType, String searchCode, Integer id);
	
	public boolean saveOrUpdate(User user, Members record);
	
	public boolean removeMembers(User user, List<Integer> ids);
	
	public boolean startMembers(User user, List<Integer> ids);
	
	public boolean lockMembers(User user, List<Integer> ids);
	
	public boolean existsPhone(String phone);
	
	public boolean existsEmail(String email);
	
	public PaginationResultSet<Members> findMembersByPagination(MembersVO vo);
	
	public boolean checkPassword(String queryType, String searchCode, String password);
}
