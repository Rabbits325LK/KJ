package com.keepjob.common.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import com.keepjob.common.exception.ApplicationException;

public class NIOFileUtils {
	/**
	 * 文件写入
	 * @param srcPath
	 * @param targetPath
	 */
	public void write(String srcPath, String targetPath){
		ByteBuffer buffer = null;
		int skip =-1;
		try{
			File srcFile = new File(srcPath);
			if(!srcFile.exists()){
				throw new ApplicationException("源文件未找到.");
			}
			
			FileInputStream fis = new FileInputStream(srcFile);
			FileChannel iChannel = fis.getChannel();
			
			buffer = ByteBuffer.allocate(1024);
			
			File targetFile = new File(targetPath);
			
			if(!targetFile.exists()){
				targetFile.createNewFile();
			}else{
				targetFile.delete();
				targetFile.createNewFile();
			}
			
			FileOutputStream fos = new FileOutputStream(targetFile, true);
			FileChannel oChannel = fos.getChannel();
			
			while(true){
				skip = iChannel.read(buffer);
				if(skip <= -1){
					break;
				}
				buffer.flip();
				oChannel.write(buffer);
				
				buffer.clear();
			}
			iChannel.close();
			oChannel.close();
			fis.close();
			fos.close();
			srcFile.delete();
		}catch(Exception ex){
			ex.printStackTrace();
			throw new ApplicationException(ex);
		}
	}
	
	public boolean validate(String path, Long size){
		try{
			File file = new File(path);
			if(!file.exists()){
				throw new ApplicationException("待验证文件不存在.");
			}
			System.out.println("filesize="+file.length()+"  "+"验证大小="+size);
			return (file.length() == size);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new ApplicationException(ex);
		}
	}
}
