package com.keepjob.sys.role;

import java.util.List;

import org.springframework.stereotype.Component;

import com.keepjob.common.mybatis.MyBatisCriteria;

@Component("roleResourceMapper")
public interface RoleResourceMapper {
    int countByExample(MyBatisCriteria example);

    int deleteByExample(MyBatisCriteria example);

    int insert(RoleResource record);

    List<RoleResource> selectByExample(MyBatisCriteria example);

    int updateByPrimaryKey(RoleResource record);
    
    public List<RoleResource> findRoleResources(String url);

}