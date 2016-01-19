package com.keepjob.sys.user;

import java.util.Date;
import java.util.List;

import com.keepjob.common.util.DateUtils;
import com.keepjob.common.util.StringUtils;
import com.keepjob.sys.resource.Resource;

public class User  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 366630950809627430L;

	private Integer id;

    private String uniqueCode;

    private String loginCode;

    private String password;

    private String realName;

    private String sexCode;

    private Date birthday;

    private String userType;

    private String userUnit;
    //用户所属机构名称
    private String unitName;

    private String mavinCode;
    //专家名称
    private String mavinName;
    
    private String address;

    private String phone;

    private String email;

    private String isSuper;

    private String im;

    private String idCard;

    private String qq;

    private String wechatCode;

    private String msn;

    private String searchCode;

    private Date lastLoginDate;

    private String lastLoginIp;

    private String imageUrl;

    private String status;

    private String remark;

    private String creator;

    private Date createTime;

    private Date updateTime;

    private Integer version;
    
    private List<String> resourceUrls;// 用户可以访问的资源地址列表
    
    private List<Resource> resources;
    /**
     * 用户所属中心，在登录时加载，其余查询该值无效。
     */
    private String centerCode;

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

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode == null ? null : loginCode.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getSexCode() {
        return sexCode;
    }

    public void setSexCode(String sexCode) {
        this.sexCode = sexCode == null ? null : sexCode.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getUserUnit() {
        return userUnit;
    }

    public void setUserUnit(String userUnit) {
        this.userUnit = userUnit == null ? null : userUnit.trim();
    }

    public String getMavinCode() {
        return mavinCode;
    }

    public void setMavinCode(String mavinCode) {
        this.mavinCode = mavinCode == null ? null : mavinCode.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getIsSuper() {
        return isSuper;
    }

    public void setIsSuper(String isSuper) {
        this.isSuper = isSuper == null ? null : isSuper.trim();
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im == null ? null : im.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getWechatCode() {
        return wechatCode;
    }

    public void setWechatCode(String wechatCode) {
        this.wechatCode = wechatCode == null ? null : wechatCode.trim();
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn == null ? null : msn.trim();
    }

    public String getSearchCode() {
        return searchCode;
    }

    public void setSearchCode(String searchCode) {
        this.searchCode = searchCode == null ? null : searchCode.trim();
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
        //最后登录时间转换成String存入lastLoginDateView
        this.lastLoginDateView = DateUtils.date2string(lastLoginDate,"yyyy-MM-dd hh:mm:ss");
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

	public List<String> getResourceUrls() {
		return resourceUrls;
	}

	public void setResourceUrls(List<String> resourceUrls) {
		this.resourceUrls = resourceUrls;
	}
	
	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getMavinName() {
		return mavinName;
	}

	public void setMavinName(String mavinName) {
		this.mavinName = mavinName;
	}
	
	public String getCenterCode() {
		return centerCode;
	}

	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
	}

	//中心管理用户
	public boolean isCenterManager(){
		return StringUtils.trim(this.userType).equals(UserCategory.CENTER_MANAGE_USER.getCode());
	}
	//行政管理用户
	public boolean isAdminManager(){
		return StringUtils.trim(this.userType).equals(UserCategory.ADMIN_MANAGE_USER.getCode());
	}
	//专家用户
	public boolean isMavin(){
		return StringUtils.trim(this.userType).equals(UserCategory.MAVIN_USER.getCode());
	}
	//基层医院用户
	public boolean isHospitalUser(){
		return StringUtils.trim(this.userType).equals(UserCategory.BASIC_HOSPITAL_USER.getCode());
	}
	// 是否为超级用户
	public boolean isSuper(){
		return StringUtils.trim(isSuper).equals("1");
	}
	// 用户是否可用
	public boolean isEnable(){
		return StringUtils.trim(this.getStatus()).equals(UserStatus.ENABLE.getCode());
	}
	//时间转换为String格式
	private String lastLoginDateView;

	public String getLastLoginDateView() {
		return lastLoginDateView;
	}

	public void setLastLoginDateView(String lastLoginDateView) {
		this.lastLoginDateView = lastLoginDateView;
	}
}