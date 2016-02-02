package com.keepjob.core.members;

import java.util.Date;

public class MembersTemplateNice {
    private Integer id;

    private String membersCode;

    private String webTemplateCode;

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

    public String getWebTemplateCode() {
        return webTemplateCode;
    }

    public void setWebTemplateCode(String webTemplateCode) {
        this.webTemplateCode = webTemplateCode == null ? null : webTemplateCode.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}