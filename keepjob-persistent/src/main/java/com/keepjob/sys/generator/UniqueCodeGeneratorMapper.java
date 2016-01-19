package com.keepjob.sys.generator;

import java.util.List;

import org.springframework.stereotype.Component;

import com.keepjob.common.mybatis.MyBatisCriteria;

@Component("uniqueCodeGeneratorMapper")
public interface UniqueCodeGeneratorMapper {
    int countByExample(MyBatisCriteria example);

    int deleteByExample(MyBatisCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(UniqueCodeGenerator record);

    List<UniqueCodeGenerator> selectByExample(MyBatisCriteria example);

    UniqueCodeGenerator selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(UniqueCodeGenerator record);
}