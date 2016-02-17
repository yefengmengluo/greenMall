package com.wk.p3.greenmall.modules.info.web;

import com.alibaba.fastjson.JSONObject;
import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.common.utils.DateUtils;
import com.wk.p3.greenmall.common.utils.StringUtils;
import com.wk.p3.greenmall.common.web.BaseController;
import com.wk.p3.greenmall.modules.cms.entity.Article;
import com.wk.p3.greenmall.modules.cms.service.ArticleDataService;
import com.wk.p3.greenmall.modules.cms.service.ArticleService;
import com.wk.p3.greenmall.modules.deal.entity.TradeOrder;
import com.wk.p3.greenmall.modules.deal.service.impl.TradeOrderService;
import com.wk.p3.greenmall.modules.info.entity.Gcategory;
import com.wk.p3.greenmall.modules.info.entity.Info;
import com.wk.p3.greenmall.modules.info.entity.InfoMessage;
import com.wk.p3.greenmall.modules.info.service.BrowseLogService;
import com.wk.p3.greenmall.modules.info.service.GcategoryService;
import com.wk.p3.greenmall.modules.info.service.HomeService;
import com.wk.p3.greenmall.modules.info.service.imp.InfoServiceImp;
import com.wk.p3.greenmall.modules.info.utils.BasicCategory;
import com.wk.p3.greenmall.modules.info.utils.InfoUtils;
import com.wk.p3.greenmall.modules.security.Person;
import com.wk.p3.greenmall.modules.security.SecurityService;
import com.wk.p3.greenmall.modules.sys.entity.Area;
import com.wk.p3.greenmall.modules.sys.entity.User;
import com.wk.p3.greenmall.modules.sys.service.AreaService;
import com.wk.p3.greenmall.modules.sys.service.SystemService;
import com.wk.p3.greenmall.modules.sys.utils.DictUtils;
import com.wk.p3.greenmall.modules.sys.utils.UserUtils;
import com.wk.p3.greenmall.modules.user.UserService;
import com.wk.p3.greenmall.modules.user.entity.FrontUser;
import com.wk.p3.greenmall.modules.user.entity.Organization;
import com.wk.p3.greenmall.modules.user.entity.OrganizationType;
import com.wk.p3.greenmall.modules.user.impl.UserServiceImpl;
import com.wk.p3.greenmall.modules.user.service.impl.OrganizationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhuyanqing on 2015/12/22.
 */
@Controller
@RequestMapping(value = "${frontPath}")
public class HomeController extends BaseController {

    @Autowired
    HomeService homeService;
    @Autowired
    TradeOrderService tradeOrderService;
    @Autowired
    InfoServiceImp infoService;
    @Autowired
    OrganizationServiceImpl organizationServiceImpl;
    @Autowired
    GcategoryService gcategoryService;
    @Autowired
    UserService userService;
    @Autowired
    SystemService systemService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleDataService articleDataService;
    @Autowired
    OrganizationServiceImpl organizationService;
    @Autowired
    BrowseLogService browseLogService;
    @Autowired
    SecurityService securityService;


//    @ModelAttribute("info")
//    public Info get(@RequestParam(required=false) String id) {
//        if (StringUtils.isNotBlank(id)){
//            try{
//                return infoService.get(id);
//            }catch(Exception e){
//                return new Info();
//            }
//        }else{
//            return new Info();
//        }
//    }

//    @ModelAttribute("to")
//    public TradeOrder get(@RequestParam(required=false) String id) {
//        if (StringUtils.isNotBlank(id)){
//            try{
//                return tradeOrderService.get(id);
//            }catch(Exception e){
//                return new TradeOrder();
//            }
//        }else{
//            return new TradeOrder();
//        }
//    }

    /**
     * 网站web端首页(果果网)
     */
    @RequestMapping(value = {"index2"})
    public String index2(Model model) {
        //首页左侧导航栏
        List<Gcategory> list = homeService.leftMenus();
        //顶部推荐的供应信息
        List<Gcategory> topPushSupplies = homeService.topPushSupplies();
        //顶部推荐的求购信息
        List<Info> topPushDemands = homeService.topPushDemands();
        //首页1F的果果种类、供应信息列表
        List<Gcategory> FirstFloorList = homeService.FirstFloorList();
        //首页2F的果品种类、供应信息列表
        List<Gcategory> SecondFloorList = homeService.SecondFloorList();

        model.addAttribute("list", list);
        model.addAttribute("topPushSupplies", topPushSupplies);
        model.addAttribute("topPushDemands", topPushDemands);
        model.addAttribute("FirstFloorList", FirstFloorList);
        model.addAttribute("SecondFloorList", SecondFloorList);

        return "modules/info/home/index";
    }

    /**
     * 网站web端首页ThirdFloor data
     */
    @ResponseBody
    @RequestMapping(value = {"thirdFloor"})
    public Map thirdFloor(Model model) {
        Map map = new HashMap();
        List<Gcategory> thirdList = homeService.FloorList(BasicCategory.chineseHerbalMedicine);
        map.put("thirdList", thirdList);
        return map;
    }

    /**
     * 网站web端首页ForthFloor data
     */
    @ResponseBody
    @RequestMapping(value = {"forthFloor"})
    public Map ForthFloor(Model model) {
        Map map = new HashMap();
        List<Gcategory> forthList = homeService.FloorList(BasicCategory.moslemFood);
        map.put("forthList", forthList);
        return map;
    }

    /**
     * 网站web端首页右侧交易动态列表
     */
    @ResponseBody
    @RequestMapping(value = {"indexTradeState"})
    public Map indexTradeState(Model model, String code) {
        Map map = new HashMap();
        try {
            Gcategory gcategory = new Gcategory();
            gcategory.setCode(code);
            String id = gcategoryService.getByCode(gcategory).getId().toString();
            List<String> strs = tradeOrderService.queryOrderList(id);
            List tradeList = new ArrayList();

            if ("fruit".equals(code) && strs.size() > 8) {
                tradeList = strs.subList(0, 9);
            }
            if ("chineseHerbalMedicine".equals(code) && strs.size() > 8) {
                tradeList = strs.subList(0, 9);
            }
            if ("moslemFood".equals(code) && strs.size() > 8) {
                tradeList = strs.subList(0, 9);
            }
            //张三,红富士,苹果,4000.0吨,4,Thu Jan 28 17:49:42 CST 2016
            //-1表示订单软删除，0取消，1表示洽谈中，2表示订单确认，3表示等待付款，4表示等待发货，5表示交易完成
            Map map2 = null;
            for (int i = 0; i < strs.size(); i++) {
                map2 = new HashMap();
                if (strs.get(i).indexOf("null") < 0) {
                    String[] ss = strs.get(i).split(",");
                    map2.put("username", ss[0]);
                    map2.put("pgoodsName", ss[1]);
                    map2.put("goodsName", ss[2]);
                    map2.put("numberAndUnit", ss[3]);
                    if (ss[4].equals("-1")) {
                        //map2.put("statue","已删除");
                        map2.put("statue", "正在洽谈");
                    }
                    if (ss[4].equals("2")) {
                        //map2.put("statue","已取消");
                        map2.put("statue", "正在洽谈");
                    }

                    if (ss[4].equals("1")) {
                        map2.put("statue", "正在洽谈");
                    }
                    if (ss[4].equals("2")) {
                        map2.put("statue", "交易确认");
                    }
                    if (ss[4].equals("3")) {
                        map2.put("statue", "等待打款");
                    }
                    if (ss[4].equals("4")) {
                        map2.put("statue", "等待发货");
                    }
                    if (ss[4].equals("5")) {
                        map2.put("statue", "交易完成");
                    }
                    map2.put("time", DateUtils.getTimeDiffer(DateUtils.formatString(ss[5])));
                    tradeList.add(map2);
                }
            }
            map.put("tradeList", tradeList);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("tradeList", null);
        }
        return map;

    }


    /**
     * 网站web端用户中心（登录进入）
     */
    @RequestMapping(value = {"userCenter"})
    public String userCenter(Model model, TradeOrder to) {
        FrontUser user = userService.getFrontUser();
        if (user == null) {
            return "modules/user/loginForm";
        }
        Organization org = null;
        if (user != null && user.getOrganizationId() != null) {
            org = organizationServiceImpl.findOrganizationById(user.getOrganizationId());
        }
        User middleMan = userService.findBussinessUserByFrontUser(userService.getFrontUser().getId());
        Info info = new Info();
        info.setType("supply");
        info.setIsFront(true);
        info.setStatue(999);
        info.setPublishUser(securityService.currentPerson().getId());
        long supNum=infoService.findInfoCount(info);
        info.setType("demand");
        long demNum=infoService.findInfoCount(info);
        List<Info> newDemands = homeService.getUcenterIndexDemands();
        model.addAttribute("middle_man", middleMan);
        model.addAttribute("org", org);
        model.addAttribute("user", user);
        model.addAttribute("newDemands", newDemands);
        model.addAttribute("supNum", supNum);
        model.addAttribute("demNum", demNum);

        //return SystemService.currentUser()==null?"modules/sys/sysLogin":"modules/info/home/userCenter";
        return user == null ? "modules/user/loginForm" : "modules/info/home/userCenter2";
    }

    /**
     * 网站web端用户中心/卖家中心/我委托的供应
     */
    @RequestMapping(value = {"myEntrustSupplies"})
    public String myEntrustSupplies(Info info, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page page = new Page<Info>(request, response);
        page.setPageSize(4);
        //Info info= new Info();
        info.setPublishUser(securityService.currentPerson().getId());
        info.setStatue(null);
        info.setType("supply");
        info.setIsEntrust(1);
        info.setPage(page);
        info.setIsFront(true);
        User middleMan = userService.findBussinessUserByFrontUser(userService.getFrontUser().getId());
        Page<Info> pg = infoService.getEntrustSupplyPage(page, info);
        model.addAttribute("page", pg);
        model.addAttribute("middleMan", middleMan);
        return "modules/info/home/myEntrustSupplies";
    }

    /**
     * 网站web端用户中心/卖家中心/我委托的供应
     */
    @RequestMapping(value = {"mySupplies2"})
    public String mySupplies2(Info info, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page page = new Page<Info>(request, response);
        page.setPageSize(4);
        //Info info= new Info();
        info.setPublishUser(securityService.currentPerson().getId());
        info.setStatue(null);
        info.setType("supply");
        info.setPage(page);
        info.setIsFront(true);
        User middleMan = homeService.getMiddleManByUserId(userService.getFrontUser().getId());
        Page<Info> pg = infoService.findPage(page, info);
        for(int i=0;i<pg.getList().size();i++){
            if(pg.getList().get(i).getEntrustSupplyDemandId()!=null){
                pg.getList().get(i).setEntrustSupplyDemand(infoService.get((pg.getList().get(i).getEntrustSupplyDemandId())));
            }
        }
        model.addAttribute("page", pg);
        model.addAttribute("middleMan", middleMan);
        return "modules/info/home/mySupplies2";
    }

    /**
     * 网站web端用户中心/卖家中心/我的供应
     */
    @RequestMapping(value = {"mySupplies"})
    public String mySupplies(Info info, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page page = new Page<Info>(request, response);
        page.setPageSize(4);
        info.setPublishUser(securityService.currentPerson().getId());
        info.setStatue(null);
        info.setType("supply");
        info.setIsFront(true);
        Page<Info> pg = infoService.findPage(page, info);
        model.addAttribute("page", pg);
        return "modules/info/home/mySupplies";
    }


    /**
     * 网站web端用户中心/卖家中心/发布供应 （登录进入）
     */
    @RequestMapping(value = {"publishSupplies"})
    public String publishSupplies(Model model, Info info) {
        List<Gcategory> list = homeService.leftMenus();
        model.addAttribute("list", list);
        return "modules/info/home/publishSupplies";
    }

    /**
     * 网站web端用户中心/卖家中心/发布供应 （登录进入）
     */
    @RequestMapping(value = {"agreement"})
    public String agreement(Model model) {
        Article article = new Article();
        article.setTitle("果果网用户协议");
        Article article1 = articleService.findAllList(article).get(0);
        article1.setArticleData(articleDataService.get(article1.getId()));
        model.addAttribute("article", article1);
        return "modules/info/home/agreement";
    }

    /**
     * 网站web端用户中心/卖家中心/发布采购 （登录进入）
     */
    @RequestMapping(value = {"publishDemands"})
    public String publishDemands(Model model, Info info) {
        List<Gcategory> list = homeService.leftMenus();
        model.addAttribute("list", list);
        return "modules/info/home/publishDemands";
    }

    /**
     * 网站web端用户中心/卖家中心/我的采购 （登录进入）
     */
    @RequestMapping(value = {"myDemands"})
    public String myDemands(Info info, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page page = new Page<Info>(request, response);
        page.setPageSize(4);
        info.setPublishUser(securityService.currentPerson().getId());
        info.setStatue(null);
        info.setType("demand");
        info.setIsFront(true);
        Page<Info> pg = infoService.findPage(page, info);
        model.addAttribute("page", pg);
        return "modules/info/home/myDemands";
    }

    /**
     * 网站web端用户中心/卖家中心/我的采购 （登录进入）
     */
    @RequestMapping(value = {"myDemands2"})
    public String myDemands2(Info info, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page page = new Page<Info>(request, response);
        page.setPageSize(4);
        info.setPublishUser(securityService.currentPerson().getId());
        info.setType("demand");
        info.setIsFront(true);
        Page<Info> pg = infoService.getCheckedDemandPage(page, info);
        model.addAttribute("page", pg);
        model.addAttribute("info", info);
        return "modules/info/home/myDemands2";
    }

    /**
     * 网站web端用户中心/卖家中心/我的报价/修改 （登录进入）
     */
    @RequestMapping(value = {"editSupply"})
    public String editSupply(Model model, Info info) {
        Map map = new HashMap();

        List<Gcategory> list = homeService.leftMenus();
        model.addAttribute("list", list);

        Info info2 = infoService.get(info.getId());

        model.addAttribute("info", info2);
        return "modules/info/home/editSupply";
    }


    /**
     * 网站web端 采购
     */
    @RequestMapping(value = {"entrustFindGoods"})
    public String entrustFindGoods(Model model, String infoId) {
        List<Gcategory> list = homeService.leftMenus();
        List<Gcategory> gcategoryList = homeService.findAllCata();
        model.addAttribute("list", list);
        model.addAttribute("gcategoryList", gcategoryList);
        if (SystemService.currentUser() != null) {
            if (infoId != null) {
                return "redirect:" + adminPath + "/info/toEntrustFromExistInfo?existInfoId=" + infoId;
            } else {
                return "modules/info/home/entrustFindGoods";
            }
        } else {
            //Info info = infoService.get(infoId);
            model.addAttribute("infoId", infoId);
            return "modules/info/home/entrustFindGoods";
        }
    }

    /**
     * 网站web端匿名 报价
     */
    @RequestMapping(value = {"entrustSupplyGoods"})
    public String entrustSupplyGoods(Model model, String infoId) {
        if (SystemService.currentUser() != null) {
            return "redirect:" + adminPath + "/info/toEntrustFromExistInfo?existInfoId=" + infoId;
        } else {
            return "redirect:" + frontPath + "/userApi/loginForm";
        }
    }

    /**
     * 得到所有企业种类
     */
    @ResponseBody
    @RequestMapping(value = {"findOrganizationType"})
    public List<OrganizationType> findOrganizationType() {
        return organizationServiceImpl.findOrganizationType();

    }

    /**
     * 根据父类取得子类
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getChildCategories")
    public List<Gcategory> getChildCategories(String id) {
        Gcategory g = new Gcategory();
        g.setId(Integer.parseInt(id));
        List<Gcategory> result = gcategoryService.getChildCategories(g);
        return result;
    }

    /**
     * 不登录情况下委托找货
     */
    @ResponseBody
    @RequestMapping(value = {"commitEntrustFindGoods"})
    public String commitEntrustFindGoods(HttpServletRequest request, HttpServletResponse response, Model model) {
//        <%--name/type/detailArea/mainType/gcategoryIds/personName/phoneTel/pgoodsName/goodsName/pgoodsId/goodsId/infospecs/remarks/cityId/provinceId/areaId--%>
        String msg = "";
        /*
        map.get("keyValue")返回的是String[]
        Map<String,String> map = request.getParameterMap();
        organization.setType( ((String[])map.get(""))[0]);
        */
        Organization organization = new Organization();
        organization.preInsert();
        organization.setName(request.getParameter("name"));
        organization.setType(request.getParameter("type"));
//        organization.setDetailArea(request.getParameter("reciveDetailArea"));
        organization.setPersonName(request.getParameter("personName"));
        organization.setPhoneTel(request.getParameter("phoneTel"));
//        organization.setCityId(request.getParameter("reciveCityId"));
//        organization.setProvinceId(request.getParameter("reciveProvinceId"));
//        organization.setArea(request.getParameter("reciveAreaId"));

        boolean beanValidator = beanValidator(model, organization);
        if (!beanValidator) {
            msg = "组织添加不成功";
        }

        Info info = new Info();
        info.setPgoodsName(request.getParameter("pgoodsName"));
        info.setGoodsName(request.getParameter("goodsName"));
        info.setPgoodsId(request.getParameter("pgoodsId"));
        info.setGoodsId(request.getParameter("goodsId"));
        info.setSpecs(request.getParameter("infospecs"));
        info.setRemarks(request.getParameter("remarks"));

        info.setCityId(request.getParameter("reciveCityId"));
        info.setProvinceId(request.getParameter("reciveProvinceId"));
        info.setAreaId(request.getParameter("reciveAreaId"));
        info.setDetailArea(request.getParameter("reciveDetailArea"));

        info.setProductionCity(request.getParameter("cityId"));
        info.setProductionProvince(request.getParameter("provinceId"));
        info.setProductionArea(request.getParameter("areaId"));
        info.setProductionDetailArea(request.getParameter("detailArea"));

        String provinceId = request.getParameter("provinceId");
        String cityId = request.getParameter("cityId");
        String areaId = request.getParameter("areaId");
        if (null != provinceId && !"".equals(provinceId)) {
            info.setProductionProvinceName(areaService.get(request.getParameter("provinceId")).getName());
        } else {
            info.setProductionProvinceName(null);
        }
        if (null != cityId && !"".equals(cityId)) {
            info.setProductionCityName(areaService.get(request.getParameter("cityId")).getName());
        } else {
            info.setProductionCityName(null);
        }
        if (null != areaId && !"".equals(areaId)) {
            info.setProductionAreaName(areaService.get(request.getParameter("areaId")).getName());
        } else {
            info.setProductionAreaName(null);
        }

        if (!"".equals(request.getParameter("infoId"))) {
            info.setIsEntrust(1);
        } else {
            info.setIsEntrust(0);
        }
        info.setEntrustOrganizationId(organization.getId());
        if (!"".equals(request.getParameter("infoId"))) {
            info.setEntrustSupplyDemandId(request.getParameter("infoId"));
        }
        info.setUserName(request.getParameter("personName"));
        info.setTelephone(request.getParameter("phoneTel"));
        info.setType("demand");
        String[] ids = request.getParameter("mainGoodss").split(",");
        List<String> ids2 = new ArrayList<String>();
        int size = 0;
        if (ids.length == 0) {
            msg = "公司主营产品必选";
        } else {
            for (int i = 0; i < ids.length; i++) {
                ids2.add(ids[i]);
                String[] pids = gcategoryService.get(Integer.parseInt(ids[i])).getParentIds().split(",");
                for (int j = 0; j < pids.length; j++) {
                    if (!InfoUtils.ifHasStr(ids2, pids[j])) {
                        ids2.add(pids[j]);
                    }
                }
            }
            size = ids2.size();
        }
        try {
            infoService.saveEntrustFindGoods(organization, info, (String[]) ids2.toArray(new String[size]));
        } catch (Exception e) {
            e.printStackTrace();
            msg = "添加失败";
        }

        return msg;
    }


    /**
     * 得到province
     */
    @ResponseBody
    @RequestMapping(value = "ajaxProvince")
    public List<Area> ajaxProvince() {
        List<Area> provinces = areaService.ajaxAllProvince();
        return provinces;
    }

    /**
     * 根据provinceId 得到citys
     */
    @ResponseBody
    @RequestMapping(value = "ajaxCity")
    public List<Area> ajaxCity(String provinceId) {
        List<Area> citys = areaService.ajaxAllCityByPovinceId(provinceId);
        return citys;
    }

    /**
     * 根据cityId得到areas
     */
    @ResponseBody
    @RequestMapping(value = "ajaxArea")
    public List<Area> ajaxArea(String cityId) {
        List<Area> areas = areaService.ajaxAllAreaByCityId(cityId);
        return areas;
    }

    /**
     * 根据ids得到用户的主营项目名称（list.toString）
     */
    @ResponseBody
    @RequestMapping(value = "getShowgCateNames")
    public Map getShowgCateNames(String ids) {
        Map map = new HashMap();
        String[] ids2 = ids.split(",");
        Gcategory gcategory = null;
        StringBuffer sb = new StringBuffer("");
        StringBuffer sb2 = new StringBuffer("");
        for (int i = 0; i < ids2.length; i++) {
            gcategory = gcategoryService.get(Integer.parseInt(ids2[i]));
            if ("1".equals(gcategory.getShowSpec())) {
                sb.append(gcategory.getName());
                sb2.append(gcategory.getId());
                if (i < ids2.length - 1) {
                    sb.append(",");
                    sb2.append(",");
                }
            }
        }
        map.put("names", sb.toString());
        map.put("ids", sb2.toString());
        return map;
    }


/*************************************************************************以上是果果网***********************************************************************************************/
/*************************************************************************以下是果果网***********************************************************************************************/

    /**
     * 网站web端首页(果果网)
     */
    @RequestMapping(value = {"index", ""})
    public String index(Model model) {
        //返回类别列表，所含供求列表内容不轮换，参数是父类别gcatefory
        try {
            List<Gcategory> list = homeService.findSimpleList(homeService.getCategory(BasicCategory.fruit));
            //返回顶部供应信息，所含内容不轮换，参数是父类别
            Info info = new Info();
            info.setIsFront(true);
            info.setStatue(999);
            info.setIsEntrust(0);
            info.setType("supply");
            List<Info> supplies = homeService.findSimpleInfoes(homeService.getCategory(BasicCategory.fruit), info);
            //返回顶部求购信息，所含内容不轮换，参数是父类别
            info.setType("demand");
            List<Info> demands = homeService.findSimpleInfoes(homeService.getCategory(BasicCategory.fruit), info);
            model.addAttribute("list", list);
            model.addAttribute("supplies", supplies);
            model.addAttribute("demands", demands);
            return "modules/info/home/guoguo";
        } catch (Exception e) {
            e.printStackTrace();
            return "error/500";
        }
    }

    /**
     * 网站web端供应大厅(果果网)
     */
    @RequestMapping(value = {"supplyList"})
    public String supplyList(Model model, HttpServletRequest request, HttpServletResponse response) {
        try {

            String tabName = "";
            Tab tab = null;
            List<Tab> tabs = new ArrayList<Tab>();
            Map<String, String> tabMap = new HashMap<String, String>();
            String pgoodsName = request.getParameter("pgoodsName");
            String goodsName = request.getParameter("goodsName");
            String detailArea = request.getParameter("detailArea");
            String organizationName = request.getParameter("organizationName");
            String pageNo = request.getParameter("pageNo");
            String pageSize = request.getParameter("pageSize");
            String gcategory = request.getParameter("gcategory");
            String pgoodsId = request.getParameter("pgoodsId");
            String goodsId = request.getParameter("goodsId");
            String provinceId = request.getParameter("provinceId");
            String cityId = request.getParameter("cityId");
            //String orderName = request.getParameter("orderName");
            String type = request.getParameter("type");
            String pids = request.getParameter("pids");

            Info info = new Info();
            info.setIsEntrust(0);
            info.setStatue(999);
            if (pgoodsName != null && !"".equals(pgoodsName)) info.setPgoodsName(pgoodsName);
            if (goodsName != null && !"".equals(goodsName)) info.setGoodsName(goodsName);
            if (detailArea != null && !"".equals(detailArea)) info.setDetailArea(detailArea);
            if (organizationName != null && !"".equals(organizationName)) info.setOrganizationName(organizationName);
            String str = homeService.getCategory(BasicCategory.fruit).getId().toString();
            if (gcategory != null && !"".equals(gcategory)) {
                if ("all".equals(gcategory)) {
                    info.setPids("," + str + ",");
                    tabName = "水果分类:全部";
                } else {
                    if (gcategory.indexOf(",") > -1) {
                        info.setPids(gcategory);
                        tabName = "水果分类:" + gcategoryService.get(Integer.parseInt(gcategory.replace(",", ""))).getName();
                    } else {
                        info.setPids("," + gcategory + ",");
                        tabName = "水果分类:" + gcategoryService.get(Integer.parseInt(gcategory)).getName();
                    }
                }
                tab = new Tab("category", tabName);
                tabs.add(tab);
            } else {
                tabName = "水果分类:全部";
                tab = new Tab("category", tabName);
                tabs.add(tab);
            }
            if (pids != null && !"".equals(pids)) {
                info.setPids("," + pids + ",");
            }

            if (pgoodsId != null && !"".equals(pgoodsId)) {
                if ("all".equals(pgoodsId)) {
                    tabName = "产品:全部";
                    info.setPgoodsId(null);
                } else {
                    info.setPgoodsId(pgoodsId);
                    Gcategory g = gcategoryService.get(Integer.parseInt(pgoodsId));
                    tabName = g.getName();
                    tabName = "产品:" + tabName;
                    tabs.clear();
                    Gcategory g2 = gcategoryService.get(g.getParent().getId());
                    String tabName2 = g2.getName();
                    info.setPids("," + g2.getId() + ",");
                    tab = new Tab("category", "水果分类:" + tabName2);
                    tabs.add(tab);
                }
                tab = new Tab("child", tabName);
                tabs.add(tab);
            } else {
                tabName = "产品:全部";
                tab = new Tab("child", tabName);
                tabs.add(tab);
            }

            if (goodsId != null && !"".equals(goodsId)) {
                if ("all".equals(goodsId)) {
                    tabName = "品种:全部";
                    info.setGoodsId(null);
                } else {
                    info.setGoodsId(goodsId);
                    Gcategory g3 = gcategoryService.get(Integer.parseInt(goodsId));
                    tabName = g3.getName();
                    tabName = "品种:" + tabName;
                    tabs.clear();
                    //goodsName 的父类别
                    Gcategory g4 = gcategoryService.get(g3.getParent().getId());
                    //pgoodsName 的父类别
                    Gcategory g5 = gcategoryService.get(g4.getParent().getId());
                    info.setPids("," + g5.getId() + ",");
                    tab = new Tab("category", "产品:" + g5.getName());
                    tabs.add(tab);
                    info.setPgoodsId(g4.getId().toString());
                    tab = new Tab("child", "品种:" + g4.getName());
                    tabs.add(tab);
                }
                tab = new Tab("grandson", tabName);
                tabs.add(tab);
            } else {
                tabName = "品种:全部";
                tab = new Tab("grandson", tabName);
                tabs.add(tab);
            }

            if (provinceId != null && !"".equals(provinceId)) {
                if ("all".equals(provinceId)) {
                    tabName = "省份:全部";
                    info.setProvinceId(null);
                } else {
                    info.setProvinceId(provinceId);
                    tabName = areaService.get(provinceId).getName();
                    tabName = "省份:" + tabName;
                }
                tab = new Tab("province", tabName);
                tabs.add(tab);
            }
            if (cityId != null && !"".equals(cityId)) {
                if ("all".equals(cityId)) {
                    tabName = "城市:全部";
                    info.setCityId(null);
                } else {
                    info.setCityId(cityId);
                    Area area = areaService.get(cityId);
                    tabName = area.getName();
                    tabName = "城市:" + tabName;
                    if (provinceId == null || "".equals(provinceId)) {
                        Area area2 = areaService.get(area.getParent().getId());
                        info.setProvinceId(area2.getId());
                        tab = new Tab("province", area2.getName());
                        tabs.add(tab);
                    }
                }
                tab = new Tab("city", tabName);
                tabs.add(tab);
            }
            if (type != null && !"".equals(type)) info.setType(type);
            info.setType("supply");

            Page page = new Page<Info>(request, response);
            //page.setOrderBy(orderName+" "+"asc");
            if (pageSize != null && !"".equals(pageSize)) {
                page.setPageSize(Integer.parseInt(pageSize));
            } else {
                page.setPageSize(10);
            }
            info.setIsFront(true);
            Page<Info> pg = infoService.findPage(page, info);
            for (int i = 0; i < pg.getList().size(); i++) {
                pg.getList().get(i).setUpdateTime(DateUtils.getTimeDiffer(pg.getList().get(i).getUpdateDate()));
            }
            List<Gcategory> gcategoryList = homeService.getCategories(BasicCategory.fruit);
            List<Gcategory> childs = null;
            List<Gcategory> grandsons = null;
            List<Area> provinces = areaService.ajaxAllProvince();
            List<Area> cities = null;

            Gcategory gcategory2 = new Gcategory();

            if (info.getPids() != null && !"".equals(info.getPids())) {
                Gcategory gy = gcategoryService.get(Integer.parseInt(info.getPids().replace(",", "")));
                if ("all".equals(gcategory) || homeService.getCategory(BasicCategory.fruit).getId().toString().equals(gcategory)) {
                    childs = gcategoryService.getGrandChildCategories(homeService.getCategory(BasicCategory.fruit));
                } else {
                    childs = gcategoryService.getChildCategories(gy);
                }
            } else {
                childs = gcategoryService.getGrandChildCategories(homeService.getCategory(BasicCategory.fruit));
            }

            if (info.getPgoodsId() != null && !"".equals(info.getPgoodsId())) {
                grandsons = gcategoryService.getChildCategories(gcategoryService.get(Integer.parseInt(info.getPgoodsId())));
            } else {
                if (info.getPids() != null && !"".equals(info.getPids())) {
                    Gcategory gy = gcategoryService.get(Integer.parseInt(info.getPids().replace(",", "")));
                    if ("all".equals(gcategory) || homeService.getCategory(BasicCategory.fruit).getId().toString().equals(gcategory)) {
                        grandsons = homeService.getGrandGrandChildCategories(homeService.getCategory(BasicCategory.fruit));
                    } else {
                        grandsons = gcategoryService.getGrandChildCategories(gy);
                    }
                } else {
                    grandsons = homeService.getGrandGrandChildCategories(homeService.getCategory(BasicCategory.fruit));
                }
            }

            Area area = null;
            if (info.getProvinceId() != null && !"".equals(info.getProvinceId())) {
                area = new Area();
                Area area2 = new Area();
                area2.setId(info.getProvinceId());
                area.setParent(area2);
                area.setType("3");
                cities = areaService.findAllList(area);
            } else {
                area = new Area();
                area.setType("3");
                cities = areaService.findAllList(area);
            }
            List<String> strlist = new ArrayList<String>();
            for (int i = 0; i < tabs.size(); i++) {
                if (!tabs.get(i).getTabName().split(":")[1].equals("全部")) {
                    strlist.add(tabs.get(i).getTabName().split(":")[1]);
                }
            }
            if (info.getPids() != null && !"".equals(info.getPids())) info.setPids(info.getPids().replace(",", ""));
            model.addAttribute("page", pg);
            model.addAttribute("gcategoryList", gcategoryList);
            model.addAttribute("childs", childs);
            model.addAttribute("grandsons", grandsons);
            model.addAttribute("provinces", provinces);
            model.addAttribute("cities", cities);
            model.addAttribute("info", info);
            model.addAttribute("tabs", tabs);
            model.addAttribute("strlist", strlist);
        } catch (Exception e) {
            e.printStackTrace();
            return "error/500";
        }
        return "modules/info/home/supplyList";
    }


    public class Tab {
        private String type;
        private String tabName;

        public Tab(String type, String tabName) {
            this.tabName = tabName;
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTabName() {
            return tabName;
        }

        public void setTabName(String tabName) {
            this.tabName = tabName;
        }
    }

    /*
    * 求购大厅
    * */
    @RequestMapping(value = {"toDemandList"})
    public String toDemandList(Info info, Model model) {
        List<Gcategory> menuList = homeService.threeLevelMenus(homeService.getCategory(BasicCategory.fruit));
        List<Area> provinces = areaService.ajaxAllProvince();
        for (int i = 0; i < provinces.size(); i++) {
            List<Area> list = areaService.ajaxAllCityByPovinceId(provinces.get(i).getId());
            provinces.get(i).setCitys(list);
        }
        Organization org = new Organization();
        Page<Organization> page = new Page<Organization>();
        page.setPageSize(8);
        org.setPage(page);
        page.setPageNo(1);
        List<Organization> organizationList = organizationService.findList(org);
        for (int x = 0; x < organizationList.size(); x++) {
            List strs = organizationService.findOrganziationMainGoodsbyId(organizationList.get(x).getId());
            if (strs != null) {
                organizationList.get(x).setMainType(strs.toString().substring(1, strs.toString().length() - 1));
            }
        }
        model.addAttribute("menuList", menuList);
        model.addAttribute("areaList", provinces);
        model.addAttribute("info", info);
        model.addAttribute("organizationList", organizationList);
        return "modules/info/home/demandList";
    }

    @ResponseBody
    @RequestMapping(value = {"demandList"})
    public Map demandList(Info info, HttpServletRequest request, HttpServletResponse response) {
        if ("".equals(info.getPgoodsName())) {
            info.setPgoodsName(null);
        }
        if ("".equals(info.getGoodsName())) {
            info.setGoodsName(null);
        }
        if ("".equals(info.getDetailArea())) {
            info.setDetailArea(null);
        }
        if ("".equals(info.getOrganizationName())) {
            info.setOrganizationName(null);
        }
        if ("".equals(info.getPgoodsId())) {
            info.setPgoodsId(null);
        }
        if ("".equals(info.getGoodsId())) {
            info.setGoodsId(null);
        }
        if ("".equals(info.getProvinceId())) {
            info.setProvinceId(null);
        }
        if ("".equals(info.getCityId())) {
            info.setCityId(null);
        }
        if ("".equals(info.getPids()) || info.getPids() == null) {
            info.setPids(null);
        } else {
            info.setPids("," + info.getPids() + ",");
        }
        info.setIsFront(true);
        Map map = new HashMap();
        info.setStatue(999);
        info.setIsEntrust(0);
        Page page = new Page<Info>(request, response);
        page.setPageSize(8);
        Page<Info> pg = infoService.findPage(page, info);
        for (int i = 0; i < pg.getList().size(); i++) {
            User middleMan = null;
            Info info1 = pg.getList().get(i);
            if (info1.getPublishUser() != null &&!"".equals(info1.getPublishUser())) {
                middleMan = userService.findBussinessUserByFrontUser(userService.getUserByPersonId(info1.getPublishUser()).getId());
            }
            if (middleMan != null) {
                pg.getList().get(i).setMiddleMan(middleMan);
            }else{
                pg.getList().get(i).setMiddleMan(new User());
            }
        }
        map.put("page", pg);
        if(info.getPids()!=null){
            info.setPids(info.getPids().replace(",",""));
        }
        map.put("info", info);
        List<String> strs = new ArrayList<String>();
        if(info.getGoodsId()!=null&&!"".equals(info.getGoodsId())){
            Gcategory grandson=gcategoryService.get(Integer.parseInt(info.getGoodsId()));
            Gcategory son = gcategoryService.get(grandson.getParentId());
            Gcategory top = gcategoryService.get(son.getParentId());
            strs.add(top.getName());
            strs.add(son.getName());
            strs.add(grandson.getName());
        }else if(info.getPgoodsId()!=null&&!"".equals(info.getPgoodsId())){
            Gcategory son=gcategoryService.get(Integer.parseInt(info.getPgoodsId()));
            Gcategory top = gcategoryService.get(son.getParentId());
            strs.add(top.getName());
            strs.add(son.getName());
        }else if(info.getPids()!=null&&!"".equals(info.getPids())){
            Gcategory top=gcategoryService.get(Integer.parseInt(info.getPids().replace(",", "")));
            strs.add(top.getName());
        }
        map.put("strs", strs);
        return map;
    }


    /*
    * 相似供求信息推荐
    *   有效期内
        状态正常
        同种品种
        发布时间倒叙
    * */

    @ResponseBody
    @RequestMapping(value = "findSimilarInfoes")
    public JSONObject findSimilarInfoes(String infoId, String count, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        try {
            Info info = new Info();
            Info target = infoService.get(infoId);
            info.setIsFront(true);
            info.setStatue(999);
            info.setIsEntrust(0);
            if (target != null) {
                info.setGoodsId(target.getGoodsId());
            }
            if (StringUtils.isBlank(count)) {
                Page page = new Page<Info>(request, response);
                page.setPageSize(10);
                Page<Info> pg = infoService.findPage(page, info);
                jsonObject.put("page", page);
            } else {
                Page<Info> page = new Page<Info>();
                page.setPageSize(4);
                page.setPageNo(1);
                info.setPage(page);
                List<Info> list = infoService.findList(info);
                jsonObject.put("list",list);
            }
            InfoUtils.responseSuccess(jsonObject,"请求成功");
        } catch (Exception e) {
            e.printStackTrace();
            InfoUtils.responseFail(jsonObject,"很抱歉，请求失败",-1);
        }
        return jsonObject;
    }
/*
* 查看请求详情
* */
    @RequestMapping(value="getInfoDetails")
    public String getInfoDetails(String id,Model model,HttpServletRequest request){
        Info info = infoService.get(id);
        homeService.addInfoLog(id, request);
        Integer count = homeService.getInfoClickNum(id);
        info.setImageUrls(infoService.getImagesByInfoId(id));
        User middleMan = homeService.getMiddleManByUserId(userService.getUserByPersonId(info.getPublishUser()).getId());
        info.setMiddleMan(middleMan);
        Organization org = organizationServiceImpl.findOrganizationById(info.getOrganizationId());
        List strs = organizationService.findOrganziationMainGoodsbyId(org.getId());
        if(strs!=null){
            org.setMainType(strs.toString().substring(1,strs.toString().length()-1));
        }
        //相似供求信息推荐
        List<Info> list = homeService.findSimilarInfoes(info,4);

        model.addAttribute("list", list);
        model.addAttribute("org", org);
        if (info.getImageUrls().size() > 0) {
            model.addAttribute("firstImg", info.getImageUrls().get(0));
        } else {
            model.addAttribute("firstImg", null);
        }
        model.addAttribute("info", info);
        model.addAttribute("count", count);
        return "modules/info/home/supplyDetail";
    }




}
