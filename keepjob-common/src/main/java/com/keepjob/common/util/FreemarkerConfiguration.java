package com.keepjob.common.util;

import freemarker.template.Configuration;

public class FreemarkerConfiguration {
	
	private static Configuration config = null;  
	static{
		config = new Configuration();
		config.setServletContextForTemplateLoading(ApplicationContextUtil.getInstance().getServletContext(), "WEB-INF/template/");
		config.setDefaultEncoding("UTF-8");
	}
	public static Configuration getConfiguation(){
		return config;
	}

}
