package com.keepjob.core.members;

import java.util.List;

import org.springframework.stereotype.Component;

import com.keepjob.common.mybatis.MyBatisCriteria;

@Component("membersModelNiceMapper")
public interface MembersModelNiceMapper {
    int countByExample(MyBatisCriteria example);

    int deleteByExample(MyBatisCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(MembersModelNice record);

    int insertSelective(MembersModelNice record);

    List<MembersModelNice> selectByExample(MyBatisCriteria example);

    MembersModelNice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MembersModelNice record);

    int updateByPrimaryKey(MembersModelNice record);
}