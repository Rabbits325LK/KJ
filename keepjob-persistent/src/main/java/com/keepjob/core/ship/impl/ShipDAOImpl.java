package com.keepjob.core.ship.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.keepjob.common.enums.Status;
import com.keepjob.common.exception.DatabaseException;
import com.keepjob.common.mybatis.MyBatisCriteria;
import com.keepjob.core.ship.Ship;
import com.keepjob.core.ship.ShipDAO;
import com.keepjob.core.ship.ShipMapper;

@Repository("shipDAO")
public class ShipDAOImpl implements ShipDAO{
	
	@Autowired
	private ShipMapper shipMapper;

	@Override
	public Ship get(String code) {
		// TODO Auto-generated method stub
		Assert.notNull(code, "唯一编码为空");
		try {
			return this.shipMapper.selectByPrimaryKey(code);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public List<Ship> finds() {
		// TODO Auto-generated method stub
		MyBatisCriteria example = new MyBatisCriteria();
		try {
			
			return this.shipMapper.selectByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public List<Ship> availableFinds() {
		// TODO Auto-generated method stub
		MyBatisCriteria example = new MyBatisCriteria();
		example.createCriteria().andEqualTo("status", Status.ENABLE.getCode());
		try {
			return this.shipMapper.selectByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean insert(Ship record) {
		// TODO Auto-generated method stub
		try {
			this.shipMapper.insert(record);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean update(Ship record) {
		// TODO Auto-generated method stub
		try {
			this.shipMapper.updateByPrimaryKey(record);
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
			this.shipMapper.deleteByExample(example);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

}
