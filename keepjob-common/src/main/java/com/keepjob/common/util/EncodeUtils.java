package com.keepjob.common.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.StringTokenizer;
import java.util.UUID;

/**
 * 字符集转换工具类
 * @version 1.0
 */
public class EncodeUtils {

	private EncodeUtils() {}
	/**
	 * 从中文转向其它码
	 * @param src
	 * @param fromCode
	 * @param toCode
	 * @return string
	 */
	public static String fromChinese(String src, String fromCode, String toCode) {
		if(StringUtils.isEmpty(src)){
			return StringUtils.EMPTY;
		}
		try {
			return new String(src.getBytes(fromCode), toCode);
		} catch (UnsupportedEncodingException ue) {
			System.out.println(ue.toString());
		}
		return src;
	}

	/**
	 * 转中文,按指定的调用参数来转码
	 * @param src
	 * @param fromCode
	 * @param toCode
	 * @return string
	 */
	public static String toChinese(String src, String fromCode, String toCode) {
		if(StringUtils.isEmpty(src)){
			return StringUtils.EMPTY;
		}
		try {
			return new String(src.getBytes(fromCode), toCode);
		} catch (UnsupportedEncodingException ue) {
			System.out.println(ue.toString());
		}
		return src;
	}

	/**
	 * base64解码
	 * @param src
	 * @return
	 */
	public static String fromBase64(String src) {
		if (StringUtils.isEmpty(src)){
			return StringUtils.EMPTY;
		}
			
		sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
		try {
			
			byte[] bytes = decoder.decodeBuffer(src);
			return new String(bytes);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return src;
	}
	
	public static String fromBase64(String src, String encode) {
		if (StringUtils.isEmpty(src)){
			return StringUtils.EMPTY;
		}
			
		sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
		try {
			
			byte[] bytes = decoder.decodeBuffer(src);
			return new String(bytes,encode);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return "";
	}

	/**
	 * base64编码
	 */
	public static String toBase64(String src) {
		if (StringUtils.isEmpty(src)){
			return StringUtils.EMPTY;
		}
		return (new sun.misc.BASE64Encoder()).encode(src.getBytes());
	}
	public static String toBase64(String src, String encode) {
		if (StringUtils.isEmpty(src)){
			return StringUtils.EMPTY;
		}
		try {
			return (new sun.misc.BASE64Encoder()).encode(src.getBytes(encode));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * Unicode 转码
	 */
	public static String fromUnicode(String src){
		if (StringUtils.isEmpty(src)){
			return StringUtils.EMPTY;
		}
		
		StringBuffer result = new StringBuffer("");
		
		StringTokenizer tokenizer = new StringTokenizer(src, "\\u");
		while(tokenizer.hasMoreTokens()){
			result.append((char)(Integer.parseInt(tokenizer.nextToken(), 16)));
		}
		
		return result.toString();
	}
	
	public static void main(String[] args){
//		System.out.println("UUID="+UUID.randomUUID().toString());
		//String message = new String("用户名不存在","utf-8");
		
		//System.out.println(EncodeUtils.toBase64(message));
		
		//System.out.println();
		//System.out.println(EncodeUtils.toBase64(message));
		//System.out.println(EncodeUtils.toBase64(message));
//		System.out.println(EncodeUtils.fromBase64("DQojIyMgRXJyb3IgcXVlcnlpbmcgZGF0YWJhc2UuICBDYXVzZTogamF2YS5zcWwuU1FMRXhjZXB0aW9uOiBPUkEtMDA5NDI6ILHtu/LK0828sru05tTaCg0KIyMjIFRoZSBlcnJvciBtYXkgZXhpc3QgaW4gY29tL2pzZC9nYXRld2F5L3N5c3RlbS9Vc2VyTWFwcGVyLnhtbA0KIyMjIFRoZSBlcnJvciBtYXkgaW52b2x2ZSBjb20uanNkLmdhdGV3YXkuc3lzdGVtLlVzZXJNYXBwZXIuc2VsZWN0QnlFeGFtcGxlLUlubGluZQ0KIyMjIFRoZSBlcnJvciBvY2N1cnJlZCB3aGlsZSBzZXR0aW5nIHBhcmFtZXRlcnMNCiMjIyBTUUw6IHNlbGVjdCAgICAgICAgICAgJ3RydWUnIGFzIFFVRVJZSUQsICAgICAgICAgICBDT0RFLCBMT0dJTl9OQU1FLCBQQVNTV09SRCwgT1JHQU5fQ09ERSwgUEFTU19MT0dJTl9OQU1FLCBQQVNTX1BBU1NXT1JELCBQQVNTX09SR0FOX0NPREUgICAgICAgICBmcm9tIFRCX1VTRVJfSU5GTyAgICAgICAgICAgICAgICAgICAgV0hFUkUgKCAgTE9HSU5fTkFNRSA9ID8gKQ0KIyMjIENhdXNlOiBqYXZhLnNxbC5TUUxFeGNlcHRpb246IE9SQS0wMDk0Mjogse278srTzbyyu7Tm1NoKCjsgYmFkIFNRTCBncmFtbWFyIFtdOyBuZXN0ZWQgZXhjZXB0aW9uIGlzIGphdmEuc3FsLlNRTEV4Y2VwdGlvbjogT1JBLTAwOTQyOiCx7bvyytPNvLK7tObU2go="));
		System.out.println("默认解码:"+EncodeUtils.fromBase64("xNq2+b/G"));
		System.out.println("GBK解码:"+EncodeUtils.fromBase64("xNq2+b/G","GBK"));
		System.out.println("UTF-8解码:"+EncodeUtils.fromBase64("xNq2+b/G","UTF-8"));		
		//System.out.println("ceshi="+(new sun.misc.BASE64Encoder()).encode("用户名不存在".getBytes()));
		
		
	}
}
