package com.wk.p3.greenmall.modules.msg.impl;

import com.wk.p3.greenmall.modules.msg.entity.MsgSms;
import com.wk.p3.greenmall.modules.msg.exception.HttpSendException;
import com.wk.p3.greenmall.modules.msg.exception.SendException;
import com.wk.p3.greenmall.modules.msg.impl.sms.Sms;
import com.wk.p3.greenmall.modules.msg.impl.sms.SmsService;
import com.wk.p3.greenmall.modules.msg.service.MsgSmsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cc on 15-12-29.
 */
@RunWith(SpringJUnit4ClassRunner.class) //指定测试用例的运行器 这里是指定了Junit4
@ContextConfiguration({
        "/spring-context.xml",
        "/spring-context-shiro.xml",
        "/spring-context-mp.xml",
        "/spring-context-cache.xml",
        "/spring-context-jedis.xml",
        "/spring-context-jms.xml",
        "/spring-context-activiti.xml"}) //指定Spring的配置文件 /为classpath下
//@Transactional //对所有的测试方法都使用事务，并在测试完成后回滚事务
public class SmsServiceTest {

    @Autowired
    SmsService smsService;

    @Autowired
    MsgSmsService msgSmsService;

    /**
     * 正常发送的短信，需要手机上面查看
     * @throws SendException
     */
    @Test
    public void sendSMS() throws SendException {
        String tel1 = "18601995563";
        String tel2 = "18600642261";
        List list =  new ArrayList<String>();
        Sms sms = new Sms(list, "刘雪玲 2016 Good Luck  "+new Date().toLocaleString());
        list.add(tel1);
        list.add(tel2);
        smsService.sendSMS(sms);
        String mid = sms.getMid();
        MsgSms ms = new MsgSms();
        ms.setMid(mid);
        List<MsgSms> mss = msgSmsService.findList(ms);
        Assert.assertTrue(mss.size()==1 && mss.get(0).getTele().equals(tel1 + "," + tel2 + ":" +tel1+"," + tel2+"\n"));
    }

    @Test
    public void sendValidate() throws SendException {
        ArrayList list =  new ArrayList<String>();
        list.add("18600642261");
        list.add("18601995563");
        smsService.sendSMS(new Sms(list, "【果果网】146875为您的注册验证码，感谢您的注册——test。"));
    }

    /**
     * 同时发送两个无效手机号
     * @throws SendException
     */
    @Test
    public void sendSMSError() throws SendException {
        ArrayList list =  new ArrayList<String>();
        list.add("1");
        list.add("2");
        smsService.sendSMS(new Sms(list, "刘雪玲 2016 Good Luck  "+new Date().toLocaleString()));
    }

    @Test
    public void sendSMSSendException(){
        smsService.setSendUrl("http://www.google.com");
        List list =  new ArrayList<String>();
        list.add("18601995563");
        try {
            smsService.sendSMS(new Sms(list, "GBK.平安夜快乐，测试"+new Date().toLocaleString()));
        } catch (SendException e) {
            e.printStackTrace();
            if(e instanceof HttpSendException){
                Assert.assertTrue(true);
            }else{
                Assert.assertTrue(false);
            }
        }
    }



}
