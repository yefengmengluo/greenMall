/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.msg.entity;

import com.wk.p3.greenmall.modules.msg.Platform;
import com.wk.p3.greenmall.modules.msg.Template;
import org.hibernate.validator.constraints.Length;

import com.wk.p3.greenmall.common.persistence.DataEntity;

/**
 * 模板管理Entity
 * @author cc
 * @version 2015-12-26
 */
public class MsgTemplate extends DataEntity<MsgTemplate> implements Template {
	
	private static final long serialVersionUID = 1L;
	private String plateform;		// 模板使用平台
	private String title;		// 模板名称
	private String content;		// 模板内容
	private String code;		// 业务类型编号
	
	public MsgTemplate() {
		super();
	}

	public MsgTemplate(String id){
		super(id);
	}

	@Length(min=0, max=64, message="模板使用平台长度必须介于 0 和 64 之间")
	public String getPlateform() {
		return plateform;
	}

	public void setPlateform(String plateform) {
		this.plateform = plateform;
	}
	
	@Length(min=0, max=100, message="模板名称长度必须介于 0 和 100 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=255, message="模板内容长度必须介于 0 和 255 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String getTemplate() {
		return getContent();
	}

	@Length(min=0, max=64, message="业务类型编号长度必须介于 0 和 64 之间")
	public String getCode() {
		return code;
	}

	@Override
	public void setTemplate(String content) {
		setContent(content);
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}