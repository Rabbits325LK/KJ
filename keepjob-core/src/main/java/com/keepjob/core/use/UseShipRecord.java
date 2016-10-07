package com.keepjob.core.use;

import java.util.Date;

import com.keepjob.common.util.DateUtils;
import com.keepjob.common.util.StringUtils;
import com.keepjob.common.util.UUIDGenerator;
import com.keepjob.core.employee.Employee;

public class UseShipRecord {
    private String code;

    private String employeeCode;

    private String shipCode;

    private Date useDate;

    private Date startTime;

    private Date endTime;

    private String status;

    private String userType;

    private String userName;

    private Integer number;
    
    private String creator;

    public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode == null ? null : employeeCode.trim();
    }

    public String getShipCode() {
        return shipCode;
    }

    public void setShipCode(String shipCode) {
        this.shipCode = shipCode == null ? null : shipCode.trim();
    }

    public Date getUseDate() {
        return useDate;
    }

    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    
    /**
     * 创建记录
     * @param employee
     */
    public void create(Employee employee){
    		this.setCode(UUIDGenerator.generate());
    		this.setCreator(StringUtils.trimToEmpty(employee.getCode()));
    		this.setUseDate(DateUtils.getCurrentDateTime());
    		this.setStatus(UseStatus.REGISTER.getCode());
    }
    
    /**
     * 执行中
     * @param employee
     */
    public void implement(){
    		this.setStartTime(DateUtils.getCurrentDateTime());
    		this.setStatus(UseStatus.IMPLEMENT.getCode());
    }
    
    /**
     * 完成
     * @param employee
     */
    public void complete() {
    		this.setEndTime(DateUtils.getCurrentDateTime());
    		this.setStatus(UseStatus.COMPLETE.getCode());
    }
    
    /**
     * 取消
     */
    public void cancel(){
    		this.setStatus(UseStatus.CANCEL.getCode());
    }
}