package com.wk.p3.greenmall.modules.user.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wk.p3.greenmall.modules.info.entity.Info;
import com.wk.p3.greenmall.modules.info.service.InfoService;
import com.wk.p3.greenmall.modules.info.utils.InfoUtils;
import com.wk.p3.greenmall.modules.security.Person;
import com.wk.p3.greenmall.modules.user.UserService;
import com.wk.p3.greenmall.modules.user.entity.FrontUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.common.utils.StringUtils;
import com.wk.p3.greenmall.common.web.BaseController;
import com.wk.p3.greenmall.modules.info.entity.Gcategory;
import com.wk.p3.greenmall.modules.info.service.HomeService;
import com.wk.p3.greenmall.modules.sys.entity.Office;
import com.wk.p3.greenmall.modules.sys.entity.User;
import com.wk.p3.greenmall.modules.sys.service.SystemService;
import com.wk.p3.greenmall.modules.sys.utils.DictUtils;
import com.wk.p3.greenmall.modules.sys.utils.UserUtils;
import com.wk.p3.greenmall.modules.user.entity.AdminUser;
import com.wk.p3.greenmall.modules.user.entity.Organization;
import com.wk.p3.greenmall.modules.user.entity.OrganizationType;
import com.wk.p3.greenmall.modules.user.service.AdminUserService;
import com.wk.p3.greenmall.modules.user.service.OrganizationService;

/**
 * Created by cc on 15-12-8.
 */
@Controller("adminUserController")
@RequestMapping(value = "${adminPath}/user/userAdmin")
public class UserController extends BaseController {

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private HomeService homeService;

	@Autowired
	private AdminUserService adminUserService;

    @Autowired
    private UserService userService;

    @Autowired
    private InfoService infoService;


    @RequestMapping(value = "index")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model, AdminUser adminUser) {
        FrontUser frontUser = new FrontUser();
        Integer userCount = userService.getCountUser(null);
        frontUser.setStatue("1");
        Integer beforeFrontuserCount = userService.getCountUser(frontUser);
        Info info = new Info();
        info.setType("supply");
        Integer supplyCount = infoService.findSupplyAndDemandCount(info);
        info.setType("demand");
        Integer demandCount = infoService.findSupplyAndDemandCount(info);
        info.setStatue(0);
        info.setType("supply");
        Integer beforeSupplyCount = infoService.findSupplyAndDemandCount(info);
        info.setType("demand");
        Integer beforeDemandCount = infoService.findSupplyAndDemandCount(info);
        adminUser.setStatue("1");
        Page<AdminUser> page = adminUserService.userList(new Page<AdminUser>(request, response), adminUser);
        List<AdminUser> list = page.getList();
        for (AdminUser currentUser : list) {
            String resultGoods = this.getOrganizationMainGoods(currentUser.getOrganizationId());
            currentUser.setMainGoods(resultGoods);
        }
        model.addAttribute("page", page);
        //会员数
        model.addAttribute("userCount", userCount);
        //待审核会员数
        model.addAttribute("beforeFrontuserCount", beforeFrontuserCount);
        //供应信息数
        model.addAttribute("supplyCount", supplyCount);
        //求购信息数
        model.addAttribute("demandCount", demandCount);
        //待审核求购信息数
        model.addAttribute("beforeInfoCount", beforeDemandCount);
        //待审核供应信息数
        model.addAttribute("beforeSupplyCount", beforeSupplyCount);
        return "modules/sys/index";
    }

    /**
	 * 后台管理用户列表
	 * @param request
	 * @param response
	 * @param model
	 * @param adminUser
	 * @return
	 */
	@RequestMapping(value = "userList")
	public String userList(HttpServletRequest request, HttpServletResponse response, Model model,AdminUser adminUser) {
        Page<AdminUser> page = adminUserService.userList(new Page<AdminUser>(request, response), adminUser);
        List<AdminUser> list = page.getList();
        for (AdminUser currentUser : list) {
            String resultGoods = this.getOrganizationMainGoods(currentUser.getOrganizationId());
            currentUser.setMainGoods(resultGoods);
        }
        model.addAttribute("page", page);
        return "modules/user/adminUserList";
	}
	/**
	 * 查询单个用户
	 * @param request
	 * @param response
	 * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "findUserInfoById")
	public String findUserInfoById(HttpServletRequest request, HttpServletResponse response, Model model,String id,String ifApprove){
        FrontUser user = userService.getUserByUserId(id);
        user.getRemarks();
		if (StringUtils.isBlank(user.getOrganizationId())&&StringUtils.isBlank(ifApprove)) {
			request.setAttribute("user", user);
            return "modules/user/beforeAdminUserForm";
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
			return "modules/user/adminUpdateUser";
		}
	}

	@RequestMapping(value = "perfectUserOrganization")
	public String perfectUserOrganization(HttpServletRequest request,Model model,String userId) {
        FrontUser user = userService.getUserByUserId(userId);
        List<Gcategory> list = homeService.leftMenus();
		List<OrganizationType> organizationType = organizationService.findOrganizationType();
		request.setAttribute("organizationType", organizationType);
		request.setAttribute("list", list);
		request.setAttribute("user", user);
		return "modules/user/adminPrefectUser";
	}

	@RequestMapping(value = "updateUser")
	public String updateUser(
			@RequestParam(required = false, value = "type") String type,
			HttpServletRequest request,
			@RequestParam(required = false, value = "organizationName") String organizationName,
            FrontUser user, String area,
            @RequestParam(required = false, value = "mainGoods") String[] mainGoods,
			@RequestParam(required = false, value = "province") String province,
			@RequestParam(required = false, value = "city") String city,String detailArea,Model model,RedirectAttributes redirectAttributes,HttpServletResponse response,String choiceMainGoods) {
        FrontUser currentUser = userService.getUserByPersonId(user.getId());
        user.preUpdate();
		if (user.getOrganizationId() != null && (!("".equals(user.getOrganizationId())))) {
			Organization organization = new Organization();
			organization.setArea(area);
			organization.setDetailArea(detailArea);
			organization.preUpdate();
			organization.setType(type);
			Map map = new HashMap();
			map.put("id", user.getOrganizationId());
			organization.setName(organizationName);
			organization.setCityId(city);
			organization.setProvinceId(province);
			organization.setId(user.getOrganizationId());
			if(StringUtils.isBlank(choiceMainGoods)){
				if(null==mainGoods||0==mainGoods.length){
					model.addAttribute("message", "请选择主营品种");
					return this.findUserInfoById(request, response,model,user.getId(),null);
				}else{
					map.put("mainGoods", InfoUtils.getMainGoods(mainGoods));
				}
			}
			organizationService.updateUserAndOrganization(user, organization, map);
		} else {
			if (!beanValidator(model, user)) {
				return this.findUserInfoById(request, response, model,user.getId(),null);
			}
			organizationService.updateUser(user);
		}

		return "redirect:" + adminPath + "/user/userAdmin/userList";
	}

	@RequestMapping(value = "perfectUser")
	public String perfectUser(HttpServletRequest request,Model model,String userId,Organization organization, @RequestParam(required = false, value = "mainGoods") String[] mainGoods, @RequestParam(required = false, value = "province") String province, @RequestParam(required = false, value = "city") String city) {
        FrontUser user = userService.getUserByUserId(userId);
        organization.preInsert();
		organization.setCityId(city);
		organization.setProvinceId(province);
		//User数据库字段验证
		boolean beanValidator = beanValidator(model, user);
		if(!beanValidator){
			return this.perfectUserOrganization(request, model, userId);
		}
		//organization数据库字段验证
		boolean beanValidator2 = beanValidator(model, organization);
		if(!beanValidator2){
			return this.perfectUserOrganization(request, model, userId);
		}
		if(StringUtils.isAnyBlank(mainGoods)){
			addMessage(model, "请选择主营品种");
			return this.perfectUserOrganization(request, model,userId);
		}
		organizationService.prefectUser(organization, InfoUtils.getMainGoods(mainGoods), user);
		return "redirect:" + adminPath + "/user/userAdmin/userList";
	}

    @RequestMapping(value = "approveUser")
    @ResponseBody
    public String approveUser(HttpServletRequest request, Model model, @RequestParam("statue") String statue, @RequestParam("userId") String userId) {
        User user = SystemService.currentUser();
        String result = adminUserService.approveUser(statue, userId, user);
        return result;
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
}
