/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.user.web;

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
import com.wk.p3.greenmall.modules.user.entity.UserRelation;
import com.wk.p3.greenmall.modules.user.service.UserRelationService;

/**
 * 用户之间的关系Controller
 * @author cc
 * @version 2016-01-05
 */
@Controller
@RequestMapping(value = "${adminPath}/user/userRelation")
public class UserRelationController extends BaseController {

	@Autowired
	private UserRelationService userRelationService;
	
	@ModelAttribute
	public UserRelation get(@RequestParam(required=false) String id) {
		UserRelation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = userRelationService.get(id);
		}
		if (entity == null){
			entity = new UserRelation();
		}
		return entity;
	}
	
	@RequiresPermissions("user:userRelation:view")
	@RequestMapping(value = {"list", ""})
	public String list(UserRelation userRelation, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<UserRelation> page = userRelationService.findPage(new Page<UserRelation>(request, response), userRelation); 
		model.addAttribute("page", page);
		return "modules/user/userRelationList";
	}

	@RequiresPermissions("user:userRelation:view")
	@RequestMapping(value = "form")
	public String form(UserRelation userRelation, Model model) {
		model.addAttribute("userRelation", userRelation);
		return "modules/user/userRelationForm";
	}

	@RequiresPermissions("user:userRelation:edit")
	@RequestMapping(value = "save")
	public String save(UserRelation userRelation, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, userRelation)){
			return form(userRelation, model);
		}
		if(userRelation.getId().equals(userRelation.getOtherUser())){
			addMessage(redirectAttributes, "不能保存自己与自己的关联");
		}else{
			try {
				userRelationService.findAndSave(userRelation);
				addMessage(redirectAttributes, "用户关系添加成功");
			} catch (Exception e) {
				addMessage(redirectAttributes, "保存用户关系失败,已经有相同的关系存在");
			}
		}
		return "redirect:"+Global.getAdminPath()+"/user/userRelation/?repage";
	}
	
	@RequiresPermissions("user:userRelation:edit")
	@RequestMapping(value = "delete")
	public String delete(UserRelation userRelation, RedirectAttributes redirectAttributes) {
		userRelationService.delete(userRelation);
		addMessage(redirectAttributes, "删除用户关系表成功");
		return "redirect:"+Global.getAdminPath()+"/user/userRelation/?repage";
	}

}