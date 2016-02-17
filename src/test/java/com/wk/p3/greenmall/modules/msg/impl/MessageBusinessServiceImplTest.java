package com.wk.p3.greenmall.modules.msg.impl;

import com.wk.p3.greenmall.modules.msg.*;
import com.wk.p3.greenmall.modules.msg.exception.SendException;
import com.wk.p3.greenmall.modules.msg.service.MsgSmsService;
import com.wk.p3.greenmall.modules.sys.entity.User;
import com.wk.p3.greenmall.modules.sys.utils.DictUtils;
import org.apache.shiro.crypto.hash.Hash;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * Created by cc on 16-1-11.
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
public class MessageBusinessServiceImplTest {

    @Autowired
    MessageBusinessService messageBusinessService;

    CountDownLatch signal= new CountDownLatch(1);

    @Before
    public void init(){
        TargetInterceptor target = new TargetForTest();
        TargetForTest.callable = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                signal.countDown();
                return false;
            }
        };
        MessageBusinessServiceImpl impl = (MessageBusinessServiceImpl)messageBusinessService;
        impl.setTarget(target);
    }

    @Test
    public void sendMatchSMSMessage() throws SendException, InterruptedException {
        String code = DictUtils.getDictValue("demand", "MsgCode", "demand");
        List<String> list = new ArrayList<String>();
        list.add("18601995563");
        Map<String,String> model = new HashMap<String, String>();
        model.put("message","供应大白菜20吨，价格200元");
        messageBusinessService.sendMatchSMSMessage(code,list,model);
        signal.await();
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
            Assert.assertTrue(msg.getModel().get("message").equals("供应大白菜20吨，价格200元"));
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
