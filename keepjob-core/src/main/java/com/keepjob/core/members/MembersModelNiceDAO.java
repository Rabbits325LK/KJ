package com.keepjob.core.members;

public interface MembersModelNiceDAO {

	public boolean saveMembersModelNice(MembersModelNice record);
	
	public boolean updateMembersModelNice(MembersModelNice record);
	
	public boolean deleteMembersModelNiceByCode(String membersCode, String webModelCode);
	
	public Integer countMembersModelNiceByCode(String membersCode, String webModelCode);
	
	public boolean existsMembersModelNiceByCode(String membersCode, String webModelCode);
}
