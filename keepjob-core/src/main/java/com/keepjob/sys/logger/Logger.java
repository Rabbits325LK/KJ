package com.keepjob.sys.logger;

import java.util.Date;
import java.util.List;

import com.keepjob.common.util.DateUtils;

public class Logger {
    private String userCode;

    private Date operateDate;

    private String ip;

    private String realName;

    private String action;

    private String module;

    private String remark;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module == null ? null : module.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
    
    @SuppressWarnings("rawtypes")
   	public static Logger stopLogger(String userCode, String ip, String module, List objects){
       	return Logger.createLogger(userCode, ip, ActionType.STOP, module, objects);
    }
       
    public static Logger stopLogger(String userCode, String ip, String module, String remark){
       	return Logger.createLogger(userCode, ip, ActionType.STOP, module, remark);
    }
       
    @SuppressWarnings("rawtypes")
	public static Logger startLogger(String userCode, String ip, String module, List objects){
    	return Logger.createLogger(userCode, ip, ActionType.START, module, objects);
    }
    
    public static Logger startLogger(String userCode, String ip, String module, String remark){
    	return Logger.createLogger(userCode, ip, ActionType.START, module, remark);
    }
    
    @SuppressWarnings("rawtypes")
	public static Logger deleteLogger(String userCode, String ip, String module, List objects){
    	return Logger.createLogger(userCode, ip, ActionType.DELETE, module, objects);
    }
    
    public static Logger deleteLogger(String userCode, String ip, String module, String remark){
    	return Logger.createLogger(userCode, ip, ActionType.DELETE, module, remark);
    }
    
    @SuppressWarnings("rawtypes")
	public static Logger updateLogger(String userCode, String ip, String module, List objects){
    	return Logger.createLogger(userCode, ip, ActionType.UPDATE, module, objects);
    }
    
    public static Logger updateLogger(String userCode, String ip, String module, String remark){
    	return Logger.createLogger(userCode, ip, ActionType.UPDATE, module, remark);
    }
    
    @SuppressWarnings("rawtypes")
	public static Logger saveLogger(String userCode, String ip, String module, List objects){
    	return Logger.createLogger(userCode, ip, ActionType.INSERT, module, objects);
    }
    
    public static Logger saveLogger(String userCode, String ip, String module, String remark){
    	return Logger.createLogger(userCode, ip, ActionType.INSERT, module, remark);
    }
    
    public static Logger createLogger(String userCode, String ip, ActionType action, String module, String remark){
    	Logger result = new Logger();
    	result.setUserCode(userCode);
    	result.setAction(action.getCode());
    	result.setOperateDate(DateUtils.getCurrentDateTime());
    	result.setModule(module);
    	result.setRemark(remark);
    	result.setIp(ip);
    	return result;
    }
    
    
	@SuppressWarnings("rawtypes")
	public static Logger createLogger(String userCode, String ip, ActionType action, String module, List objects){
    	StringBuffer remark = new StringBuffer();
    	if(null != objects && !objects.isEmpty()){
    		for(Object object : objects){
    			if(object instanceof String){
    				remark.append("[").append(object).append("]");
    			}else{
    				remark.append(object);
    			}
    		}
    	}
    	return createLogger(userCode, ip, action, module, "信息:"+remark.toString());
    }
    
    public String toString(){
    	StringBuffer sb = new StringBuffer();
    	sb.append("操作用户:").append(this.userCode).append(",");
    	sb.append("操作动作:").append(this.action).append(",");
    	sb.append("目标:").append(this.module).append(",");
    	sb.append("描述:").append(this.remark);
    	return sb.toString();
    }
}