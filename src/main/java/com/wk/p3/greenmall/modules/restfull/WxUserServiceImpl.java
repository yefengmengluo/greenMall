package com.wk.p3.greenmall.modules.restfull;

import com.wk.p3.greenmall.common.utils.SpringContextHolder;
import com.wk.p3.greenmall.modules.info.service.GcategoryService;
import com.wk.p3.greenmall.modules.sys.service.AreaService;
import com.wk.p3.greenmall.modules.user.entity.*;
import com.wk.p3.greenmall.modules.user.service.OrganizationService;
import com.wk.p3.greenmall.modules.user.service.impl.OrganizationServiceImpl;
import io.swagger.model.*;
import io.swagger.model.Organization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.swagger.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WxUserServiceImpl implements UserService{

    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());
    private static com.wk.p3.greenmall.modules.user.UserService userService = SpringContextHolder.getBean(com.wk.p3.greenmall.modules.user.UserService.class);
    private static AreaService areaService = SpringContextHolder.getBean(AreaService.class);
    private static OrganizationServiceImpl organizationServiceImpl = SpringContextHolder.getBean(OrganizationServiceImpl.class);
    private static GcategoryService gcategoryService = SpringContextHolder.getBean(GcategoryService.class);
    private static OrganizationService organizationService = SpringContextHolder.getBean(OrganizationService.class);

    @Override
    public ResponseEntity<User> getUserByUserId(String userId) {
        ResponseEntity entity=null;
        try {
            FrontUser frontUser = userService.getUserByUserId(userId);
            com.wk.p3.greenmall.modules.user.entity.Organization organization=organizationServiceImpl.findOrganizationByIdNoSetAreaName(frontUser.getOrganizationId());
            if(organization!=null) {
                Organization currentOrganziation = new Organization();
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
                user.setProducts(products);
                user.setRoleType(User.RoleTypeEnum.agent);
                entity = new ResponseEntity(user, HttpStatus.OK);
            }else{
                entity = new ResponseEntity(frontUser, HttpStatus.OK);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            entity = new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    @Override
    public ResponseEntity<Void> updateUser(String userId, User body) {
        ResponseEntity rep=null;
        try {
            if(body.getOrganization()!=null){
                Map map = new HashMap();
                map.put("id", body.getOrganization().getId());
                List<Product> products = body.getProducts();
                final int size =  products.size();
                String  mainGoods[]=new String[size];
                for (int i=0;i<size;i++){
                    mainGoods[i]=products.get(i).getGcategory().getId().toString();
                }
                map.put("mainGoods", mainGoods);
                com.wk.p3.greenmall.modules.user.entity.Organization organization = organizationService.findOrganizationById(body.getOrganization().getId());
                FrontUser frontUser = userService.getUserByUserId(userId);
                organizationService.updateUserAndOrganization(frontUser,organization , map);
            }
            rep=new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            rep = new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return rep;
    }
}
