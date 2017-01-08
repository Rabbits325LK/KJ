package com.keepjob.turnover.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.keepjob.common.util.StringUtils;
import com.keepjob.turnover.Turnover;
import com.keepjob.turnover.TurnoverDAO;
import com.keepjob.turnover.TurnoverHandler;

public class TurnoverHandlerImpl implements TurnoverHandler{

	@Autowired
	private TurnoverDAO turnoverDAO;

	@Override
	public Turnover getTurnover(String code) {
		// TODO Auto-generated method stub
		return turnoverDAO.getTurnover(code);
	}

	@Override
	public List<Turnover> findTurnovers() {
		// TODO Auto-generated method stub
		return turnoverDAO.findTurnover();
	}

	@Override
	public boolean saveOrUpdate(Turnover record) {
		if(StringUtils.isNotEmpty(record.getCode())){
			return turnoverDAO.insert(record);
		} else {
			return turnoverDAO.update(record);
		}
		
	}
}
