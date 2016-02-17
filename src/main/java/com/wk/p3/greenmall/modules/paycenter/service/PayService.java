package com.wk.p3.greenmall.modules.paycenter.service;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.wk.p3.greenmall.common.utils.DateUtils;
import com.wk.p3.greenmall.modules.paycenter.Encrypt;
import com.wk.p3.greenmall.modules.paycenter.PayStatus;
import com.wk.p3.greenmall.modules.paycenter.PayType;
import com.wk.p3.greenmall.modules.paycenter.Serail;
/**
 * 
 * @author Robin
 *
 */
@Service
public class PayService {
	private static Logger logger = LoggerFactory.getLogger(PayService.class);
	private Map<String,String>urlMap=new HashMap<String, String>();
	private String paytype;
	private final Timer timer = new Timer();
	/**
	 * 支付
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void pay(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String ordernum=request.getParameter("ordernum");
		String money=request.getParameter("money");
		String date=request.getParameter("date");
		this.paytype=request.getParameter("paytype");
		StringBuffer url=new StringBuffer();
		url.append("&ordernum="+ordernum);
		url.append("&money="+money);
		url.append("&paystatus="+payResult());
		url.append("&paytype="+this.paytype);
		url.append("&key="+Encrypt.key(ordernum+money+date));
		url.append("&serail="+Serail.getSerail());
		String returnurl=request.getParameter("returnurl")+url.toString();
		response.setContentType("text/plain; charset=utf-8");
		urlMap.put(Encrypt.key(ordernum+money+date), returnurl);
		logger.info("[支付中心]-[支付]-[RETURN]"+returnurl);
		logger.info("[支付中心]-[轮循]-[ADD]"+returnurl);
		response.sendRedirect(returnurl);
		response.flushBuffer();
	}
	
	/**
	 * 支付结果
	 */
	private String payResult(){
		if(PayType.ALIPAY.toString().equals(this.paytype)){
			JSONObject payresult=aliPay();
			return payresult.getString("result");
		}
		return PayStatus.FAIL.toString();
		
	}
	/**
	 * alipay支付
	 */
	private JSONObject aliPay(){
		JSONObject alipayResult=new JSONObject();
		//TODO alipay 业务
		alipayResult.put("result", PayStatus.SUCCESS);
		return alipayResult;
	}
	
	/**
	 * 删除轮循url
	 * @throws IOException 
	 */
	public void removeUrl(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String key=request.getParameter("key");
		String returnurl=request.getParameter("returnurl");
		synchronized(urlMap){
			Iterator<String>iterator=urlMap.keySet().iterator();
			while(iterator.hasNext()){
				String keys=iterator.next();
				if(keys.equals(key)){
					 iterator.remove();      
					 urlMap.remove(key);      
					 logger.info("[支付中心]-[轮循]-[REMOVE]-[SUCCESS]"+key);
					 response.sendRedirect(returnurl+"?key="+key+"status="+true);
					 response.flushBuffer();
				}
			}
		}
		
		
	}
	
	/***
	 * 40分钟轮询一次
	 */
	public void sendSchedule() {
		//TODO 发现注解Schdule不好使，暂时用timeer实现
//		timer.schedule(new TimerTask() {
//			@Override
//			public void run() {
//				String sendUrl="";
//				try {
//				    logger.info(DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss")+"本次轮循数据"+JSONObject.toJSON(urlMap));
//					for (Map.Entry<String, String>entry : urlMap.entrySet()) {
//						sendUrl=entry.getValue();
//						URL localurl=new URL(sendUrl);
//						localurl.openConnection().getContent();
//						logger.info("[支付中心]-[轮询]-[SEND]-[SUCCESS]"+sendUrl);
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//					logger.error("[支付中心]-[轮询]-[SEND]-[ERROR]"+sendUrl);
//				}
//			}
//			//2400000
//		}, 1,2400000);
	}
	
	private PayService(){
		sendSchedule();
	}
	
	
	/**
	 * Robin
	 * 测试同步信息
	 */
	public static void main(String[] args) throws InterruptedException {
		PayService payservice=new PayService();
		int i=0;
		while(true){
			if(i==10)break;
			payservice.urlMap.put("com.baidu"+i, "http://www.baidu.com");
			i++;
		}
		Thread.sleep(2000);
		System.out.println("添加到此结束");
		while(true){
			if(i<0)break;
			//payservice.removeUrl("com.baidu"+i);
			i--;
		}
	}
}
