package com.wk.p3.greenmall.modules.deal.entity;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author lf
 *
 */
@SuppressWarnings("serial")
public interface Invok {
		
	public final static ConcurrentHashMap<String,String> statusshow = new ConcurrentHashMap<String,String>(){{
		put("s1","<script type=\"text/javascript\">$(\".orderflow\").setStep(1);$(\"#orderconfirm\").show();</script>");
		put("s2","<script type=\"text/javascript\">$(\".orderflow\").setStep(2);$(\"#orderpayment\").show();</script>");
		put("s3","<script type=\"text/javascript\">$(\".orderflow\").setStep(3);$(\"#orderdeliver\").show();</script>");
		put("s4","<script type=\"text/javascript\">$(\".orderflow\").setStep(4);$(\"#ordercomplete\").show();</script>");
		put("s5","<script type=\"text/javascript\">$(\".orderflow\").setStep(5);$(\"#ordersuccess\").show();</script>");
		put("s0","交易取消");
	}};
	
	public final static ConcurrentHashMap<String,ConcurrentHashMap<String,String>> orderstatus = new ConcurrentHashMap<String,ConcurrentHashMap<String,String>>(){{
		put("s1",statusshow);
		put("s2",statusshow);
		put("s3",statusshow);
		put("s4",statusshow);
		put("s5",statusshow);
		put("s0",statusshow);
	}};
	
    public final static ConcurrentHashMap<String,String> orderinvok = new ConcurrentHashMap<String,String>(){{
		put("order_home_show","order_home_show_15");
		put("","");
		put("","");
		put("","");
		put("","");
		put("","");
	}};
	
}
