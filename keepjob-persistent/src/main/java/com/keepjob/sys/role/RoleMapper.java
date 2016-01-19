package com.keepjob.sys.role;

import java.util.List;

import org.springframework.stereotype.Component;

import com.keepjob.common.mybatis.MyBatisCriteria;

@Component("roleMapper")
public interface RoleMapper {
    int countByExample(MyBatisCriteria example);

    int deleteByExample(MyBatisCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    List<Role> selectByExample(MyBatisCriteria example);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Role record);
    
    public List<Role> findRolesByUserCode(String userCode);    
}