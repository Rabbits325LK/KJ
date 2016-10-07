package com.keepjob.core.employee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.keepjob.common.exception.DatabaseException;
import com.keepjob.common.mybatis.MyBatisCriteria;
import com.keepjob.core.employee.EmployeeCategory;
import com.keepjob.core.employee.EmployeeCategoryDAO;
import com.keepjob.core.employee.EmployeeCategoryMapper;

@Repository("employeeCategoryDAO")
public class EmployeeCategoryDAOImpl implements EmployeeCategoryDAO{

	@Autowired
	private EmployeeCategoryMapper employeeCategoryMapper;
	
	@Override
	public EmployeeCategory get(String code) {
		// TODO Auto-generated method stub
		Assert.notNull(code, "唯一编码为空");
		try {
			return this.employeeCategoryMapper.selectByPrimaryKey(code);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public List<EmployeeCategory> finds() {
		// TODO Auto-generated method stub
		MyBatisCriteria example = new MyBatisCriteria();
		try {
			return this.employeeCategoryMapper.selectByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean insert(EmployeeCategory record) {
		// TODO Auto-generated method stub
		try {
			this.employeeCategoryMapper.insert(record);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean update(EmployeeCategory record) {
		// TODO Auto-generated method stub
		try {
			this.employeeCategoryMapper.updateByPrimaryKey(record);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean deletes(List<String> codes) {
		// TODO Auto-generated method stub
		Assert.notEmpty(codes, "唯一编码为空");
		MyBatisCriteria example = new MyBatisCriteria();
		example.createCriteria().andInString("code", codes);
		try {
			this.employeeCategoryMapper.deleteByExample(example);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

}
