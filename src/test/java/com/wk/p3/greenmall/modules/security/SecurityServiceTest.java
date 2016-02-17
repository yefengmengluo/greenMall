package com.wk.p3.greenmall.modules.security;

import com.wk.p3.greenmall.modules.security.entity.SecurityRole;
import com.wk.p3.greenmall.modules.security.service.SecurityRoleService;
import com.wk.p3.greenmall.modules.user.LoginType;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class) //指定测试用例的运行器 这里是指定了Junit4
@ContextConfiguration({
        "/spring-context.xml",
        "/spring-context-shiro.xml",
        "/spring-context-mp.xml",
        "/spring-context-cache.xml",
        "/spring-context-jedis.xml",
        "/spring-context-activiti.xml"}) //指定Spring的配置文件 /为classpath下
//@Transactional //对所有的测试方法都使用事务，并在测试完成后回滚事务
public class SecurityServiceTest{

    @Autowired
    SecurityService securityService;

    @Autowired
    SecurityRoleService securityRoleService ;

    @Resource
    private DefaultWebSecurityManager securityManager;

    Principal principal;
    Person person;

    @Before
    public void setUp() {
        ThreadContext.bind(securityManager);
    }

    @Test
    public void register(){
        principal = securityService.newPrincipalFromPassword("admin","admin", LoginType.pcWeb.toString());
        person = securityService.register(principal);
        assertTrue(person!=null);
    }


    @Test
    public void login(){
        register();
        securityService.login(principal);
        assertTrue(SecurityUtils.getSubject().isAuthenticated());
    }


    @Test
    public void logout() {
        login();
        securityService.logout();
        assertTrue(!SecurityUtils.getSubject().isAuthenticated());
    }

    @Test
    public void currentPerson() {
        login();
        Person person = securityService.currentPerson();
        assertTrue(person.getId()!=null);
    }

    @Test
    public void getRoles() {
        SecurityRole r1 = new SecurityRole();
        r1.setName("r1");
        SecurityRole r2 = new SecurityRole();
        r2.setName("r2");
        SecurityRole r3 = new SecurityRole();
        r3.setName("r3");
        securityRoleService.save(r1);
        securityRoleService.save(r2);
        securityRoleService.save(r3);
        principal = securityService.newPrincipalFromPassword("admin","admin", LoginType.pcWeb.toString());
        person = securityService.register(principal);
        Set<Role> set = new HashSet<Role>();
        set.add(r1);
        set.add(r2);
        set.add(r3);
        securityService.setRoles(person,set);
        securityService.login(principal);
        Person person = securityService.currentPerson();
        Set<Role> roles = person.getRoleSet();
        assertTrue(roles.contains(r1));
        assertTrue(roles.contains(r2));
        assertTrue(roles.contains(r3));
    }

    @Test
    public void getPrincipalByToken() {

    }

    @Test
    public void getPersonByPrincipal() {

    }
}
