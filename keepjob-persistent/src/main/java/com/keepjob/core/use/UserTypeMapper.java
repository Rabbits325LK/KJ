package com.keepjob.core.use;

import java.util.List;

import org.springframework.stereotype.Component;

import com.keepjob.common.mybatis.MyBatisCriteria;

@Component("userTypeMapper")
public interface UserTypeMapper {
	
    int countByExample(MyBatisCriteria example);

    int deleteByExample(MyBatisCriteria example);

    int deleteByPrimaryKey(String code);

    int insert(UserType record);

    List<UserType> selectByExample(MyBatisCriteria example);

    UserType selectByPrimaryKey(String code);

    int updateByPrimaryKey(UserType record);
}