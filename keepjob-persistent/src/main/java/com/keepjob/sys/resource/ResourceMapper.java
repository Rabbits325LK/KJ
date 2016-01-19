package com.keepjob.sys.resource;

import java.util.List;

import org.springframework.stereotype.Component;

import com.keepjob.common.mybatis.MyBatisCriteria;

@Component("resourceMapper")
public interface ResourceMapper {
    int countByExample(MyBatisCriteria example);

    int deleteByExample(MyBatisCriteria example);

    int deleteByPrimaryKey(String code);

    int insert(Resource record);

    List<Resource> selectByExample(MyBatisCriteria example);

    Resource selectByPrimaryKey(String code);

    int updateByPrimaryKey(Resource record);
    
    public List<Resource> findResourceByUserCode(String userCode);
    
    public List<Resource> findResourceByRoleCode(String roleCode);
   	
}