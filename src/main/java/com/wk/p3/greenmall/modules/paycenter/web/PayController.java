package com.wk.p3.greenmall.modules.paycenter.web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wk.p3.greenmall.modules.paycenter.Encrypt;
import com.wk.p3.greenmall.modules.paycenter.service.PayService;

@Controller
public class PayController {
	private static Logger logger = LoggerFactory.getLogger(PayController.class);
	private final static String puky="1ero758w"; 
	private final static String PID="99114001";
	@Autowired
	PayService payservice;
	/**
	 * 支付
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("/payrequest")
	public void pay(HttpServletResponse response,HttpServletRequest request) throws IOException{
		String ordernum=request.getParameter("ordernum");
		String money=request.getParameter("money");
		String date=request.getParameter("date");
		String sign=request.getParameter("sign");
		String prky=(String) request.getSession().getAttribute("prky");
		try {
			
			if(sign.equals(Encrypt.encry(ordernum+money+this.puky+prky+date))&&StringUtils.isNotBlank(request.getParameter("paytype"))){
				payservice.pay(request, response);
			}else{
				logger.info("[支付中心]-[PAYSIGN]-[PROGRAM-ERROR]:ordernum="+ordernum+"money="+money+"date="+date+"sign="+sign);
				response.setContentType("text/plain; charset=utf-8");
				response.getWriter().write("支付异常:签名验证失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("[支付中心]-[PAYSIGN]-[SYSTEM-ERROR]:ordernum="+ordernum+"money="+money+"date="+date+"sign="+sign);
			response.setContentType("text/plain; charset=utf-8");
			response.getWriter().write("支付异常"+e.getMessage());
		}finally{
			response.flushBuffer();
		}
	}
	
	/**
	 * 获得私钥
	 * @throws IOException 
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("/payprky")
	public void payPrky(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String PID=request.getParameter("PID");
		String puky=request.getParameter("puky");
		String returnurl=request.getParameter("returnurl");
		try {
			if(this.PID.equals(PID)&&Encrypt.encry(this.puky).equals(puky)){
				String pvky=Encrypt.key(PID+puky+new Date().getTime());
				request.getSession().setAttribute("prky",pvky);
				response.setContentType("text/plain; charset=utf-8");
				response.sendRedirect(returnurl+"?prky="+pvky);
				logger.info("[支付中心]-[GETSIGN]-[SUCCESS]"+returnurl+"?prky="+pvky);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setContentType("text/plain; charset=utf-8");
			response.getWriter().write("支付异常：签名验证失败");
			logger.info("[支付中心]-[GETSIGN]-[SYSTEM-ERROR]:PID="+PID+"puky="+puky+"returnurl="+returnurl+e.getMessage());
		}finally{
			response.flushBuffer();
		}
	}
	
	/**
	 * 支付CallBack
	 * @throws IOException 
	 */
	@RequestMapping("/paycallback")
	public void callback(HttpServletRequest request,HttpServletResponse response) throws IOException{
			try {
				payservice.removeUrl(request,response);
			} catch (IOException e) {
				response.setContentType("text/plain; charset=utf-8");
				response.getWriter().write("回调确认失败");
				e.printStackTrace();
			}
	}
	
}
	

