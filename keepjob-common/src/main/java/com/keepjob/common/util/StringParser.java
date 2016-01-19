package com.keepjob.common.util;

import java.util.HashMap;
import java.util.Map;

public class StringParser {
	/**
	 * 
	 * @param content
	 * @return
	 */
	public static Map<String, String> toMap(String content, String itemSeparator, String keySeparator){
		Map<String, String> result = new HashMap<String, String>();
		String[] itemSplit=null;
		if(StringUtils.isNotBlank(content)){
			String split[] = content.split(itemSeparator);
			for(String item : split){
				if(StringUtils.isNotBlank(item)){
					itemSplit = item.split(keySeparator);
					if(itemSplit.length == 2){
						result.put(StringUtils.trimToEmpty(itemSplit[0]), StringUtils.trimToEmpty(itemSplit[1]));
					}
				}
			}
		}
		return result;
	}
}
