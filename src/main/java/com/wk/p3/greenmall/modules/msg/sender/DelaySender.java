package com.wk.p3.greenmall.modules.msg.sender;

import org.apache.activemq.ScheduledMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

/**
 * Created by cc on 15-12-14.
 */
public class DelaySender extends AbstractSender {
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 周期发送的开始时间
     */
    private long periodTime;

    public DelaySender(Long periodTime) {
        this.periodTime = periodTime;
    }

    @Override
    public void onMessageConfig(ObjectMessage message) throws JMSException {
        message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY,periodTime);
    }

}
