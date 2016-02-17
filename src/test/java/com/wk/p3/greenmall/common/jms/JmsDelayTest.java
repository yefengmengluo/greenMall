package com.wk.p3.greenmall.common.jms;

import org.apache.activemq.ScheduledMessage;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * Created by cc on 15-12-13.
 */

@RunWith(SpringJUnit4ClassRunner.class) //指定测试用例的运行器 这里是指定了Junit4
@ContextConfiguration({"/spring-context.xml","/spring-context-jms.xml"}) //指定Spring的配置文件 /为classpath下
public class JmsDelayTest {

    @Resource(name = "queueSender")
    QueueSender queueSender;

    @Resource(name = "topicSender")
    TopicSender topicSender;

    @Resource(name = "cachingConnectionFactory")
    QueueConnectionFactory queueConnectionFactory;

    Session session;
    MessageProducer producer;
    MessageConsumer consumer;
    Destination destination;

    @Before
    public void init() throws JMSException {
        QueueConnection connection = queueConnectionFactory.createQueueConnection();
        connection.start();
        session = connection.createSession(true, 0);
        destination = new ActiveMQQueue("test_queue");
        producer = session.createProducer(destination);
        consumer = session.createConsumer(destination);
        System.out.println("setMessageListener");
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                System.out.println("ok");
            }
        });
    }

    @After
    public void over() throws JMSException {
        System.out.println("close");
        session.close();
    }

    public void sendAndReciev() throws JMSException {
        TextMessage message = session.createTextMessage("test msg");
        long delay = 3 * 1000;
        long period = 2 * 1000;
        int repeat = 9;
        message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, delay);
        message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, period);
        message.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, repeat);
        producer.send(destination,message);
    }

    @Test
    public void send() throws JMSException, InterruptedException {
//        new Thread(new java.lang.Runnable() {
//            @Override
//            public void run() {
//                try {
//                    System.out.println("sendAndReciev");
//                    sendAndReciev();
//                } catch (JMSException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//        System.out.println("sleep");
//        Thread.currentThread().sleep(20*1000);
    }




}
