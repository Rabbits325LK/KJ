package com.keepjob.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProcessExecutor {
	
	public static boolean execute(String command){
		String line="";
		String result = "";
		try{
			ProcessBuilder pb = new ProcessBuilder(commandLineAsList(command));
			pb.redirectErrorStream(true);
			Process p = pb.start();
			BufferedReader is = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while((line = is.readLine()) != null){
				System.err.println("CMD="+line);	
				if(line.toLowerCase().startsWith("error")){
					result = result + "\t ERROR: " + line;
					p.waitFor();
					return false;
				}else if(line.toLowerCase().startsWith("fatal")){
					result = result + "\t FATAL: " + line;
					p.waitFor();
					return false;
				}
			}
			p.waitFor();
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new RuntimeException(ex.getMessage());
		}
	}
	
	public static List<String> commandLineAsList(String command){
		List<String> result = new ArrayList<String>();
		boolean insided = false;
		String item = "";
		char c =' ';
		
		for(int i = 0; i < command.length(); i++){
			c = command.charAt(i);

			if(!insided && (c == ' ' || c == '\t')){
				if (item.length() > 0) {
					result.add(item);
					item = "";
				}
				continue;
			}else if(c == '"') {
				insided = !insided;
			}

			item += c;
		}
		if (item.length() > 0) {
			result.add(item);
		}
		return result;
	}
	
//	public static void main(String args[]){
//		ProcessExecutor test = new ProcessExecutor();
//		test.execute("D:\\JavaTool\\SWFTools\\pdf2swf.exe D:\\flexpaper\\pdf\\H0001\\report\\U1\\2014\\06\\2201422220000061649-D14.pdf -o D:\\flexpaper\\swf\\H0001\\report\\U1\\2014\\06\\2201422220000061649-D14.swf -f -T 9 -t -s storeallcharacters -s linknameurl");
//	}
}
