package com.keepjob.core.use;

import java.util.List;

import org.springframework.stereotype.Component;

import com.keepjob.common.mybatis.MyBatisCriteria;

@Component("useShipRecordMapper")
public interface UseShipRecordMapper {
    int countByExample(MyBatisCriteria example);

    int deleteByExample(MyBatisCriteria example);

    int deleteByPrimaryKey(String code);

    int insert(UseShipRecord record);

    List<UseShipRecord> selectByExample(MyBatisCriteria example);

    UseShipRecord selectByPrimaryKey(String code);

    int updateByPrimaryKey(UseShipRecord record);
}