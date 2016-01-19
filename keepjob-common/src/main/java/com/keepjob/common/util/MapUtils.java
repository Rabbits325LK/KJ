package com.keepjob.common.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

public abstract class MapUtils {
	protected final static Log logger = LogFactory.getLog(MapUtils.class);

	public static int getInt(Map<String, Object> map, String p) {
		Object o = map.get(p);
		if (o != null) {
			return Integer.parseInt(o.toString());
		}
		throw new RuntimeException("参数转换为Integer异常！");
	}


	@SuppressWarnings("rawtypes")
	public static Map<String, Object> toMap(Object object){
		Map<String, Object> result = new HashMap<String, Object>(); 
		if (null == object) {
			return result;
	    }
		try{
			Class clazz = object.getClass();
			Field[] fields = clazz.getDeclaredFields();
			if(null != fields && fields.length > 0){
				for(Field field : fields){
					field.setAccessible(true);
					result.put(field.getName(), field.get(object));
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return result;
	}
	
	/** 
	 * @param map
	 * @param key
	 * @return
	 */
	public static String getString(Map<String, Object> map, String key) {
		if (CollectionUtils.isEmpty(map)) {
			return "";
		}
		Object o = map.get(key);
		if (o == null) {
			return "";
		} else {
			return o.toString();
		}
	}

	public static List<?> getList(Map<String, Object> map, String key) {
		Object o = map.get(key);
		if (o instanceof List<?>) {
			return (List<?>) o;
		}
		return null;
	}

	public static Map<?, ?> getMap(Map<?, ?> map, String key) {
		Object o = map.get(key);
		if (o instanceof Map<?, ?>) {
			return (Map<?, ?>) o;
		}
		return null;
	}

	/**
	 * 
	 * @param map
	 *            {key:value,key2:value,...}
	 * @return "key,key2,..."
	 */
	public static String getKeys(Map<String, Object> map) {
		Assert.notNull(map, "map must not be null");
		Iterator<String> keys = map.keySet().iterator();
		StringBuilder sb = new StringBuilder();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			sb.append(key).append(",");
		}
		if (sb.length() > 0) {
			return sb.substring(0, sb.length() - 1);
		}
		return null;
	}	

	public static Map<String, String> trimValues(Map<String, String> map) {
		if (map == null) {
			return null;
		}
		Set<String> keys = map.keySet();
		for (String key : keys) {
			String value = map.get(key);
			if (value != null) {
				map.put(key, value.trim());
			}
		}
		return map;
	}

}
