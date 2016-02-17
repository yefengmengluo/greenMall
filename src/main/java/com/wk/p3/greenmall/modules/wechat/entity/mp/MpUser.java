/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.wechat.entity.mp;

import com.wk.p3.greenmall.common.persistence.DataEntity;
import com.wk.p3.greenmall.modules.security.Principal;
import com.wk.p3.greenmall.modules.sys.entity.Role;
import com.wk.p3.greenmall.modules.sys.entity.User;
import com.wk.p3.greenmall.modules.user.LoginType;
import org.apache.shiro.authc.AuthenticationToken;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 微信用户Entity
 * @author cc
 * @version 2015-11-25
 */
public class MpUser extends DataEntity<MpUser>{
	
	private static final long serialVersionUID = 1L;
	private User userId;		// 业务主表ID
	private String name;		// 名称
	private String subscribe;		// 是否订阅
	private String openid;		// openId
	private String unionid;		// unionId
	private String nickname;		// 昵称
	private String sex;		// 性别
	private String language;		// 语言
	private String city;		// 城市
	private String province;		// 省
	private String country;		// 国家
	private String headimgurl;		// 图像URL
	private String remark;		// 标记
	private String subscribetime;		// 订阅时间
	private String sexid;		// 性别id
	private String groupid;		// 分组Id
	private User createById;		// 创建者
	private User updateById;		// 更新者

	private String uniqueUser;
	private List<Role> roles;
	
	public MpUser() {
		super();
	}

	public MpUser(String id){
		super(id);
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=1, message="是否订阅长度必须介于 0 和 1 之间")
	public String getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}
	
	@Length(min=0, max=64, message="openId长度必须介于 0 和 64 之间")
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	@Length(min=0, max=64, message="unionId长度必须介于 0 和 64 之间")
	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	
	@Length(min=0, max=64, message="昵称长度必须介于 0 和 64 之间")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Length(min=0, max=64, message="性别长度必须介于 0 和 64 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=64, message="语言长度必须介于 0 和 64 之间")
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	@Length(min=0, max=64, message="城市长度必须介于 0 和 64 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Length(min=0, max=64, message="省长度必须介于 0 和 64 之间")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Length(min=0, max=64, message="国家长度必须介于 0 和 64 之间")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	@Length(min=0, max=100, message="图像URL长度必须介于 0 和 100 之间")
	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	
	@Length(min=0, max=100, message="标记长度必须介于 0 和 100 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Length(min=0, max=11, message="订阅时间长度必须介于 0 和 11 之间")
	public String getSubscribetime() {
		return subscribetime;
	}

	public void setSubscribetime(String subscribetime) {
		this.subscribetime = subscribetime;
	}
	
	@Length(min=0, max=11, message="性别id长度必须介于 0 和 11 之间")
	public String getSexid() {
		return sexid;
	}

	public void setSexid(String sexid) {
		this.sexid = sexid;
	}
	
	@Length(min=0, max=11, message="分组Id长度必须介于 0 和 11 之间")
	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	
	@NotNull(message="创建者不能为空")
	public User getCreateById() {
		return createById;
	}

	public void setCreateById(User createById) {
		this.createById = createById;
	}
	
	@NotNull(message="更新者不能为空")
	public User getUpdateById() {
		return updateById;
	}

	public void setUpdateById(User updateById) {
		this.updateById = updateById;
	}

	public void setUniqueUser(String uniqueUser) {
		this.uniqueUser = uniqueUser;
	}

	public String getUniqueUser() {
		return uniqueUser;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoleBases(List<Role> roles) {
		this.roles = roles;
	}


	public String getLoginName() {
		return openid;
	}
}