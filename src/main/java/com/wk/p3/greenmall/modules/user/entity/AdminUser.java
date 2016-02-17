package com.wk.p3.greenmall.modules.user.entity;

import com.wk.p3.greenmall.common.persistence.DataEntity;

public class AdminUser extends DataEntity<AdminUser>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private String statue;
	private String userName;
	private String mobile;
	private String organizationType;
	private String organizationName;
	private String mainGoods;
	private String province;
	private String city;
	private String area;
	private String detailArea;
	private String remark;
	private String userType;
	private String organizationId;
	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStatue() {
		return statue;
	}
	public void setStatue(String statue) {
		this.statue = statue;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOrganizationType() {
		return organizationType;
	}
	public void setOrganizationType(String organizationType) {
		this.organizationType = organizationType;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getMainGoods() {
		return mainGoods;
	}
	public void setMainGoods(String mainGoods) {
		this.mainGoods = mainGoods;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getDetailArea() {
		return detailArea;
	}
	public void setDetailArea(String detailArea) {
		this.detailArea = detailArea;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
