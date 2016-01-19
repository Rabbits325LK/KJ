package com.keepjob.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class ReadUtils {
	
	private InputStream in=null;
	private char[] end=null;
	int endLength = 0;
	int endIndex = 0;
	
	
	char[] error="error".toCharArray();
	int errorLength = error.length;
	char[] fatal="fatal".toCharArray();
	int fatalLength = fatal.length;
	int errorIndex=0;
	int fatalIndex=0;
	
	private void init(){
		endIndex=0;
	}
	
	public ReadUtils(InputStream input,char[] endStr){
		this.in=input;
		end = endStr;
		if(end!=null)
		endLength = end.length;
	}
	
	public boolean readIncludeError(char[] endStr) throws IOException{
		end = endStr;
		endLength = end.length;
		return readIncludeError();
	}
	
	public boolean readIncludeError() throws IOException{
		init();
		boolean isFailed = false;
		int i=-1;
		while(true){
			i=in.read();
			if (i==0) {
				continue;
			}
			if (error[errorIndex]==i) {
				errorIndex++;
			}else{
				errorIndex=0;
			}
			
			if (errorIndex==errorLength) {
				isFailed=true;
			}
			System.out.print((char)i);
			if (fatal[fatalIndex]==i) {
				fatalIndex++;
			}else{
				fatalIndex=0;
			}
			if (fatalIndex==fatalLength) {
				fatalIndex=0;
				isFailed=true;
			}
			
			if (end[endIndex] == i) {
				endIndex++;
			}else{
				endIndex=0;
			}
			if(endIndex==endLength || i==-1){
				break;
			}
		}
		return isFailed;
	}
	
	public String read(char[] endStr) throws IOException{
		if (endStr!=null) {
			end =endStr;
			endLength = end.length;
		}
		return read();
	}
	public String read() throws IOException{
		init();
		StringBuffer sub = new StringBuffer();
		int i=-1;
		while(true){
			i=in.read();
			if (i==0) {
				continue;
			}
			sub.append((char)i);
			if (end[endIndex] == (char)i) {
				endIndex++;
			}else{
				endIndex=0;
			}
			if(endIndex==endLength || i==-1){
				break;
			}
		}
		try {
			return new String(sub.toString().getBytes("iso-8859-1"),"GBK");
		} catch (UnsupportedEncodingException e) {
			return sub.toString();
		}
		
	}
	
}
