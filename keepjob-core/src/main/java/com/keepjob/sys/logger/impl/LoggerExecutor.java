package com.keepjob.sys.logger.impl;

import com.keepjob.common.spring.SpringBeanFactory;
import com.keepjob.sys.logger.Logger;
import com.keepjob.sys.logger.LoggerHandler;

/**
 * 日志记录执行器
 *
 */
public class LoggerExecutor implements Runnable {
	
	private Logger message;	

	@Override
	public void run() {
		LoggerHandler loggerHandler = (LoggerHandler) SpringBeanFactory.getBean("loggerHandler");
		loggerHandler.save(message);
	}
	
	public LoggerExecutor(Logger message){
		this.message = message;
	}
}
