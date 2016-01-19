package com.keepjob.sys.logger;

import java.util.List;

import org.springframework.stereotype.Component;

import com.keepjob.common.mybatis.MyBatisCriteria;

@Component("loggerMapper")
public interface LoggerMapper {
    int countByExample(MyBatisCriteria example);

    int deleteByExample(MyBatisCriteria example);

    int insert(Logger record);

    List<Logger> selectByExample(MyBatisCriteria example);
}