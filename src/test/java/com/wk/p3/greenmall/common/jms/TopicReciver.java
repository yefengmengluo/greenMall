package com.wk.p3.greenmall.common.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by cc on 15-12-13.
 */

@Component("topicReciver")
public class TopicReciver extends MessageListenerAdapter {
    private static final Logger log = LoggerFactory.getLogger(QueueReciver.class);
    //@JmsListener(destination="test_queue",concurrency="5-10")
    public void onMessage(Message message, Session session) throws JMSException {
        try {
            Object bean = getMessageConverter().fromMessage(message);
            System.out.println(bean);
            System.out.println(session);
            message.acknowledge();
            message.acknowledge();
        } catch (MessageConversionException e){
            e.printStackTrace();
        } catch (JMSException e){
            e.printStackTrace();
        }
    }
}