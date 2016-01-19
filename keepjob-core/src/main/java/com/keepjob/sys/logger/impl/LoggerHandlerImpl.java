package com.keepjob.sys.logger.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keepjob.sys.logger.Logger;
import com.keepjob.sys.logger.LoggerDAO;
import com.keepjob.sys.logger.LoggerHandler;

@Service("loggerHandler")
public class LoggerHandlerImpl implements LoggerHandler {
	@Autowired
	private LoggerDAO loggerDAO;
	
	@Override
	@Transactional
	public boolean save(Logger record) {
		return this.loggerDAO.saveLogger(record);
	}

}
