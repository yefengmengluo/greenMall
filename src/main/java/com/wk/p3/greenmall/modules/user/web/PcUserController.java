/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.user.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wk.p3.greenmall.common.utils.CacheUtils;
import com.wk.p3.greenmall.modules.info.utils.InfoUtils;
import com.wk.p3.greenmall.modules.security.Person;
import com.wk.p3.greenmall.modules.security.Principal;
import com.wk.p3.greenmall.modules.security.SecurityService;
import com.wk.p3.greenmall.modules.sys.web.LoginController;
import com.wk.p3.greenmall.modules.user.UserService;
import com.wk.p3.greenmall.modules.user.entity.FrontUser;
import com.wk.p3.greenmall.modules.user.service.UserRelationService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.wk.p3.greenmall.common.servlet.SMSValidate;
import com.wk.p3.greenmall.common.utils.IdGen;
import com.wk.p3.greenmall.common.utils.PatternUtil;
import com.wk.p3.greenmall.common.utils.StringUtils;
import com.wk.p3.greenmall.common.web.BaseController;
import com.wk.p3.greenmall.common.web.Response;
import com.wk.p3.greenmall.modules.info.entity.Gcategory;
import com.wk.p3.greenmall.modules.info.service.HomeService;
import com.wk.p3.greenmall.modules.sys.entity.Office;
import com.wk.p3.greenmall.modules.sys.entity.Role;
import com.wk.p3.greenmall.modules.sys.entity.User;
import com.wk.p3.greenmall.modules.sys.security.UsernamePasswordToken;
import com.wk.p3.greenmall.modules.sys.service.SystemService;
import com.wk.p3.greenmall.modules.sys.utils.DictUtils;
import com.wk.p3.greenmall.modules.sys.utils.UserUtils;
import com.wk.p3.greenmall.modules.user.LoginType;
import com.wk.p3.greenmall.modules.user.entity.Organization;
import com.wk.p3.greenmall.modules.user.entity.OrganizationType;
import com.wk.p3.greenmall.modules.user.service.OrganizationService;


/**
 * 用户Controller
 *
 * @author ThinkGem
 * @version 2013-8-29
 */
@Controller
@RequestMapping(value = "userApi")
public class PcUserController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(PcUserController.class);

    @Autowired
    private SystemService systemService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private HomeService homeService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRelationService userRelationService;


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //前台用户添加
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

//    //图形验证码
//    private Response captchaValidate(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
//        //验证码
//        Session session = UserUtils.getSession();
//        String code = (String) session.getAttribute(ValidateCodeServlet.VALIDATE_CODE);
//        if (request.getParameter("validateCode") == null || !request.getParameter("validateCode").toUpperCase().equals(code)) {
//            return Response.error(-9, "图形验证码失败");
//        }
//        return Response.success();
//    }

    //短信验证
    private Response smsValidate(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        //验证码
        Session session = UserUtils.getSession();
        String smsCode = (String) session.getAttribute("smsCode");
        //短信验证码
        if (request.getParameter("smsCode") == null || !request.getParameter("smsCode").toUpperCase().equals(smsCode)) {
            return Response.error(-10,"短信验证码失败");
        }
        return Response.success();
    }
    /**
     * 跳转用户协议界面
     * @return
     */
    @RequestMapping(value = "userAgreement")
    private String userAgreement() {
    	return "modules/user/agreementPage";
    }

    /**
     * 发送短信验证码码
     * 前提是图形验证码正确
     * 发送消息后在session中同时保存smsCode 和 smsCodeTime
     *
     * @param request
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "smsCode", method = RequestMethod.GET)
    @ResponseBody
    public Response smsCode(FrontUser frontUser, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        Response response = smsvalidate.requestSMS(frontUser.getMobile(), request);
        return response;
    }

    /**
     * 跳转到供应商详情页面
     *
     * @param request
     * @param model
     * @param redirectAttributes
     * @param organizationId
     * @return
     */
    @RequestMapping(value = "supplyDetail")
    public String supplyDetail(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes, @RequestParam("organizationId") String organizationId) {
        Organization organization = organizationService.findOrganizationById(organizationId);
        FrontUser frontUser = userService.getFrontUserByOrganizationId(organizationId);
        User user = userService.findBussinessUserByFrontUser(frontUser.getId());
        FrontUser currentUser = userService.getFrontUser();
        if (currentUser == null) {
            frontUser.setMobile(frontUser.getMobile().substring(0, 3) + "****" + frontUser.getMobile().substring(7, frontUser.getMobile().length()));
        }
        String resultGoods = this.getOrganizationMainGoods(frontUser.getOrganizationId());
        model.addAttribute("organization", organization);
        model.addAttribute("resultGoods", resultGoods);
        model.addAttribute("frontUser", frontUser);
        model.addAttribute("user", user);
        return "modules/user/supplydetail";
    }

    /**
     * 发送验证码
     * 先写死模板
     */
    @Autowired
    SMSValidate smsvalidate;

    @RequestMapping(value = "organizationForm")
    public String organizationForm(HttpServletRequest request, HttpServletResponse response, Model model) {
        FrontUser user = userService.getFrontUser();
        if(user==null){
            return "modules/user/organizationForm";
        }

        Organization organization = organizationService.findOrganizationById(user.getOrganizationId());
        String resultGoods = this.getOrganizationMainGoods(user.getOrganizationId());
        List<Gcategory> list = homeService.leftMenus();
        List<OrganizationType> organizationType = organizationService.findOrganizationType();
        request.setAttribute("organizationType", organizationType);
        request.setAttribute("list", list);
        request.setAttribute("mainGoods", resultGoods);
        request.setAttribute("organization", organization);
        request.setAttribute("user", user);

        return "modules/user/organizationForm";
    }


    @RequestMapping(value = "organization", method = RequestMethod.POST)
    public String organization(
            HttpServletRequest request,
            FrontUser user, String area,
            @RequestParam(required = false, value = "mainGoods") String[] mainGoods,Model model,Organization organization,HttpServletResponse response) {
    	organization.preInsert();
        FrontUser currentUser = userService.getFrontUser();
        if(null==mainGoods||mainGoods.length==0){
        	addMessage(model, "请选择主营品种");
        }
        if(!PatternUtil.isUserName(organization.getName())) {
            addMessage(model, "请输入正确的公司名称");
        }
        if(StringUtils.isBlank(organization.getType())){
        	addMessage(model, "请选择公司类型");
        }
        if(model.asMap().get("message")!=null){
        	return this.organizationForm(request, response, model);
        }else{
        	currentUser.setStatue("1");
        	organizationService.prefectUser(organization, mainGoods, currentUser);
        }
        return "modules/user/registerSuccess";
    }

    /**
     * 添加web用户
     * 依赖数据字典:
     *
     *      用户office字段：     new Office(DictUtils.getDictValue("webUserOffice", "defaultOffice", "1"))
     *      用户company字段：    new Office(DictUtils.getDictValue("webUserCompany", "defaultOffice", "0"))
     *      createUser字段：    DictUtils.getDictValue("defaultUserId", "webUserCreateUser", "1") //默认为管理员
     *      userType字段：      DictUtils.getDictValue("PcWeb", "sys_user_type", "4")
     *      供应商角色Enname：   DictUtils.getDictValue( "defaultUserId", "webUserRole","supply")
     *      采购商角色Enname：   DictUtils.getDictValue("defaultUserId", "webUserRole", "demand")
     *
     * @param user
     * @param request
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "user", method = RequestMethod.POST)
    public
    @ResponseBody
    Response user(FrontUser user, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        //验证用户名
        if(!PatternUtil.isUserName(user.getName())) {
            return Response.error(-1, "请输入正确的姓名");
        }
        //验证手机号，并将手机号设置到登陆名
        if(!SMSValidate.validateMObile(user.getMobile())){
            return Response.error(-2, "请输入正确的手机号");
        }else{
            user.setLoginName(user.getMobile());
        }
        //密码
        if(!PatternUtil.isPassword(user.getNewPassword())){
            return Response.error(-3, "请输入正确的密码");
        }

        //验证用户是否存在
        if (userService.getUserByMobile(user.getMobile()) >= 1) {
            return Response.error(-4, "用户已经存在");
        }

        //短信验证码
        Response result = smsvalidate.validateSMS(user.getMobile(), request, request.getParameter("smsCode"));
        if (result.isError()) {
            return Response.error(-3,"短信验证码失败");
        }
        //设置用户类型
        user.setUserType(DictUtils.getDictValue("PcWeb", "sys_user_type", "4"));
        //保存用户之前先加密密码
        if (StringUtils.isNotBlank(user.getNewPassword())) {
            user.setPassword(SystemService.entryptPassword(user.getNewPassword()));
        }
        Principal principal = null;
        // 保存用户信息
        try{
        	user.setStatue("1");
            user.preInsert();
            user.setCreateIp(request.getRemoteAddr());
            principal = securityService.newPrincipalFromPassword(user.getLoginName(), user.getPassword(), LoginType.pcWeb.toString());
            Person currentPerson = securityService.register(principal);
            user.setPersonId(currentPerson.getId());
            userService.registerFrontUser(user);
        }catch (Exception e){
            return Response.error(-5, "保存用户失败");
        }
        try{
            securityService.login(principal);
            SecurityUtils.getSubject().isAuthenticated();
        }catch (AuthenticationException e){
            return Response.error(-6, "登陆失败");
        }
        addMessage(redirectAttributes, "保存用户'" + user.getLoginName() + "'成功");
        return Response.success();
    }

    @RequestMapping(value = "register")
    public String register(FrontUser frontUser, HttpServletRequest request, HttpServletResponse response, Model model) {
        FrontUser currentUser = userService.getFrontUser();
        if(currentUser!=null){
    		return "redirect:" + frontPath + "/userCenter";
    	}
        return "modules/user/register";
    }

    @RequestMapping(value = "loginForm")
    public String loginForm(FrontUser frontUser, HttpServletRequest request, HttpServletResponse response, Model model) {
        FrontUser currentUser = userService.getFrontUser();
        if (currentUser != null) {
            return "redirect:" + frontPath + "/userCenter";
        }
        return "modules/user/loginForm";
    }

    @RequestMapping(value = "userInfo")
    public String userInfo(HttpServletRequest request, String ifApprove,Model model) {
        FrontUser user = userService.getFrontUser();
        if (("".equals(user.getOrganizationId()) || null == user.getOrganizationId()) & (null == ifApprove || "".equals(ifApprove))) {
            request.setAttribute("user", user);
            return "modules/user/beforeUserForm";
        } else {
            Organization organization = organizationService.findOrganizationById(user.getOrganizationId());
            String resultGoods = this.getOrganizationMainGoods(user.getOrganizationId());
            List<Gcategory> list = homeService.leftMenus();
            List<OrganizationType> organizationType = organizationService.findOrganizationType();
            request.setAttribute("organizationType", organizationType);
            request.setAttribute("list", list);
            request.setAttribute("mainGoods", resultGoods);
            request.setAttribute("organization", organization);
            request.setAttribute("user", user);
            return "modules/user/userForm";
        }
    }

    @RequestMapping(value = "updateUser")
    public String updateUser(
            HttpServletRequest request,
            @RequestParam(required = false, value = "organizationName") String organizationName,
            Organization organization,
            FrontUser user,
            @RequestParam(required = false, value = "mainGoods") String[] mainGoods,Model model,RedirectAttributes redirectAttributes,String choiceMainGoods) {
        FrontUser currentUser = userService.getFrontUser();
        user.preUpdate();
        if (user.getOrganizationId() != null && (!("".equals(user.getOrganizationId())))) {
        	user.setMobile(currentUser.getMobile());
            organization.preUpdate();
            Map map = new HashMap();
            map.put("id", user.getOrganizationId());
            organization.setName(organizationName);
            organization.setId(user.getOrganizationId());
            if(StringUtils.isBlank(choiceMainGoods)){
                if (null == mainGoods || 0 == mainGoods.length) {
                    addMessage(model, "请选择主营品种");
				}else{
                    map.put("mainGoods", InfoUtils.getMainGoods(mainGoods));
                }
			}
            if(StringUtils.isNotBlank(user.getPhone())){
            	if(!PatternUtil.isPhone(user.getPhone())){
            		addMessage(model, "请输入正确的固定电话");
                }
            }
            if(StringUtils.isNotBlank(user.getEmail())){ 
            	if(!PatternUtil.isEmail(user.getEmail())){
            		addMessage(model, "请输入正确的电子邮件地址");
            	}
            }
            if(StringUtils.isNotBlank(user.getQq())){
            	if(!PatternUtil.isQQ(user.getQq())){
            		addMessage(model, "请输入正确的QQ号码");
            	}
            }
            if(StringUtils.isNotBlank(user.getFaxNumber())){
            	if(!PatternUtil.isFaxNumber(user.getFaxNumber())){
            		addMessage(model, "请输入正确的传真号");
            	}
            }
            if(model.asMap().get("message")!=null){
            	return this.userInfo(request, null, model);
            }else{
            	organizationService.updateUserAndOrganization(user, organization, map);
            }
        } else {
            user.setPassword(currentUser.getPassword());
        	//User数据库字段验证
        	if (!beanValidator(model, user)) {
    			return this.userInfo(request, null, model);
        	}
        	user.setMobile(currentUser.getMobile());
            organizationService.updateUser(user);
        }
        return "redirect:" + frontPath + "/userCenter";
    }

    @RequestMapping(value = "changePassword")
    public String changePassword(Model model) {
        return "modules/user/changePass";
    }

    /**
     * 用户登录
     * @param request
     * @return
     */
    @RequestMapping(value = "userLogin")
    @ResponseBody
    public String userLogin(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String validateCode = request.getParameter("validateCode");
        String loginType = request.getParameter("loginType");
        try {
            Principal principal = securityService.newPrincipalFromPassword(username, password, loginType);
            securityService.login(principal);
            return "1";
        } catch (Exception e) {
            logger.debug("登陆失败" + e.getMessage());
        }
        return null;
    }

    @RequestMapping(value = "perfectUser")
    public String perfectUser(HttpServletRequest request,Model model,Organization organization, @RequestParam(required = false, value = "mainGoods") String[] mainGoods) {
        organization.preInsert();
        FrontUser currentUser = userService.getFrontUser();
//        currentUser.setName(name);
        //organization.setUpdateBy(currentUser);
        //User数据库字段验证
        beanValidator(model, currentUser);
        //organization数据库字段验证
        beanValidator(model, organization);
        if(null==mainGoods||mainGoods.length==0){
        	addMessage(model, "请选择主营品种");
        }
        if(!PatternUtil.isUserName(organization.getName())) {
            addMessage(model, "请输入正确的公司名称");
        }
        if(StringUtils.isBlank(organization.getType())){
        	addMessage(model, "请选择公司类型");
        }
        if(model.asMap().get("message")!=null){
        	return this.perfectUserOrganization(request, model);
        }else{
        	currentUser.setStatue("1");
        	organizationService.prefectUser(organization, mainGoods, currentUser);
        }
        return "modules/user/perfectUserSuccess";
    }

    @RequestMapping(value = "perfectUserOrganization")
    public String perfectUserOrganization(HttpServletRequest request,Model model) {
        FrontUser currentUser = userService.getFrontUser();
        List<Gcategory> list = homeService.leftMenus();
        List<OrganizationType> organizationType = organizationService.findOrganizationType();
        request.setAttribute("organizationType", organizationType);
        request.setAttribute("list", list);
        request.setAttribute("user", currentUser);
        return "modules/user/perfectUser";
    }

    private String getOrganizationMainGoods(String organizationId) {
        List<String> mainGoods = organizationService.findOrganziationMainGoodsbyId(organizationId);
        StringBuilder resultGoods = new StringBuilder();
        boolean flag = false;
        for (String string : mainGoods) {
            if (flag) {
                resultGoods.append(",");
            } else {
                flag = true;
            }
            resultGoods.append(string);
        }
        return resultGoods.toString();
    }
    @RequestMapping(value = "forgetPwd")
    public String forgetPwd(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "modules/user/password1";
    }
}
