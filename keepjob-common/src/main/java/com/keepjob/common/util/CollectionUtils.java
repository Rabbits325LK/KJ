package com.keepjob.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionUtils extends org.apache.commons.collections.CollectionUtils{
	
	private CollectionUtils(){	}
	
	@SuppressWarnings("rawtypes")
	public static String collectionToString(Collection list){
		StringBuffer result = new StringBuffer("");
		for(Object item : list){
			if(null == result || result.toString().equals("")){
				result.append("{");
				result.append(item.toString());
			}else{
				result.append(", ").append(item.toString());
			}
		}
		result.append("}");
		return result.toString();
	}
	
	@SuppressWarnings("rawtypes")
	public static String listToString(List list){
		StringBuffer result = new StringBuffer("");
		for(Object item : list){
			if(null == result || result.toString().equals("")){
				result.append("{");
				result.append(item.toString());
			}else{
				result.append(", ").append(item.toString());
			}
		}
		result.append("}");
		return result.toString();
	}
	
	
	public static String toString(List<String> list){
		StringBuffer result = new StringBuffer("[");
		for(String item : list){
			if(result.equals("[")){
				result.append(item);
			}else{
				result.append(", ").append(item);
			}
		}
		result.append("]");
		return result.toString();
	}


	public static String numberToString(List<Number> list){
		List<String> items = new ArrayList<String>();
		for(Number item : list){
			items.add(item.toString());
		}
		return toString(items);
	}
	
	public static String longToString(List<Long> list){
		List<String> items = new ArrayList<String>();
		for(Long item : list){
			items.add(item.toString());
		}
		return toString(items);
	}
	
	public static String integerToString(List<Integer> list){
		List<String> items = new ArrayList<String>();
		for(Integer item : list){
			items.add(item.toString());
		}
		return toString(items);
	}
}
