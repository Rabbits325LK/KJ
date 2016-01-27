package com.keepjob.app.associator;

import java.util.List;

import org.springframework.stereotype.Component;

import com.keepjob.common.mybatis.MyBatisCriteria;

@Component("associatorMapper")
public interface AssociatorMapper {
    int countByExample(MyBatisCriteria example);

    int deleteByExample(MyBatisCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(Associator record);

    int insertSelective(Associator record);

    List<Associator> selectByExample(MyBatisCriteria example);

    Associator selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Associator record);

    int updateByPrimaryKey(Associator record);
}