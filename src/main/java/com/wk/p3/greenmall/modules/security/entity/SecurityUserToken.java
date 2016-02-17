/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.security.entity;

import com.wk.p3.greenmall.common.utils.IdGen;
import com.wk.p3.greenmall.modules.security.Principal;
import com.wk.p3.greenmall.modules.security.system.PcWebToken;
import com.wk.p3.greenmall.modules.user.LoginType;
import org.apache.shiro.authc.AuthenticationToken;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.wk.p3.greenmall.common.persistence.DataEntity;

/**
 * 保存用户账号密码登陆凭证Entity
 * @author cc
 * @version 2016-01-21
 */
public class SecurityUserToken extends DataEntity<SecurityUserToken> implements Principal {
	
	private static final long serialVersionUID = 1L;
	private String uniqueUser;		// 关联用户
	private String loginName;		// 用户名
	private String loginType;		// 登陆类型
	private String password;		// 密码
	private String loginIp;		// 最后登陆IP
	private Date loginDate;		// 最后登陆时间
	private String loginFlag;		// 是否可登录
	
	public SecurityUserToken() {
		super();
	}

	public SecurityUserToken(String id){
		super(id);
	}

	@Length(min=0, max=64, message="关联用户长度必须介于 0 和 64 之间")
	public String getUniqueUser() {
		return uniqueUser;
	}

	public void setUniqueUser(String uniqueUser) {
		this.uniqueUser = uniqueUser;
	}
	
	@Length(min=0, max=64, message="用户名长度必须介于 0 和 64 之间")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	@Length(min=0, max=64, message="登陆类型长度必须介于 0 和 64 之间")
	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType.toString();
	}
	
	@Length(min=0, max=255, message="密码长度必须介于 0 和 255 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Length(min=0, max=100, message="最后登陆IP长度必须介于 0 和 100 之间")
	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	
	@Length(min=0, max=64, message="是否可登录长度必须介于 0 和 64 之间")
	public String getLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}

	@Override
	public AuthenticationToken getAuthentication() {
		return new PcWebToken(this.getLoginName(),this.getPassword(),true,LoginType.parse(loginType));
	}

	@Override
	public LoginType getType() {
		return LoginType.parse(loginType);
	}

	@Override
	public String getPersonId() {
		return this.getUniqueUser();
	}

	public void preInsert(){
		if (!this.isNewRecord){
			setId(IdGen.uuid());
		}
	}
}