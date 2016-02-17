package com.wk.p3.greenmall.modules.restfull;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by liujiabao on 2016/1/29.
 */
@RunWith(SpringJUnit4ClassRunner.class) //指定测试用例的运行器 这里是指定了Junit4
@ContextConfiguration({
        "/spring-context.xml",
        "/spring-context-shiro.xml",
        "/spring-context-mp.xml",
        "/spring-context-cache.xml",
        "/spring-context-jedis.xml",
        "/spring-context-jms.xml",
        "/spring-context-activiti.xml"}) //指定Spring的配置文件 /为classpath下
@Transactional //对所有的测试方法都使用事务，并在测试完成后回滚事务
public class ApiServiceTest {
//    @Autowired
//    ApiServiceImp apiServiceImp;



}
