package com.keepjob.core.members;

import java.util.List;

import org.springframework.stereotype.Component;

import com.keepjob.common.mybatis.MyBatisCriteria;

@Component("membersMapper")
public interface MembersMapper {
    int countByExample(MyBatisCriteria example);

    int deleteByExample(MyBatisCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(Members record);

    int insertSelective(Members record);

    List<Members> selectByExample(MyBatisCriteria example);

    Members selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Members record);

    int updateByPrimaryKey(Members record);
}