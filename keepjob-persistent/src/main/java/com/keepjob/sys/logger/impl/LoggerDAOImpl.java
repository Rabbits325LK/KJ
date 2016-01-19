package com.keepjob.sys.logger.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.keepjob.common.exception.DatabaseException;
import com.keepjob.sys.logger.Logger;
import com.keepjob.sys.logger.LoggerDAO;
import com.keepjob.sys.logger.LoggerMapper;

@Repository("loggerDAO")
public class LoggerDAOImpl implements LoggerDAO {
	@Autowired
	private LoggerMapper loggerMapper;
	
	@Override
	public boolean saveLogger(Logger record) {
		try{
			this.loggerMapper.insert(record);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
		return true;
	}

}
