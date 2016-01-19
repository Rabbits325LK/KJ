package com.keepjob.app.carte;

import java.util.List;

import org.springframework.stereotype.Component;

import com.keepjob.common.mybatis.MyBatisCriteria;

@Component("carteMapper")
public interface CarteMapper {
    int countByExample(MyBatisCriteria example);

    int deleteByExample(MyBatisCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(Carte record);

    int insertSelective(Carte record);

    List<Carte> selectByExampleWithBLOBs(MyBatisCriteria example);

    List<Carte> selectByExample(MyBatisCriteria example);

    Carte selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Carte record);

    int updateByPrimaryKeyWithBLOBs(Carte record);

    int updateByPrimaryKey(Carte record);
}