package com.keepjob.sys.resource.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.keepjob.common.mybatis.MyBatisCriteria;
import com.keepjob.sys.resource.Operate;
import com.keepjob.sys.resource.OperateDAO;
import com.keepjob.sys.resource.OperateMapper;

@Repository("operateDAO")
public class OperateDAOImpl implements OperateDAO {

	@Autowired
	private OperateMapper operateMapper;
	@Override
	public List<Operate> findOperatesAll() {
		MyBatisCriteria example = new MyBatisCriteria();
		return operateMapper.selectByExample(example);
	}

}
