package com.keepjob.core.use.vo;

import com.keepjob.common.pagination.PaginationBasicVO;

import java.util.Date;

/**
 * Created by liangkang on 2016/10/8 0008.
 */
public class UseShipRecordVO extends PaginationBasicVO{

    private String employeeCode;

    private String status;

    private Date useDate;

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUseDate() {
        return useDate;
    }

    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }
}
