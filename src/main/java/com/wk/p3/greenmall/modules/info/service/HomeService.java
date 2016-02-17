package com.wk.p3.greenmall.modules.info.service;

import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.common.service.BaseService;
import com.wk.p3.greenmall.common.utils.DateUtils;
import com.wk.p3.greenmall.common.utils.StringUtils;
import com.wk.p3.greenmall.common.utils.UserAgentUtils;
import com.wk.p3.greenmall.modules.deal.entity.TradeOrder;
import com.wk.p3.greenmall.modules.deal.service.impl.TradeOrderService;
import com.wk.p3.greenmall.modules.info.entity.BrowseLog;
import com.wk.p3.greenmall.modules.info.entity.Gcategory;
import com.wk.p3.greenmall.modules.info.entity.Info;
import com.wk.p3.greenmall.modules.info.service.imp.InfoServiceImp;
import com.wk.p3.greenmall.modules.info.utils.BasicCategory;
import com.wk.p3.greenmall.modules.security.Person;
import com.wk.p3.greenmall.modules.security.SecurityService;
import com.wk.p3.greenmall.modules.sys.dao.UserDao;
import com.wk.p3.greenmall.modules.sys.entity.User;
import com.wk.p3.greenmall.modules.sys.service.SystemService;
import com.wk.p3.greenmall.modules.sys.utils.DictUtils;
import com.wk.p3.greenmall.modules.user.entity.FrontUser;
import com.wk.p3.greenmall.modules.user.entity.Organization;
import com.wk.p3.greenmall.modules.user.entity.UserAddressInfo;
import com.wk.p3.greenmall.modules.user.impl.UserServiceImpl;
import com.wk.p3.greenmall.modules.user.service.UserAddressInfoService;
import com.wk.p3.greenmall.modules.user.service.impl.OrganizationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhuyanqing on 2015/12/22.
 */
@Service
@Transactional(readOnly = true)
public class HomeService extends BaseService {

    @Autowired
    GcategoryService gcategoryService;
    @Autowired
    InfoServiceImp infoService;
    @Autowired
    TradeOrderService tradeOrderService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    SystemService systemService;
    @Autowired
    UserAddressInfoService userAddressInfoService;
    @Autowired
    OrganizationServiceImpl organizationService;
    @Autowired
    BrowseLogService browseLogService;
    @Autowired
    private UserDao userDao;
    @Autowired
    SecurityService securityService;



    public List<Gcategory> leftMenus() {
        Gcategory gcategory = new Gcategory();
        gcategory.setCode(BasicCategory.agricultural.getCode());
        Gcategory gy = gcategoryService.getByCode(gcategory);
        Gcategory gy2 = new Gcategory();
        gy2.setParent(gy);
        List<Gcategory> list = gcategoryService.getList(gy2);//果果、水果
        List<Gcategory> childlist = null;
        List<Gcategory> grandChildlist = null;
        for (int i = 0; i < list.size(); i++) {
            childlist = gcategoryService.getListByParent(list.get(i));
            list.get(i).setChildList(childlist);
            for (int j = 0; j < childlist.size(); j++) {
                grandChildlist = gcategoryService.getListByParent(childlist.get(j));
                childlist.get(j).setChildList(grandChildlist);
            }
        }
        return list;
    }


    public List<Gcategory> topPushSupplies() {
        List<Gcategory> list = getCategories(BasicCategory.agricultural);
        Info info = null;
        for (int i = 0; i < list.size(); i++) {
            info = new Info();
            info.setPids("," + list.get(i).getId().toString() + ",");
            info.setType("supply");
            info.setStatue(1);

            /*
            //给推荐传递的goodsType传递供求信息的pids

            StringBuffer sb = new StringBuffer("");
            String[] ids={"1","2"};
            int size = ids.length;
            if(ids.length>0) {
                for (int x = 0; x < ids.length; x++) {
                    sb.append(ids[x]);
                    if (x < ids.length-1) {
                        sb.append(",");
                    }
                }
                //info.setIgnoreIds(sb.toString());
            }

            //推荐信息不够五条的，用未推荐的补齐
            Page<Info> page = new Page<Info>();
            if(size>=0 &&size <=2){
                page.setPageSize(5-ids.length);
                //TODO 将查询出的推荐信息加入到返回的list中
            }else if(size>2){
                page.setPageSize(3);
                //TODO 截取两条推荐信息 list.subList(0,2);
            }
            */
            Page<Info> page = new Page<Info>();
            page.setPageSize(5);
            info.setPage(page);
            List<Info> infoList = infoService.findList(info);
            for (int j = 0; j < infoList.size(); j++) {
                infoList.get(j).setUpdateTime(DateUtils.getTimeDiffer(infoList.get(j).getUpdateDate()));
            }
            list.get(i).setSupplyList(infoList);
        }
        return list;
    }

    public List<Info> topPushDemands() {
        //TODO 推荐
        Info info = new Info();
        info.setType("demand");
        info.setStatue(1);
        //info.setGoodsId("22");
        Page<Info> page = new Page<Info>();
        page.setPageSize(4);
        info.setPage(page);
        List<Info> infoList = infoService.findList(info);
        for (int j = 0; j < infoList.size(); j++) {
            infoList.get(j).setUpdateTime(DateUtils.getTimeDiffer(infoList.get(j).getUpdateDate()));
        }
        return infoList;
    }

    public List<Gcategory> FirstFloorList() {
        //TODO 推荐
        List<Gcategory> list = getCategories(BasicCategory.vegetable);
        Info info = null;
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setChildList(getChildCategories(list.get(i)));
            info = new Info();
            info.setPids("," + list.get(i).getId().toString() + ",");
            info.setType("supply");
            info.setStatue(1);
            Page<Info> page = new Page<Info>();
            page.setPageSize(6);
            info.setPage(page);
            List<Info> infoList = infoService.findList(info);
            list.get(i).setSupplyList(infoList);
        }
        return list;
    }

    public List<Gcategory> SecondFloorList() {
        //TODO 推荐
        List<Gcategory> list = getGrandChildCategories(getCategory(BasicCategory.fruit));
        Info info = null;
        for (int i = 0; i < list.size(); i++) {
            info = new Info();
            info.setPids("," + list.get(i).getId().toString() + ",");
            info.setType("supply");
            info.setStatue(1);
            Page<Info> page = new Page<Info>();
            page.setPageSize(9);
            info.setPage(page);
            List<Info> supplyList = infoService.findList(info);
            list.get(i).setSupplyList(supplyList);


            info = new Info();
            info.setPids("," + list.get(i).getId().toString() + ",");
            info.setType("demand");
            info.setStatue(1);
            Page<Info> page2 = new Page<Info>();
            page2.setPageSize(6);
            info.setPage(page2);
            List<Info> demandList = infoService.findList(info);
            list.get(i).setDemandList(demandList);

        }
        return list;
    }

    public List<Gcategory> FloorList(BasicCategory bc){
        //TODO 推荐
        List<Gcategory> list = null;
        Info info = null;
        //if("chineseHerbalMedicine".equals(bc.getCode())){
            list =gcategoryService.getCategories(bc);
            for(int i=0;i<list.size();i++){
                if("moslemFood".equals(bc.getCode())){
                    list.get(i).setChildList(gcategoryService.getChildCategories(list.get(i)));
                }
                info = new Info();
                info.setPids("," + list.get(i).getId().toString() + ",");
                info.setType("supply");
                info.setStatue(1);
                Page<Info> page = new Page<Info>();
                if("moslemFood".equals(bc.getCode())){
                    page.setPageSize(6);
                }else{
                    page.setPageSize(9);
                }
                info.setPage(page);

                List<Info> supplyList = infoService.findList(info);
                list.get(i).setSupplyList(supplyList);
                for (int j = 0; j < supplyList.size(); j++) {
                    supplyList.get(j).setUpdateTime(DateUtils.getTimeDiffer(supplyList.get(j).getUpdateDate()));
                }
                info = new Info();
                info.setPids("," + list.get(i).getId().toString() + ",");
                info.setType("demand");
                info.setStatue(1);
                Page<Info> page2 = new Page<Info>();
                page2.setPageSize(6);
                info.setPage(page2);
                List<Info> demandList = infoService.findList(info);
                list.get(i).setDemandList(demandList);
                for (int j = 0; j < demandList.size(); j++) {
                    demandList.get(j).setUpdateTime(DateUtils.getTimeDiffer(demandList.get(j).getUpdateDate()));
                }
            }
       // }
        return list;
    }

    /**
     * 根据CODE枚举类得到子类别LIST
     *
     * @return
     */
    public List<Gcategory> getCategories(BasicCategory bc) {
        Gcategory gcategory = new Gcategory();
        gcategory.setCode(bc.getCode());
        Gcategory gy = gcategoryService.getByCode(gcategory);
        Gcategory gy2 = new Gcategory();
        gy2.setParent(gy);
        List<Gcategory> list = gcategoryService.getList(gy2);
        return list;
    }
    /**
     * 根据CODE枚举类得到类别Gcategory
     * @return
     */
    public Gcategory getCategory(BasicCategory bc) {
        Gcategory gcategory = new Gcategory();
        gcategory.setCode(bc.getCode());
        return gcategoryService.getByCode(gcategory);
    }

    /**
     * 得到子类别
     *
     * @return
     */
    public List<Gcategory> getChildCategories(Gcategory gy) {
        Gcategory gy2 = new Gcategory();
        gy2.setParent(gy);
        List<Gcategory> list = gcategoryService.getList(gy2);
        return list;
    }
    /**
     * 得到孙类别
     *
     * @return
     */
    public List<Gcategory> getGrandChildCategories(Gcategory gy) {
        List<Gcategory> grandList=new ArrayList<Gcategory>();
        Gcategory gy2 = new Gcategory();
        gy2.setParent(gy);
        List<Gcategory> list = gcategoryService.getList(gy2);
        for(int i=0;i<list.size();i++){
            List<Gcategory> list2 = getChildCategories(list.get(i));
            for(int j=0;j<list2.size();j++){
                grandList.add(list2.get(j));
            }
        }
        return grandList;
    }
    /**
     * 得到曾孙类别
     *
     * @return
     */
    public List<Gcategory> getGrandGrandChildCategories(Gcategory gy) {
        List<Gcategory> grandGrandList=new ArrayList<Gcategory>();
        Gcategory gy2 = new Gcategory();
        gy2.setParent(gy);
        List<Gcategory> list = gcategoryService.getList(gy2);
        for(int i=0;i<list.size();i++){
            List<Gcategory> list2 = getChildCategories(list.get(i));
            for(int j=0;j<list2.size();j++){
                List<Gcategory> list3 = getChildCategories(list2.get(j));
                for(int x=0;x<list3.size();x++){
                    grandGrandList.add(list3.get(x));
                }
            }
        }
        return grandGrandList;
    }

//    public List<TradeOrder> indexTradeState(BasicCategory bc){
//        TradeOrder to = new TradeOrder();
//        to.setCategorycode(bc.getCode());
//        return tradeOrderService.findList(to);
//    }

    public Page<Info> getMySupplies(Page<Info> page,Info info) {
        info.setPage(page);
        page.setList(infoService.findList(info));
        return page;
    }

    public List<Info> getUcenterIndexDemands(){
        Info info = new Info();
        Page<Info> page= new Page<Info>();
        page.setPageSize(5);
        info.setType("demand");
        info.setPage(page);
        //此时需要展示审核通过和洽谈中的供求信息
        info.setStatue(999);
        info.setIsFront(true);
        List<Info> infoList = infoService.findList(info);
        return infoList;
    }

    /**
     * 根据用户的ID得到其交易员（第一个交易员，可能有多个）
     * @return
     */
    public User getMiddleManByUserId(String id) {
        List list =userService.relationUser(DictUtils.getDictValue("middle_user", "user_relation_type", "middle"), id);
        User middle_man = null;
        if(list.size()>0) {
            middle_man = userDao.get(id);
        }
        return middle_man;
    }

    public List<Gcategory> findAllCata(){
        return gcategoryService.findAll();
    }


    public List<Gcategory> findSimpleList(Gcategory gcategory) throws Exception{
        List<Gcategory> list = getChildCategories(gcategory);
        Info info = null;
        for (int i = 0; i < list.size(); i++) {
            //供应列表
            info = new Info();
            info.setIsEntrust(0);
            info.setPids("," + list.get(i).getId().toString() + ",");
            info.setType("supply");
            info.setIsFront(true);
            info.setStatue(999);
            Page<Info> page = new Page<Info>();
            page.setPageSize(8);
            info.setPage(page);
            List<Info> supplyList = infoService.findList(info);
            for (int j = 0; j < supplyList.size(); j++) {
                supplyList.get(j).setUpdateTime(DateUtils.getTimeDiffer(supplyList.get(j).getUpdateDate()));
                /*FrontUser user = supplyList.get(j).getPublishUser();
                if(user != null){
                    supplyList.get(j).setUserName(userService.getUserByPersonId(user.getId()).getName());
                }*/
                if(userAddressInfoService.get(supplyList.get(j).getAddressId()) != null){
                    supplyList.get(j).setDetailArea(userAddressInfoService.get(supplyList.get(j).getAddressId()).getDetailArea());
                }
            }
            list.get(i).setSupplyList(supplyList);
            //求购列表
            info = new Info();
            info.setIsEntrust(0);
            info.setPids("," + list.get(i).getId().toString() + ",");
            info.setType("demand");
            info.setIsFront(true);
            info.setStatue(999);
            Page<Info> page2 = new Page<Info>();
            page2.setPageSize(6);
            info.setPage(page2);
            List<Info> demandList = infoService.findList(info);
            for (int j = 0; j < demandList.size(); j++) {
                demandList.get(j).setUpdateTime(DateUtils.getTimeDiffer(demandList.get(j).getUpdateDate()));
                /*FrontUser user = demandList.get(j).getPublishUser();
                if(user != null){
                    demandList.get(j).setUserName(userService.getUserByUserId(user.getId()).getName());
                }*/
                if(userAddressInfoService.get(demandList.get(j).getAddressId()) != null) {
                    demandList.get(j).setDetailArea(userAddressInfoService.get(demandList.get(j).getAddressId()).getDetailArea());
                }
            }
            list.get(i).setDemandList(demandList);
            //交易动态
            list.get(i).setTradeList(getTradeListByGcategory(list.get(i)));
            //子类别
            list.get(i).setChildList(getChildCategories(list.get(i)));
            //推荐企业
            Gcategory gy = new Gcategory();
            Page<Gcategory> page3 = new Page<Gcategory>();
            page3.setPageSize(8);
            page3.setPageNo(i + 1);
            list.get(i).setPage(page3);

            List<Organization> organizationList = organizationService.findOrganizationByMainGoodsType(list.get(i));
            List<String> ss= null;
            for(int x = 0;x<organizationList.size();x++){
                List strs = organizationService.findOrganziationMainGoodsbyId(organizationList.get(x).getId());
                if(strs!=null){
                    organizationList.get(x).setMainType(strs.toString().substring(1,strs.toString().length()-1));
                }
            }
            list.get(i).setOrganizationList(organizationList);
        }
        return list;
    }
        /*
        *
        * 参数为getShowSpec()==1或者其父类 （TODO 待扩充）
        * */
    public List<Organization> getOrgsByGcategory(Gcategory g){
        List<String> ids=new ArrayList<String>();
        if(g.getShowSpec()=="1"){
            //ids.add(g.getId());
        }else{

        }


        return null;
    }

/*
* 首页顶部展示供求信息，简单展示，不轮换
* */
    public List<Info> findSimpleInfoes(Gcategory gcategory,Info info){
        Page<Info> page = new Page<Info>();
        info.setPage(page);
        info.setPids("," + gcategory.getId().toString() + ",");
        if("supply".equals(info.getType())){
            page.setPageSize(6);
        }else{
            page.setPageSize(5);
        }
        List<Info> infoList = infoService.findList(info);
        for (int j = 0; j < infoList.size(); j++) {
            infoList.get(j).setUpdateTime(DateUtils.getTimeDiffer(infoList.get(j).getUpdateDate()));
            /*FrontUser user = infoList.get(j).getPublishUser();
            if(user != null){
                infoList.get(j).setUserName(userService.getUserByPersonId(user.getId()).getName());
            }*/
            if(userAddressInfoService.get(infoList.get(j).getAddressId()) != null) {
                infoList.get(j).setDetailArea(userAddressInfoService.get(infoList.get(j).getAddressId()).getDetailArea());
            }
        }
        return infoList;
    }


/*
* 根据类别ID取最近的交易记录
* */
    public List<Map> getTradeListByGcategory(Gcategory gcategory)  throws Exception {
        Map map = new HashMap();
        List<String> strs = tradeOrderService.queryOrderList(gcategory.getId().toString());
        List tradeList = new ArrayList();
        if("fruit".equals(gcategory.getCode()) && strs.size()>8){
            tradeList = strs.subList(0,9);
        }
        //张三,红富士,苹果,4000.0吨,4,Thu Jan 28 17:49:42 CST 2016
        //-1表示订单软删除，0取消，1表示洽谈中，2表示订单确认，3表示等待付款，4表示等待发货，5表示交易完成
        Map map2 = null;
        for(int i =0;i<strs.size();i++){
            map2 = new HashMap();
            if(strs.get(i).indexOf("null")<0){
                String[] ss = strs.get(i).split(",");
                map2.put("username",ss[0].substring(0,1));
                map2.put("pgoodsName",ss[1]);
                map2.put("goodsName",ss[2]);
                map2.put("numberAndUnit",ss[3]);
                if(ss[4].equals("-1")){
                    //map2.put("statue","已删除");
                    map2.put("statue","正在洽谈");
                }if(ss[4].equals("2")){
                    //map2.put("statue","已取消");
                    map2.put("statue","正在洽谈");
                }

                if(ss[4].equals("1")){
                    map2.put("statue","正在洽谈");
                } if(ss[4].equals("2")){
                    map2.put("statue","交易确认");
                } if(ss[4].equals("3")){
                    map2.put("statue","等待打款");
                } if(ss[4].equals("4")){
                    map2.put("statue","等待发货");
                }if(ss[4].equals("5")){
                    map2.put("statue","交易完成");
                }
                map2.put("time",DateUtils.getTimeDiffer(DateUtils.formatString(ss[5])));
                tradeList.add(map2);
            }
        }
        return tradeList;
    }


    public List<Gcategory> threeLevelMenus(Gcategory g){
        Gcategory gy2 = new Gcategory();
        gy2.setParent(g);
        List<Gcategory> list = gcategoryService.getList(gy2);
        List<Gcategory> childlist = null;
        List<Gcategory> grandChildlist = null;
        for (int i = 0; i < list.size(); i++) {
            childlist = gcategoryService.getListByParent(list.get(i));
            list.get(i).setChildList(childlist);
            for (int j = 0; j < childlist.size(); j++) {
                grandChildlist = gcategoryService.getListByParent(childlist.get(j));
                childlist.get(j).setChildList(grandChildlist);
            }
        }
        return list;
    }

    @Transactional(readOnly = false)
    public void addInfoLog(String infoId,HttpServletRequest request){
        BrowseLog browseLog = new BrowseLog();
        browseLogService.beforeSave(request, browseLog);
        browseLog.setTargetId(infoId);
        browseLog.setTargetType(1);
        //添加访问用户
        Person p = securityService.currentPerson();
        User u = systemService.currentUser();
        if(p!=null&&p.getId()!=null){
            browseLog.setUserId(p.getId());
            browseLog.setUserType(1);
        }else if(u!=null && u.getId()!=null){
            browseLog.setUserId(u.getId());
            browseLog.setUserType(2);
        }
        browseLogService.save(browseLog);
    }

    public Integer getInfoClickNum(String id){
        BrowseLog browseLog = new BrowseLog();
        browseLog.setTargetType(1);
        browseLog.setTargetId(id);
        return browseLogService.count(browseLog);
    }

    public List<Info> findSimilarInfoes(Info info, Integer count) {
        Info info1 = new Info();
        info.setIsFront(true);
        info.setStatue(999);
        info.setIsEntrust(0);
        if (info != null) {
            info.setGoodsId(info.getGoodsId());
        }
        List<Info> list = null;
        if (count != null) {
            Page<Info> page = new Page<Info>();
            page.setPageSize(count);
            page.setPageNo(1);
            info.setPage(page);
            list = infoService.findList(info);
        }
        return list;
    }

    public static void main(String[] args) {
//		@SuppressWarnings("rawtypes")
//		List<Integer> list = new ArrayList<Integer>();
//
//				list.add(1);
//				list.add(2);
//				list.add(3);
//				list.add(4);
//
//				System.out.println(list.subList(0, 2));
        //System.out.println(formatString("Thu Jan 28 17:49:42 CST 2016"));

        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("a");
        list.add("a");
        String s = ",aas,s";
        //System.out.println(list.toString().substring(1,list.toString().length()-1));
        System.out.println(s.indexOf("w"));
        System.out.println(",a,".substring(1, 2));


    }



}
