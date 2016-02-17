package com.wk.p3.greenmall.modules.deal.web;

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
import com.wk.p3.greenmall.modules.deal.entity.SupplyDemandInfo;
import com.wk.p3.greenmall.modules.deal.service.impl.SupplyDemandInfoService;

/**
 * 业务订单Controller
 * @author lf
 * @version 2015-12-25
 */
@Controller
@RequestMapping(value = "${adminPath}/deal/supplyDemandInfo")
public class SupplyDemandInfoController extends BaseController {

	@Autowired
	private SupplyDemandInfoService supplyDemandInfoService;
	
	@ModelAttribute
	public SupplyDemandInfo get(@RequestParam(required=false) String id) {
		SupplyDemandInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = supplyDemandInfoService.get(id);
		}
		if (entity == null){
			entity = new SupplyDemandInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("deal:supplyDemandInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(SupplyDemandInfo supplyDemandInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SupplyDemandInfo> page = supplyDemandInfoService.findPage(new Page<SupplyDemandInfo>(request, response), supplyDemandInfo); 
		model.addAttribute("page", page);
		return "modules/deal/supplyDemandInfoList";
	}

	@RequiresPermissions("deal:supplyDemandInfo:view")
	@RequestMapping(value = "form")
	public String form(SupplyDemandInfo supplyDemandInfo, Model model) {
		model.addAttribute("supplyDemandInfo", supplyDemandInfo);
		return "modules/deal/supplyDemandInfoForm";
	}

	@RequiresPermissions("deal:supplyDemandInfo:edit")
	@RequestMapping(value = "save")
	public String save(SupplyDemandInfo supplyDemandInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, supplyDemandInfo)){
			return form(supplyDemandInfo, model);
		}
		supplyDemandInfoService.save(supplyDemandInfo);
		addMessage(redirectAttributes, "保存生成&quot;供求信息&quot;成功成功");
		return "redirect:"+Global.getAdminPath()+"/deal/supplyDemandInfo/?repage";
	}
	
	@RequiresPermissions("deal:supplyDemandInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(SupplyDemandInfo supplyDemandInfo, RedirectAttributes redirectAttributes) {
		supplyDemandInfoService.delete(supplyDemandInfo);
		addMessage(redirectAttributes, "删除生成&quot;供求信息&quot;成功成功");
		return "redirect:"+Global.getAdminPath()+"/deal/supplyDemandInfo/?repage";
	}
}