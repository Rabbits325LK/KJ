package com.keepjob.servlet;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.DispatcherServlet;

import com.keepjob.common.util.ApplicationContextUtil;

public class ApplicationInitializer implements WebApplicationInitializer {

	private static final Logger LOGGER=LoggerFactory.getLogger(ApplicationInitializer.class);
	private static final Long MAX_FILE_SIZE=2048000L;
	private static final Long MAX_UPLOAD_SIZE=2560000L;
	private static final int SIZE_THRESHOLD=8192;
	private static final String FILE_LOCATION="files";
	private static final String CONFIG_LOCATION="contextConfigLocation";
	
	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		// TODO Auto-generated method stub
		servletContext.setInitParameter(CONFIG_LOCATION, "classpath*:META-INF/spring/applicationContext-*.xml");
		ServletRegistration.Dynamic registration=servletContext.addServlet("springController", new DispatcherServlet());
		ContextLoaderListener contextLoaderListener=new ContextLoaderListener();
		RequestContextListener requestContextListener=new RequestContextListener();
		servletContext.addListener(contextLoaderListener);
		servletContext.addListener(requestContextListener);
		registration.setLoadOnStartup(1);
		registration.addMapping("/");
		registration.setAsyncSupported(true);
		registration.setInitParameter(CONFIG_LOCATION, "");
		registration.setMultipartConfig(new MultipartConfigElement(FILE_LOCATION,MAX_FILE_SIZE,MAX_UPLOAD_SIZE,SIZE_THRESHOLD));
		ApplicationContextUtil.init(servletContext);
		LOGGER.debug("Spring controller initialization completed.");
	}

}
