package com.keepjob.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;


public class EncryptUtils {
	private EncryptUtils(){}
	
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"a", "b", "c", "d", "e", "f" };

	public static String encode(File file) {
		FileInputStream in = null;
		MessageDigest md5 = null;
		try {
			in = new FileInputStream(file);
			FileChannel ch = in.getChannel();
			MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
			md5 = MessageDigest.getInstance("MD5");
			md5.update(byteBuffer);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return toHex(md5.digest());
	}
	
	public static String encode(String text) {
		if (StringUtils.isEmpty(text)) {
			text = "";
		}
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update(text.getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toHex(md5.digest());
	}

	private static String toHex(byte[] bytes) {
		StringBuffer result = new StringBuffer(32);
		for (byte b : bytes) {
			result.append(hexDigits[(b & 0xf0) >> 4]);
			result.append(hexDigits[(b & 0x0f)]);
		}
		return result.toString();
	}
	
	public static void main(String[] args){
		System.out.println(EncryptUtils.encode("gateway_client"));
	}
}
