package com.keepjob.core.webtemplate;

import java.util.List;

import org.springframework.stereotype.Component;

import com.keepjob.common.mybatis.MyBatisCriteria;

@Component("webTemplateMapper")
public interface WebTemplateMapper {
    int countByExample(MyBatisCriteria example);

    int deleteByExample(MyBatisCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(WebTemplate record);

    List<WebTemplate> selectByExample(MyBatisCriteria example);

    WebTemplate selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(WebTemplate record);
}