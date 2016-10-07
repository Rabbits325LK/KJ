package com.keepjob.core.employee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.keepjob.core.employee.Employee;
import com.keepjob.core.employee.EmployeeCategory;
import com.keepjob.core.employee.EmployeeCategoryDAO;
import com.keepjob.core.employee.EmployeeDAO;
import com.keepjob.core.employee.EmployeeHandler;

@Service("employeeHandler")
public class EmployeeHandlerImpl implements EmployeeHandler {

	@Autowired
	private EmployeeDAO employeeDAO;
	@Autowired
	private EmployeeCategoryDAO employeeCategoryDAO;
	
	@Override
	@Transactional
	public boolean bindWeChat(String wechatId, String wechatName) {
		// TODO Auto-generated method stub
		Assert.notNull(wechatId, "微信ID为空");
		Employee record = new Employee();
		record.bindWeChat(wechatId, wechatName);
		this.employeeDAO.insert(record);
		return true;
	}

	@Override
	public boolean existWeChat(String wechatId) {
		// TODO Auto-generated method stub
		return this.employeeDAO.exist(null, wechatId);
	}

	@Override
	public List<Employee> findsByCategory(String category) {
		// TODO Auto-generated method stub
		return this.employeeDAO.finds(category);
	}

	@Override
	public List<EmployeeCategory> findEmployeeCategorys() {
		// TODO Auto-generated method stub
		return this.employeeCategoryDAO.finds();
	}

}
