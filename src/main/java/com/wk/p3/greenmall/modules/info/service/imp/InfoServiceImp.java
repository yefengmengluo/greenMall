package com.wk.p3.greenmall.modules.info.service.imp;

import java.util.*;

import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.common.utils.DateUtils;
import com.wk.p3.greenmall.common.utils.InfoOrderStatueUtils;
import com.wk.p3.greenmall.common.utils.SpringContextHolder;
import com.wk.p3.greenmall.modules.deal.entity.TradeOrder;
import com.wk.p3.greenmall.modules.deal.entity.TradeOrderSupplyDemand;
import com.wk.p3.greenmall.modules.info.entity.*;
import com.wk.p3.greenmall.modules.info.service.*;
import com.wk.p3.greenmall.modules.info.utils.InfoUtils;
import com.wk.p3.greenmall.modules.match.service.MatchService;
import com.wk.p3.greenmall.modules.msg.MessageBusinessService;
import com.wk.p3.greenmall.modules.sys.entity.Area;
import com.wk.p3.greenmall.modules.sys.entity.Dict;
import com.wk.p3.greenmall.modules.sys.entity.User;
import com.wk.p3.greenmall.modules.sys.service.AreaService;
import com.wk.p3.greenmall.modules.match.service.impl.MatchServiceImpl;
import com.wk.p3.greenmall.modules.sys.service.DictService;
import com.wk.p3.greenmall.modules.sys.service.SystemService;
import com.wk.p3.greenmall.modules.sys.utils.DictUtils;
import com.wk.p3.greenmall.modules.sys.utils.UserUtils;
import com.wk.p3.greenmall.modules.user.entity.Organization;
import com.wk.p3.greenmall.modules.user.entity.UserAddressInfo;
import com.wk.p3.greenmall.modules.user.service.UserAddressInfoService;
import com.wk.p3.greenmall.modules.user.service.impl.OrganizationServiceImpl;
import org.codehaus.groovy.util.ListHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wk.p3.greenmall.common.service.CrudService;
import com.wk.p3.greenmall.modules.info.dao.InfoDao;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2015/12/15.
 */
@Service
@Transactional(readOnly = true)
public class InfoServiceImp extends CrudService<InfoDao,Info> implements  InfoService{


    private final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(InfoServiceImp.class);

    @Autowired
    InfoDao infoDao;
//    @Autowired
    private static InfoSpecsService infoSpecsService = SpringContextHolder.getBean(InfoSpecsService.class);
    private static GvalueService gvalueService = SpringContextHolder.getBean(GvalueService.class);
    private static GspecService gspecService = SpringContextHolder.getBean(GspecService.class);
    private static GcategoryService gcategoryService = SpringContextHolder.getBean(GcategoryService.class);
    private static UserAddressInfoService userAddressInfoService = SpringContextHolder.getBean(UserAddressInfoService.class);
    private static AreaService areaService = SpringContextHolder.getBean(AreaService.class);
    private static MatchService matchService = SpringContextHolder.getBean(MatchServiceImpl.class);
    private static UnitRelationService unitRelationService = SpringContextHolder.getBean(UnitRelationService.class);
    private static UnitService unitService = SpringContextHolder.getBean(UnitService.class);
    private static InfoUnitCategoryService infoUnitCategoryService = SpringContextHolder.getBean(InfoUnitCategoryService.class);
    private static OrganizationServiceImpl organizationServiceImpl = SpringContextHolder.getBean(OrganizationServiceImpl.class);
    private static DictService dictService = SpringContextHolder.getBean(DictService.class);
    private static MessageBusinessService messageBusinessService = SpringContextHolder.getBean(MessageBusinessService.class);
    private static InfoRelationService infoRelationService = SpringContextHolder.getBean(InfoRelationService.class);
    @Autowired
    SystemService systemService;
    @Transactional(readOnly = false)
    @Override
    public void saveInfoMessage(InfoMessage infoMessage) {

        if (infoMessage.getIsNewRecord()) {
            infoMessage.preInsert();
            dao.insertInfoMessage(infoMessage);
        } else {
            infoMessage.preUpdate();
            dao.updateInfoMessage(infoMessage);
        }

    }

    @Override
    public Integer findSupplyAndDemandCount(Info info) {
        Integer infoCount = infoDao.findSupplyAndDemandCount(info);
        return null;
    }

    public void noIntentionMatchingInfo(String id,double score){
        matchService.removeUserMatchById(id,score);
    }
    public List querySystemMatching(String id){
        List result = new ArrayList();
        Map<Double,String> map = matchService.findMatchingBySupplyAndDemandId(id);
        for (Map.Entry<Double,String> entry : map.entrySet()) {

            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            Map<String,Object> m = new HashMap<String, Object>();
            Info info = get(entry.getValue());
            if(info!=null && info.getId()!=null && !info.getId().equals("")) {
                m.put("detailId",id);
                m.put("score", entry.getKey());
                m.put("info", info);
                result.add(m);
            }
        }
        return result;
    }


    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public String saveEditInfo(Info info){
        String message = "";
        Info beforeEdit = get(info.getId());

        //判断审核状态是否变化，假如从审核前变成审核后 那么需要将审核前的数据记录下来
        Integer beforeStatue = beforeEdit.getStatue();
        Integer statue = info.getStatue();
        if(statue!=beforeStatue && statue==1 && beforeStatue==0){
            InfoMessage infoMessage = new InfoMessage();
            infoMessage.setSupplyDemandId(info.getId());
            List<InfoMessage> infoMessages = getInfoMessages(infoMessage);
            if(infoMessages.size()>0){
                InfoMessage o = infoMessages.get(0);
                o.setStatue(statue);
                o.setSupplyDemandId(info.getId());
                infoDao.updateInfoMessage(o);
            }else {
                message += recordBeforeCheckedData(beforeEdit);
            }
//            return message;
        }
        //审核不通过或者删除
        if(statue==-1 || statue==-2){
            //假如是未登陆时发布的求购信息，那么需要删除机构信息
            String organizationId = beforeEdit.getEntrustOrganizationId();
            if(null != organizationId  && !organizationId.equals("")){
                organizationServiceImpl.updateOrganization(organizationId);
            }
        }
        if(message.equals("")){
            info.setCheckRecordId(info.getId());
        }else{
            return message;
        }
        info.setCreateBy(beforeEdit.getCreateBy());
        info.setIsEntrust(beforeEdit.getIsEntrust());
        info.setEntrustSupplyDemandId(beforeEdit.getEntrustSupplyDemandId());
        info.setEntrustOrganizationId(beforeEdit.getEntrustOrganizationId());
        info.setPublishUser(beforeEdit.getPublishUser());
        info.setCreateDate(beforeEdit.getCreateDate());
        if(!info.getGoodsId().equals("") && info.getGoodsId()!=null) {
            info.setPids(gcategoryService.get(Integer.parseInt(info.getGoodsId())).getParentIds());
        }
        save(info);
        message += updateInfoSpecs(info);
        message += updateUserAddress(info);
        return message;
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public int updateDemandStatus(Info info) {

        int flag = 1;//默认成功
        try{
            int statue = info.getStatue();
            //审核不通过或者删除
            if(statue==-1 || statue==-2){
                //假如是未登陆时发布的求购信息，那么需要删除机构信息
                String organizationId = info.getEntrustOrganizationId();
                if(null != organizationId  && !organizationId.equals("")){
                    organizationServiceImpl.updateOrganization(organizationId);
                }
            }
            infoDao.updateInfoStatus(info);
        }catch(Exception e){
            flag = 0;
            logger.error(e.getMessage());
        }
        return flag;
    }


    @Override
    @Transactional(readOnly = false)
    public Map updateDemandStatueNumberByOrderStatueNumber(String demandId,int orderStatue,double orderNumber,String orderUnitCode) {

        //info信息
        Info info = get(demandId);
        //info单位id
        Integer numberUnitId = info.getNumberUnitId();
        //info单位
        InfoUnitCategory unit = infoUnitCategoryService.get(numberUnitId.toString());
        String unitCode = unit.getUnitCode();
        Double number = info.getNumber();

        String message = "";
        int code = 1;

        Map m = new HashMap();

        Double errand = 0.0;
        if(unitCode==orderUnitCode){//单位相同
            errand = number-orderNumber;
            if(errand<0){
                errand = 0.0;
            }

        }else{//单位不同  需要单位转换
            UnitRelation unitRelation = new UnitRelation();
            unitRelation.setFromUnitCode(orderUnitCode);
            unitRelation.setToUnitCode(unitCode);
            List<UnitRelation> unitRelations = unitRelationService.findList(unitRelation);
            if(unitRelations.size()<1){
                message = "单位'"+orderUnitCode+"'与单位'"+unitCode+"'的转化关系没有维护，请先进行维护！";
                code = 0;
                m.put("code",code);
                m.put("message",message);
                return m;
            }else{
                double multiplier = unitRelations.get(0).getMultiplier();
                errand = number-orderNumber*multiplier;
                if(errand<0){
                    errand = 0.0;
                }
            }
        }

        info.setNumber(errand.toString());
        int infoStatue = InfoOrderStatueUtils.getInfoStatueByOrderStatue(orderStatue,errand);
        info.setStatue(infoStatue);
        try {
            save(info);
        }catch (Exception e){
            logger.error(e.getMessage());
            code = 0;
            message = e.getMessage();
        }
        m.put("code",code);
        m.put("message",message);

        return m;
    }
    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public int updateSupplyStatus(Info info) {

        int flag = 1;//默认成功
        try{
            infoDao.updateInfoStatus(info);
        }catch(Exception e){
            flag = 0;
            logger.error(e.getMessage());
        }
        return flag;
    }


    @Override
    public Long getDemandCount(Info info) {
        return null;
    }

    @Override
    public List<Info> getDemand(Info info) {
        info.setType("demand");
        return infoDao.getInfos(info);
    }

    @Override
    public Long getSupplyCount(Info info) {
        return null;
    }

    @Override
    public List<Info> getSupply(Info info) {

        info.setType("supply");
        return infoDao.getInfos(info);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Info> getSupplyPage(Page<Info> page, Info entity) {
        entity.setPage(page);
        entity.setType("supply");
        page.setList(infoDao.getInfos(entity));
        return page;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Info> getDemandPage(Page<Info> page, Info entity) {
        entity.setPage(page);
        entity.setType("demand");
        page.setList(infoDao.getInfos(entity));
        return page;
    }
    @Override
    @Transactional(readOnly = true)
    public Page<InfoMessage> getInfoMessagePage(Page<InfoMessage> page, InfoMessage entity) {
        entity.setPage(page);
        page.setList(infoDao.getInfoMessages(entity));
        return page;
    }

    public List<InfoMessage> getInfoMessages(InfoMessage infoMessage){
        return infoDao.getInfoMessages(infoMessage);
    }
    /**
     * 获取单条数据
     * 纯信息
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public InfoMessage getInfoMessage(String id) {
        return dao.getInfoMessage(id);
    }

    /**
     * 删除纯信息数据
     *
     * @param entity
     */
    @Transactional(readOnly = false)
    public void deleteInfoMessage(InfoMessage entity) {
        dao.deleteInfoMessage(entity);
    }

    @Override
    public Page<Info> getCheckedDemandPage(Page<Info> page, Info entity) {
        entity.setPage(page);
        entity.setType("demand");
        page.setList(infoDao.getCheckedInfos(entity));
        return page;
    }

    /**
     * 得到 info的规格信息
     * @param info
     * @return
     */
    public String  getCheckedSpecsByInfo(Info info){
        String specs = info.getSpecs();
        List<InfoSpecs> infoSpecses = infoSpecsService.getInfoSpecsByInfoId(info.getId());
        int i=0;
        String checkedSpecs = "";
        for(InfoSpecs infoSpecs:infoSpecses){
            if(i==0){
                checkedSpecs += infoSpecs.getSpecValue();
            }else{
                checkedSpecs += ","+infoSpecs.getSpecValue();
            }
            i++;
        }
        if(checkedSpecs.equals("")){
            checkedSpecs = specs;
        }else{
            if(specs!=null && !specs.equals("")) {
                checkedSpecs += ","+specs;
            }
        }
        return checkedSpecs;
    }

    /**
     * 得到 收发货的地址
     * @param info
     * @param addressId
     * @return
     */
    public Info getCheckedInfoArea(Info info,String addressId){
        UserAddressInfo userAddressInfo = userAddressInfoService.get(addressId);
        if (userAddressInfo != null) {
            info.setProvinceId(userAddressInfo.getProvinceId());
            info.setProvinceName(userAddressInfo.getProvinceName());
            info.setCityId(userAddressInfo.getCityId());
            info.setCityName(userAddressInfo.getCityName());
            info.setAreaId(userAddressInfo.getAreaId());
            info.setAreaName(userAddressInfo.getAreaName());
            info.setDetailArea(userAddressInfo.getDetailArea());
        }
        return info;
    }

    /**
     * 委托 审核前后求购信息 查询
     * @param page 分页对象
     * @param entity
     * @return
     */
    @Override
    public Page<Info> getEntrustDemandPage(Page<Info> page, Info entity) {
        entity.setPage(page);
        List<Info> infos = infoDao.getEntrustDemandPage(entity);
        for(Info info:infos){
            //得到规格信息
            Info entrustSupplyDemand = info.getEntrustSupplyDemand();
            if(entrustSupplyDemand!=null) {
                String entrustSupplyDemandId = entrustSupplyDemand.getEntrustSupplyDemandId();
                if (entrustSupplyDemandId != null && !entrustSupplyDemandId.equals("")) {
                    String checkedSpecs = getCheckedSpecsByInfo(entrustSupplyDemand);
                    entrustSupplyDemand.setCheckedSpecs(checkedSpecs);
                }

                //收发货地址的赋值
                String addressId = entrustSupplyDemand.getAddressId();
                if (addressId != null && !addressId.equals("")) {
                    entrustSupplyDemand = getCheckedInfoArea(entrustSupplyDemand,addressId);
                }
            }
        }

        page.setList(infos);
        return page;
    }






    /**
     * 委托 审核前供应信息 查询
     * @param page 分页对象
     * @param entity
     * @return
     */
    @Override
    public Page<Info> getEntrustSupplyPage(Page<Info> page, Info entity) {
        entity.setPage(page);
        List<Info> infos = infoDao.getEntrustSupplyPage(entity);
        for(Info info:infos){
            //得到规格信息
            Info entrustSupplyDemand = info.getEntrustSupplyDemand();
            if(entrustSupplyDemand!=null) {
                String entrustSupplyDemandId = entrustSupplyDemand.getEntrustSupplyDemandId();
                if (entrustSupplyDemandId != null && !entrustSupplyDemandId.equals("")) {
                    String checkedSpecs = getCheckedSpecsByInfo(entrustSupplyDemand);
                    entrustSupplyDemand.setCheckedSpecs(checkedSpecs);
                }

                //收发货地址的赋值
                String addressId = entrustSupplyDemand.getAddressId();
                if (addressId != null && !addressId.equals("")) {
                    entrustSupplyDemand = getCheckedInfoArea(entrustSupplyDemand,addressId);
                }
            }
        }

        page.setList(infos);
        return page;
    }




    @Override
    public List<Info> getCheckedDemand(Info info) {
        info.setType("demand");
        return infoDao.getCheckedInfos(info);
    }
    @Override
    public Page<Info> getCheckedSupplyPage(Page<Info> page, Info entity) {
        entity.setPage(page);
        entity.setType("supply");
        page.setList(infoDao.getCheckedInfos(entity));
        return page;
    }

    @Override
    public List<Info> getCheckedSupply(Info info) {
        info.setType("supply");
        return infoDao.getCheckedInfos(info);
    }
    public List<Info> findInfoTable(Info info){
        return dao.findInfoTable(info);
    }
    public long findInfoCount(Info info){
        return dao.findInfoCount(info);
    }


    /**
     * 更新规格信息
     * @param info
     * @return
     */
    @Transactional(readOnly = false)
    public String updateInfoSpecs(Info info){

        String checkedSpecsId = info.getCheckedSpecsId();
        String message = "";
        String[] checkedSpecsIds = null;
        //删除所有
        infoSpecsService.deleteByInfoId(info.getId());
        if(checkedSpecsId !=null && !checkedSpecsId.trim().equals("")) {
            checkedSpecsIds = checkedSpecsId.trim().split(",");

            try {

                for(int i=0;i<checkedSpecsIds.length;i++){
                    logger.info(checkedSpecsIds[i]+"00");
                    //插入
                    logger.info(checkedSpecsIds[i].trim());
                    Integer spec_value_id = Integer.parseInt(checkedSpecsIds[i].trim());
                    InfoSpecs o1 = new InfoSpecs();
                    o1.setSupplyDemandId(info.getId());
                    o1.setSpecValueId(spec_value_id);
                    List<InfoSpecs> os = infoSpecsService.getInfoSpecsByInfoIdAndSpecs(o1);
                    Gvalue gvalue = gvalueService.get(spec_value_id);
                    Integer gspecId = gvalue.getGspec().getId();
                    Gspec gspec = gspecService.get(gspecId);
                    Integer gcategoryId = gvalue.getGcategory().getId();
                    Gcategory gcategory = gcategoryService.get(gcategoryId);
                    gvalue.setGspec(gspec);
                    gvalue.setGcategory(gcategory);


                    InfoSpecs infoSpecs = new InfoSpecs();
                    infoSpecs.setSpecValue(gvalue.getVal());
                    infoSpecs.setSpecName(gspec.getName());
                    infoSpecs.setSpecValueId(spec_value_id);
                    infoSpecs.setSupplyDemandId(info.getId());
                    infoSpecs.setGcategoryId(gcategoryId);
                    infoSpecs.setSpecId(gspecId);
                    infoSpecs.setCreateBy(UserUtils.getUser());
                    infoSpecs.setUpdateBy(UserUtils.getUser());


                    if (os.size() > 0) {
                        infoSpecs.setId(os.get(0).getId());
                        //更新
                        infoSpecsService.save(infoSpecs);
                    } else {
                        //新增
                        infoSpecsService.save(infoSpecs);
                    }



                }
            }catch (Exception e){
                e.printStackTrace();
                logger.error(e.getMessage());
                message = e.getMessage();
            }
        }
        return message;
    }
    /**
     * 更新收发货人员 地址信息
     * @param info
     * @return
     */
    @Transactional(readOnly = false)
    public String updateUserAddress(Info info){

        String addressId = info.getAddressId();
        UserAddressInfo userAddressInfo = userAddressInfoService.get(addressId);
        if(userAddressInfo==null){
            userAddressInfo = new UserAddressInfo();
        }
        userAddressInfo.setUserName(info.getUserName());
        userAddressInfo.setTelephone(info.getTelephone());
        userAddressInfo.setProvinceId(info.getProvinceId());
        userAddressInfo.setCityId(info.getCityId());
        userAddressInfo.setAreaId(info.getAreaId());
        if(info.getProvinceName() == null){
            userAddressInfo.setProvinceName(areaService.get(info.getProvinceId()).getName());
        }else {
            userAddressInfo.setProvinceName(info.getProvinceName());
        }
        if(info.getCityName() == null){
            userAddressInfo.setCityName(areaService.get(info.getCityId()).getName());
        }else {
            userAddressInfo.setCityName(info.getCityName());
        }
        if(info.getAreaName() == null){
            userAddressInfo.setAreaName(areaService.get(info.getAreaId()).getName());
        }else {
            userAddressInfo.setAreaName(info.getAreaName());
        }
        userAddressInfo.setDetailArea(info.getDetailArea());
        userAddressInfo.setUpdateBy(UserUtils.getUser());
        String message = "";
        try {
            userAddressInfoService.save(userAddressInfo);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            message = e.getMessage();
        }

        return message;
    }


    /**
     * 新增info信息,设置INFO pids,
     * @param info
     * @return
     */
    @Transactional(readOnly = false,rollbackFor =Exception.class )
    public String saveInfo(Info info) {
        String str = null;
//        try {
            UserAddressInfo uai = new UserAddressInfo();
            uai.setProvinceId(info.getProvinceId());
            uai.setCityId(info.getCityId());
            uai.setAreaId(info.getAreaId());
            uai.setProvinceName(areaService.get(info.getProvinceId()).getName());
            uai.setCityName(areaService.get(info.getCityId()).getName());
            uai.setAreaName(areaService.get(info.getAreaId()).getName());
            uai.setDetailArea(info.getDetailArea());
            uai.setTelephone(info.getTelephone());

            if(info.getPublishUser() != null && !"".equals(info.getPublishUser())){
                uai.setUserId(info.getPublishUser());
                uai.setUserName(info.getUserName());
            }else {
                uai.setUserName(info.getUserName());
            }
            uai.setType(info.getType());
            //邮政编码，是否为默认地址，暂时不做处理 TODO
            String id = userAddressInfoService.saveAndReturnId(uai);

            Integer statue = info.getStatue();
            if(statue==null){
                statue = 0;
            }
            info.setAddressId(id);
            info.setStatue(statue);

//            Area province = areaService.get(info.getProductionProvince()) ;
//            info.setProductionProvince();

            info.setPids(gcategoryService.get(Integer.parseInt(info.getGoodsId())).getParentIds());

            String infoId = saveAndReturnId(info);
            //保存规格信息
            updateInfoSpecs(info);
            str = infoId;
            //保存图片
            if(info.getImageUrls()!=null) {
                int size = info.getImageUrls().size();
                if (size > 0) {
                    InfoImage infoImage = null;
                    for (int i = 0; i < size; i++) {
                        infoImage = new InfoImage();
                        infoImage.setInfoId(infoId);
                        infoImage.setPath(info.getImageUrls().get(i));
                        saveInfoImage(infoImage);
                    }
                }
            }
        //如果审核通过而且是委托的话，产生infoRelation
        if(info.getStatue()==1 && info.getIsEntrust()==1){
            InfoRelation infoRelation=new InfoRelation();
            Info from = new Info(infoId);
            Info to = new Info(info.getEntrustSupplyDemandId());
            infoRelation.setFromInfo(from);
            infoRelation.setToInfo(to);
            infoRelation.setFromStatue(1);
            infoRelation.setToStatue(0);
            infoRelation.setRelation(1);
            infoRelation.setStatue(0);
            infoRelationService.save(infoRelation);
        }
//        }catch (Exception e){
//            str = null;
//            logger.error(e.getMessage());
//        }

        return str;
    }
/*
* 保存图片
* */
    @Transactional(readOnly = false,rollbackFor =Exception.class )
    public void saveInfoImage(InfoImage infoImage) {
        if (infoImage.getIsNewRecord()) {
            infoImage.preInsert();
            dao.saveInfoImage(infoImage);
        }else{

        }
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void delImgsCauseExcep(List<String> strs,HttpServletRequest request) {
        if (strs.size() > 0) {
            for(int i =0;i<strs.size();i++){
                InfoUtils.delFile(strs.get(i),request);
            }
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void updateInfoMessage(InfoMessage infoMessage){
        infoDao.updateInfoMessage(infoMessage);
    }


    @Transactional(readOnly = false)
    public String recordBeforeCheckedData(Info info){

        String message = "";
        try {
            infoDao.recordBeforeCheckedData(info);
        }catch (Exception e){
            message = e.getMessage();
            logger.error(e.getMessage());
        }
        return message;


    }

    @Transactional(readOnly = false)
    public String toSendNews(String type,Map<String,String> map){


        String message="";
        Map<String,String> model = new HashMap<String, String>();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            List<String> list = new ArrayList<String>();
            list.add(entry.getKey());
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            model.put("message", entry.getValue());

            String code = DictUtils.getDictValue(type, "MsgCode", "");
            Dict dict = new Dict();
            dict.setType("web_name");
            Dict o = dictService.findList(dict).get(0);

            model.put("webName", o.getLabel());
            try {
                messageBusinessService.sendMatchSMSMessage(code, list, model);
            }catch (Exception e){
                logger.error(e.getMessage());
                message += "发送信息失败";
            }
        }




        return message;
    }

    @Transactional(readOnly = false)
    public String sendSelectNews(String ids){

        String message = "";
        String[] idArrays = ids.split(";");
        String type="";

        Map<String,String> map = new HashMap<String,String>();
        for(String id:idArrays){
            Info info = get(id);
            type=info.getType();

            String typeStr = "求购";
            if(type=="supply"){
                typeStr="供应";
            }

            String m = "";
//            举例：【果果网】01月20日-02月23日 采购红富士苹果苹果 2T 刘玲：18600642261 sdt/aaa.cn;05月22日-09月28日 采购红富士苹果苹果 2T 刘海：13865215321 sdt/aa.cn;
            m+= DateUtils.fmtDate(info.getValidateStartDate(),"yyyy年MM月dd日")+"-"+DateUtils.fmtDate(info.getValidateEndDate(), "yyyy年MM月dd日") +" "+typeStr+ info.getGoodsName()+info.getPgoodsName()+" "+info.getNumber()+info.getNumberUnitValue()+" "+info.getUserName()+
                    "："+info.getTelephone();
            if(null == map.get(info.getTelephone())) {
                map.put(info.getTelephone(), m);
            }else{
                String mm = map.get(info.getTelephone());
                mm += "；"+m;
                map.put(info.getTelephone(),mm);
            }

//            list.add(info.getTelephone());//info.getTelephone()  "18911903691"

        }
        toSendNews(type, map);
        return message;
    }

    @Transactional(readOnly = false)
    public String updateInfo(Info info){
        String message = "";
        try {
            updateUserAddress(info);
            Info info1 = get(info.getId());
            UserAddressInfo userAddressInfo = userAddressInfoService.get(info.getAddressId());
            info1.setProvinceId(userAddressInfo.getProvinceId());
            info1.setProvinceName(userAddressInfo.getProvinceName());
            info1.setCityId(userAddressInfo.getCityId());
            info1.setCityName(userAddressInfo.getCityName());
            info1.setAreaId(userAddressInfo.getAreaId());
            info1.setAreaName(userAddressInfo.getAreaName());
            info1.setDetailArea(userAddressInfo.getDetailArea());
            info1.setUserName(userAddressInfo.getUserName());
            info1.setTelephone(userAddressInfo.getTelephone());
            info1.setRemarks(info.getRemarks());
            info1.setValidateStartDate(DateUtils.formatDateTime(info.getValidateStartDate()));
            info1.setValidateEndDate(DateUtils.formatDateTime(info.getValidateEndDate()));
            info1.setGoodsId(info.getGoodsId());
            info1.setGoodsName(info.getGoodsName());
            info1.setPgoodsId(info.getPgoodsId());
            info1.setPgoodsName(info.getPgoodsName());
            info1.setNumber(info.getNumber().toString());
            info1.setNumberUnitId(info.getNumberUnitId().toString());
            info1.setNumberUnitValue(info.getNumberUnitValue());
            info1.setNonstandardNumber(info.getNonstandardNumber());
            info1.setNonstandardNumberUnitId(info.getNonstandardNumberUnitId());
            info1.setNonstandardNumberUnitName(info.getNonstandardNumberUnitName());
            save(info1);
        }catch (Exception e){
            message = e.getMessage();
            logger.error(e.getMessage());
        }
        return message;


    }
    @Transactional(readOnly = false,rollbackFor =Exception.class )
    public void saveEntrustFindGoods(Organization organization,Info info,String[] mainGoods)throws Exception{
        saveInfo(info);
        organizationServiceImpl.prefectUser(organization,mainGoods);
    }
/*
* 获得当前前台用户的详情 TODO
* */
    public User getCurrentUserInfo(){
        User user = systemService.currentUser();
        UserAddressInfo address = new UserAddressInfo();
        address.setUserId(user.getId());
        address.setIsDefalt(1);
        List<UserAddressInfo> list = userAddressInfoService.findList(address);
        user.setAddresses(list);
        return user;
    }

    /**
     * 根据infoID获得图片
     * @param id
     * @return
     */
    public List<String> getImagesByInfoId(String id){
        return infoDao.getImagesByInfoId(id);
    }

}
