package com.keepjob.core.members;

import java.util.Date;

public class MembersModelNice {
    private Integer id;

    private String membersCode;

    private String webModelCode;

    private String webModelType;

    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMembersCode() {
        return membersCode;
    }

    public void setMembersCode(String membersCode) {
        this.membersCode = membersCode == null ? null : membersCode.trim();
    }

    public String getWebModelCode() {
        return webModelCode;
    }

    public void setWebModelCode(String webModelCode) {
        this.webModelCode = webModelCode == null ? null : webModelCode.trim();
    }

    public String getWebModelType() {
        return webModelType;
    }

    public void setWebModelType(String webModelType) {
        this.webModelType = webModelType == null ? null : webModelType.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}