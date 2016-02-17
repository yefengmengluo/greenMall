两个基类
=======

                Msg     --- Template
                        --- Model

 #一条消息Msg由模板Template和模型Model确定。模板目前使用FreeMarker模板实现，模型使用Map<String,?>类型实现


                Target  --- Restriction
                        --- User
                        --- Platform


 #Target表示消息目标，由限制条件Restriction，用户User，平台Platform确定消息最终的发送在Target方法中


几个实体类
=========
                MsgSms,保存短信消息相关记录
                MsgTemplate,消息模板
                MsgUser,消息用户


业务简图
========

                业务code  | --- 平台1 | --- 模板1
                                       --- 模板2
                                       --- 模板3
                          --- 平台2  | --- 模板1
                                       --- 模板2
                                       --- 模板3
                       

 #对于一种业务，有多个平台渠道去发送，每个渠道有多种模板可以选择
 
                比如：业务code,指业务类型，比如忘记密码，意指忘记密码后发送忘记密码的短信
                     平台Platform,指发送短信的渠道，比如短信通道，微信消息通道，android推送通道，ios推送通道等
                        不同平台对应不同的具体发送方法，数量不多，每种都需要代码具体实现
                     模板template,在后台编辑，比如一段模板为：您好,${username},您的订单${orderNo}已经支付成功。
                     
                     
消息发送流程
==========

                消息=====》队列======》发送

 #消息进入队列时和发送时都可以拦截处理