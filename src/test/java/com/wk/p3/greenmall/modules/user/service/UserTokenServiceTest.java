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
public class UserTokenServiceTest {

//    @Resource//会自动注入 default by type
//    public UserTokenService userTokenService;
//
//    @Before
//    public void init(){
//
//    }
//
//    @Test
//    public void testSave() {
//        UserToken userToken = new UserToken();
//        userTokenService.save(userToken);
//        UserToken getSaveSole = userTokenService.get(userToken.getId());
//        assertEquals(getSaveSole.getId(),userToken.getId());
//    }
//
//    private void insertList(int n){
//        for(int i=0;i<n;i++){
//            userTokenService.save(new UserToken());
//        }
//    }
//
//    @Test
//    public void testFindList() {
//        insertList(100);
//        UserToken userToken = new UserToken();
//        List<UserToken> userTokens = userTokenService.findList(userToken);
//        assertEquals(userTokens.size(),100);
//    }
//
//    @Test
//    public void testFindPage() {
//        insertList(100);
//        Page<UserToken> page = new Page<UserToken>();
//        page.setPageNo(2);
//        page.setPageSize(50);
//        Page<UserToken> saveSoles = userTokenService.findPage(page,new UserToken());
//        assertEquals(saveSoles.getList().size(),50);
//    }
//
//    @Test
//    public void testDelete() {
//        insertList(100);
//        List<UserToken> saveSoles = userTokenService.findList(new UserToken());
//        userTokenService.delete(saveSoles.get(0));
//        UserToken userToken = new UserToken();
//        userToken.setDelFlag(BaseEntity.DEL_FLAG_NORMAL);
//        saveSoles = userTokenService.findList(userToken);
//        assertEquals(saveSoles.size(),99);
//    }


}
