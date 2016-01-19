package com.keepjob.sys.resource;

import java.util.List;

import org.springframework.stereotype.Component;

import com.keepjob.common.mybatis.MyBatisCriteria;

@Component("operateMapper")
public interface OperateMapper {
    int countByExample(MyBatisCriteria example);

    int deleteByExample(MyBatisCriteria example);

    int deleteByPrimaryKey(String code);

    int insert(Operate record);

    List<Operate> selectByExample(MyBatisCriteria example);

    Operate selectByPrimaryKey(String code);

    int updateByPrimaryKey(Operate record);
}