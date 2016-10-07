package com.keepjob.core.employee;

import java.util.List;

public interface EmployeeDAO {

	Employee get(String code);
	
	Employee getByWechatId(String wechatId);
	
	boolean exist(String code, String wechatId);
	
	List<Employee> finds();
	
	List<Employee> finds(String employeeCategory);
	
	boolean insert(Employee record);
	
	boolean update(Employee record);
	
	boolean deletes(List<String> codes);
}
