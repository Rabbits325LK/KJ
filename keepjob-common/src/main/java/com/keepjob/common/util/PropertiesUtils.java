package com.keepjob.common.util;

import java.util.ResourceBundle;

public class PropertiesUtils {

	public static String getProperties(String config, String key){
		try {
			ResourceBundle bundle = ResourceBundle.getBundle(config);
			return StringUtils.trim(bundle.getString(key));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
