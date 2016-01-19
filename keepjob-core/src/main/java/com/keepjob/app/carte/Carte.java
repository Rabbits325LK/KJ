package com.keepjob.app.carte;

import java.util.Date;

public class Carte {
    private Integer id;

    private String uniqueCode;

    private String carteName;

    private String carteLogo;

    private Date createDate;

    private Date updateDate;

    private String version;

    private String carteType;

    private String status;

    private String creater;

    private String createrCode;

    private String carteSummary;

    private String carteContent;

    private String carteTypeName;
    
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

    public String getCarteName() {
        return carteName;
    }

    public void setCarteName(String carteName) {
        this.carteName = carteName == null ? null : carteName.trim();
    }

    public String getCarteLogo() {
        return carteLogo;
    }

    public void setCarteLogo(String carteLogo) {
        this.carteLogo = carteLogo == null ? null : carteLogo.trim();
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getCarteType() {
        return carteType;
    }

    public void setCarteType(String carteType) {
        this.carteType = carteType == null ? null : carteType.trim();
    }

    public String getCarteTypeName() {
        return carteTypeName;
    }

    public void setCarteTypeName(String carteTypeName) {
        this.carteTypeName = carteTypeName == null ? null : carteTypeName.trim();
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public String getCreaterCode() {
        return createrCode;
    }

    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode == null ? null : createrCode.trim();
    }

    public String getCarteSummary() {
        return carteSummary;
    }

    public void setCarteSummary(String carteSummary) {
        this.carteSummary = carteSummary == null ? null : carteSummary.trim();
    }

    public String getCarteContent() {
        return carteContent;
    }

    public void setCarteContent(String carteContent) {
        this.carteContent = carteContent == null ? null : carteContent.trim();
    }
}