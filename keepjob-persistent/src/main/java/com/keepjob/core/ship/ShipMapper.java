package com.keepjob.core.ship;

import java.util.List;

import org.springframework.stereotype.Component;

import com.keepjob.common.mybatis.MyBatisCriteria;

@Component("shipMapper")
public interface ShipMapper {
    int countByExample(MyBatisCriteria example);

    int deleteByExample(MyBatisCriteria example);

    int deleteByPrimaryKey(String code);

    int insert(Ship record);

    List<Ship> selectByExample(MyBatisCriteria example);

    Ship selectByPrimaryKey(String code);

    int updateByPrimaryKey(Ship record);
}