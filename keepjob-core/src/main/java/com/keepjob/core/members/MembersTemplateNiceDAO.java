package com.keepjob.core.members;

public interface MembersTemplateNiceDAO {

	public MembersTemplateNice getMembersTemplateNice(Integer id);
	
	public Integer niceNums(String membersCode, String webTemplateCode);
	
	public boolean saveMembersTemplateNice(MembersTemplateNice record);
	
	public boolean updateMembersTemplateNice(MembersTemplateNice record);
	
	public boolean deleteMembersTemplateNice(Integer id);
}
