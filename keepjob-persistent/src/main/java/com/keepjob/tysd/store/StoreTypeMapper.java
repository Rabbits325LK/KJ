package com.keepjob.tysd.store;

import java.util.List;

import org.springframework.stereotype.Component;

import com.keepjob.common.mybatis.MyBatisCriteria;

@Component("storeTypeMapper")
public interface StoreTypeMapper {
    int deleteByExample(MyBatisCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(StoreType record);

    int insertSelective(StoreType record);

    List<StoreType> selectByExample(MyBatisCriteria example);

    StoreType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StoreType record);

    int updateByPrimaryKey(StoreType record);
}