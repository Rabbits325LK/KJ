package com.keepjob.core.webmodel;

import java.util.Date;

public class WebModelA {
    private Integer id;

    private String uniqueCode;

    private String headTitle;

    private String bodyLogo;

    private String bodyBackgroundColor;

    private String bodyFontColor;

    private String body2Code;

    private String status;

    private Date createDate;

    private Date updateDate;

    private String createrCode;

    private Integer browsingTimes;

    private Integer niceNum;
    
    private String headImgs;

    private String headContent;

    private String bodyContent;

    public String getHeadImgs() {
        return headImgs;
    }

    public void setHeadImgs(String headImgs) {
        this.headImgs = headImgs == null ? null : headImgs.trim();
    }

    public String getHeadContent() {
        return headContent;
    }

    public void setHeadContent(String headContent) {
        this.headContent = headContent == null ? null : headContent.trim();
    }

    public String getBodyContent() {
        return bodyContent;
    }

    public void setBodyContent(String bodyContent) {
        this.bodyContent = bodyContent == null ? null : bodyContent.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode == null ? null : uniqueCode.trim();
    }

    public String getHeadTitle() {
        return headTitle;
    }

    public void setHeadTitle(String headTitle) {
        this.headTitle = headTitle == null ? null : headTitle.trim();
    }

    public String getBodyLogo() {
        return bodyLogo;
    }

    public void setBodyLogo(String bodyLogo) {
        this.bodyLogo = bodyLogo == null ? null : bodyLogo.trim();
    }

    public String getBodyBackgroundColor() {
        return bodyBackgroundColor;
    }

    public void setBodyBackgroundColor(String bodyBackgroundColor) {
        this.bodyBackgroundColor = bodyBackgroundColor == null ? null : bodyBackgroundColor.trim();
    }

    public String getBodyFontColor() {
        return bodyFontColor;
    }

    public void setBodyFontColor(String bodyFontColor) {
        this.bodyFontColor = bodyFontColor == null ? null : bodyFontColor.trim();
    }

    public String getBody2Code() {
        return body2Code;
    }

    public void setBody2Code(String body2Code) {
        this.body2Code = body2Code == null ? null : body2Code.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreaterCode() {
        return createrCode;
    }

    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode == null ? null : createrCode.trim();
    }

    public Integer getBrowsingTimes() {
        return browsingTimes;
    }

    public void setBrowsingTimes(Integer browsingTimes) {
        this.browsingTimes = browsingTimes;
    }

    public Integer getNiceNum() {
        return niceNum;
    }

    public void setNiceNum(Integer niceNum) {
        this.niceNum = niceNum;
    }
}