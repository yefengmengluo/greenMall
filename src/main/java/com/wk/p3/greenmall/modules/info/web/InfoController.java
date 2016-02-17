package com.wk.p3.greenmall.modules.info.web;

import com.alibaba.fastjson.JSONObject;
import com.wk.p3.greenmall.common.config.Global;
import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.common.utils.DateUtils;
import com.wk.p3.greenmall.common.utils.SpringContextHolder;
import com.wk.p3.greenmall.common.utils.StringUtils;
import com.wk.p3.greenmall.common.web.BaseController;
import com.wk.p3.greenmall.modules.advise.entry.Recommend;
import com.wk.p3.greenmall.modules.advise.service.RecommendService;
import com.wk.p3.greenmall.modules.info.entity.*;
import com.wk.p3.greenmall.modules.info.service.HomeService;
import com.wk.p3.greenmall.modules.info.service.InfoUnitCategoryService;
import com.wk.p3.greenmall.modules.info.service.imp.InfoServiceImp;
import com.wk.p3.greenmall.modules.info.utils.InfoUtils;
import com.wk.p3.greenmall.modules.match.service.impl.MatchServiceImpl;
import com.wk.p3.greenmall.modules.security.SecurityService;
import com.wk.p3.greenmall.modules.sys.entity.User;
import com.wk.p3.greenmall.modules.sys.service.DictService;
import com.wk.p3.greenmall.modules.sys.service.SystemService;
import com.wk.p3.greenmall.modules.sys.utils.UserUtils;
import com.wk.p3.greenmall.modules.user.UserService;
import com.wk.p3.greenmall.modules.user.entity.FrontUser;
import com.wk.p3.greenmall.modules.user.entity.UserAddressInfo;
import com.wk.p3.greenmall.modules.user.impl.UserServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by liujiabao on 2015/12/18 0018.
 */
@Controller
@RequestMapping(value = "${adminPath}/info")

public class InfoController extends BaseController{

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(InfoController.class);

    @Autowired
    InfoServiceImp infoServiceImp;
    @Autowired
    MatchServiceImpl matchServiceImpl;

    @Autowired
    RecommendService recommendService;

    @Autowired
    InfoUnitCategoryService infoUnitCategoryService;

    @Autowired
    SystemService systemService;

    @Autowired
    DictService dictService;

    @Autowired
    UserService userService;

    @Autowired
    SecurityService securityService;

    @Value("${infoImgUrl}")
    protected String infoImgUrl;

    private static HomeService homeService = SpringContextHolder.getBean(HomeService.class);
   /* @ModelAttribute("info")
    public Info get(@RequestParam(required=false) String id) {
        if (StringUtils.isNotBlank(id)){
            try{
                return infoServiceImp.get(id);
            }catch(Exception e){
                return new Info();
            }
        }else{
            return new Info();
        }
    }*/


    @ResponseBody
    @RequestMapping(value = "sendSelectNews")
    public String sendSelectNews(String ids) {

        String message = "";
        message = infoServiceImp.sendSelectNews(ids);
        return message;
    }


    @RequestMapping(value="demandInfo")
    public String demandInfo(String id,Model model){

        Info info = infoServiceImp.get(id);

        model.addAttribute("info",info);

        return "modules/info/demandDetailInfo";
    }
    @RequestMapping(value="supplyInfo")
    public String supplyInfo(String id,Model model){

        Info info = infoServiceImp.get(id);

        model.addAttribute("info",info);

        return "modules/info/supplyDetailInfo";
    }

    @RequestMapping(value = "getInfoById")
    public String getInfoById(String id,Model model){
        Info info = infoServiceImp.get(id);
        model.addAttribute("info", info);
        return "modules/info/infoForm";
    }
    @RequestMapping(value = "getInfoByInfo")
    public String getInfoByInfo(Info info,Model model){
        info = infoServiceImp.get(info);
        model.addAttribute("info", info);
        return "modules/info/infoForm";
    }


    @RequestMapping(value = "update")
    public String update(Info info,Model model) {
        info.setType("supply");
        info.setGoodsId("8");
        info.setGoodsName("name");
        info.setPgoodsId("2");
        info.setPgoodsName("pGoodsName2");
        User user = UserUtils.getUser();
        info.setCreateBy(user);
        info.setUpdateBy(user);
        info.setCreateDate(new Date());
        info.setUpdateDate(new Date());
        info.setPublishUser(securityService.currentPerson().getId());

        info.setIsNewRecord(false);

        infoServiceImp.save(info);
        model.addAttribute("info", info);
        return "modules/info/infoForm";
    }

    @RequestMapping(value="delete")
    public void delete(Info info){
        infoServiceImp.delete(info);
    }

    @RequestMapping(value="findList")
    public String findList(Info info,Model model){
        List<Info> lists = infoServiceImp.findList(info);
        model.addAllAttributes(lists);
        return "modules/info/infoForm";
    }

    @RequestMapping(value="updateDemandStatus")
    public void updateDemandStatus(Info info){
        info.setId("123");
        info.setStatue(3);
        System.out.println(info);
        infoServiceImp.updateDemandStatus(info);
    }


    @ResponseBody
    @RequestMapping(value="infoDataTable")
    public Map supplyDataTable(Info info,HttpServletRequest request){
            Map map = new HashMap();
            info.setDataSize(Integer.parseInt(request.getParameter("length")));
            info.setStart(Integer.parseInt(request.getParameter("start")));
            List<Info> list = infoServiceImp.findInfoTable(info);
            long count = infoServiceImp.findInfoCount(info);
            map.put("data", list);
            map.put("recordsFiltered", count);
            map.put("recordsTotal", Integer.parseInt(request.getParameter("length")));
            return map;
        }

    /**
     * 未审核列表
     * @param info
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="demandCheck")
    public String demandCheck(Info info, HttpServletRequest request, HttpServletResponse response,Model model){
//        Page<Info> page = infoServiceImp.findPage(new Page<Info>(request, response), info);
        String telephone = info.getTelephone();
        if("".equals(telephone)){
            info.setTelephone(null);
        }
        String userName = info.getUserName();
        if("".equals(userName)){
            info.setUserName(null);
        }
        if(info.getStatue()==null){
            info.setStatue(0);
        }
        info.setIsEntrust(0);
        Page<Info> page = infoServiceImp.getDemandPage(new Page<Info>(request, response), info);
        model.addAttribute("page", page);
        return "modules/info/beforeDemandCheck";
    }

    /**
     * 审核不通过列表
     * @param info
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="demandCheckedNo")
    public String demandCheckedNo(Info info, HttpServletRequest request, HttpServletResponse response,Model model){
//        Page<Info> page = infoServiceImp.findPage(new Page<Info>(request, response), info);
        String telephone = info.getTelephone();
        if("".equals(telephone)){
            info.setTelephone(null);
        }
        String userName = info.getUserName();
        if("".equals(userName)){
            info.setUserName(null);
        }
        info.setIsEntrust(0);

        Page<Info> page = infoServiceImp.getDemandPage(new Page<Info>(request, response), info);
        model.addAttribute("page", page);
        return "modules/info/demandCheckedNo";
    }

    /**
     * 删除信息列表
     * @param info
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="demandDelete")
    public String demandDelete(Info info, HttpServletRequest request, HttpServletResponse response,Model model){
//        Page<Info> page = infoServiceImp.findPage(new Page<Info>(request, response), info);
        String telephone = info.getTelephone();
        if("".equals(telephone)){
            info.setTelephone(null);
        }
        String userName = info.getUserName();
        if("".equals(userName)){
            info.setUserName(null);
        }
        info.setIsEntrust(0);

        Page<Info> page = infoServiceImp.getDemandPage(new Page<Info>(request, response), info);
        model.addAttribute("page", page);
        return "modules/info/demandDelete";
    }
    /**
     * 洽谈中信息列表
     * @param info
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="demandTalking")
    public String demandTalking(Info info, HttpServletRequest request, HttpServletResponse response,Model model){
//        Page<Info> page = infoServiceImp.findPage(new Page<Info>(request, response), info);
        String telephone = info.getTelephone();
        if("".equals(telephone)){
            info.setTelephone(null);
        }
        String userName = info.getUserName();
        if("".equals(userName)){
            info.setUserName(null);
        }

        info.setIsEntrust(0);
        Page<Info> page = infoServiceImp.getDemandPage(new Page<Info>(request, response), info);
        model.addAttribute("page", page);
        return "modules/info/demandTalking";
    }
    /**
     * 洽谈中信息列表
     * @param info
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="demandWaitingMoney")
    public String demandWaitingMoney(Info info, HttpServletRequest request, HttpServletResponse response,Model model){
//        Page<Info> page = infoServiceImp.findPage(new Page<Info>(request, response), info);
        String telephone = info.getTelephone();
        if("".equals(telephone)){
            info.setTelephone(null);
        }
        String userName = info.getUserName();
        if("".equals(userName)){
            info.setUserName(null);
        }

        info.setIsEntrust(0);
        Page<Info> page = infoServiceImp.getDemandPage(new Page<Info>(request, response), info);
        model.addAttribute("page", page);
        return "modules/info/demandWaitingMoney";
    }
    /**
     * 洽谈中信息列表
     * @param info
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="demandSuccess")
    public String demandSuccess(Info info, HttpServletRequest request, HttpServletResponse response,Model model){
//        Page<Info> page = infoServiceImp.findPage(new Page<Info>(request, response), info);
        String telephone = info.getTelephone();
        if("".equals(telephone)){
            info.setTelephone(null);
        }
        String userName = info.getUserName();
        if("".equals(userName)){
            info.setUserName(null);
        }
        info.setIsEntrust(0);
        Page<Info> page = infoServiceImp.getDemandPage(new Page<Info>(request, response), info);
        model.addAttribute("page", page);
        return "modules/info/demandSuccess";
    }
    /**
     * 审核不通过列表
     * @param info
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="supplyCheckedNo")
    public String supplyCheckedNo(Info info, HttpServletRequest request, HttpServletResponse response,Model model){
//        Page<Info> page = infoServiceImp.findPage(new Page<Info>(request, response), info);
        String telephone = info.getTelephone();
        if("".equals(telephone)){
            info.setTelephone(null);
        }
        String userName = info.getUserName();
        if("".equals(userName)){
            info.setUserName(null);
        }


        Page<Info> page = infoServiceImp.getSupplyPage(new Page<Info>(request, response), info);
        model.addAttribute("page", page);
        return "modules/info/supplyCheckedNo";
    }

    /**
     * 删除信息列表
     * @param info
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="supplyDelete")
    public String supplyDelete(Info info, HttpServletRequest request, HttpServletResponse response,Model model){
//        Page<Info> page = infoServiceImp.findPage(new Page<Info>(request, response), info);
        String telephone = info.getTelephone();
        if("".equals(telephone)){
            info.setTelephone(null);
        }
        String userName = info.getUserName();
        if("".equals(userName)){
            info.setUserName(null);
        }

        info.setIsEntrust(0);
        Page<Info> page = infoServiceImp.getSupplyPage(new Page<Info>(request, response), info);
        model.addAttribute("page", page);
        return "modules/info/supplyDelete";
    }
    /**
     * 洽谈中信息列表
     * @param info
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="supplyTalking")
    public String supplyTalking(Info info, HttpServletRequest request, HttpServletResponse response,Model model){
//        Page<Info> page = infoServiceImp.findPage(new Page<Info>(request, response), info);
        String telephone = info.getTelephone();
        if("".equals(telephone)){
            info.setTelephone(null);
        }
        String userName = info.getUserName();
        if("".equals(userName)){
            info.setUserName(null);
        }

        info.setIsEntrust(0);
        Page<Info> page = infoServiceImp.getSupplyPage(new Page<Info>(request, response), info);
        model.addAttribute("page", page);
        return "modules/info/supplyTalking";
    }
    /**
     * 洽谈中信息列表
     * @param info
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="supplyWaitingMoney")
    public String supplyWaitingMoney(Info info, HttpServletRequest request, HttpServletResponse response,Model model){
//        Page<Info> page = infoServiceImp.findPage(new Page<Info>(request, response), info);
        String telephone = info.getTelephone();
        if("".equals(telephone)){
            info.setTelephone(null);
        }
        String userName = info.getUserName();
        if("".equals(userName)){
            info.setUserName(null);
        }

        info.setIsEntrust(0);
        Page<Info> page = infoServiceImp.getSupplyPage(new Page<Info>(request, response), info);
        model.addAttribute("page", page);
        return "modules/info/supplyWaitingMoney";
    }
    /**
     * 洽谈中信息列表
     * @param info
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="supplySuccess")
    public String supplySuccess(Info info, HttpServletRequest request, HttpServletResponse response,Model model){
//        Page<Info> page = infoServiceImp.findPage(new Page<Info>(request, response), info);
        String telephone = info.getTelephone();
        if("".equals(telephone)){
            info.setTelephone(null);
        }
        String userName = info.getUserName();
        if("".equals(userName)){
            info.setUserName(null);
        }
        info.setIsEntrust(0);
        Page<Info> page = infoServiceImp.getSupplyPage(new Page<Info>(request, response), info);
        model.addAttribute("page", page);
        return "modules/info/supplySuccess";
    }
    @RequestMapping(value="demandChecked")
    public String demandChecked(Info info, HttpServletRequest request, HttpServletResponse response,Model model){
        String telephone = info.getTelephone();
        if("".equals(telephone)){
            info.setTelephone(null);
        }
        String userName = info.getUserName();
        if("".equals(userName)){
            info.setUserName(null);
        }
        if(info.getStatue()==null){
            info.setStatue(0);
        }
        info.setIsEntrust(0);
        Page<Info> page = infoServiceImp.getCheckedDemandPage(new Page<Info>(request, response), info);
        for(Info o:page.getList()){
            List<Recommend> recommends = recommendService.list("0","platform",o.getId(),UserUtils.getUser().getId(),null,null,info.getPgoodsId());
            if(recommends.size()>0){
                o.setIsRecommend(1);
            }else{
                o.setIsRecommend(0);
            }
            InfoCheck infoCheck = o.getInfoCheck();
            if(infoCheck==null){
                InfoMessage infoMessage = new InfoMessage();
                infoMessage.setSupplyDemandId(o.getId());
                List<InfoMessage> infoMessages = infoServiceImp.getInfoMessages(infoMessage);
                if(infoMessages.size()>0){
                    o.setInfoMessage(infoMessages.get(0));
                }
            }
        }
        model.addAttribute("page", page);
        return "modules/info/afterDemandCheck";
    }

    /**
     * 求购信息对应的匹配信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "querySystemMatchingSupplys")
    public String querySystemMatchingSupplys(String id,String detailId, Model model){

        List result =  infoServiceImp.querySystemMatching(id);
        model.addAttribute("matching",result);
        model.addAttribute("idStatue", id);
        model.addAttribute("detailId", detailId);
        return "modules/info/showSystemMatchingSupplys";
    }
    /**
     * 求购信息对应的委托供应信息
     * @param info
     * @param model
     * @return
     */
    @RequestMapping(value = "showEntrustSupplys")
    public String showEntrustSupplys(Info info,HttpServletRequest request, HttpServletResponse response, Model model) {
        Info o = new Info();
        o.setStatue(1);
        o.setEntrustSupplyDemandId(info.getId());
        model.addAttribute("list", infoServiceImp.findList(o));
        model.addAttribute("basicId",info.getId());
        return "modules/info/entrust/showEntrustSupplys";
    }
    /**
     * 供应信息对应的委托求购信息
     * @param info
     * @param model
     * @return
     */
    @RequestMapping(value = "showEntrustDemands")
    public String showEntrustDemands(Info info,HttpServletRequest request, HttpServletResponse response, Model model) {
        Info o = new Info();
        o.setStatue(1);
        o.setEntrustSupplyDemandId(info.getId());
        model.addAttribute("list", infoServiceImp.findList(o));
        model.addAttribute("basicId",info.getId());
        return "modules/info/entrust/showEntrustDemands";
    }
    /**
     * 供应信息对应的匹配信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "querySystemMatchingDemands")
    public String querySystemMatchingDemands(String id,String detailId,Model model){

        List result =  infoServiceImp.querySystemMatching(id);
        model.addAttribute("matching", result);
        model.addAttribute("idStatue", id);
        model.addAttribute("detailId", detailId);
        return "modules/info/showSystemMatchingDemands";
    }
    /**
     * 新增求购信息 页面
     * @return
     */
    @RequestMapping(value="createDemand")
    public String createDemand(Model model){
        List<Gcategory> gcategories = homeService.leftMenus();
        List<User> users = systemService.findUser(new User());
        model.addAttribute("publishUsers",users);
        model.addAttribute("gcategories",gcategories);
        return "modules/info/createDemand";
    }

    /**
     * 新增供应信息 页面
     * @return
     */
    @RequestMapping(value="createSupply")
    public String createSupply(Model model){
        List<Gcategory> gcategories = homeService.leftMenus();
        List<User> users = systemService.findUser(new User());
        model.addAttribute("publishUsers",users);
        model.addAttribute("gcategories",gcategories);
        return "modules/info/createSupply";
    }


    @ResponseBody
    @RequestMapping(value="deleteDemandMessages")
    public String deleteDemandMessages(String ids){
        String message = "";
        String[] idArrays = ids.split(";");
        for(String id:idArrays){
            InfoMessage info = new InfoMessage();
            info.setId(id);
            try {
                infoServiceImp.deleteInfoMessage(info);
            }catch (Exception e){
                message += e.getMessage();
            }
        }
        return message;
    }

    @ResponseBody
    @RequestMapping(value="deleteDemands")
    public String deleteDemands(String ids){
        String message = "";
        String[] idArrays = ids.split(";");
        for(String id:idArrays){
            Info info = new Info();
            info.setId(id);
            try {
                infoServiceImp.delete(info);
            }catch (Exception e){
                message += e.getMessage();
            }
        }
        return message;
    }

    @RequestMapping(value="supplyCheck")
    public String supplyCheck(Info info, HttpServletRequest request, HttpServletResponse response,Model model){
//        Page<Info> page = infoServiceImp.findPage(new Page<Info>(request, response), info);
        String telephone = info.getTelephone();
        if("".equals(telephone)){
            info.setTelephone(null);
        }
        String userName = info.getUserName();
        if("".equals(userName)){
            info.setUserName(null);
        }
        if(info.getStatue()==null){
            info.setStatue(0);
        }
        info.setIsEntrust(0);
        Page<Info> page = infoServiceImp.getSupplyPage(new Page<Info>(request, response), info);
        model.addAttribute("page", page);
        return "modules/info/beforeSupplyCheck";
    }
    @RequestMapping(value="supplyChecked")
    public String supplyChecked(Info info, HttpServletRequest request, HttpServletResponse response,Model model){
        String telephone = info.getTelephone();
        if("".equals(telephone)){
            info.setTelephone(null);
        }
        String userName = info.getUserName();
        if("".equals(userName)){
            info.setUserName(null);
        }
        info.setIsEntrust(0);
        Page<Info> page = infoServiceImp.getCheckedSupplyPage(new Page<Info>(request, response), info);
        for(Info o:page.getList()){
            List<Recommend> recommends = recommendService.list("0","platform",o.getId(),UserUtils.getUser().getId(),null,null,info.getPgoodsId());
            if(recommends.size()>0){
                o.setIsRecommend(1);
            }else{
                o.setIsRecommend(0);
            }
            InfoCheck infoCheck = o.getInfoCheck();
            if(infoCheck==null){
                InfoMessage infoMessage = new InfoMessage();
                infoMessage.setSupplyDemandId(o.getId());
                List<InfoMessage> infoMessages = infoServiceImp.getInfoMessages(infoMessage);
                if(infoMessages.size()>0){
                    o.setInfoMessage(infoMessages.get(0));
                }
            }
        }
        model.addAttribute("page", page);
        return "modules/info/afterSupplyCheck";
    }

    @ResponseBody
    @RequestMapping(value="deleteSupplys")
    public String deleteSupplys(String ids){
        String message = "";
        String[] idArrays = ids.split(";");
        for(String id:idArrays){
            Info info = new Info();
            info.setId(id);
            try {
                infoServiceImp.delete(info);
            }catch (Exception e){
                message += e.getMessage();
            }
        }
        return message;
    }
    @RequestMapping(value = "checkDemand")
    public String checkDemand(String id,Model model){
        Info info = infoServiceImp.get(id);
        List nonstandardNumberUnits = infoUnitCategoryService.queryAllNonstandardNumberUnitByGcategory(info.getPgoodsId());
        List numberUnits = infoUnitCategoryService.queryAllNumberUnitByGcategory(info.getPgoodsId());
        model.addAttribute("info",info);
        model.addAttribute("nonstandardNumberUnits",nonstandardNumberUnits);
        model.addAttribute("numberUnits",numberUnits);
        return "modules/info/checkDemand";
    }

    /**
     * 纯信息的编辑
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "checkDemandMessage")
    public String checkDemandMessage(String id,Model model){

        InfoMessage infoMessage = infoServiceImp.getInfoMessage(id);

        List<Gcategory> gcategories = homeService.leftMenus();
        model.addAttribute("gcategories",gcategories);
        model.addAttribute("infoMessage",infoMessage);
        return "modules/info/createDemandMessage";
    }
    /**
     * 纯信息的编辑
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "checkSupplyMessage")
    public String checkSupplyMessage(String id,Model model){

        InfoMessage infoMessage = infoServiceImp.getInfoMessage(id);

        List<Gcategory> gcategories = homeService.leftMenus();
        if(StringUtils.isNotBlank(infoMessage.getEntrustSupplyDemandId())){
            model.addAttribute("entrustInfo",infoServiceImp.get(infoMessage.getEntrustSupplyDemandId()));
        }
        model.addAttribute("gcategories",gcategories);
        model.addAttribute("infoMessage",infoMessage);
        return "modules/info/createSupplyMessage";
    }

    @RequestMapping(value = "checkSupply")
    public String checkSupply(String id,Model model){
        Info info = infoServiceImp.get(id);
        List nonstandardNumberUnits = infoUnitCategoryService.queryAllNonstandardNumberUnitByGcategory(info.getPgoodsId());
        List numberUnits = infoUnitCategoryService.queryAllNumberUnitByGcategory(info.getPgoodsId());
        model.addAttribute("info",info);
        model.addAttribute("nonstandardNumberUnits",nonstandardNumberUnits);
        model.addAttribute("numberUnits",numberUnits);
        return "modules/info/checkSupply";
    }
    @ResponseBody
    @RequestMapping(value="queryAllNumberUnitByGcategory")
    public List<Map> queryAllNumberUnitByGcategory(Integer id){
        List numberUnits = infoUnitCategoryService.queryAllNumberUnitByGcategory(id.toString());
        return numberUnits;
    }

    @ResponseBody
    @RequestMapping(value="queryAllNonstandardNumberUnitByGcategory")
    public List<Map> queryAllNonstandardNumberUnitByGcategory(Integer id){
        List nonstandardNumberUnits = infoUnitCategoryService.queryAllNonstandardNumberUnitByGcategory(id.toString());
        return nonstandardNumberUnits;
    }

    /**
     * 编辑 供求信息
     * @param info
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "saveEditInfo")

    public String saveEditInfo(Info info){
        if("".equals(info.getNonstandardNumber())){
            info.setNonstandardNumber(null);
        }
        if("".equals(info.getNonstandardNumberUnitId())){
            info.setNonstandardNumberUnitId(null);
            info.setNonstandardNumberUnitName(null);
        }
        String message = "";
        try {
           message = infoServiceImp.saveEditInfo(info);
        }catch (Exception e){
            e.printStackTrace();
            message += e.getMessage();
        }
        return message;
    }


    /**
     * 修改 采购、供应信息(用户登录后从会员中心-列表-修改)
     *
     * @param info
     * @return
     */
    @RequestMapping(value = "editInfo")
    public String editInfo(Info info,Model model,RedirectAttributes redirectAttributes) {
        String message = "";
        try {
            //infoServiceImp.updateUserAddress(info);
            infoServiceImp.updateInfo(info);
        } catch (Exception e) {
            e.printStackTrace();
            message += e.getMessage();
        }
        if(message.equals("")){
            message="修改成功";
            //if("supply".equals(info.getType())){
                return "redirect:"+frontPath+"/myOffers";
            //}
//            else{
//                return "modules/info/home/editDemand";
//            }

        }else{
            message="很抱歉，保存失败，请重试";
            model.addAttribute("info",info);
            redirectAttributes.addAttribute("failMsg",message);
            //if("supply".equals(info.getType())){
                return "redirect:"+frontPath+"/editSupply";
            //}
//            else{
//                return "redirect:"+frontPath+"/publishDemands";
//            }

        }
    }


    /**
     * 发布 采购、供应信息(用户登录后从会员中心发布)
     *
     * @param info
     * @return
     */
    @RequestMapping(value = "publishInfo")
    public String publishInfo(Info info,Model model,RedirectAttributes redirectAttributes,HttpServletRequest request) {
        String str = "";
        String message = "";
        List<String> urllist = new ArrayList<String>();
        String img1 = request.getParameter("img1");
        if(StringUtils.isNotBlank(img1)){
            urllist.add(img1);
        }
        String img2 = request.getParameter("img2");
        if(StringUtils.isNotBlank(img2)){
            urllist.add(img2);
        }
        String img3 = request.getParameter("img3");
        if(StringUtils.isNotBlank(img3)){
            urllist.add(img3);
        }
        String img4 = request.getParameter("img4");
        if(StringUtils.isNotBlank(img4)){
            urllist.add(img4);
        }
        try {
            info.setPublishUser(securityService.currentPerson().getId());
            info.setIsEntrust(0);
            info.setImageUrls(urllist);
            str = infoServiceImp.saveInfo(info);

        } catch (Exception e) {
            try {
                infoServiceImp.delImgsCauseExcep(urllist,request);
            }catch (Exception e1){
                e1.printStackTrace();
                logger.error("图片删除失败，图片路径为" + urllist.toString());
            }
            str = null;
            e.printStackTrace();
            message += e.getMessage();
        }

        if(str!=null){
            message="添加成功";
            Map<Double,String> map = matchServiceImpl.findMatchingBySupplyAndDemand(infoServiceImp.get(str));
            List<Info> list = new ArrayList<Info>();
            if(map.size()>0){
            List<String> ids = new ArrayList<String>();
            for (Object object : map.values()) {
                ids.add(object.toString());
            }
            if(map.size()>=5){
                for(int i=0;i<5;i++){
                    list.add(infoServiceImp.get(ids.get(i)));
                }
            }else{
                for(int i=0;i<ids.size();i++){
                    list.add(infoServiceImp.get(ids.get(i)));
                }
            }
            }
            model.addAttribute("list",list);
            model.addAttribute("info",info);
            if("supply".equals(info.getType())){
                return "modules/info/home/publishSucc";
            }else{
                return "modules/info/home/publishDemandSucc";
            }

        }else{
            message="很抱歉，发布失败，请重试";
            redirectAttributes.addFlashAttribute("failMsg",message);
            if("supply".equals(info.getType())){
                return "redirect:"+frontPath+"/publishSupplies";
            }else{
                return "redirect:"+frontPath+"/publishDemands";
            }

        }
    }

    /**
     * 发布 采购、供应信息（从后台添加）
     * TODO 委托后产生一条InfoRelation
     * @param info
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "saveInfo")
    public String saveInfo(Info info,Model model) {
        String str = "";
        String message = "";
        try
        {

            if("".equals(info.getNonstandardNumber())){
                info.setNonstandardNumber(null);
            }
            if("".equals(info.getNonstandardNumberUnitId())){
                info.setNonstandardNumberUnitId(null);
                info.setNonstandardNumberUnitName(null);
            }
            info.setPublishUser(securityService.currentPerson().getId());
            if(StringUtils.isNotBlank(info.getInfoMessageId())){
                InfoMessage infoMessage = infoServiceImp.getInfoMessage(info.getInfoMessageId());
                if(infoMessage!=null){
                    info.setIsEntrust(infoMessage.getIsEntrust());
                    info.setEntrustSupplyDemandId(infoMessage.getEntrustSupplyDemandId());
                }
            }else{
                info.setIsEntrust(0);
            }
            str = infoServiceImp.saveInfo(info);

            if(str==null){
                message="很抱歉，发布失败，请重试";
            }else{

                InfoMessage infoMessage = infoServiceImp.getInfoMessage(info.getInfoMessageId());
                if(infoMessage!=null) {
                    infoMessage.setStatue(info.getStatue());
                    infoMessage.setSupplyDemandId(str);
                    infoServiceImp.updateInfoMessage(infoMessage);

                }

            }
        } catch (Exception e) {
            //i=0;
            e.printStackTrace();
            message += e.getMessage();
        }
        return message;
    }


    /**
     * 委托的 审核前的求购信息
     * @param info
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="entrustBeforeDemandCheck")
    public String entrustBeforeDemandCheck(Info info, HttpServletRequest request, HttpServletResponse response,Model model){
        String telephone = info.getTelephone();
        if("".equals(telephone)){
            info.setTelephone(null);
        }
        String userName = info.getUserName();
        if("".equals(userName)){
            info.setUserName(null);
        }
        if(info.getStatue()==null){
            info.setStatue(0);
        }
        Page<Info> page = infoServiceImp.getEntrustDemandPage(new Page<Info>(request, response), info);
        model.addAttribute("page", page);
        return "modules/info/entrust/entrustBeforeDemandCheck";
    }

    /**
     * 委托的 审核后的求购信息
     * @param info
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="entrustAfterDemandCheck")
    public String entrustAfterDemandCheck(Info info, HttpServletRequest request, HttpServletResponse response,Model model){
        String telephone = info.getTelephone();
        if("".equals(telephone)){
            info.setTelephone(null);
        }
        String userName = info.getUserName();
        if("".equals(userName)){
            info.setUserName(null);
        }
        if(info.getStatue()==null){
            info.setStatue(0);
        }
        Page<Info> page = infoServiceImp.getEntrustDemandPage(new Page<Info>(request, response), info);
        model.addAttribute("page", page);
        return "modules/info/entrust/entrustAfterDemandCheck";
    }
    /**
     * 委托的 审核不通过的求购信息
     * @param info
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="entrustDemandCheckedNo")
    public String entrustDemandCheckedNo(Info info, HttpServletRequest request, HttpServletResponse response,Model model){
        String telephone = info.getTelephone();
        if("".equals(telephone)){
            info.setTelephone(null);
        }
        String userName = info.getUserName();
        if("".equals(userName)){
            info.setUserName(null);
        }
        if(info.getStatue()==null){
            info.setStatue(0);
        }
        Page<Info> page = infoServiceImp.getEntrustDemandPage(new Page<Info>(request, response), info);
        model.addAttribute("page", page);
        return "modules/info/entrust/entrustDemandCheckedNo";
    }
    /**
     * 委托的 删除的求购信息
     * @param info
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="entrustDemandDelete")
    public String entrustDemandDelete(Info info, HttpServletRequest request, HttpServletResponse response,Model model){
        String telephone = info.getTelephone();
        if("".equals(telephone)){
            info.setTelephone(null);
        }
        String userName = info.getUserName();
        if("".equals(userName)){
            info.setUserName(null);
        }
        if(info.getStatue()==null){
            info.setStatue(0);
        }
        Page<Info> page = infoServiceImp.getEntrustDemandPage(new Page<Info>(request, response), info);
        model.addAttribute("page", page);
        return "modules/info/entrust/entrustDemandDelete";
    }
    /**
     * 委托的 正在洽谈的求购信息
     * @param info
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="entrustDemandTalking")
    public String entrustDemandTalking(Info info, HttpServletRequest request, HttpServletResponse response,Model model){
        String telephone = info.getTelephone();
        if("".equals(telephone)){
            info.setTelephone(null);
        }
        String userName = info.getUserName();
        if("".equals(userName)){
            info.setUserName(null);
        }
        if(info.getStatue()==null){
            info.setStatue(0);
        }
        Page<Info> page = infoServiceImp.getEntrustDemandPage(new Page<Info>(request, response), info);
        model.addAttribute("page", page);
        return "modules/info/entrust/entrustDemandTalking";
    }
    /**
     * 委托的 等待打款的求购信息
     * @param info
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="entrustDemandWaitingMoney")
    public String entrustDemandWaitingMoney(Info info, HttpServletRequest request, HttpServletResponse response,Model model){
        String telephone = info.getTelephone();
        if("".equals(telephone)){
            info.setTelephone(null);
        }
        String userName = info.getUserName();
        if("".equals(userName)){
            info.setUserName(null);
        }
        if(info.getStatue()==null){
            info.setStatue(0);
        }
        Page<Info> page = infoServiceImp.getEntrustDemandPage(new Page<Info>(request, response), info);
        model.addAttribute("page", page);
        return "modules/info/entrust/entrustDemandWaitingMoney";
    }
    /**
     * 委托的 交易成功的求购信息
     * @param info
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="entrustDemandSuccess")
    public String entrustDemandSuccess(Info info, HttpServletRequest request, HttpServletResponse response,Model model){
        String telephone = info.getTelephone();
        if("".equals(telephone)){
            info.setTelephone(null);
        }
        String userName = info.getUserName();
        if("".equals(userName)){
            info.setUserName(null);
        }
        if(info.getStatue()==null){
            info.setStatue(0);
        }
        Page<Info> page = infoServiceImp.getEntrustDemandPage(new Page<Info>(request, response), info);
        model.addAttribute("page", page);
        return "modules/info/entrust/entrustDemandSuccess";
    }
//=========================委托的供应
    /**
     * 委托的 审核前的供应信息
     * @param info
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="entrustBeforeSupplyCheck")
    public String entrustBeforeSupplyCheck(Info info, HttpServletRequest request, HttpServletResponse response,Model model){
        String telephone = info.getTelephone();
        if("".equals(telephone)){
            info.setTelephone(null);
        }
        String userName = info.getUserName();
        if("".equals(userName)){
            info.setUserName(null);
        }
        if(info.getStatue()==null){
            info.setStatue(0);
        }
        Page<Info> page = infoServiceImp.getEntrustSupplyPage(new Page<Info>(request, response), info);
        model.addAttribute("page", page);
        return "modules/info/entrust/entrustBeforeSupplyCheck";
    }

    /**
     * 委托的 审核后的供应信息
     * @param info
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="entrustAfterSupplyCheck")
    public String entrustAfterSupplyCheck(Info info, HttpServletRequest request, HttpServletResponse response,Model model){
        String telephone = info.getTelephone();
        if("".equals(telephone)){
            info.setTelephone(null);
        }
        String userName = info.getUserName();
        if("".equals(userName)){
            info.setUserName(null);
        }
        if(info.getStatue()==null){
            info.setStatue(0);
        }

        Page<Info> page = infoServiceImp.getEntrustSupplyPage(new Page<Info>(request, response), info);
        model.addAttribute("page", page);
        return "modules/info/entrust/entrustAfterSupplyCheck";
    }
    /**
     * 委托的 审核不通过的供应信息
     * @param info
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="entrustSupplyCheckedNo")
    public String entrustSupplyCheckedNo(Info info, HttpServletRequest request, HttpServletResponse response,Model model){
        String telephone = info.getTelephone();
        if("".equals(telephone)){
            info.setTelephone(null);
        }
        String userName = info.getUserName();
        if("".equals(userName)){
            info.setUserName(null);
        }
        if(info.getStatue()==null){
            info.setStatue(0);
        }
        info.setIsEntrust(0);
        Page<Info> page = infoServiceImp.getEntrustSupplyPage(new Page<Info>(request, response), info);
        model.addAttribute("page", page);
        return "modules/info/entrust/entrustSupplyCheckedNo";
    }
    /**
     * 委托的 删除的供应信息
     * @param info
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="entrustSupplyDelete")
    public String entrustSupplyDelete(Info info, HttpServletRequest request, HttpServletResponse response,Model model){
        String telephone = info.getTelephone();
        if("".equals(telephone)){
            info.setTelephone(null);
        }
        String userName = info.getUserName();
        if("".equals(userName)){
            info.setUserName(null);
        }
        if(info.getStatue()==null){
            info.setStatue(0);
        }
        Page<Info> page = infoServiceImp.getEntrustSupplyPage(new Page<Info>(request, response), info);
        model.addAttribute("page", page);
        return "modules/info/entrust/entrustSupplyDelete";
    }
    /**
     * 委托的 正在洽谈的供应信息
     * @param info
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="entrustSupplyTalking")
    public String entrustSupplyTalking(Info info, HttpServletRequest request, HttpServletResponse response,Model model){
        String telephone = info.getTelephone();
        if("".equals(telephone)){
            info.setTelephone(null);
        }
        String userName = info.getUserName();
        if("".equals(userName)){
            info.setUserName(null);
        }
        if(info.getStatue()==null){
            info.setStatue(0);
        }
        Page<Info> page = infoServiceImp.getEntrustSupplyPage(new Page<Info>(request, response), info);
        model.addAttribute("page", page);
        return "modules/info/entrust/entrustSupplyTalking";
    }
    /**
     * 委托的 正在洽谈的供应信息
     * @param info
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="entrustSupplyWaitingMoney")
    public String entrustSupplyWaitingMoney(Info info, HttpServletRequest request, HttpServletResponse response,Model model){
        String telephone = info.getTelephone();
        if("".equals(telephone)){
            info.setTelephone(null);
        }
        String userName = info.getUserName();
        if("".equals(userName)){
            info.setUserName(null);
        }
        if(info.getStatue()==null){
            info.setStatue(0);
        }
        Page<Info> page = infoServiceImp.getEntrustSupplyPage(new Page<Info>(request, response), info);
        model.addAttribute("page", page);
        return "modules/info/entrust/entrustSupplyWaitingMoney";
    }
    /**
     * 委托的 正在洽谈的供应信息
     * @param info
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="entrustSupplySuccess")
    public String entrustSupplySuccess(Info info, HttpServletRequest request, HttpServletResponse response,Model model){
        String telephone = info.getTelephone();
        if("".equals(telephone)){
            info.setTelephone(null);
        }
        String userName = info.getUserName();
        if("".equals(userName)){
            info.setUserName(null);
        }
        if(info.getStatue()==null){
            info.setStatue(0);
        }
        Page<Info> page = infoServiceImp.getEntrustSupplyPage(new Page<Info>(request, response), info);
        model.addAttribute("page", page);
        return "modules/info/entrust/entrustSupplySuccess";
    }


    /**
     * 前台用户点击供求信息，生成委托(点击进入编辑页面)
     * @param
     * @return
     */
    @RequestMapping(value="toEntrustFromExistInfo")
    public String toEntrustFromExistInfo(String existInfoId,Model model){
        //model.addAttribute("existInfoId",existInfoId);
        List<Gcategory> list = homeService.leftMenus();
        User user = infoServiceImp.getCurrentUserInfo();
        model.addAttribute("list", list);
        model.addAttribute("existInfo", infoServiceImp.get(existInfoId));
        model.addAttribute("user", user);
        if(user.getAddresses().size()>0){
            model.addAttribute("userAddressInfo",user.getAddresses().get(0));
        }else {
            model.addAttribute("userAddressInfo",new UserAddressInfo());
        }
        return "modules/info/home/toEntrustFromExistInfo";
    }

    /**
     * 前台用户点击供求信息，生成委托
     * @return
     */
    @RequestMapping(value="entrustFromExistInfo")
    public String entrustFromExistInfo(Info info,RedirectAttributes redirectAttributes){
        String message = "";
        try {
            info.setPublishUser(securityService.currentPerson().getId());
            info.setIsEntrust(1);
            //info.setEntrustOrganizationId(infoServiceImp.get(info.getEntrustSupplyDemandId()).getOrganizationId());
            infoServiceImp.saveInfo(info);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            message = e.getMessage();
        }
        if("".equals(message)){
            redirectAttributes.addFlashAttribute("message","1");
           // return "redirect:"+frontPath+"/userCenter";
            return "redirect:"+adminPath+"/info/toEntrustFromExistInfo?existInfoId="+info.getId();
        }else{
            redirectAttributes.addFlashAttribute("message", "0");
            return "redirect:"+adminPath+"/info/toEntrustFromExistInfo?existInfoId="+info.getId();
        }
    }

    /*@ResponseBody
    @RequestMapping(value = "testStatue")
    public String testStatue(){
        infoServiceImp.updateDemandStatueNumberByOrderStatueNumber("1",2,12,"kg");
        return "";
    }*/

    @ResponseBody
    @RequestMapping(value="recommendTheInfo")
    public String recommendTheInfo(String id){
        String message = "";
        Info info = infoServiceImp.get(id);
        Recommend recommend = new Recommend();
        recommend.setObjectType("0");
        recommend.setObjectId(id);
        recommend.setGoodsType(info.getPgoodsId());
        recommend.setRecommendType("platform");
        recommend.setStatus(0);
        String userId = UserUtils.getUser().getId();
        recommend.setUserId(userId);
        Date now = new Date();
        recommend.setStartTime(now);
        String date = DateUtils.getBeforeTimeByH(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss", -Global.recommendValidateDays * 24, "yyyy-MM-dd HH:mm:ss");
        recommend.setEndTime(DateUtils.fmtDate(date,"yyyy-MM-dd HH:mm:ss"));
        recommend.setCreateDate(now);
        try {
            recommendService.insert(recommend);

        }catch (Exception e){
            logger.error(e.getMessage());
            message = e.getMessage();
        }
        return message;
    }



    /**
     * 纯信息
     * 未审核列表
     * @param infoMessage
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="demandMessageCheck")
    public String demandMessageCheck(InfoMessage infoMessage, HttpServletRequest request, HttpServletResponse response,Model model){

        if(infoMessage.getStatue()==null || infoMessage.getStatue()==100){
            infoMessage.setStatue(0);
        }
        infoMessage.setType("demand");

        Page<InfoMessage> page = infoServiceImp.getInfoMessagePage(new Page<InfoMessage>(request, response), infoMessage);
        model.addAttribute("page", page);
        return "modules/info/beforeDemandMessageCheck";
    }

    /**
     * 纯信息
     * 未审核列表
     * @param infoMessage
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="supplyMessageCheck")
    public String supplyMessageCheck(InfoMessage infoMessage, HttpServletRequest request, HttpServletResponse response,Model model){

//        if(infoMessage.getStatue()==null || infoMessage.getStatue()==100){
//            infoMessage.setStatue(0);
//        }
        if(infoMessage.getPublishUser()!=null) {
            if (StringUtils.isBlank(infoMessage.getPublishUser())) {
                infoMessage.setPublishUser(null);
            }
        }
        infoMessage.setType("supply");
        Page<InfoMessage> page = infoServiceImp.getInfoMessagePage(new Page<InfoMessage>(request, response), infoMessage);
        model.addAttribute("page", page);
        return "modules/info/beforeSupplyMessageCheck";
    }


    /**
     * 发布 纯信息info
     * code=0错误 code=1成功
     * message 信息
     * [code:0,message:xxx]
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value="saveInfoMessage")
    public Map<String,Object> saveInfoMessage(InfoMessage infoMessage){

        int code = 1;
        String message = "";
        try {
            infoServiceImp.saveInfoMessage(infoMessage);
        }catch (Exception e){
            logger.error(e.getMessage());
            code = 0;
            message = "保存出错!";
        }
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("code",code);
        result.put("message", message);
        return result;
    }

    @ResponseBody
    @RequestMapping(value="noIntentionMatchingInfo")
    public String noIntentionMatchingInfo(String id,String score){
        String message = "";
        try {
            infoServiceImp.noIntentionMatchingInfo(id, Double.parseDouble(score));
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            message = e.getMessage();
        }
        return message;
    }
    /*
    * 后台页面，纯信息委托供应
    * */
    @RequestMapping(value="msgEntrustInfoes")
    public String msgEntrustInfoes(InfoMessage infoMessage,Model model, HttpServletRequest request, HttpServletResponse response){
        infoMessage.setIsEntrust(1);
        if(infoMessage.getPublishUser()!=null) {
            if (StringUtils.isBlank(infoMessage.getPublishUser())){
                infoMessage.setPublishUser(null);
            }
            if (StringUtils.isBlank(infoMessage.getTelephone())){
                infoMessage.setTelephone(null);
            }
            if (StringUtils.isBlank(infoMessage.getName())) {
                infoMessage.setName(null);
            }
        }
        InfoMessage infoMessage1 = new InfoMessage();
        infoMessage1.setIsEntrust(1);
        infoMessage1.setStatue(0);
        int t = infoServiceImp.getInfoMessages(infoMessage1).size();
        Page<InfoMessage> page = infoServiceImp.getInfoMessagePage(new Page<InfoMessage>(request, response), infoMessage);
        model.addAttribute("page", page);
        model.addAttribute("count", t);
        return "modules/info/msgEntrustInfoes";
    }


    @ResponseBody
    @RequestMapping(value = "infoImgUpload")
    @Transactional(readOnly = false)
    public JSONObject infoImgUpload(HttpServletRequest request){
        JSONObject jsonObject =new JSONObject();
        String name = request.getParameter("name");
        String fileName = String.valueOf(new Date().getTime())+".jpg";
        String imgPath=infoImgUrl+"/"+fileName;
                try{
                    InfoUtils.saveFile(request, name, imgPath);
                    jsonObject.put("imgPath",imgPath);
                    InfoUtils.responseSuccess(jsonObject,"上传成功");
                }catch (Exception e){
                     InfoUtils.responseFail(jsonObject,"上传失败",-1);
                }
        return jsonObject;
    }



}
