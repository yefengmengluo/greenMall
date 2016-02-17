package com.wk.p3.greenmall.Info;

import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.common.utils.StringUtils;
import com.wk.p3.greenmall.modules.info.entity.Gcategory;
import com.wk.p3.greenmall.modules.restfull.WxCategoryServiceImpl;
import com.wk.p3.greenmall.modules.restfull.WxInfoServiceImp;
import com.wk.p3.greenmall.modules.sys.dao.UserDao;
import com.wk.p3.greenmall.modules.sys.entity.User;
import com.wk.p3.greenmall.modules.user.entity.Organization;
import com.wk.p3.greenmall.modules.user.service.impl.OrganizationServiceImpl;
import io.swagger.model.Info;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.List;

/**
 * Created by zhuyanqing on 2016/1/25.
 */
@RunWith(SpringJUnit4ClassRunner.class) //指定测试用例的运行器 这里是指定了Junit4
@ContextConfiguration(locations = "classpath:spring-context.xml")
//@ContextConfiguration({"/spring-context.xml"}) //指定Spring的配置文件 /为classpath下
public class InfoTest {

    @Autowired
    OrganizationServiceImpl organizationServiceImpl;

    @Autowired
    private UserDao userDao;


    @Test
    public void findOrganizationByMainGoodsType() throws Exception{
        Gcategory gcategory = new Gcategory();
        gcategory.setId(64);
        Page<Gcategory> page = new Page<Gcategory>();
        page.setPageSize(3);
        gcategory.setPage(page);
        page.setPageNo(1);
        List<Organization> list = organizationServiceImpl.findOrganizationByMainGoodsType(gcategory);
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }

    @Test
    public void testIntDouble(){
        Double d = 12.22;
        System.out.println(d.intValue());
    }


    //接口无法注入，但是有的地方可以
    @Test
    public void testUserDao(){
       User user =  userDao.get("1");
        System.out.println(user);
    }

    @Test
    public void testFile(){
        String uploadPath = "D:\\wangku\\greenMall\\upload\\info_images\\1455591602711.jpg";
        File file = new File(uploadPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    @Test
    public void test(){
        System.out.println(StringUtils.modityTel("15111111111"));
    }




}
