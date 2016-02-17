package com.wk.p3.greenmall.modules.deal.entity;

import com.wk.p3.greenmall.modules.sys.entity.User;
import com.wk.p3.greenmall.modules.user.entity.Organization;

/**
 * 存放买家或者卖家的信息 
 * @author lf
 */
public class InfoVO {
	private User user;
	private Organization organization;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}
