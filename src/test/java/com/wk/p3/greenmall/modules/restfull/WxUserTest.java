package com.wk.p3.greenmall.modules.restfull;

import com.wk.p3.greenmall.modules.info.service.GcategoryService;
import com.wk.p3.greenmall.modules.msg.entity.MsgTemplate;
import com.wk.p3.greenmall.modules.msg.exception.SendException;
import com.wk.p3.greenmall.modules.msg.impl.FreeMarkerMsg;
import com.wk.p3.greenmall.modules.sys.service.AreaService;
import com.wk.p3.greenmall.modules.user.UserService;
import com.wk.p3.greenmall.modules.user.entity.FrontUser;
import com.wk.p3.greenmall.modules.user.entity.Organization;
import com.wk.p3.greenmall.modules.user.service.OrganizationService;
import com.wk.p3.greenmall.modules.user.service.impl.OrganizationServiceImpl;
import io.swagger.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class WxUserTest {
    //    @Autowired
//    ApiServiceImp apiServiceImp;
    @Autowired
    WxUserServiceImpl wxUserService;

    @Autowired
    UserService userService;

    @Autowired
    OrganizationService organizationService;

    @Autowired
    GcategoryService gcategoryService;

    @Autowired
    AreaService areaService;

    @Autowired
    OrganizationServiceImpl organizationServiceImpl;


    @Test
    public void testGetUserById() throws SendException {
        ResponseEntity<User> userByUserId = wxUserService.getUserByUserId("d3adc2d4352a455da2a8cbb0a3eae304");
        System.out.print("11111111111111111111");
    }
    @Test
    public void testUpdateUser() throws SendException {
        FrontUser frontUser = userService.getUserByUserId("e779aea6d5604d6a9bf5d02cbb422280");
        com.wk.p3.greenmall.modules.user.entity.Organization organization = organizationServiceImpl.findOrganizationByIdNoSetAreaName(frontUser.getOrganizationId());
        if (organization != null) {
            io.swagger.model.Organization currentOrganziation = new io.swagger.model.Organization();
            currentOrganziation.setId(organization.getId());
            currentOrganziation.setName(organization.getName());
            User user = new User();
            user.setId(frontUser.getId());
            user.setOrganization(currentOrganziation);
            user.setUserStatus(Integer.parseInt(frontUser.getStatue()));
            //User user address
            Address address = new Address();
            Area province = new Area();
            com.wk.p3.greenmall.modules.sys.entity.Area pro = areaService.get(organization.getProvinceId());
            province.setId(pro.getId());
            province.setName(pro.getName());
            province.setCode(pro.getCode());
            province.setType(pro.getType());
            Area city = new Area();
            com.wk.p3.greenmall.modules.sys.entity.Area cit = areaService.get(organization.getCityId());
            city.setId(cit.getId());
            city.setName(cit.getName());
            city.setCode(cit.getCode());
            city.setType(cit.getType());
            Area area = new Area();
            com.wk.p3.greenmall.modules.sys.entity.Area area1 = areaService.get(organization.getArea());
            area.setId(area1.getId());
            area.setName(area1.getName());
            area.setCode(area1.getCode());
            area.setType(area1.getType());
            address.setProvince(province);
            address.setCity(city);
            address.setArea(area);
            user.setAddress(address);
            //use List<Product> products主营产品
            List<Product> products = new ArrayList<Product>();
            List<Integer> ids = organizationServiceImpl.findOrganziationMainIdsGoodsbyId(frontUser.getOrganizationId());
            Product product = null;
            com.wk.p3.greenmall.modules.info.entity.Gcategory gcategory = null;
            io.swagger.model.Gcategory gcategory1 = null;
            for (Integer id : ids) {
                product = new Product();
                gcategory = gcategoryService.get(id);
                product.setId(gcategory.getId().toString());
                gcategory1 = new io.swagger.model.Gcategory();
                gcategory1.setId(gcategory.getId());
                gcategory1.setName(gcategory.getName());
                gcategory1.setCode(gcategory.getCode());
                product.setGcategory(gcategory1);
                products.add(product);
            }
            Product product1=new Product();
            io.swagger.model.Gcategory gcategory2 = new Gcategory();
            gcategory2.setId(64);
            gcategory2.setCode("renguo");
            gcategory2.setName("仁果");
            product1.setGcategory(gcategory2);
            product1.setId(gcategory1.getId().toString());
            products.add(product1);
            user.setProducts(products);
            user.setRoleType(User.RoleTypeEnum.agent);
            wxUserService.updateUser("e779aea6d5604d6a9bf5d02cbb422280", user);
            System.out.print("11111111111111111111");
        }
    }
}
