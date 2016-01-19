package com.keepjob.common.util;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.lang.StringUtils;

public class FileUtils extends org.apache.commons.io.FileUtils{
	/**
	 * 判断文件或目录是否存在
	 * @param path
	 * @return
	 */
	public static boolean exists(String path){
		if(StringUtils.isEmpty(path)){
			return false;
		}
		File file = new File(path);
		if(file.exists()){
			return true;
		}
		return false;
	}
		
	public static boolean isDir(String path){
		if(StringUtils.isEmpty(path)){
			return false;
		}
		File file = new File(path);
		if(file.exists() && file.isDirectory()){
			return true;
		}
		return false;
	}
	
	public static String size(long size){
		double b= 0D;
		b = size / 1024;
		if(b<900){
			return NumberUtils.roundDouble(b, 2)+" kb";
		}else{
			return NumberUtils.roundDouble(b/1024, 2)+" kb";
		}
	}
	/**
	 * 判断是否为存在的文件
	 * @param path
	 * @return
	 */
	public static boolean isFile(String path){
		if(StringUtils.isEmpty(path)){
			return false;
		}
		File file = new File(path);
		if(file.exists() && file.isFile()){
			return true;
		}
		return false;
	}
	
	public static boolean createDir(String path){
		File file = new File(path);
		if(!file.exists()){
			try{
				file.mkdirs();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return true;
	}
	
	public static byte[] read(String path){
		byte[] result = {0};
		if(StringUtils.isEmpty(path)){
			return result;
		}
		try{
			File file = new File(path);
			if(!file.isFile() || !file.canRead()){
				return result;
			}
				
			FileInputStream stream = new FileInputStream(file);
			result = new byte[(int)file.length()];
			stream.read(result);
			stream.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
	
	public static void delete(String path){
		try{
			File file = new File(path);
			if(file.isDirectory()){
				FileUtils.deleteDirectory(file);
			}else{
				file.deleteOnExit();
			}
		}catch(Exception ex){
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}
}
