package com.keepjob.common.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 * Spring容器外获取容器内对象
 * @author 
 *
 */
public class SpringBeanFactory implements ApplicationContextAware {
	private static ApplicationContext context;
	
	@SuppressWarnings("static-access")
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.context = applicationContext;
	}
	
	public static ApplicationContext getApplicationContext(){
		return context;
	}
	
	public static Object getBean(String name){
		return context.getBean(name);
	}
}
