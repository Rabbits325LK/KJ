package com.keepjob.sys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.keepjob.common.util.StringUtils;

@Component("config")
public class Config {

	@Value("${storage.mode}")
	private String storageMode;
	
	@Value("${file.root.dir}")
	private String fileRootDir;
	
	@Value("${temporary.file.root.dir}")
	private String temporaryFileRootDir;
	
	@Value("${report.file.root.dir}")
	private String reportFileRootDir;
	
	@Value("${case.file.root.dir}")
	private String caseFileRootDir;
	
	@Value("${hems.root.dir}")
	private String hemsRootDir;
	
	@Value("${hems.server.url}")
	private String hemsServerUrl;

	@Value("${picture.file.root.dir}")
	private String pictureFileRootDir;
	
	@Value("${picture.server.url}")
	private String pictureServerUrl;
	
	@Value("${rdcc.root.dir}")
	private String rdccRootDir;

	@Value("${rdcc.server.url}")
	private String rdccServerUrl;
	
	@Value("${ftp.host}")
	private String ftpHost;
	
	@Value("${ftp.port}")
	private String ftpPort;

	@Value("${ftp.username}")
	private String ftpUserName;
	
	@Value("${ftp.password}")
	private String ftpPassword;
	
	@Value("${soffice.timeout}")
	private String sofficeTimeout;

	@Value("${soffice.host}")
	private String sofficeHost;
	
	@Value("${soffice.port}")
	private String sofficePort;

	@Value("${swftool.local.path}")
	private String swfToolLocalPath;

	@Value("${swftool.language.dir}")
	private String swfToolLanguageDir;

	@Value("${swftool.timeout}")
	private String swfToolTimeout;

	public String getStorageMode() {
		return storageMode;
	}

	public void setStorageMode(String storageMode) {
		this.storageMode = storageMode;
	}

	public String getFileRootDir() {
		return fileRootDir;
	}

	public void setFileRootDir(String fileRootDir) {
		this.fileRootDir = fileRootDir;
	}

	public String getHemsRootDir() {
		return hemsRootDir;
	}

	public void setHemsRootDir(String hemsRootDir) {
		this.hemsRootDir = hemsRootDir;
	}

	public String getPictureServerUrl() {
		return pictureServerUrl;
	}

	public void setPictureServerUrl(String pictureServerUrl) {
		this.pictureServerUrl = pictureServerUrl;
	}

	public String getTemporaryFileRootDir() {
		return temporaryFileRootDir;
	}

	public void setTemporaryFileRootDir(String temporaryFileRootDir) {
		this.temporaryFileRootDir = temporaryFileRootDir;
	}

	public String getReportFileRootDir() {
		return reportFileRootDir;
	}

	public void setReportFileRootDir(String reportFileRootDir) {
		this.reportFileRootDir = reportFileRootDir;
	}

	public String getCaseFileRootDir() {
		return caseFileRootDir;
	}

	public void setCaseFileRootDir(String caseFileRootDir) {
		this.caseFileRootDir = caseFileRootDir;
	}

	public String getPictureFileRootDir() {
		return pictureFileRootDir;
	}

	public void setPictureFileRootDir(String pictureFileRootDir) {
		this.pictureFileRootDir = pictureFileRootDir;
	}

	public String getRdccRootDir() {
		return rdccRootDir;
	}

	public void setRdccRootDir(String rdccRootDir) {
		this.rdccRootDir = rdccRootDir;
	}

	public String getRdccServerUrl() {
		return rdccServerUrl;
	}

	public void setRdccServerUrl(String rdccServerUrl) {
		this.rdccServerUrl = rdccServerUrl;
	}

	public String getHemsServerUrl() {
		return hemsServerUrl;
	}

	public void setHemsServerUrl(String hemsServerUrl) {
		this.hemsServerUrl = hemsServerUrl;
	}

	public String getFtpHost() {
		return ftpHost;
	}

	public void setFtpHost(String ftpHost) {
		this.ftpHost = ftpHost;
	}

	public String getFtpPort() {
		return ftpPort;
	}

	public void setFtpPort(String ftpPort) {
		this.ftpPort = ftpPort;
	}

	public String getFtpUserName() {
		return ftpUserName;
	}

	public void setFtpUserName(String ftpUserName) {
		this.ftpUserName = ftpUserName;
	}

	public String getFtpPassword() {
		return ftpPassword;
	}

	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword = ftpPassword;
	}

	public String getSofficeTimeout() {
		return sofficeTimeout;
	}

	public void setSofficeTimeout(String sofficeTimeout) {
		this.sofficeTimeout = sofficeTimeout;
	}

	public String getSofficeHost() {
		return sofficeHost;
	}

	public void setSofficeHost(String sofficeHost) {
		this.sofficeHost = sofficeHost;
	}

	public String getSofficePort() {
		return sofficePort;
	}

	public void setSofficePort(String sofficePort) {
		this.sofficePort = sofficePort;
	}

	public String getSwfToolLocalPath() {
		return swfToolLocalPath;
	}

	public void setSwfToolLocalPath(String swfToolLocalPath) {
		this.swfToolLocalPath = swfToolLocalPath;
	}

	public String getSwfToolLanguageDir() {
		return swfToolLanguageDir;
	}

	public void setSwfToolLanguageDir(String swfToolLanguageDir) {
		this.swfToolLanguageDir = swfToolLanguageDir;
	}

	public String getSwfToolTimeout() {
		return swfToolTimeout;
	}

	public void setSwfToolTimeout(String swfToolTimeout) {
		this.swfToolTimeout = swfToolTimeout;
	}
	
	public boolean isDisk(){
		return StringUtils.trimToEmpty(this.storageMode).equals("DISK");
	}
}
