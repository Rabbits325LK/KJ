package com.keepjob.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import com.keepjob.common.Constant;
import com.keepjob.common.exception.ApplicationException;

/**
 * FTP文件上传工具类
 */
public class FTPFileUtils {
	private FTPClient client;
	private String host;
	private Integer port;
	private String username;
	private String password;
	
	public FTPFileUtils() {
		client = new FTPClient();
	}
	
	public FTPFileUtils(FTPClient client){
		this.client = client;
	}
	
	public FTPFileUtils(String host, Integer port, String username, String password){
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
		
		this.client = this.newClient();
	}
	
	public void connect() {
		if(!client.isConnected()){
			try {
				client.connect(host, port);
				client.enterLocalPassiveMode();
				client.login(username, password);
				client.setFileType(FTP.BINARY_FILE_TYPE);
				System.out.println("FTP服务器[" + host + "]连接成功....");
			} catch (SocketException ex) {
				System.out.println("FTP服务器[" + host + "]连接失败....");
				ex.printStackTrace();
			} catch (IOException ex) {
				System.out.println("FTP服务器[" + host + "]连接失败....");
				ex.printStackTrace();
			}
		}
	}
	/**
	 * 创建新的ftp链接
	 * @return
	 */
	public FTPClient newClient(){
		FTPClient client = new FTPClient();
		try{
			client.connect(host, port);
			client.enterLocalPassiveMode();
			client.login(username, password);
			client.setFileType(FTP.BINARY_FILE_TYPE);
			System.out.println("FTP服务器[" + host + "]连接成功....");
		} catch (SocketException ex) {
			System.out.println("FTP服务器[" + host + "]连接失败....");
			ex.printStackTrace();
		} catch (IOException ex) {
			System.out.println("FTP服务器[" + host + "]连接失败....");
			ex.printStackTrace();
		}
		return client;
	}
	
	
	public void uploadFile(String dir, String fileName, InputStream is) throws IOException {
		if(null == client){
			throw new ApplicationException("FTP客户端创建失败.");
		}
		this.connect();
		String[] dirs = dir.split(Constant.SEPARATOR);
		client.changeWorkingDirectory("~");
			
		for(String d : dirs){
			if(client.changeWorkingDirectory(StringUtils.trimToEmpty(d))){
				continue;
			}
			client.makeDirectory(StringUtils.trimToEmpty(d));
			client.changeWorkingDirectory(StringUtils.trimToEmpty(d));
		}
			
		client.storeFile(fileName, is);
		client.logout();
		if(client.isConnected()){
			client.disconnect();
		}
	}
	 
	public boolean removeFile(String fileName) throws IOException {
		if(null == client){
			throw new ApplicationException("FTP客户端创建失败.");
		}
		this.connect();

		client.deleteFile(fileName);
		client.logout();
		if(client.isConnected()){
			client.disconnect();
		}
		
		return true;
	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public static void main(String[] args) throws IOException {
		FTPFileUtils ftputil = new FTPFileUtils();
		ftputil.host="192.168.1.66";
		ftputil.port=22;
		ftputil.username="root";
		ftputil.password="root";
		OutputStream out = ftputil.client.storeFileStream("rmhs/consultation/03/doc/test1.pdf");
		System.out.println(out==null);
		out.write("ss".getBytes());
		out.flush();
		ftputil.client.disconnect();
		
	}
	
}
