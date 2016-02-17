package com.wk.p3.greenmall.common.jms;

import org.apache.activemq.ScheduledMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Created by cc on 15-12-13.
 */
@Resource
@Component//("queueSender")
public class QueueSender {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name = "jmsQueueTemplate")
    private JmsTemplate jmsQueueTemplate;

    public void send(String destination, final Object msg) {
        jmsQueueTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                logger.debug("createMessage");
                TextMessage message = session.createTextMessage();
                long delay = 1 * 1000;
                long period = 2 * 1000;
                int repeat = 5;
                message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, delay);
                message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, period);
                message.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, repeat);
                message.setObjectProperty("msg", msg);
                return jmsQueueTemplate.getMessageConverter().toMessage(message, session);
            }
        });
    }

}
