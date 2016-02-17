package com.wk.p3.greenmall.modules.user.service;

import com.wk.p3.greenmall.modules.sys.service.SystemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by zhaomeng on 2016/2/1.
 */
@RunWith(SpringJUnit4ClassRunner.class) //指定测试用例的运行器 这里是指定了Junit4
@ContextConfiguration({"/spring-context.xml"}) //指定Spring的配置文件 /为classpath下
@Transactional //对所有的测试方法都使用事务，并在测试完成后回滚事务
public class AdminUserServiceTest {
    @Resource
    public AdminUserService adminUserService;

    @Test
    public void testapproveUser() {
        adminUserService.approveUser("2", "ad1845f8b1304c3fab38c8e70f2b9a68", SystemService.currentUser());
    }
}
