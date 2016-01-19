package com.keepjob.common.util;

import java.util.ResourceBundle;

import com.keepjob.common.Constant;

public class ApplicationConfigPath {
	
	private static String [] config;
	

	
	private ApplicationConfigPath(){
		
	}
	
	private static ApplicationConfigPath applicationConfigPath=new ApplicationConfigPath();
	
	/**
	 * 
	 *
	 */
	private void init(){	
		
		try{
			ResourceBundle bundle=ResourceBundle.getBundle(Constant.PROPERTY_FILE_NAME);
			config=new String[1];
				config[0]=bundle.getString(Constant.SPRING_CONFIG_FILE);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public static ApplicationConfigPath getInstance(){
		if(applicationConfigPath!=null){
			return applicationConfigPath;
		}else{
			return new ApplicationConfigPath();
		}
	}
	public String [] getConfigFile(){
		init();
		return config;
	}
	
	public static void main(String args[]){
		ApplicationConfigPath config=ApplicationConfigPath.getInstance();
		System.out.println(config.getConfigFile().length);
		System.out.println(config.getConfigFile()[0]);			
	}
}