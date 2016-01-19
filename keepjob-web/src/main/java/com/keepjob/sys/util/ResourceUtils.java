package com.keepjob.sys.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class ResourceUtils {
	private static Map<String, String> resourceMap = new HashMap<String, String>();
	static {
		String key="";
		try{
			Resource resource = new ClassPathResource("/META-INF/config.properties");
			if(null != resource){
				Properties props = PropertiesLoaderUtils.loadProperties(resource);
				if(null != props){
					Iterator<Object> it = props.keySet().iterator();
					while(it.hasNext()){
						key = (String)it.next();
						resourceMap.put(key, (String)props.get(key));
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static String getProperties(String key){
		if(null != resourceMap && !resourceMap.isEmpty()){
			return resourceMap.get(key);
		}
		return "";
	}
}
