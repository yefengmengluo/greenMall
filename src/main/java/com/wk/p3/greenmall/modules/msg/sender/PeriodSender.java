package com.wk.p3.greenmall.modules.msg.sender;

import com.wk.p3.greenmall.common.utils.SpringContextHolder;
import com.wk.p3.greenmall.modules.msg.Msg;
import com.wk.p3.greenmall.modules.msg.Target;
import com.wk.p3.greenmall.modules.msg.Sender;
import com.wk.p3.greenmall.modules.msg.Template;
import com.wk.p3.greenmall.modules.msg.exception.SendException;
import com.wk.p3.greenmall.modules.msg.impl.TargetToSend;
import org.apache.activemq.ScheduledMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import java.io.IOException;
import java.util.Date;

/**
 * Created by cc on 15-12-14.
 */
public class PeriodSender extends AbstractSender {
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 周期发送的开始时间
     */
    private Date begin;

    /**
     * 周期发送的周期时间段,1/1000秒作为单位
     */
    private Long periodTime;

    public PeriodSender(Date begin, Long periodTime) {
        this.begin = begin;
        this.periodTime = periodTime;
    }


    @Override
    public void onMessageConfig(ObjectMessage message) throws JMSException {
        message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY,1000);
    }

}
