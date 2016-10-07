package com.keepjob.core.employee;

import com.keepjob.common.mybatis.MyBatisCriteria;
import com.keepjob.core.employee.EmployeeCategory;
import java.util.List;

import org.springframework.stereotype.Component;

@Component("employeeCategoryMapper")
public interface EmployeeCategoryMapper {
    int countByExample(MyBatisCriteria example);

    int deleteByExample(MyBatisCriteria example);

    int deleteByPrimaryKey(String code);

    int insert(EmployeeCategory record);

    List<EmployeeCategory> selectByExample(MyBatisCriteria example);

    EmployeeCategory selectByPrimaryKey(String code);

    int updateByPrimaryKey(EmployeeCategory record);
}