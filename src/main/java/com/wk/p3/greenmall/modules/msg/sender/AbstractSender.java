package com.wk.p3.greenmall.modules.msg.sender;

import com.wk.p3.greenmall.common.utils.SpringContextHolder;
import com.wk.p3.greenmall.modules.msg.Msg;
import com.wk.p3.greenmall.modules.msg.Sender;
import com.wk.p3.greenmall.modules.msg.Target;
import com.wk.p3.greenmall.modules.msg.exception.SendException;
import com.wk.p3.greenmall.modules.msg.impl.TargetToSend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

/**
 * Created by cc on 15-12-14.
 */
@Service
public abstract class AbstractSender implements Sender {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private JmsTemplate jmsQueueTemplate = SpringContextHolder.getBean("jmsQueueTemplate") ;

    public JmsTemplate getJmsQueueTemplate() {
        return jmsQueueTemplate;
    }

    /**
     * 实时发送方法
     * 将消息对象投递到队列中，队列无延迟的接受到消息后即刻发送消息
     * 注意：真正的发送方法是 target.send(msg)
     * @param msg       消息对象
     * @param target    消息发送目的对象
     * @throws SendException
     */
    @Override
    public void send(final Msg msg, final Target target) throws SendException {
        getJmsQueueTemplate().send("msg_queue", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                logger.debug("JmsTemplate creating Message");
                ObjectMessage message = session.createObjectMessage(new TargetToSend.ToSend(msg, target));
                onMessageConfig(message);
                return jmsQueueTemplate.getMessageConverter().toMessage(message, session);
            }
        });

    }

    /**
     * 投递到队列时对消息进行配置
     * 使用ScheduledMessage类中属性
     *          message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, delay);
     *          message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, period);
     *          message.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, repeat);
     *          ......
     * @param message
     */
    public abstract void onMessageConfig(ObjectMessage message) throws JMSException;

}
