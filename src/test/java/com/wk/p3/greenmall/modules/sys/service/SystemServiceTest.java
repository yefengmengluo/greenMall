package com.wk.p3.greenmall.modules.sys.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by cc on 15-12-18.
 */
@RunWith(SpringJUnit4ClassRunner.class) //指定测试用例的运行器 这里是指定了Junit4
@ContextConfiguration({
        "/spring-context.xml",
        "/spring-context-shiro.xml",
        "/spring-context-mp.xml",
        "/spring-context-cache.xml",
        "/spring-context-jedis.xml",
        "/spring-context-activiti.xml"}) //指定Spring的配置文件 /为classpath下
@Transactional //对所有的测试方法都使用事务，并在测试完成后回滚事务
public class SystemServiceTest {

    @Autowired
    private SystemService systemService;

    @Autowired
    //private UserTokenService userTokenService;

    @Before
    public void init(){
       // UserToken userToken = new UserToken("cc","cc", LoginType.pcWeb);
       // userTokenService.save(userToken);
    }

    @Test
    public void getPrincipalByUsernameAndloginType(){
       // assertNotNull(systemService.getPrincipal("cc", LoginType.pcWeb));
       // assertNotNull(systemService.getPrincipal("thinkGem", LoginType.admin));
    }

    @Test
    public void getPrincipalById(){
      //  Principal cc = systemService.getPrincipal("cc", LoginType.pcWeb);
      //  Principal admin = systemService.getPrincipal("thinkGem", LoginType.admin);
        //走缓存
      //  assertNotNull(systemService.getPrincipalById(cc.getId()));
      //  assertNotNull(systemService.getPrincipalById(admin.getId()));
    }

}
