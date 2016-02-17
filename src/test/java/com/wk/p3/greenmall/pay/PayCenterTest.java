package com.wk.p3.greenmall.pay;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wk.p3.greenmall.modules.paycenter.Encrypt;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class PayCenterTest {
	private static String curLine = "";
	private static String content = "";
	@Test
	public void testHttps(){
		
		
	}
	
	@Test
	public void testpay() throws IOException{
		StringBuffer url=new StringBuffer();
		String ordernum="123456985";
		url.append("1=1?&ordernum="+ordernum);
		String money="20.5";
		url.append("&money="+money);
		String puky="1ero758w";
		String prky="2d6sd23==";
		String date=String.valueOf(new Date().getTime());
		url.append("&date="+date);
		String sign=Encrypt.encry(ordernum+money+puky+prky+date);
		System.out.println("http://localhost/payrequest"+url.toString()+"&sing="+sign);
	}
	
	public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String test="1236544100.36656564da45d5as";
		System.out.println(Encrypt.encry(test));
		StringBuffer url=new StringBuffer();
		String ordernum="123456985";
		url.append("?ordernum="+ordernum);
		String money="20.5";
		url.append("&money="+money);
		String puky="1ero758w";
		String prky="2d6sd23==";
		String date=String.valueOf(new Date().getTime());
		url.append("&date="+date);
		String sign=Encrypt.encry(ordernum+money+puky+"doQs6ZZP3LK21rH8ccqO8w=="+date);
		String returnurl="http://localhost";
		url.append("&returnurl="+returnurl);
		String paytype="alipay";
		url.append("&paytype="+paytype);
		System.out.println("http://localhost/greenMall/payrequest"+url.toString()+"&sign="+sign);
		String pid="99114001";
		System.out.println("http://localhost/greenMall/payprky?PID="+pid+"&puky="+Encrypt.encry(puky)+"&returnurl="+returnurl);
		String locaurl="http://localhost/greenMall/payprky?PID="+pid+"&puky="+Encrypt.encry(puky)+"&returnurl="+returnurl;
    	URL localurl=new URL(locaurl);
		localurl.openConnection().getContent();
		
	}
	
}
