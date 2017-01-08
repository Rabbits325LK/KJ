package com.keepjob.turnover.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.keepjob.common.exception.DatabaseException;
import com.keepjob.common.mybatis.MyBatisCriteria;
import com.keepjob.common.util.StringUtils;
import com.keepjob.turnover.Turnover;
import com.keepjob.turnover.TurnoverDAO;
import com.keepjob.turnover.TurnoverMapper;

public class TurnoverDAOImpl implements TurnoverDAO {

	@Autowired
	private TurnoverMapper turnoverMapper;
	
	@Override
	public Turnover getTurnover(String code) {
		// TODO Auto-generated method stub
		try {
			
			return this.turnoverMapper.selectByPrimaryKey(StringUtils.trimToEmpty(code));
		} catch (Exception e) {
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		
	}

	@Override
	public List<Turnover> findTurnover() {
		// TODO Auto-generated method stub
		MyBatisCriteria example = new MyBatisCriteria();
		try {
			return this.turnoverMapper.selectByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean insert(Turnover record) {
		// TODO Auto-generated method stub
		try {
			this.turnoverMapper.insert(record);
			return false;
		} catch(Exception e) {
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean update(Turnover record) {
		// TODO Auto-generated method stub
		try {
			this.turnoverMapper.updateByPrimaryKey(record);
			return false;
		} catch(Exception e) {
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

}
