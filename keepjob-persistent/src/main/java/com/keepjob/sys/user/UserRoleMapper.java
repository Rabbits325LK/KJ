package com.keepjob.sys.user;

import java.util.List;

import org.springframework.stereotype.Component;

import com.keepjob.common.mybatis.MyBatisCriteria;

@Component("userRoleMapper")
public interface UserRoleMapper {
    int countByExample(MyBatisCriteria example);

    int deleteByExample(MyBatisCriteria example);

    int deleteByPrimaryKey(UserRole record);

    int insert(UserRole record);

    List<UserRole> selectByExample(MyBatisCriteria example);

    UserRole selectByPrimaryKey(UserRole record);

    int updateByPrimaryKey(UserRole record);
}