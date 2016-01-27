package com.keepjob.core.webmodel;

import java.util.List;

import com.keepjob.common.mybatis.MyBatisCriteria;

public interface WebModelAMapper {
    int countByExample(MyBatisCriteria example);

    int deleteByExample(MyBatisCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(WebModelA record);

    int insertSelective(WebModelA record);

    //List<WebModelA> selectByExampleWithBLOBs(MyBatisCriteria example);

    List<WebModelA> selectByExample(MyBatisCriteria example);

    WebModelA selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WebModelA record);

    //int updateByPrimaryKeyWithBLOBs(WebModelA record);

    int updateByPrimaryKey(WebModelA record);
}