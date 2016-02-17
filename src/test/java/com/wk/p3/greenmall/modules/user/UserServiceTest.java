package com.wk.p3.greenmall.modules.user;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static junit.framework.Assert.assertTrue;

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
public class UserServiceTest {

    @Resource
    private DefaultWebSecurityManager securityManager;

    @Resource
   // private UserService<UserSole> userService;

    @Before
    public void setUp() throws Exception {
        ThreadContext.bind(securityManager);
    }

    @Test
    public void login(){
        //userService.login("thinkGem", "admin", null,LoginType.admin);
        assertTrue(SecurityUtils.getSubject().isAuthenticated());
    }

    private void registerAndLogin(String username,String password) {
        //Principal principal = new UserToken(username,password,LoginType.pcWeb);
        //userService.register(principal);
        //userService.login(username, password,null, principal.getType());
    }

   // @Test
    //public void register() throws UserException {
    //    registerAndLogin("cc", "cc");
    //    assertTrue(SecurityUtils.getSubject().isAuthenticated());
   // }

//    @Test
//    public void logout() throws UserException {
//        registerAndLogin("cc", "cc");
//        //userService.logout();
//        assertTrue(!SecurityUtils.getSubject().isAuthenticated());
//    }
//
//    @Test
//    public void currentUser() throws UserException {
//        registerAndLogin("cc", "cc");
//        Principal principal = userService.currentUser();
//        assertTrue(principal.getLoginName().equals("cc"));
//    }
//
//    @Test
//    public void currentSession() {
//
//    }
//
//    @Test
//    public void bind() throws UserException {
//        registerAndLogin("cc", "cc");
//        Principal cc = userService.currentUser();
//        registerAndLogin("ff", "ff");
//        Principal ff = userService.currentUser();
//        Set<Principal> set = new HashSet();
//        set.add(ff);
//        set.add(cc);
//        UserSole sole = new UserSole();
//        sole.setId(cc.getUniqueUser());
//        //userService.bind(sole,set);
//        //assertTrue(userService.getPrincipal("cc",LoginType.pcWeb).getUniqueUser().equals(cc.getUniqueUser()));
//        //assertTrue(userService.getPrincipal("cc",LoginType.pcWeb).getUniqueUser().equals(cc.getUniqueUser()));
//    }


}
