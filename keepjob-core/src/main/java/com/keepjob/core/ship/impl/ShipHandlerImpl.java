package com.keepjob.core.ship.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keepjob.core.ship.Ship;
import com.keepjob.core.ship.ShipDAO;
import com.keepjob.core.ship.ShipHandler;

@Service("shipHandler")
public class ShipHandlerImpl implements ShipHandler {

	@Autowired
	private ShipDAO shipDAO;
	
	@Override
	public List<Ship> finds() {
		// TODO Auto-generated method stub
		return this.shipDAO.finds();
	}

	@Override
	@Transactional
	public boolean inUse(String code) {
		// TODO Auto-generated method stub
		Ship record = new Ship();
		record.setCode(StringUtils.trimToEmpty(code));
		record.inUse();
		return this.shipDAO.update(record);
	}

	@Override
	@Transactional
	public boolean normal(String code) {
		// TODO Auto-generated method stub
		Ship record = new Ship();
		record.setCode(StringUtils.trimToEmpty(code));
		record.normal();
		return this.shipDAO.update(record);
	}

	@Override
	public List<Ship> findNormals() {
		// TODO Auto-generated method stub
		return this.shipDAO.availableFinds();
	}

}
