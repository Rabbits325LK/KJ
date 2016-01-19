package com.keepjob.common.util;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 * 
 * @author wolf
 *
 */
public class ApplicationContextUtil{
	private static Logger logger=LoggerFactory.getLogger(ApplicationContextUtil.class);
	public ApplicationContextUtil(String os){}
	private ApplicationContextUtil(){
		
	}
	
	private static ApplicationContext context_web=null;
	
	private static ApplicationContextUtil instance=null;
	
	private static ServletContext servletContext = null;
	
	/**
	 * @return ApplicationContextUtil instance
	 */
	public static ApplicationContextUtil getInstance(){
		if(instance!=null){
			return instance;
		}else{
			return instance=new ApplicationContextUtil();
		}
	}
	
	public static ApplicationContextUtil getInstance(ServletContext servletContext){
		return init(servletContext);
	}
	/**
	 * 
	 * @return ApplicationContextUtil instance
	 */
	public static ApplicationContextUtil init(ServletContext iservletContext){
		servletContext = iservletContext;
		if(instance!=null){
			logger.debug(ApplicationContextUtil.class + "is Not NULL.");
			context_web=WebApplicationContextUtils.getWebApplicationContext(servletContext);
			return instance;
		}else{
			logger.info(ApplicationContextUtil.class.getName());
			context_web=WebApplicationContextUtils.getWebApplicationContext(servletContext);
			return instance=new ApplicationContextUtil();
		}
	}
	
	/**
	 * @param servletContext
	 * @param beanId
	 * @return
	 */
	public Object getBeanByIdForWeb(String beanId){
		if(context_web==null){
			context_web=WebApplicationContextUtils.getWebApplicationContext(servletContext);
		}
		return context_web.getBean(beanId); 
	}
	
	public ServletContext getServletContext(){
		return servletContext;
	}


}