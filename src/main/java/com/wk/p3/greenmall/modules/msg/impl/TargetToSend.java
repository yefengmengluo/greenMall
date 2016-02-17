package com.wk.p3.greenmall.modules.msg.impl;

import com.wk.p3.greenmall.modules.msg.Msg;
import com.wk.p3.greenmall.modules.msg.Target;
import com.wk.p3.greenmall.modules.msg.exception.SendException;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.Serializable;


/**
 * 队列接受到消息后通过平台方法发送该消息
 * Created by cc on 15-12-26.
 */
@Service
public class TargetToSend extends MessageListenerAdapter {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public void onMessage(Message message, Session session) throws JMSException {
        logger.debug("onMessage");
        try {
            ToSend msg = (ToSend) ((ActiveMQObjectMessage) message).getObject();
            msg.send();
            message.acknowledge();
        } catch (MessageConversionException e){
            e.printStackTrace();
            throw e;
        } catch (JMSException e){
            e.printStackTrace();
            throw e;
        } catch (SendException e) {
            e.printStackTrace();
        }
    }

    public static class ToSend implements Serializable{
        private Msg msg;
        private Target target;

        public ToSend(Msg msg, Target target) {
            this.msg = msg;
            this.target = target;
        }
        public void send() throws SendException{
            if(msg!=null && target!=null){
                target.send(msg);
            }
        }
    }

}
