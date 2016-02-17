package com.wk.p3.greenmall.common.jms;

/**
 * Created by cc on 15-12-8.
 */

/**
 JMS 接口

 PTP和Pub/Sub 接口的关系
 JMS 公共接口	               PTP 专有接口	              Pub/Sub 专有接口
 ConnectionFactory	    QueueConnectionFactory	    TopicConnectionFactory
 Connection	            QueueConnection	            TopicConnection
 Destination	            Queue	                    Topic
 Session	                QueueSession	            TopicSession
 MessageProducer	        QueueSender	                TopicPublisher
 MessageConsumer	        QueueReceiver,QueueBrowser	TopicConsumer

 简要定义
 ConnectionFactory——客户端使用这个被管理对象来创建一个Connection。
 Connection——一个到JMS 提高商的活动连接。
 Destination——封装了消息目的地标识的被管理对象。
 Session——一个用于发送和接收消息的单线程上下文。
 MessageProducer——一个由Session 创建用于往目的地发送消息的对象。
 MessageConsumer——一个由Session 创建用于接收发送到目的地的消息的对象。

 开发一个 JMS 客户端
 典型的JMS 客户端执行下面的JMS 设置过程：
 使用 JNDI 来发现ConnectionFactory 对象。
 使用 JNDI 来发现一个或多个Desitination 对象。
 使用 ConnectionFactory 来创建一个具有消息转发约束的JMS Connection。
 使用 Connection 来创建一个或多个JMS Session。
 使用 Session 和Destination 来创建需要的MessageProducer 和MessageConsumer。
 告诉 Connection 开始转发消息。
 此时，客户端有了生产和消费消息的基本的JMS 设置
 */


public class jms {

}
