package com.keepjob.core.use.impl;

import com.keepjob.common.exception.DatabaseException;
import com.keepjob.common.mybatis.MyBatisCriteria;
import com.keepjob.common.util.StringUtils;
import com.keepjob.core.use.UseShipRecord;
import com.keepjob.core.use.UseShipRecordDAO;
import com.keepjob.core.use.UseShipRecordMapper;
import com.keepjob.core.use.vo.UseShipRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

@Repository("useShipRecordDAO")
public class UseShipRecordDAOImpl implements UseShipRecordDAO {

	@Autowired
	private UseShipRecordMapper useShipRecordMapper;
	
	@Override
	public UseShipRecord get(String code) {
		// TODO Auto-generated method stub
		Assert.notNull(code, "唯一编码为空");
		try {
			return this.useShipRecordMapper.selectByPrimaryKey(code);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public List<UseShipRecord> finds(UseShipRecordVO vo) {
		// TODO Auto-generated method stub
		MyBatisCriteria example = new MyBatisCriteria();
		if(null != vo){
			MyBatisCriteria.Criteria criteria = example.createCriteria();
			if(StringUtils.isNotEmpty(vo.getEmployeeCode())){
				criteria.andEqualTo("employee_code", StringUtils.trimToEmpty(vo.getEmployeeCode()));
			}
			if(StringUtils.isNotEmpty(vo.getStatus())){
				criteria.andEqualTo("status", StringUtils.trimToEmpty(vo.getStatus()));
			}
		}
		try {
			return this.useShipRecordMapper.selectByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean insert(UseShipRecord record) {
		// TODO Auto-generated method stub
		try {
			this.useShipRecordMapper.insert(record);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean update(UseShipRecord record) {
		// TODO Auto-generated method stub
		try {
			this.useShipRecordMapper.updateByPrimaryKey(record);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

}
