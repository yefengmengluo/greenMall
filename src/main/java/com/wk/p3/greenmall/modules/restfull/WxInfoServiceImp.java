package com.wk.p3.greenmall.modules.restfull;

import com.wk.p3.greenmall.common.utils.SpringContextHolder;
import com.wk.p3.greenmall.modules.info.entity.*;
import com.wk.p3.greenmall.modules.info.service.GcategoryService;
import com.wk.p3.greenmall.modules.info.service.InfoRelationService;
import com.wk.p3.greenmall.modules.info.service.UnitService;
import com.wk.p3.greenmall.modules.info.service.imp.InfoServiceImp;
import com.wk.p3.greenmall.modules.match.service.MatchService;
import com.wk.p3.greenmall.modules.match.service.impl.MatchServiceImpl;
import com.wk.p3.greenmall.modules.security.Person;
import com.wk.p3.greenmall.modules.security.SecurityService;
import com.wk.p3.greenmall.modules.security.impl.SecurityServiceImpl;
import com.wk.p3.greenmall.modules.sys.entity.User;
import com.wk.p3.greenmall.modules.sys.service.AreaService;
import com.wk.p3.greenmall.modules.sys.service.SystemService;

import com.wk.p3.greenmall.modules.sys.utils.UserUtils;
import com.wk.p3.greenmall.modules.user.UserService;
import com.wk.p3.greenmall.modules.user.entity.*;
import com.wk.p3.greenmall.modules.user.service.impl.OrganizationServiceImpl;
import io.swagger.model.*;
import io.swagger.model.Gcategory;
import io.swagger.model.Info;
import io.swagger.model.Organization;
import io.swagger.model.Unit;
import io.swagger.service.InfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liujiabao on 2016/2/1.
 */
@Service
@Transactional(readOnly = true)
public class WxInfoServiceImp implements InfoService{

    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());
    private static AreaService areaService = SpringContextHolder.getBean(AreaService.class);
    private static InfoServiceImp infoServiceImp = SpringContextHolder.getBean(InfoServiceImp.class);
    private static OrganizationServiceImpl organizationServiceImpl = SpringContextHolder.getBean(OrganizationServiceImpl.class);
    private static GcategoryService gcategoryService = SpringContextHolder.getBean(GcategoryService.class);
    private static UnitService unitService = SpringContextHolder.getBean(UnitService.class);
    private static UserService userService = SpringContextHolder.getBean(UserService.class);
    private static SecurityService securityService = SpringContextHolder.getBean(SecurityServiceImpl.class);
    private static InfoRelationService infoRelationService = SpringContextHolder.getBean(InfoRelationService.class);


    private static MatchService matchService = SpringContextHolder.getBean(MatchServiceImpl.class);

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class )
    public ResponseEntity<Void> addInfo(Info info) {
        ResponseEntity<Void> entity = null;
        try {
            InfoMessage o = new InfoMessage();
            o.setType(info.getType().name());
            o.setMessage(info.getValue());
            //user
            o.setPublishUser(securityService.currentPerson().getId());
//            if(info.getAgent()!=null) {
//                o.setIsEntrust(1);
//            }else{
                o.setIsEntrust(0);
//            }
            infoServiceImp.saveInfoMessage(o);
            entity = new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            entity = new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class )
    public ResponseEntity<Void> publishInfo(String s, Info info) {

        ResponseEntity<Void> entity = null;
        try {
            InfoMessage o = new InfoMessage();
            o.setType(info.getType().name());
            o.setMessage(info.getValue());
            //user
            o.setPublishUser(securityService.currentPerson().getId());
            if(info.getAgent()!=null) {
                o.setIsEntrust(1);
                o.setEntrustSupplyDemandId(s);
            }else{
                o.setIsEntrust(0);
            }
            infoServiceImp.saveInfoMessage(o);
            entity = new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            entity = new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return entity;

    }

    @Override
    @Transactional
    public ResponseEntity<List<Info>> guessList() {
        ResponseEntity<List<Info>> entity = null;
        Person person = securityService.currentPerson();
        if(null != person){
            String personId = person.getId();
//            System.out.println(matchService.findMatchingByUser(personId));
            List result = new ArrayList();
            Map<Double,String> map = matchService.findMatchingByUser(personId);
            for (Map.Entry<Double,String> entry : map.entrySet()) {

//                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                Map<String,Object> m = new HashMap<String, Object>();
//                Info info = getInfoById(entry.getValue());
                com.wk.p3.greenmall.modules.info.entity.Info info = infoServiceImp.get(entry.getValue());
                Info info11 = transferInfo(info);
                result.add(info11);
            }
            entity = new ResponseEntity<List<Info>>(result,HttpStatus.OK);
        }else{
            entity = new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @Override
    public ResponseEntity<List<Info>> getInfoListBySpecificInfo(Info infoId) {

        Person person ;

        ResponseEntity<List<Info>> entity = null;

        List<Info> result = new ArrayList<Info>();
        try {
            Map<Double,String> map = matchService.findMatchingBySupplyAndDemandId(infoId.getId()+"1");
            if(null!=map) {
                for (Map.Entry<Double, String> entry : map.entrySet()) {
                    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//            Map<String,Object> m = new HashMap<String, Object>();
                    com.wk.p3.greenmall.modules.info.entity.Info originInfo = infoServiceImp.get(entry.getValue());
                    if (null != originInfo) {
//                m.put("score", entry.getKey());
//                m.put("info", info);
                        Info info = transferInfo(originInfo);
                        if (null != info) {
                            result.add(info);
                        }
                    }
                }
            }
            entity = new ResponseEntity(result,HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            entity = new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        logger.info(entity.toString());

        return entity;
    }

    /*@Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<Info>> getInfoListBySpecificInfoId(String s) {
        ResponseEntity<List<Info>> entity = null;

        List<Info> result = new ArrayList<Info>();
        try {
            Map<Double,String> map = matchService.findMatchingBySupplyAndDemandId(s+"1");
            if(null!=map) {
                for (Map.Entry<Double, String> entry : map.entrySet()) {
                    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//            Map<String,Object> m = new HashMap<String, Object>();
                    com.wk.p3.greenmall.modules.info.entity.Info originInfo = infoServiceImp.get(entry.getValue());
                    if (null != originInfo) {
//                m.put("score", entry.getKey());
//                m.put("info", info);
                        Info info = transferInfo(originInfo);
                        if (null != info) {
                            result.add(info);
                        }
                    }
                }
            }
            entity = new ResponseEntity(result,HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            entity = new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
logger.info(entity.toString());

        return entity;
    }*/

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class )
    public ResponseEntity<Void> updateInfo(String s, Info info) {
        return null;
    }

    //==================================================================================================================================//
    @Override
    public ResponseEntity<Info> getInfoById(String s) {
        com.wk.p3.greenmall.modules.info.entity.Info info = infoServiceImp.get(s);
        Info info11 = transferInfo(info);
        //todo 如果是点击查看详情，还应该生成访问日志
        return new ResponseEntity<Info>(info11, HttpStatus.OK);
    }
    /*
    * 此处不捕捉异常,由Controller捕捉
    * */
    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class )
    public ResponseEntity<Void> addClickInfo(String id, Info body){
            ResponseEntity<Void> entity = null;
            InfoRelation infoRelation = new InfoRelation();
            com.wk.p3.greenmall.modules.info.entity.Info from = new com.wk.p3.greenmall.modules.info.entity.Info(id);
            com.wk.p3.greenmall.modules.info.entity.Info to = new com.wk.p3.greenmall.modules.info.entity.Info(body.getId());
            infoRelation.setFromInfo(from);
            infoRelation.setToInfo(to);
            infoRelation.setFromStatue(1);
            infoRelation.setToStatue(0);
            infoRelation.setRelation(1);
            infoRelation.setStatue(0);
            infoRelationService.save(infoRelation);
            entity = new ResponseEntity(HttpStatus.OK);
            //todo 如果需要发送消息提醒，还需添加
            return entity;
    }




    /*
    * Info转换
    * */
    public Info transferInfo(com.wk.p3.greenmall.modules.info.entity.Info info){
        Info info1 = new Info();
        if (info != null) {
            FrontUser user=userService.getUserByPersonId(info.getPublishUser());
            info1.setId(info.getId());
            if ("supply".equals(info.getType())) {
                info1.setType(Info.TypeEnum.supply);
            } else {
                info1.setType(Info.TypeEnum.demand);
            }
             /*user 开始*/
            String publishUserId = info.getPublishUser();
            if (publishUserId != null &&!"".equals(publishUserId)) {
                if (user != null) {
                    //User user
                    io.swagger.model.User user1 = new io.swagger.model.User();
                    user1.setId(publishUserId);//user ID定为personId
                    user1.setUsername(user.getName());//frontUser name
                    if ("supply".equals(info.getType())) {
                        user1.setRoleType(io.swagger.model.User.RoleTypeEnum.supply);
                    } else {
                        user1.setRoleType(io.swagger.model.User.RoleTypeEnum.demand);
                    }
                    //User user address
                    Address address = new Address();
                    address.setId(info.getAddressId());
                    address.setAddress(info.getDetailArea());
                    // //User user address area
                    Area province = new Area();
                    com.wk.p3.greenmall.modules.sys.entity.Area pro = areaService.get(info.getProvinceId());
                    province.setId(pro.getId());
                    province.setName(pro.getName());
                    province.setCode(pro.getCode());
                    province.setType(pro.getType());
                    Area city = new Area();
                    com.wk.p3.greenmall.modules.sys.entity.Area cit = areaService.get(info.getCityId());
                    city.setId(cit.getId());
                    city.setName(cit.getName());
                    city.setCode(cit.getCode());
                    city.setType(cit.getType());
                    Area area = new Area();
                    com.wk.p3.greenmall.modules.sys.entity.Area area1 = areaService.get(info.getAreaId());
                    area.setId(area1.getId());
                    area.setName(area1.getName());
                    area.setCode(area1.getCode());
                    area.setType(area1.getType());
                    address.setProvince(province);
                    address.setCity(city);
                    address.setArea(area);
                    user1.setAddress(address);
                    //user userStatus
                    try {
                        user1.setUserStatus(Integer.parseInt(user.getStatue()));
                    }catch (Exception e){
                        user1.setUserStatus(null);
                    }
                    //use List<Product> products主营产品
                    List<Product> products = new ArrayList<Product>();
                    List<Integer> ids = organizationServiceImpl.findOrganziationMainIdsGoodsbyId(user.getOrganizationId());
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
                    user1.setProducts(products);
                    //user Organization
                    Organization organization = new Organization();
                    com.wk.p3.greenmall.modules.user.entity.Organization organization1 = organizationServiceImpl.findOrganizationById(user.getOrganizationId());
                    if(organization1!=null){
                        organization.setId(organization1.getId());
                        organization.setName(organization1.getName());
                    }
                    info1.setUser(user1);
                }
            }
            /*user 结束*/
            /*agent TODO 开始*/
            /*agent 结束*/
            /*infoDetail开始*/
            InfoDetail infoDetail = new InfoDetail();
            infoDetail.setId(info.getId());
            infoDetail.setN(info.getNumber().intValue());
            Unit unit = new Unit();
            unit.setId(info.getNumberUnitId().toString());
            unit.setName(info.getNumberUnitValue());
            unit.setCode(unitService.get(info.getNumberUnitId().toString()).getCode());
            infoDetail.setUnit(unit);
            io.swagger.model.Gcategory gcategory = new io.swagger.model.Gcategory();
            com.wk.p3.greenmall.modules.info.entity.Gcategory gcategory1 = gcategoryService.get(Integer.parseInt(info.getGoodsId()));
            gcategory.setId(gcategory1.getId());
            gcategory.setCode(gcategory1.getCode());
            gcategory.setName(gcategory1.getName());
            info1.setInfoDetail(infoDetail);
            /*infoDetail 结束*/
        }
        return info1;
    }
}
