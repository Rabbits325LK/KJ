package com.keepjob.turnover;

import com.keepjob.turnover.Turnover;
import com.keepjob.common.mybatis.MyBatisCriteria;
import java.util.List;

import org.springframework.stereotype.Component;

@Component("TurnoverMapper")
public interface TurnoverMapper {
    int countByExample(MyBatisCriteria example);

    int deleteByExample(MyBatisCriteria example);

    int deleteByPrimaryKey(String code);

    int insert(Turnover record);

    List<Turnover> selectByExample(MyBatisCriteria example);

    Turnover selectByPrimaryKey(String code);

    int updateByPrimaryKey(Turnover record);
}