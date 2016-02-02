package com.keepjob.core.members;

import java.util.List;

import org.springframework.stereotype.Component;

import com.keepjob.common.mybatis.MyBatisCriteria;

@Component("membersTemplateNiceMapper")
public interface MembersTemplateNiceMapper {
	int countByExample(MyBatisCriteria example);

	int deleteByExample(MyBatisCriteria example);

	int deleteByPrimaryKey(Integer id);

	int insert(MembersTemplateNice record);

	List<MembersTemplateNice> selectByExample(MyBatisCriteria example);

	MembersTemplateNice selectByPrimaryKey(Integer id);

	int updateByPrimaryKey(MembersTemplateNice record);
}