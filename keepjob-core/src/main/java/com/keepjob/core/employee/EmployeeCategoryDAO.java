package com.keepjob.core.employee;

import java.util.List;

public interface EmployeeCategoryDAO {

	EmployeeCategory get(String code);
	
	List<EmployeeCategory> finds();
	
	boolean insert(EmployeeCategory record);
	
	boolean update(EmployeeCategory record);
	
	boolean deletes(List<String> codes);
}
