/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.common.servlet;

import com.wk.p3.greenmall.common.web.Response;
import com.wk.p3.greenmall.modules.msg.exception.SendException;
import com.wk.p3.greenmall.modules.msg.impl.sms.Sms;
import com.wk.p3.greenmall.modules.msg.impl.sms.SmsService;
import com.wk.p3.greenmall.modules.sys.utils.DictUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * 短信验证码服务类
 * 两个方法：
 * 		1.requestSMS()
 * 		2.validateSMS()
 * 		先请求验证码，再验证验证码的有效性
 * 一个工具类：
 *  	validateMObile()
 *  	验证手机号码有效性
 */
@Service
public class SMSValidate  {

	private static final Pattern pattern = Pattern.compile("^1(3[0-9]|4[57]|5[0-35-9]|8[0-9]|70)\\d{8}$");
	private static final String name = "SMS";
	private static final long timeOut = 1000*60; //60秒

	@Autowired
	SmsService smsService;

	/**
	 * 验证手机验证码是否有效
	 * @param mobile
	 * @param request
	 * @return
	 */
	public Response validateSMS(String mobile,HttpServletRequest request,String code){
		Object sm = request.getSession().getAttribute(name);
		if(sm==null){//没有发送过短信
			return Response.error(-14, "验证失败，验证码没有发送");
		}else{
			SMS sms = (SMS)sm;
			Response r = sms.validate(mobile,code);
			if(sms.timeOut()>0){//验证码已经超时，重新发送验证码
				if(r.isSuccess()){
					request.getSession().removeAttribute(name);
					return Response.success("验证码已经超时，但验证正确");
				}else{
					return r;
				}
			}else{
				if(r.isSuccess()){
					request.getSession().removeAttribute(name);
					return Response.success("验证正确");
				}else{
					return r;
				}
			}
		}
	}

	public Response requestSMS(String mobile,HttpServletRequest request){
		//判断手机是否正确
		if(!validateMObile(mobile)){
			return Response.error(-11, "请输入正确的手机号");
		}
		//判断设置时间是否已经超时了，超时了才可以发送
		long restTime = isTimeOut(mobile,request);
		if(restTime>=0){
			try {
				String fixCode = DictUtils.getDictValue("fixCode", "smsCode", "true");
				String smsCode = DictUtils.getDictValue("code", "smsCode", "1234");
				if (fixCode.equals("false")) {
					smsCode = sendsms(mobile);
				}
				updateSMS(mobile,request,smsCode);
				return Response.error(-13, "验证码已经成功发送");
			} catch (SendException e) {
				e.printStackTrace();
				return Response.error(-12, "往手机发送验证码时失败,请联系客服");
			}
		}else{
			return Response.error(-13, "验证码已经发送，距离下次发送时间为"+(-restTime/1000)+"秒",-restTime/1000);
		}

	}

	/**
	 * 更新SMS状态
	 *
	 * @param mobile
	 * @param request
	 */
	private void updateSMS(String mobile, HttpServletRequest request, String code) {
		Object sm = request.getSession().getAttribute(name);
		if (sm == null) {//没有发送过短信
			request.getSession().setAttribute(name, new SMS(mobile));
		} else {
			SMS sms = (SMS) sm;
			sms.setSendTime(System.currentTimeMillis());
			sms.setCode(code);
		}
	}

	/**
	 * 发送消息
	 * @param phoneNumber
	 * @return
	 * @throws SendException
	 */
	private String sendsms(String phoneNumber) throws SendException {
		//六位验证码
		String value = new String(new Random().nextLong()+"").substring(1,7);
		List list = new ArrayList<String>();
		list.add(phoneNumber);
		smsService.sendSMS(new Sms(list, "【果果网】" + value + "为您的注册验证码，感谢您的注册。"));
		return value;
	}

	/**
	 * 超时时间
	 *  == 0  没有发过
	 *  > 0   可以发送
	 *  < 0   没有发送
	 * @param mobile
	 * @param request
	 * @return
	 */
	private long isTimeOut(String mobile, HttpServletRequest request) {
		Object sm = request.getSession().getAttribute(name);
		if(sm==null){//没有发送过短信
			request.getSession().setAttribute(name,new SMS(mobile));
			return 0;
		}else{
			SMS sms = (SMS)sm;
			if(sms.getMobile().equals(mobile)){
				return sms.timeOut();
			}else{
				request.getSession().setAttribute(name,new SMS(mobile));
				return 0;
			}

		}
	}

	/**
	 * 手机正则验证
	 * @param mobile
	 * @return
	 */
	public static boolean validateMObile(String mobile){
		return pattern.matcher(mobile).find();
	}


	/**
	 * SMS  --  (sendTime)	发送时间
	 * 		--  (mobile)	发送手机号
	 * 		--  (code) 		验证码
	 */
	public static class SMS implements Serializable{


		private long sendTime;
		private String mobile;
		private String code = "";

		public SMS(String mobile) {
			this.sendTime = System.currentTimeMillis();
			this.mobile = mobile;
		}

		public long getSendTime() {
			return sendTime;
		}

		public void setSendTime(long sendTime) {
			this.sendTime = sendTime;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public long timeOut() {
			return System.currentTimeMillis()-this.getSendTime() - timeOut;
		}

		public Response validate(String mobile,String code) {
			if(!this.mobile.equals(mobile)){
				return Response.error(-16,"当前发送验证码的手机号与当前手机号不符合");
			}else if(!this.code.equals(code)){
				return Response.error(-17,"验证码不符合");
			}else{
				return Response.success("验证通过");
			}
		}

		public void setCode(String code) {
			this.code = code;
		}
	}
}
