package com.wk.p3.greenmall.common.cache;

import com.wk.p3.greenmall.modules.sys.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cc on 15-12-3.
 */
@RunWith(SpringJUnit4ClassRunner.class) //指定测试用例的运行器 这里是指定了Junit4
@ContextConfiguration({"/spring-context-cache.xml","/spring-context-jedis.xml","/spring-context.xml"}) //指定Spring的配置文件 /为classpath下
public class CacheTest {
    private final  Logger logger = LoggerFactory.getLogger(CacheTest.class);

    @Resource
    private CacheServiceForTest accountService ;

    @Test
    public void testGetUserByNameAndId() throws Exception{
        logger.info("=========first qery========user");
        User user = new User();
        user.setName("1234567890");
        List<User> usres = accountService.testGetUserByNameAndId(user);
        logger.info("=========second qery========user");
        List<User> usres1 =  accountService.testGetUserByNameAndId(user);
        System.out.println(usres);
        System.out.println(usres1);
    }

    @Test
    public void testGetUserByName() throws Exception{
        logger.info("=========first qery========user");
        User user = new User();
        user.setId("1112");
        user.setName("testUser11");
        accountService.getUserByName(user);
        logger.info("=========second qery========user");
        accountService.getUserByName(user);

    }
    @Test
    public void testGetUserById() throws Exception{
        logger.info("=========first qery========user");
        User user = new User();
        user.setId("1113");
        user.setName("testUser13");
        accountService.getUserById(user);
        logger.info("=========second qery========user");
        accountService.getUserById(user);

    }
    @Test
    public void testGetAccountByName() throws Exception {
        logger.info("first query...");
        accountService.getAccountByName("a");

        logger.info("second query...");
        accountService.getAccountByName("a");
    }

    @Test
    public void testGetAccountByNameLength() throws Exception {
        logger.info("na:会缓存");
        Account a = accountService.getAccountByNameLength("na");
        Account a1 = accountService.getAccountByNameLength("na");
        logger.info("accountName:不会缓存");
        Account a2 = accountService.getAccountByNameLength("accountName");
        Account a3 = accountService.getAccountByNameLength("accountName");
        System.out.println(a);
        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a3);
    }

    @Test
    public void testGetAccountByNamePassword() throws Exception {
        Account account1 = accountService.getAccountByNamePassword("accountName","1",true);
        logger.info(account1.toString());
        Account account2 = accountService.getAccountByNamePassword("accountName","2",true);
        logger.info(account2.toString());
        Account account3 = accountService.getAccountByNamePassword("accountName","1",false);
        logger.info(account3.toString());
    }

    @Test
    public void testUpdateAccount() throws Exception {
        Account account1 = accountService.getAccountByName("accountName1");
        Account account2 = accountService.getAccountByName("accountName2");

        account2.setId(121212);
        accountService.updateAccount(account2);

        // account1会走缓存
        accountService.getAccountByName("accountName1");
        // account2会查询db
        accountService.getAccountByName("accountName2");

    }

    @Test
    public void testUpdateAccountByCachePut() throws Exception {

        Account account1 = accountService.getAccountByName("accountName1");
        Account account2 = accountService.getAccountByName("accountName2");

        account2.setId(121212);
        accountService.updateAccountByCachePut(account2);

        // account1会走缓存
        accountService.getAccountByName("accountName1");
        // account2会查询db
        accountService.getAccountByName("accountName2");

    }

    @Test
    public void testReload() throws Exception {
        accountService.reload();
        // 这2行查询数据库
        logger.info("这2行查询数据库");
        accountService.getAccountByName("somebody1");
        accountService.getAccountByName("somebody2");
        // 这两行走缓存
        logger.info("这两行走缓存");
        accountService.getAccountByName("somebody1");
        accountService.getAccountByName("somebody2");
    }

    @Test
    public void testReloadException(){
        accountService.getAccountByName("somebody1");
        accountService.getAccountByName("somebody2");
        try {
            accountService.reloadException();
        } catch (Exception e) {
            logger.info("更新异常，缓存不会清空,下面查询走缓存");
            //...
        }
        accountService.getAccountByName("somebody1");
        accountService.getAccountByName("somebody2");
    }

    @Test
    public void testAccountByName2(){
        accountService.getAccountByName2("somebody1");
        logger.info("缓存失败，走数据库");
        accountService.getAccountByName2("somebody1");
    }



}
