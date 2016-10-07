package com.keepjob.core.employee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.keepjob.common.exception.ApplicationException;
import com.keepjob.common.exception.DatabaseException;
import com.keepjob.common.mybatis.MyBatisCriteria;
import com.keepjob.common.mybatis.MyBatisCriteria.Criteria;
import com.keepjob.common.util.StringUtils;
import com.keepjob.core.employee.Employee;
import com.keepjob.core.employee.EmployeeDAO;
import com.keepjob.core.employee.EmployeeMapper;

@Repository("employeeDAO")
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Override
	public Employee get(String code) {
		// TODO Auto-generated method stub
		try {
			return this.employeeMapper.selectByPrimaryKey(StringUtils.trimToEmpty(code));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public Employee getByWechatId(String wechatId) {
		// TODO Auto-generated method stub
		Assert.notNull(wechatId, "微信ID为空");
		MyBatisCriteria example = new MyBatisCriteria();
		example.createCriteria().andEqualTo("wechat_id", wechatId);
		try {
			return this.employeeMapper.selectByExample(example).get(0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean exist(String code, String wechatId) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(code) && StringUtils.isEmpty(wechatId)){
			throw new ApplicationException("唯一编码，与微信ID都为空");
		}
		MyBatisCriteria example = new MyBatisCriteria();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotEmpty(code)){
			criteria.andEqualTo("code", StringUtils.trimToEmpty(code));
		}
		if(StringUtils.isNotEmpty(wechatId)){
			criteria.andEqualTo("wechatId", StringUtils.trimToEmpty(wechatId));
		}
		try {
			Integer rows = this.employeeMapper.countByExample(example);
			return (rows>0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public List<Employee> finds() {
		// TODO Auto-generated method stub
		try {
			return this.employeeMapper.selectByExample(null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public List<Employee> finds(String employeeCategory) {
		// TODO Auto-generated method stub
		Assert.notNull(employeeCategory, "员工类型为空");
		MyBatisCriteria example = new MyBatisCriteria();
		example.createCriteria().andEqualTo("category", employeeCategory);
		try {
			return this.employeeMapper.selectByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean insert(Employee record) {
		// TODO Auto-generated method stub
		try {
			this.employeeMapper.insert(record);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean update(Employee record) {
		// TODO Auto-generated method stub
		try {
			this.employeeMapper.updateByPrimaryKey(record);
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
			this.employeeMapper.deleteByExample(example);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

}
