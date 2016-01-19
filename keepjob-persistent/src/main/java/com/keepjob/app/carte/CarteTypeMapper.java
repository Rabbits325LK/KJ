package com.keepjob.app.carte;

import java.util.List;

import org.springframework.stereotype.Component;

import com.keepjob.common.mybatis.MyBatisCriteria;

@Component("carteTypeMapper")
public interface CarteTypeMapper {
    int countByExample(MyBatisCriteria example);

    int deleteByExample(MyBatisCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(CarteType record);

    int insertSelective(CarteType record);

    List<CarteType> selectByExample(MyBatisCriteria example);

    CarteType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarteType record);

    int updateByPrimaryKey(CarteType record);
}