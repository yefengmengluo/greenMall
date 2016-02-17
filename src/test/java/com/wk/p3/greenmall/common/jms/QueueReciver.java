package com.wk.p3.greenmall.common.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.jms.annotation.JmsListener;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
//import cn.test.MqBean;
//import cn.test.activemq.message.types.QueueDefination;

/**
 * Created by cc on 15-12-13.
 */
@Component("queueReciver")
public class QueueReciver extends MessageListenerAdapter{
    private static final Logger log = LoggerFactory.getLogger(QueueReciver.class);

    //@JmsListener(containerFactory = "jmsListenerContainerFactory",destination="test_queue",concurrency="5-10")
    public void onMessage(Message message, Session session) throws JMSException {
        log.debug("onMessage");
        try {
            //Object bean = getMessageConverter().fromMessage(message);
            System.out.println(message.getObjectProperty("msg"));
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
