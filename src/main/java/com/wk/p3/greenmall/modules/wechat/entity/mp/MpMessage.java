/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.wechat.entity.mp;

import com.wk.p3.greenmall.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.wk.p3.greenmall.common.persistence.DataEntity;

/**
 * 推送给用户的消息Entity
 * @author cc
 * @version 2015-11-25
 */
public class MpMessage extends DataEntity<MpMessage> {
	
	private static final long serialVersionUID = 1L;
	private User mpUserId;		// 关联用户
	private String infoType;		// 信息类型
	private String title;		// 信息名称
	private String content;		// 信息内容
	private Date showDate;		// 显示日期
	private Date sendDate;		// 推送时间
	private String sendFlag;		// 是否允许推送
	private User createById;		// 创建者
	private User updateById;		// 更新者
	
	public MpMessage() {
		super();
	}

	public MpMessage(String id){
		super(id);
	}

	public User getMpUserId() {
		return mpUserId;
	}

	public void setMpUserId(User mpUserId) {
		this.mpUserId = mpUserId;
	}
	
	@Length(min=0, max=64, message="信息类型长度必须介于 0 和 64 之间")
	public String getInfoType() {
		return infoType;
	}

	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}
	
	@Length(min=0, max=100, message="信息名称长度必须介于 0 和 100 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=255, message="信息内容长度必须介于 0 和 255 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getShowDate() {
		return showDate;
	}

	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	
	@Length(min=1, max=1, message="是否允许推送长度必须介于 1 和 1 之间")
	public String getSendFlag() {
		return sendFlag;
	}

	public void setSendFlag(String sendFlag) {
		this.sendFlag = sendFlag;
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
	
}