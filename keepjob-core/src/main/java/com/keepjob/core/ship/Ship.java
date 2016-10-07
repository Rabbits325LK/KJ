package com.keepjob.core.ship;

import java.util.Date;

import com.keepjob.common.util.DateUtils;

public class Ship {
    private String code;

    private String name;

    private String status;

    private String version;

    private Date createDate;

    private Date updateTime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    /**
     * 维修
     */
    public void repair(){
    		this.status = ShipStatus.REPAIR.getCode();
    		this.updateTime = DateUtils.getCurrentDateTime();
    }
    
    /**
     * 正常
     */
    public void normal(){
    		this.status = ShipStatus.NORMAL.getCode();
    		this.updateTime = DateUtils.getCurrentDateTime();
    }
    
    /**
     * 使用中
     */
    public void inUse(){
    		this.status = ShipStatus.NORMAL.getCode();
    		this.updateTime = DateUtils.getCurrentDateTime();
    }
}