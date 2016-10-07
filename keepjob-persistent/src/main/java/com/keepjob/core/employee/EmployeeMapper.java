package com.keepjob.core.employee;

import com.keepjob.common.mybatis.MyBatisCriteria;
import com.keepjob.core.employee.Employee;
import java.util.List;

import org.springframework.stereotype.Component;

@Component("employeeMapper")
public interface EmployeeMapper {
    int countByExample(MyBatisCriteria example);

    int deleteByExample(MyBatisCriteria example);

    int deleteByPrimaryKey(String code);

    int insert(Employee record);

    List<Employee> selectByExample(MyBatisCriteria example);

    Employee selectByPrimaryKey(String code);

    int updateByPrimaryKey(Employee record);
}