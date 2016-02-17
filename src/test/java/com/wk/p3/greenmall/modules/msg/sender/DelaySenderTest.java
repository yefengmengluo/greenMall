package com.wk.p3.greenmall.modules.msg.sender;

import com.wk.p3.greenmall.modules.msg.*;
import com.wk.p3.greenmall.modules.msg.entity.MsgTemplate;
import com.wk.p3.greenmall.modules.msg.exception.SendException;
import com.wk.p3.greenmall.modules.msg.impl.FreeMarkerMsg;
import com.wk.p3.greenmall.modules.sys.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

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
@Transactional //对所有的测试方法都使用事务，并在测试完成后回滚事务
public class DelaySenderTest{

    @Autowired
    MessageService messageService;

    private static final int delay = 1;

    CountDownLatch signal= new CountDownLatch(1);
    TargetForTest target = null;

    @Before
    public void init(){
        target = new TargetForTest();
        TargetForTest.callable = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                signal.countDown();
                return false;
            }
        };
    }

    /**
     * 发送一条延时消息
     * 先投递到队列再发送消息
     */
    @Test
    public void sendMessageDelay() {
        try {
            messageService.sendMessage(new DelaySender(DelaySenderTest.delay*1000l),new FreeMarkerMsg(),target, new DeliverInterceptor() {
                @Override
                public boolean pre(Msg msg,Target target,Map<String,Object> context) throws SendException { return true; }

                /**
                 * 消息投递拦截之-post
                 * @param msg
                 * @param target
                 * @param context
                 * @throws SendException
                 */
                @Override
                public void post(Msg msg,Target target,Map<String,Object> context) throws SendException {
                    //投递前配置模板
                    MsgTemplate template = new MsgTemplate();
                    template.setTemplate("[${deliverTime}][${toSendTime}]");
                    msg.setTemplate(template);
                    //投递前设置时间
                    Map<String, Date> model = new HashMap<String,Date>();
                    msg.setModel(model);
                    model.put("deliverTime", new Date());
                }
                @Override
                public void afterCompletion(Msg msg,Target target,Map<String,Object> context, Exception ex) throws SendException {}
            });
            signal.await();
        } catch (SendException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



    public static class TargetForTest extends TargetInterceptor implements Serializable {
        private Logger logger = LoggerFactory.getLogger(getClass());
        public static Callable<Boolean> callable = null;

        /**
         * 消息真实发送
         * @param msg
         * @throws SendException
         */
        @Override
        public void send0(Msg msg) throws SendException {
            //接收到投递消息后准备发送时，设置当前时间
            Map<String, Date> map = (Map<String, Date>) msg.getModel();
            map.put("toSendTime", new Date());
            Assert.assertTrue(map.get("toSendTime").getSeconds() - map.get("deliverTime").getSeconds() == DelaySenderTest.delay);
            //logger.debug("sendMessageDelay消息:"+msg.toMsg() );
            if(callable!=null){
                try {
                    callable.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * 消息发送前拦截-preSend
         * @param msg
         * @return
         * @throws SendException
         */
        @Override
        public boolean preSend(Msg msg) throws SendException { return true; }

        @Override
        public void postSend(Msg msg) throws SendException {}

        @Override
        public void afterCompletion(Msg msg, Exception ex) throws SendException {}

        @Override
        public Platform getPlatform() {
            return null;
        }

        @Override
        public void setRestrictions(List<Restriction> restrictions) {}

        @Override
        public List<Restriction> getRestrictions() {
            return null;
        }

        @Override
        public void setUsers(List<String> users) {}
    }
}
