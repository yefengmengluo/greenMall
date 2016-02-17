/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.msg.entity;

import org.hibernate.validator.constraints.Length;

import com.wk.p3.greenmall.common.persistence.DataEntity;

/**
 * 发送短信保存Entity
 * @author cc
 * @version 2015-12-26
 */
public class MsgSms extends DataEntity<MsgSms> {
	
	private static final long serialVersionUID = 1L;
	private String mid;		// 模板编号
	private String tele;		// 发送目标手机号，多个用逗号分开
	private String msg;		// 短信内容
	private String extno;		// 参数等于1时拆分短信
	private String result;		// 发送结果
	
	public MsgSms() {
		super();
	}

	public MsgSms(String id){
		super(id);
	}

	@Length(min=1, max=64, message="模板编号长度必须介于 1 和 64 之间")
	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}
	
	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}
	
	@Length(min=0, max=100, message="短信内容长度必须介于 0 和 100 之间")
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Length(min=0, max=8, message="参数等于1时拆分短信长度必须介于 0 和 8 之间")
	public String getExtno() {
		return extno;
	}

	public void setExtno(String extno) {
		this.extno = extno;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}