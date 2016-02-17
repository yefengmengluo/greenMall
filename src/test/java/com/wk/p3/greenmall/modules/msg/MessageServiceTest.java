package com.wk.p3.greenmall.modules.msg;

import com.wk.p3.greenmall.modules.msg.entity.MsgTemplate;
import com.wk.p3.greenmall.modules.msg.exception.SendException;
import com.wk.p3.greenmall.modules.msg.impl.FreeMarkerMsg;
import com.wk.p3.greenmall.modules.msg.impl.sms.SMSTarget;
import com.wk.p3.greenmall.modules.msg.sender.SimpleSender;
import com.wk.p3.greenmall.modules.msg.service.MsgTemplateService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * Created by cc on 15-12-19.
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
@Transactional //对所有的测试方法都使用事务，并在测试完成后回滚事务
public class MessageServiceTest {

    @Autowired
    MessageService messageService;

    @Autowired
    MsgTemplateService msgTemplateService;

    public static class SMSTargetEx extends SMSTarget{

        public SMSTargetEx(String msg) {
            this.msg = msg;
        }

        private String msg=null;

        @Override
        public List<Restriction> getRestrictions() {
            return null;
        }
        @Override
        public void send(Msg msg) throws SendException {
            if(!msg.toMsg().equals(this.msg)){
                Assert.assertTrue(false);
            }else{
                Assert.assertTrue(true);
            }
            if(callable!=null){
                try {
                    callback(callable);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public void callback(Callable callable) throws Exception {
            callable.call();
        }

        public static Callable callable = null;
    }

    /**
     * 直接调用target.send方法发送消息
     * @throws SendException
     */
    @Test
    public void sendMessage() throws SendException {
        String value = "value";
        MsgTemplate template = new MsgTemplate();
        template.setContent("${a}");
        Map<String,String> map = new HashMap<String, String>();
        map.put("a",value);
        SMSTargetEx target = new SMSTargetEx(value);
        //直接发送消息
        target.send(new FreeMarkerMsg(template, map));
    }


    /**
     * 先投递到队列再发送消息
     */
    @Test
    public void sendMessageByQueue() {

        final CountDownLatch signal = new CountDownLatch(3);
        SMSTargetEx.callable = new Callable() {
            @Override
            public Object call() throws Exception {
                signal.countDown();
                return null;
            }
        };

        String value = "value";
        MsgTemplate template = new MsgTemplate();
        template.setContent("${a}");
        Map<String,String> map = new HashMap<String, String>();
        map.put("a",value);
        SMSTargetEx target = new SMSTargetEx(value);

        try {
            messageService.sendMessage(new SimpleSender(),new FreeMarkerMsg(template,map),target);
            messageService.sendMessage(new SimpleSender(),new FreeMarkerMsg(template,map),target);
            messageService.sendMessage(new SimpleSender(),new FreeMarkerMsg(template,map),target);
            signal.await();
        } catch (SendException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




}
