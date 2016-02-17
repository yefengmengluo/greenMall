package com.wk.p3.greenmall.common.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by cc on 15-12-13.
 */

@RunWith(SpringJUnit4ClassRunner.class) //指定测试用例的运行器 这里是指定了Junit4
@ContextConfiguration({"/spring-context.xml","/spring-context-jms.xml"}) //指定Spring的配置文件 /为classpath下
public class JmsTest {

    @Resource(name = "queueSender")
    QueueSender queueSender;

    @Resource(name = "topicSender")
    TopicSender topicSender;

    @Test
    public void sendQueue() throws Exception {
        queueSender.send("test_queue","test_queue");
        Thread.currentThread().sleep(20*1000);
    }

    @Test
    public void sendTopic() throws Exception {
        topicSender.publish("test_topic","test_topic_message");
    }


}
