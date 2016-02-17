package com.wk.p3.greenmall.modules.user.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by cc on 15-12-17.
 */
@RunWith(SpringJUnit4ClassRunner.class) //指定测试用例的运行器 这里是指定了Junit4
@ContextConfiguration({"/spring-context.xml"}) //指定Spring的配置文件 /为classpath下
@Transactional //对所有的测试方法都使用事务，并在测试完成后回滚事务
public class UserSoleServiceTest {

//    @Resource//会自动注入 default by type
//    public UserSoleService userSoleService;
//
//    @Test
//    public void testSave() {
//        UserSole userSole = new UserSole();
//        UserSole savedSole = userSoleService.save(userSole);
//        UserSole getSaveSole = userSoleService.get(savedSole.getId());
//        assertEquals(savedSole.getPrincipal(),getSaveSole.getPrincipal());
//    }
//
//    private String insertList(int n){
//        String userId = IdGen.uuid();
//        for(int i=0;i<n;i++){
//            UserSole userSole = new UserSole();
//            userSole.setPrincipal(userId);
//            userSoleService.save(userSole);
//        }
//        return userId;
//    }
//
//    @Test
//    public void testFindList() {
//        UserSole userSole = new UserSole();
//        userSole.setPrincipal(insertList(100));
//        List<UserSole> saveSoles = userSoleService.findList(userSole);
//        assertEquals(saveSoles.size(),100);
//    }
//
//    @Test
//    public void testFindPage() {
//        UserSole userSole = new UserSole();
//        userSole.setPrincipal(insertList(100));
//        Page<UserSole> page = new Page<UserSole>();
//        page.setPageNo(2);
//        page.setPageSize(50);
//        Page<UserSole> saveSoles = userSoleService.findPage(page,userSole);
//        assertEquals(saveSoles.getList().size(),50);
//    }
//
//    @Test
//    public void testDelete() {
//        UserSole userSole = new UserSole();
//        userSole.setPrincipal(insertList(100));
//        List<UserSole> saveSoles = userSoleService.findList(userSole);
//        userSoleService.delete(saveSoles.get(0));
//        saveSoles = userSoleService.findList(userSole);
//        assertEquals(saveSoles.size(),99);
//    }


}
