package com.keepjob.turnover;

import java.util.Date;

public class Turnover {
    private String code;

    private String shipCode;
    
    private String shipName;

    private Date startTime;

    private Date endTime;

    private Double money;

    private String payType;

    private Double prePay;

    private String turnoverCode;

    private String checker;

    private String remark;

    private Date fireworksDay;

    private Date createDate;

    private String creator;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return shipName;
    }

    public void setName(String shipName) {
        this.shipName = shipName == null ? null : shipName.trim();
    }
    
    public String getShipCode() {
        return shipCode;
    }

    public void setShipCode(String shipCode) {
        this.shipCode = shipCode == null ? null : shipCode.trim();
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

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public Double getPrePay() {
        return prePay;
    }

    public void setPrePay(Double prePay) {
        this.prePay = prePay;
    }

    public String getTurnoverCode() {
        return turnoverCode;
    }

    public void setTurnoverCode(String turnoverCode) {
        this.turnoverCode = turnoverCode == null ? null : turnoverCode.trim();
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker == null ? null : checker.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getFireworksDay() {
        return fireworksDay;
    }

    public void setFireworksDay(Date fireworksDay) {
        this.fireworksDay = fireworksDay;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }
}