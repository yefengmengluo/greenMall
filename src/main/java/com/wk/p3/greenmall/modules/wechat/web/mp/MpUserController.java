/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.wechat.web.mp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wk.p3.greenmall.common.config.Global;
import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.common.web.BaseController;
import com.wk.p3.greenmall.common.utils.StringUtils;
import com.wk.p3.greenmall.modules.wechat.entity.mp.MpUser;
import com.wk.p3.greenmall.modules.wechat.service.mp.MpUserService;

/**
 * 微信用户Controller
 * @author cc
 * @version 2015-11-25
 */
@Controller
@RequestMapping(value = "${adminPath}/wechat/mp/mpUser")
public class MpUserController extends BaseController {

	@Autowired
	private MpUserService mpUserService;
	
	@ModelAttribute
	public MpUser get(@RequestParam(required=false) String id) {
		MpUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mpUserService.get(id);
		}
		if (entity == null){
			entity = new MpUser();
		}
		return entity;
	}
	
//	@RequiresPermissions("wechat:mp:mpUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(MpUser mpUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MpUser> page = mpUserService.findPage(new Page<MpUser>(request, response), mpUser); 
		model.addAttribute("page", page);
		return "modules/wechat/mp/mpUserList";
	}

	@RequiresPermissions("wechat:mp:mpUser:view")
	@RequestMapping(value = "form")
	public String form(MpUser mpUser, Model model) {
		model.addAttribute("mpUser", mpUser);
		return "modules/wechat/mp/mpUserForm";
	}

	@RequiresPermissions("wechat:mp:mpUser:edit")
	@RequestMapping(value = "save")
	public String save(MpUser mpUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mpUser)){
			return form(mpUser, model);
		}
		mpUserService.save(mpUser);
		addMessage(redirectAttributes, "保存保存微信用户成功");
		return "redirect:"+Global.getAdminPath()+"/wechat/mp/mpUser/?repage";
	}
	
	@RequiresPermissions("wechat:mp:mpUser:edit")
	@RequestMapping(value = "delete")
	public String delete(MpUser mpUser, RedirectAttributes redirectAttributes) {
		mpUserService.delete(mpUser);
		addMessage(redirectAttributes, "删除保存微信用户成功");
		return "redirect:"+Global.getAdminPath()+"/wechat/mp/mpUser/?repage";
	}

}