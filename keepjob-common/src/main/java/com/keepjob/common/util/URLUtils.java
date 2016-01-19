package com.keepjob.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URLUtils {
	
	public static String decode(String s, String encoding){
		if(null == s){
			return "";
		}
		try {
			return URLDecoder.decode(s, encoding);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

}
