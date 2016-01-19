package com.keepjob.sys.logger;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.keepjob.sys.logger.impl.LoggerExecutor;

public class LoggerDecorator {
	private org.slf4j.Logger logger =null;
	private static final ExecutorService threadPool=Executors.newCachedThreadPool();
	
	@SuppressWarnings("rawtypes")
	private LoggerDecorator(Class clazz){
		logger = org.slf4j.LoggerFactory.getLogger(clazz);
	}
	
	private LoggerDecorator(String className){
		logger = org.slf4j.LoggerFactory.getLogger(className);
	}
	
	@SuppressWarnings("rawtypes")
	public static LoggerDecorator getLogger(Class clazz){
		return new LoggerDecorator(clazz);
	}
	
	public static LoggerDecorator getLogger(String className){
		return new LoggerDecorator(className);
	}
	
	public void debug(String message) {
		logger.debug(message);
	}

	public void debug(Object object) {
		if(object instanceof Logger){
			this.execute((Logger)object);
		}
		logger.debug("{}", object);
	}

	public void debug(List<Object> objects) {
		for(Object object : objects){
			debug(object);
		}
	}

	public void error(String message) {
		logger.error(message);
	}

	public void error(Object object) {
		if(object instanceof Logger){
			this.execute((Logger)object);
		}
		logger.error("{}", object);
	}

	public void error(List<Object> objects) {
		for(Object object : objects){
			error(object);
		}
	}

	public String getName() {
		return logger.getName();
	}

	public void info(String message) {
		logger.info(message);
	}

	public void info(Object object) {
		if(object instanceof Logger){
			this.execute((Logger)object);
		}
		this.logger.info("{}", object);
	}

	public void info(List<Object> objects) {
		for(Object object : objects){
			info(object);
		}
	}

	public void execute(Logger object){
		threadPool.execute(new LoggerExecutor(object));
	}
}
